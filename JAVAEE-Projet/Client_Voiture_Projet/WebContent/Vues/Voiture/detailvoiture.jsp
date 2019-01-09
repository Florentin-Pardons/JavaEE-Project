<%@ page import="javabean.Voiture" %>
<%@ page import="java.util.List;" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Detail de la voiture</h2>
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
       	</tr>
   	</thead>
    <tbody>        
        <% Voiture voi = (Voiture)request.getAttribute("voiture");

			out.println("<tr>");
				out.println("<td>" + voi.getId() + "</td>");
				out.println("<td>" + voi.getCouleur() + "</td>");
				out.println("<td>" + voi.getCarburant() + "</td>");
				out.println("<td>" + voi.getBoiteVitesse() + "</td>");
				out.println("<td>" + voi.getNbkm() + "</td>");
				out.println("<td>" + voi.getAge() + "</td>");
				out.println("<td>" + voi.isDispo() + "</td>");
				out.println("<td>" + voi.getModele().getNom() + "</p>");
			out.println("</tr>");
			
		%>
    </tbody>
</table>

 <br/>
 <br/>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
