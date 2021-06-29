package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageCreerCompte extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("WEB-INF/html/PageCreerCompte.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("boutonAction").equals("annuler")) {
            resp.sendRedirect(req.getContextPath() + "/PageAccueilEnchere");
        } else {
            BusinessException businessException = new BusinessException();
            Boolean creationCompteValide = false;

            Utilisateur util = new Utilisateur();
            util.setNom(req.getParameter("nom"));
            util.setPrenom(req.getParameter("prenom"));
            util.setPseudo(req.getParameter("pseudo"));

            util.setEmail(req.getParameter("email"));
            util.setTelephone(req.getParameter("tel"));

            util.setRue(req.getParameter("rue"));
            util.setVille(req.getParameter("ville"));
            util.setCodePostal(req.getParameter("CP"));


            if (req.getParameter("mdp").equals(req.getParameter("mdpC")) && req.getParameter("mdp")!="" && req.getParameter("mdp")!=null) {
                creationCompteValide = true;
                util.setMotDePasse(req.getParameter("mdp"));
                HttpSession session = req.getSession();
                session.setAttribute("utilisateur", util);


            } else {
                String message = "Le mot de passe et sa confirmation doivent être identiques";
                req.setAttribute("message", message);
            }


            if (!creationCompteValide) {
                req.getRequestDispatcher("WEB-INF/html/PageCreerCompte.jsp").forward(req, resp);

            } else {

                UtilisateurManager uM = new UtilisateurManager();

                try {
                    uM.ajouterUtilisateur(util);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.RECORD_UTILISATEUR);
                }


                req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
            }
        }


    }
}
