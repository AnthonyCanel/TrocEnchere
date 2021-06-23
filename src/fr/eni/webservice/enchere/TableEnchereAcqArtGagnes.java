package fr.eni.webservice.enchere;

import fr.eni.BusinessException;
import fr.eni.bo.Enchere;
import fr.eni.dal.EnchereDAOJdbcImpl;
import fr.eni.webservice.CodeResultIhm;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/encheres")
public class TableEnchereAcqArtGagnes {
    private static BusinessException businessException = new BusinessException();
    private static List<Enchere> listEncheres = new ArrayList<>();


//todo ajouer l'acquereur lors que la session et faite
//    @GET
//    public List<Enchere> getEncheresGagnees() {
//
//        EnchereDAOJdbcImpl eDJI =null;
//
//        try {
//            eDJI = new EnchereDAOJdbcImpl();
//            listEncheres = eDJI.selectByAcqEtArtVendu();
//        } catch ( Exception e) {
//            e.printStackTrace();
//            businessException.ajouterErreur(CodeResultIhm.IMPORT_WEBSERVICE_TAB_ENCHERE_ARTICLES_GAGNES);
//        }
//
//        return listEncheres;
//    }

}
