package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.UtilisateurManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SupprimerCompte extends HttpServlet {
    private static UtilisateurManager um = new UtilisateurManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupére la session
        HttpSession session = req.getSession();
        //Si l'utilisateur n'est pas connecté, on le renvoie vers la page
        if (session.getAttribute("idUtilisateur") == null) {
            //Vérification si le paramètre id est renseigné
            if (req.getParameter("id") != null) {
                try {
                    //Suppression utilisateur
                    um.supprimer(Integer.valueOf(req.getParameter("id")));
                    //Invalide la session
                    session.invalidate();
                    //Redirection vers la page d'accueil
                    req.getRequestDispatcher("WEB-INF/html/PageAccueilNonConnecte.jsp").forward(req, resp);

                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else {
                //Affichage page d'erreur
                this.getServletContext().getRequestDispatcher("/WEB-INF/erreur418.jsp").forward(req, resp);
            }
        }else{
            //Affichage page d'erreur
            this.getServletContext().getRequestDispatcher("/WEB-INF/erreur418.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
