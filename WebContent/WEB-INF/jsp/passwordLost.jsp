<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Email with Spring MVC</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 100%; margin: 0 1.5% 24px 1.5%;">
	<div class="form-gap" style="padding-top: 50px;"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h3>
								<span style="font-size:3.5em;" class="glyphicon glyphicon-lock"></span>
							</h3>
							<h2 class="text-center">Mot de passe oublié?</h2>
							<p>Vou pouvez recuperer votre mot de passe ici.</p>
							<div class="panel-body">

								<form id="register-form" role="form" autocomplete="off"
									class="form" method="post" action="sendMail">

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-envelope color-blue"></i></span> <input
												id="email" name="email" placeholder="email address"
												class="form-control" type="email">
										</div>
									</div>
									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user color-blue"></i></span> <input
												id="id" name="id" placeholder="Id" class="form-control"
												type="text">
										</div>
									</div>
									<div class="form-group">
										<input name="recover-submit"
											class="btn btn-lg btn-primary btn-block" value="Send Email"
											type="submit">
									</div>
									<div class="form-group">
										<c:if test="${error_pwd == 'yes'}">
											<div class="alert alert-danger">
												<c:out value="${notify_pwd}"></c:out>

											</div>
										</c:if>
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

</body>
</html>