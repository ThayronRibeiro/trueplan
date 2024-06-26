package com.graco.trueplan.web.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.graco.trueplan.entity.Chamado;
import com.graco.trueplan.service.ChamadoService;
import com.graco.trueplan.web.dto.ChamadoDTO;

@RestController
@RequestMapping("/api/v1/chamados")
@CrossOrigin("*")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;
	
	@Autowired
	ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Chamado> criarNovoChamado(@RequestBody ChamadoDTO chamado) {
		Chamado chamadoNovo = chamadoService.save(chamado);
		return ResponseEntity.status(HttpStatus.CREATED).body(chamadoNovo);
	}

	@GetMapping("/datas")
	public ResponseEntity<List<String>> listarDatasAberturas() {
		List<String> datasChamados = chamadoService.listDates();
		return ResponseEntity.status(HttpStatus.OK).body(datasChamados);
	}

	@GetMapping("/{date}")
	public ResponseEntity<List<Chamado>> listarChamadosPorDatas(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		List<Chamado> chamadoEncontrados = chamadoService.findByDataAbertura(date);
		return ResponseEntity.status(HttpStatus.OK).body(chamadoEncontrados);
	}
	
	@GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarChamados(@RequestParam("dataChamado") @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<Date> dataChamado) {
		List<ChamadoDTO> chamadosDTO = new ArrayList<ChamadoDTO>();
			
		if(dataChamado.isPresent()) {
			Date date = dataChamado.get();
			chamadosDTO = chamadoService.listarChamadosDTOByData(date);
		} else {
			chamadosDTO = chamadoService.listarChamadosDTO(); 	
		}
		return ResponseEntity.status(HttpStatus.OK).body(chamadosDTO);
    }
		
	@GetMapping("/id/{id}")
	public ResponseEntity<ChamadoDTO> encontrarChamado(@PathVariable Long id) {
		ChamadoDTO chamadoEncontrado = chamadoService.listChamadoDTO(id);
		return ResponseEntity.status(HttpStatus.OK).body(chamadoEncontrado);
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<Chamado> atualizarChamado(@PathVariable Long id, @RequestBody ChamadoDTO chamado) {
		Chamado chamadoEncontrado = chamadoService.findById(id);
		if (chamadoEncontrado != null) {

			Chamado chamadoAtualizado = chamadoService.update(modelMapper.map(chamado, Chamado.class));
			return ResponseEntity.status(HttpStatus.OK).body(chamadoAtualizado);

		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PutMapping("/reagendarChamado")
	public ResponseEntity<Chamado> reagendar (@RequestBody Chamado chamado){
		
		chamadoService.reagendar(chamado);

		return ResponseEntity.status(HttpStatus.OK).body(chamado);
	}
	
	@PutMapping("/reagendarChamados")
	public ResponseEntity<List<Chamado>> reagendarTodos (@RequestBody List<Chamado> chamados){
		
		List<Chamado> chamadosNovos = chamados.stream().map(chamado -> chamadoService.reagendar(chamado)).collect(Collectors.toList());
		

		return ResponseEntity.status(HttpStatus.OK).body(chamadosNovos);
	}
}
