<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" ng-app="CreditApp">

<head>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="icon" type="image/png" href="${context}/resources/img/rupee_symbol.png"  sizes="16x16">
      <title>:: Lending Kart ::</title>
      <!-- Bootstrap -->
      <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
      <!--  Font -->
      <link href='//fonts.googleapis.com/css?family=Roboto+Slab:400,300,100,700' rel='stylesheet' type='text/css'>
      <link href='//fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic' rel='stylesheet' type='text/css'>
      <!-- Style.css -->
      <!-- <link href="css/style.css" rel="stylesheet">
      <link href="css/screen.css" rel="stylesheet"> -->
      <!-- Font Awesome Icon -->
     <!--  <link rel="stylesheet" href="fonts/css/font-awesome.min.css"> -->
      <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
      <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.css">
      <link rel="stylesheet" href="${context}/resources/css/xeditable.css"> 

       <style type="text/css">
$gray: #eee;

md-content.md-default-theme {
  background-color: $gray;
}
md-card {
  background-color: #fff;
  h2:first-of-type {
    margin-top: 0;
  }
}
md-toolbar {
  .md-button.md-default-theme {
    border-radius: 99%; // round toolbar buttons
  }
}
h2 {
  font-weight: 400;
}
.md-toolbar-tools-bottom {
  font-size: small;
  & :last-child {
    opacity: 0.8;
  }
}
md-toolbar:not(.md-hue-1),
.md-fab {
  fill: #fff;
  background-color: rgb(13, 93, 130);
    color: rgb(255,255,255);
}
md-sidenav {
  fill: #737373;
  ng-md-icon {
    position: relative;
    top: 5px; // adjust for svg viewbox
  }
}
.user-avatar {
  border-radius: 99%;
}
.inset {
    padding: 0px !important;
}
md-toolbar.md-default-theme {
    background-color: rgb(13, 93, 130);
    color: rgb(255,255,255);
    
}
md-tabs.md-default-theme.md-primary .md-header {
    background-color: rgb(13, 93, 130);
}
table {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    display: flex;
    flex-direction: column;
   /*  align-items: stretch; */
    height: 500px; /* this can vary */
    width:auto !important;
}

/* table * {
    box-sizing: inherit;
    -moz-box-sizing: inherit;
}
 */
/* thead {
    display: flex;
    flex-direction: column;
    align-items: stretch;
}
 */
tbody {
    overflow-y: scroll;
    /* display: inline-block; */
}

thead > tr, tbody > tr, tfoot > tr {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
}

/* thead, tfoot {
    flex-shrink: 0;
}
 */
th, tbody td {
    width: 20%; /* this can vary */
    overflow-x: hidden;
   /*  text-overflow: ellipsis;
    display: inline-block;
 */}
thead, tfoot {
    flex-shrink: 0;
}
tfoot {
    display: inline-block;
}

tfoot td {
    width: 100%;
    display: inline-block;
}
md-content.md-default-theme {
    background-color: rgb(241, 241, 241);
    overflow: hidden;
}
big{
width:200%!important;
}
.pattern
{
background: #313131;
    color: white;
}
md-card md-card-content {
    -webkit-order: 1;
    -ms-flex-order: 1;
    order: 1;
    padding: 16px;
}
md-card.md-default-theme {
    border-radius: 2px;
    margin-left: 1%;
}
.changeclass
{
border: 1px solid transparent;
background-color:  rgba(184, 184, 184, 0.5) !important;
}

</style>
<!-- angular files -->
	



