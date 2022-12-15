package com.pior.filme.texo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmRangeDataDto {

  private String producers;
  private Integer interval;
  private Integer previousWin;
  private Integer followingWin;

}
