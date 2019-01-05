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


@WebServlet("/GestionCategorie")
public class GestionCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public GestionCategorie() {
    	
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
			Categorie cat = new Categorie();
			cat.Trouver(id);
			
			request.setAttribute("categorie", cat);
			
			request.setAttribute("titre", "Modifier Categorie");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\updcategorie.jsp").forward(request, response);
		}
		
		if(request.getParameter("update2") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			
			//Modification
			Categorie categorie = new Categorie(id, nom, description);
			categorie.Update();
			
			//Update la liste
			List<Categorie> listmar = Categorie.List();
			request.setAttribute("listcategorie", listmar);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
			//response.sendRedirect("/Client_Voiture_Projet/GestionCategorie");
		}
		
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Categorie mar = new Categorie();
			mar.Trouver(id);
			
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page Creer
		if(request.getParameter("insert") != null)
		{
			request.setAttribute("titre", "Ajouter Categorie");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\addcategorie.jsp").forward(request, response);
		}
		
		if(request.getParameter("insert2") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			
			//Creer
			Categorie categorie = new Categorie();
			categorie.setId(id);
			categorie.setNom(nom);
			categorie.setDescription(description);
			categorie.Creer();
			
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
			//response.sendRedirect("/Client_Voiture_Projet/GestionCategorie");
		}
		else
		{
			List<Categorie> listmar = Categorie.List();
			request.setAttribute("listcategorie", listmar);
			
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
