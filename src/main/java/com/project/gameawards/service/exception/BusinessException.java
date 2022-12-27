package com.project.gameawards.service.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//Criando uma exceção com mensagem para mostrar no front-end
	public BusinessException(String message) {
		super(message);
		
	}
}
