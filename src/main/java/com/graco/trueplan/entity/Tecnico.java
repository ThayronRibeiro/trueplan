package com.graco.trueplan.entity;

import java.io.Serializable;

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
	
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@JoinColumn(name = "cargo_id", referencedColumnName = "id", nullable = false)
	private Cargo cargo;
	
	@Enumerated(EnumType.STRING)
	private STATUS status;
	
	@JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
	private Usuario usuario;
	

}
