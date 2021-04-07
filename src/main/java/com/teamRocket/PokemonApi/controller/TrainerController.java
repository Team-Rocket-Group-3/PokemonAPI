package com.teamRocket.PokemonApi.controller;

import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.service.TrainerService;
import com.teamRocket.PokemonApi.support.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Slf4j
@RestController
public class TrainerController {
    @Autowired
    private TrainerService trainerService;
    @Operation(summary = "Get an trainer by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainer found", content = @Content(schema = @Schema(implementation = Trainer.class))),
            @ApiResponse(responseCode = "204", description = "The trainer does not exist", content = @Content(schema = @Schema(implementation = Response.class)))
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
    @Operation(summary = "Register a new trainer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trainer registered", content = @Content(schema = @Schema(implementation = Trainer.class)))
    })
    @PostMapping(value = "trainers",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer){
        log.info("init addTrainer");
        Trainer addedTrainer = trainerService.addDlc(trainer);
        return new ResponseEntity<>(addedTrainer, HttpStatus.CREATED);
    }
}
