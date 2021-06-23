package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Retrait;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetraitDAOJdbcImpl implements DAO<Retrait>{
    private static DAO articleDao = new ArticleDAOJdbcImpl();
    private static BusinessException businessException = new BusinessException();
    private static final String INSERT = "INSERT INTO RETRAITS (rue, code_postal, ville) values (?,?,?)";
    private static final String SELECTBYID = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = ?";
    private static final String SELECTALL = "SELECT no_article, rue, code_postal, ville FROM RETRAITS";
    private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article=?";
    private static final String DELETE = "DELETE RETRAITS WHERE no_article = ?";

    @Override
    public List<Retrait> selectAll() throws  DALException {
        List<Retrait> listeRetraits = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection();
             Statement stt = cnx.createStatement()) {
            ResultSet rs = stt.executeQuery(SELECTALL);
            while(rs.next()){
                Retrait retrait = new Retrait();
                retrait.setId(rs.getInt("no_article"));
                retrait.setRue(rs.getString("rue"));
                retrait.setCodePostal(rs.getString("code_postal"));
                retrait.setVille(rs.getString("ville"));
                listeRetraits.add(retrait);
            }
            rs.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
        }
        return listeRetraits;
    }

    @Override
    public Retrait selectById(int id) throws BusinessException {
        Retrait retrait = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECTBYID)) {
            pstt.setInt(1,id);
            ResultSet rs = pstt.executeQuery();
            if(rs.next()){
                retrait = new Retrait();
                retrait.setId(rs.getInt("no_article"));
                retrait.setRue(rs.getString("rue"));
                retrait.setCodePostal(rs.getString("code_postal"));
                retrait.setVille(rs.getString("ville"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
            throw businessException;
        }
        return null;
    }

    @Override
    public void insert(Retrait retrait) throws BusinessException {
        if(retrait == null){
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
            pstt.setString(1, retrait.getRue());
            pstt.setString(2, retrait.getCodePostal());
            pstt.setString(3, retrait.getVille());
            pstt.executeUpdate();
            ResultSet rs = pstt.getGeneratedKeys();
            if(rs.next()){
                retrait.setId(rs.getInt(1)); //TODO à vérifier
            }
            rs.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
            throw businessException;
        }
    }

    @Override
    public void update(Retrait retrait) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstt = cnx.prepareStatement(UPDATE)) {
            pstt.setString(1, retrait.getRue());
            pstt.setString(2, retrait.getCodePostal());
            pstt.setString(3, retrait.getVille());
            pstt.setInt(4, retrait.getId());
            pstt.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
            throw businessException;
        }

    }

    @Override
    public void delete(int id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstt = cnx.prepareStatement(DELETE)) {
            pstt.setInt(1,id);
            Retrait retrait = selectById(id);
            List<Article> listeArticles = articleDao.getByRetrait(retrait);
            for(Article article: listeArticles){
                Article.setLieuRetrait = null;
            }
            pstt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
            throw businessException;
        }

    }
}
