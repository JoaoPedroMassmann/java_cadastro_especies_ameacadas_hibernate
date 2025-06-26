package br.edu.service;

import jakarta.persistence.EntityManager;
import br.edu.dao.EspecieDao;
import br.edu.model.Especie;

import java.util.List;

public class EspecieService {
    private EspecieDao especieDao;

    public EspecieService(EntityManager em){
        especieDao = new EspecieDao(em);
    }

    public void inserir(Especie especie){
        especieDao.cadastrar(especie);
    }

    public void alterar(Especie especie){
        especieDao.atualizar(especie);
    }

    public void excluir(Especie especie){
        especieDao.excluir(especie);
    }

    public Especie buscarEspeciePorId(long id){
        return especieDao.buscarPorId(id);
    }

    public Especie buscarEspeciePorNomeCientifico(String nomeCientifico) {
        return especieDao.buscarPorNomeCientifico(nomeCientifico);
    }

    public List<Especie> buscarTodasAsEspecies() {
        return especieDao.buscarTodos();
    }
}