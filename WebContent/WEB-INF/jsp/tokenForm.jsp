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
							<h2 class="text-center">Sending e-mail with Spring MVC</h2>
							<p>Vou pouvez réinitialisez votre mot de passe.</p>
							<div class="panel-body">

								<form id="register-form" role="form" autocomplete="off" class="form" method="post" action="token">

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-console color-blue"></i></span> <input
												id="token" name="token" placeholder="Code"
												class="form-control" type="text">
										</div>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-eye-close color-blue"></i></span> <input
												id="password1" name="password1" placeholder="Password"
												class="form-control" type="password">
										</div>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-eye-close color-blue"></i></span> <input
												id="Password2" name="password2" placeholder="Password"
												class="form-control" type="password">
										</div>
									</div>
									<div class="form-group">
										<input name="recover-submit"
											class="btn btn-lg btn-primary btn-block"
											value="Reset Password" type="submit">
									</div>

								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!--     <center> -->
<!--         <h1>Sending e-mail with Spring MVC</h1> -->
<%--         <c:out value="${error }"></c:out> --%>
<!--         <form method="post" action="token"> -->
<!--             <table border="0" width="80%"> -->
<!--                 <tr> -->
<!--                     <td>token:</td> -->
<!--                     <td><input type="text" name="token" size="65" /></td> -->
<!--                 </tr> -->
<!--                                 <tr> -->
<!--                     <td>password1:</td> -->
<!--                     <td><input type="text" name="password1" size="65" /></td> -->
<!--                 </tr> -->
<!--                                 <tr> -->
<!--                     <td>password2:</td> -->
<!--                     <td><input type="text" name="password2" size="65" /></td> -->
<!--                 </tr> -->
<!--                  <tr> -->
<!--                     <td colspan="2" align="center"> -->
<!--                         <input type="submit" value="Send E-mail" /> -->
<!--                     </td> -->
<!--                 </tr> -->
<!--             </table> -->
<!--         </form> -->
<!--     </center> -->
</body>
</html>