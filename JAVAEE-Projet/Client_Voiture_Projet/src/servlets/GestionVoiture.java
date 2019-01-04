package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.Modele;
import javabean.Voiture;

@WebServlet("/GestionVoiture")
public class GestionVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public GestionVoiture() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Verifie l'utilisateur connecté
		HttpSession session=request.getSession(); 
		if(session == null || !session.getAttribute("role").equals("user"))
		{
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page Modifier
		if(request.getParameter("update") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Voiture mod = new Voiture();
			mod.Trouver(id);
			
			request.setAttribute("voiture", mod);
			
			List<Modele> listmod = Modele.List();
			request.setAttribute("listmodelee", listmod);
			
			request.setAttribute("titre", "Modifier Voiture");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\updvoiture.jsp").forward(request, response);
		}
		
		if(request.getParameter("update2") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String couleur = request.getParameter("couleur");
			String carburant = request.getParameter("carburant");
			String boiteVitesse = request.getParameter("boiteVitesse");
			int nbkm = Integer.parseInt(request.getParameter("nbkm"));
			int age = Integer.parseInt(request.getParameter("age"));
			boolean dispo = Integer.parseInt(request.getParameter("dispo"))!=0?true:false;
			int modele = Integer.parseInt(request.getParameter("modele"));
			
			//Modification			
			Modele mod = new Modele();
			mod.Trouver(modele);
			
			Voiture voiture = new Voiture(id, couleur, carburant, boiteVitesse, nbkm, age, dispo, mod);
			voiture.Update();
			
			//Redirection
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		}
		
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Voiture mar = new Voiture();
			mar.Trouver(id);
			
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page Creer
		if(request.getParameter("insert") != null)
		{
			List<Modele> listmod = Modele.List();
			request.setAttribute("listmodele", listmod);
			
			request.setAttribute("titre", "Ajouter Voiture");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\addvoiture.jsp").forward(request, response);
		}
		
		if(request.getParameter("insert2") != null)
		{
			String couleur = request.getParameter("couleur");
			String carburant = request.getParameter("carburant");
			String boiteVitesse = request.getParameter("boiteVitesse");
			int nbkm = Integer.parseInt(request.getParameter("nbkm"));
			int age = Integer.parseInt(request.getParameter("age"));
			boolean dispo = Integer.parseInt(request.getParameter("dispo"))!=0?true:false;
			int modele = Integer.parseInt(request.getParameter("modele"));
			
			//Creer			
			Modele mod = new Modele();
			mod.Trouver(modele);
			
			Voiture voiture = new Voiture(couleur, carburant, boiteVitesse, nbkm, age, dispo, mod);
			voiture.Creer();
			
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		}
		else
		{
			List<Voiture> listvoi = Voiture.List();
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
