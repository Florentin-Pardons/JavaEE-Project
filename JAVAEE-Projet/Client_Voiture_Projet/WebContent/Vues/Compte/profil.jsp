<%@ page import="javabean.Utilisateur" %>

<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>


<h1>Profil</h1>
<% Utilisateur user = (Utilisateur)session.getAttribute("utilisateur");

	out.println("<p>" + user.getMail() + "</p>");
	out.println("<p>" + user.getMp() + "</p>");
	out.println("<p>" + user.getNom() + "</p>");
	out.println("<p>" + user.getPrenom() + "</p>");
	out.println("<p>" + user.getAdresse() + "</p>");
%>
<!--
<jsp:useBean id="utilisateur" class = "javabean.Utilisateur" scope = "session" />
<jsp:getProperty property="mail" name="utilisateur"/>
<jsp:getProperty property="mp" name="utilisateur"/>
-->

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
