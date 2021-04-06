package com.teamRocket.PokemonApi.repository;

import com.teamRocket.PokemonApi.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Set<Game> findAll();
    Game findByName(String name);
    Set<Game> findByPlatform(String platform);
}
