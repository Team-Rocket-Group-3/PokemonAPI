package com.teamRocket.PokemonApi.repository;

import com.teamRocket.PokemonApi.domain.Ability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Repository
public interface AbilityRepository extends CrudRepository<Ability, Long> {

    Set<Ability> findAll();
    Ability findByName(String name);
}
