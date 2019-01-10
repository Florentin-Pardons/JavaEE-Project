package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.Utilisateur;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute("titre", "Login");
			getServletContext().getRequestDispatcher("/Vues/Compte\\login.jsp").forward(request, response);			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		String mp = request.getParameter("mp");
		Utilisateur user = new Utilisateur();
		
		if(mail != null && mp != null)
		{
			user = user.verif(mail, mp);
			if(user != null)
			{
				HttpSession session=request.getSession();
				if(!session.isNew()) { 
					session.invalidate(); 
					session=request.getSession(); 
				}
				
				if(user.isRole())
				{
					session.setAttribute("role", "admin");
					session.setAttribute("utilisateur", user);
				}
				else
				{
					session.setAttribute("role", "user");
					session.setAttribute("utilisateur", user);
				}
				
				request.setAttribute("titre", "Accueil");
				getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("titre", "Erreur");
				request.setAttribute("erreur", "Mauvais Login/MP");
				getServletContext().getRequestDispatcher("/Vues/Erreur\\erreur.jsp").forward(request, response);
			}
		}
		else
		{
			request.setAttribute("titre", "Erreur");
			request.setAttribute("erreur", "Champs vide");
			getServletContext().getRequestDispatcher("/Vues/Erreur\\erreur.jsp").forward(request, response);
		}
	}
}
