package com.clubfutbol.controller;

import com.clubfutbol.model.Entrenador;
import com.clubfutbol.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EntrenadorWebController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping("/entrenador/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("modo", "crear");
        return "entrenador-form";
    }

    @PostMapping("/entrenador/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenador/lista";
    }

    @GetMapping("/entrenador/lista")
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenador-lista";
    }

    @GetMapping("/entrenador/editar/{id}")
    public String editarEntrenador(@PathVariable Long id, Model model) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("entrenador", entrenador);
        model.addAttribute("modo", "editar");
        return "entrenador-form";
    }

    @GetMapping("/entrenador/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenador/lista";
    }
}
