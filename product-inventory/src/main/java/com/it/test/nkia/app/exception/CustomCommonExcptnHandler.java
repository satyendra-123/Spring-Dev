package com.it.test.nkia.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class, basePackages = "com.it.test.nkia.app.controller")
public class CustomCommonExcptnHandler {
	
	@ExceptionHandler(value = ProductInventoryException.class)
	public ResponseEntity<Object> handleEntityFunctionalExcptn(
			ProductInventoryException ex) {
	       CustomApiError apiError = new CustomApiError(HttpStatus.INTERNAL_SERVER_ERROR , ex);
	       apiError.setMessage(ex.getMessage());
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
