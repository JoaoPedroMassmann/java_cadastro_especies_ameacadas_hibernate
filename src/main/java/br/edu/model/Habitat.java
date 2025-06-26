package br.edu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "habitat")
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitat;

    @Column(nullable = false, length = 100)
    private String regiao;
    @Column(nullable = false, length = 100)
    private String estado;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false, length = 100)
    private String bioma;
    @Column(nullable = false)
    private double extensao;

    public Habitat() {
    }

    public Habitat(String regiao, String estado, double latitude, double longitude, String bioma, double extensao) {
        this.regiao = regiao;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bioma = bioma;
        this.extensao = extensao;
    }

    public Long getIdHabitat() {
        return idHabitat;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getBioma() {
        return bioma;
    }

    public void setBioma(String bioma) {
        this.bioma = bioma;
    }

    public double getExtensao() {
        return extensao;
    }

    public void setExtensao(double extensao) {
        this.extensao = extensao;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "idHabitat=" + idHabitat +
                ", regiao='" + regiao + '\'' +
                ", estado='" + estado + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", bioma='" + bioma + '\'' +
                ", extensao=" + extensao +
                '}';
    }
}
