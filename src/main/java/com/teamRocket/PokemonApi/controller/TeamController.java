package com.teamRocket.PokemonApi.controller;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.domain.Team;
import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.exception.TeamNotFoundException;
import com.teamRocket.PokemonApi.service.TeamService;
import com.teamRocket.PokemonApi.support.Response;
import io.swagger.v3.oas.annotations.Operation;
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

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@RestController
@Tag(name = "Team", description = "Team's catalog")
@Slf4j
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Save new team") // Descripción de la operación
    @ApiResponses(value = { // Possible answers
            @ApiResponse(responseCode = "201", description = "Team was added", content = @Content(schema = @Schema(implementation = Team.class)))
    })
    @PostMapping(value = "/team", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> newTeam(@RequestBody String name, @RequestBody Trainer trainer) {
        log.info("Start newTeam");
        Team team = teamService.newTeam(name, trainer);
        log.info("End newTeam");
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @Operation(summary = "Save a Pokemon into a team") // Operation's description
    @ApiResponses(value = { // Possible answers
            @ApiResponse(responseCode = "201", description = "Pokemon was added", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/team/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> addPokemon(@PathVariable long id, @RequestBody Pokemon pokemon) {
        log.info("Start addPokemon");
        Team team = teamService.addPokemon(id, pokemon);
        log.info("End addPokemon");
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleTeamNotFoundException(TeamNotFoundException tnfe) {
        Response response = Response.errorResponse(Response.ERROR_NOT_FOUND, tnfe.getMessage());
        log.error(tnfe.getMessage(), tnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleException(Exception exception) {
        Response response = Response.errorResponse(500, "Unexpected error. Please, contact with the administrator");
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
