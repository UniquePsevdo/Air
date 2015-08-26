<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	});
</script>

<h3>Login with username and password</h3>

<%-- <c:if test="${param.error != null}">
	<p class="error-message">Login failed. Check your username and password.</p>
</c:if> --%>

<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
	<table class="formtable">
		<tr>
			<td>User:</td>
			<td><input style="width: 260px" type='text' name='j_username'></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input style="width: 260px" type='password' name='j_password' /></td>
		</tr>
		<tr>
			<td>Remember me:</td>
			<td><input type='checkbox' name='_spring_security_remember_me'
				checked="checked" /></td>
		</tr>
		<tr>
			<td colspan='2'><input class="btn btn-primary btn-md" name="submit" type="submit" value="Login" /></td>
		</tr>
	</table>
</form>
<p>

	<p style="font-size: medium;"><a href="<c:url value="/newaccount"></c:url>">Registration</a></p>

<script type="text/javascript">
function setLoginActive() {
    $('#fixednav ul li').removeClass('active');
    $('#mylogin').addClass('active');
}

$(document).ready(setLoginActive) ;
</script>
