package com.teamRocket.PokemonApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
/**
 * @version Curso 2020-2021
 * @author: Guillermo
 */
@Data
@NoArgsConstructor
@ToString
@Entity(name = "game")
public class Game {
    @Schema(description = "game identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Game name", example = "Pokemon Gold", required = true)
    @Column
    private String name;

    @Schema(description = "platform name where the game was released", example = "Nintendo Game Boy", required = false)
    @Column
    private String platform;

    @Schema(description = "Release date", example = "2016/06/04", required = true)
    @Column
    private LocalDate releaseDate;

    @Schema(description = "List of Pokemon that belongs to the game", example = "Pikachu", required = true)
    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL)
    @JsonBackReference(value="pokemons")
    private List<Pokemon> pokemons;

    @Schema(description = "URL with a image of the game", example = "https://www.pokeapi.com/pokemongold.png", required = true)
    @Column
    private String gameImage;
}
