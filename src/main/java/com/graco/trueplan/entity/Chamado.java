package com.graco.trueplan.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.graco.trueplan.enums.PRIORIDADE;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "chamados")
@JsonRootName(value = "chamado")
public class Chamado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao_problema")
	private String descricaoProblema;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
	private StatusChamado status;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_abertura")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_finalizacao")
	private LocalDateTime dataFinalizacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_chamado")
	private LocalDate dataChamado;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_cancelamento")
	private LocalDateTime dataCancelamento;
	
	@Enumerated(EnumType.ORDINAL)
	private PRIORIDADE prioridade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
	private Cliente cliente;
	
	@Column(name = "contato", length = 50, nullable = false)
	private String contato;
	
	@Column(name = "telefone1", nullable = false, length = 15)
	private String telefone1;
	
	@Column(name = "telefone2", nullable = true, length = 15)
	private String telefone2;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id", referencedColumnName = "id")
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name = "tecnico2_id", referencedColumnName = "id")
	private Tecnico tecnico2;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
	private Categoria categoria;
	
	@Column(name = "observacao", length = 300)
	private String observacao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL)
	private List<Comentario> comentarios;
	
}



