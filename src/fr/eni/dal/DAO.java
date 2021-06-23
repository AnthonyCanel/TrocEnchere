package fr.eni.dal;

import fr.eni.BusinessException;

import java.util.List;

public interface DAO<T>{

    List<T> selectAll() throws BusinessException;
    T selectById(int id) throws BusinessException;
    void insert(T objet) throws BusinessException;
    void update(T objet) throws BusinessException;
    void delete(int id) throws BusinessException;

    //TODO Ajouter le seletById apres test des requetes
    default T restituerPoints(int idvendeur, int idAcquereur) {
        return null;
    }

}
