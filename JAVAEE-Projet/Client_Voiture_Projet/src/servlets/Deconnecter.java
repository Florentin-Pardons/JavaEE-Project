package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Deconnecter")
public class Deconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Deconnecter() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		if(!session.isNew()) { 
			session.invalidate(); 
			session=request.getSession(); 
		}
		
		session.setAttribute("role", "defaut");
		session.setAttribute("utilisateur", null);
		
		request.setAttribute("titre", "Accueil");
		getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
