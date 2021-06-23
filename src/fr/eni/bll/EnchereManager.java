package fr.eni.bll;


import fr.eni.bo.Enchere;
import fr.eni.dal.DALException;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class EnchereManager {
    private DAO generiqueDao;

    public EnchereManager() throws BusinessException {

        try {
            this.generiqueDao = DAOFactory.getEnchereDAO();
        } catch (DALException e) {
            e.printStackTrace();
            throw new BusinessException("création de l'enchere Manager impossible");
        }
    }

    public List<Enchere> importEncheres() throws BusinessException {
        List<Enchere> listEnchere = new ArrayList<>();

        try {
            listEnchere = generiqueDao.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
            throw new BusinessException("Import de la table Enchere impossible dans la businessException");
        }
//        A supprimer a la fin
//        for (Enchere e : listEnchere
//        ) {
//            System.out.println(
//                    e.getDateEnchere() + "\n" +
//                    e.getEtatEnchere() + "\n" +
//                    e.getMontantEnchere() + "\n" +
//                    e.getNoArticle() + "\n" +
//                    e.getDateEnchere());
//        }

        return listEnchere;

    }


}
