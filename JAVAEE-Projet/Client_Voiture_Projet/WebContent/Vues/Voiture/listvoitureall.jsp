<%@ page import="javabean.Voiture" %>
<%@ page import="java.util.List;" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Liste des voitures</h2>
<table class="table">
	<thead>
    	<tr>
           	<th>Id</th>
           	<th>Couleur</th>
           	<th>Carburant</th>
           	<th>Boite de Vitesse</th>
           	<th>Nb de KM</th>
           	<th>Age</th>
           	<th>Dispo</th>
           	<th>Modele</th>
           	<th>Detail</th>
       	</tr>
   	</thead>
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
					out.println("<td> <form action=\"/Client_Voiture_Projet/ListVoiture\" method=\"POST\"><input id=\"id\" name=\"id\" type=\"hidden\" value=\"" + voi.getId() + "\"><input type=\"submit\" name=\"detail\" id=\"detail\" value=\"Detail\"/></form> </td>");
				out.println("</tr>");
			}
		%>
    </tbody>
</table>

 <br/>
 <br/>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
