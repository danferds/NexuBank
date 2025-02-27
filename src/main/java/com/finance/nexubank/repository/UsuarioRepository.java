package com.finance.nexubank.repository;

import com.finance.nexubank.model.AbstractUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<AbstractUsuario, UUID> {
    @Query("SELECT u FROM Usuario u WHERE TREAT(u AS PessoaFisica).cpf = :cpf")
    Optional<AbstractUsuario> findByCpf(@Param("cpf") String cpf);

    @Query("SELECT u FROM Usuario u WHERE TREAT(u AS PessoaJuridica).cnpj = :cnpj")
    Optional<AbstractUsuario> findByCnpj(@Param("cnpj") String cnpj);

    default boolean hasPendingTransactions(AbstractUsuario usuario) {
        return false;
    }
}
