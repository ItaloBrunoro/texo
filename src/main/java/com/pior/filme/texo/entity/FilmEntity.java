package com.pior.filme.texo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FILM")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer years;
  private String title;
  private String studios;
  private String producers;
  private String winner;

}
