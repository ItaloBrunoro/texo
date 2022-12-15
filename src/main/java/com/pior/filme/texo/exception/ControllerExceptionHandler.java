package com.pior.filme.texo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler({ProducerExistsException.class, InvalidBodyException.class})
  public final ResponseEntity<Object> handleExistsException(RuntimeException ex) {

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ProducerNotFoundException.class)
  public final ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}