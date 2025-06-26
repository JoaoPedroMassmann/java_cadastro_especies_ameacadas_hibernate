package br.edu.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registroOcorrencia")
public class RegistroOcorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroOcorrencia;

    @Column(nullable = false)
    private LocalDateTime dataHora;
    @Column(nullable = false, length = 100)
    private String observador;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
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

    public Long getIdRegistroOcorrencia() {
        return idRegistroOcorrencia;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservador() {
        return observador;
    }

    public void setObservador(String observador) {
        this.observador = observador;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}