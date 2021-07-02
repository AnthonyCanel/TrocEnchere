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
    private UtilisateurManager um = new UtilisateurManager();
    private BusinessException businessException = new BusinessException();
    Utilisateur util = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupère la session
        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");
        //Si utilisateur est connecté
        if(session.getAttribute("utilisateur") != null ) {
            try {
                Utilisateur utilEnCours = um.choisirUtilisateur(util.getNoUtilisateur());
                //Mise en place des informations récupérées
                req.setAttribute("pseudo", utilEnCours.getPseudo());
                req.setAttribute("nom", utilEnCours.getNom());
                req.setAttribute("prenom", utilEnCours.getPrenom());
                req.setAttribute("email", utilEnCours.getEmail());
                req.setAttribute("telephone", utilEnCours.getTelephone());
                req.setAttribute("rue", utilEnCours.getRue());
                req.setAttribute("CP", utilEnCours.getCodePostal());
                req.setAttribute("ville", utilEnCours.getVille());
                //Affichage de la page
                req.getRequestDispatcher("WEB-INF/html/PageMonProfil.jsp").forward(req, resp);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }else{
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupère la session
        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");
        if(!req.getParameter("button").equals("supprimer") ){

            //Si utilisateur est connecté
            if(session.getAttribute("utilisateur") != null ) {
                //Récupération des différents champs afin de faire un update
                int idUtilisateur = util.getNoUtilisateur();
                String pseudo = req.getParameter("pseudo");
                String nom = req.getParameter("nom");
                String prenom = req.getParameter("prenom");
                String email = req.getParameter("email");
                String tel = req.getParameter("telephone");
                String rue = req.getParameter("rue");
                String cP = req.getParameter("CP");
                String ville = req.getParameter("ville");
                String motPasse = "";
                //si mot de passe actuel différent de nouveau Mot de passe et que confirmation Mot de passe est rempli
                if((req.getParameter("mdpa") != req.getParameter("nmdp")) && req.getParameter("cmdp") != ""){
                    if(req.getParameter("nmdp") == req.getParameter("cmdp")){
                        motPasse = req.getParameter("nmdp");
                    }else{
                        businessException.ajouterErreur(CodesResultatServlet.REGLE_UTILISATEUR_NOUVEAUMOTDEPASSE_ERREUR);
                    }
                }
                if(req.getParameter("nmdp") == ""){
                    //motPasse = req.getParameter("mdpa");
                    try {
                        Utilisateur utilMDP = um.choisirUtilisateur(idUtilisateur);
                        motPasse = utilMDP.getMotDePasse();
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }

                }
                Boolean admin = false;
                //Création de l'objet utilisateur
                Utilisateur utilUpdate = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email, tel, rue, cP, ville, motPasse, admin);
                //Update
                try {
                    um.miseAJourUtilisateur(utilUpdate);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
            //Renvoie vers doGet afin de charger les nouvelles données
            doGet(req, resp);
        }else{
            try {
                um.supprimer(util.getNoUtilisateur());
                //efface la session
                session.invalidate();
                req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
    }
}
