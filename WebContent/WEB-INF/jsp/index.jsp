<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Bienvenu sur l'annuaire</title>
<c:url var="groupList" value="/actions/directory/group/list" />
<%-- <c:url var="passwordLost" value="/actions/directory/passwordLost" /> --%>
<c:url var="search" value="/actions/directory/search" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
		<article>
			<h1>Bonjour</h1>
			<p>Pour acceder a l'annuaire de Yann MASLIAH et redouane TIGRARA
				il faut s'authentifier</p>
		</article>

	</section>

<c:forEach items="${errors}" var="error">
            ${error.value}
    </c:forEach>

    
	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>


</body>
</html>