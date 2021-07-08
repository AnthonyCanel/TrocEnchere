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

    public ArticleManager() {
        articleDAO = DAOFactory.getArticleDAO();
    }

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




    public List<Article> affichageArticles(String categorie, String motCle) {
        List<Article> listeArticle = new ArrayList<>();
        try {
            listeArticle = articleDAO.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return listeArticle;
    }


    public boolean VerifDates(LocalDate dateDebutArticle, LocalDate dateFinArticle) {
        return true;
    }


    /**
     * return listArticle en fonction du filtre et de la catégorie selectionnée
     * @param idUtilisateur
     * @param filtre
     * @param idCategorie
     * @return
     * @throws BusinessException
     */
    public List<InfoArticle> rechercheParFiltreEtNoCategorie(int idUtilisateur, String filtre, int idCategorie) throws BusinessException {
        List<InfoArticle> listInfoArticle = null;

        listInfoArticle =   articleDAO.rechercheParFiltreEtNoCategorie(idUtilisateur,filtre, idCategorie);
        return listInfoArticle;
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

        listInfoArticle =   articleDAO.selectByIdAndDatesEnchere(idUtilisateur,filtre, idCategorie);
        return listInfoArticle;
    }

    public List<InfoArticle> encheresOuvertes(int idUtilisateur, String filtre, int noCategorie) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;

        listInfoArticles = articleDAO.selectByDateSupDebEnchereAndInfFinEnchere(idUtilisateur, filtre, noCategorie );

        return listInfoArticles;

    }

    public List<InfoArticle> MesVentesNonDebutees(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;

        listInfoArticles = articleDAO.selectByIdDateInfDebEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;

    }

    public List<InfoArticle> mesEncheresEnCours(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;

        listInfoArticles = articleDAO.selectByIdDateDerEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;


    }

    public List<InfoArticle> MesVentesTerminees(int idUtilisateur, String filtreSaisi, int noCatSelect) throws BusinessException {
        List<InfoArticle> listInfoArticles = null;

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

        listInfoArticles = articleDAO.selectByIdAndEtatEnchere(idUtilisateur, filtreSaisi, noCatSelect);
        return listInfoArticles;
    }

    /**
     * Récupère les informations de l'enchère remportée via l'identifiant utilisateur
     * @param idUtilisateur
     * @return objet Article avec les objets : Utilisateur, Retrait
     * @throws BusinessException
     */
    public Article ChoisirArticlesRemportes(int idUtilisateur) throws BusinessException {
        Article art = null;
        art = articleDAO.selectByEnchereRemportee(idUtilisateur);
        return art;
    }

}
