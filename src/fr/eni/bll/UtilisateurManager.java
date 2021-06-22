package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.Dao;

public class UtilisateurManager {
    private DAO utilisateurDao;

    public UtilisateurManager() {
        utilisateurDao = DAOFactory.getUtilisateurDAO();
    }

    public Utilisateur choisirUtilisateur(int id) throws BusinessException {
        try {
            return utilisateurDao.selectById(id);
        }catch (DALException e){
            throw new BusinessException("Problème d'accès à la base : " + e.getMessage());
        }
    }
}
