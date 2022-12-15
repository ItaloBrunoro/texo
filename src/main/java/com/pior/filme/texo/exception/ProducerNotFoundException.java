package com.pior.filme.texo.exception;

public class ProducerNotFoundException extends RuntimeException {

  public ProducerNotFoundException() {
    super("Produtor não encontrado para o ano informado!");
  }
}
