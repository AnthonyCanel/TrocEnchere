package fr.eni.bo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Enchere {
    private int noUtilisateur;
    private int noArticle;

    private Timestamp dateEnchere;
    private int montantEnchere;

    private String etatEnchere;
    private int noAcquereur;


    public Enchere() {

    }

}
