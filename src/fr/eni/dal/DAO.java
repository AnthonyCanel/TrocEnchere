package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Utilisateur;

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
    default boolean verifMail(String email) throws BusinessException {
        return false;
    }
    default boolean verifUtilisateur(String nom) throws BusinessException {
        return false;
    }
    default Utilisateur getUtilisateur(String pseudo, String pwd) throws BusinessException {
        return null;
    }
}
