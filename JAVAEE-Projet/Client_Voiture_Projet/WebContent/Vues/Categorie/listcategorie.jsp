<%@ page import="javabean.Categorie" %>
<%@ page import="java.util.List;" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Liste des categories</h1>
<table class="table">
    <tbody>        
        <% List<Categorie> listcategorie = (List<Categorie>)request.getAttribute("listcategorie");

			for(Categorie cat : listcategorie)
			{
				out.println("<tr>");
					out.println("<td>" + cat.getId() + "</td>");
					out.println("<td>" + cat.getNom() + "</td>");
					out.println("<td>" + cat.getDescription() + "</td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionCategorie\" method=\"GET\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + cat.getId() + "\"><input type=\"submit\" name=\"update\" id=\"update\" value=\"Modifier\"/></form> </td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionCategorie\" method=\"GET\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + cat.getId() + "\"><input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Supprimer\"/></form> </td>");
				out.println("</tr>");
			}
		%>
    </tbody>
</table>

 <br/>
 <br/>
 <p><form action="/Client_Voiture_Projet/GestionCategorie" method="GET"><input type="submit" name="insert" id="insert" value="Ajouter"/></form></p>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
