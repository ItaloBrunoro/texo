package com.pior.filme.texo.api;

import com.pior.filme.texo.api.reponse.FilmResponse;
import com.pior.filme.texo.dto.FilmDto;
import com.pior.filme.texo.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Serviço para Manipulação de Filmes.")
@RestController
@RequestMapping(value = "/film")
@AllArgsConstructor
public class FilmApi {
    private final FilmService filmService;


    @ApiOperation(value = "Buscar filmes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filmes encontrados com sucesso."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @GetMapping()
    public ResponseEntity<List<FilmResponse>> getAllFilm() {
        filmService.getAllFilm();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
