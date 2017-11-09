<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Bienvenu sur l'annuaire</title>
<c:url var="groupList"   value="/actions/directory/group/list" />
</head>
<body>
<!-- 	<header> -->
<%-- 		<c:import url="/WEB-INF/jsp/loginMenu.jsp"> --%>
<%-- 			<c:param name="page" value="1" /> --%>
<%-- 		</c:import> --%>
<!-- 	</header> -->
	<section>
		<article>
			<h1>Bonjour</h1>
			<p>Pour acceder a l'annuaire de Yann MASLIAH et redouane TIGRARA
				il faut s'authentifier</p>
		</article>
		<aside>wola je suis un truc qui servira peut etre</aside>
	</section>
	
	
	<section><a href="${groupList}">liste des groupes</a></section>
<section>fonction de recherche a implementer</section>
	<footer> Annuaire JEE 2017/2018 </footer>


</body>
</html>
<%-- <c:redirect url="/hello.htm"/> --%>