package com.graco.trueplan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Tecnico;
import com.graco.trueplan.service.TecnicoService;

@RestController
@RequestMapping("/api/v1/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;
	
	@PostMapping
	public ResponseEntity<Tecnico> criarNovoTecnico (@RequestBody Tecnico tecnico) {
		Tecnico tecnicoNovo = tecnicoService.save(tecnico);
		return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoNovo);
	}
	
	@GetMapping
	public ResponseEntity<List<Tecnico>> listarTecnicos () {
		List<Tecnico> tecnicos = tecnicoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tecnicos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tecnico> listarTecnico (@PathVariable Long id) {
		Tecnico tecnico = tecnicoService.findById(id);
		
		if(tecnico != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tecnico);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarTecnico (@PathVariable Long id) {
		Tecnico tecnico = tecnicoService.findById(id);
		
		if(tecnico != null) {
			tecnicoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
