package com.pior.filme.texo.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmDataResponse {

  String producers;
  Integer interval;
  Integer previousWin;
  Integer followingWin;

}
