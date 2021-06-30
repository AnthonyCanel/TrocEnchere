package fr.eni.bo;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class InfoArticle {
    int idArticle;
    String nomArticle;
    int prixArticle;
    LocalDate finEnchere;
    String vendeur;

    public InfoArticle(){    }


}
