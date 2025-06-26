package br.edu.dao;

import jakarta.persistence.EntityManager;
import br.edu.exception.DataAccessException;
import br.edu.model.RegistroOcorrencia;

import java.util.List;

public class RegistroOcorrenciaDao extends GenericDao<RegistroOcorrencia>{
    private EntityManager em;

    public RegistroOcorrenciaDao (EntityManager em) {
        super(em, RegistroOcorrencia.class);
    }

    public List<RegistroOcorrencia> buscarRegistrosObservador(String observador) {
        try{
            String jpql = "SELECT r FROM RegistroOcorrencia r WHERE LOWER(r.observador) = LOWER(:observador)";
            return em.createQuery(jpql, RegistroOcorrencia.class)
                    .setParameter("observador", observador)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registro de ocorrencia por observador: ", e);
        }
    }

    public List<RegistroOcorrencia> buscarRegistrosEspecie(long idEspecie) {
        try{
            String jpql = "SELECT r FROM RegistroOcorrencia r WHERE r.especie.id = :id ";
            return em.createQuery(jpql, RegistroOcorrencia.class)
                    .setParameter("id", idEspecie)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registro de ocorrencia por especie: ", e);
        }
    }

    public List<RegistroOcorrencia> buscarRegistrosHabitat(long idHabitat) {
        try{
            String jpql = "SELECT r FROM RegistroOcorrencia r WHERE r.habitat.id = :id ";
            return em.createQuery(jpql, RegistroOcorrencia.class)
                    .setParameter("id", idHabitat)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registro de ocorrencia por habitat: ", e);
        }
    }
}
