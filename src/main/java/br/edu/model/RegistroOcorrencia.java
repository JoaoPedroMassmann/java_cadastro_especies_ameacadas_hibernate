package br.edu.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registroOcorrencia")
public class RegistroOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroOcorrencia;

    private LocalDateTime dataHora;
    private String observador;
    private double latitude;
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitatId")
    private Habitat habitat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especieId")
    private Especie especie;

    public RegistroOcorrencia() {
    }

    public RegistroOcorrencia(LocalDateTime dataHora, String observador, double latitude, double longitude, Habitat habitat, Especie especie) {
        this.dataHora = dataHora;
        this.observador = observador;
        this.latitude = latitude;
        this.longitude = longitude;
        this.habitat = habitat;
        this.especie = especie;
    }
}
