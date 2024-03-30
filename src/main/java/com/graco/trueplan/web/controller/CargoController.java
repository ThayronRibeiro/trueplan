package com.graco.trueplan.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Cargo;
import com.graco.trueplan.service.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@PostMapping
	public ResponseEntity<Cargo> criarNovoCargo (@RequestBody Cargo cargo) {
		Cargo cargoNovo = cargoService.save(cargo);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoNovo);
	}
}
