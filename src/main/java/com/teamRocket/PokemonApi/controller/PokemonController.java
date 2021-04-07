package com.teamRocket.PokemonApi.controller;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.exception.PokemonNotFoundException;
import com.teamRocket.PokemonApi.service.PokemonService;
import com.teamRocket.PokemonApi.support.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@RestController
@Tag(name = "Pokemon", description = "Pokemon's catalog")
@Slf4j
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Return the pokemon's list") // Operation's description
    @ApiResponses(value = { // Possibles Answers
            @ApiResponse(responseCode = "200", description = "Pokemon's list",
                    // Type of content:
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pokemon.class)))),
    })
    @GetMapping(value = "/pokemon", produces = "application/json")
    public ResponseEntity<Set<Pokemon>> getPokemon() {
        log.info("Start getPokemon");
        Set<Pokemon> pokemon = pokemonService.findAll();
        log.info("End getPokemon");
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handlePokemonNotFoundException(PokemonNotFoundException fnfe) {
        Response response = Response.errorResponse(Response.ERROR_NOT_FOUND, fnfe.getMessage());
        log.error(fnfe.getMessage(), fnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleException(Exception exception) {
        Response response = Response.errorResponse(500, "Error inesperado. Contacte con el administrador");
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
