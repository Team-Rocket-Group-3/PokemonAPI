package com.teamRocket.PokemonApi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Data
@Slf4j
@NoArgsConstructor
public class Team {
    private long id;
    private String name;
    private LocalDate creationDate;
    private Trainer trainer;
}
