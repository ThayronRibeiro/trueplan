package com.graco.trueplan.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.Cliente;
import com.graco.trueplan.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente save(Cliente cliente) {
		cliente.setDataCadastro(LocalDateTime.now());
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> findAll () {
		return clienteRepository.findAll();
	}
	
	public Cliente findById (Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public void deleteById (Long id) {
		clienteRepository.deleteById(id);
	}
	 
}
