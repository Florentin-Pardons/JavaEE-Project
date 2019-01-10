<%@ page import="javabean.Categorie" %>

<% Categorie cat = (Categorie)request.getAttribute("categorie"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Modifier Categorie</h2>
<form action="/Client_Voiture_Projet/GestionCategorie" method="POST">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Id : </td>
		    <td><input type="text" name="id" id="id" value="<% out.println(cat.getId()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="<% out.println(cat.getNom()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Description : </td>
		    <td><input type="text" name="description" id="description" value="<% out.println(cat.getDescription()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="update2" id="update2" value="Modifier"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>