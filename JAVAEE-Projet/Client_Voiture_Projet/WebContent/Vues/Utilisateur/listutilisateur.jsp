<%@ page import="javabean.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Liste des utilisateurs</h2>
<table class="table">
	<thead>
    	<tr>
           	<th>Id</th>
           	<th>Mail</th>
           	<th>Nom</th>
           	<th>Prenom</th>
           	<th>Date de Naissance</th>
           	<th>Adresse</th>
           	<th>Role</th>
           	<th>Modifier</th>
           	<th>Supprimer</th>
       	</tr>
   	</thead>
    <tbody>        
        <% List<Utilisateur> listutilisateur = (List<Utilisateur>)request.getAttribute("listutilisateur");

        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	
			for(Utilisateur user : listutilisateur)
			{
				out.println("<tr>");
					out.println("<td>" + user.getId() + "</td>");
					out.println("<td>" + user.getMail() + "</td>");
					out.println("<td>" + user.getNom() + "</td>");
					out.println("<td>" + user.getPrenom() + "</td>");
					out.println("<td>" + formatter.format(user.getDateNaissance()) + "</td>");
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
