package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageMonProfil extends HttpServlet {
    private static UtilisateurManager um = new UtilisateurManager();
    Utilisateur util = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupère la session
        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");

        //Si utilisateur est connecté
        if(session.getAttribute("utilisateur") != null ) {
            //Mise en place des informations récupérées
            req.setAttribute("pseudo", util.getPseudo());
            req.setAttribute("nom", util.getNom());
            req.setAttribute("prenom", util.getPrenom());
            req.setAttribute("email", util.getEmail());
            req.setAttribute("telephone", util.getTelephone());
            req.setAttribute("rue", util.getRue());
            req.setAttribute("CP", util.getCodePostal());
            req.setAttribute("ville", util.getVille());
            //Affichage de la page
            req.getRequestDispatcher("WEB-INF/html/PageMonProfil.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LUT");
        doGet(req, resp);
    }
}
