package com.teamRocket.PokemonApi.repository;

import com.teamRocket.PokemonApi.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();
    Team findByName(String name);

    List<Team> findByTrainerId(long id);

}
