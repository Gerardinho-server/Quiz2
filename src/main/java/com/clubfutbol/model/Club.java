package com.clubfutbol.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;
    private int fundacion;

    @OneToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Jugador> jugadores;

    @ManyToOne
    @JoinColumn(name = "id_asociacion")
    private Asociacion asociacion;
    
    @ManyToMany
    @JoinTable(
        name = "club_competicion",
        joinColumns = @JoinColumn(name = "id_club"),
        inverseJoinColumns = @JoinColumn(name = "id_competicion")
    )
    private List<Competicion> competiciones;

    // Relaciones futuras (pendientes):
    // @OneToMany(mappedBy = "club") List<Jugador>
    // @ManyToOne Asociacion
    // @ManyToMany List<Competicion>

    // Getters y Setters
    
    public List<Competicion> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Competicion> competiciones) {
        this.competiciones = competiciones;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}
