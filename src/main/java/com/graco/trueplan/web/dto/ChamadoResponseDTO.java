package com.graco.trueplan.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graco.trueplan.entity.Categoria;
import com.graco.trueplan.entity.StatusChamado;
import com.graco.trueplan.entity.Tecnico;
import com.graco.trueplan.enums.PRIORIDADE;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ChamadoResponseDTO {

	private Long id;
	private String descricaoProblema;
	private String contato;
	private String telefone1;
	private String telefone2;
	private String observacao;
	private StatusChamado statusChamado;
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
	private ClienteDTO cliente;
	private UsuarioDTO usuario;
	private Categoria categoria;
	private Tecnico tecnico;
	private Tecnico tecnico2;
	
}
