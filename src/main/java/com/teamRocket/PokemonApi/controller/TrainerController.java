package com.teamRocket.PokemonApi.controller;

import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.exception.TrainerNotFoundException;
import com.teamRocket.PokemonApi.service.TrainerService;
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

import java.util.List;

import static com.teamRocket.PokemonApi.support.Response.NOT_FOUND;

/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Slf4j
@RestController
@Tag(name = "Trainer", description = "Trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Operation(summary = "Get all trainers") // Operation's description
    @ApiResponses(value = { // Possibles Answers
            @ApiResponse(responseCode = "200", description = "Trainer's list",
                    // Type of content:
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Trainer.class))))
    })
    @GetMapping(value = "/trainers", produces = "application/json")
    public ResponseEntity<List<Trainer>> getTrainers() {
        log.info("Start getTrainers");
        List<Trainer> trainers = trainerService.findAll();
        log.info("End getTrainers");
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @Operation(summary = "Get an trainer by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainer found", content = @Content(schema = @Schema(implementation = Trainer.class))),
            @ApiResponse(responseCode = "404", description = "The trainer does not exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("trainers/{name}")
    public ResponseEntity<Trainer> getTrainer(@PathVariable String name){
        log.info("init get Trainer by name");
        Trainer trainer;
        trainer = trainerService.findByName(name);
        if(trainer == null){
            log.error("trainer not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    @Operation(summary = "Get trainer by name and password") // Operation's description
    @ApiResponses(value = { // Possibles Answers
            @ApiResponse(responseCode = "200", description = "Trainer found",
                    // Type of content:
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Trainer.class)))),
            @ApiResponse(responseCode = "404", description = "The trainer does not exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Trainer> getTrainerLogin(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "password", defaultValue = "") String password) {
        log.info("start Login");
        Trainer trainer = trainerService.findByNameAndPassword(name, password);
        log.info("end login");
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    @Operation(summary = "Register a new trainer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trainer registered", content = @Content(schema = @Schema(implementation = Trainer.class)))
    })
    @PostMapping(value = "trainers",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer){
        log.info("init addTrainer");
        Trainer addedTrainer = trainerService.addTrainer(trainer);
        return new ResponseEntity<>(addedTrainer, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a trainer") // Operation's description
    @ApiResponses(value = { // Possibles Answers
            @ApiResponse(responseCode = "200", description = "Trainer deleted", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Trainer not found", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/trainers/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteTrainer(@PathVariable long id) {
        log.info("Start deleteTrainer");
        trainerService.deleteTrainer(id);
        log.info("End deleteTrainer");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Modify a trainer") // Operation's description
    @ApiResponses(value = { // Possibles Answers
            @ApiResponse(responseCode = "200", description = "Trainer modified", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Trainer not found", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/trainers/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Trainer> modifyTrainer(@PathVariable long id, @RequestBody Trainer newTrainer) {
        log.info("Start modifyTrainer");
        Trainer trainer = trainerService.modifyTrainer(id, newTrainer);
        log.info("End modifyTrainer");
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleTrainerNotFoundException(TrainerNotFoundException tnfe) {
        Response response = Response.errorResponse(NOT_FOUND, tnfe.getMessage());
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

