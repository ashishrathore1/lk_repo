<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="CreditApp">
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"	href="${context}/resources/css/angular-material.css" />
<link rel="stylesheet" href="${context}/resources/css/italicfont.css" />
<link rel="stylesheet" href="${context}/resources/css/test.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="${context}/resources/js/lib/jquery-1.9.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${context}/resources/js/lib/angular.min.js"></script>
<script src="${context}/resources/js/lib/xeditable.min.js"></script>
<script src="${context}/resources/js/lib/smart-table.min.js"></script>
<script src="${context}/resources/js/lib/angular-animate.min.js"></script>
<script src="${context}/resources/js/lib/angular-aria.min.js"></script>
<script src="${context}/resources/js/lib/angular-material.min.js"></script>
<script src="${context}/resources/js/lib/jquery-1.8.3.min.js"></script><script src="${context}/resources/js/app/creditTemplateapp.js"></script>
    <script src="${context}/resources/js/app/controller/creditController.js"></script>
<script src="${context}/resources/js/app/creditTemplateapp.js"></script>
<script src="${context}/resources/js/app/controller/creditController.js"></script>
<script src="${context}/resources/js/app/directives/navbardirective.js"></script>
<title>Lendingkart</title>
<style>
		body {background: #fff;margin: 0; padding: 20px; text-align:center; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#666666;}
		.error_page {width: 600px; padding: 50px; margin: auto;}
		.error_page h1 {margin: 20px 0 0;}
		.error_page p {margin: 10px 0; padding: 0;}		
		a {color: #9caa6d; text-decoration:none;}
		a:hover {color: #9caa6d; text-decoration:underline;}
		</style>
</head>
<body ng-controller="creditController">
<lknav></lknav>
<div class="error_page">
    <img alt="Kidmondo_face_sad" src="${context}/resources/img/smiley.jpg" />
    <h1>Oopss!!</h1>
    <h4>Credit Template with this version id cannot be generated.</h4>
    <h5><a href="/lkart/adminlayout.html#!/home">Retreat back to Admin Console</a></h5>
  </div>
</body>

</body>
</html>