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
        if(!businessException.hasErreurs()){
            utilisateurDao.insert(util);
        }
        else{
            throw businessException;
        }
        return util;
    }


    /**
     * Méthode pour vérifier que les champs sont remplis car obligatoires
     *
     * @param util
     * @param bE
     */
    private void validerCoordonnees(Utilisateur util, BusinessException bE) {
        if (util.getPseudo() == null || util.getNom() == null || util.getPrenom() == null || util.getEmail() == null || util.getTelephone() == null ||
                util.getRue() == null || util.getCodePostal() == null || util.getVille() == null || util.getMotDePasse() == null ||
                util.getPseudo().trim().equals("") || util.getNom().trim().equals("") || util.getPrenom().trim().equals("") || util.getEmail().trim().equals("") ||
                util.getTelephone().trim().equals("") || util.getRue().trim().equals("") || util.getCodePostal().trim().equals("") ||
                util.getVille().trim().equals("") || util.getMotDePasse().trim().equals("")) {
            businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ADRESSE_ERREUR);
        }
    }
}
