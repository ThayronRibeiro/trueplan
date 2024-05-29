package com.graco.trueplan.exception;

public class DataMenorIgualReagendamentoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataMenorIgualReagendamentoException() {
		super("Nova data não pode ser menor ou igual a data atual do chamado!");
	}
	
	public DataMenorIgualReagendamentoException(String message) {
		super(message);
	}

}
