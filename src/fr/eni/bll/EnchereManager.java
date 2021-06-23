package fr.eni.bll;


import fr.eni.BusinessException;
import fr.eni.bo.Enchere;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EnchereDAOJdbcImpl;

import java.util.List;

public class EnchereManager {
    private DAO generiqueDao;
    private static BusinessException businessException = new BusinessException();

    /**
     * return List of enchere 's table
     * @throws BusinessException
     */
    public EnchereManager() throws BusinessException {

        this.generiqueDao = DAOFactory.getEnchereDAO();
    }

    public List<Enchere> importEncheres() throws BusinessException {
        List<Enchere> listEnchere=null;

        try {
            listEnchere = generiqueDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatBLL.IMPORT_BLL_ENCHERE);

        }

        return listEnchere;

    }
    //TODO finaliser la méthode ci dessous
    public List<Enchere> encheresGagnées(int id_acquereur) throws BusinessException {
        List<Enchere> listEnchere = null;

        EnchereDAOJdbcImpl eDaoJdbcImpl = new EnchereDAOJdbcImpl();
        try {
            listEnchere = eDaoJdbcImpl.selectByAcqEtArtVendu(id_acquereur);
        } catch (Exception e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatBLL.IMPORT_BLL_ENCHERES_GAGNEES);
        }
        return listEnchere;
    }
}
