package com.graco.trueplan.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.Usuario;
import com.graco.trueplan.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario save(Usuario usuario) {
		usuario.setDataCadastro(LocalDateTime.now());
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	@Transactional
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}
}
