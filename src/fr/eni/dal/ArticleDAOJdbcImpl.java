package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.InfoArticle;
import fr.eni.bo.Utilisateur;
import sun.invoke.empty.Empty;

import javax.sound.sampled.Line;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements DAO<Article> {
    BusinessException businessException = new BusinessException();

    private static final String SELECT_ALL = "SELECT TOP(6) no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, photo, vues,no_categorie, libelle, no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM V_ARTICLES_CATEGORIES_UTILISATEURS";

    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET no_article = ?, nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, etat_article = ?, photo = ?, no_utilisateur = ?, no_categorie = ?, vues = ? where id=?";

    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id=?";
    //mes ventes en cours
    private static String selectByIdAndDatesEnchere ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo  FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES where utils_no_utilisateur=? and arts_date_fin_encheres>? and arts_date_debut_encheres<?";
    //mes ventes non débutees
    private static String selectByIdDateInfDebEnchere ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo  from V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?< arts_date_debut_encheres and arts_no_utilisateur=? ORDER BY arts_date_debut_encheres DESC";
    //encheres ouvertes
    private static String selectByDateSupDebEnchereAndInfFinEnchere ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?> arts_date_debut_encheres and ?< arts_date_fin_encheres ORDER BY arts_date_debut_encheres DESC";
    //Mes encheres en Cours
    private static String selectByIdDateDerEnchere ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE arts_date_debut_encheres < ? AND arts_date_fin_encheres > ? AND encs_no_utilisateur=? AND encs_derniere_enchere=1";



    //mes ventes terminees
    private static String selectByIdAndDateSupFinEnchere ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?>arts_date_fin_encheres and utils_no_utilisateur=? ORDER BY arts_date_fin_encheres DESC";
       //Mes encheres Remportees
    private static String selectByIdAndEtatEnchere ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_prix_vente,encs_montant_enchere, cats_libelle, utils_pseudo FROM V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE arts_no_utilisateur=? and encs_etat_enchere='Vendu' ORDER BY arts_date_fin_encheres DESC";





//Mes encheres en Cours
    /**
     * retour les encheres en cours pour l'utilisateur
     * @param idUtilisateur
     * @param filtre
     * @param noCategorie
     * @return
     */
    public List<InfoArticle> selectByIdDateDerEnchere(int idUtilisateur, String filtre, int noCategorie){
        List<InfoArticle> listInfoArticle = null;
        InfoArticle infoArticle = new InfoArticle();
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%?%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_libelle=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            selectByIdDateDerEnchere = selectByIdDateDerEnchere + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(selectByIdDateDerEnchere);
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

                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres"));
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
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
        List<InfoArticle> listInfoArticle = null;
        InfoArticle infoArticle = new InfoArticle();
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%?%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_libelle=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            selectByIdAndEtatEnchere = selectByIdAndEtatEnchere + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(selectByIdAndEtatEnchere);
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

                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres"));
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
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
        List<InfoArticle> listInfoArticle = null;
        InfoArticle infoArticle = new InfoArticle();
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%?%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_libelle=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            selectByDateSupDebEnchereAndInfFinEnchere = selectByDateSupDebEnchereAndInfFinEnchere + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(selectByDateSupDebEnchereAndInfFinEnchere);
            ptt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ptt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

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

                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres"));
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

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
        List<InfoArticle> listInfoArticle = null;
        InfoArticle infoArticle = new InfoArticle();
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%?%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            selectByIdAndDateSupFinEnchere = selectByIdAndDateSupFinEnchere + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(selectByIdAndDateSupFinEnchere);
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

                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres"));
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
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
        List<InfoArticle> listInfoArticle = null;
        InfoArticle infoArticle = new InfoArticle();
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%?%'";
                filtreSaisi = true;

            }
            if(noCategorie!=0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            selectByIdDateInfDebEnchere = selectByIdDateInfDebEnchere + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(selectByIdDateInfDebEnchere);
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

                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres"));
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
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
        List<InfoArticle> listInfoArticle = null;
        InfoArticle infoArticle = new InfoArticle();
        String restrictionsComplementaire = "";
        Boolean filtreSaisi = false;
        Boolean categorieSelect = false;


        try (
                Connection cxn = ConnectionProvider.getConnection();
        ){

            if(filtre!=""){
                restrictionsComplementaire = restrictionsComplementaire + "AND arts_nom_article LIKE '%?%'";
                filtreSaisi = true;

            }
            if(noCategorie != 0){
                restrictionsComplementaire = restrictionsComplementaire + "AND cats_no_categorie=?";
                categorieSelect = true;
            }
            //Preparation de la requete
            selectByIdAndDatesEnchere = selectByIdAndDatesEnchere + restrictionsComplementaire;

            PreparedStatement ptt = cxn.prepareStatement(selectByIdAndDatesEnchere);
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

                infoArticle.setIdArticle(rs.getInt("arts_no_articles"));
                infoArticle.setPrixArticle(Math.max(rs.getInt("arts_prix_initial"),Math.max(rs.getInt( "arts_prix_vente"),
                        rs.getInt("encs_montant_enchere" ))));
                infoArticle.setNomArticle(rs.getString("arts_nom_article"));
                infoArticle.setFinEnchere(rs.getDate("arts_date_fin_encheres"));
                infoArticle.setVendeur(rs.getString("utils_pseudo"));

                listInfoArticle.add(infoArticle);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
        }

        return listInfoArticle;

    }




    /**
     * (ventes terminées consulté par l'utilisateur)
     * @param idUtilisateur
     * @return list's articles
     */
//    public List<Article> selectByIdDateEnchereEtatEnchere(int idUtilisateur){
//        List<Article> listArticle = new ArrayList<>();
//        Article article = new Article();
//        try (
//                Connection cxn = ConnectionProvider.getConnection();
//                PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_AND_DATE_FIN_ENCHERE);
//                ){
//            ptt.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
//            ptt.setInt(2,idUtilisateur);
//
//            ResultSet rs = ptt.executeQuery();
//
//            while(rs.next()){
//                article.setNoArticle(rs.getInt("no_article"));
//                article.setNomArticle(rs.getString("nom_article"));
//                article.getEnchere().setMontantEnchere(rs.getInt("montant_enchere"));
//                article.getCategorie().setNoCategorie(rs.getInt("no_categorie"));
//                article.getCategorie().setLibelle(rs.getString("libelle"));
//                article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
//                article.getUtilisateur().setNoUtilisateur(rs.getInt("no_utilisateur"));
//                article.getUtilisateur().setNom(rs.getString("nom"));
//                article.getEnchere().setNoAcquereur(rs.getInt("no_acquereur"));
//
//                listArticle.add(article);
//            }
//            rs.close();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            businessException.ajouterErreur(CodesResultatDAL.IMPORT_VENTES_TERMINEES);
//        }
//        return listArticle;
//    }

//    /**
//     * (Ventes non débutées)
//     *return the list's article where the selling haven't started
//     * @return list's article
//     */
//    public List<Article> selectByDateInfDebEnchere(){
//        List<Article> listArticle = null;
//
//        try (
//            Connection cxn = ConnectionProvider.getConnection();
//            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_DATE_INF_DEB_ENCHERE);
//            ){
//            ptt.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
//            ResultSet rs = ptt.executeQuery();
//
//            while(rs.next()) {
//                listArticle.add(new Article(
//                        rs.getInt("arts_no_article"),
//                        rs.getString("arts_nom_article"),
//                        rs.getInt("arts_prix_initial"),
//                        rs.getDate("arts_date_debut_enchere"),
//                        rs.getInt("encs_montant_enchere"),
//                        rs.getString("utils_nom"),
//                        rs.getInt("utils_no_utilisateur"),
//                        rs.getInt("cats_no_categorie"),
//                        rs.getString("cats_labelle")
//                ));
//            }
//            rs.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            businessException.ajouterErreur(CodesResultatDAL.IMPORT_VENTES_NON_DEBUTEES);
//        }
//    return listArticle;
//    }
//
//    public List<Article> selectByIdDateFinEnchere(int idUtilisateur, int idCategorie, String filtreSaisie) throws BusinessException {
//        List<Article> listArticles = null;
//        Categorie  cat = new Categorie();
//        Utilisateur util = new Utilisateur();
//        try (
//                Connection cxn = ConnectionProvider.getConnection();
//                )
//        {
//            //TOdo tester avec la valeur par défaut de la combobox
//            String restrictionComplementaire = " no_categorie=?";
//            if(idCategorie>0){
//                selectByIdUtilisateurAndDateFinEnchere=
//                        selectByIdUtilisateurAndDateFinEnchere+restrictionComplementaire;
//            }
//            if(filtreSaisie!= ""&&filtreSaisie!=null){
//
//            }
//        PreparedStatement pst = cxn.prepareStatement(selectByIdUtilisateurAndDateFinEnchere);
//        pst.setInt(1, idUtilisateur);
//        pst.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
//        ResultSet rs = pst.executeQuery();
//        while(rs.next()){
//            cat.setNoCategorie(rs.getInt("no_categorie"));
//            cat.setLibelle(rs.getString("libelle"));
//
//                    util.setNoUtilisateur(rs.getInt("no_utilisateur"));
//                    util.setPseudo(rs.getString("pseudo"));
//                    util.setNom(rs.getString("nom"));
//
//                    util.setPrenom(rs.getString("prenom"));
//                    util.setEmail(rs.getString("email"));
//                    util.setTelephone(rs.getString("telephone"));
//
//                    util.setRue(rs.getString("rue"));
//                    util.setCodePostal(rs.getString("code_postal"));
//                    util.setVille(rs.getString("ville"));
//
//                    util.setMotDePasse(rs.getString("mot_de_passe"));
//                    util.setCredit(rs.getInt("credit"));
//                    util.setAdmin(false);
//
//            listArticles.add(new Article(
//                    rs.getInt("no_article"),
//                    rs.getString("nom_article"),
//                    rs.getString("description"),
//                    rs.getDate("date_debut_enchere"),
//                    rs.getDate("date_fin_enchere"),
//                    rs.getInt("prix_initial"),
//                    rs.getInt("prix_vente"),
//                    rs.getString("etat_article"),
//                    "",
//                    util,
//                    cat,
//          0
//            ));
//        }
//        rs.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
//            throw businessException;
//        }
//
//        return listArticles;
//    }

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
                    utilisateurEnCours.setPseudo(rs.getString(14).toString());
                    utilisateurEnCours.setNom(rs.getString(15).toString());
                    utilisateurEnCours.setPrenom(rs.getString(16).toString());
                    utilisateurEnCours.setEmail(rs.getString(17).toString());
                    utilisateurEnCours.setTelephone(rs.getString(18).toString());
                    utilisateurEnCours.setRue(rs.getString(19).toString());
                    utilisateurEnCours.setCodePostal(rs.getString(20).toString());
                    utilisateurEnCours.setVille(rs.getString(21).toString());
                    utilisateurEnCours.setMotDePasse(rs.getString(22).toString());
                    utilisateurEnCours.setCredit(rs.getInt(23));
                    boolean admin = true;
                    if (rs.getByte(24) == 0){
                        admin = false;
                    }
                    utilisateurEnCours.setAdmin(admin);
                    listeUtilisateur.add(utilisateurEnCours);
                }
                Categorie categorieEnCours = new Categorie();
                if (rs.getInt(1) != categorieEnCours.getNoCategorie()){
                    categorieEnCours.setNoCategorie(rs.getInt(1));
                    categorieEnCours.setLibelle(rs.getString(2).toString());
                    listeCategorie.add(categorieEnCours);
                }
                Article articleEnCours = new Article();
                articleEnCours.setNoArticle(rs.getInt("no_article"));
                articleEnCours.setNomArticle(rs.getString("nom_article"));
                articleEnCours.setDescription(rs.getString("description"));
                articleEnCours.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
                articleEnCours.setDateFinEncheres(rs.getDate("date_fin_encheres"));
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
                    pstmt.setDate(3, (Date) article.getDateDebutEncheres());
                    pstmt.setDate(4, (Date) article.getDateFinEncheres());
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
            pstmt.setDate(3, (Date) article.getDateDebutEncheres());
            pstmt.setDate(4, (Date) article.getDateFinEncheres());
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
}
