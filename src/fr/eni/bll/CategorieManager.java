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
    private DAO<Categorie> categorieDAO;
    private BusinessException businessException = new BusinessException();

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

    public Categorie ChoisirCategorie(int id) throws BusinessException {
        Categorie cat = null;
        //Vérification que l'identifiant n'est pas vide
        if(id!=0){
            cat = categorieDAO.selectById(id);
        }else{
            businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIES_ID_ERREUR);
        }
        return cat;
    }
}
