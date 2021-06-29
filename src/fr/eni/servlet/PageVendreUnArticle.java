package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PageVendreUnArticle extends HttpServlet {
    List<Categorie> listeCategories = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Session
        HttpSession session = req.getSession();
        CategorieManager cm = new CategorieManager();
        listeCategories = cm.AfficherCategories();
        req.setAttribute("listeCategories",listeCategories);
        session.setAttribute("categorie",session.getAttribute("combo"));
        Utilisateur util = (Utilisateur) session.getAttribute("utilisateur");
        req.setAttribute("rue", util.getRue());
        req.setAttribute("CP", util.getCodePostal());
        req.setAttribute("ville", util.getVille());
        req.getRequestDispatcher("WEB-INF/html/PageVendreUnArticle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btn = req.getParameter("btn");
        if (btn.equals("annulerVente") ) {

        } else if (btn.equals("annuler")){
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
        } else if(btn.equals("enregistrer")) {
            Retrait retrait = new Retrait();
            Categorie categorie = new Categorie();
            Article newArticle = new Article();

            newArticle.setNomArticle(req.getParameter("nomArticle"));
            newArticle.setDescription(req.getParameter("description"));


            categorie.setLibelle(req.getParameter("categories"));
            categorie.setNoCategorie(Integer.parseInt(req.getParameter("categories")));
            newArticle.setCategorie(categorie);

            newArticle.setPrixVente(Integer.parseInt(req.getParameter("prix")));
            Date dateDebut = null;
            Date dateFin = null;
            try {
                dateDebut = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dateDebut"));
                dateFin = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dateFin"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newArticle.setDateDebutEncheres(dateDebut);
            newArticle.setDateFinEncheres(dateFin);

            retrait.setRue(req.getParameter("rue"));
            retrait.setCodePostal(req.getParameter("CP"));
            retrait.setVille(req.getParameter("ville"));
            newArticle.setRetrait(retrait);
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
            }
        }
}


