package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import sun.invoke.empty.Empty;

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

    private static String selectByIdUtilisateurAndDateFinEnchere ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente,etat_article, photo, vues, no_categorie, libelle, no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM V_ARTICLES_CATEGORIES_UTILISATEURS where no_utilisateur=? and date_fin_encheres>?";

    private static final String SELECT_BY_DATE_INF_DEB_ENCHERE ="SELECT arts_no_articles, arts_nom_article, arts_prix_initial, arts_date_debut_encheres, encs_montant_enchere,utils_nom, utils_no_utilisateur, cats_no_categorie, cats_libelle from V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES WHERE ?< arts_date_debut_encheres ORDER BY arts_date_debut_encheres DESC";

    private static final String SELECT_BY_ID_AND_DATE_FIN_ENCHERE="SELECT no_utilisateur, nom, pseudo, nom_article, montant_enchere, date_fin_encheres FROM V_UTILISATEURS_ENCHERES_ARTICLES_RETRAITS_CATEGORIES WHERE ?>date_fin_encheres and no_utilisateur=? and etat_enchere='Vendu' ORDER BY date_fin_encheres DESC";

    /**
     * (ventes terminées consulté par l'utilisateur)
     * @param idUtilisateur
     * @return list's articles
     */
    public List<Article> selectByIdDateEnchereEtatEnchere(int idUtilisateur){
        List<Article> listArticle = new ArrayList<>();
        Article article = new Article();
        try (
                Connection cxn = ConnectionProvider.getConnection();
                PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_ID_AND_DATE_FIN_ENCHERE)
        ){
            ptt.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
            ptt.setInt(2,idUtilisateur);

            ResultSet rs = ptt.executeQuery();

            while(rs.next()){
                article.setNoArticle(rs.getInt("no_article"));
                article.setNomArticle(rs.getString("nom_article"));
                article.getEnchere().setMontantEnchere(rs.getInt("montant_enchere"));
                article.getCategorie().setNoCategorie(rs.getInt("no_categorie"));
                article.getCategorie().setLibelle(rs.getString("libelle"));
                article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                article.getUtilisateur().setNoUtilisateur(rs.getInt("no_utilisateur"));
                article.getUtilisateur().setNom(rs.getString("nom"));
                article.getEnchere().setNoAcquereur(rs.getInt("no_acquereur"));

                listArticle.add(article);
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.IMPORT_VENTES_TERMINEES);
        }
        return listArticle;
    }

    /**
     * (Ventes non débutées)
     *return the list's article where the selling haven't started
     * @return list's article
     */
    public List<Article> selectByDateInfDebEnchere(){
        List<Article> listArticle = null;

        try (
            Connection cxn = ConnectionProvider.getConnection();
            PreparedStatement ptt = cxn.prepareStatement(SELECT_BY_DATE_INF_DEB_ENCHERE)
        ){
            ptt.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
            ResultSet rs = ptt.executeQuery();

            while(rs.next()) {
                listArticle.add(new Article(
                        rs.getInt("arts_no_article"),
                        rs.getString("arts_nom_article"),
                        rs.getInt("arts_prix_initial"),
                        rs.getDate("arts_date_debut_enchere").toLocalDate(),
                        rs.getInt("encs_montant_enchere"),
                        rs.getString("utils_nom"),
                        rs.getInt("utils_no_utilisateur"),
                        rs.getInt("cats_no_categorie"),
                        rs.getString("cats_labelle")
                ));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.IMPORT_VENTES_NON_DEBUTEES);
        }
    return listArticle;
    }

    public List<Article> selectByIdDateFinEnchere(int idUtilisateur, int idCategorie, String filtreSaisie) throws BusinessException {
        List<Article> listArticles = null;
        Categorie  cat = new Categorie();
        Utilisateur util = new Utilisateur();
        try (
                Connection cxn = ConnectionProvider.getConnection()
        )
        {
            //TOdo tester avec la valeur par défaut de la combobox
            String restrictionComplementaire = " no_categorie=?";
            if(idCategorie>0){
                selectByIdUtilisateurAndDateFinEnchere=
                        selectByIdUtilisateurAndDateFinEnchere+restrictionComplementaire;
            }
            if(filtreSaisie!= ""&&filtreSaisie!=null){

            }
        PreparedStatement pst = cxn.prepareStatement(selectByIdUtilisateurAndDateFinEnchere);
        pst.setInt(1, idUtilisateur);
        pst.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            cat.setNoCategorie(rs.getInt("no_categorie"));
            cat.setLibelle(rs.getString("libelle"));

                    util.setNoUtilisateur(rs.getInt("no_utilisateur"));
                    util.setPseudo(rs.getString("pseudo"));
                    util.setNom(rs.getString("nom"));

                    util.setPrenom(rs.getString("prenom"));
                    util.setEmail(rs.getString("email"));
                    util.setTelephone(rs.getString("telephone"));

                    util.setRue(rs.getString("rue"));
                    util.setCodePostal(rs.getString("code_postal"));
                    util.setVille(rs.getString("ville"));

                    util.setMotDePasse(rs.getString("mot_de_passe"));
                    util.setCredit(rs.getInt("credit"));
                    util.setAdmin(false);

            listArticles.add(new Article(
                    rs.getInt("no_article"),
                    rs.getString("nom_article"),
                    rs.getString("description"),
                    rs.getDate("date_debut_enchere").toLocalDate(),
                    rs.getDate("date_fin_enchere").toLocalDate(),
                    rs.getInt("prix_initial"),
                    rs.getInt("prix_vente"),
                    rs.getString("etat_article"),
                    "",
                    util,
                    cat,
          0
            ));
        }
        rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
            throw businessException;
        }

        return listArticles;
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
}
