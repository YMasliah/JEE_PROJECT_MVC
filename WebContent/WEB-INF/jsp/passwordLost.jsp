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
	<div class="form-gap" style="padding-top: 70px;"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h3>
								<i class="fa fa-lock fa-4x"></i>
							</h3>
							<h2 class="text-center">Mot de passe oublié?</h2>
							<p>Vou pouvez recuperer votre mot de passe ici.</p>
							<div class="panel-body">

								<form id="register-form" role="form" autocomplete="off" class="form" method="post" action="sendMail">

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-envelope color-blue"></i></span> <input
												id="email" name="email" placeholder="email address"
												class="form-control" type="email">
										</div>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user color-blue"></i></span> <input
												id="id" name="id" placeholder="Id"
												class="form-control" type="text">
										</div>
									</div>
									<div class="form-group">
										<input name="recover-submit"
											class="btn btn-lg btn-primary btn-block"
											value="Send Email" type="submit">
									</div>

								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>