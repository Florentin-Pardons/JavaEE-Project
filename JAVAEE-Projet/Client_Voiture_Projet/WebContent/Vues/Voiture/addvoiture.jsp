<%@ page import="javabean.Modele" %>
<%@ page import="java.util.List;" %>

<% List<Modele> listmodele = (List<Modele>)request.getAttribute("listmodele"); %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<h2>Ajouter une Voiture</h2>
<form action="/Client_Voiture_Projet/GestionModele" method="POST">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
		    <td> Couleur : </td>
		    <td><input type="text" name="couleur" id="couleur" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Carburant : </td>
		    <td><input type="text" name="carburant" id="carburant" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Boite de Vitesse : </td>
		    <td><input type="text" name="boiteVitesse" id="boiteVitesse" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Nombre de Kilometre : </td>
		    <td><input type="text" name="nbkm" id="nbkm" value="" size="20"/></td>
		</tr>
		<tr>
		    <td> Disponibilité: </td>
		    <td>
			    <div class="form-group">
		            <label class="radio-inline">
		                <input type="radio" name="dispo" value="0" checked>Disponible
		            </label>
		            <label class="radio-inline">
		                <input type="radio" name="dispo" value="1">Non-Disponible
		            </label>
	        	</div>
        	</td>
		</tr>
       	<tr>
       		<td> Modele : </td>
       		<td>
       			<select name="modele" id="modele">
       			<%
       				String mar = "";
       			
					for(Modele mod : listmodele)
					{
						if(mar.equals(""))
						{
							mar = mod.getMarque().getNom();
							out.println("<optgroup label=" + mar + ">");
						}
						else 
						{
							if(mar.equals(mod.getMarque().getNom()))
							{
								out.println("<option value=" + mod.getId() + ">" + mod.getNom() + "</option>");
							}
							else
							{
								out.println("</optgroup>");
								mar = mod.getMarque().getNom();
								out.println("<optgroup label=" + mar + ">");
								out.println("<option value=" + mod.getId() + ">" + mod.getNom() + "</option>");
							}
						}
					}
					out.println("</optgroup>");
				%>
       			</select>
       		</td>
       	</tr>
		<tr>
		    <td colspan="2" align="center"><input type="submit" name="insert2" id="insert2" value="Ajouter"/></td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>