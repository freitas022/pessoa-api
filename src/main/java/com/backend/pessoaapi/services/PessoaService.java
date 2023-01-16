package com.backend.pessoaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pessoaapi.domain.Pessoa;
import com.backend.pessoaapi.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repo;

	public List<Pessoa> findAll() {
		return repo.findAll();
	}
	
	public Pessoa findById(Long id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.get();
	}

	public Pessoa insert(Pessoa obj) {
		return repo.save(obj);
	}

	public Pessoa update(Long id, Pessoa obj) {
		Pessoa entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Pessoa entity, Pessoa obj) {
		entity.setNome(obj.getNome());
		entity.setDtNascimento(obj.getDtNascimento());
	}

}
