package com.graco.trueplan.exception;

public class ChamadoNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ChamadoNaoEncontradoException () {
		super("Chamado não encontrado!");
	}
	
	public ChamadoNaoEncontradoException(String message) {
		super(message);
	}
}
