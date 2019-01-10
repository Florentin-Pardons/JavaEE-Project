<%@ page import="javabean.Marque" %>
<%@ page import="java.text.SimpleDateFormat" %>

<% Marque marque = (Marque)request.getAttribute("marque"); %>
<% SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Modifier Marque</h2>
<form action="/Client_Voiture_Projet/GestionMarque" method="POST">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Id : </td>
		    <td><input type="text" name="id" id="id" value="<% out.println(marque.getId()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Nom : </td>
		    <td><input type="text" name="nom" id="nom" value="<% out.println(marque.getNom()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Date Creation : </td>
		    <td><input type="text" name="datecreation" id="datecreation" value="<% out.println(formatter.format(marque.getDateCrea())); %>" size="20"/></td>
		</tr>
		<tr>
		    <td> Pays d' Origine : </td>
		    <td><input type="text" name="paysorigine" id="paysorigine" value="<% out.println(marque.getPaysOrigine()); %>" size="20"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="update2" id="update2" value="Modifier"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>