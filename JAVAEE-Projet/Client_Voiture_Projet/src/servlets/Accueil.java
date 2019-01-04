package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Accueil() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		//Creer un session si aucune n'existe
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
		    session = request.getSession();
		    session.setAttribute("role", "defaut");
		}
		
		//Appel Vue
		request.setAttribute("titre", "Accueil");
		getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
