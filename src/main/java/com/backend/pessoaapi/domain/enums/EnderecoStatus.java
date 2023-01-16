package com.backend.pessoaapi.domain.enums;

public enum EnderecoStatus {
	Principal(1),
	Secundário(2);
	
	private int codigo;
	
	private EnderecoStatus(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public static EnderecoStatus valueOf(int codigo) {
		for (EnderecoStatus valor : EnderecoStatus.values()) {
			if (valor.getCodigo() == codigo) {
				return valor;
			}
		}
		throw new IllegalArgumentException("Cógido de Endereço Principal invalido");
	}
}
