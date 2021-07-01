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
    private final DAO<Article> articleDAO;
    private final BusinessException businessException = new BusinessException();


    public Article ajouterArticle(Article article) throws BusinessException {
        validerCaracteristique(article, businessException);
        if(!businessException.hasErreurs()){
            articleDAO.insert(article);
        }
        else{
            throw businessException;
        }
        return article;
    }

    private void validerCaracteristique(Article article, BusinessException bE) {
        if (article.getNomArticle() == null || article.getNomArticle().trim().equals("")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_SAISIE_ERREUR);
        }
        if(article.getDescription() == null || article.getDescription().trim().equals("") || article.getDescription().length()>200){
            bE.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_SAISIE_ERREUR);
        }
        if(article.getCategorie().getNoCategorie() == 0){
            bE.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_CATEGORIE_NUL_ERREUR);
        }
        if(article.getPrixInitial() < 0) {
            bE.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_INITIAL_ERREUR);
        }
    }

    public ArticleManager() {
        articleDAO = DAOFactory.getArticleDAO();
    }

    /**
     * retourne toutes les ventes Terminées (etat enchere 'Vendu') auquelles l'utilisateur a participé de la plus récente a la plus ancienne.
     * @param idUtilisateur
     * @return
     */
//    public List<Article> ventesTerminées(int idUtilisateur){
//        ArticleDAOJdbcImpl adao = new ArticleDAOJdbcImpl();
//       List<Article> listArticles = adao.selectByIdDateEnchereEtatEnchere(idUtilisateur);
//        return listArticles;
//
//    }

    /**
     * retourne les ventes non débutées
     * @return une liste d'article
     */
//    public List<Article> ventesNonDebutees(){
//
//        ArticleDAOJdbcImpl adao = new ArticleDAOJdbcImpl();
//        List<Article> listArticle =  adao.selectByDateInfDebEnchere();
//
//        return listArticle;
//    }
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
            listeArticle = articleDAO.selectAll();
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
    public List<InfoArticle> MesVentesEnCours(int idUtilisateur, String filtre, int idCategorie) throws BusinessException {
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
            listeArticles = articleDAO.selectByEnchere(idArticle);
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
