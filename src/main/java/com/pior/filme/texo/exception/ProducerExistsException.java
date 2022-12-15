package com.pior.filme.texo.exception;

public class ProducerExistsException extends RuntimeException {

  public ProducerExistsException() {
    super("Produtor já cadastrado para o ano informado!");
  }
}
