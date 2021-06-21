package fr.eni.bo;

import lombok.Data;

import java.util.Date;

@Data
public class Enchere {
    private Date dateEnchere;
    private int montantEnchere;
    private Utilisateur utilisateur;
    private Article article;

    public Enchere() {
    }

    public Enchere(Date dateEnchere, int montantEnchere, Utilisateur utilisateur, Article article) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.article = article;
    }
}
