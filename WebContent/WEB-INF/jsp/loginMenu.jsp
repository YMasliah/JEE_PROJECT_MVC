Je suis la future barre de menu je me trouve a droite du header html5 je
possede 2 etats. un etat quand je suis authentifier et un non.

Authentifier j'affiche : le nom de la perssonne connecté et le bouton
logout. 

Non authentifier j'affiche : une case pour le nom, une case pour
le mdp, un bouton pour valider, un bouton pour s'incrire et en dessous
un texte "mot de passe perdu" interactif qui ouvre une nouvelle page
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
<c:url var="show"   value="/actions/header/login" />
<form action="${show}" method="post">
	<button>New User</button>
</form>

<div class="container">
		<h1>Edit Product</h1>

		<form:form method="POST" commandName="user">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<div class="form-group">
				<label for="id">id:</label>
				<form:input class="form-control" path="id" />
				<form:errors path="id" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="password">password:</label>
				<form:input class="form-control" path="password" />
				<form:errors path="password" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-info">Submit</button>
			</div>
		</form:form>
	</div>



<a href="/actions/user">Nom d'une personne</a>
