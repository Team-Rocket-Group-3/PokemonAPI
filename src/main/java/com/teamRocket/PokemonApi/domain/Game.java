package com.teamRocket.PokemonApi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Slf4j
public class Game {
    private long id;
    private String name;
    private String platform;
    private LocalDate releaseDate;
    private Set<Pokemon> pokemonSet;
    private String gameImage;
}
