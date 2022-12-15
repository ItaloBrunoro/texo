package com.pior.filme.texo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pior.filme.texo.api.reponse.FilmRangeResponse;
import com.pior.filme.texo.api.reponse.FilmResponse;
import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.entity.FilmEntity;
import com.pior.filme.texo.exception.InvalidBodyException;
import com.pior.filme.texo.exception.ProducerExistsException;
import com.pior.filme.texo.exception.ProducerNotFoundException;
import com.pior.filme.texo.repository.FilmRepository;
import com.pior.filme.texo.stub.ProducerStub;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilmServiceTest {

  @InjectMocks
  private FilmService filmService;
  @Mock
  private FilmRepository filmRepository;
  @Spy
  private ObjectMapper objectMapper;

  @Test
  void getFilmShouldReturnSuccess() {

    Mockito.doReturn(Optional.of(ProducerStub.producerStub()))
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    FilmResponse producer = filmService.getFilm("producer", 1900);

    Assertions.assertEquals("producer", producer.getProducers());
    Assertions.assertEquals(1900, producer.getYears());
  }

  @Test
  void getFilmShouldReturnNotFoundException() {

    Mockito.doReturn(Optional.empty())
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    Assertions.assertThrows(ProducerNotFoundException.class, () ->{
      filmService.getFilm("producer", 1900);
    });
  }

  @Test
  void createFilmShouldReturnSuccess() {

    filmService.createFilm(ProducerStub.producerDtoStub());

    verify(filmRepository).save(any(FilmEntity.class));
  }

  @Test
  void createFilmShouldReturnExistsException() {

    Mockito.doReturn(Optional.of(ProducerStub.producerStub()))
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    Assertions.assertThrows(ProducerExistsException.class, () ->{
      filmService.createFilm(ProducerStub.producerDtoStub());
    });
  }

  @Test
  void createFilmShouldReturnInvalidBodyException() {

    Assertions.assertThrows(InvalidBodyException.class, () ->{
      filmService.createFilm(ProducerStub.producerDtoInvalidStub());
    });
  }

  @Test
  void updateFilmShouldReturnSuccess() {

    Mockito.doReturn(Optional.of(ProducerStub.producerStub()))
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    filmService.updateFilm("producer", 1900, ProducerStub.producerDtoStub());

    verify(filmRepository).save(any(FilmEntity.class));
  }

  @Test
  void updateFilmShouldReturnNotFoundException() {

    Mockito.doReturn(Optional.empty())
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    Assertions.assertThrows(ProducerNotFoundException.class, () ->{
      filmService.updateFilm("producer", 1900, ProducerStub.producerDtoStub());
    });
  }

  @Test
  void updateFilmShouldReturnInvalidBodyException() {

    Assertions.assertThrows(InvalidBodyException.class, () ->{
      filmService.updateFilm("producer", 1900, ProducerStub.producerDtoInvalidStub());
    });
  }

  @Test
  void deleteFilmShouldReturnNotFoundException() {

    Mockito.doReturn(Optional.empty())
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    Assertions.assertThrows(ProducerNotFoundException.class, () ->{
      filmService.deleteFilm("producer", 1900);
    });
  }

  @Test
  void deleteFilmShouldReturnSuccess() {

    Mockito.doReturn(Optional.of(ProducerStub.producerStub()))
      .when(this.filmRepository)
      .findFilmByProducersAndYears(any(), any());

    filmService.deleteFilm("producer", 1900);

    verify(filmRepository).deleteFilmByProducersAndYears(any(), any());
  }

  @Test
  void getAllMaxShouldReturnSuccess() {
    Mockito.doReturn(ProducerStub.producerWinStub())
      .when(this.filmRepository)
      .findAll();

    FilmRangeResponse allFilm = filmService.getAllFilm();

    Assertions.assertAll(
      () -> Assertions.assertEquals("producer", allFilm.getMax().get(0).getProducers()),
      () -> Assertions.assertEquals("producer2", allFilm.getMax().get(1).getProducers()),
      () -> Assertions.assertEquals(99, allFilm.getMax().get(0).getInterval()),
      () -> Assertions.assertEquals(99, allFilm.getMax().get(1).getInterval()));
  }

  @Test
  void getAllMinShouldReturnSuccess() {
    Mockito.doReturn(ProducerStub.producerWinStub())
      .when(this.filmRepository)
      .findAll();

    FilmRangeResponse allFilm = filmService.getAllFilm();

    Assertions.assertAll(
      () -> Assertions.assertEquals("producer3", allFilm.getMin().get(1).getProducers()),
      () -> Assertions.assertEquals("producer4", allFilm.getMin().get(0).getProducers()),
      () -> Assertions.assertEquals(1, allFilm.getMin().get(0).getInterval()),
      () -> Assertions.assertEquals(1, allFilm.getMin().get(1).getInterval()));
  }

  @Test
  void getAllMinShouldReturnEmpty() {
    Mockito.doReturn(ProducerStub.producerNoWinStub())
      .when(this.filmRepository)
      .findAll();

    FilmRangeResponse allFilm = filmService.getAllFilm();

    Assertions.assertAll(
      () -> Assertions.assertTrue(allFilm.getMin().isEmpty()));
  }

  @Test
  void getAllMaxShouldReturnEmpty() {
    Mockito.doReturn(ProducerStub.producerNoWinStub())
      .when(this.filmRepository)
      .findAll();

    FilmRangeResponse allFilm = filmService.getAllFilm();

    Assertions.assertAll(
      () -> Assertions.assertTrue(allFilm.getMax().isEmpty()));
  }
}
