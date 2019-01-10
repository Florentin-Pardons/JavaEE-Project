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

import javabean.Marque;


@WebServlet("/GestionMarque")
public class GestionMarque extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public GestionMarque() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Verifie que lutilisateur connecté est un admin
		HttpSession session=request.getSession(); 
		if(session == null || !session.getAttribute("role").equals("admin"))
		{
			request.setAttribute("titre", "Accueil");
			getServletContext().getRequestDispatcher("/Vues/Accueil\\accueil.jsp").forward(request, response);
		}
		
		//Redirection vers la page creer marque
		if(request.getParameter("insert") != null)
		{
			request.setAttribute("titre", "Ajouter Marque");
			getServletContext().getRequestDispatcher("/Vues/Marque\\addmarque.jsp").forward(request, response);
		}
		else
		{
			//Renvoie vers la page de gestion des marques
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			request.setAttribute("titre", "Gestion des Marques");
			getServletContext().getRequestDispatcher("/Vues/Marque\\listmarque.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creation d'une marque
		if(request.getParameter("insert2") != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			String nom = request.getParameter("nom");
			Date date = null;
			try {
				date = formatter.parse(request.getParameter("datecreation"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String paysorigine = request.getParameter("paysorigine");
			
			Marque marque = new Marque();
			marque.setNom(nom);
			marque.setDateCrea(date);
			marque.setPaysOrigine(paysorigine);
			marque.Creer();
			
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			request.setAttribute("titre", "Gestion des Marques");
			getServletContext().getRequestDispatcher("/Vues/Marque\\listmarque.jsp").forward(request, response);
		}
		
		//Redirection vers la page modifier marque
		if(request.getParameter("update") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Marque mar = new Marque();
			mar = mar.Trouver(id); //recupere la marque à afficher dans la page
			
			request.setAttribute("marque", mar);
			
			request.setAttribute("titre", "Modifier Marque");
			getServletContext().getRequestDispatcher("/Vues/Marque\\updmarque.jsp").forward(request, response);
		}
		
		//Modifier la marque
		if(request.getParameter("update2") != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			Date date = null;
			try {
				date = formatter.parse(request.getParameter("datecreation"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String paysorigine = request.getParameter("paysorigine");
			
			//Modification
			Marque marque = new Marque(id, nom, date, paysorigine);
			marque.Update();
			
			//Update la liste
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Marques");
			getServletContext().getRequestDispatcher("/Vues/Marque\\listmarque.jsp").forward(request, response);
		}
		
		//Supprimer la marque
		if(request.getParameter("delete") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Marque mar = new Marque();
			mar = mar.Trouver(id);
			mar.Delete();
			
			//Update la liste
			List<Marque> listmar = Marque.List();
			request.setAttribute("listmarque", listmar);
			
			//Redirection
			request.setAttribute("titre", "Gestion des Marques");
			getServletContext().getRequestDispatcher("/Vues/Marque\\listmarque.jsp").forward(request, response);
		}
	}

}