<!-- <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-aria.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.js"></script>
    <script src="//cdn.jsdelivr.net/angular-material-icons/0.4.0/angular-material-icons.min.js"></script> -->
     <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-animate.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-aria.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular-route.js"></script>
    <script src="//cdn.jsdelivr.net/angular-material-icons/0.4.0/angular-material-icons.min.js"></script>
    <script src="${context}/resources/js/lib/xeditable.min.js"></script>
     <script src="${context}/resources/js/lib/smart-table.min.js"></script>
     <script type="text/javascript" src="${context}/resources/js/lib/angular-route.min.js"></script>
     <script type="text/javascript" src="${context}/resources/js/lib/angular-resource.min.js"></script>
	<script type="text/javascript" src="${context}/resources/js/lib/angular-sanitize.min.js"></script>
      
      
    	  <script src="${context}/resources/js/app/creditTemplateapp.js"></script>
     <script type="text/javascript" src="${context}/resources/js/app/controller/lkcreditcontroller.js"></script>
     <script type="text/javascript" src="${context}/resources/js/app/services/lkcreditService.js"></script>
     
     
   </head>
   <body >
   <div ng-controller="lkCreditController">
       <div layout="column" class="relative" layout-fill role="main">
      <md-toolbar layout="row">
    <div class="md-toolbar-tools">
          
          <h3>
            LK Credit Score
          </h3>
            
        </div>
        </md-toolbar>
   </div>
   <md-card flex="100" class="md-default-theme">
                    <md-card-content>
                    <div layout="row" layout-wrap>
                      <div flex="" class="col-md-12">
                      <div class="col-md-3"><b style="color:rgb(13, 93, 130);">Application Id</b>:{{id}}</div>
                      <div class="col-md-3"><b style="color:rgb(13, 93, 130);">Email Id</b>:{{email}}</div>
                      <div class="col-md-3"><b style="color:rgb(13, 93, 130);">Phone</b>:{{phn}}</div>
                      <div class="col-md-3"><b style="color:rgb(13, 93, 130);">Company</b>:{{company}}</div>
                      </div>
                      </div>
                      </md-card-content>
                  </md-card>
<md-card flex="100">
                    <md-card-content>
                     <h2>Summary Data</h2>
                     <div layout="row" layout-wrap>
                      <div flex="25" ng-repeat="header in summary">
                      <b style="color:rgb(13, 93, 130);">{{header.title}}</b>:{{header.value}}
                      </div>
                      </div>
                      </md-card-content>
</md-card>
		<md-card flex="100">
                    <md-card-content>
                     <form editable-form name="summaryform" onaftersave="saveTable();" oncancel="cancel()">
                     <!-- <h2>LK Credit Score Data</h2> -->
		<table flex-gt-sm="100" st-safe-src="" flex-gt-md="100" st-table="" class="table table-bordered table-striped" style="height:10%">
        <thead><tr class="pattern" ><th st-sort="header.title" ng-repeat="header in sheader">{{header.title}}</th></tr>
			</thead>
			<tbody><tr ng-repeat="s in scolumn">
				<td>{{s.title}}</td>
				<td align="right">{{s.value}}</td>
				<td align="right">{{s.percentile}}</td>
	
				
				</tr>
			</tbody>
		</table>
		</form>
		</md-card-content>
		</md-card>
<md-card flex="100">
                    <md-card-content>
                     <%-- <form editable-form name="cibilform" onaftersave="saveTable();" oncancel="cancel()"> --%>
                     <h2>Variables</h2>
		<table flex="50" st-safe-src=""  st-table="" class="table table-bordered table-striped">
                 <!-- <tr><th colspan="2" style="width:50%"><input st-search="" class="input-sm form-control" placeholder="search ..." type="text"/></th></tr> -->
                 <thead><tr class="pattern" >
                <!-- <th st-sort="header.title" ng-repeat="header in cibilheader">{{header.title}}</th> -->
                	<th st-ratio="10" style="width:8% !important">Number</th>
					<th st-ratio="20">Variables</th>
					<th st-ratio="10">Definition</th>
					<th st-ratio="30" style="width:8% !important">Weightage</th>
					<th st-ratio="20" style="width:10% !important">Lk Credit Score</th>
                </tr>
			</thead>
			<tbody><tr ng-repeat="data in values">
				<td st-ratio="10" style="width:8% !important">{{$index+1}}</td>
				<td st-ratio="20">{{data.varScoreBean.varName}}</td>
				<td st-ratio="10">{{data.varScoreBean.definition}}</td>
				<td st-ratio="30" align="right" style="width:8% !important">{{data.varScoreBean.weight}}</td>
				<td  st-ratio="20" align="right" style="width:10% !important">{{data.varScore}}</td>
			
				</tr>
			</tbody>
		</table>
		<%-- </form> --%>
		</md-card-content>
		</md-card>
   </div>
   <div ng-view></div>
   </body>
   </html>