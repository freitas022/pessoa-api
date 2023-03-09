package com.backend.pessoaapi.models;

import java.io.Serializable;
import java.util.UUID;

import com.backend.pessoaapi.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, length = 9)
	private String cep;
	@Column(nullable = false, length = 30)
	private String logradouro;
	@Column(nullable = false)
	private Integer numero;
	@Column(nullable = false, length = 20)
	private String cidade;
	@Column(nullable = false, length = 15)
	private String estado;
	
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

}
