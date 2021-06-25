package fr.eni.servlet;

import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageAccueilEnchere extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      HttpSession session = req.getSession();
//      Utilisateur util = new Utilisateur();
//      session.setAttribute("utilisateur", util);

        req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String achatVente = req.getParameter("AchatsVentes");
        req.setAttribute("etat", achatVente);
        this.doGet(req, resp);
    }
}
