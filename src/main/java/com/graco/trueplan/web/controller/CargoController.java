package com.graco.trueplan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Cargo;
import com.graco.trueplan.service.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoController extends AbstractController<Cargo, Cargo, Long>{

//	@Autowired
//	private CargoService cargoService;
//	
//	@PostMapping
//	public ResponseEntity<Cargo> criarNovoCargo (@RequestBody Cargo cargo) {
//		Cargo cargoNovo = cargoService.save(cargo);
//		return ResponseEntity.status(HttpStatus.CREATED).body(cargoNovo);
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<Cargo>> listarCargos () {
//		List<Cargo> cargos = cargoService.findAll();
//		return ResponseEntity.status(HttpStatus.OK).body(cargos);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Cargo> listarCargo (@PathVariable Long id) {
//		Cargo cargo = cargoService.findById(id);
//		if(cargo != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(cargo);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deletarCargo (@PathVariable Long id) {
//		Cargo cargo = cargoService.findById(id);
//		if(cargo != null) {
//			cargoService.deleteById(id);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//	}
}
