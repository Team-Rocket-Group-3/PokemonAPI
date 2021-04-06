package com.teamRocket.PokemonApi.repository;

import com.teamRocket.PokemonApi.domain.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {

    Set<Pokemon> findAll();
    Pokemon findByName(String name);
    Set<Pokemon> findByType(String type);

}
