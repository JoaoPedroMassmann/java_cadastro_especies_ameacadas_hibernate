package br.edu.service;

import br.edu.dao.EspecieHabitatDao;
import br.edu.model.EspecieHabitat;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EspecieHabitatService {
    private EspecieHabitatDao especieHabitatDao;

    public EspecieHabitatService(EntityManager em){
        especieHabitatDao = new EspecieHabitatDao(em);
    }

    public void inserir(EspecieHabitat especieHabitat){
        especieHabitatDao.cadastrar(especieHabitat);
    }

    public void alterar(EspecieHabitat especieHabitat){
        especieHabitatDao.atualizar(especieHabitat);
    }

    public void excluir(EspecieHabitat especieHabitat){
        especieHabitatDao.excluir(especieHabitat);
    }

    public EspecieHabitat buscarRelacaoEspecieHabitatPorId(long id){
        return especieHabitatDao.buscarPorId(id);
    }


    public List<EspecieHabitat> buscarTodasAsRelacoesEspeciesHabitat() {
        return especieHabitatDao.buscarTodos();
    }

    public List<EspecieHabitat> buscarEspeciesEmUmHabitat(long idHabitat) {
        return especieHabitatDao.buscarEspeciesEmUmHabitat(idHabitat);
    }

    public List<EspecieHabitat> buscarHabitatsDeUmaEspecie(long idEspecie) {
        return especieHabitatDao.buscarHabitatsDeUmaEspecie(idEspecie);
    }
}