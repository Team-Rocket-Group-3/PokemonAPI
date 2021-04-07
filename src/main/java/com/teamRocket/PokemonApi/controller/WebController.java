package com.teamRocket.PokemonApi.controller;

import com.teamRocket.PokemonApi.domain.Pokemon;
import com.teamRocket.PokemonApi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Controller
public class WebController {

    @Autowired
    private PokemonService pokemonService;

    @RequestMapping(value = "/") // Web zone
    public String index(Model model) {
        Set<Pokemon> pokemons = pokemonService.findAll();
        model.addAttribute("pokemons", pokemons);
        return "index";
    }


}
