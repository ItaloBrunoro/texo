package com.pior.filme.texo.stub;

import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.entity.FilmEntity;
import java.util.ArrayList;
import java.util.List;

public class ProducerStub {

  public static List<FilmEntity> producerWinStub() {
    List<FilmEntity> filmEntity = new ArrayList<>();

    filmEntity.add(FilmEntity.builder()
      .years(1900)
      .producers("producer")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1999)
      .producers("producer")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1900)
      .producers("producer2")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1999)
      .producers("producer2")
      .winner("yes")
      .build());

    filmEntity.add(FilmEntity.builder()
      .years(1900)
      .producers("producer3")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1901)
      .producers("producer3")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1900)
      .producers("producer4")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1901)
      .producers("producer4")
      .winner("yes")
      .build());

    return filmEntity;
  }

  public static List<FilmEntity> producerNoWinStub() {
    List<FilmEntity> filmEntity = new ArrayList<>();

    filmEntity.add(FilmEntity.builder()
      .years(1900)
      .producers("producer")
      .winner("ye1s")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1999)
      .producers("producer")
      .winner("yes")
      .build());
    filmEntity.add(FilmEntity.builder()
      .years(1900)
      .producers("producer2")
      .winner("yes")
      .build());

    return filmEntity;
  }

  public static FilmEntity producerStub() {
    return FilmEntity.builder()
      .producers("producer")
      .studios("studios")
      .title("title")
      .years(1900)
      .winner("yes")
      .build();
  }

  public static FilmDto producerDtoStub() {
    return FilmDto.builder()
      .producers("producer")
      .studios("studios")
      .title("title")
      .years(1900)
      .winner("yes")
      .build();
  }

  public static FilmDto producerDtoInvalidStub() {
    return FilmDto.builder()
      .producers("producer")
      .studios("studios")
      .title("title")
      .years(1900)
      .winner("yessss")
      .build();
  }
}
