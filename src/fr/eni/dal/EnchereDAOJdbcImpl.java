package fr.eni.dal;

import fr.eni.BusinessException;

import fr.eni.bll.CodesResultatBLL;
import fr.eni.bo.Enchere;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class EnchereDAOJdbcImpl implements DAO<Enchere> {
    private static final BusinessException businessException = new BusinessException();

    private static final String SELECTALL = "SELECT * FROM ENCHERES";
    private static final String SELECT_BY_ACQ_ET_ART_VENDU ="SELECT * FROM ENCHERES WHERE (no_acquereur=? and etat_enchere='Vendu')";
    private static final String SELECT_BY_ID_UTIL_AND_ID_ART = "SELECT date_enchere, montant_enchere, etat_enchere, no_acquereur FROM ENCHERES WHERE no_article = ? AND no_utilisateur = ?";


    @Override
    public List selectAll() throws BusinessException {
        List<Enchere> listEnchere = new ArrayList<>();
        Enchere enchere = new Enchere();
        try (
                Connection cnx = ConnectionProvider.getConnection();
                Statement stt = cnx.createStatement()
        ) {

            ResultSet rs = stt.executeQuery(SELECTALL);
            while (rs.next()) {
                enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
                enchere.setNoArticle(rs.getInt("no_article"));
                enchere.setDateEnchere( rs.getTimestamp("date_enchere"));
                enchere.setMontantEnchere( rs.getInt("montant_enchere"));
                enchere.setEtatEnchere(rs.getString("etat_enchere"));
                enchere.setNoAcquereur( rs.getInt("no_acquereur"));

                listEnchere.add(enchere);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.IMPORT_DAL_ENCHERE);
            throw new BusinessException();

        }
        return listEnchere;
    }

    @Override
    public Enchere selectById(int id) {

        return null;
    }

    @Override
    public void insert(Enchere objet) {

    }

    @Override
    public void update(Enchere objet) {

    }

    /**
     *
     * @param acquereur
     * @return List of encheres that user win. check no_user and etat_enchere='Vendu'
     * @throws
     */
    public List<Enchere> selectByAcqEtArtVendu(int acquereur) throws BusinessException {
        List<Enchere> listEnchere = new ArrayList<>();

        try (
                Connection cnx = ConnectionProvider.getConnection()

                )
        {
            PreparedStatement pst = cnx.prepareStatement(SELECT_BY_ACQ_ET_ART_VENDU);
            pst.setInt(1,acquereur );
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.IMPORT_DAL_ENCHERE_GAGNE);
            throw new BusinessException();

        }
        return listEnchere;
    }

    @Override
    public void delete(int id) {

    }


}
