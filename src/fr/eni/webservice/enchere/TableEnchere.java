package fr.eni.webservice.enchere;



import fr.eni.BusinessException;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.Enchere;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.webservice.CodeResultIhm;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/encheres")
public class TableEnchere {
    private static BusinessException businessException = new BusinessException();

    private static List<Enchere> listEncheres = new ArrayList<>();
    EnchereManager em =null;

    {
        try {
            em = new EnchereManager();
            listEncheres = em.importEncheres();
        } catch ( BusinessException e) {
            e.printStackTrace();
            businessException.ajouterErreur(CodeResultIhm.IMPORT_WEBSERVICE_TAB_ENCHERE);
        }
    }

    @GET
    public List<Enchere> getListEncheres() {
        return listEncheres;
    }

}
