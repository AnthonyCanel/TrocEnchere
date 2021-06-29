package fr.eni.dal;

import fr.eni.bo.Utilisateur;

public abstract class DAOFactory {

    public static DAO getArticleDAO(){
        return new ArticleDAOJdbcImpl();
    }

    public static DAO getCategorieDAO(){
        return new CategorieDAOJdbcImpl();
    }

    public static DAO getEnchereDAO(){
        return new EnchereDAOJdbcImpl();
    }

    public static DAO getRetraitDAO(){

        return new RetraitDAOJdbcImpl();
    }

    public static DAO getUtilisateurDAO(){

        return new UtilisateurDAOJdbcImpl();
    }
}
