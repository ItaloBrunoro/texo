package com.pior.filme.texo.exception;

public class InvalidBodyException extends RuntimeException {

  public InvalidBodyException() {
    super("Corpo do payload inválido!");
  }
}
