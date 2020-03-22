package servlet;

import java.util.*;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

import com.excilys.librarymanager.service_impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service_impl.LivreServiceImpl;
import com.excilys.librarymanager.service_impl.MembreServiceImpl;
import com.excilys.librarymanager.modele.*;


public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        try {
            EmpruntServiceImpl empruntService = EmpruntServiceImpl .getInstance();
            int nb_emprunt=empruntService.count();
            List<Emprunt> listEmprunt=empruntService.getListCurrent();
            LivreServiceImpl livreService = LivreServiceImpl .getInstance();
            int nb_livre=livreService.count();
            MembreServiceImpl membreService = MembreServiceImpl .getInstance();
            int nb_membre=membreService.count();

            request.setAttribute("nb_emprunt", nb_emprunt);
            request.setAttribute("listEmprunt", listEmprunt);
            request.setAttribute("nb_livre", nb_livre);
            request.setAttribute("nb_membre", nb_membre);
            
        } catch (Exception e) {
            throw new ServletException();
        }   
    }
}