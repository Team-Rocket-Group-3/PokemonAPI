package com.teamRocket.PokemonApi.exception;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException() {
        super();
    }

    public TeamNotFoundException(String message) {
        super(message);
    }

    public TeamNotFoundException(long id) {
        super("Team not found: " + id);
    }
}
