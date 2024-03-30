package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "configuracao")
@JsonRootName(value = "configuracao")
public class Configuracao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = false)
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "status_padrao_id", referencedColumnName = "id")
	private StatusChamado statusPadrao;
	
	@OneToOne
	@JoinColumn(name = "status_finalizado_id", referencedColumnName = "id")
	private StatusChamado statusFinalizado;
	
	@OneToOne
	@JoinColumn(name = "status_ematendimento_id", referencedColumnName = "id")
	private StatusChamado statusEmAtendimento;
	
	@OneToOne
	@JoinColumn(name = "status_cancelado_id", referencedColumnName = "id")
	private StatusChamado statusCancelado;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ultima_alteracao", nullable = false)
	private LocalDateTime ultimaAlteracao;
	
	
	
	
}
