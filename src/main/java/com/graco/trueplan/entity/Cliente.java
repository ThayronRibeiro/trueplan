package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graco.trueplan.enums.STATUS;

import jakarta.persistence.*;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	@Column(name = "telefone1", length = 15)
	private String telefone1;
	
	@Column(name = "telefone2", length = 15)
	private String telefone2;
	
	@Column(name = "cnpj", length = 14)
	private String cnpj;
	
	@Column(name = " inscricao_estadual", length = 20)
	private String inscricaoEstadual;
	
	@Column(name = "inscricao_municipal", length = 20)
	private String inscricaoMunicipal;
	
	@Column(name = "data_cadastro", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;
	
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private STATUS status = STATUS.A;
	

}

