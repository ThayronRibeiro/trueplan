package com.graco.trueplan.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Cliente;
import com.graco.trueplan.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> criarNovoCliente(@RequestBody Cliente cliente) {
		Cliente clienteNovo = clienteService.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteNovo);
	}
}
