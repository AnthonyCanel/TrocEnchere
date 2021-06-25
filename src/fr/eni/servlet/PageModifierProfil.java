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
    Utilisateur util = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");
        //Si utilisateur est connecté
        if(session.getAttribute("utilisateur") != null ) {
            try {
                Utilisateur utilEnCours = um.choisirUtilisateur(util.getNoUtilisateur());
                //Affichage des informations présentes dans la base de données
                req.setAttribute("pseudo", utilEnCours.getPseudo());
                req.setAttribute("nom", utilEnCours.getNom());
                req.setAttribute("prenom", utilEnCours.getPrenom());
                req.setAttribute("email", utilEnCours.getEmail());
                req.setAttribute("telephone", utilEnCours.getTelephone());
                req.setAttribute("rue", utilEnCours.getRue());
                req.setAttribute("CP", utilEnCours.getCodePostal());
                req.setAttribute("ville", utilEnCours.getVille());
                req.setAttribute("mdpa", utilEnCours.getMotDePasse());
                req.setAttribute("credit",utilEnCours.getCredit());
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("WEB-INF/html/PageModifierProfil.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("lut");
        //Récupère la session
        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");
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
    req.getRequestDispatcher("WEB-INF/html/PageMonProfil.jsp").forward(req, resp);
    }
}
