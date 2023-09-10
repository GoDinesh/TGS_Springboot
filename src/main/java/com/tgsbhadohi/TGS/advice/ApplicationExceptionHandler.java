package com.tgsbhadohi.TGS.advice;
import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	String msg = "";
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ResponseModel> handleInvalidArguments(MethodArgumentNotValidException ex){
		ex.getBindingResult().getFieldErrors().forEach( error->{
			msg = error.getDefaultMessage();
		});
		ResponseModel res = new ResponseModel(msg, Constants.ERROR, true, null );
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	 }
	
	 @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	 @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	 public ResponseEntity<ResponseModel> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		 ResponseModel res = new ResponseModel(Constants.REQUEST_METHOD_NOT_SUPPORTED, Constants.ERROR, true, null);
		 return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	 }
	 
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	 public ResponseEntity<ResponseModel> duplicateEntryException(SQLIntegrityConstraintViolationException ex) {
		 ResponseModel res = new ResponseModel(ex.toString(), Constants.ERROR, true, null);
		 return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	 @ExceptionHandler(ConnectException.class)
	 public ResponseEntity<ResponseModel> handleConnectException(ConnectException ex) {
		 ResponseModel res = new ResponseModel(Constants.SERVICE_NOT_AVAILABLE, Constants.ERROR, true, null);	
		 return new ResponseEntity<>(res, HttpStatus.SERVICE_UNAVAILABLE);
	 }
}

