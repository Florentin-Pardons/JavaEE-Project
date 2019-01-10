<%@ page import="javabean.Marque" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Liste des marques</h2>
<table class="table">
	<thead>
    	<tr>
           	<th>Id</th>
           	<th>Nom</th>
           	<th>Date Creation</th>
           	<th>Origine</th>
           	<th>Modifier</th>
           	<th>Supprimer</th>
       	</tr>
   	</thead>
    <tbody>        
        <% List<Marque> listmarque = (List<Marque>)request.getAttribute("listmarque");
        
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	
			for(Marque mar : listmarque)
			{
				out.println("<tr>");
					out.println("<td>" + mar.getId() + "</td>");
					out.println("<td>" + mar.getNom() + "</td>");
					out.println("<td>" + formatter.format(mar.getDateCrea()) + "</td>");
					out.println("<td>" + mar.getPaysOrigine() + "</td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionMarque\" method=\"POST\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + mar.getId() + "\"><input type=\"submit\" name=\"update\" id=\"update\" value=\"Modifier\"/></form> </td>");
					out.println("<td> <form action=\"/Client_Voiture_Projet/GestionMarque\" method=\"POST\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + mar.getId() + "\"><input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Supprimer\"/></form> </td>");
				out.println("</tr>");
			}
		%>
    </tbody>
</table>

 <br/>
 <br/>
 <p><form action="/Client_Voiture_Projet/GestionMarque" method="GET"><input type="submit" name="insert" id="insert" value="Ajouter"/></form></p>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
