package com.backend.pessoaapi.services;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.pessoaapi.dto.PessoaDTO;
import com.backend.pessoaapi.models.Pessoa;
import com.backend.pessoaapi.repositories.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {
	//
	final PessoaRepository repository;
	//
	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}
	//
	@Transactional
	public Pessoa save (Pessoa pessoa) {
		 return repository.save(pessoa);
	}
	//
	public Page<Pessoa> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
	//
	public Optional<Pessoa> findById(UUID id) {
        return repository.findById(id);
    }	
	//
	@Transactional
	public void delete(Pessoa pessoa) {
		repository.delete(pessoa);
	}
	//
	public void updateData(Pessoa pessoa, PessoaDTO objDto) {
		pessoa.setNome(objDto.getNome());
		pessoa.setDtNascimento(objDto.getDtNascimento());
	}
	//
	public boolean existsPessoaByNome(String nome) {
		return repository.existsPessoaByNome(nome);
	}
}
