package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.ArticleManager;
import fr.eni.bo.Article;
import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageAcquisition extends HttpServlet {
    private ArticleManager am = new ArticleManager();
    Utilisateur util = null;
    Article art = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        util = (Utilisateur) session.getAttribute("utilisateur");
        //Récupère l'enchère remportée
        //TODO passage de paramètre
        int id = 1;
        //Si utilisateur est connecté
        if(session.getAttribute("utilisateur") != null ) {
            //Récupération de l'article en fonction de l'id
            try {
                art = am.ChoisirArticlesRemportes(id);
                if(art != null) {
                    req.setAttribute("nom_art", art.getNomArticle());
                    req.setAttribute("description", art.getDescription());
                    req.setAttribute("prix_vente", art.getPrixVente());
                    req.setAttribute("prix_initial", art.getPrixInitial());
                    if (art.getRetrait() != null) {
                        req.setAttribute("rue", art.getRetrait().getRue());
                        req.setAttribute("CP", art.getRetrait().getCodePostal());
                        req.setAttribute("ville", art.getRetrait().getVille());
                    } else {
                        req.setAttribute("rue", art.getUtilisateur().getRue());
                        req.setAttribute("CP", art.getUtilisateur().getCodePostal());
                        req.setAttribute("ville", art.getUtilisateur().getVille());
                    }
                    req.setAttribute("pseudo", art.getUtilisateur().getPseudo());
                    req.setAttribute("telephone", art.getUtilisateur().getTelephone());
                }

            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("/WEB-INF/html/PageAcquisition.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
