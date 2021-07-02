package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;
import fr.eni.messages.LecteurMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PageCreerCompte extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

       String s = LecteurMessage.getMessageErreur(30000);
       System.out.println(s);
req.setAttribute("error", s);
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
                UtilisateurManager uM = new UtilisateurManager();
                util.setMotDePasse(req.getParameter("mdp"));
                HttpSession session = req.getSession();
                session.setAttribute("utilisateur", util);
                try {
                    uM.ajouterUtilisateur(util);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.RECORD_UTILISATEUR);
                }
                finally {
                    if(businessException.hasErreurs()){
                        req.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
                    }
                }
                creationCompteValide = true;

            } else {
                businessException.ajouterErreur(CodesResultatServlet.MDP_DIFFERENT);
                req.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
            }

                if (!creationCompteValide) {
                    req.setAttribute("pseudo", req.getParameter("pseudo"));
                    req.setAttribute("nom", req.getParameter("nom"));
                    req.setAttribute("prenom", req.getParameter("prenom"));
                    req.setAttribute("email", req.getParameter("email"));
                    req.setAttribute("tel", req.getParameter("tel"));
                    req.setAttribute("rue", req.getParameter("rue"));
                    req.setAttribute("CP", req.getParameter("CP"));
                    req.setAttribute("ville", req.getParameter("ville"));
                    this.doGet(req, resp);
                }
                req.setCharacterEncoding("UTF-8");
                req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
            }
        }
}

