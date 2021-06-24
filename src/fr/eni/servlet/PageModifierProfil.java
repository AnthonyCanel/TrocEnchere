package fr.eni.servlet;

import fr.eni.bll.UtilisateurManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageModifierProfil extends HttpServlet {
    private static UtilisateurManager um = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Savoir si l'utilisateur est connecté
        //Récupère la session
        HttpSession session = req.getSession();
        //Si utilisateur est connecté
        if(session.getAttribute("idUtilisateur") != null ) {
            //Récupération des différents champs afin de faire un update
            String pseudo = req.getParameter("pseudo");
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
//            String nom = req.getParameter("nom");
//            String nom = req.getParameter("nom");
//            String nom = req.getParameter("nom");

            //Création de l'objet utilisateur

        }

    }
}
