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
    private boolean der_ench;
    private int noAcquereur;

    private String nomAcquereur;


    public Enchere() {

    }

}
