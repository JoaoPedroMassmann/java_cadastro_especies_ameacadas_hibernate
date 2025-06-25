package br.edu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "habitat")
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitat;

    private String regiao;
    private String estado;
    private double latitude;
    private double longitude;
    private String bioma;

    public Habitat() {
    }

    public Habitat(String regiao, String estado, double latitude, double longitude, String bioma) {
        this.regiao = regiao;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bioma = bioma;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "idHabitat=" + idHabitat +
                ", regiao='" + regiao + '\'' +
                ", estado='" + estado + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
