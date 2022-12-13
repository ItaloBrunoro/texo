package com.pior.filme.texo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class FilmDto {

  Integer years;
  String title;
  String studios;
  String producers;
  String winner;

}
