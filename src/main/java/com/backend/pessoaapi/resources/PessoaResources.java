package com.backend.pessoaapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.pessoaapi.domain.Endereco;
import com.backend.pessoaapi.domain.Pessoa;
import com.backend.pessoaapi.dto.PessoaDTO;
import com.backend.pessoaapi.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResources {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAll() {

		List<Pessoa> list = service.findAll();
		List<PessoaDTO> listDto = list.stream().map(x -> new PessoaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		Pessoa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/enderecos")
	public ResponseEntity<List<Endereco>> buscarEnderecos(@PathVariable Long id) {
		Pessoa obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getEnderecos());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserir (@RequestBody Pessoa obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
