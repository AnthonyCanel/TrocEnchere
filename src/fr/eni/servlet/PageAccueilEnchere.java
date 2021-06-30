package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.ArticleManager;
import fr.eni.bll.CategorieManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.InfoArticle;
import fr.eni.bo.TransfertRequest;
import fr.eni.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageAccueilEnchere extends HttpServlet {
    List<Categorie> listeCategories = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Categorie categorieObjet = new Categorie();
        CategorieManager cm = new CategorieManager();
        listeCategories = cm.AfficherCategories();

        req.setAttribute("listeCategories", listeCategories);
        req.setAttribute("categorie", req.getParameter("combo"));
        req.setAttribute("saisie", req.getParameter("filtreSaisi"));


        req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

//        session.setAttribute("AchatVentes",session.getAttribute("AchatsVentes"));

        ArticleManager aM = new ArticleManager();
        BusinessException businessException = new BusinessException();
        int noCatSelect = 0;

        List<InfoArticle> mesVentesEnCours = null;
        List<InfoArticle> mesVentesNonDebutees = null;
        List<InfoArticle> mesVentesTerminees = null;

        List<InfoArticle> encheresOuvertes = null;
        List<InfoArticle> mesEncheresEnCours = null;
        List<InfoArticle> mesEncheresRemportees = null;

        if ((session.getAttribute("utilisateur")) != null) {
            int idUtilisateur = ((Utilisateur) session.getAttribute("utilisateur")).getNoUtilisateur();
            req.setAttribute("AchatsVentes",req.getParameter("AchatsVentes"));

            String filtreSaisi = req.getParameter("filtreSaisi");
            if (req.getParameter("combo") != null||req.getParameter("combo") != "0") {
                noCatSelect = Integer.parseInt(req.getParameter("combo"));
            }

            if (req.getParameter("AchatsVentes").equals("a")) {

                if (req.getParameter("achats").equals("encheresOuvertes")) {
                    try {
                        encheresOuvertes = aM.encheresOuvertes(idUtilisateur, filtreSaisi, noCatSelect);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                    }
                    req.setAttribute("encheresOuvertes", encheresOuvertes);
                }

                if (req.getParameter("achats").equals("mesEncheresEnCours")) {
                    try {
                        mesEncheresEnCours = aM.mesEncheresEnCours(idUtilisateur, filtreSaisi, noCatSelect);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                    }
                    req.setAttribute("mesEncheresEnCours", mesEncheresEnCours);
                }
                if (req.getParameter("achats").equals("mesEncheresRemportees")) {
                    try {
                        mesEncheresRemportees = aM.mesEncheresRemportees(idUtilisateur, filtreSaisi, noCatSelect);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                    }
                    req.setAttribute("mesEncheresRemportees", mesEncheresRemportees);
                }

            }

            if (req.getParameter("AchatsVentes").equals("v")) {

                if (req.getParameter("ventes").equals("mesVentesEnCours")) {
                    try {
                        mesVentesEnCours = aM.MesVentesEnCours(idUtilisateur, filtreSaisi, noCatSelect);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);

                    }
                    req.setAttribute("mesVentesEnCours", mesVentesEnCours);
                }

                if (req.getParameter("ventes").equals("ventesNonDebutees")) {
                    try {
                        mesVentesNonDebutees = aM.MesVentesNonDebutees(idUtilisateur, filtreSaisi, noCatSelect);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                    }
                    req.setAttribute("mesVentesNonDebutees", mesVentesNonDebutees);
                }

                if (req.getParameter("ventes").equals("ventesTerminees")) {
                    try {
                        mesVentesTerminees = aM.MesVentesTerminees(idUtilisateur, filtreSaisi, noCatSelect);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                    }
                    req.setAttribute("mesVentesTerminees", mesVentesTerminees);
                }

            }

        }

//    req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req,resp);



        doGet(req, resp);
    }


}
