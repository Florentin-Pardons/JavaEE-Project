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
import javabean.Modele;

@WebServlet("/GestionModele")
public class GestionModele extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public GestionModele() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Verifie l'utilisateur connecté
		HttpSession session=request.getSession(); 
		if(session == null || !session.getAttribute("role").equals("admin"))
		{
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page Modifier
		if(request.getParameter("update") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Modele mod = new Modele();
			mod.Trouver(id);
			
			request.setAttribute("modele", mod);
			
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			request.setAttribute("titre", "Modifier Modele");
			getServletContext().getRequestDispatcher("/Vues/Modele\\updmodele.jsp").forward(request, response);
		}
		
		if(request.getParameter("update2") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			int nbporte = Integer.parseInt(request.getParameter("nbporte"));
			int volumecoffre = Integer.parseInt(request.getParameter("volumecoffre"));
			int marque = Integer.parseInt(request.getParameter("marque"));
			int categorie = Integer.parseInt(request.getParameter("categorie"));
			
			//Modification
			Marque mar = new Marque();
			mar.Trouver(marque);
			
			Categorie cat = new Categorie();
			cat.Trouver(categorie);
			
			Modele modele = new Modele(id, nom, nbporte, volumecoffre, mar, cat);
			modele.Update();
			
			//Redirection
			request.setAttribute("titre", "Gestion des Modeles");
			getServletContext().getRequestDispatcher("/Vues/Modele\\listmodele.jsp").forward(request, response);
			//response.sendRedirect("/Client_Voiture_Projet/GestionModele");
		}
		
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Modele mar = new Modele();
			mar.Trouver(id);
			
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page Creer
		if(request.getParameter("insert") != null)
		{
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			request.setAttribute("titre", "Ajouter Modele");
			getServletContext().getRequestDispatcher("/Vues/Modele\\addmodele.jsp").forward(request, response);
		}
		
		if(request.getParameter("insert2") != null)
		{
			String nom = request.getParameter("nom");
			int nbporte = Integer.parseInt(request.getParameter("nbporte"));
			int volumecoffre = Integer.parseInt(request.getParameter("volumecoffre"));
			int marque = Integer.parseInt(request.getParameter("marque"));
			int categorie = Integer.parseInt(request.getParameter("categorie"));
			
			//Creer
			Marque mar = new Marque();
			mar.Trouver(marque);
			
			Categorie cat = new Categorie();
			cat.Trouver(categorie);
			
			Modele modele = new Modele(nom, nbporte, volumecoffre, mar, cat);
			modele.Creer();
			
			request.setAttribute("titre", "Gestion des Modeles");
			getServletContext().getRequestDispatcher("/Vues/Modele\\listmodele.jsp").forward(request, response);
		}
		else
		{
			List<Modele> listmar = Modele.List();
			request.setAttribute("listmodele", listmar);
			
			request.setAttribute("titre", "Gestion des Modeles");
			getServletContext().getRequestDispatcher("/Vues/Modele\\listmodele.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
