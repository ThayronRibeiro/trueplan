package com.graco.trueplan.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TodosDTO {

	private String descricao;
	private boolean realizado;
	private String chamadoId;
}
