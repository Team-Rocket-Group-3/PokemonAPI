package com.teamRocket.PokemonApi.repository;

import com.teamRocket.PokemonApi.domain.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    Set<Trainer> findAll();
    Trainer findByName(String name);
}
