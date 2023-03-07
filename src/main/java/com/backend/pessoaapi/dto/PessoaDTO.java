package com.backend.pessoaapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.backend.pessoaapi.models.Endereco;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String dtNascimento;
	
	private List<Endereco> enderecos = new ArrayList<>();

}
