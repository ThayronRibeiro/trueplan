package com.graco.trueplan.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.Tecnico;
import com.graco.trueplan.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico save (Tecnico tecnico) {
		tecnico.setDataCadastro(LocalDateTime.now());
		return tecnicoRepository.save(tecnico);
	}
	
	public List<Tecnico> findAll () {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico findById (Long id) {
		return tecnicoRepository.findById(id).orElse(null);
	}
	
	public void deleteById (Long id) {
		tecnicoRepository.deleteById(id);
	}
}
