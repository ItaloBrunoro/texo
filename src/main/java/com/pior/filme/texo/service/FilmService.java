package com.pior.filme.texo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.entity.FilmEntity;
import com.pior.filme.texo.repository.FilmRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmService {

    private FilmRepository filmRepository;
    private ObjectMapper objectMapper;

    public List<FilmDto> getAllFilm(){



      return null;
    }

}
