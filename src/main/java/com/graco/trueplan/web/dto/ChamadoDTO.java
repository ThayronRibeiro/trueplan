package com.graco.trueplan.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graco.trueplan.enums.PRIORIDADE;

import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ChamadoDTO {

	private Long id;
	private Long clienteId;
	private Long usuarioId;
	private Long tecnicoId;
	private Long tecnico2Id;
	private Long categoriaId;
	private Long statusChamadoId;
	private String contato;
	private String telefone1;
	private String telefone2;
	private String descricaoProblema;
	private String observacao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataChamado;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataFinalizacao;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCancelamento; 
	private PRIORIDADE prioridade;
	
}
