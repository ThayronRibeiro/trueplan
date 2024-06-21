package com.graco.trueplan.web.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.graco.trueplan.service.AbstractService;



public abstract class AbstractController<Entity, DTO, ID> {

	@Autowired
	AbstractService<Entity, DTO, ID> abstractService;
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Entity> create(@RequestBody Entity entity){
		Entity novaEntidade = abstractService.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaEntidade);
	}
	
	@GetMapping
	public ResponseEntity<List<Entity>> allList(){
		List<Entity> dto = abstractService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Entity> listEntity(@PathVariable ID id){
		Entity entity = abstractService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Entity> update(@PathVariable ID id, @RequestBody Entity entity){
		abstractService.findById(id);
		Entity entidadeAtualizada = abstractService.update(entity);
		return ResponseEntity.status(HttpStatus.OK).body(entidadeAtualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable ID id){
		abstractService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
