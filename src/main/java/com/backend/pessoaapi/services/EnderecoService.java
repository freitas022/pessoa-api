package com.backend.pessoaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pessoaapi.domain.Endereco;
import com.backend.pessoaapi.repositories.EnderecoRepository;
import com.backend.pessoaapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Endereco update(Long id, Endereco obj) {
		try {
		Endereco entity = enderecoRepository.getReferenceById(id);
		updateData(entity, obj);
		return enderecoRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Endereco entity, Endereco obj) {
		entity.setCep(obj.getCep());
		entity.setCidade(obj.getCidade());
		entity.setEnderecoStatus(obj.getEnderecoStatus());
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
	}

}
