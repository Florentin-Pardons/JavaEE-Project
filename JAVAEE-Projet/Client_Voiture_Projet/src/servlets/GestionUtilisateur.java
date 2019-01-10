package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.Utilisateur;


@WebServlet("/GestionUtilisateur")
public class GestionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public GestionUtilisateur() {
    	
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
			request.setAttribute("titre", "Ajouter Utilisateur");
			getServletContext().getRequestDispatcher("/Vues/Utilisateur\\addutilisateur.jsp").forward(request, response);
		}
		else
		{
			//Renvoie vers la page de gestion des utilisateurs
			List<Utilisateur> listuser = Utilisateur.List();
			request.setAttribute("listutilisateur", listuser);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Utilisateurs");
			getServletContext().getRequestDispatcher("/Vues/Utilisateur\\listutilisateur.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creation d'un utilisateur
		if(request.getParameter("insert2") != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			//Recupere les champs de la vue
			String mail = request.getParameter("mail");
			String mp = request.getParameter("mp");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			Date datenaissance = null;
			try {
				datenaissance = formatter.parse(request.getParameter("datenaissance"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String adresse = request.getParameter("adresse");
			boolean role = Integer.parseInt(request.getParameter("role"))!=0?true:false;
			
			//Creer
			Utilisateur utilisateur = new Utilisateur(mail, mp, nom, prenom, datenaissance, adresse, role);
			utilisateur.Creer();
			
			//Update liste
			List<Utilisateur> listuser = Utilisateur.List();
			request.setAttribute("listutilisateur", listuser);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Utilisateurs");
			getServletContext().getRequestDispatcher("/Vues/Utilisateur\\listutilisateur.jsp").forward(request, response);
		}
		
		//Redirection vers la page modifier categorie
		if(request.getParameter("update") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Utilisateur user = new Utilisateur();
			user = user.Trouver(id); //recupere l'utilisateur à afficher dans la page
			
			request.setAttribute("utilisateur", user);
			
			request.setAttribute("titre", "Modifier Utilisateur");
			getServletContext().getRequestDispatcher("/Vues/Utilisateur\\updutilisateur.jsp").forward(request, response);
		}
				
		//Modifier les utilisateurs
		if(request.getParameter("update2") != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			//Recupere les champs de la vue
			int id = Integer.parseInt(request.getParameter("id"));
			String mail = request.getParameter("mail");
			String mp = request.getParameter("mp");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			Date datenaissance = null;
			try {
				datenaissance = formatter.parse(request.getParameter("datenaissance"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String adresse = request.getParameter("adresse");
			boolean role = Integer.parseInt(request.getParameter("role"))!=0?true:false;
			
			//Modification
			Utilisateur utilisateur = new Utilisateur(id, mail, mp, nom, prenom, datenaissance, adresse, role);
			utilisateur.Update();
			
			//Update la liste
			List<Utilisateur> listuser = Utilisateur.List();
			request.setAttribute("listutilisateur", listuser);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Utilisateurs");
			getServletContext().getRequestDispatcher("/Vues/Utilisateur\\listutilisateur.jsp").forward(request, response);
		}
		
		//Supprimer un utilisateur
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Utilisateur mar = new Utilisateur();
			mar = mar.Trouver(id); 
			mar.Delete(); //Supression
			
			//Update la liste
			List<Utilisateur> listuser = Utilisateur.List();
			request.setAttribute("listutilisateur", listuser);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Utilisateurs");
			getServletContext().getRequestDispatcher("/Vues/Utilisateur\\listutilisateur.jsp").forward(request, response);
		}
	}

}
