package servlets;

import java.io.IOException;
import java.util.Date;
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
		
		if(request.getParameter("detail") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			//Detail		
			//Voiture voi = new Voiture();
			//voi = voi.Trouver(id);
			Voiture voi = new Voiture(1, "jaune", "essence", "auto", 1500, 15, true, new Modele(1, "c4", 55, 13, new Marque(1,"test", new Date(01/01/1990),"sdsf"), new Categorie(1, "4x4", "blabla")));
			request.setAttribute("voiture", voi);
			
			//Redirection
			request.setAttribute("titre", "Detail Voiture");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\detailvoiture.jsp").forward(request, response);
		}
		
		else
		{
			List<Voiture> listvoi = Voiture.List();
			/*
			for(int i = 0; i < listvoi.size(); i++)
			{
				Categorie cat = new Categorie();
				cat.Trouver(listvoi.get(i).getModele().getCategorie().getId());
				
				Marque mar = new Marque();
				mar.Trouver(listvoi.get(i).getModele().getMarque().getId());
				
				listvoi.get(i).getModele().setCategorie(cat);
				listvoi.get(i).getModele().setMarque(mar);
			}*/
			request.setAttribute("listvoiture", listvoi);
			
			request.setAttribute("titre", "Liste des Voitures");
			getServletContext().getRequestDispatcher("/Vues/Voiture\\listvoitureall.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}