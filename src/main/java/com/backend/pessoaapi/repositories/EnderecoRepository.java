package com.backend.pessoaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pessoaapi.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
