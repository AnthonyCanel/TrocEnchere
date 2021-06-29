package fr.eni.bll;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Utilisateur;
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
        validerCoordonnees(article, businessException);
        if(!businessException.hasErreurs()){
            articleDAO.insert(article);
        }
        else{
            throw businessException;
        }
        return article;
    }

    private void validerCoordonnees(Article article, BusinessException bE) {
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
        if(article.getRetrait().getCodePostal() == null ||  article.getRetrait().getCodePostal().trim().equals("") || article.getRetrait().getCodePostal().length() > 10 || article.getRetrait().getCodePostal().contains("[a-zA-Z]")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_CODEPOSTAL_ERREUR);
        }
        if(article.getRetrait().getRue() == null || article.getRetrait().getRue().trim().equals("") || article.getRetrait().getRue().length() >30){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_RUE_ERREUR);
        }
        if(article.getRetrait().getVille() == null || article.getRetrait().getVille().trim().equals("") || article.getRetrait().getVille().length() >30 || article.getRetrait().getVille().contains("[0-9]")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_VILLE_ERREUR);
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
    public List<Article> ventesTerminées(int idUtilisateur){
        ArticleDAOJdbcImpl adao = new ArticleDAOJdbcImpl();
        List<Article> listArticles = adao.selectByIdDateEnchereEtatEnchere(idUtilisateur);
        return listArticles;

    }

    /**
     * retourne les ventes non débutées
     * @return une liste d'article
     */
    public List<Article> ventesNonDebutees(){

        ArticleDAOJdbcImpl adao = new ArticleDAOJdbcImpl();
        List<Article> listArticle =  adao.selectByDateInfDebEnchere();

        return listArticle;
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
    public List<Article> MesVentesEnCours(int idUtilisateur, int idCategorie) throws BusinessException {
        List<Article> listArticle = null;

        ArticleDAOJdbcImpl articleDAOJdbc = new ArticleDAOJdbcImpl();
       DAO<Article> articleDAO = DAOFactory.getArticleDAO();

        listArticle = articleDAOJdbc.selectByIdDateFinEnchere(idUtilisateur, idCategorie);
        return listArticle;
    }

}
