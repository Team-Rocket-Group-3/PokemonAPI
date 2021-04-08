package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.exception.PokemonNotFoundException;
import com.teamRocket.PokemonApi.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public Set<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon findById(Long id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException(id));
    }
}
