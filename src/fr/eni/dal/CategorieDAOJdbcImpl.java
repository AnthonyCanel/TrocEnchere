package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {

    private static final String SELECT_ALL = " SELECT no_categorie, libelle FROM TROC_ENCHERES";
    private static final String INSERT_CATEGORIES = "INSERT into CATEGORIES(no_categorie, libelle) VALUES(?,?)";
    private static final String DELETE_CATEGORIES = "delete from ARTICLES where id=?";
    private static final String UPDATE_CATEGORIES = "UPDATE ARTICLES SET libelle = ?, where id=?";

    /**
     * Récupère les catégories et leur numéros
     * @return
     * @throws BusinessException
     */
    @Override
    public List<Categorie> selectAll() throws BusinessException {
        List<Categorie> listeCategorie = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            Statement pstmt = cnx.createStatement();
            ResultSet rs = pstmt.executeQuery(SELECT_ALL);
            while (rs.next()) {
                listeCategorie.add(new Categorie(rs.getInt("no_categorie"),
                        rs.getString("libelle").toString()));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
    }

        @Override
    public Categorie selectById(int id) throws BusinessException {
        return null;
    }

    /**
     * Insert une catégorie
     * @param categorie
     * @throws BusinessException
     */
    @Override
    public void insert(Categorie categorie) throws BusinessException {
        if (categorie == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt;
                ResultSet rs;
                if (categorie.getNoCategorie() == 0){
                    pstmt = cnx.prepareStatement(INSERT_CATEGORIES, PreparedStatement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, categorie.getLibelle());
                    pstmt.executeUpdate();
                    rs = pstmt.getGeneratedKeys();
                    if(rs.next()){
                        categorie.setNoCategorie(rs.getInt(1));
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
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
            throw businessException;
        }
    }

    /**
     * Change le libelle de la categorie sélectionnée
     * @param categorie
     * @throws BusinessException
     */
    @Override
    public void update(Categorie categorie) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CATEGORIES);
            pstmt.setString(1, categorie.getLibelle());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
            throw businessException;
        }
    }

    /**
     * Supprime une catégorie
     * @param id
     * @throws BusinessException
     */
    @Override
    public void delete(int id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIES);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
            throw businessException;
        }
    }
}
