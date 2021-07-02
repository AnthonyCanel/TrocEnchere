package fr.eni.messages;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 * Cette classe permet de lire le contenu du fichier messages_erreur.properties
 */
public class LecteurMessage {
    private static ResourceBundle rb;

    static
    {
        try
        {
            rb = ResourceBundle.getBundle("fr.eni.messages.messages_erreur", new UTF8Control());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private LecteurMessage()
    {

    }
    /**
     * @param code
     * @return
     */
    public static  String getMessageErreur(int code)
    {
        String message="";
        try
        {
            if(rb!=null)
            {
                message = rb.getString(String.valueOf(code));
            }
            else
            {
                message="Problème à la lecture du fichier contenant les messages";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            message="Une erreur inconnue est survenue";
        }
        System.out.println("message="+message);



        return message;
    }
}
