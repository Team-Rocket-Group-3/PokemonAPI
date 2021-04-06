package com.teamRocket.PokemonApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Slf4j
@NoArgsConstructor
@ToString
@Entity
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
    @JoinColumn(name = "trainer" )
    @JsonBackReference
    private Trainer trainer;
}
