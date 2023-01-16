package com.backend.pessoaapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.backend.pessoaapi.domain.Pessoa;
import com.backend.pessoaapi.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private PessoaRepository repo;

	@Override
	public void run(String... args) throws Exception {
	
	
		
		Pessoa p1 = new Pessoa(null, "Fulano de Tal", "13/09/2000");
		Pessoa p2 = new Pessoa(null, "Siclano S. P.", "26/08/1993");
		
		repo.saveAll(Arrays.asList(p1, p2));
		
	}
}
