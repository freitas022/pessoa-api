package com.backend.pessoaapi.config;

//@Configuration
//@Profile("test")
//public class TestConfig implements CommandLineRunner{
//	
//	@Autowired
//	private PessoaRepository pessoaRepository;
//	
//	@Autowired
//	private EnderecoRepository enderecoRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//	
//	
//		
//		Pessoa p1 = new Pessoa(null, "Pessoa 1", "13-09-2000");
//		Pessoa p2 = new Pessoa(null, "Pessoa 2", "26-08-1993");
//		
//		Endereco e1 = new Endereco(null, "Rua A", "28737-234", 123, "Rio de Janeiro - RJ", EnderecoStatus.PRINCIPAL, p1);
//		Endereco e2 = new Endereco(null, "Rua B", "28939-012", 456, "São Paulo - SP", EnderecoStatus.PRINCIPAL, p2);
//		Endereco e3 = new Endereco(null, "Rua C", "28939-035", 789, "Belo Horizonte - MG", EnderecoStatus.SECUNDARIO, p1);
//		Endereco e4 = new Endereco(null, "Rua D", "28544-168", 10, "Espírito Santo - ES", EnderecoStatus.SECUNDARIO, p2);
//		
//		pessoaRepository.saveAll(Arrays.asList(p1, p2));
//		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
//		
//	}
//}
