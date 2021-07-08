package fr.eni.bll;


import fr.eni.BusinessException;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EnchereDAOJdbcImpl;

import java.util.List;

public class EnchereManager {
    private DAO<Enchere> generiqueDao;
    private BusinessException businessException = new BusinessException();
    private DAO<Utilisateur> utilisateurDAO;

    /**
     * return List of enchere 's table
     * @throws BusinessException
     */
    public void EnchereManager() throws BusinessException {

        this.generiqueDao = DAOFactory.getEnchereDAO();
        utilisateurDAO = DAOFactory.getUtilisateurDAO();
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

    public void miseAJourEnchere(Enchere enchere) throws BusinessException{
        try {
            //Vérification du montant enchère avec crédit dispo de l'utilisateur
            //Création de l'objet Utilisateur
            Utilisateur util = utilisateurDAO.selectById(enchere.getNoUtilisateur());
            if(enchere.getMontantEnchere() < util.getCredit()) {
                if(!enchere.isDer_ench()) {
                    generiqueDao.update(enchere);
                }
                else{
                    businessException.ajouterErreur(CodesResultatBLL.ENCHERES_DEJA_REALISEES);
                }
            }else{
                businessException.ajouterErreur(CodesResultatBLL.UPDATE_BLL_ENCHERES);
            }

        } catch (BusinessException e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodesResultatBLL.UPDATE_BLL_ENCHERES);
        }
    }
}
