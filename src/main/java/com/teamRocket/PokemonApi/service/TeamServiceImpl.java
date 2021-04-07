package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.domain.Team;
import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.exception.TeamNotFoundException;
import com.teamRocket.PokemonApi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Optional<Team> findById(long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team newTeam(String name, Trainer trainer) {
        Team team = new Team();
        team.setName(name);
        team.setTrainer(trainer);
        return teamRepository.save(team);
    }

    @Override
    public Team addPokemon(long id, Pokemon pokemon) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        List<Pokemon> lstPokemon = new ArrayList<>();
        lstPokemon.add(pokemon);
        team.setPokemonTeam(lstPokemon);
        return teamRepository.save(team);
    }
}
