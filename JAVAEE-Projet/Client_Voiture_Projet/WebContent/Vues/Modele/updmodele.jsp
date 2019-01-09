<%@ page import="javabean.Modele" %>
<%@ page import="javabean.Marque" %>
<%@ page import="javabean.Categorie" %>
<%@ page import="java.util.List;" %>

<% Modele mod = (Modele)request.getAttribute("modele"); %>
<% List<Categorie> listcategorie = (List<Categorie>)request.getAttribute("listcategorie"); %>
<% List<Marque> listmarque = (List<Marque>)request.getAttribute("listmarque"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Modifier une Modele</h1>
<form action="/Client_Voiture_Projet/GestionModele" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Id : </td>
		    <td><input type="text" name="id" id="id" value="<% out.println(mod.getId()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="<% out.println(mod.getNom()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Nombre de porte : </td>
		    <td><input type="text" name="nbporte" id="nbporte" value="<% out.println(mod.getNbPorte()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Volume du coffre : </td>
		    <td><input type="text" name="volumecoffre" id="volumecoffre" value="<% out.println(mod.getVolumeCoffre()); %>" size="20"/></td>
		</tr>
		<tr>
			<td> Marque : </td>
       		<td>
       			<select name="marque" id="marque">
       			<%
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
					for(Categorie cat : listcategorie)
					{
						out.println("<option value=" + cat.getId() + ">" + cat.getNom() + "</option>");
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