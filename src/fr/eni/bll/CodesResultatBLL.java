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
    public static final int REGLE_RETRAITS_ADRESSE_ERREUR = 20002;
    /**
     * Echec suite à la vérification de l'adresse de Utilisateur
     */
    public static final int REGLE_UTILISATEURS_ADRESSE_ERREUR = 20003;

    /**
     * Echec suite à import des données à la bll
     */
    public static final int IMPORT_BLL_ENCHERE = 20050;

    public static final int IMPORT_BLL_ENCHERES_GAGNEES=20051;

}
