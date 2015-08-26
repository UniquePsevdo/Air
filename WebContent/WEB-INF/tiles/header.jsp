<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<div class="row">
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
		<div class="row">
			<div class="col-md-6 navbar-header" id="fixednav">
				<ul class="nav navbar-nav">
					<li id="home"><a href="<c:url value='/'/>">Home</a></li>
					<sec:authorize access="isAuthenticated()">
						<li id="projects"><a href="<c:url value='/projects'/>">Projects</a></li>
						<sec:authorize access="hasRole('ROLE_PRODUCER')">
						<li id="incoming"><a href="<c:url value='/incoming'/>">Incoming</a></li>
						</sec:authorize>
					</sec:authorize>

					<sec:authorize access="!isAuthenticated()">
						<li id="mylogin"><a href="<c:url value='/login'/>">Log in</a></li>
						<li id="registration"><a href="<c:url value='/newaccount'/>">Registration</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a></li>
					</sec:authorize>
				</ul>
			</div>
			<c:if test="${sessionScope.username!=null}">
			<div class="col-md-6" style="display: inline; position: absolute; top: 27%; text-align: right; color:#777">
			You are logged in as:&nbsp;<em><c:out value="${sessionScope.username}"></c:out></em></div>
			</c:if>
			</div>
		</div>
	</div>
</div>

<c:if test="${param.error != null}">
	<p class="error-message">Login failed. Check your username and password.</p>
</c:if>
