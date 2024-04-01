package com.graco.trueplan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Cliente;
import com.graco.trueplan.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin("*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> criarNovoCliente(@RequestBody Cliente cliente) {
		Cliente clienteNovo = clienteService.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteNovo);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarCliente(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);

		if (cliente != null) {
			clienteService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}
}
