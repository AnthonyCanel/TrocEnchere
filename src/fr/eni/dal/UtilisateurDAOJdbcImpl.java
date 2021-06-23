package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOJdbcImpl implements DAO{
    private String SELECTALL="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
    private String SELECTBYID = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = ?";
    private String CONNEXION = "SELECT nom, prenom WHERE pseudo=? AND mot_de_passe = ?";
    private String UPDATE_UTILISATEURS= "UPDATE UTILISATEURS SET nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";
    private String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";
    private String INSERT= "INSERT INTO UTILISATEURS() VALUES()";
    private String UPDATE_DELETE= "UPDATE UTILISATEURS SET pseudo = 'compte supprimé', nom = 'compte supprimé', prenom = 'compte supprimé', email = 'compte supprimé', telephone = 'compte supprimé', rue = 'compte supprimé',code_postal = 'compte supprimé', mot_de_passe='compte supprimé', credit = 0 WHERE no_utilisateur = ?";

    @Override
    public List<Utilisateur> selectAll() throws BusinessException, DALException{
        List<Utilisateur> listUtilisateurs = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection();
             Statement stt = cnx.createStatement()) {
            ResultSet rs = stt.executeQuery(SELECTALL);
            while(rs.next()){
                int idUti = rs.getInt("no_utilisateur");
                String pseudoUti = rs.getString("pseudo");
                String nomUti = rs.getString("nom");
                String prenomUti = rs.getString("prenom");
                String emailUti = rs.getString("email");
                String telUti = rs.getString("telephone");
                String rueUti = rs.getString("rue");
                String codePostalUti = rs.getString("code_postal");
                String villeUti = rs.getString("ville");
                String pwdUti = rs.getString("mot_de_passe");
                int creditUti = rs.getInt("credit");
                boolean adminUti = true;
                if(rs.getByte("administrateur")==0){
                    adminUti = false;
                }

//                Boolean adminUti = rs.getByte("adminisitrateur");
                //Création de l'objet
                Utilisateur util = new Utilisateur(idUti,pseudoUti,nomUti, prenomUti,emailUti, telUti, rueUti, codePostalUti, villeUti, pwdUti, creditUti, adminUti);
                listUtilisateurs.add(util);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();

        }
        return listUtilisateurs;
    }

    @Override
    public Utilisateur selectById(int id) throws DALException {
        Utilisateur util = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECTBYID)) {
            pstt.setInt(1,id);
            ResultSet rs = pstt.executeQuery();
            if(rs.next()){
                String pseudoUti = rs.getString("pseudo");
                String nomUti = rs.getString("nom");
                String prenomUti = rs.getString("prenom");
                String emailUti = rs.getString("email");
                String telUti = rs.getString("telephone");
                String rueUti = rs.getString("rue");
                String codePostalUti = rs.getString("code_postal");
                String villeUti = rs.getString("ville");
                String pwdUti = rs.getString("mot_de_passe");
                int creditUti = rs.getInt("credit");
                Boolean adminUti = rs.getBoolean("adminisitrateur");
                util = new Utilisateur(pseudoUti, nomUti, prenomUti, emailUti, telUti, rueUti, codePostalUti, villeUti, pwdUti, creditUti);
            }
            rs.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DALException("Une erreur est survenue lors de la récupération de l'utilisateur " + id);
        }
        return util;
    }

    @Override
    public void insert(Object data) {

    }

    @Override
    public void update(Object data) {

    }

    @Override
    public void delete(int id) {

    }
}
