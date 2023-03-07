package com.backend.pessoaapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import com.backend.pessoaapi.exceptions.RegraNegocioException;
import com.backend.pessoaapi.models.Endereco;
import com.backend.pessoaapi.models.Pessoa;
import com.backend.pessoaapi.services.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api-pessoas")
public class PessoaController {

	
	final PessoaService service;
	
	public PessoaController(PessoaService service) {
		this.service = service;
	}
	//
	@PostMapping(value = "/registrar")
	public ResponseEntity<Object> salvarPessoa(@RequestBody @Valid PessoaDTO pessoaDto) {
		var novaPessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDto, novaPessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(novaPessoa));
	}
	//
	@GetMapping(value = "/pessoas")
	public ResponseEntity<List<Pessoa>> obterPessoas() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	//
	@GetMapping(value = "/pessoas/{id}")
	public ResponseEntity<Object> obterPessoaPorId(@PathVariable(value = "id") Long id) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		if (!pessoaOptional.isPresent()) {
			throw new RegraNegocioException("Não foi possível encontrar o usuário " + id);
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. Id: ." + id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	//
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<Object> atualizarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaDTO dto) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		if (!pessoaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. Id: ." + id);
		}		
		var pessoa = new Pessoa();
		BeanUtils.copyProperties(dto, pessoa);
		pessoa.setId(pessoaOptional.get().getId());
		pessoa.setNome(pessoaOptional.get().getNome());
		pessoa.setDtNascimento(pessoaOptional.get().getDtNascimento());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(pessoa));
	}
	//
	@DeleteMapping(value="/deletar/{id}")
	public ResponseEntity<Object> deletarPessoa(@PathVariable(value = "id") Long id) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		if (!pessoaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrada. Id: ." + id);
		}
		service.delete(pessoaOptional.get());		
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado da base de dados");
	}	
	//
	@GetMapping(value = "/pessoas/{id}/enderecos")
	public ResponseEntity<List<Endereco>> buscarEnderecos(@PathVariable Long id) {
		Optional<Pessoa> pessoaOptional = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get().getEnderecos());
	}
}
