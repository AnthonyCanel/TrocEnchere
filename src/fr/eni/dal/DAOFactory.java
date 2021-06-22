package fr.eni.dal;

import fr.eni.bo.Utilisateur;

public abstract class DAOFactory {

    public static Dao getArticleDAO(){
        return new ArticleDAOJdbcImpl();
    }

    public static Dao getCategorieDAO(){
        return new CategorieDAOJdbcImpl();
    }

    public static Dao getEnchereDAO(){
        return new EnchereDAOJdbcImpl();
    }

    public static Dao getRetraitDAO(){
        return new UtilisateurDAOJdbcImpl();
    }

    public static Dao getUtilisateurDAO(){
        return new UtilisateurDAOJdbcImpl();
    }
}
