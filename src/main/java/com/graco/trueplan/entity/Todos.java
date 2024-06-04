package com.graco.trueplan.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private boolean realizado;
	@ManyToOne
	@JoinColumn(name = "chamado_id", referencedColumnName = "id")
	@JsonIgnore
	private Chamado chamado;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ultima_alteracao")
	private LocalDateTime ultimaAlteracao;
	
	@PrePersist
	public void prePersist() {

		this.ultimaAlteracao = LocalDateTime.now();
		this.dataCriacao = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.ultimaAlteracao = LocalDateTime.now();
	}
}
