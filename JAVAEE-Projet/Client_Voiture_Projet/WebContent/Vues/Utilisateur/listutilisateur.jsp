<%@ page import="javabean.Utilisateur" %>
<%@ page import="java.util.List;" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Liste des utilisateurs</h1>
<table class="table">
    <tbody>        
        <% List<Utilisateur> listutilisateur = (List<Utilisateur>)request.getAttribute("listutilisateur");

			for(Utilisateur user : listutilisateur)
			{
				out.println("<tr>");
					out.println("<td>" + user.getId() + "</td>");
					out.println("<td>" + user.getMail() + "</td>");
					out.println("<td>" + user.getNom() + "</td>");
					out.println("<td>" + user.getPrenom() + "</td>");
					out.println("<td>" + user.getDateNaissance() + "</td>");
					out.println("<td>" + user.getAdresse() + "</td>");
					out.println("<td>" + user.isRole() + "</td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionUtilisateur\" method=\"GET\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + user.getId() + "\"><input type=\"submit\" name=\"update\" id=\"update\" value=\"Modifier\"/></form> </td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionUtilisateur\" method=\"GET\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + user.getId() + "\"><input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Supprimer\"/></form> </td>");
				out.println("</tr>");
			}
		%>
    </tbody>
</table>

 <br/>
 <br/>
 <p><form action="/Client_Voiture_Projet/GestionUtilisateur" method="GET"><input type="submit" name="insert" id="insert" value="Ajouter"/></form></p>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
