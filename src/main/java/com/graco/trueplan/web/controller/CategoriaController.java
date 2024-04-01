package com.graco.trueplan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Categoria;
import com.graco.trueplan.service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<Categoria> criarNovaCategoria (@RequestBody Categoria categoria) {
		Categoria categoriaNova = categoriaService.save(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNova);		
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias () {
		List<Categoria> categorias = categoriaService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> listarCategoria (@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id);
		if(categoria != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCategoria (@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id);
		if(categoria != null) {
			categoriaService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}
