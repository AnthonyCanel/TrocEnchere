package fr.eni.dal;

import fr.eni.bll.BusinessException;
import fr.eni.bo.Enchere;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EnchereDAOJdbcImpl implements DAO<Enchere> {

    private static final String SELECTALL = "SELECT * FROM ENCHERES";
    private static final String SELECT_BY_ACQ_ET_ART_VENDU ="SELECT * FROM ENCHERES WHERE (no_acquereur=? and etat_enchere='Vendu')";

    @Override
    public List selectAll() throws DALException{
        List<Enchere> listEnchere = new ArrayList<>();

        try (
                Connection cnx = ConnectionProvider.getConnection();
                Statement stt = cnx.createStatement()
        ) {

            ResultSet rs = stt.executeQuery(SELECTALL);
            while (rs.next()) {
                listEnchere.add(new Enchere(rs.getInt("no_utilisateur"),
                        rs.getInt("no_article"),
                        rs.getTimestamp("date_enchere"),
                        rs.getInt("montant_enchere"),
                        rs.getString("etat_enchere"),
                        rs.getInt("no_vendeur")));
            }
            rs.close();

//
//convertion datetime to date ou time
//            LocalDateTime localDateTime = listEnchere.get(0).getDateEnchere().toLocalDateTime();
//            System.out.println("coucou"+
//                    localDateTime.toLocalDate()+"\n"+
//                            localDateTime.toLocalTime()
//            );

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("import de la base enchere impossible");
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
     * @throws DALException
     */
    public List<Enchere> selectByAcqEtArtVendu(int acquereur) throws DALException {
        List<Enchere> listEnchere = new ArrayList<>();

        try (
                Connection cnx = ConnectionProvider.getConnection()

                )
        {
            PreparedStatement pst = cnx.prepareStatement(SELECT_BY_ACQ_ET_ART_VENDU);
            pst.setInt(1,acquereur );
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("Import des encheres gagnées par utilisateur impossible");
        }

        return listEnchere;
    }











    @Override
    public void delete(int id) {

    }


}
