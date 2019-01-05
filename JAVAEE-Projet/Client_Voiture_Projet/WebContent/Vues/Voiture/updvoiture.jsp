<%@ page import="javabean.Voiture" %>
<%@ page import="javabean.Modele" %>
<%@ page import="java.util.List;" %>

<% Voiture voi = (Voiture)request.getAttribute("voiture"); %>
<% List<Modele> listmodele = (List<Modele>)request.getAttribute("listmodele"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Ajouter une Voiture</h1>
<form action="/Client_Voiture_Projet/GestionVoiture" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Id : </td>
		    <td><input type="text" name="id" id="id" value="<% out.println(voi.getId()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Couleur : </td>
		    <td><input type="text" name="couleur" id="couleur" value="<% out.println(voi.getCouleur()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Carburant : </td>
		    <td><input type="text" name="carburant" id="carburant" value="<% out.println(voi.getCarburant()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Boite de Vitesse : </td>
		    <td><input type="text" name="boiteVitesse" id="boiteVitesse" value="<% out.println(voi.getBoiteVitesse()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Nombre de Kilometre : </td>
		    <td><input type="text" name="nbkm" id="nbkm" value="<% out.println(voi.getNbkm()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Dispo : </td>
		    <td><input type="text" name="age" id="age" value="<% out.println(voi.isDispo()); %>" size="20"/></td>
		</tr>
       	<tr>
       		<td> Modele : </td>
       		<td>
       			<select name="modele" id="modele">
       			<%
					for(Modele mod : listmodele)
					{
						out.println("<option value=" + mod.getId() + ">" + mod.getNom() + "</option>");
					}
				%>
       			</select>
       		</td>
       	</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="update2" id="update2" value="Modifier"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>