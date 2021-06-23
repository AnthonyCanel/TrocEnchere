package fr.eni.bll;


import fr.eni.bo.Enchere;
import fr.eni.dal.DALException;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EnchereDAOJdbcImpl;

import java.util.ArrayList;
import java.util.List;

public class EnchereManager {
    private DAO generiqueDao;

    /**
     * return List of enchere 's table
     * @throws BusinessException
     */
    public EnchereManager() throws BusinessException {

        this.generiqueDao = DAOFactory.getEnchereDAO();
    }

    public List<Enchere> importEncheres() throws BusinessException {
        List<Enchere> listEnchere;

        try {
            listEnchere = generiqueDao.selectAll();
        } catch (DALException | fr.eni.BusinessException e) {
            e.printStackTrace();
            throw new BusinessException("Import de la table Enchere impossible dans la businessException");
        }

        return listEnchere;

    }
    //TODO finaliser la méthode ci dessous
    public List<Enchere> encheresGagnées(int id_acquereur) throws BusinessException {
        List<Enchere> listEnchere;

        EnchereDAOJdbcImpl e = new EnchereDAOJdbcImpl();
        try {
            listEnchere = e.selectByAcqEtArtVendu(id_acquereur);
        } catch (DALException dalException) {
            dalException.printStackTrace();
            throw new BusinessException("Import des articles gagnés par l'acquereur de la table enchere est impossible");
        }
        return listEnchere;
    }
}
