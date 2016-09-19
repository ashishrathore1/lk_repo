<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<c:set var="context" value="${pageContext.request.contextPath}" />
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
    align-items: stretch;
    overflow-x: scroll;
    height: 500px; /* this can vary */
}

table * {
    box-sizing: inherit;
    -moz-box-sizing: inherit;
}

thead {
    display: flex;
    flex-direction: column;
    align-items: stretch;
    
}

tbody {
    overflow-y: scroll;
    display: inline-block;

}
big{
width:200%!important;
}
thead > tr, tbody > tr, tfoot > tr {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
}

thead, tfoot {
    flex-shrink: 0;
}
md-content.md-default-theme {
    background-color: rgb(241, 241, 241);
    overflow: hidden;
}
th, tbody td {
    width: 20%; /* this can vary */
    overflow-x: hidden;
    text-overflow: ellipsis;
    display: inline-block;
}

tfoot {
    display: inline-block;
}
.pattern
{
background: #313131;
    color: white;
}
tfoot td {
    width: 100%;
    display: inline-block;
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
.customcon {
	    margin-left: 47%;
}
.cls{
    color: #0D5D82 !important;
}
</style>
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
     <script src="${context}/resources/js/app/creditTemplateapp.js"></script>
    <script src="${context}/resources/js/app/controller/admincoordinateController.js"></script>
    <script src="${context}/resources/js/app/services/admincoordinateService.js"></script>
    

     
</head>
<body ng-app="CreditApp">

<div ng-controller="coordinateController">
<div layout="column" class="relative" layout-fill role="main">
      <md-toolbar layout="row">
    <div class="md-toolbar-tools">
          
          <h3>
            Co-ordinates for Credit Template
          </h3>
            
        </div>
        </md-toolbar>
   </div>
   <md-card>
    <md-content layout-padding="">
    <form name="userForm">

	<div layout="" layout-sm="column" flex="50">
	<md-input-container>
          <label>Version Id</label>
          <input ng-model="tab.versionid">
        </md-input-container>
        </div>
        <div layout="" layout-sm="column">
         <div layout="" layout-sm="column" flex="50">
         <h2 class="cls">Executive Summary</h2>
         </div>
          <div layout="" layout-sm="column" flex="50">
          <h2 class="cls">Cycle</h2>
          </div>
        </div>
         <div layout="" layout-sm="column">
	 <div layout="" layout-sm="column" flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.execXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.execYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
  
	 <div layout="" layout-sm="column" flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.cycXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.cycYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      </div>
      <div layout="" layout-sm="column">
         <div layout="" layout-sm="column" flex="50">
        <h2 class="cls">Cibil</h2>
         </div>
          <div layout="" layout-sm="column" flex="50">
          <h2 class="cls">Non Cibil</h2>
          </div>
        </div>
      <div layout="" layout-sm="column">
	 <div layout="" layout-sm="column"  flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.cibXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.cibYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
       
	 <div layout="" layout-sm="column"  flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.noncibXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.noncibYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      </div>
       <div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="50">
        <h2 class="cls">Applicants</h2>
       </div>
       <div layout="" layout-sm="column"  flex="50">
       <h2 class="cls">Bank Details Data</h2>
       </div>
       </div>
      <div layout="" layout-sm="column">
      
	 <div layout="" layout-sm="column"  flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.appXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.appYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
       
	 <div layout="" layout-sm="column"  flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.bankXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.bankYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      </div>
       <div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="50">
        <h2 class="cls">Revenue</h2>
       </div>
       <div layout="" layout-sm="column"  flex="50">
       <h2 class="cls">Consolidated</h2>
       </div>
       </div>
      
	 <div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.revXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.revYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
       
	 <div layout="" layout-sm="column" flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.conXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.conYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      </div>
      <div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="50">
        <h2 class="cls">Prevenue</h2>
       </div>
       <div layout="" layout-sm="column"  flex="50">
        <h2 class="cls">Comment 1</h2>
       </div>
       </div>
       
	<div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.prevXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.prevYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      
	 <div layout="" layout-sm="column" flex="50">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.comXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.comYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      </div>
      <div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="">
        <h2 class="cls">Comment 1</h2>
       </div>
       <div layout="" layout-sm="column"  flex="">
        <h2 class="cls">Comment 1</h2>
       </div>
       <div layout="" layout-sm="column"  flex="">
        <h2 class="cls">Comment 2</h2>
       </div>
       </div>
     
	<div layout="" layout-sm="column">
       <div layout="" layout-sm="column"  flex="">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.commXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.commYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
     
	 <div layout="" layout-sm="column" flex="">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.cmtXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.cmtYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      <div layout="" layout-sm="column">
        <md-input-container flex="">
          <label>Enter x-coordinate</label>
          <input ng-model="tab.commentXcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
        <md-input-container flex="">
          <label>Enter y-coordinate</label>
          <input ng-model="tab.commentYcoordinate" pattern="\d{1,4}[,]\d{1,4}" ng-required="true">
        </md-input-container>
      </div>
      </div>
      
	 



	<input type="submit" ng-disabled="userForm.$invalid" ng-click="submit($event)"
							class="customcon btn btn-primary btn-lg" 
							value="Submit" />
    </form>
    </md-content>
    </md-card>
    </div>
</body>


</html>