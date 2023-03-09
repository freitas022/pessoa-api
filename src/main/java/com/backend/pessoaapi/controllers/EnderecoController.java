package com.backend.pessoaapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pessoaapi.dto.EnderecoDTO;
import com.backend.pessoaapi.models.Endereco;
import com.backend.pessoaapi.services.EnderecoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	
	final EnderecoService enderecoService;
	
	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	//
	@Transactional
	@PostMapping(value = "/save")
	public ResponseEntity<Object> saveEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {		
		var novoEndereco = new Endereco();
		BeanUtils.copyProperties(enderecoDTO, novoEndereco);		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(novoEndereco));
	}
	//
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Endereco>> getAllEnderecos() {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
	}	
	//
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Object> updateEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDTO objDTO) {
		Optional<Endereco> enderecoOptional = enderecoService.findById(id);
		if (!enderecoOptional.isPresent()) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado. Id: ." + id);
		}		
		
		var endereco = enderecoOptional.get();
		enderecoService.updateData(endereco, objDTO);		
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco));
	}
}
