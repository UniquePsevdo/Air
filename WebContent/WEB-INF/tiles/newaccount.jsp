<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<table border=1 rules=none frame=box>
		<tbody>
			<tr>
				<td style="padding: 5px">Username:</td>
				<td style="text-align: center"><sf:input type="text"
						name="username" path="username"
						style="width: 200px" /></br><div class="error-message"><sf:errors path="username"></sf:errors></div>
				</td>
			</tr>
			
			<tr>
				<td>Company name:</td>
				<td style="text-align: center"><sf:input type="text"
						name="name" path="name"
						style="width: 200px" /><br><div class="error-message"><sf:errors path="name"></sf:errors></div>
				</td>
			</tr>
			
			<tr>
				<td>City:</td>
				<td style="text-align: center"><sf:input type="text"
						name="city" path="city"
						style="width: 200px" /><br><div class="error-message"><sf:errors path="city"></sf:errors></div>
				</td>
			</tr>

			<tr>
				<td>Email:</td>
				<td style="text-align: center"><sf:input type="text"
						name="email" path="email" style="width: 200px" /><br><div class="error-message"><sf:errors path="email"></sf:errors></div>
				</td>
			</tr>

			<tr>
				<td>Password:</td>
				<td style="text-align: center"><sf:input id="password" type="password"
						name="password" path="password"
						style="width: 200px" /><br><div class="error-message"><sf:errors path="password"></sf:errors></div>
				</td>
			</tr>

			<tr>
				<td style="text-align: left">Confirm password:</td>
				<td style="text-align: center"><input id = "confirmpass" type="password"
					name="confirmpass" style="width: 200px" /><div id="matchpass"></div></td>
			</tr>
			
			<tr>
				<td style="text-align: left">Type of user:</td>
				<td style="text-align: center">
					designer&nbsp;<sf:radiobutton name="authority" path="authority" value="ROLE_DESIGNER"/>&nbsp;&nbsp;
					<sf:radiobutton name="authority" path="authority" value="ROLE_PRODUCER" />&nbsp;producer
					<br><div class="error-message"><sf:errors path="authority"></sf:errors></div>
				</td>
			</tr>
			</tbody>
		</table><br>		
		<p><input class="btn btn-primary btn-md" value="Create account" type="submit" /></p>		
	</sf:form>
	
