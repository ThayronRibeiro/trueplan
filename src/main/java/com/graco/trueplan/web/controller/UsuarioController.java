package com.graco.trueplan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Usuario;
import com.graco.trueplan.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Usuario> criarNovoUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioNovo = usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario != null) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);

		if (usuario != null) {
			usuarioService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}
}
