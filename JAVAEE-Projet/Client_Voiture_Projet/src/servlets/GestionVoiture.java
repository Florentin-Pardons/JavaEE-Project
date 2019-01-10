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
import javabean.Utilisateur;
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
		
		//Redirection vers la page creer voiture
		if(request.getParameter("insert") != null)
		{
			List<Modele> listmod = Modele.List();
			request.setAttribute("listmodele", listmod);
			
			request.setAttribute("titre", "Ajouter Voiture");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\addvoiture.jsp").forward(request, response);
		}
		else
		{
			//Renvoie vers la page de gestion des voitures
			Utilisateur user = (Utilisateur)session.getAttribute("utilisateur");
			
			List<Voiture> listvoi = Voiture.List(user);
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(); 
		
		//Creation d'une voiture
		if(request.getParameter("insert2") != null)
		{
			//Recupere les champs de la vue
			String couleur = request.getParameter("couleur");
			String carburant = request.getParameter("carburant");
			String boiteVitesse = request.getParameter("boiteVitesse");
			int nbkm = Integer.parseInt(request.getParameter("nbkm"));
			int age = Integer.parseInt(request.getParameter("age"));
			boolean dispo = Integer.parseInt(request.getParameter("dispo"))!=0?true:false;
			int modele = Integer.parseInt(request.getParameter("modele"));
			Utilisateur user = (Utilisateur)session.getAttribute("utilisateur");
			
			//Creer			
			Modele mod = new Modele();
			mod = mod.Trouver(modele);
			
			//Update la listes
			Voiture voiture = new Voiture(couleur, carburant, boiteVitesse, nbkm, age, dispo, mod);
			voiture.setUtilisateur(user);
			voiture.Creer();
			
			//Redirection
			Utilisateur user2 = (Utilisateur)session.getAttribute("utilisateur");
			
			List<Voiture> listvoi = Voiture.List(user2);
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		}
		
		//Redirection vers la page modifier voiture
		if(request.getParameter("update") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Voiture voi = new Voiture();
			voi = voi.Trouver(id); //recupere la voiture à afficher dans la page
			
			request.setAttribute("voiture", voi);
			
			List<Modele> listmod = Modele.List();
			request.setAttribute("listmodele", listmod);
			
			request.setAttribute("titre", "Modifier Voiture");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\updvoiture.jsp").forward(request, response);
		}
		
		//Modification de la voiture
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
			mod = mod.Trouver(modele);
			
			Voiture voiture = new Voiture(id, couleur, carburant, boiteVitesse, nbkm, age, dispo, mod);
			voiture.Update();
			
			//Redirection
			Utilisateur user2 = (Utilisateur)session.getAttribute("utilisateur");
			
			List<Voiture> listvoi = Voiture.List(user2);
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		}
		
		//Suppression d' une voiture
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Voiture mar = new Voiture();
			mar = mar.Trouver(id);
			mar.Delete();
			
			//Redirection
			Utilisateur user2 = (Utilisateur)session.getAttribute("utilisateur");
			
			List<Voiture> listvoi = Voiture.List(user2);
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Gestion des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoiture.jsp").forward(request, response);
		}
	}

}
