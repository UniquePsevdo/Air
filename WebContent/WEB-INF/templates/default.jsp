<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" /></title>
	<link rel="stylesheet" href= "${pageContext.request.contextPath}/static/css/jquery-ui.css">
    <link rel="stylesheet" href= "${pageContext.request.contextPath}/static/css/bootstrap.css">
    <link rel="stylesheet" href= "${pageContext.request.contextPath}/static/css/style.css">
    <link rel="stylesheet" href= "${pageContext.request.contextPath}/static/css/structure_bs.css">
    <link rel="stylesheet" href= "${pageContext.request.contextPath}/static/css/prettify.css">
    <link rel="stylesheet" href= "${pageContext.request.contextPath}/static/css/jquery.dataTables.css">
    <script type="text/javascript" src= "${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script type="text/javascript" src= "${pageContext.request.contextPath}/static/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/multiselect.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.dataTables.columnFilter.js"></script>            
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.blockUI.js"></script>
    <tiles:insertAttribute name="includes"></tiles:insertAttribute>
</head>
<body>
	<div class="container">
		<div class="myHeader">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>

		<div class="content" style="margin-bottom: 60px">
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
		</div>

		<div class="footer">
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		</div>
	</div>
</body>
</html>