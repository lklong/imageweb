<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>fileupload | mvc-showcase</title>
</head>
<body>
	<div>
		<form:form action="/file/test" modelAttribute="zhiguFile">
			<form:input path="uri" />
			<form:errors path="uri" ></form:errors>
			<form:input path="specs" />
			<form:errors path="specs" ></form:errors>
			<form:errors path="userID" ></form:errors>
			<form:button value="submit" ></form:button>
		</form:form>
	</div>
</body>
</html>
