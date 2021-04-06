package com.teamRocket.PokemonApi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Data
@NoArgsConstructor
@Slf4j
public class Pokemon {
    private long id;
    private String name;
    private int ps;
    private String type;
    private Set<Ability> abilitySet;
    private boolean evolution;
    private Game game;
    private String imageUrl;
}
