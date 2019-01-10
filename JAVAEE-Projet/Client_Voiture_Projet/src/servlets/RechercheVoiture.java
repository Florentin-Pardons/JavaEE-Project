package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.Categorie;
import javabean.Marque;
import javabean.Voiture;

@WebServlet("/RechercheVoiture")
public class RechercheVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RechercheVoiture() {
    	
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
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			request.setAttribute("titre", "Recherche de Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\recherchevoiture.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recherche d'une voiture
		if(request.getParameter("recherche") != null)
		{
			//Recupere les champs de la vue
			String couleur = request.getParameter("couleur");
			String carburant = request.getParameter("carburant");
			String boiteVitesse = request.getParameter("boiteVitesse");
			int marque = Integer.parseInt(request.getParameter("marque"));
			int categorie = Integer.parseInt(request.getParameter("categorie"));
			
			if(couleur == "")
				couleur = null;
			if(carburant == "")
				carburant = null;
			if(boiteVitesse == "")
				boiteVitesse = null;
			
			//Chercher		
			List<Voiture> listvoi = Voiture.Chercher(couleur, carburant, boiteVitesse, marque, categorie);
			request.setAttribute("listvoiture", listvoi);
			
			//Redirection
			request.setAttribute("titre", "Liste des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoitureall.jsp").forward(request, response);
		}
	}

}