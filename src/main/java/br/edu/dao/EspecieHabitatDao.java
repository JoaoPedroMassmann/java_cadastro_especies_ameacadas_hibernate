package br.edu.dao;

import br.edu.model.Especie;
import jakarta.persistence.EntityManager;
import br.edu.exception.DataAccessException;
import br.edu.model.EspecieHabitat;
import java.util.List;

public class EspecieHabitatDao extends GenericDao<EspecieHabitat> {
    private EntityManager em;

    public EspecieHabitatDao (EntityManager em) {
        super(em, EspecieHabitat.class);
        this.em = em;
    }

    public List<EspecieHabitat> buscarEspeciesEmUmHabitat(long idHabitat) {
        try {
            String jpql = "SELECT e FROM EspecieHabitat e WHERE e.idHabitat.id = :id";

            return em.createQuery(jpql, EspecieHabitat.class)
                    .setParameter("id", idHabitat)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar especies em um habitat: ", e);
        }
    }

    public List<EspecieHabitat> buscarHabitatsDeUmaEspecie(long idEspecie) {
        try {
            String jpql = "SELECT e FROM EspecieHabitat e WHERE e.idEspecie.id = :id";

            return em.createQuery(jpql, EspecieHabitat.class)
                    .setParameter("id", idEspecie)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar habitats de uma especie: ", e);
        }
    }
}

