package com.teamRocket.PokemonApi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class Ability {
    private long id;
    private String name;
    private int pp;
    private String description;
    private Pokemon pokemon;
    private boolean general;
}
