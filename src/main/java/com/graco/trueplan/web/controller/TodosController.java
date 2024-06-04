package com.graco.trueplan.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graco.trueplan.entity.Todos;
import com.graco.trueplan.service.TodosService;
import com.graco.trueplan.web.dto.TodosDTO;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {
	
	@Autowired
	private TodosService todosService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Todos> criarNovoTodo(@RequestBody TodosDTO dto){
		Todos novoTodo = todosService.save(modelMapper.map(dto, Todos.class));
		return ResponseEntity.status(HttpStatus.OK).body(novoTodo);
	}

}
