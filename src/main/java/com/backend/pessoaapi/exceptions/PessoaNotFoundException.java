package com.backend.pessoaapi.exceptions;

public class PessoaNotFoundException extends RuntimeException {
    
    public PessoaNotFoundException(Object id) {
        super("Usuário não encontrado. Id: " + id);
    }
}
