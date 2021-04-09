package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.domain.Team;
import com.teamRocket.PokemonApi.domain.Trainer;

import java.util.List;
import java.util.Optional;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
public interface TeamService {

    //Search a team by id
    Optional<Team> findById(long id);

    // Creates a new Team
    Team newTeam(String name, Trainer trainer);

    // Add a Pokemon into team
    Team addPokemon(long id, Pokemon pokemon);

    // delete a team
    void deleteTeam(long id);

    //modify team
    Team modifyTeam(long id, Team newTeam);

    List<Team> findbyTrainer(long id);
}

