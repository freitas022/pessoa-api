package com.backend.pessoaapi.controllers;

import java.util.List;
import java.util.Optional;

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
import com.backend.pessoaapi.services.PessoaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api-enderecos")
public class EnderecoController {
	
	final EnderecoService enderecoService;
	final PessoaService pessoaService;
	
	public EnderecoController(EnderecoService enderecoService, PessoaService pessoaService) {
		this.enderecoService = enderecoService;
		this.pessoaService = pessoaService;
	}
	//
	@Transactional
	@PostMapping(value = "/registrar")
	public ResponseEntity<Object> criarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {		
		var novoEndereco = new Endereco();
		BeanUtils.copyProperties(enderecoDTO, novoEndereco);		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(novoEndereco));
	}
	//
	@GetMapping(value = "/enderecos")
	public ResponseEntity<List<Endereco>> obterEnderecos() {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
	}	
	//
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<Object> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
		Optional<Endereco> enderecoOptional = enderecoService.findById(id);
		if (!enderecoOptional.isPresent()) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado. Id: ." + id);
		}
		
		var endereco = new Endereco();
		BeanUtils.copyProperties(enderecoDTO, endereco);
		endereco.setLogradouro(enderecoOptional.get().getLogradouro());
		endereco.setNumero(enderecoOptional.get().getNumero());
		endereco.setCidade(enderecoOptional.get().getCidade());
		endereco.setEstado(enderecoOptional.get().getEstado());
		
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco));
	}
}
