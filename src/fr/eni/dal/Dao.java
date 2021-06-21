package fr.eni.dal;

import java.util.List;

interface Dao<T>{

    List<T> selectAll();
    T selectById(T id);
    void insert(T data);
    void update(T data);
    void delete(T data);
//TODO Ajouter le seletById apres test des requetes

}
