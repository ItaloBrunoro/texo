package com.pior.filme.texo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class FilmDto {

  private Integer years;
  private String title;
  private String studios;
  private String producers;
  private String winner;

}
