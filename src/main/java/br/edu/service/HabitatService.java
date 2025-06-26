package br.edu.service;

import jakarta.persistence.EntityManager;
import br.edu.dao.HabitatDao;
import br.edu.model.Habitat;

import java.util.List;

public class HabitatService {
    private HabitatDao habitatDao;

    public HabitatService(EntityManager em){
        habitatDao = new HabitatDao(em);
    }

    public void inserir(Habitat habitat){
        habitatDao.cadastrar(habitat);
    }

    public void alterar(Habitat habitat){
        habitatDao.atualizar(habitat);
    }

    public void excluir(Habitat habitat){
        habitatDao.excluir(habitat);
    }

    public Habitat buscarHabitatPorID(long id){
        return habitatDao.buscarPorId(id);
    }

    public List<Habitat> buscarTodosOsHabitats(){
        return habitatDao.buscarTodos();
    }
}