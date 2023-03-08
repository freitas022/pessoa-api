package com.backend.pessoaapi.services;

import java.util.List;
import java.util.Optional;

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
	public List<Pessoa> findAll() {
		return repository.findAll();
	}
	//
	public Optional<Pessoa> findById(Long id) {
		return repository.findById(id);	
	}
	//
	@Transactional
	public void delete(Pessoa pessoa) {
		repository.delete(pessoa);
	}
	public void updateData(Pessoa pessoa, PessoaDTO objDto) {
		pessoa.setNome(objDto.getNome());
		pessoa.setDtNascimento(objDto.getDtNascimento());
	}
}
