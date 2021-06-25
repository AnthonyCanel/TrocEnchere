package fr.eni.servlet;

import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageDeconnexion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupère la session
        HttpSession session = req.getSession();
        Utilisateur util = (Utilisateur) session.getAttribute("utilisateur");

        if(session.getAttribute("utilisateur") != null ){
            session.invalidate();
            this.getServletContext().getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
