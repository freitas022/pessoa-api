package com.backend.pessoaapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pessoaapi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    //
    boolean existsPessoaByNome(String nome);
}
