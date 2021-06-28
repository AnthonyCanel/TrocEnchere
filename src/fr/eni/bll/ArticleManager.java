package fr.eni.bll;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.dal.ArticleDAOJdbcImpl;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {
    private DAO articleDao;

    public List<Article> ventesNonDebutees(){

        ArticleDAOJdbcImpl adao = new ArticleDAOJdbcImpl();
        List<Article> listArticle =  adao.selectByDateInfDebEnchere();

        return listArticle;
    }

    public ArticleManager() {
        articleDao = DAOFactory.getArticleDAO();
    }

    /**
     * Recherche en étant connecté
     *
     * @param utilisateur
     * @param motCle
     * @param categorie
     * @param achatOuVente
     * @return
     */
    public List<Article> affichageArticlesConnexion(String utilisateur, String motCle, String categorie, String achatOuVente) {
        return null;
    }

    public List<Article> affichageArticles(String categorie, String motCle) {
        List<Article> listeArticle = new ArrayList<>();
        try {
            listeArticle = articleDao.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return listeArticle;
    }

    public void VenteArticle(String utilisateur, String nomArticle, String articleCategorie, LocalDate dateDebutArticle, LocalDateTime debutEnchere, LocalDate dateFinArticle, String rueRetrait,
                             String codePostalRetrait, String villeRetrait) {

    }

    public boolean VerifDates(LocalDate dateDebutArticle, LocalDate dateFinArticle) {
        return true;
    }

    public Article selectArticle(String idArt) {
        return null;
    }

    /**
     * return listArticle en fonction de l'id utilisateur et de l'id categories (Ventes en cours de l'utilisateur)
     * @param idUtilisateur
     * @param idCategorie
     * @return
     * @throws BusinessException
     */
    public List<Article> MesVentesEnCours(int idUtilisateur, int idCategorie) throws BusinessException {
        List<Article> listArticle = null;

        ArticleDAOJdbcImpl articleDAOJdbc = new ArticleDAOJdbcImpl();
       DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listArticle = articleDAOJdbc.selectByIdDateFinEnchere(idUtilisateur, idCategorie);
        return listArticle;
    }

}
