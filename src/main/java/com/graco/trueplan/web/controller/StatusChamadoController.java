package com.graco.trueplan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.StatusChamado;
import com.graco.trueplan.service.StatusChamadoService;

@RestController
@RequestMapping("/api/v1/statuschamado")
@CrossOrigin("*")
public class StatusChamadoController {

	@Autowired
	private StatusChamadoService statusChamadoService;

	@PostMapping
	public ResponseEntity<StatusChamado> criarNovoStatusChamado(@RequestBody StatusChamado statusChamado) {
		StatusChamado statusChamadoNovo = statusChamadoService.save(statusChamado);
		return ResponseEntity.status(HttpStatus.OK).body(statusChamadoNovo);
	}

	@GetMapping
	public ResponseEntity<List<StatusChamado>> listarTodosStatusChamado() {
		List<StatusChamado> allStatusChamado = statusChamadoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(allStatusChamado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StatusChamado> listarStatusChamado (@PathVariable Long id) {
		StatusChamado statusChamado = statusChamadoService.findById(id);
		
		if(statusChamado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(statusChamado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StatusChamado> atualizarStatusChamado (@PathVariable Long id, @RequestBody StatusChamado statusChamado){
		StatusChamado statusChamadoEncontrada = statusChamadoService.findById(id);
		if(statusChamadoEncontrada != null) {
			statusChamadoService.save(statusChamado);
			return ResponseEntity.status(HttpStatus.OK).body(statusChamado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarStatusChamado (@PathVariable Long id) {
		StatusChamado statusChamado = statusChamadoService.findById(id);
		
		if(statusChamado != null) {
			statusChamadoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
