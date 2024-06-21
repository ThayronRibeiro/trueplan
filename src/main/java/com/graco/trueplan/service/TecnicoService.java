package com.graco.trueplan.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.Tecnico;
import com.graco.trueplan.repository.TecnicoRepository;

@Service
public class TecnicoService extends AbstractService<Tecnico, Tecnico, Long>{

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico save (Tecnico tecnico) {
		tecnico.setDataCadastro(LocalDateTime.now());
		return tecnicoRepository.save(tecnico);
	}
	
}
