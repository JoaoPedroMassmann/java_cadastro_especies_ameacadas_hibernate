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


    // Metodo para buscar animal por nome cientifico.

    public List<Especie> buscaPorId(int idEspecie) {
        try{
            String jpql = "SELECT c FROM Especie c WHERE c.idEspecie = :idEspecie";

            return em.createQuery(jpql, Especie.class)
                    .setParameter("idEspecie", idEspecie)
                    .getResultList();
        } catch (Exception e){
            throw new DataAccessException("Erro ao buscar categorias por id: " + idEspecie, e);
        }
    }

    public List<Especie> buscarPorNome(String nomeCientifico) {
        try{
            // Consulta JPQL para buscar categorias por nome.
            String jpql = "SELECT c FROM Especie c WHERE c.nomeCientifico = :nomeCientifico";

            return em.createQuery(jpql, Especie.class)
                    .setParameter("nomeCientifico", nomeCientifico) // Define o parâmetro "nome" na consulta.
                    .getResultList(); // Executa a consulta e retorna os resultados.
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar categorias por nome cientifico: " + nomeCientifico, e);
        }
    }
}

