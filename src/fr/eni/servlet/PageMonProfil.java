package fr.eni.servlet;

import fr.eni.bll.UtilisateurManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageMonProfil extends HttpServlet {
    private static UtilisateurManager um = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupère la session
        HttpSession session = req.getSession();
        //Si utilisateur est connecté
        if(session.getAttribute("idUtilisateur") != null ) {
            //req.setAttribute("Pseudo", um.);
            //req.getServletContext().setAttribute("Utilisateur", util);
        }
        else{
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
