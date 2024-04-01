package com.graco.trueplan.web.controller;

import java.util.Date;
import java.util.List;

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
	public ResponseEntity<Chamado> criarNovoChamado (@RequestBody Chamado chamado) {
		Chamado chamadoNovo = chamadoService.save(chamado);
		return ResponseEntity.status(HttpStatus.CREATED).body(chamadoNovo);
	} 
	
	@GetMapping("/datas")
	public ResponseEntity<List<String>> listarDatasAberturas () {
		List<String> datasChamados = chamadoService.listDates();
		return ResponseEntity.status(HttpStatus.OK).body(datasChamados);
	}
	
	@GetMapping("/{date}")
	public ResponseEntity<List<Chamado>> listarChamadosPorDatas (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<Chamado> chamadoEncontrados = chamadoService.findByDataAbertura(date);
		return ResponseEntity.status(HttpStatus.OK).body(chamadoEncontrados);
	}
}
