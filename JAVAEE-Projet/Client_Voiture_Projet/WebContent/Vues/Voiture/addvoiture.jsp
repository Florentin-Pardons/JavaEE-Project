<%@ page import="javabean.Modele" %>
<%@ page import="java.util.List;" %>

<% List<Modele> listmodele = (List<Modele>)request.getAttribute("listmodele"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Ajouter une Modele</h1>
<form action="/Client_Voiture_Projet/GestionModele" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Couleur : </td>
		    <td><input type="text" name="couleur" id="couleur" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Carburant : </td>
		    <td><input type="text" name="carburant" id="carburant" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Boite de Vitesse : </td>
		    <td><input type="text" name="boiteVitesse" id="boiteVitesse" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Nombre de Kilometre : </td>
		    <td><input type="text" name="nbkm" id="nbkm" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Dispo : </td>
		    <td><input type="text" name="age" id="age" value="" size="20"/></td>
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
		    <td colspan="2" align="center"><input type="submit" name="insert2" id="insert2" value="Ajouter"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>