package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Trainer;
import com.teamRocket.PokemonApi.exception.TrainerNotFoundException;
import com.teamRocket.PokemonApi.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public Optional<Trainer> findById(long id) {
        return trainerRepository.findById(id);
    }

    @Override
    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findByName(String name) {
        return trainerRepository.findByName(name);
    }

    @Override
    public Trainer findByNameAndPassword(String name, String password) {
        Trainer trainer = trainerRepository.findByNameAndPassword(name, password);
        if(trainer == null)
            throw new TrainerNotFoundException();

        return trainer;
    }

    @Override
    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(long id) {
        trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));

        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer modifyTrainer(long id, Trainer newTrainer) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));

        trainer.setId(id);
        trainer.setName(newTrainer.getName());
        trainer.setRegisterDate(LocalDate.now());
        return trainerRepository.save(trainer);
    }
}