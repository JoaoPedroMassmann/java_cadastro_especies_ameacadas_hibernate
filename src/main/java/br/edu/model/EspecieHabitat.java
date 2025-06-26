package br.edu.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

public class EspecieHabitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEspecieHabitat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitatId")
    private Especie idEspecie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitatId")
    private Habitat idHabitat;

    private int populacao;



}
