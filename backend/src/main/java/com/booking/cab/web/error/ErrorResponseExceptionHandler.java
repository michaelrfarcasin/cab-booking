package com.booking.cab.web.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.booking.cab.exception.UserNotFoundException;

@ControllerAdvice
public class ErrorResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), 
				ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), 
				ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		@NonNull MethodArgumentNotValidException ex, 
		@NonNull HttpHeaders headers, 
		@NonNull HttpStatusCode status,
		@NonNull WebRequest request
	) {
		StringBuilder message = new StringBuilder();
		for (ObjectError error : ex.getAllErrors()) {
			message.append(error.toString());
		}
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), 
				message.toString(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
