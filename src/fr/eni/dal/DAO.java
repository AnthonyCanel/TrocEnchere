package fr.eni.dal;

import fr.eni.bll.BusinessException;

import java.time.LocalDate;
import java.util.List;

public interface DAO<T>{

    List<T> selectAll() throws BusinessException, DALException;
    T selectById(int id);
    void insert(T objet);
    void update(T objet);
    void delete(int id);
//TODO Ajouter le seletById apres test des requetes

}
