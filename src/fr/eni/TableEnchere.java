package fr.eni;


import fr.eni.bll.BusinessException;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.Enchere;
import fr.eni.dal.DALException;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/encheres")
public class TableEnchere {

    private static List<Enchere> listEncheres = new ArrayList<>();
    EnchereManager em =null;

    {
        try {
            em = new EnchereManager();
            listEncheres = em.importEncheres();
        } catch ( BusinessException e) {
            e.printStackTrace();
        }
    }

    @GET
    public List<Enchere> getVoitures() {
        return listEncheres;
    }

}
