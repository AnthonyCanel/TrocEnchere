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
    private String etat_Article;
    private String photo;
    private int no_Utilisateur;
    private int no_Categorie;

    public Article() {
    }

    public Article(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, int no_Utilisateur, int no_Categorie) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.etat_Article = etat_Article;
        this.photo = photo;
        this.no_Utilisateur = no_Utilisateur;
        this.no_Categorie = no_Categorie;
    }

    public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, int no_Utilisateur, int no_Categorie) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.etat_Article = etat_Article;
        this.photo = photo;
        this.no_Utilisateur = no_Utilisateur;
        this.no_Categorie = no_Categorie;
    }

}
