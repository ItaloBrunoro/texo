package com.pior.filme.texo.repository;

import com.pior.filme.texo.entity.FilmEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

  Optional<FilmEntity> findFilmByProducersAndYears(String producers, Integer years);

  void deleteFilmByProducersAndYears(String producers, Integer years);
}
