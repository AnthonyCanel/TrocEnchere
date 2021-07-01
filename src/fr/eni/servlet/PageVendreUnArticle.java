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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PageVendreUnArticle extends HttpServlet {
    List<Categorie> listeCategories = null;
    CategorieManager categorieManager = new CategorieManager();
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

            try {
                categorie = categorieManager.ChoisirCategorie(Integer.parseInt(req.getParameter("combo")));
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            categorie.setLibelle(categorie.getLibelle());
            categorie.setNoCategorie(categorie.getNoCategorie());
            newArticle.setCategorie(categorie);


            newArticle.setPrixVente(Integer.parseInt(req.getParameter("prix")));
            //Formatage de Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateD = req.getParameter("dateDebut");
            String dateF = req.getParameter("dateFin");

            LocalDate dateDebut = LocalDate.parse(dateD, formatter);
            LocalDate dateFin = LocalDate.parse(dateF, formatter);

            newArticle.setDateDebutEncheres(dateDebut);
            newArticle.setDateFinEncheres(dateFin);

            retrait.setRue(req.getParameter("rue"));
            retrait.setCodePostal(req.getParameter("CP"));
            retrait.setVille(req.getParameter("ville"));
            newArticle.setRetrait(retrait);

            ArticleManager articleManager = new ArticleManager();
            try {
                articleManager.ajouterArticle(newArticle);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
            }
        }
}


