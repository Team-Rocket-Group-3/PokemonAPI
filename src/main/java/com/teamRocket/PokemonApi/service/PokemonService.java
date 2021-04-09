package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Pokemon;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
public interface PokemonService {

    Set<Pokemon> findAll();

    Pokemon findById(Long id);
}
