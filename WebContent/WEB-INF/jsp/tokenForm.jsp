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
    <center>
        <h1>Sending e-mail with Spring MVC</h1>
        <c:out value="${error }"></c:out>
        <form method="post" action="token">
            <table border="0" width="80%">
                <tr>
                    <td>token:</td>
                    <td><input type="text" name="token" size="65" /></td>
                </tr>
                                <tr>
                    <td>password1:</td>
                    <td><input type="text" name="password1" size="65" /></td>
                </tr>
                                <tr>
                    <td>password2:</td>
                    <td><input type="text" name="password2" size="65" /></td>
                </tr>
                 <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Send E-mail" />
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>