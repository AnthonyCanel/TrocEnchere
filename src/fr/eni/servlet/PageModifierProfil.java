package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.CodesResultatBLL;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageModifierProfil extends HttpServlet {
    private static UtilisateurManager um = new UtilisateurManager();
    private static BusinessException businessException = new BusinessException();

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
            int idUtilisateur = (int) session.getAttribute("idUtilisateur");
            String pseudo = req.getParameter("pseudo");
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String email = req.getParameter("email");
            String tel = req.getParameter("telephone");
            String rue = req.getParameter("rue");
            String cP = req.getParameter("codePostal");
            String ville = req.getParameter("ville");
            String motPasse ="";
            //si mot de passe actuel différent de nouveau Mot de passe et que confirmation Mot de passe est rempli
            if((req.getParameter("motPasseActuel") != req.getParameter("nouveauMotPasse")) && req.getParameter("confirmationMotPasse") != ""){
                if(req.getParameter("nouveauMotPasse") == req.getParameter("confirmationMotPasse")){
                    motPasse = req.getParameter("nouveauMotPasse");
                }else{
                    businessException.ajouterErreur(CodesResultatServlet.REGLE_UTILISATEUR_NOUVEAUMOTDEPASSE_ERREUR);
                }

            }else{
                if(req.getParameter("nouveauMotPasse") == ""){
                    motPasse = req.getParameter("motPasseActuel");
                }
            }

            //Création de l'objet utilisateur
            Utilisateur util = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email, tel, rue, cP, ville, motPasse);
            //Update
            try {
                um.miseAJourUtilisateur(util);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }

    }
}
