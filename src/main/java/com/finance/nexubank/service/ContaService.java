package com.finance.nexubank.service;

import com.finance.nexubank.dto.*;
import com.finance.nexubank.exception.ContaNotFoundException;
import com.finance.nexubank.factory.ContaCreator;
import com.finance.nexubank.mapper.ContaMapper;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.AbstractUsuario;
import com.finance.nexubank.model.TipoConta;
import com.finance.nexubank.finder.UsuarioFinder;
import com.finance.nexubank.repository.ContaRepository;
import com.finance.nexubank.resolver.ContaFactoryResolver;
import com.finance.nexubank.validation.TipoContaResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ContaService {

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    private final ContaRepository contaRepository;
    private final UsuarioFinder usuarioFinder;
    private final ContaFactoryResolver contaFactoryResolver;
    private final TipoContaResolver tipoContaResolver;
    private final LimiteService limiteService;

    public ContaService(ContaRepository contaRepository,
                        UsuarioFinder usuarioFinder,
                        ContaFactoryResolver contaFactoryResolver,
                        TipoContaResolver tipoContaResolver,
                        LimiteService limiteService) {
        this.contaRepository = contaRepository;
        this.usuarioFinder = usuarioFinder;
        this.contaFactoryResolver = contaFactoryResolver;
        this.tipoContaResolver = tipoContaResolver;
        this.limiteService = limiteService;
    }

    public ContaResponseDTO criarConta(ContaCriacaoDTO dto) {
        AbstractUsuario usuario = usuarioFinder.findByDocumento(dto.getDocumentoUsuario());
        TipoConta tipoConta = tipoContaResolver.resolverTipoConta(dto);
        ContaCreator factory = contaFactoryResolver.resolve(tipoConta);
        AbstractConta conta = factory.criarConta(dto);
        conta.setUsuario(usuario);

        limiteService.atualizarLimites(conta, usuario.getRendaMensal());

        contaRepository.save(conta);
        logger.info("Conta criada com sucesso: {}", conta.getNumeroConta());
        return ContaMapper.mapearRespostaDTO(conta);
    }

    @Transactional
    public DepositoResponseDTO depositar(DepositoDTO dto) {
        AbstractConta conta = contaRepository.findByNumeroContaAndAgencia(dto.getNumeroConta(), dto.getAgencia())
                .orElseThrow(() -> new ContaNotFoundException(
                        "Conta não encontrada com número " + dto.getNumeroConta() + " e agência " + dto.getAgencia()));
        conta.setSaldo(conta.getSaldo() + dto.getValor());
        contaRepository.save(conta);

        DepositoResponseDTO response = new DepositoResponseDTO();
        response.setValorDepositado(dto.getValor());
        response.setNumeroConta(conta.getNumeroConta());
        response.setAgencia(dto.getAgencia());

        logger.info("Depósito de {} realizado na conta {} da agência {}", dto.getValor(), dto.getNumeroConta(), dto.getAgencia());

        return response;
    }

    public RendimentoPoupancaResponseDTO calcularRendimentoMensalPoupanca(UUID id) {
        AbstractConta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada"));

        if (!(conta instanceof com.finance.nexubank.model.ContaPoupanca)) {
            throw new IllegalArgumentException("O rendimento mensal só pode ser calculado para contas poupança.");
        }

        com.finance.nexubank.model.ContaPoupanca contaPoupanca = (com.finance.nexubank.model.ContaPoupanca) conta;
        double rendimento = com.finance.nexubank.utils.ContaPoupancaUtils.calcularRendimentoMensal(contaPoupanca.getSaldo());
        contaPoupanca.setSaldo(contaPoupanca.getSaldo() + rendimento);
        contaRepository.save(contaPoupanca);

        RendimentoPoupancaResponseDTO response = new RendimentoPoupancaResponseDTO();
        response.setRendimento(rendimento);
        response.setNovoSaldo(contaPoupanca.getSaldo());
        response.setNumeroConta(contaPoupanca.getNumeroConta());

        logger.info("Rendimento mensal de {} calculado para a conta poupança {}. Novo saldo: {}",
                rendimento, contaPoupanca.getNumeroConta(), contaPoupanca.getSaldo());

        return response;
    }
}
