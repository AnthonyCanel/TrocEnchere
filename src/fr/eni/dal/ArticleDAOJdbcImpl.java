package fr.eni.dal;

import fr.eni.bll.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements DAO<Article>{

    private static final String SELECT_ALL = "SELECT TOP(6) A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues,\n" +
            "C.no_categorie, C.libelle, U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur\n" +
            "FROM V_ARTICLES_CATEGORIES_UTILISATEURS";
//    private static final String SELECT_BY_ID = "eieeffezq";
    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET no_article = ?, nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, etat_article = ?, photo = ?, no_utilisateur = ?, no_categorie = ?, vues = ? where id=?";
    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id=?";

    @Override
    public List<Article> selectAll() throws BusinessException{
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
                    utilisateurEnCours.setNoUtilisateur(rs.getInt(1));
                    utilisateurEnCours.setPseudo(rs.getString(2).toString());
                    utilisateurEnCours.setPrenom(rs.getString(3).toString());
                    utilisateurEnCours.setNom(rs.getString(4).toString());
                    utilisateurEnCours.setEmail(rs.getString(5).toString());
                    utilisateurEnCours.setTelephone(rs.getString(6).toString());
                    utilisateurEnCours.setRue(rs.getString(7).toString());
                    utilisateurEnCours.setCodePostal(rs.getString(8).toString());
                    utilisateurEnCours.setVille(rs.getString(9).toString());
                    utilisateurEnCours.setMotDePasse(rs.getString(10).toString());
                    utilisateurEnCours.setCredit(rs.getInt(11));
                    utilisateurEnCours.setAdmin(rs.getBoolean(12));
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
//          e.printStackTrace();
//          BusinessException businessException = new BusinessException();
//          businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
//          throw businessException;
        }
        return listeArticle;
    }

    @Override
    public Article selectById(int id) {
        return null;
    }

    @Override
    public void insert(Article article) throws BusinessException {
//     if(Article == null){
//         BusinessException businessException = new BusinessException();
//         businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
//         throw businessException;
//     }

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
//    e.printStackTrace();
//    BusinessException businessException = new BusinessException();
//    businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
//    throw businessException;
    }
    }

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
//         e.printStackTrace();
//         BusinessException businessException = new BusinessException();
//         businessException.ajouterErreur(CodesResultatDAL.DECOCHE_ARTICLE_ERREUR);
//         throw businessException;
        }
    }

    @Override
    public void delete(int id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
//         e.printStackTrace();
//         BusinessException businessException = new BusinessException();
//         businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
//         throw businessException;
     }
    }
}
