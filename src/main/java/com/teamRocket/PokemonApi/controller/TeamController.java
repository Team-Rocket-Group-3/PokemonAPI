package com.teamRocket.PokemonApi.controller;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.domain.Team;
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

import java.util.List;

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
    @PostMapping(value = "/teams", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> newTeam(@RequestBody Team team) {
        log.info("Start newTeam");
        Team team1 = teamService.newTeam(team);
        log.info("End newTeam");
        return new ResponseEntity<>(team1, HttpStatus.CREATED);
    }

    @Operation(summary = "Save a Pokemon into a team") // Operation's description
    @ApiResponses(value = { // Possible answers
            @ApiResponse(responseCode = "201", description = "Pokemon was added", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/teams/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> addPokemon(@PathVariable long id, @RequestBody Pokemon pokemon) {
        log.info("Start addPokemon");
        Team team = teamService.addPokemon(id, pokemon);
        log.info("End addPokemon");
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @Operation(summary = "delete team") // Descripción de la operación
    @ApiResponses(value = { // Possible answers
            @ApiResponse(responseCode = "201", description = "Team was deleted", content = @Content(schema = @Schema(implementation = Team.class)))
    })
    @DeleteMapping(value = "teams/{id}")
    public ResponseEntity<Response> deleteTeam(@PathVariable long id) {
        log.info("init delete team");
        try {
            teamService.deleteTeam(id);
        } catch (TeamNotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(Response.errorResponse(Response.NOT_FOUND, "Team not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Modify a team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team modified", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "204", description = "The team does not exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> modifyTeam(@PathVariable long id, @RequestBody Team newTeam) {
        log.info("init modifyTeam");
        Team team;
        try {
            team = teamService.modifyTeam(id, newTeam);
        } catch (TeamNotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
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
