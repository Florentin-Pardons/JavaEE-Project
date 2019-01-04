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
		
		HttpSession session=request.getSession(false); 
		if(session == null)
		{
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		else
		{
			List<Voiture> listvoi = Voiture.List();
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Liste des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}