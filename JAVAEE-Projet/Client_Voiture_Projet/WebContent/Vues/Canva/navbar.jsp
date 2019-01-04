<%
	//Verifie l'utilisateur connecté
	if(session.getAttribute("role").equals("defaut"))
	{ 
	%>
		<%@ include file='navbardefaut.jsp' %>
	<%
	}
	else if(session.getAttribute("role").equals("admin"))
	{ 
	%>
		<%@ include file='navbaradmin.jsp' %>
	<%
	}
	else if(session.getAttribute("role").equals("user"))
	{ 
	%>
		<%@ include file='navbaruser.jsp' %>
	<%
	}
%>
