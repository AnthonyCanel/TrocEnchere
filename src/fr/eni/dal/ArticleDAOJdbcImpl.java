package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Enchere;
import fr.eni.bo.InfoArticle;
import fr.eni.bo.Utilisateur;
import sun.invoke.empty.Empty;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements DAO<Article> {
    BusinessException businessException = new BusinessException();

    private static final String SELECT_ALL = "SELECT TOP(6) no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, photo, vues,no_categorie, libelle, no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM V_ARTICLES_CATEGORIES_UTILISATEURS";

    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, photo, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET no_article = ?, nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, etat_article = ?, photo = ?, no_utilisateur = ?, no_categorie = ?, vues = ? where id=?";

    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id=?";
    //mes ventes en cours
    private static String SELECT_BY_ID_AND_DATES_ENCHERE                    ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo, arts_date_fin_encheres  FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES where utils_no_utilisateur=? and arts_date_fin_encheres>? and arts_date_debut_encheres<? ";
    //mes ventes non débutees
    private static String SELECT_BY_ID_DATE_INF_DEB_ENCHERE                 ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo, arts_date_fin_encheres  from V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?< arts_date_debut_encheres and arts_no_utilisateur=? ";
    //encheres ouvertes
    private static String SELECT_BY_DATE_SUP_DEB_ENCH_AND_INF_FIN_ENCHERE   ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo, arts_date_fin_encheres  FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?>arts_date_debut_encheres and ?< arts_date_fin_encheres AND encs_derniere_enchere=1 ";
    //Mes encheres en Cours
    private static String SELECT_BY_ID_DATE_DER_ENCHERE                     ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo, arts_date_fin_encheres FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE arts_date_debut_encheres < ? AND arts_date_fin_encheres > ? AND encs_no_utilisateur=? AND encs_derniere_enchere=1 ";
    //mes ventes terminees
    private static String SELECT_BY_ID_DATE_SUP_FIN_ENCHERE                 ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo, arts_date_fin_encheres FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?>arts_date_fin_encheres and utils_no_utilisateur=? ";
       //Mes encheres Remportees
    private static String SELECT_BY_ID_AND_ETATENCHERE                      ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo, arts_date_fin_encheres FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE arts_no_utilisateur=? and encs_etat_enchere='Vendu'";

    private static final String SELECT_BY_ID_AND_DATE_FIN_ENCHERE="SELECT no_utilisateur, nom, pseudo, nom_article, montant_enchere, date_fin_encheres FROM V_UTILISATEURS_ENCHERES_ARTICLES_RETRAITS_CATEGORIES WHERE ?>date_fin_encheres and no_utilisateur=? and etat_enchere='Vendu' ORDER BY date_fin_encheres DESC";

    private static String SELECT_BY_ID_VIEW = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rueUtilisateur, codePostalUtilisateur, villeUtilisateur, credit, date_enchere, montant_enchere, etat_enchere, no_acquereur, no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, rueRetrait, codePostalRetrait, villeRetrait, no_categorie, libelle FROM V_UTIL_ENCHERES_ARTICLES_CATEGORIES_LEFT_RETRAITS WHERE no_article = ? ";


//Mes encheres en Cours
    /**
     * retour les encheres en cours pour l'utilisateur
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByIdDateDerEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = new ArrayList<>();
        String requestSql=null;
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%'+?+'%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            requestSql = SELECT_BY_ID_DATE_DER_ENCHERE + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_DATE_DER_ENCHERE);
            ptt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setInt(3, idUtilisateur);

            if(filtreSaisi && categorieSelect){
                ptt.setString(4, filtre);
                ptt.setInt(5, noCategorie);
            }
            if(filtreSaisi && !categorieSelect){
                ptt.setString(4, filtre);
            }
            if(!filtreSaisi && categorieSelect){
                ptt.setInt(4, noCategorie);
            }

            ResultSet rs = ptt.executeQuery();
            while(rs.next()){
                InfoArticle infoArticle = new InfoArticle();
                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres").toLocalDate());
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            ptt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

        return listInfoArticle;
    }
    /**
     * retourne la liste des infoArticles des encheres remportees par l'utilisateur en fonction du filtre saisie et de la catégorie selectionnée
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByIdAndEtatEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = new ArrayList<>();
        String requestSql=null;
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%'+?+'%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            requestSql = SELECT_BY_ID_AND_ETATENCHERE + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_AND_ETATENCHERE);
            ptt.setInt(1, idUtilisateur);

            if(filtreSaisi && categorieSelect){
                ptt.setString(2, filtre);
                ptt.setInt(3, noCategorie);
            }
            if(filtreSaisi && !categorieSelect){
                ptt.setString(2, filtre);
            }
            if(!filtreSaisi && categorieSelect){
                ptt.setInt(2, noCategorie);
            }

            ResultSet rs = ptt.executeQuery();
            while(rs.next()){
                InfoArticle infoArticle = new InfoArticle();
                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres").toLocalDate());
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            ptt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

        return listInfoArticle;

    }

    /**
     * retourne la liste des infoArticles des encheres ouvertes (en cours) en fonction du filtre saisie et de la catégorie selectionnée
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByDateSupDebEnchereAndInfFinEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = new ArrayList<>();
        String requestSql=null;
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){
            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%'+?+'%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }

            //Preparation de la requete
            requestSql = SELECT_BY_DATE_SUP_DEB_ENCH_AND_INF_FIN_ENCHERE + restrictionsComplementaire;



            PreparedStatement ptt = cxn.prepareStatement(requestSql);
            ptt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

            if(filtreSaisi && categorieSelect){
                ptt.setString(3, filtre);
                ptt.setInt(4, noCategorie);
            }

            if(filtreSaisi && !(categorieSelect)){
                ptt.setString(3, filtre);
            }

            if(!(filtreSaisi) && categorieSelect){
                ptt.setInt(3, noCategorie);
            }

            ResultSet rs = ptt.executeQuery();
            while(rs.next()){
                InfoArticle infoArticle = new InfoArticle();
                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres").toLocalDate());
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            ptt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

//        for ( InfoArticle i: listInfoArticle
//        ) {
//            System.out.println(
//                    i.getIdArticle()+"\n"+
//                            i.getPrixArticle()+"\n"+
//                            i.getFinEnchere()+"\n"+
//                            i.getNomArticle()+"\n"
//            );
//        }
        return listInfoArticle;

    }


    /**
     * retourne la liste des infoArticles des ventes terminées de l'utilisateur (uniquement les articles qu'il a mis en vente) en fonction du filtre saisie et de la catégorie selectionnée.
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByIdAndDateSupFinEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = new ArrayList<>();
        String requestSql = null;
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%'+?+'%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            requestSql = SELECT_BY_ID_DATE_SUP_FIN_ENCHERE + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_DATE_SUP_FIN_ENCHERE);
            ptt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setInt(2, idUtilisateur);

            if(filtreSaisi && categorieSelect){
                ptt.setString(3, filtre);
                ptt.setInt(4, noCategorie);
            }
            if(filtreSaisi && !categorieSelect){
                ptt.setString(3, filtre);
            }
            if(!filtreSaisi && categorieSelect){
                ptt.setInt(3, noCategorie);
            }

            ResultSet rs = ptt.executeQuery();
            while(rs.next()){
                InfoArticle infoArticle = new InfoArticle();
                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres").toLocalDate());
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            ptt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

        return listInfoArticle;

    }


    /**
     * retourne la liste des infoArticles des ventes en cours non débutées
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByIdDateInfDebEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = new ArrayList<>();
        String requestSql=null;
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%'+?+'%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            requestSql = SELECT_BY_ID_DATE_INF_DEB_ENCHERE + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_DATE_INF_DEB_ENCHERE);
            ptt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setInt(2, idUtilisateur);

            if(filtreSaisi && categorieSelect){
                ptt.setString(3, filtre);
                ptt.setInt(4, noCategorie);
            }
            if(filtreSaisi && !categorieSelect){
                ptt.setString(3, filtre);
            }
            if(!filtreSaisi && categorieSelect){
                ptt.setInt(3, noCategorie);
            }

            ResultSet rs = ptt.executeQuery();
            while(rs.next()){
                InfoArticle infoArticle = new InfoArticle();
                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres").toLocalDate());
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            ptt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

        return listInfoArticle;

    }

    /**
     * return la liste des infoArticles des ventes en cours en fonction du filtre saisie et de la catégorie selectionnée
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByIdAndDatesEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = new ArrayList<>();
        String requestSql=null;
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%'+?+'%'";
                filtreSaisi = true;

            }
            if(noCategorie != 0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            requestSql = SELECT_BY_ID_AND_DATES_ENCHERE + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_AND_DATES_ENCHERE);
            ptt.setInt(1, idUtilisateur);
            ptt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            if(filtreSaisi && categorieSelect){
                ptt.setString(4, filtre);
                ptt.setInt(5, noCategorie);
            }
            if(filtreSaisi && !categorieSelect){
                ptt.setString(4, filtre);
            }
            if(!filtreSaisi && categorieSelect){
                ptt.setInt(4, noCategorie);
            }

            ResultSet rs = ptt.executeQuery();
            while(rs.next()){
                InfoArticle infoArticle = new InfoArticle();
                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres").toLocalDate());
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            ptt.close();
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

        return listInfoArticle;

    }


    /**
     * Récupère toute les données de la table article
     * @return listeArticle
     * @throws BusinessException LECTURE_ARTICLE_ECHEC
     */
    @Override
    public List<Article> selectAll() throws BusinessException {
        List<Article> listeArticle = new ArrayList<>();
        List<Utilisateur> listeUtilisateur = new ArrayList<>();
        List<Categorie> listeCategorie = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            Statement pstmt = cnx.createStatement();
            ResultSet rs = pstmt.executeQuery(SELECT_ALL);
            Utilisateur utilisateurEnCours = new Utilisateur();
            while (rs.next()) {
                if(rs.getInt(1) != utilisateurEnCours.getNoUtilisateur()){
                    utilisateurEnCours = new Utilisateur();
                    utilisateurEnCours.setNoUtilisateur(rs.getInt(13));
                    utilisateurEnCours.setPseudo(rs.getString(14));
                    utilisateurEnCours.setNom(rs.getString(15));
                    utilisateurEnCours.setPrenom(rs.getString(16));
                    utilisateurEnCours.setEmail(rs.getString(17));
                    utilisateurEnCours.setTelephone(rs.getString(18));
                    utilisateurEnCours.setRue(rs.getString(19));
                    utilisateurEnCours.setCodePostal(rs.getString(20));
                    utilisateurEnCours.setVille(rs.getString(21));
                    utilisateurEnCours.setMotDePasse(rs.getString(22));
                    utilisateurEnCours.setCredit(rs.getInt(23));
                    boolean admin = rs.getByte(24) != 0;
                    utilisateurEnCours.setAdmin(admin);
                    listeUtilisateur.add(utilisateurEnCours);
                }
                Categorie categorieEnCours = new Categorie();
                if (rs.getInt(1) != categorieEnCours.getNoCategorie()){
                    categorieEnCours.setNoCategorie(rs.getInt(1));
                    categorieEnCours.setLibelle(rs.getString(2));
                    listeCategorie.add(categorieEnCours);
                }
                Article articleEnCours = new Article();
                articleEnCours.setNoArticle(rs.getInt("no_article"));
                articleEnCours.setNomArticle(rs.getString("nom_article"));
                articleEnCours.setDescription(rs.getString("description"));
                articleEnCours.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                articleEnCours.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                articleEnCours.setPrixInitial(rs.getInt("prix_initial"));
                articleEnCours.setPrixVente(rs.getInt("prix_vente"));
                articleEnCours.setEtat_Article(rs.getString("etat_article"));
                articleEnCours.setPhoto(rs.getString("photo"));
                articleEnCours.setVues(rs.getInt("vues"));
                articleEnCours.setUtilisateur(utilisateurEnCours);
                articleEnCours.setCategorie(categorieEnCours);
                listeArticle.add(articleEnCours);
            }

        } catch (Exception e) {
          e.printStackTrace();
          businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
          throw businessException;
        }
        return listeArticle;
    }

    /**
     * Sélectionne un article selon son identifiant
     * Se base sur une vue qui ramène toutes les tables ( à noter que la jointure
     * entre la table Retraits et Articles est LEFT JOIN)
     * @param id
     * @return
     */
    @Override
    public Article selectById(int id) {
        return null;
    }

    /**
     * Insertion d'article dans la table article
     * @param article
     * @throws BusinessException
     */
    @Override
    public void insert(Article article) throws BusinessException {
     if(article == null){
         businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
         throw businessException;
     }

        try(Connection cnx = ConnectionProvider.getConnection()){
            try{
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                ResultSet rs;
                if(article.getNoArticle() == 0){
                    pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, article.getNomArticle());
                    pstmt.setString(2, article.getDescription());
                    pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
                    pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
                    pstmt.setInt(5, article.getPrixInitial());
                    pstmt.setInt(6, article.getPrixVente());
                    pstmt.setString(7, article.getEtat_Article());
                    pstmt.setString(8, article.getPhoto());
                    pstmt.setInt(9, article.getUtilisateur().getNoUtilisateur());
                    pstmt.setInt(10, article.getCategorie().getNoCategorie());
                    pstmt.executeUpdate();
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()){
                        article.setNoArticle(rs.getInt(1));
                    }
                    rs.close();
                    pstmt.close();
                    cnx.commit();
                }
        } catch (Exception e) {
            e.printStackTrace();
            cnx.rollback();
            throw e;
        }
    } catch (Exception e) {
        e.printStackTrace();
        businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
        throw businessException;
    }
    }

    /**
     * Update tous les paramètres d'un article
     * @param article
     * @throws BusinessException
     */
    @Override
    public void update(Article article) throws BusinessException{
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
            pstmt.setString(1, article.getNomArticle());
            pstmt.setString(2, article.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
            pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
            pstmt.setInt(5, article.getPrixInitial());
            pstmt.setInt(6, article.getPrixVente());
            pstmt.setString(7, article.getEtat_Article());
            pstmt.setString(8, article.getPhoto());
            pstmt.setInt(9, article.getUtilisateur().getNoUtilisateur());
            pstmt.setInt(10, article.getCategorie().getNoCategorie());
            pstmt.executeUpdate();
        } catch (SQLException e) {
         e.printStackTrace();
         businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
         throw businessException;
        }
    }
    /**
     * Supprime un article selon son id
     * @param id
     * @throws BusinessException
     */
    @Override
    public void delete(int id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
         e.printStackTrace();
         BusinessException businessException = new BusinessException();
         businessException.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ERREUR);
         throw businessException;
     }
    }

    /**
     * Méthode pour récupérer les différentes données pour la page enchère
     * @param id no_article
     * @return
     */
    @Override
    public List<Article> selectByEnchere(int id) throws BusinessException {
        DAO<Retrait> retraitDAO = DAOFactory.getRetraitDAO();
        List<Article> listeArticles = new ArrayList<>();
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        List<Categorie> listeCategories = new ArrayList<>();
        List<Enchere> listeEncheres = new ArrayList<>();
        List<Retrait> listeRetraits = new ArrayList<>();
        if(id == 0){
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ID_ARTICLE_ECHEC);
            throw businessException;
        }else{
            try (Connection cnx = ConnectionProvider.getConnection();
                 PreparedStatement pstt = cnx.prepareStatement(SELECT_BY_ID_VIEW)){
                pstt.setInt(1,id);
                ResultSet rs = pstt.executeQuery();
                Utilisateur utilisateurEnCours = new Utilisateur();
                while(rs.next()){
                    //Si l'identifiant de la requête est différent de l'identifiant de l'objet
                    if(rs.getInt("no_utilisateur") != utilisateurEnCours.getNoUtilisateur()){
//                        utilisateurEnCours = new Utilisateur();
                        utilisateurEnCours.setNoUtilisateur(rs.getInt("no_utilisateur"));
                        utilisateurEnCours.setPseudo(rs.getString("pseudo"));
                        utilisateurEnCours.setNom(rs.getString("nom"));
                        utilisateurEnCours.setPrenom(rs.getString("prenom"));
                        utilisateurEnCours.setEmail(rs.getString("email"));
                        utilisateurEnCours.setTelephone(rs.getString("telephone"));
                        utilisateurEnCours.setRue(rs.getString("rueUtilisateur"));
                        utilisateurEnCours.setCodePostal(rs.getString("codePostalUtilisateur"));
                        utilisateurEnCours.setVille(rs.getString("villeUtilisateur"));
                        utilisateurEnCours.setCredit(rs.getInt("credit"));
                        listeUtilisateurs.add(utilisateurEnCours);
                    }
                    //Catégorie
                    Categorie categorieEnCours = new Categorie();
                    if (rs.getInt("no_categorie") != categorieEnCours.getNoCategorie()){
                        categorieEnCours.setNoCategorie(rs.getInt("no_categorie"));
                        categorieEnCours.setLibelle(rs.getString("libelle"));
                        listeCategories.add(categorieEnCours);
                    }

                    //Retrait
                    Retrait retraitEnCours = null;
                    if(rs.getInt("no_article") != retraitEnCours.getId()){
                        retraitEnCours = retraitDAO.selectById(rs.getInt("no_article")); //SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rueUtilisateur, codePostalUtilisateur, villeUtilisateur, credit, date_enchere, montant_enchere, etat_enchere, no_acquereur, no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, rueRetrait, codePostalRetrait, villeRetrait, no_categorie, libelle FROM V_UTIL_ENCHERES_ARTICLES_CATEGORIES_LEFT_RETRAITS WHERE no_article = ?"
                        if(!retraitEnCours.equals(null)) {
                            listeRetraits.add(retraitEnCours);
                        }
                    }
                    //Enchere
                    Enchere enchereEnCours = new Enchere();
                    if(rs.getInt("no_article")!= enchereEnCours.getNoArticle() && rs.getInt("no_utilisateur") != enchereEnCours.getNoUtilisateur()){ //date_enchere, montant_enchere, etat_enchere, no_acquereur
                        enchereEnCours.setNoArticle(rs.getInt("no_article"));
                        enchereEnCours.setNoUtilisateur(rs.getInt("no_utilisateur"));
                        enchereEnCours.setDateEnchere(rs.getTimestamp("date_enchere"));
                        enchereEnCours.setMontantEnchere(rs.getInt("montant_enchere"));
                        enchereEnCours.setEtatEnchere(rs.getString("etat_enchere"));
                        enchereEnCours.setNoAcquereur(rs.getInt("no_acquereur"));
                        listeEncheres.add(enchereEnCours);
                    }
                    //Article
                    Article articleEnCours = new Article();
                    if(rs.getInt("no_article") != articleEnCours.getNoArticle()) {
                        articleEnCours.setNoArticle(rs.getInt("no_article"));
                        articleEnCours.setNomArticle(rs.getString("nom_article"));
                        articleEnCours.setDescription(rs.getString("description"));
                        articleEnCours.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
                        articleEnCours.setDateFinEncheres(rs.getDate("date_fin_encheres"));
                        articleEnCours.setPrixInitial(rs.getInt("prix_initial"));
                        articleEnCours.setPrixVente(rs.getInt("prix_vente"));
                        articleEnCours.setEtat_Article(rs.getString("etat_article"));
                        articleEnCours.setUtilisateur(utilisateurEnCours);
                        articleEnCours.setCategorie(categorieEnCours);
                        articleEnCours.setRetrait(retraitEnCours);
                        articleEnCours.setEnchere(enchereEnCours);
                        listeArticles.add(articleEnCours);
                    }
                } //Fin de la boucle while
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listeArticles;
    }
}
