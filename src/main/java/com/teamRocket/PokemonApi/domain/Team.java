package com.teamRocket.PokemonApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "team")
public class Team {
    @Schema(description = "team identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "team name", example = "Team aqua", required = true)
    @Column
    private String name;

    @Schema(description = "creation date", example = "2016/06/04", required = true)
    @Column
    private LocalDate creationDate;

    @Schema(description = "Trainer which has the team", example = "Zelda Ruiz", required = true)
    @ManyToOne
    @JoinColumn(name = "trainer")
    @JsonIgnore
    private Trainer trainer;

    @Schema(description = "Pokemon in the team", example = "Pikachu", required = true)
    @ManyToMany
    @JoinTable(name = "teams_Pokemon",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id", referencedColumnName = "id"))
    private List<Pokemon> pokemons;

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }
}
