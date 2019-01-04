<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h1>Modifier Marque</h1>
<form action="/Client_Voiture_Projet/GestionMarque" method="GET">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Id : </td>
		    <td><input type="text" name="id" id="id" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Date Creation : </td>
		    <td><input type="text" name="datecreation" id="datecreation" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Pays d' Origine : </td>
		    <td><input type="text" name="paysorigine" id="paysorigine" value="" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="update2" id="update2" value="Modifier"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>