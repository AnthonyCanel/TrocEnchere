package fr.eni.bo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Enchere {
    private Utilisateur utilisateur;
    private Article article;

    private int noUtilisateur;
    private int noArticle;

    private Timestamp dateEnchere;
    private int montantEnchere;

    private String etatEnchere;
    private int noVendeur;


    public Enchere() {

    }

    public Enchere( int noUtilisateur, int noArticle, Timestamp dateEnchere, int montantEnchere, String etatEnchere, int noVendeur) {
        this.utilisateur = utilisateur;
        this.article = article;
        this.noUtilisateur = noUtilisateur;
        this.noArticle = noArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.etatEnchere = etatEnchere;
        this.noVendeur = noVendeur;
    }

    public Enchere(Utilisateur utilisateur, Article article, Timestamp dateEnchere, int montantEnchere, String etatEnchere, int noVendeur) {
        this.utilisateur = utilisateur;
        this.article = article;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.etatEnchere = etatEnchere;
        this.noVendeur = noVendeur;
    }
}
