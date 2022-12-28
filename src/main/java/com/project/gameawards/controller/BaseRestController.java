package com.project.gameawards.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.gameawards.service.exception.BusinessException;
import com.project.gameawards.service.exception.NoContentException;

public abstract class BaseRestController {

	@ExceptionHandler(NoContentException.class) //Esta anotação serve para interceptar qualquer exceção do tipo No Content Exception, vai interceptar e cair no método criado abaixo 
	public ResponseEntity<Void> handlerNoContentException(NoContentException exception){
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiErrorDTO> handlerBusinessException(BusinessException e) {
		return ResponseEntity.badRequest().body(new ApiErrorDTO("Ocorreu um erro inesperado."));
	}
}
