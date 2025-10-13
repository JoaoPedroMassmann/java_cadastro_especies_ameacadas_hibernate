package br.edu.service;

import br.edu.dao.RegistroOcorrenciaDao;
import br.edu.model.RegistroOcorrencia;
import br.edu.vo.RelatorioOcorrenciaPeriodoVo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroOcorrenciaService {
    private RegistroOcorrenciaDao registroOcorrenciaDao;

    public RegistroOcorrenciaService(EntityManager em){
        registroOcorrenciaDao = new RegistroOcorrenciaDao(em);
    }

    public void inserir(RegistroOcorrencia registroOcorrencia){
        registroOcorrenciaDao.cadastrar(registroOcorrencia);
    }

    public void alterar(RegistroOcorrencia registroOcorrencia){
        registroOcorrenciaDao.atualizar(registroOcorrencia);
    }

    public void excluir(RegistroOcorrencia registroOcorrencia){
        registroOcorrenciaDao.excluir(registroOcorrencia);
    }

    public RegistroOcorrencia buscarRegistroOcorrenciaPorId(long id){
        return registroOcorrenciaDao.buscarPorId(id);
    }

    public List<RegistroOcorrencia> buscarTodosOsRegistrosDeOcorrencia(){
        return registroOcorrenciaDao.buscarTodos();
    }

    public List<RegistroOcorrencia> buscarRegistrosOcorrenciaPorObservador(String observador) {
        return registroOcorrenciaDao.buscarRegistrosObservador(observador);
    }

    public List<RegistroOcorrencia> buscarRegistrosOcorrenciaPorEspecie(Long idEspecie) {
        return registroOcorrenciaDao.buscarRegistrosEspecie(idEspecie);
    }

    public List<RegistroOcorrencia> buscarRegistrosOcorrenciaPorHabitat(Long idHabitat) {
        return registroOcorrenciaDao.buscarRegistrosHabitat(idHabitat);
    }

    public List<RelatorioOcorrenciaPeriodoVo> relatorioPorPeriodo(LocalDate inicio, LocalDate fim) {
        return registroOcorrenciaDao.listarOcorrenciasPorPeriodo(inicio, fim);
    }

}