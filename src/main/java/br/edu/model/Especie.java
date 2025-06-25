package br.edu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecie;

    private String nomeComum;
    private String nomeCientifico;
    private String reino;
    private String filo;
    private String classe;
    private String ordem;
    private String familia;
    private String genero;
    private int numPopulacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitatId")
    private EstadoConservacao estadoConservacao;

    public Especie() {
    }

    public Especie(String nomeComum, String nomeCientifico, String reino, String filo, String classe, String ordem, String familia, String genero, int numPopulacao, EstadoConservacao estadoConservacao) {
        this.nomeComum = nomeComum;
        this.nomeCientifico = nomeCientifico;
        this.reino = reino;
        this.filo = filo;
        this.classe = classe;
        this.ordem = ordem;
        this.familia = familia;
        this.genero = genero;
        this.numPopulacao = numPopulacao;
        this.estadoConservacao = estadoConservacao;
    }
}
