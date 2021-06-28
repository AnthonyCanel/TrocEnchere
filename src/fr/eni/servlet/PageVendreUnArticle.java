package fr.eni.servlet;

import fr.eni.bll.CategorieManager;
import fr.eni.bo.Categorie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PageVendreUnArticle extends HttpServlet {
    List<Categorie> listeCategories = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Session
        HttpSession session = req.getSession();
        CategorieManager cm =new CategorieManager();
        listeCategories = cm.AfficherCategories();
        req.setAttribute("categorie",listeCategories);
        session.setAttribute("categorie",session.getAttribute("combo"));
        req.getRequestDispatcher("WEB-INF/html/PageVendreUnArticle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
