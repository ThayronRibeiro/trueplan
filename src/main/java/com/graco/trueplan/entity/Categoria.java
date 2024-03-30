package com.graco.trueplan.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "categorias")
@JsonRootName(value = "categoria")
public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "descricao", length = 60)
	private String descricao;
	
	

}
