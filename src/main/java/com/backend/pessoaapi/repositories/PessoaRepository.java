package com.backend.pessoaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pessoaapi.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
