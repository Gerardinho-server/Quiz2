package com.clubfutbol.controller;

import com.clubfutbol.model.Club;
import com.clubfutbol.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubes")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    // Obtener todos los clubes
    @GetMapping
    public List<Club> listarClubes() {
        return clubRepository.findAll();
    }

    // Crear nuevo club
    @PostMapping
    public Club crearClub(@RequestBody Club club) {
        return clubRepository.save(club);
    }
}
