<%@ page import="javabean.Modele" %>
<%@ page import="java.util.List;" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Liste des modeles</h1>
<table class="table">
	<thead>
    	<tr>
           	<th>Id</th>
           	<th>Nom</th>
           	<th>Nb Portes</th>
           	<th>Volume Coffre</th>
           	<th>Marque</th>
           	<th>Categorie</th>
           	<th>Modifier</th>
           	<th>Supprimer</th>
       	</tr>
   	</thead>
    <tbody>        
        <% List<Modele> listmodele = (List<Modele>)request.getAttribute("listmodele");

			for(Modele mod : listmodele)
			{
				out.println("<tr>");
					out.println("<td>" + mod.getId() + "</td>");
					out.println("<td>" + mod.getNom() + "</td>");
					out.println("<td>" + mod.getNbPorte() + "</td>");
					out.println("<td>" + mod.getVolumeCoffre() + "</td>");
					out.println("<td>" + mod.getMarque().getNom() + "</td>");
					out.println("<td>" + mod.getCategorie().getNom() + "</td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionModele\" method=\"POST\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + mod.getId() + "\"><input type=\"submit\" name=\"update\" id=\"update\" value=\"Modifier\"/></form> </td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionModele\" method=\"POST\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + mod.getId() + "\"><input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Supprimer\"/></form> </td>");
				out.println("</tr>");
			}
		%>
    </tbody>
</table>

 <br/>
 <br/>
 <p><form action="/Client_Voiture_Projet/GestionModele" method="GET"><input type="submit" name="insert" id="insert" value="Ajouter"/></form></p>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
