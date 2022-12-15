package com.pior.filme.texo.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class FilmRequest {

  private Integer years;
  private String title;
  private String studios;
  private String producers;
  @ApiModelProperty(example = "yes")
  private String winner;

}
