package com.backend.pessoaapi.dto;

import java.io.Serializable;

import com.backend.pessoaapi.domain.Pessoa;

public class PessoaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String dtNascimento;

	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.dtNascimento = obj.getDtNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

}
