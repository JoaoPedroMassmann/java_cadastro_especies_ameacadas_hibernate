package br.edu.dao;

import jakarta.persistence.EntityManager;
import br.edu.exception.DataAccessException;
import br.edu.model.Especie;
import java.util.List;

public class EspecieDao extends GenericDao<Especie> {
    private EntityManager em;

    public EspecieDao (EntityManager em) {
        super(em, Especie.class);
    }

    public Especie buscarPorNomeCientifico(String nomeCientifico) {
        try {
            String jpql = "SELECT e FROM Especie e WHERE LOWER(e.nomeCientifico) = LOWER(:nomeCientifico)";

            return em.createQuery(jpql, Especie.class)
                    .setParameter("nomeCientifico", nomeCientifico)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar categorias por nome cientifico: " + nomeCientifico, e);
        }
    }
}

