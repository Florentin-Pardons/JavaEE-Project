
<!-- Header -->
<%@ include file='/Vues/Canva\\header.jsp' %>

<div>
    <h2>Erreur</h2>
    <p>
        <%  out.println(session.getAttribute("erreur")); %>
    </p> 
</div>

<!-- Footer -->
<%@ include file='/Vues/Canva\\footer.jsp' %>
