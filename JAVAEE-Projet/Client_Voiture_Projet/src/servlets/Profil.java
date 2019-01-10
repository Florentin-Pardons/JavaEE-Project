package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Profil() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false); 
		if(session == null)
		{
			request.setAttribute("titre", "Erreur");
			request.setAttribute("erreur", "La session n'existe pas");
			getServletContext().getRequestDispatcher("/Vues/Erreur\\erreur.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("titre", "Profil");
			getServletContext().getRequestDispatcher("/Vues/Compte\\profil.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
