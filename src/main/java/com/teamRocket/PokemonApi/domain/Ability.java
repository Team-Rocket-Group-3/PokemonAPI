package com.teamRocket.PokemonApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Data
@NoArgsConstructor
@Slf4j
@ToString
@Entity(name = "ability")
public class Ability {
    @Schema(description = "ability identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Ability name", example = "Fly", required = true)
    @Column
    private String name;
    @Schema(description = "Power points", example = "8", required = true)
    @Column
    private int pp;
    @Schema(description = "Ability description", example = "On the turn that Fly is selected, the user will fly up high and become semi-invulnerable", required = true)
    @Column
    private String description;
    @Schema(description = "Pokemon which ahs this ability", example = "Pikachu", required = true)
    @ManyToOne
    @JoinColumn(name = "pokemon" )
    @JsonBackReference(value="trainer")
    private Pokemon pokemon;
    @Schema(description = "Check if the ability is common or exclusive (true = common)", example = "true", required = true)
    @Column
    private boolean general;
}
