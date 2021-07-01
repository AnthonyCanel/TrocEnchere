package fr.eni.servlet;

import fr.eni.BusinessException;
import fr.eni.bll.ArticleManager;
import fr.eni.bll.CategorieManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.InfoArticle;
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
        CategorieManager cm = new CategorieManager();
        listeCategories = cm.AfficherCategories();

        //Récupération et réinjection des paramètres de la page jsp pour conservation lors de l'actualisation
        req.setAttribute("listeCategories", listeCategories);
        req.setAttribute("categorie", req.getParameter("combo"));
        req.setAttribute("saisie", req.getParameter("filtreSaisi"));
        //conservation valeur radio button achats & ventes
        req.setAttribute("check", req.getParameter("AchatsVentes"));
        //conservation valeurs checkbox
        req.setAttribute("btnEnchereOuverte", req.getParameter("btnEnchereOuverte"));
        req.setAttribute("btnEnchereEnCour", req.getParameter("btnEnchereEnCour"));
        req.setAttribute("btnEnchereRemporte", req.getParameter("btnEnchereRemporte"));
        req.setAttribute("btnVenteEnCour", req.getParameter("btnVenteEnCour"));
        req.setAttribute("btnVenteNonDebute", req.getParameter("btnVenteNonDebute"));
        req.setAttribute("btnVenteTerminee", req.getParameter("btnVenteTerminee"));

        req.getRequestDispatcher("WEB-INF/html/PageAccueilEnchere.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ArticleManager aM = new ArticleManager();
        BusinessException businessException = new BusinessException();
        int noCatSelect = 0;

        List<InfoArticle> mesVentesEnCours = null;
        List<InfoArticle> mesVentesNonDebutees = null;
        List<InfoArticle> mesVentesTerminees = null;

        List<InfoArticle> encheresOuvertes = null;
        List<InfoArticle> mesEncheresEnCours = null;
        List<InfoArticle> mesEncheresRemportees = null;

        List<InfoArticle> rechercheParDefaut = null;


        if (((session.getAttribute("utilisateur")) == null) ||
                (
                        ((session.getAttribute("utilisateur")) != null) &&
                                (req.getParameter("btnEnchereOuverte") == null ) &&
                                (req.getParameter("btnEnchereEnCour") == null ) &&
                                (req.getParameter("btnEnchereRemporte") == null) &&

                                (req.getParameter("btnVenteEnCour") == null ) &&
                                (req.getParameter("btnVenteNonDebute") == null) &&
                                (req.getParameter("btnVenteTerminee") == null)
//
//        (req.getParameter("btnEnchereOuverte") == null || req.getParameter("btnEnchereOuverte") != "on") &&
//                (req.getParameter("btnEnchereEnCour") == null || req.getParameter("btnEnchereEnCour") != "on") &&
//                (req.getParameter("btnEnchereRemporte") == null || req.getParameter("btnEnchereRemporte") != "on") &&
//
//                (req.getParameter("btnVenteEnCour") == null || req.getParameter("btnVenteEnCour") != "on") &&
//                (req.getParameter("btnVenteNonDebute") == null || req.getParameter("btnVenteNonDebute") != "on") &&
//                (req.getParameter("btnVenteTerminee") == null || req.getParameter("btnVenteTerminee") != "on")

                )
        ) {
            int idUtilisateur = 0;
            if ((session.getAttribute("utilisateur")) != null) {
                idUtilisateur = ((Utilisateur) session.getAttribute("utilisateur")).getNoUtilisateur();
            }
            req.setAttribute("AchatsVentes", req.getParameter("AchatsVentes"));
            String filtreSaisi = req.getParameter("filtreSaisi");
            if (req.getParameter("combo") != null || req.getParameter("combo") != "0") {
                noCatSelect = Integer.parseInt(req.getParameter("combo"));
            }

            try {
                rechercheParDefaut = aM.rechercheParFiltreEtNoCategorie(idUtilisateur, filtreSaisi, noCatSelect);
            } catch (BusinessException e) {
                e.printStackTrace();
                businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
            }
            req.setAttribute("rechercheParDefaut", rechercheParDefaut);

        } else {

            if ((session.getAttribute("utilisateur")) != null) {
                int idUtilisateur = ((Utilisateur) session.getAttribute("utilisateur")).getNoUtilisateur();
                req.setAttribute("AchatsVentes", req.getParameter("AchatsVentes"));

                String filtreSaisi = req.getParameter("filtreSaisi");
                if (req.getParameter("combo") != null || req.getParameter("combo") != "0") {
                    noCatSelect = Integer.parseInt(req.getParameter("combo"));
                }

                if (req.getParameter("AchatsVentes") != null && req.getParameter("AchatsVentes").equals("a")) {

                    if (req.getParameter("btnEnchereOuverte") != null && req.getParameter("btnEnchereOuverte").equals("on")) {
                        try {

                            encheresOuvertes = aM.encheresOuvertes(idUtilisateur, filtreSaisi, noCatSelect);
                        } catch (BusinessException e) {
                            e.printStackTrace();
                            businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                        }
                        req.setAttribute("encheresOuvertes", encheresOuvertes);
                    }

                    if (req.getParameter("btnEnchereEnCour") != null && req.getParameter("btnEnchereEnCour").equals("on")) {
                        try {
                            mesEncheresEnCours = aM.mesEncheresEnCours(idUtilisateur, filtreSaisi, noCatSelect);
                        } catch (BusinessException e) {
                            e.printStackTrace();
                            businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                        }
                        req.setAttribute("mesEncheresEnCours", mesEncheresEnCours);
                    }
                    if (req.getParameter("btnEnchereRemporte") != null && req.getParameter("btnEnchereRemporte").equals("on")) {
                        try {
                            mesEncheresRemportees = aM.mesEncheresRemportees(idUtilisateur, filtreSaisi, noCatSelect);
                        } catch (BusinessException e) {
                            e.printStackTrace();
                            businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                        }
                        req.setAttribute("mesEncheresRemportees", mesEncheresRemportees);
                    }

                }

                if (req.getParameter("AchatsVentes") != null && req.getParameter("AchatsVentes").equals("v")) {

                    if (req.getParameter("btnVenteEnCour") != null && req.getParameter("btnVenteEnCour").equals("on")) {
                        try {
                            mesVentesEnCours = aM.MesVentesEnCours(idUtilisateur, filtreSaisi, noCatSelect);
                        } catch (BusinessException e) {
                            e.printStackTrace();
                            businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);

                        }
                        req.setAttribute("mesVentesEnCours", mesVentesEnCours);
                    }

                    if (req.getParameter("btnVenteNonDebute") != null && req.getParameter("btnVenteNonDebute").equals("on")) {
                        try {
                            mesVentesNonDebutees = aM.MesVentesNonDebutees(idUtilisateur, filtreSaisi, noCatSelect);
                        } catch (BusinessException e) {
                            e.printStackTrace();
                            businessException.ajouterErreur(CodesResultatServlet.IMPORT_UTILISATEUR);
                        }
                        req.setAttribute("mesVentesNonDebutees", mesVentesNonDebutees);
                    }

                    if (req.getParameter("btnVenteTerminee") != null && req.getParameter("btnVenteTerminee").equals("on")) {
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
        }


        doGet(req, resp);
    }


}
