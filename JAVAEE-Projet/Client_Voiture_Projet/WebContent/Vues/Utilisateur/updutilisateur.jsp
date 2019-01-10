<%@ page import="javabean.Utilisateur" %>
<%@ page import="java.text.SimpleDateFormat" %>

<% Utilisateur user = (Utilisateur)request.getAttribute("utilisateur"); %>
<% SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Modifier Utilisateur</h2>
<form action="/Client_Voiture_Projet/GestionUtilisateur" method="POST">
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
		    <td><input type="text" name="datenaissance" id="datenaissance" value="<% out.println(formatter.format(user.getDateNaissance())); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Adresse: </td>
		    <td><input type="text" name="adresse" id="adresse" value="<% out.println(user.getAdresse()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Role: </td>
		    <td>
			    <div class="form-group">
		            <label class="radio-inline">
		                <input type="radio" name="role" value="0" checked>Utilisateur
		            </label>
		            <label class="radio-inline">
		                <input type="radio" name="role" value="1">Administrateur
		            </label>
	        	</div>
        	</td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="update2" id="update2" value="Modifier"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>