package com.pior.filme.texo.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmResponse {

  private Integer id;
  private Integer years;
  private String title;
  private String studios;
  private String producers;
  private String winner;

}
