package com.graco.trueplan.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.Cliente;
import com.graco.trueplan.repository.ClienteRepository;

@Service
public class ClienteService extends GenericService<Cliente, Long> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente save(Cliente cliente) {
		cliente.setDataCadastro(LocalDateTime.now());
		return clienteRepository.save(cliente);
	}
		 
}
