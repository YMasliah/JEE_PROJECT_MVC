<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Accueil</title>
<%-- <c:url var="groupList" value="/actions/directory/group/list" /> --%>
<%-- <c:url var="passwordLost" value="/actions/directory/passwordLost" /> --%>
<%-- <c:url var="search" value="/actions/directory/search" /> --%>
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
		<article>
			<h1>Accueil</h1>
			<h2>bonjour bienvenue au portail web de l'annuaire de Yann MASLIAH et redouane TIGRARA.</h2>
			<h4>Cette application web vous permettra de : </h4>
			<p>     ->      Vous authentifiez si vous êtes inscrit sur l annuaire avec vos coordonnées.</p>
			<p>     ->      Afficher la liste de tous les groupes de l annuaire.</p>
			<p>     ->      Rechercher un membre ou groupe particuliers dans l annuaire.</p>
			<p>     ->      Si vous êtes inscrit sur l annuaire et que vous avez oublié votre mot de passe un système de récupération est mis en place.</p>
			
		</article>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>