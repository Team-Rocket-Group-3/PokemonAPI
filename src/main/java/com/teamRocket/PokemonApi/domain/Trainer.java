package com.teamRocket.PokemonApi.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Data
@Slf4j
@NoArgsConstructor
@ToString
@Entity(name = "trainer")
public class Trainer {
    @Schema(description = "trainer identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "trainer name", example = "Zelda Ruiz", required = true)
    @Column
    private String name;
    @Schema(description = "Register date", example = "2016/06/04", required = true)
    @Column
    private LocalDate registerDate;
    @Schema(description = "List of Pokemon teams", example = "Team aqua", required = true)
    @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL)
    private List<Team> pokemonTeam;

}
