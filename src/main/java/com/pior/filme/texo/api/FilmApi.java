package com.pior.filme.texo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pior.filme.texo.api.reponse.FilmRangeResponse;
import com.pior.filme.texo.api.reponse.FilmResponse;
import com.pior.filme.texo.api.request.FilmRequest;
import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Serviço para Manipulação de Filmes.")
@RestController
@RequestMapping(value = "/film")
@AllArgsConstructor
public class FilmApi {

    private final FilmService filmService;
    private final ObjectMapper objectMapper;

    @ApiOperation(value = "Buscar todos os filmes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filmes encontrados com sucesso."),
            @ApiResponse(code = 400, message = "Erro requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @GetMapping()
    public ResponseEntity<FilmRangeResponse> getAllFilm() {
        return new ResponseEntity<>(filmService.getAllFilm(), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar todos os filmes.")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Filmes encontrados com sucesso."),
      @ApiResponse(code = 400, message = "Erro requisição inválida."),
      @ApiResponse(code = 500, message = "Erro interno.")
    })
    @GetMapping("/producer/{producer}/year/{year}")
    public ResponseEntity<FilmResponse> getFilm(
      @ApiParam(value = "Produtor que deja atualizado.", required = true)
      @PathVariable String producer,
      @ApiParam(value = "Ano que deja atualizado.", required = true)
      @PathVariable Integer year) {
      return new ResponseEntity<>(filmService.getFilm(producer, year), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria filme.")
    @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Filme criado com sucesso."),
      @ApiResponse(code = 400, message = "Erro requisição inválida."),
      @ApiResponse(code = 500, message = "Erro interno.")
    })
    @PostMapping()
    public ResponseEntity<Void> createFilm(
        @ApiParam(value = "Filme que será criado.", required = true)
        @RequestBody FilmRequest filmRequest) {
        filmService.createFilm(objectMapper.convertValue(filmRequest, FilmDto.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar filme.")
    @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Filme atualizado com sucesso."),
      @ApiResponse(code = 404, message = "Erro produtor não encontrado."),
      @ApiResponse(code = 400, message = "Erro requisição inválida."),
      @ApiResponse(code = 500, message = "Erro interno.")
    })
    @PutMapping("/producer/{producer}/year/{year}")
    public ResponseEntity<Void> updateFilm(
      @ApiParam(value = "Produtor que deja atualizado.", required = true)
      @PathVariable String producer,
      @ApiParam(value = "Ano que deja atualizado.", required = true)
      @PathVariable Integer year,
      @ApiParam(value = "Filme que será atualizado.", required = true)
      @RequestBody FilmRequest filmRequest) {
        filmService.updateFilm(producer, year, objectMapper
          .convertValue(filmRequest, FilmDto.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar filme.")
    @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Filme deletado com sucesso."),
      @ApiResponse(code = 404, message = "Erro produtor não encontrado."),
      @ApiResponse(code = 500, message = "Erro interno.")
    })
    @DeleteMapping("/producer/{producer}/year/{year}")
    public ResponseEntity<Void> deleteFilm(
      @ApiParam(value = "Produtor que será deletado.", required = true)
      @PathVariable String producer,
      @ApiParam(value = "Ano que será deletado.", required = true)
      @PathVariable Integer year) {
        filmService.deleteFilm(producer, year);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
