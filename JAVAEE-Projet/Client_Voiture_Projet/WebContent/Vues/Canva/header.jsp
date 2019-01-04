<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv=\Content-Type\ content=\text/html; charset=ISO-8859-1\>
    <title><% out.println((String) request.getAttribute("titre")); %></title>
</head>
<body>
	<%@ include file='/Vues/Canva\\navbar.jsp' %>

    <div class="container body-content">