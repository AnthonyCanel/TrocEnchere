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

public class PageConnexion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/PageConnexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UtilisateurManager um = new UtilisateurManager();
        Utilisateur util = null;

        try {
            util = um.getUtilisateurExiste(req.getParameter("Identifiant"), req.getParameter("MotDePasse"));
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        if (util != null) {
            HttpSession session = req.getSession();
            session.setAttribute("utilisateur", util);
            System.out.println("je suuis connecté");
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        }else{
            System.out.println("je ne suis pas connecté");
            doGet(req,resp);
        }


    }

}
