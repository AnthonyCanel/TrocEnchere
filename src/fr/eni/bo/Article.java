package fr.eni.bo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Article {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private String etat_Article;
    private String photo;
    private Utilisateur utilisateur;
    private Categorie categorie;
    private Retrait retrait;
    private int vues;
    private Enchere enchere;

    public Article(int noArticle,String nomArticle, int articlePrixInitial, LocalDate articleDateDebutEncheres,int encherePrix,String nomUtilisateur,int noUtilisateur,int noCategorie,String categorieLabelle){
        this.noArticle=noArticle;
        this.nomArticle= nomArticle;
        this.prixInitial = articlePrixInitial;
        this.dateDebutEncheres = articleDateDebutEncheres;
        this.enchere.setMontantEnchere(encherePrix);
        this.utilisateur.setNom(nomUtilisateur);
        this.utilisateur.setNoUtilisateur(noUtilisateur);
        this.categorie.setNoCategorie(noCategorie);
        this.categorie.setLibelle(categorieLabelle);
    }

    public Article() {

    }

    public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, int vues) {
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

    public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, int vues) {
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

    public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, Utilisateur utilisateur, Categorie categorie) {
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

    public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, String etat_Article, String photo, Utilisateur utilisateur, Categorie categorie, int vues) {
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
