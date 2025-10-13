package br.edu.dao;

import br.edu.vo.RelatorioEspeciesEstadoVo;
import br.edu.vo.RelatorioOcorrenciaPeriodoVo;
import jakarta.persistence.EntityManager;
import br.edu.exception.DataAccessException;
import br.edu.model.RegistroOcorrencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RegistroOcorrenciaDao extends GenericDao<RegistroOcorrencia>{
    private EntityManager em;

    public RegistroOcorrenciaDao (EntityManager em) {
        super(em, RegistroOcorrencia.class);
        this.em = em;
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

    public List<RelatorioOcorrenciaPeriodoVo> listarOcorrenciasPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        try{
            LocalDateTime inicio = dataInicio.atStartOfDay();
            LocalDateTime fim = dataFim.atTime(23, 59, 59, 999_999_999); // até o fim do dia

            String jpql = "SELECT new br.edu.vo.RelatorioOcorrenciaPeriodoVo(" +
                    "e.nomeCientifico, " +
                    "h.idHabitat, " +
                    "r.observador, " +
                    "r.dataHora) " +
                    "FROM RegistroOcorrencia r " +
                    "JOIN r.especie e " +
                    "JOIN r.habitat h " +
                    "WHERE r.dataHora BETWEEN :inicio AND :fim " +
                    "ORDER BY r.dataHora";

            return em.createQuery(jpql, RelatorioOcorrenciaPeriodoVo.class)
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registro de ocorrencia por habitat: ", e);
        }
    }
}
