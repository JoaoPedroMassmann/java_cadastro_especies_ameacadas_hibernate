package br.edu.dao;

import jakarta.persistence.EntityManager;
import br.edu.exception.DataAccessException;
import br.edu.model.Habitat;

import java.util.List;

public class HabitatDao extends GenericDao<Habitat> {
    private EntityManager em;

    public HabitatDao (EntityManager em) {
        super(em, Habitat.class);
    }



}

