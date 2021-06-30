package fr.eni.bo;

import lombok.Data;

import java.sql.Date;
@Data
public class InfoArticle {
    int idArticle;
    String nomArticle;
    int prixArticle;
    Date finEnchere;
    String vendeur;

    public InfoArticle(){    }


}
