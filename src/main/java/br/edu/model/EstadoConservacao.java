package br.edu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estados_de_conservacao")

public class EstadoConservacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoConservacao;

    private String nomeEstado;
    private String descricaoEstado;

    public EstadoConservacao() {
    }

    public EstadoConservacao(String nomeEstado, String descricaoEstado) {
        this.nomeEstado = nomeEstado;
        this.descricaoEstado = descricaoEstado;
    }
}
