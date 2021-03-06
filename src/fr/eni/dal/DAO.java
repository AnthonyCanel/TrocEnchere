package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Enchere;
import fr.eni.bo.InfoArticle;
import fr.eni.bo.Utilisateur;

import java.util.List;

public interface DAO<T>{

    List<T> selectAll() throws BusinessException;
    T selectById(int id) throws BusinessException;
    void insert(T objet) throws BusinessException;
    void update(T objet) throws BusinessException;
    void delete(int id) throws BusinessException;

    //TODO Ajouter le seletById apres test des requetes
    default void restituerPoints(int points, int idAcquereur) throws BusinessException {
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
    default List<Article> selectByIdDateFinEnchere(int idUtilisateur, int idCategorie) throws BusinessException{
        return null;
    }

    /**
     * Selection d'un article avec les informations pour PageEncherir
     * @param idArticle
     * @param idEncherisseur
     * @return
     * @throws BusinessException
     */
    default List<Article> selectByEnchere(int idArticle, int idEncherisseur) throws BusinessException {
        return null;
    }

    default Article selectByEnchereRemportee(int id) throws BusinessException {
        return null;
    }




    default List<InfoArticle> selectByIdAndDatesEnchere(int idUtilisateur, String filtre, int noCategorie) throws BusinessException{return null;}
    default List<InfoArticle> selectByIdDateInfDebEnchere(int idUtilisateur, String filtre, int noCategorie) throws BusinessException{return null;}
    default List<InfoArticle> selectByIdAndDateSupFinEnchere(int idUtilisateur, String filtre, int noCategorie) throws BusinessException{return null;}

    default List<InfoArticle> selectByDateSupDebEnchereAndInfFinEnchere(int idUtilisateur, String filtre, int noCategorie) throws BusinessException{return null;}
    default List<InfoArticle> selectByIdAndEtatEnchere(int idUtilisateur, String filtre, int noCategorie) throws BusinessException{return null;}
    default List<InfoArticle> selectByIdDateDerEnchere(int idUtilisateur, String filtre, int noCategorie) throws BusinessException{return null;}
    default List<InfoArticle> rechercheParFiltreEtNoCategorie(int idUtilisateur, String filtre, int noCategorie)throws BusinessException{return null;}

    default Utilisateur selectByPseudo(String pseudo) throws BusinessException {return null;}

    default List<Enchere> selectByAcqEtArtVendu(int acquereur) throws BusinessException {return null;}
}
