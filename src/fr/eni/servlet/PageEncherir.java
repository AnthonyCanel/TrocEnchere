package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.ArticleManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Article;
import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PageEncherir  extends HttpServlet {
    private static UtilisateurManager um = new UtilisateurManager();
    private static ArticleManager am = new ArticleManager();
    private static BusinessException businessException = new BusinessException();
    Utilisateur util = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupère la session
        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");
        int id = 2;
        //Si utilisateur est connecté
        if(session.getAttribute("utilisateur") != null ) {
            List<Article> listeArticles = am.ChoisirArticlesEncherir(id);
            //Affichage des données dans la jsp
            for (Article art: listeArticles) {
                req.setAttribute("nom_art", art.getNomArticle());
                req.setAttribute("description", art.getDescription());
            }

            //Affichage de la page
            req.getRequestDispatcher("WEB-INF/html/PageEncherir.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
