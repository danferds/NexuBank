package com.finance.nexubank.repository;

import com.finance.nexubank.model.AbstractConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<AbstractConta, UUID> {
    Optional<AbstractConta> findByNumeroConta(String numeroConta);
    Optional<AbstractConta> findByNumeroContaAndAgencia(String numeroConta, String agencia);
}
