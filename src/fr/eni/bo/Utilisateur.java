package fr.eni.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Utilisateur {
    private int noUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
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

    /**
     *
     * @param pNoUtilisateur
     * @param pPseudo
     * @param pNom
     * @param pPrenom
     * @param pEmail
     * @param pTelephone
     * @param pRue
     * @param pCodePostal
     * @param pVille
     * @param pMotDePasse
     */
    public Utilisateur(int pNoUtilisateur, String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, String pCodePostal, String pVille, String pMotDePasse) {
        noUtilisateur = pNoUtilisateur;
        pseudo = pPseudo;
        nom = pNom;
        prenom = pPrenom;
        email = pEmail;
        telephone = pTelephone;
        rue = pRue;
        CodePostal = pCodePostal;
        ville = pVille;
        motDePasse = pMotDePasse;
    }

    /**
     *
     * @param pNoUtilisateur
     * @param pPseudo
     * @param pNom
     * @param pPrenom
     * @param pEmail
     * @param pTelephone
     * @param pRue
     * @param pCodePostal
     * @param pVille
     * @param pMotDePasse
     * @param pAdmin
     */
    public Utilisateur(int pNoUtilisateur, String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, String pCodePostal, String pVille, String pMotDePasse, boolean pAdmin) {
        noUtilisateur = pNoUtilisateur;
        pseudo = pPseudo;
        nom = pNom;
        prenom = pPrenom;
        email = pEmail;
        telephone = pTelephone;
        rue = pRue;
        CodePostal = pCodePostal;
        ville = pVille;
        motDePasse = pMotDePasse;
        admin = pAdmin;
    }

    /**
     *
     * @param pPseudo
     * @param pNom
     * @param pPrenom
     * @param pEmail
     * @param pTelephone
     * @param pRue
     * @param pCodePostal
     * @param pVille
     * @param pMotDePasse
     * @param pCredit
     * @param pAdmin
     */
    public Utilisateur(String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, String pCodePostal, String pVille, String pMotDePasse, int pCredit, boolean pAdmin) {
        pseudo = pPseudo;
        nom = pNom;
        prenom = pPrenom;
        email = pEmail;
        telephone = pTelephone;
        rue = pRue;
        CodePostal = pCodePostal;
        ville = pVille;
        motDePasse = pMotDePasse;
        credit = pCredit;
        admin = pAdmin;
    }

    /**
     *
     * @param pPseudo
     * @param pNom
     * @param pPrenom
     * @param pEmail
     * @param pTelephone
     * @param pRue
     * @param pCodePostal
     * @param pVille
     * @param pMotDePasse
     * @param pCredit
     */
    public Utilisateur(String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, String pCodePostal, String pVille, String pMotDePasse, int pCredit) {
        pseudo = pPseudo;
        nom = pNom;
        prenom = pPrenom;
        email = pEmail;
        telephone = pTelephone;
        rue = pRue;
        CodePostal = pCodePostal;
        ville = pVille;
        motDePasse = pMotDePasse;
        credit = pCredit;
    }

    /**
     *
     * @param noUtilisateur
     * @param pPseudo
     * @param pNom
     * @param pPrenom
     * @param pEmail
     * @param pTelephone
     * @param pRue
     * @param pCodePostal
     * @param pVille
     * @param pMotDePasse
     * @param pCredit
     */
    public Utilisateur(int noUtilisateur, String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, String pCodePostal, String pVille, String pMotDePasse, int pCredit) {
        this.noUtilisateur = noUtilisateur;
        pseudo = pPseudo;
        nom = pNom;
        prenom = pPrenom;
        email = pEmail;
        telephone = pTelephone;
        rue = pRue;
        CodePostal = pCodePostal;
        ville = pVille;
        motDePasse = pMotDePasse;
        credit = pCredit;
    }

    /**
     *
     * @param noUtilisateur
     * @param pPseudo
     * @param pNom
     * @param pPrenom
     * @param pEmail
     * @param pTelephone
     * @param pRue
     * @param pCodePostal
     * @param pVille
     * @param pMotDePasse
     * @param pCredit
     * @param pAdmin
     */
    public Utilisateur(int noUtilisateur, String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, String pCodePostal, String pVille, String pMotDePasse, int pCredit, boolean pAdmin) {
        this.noUtilisateur = noUtilisateur;
        pseudo = pPseudo;
        nom = pNom;
        prenom = pPrenom;
        email = pEmail;
        telephone = pTelephone;
        rue = pRue;
        CodePostal = pCodePostal;
        ville = pVille;
        motDePasse = pMotDePasse;
        credit = pCredit;
        admin = pAdmin;
    }

}
