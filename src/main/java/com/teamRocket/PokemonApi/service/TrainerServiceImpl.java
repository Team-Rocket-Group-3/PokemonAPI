package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerRepository trainerRepository;
    @Override
    public Trainer findByName(String name) {
        return trainerRepository.findByName(name);
    }

    @Override
    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }


}
