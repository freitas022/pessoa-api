package com.backend.pessoaapi.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pessoaapi.domain.Pessoa;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResources {

	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Pessoa p1 = new Pessoa(1L, "Matheus", sdf.parse("13/09/2000"));
		Pessoa p2 = new Pessoa(1L, "Elias", sdf.parse("02/11/1985"));

		List<Pessoa> list = new ArrayList<>();
		list.addAll(Arrays.asList(p1, p2));
		return ResponseEntity.ok().body(list);
	}
}
