package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "chamados")
@JsonRootName(value = "chamado")
public class Chamado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao_problema")
	private String descricaoProblema;
	
	@JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
	private StatusChamado status;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_abertura")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_finalizacao")
	private LocalDateTime dataFinalizacao;
	
	@Enumerated(EnumType.ORDINAL)
	private PRIORIDADE prioridade;
	
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	
	@JoinColumn(name = "tecnico_id", referencedColumnName = "id")
	private Tecnico tecnico;
	
	@JoinColumn(name = "tecnico2_id", referencedColumnName = "id")
	private Tecnico tecnico2;
	
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	
	

}

enum PRIORIDADE {
	URGENTE, ALTA, MEDIA, BAIXA
}


