package com.clubfutbol.controller;

import com.clubfutbol.model.Jugador;
import com.clubfutbol.model.Club;
import com.clubfutbol.repository.JugadorRepository;
import com.clubfutbol.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JugadorWebController {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping("/jugador/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubRepository.findAll());
        model.addAttribute("modo", "crear");
        return "jugador-form";
    }

    @PostMapping("/jugador/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorRepository.save(jugador);
        return "redirect:/jugador/lista";
    }

    @GetMapping("/jugador/lista")
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        return "jugador-lista";
    }

    @GetMapping("/jugador/editar/{id}")
    public String editarJugador(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("jugador", jugador);
        model.addAttribute("clubes", clubRepository.findAll());
        model.addAttribute("modo", "editar");
        return "jugador-form";
    }

    @GetMapping("/jugador/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorRepository.deleteById(id);
        return "redirect:/jugador/lista";
    }
}
