package fr.eni.servlet;

public class CodesResultatServlet {
    /**
     * Echec général quand tentative d'ajouter un objet null
     */
    public static final int REGLE_UTILISATEUR_NOUVEAUMOTDEPASSE_ERREUR=30000;
    /**
     * Echec lors de l'insertion du compte utilisateur dans la base de données.
     */
    public static final int RECORD_UTILISATEUR=30051;
    /**
     * Echec lors de l'import des données
     */
    public static final int IMPORT_UTILISATEUR=30052;

}
