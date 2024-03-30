package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.graco.trueplan.enums.STATUS;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "tecnicos")
@JsonRootName(value = "tecnico")
public class Tecnico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id", referencedColumnName = "id", nullable = true)
	private Cargo cargo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 1)
	private STATUS status = STATUS.A;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
	private Usuario usuario;
	
	@Column(name = "data_cadastro", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	

}
