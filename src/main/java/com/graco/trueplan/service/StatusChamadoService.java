package com.graco.trueplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.StatusChamado;
import com.graco.trueplan.repository.StatusChamadoRepository;

@Service
public class StatusChamadoService {

	@Autowired
	private StatusChamadoRepository statusChamadoRepository;

	public StatusChamado save(StatusChamado statusChamado) {
		return statusChamadoRepository.save(statusChamado);
	}

	public List<StatusChamado> findAll() {
		return statusChamadoRepository.findAll();
	}

	public StatusChamado findById(Long id) {
		return statusChamadoRepository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		statusChamadoRepository.deleteById(id);
	}
}
