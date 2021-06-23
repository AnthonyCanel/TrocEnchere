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
    private Utilisateur utilisateur;
    private Categorie categorie;
    private int vues;

    public Article() {

    }

    public Article(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, int vues) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.etat_Article = etat_Article;
        this.photo = photo;
        this.vues = vues;
    }

    public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, int vues) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.etat_Article = etat_Article;
        this.photo = photo;
        this.vues = vues;
    }

    public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, Utilisateur utilisateur, Categorie categorie) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.etat_Article = etat_Article;
        this.photo = photo;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
    }

    public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, Utilisateur utilisateur, Categorie categorie, int vues) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.etat_Article = etat_Article;
        this.photo = photo;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.vues = vues;
    }
}
