package com.teamRocket.PokemonApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;
/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Data
@NoArgsConstructor
@Slf4j
@ToString
@Entity(name = "pokemon")
public class Pokemon {
    @Schema(description = "Pokemon identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Pokemon name", example = "Pikachu", required = true)
    @Column
    private String name;
    @Schema(description = "Health points", example = "30", required = true)
    @Column
    private int hp;
    @Schema(description = "Pokemon type", example = "fire", required = true)
    @Column
    private String type;
    @Schema(description = "List of Pokemon abilities", example = "fly", required = true)
    @Column
    @OneToMany(mappedBy = "pokemon",cascade = CascadeType.ALL)
    private Set<Ability> abilitySet;
    @Schema(description = "Check if the Pokemon is an evolution", example = "true", required = true)
    @Column
    private boolean evolution;
    @Schema(description = "Game were the Pokemon appear for the first time", example = "Pokemon gold", required = true)
    @ManyToOne
    @JoinColumn(name = "game")
    private Game game;
    @Schema(description = "URL with a image of that Pokemon", example = "https://www.pokeapi.com/pikachu.png", required = true)
    private String imageUrl;
    @Schema(description = "Team of pokemon", example = "team aqua", required = true)
    @ManyToOne
    @JoinColumn(name = "team")
    @JsonBackReference (value="get-team")
    private Team team;

}
