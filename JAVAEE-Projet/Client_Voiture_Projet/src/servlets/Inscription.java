package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Utilisateur;


@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Inscription() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		if(request.getParameter("insert") != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			
			String mail = request.getParameter("mail");
			String mp = request.getParameter("mp");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			Date datenaissance = null;
			try {
				datenaissance = formatter.parse(request.getParameter("datenaissance"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String adresse = request.getParameter("adresse");
			
			//Creer
			Utilisateur utilisateur = new Utilisateur(mail, mp, nom, prenom, datenaissance, adresse, false);
			utilisateur.Creer();
			
			request.setAttribute("titre", "Gestion des Utilisateurs");
			getServletContext().getRequestDispatcher("/Vues/Compte\\login.jsp").forward(request, response);
			//response.sendRedirect("/Client_Voiture_Projet/GestionUtilisateur");
		}
		else 
		{
			request.setAttribute("titre", "Inscription");
			getServletContext().getRequestDispatcher("/Vues/Compte\\inscription.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
