<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h1>Ajouter une Utilisateur</h1>
<form action="/Client_Voiture_Projet/GestionUtilisateur" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Mail : </td>
		    <td><input type="text" name="mail" id="mail" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Mot de passe : </td>
		    <td><input type="text" name="mp" id="mp" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Prenom : </td>
		    <td><input type="text" name="prenom" id="prenom" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Date Naissance : </td>
		    <td><input type="text" name="datenaissance" id="datenaissance" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Adresse: </td>
		    <td><input type="text" name="adresse" id="adresse" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Role: </td>
		    <td><input type="text" name="role" id="role" value="" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="insert2" id="insert2" value="Ajouter"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>