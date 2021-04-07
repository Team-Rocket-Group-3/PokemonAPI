package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Team;
import com.teamRocket.PokemonApi.domain.Trainer;

import java.util.List;
import java.util.Optional;

/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
public interface TrainerService {

    //Search a trainer by id
    Optional<Trainer> findById(long id);

    //Get a list of trainers
    List<Trainer> findAll();

    //Search a trainer by name
    Trainer findByName(String name);

    //Add a trainer
    Trainer addTrainer(Trainer trainer);

    // Delete a trainer by id
    void deleteTrainer(long id);

    //Modify a trainer
    Trainer modifyTrainer(long id, Trainer newTrainer);
}
