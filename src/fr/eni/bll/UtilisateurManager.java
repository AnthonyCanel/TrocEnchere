package fr.eni.bll;

import fr.eni.BusinessException;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager {
    private DAO utilisateurDao;
    private static BusinessException businessException = new BusinessException();

    public Utilisateur getUtilisateurExiste(String pseudo, String pwd) throws BusinessException {
        Utilisateur util = null;

        this.utilisateurDao = DAOFactory.getUtilisateurDAO();
        util = utilisateurDao.getUtilisateur(pseudo, pwd);

        return util;
    }

    public UtilisateurManager() {
        utilisateurDao = DAOFactory.getUtilisateurDAO();
    }

    /**
     * Choisir un utilisateur en fonction de son identifiant
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public Utilisateur choisirUtilisateur(int id) throws BusinessException {
        return (Utilisateur) utilisateurDao.selectById(id);
    }

    /**
     * Ramène la liste de tous les utilisateurs
     *
     * @return
     */
    public List<Utilisateur> trouverTous() {
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        try {
            listeUtilisateurs = utilisateurDao.selectAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeUtilisateurs;
    }

    public Utilisateur ajouterUtilisateur(Utilisateur util) throws BusinessException {
        validerCoordonnees(util, businessException);
        //verif util existe déjà
        verifUtilisateur(util.getPseudo(),businessException);
        verifEmail(util.getEmail(),businessException);
        if(!businessException.hasErreurs()){
            utilisateurDao.insert(util);
        }
        else{
            throw businessException;
        }
        return util;
    }

    /**
     *
     * @param util
     * @throws BusinessException
     */
    public void miseAJourUtilisateur(Utilisateur util) throws BusinessException {
        validerCoordonnees(util, businessException);
        verifUtilisateur(util.getPseudo(),businessException);
        verifEmail(util.getEmail(),businessException);
        if(!businessException.hasErreurs()){
            utilisateurDao.update(util);
        }else{
            throw businessException;
        }
    }

    public void supprimer(int id) throws BusinessException {
        utilisateurDao.delete(id);
    }
    /**
     * Méthode pour vérifier que les champs sont remplis car obligatoires
     *
     * @param util
     * @param bE
     */
    private void validerCoordonnees(Utilisateur util, BusinessException bE) {
        if (util.getPseudo() == null || util.getPseudo().trim().equals("")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_PSEUDO_ERREUR);
        }
        if(util.getNom() == null || util.getNom().trim().equals("") || util.getNom().length()>30 || util.getNom().contains("[0-9]")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_NOM_ERREUR);
        }
        if(util.getPrenom() == null || util.getPrenom().trim().equals("") || util.getPrenom().length() >30 || util.getNom().contains("[0-9]")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_PRENOM_ERREUR);
        }
        if(util.getEmail() == null || util.getEmail().trim().equals("") || util.getEmail().length() > 75
            || !util.getEmail().contains("@")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_EMAIL_ERREUR);
        }
        if(util.getTelephone() == null || util.getTelephone().trim().equals("") || util.getTelephone().length() > 15
            || util.getTelephone().contains("[a-zA-Z]")) {
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_TEL_ERREUR);
        }
        if( util.getRue() == null || util.getRue().trim().equals("") || util.getRue().length() >30){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_RUE_ERREUR);
        }
        if(util.getCodePostal() == null ||  util.getCodePostal().trim().equals("") || util.getCodePostal().length() > 10 || util.getCodePostal().contains("[a-zA-Z]")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_CODEPOSTAL_ERREUR);
        }
        if(util.getVille() == null || util.getVille().trim().equals("") || util.getVille().length() >30 || util.getNom().contains("[0-9]")){
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_VILLE_ERREUR);
        }
        if(util.getMotDePasse() == null || util.getMotDePasse().trim().equals("") || util.getMotDePasse().length() >30) {
            bE.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_MOTDEPASSE_ERREUR);
        }
    }

    private void verifUtilisateur(String pseudo, BusinessException bE) throws BusinessException {
        boolean utilisateurExiste = utilisateurDao.verifUtilisateur(pseudo);
        if(utilisateurExiste){
            bE.ajouterErreur(CodesResultatBLL.UTILISATEURS_PSEUDO_ERREUR);
        }
    }

    private void verifEmail(String email, BusinessException bE) throws BusinessException {
        boolean emailExiste = utilisateurDao.verifMail(email);
        if(emailExiste){
            bE.ajouterErreur(CodesResultatBLL.UTILISATEURS_EMAIL_ERREUR);
        }
    }
}
