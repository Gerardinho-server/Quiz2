package com.clubfutbol.repository;

import com.clubfutbol.model.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
}
