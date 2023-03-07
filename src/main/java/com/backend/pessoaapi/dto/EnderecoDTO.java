package com.backend.pessoaapi.dto;

import java.io.Serializable;

import com.backend.pessoaapi.enums.Status;
import com.backend.pessoaapi.models.Pessoa;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	@NotNull
	private Integer numero;
	@NotBlank
	private String cidade;
	@NotBlank
	private String estado;	
	
	private Pessoa pessoa;
	@Enumerated(EnumType.STRING)
	private Status status;
	
}
