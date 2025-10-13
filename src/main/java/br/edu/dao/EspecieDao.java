package br.edu.dao;

import br.edu.vo.RelatorioEspeciesEstadoVo;
import jakarta.persistence.EntityManager;
import br.edu.exception.DataAccessException;
import br.edu.model.Especie;

import java.util.List;

public class EspecieDao extends GenericDao<Especie> {
    private EntityManager em;

    public EspecieDao (EntityManager em) {
        super(em, Especie.class);
        this.em = em;
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

    public List<RelatorioEspeciesEstadoVo> gerarRelatorioEspeciesEstado(){
        try{
            String jpql = "SELECT new br.edu.vo.RelatorioEspeciesEstadoVo("
                    + "e.estadoConservacao, "
                    + "COUNT(e))"
                    + "FROM Especie e "
                    + "GROUP BY e.estadoConservacao "
                    + "ORDER BY COUNT(e) DESC";

            return em.createQuery(jpql, RelatorioEspeciesEstadoVo.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao retornar relatório de estados de conervação.", e);
        }    }
}

