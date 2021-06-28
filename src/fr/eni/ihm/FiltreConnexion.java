package fr.eni.ihm;

import fr.eni.bo.Utilisateur;

import javax.annotation.processing.Filer;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

public class FiltreConnexion implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain isConnected) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession();

        Utilisateur util = new Utilisateur();

        if(session.getAttribute("utilisateur")!=null){
            session.setAttribute("utilisateur",util);
        }
        isConnected.doFilter(req, servletResponse);
        System.out.println("je suis dans le filtre");
//        req.getRequestDispatcher("PageAccueilEnchere").forward(req,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
