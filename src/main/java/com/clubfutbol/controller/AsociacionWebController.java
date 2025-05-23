package com.clubfutbol.controller;

import com.clubfutbol.model.Asociacion;
import com.clubfutbol.repository.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AsociacionWebController {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping("/asociacion/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        model.addAttribute("modo", "crear");
        return "asociacion-form";
    }

    @PostMapping("/asociacion/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        asociacionRepository.save(asociacion);
        return "redirect:/asociacion/lista";
    }

    @GetMapping("/asociacion/lista")
    public String listarAsociaciones(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "asociacion-lista";
    }

    @GetMapping("/asociacion/editar/{id}")
    public String editarAsociacion(@PathVariable Long id, Model model) {
        Asociacion asociacion = asociacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("asociacion", asociacion);
        model.addAttribute("modo", "editar");
        return "asociacion-form";
    }

    @GetMapping("/asociacion/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionRepository.deleteById(id);
        return "redirect:/asociacion/lista";
    }
}
