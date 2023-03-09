package com.backend.pessoaapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pessoaapi.dto.PessoaDTO;
import com.backend.pessoaapi.models.Endereco;
import com.backend.pessoaapi.models.Pessoa;
import com.backend.pessoaapi.services.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	
	final PessoaService service;
	
	public PessoaController(PessoaService service) {
		this.service = service;
	}
	//
	@PostMapping(value = "/save")
	public ResponseEntity<Object> savePessoa(@RequestBody @Valid PessoaDTO pessoaDto) {
		var novaPessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDto, novaPessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(novaPessoa));
	}
	//
	@GetMapping(value = "/getAll")
	public ResponseEntity<Page<Pessoa>> getPessoas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC ) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}
	//
	@GetMapping(value = "/findBy/{id}")
	public ResponseEntity<Object> getPessoaById(@PathVariable(value = "id") UUID id) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		if (!pessoaOptional.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. Id: " + id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get());
	}
	//
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Object> updatePessoa(@PathVariable UUID id, @RequestBody @Valid PessoaDTO objDto) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		if (!pessoaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. Id: " + id);
		}	
		var pessoa = pessoaOptional.get();
		service.updateData(pessoa, objDto);	
		return ResponseEntity.status(HttpStatus.OK).body(service.save(pessoa));
	}
	//
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") UUID id) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		if (!pessoaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. Id: " + id);
		}
		service.delete(pessoaOptional.get());		
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado da base de dados.");
	}	
	//
	@GetMapping(value = "/{id}/enderecos")
	public ResponseEntity<List<Endereco>> findAllEnderecosByPessoa(@PathVariable UUID id) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get().getEnderecos());
	}
}
