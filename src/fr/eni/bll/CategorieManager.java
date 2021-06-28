package fr.eni.bll;

import fr.eni.BusinessException;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDAOJdbcImpl;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class CategorieManager {
    private DAO categorieDAO;

    public CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public List<Categorie> AfficherCategories(){
        List<Categorie> listeCategorie = new ArrayList<>();
        try {
            listeCategorie = categorieDAO.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return listeCategorie;
    }
}
