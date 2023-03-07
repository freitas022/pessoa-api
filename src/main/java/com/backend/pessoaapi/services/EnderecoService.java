package com.backend.pessoaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.pessoaapi.enums.Status;
import com.backend.pessoaapi.models.Endereco;
import com.backend.pessoaapi.repositories.EnderecoRepository;
import com.backend.pessoaapi.repositories.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

	final EnderecoRepository enderecoRepository;
	final PessoaRepository pessoaRepository;
	
	public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
		this.enderecoRepository = enderecoRepository;
		this.pessoaRepository = pessoaRepository;
	}
	//
	public Endereco save(Endereco novoEndereco) {
		enderecoRepository.save(novoEndereco);
		return novoEndereco;
	}
	//	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	//	
	public Optional<Endereco> findById(Long id) {
		return enderecoRepository.findById(id);
	}
	//
	@Transactional
	public void atualizaStatus(Integer id, Status enderecoStatus) {
		
	}
}
