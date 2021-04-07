package com.teamRocket.PokemonApi.exception;

/**
 * Excepción de Pokemon no encontrado
 * @version Curso 2020-2021
 * @author: veronica
 */
public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException() {
        super();
    }

    public PokemonNotFoundException(String message) {
        super(message);
    }

    public PokemonNotFoundException(long id) {
        super("Pokemon not found: " + id);
    }
}
