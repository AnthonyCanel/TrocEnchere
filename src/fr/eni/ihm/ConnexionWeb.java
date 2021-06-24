package fr.eni.ihm;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConnexionWeb {
    public boolean isConnected(HttpServletRequest req) {
        Boolean connected = false;
        HttpSession session = req.getSession();
        if (session.getAttribute("connexion") == "true") {
            session.setAttribute("connexion", "true");
            connected = true;
        }
        return true;
    }

    public void connexion(HttpServletRequest req) {
        UtilisateurManager um = new UtilisateurManager();
        List<Utilisateur> listUtilisateur = null;
        listUtilisateur = um.trouverTous();

        String IdentifiantSaisie = req.getParameter("Identifiant");
        String MotDePasseSaisie = req.getParameter("MotDePasse");

        for (Utilisateur u : listUtilisateur
        ) {
            if (IdentifiantSaisie.equals(u.getPseudo()) && MotDePasseSaisie.equals(u.getMotDePasse())) {
                HttpSession session = req.getSession();
                if (session.getAttribute("connexion") == "true") {

                } else {
                    session.setAttribute("connexion", "true");
                }
                break;
            }
        }

    }
}
