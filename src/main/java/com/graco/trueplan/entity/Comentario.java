package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import jakarta.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "comentarios")
@JsonRootName("comentario")
public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "texto_comentario")
	private String textoComentario;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_hora_comentario")
	private LocalDateTime dataHoraComentario;
	
	@JoinColumn(name = "chamado_id", referencedColumnName = "id", nullable = false)
	private Chamado chamado;
	
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

}
