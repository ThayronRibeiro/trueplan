package com.graco.trueplan.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "enderecos")
@JsonRootName(value = "endereco")
public class Endereco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "logradouro", length = 250)
	private String logradouro;
	
	@Column(name = "numero", length = 8)
	private String numero;
	
	@Column(name = "bairro", length = 30)
	private String bairro;
	
	@Column(name = "cidade", length = 60)
	private String cidade;
	
	@Column(name = "codigo_cidade", length = 8)
	private String codigoCidade;
	
	@Column(name = "uf_estado", length = 2)
	private String ufEstado;
	
	@Column(name = "cep", length = 8)
	private String cep;
	
	@Column(name = "complemento", length = 50)
	private String complemento;
	
	@Column(name = "ponto_referencia", length = 60)
	private String pontoReferencia;
	
	
	
}
