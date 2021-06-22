package fr.eni.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ArticleManager {
    private DAO articleDao;

    public ArticleManager() {
        articleDao = DaoFactory.getArticle();
    }

    /**
     * Recherche en étant connecté
     * @param utilisateur
     * @param motCle
     * @param categorie
     * @param achatOuVente
     * @return
     */
    public List<Article> affichageArticlesConnexion(String utilisateur, String motCle, String categorie, String achatOuVente){


    }
    public List<Article>affichageArticles(String categorie, String motCle){

    }

    public void VenteArticle(String utilisateur, String nomArticle, String articleCategorie, LocalDate dateDebutArticle, LocalDateTime debutEnchere, LocalDate dateFinArticle, String rueRetrait,
                        String codePostalRetrait, String villeRetrait){

    }

    public boolean VerifDates(LocalDate dateDebutArticle, LocalDateTime debutEnchere, LocalDate dateFinArticle){

    }

    public Article selectArticle(String idArt){


    }

    public void updateArticle(){

    }
}
