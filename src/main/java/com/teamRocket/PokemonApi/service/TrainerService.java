package com.teamRocket.PokemonApi.service;

import com.teamRocket.PokemonApi.domain.Trainer;
/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
public interface TrainerService {
    Trainer findByName(String name);

    Trainer addDlc(Trainer trainer);
}
