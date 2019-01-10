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
		
		//Verifie que l'utilisateur connecté est un admin
		HttpSession session=request.getSession(); 
		if(session == null || !session.getAttribute("role").equals("admin"))
		{
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page creer categorie
		if(request.getParameter("insert") != null)
		{
			request.setAttribute("titre", "Ajouter Categorie");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\addcategorie.jsp").forward(request, response);
		}
		else
		{
			//Renvoie vers la page de gestion des categories
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creation d'une categorie
		if(request.getParameter("insert2") != null)
		{
			//Recupere les champs de la vue
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			
			//Creer
			Categorie categorie = new Categorie();
			categorie.setNom(nom);
			categorie.setDescription(description);
			categorie.Creer();
			
			//Update la liste
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
		}
		
		//Redirection vers la page modifier categorie
		if(request.getParameter("update") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Categorie cat = new Categorie();
			cat = cat.Trouver(id); //recupere la categorie à afficher dans la page
			
			request.setAttribute("categorie", cat);
			
			request.setAttribute("titre", "Modifier Categorie");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\updcategorie.jsp").forward(request, response);
		}
				
		//Modifier la categorie
		if(request.getParameter("update2") != null)
		{
			//Recupere les champs de la vue
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			
			//Modification
			Categorie categorie = new Categorie(id, nom, description);
			categorie.Update();
			
			
			//Update la liste
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
		}
		
		//Supprimer une categorie
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Categorie cat = new Categorie();
			cat = cat.Trouver(id);
			cat.Delete(); //Supression
			
			//Update la liste
			List<Categorie> listcat = Categorie.List();
			request.setAttribute("listcategorie", listcat);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Categories");
			getServletContext().getRequestDispatcher("/Vues/Categorie\\listcategorie.jsp").forward(request, response);
		}
	}

}
