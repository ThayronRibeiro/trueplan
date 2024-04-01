package com.graco.trueplan.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name= "status_chamado")
@JsonRootName(value = "status_chamado")
public class StatusChamado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "cor_background", nullable = false)
	private String corBackground;
	
	@Column(name = "cor_letras", nullable = false)
	private String corLetras;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<Chamado> chamadosStatus;

}
