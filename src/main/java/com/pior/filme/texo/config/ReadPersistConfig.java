package com.pior.filme.texo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.entity.FilmEntity;
import com.pior.filme.texo.repository.FilmRepository;
import com.pior.filme.texo.utils.FilesUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ReadPersistConfig {

  private ObjectMapper objectMapper;
  private FilmRepository filmRepository;
  private final Logger logger = LoggerFactory.getLogger(ReadPersistConfig.class);

  @Bean
  public void readPersistData(){
    logger.info("Iniciando leitura e persistência de dados.");
    var classloader = Thread.currentThread().getContextClassLoader();
    var inputStream = classloader.getResourceAsStream("movielist.csv");

    List<FilmDto> populate = populate(Objects.requireNonNull(FilesUtil.readLinesFromFile(inputStream)));
    logger.info("Lido {} dados do arquivo movielist.csv.", populate.size());
    List<FilmEntity> entity = populate.stream()
      .map(filmDto -> objectMapper.convertValue(filmDto, FilmEntity.class)).collect(Collectors.toList());
    entity.sort(Comparator.comparing(FilmEntity::getYears));
    filmRepository.saveAll(entity);
    logger.info("Persistido {} dados.", populate.size());
  }

  private List<FilmDto> populate(List<String> lines) {
    List<FilmDto> datas = new ArrayList<>();
    lines.remove(0);
    lines.forEach(line -> {
      String[] items = line.split("\\;", -1);
      var dto = FilmDto.builder()
        .years(Integer.valueOf(items[0]))
        .title(items[1])
        .studios(items[2])
        .producers(items[3])
        .winner(items[4])
        .build();

      datas.add(dto);
    });

    return datas;
  }
}
