<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv=\Content-Type\ content=\text/html; charset=ISO-8859-1\>
    
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="/Client_Voiture_Projet/Vues/Canva/resources/Content/Site.css" rel="stylesheet" />
    <link href="/Client_Voiture_Projet/Vues/Canva/resources/Content/bootstrap.min.css" rel="stylesheet" />
    <script src="/Client_Voiture_Projet/Vues/Canva/resources/Scripts/modernizr-2.6.2.js"></script>
    
    <title><% out.println((String) request.getAttribute("titre")); %></title>
</head>
<body>
	<%@ include file='/Vues/Canva\\navbar.jsp' %>

    <div class="container body-content">