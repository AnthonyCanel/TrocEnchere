package fr.eni.dal;

import fr.eni.bll.BusinessException;
import fr.eni.bo.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements Dao{

    private static final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, photo, no_utilisateur, no_categorie, vues FROM ARTICLES";
    private static final String SELECT_BY_ID = "eieeffezq";
    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET no_article = ?, nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, etat_article = ?, photo = ?, no_utilisateur = ?, no_categorie = ?, vues = ? where id=?";
    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id=?";

    @Override
    public List<Article> selectAll() throws BusinessException{
        List<Article> listeArticle = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            Statement pstmt = cnx.createStatement();
            ResultSet rs = pstmt.executeQuery(SELECT_ALL);
            while (rs.next()) {
                listeArticle.add(new Article(rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres"),
                        rs.getDate("date_fin_encheres"),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        rs.getString("etat_article"),
                        rs.getString("photo"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_categorie")));
            }
        } catch (Exception e) {
//           e.printStackTrace();
//           BusinessException businessException = new BusinessException();
//           businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
//           throw businessException;
        }
        return listeArticle;
    }

    @Override
    public Object selectById(Object id) {
        return null;
    }

    @Override
    public void insert(Object data) {
    }

    @Override
    public void update(Object data) {

    }

    @Override
    public void delete(int id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
//          e.printStackTrace();
//          BusinessException businessException = new BusinessException();
//          businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
//          throw businessException;
        }
    }
}
