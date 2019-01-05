<%@ page import="javabean.Utilisateur" %>

<% Utilisateur user = (Utilisateur)request.getAttribute("utilisateur"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h1>Modifier Utilisateur</h1>
<form action="/Client_Voiture_Projet/GestionUtilisateur" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Id : </td>
		    <td><input type="text" name="id" id="id" value="<% out.println(user.getId()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Mail : </td>
		    <td><input type="text" name="mail" id="mail" value="<% out.println(user.getMail()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Mot de passe : </td>
		    <td><input type="text" name="mp" id="mp" value="<% out.println(user.getMp()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="<% out.println(user.getNom()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Prenom : </td>
		    <td><input type="text" name="prenom" id="prenom" value="<% out.println(user.getPrenom()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Date Naissance : </td>
		    <td><input type="text" name="datenaissance" id="datenaissance" value="<% out.println(user.getDateNaissance()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Adresse: </td>
		    <td><input type="text" name="adresse" id="adresse" value="<% out.println(user.getAdresse()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Role: </td>
		    <td><input type="text" name="role" id="role" value="<% out.println(user.isRole()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="update2" id="update2" value="Modifier"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>