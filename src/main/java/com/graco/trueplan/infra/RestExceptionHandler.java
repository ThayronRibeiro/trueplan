package com.graco.trueplan.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.graco.trueplan.exception.ChamadoNaoEncontradoException;
import com.graco.trueplan.exception.DataMenorIgualReagendamentoException;
import com.graco.trueplan.utils.ApiError;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(DataMenorIgualReagendamentoException.class)
	private ResponseEntity<ApiError> dataMenorIgualReagendamentoException(DataMenorIgualReagendamentoException exception) {
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(ChamadoNaoEncontradoException.class)
	private ResponseEntity<ApiError> chamadoNaoEncontradoException(ChamadoNaoEncontradoException exception) {
		ApiError error = new ApiError();	
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
