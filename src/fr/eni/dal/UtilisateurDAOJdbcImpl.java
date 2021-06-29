package fr.eni.dal;

import fr.eni.BusinessException;
import fr.eni.bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur>{
    private static BusinessException businessException = new BusinessException();
    private String SELECTALL="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
    private String SELECTBYID = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = ?";
    private String UPDATE_UTILISATEURS= "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, administrateur = ? WHERE no_utilisateur = ?";
    private String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";
    private String INSERT= "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private String UPDATE_DELETE= "UPDATE UTILISATEURS SET pseudo = 'compte supprimé', nom = 'compte supprimé', prenom = 'compte supprimé', email = 'compte supprimé', telephone = 'compte supprimé', rue = 'compte supprimé',code_postal = 'compte supprimé', mot_de_passe='compte supprimé', credit = 0 WHERE no_utilisateur = ?";
    private String SELECTBYMAIL= "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email = ?";
    private String SELECTBYPSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ?";
    private String SELECTBYPSEUDOANDPWD = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, " +
            "code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ? and  mot_de_passe=?";

    @Override
    public List<Utilisateur> selectAll() throws BusinessException{
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
                //Création de l'objet
                Utilisateur util = new Utilisateur(idUti,pseudoUti,nomUti, prenomUti,emailUti, telUti, rueUti, codePostalUti, villeUti, pwdUti, creditUti, adminUti);
                listUtilisateurs.add(util);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;

        }
        return listUtilisateurs;
    }

    @Override
    public Utilisateur selectById(int id) throws BusinessException {
        Utilisateur util = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECTBYID)) {
            pstt.setInt(1,id);
            ResultSet rs = pstt.executeQuery();
            if(rs.next()){
                int idUti = id;
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
                Boolean adminUti = rs.getBoolean("administrateur");
                util = new Utilisateur(idUti, pseudoUti, nomUti, prenomUti, emailUti, telUti, rueUti, codePostalUti, villeUti, pwdUti, creditUti);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return util;
    }

    /**
     * Insertion d'un utilisateur dans la base de données
     * Vérification que l'objet passé en paramètre n'est pas null
     * @param util
     */
    @Override
    public void insert(Utilisateur util) throws BusinessException {
        if(util == null){
            businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstt.setString(1, util.getPseudo());
            pstt.setString(2, util.getNom());
            pstt.setString(3, util.getPrenom());
            pstt.setString(4, util.getEmail());
            //Gestion numéro de téléphone si null
            if(util.getTelephone() != null){
                pstt.setString(5, util.getTelephone());
            }
            else{
                pstt.setNull(5, Types.CHAR);
            }
            pstt.setString(6, util.getRue());
            pstt.setString(7,util.getCodePostal());
            pstt.setString(8, util.getVille());
            pstt.setString(9, util.getMotDePasse());
            pstt.setInt(10, 0);
            pstt.setBoolean(11, util.isAdmin());
            pstt.executeUpdate();

            ResultSet rs = pstt.getGeneratedKeys();
            if(rs.next()){
                util.setNoUtilisateur(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_NULL);
            throw businessException;
        }
    }

    @Override
    public void update(Utilisateur util) throws BusinessException {
        if(util == null){
            businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
            throw businessException;
        }
        try (Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstt = cnx.prepareStatement(UPDATE_UTILISATEURS)) {
            pstt.setString(1, util.getPseudo());
            pstt.setString(2, util.getNom());
            pstt.setString(3, util.getPrenom());
            pstt.setString(4, util.getEmail());
            if(util.getTelephone()!=null){
                pstt.setString(5, util.getTelephone());
            }
            else{
                pstt.setNull(5, Types.CHAR);
            }
            pstt.setString(6, util.getRue());
            pstt.setString(7,util.getCodePostal());
            pstt.setString(8, util.getVille());
            pstt.setString(9, util.getMotDePasse());
            pstt.setByte(10, (byte) 0);
            pstt.setInt(11, util.getNoUtilisateur());
            pstt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
            throw businessException;
        }
    }

    /**
     * Si un utilisateur est supprimé on anonymise son compte afin de garder l'historique
     * @param id
     * @throws BusinessException
     */
    @Override
    public void delete(int id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstt = cnx.prepareStatement(UPDATE_DELETE)) {
            pstt.setInt(1, id);
            pstt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
            throw businessException;
        }
    }

    /**
     * Vérification de l'unicité du courriel de l'utilisateur
     * @param email
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean verifMail(String email) throws BusinessException {
        boolean unique = false;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECTBYMAIL)) {
            pstt.setString(1,email);
            ResultSet rs = pstt.executeQuery();
            if(rs.next()){
                unique = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.EMAIL_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return unique;
    }

    /**
     * Vérification de l'unicité du pseudo de l'utilisateur
     * @param pseudo
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean verifUtilisateur(String pseudo) throws BusinessException {
        boolean unique = false;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECTBYPSEUDO)) {
            pstt.setString(1,pseudo);
            ResultSet rs = pstt.executeQuery();
            if(rs.next()){
                //todo ???
                unique = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.PSEUDO_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return unique;
    }

    public Utilisateur getUtilisateur(String pseudo, String pwd) throws BusinessException {
        Utilisateur util = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECTBYPSEUDOANDPWD)) {
            pstt.setString(1,pseudo);
            pstt.setString(2,pwd);
            ResultSet rs = pstt.executeQuery();
            if(rs.next()){
                int idUti = rs.getInt("no_utilisateur");
                String pseudoUti = rs.getString("pseudo");
                String nomUti = rs.getString("nom");
                String prenomUti = rs.getString("prenom");
                String emailUti = rs.getString("email");
                String telUti = rs.getString("telephone");
                String rueUti = rs.getString("rue");
                String codePostalUti = rs.getString("code_postal");
                String villeUti = rs.getString("ville");
                String pwdUti = "";
                int creditUti = rs.getInt("credit");
                Boolean adminUti = rs.getBoolean("administrateur");
                util = new Utilisateur(idUti, pseudoUti, nomUti, prenomUti, emailUti, telUti, rueUti, codePostalUti, villeUti, pwdUti, creditUti);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
            throw businessException;
        }
        return util;
    }
}
