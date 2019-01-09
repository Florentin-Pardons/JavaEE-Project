<%@ page import="javabean.Marque" %>
<%@ page import="javabean.Categorie" %>
<%@ page import="java.util.List;" %>

<% List<Categorie> listcategorie = (List<Categorie>)request.getAttribute("listcategorie"); %>
<% List<Marque> listmarque = (List<Marque>)request.getAttribute("listmarque"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Rechercher Voitures</h2>
<form action="/Client_Voiture_Projet/RechercheVoiture" method="GET">
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
			<td> Marque : </td>
       		<td>
       			<select name="marque" id="marque">
       			<%
       				out.println("<option value=" + 0 + ">//</option>");
					for(Marque mar : listmarque)
					{
						out.println("<option value=" + mar.getId() + ">" + mar.getNom() + "</option>");
					}
				%>
       			</select>
       		</td>
       	</tr>
       	<tr>
       		<td> Categorie : </td>
       		<td>
       			<select name="categorie" id="categorie">
       			<%
       				out.println("<option value=" + 0 + ">//</option>");
					for(Categorie cat : listcategorie)
					{
						out.println("<option value=" + cat.getId() + ">" + cat.getNom() + "</option>");
					}
				%>
       			</select>
       		</td>
       	</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="recherche" id="recherche" value="Chercher"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>