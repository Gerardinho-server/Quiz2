package com.clubfutbol.controller;

import com.clubfutbol.model.Club;
import com.clubfutbol.repository.ClubRepository;
import com.clubfutbol.repository.EntrenadorRepository;
import com.clubfutbol.repository.CompeticionRepository;
import com.clubfutbol.repository.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubWebController {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private CompeticionRepository competicionRepository;

    @Autowired
    private AsociacionRepository asociacionRepository;

    // Página principal con botones
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Mostrar formulario para nuevo club
    @GetMapping("/club/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("modo", "crear");
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "club-form";
    }

    // Guardar o actualizar club
    @PostMapping("/club/guardar")
    public String guardarClub(@ModelAttribute Club club) {
        clubRepository.save(club);
        return "redirect:/club/lista";
    }

    // Listar clubes
    @GetMapping("/club/lista")
    public String listarClubes(Model model) {
        List<Club> clubes = clubRepository.findAll();
        model.addAttribute("clubes", clubes);
        return "club-lista";
    }

    // Formulario para editar club
    @GetMapping("/club/editar/{id}")
    public String editarClub(@PathVariable Long id, Model model) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("club", club);
        model.addAttribute("modo", "editar");
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "club-form";
    }

    // Eliminar club
    @GetMapping("/club/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        clubRepository.deleteById(id);
        return "redirect:/club/lista";
    }
}
