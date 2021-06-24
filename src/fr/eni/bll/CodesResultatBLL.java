package fr.eni.bll;

public class CodesResultatBLL {
    /**
     * Echec le nom de l'article ne respecte pas les règles définies
     */
    public static final int REGLE_ARTICLE_NOM_ERREUR = 20000;
    /**
     * Echec le nom de l'article ne respecte pas les règles définies
     */
    public static final int REGLE_LISTE_NOM_ERREUR = 20001;
    /**
     * Echec suite à la vérification de l'adresse de Retraits
     */

    public static final int REGLE_RETRAITS_RUE_ERREUR = 20002;
    public static final int REGLE_RETRAITS_CODEPOSTAL_ERREUR = 20003;
    public static final int REGLE_RETRAITS_VILLE_ERREUR = 20004;
    /**
     * Echec suite à la vérification de l'adresse de Utilisateur
     */
    public static final int REGLE_UTILISATEURS_PSEUDO_ERREUR = 20005;
    public static final int REGLE_UTILISATEURS_NOM_ERREUR = 20006;
    public static final int REGLE_UTILISATEURS_PRENOM_ERREUR = 20007;
    public static final int REGLE_UTILISATEURS_EMAIL_ERREUR = 20008;
    public static final int REGLE_UTILISATEURS_TEL_ERREUR = 20009;
    public static final int REGLE_UTILISATEURS_RUE_ERREUR = 20010;
    public static final int REGLE_UTILISATEURS_CODEPOSTAL_ERREUR = 20011;
    public static final int REGLE_UTILISATEURS_VILLE_ERREUR = 20012;
    public static final int REGLE_UTILISATEURS_MOTDEPASSE_ERREUR = 20013;


    public static final int UTILISATEURS_PSEUDO_ERREUR = 20014;

    public static final int UTILISATEURS_EMAIL_ERREUR = 20015;


    /**
     * Echec suite à import des données à la bll
     */
    public static final int IMPORT_BLL_ENCHERE = 20050;

    public static final int IMPORT_BLL_ENCHERES_GAGNEES=20051;

    public static final int UTILISATEUR_CONNECTION_IMPOSSIBLE = 20052;

}
