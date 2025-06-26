package br.edu.model;

import br.edu.enums.EstadoConservacaoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecie;

    @Column(nullable = false, length = 100)
    private String nomeComum;
    @Column(nullable = false, length = 100, unique = true)
    private String nomeCientifico;
    @Column(nullable = false, length = 100)
    private String reino;
    @Column(nullable = false, length = 100)
    private String filo;
    @Column(nullable = false, length = 100)
    private String classe;
    @Column(nullable = false, length = 100)
    private String ordem;
    @Column(nullable = false, length = 100)
    private String familia;
    @Column(nullable = false, length = 100)
    private String genero;

    private int numPopulacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoConservacao")
    private EstadoConservacaoEnum estadoConservacao;

    public Especie() {
    }

    public Especie(String nomeComum, String nomeCientifico, String reino, String filo, String classe, String ordem, String familia, String genero, int numPopulacao, EstadoConservacaoEnum estadoConservacao) {
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

    public Long getIdEspecie() {
        return idEspecie;
    }

    public String getNomeComum() {
        return nomeComum;
    }

    public void setNomeComum(String nomeComum) {
        this.nomeComum = nomeComum;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getReino() {
        return reino;
    }

    public void setReino(String reino) {
        this.reino = reino;
    }

    public String getFilo() {
        return filo;
    }

    public void setFilo(String filo) {
        this.filo = filo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumPopulacao() {
        return numPopulacao;
    }

    public void setNumPopulacao(int numPopulacao) {
        this.numPopulacao = numPopulacao;
    }

    public EstadoConservacaoEnum getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacaoEnum estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }
}
