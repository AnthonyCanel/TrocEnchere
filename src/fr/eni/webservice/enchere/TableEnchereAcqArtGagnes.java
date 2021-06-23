package fr.eni.webservice.enchere;

import fr.eni.bll.BusinessException;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.Enchere;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;


//package fr.eni.webservice.enchere;


        import fr.eni.bll.BusinessException;
        import fr.eni.bll.EnchereManager;
        import fr.eni.bo.Enchere;
        import fr.eni.dal.DALException;
import fr.eni.dal.EnchereDAOJdbcImpl;

import javax.ws.rs.*;
        import java.util.ArrayList;
        import java.util.List;



//@Path("/encheres")
//public class TableEnchereAcqArtGagnes {
//
//    private static List<Enchere> listEncheres = new ArrayList<>();
//    EnchereDAOJdbcImpl e =null;
//
//    {
//        try {
//            e = new EnchereDAOJdbcImpl();
//            listEncheres = e.selectByAcqEtArtVendu();
//        } catch ( BusinessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @GET
//    public List<Enchere> getVoitures() {
//        return listEncheres;
//    }
//
//}
