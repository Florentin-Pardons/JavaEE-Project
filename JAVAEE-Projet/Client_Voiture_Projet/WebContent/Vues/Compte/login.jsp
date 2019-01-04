
<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h1>Login</h1>
<form action="/Client_Voiture_Projet/Login" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td>mail : </td>
		    <td><input type="text" name="mail" id="mail"
		value="" size="20"/></td>
		</tr>
		<tr>
		    <td>Mot de passe : </td>
		    <td><input type="text" name="mp" id="mp"
		value="" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="valider" id="valider" value="Valider"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
