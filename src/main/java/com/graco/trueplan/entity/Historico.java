package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "historicos")
@JsonRootName(value = "historico")
public class Historico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "chamado_id", referencedColumnName = "id", nullable = false)
	private Chamado chamado;
	
	@Column(name="ocorrencia", length = 100)
	private String ocorrencia;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_hora_ocorrencia", nullable = false)
	private LocalDateTime dataHoraOcorrencia;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "id")
	private StatusChamado status;

}
