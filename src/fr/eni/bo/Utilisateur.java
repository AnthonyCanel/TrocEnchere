package fr.eni.bo;

import lombok.Data;

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
    private List article;

    public Utilisateur(){
    }

    public Utilisateur(String pseudo, String prenom, String nom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean admin, List article) {
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
        this.article = article;
    }
}
