package com.pior.filme.texo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pior.filme.texo.api.reponse.FilmRangeResponse;
import com.pior.filme.texo.api.reponse.FilmResponse;
import com.pior.filme.texo.api.request.FilmRequest;
import com.pior.filme.texo.config.ReadPersistConfig;
import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.dto.FilmRangeDataDto;
import com.pior.filme.texo.dto.FilmRangeDto;
import com.pior.filme.texo.dto.ProducersWinDto;
import com.pior.filme.texo.entity.FilmEntity;
import com.pior.filme.texo.exception.InvalidBodyException;
import com.pior.filme.texo.exception.ProducerExistsException;
import com.pior.filme.texo.exception.ProducerNotFoundException;
import com.pior.filme.texo.repository.FilmRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final ObjectMapper objectMapper;
    private static final String REGEX_SEPARATE_PRODUCER = ",|W*(\\sand\\s)W*";
    private final Logger logger = LoggerFactory.getLogger(ReadPersistConfig.class);

  public FilmResponse getFilm(String producer, Integer year) {
    logger.info("Iniciando busca do filme.");
    Optional<FilmEntity> getFilm = filmRepository
      .findFilmByProducersAndYears(producer, year);

    if (getFilm.isEmpty()) throw new ProducerNotFoundException();

    logger.info("Busca finalizado com sucesso.");
    return objectMapper.convertValue(getFilm.get(), FilmResponse.class);
  }

  public FilmRangeResponse getAllFilm(){
    logger.info("Iniciando busca dos filmes.");

    //Pega todos os produtores vencedores
      var producersWin = getProducersWinners();
      var filmRangeDto = new FilmRangeDto();
      var listFilm = new ArrayList<FilmRangeDataDto>();

      producersWin.stream()
        .collect(Collectors.groupingBy(ProducersWinDto::getProducers,
          Collectors.summarizingInt(ProducersWinDto::getYear))) //Agrupa todos os produtores contando quantos vencedores tem.
        .entrySet().stream().filter(film -> film.getValue().getCount() > 1) //Filtra para retornar apenas os que tem mais de 1 premiação
        .collect(Collectors.toList()).forEach(entry -> {
                    var filmData = FilmRangeDataDto.builder()
                .producers(entry.getKey())
                .interval(Math.abs(entry.getValue().getMax() - entry.getValue().getMin()))
                .previousWin(entry.getValue().getMin())
                .followingWin(entry.getValue().getMax())
                .build();
        listFilm.add(filmData);
        });

      //Retorna só os com maior intervalo
      filmRangeDto.setMax(getRangeMax(listFilm));
      //Retorna só os com menor intervalo
      filmRangeDto.setMin(getRangeMin(listFilm));

    logger.info("Busca finalizada com sucesso.");
    return objectMapper.convertValue(filmRangeDto, FilmRangeResponse.class);
  }

  public void createFilm(FilmDto filmDto) {
    logger.info("Iniciando criação do filme.");
    if (!filmDto.getWinner().equals("yes")) throw new InvalidBodyException();
    filmRepository.findFilmByProducersAndYears(filmDto.getProducers(), filmDto.getYears())
      .ifPresent(value -> { throw new ProducerExistsException(); });

    filmRepository.save(objectMapper.convertValue(filmDto, FilmEntity.class));
    logger.info("Criação finalizado com sucesso.");
  }

  public void updateFilm(String producer, Integer year, FilmDto filmDto) {
    logger.info("Iniciando atualização do filme.");
    if (!filmDto.getWinner().equals("yes")) throw new InvalidBodyException();
    Optional<FilmEntity> getFilm = filmRepository.findFilmByProducersAndYears(producer, year);
    if (getFilm.isEmpty()) throw new ProducerNotFoundException();

    getFilm.get().setStudios(filmDto.getStudios());
    getFilm.get().setTitle(filmDto.getTitle());
    getFilm.get().setWinner(filmDto.getWinner());
    getFilm.get().setProducers(filmDto.getProducers());
    getFilm.get().setYears(filmDto.getYears());
    filmRepository.save(getFilm.get());
    logger.info("Filme atualizado com sucesso.");
  }

  @Transactional
  public void deleteFilm(String producer, Integer year) {
    logger.info("Iniciando deleção do filme.");
    filmRepository.findFilmByProducersAndYears(producer, year)
      .ifPresentOrElse(value -> filmRepository.deleteFilmByProducersAndYears(producer, year),
        () -> { throw new ProducerNotFoundException(); });
    logger.info("Filme deletado com sucesso.");
  }


  private List<FilmRangeDataDto> getRangeMin(List<FilmRangeDataDto> filmRangeDataDtos) {
    int min = filmRangeDataDtos.stream()
      .mapToInt(FilmRangeDataDto::getInterval)
      .min()
      .orElse(-1);

    return filmRangeDataDtos.stream()
      .filter(t -> t.getInterval() <= min)
      .collect(Collectors.toList());
  }

  private List<FilmRangeDataDto> getRangeMax(List<FilmRangeDataDto> filmRangeDataDtos) {
    int max = filmRangeDataDtos.stream()
      .mapToInt(FilmRangeDataDto::getInterval)
      .max()
      .orElse(-1);

    return filmRangeDataDtos.stream()
      .filter(t -> t.getInterval() >= max)
      .collect(Collectors.toList());
  }

  private List<ProducersWinDto> getProducersWinners() {
    var films = filmRepository.findAll();
    var producersWinDto = new ArrayList<ProducersWinDto>();
    films.forEach(film -> {
      if (!film.getWinner().equals("yes")) return;

      if (film.getProducers().contains(",") || film.getProducers().contains("and") ) {
        var split = film.getProducers().split(REGEX_SEPARATE_PRODUCER);
        Arrays.stream(split).forEach(producer -> producersWinDto.add(ProducersWinDto.builder()
          .producers(producer.trim())
          .year(film.getYears())
          .build()));
      } else {
        producersWinDto.add(ProducersWinDto.builder()
          .producers(film.getProducers())
          .year(film.getYears())
          .build());
      }
    });
    return producersWinDto;
  }
}
