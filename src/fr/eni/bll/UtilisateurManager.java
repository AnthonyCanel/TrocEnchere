package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager {
    private DAO utilisateurDao;

    public UtilisateurManager() {
        utilisateurDao = DAOFactory.getUtilisateurDAO();
    }

    public Utilisateur choisirUtilisateur(int id) throws BusinessException {
        try {
            return (Utilisateur) utilisateurDao.selectById(id);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BusinessException("Problème d'accès à la base : " + e.getMessage());
        }
    }
    public List<Utilisateur> trouverTous(){
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        try {
            listeUtilisateurs = utilisateurDao.selectAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeUtilisateurs;
    }

}
