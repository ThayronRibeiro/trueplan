package com.graco.trueplan.web.controller;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Chamado;
import com.graco.trueplan.service.ChamadoService;

@RestController
@RequestMapping("/api/v1/chamados")
@CrossOrigin("*")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	@PostMapping
	public ResponseEntity<Chamado> criarNovoChamado(@RequestBody Chamado chamado) {
		Chamado chamadoNovo = chamadoService.save(chamado);
		return ResponseEntity.status(HttpStatus.CREATED).body(chamadoNovo);

	}

	@GetMapping("/datas")
	public ResponseEntity<List<String>> listarDatasAberturas() {
		List<String> datasChamados = chamadoService.listDates();
		return ResponseEntity.status(HttpStatus.OK).body(datasChamados);
	}

	@GetMapping("/{date}")
	public ResponseEntity<List<Chamado>> listarChamadosPorDatas(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		List<Chamado> chamadoEncontrados = chamadoService.findByDataAbertura(date);
		return ResponseEntity.status(HttpStatus.OK).body(chamadoEncontrados);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Chamado> encontrarChamado(@PathVariable Long id) {
		Chamado chamadoEncontrado = chamadoService.findById(id);
		if (chamadoEncontrado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(chamadoEncontrado);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<Chamado> atualizarChamado(@PathVariable Long id, @RequestBody Chamado chamado) {
		Chamado chamadoEncontrado = chamadoService.findById(id);
		if (chamadoEncontrado != null) {

			chamadoService.update(chamado);
			return ResponseEntity.status(HttpStatus.OK).body(chamado);

		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PutMapping("/reagendarChamado")
	public ResponseEntity<Chamado> reagendar (@RequestBody Chamado chamado){
		
		chamadoService.reagendar(chamado);

		return ResponseEntity.status(HttpStatus.OK).body(chamado);
	}
	
	@PutMapping("/reagendarChamados")
	public ResponseEntity<List<Chamado>> reagendarTodos (@RequestBody List<Chamado> chamados){
		
		List<Chamado> chamadosNovos = chamados.stream().map(chamado -> chamadoService.reagendar(chamado)).collect(Collectors.toList());
		

		return ResponseEntity.status(HttpStatus.OK).body(chamadosNovos);
	}
}
