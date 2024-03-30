package com.graco.trueplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graco.trueplan.entity.Cargo;
import com.graco.trueplan.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	public Cargo save (Cargo cargo) {
		return cargoRepository.save(cargo);
	}
	
	public List<Cargo> findAll (){
		return cargoRepository.findAll();
	}
	
	public Cargo findById (Long id) {
		return cargoRepository.findById(id).orElse(null);
	}
	
	public void deleteById (Long id) {
		cargoRepository.deleteById(id);
	}
	
}
