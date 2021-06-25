package fr.eni.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Utilisateur {
    private int noUtilisateur;
    private String pseudo;
    private String prenom;
    private String nom;
    private String email;
    private String telephone;
    private String rue;
    private String CodePostal;
    private String ville;
    private String motDePasse;
    private int credit;
    private boolean admin;
    private List<Article> article;

    public Utilisateur(){
        article = new ArrayList<>();
    }

    public Utilisateur(int noUtilisateur, String pseudo, String prenom, String nom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        CodePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(String pseudo, String prenom, String nom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean admin) {
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        CodePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.admin = admin;
    }

    public Utilisateur(String pseudo, String prenom, String nom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit) {
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        CodePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(int noUtilisateur, String pseudo, String prenom, String nom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean admin) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        CodePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.admin = admin;
    }
}
