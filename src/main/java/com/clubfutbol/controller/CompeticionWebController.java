package com.clubfutbol.controller;

import com.clubfutbol.model.Competicion;
import com.clubfutbol.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompeticionWebController {

    @Autowired
    private CompeticionRepository competicionRepository;

    @GetMapping("/competicion/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("modo", "crear");
        return "competicion-form";
    }

    @PostMapping("/competicion/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion) {
        competicionRepository.save(competicion);
        return "redirect:/competicion/lista";
    }

    @GetMapping("/competicion/lista")
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competicion-lista";
    }

    @GetMapping("/competicion/editar/{id}")
    public String editarCompeticion(@PathVariable Long id, Model model) {
        Competicion c = competicionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("competicion", c);
        model.addAttribute("modo", "editar");
        return "competicion-form";
    }

    @GetMapping("/competicion/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionRepository.deleteById(id);
        return "redirect:/competicion/lista";
    }
}
