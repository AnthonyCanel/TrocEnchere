package fr.eni.bo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Enchere {
    private Utilisateur acquereur;
    private Article article;

    private int noAcquereur;
    private int noArticle;

    private Timestamp dateEnchere;
    private int montantEnchere;

    private String etatEnchere;
    private int noVendeur;


    public Enchere() {

    }

    public Enchere(int noAcquereur, int noArticle, Timestamp dateEnchere, int montantEnchere, String etatEnchere, int noVendeur) {
        this.acquereur = acquereur;
        this.article = article;
        this.noAcquereur = noAcquereur;
        this.noArticle = noArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.etatEnchere = etatEnchere;
        this.noVendeur = noVendeur;
    }

    public Enchere(Utilisateur acquereur, Article article, Timestamp dateEnchere, int montantEnchere, String etatEnchere, int noVendeur) {
        this.acquereur = acquereur;
        this.article = article;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.etatEnchere = etatEnchere;
        this.noVendeur = noVendeur;
    }

}
