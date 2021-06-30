package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.ArticleManager;
import fr.eni.bo.Article;
import fr.eni.bo.InfoArticle;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAO;
import fr.eni.dal.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RechercheAchatsVentes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleManager aM = new ArticleManager();
        HttpSession session = req.getSession();
        BusinessException businessException = new BusinessException();

        List<InfoArticle> mesVentesEnCours = null;
        List<InfoArticle> mesVentesNonDebutees= null;
        List<InfoArticle> mesVentesTerminees = null;

        List<InfoArticle> encheresOuvertes = null;
        List<InfoArticle> mesEncheresEnCours = null;
        List<InfoArticle> mesEncheresRemportees = null;

        int idUtilisateur = ((Utilisateur)session.getAttribute("utilisateur")).getNoUtilisateur();
        String filtreSaisi= req.getParameter("textRecherche");
        int noCatSelect= Integer.parseInt(req.getParameter("combo"));




        if (req.getParameter("AchatsVentes") == "a") {

            if (req.getParameter("achats") == "encheresOuvertes") {
                try {
                    encheresOuvertes = aM.encheresOuvertes(idUtilisateur, filtreSaisi, noCatSelect);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                }
                req.setAttribute("mesVentesEnCours",mesVentesEnCours);
            }

            if (req.getParameter("achats") == "mesEncheresEnCours") {
                try {
                    mesEncheresEnCours = aM.mesEncheresEnCours(idUtilisateur, filtreSaisi, noCatSelect);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                }
                req.setAttribute("mesVentesNonDebutees",mesVentesNonDebutees);
            }
            if (req.getParameter("achats") == "mesEncheresRemportees") {
                try {
                    mesEncheresRemportees = aM.mesEncheresRemportees(idUtilisateur, filtreSaisi, noCatSelect);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                }
                req.setAttribute("mesVentesTerminees",mesVentesTerminees);
            }

        }

        if (req.getParameter("AchatsVentes") == "v") {

            if (req.getParameter("ventes") == "mesVentesEnCours") {
                try {
                    mesVentesEnCours = aM.MesVentesEnCours(idUtilisateur, filtreSaisi, noCatSelect );
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);

                }
                req.setAttribute("encheresOuvertes",encheresOuvertes);
            }

            if (req.getParameter("ventes") == "ventesNonDebutees") {
                try {
                    mesVentesNonDebutees = aM.MesVentesNonDebutees(idUtilisateur, filtreSaisi, noCatSelect);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                }
                req.setAttribute("mesEncheresEnCours",mesEncheresEnCours);
            }

            if (req.getParameter("ventes") == "ventesTerminees") {
                try {
                    mesVentesTerminees = aM.MesVentesTerminees(idUtilisateur, filtreSaisi, noCatSelect);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                }
                req.setAttribute("mesEncheresRemportees",mesEncheresRemportees);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
