package com.teamRocket.PokemonApi.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Data
@NoArgsConstructor
@ToString
@Entity(name = "trainer")
public class Trainer {

    @Schema(description = "Trainer identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Trainer name", example = "Zelda Ruiz", required = true)
    @Column
    private String name;

    @Schema(description = "Register date", example = "04/07/2021")
    @Column
    private LocalDate registerDate;

    @Schema(description = "List of Pokemon teams", example = "Team aqua")
    @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL)
    private List<Team> teams;
}
