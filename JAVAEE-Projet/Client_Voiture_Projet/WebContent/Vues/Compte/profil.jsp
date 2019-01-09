<%@ page import="javabean.Utilisateur" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h2>Profil</h2>
<% Utilisateur user = (Utilisateur)session.getAttribute("utilisateur");

	out.println("<p> Mail : " + user.getMail() + "</p>");
	out.println("<p> Mot de passe : " + user.getMp() + "</p>");
	out.println("<p> Nom : " + user.getNom() + "</p>");
	out.println("<p> Prenom : " + user.getPrenom() + "</p>");
	out.println("<p> Adresse : " + user.getAdresse() + "</p>");
%>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
