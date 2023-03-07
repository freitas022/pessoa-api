package com.backend.pessoaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pessoaapi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
