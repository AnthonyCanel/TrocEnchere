package fr.eni.bll;

import fr.eni.bo.Retrait;
import fr.eni.dal.DALException;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;
import fr.eni.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class RetraitManager {
    private DAO retraitDao;
    private static BusinessException businessException = new BusinessException();

    public RetraitManager() {
        retraitDao = DAOFactory.getRetraitDAO();
    }

    /**
     * Ajouter le lieu dans la table Retrait avec vérification de celui-ci
     * @param retrait
     * @return
     * @throws BusinessException
     */
    public Retrait ajouterLieu(Retrait retrait) throws BusinessException {
        validerAdresse(retrait,  businessException);
        if (!businessException.hasErreurs()) {
            retraitDao.insert(retrait);
        }
        else{
            throw businessException;
        }
        return retrait;
    }

    /**
     * Recherche par Id retrait
     * @param id
     * @return objet Retrait
     * @throws DALException
     * @throws BusinessException
     */
    public Retrait rechercheParCle(int id) throws DALException, BusinessException {
        Retrait retrait = (Retrait) retraitDao.selectById(id);
        return retrait;
    }

    /**
     * Modifier le lieu retrait
     * @param retrait
     * @throws BusinessException
     */
    public void modifierRetrait(Retrait retrait) throws BusinessException {
        validerAdresse(retrait,  businessException);
        if(!businessException.hasErreurs()){
            retraitDao.update(retrait);
        }
    }

    public List<Retrait> trouverTous(){
        List<Retrait> listeRetraits = new ArrayList<>();
        try {
            listeRetraits = retraitDao.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (DALException e) {
            e.printStackTrace();
        }
        return listeRetraits;
    }

    /**
     * Méthode pour vérifier que les champs sont remplis car obligatoires
     * @param retrait
     * @param bE
     */
    public void validerAdresse(Retrait retrait, BusinessException bE){
        if(retrait.getRue() == null || retrait.getCodePostal() == null || retrait.getVille() == null ||
            retrait.getRue().trim().equals("") || retrait.getCodePostal().trim().equals("") ||
            retrait.getVille().trim().equals("")){
            businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAITS_ADRESSE_ERREUR);
        }
    }
}
