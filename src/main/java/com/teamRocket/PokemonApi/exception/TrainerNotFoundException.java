package com.teamRocket.PokemonApi.exception;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
public class TrainerNotFoundException extends RuntimeException {

    public TrainerNotFoundException() {
        super();
    }

    public TrainerNotFoundException(String message) {
        super(message);
    }

    public TrainerNotFoundException(long id) {
        super("Trainer not found: " + id);
    }
}
