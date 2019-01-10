<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h2>Ajouter une Categorie</h2>
<form action="/Client_Voiture_Projet/GestionCategorie" method="POST">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Description : </td>
		    <td><input type="text" name="description" id="description" value="" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="insert2" id="insert2" value="Ajouter"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>