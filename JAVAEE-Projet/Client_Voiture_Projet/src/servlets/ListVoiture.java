package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.Voiture;


@WebServlet("/ListVoiture")
public class ListVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ListVoiture() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Verifie l'utilisateur connecté
		HttpSession session=request.getSession(); 
		if(session == null || !session.getAttribute("role").equals("user"))
		{
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		else
		{
			List<Voiture> listvoi = Voiture.List();
			
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Liste des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoitureall.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Afficher la vue des details d'une voiture
		if(request.getParameter("detail") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
				
			Voiture voi = new Voiture();
			voi = voi.Trouver(id); //Recherche de la voiture a afficher
			
			request.setAttribute("voiture", voi);
			
			//Redirection
			request.setAttribute("titre", "Detail Voiture");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\detailvoiture.jsp").forward(request, response);
		}
	}

}