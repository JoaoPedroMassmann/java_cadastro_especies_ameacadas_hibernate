package br.edu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "especieHabitat")
public class EspecieHabitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecieHabitat")
    private long idEspecieHabitat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEspecie", nullable = false)
    private Especie idEspecie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idhabitat", nullable = false)
    private Habitat idHabitat;

    private int populacao;

    public EspecieHabitat() {
    }

    public EspecieHabitat(Especie idEspecie, Habitat idHabitat, int populacao) {
        this.idEspecie = idEspecie;
        this.idHabitat = idHabitat;
        this.populacao = populacao;
    }

    public long getIdEspecieHabitat() {
        return idEspecieHabitat;
    }

    public Especie getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Especie idEspecie) {
        this.idEspecie = idEspecie;
    }

    public Habitat getIdHabitat() {
        return idHabitat;
    }

    public void setIdHabitat(Habitat idHabitat) {
        this.idHabitat = idHabitat;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    @Override
    public String toString() {
        return "EspecieHabitat{" +
                "idEspecieHabitat=" + idEspecieHabitat +
                ", idEspecie=" + idEspecie +
                ", idHabitat=" + idHabitat +
                ", populacao=" + populacao +
                '}';
    }
}
