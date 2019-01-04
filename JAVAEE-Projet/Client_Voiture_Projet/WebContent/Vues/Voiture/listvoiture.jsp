<%@ page import="javabean.Voiture" %>
<%@ page import="java.util.List;" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Liste des voitures</h1>
<table class="table">
    <tbody>        
        <% List<Voiture> listvoiture = (List<Voiture>)request.getAttribute("listvoiture");

			for(Voiture voi : listvoiture)
			{
				out.println("<tr>");
					out.println("<td>" + voi.getId() + "</td>");
					out.println("<td>" + voi.getCouleur() + "</td>");
					out.println("<td>" + voi.getCarburant() + "</td>");
					out.println("<td>" + voi.getBoiteVitesse() + "</td>");
					out.println("<td>" + voi.getNbkm() + "</td>");
					out.println("<td>" + voi.getAge() + "</td>");
					out.println("<td>" + voi.isDispo() + "</td>");
					out.println("<td>" + voi.getModele().getNom() + "</p>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionVoiture\" method=\"GET\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + voi.getId() + "\"><input type=\"submit\" name=\"update\" id=\"update\" value=\"Modifier\"/></form> </td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionVoiture\" method=\"GET\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + voi.getId() + "\"><input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Supprimer\"/></form> </td>");
				out.println("</tr>");
			}
		%>
    </tbody>
</table>

 <br/>
 <br/>
 <p><form action="/Client_Voiture_Projet/GestionVoiture" method="GET"><input type="submit" name="insert" id="insert" value="Ajouter"/></form></p>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
