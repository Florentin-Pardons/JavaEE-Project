
<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h1>Inscription</h1>
<form action="/Client_Voiture_Projet/Profil" method="POST">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td>Identifiant/Login : </td>
		    <td><input type="text" name="identifiant" id="identifiant"
		value="" size="20"/></td>
		</tr>
		<tr>
		    <td>Mot de passe : </td>
		    <td><input type="text" name="motdepasse" id="motdepasse"
		value="" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit"
		name="valider" id="valider" value="Valider"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
