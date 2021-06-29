package fr.eni.bll;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.InfoArticle;
import fr.eni.dal.ArticleDAOJdbcImpl;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {
    private DAO<Article> articleDao;



    public ArticleManager() {
        articleDao = DAOFactory.getArticleDAO();
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
    public List<InfoArticle> MesVentesEnCours(int idUtilisateur,String filtre, int idCategorie) throws BusinessException {
        List<InfoArticle> listInfoArticle = null;

       DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listInfoArticle =   articleDAO.selectByIdAndDatesEnchere(idUtilisateur,filtre, idCategorie);
        return listInfoArticle;
    }

    public List<InfoArticle> encheresOuvertes(int idUtilisateur, String filtre, int noCategorie) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;
        DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listInfoArticles = articleDAO.selectByDateSupDebEnchereAndInfFinEnchere(idUtilisateur, filtre, noCategorie );
        return listInfoArticles;

    }

    public List<InfoArticle> MesVentesNonDebutees(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;
        DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listInfoArticles = articleDAO.selectByIdDateInfDebEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;

    }

    public List<InfoArticle> mesEncheresEnCours(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;
        DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listInfoArticles = articleDAO.selectByIdDateDerEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;


    }

    public List<InfoArticle> MesVentesTerminees(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;
        DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listInfoArticles = articleDAO.selectByIdAndDateSupFinEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;

    }

    /**
     * Choisir un Article selon les enchères pour PageEncherir
     */
    public List<Article> ChoisirArticlesEncherir(int idArticle){
        List<Article> listeArticles = null;
        try {
            listeArticles = articleDao.selectByEnchere(idArticle);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return listeArticles;
    }

    public List<InfoArticle> mesEncheresRemportees(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {

        List<InfoArticle> listInfoArticles = null;
        DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listInfoArticles = articleDAO.selectByIdAndEtatEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;
    }
}
