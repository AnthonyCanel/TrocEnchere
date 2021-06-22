package fr.eni.bo;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private int noArticle;
    private String nomArticle;
    private String description;
    private Date dateDebutEncheres;
    private Date dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private Utilisateur utilisateur;

    public Article(){
    }

    public Article(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, Utilisateur utilisateur, int prixInitial, int prixVente) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
    }
}
