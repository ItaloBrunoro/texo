package com.pior.filme.texo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ProducersWinDto {

  private Integer year;
  private String producers;
}
