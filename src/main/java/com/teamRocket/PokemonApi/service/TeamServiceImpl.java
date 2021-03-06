package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.domain.Team;
import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.exception.TeamNotFoundException;
import com.teamRocket.PokemonApi.repository.TeamRepository;
import com.teamRocket.PokemonApi.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Slf4j
@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public Optional<Team> findById(long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team newTeam(long trainerId, Team team) {
        Optional<Trainer> trainer = trainerRepository.findById(trainerId);
        team.setTrainer(trainer.get());
        return teamRepository.save(team);
    }

    @Override
    public Team addPokemon(long id, Pokemon pokemon) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        team.addPokemon(pokemon);
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        teamRepository.delete(team);
    }

    @Override
    public Team modifyTeam(long id, Team newTeam) throws TeamNotFoundException {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        newTeam.setId(team.getId());
        return teamRepository.save(newTeam);
    }

    @Override
    public List<Team> findbyTrainer(long id) {
        return teamRepository.findByTrainerId(id);
    }

}