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
<style type="text/css">
.submitbtn
{
color: #fff;
    background-color: #337ab7;
    border-color: #2e6da4;
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 18px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
    width: 146px;
}

*{
    box-sizing:border-box;
    -moz-box-sizing:border-box;
    -webkit-box-sizing:border-box;
}

.inputBtnSection{
    display:inline-block;
    vertical-align:top;
    font-size:0;
    font-family:verdana;
}
.disableInputField{
    display:inline-block;
    vertical-align:top;
    height: 27px;
    margin: 0;
    font-size:14px;
    padding:0 3px;
}

.fileUpload {
	    position: relative;
    overflow: hidden;
    border: solid 1px gray;
    display: inline-block;
    vertical-align: top;
    height: 27px;
    padding: 2px 15px;
}
.uploadBtn{
    display:inline-block;
    vertical-align:top;
    background:rgba(0,0,0,0.5);
    font-size:14px;
    padding:0 10px;
    height:25px;
    line-height:22px;
    color:#fff;
}

.fileUpload input.upload {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity=0);
}
</style>
</head>
<body ng-controller="creditController">
<lknav></lknav>
<div layout="row" layout-align="center center">
                   <md-card flex="">
                    <md-card-content>
                      <h2 style="color: #7FAAC6;font-size:23px;">Invalid Application ID </h2>
<h6>FYI File Cannot be uploaded</h6>                     
                    </md-card-content>
                  </md-card>
                  </div>


</body>
</html>