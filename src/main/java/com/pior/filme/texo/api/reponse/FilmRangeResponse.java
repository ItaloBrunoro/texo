package com.pior.filme.texo.api.reponse;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmRangeResponse {

  private List<FilmRangeDataResponse> min;
  private List<FilmRangeDataResponse> max;
}
