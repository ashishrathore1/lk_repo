
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,700,300italic"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="${context}/resources/css/v-accordion.min.css">
<!-- Styles -->

<link
	href="https://lukaszwatroba.github.io/valitycss/dist/vality.min.css"
	rel="stylesheet">
<!-- <link rel="stylesheet" href="./bower/valitycss/dist/vality.css"> -->
<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.css">
<link rel="stylesheet" href="${context}/resources/css/xeditable.css">
<!-- This is the special stylesheet for print and m feeling good and xcited about this -->
<link rel="stylesheet" href="${context}/resources/css/print.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/trix/0.9.2/trix.css">
<link rel="stylesheet" href="${context}/resources/css/styles.css">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-aria.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular-route.js"></script>
<script
	src="//cdn.jsdelivr.net/angular-material-icons/0.4.0/angular-material-icons.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/trix/0.9.2/trix.js"></script>
<script src="${context}/resources/js/lib/xeditable.min.js"></script>
<script src="${context}/resources/js/lib/smart-table.min.js"></script>
<script src="${context}/resources/js/lib/moment.js"></script>
<script src="${context}/resources/js/app/creditTemplateapp.js"></script>
<script src="${context}/resources/js/app/controller/creditController.js"></script>
<script
	src="${context}/resources/js/app/controller/loanhistoryController.js"></script>
<script src="${context}/resources/js/app/controller/editController.js"></script>
<script src="${context}/resources/js/app/controller/pdController.js"></script>
<script src="${context}/resources/js/app/services/pdService.js"></script>
<script src="${context}/resources/js/app/services/creditDataservice.js"></script>
<script src="${context}/resources/js/app/services/loanhistoryservice.js"></script>
<script src="${context}/resources/js/app/directives/navbardirective.js"></script>
<script src="${context}/resources/js/lib/angular-spinner.js"></script>
<script src="${context}/resources/js/lib/v-accordion.min.js"></script>
<script src="${context}/resources/js/lib/angular-tricks.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.11.0/ui-bootstrap-tpls.min.js"></script>
<title>Lendingkart</title>
</head>
<spinner name="html5spinner" ng-cloak="">
<div class="overlay"></div>
<div class="spinner">
	<div class="double-bounce1"></div>
	<div class="double-bounce2"></div>
</div>
<div class="please-wait">Please Wait...</div>

</spinner>
<body ng-app="CreditApp" style="background: #F1F1F1;">

	<div layout="row" ng-cloak>

		<div layout="column" class="relative" layout-fill role="main"
			ng-controller="creditController">
			<md-toolbar class="norprint" ng-show="!showSearch"
				style="position:fixed;">
			<div class="md-toolbar-tools" style="background: white;">
				<h3>
					<md-icon md-svg-src="${context}/resources/img/1.svg"
						style="width: 222px;
    height: 143px;
    margin-top: 11%;"
						class="email"></md-icon>
				</h3>
				<md-button ng-click="download()" aria-label="Menu"> <md-icon
					md-svg-src="${context}/resources/img/down.svg" class="email"
					style="color: #00568D;"></md-icon> <md-tooltip>Click
				to download Excel template</md-tooltip> </md-button>
				<p style="color: #00568D; position: absolute; right: 7%; top: 24%">${uName}
				<p>
			</div>

			</md-toolbar>
			<div class="norprint"
				style="background: #F1F1F1;
	/* padding: 0%; */ padding-left: 4%; font-size: larger; font-weight: 800;
	/* font-family: monospace; */ padding-top: 4%; font: -webkit-small-control;">
				<h2>
					Credit Template:<i>${appId}</i>
				</h2>

				<form method="post" action="/CreditTemplate/uploadCreditExcel"
					enctype="multipart/form-data">
					<div class="form-group">
						<input type="hidden" name="appId" class="form-control"
							value="${appId}"> <input type="hidden" name="vid"
							class="form-control" value="${vid}"> <input type="hidden"
							name="uid" class="form-control" value="${uId} ">
					</div>
					<div class="col-md-12" ng-if="${showUpload}">
						<input id="uploadFile" placeholder="{{uploadPlaceHolder}}"
							disabled="disabled" />
						<div class="fileUpload btn btn-primary">
							<span>Choose File</span> <input id="uploadBtn" name="nhp"
								type="file" ng-click="uploadFile()" class="upload" />
						</div>
						<button class="btn btn-warning" type="submit"
							ng-click="submitFile()" value="Upload Excel"
							style="padding: 3px 26px;" name="submit">Submit</button>
					</div>

				</form>

				<button class="btn btn-sm btn-warning"
					style="width: 143px; height: 30px; border-radius: 10px !important; font-size: 13px !important; position: absolute; right: 27px; top: 70px;"
					ng-click="cibilpull()">Get CIBIL Data</button>
				<div
					style="position: absolute; top: 86px; right: 175px; color: green;"
					ng-if="check">{{confirm}}</div>
			</div>
			<md-content flex> <ui-view layout="column" layout-fill
				layout-padding>
			<div class="inset" hide-sm></div>
			<ng-switch on="data.selectedIndex" class="tabpanel-container">
			<md-tabs class="noprint md-primary" md-selected="data.selectedIndex"
				style="border-top-left-radius: 25px;
    border-top-right-radius: 25px;width:98%;font-weight:500;overflow:auto;margin: 0 auto;">
			<md-tab id="tab1" aria-controls="tab1-content"> Executive
			Summary </md-tab> <md-tab id="tab2" aria-controls="tab2-content">
			Analysis report </md-tab> <md-tab id="tab3" aria-controls="tab2-content">
			Variables </md-tab> <md-tab id="tab4" aria-controls="tab4-content">
			Personal Discussions </md-tab> </md-tabs>
			<div role="tabpanel" id="tab1-content" aria-labelledby="tab1"
				ng-switch-when="0" md-swipe-left="next()"
				md-swipe-right="previous()" layout="row" layout-wrap
				ng-controller="CommentCtrl">
				<div flex="100" ng-repeat="data in executivemaster">
					<div layout="row" ng-repeat="(key,val) in data" layout-wrap
						style="padding-bottom: 7px; padding-top: 9px;">
						<md-card flex="100" class="md-default-theme execard"
							ng-if="key=='execSummHeaders'"> <md-card-content
							layout-align="center center" style="padding-top:0px;">
						<h2>Executive Summary Information</h2>
						<div layout="column" ng-repeat="(k,v) in val" class="ng-scope"
							layout="center center">
							<div layout="row" ng-repeat="(ke,va) in v" layout-wrap>

								<table ng-if="ke==0" flex-gt-sm="100" flex-gt-md="100"
									class="table table-bordered table-striped"
									style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
									<tbody style="overflow-y: hidden;">
										<tr ng-repeat="(ke,va) in v">
											<td class="headkey" style="background: white;">{{va[0].val}}</td>
											<td class="key" style="background: white;">{{va[1].val}}</td>
											<td class="headkey" style="background: white;">{{va[2].val}}</td>
											<td class="key" style="background: white;">{{va[3].val}}</td>
											<td class="headkey" style="background: white;">{{va[4].val}}</td>
											<td class="key" style="background: white;">{{va[5].val}}</td>
											<td class="key" style="background: white;">{{va[6].val}}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						</md-card-content> </md-card>
					</div>
				</div>
				<div flex="100" ng-if="newversion">
					<div ng-if="executive.tName=='ANALYTICS'" flex="100"
						ng-repeat="executive in newexecutive.singleValList"
						style="padding-bottom: 5px;">

						<md-card ng-if="executive.tName=='ANALYTICS'" flex="100"
							class="md-default-theme execard"> <v-accordion
							id="accordionExec" class="vAccordion--default" multiple
							control="accordionExec"> <v-pane
							id="{{ ::pane.id }}"> <v-pane-header
							id="{{ ::pane.id }}-header"
							aria-controls="{{ ::pane.id }}-header"> <span
							class="catExecheader">{{executive.tName}}</span> </v-pane-header> <v-pane-content
							id="{{ ::pane.id }}-content"
							aria-labelledby="{{ ::pane.id }}-content"
							style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
						<md-card-content style="padding:0px;height:auto;">


						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key">{{executive.resultList[0].key}}</td>
									<td class="value">{{executive.resultList[0].val}}</td>
									<td class="key">{{executive.resultList[1].key}}</td>
									<td class="value">{{executive.resultList[1].val}}</td>
									<td class="key">{{executive.resultList[2].key}}</td>
									<td class="value">{{executive.resultList[2].val}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[3].key}}</td>
									<td class="value">{{executive.resultList[3].val}}</td>
									<td class="key">{{executive.resultList[4].key}}</td>
									<td class="value">{{executive.resultList[4].val}}</td>
									<td class="key">{{}}</td>
									<td class="value">{{}}</td>
								</tr>
							</tbody>
						</table>
						</md-card-content> </v-pane-content> </v-pane> </v-accordion> </md-card>
					</div>
					<div ng-if="executive.tName=='LOAN ELIGIBILTY'" flex="100"
						ng-repeat="executive in newexecutive.doubleValList"
						style="padding-bottom: 5px;">
						<md-card flex="100" class="md-default-theme card execard">
						<v-accordion id="accordionExec" class="vAccordion--default"
							multiple control="accordionExec"> <v-pane
							id="{{ ::pane.id }}" expanded> <v-pane-header
							id="{{ ::pane.id }}-header"
							aria-controls="{{ ::pane.id }}-header"> <span
							class="catExecheader">{{executive.tName}} (in Lakhs.)</span> </v-pane-header> <v-pane-content
							id="{{ ::pane.id }}-content"
							aria-labelledby="{{ ::pane.id }}-content"
							style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
						<md-card-content style="padding:0px;height:auto;">


						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key" style="font-weight: 600;">Based on Nature</td>
									<td class="value"
										style="background: #FFE2BC; font-weight: 600;">Scenario A</td>
									<td class="value"
										style="background: #FFE2BC; font-weight: 600;">Scenario B</td>
									<td class="key" style="font-weight: 600;">Based on
										Industry/Operating Cycle</td>
									<td class="value"
										style="background: #FFE2BC; font-weight: 600;">Scenario A</td>
									<td class="value"
										style="background: #FFE2BC; font-weight: 600;">Scenario B</td>
								</tr>

								<tr>
									<td class="key">{{executive.resultList[0].key}}</td>
									<td class="value">{{executive.resultList[0].val1}}</td>
									<td class="value">{{executive.resultList[0].val2}}</td>
									<td class="key">{{executive.resultList[3].key}}</td>
									<td class="value">{{executive.resultList[3].val1}}</td>
									<td class="value">{{executive.resultList[3].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[1].key}}</td>
									<td class="value">{{executive.resultList[1].val1}}</td>
									<td class="value">{{executive.resultList[1].val2}}</td>
									<td class="key">{{executive.resultList[4].key}}</td>
									<td class="value">{{executive.resultList[4].val1}}</td>
									<td class="value">{{executive.resultList[4].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[2].key}}</td>
									<td class="value">{{executive.resultList[2].val1}}</td>
									<td class="value">{{executive.resultList[2].val2}}</td>
									<td class="key">{{executive.resultList[5].key}}</td>
									<td class="value">{{executive.resultList[5].val1}}</td>
									<td class="value">{{executive.resultList[5].val2}}</td>
								</tr>
							</tbody>
						</table>
						</md-card-content> </v-pane-content> </v-pane> </v-accordion> </md-card>

					</div>
					<div ng-if="executive.tName=='PURPOSE'" flex="100"
						ng-repeat="executive in newexecutive.singleValList"
						style="padding-bottom: 5px;">
						<md-card flex="100" class="md-default-theme card execard">
						<v-accordion id="accordionExec" class="vAccordion--default"
							multiple control="accordionExec"> <v-pane
							id="{{ ::pane.id }}" expanded> <v-pane-header
							id="{{ ::pane.id }}-header"
							aria-controls="{{ ::pane.id }}-header"> <span
							class="catExecheader">{{executive.tName}}</span> </v-pane-header> <v-pane-content
							id="{{ ::pane.id }}-content"
							aria-labelledby="{{ ::pane.id }}-content"
							style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
						<md-card-content style="padding:0px;height:auto;">


						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key">{{executive.resultList[0].key}}</td>
									<td class="key" style="background: white;">{{executive.resultList[0].val}}</td>
								</tr>
							</tbody>
						</table>
						</md-card-content> </v-pane-content> </v-pane> </v-accordion> </md-card>

					</div>
					<div
						ng-if="executive.tName=='REVENUE'||executive.tName=='FINANCIAL DISCIPLINE'||executive.tName=='CIBIL'||executive.tName=='VINTAGE, STATUTORY COMPLIANCE & DEFAULT STATUS'"
						flex="100" ng-repeat="executive in newexecutive.singleValList"
						style="padding-bottom: 5px;">
						<md-card flex="100" class="md-default-theme card execard"
							style="padding: 11px;font-family:sans-serif;font-size:.8em;line-height: 1.214em;font-weight:300;color: black;">
						<v-accordion id="accordionExec" class="vAccordion--default"
							multiple control="accordionExec"> <v-pane
							id="{{ ::pane.id }}" expanded> <v-pane-header
							id="{{ ::pane.id }}-header"
							aria-controls="{{ ::pane.id }}-header"> <span
							class="catExecheader"
							ng-if="executive.tName=='REVENUE'||executive.tName=='CIBIL'">{{executive.tName}}
							(in Lakhs.)</span> <span class="catExecheader"
							ng-if="executive.tName!='REVENUE'&&executive.tName!='CIBIL'">{{executive.tName}}</span>
						</v-pane-header> <v-pane-content id="{{ ::pane.id }}-content"
							aria-labelledby="{{ ::pane.id }}-content"
							style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
						<md-card-content style="padding:0px;height:auto;">
						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key">{{executive.resultList[0].key}}</td>
									<td class="value">{{executive.resultList[0].val}}</td>
									<td class="key">{{executive.resultList[1].key}}</td>
									<td class="value">{{executive.resultList[1].val}}</td>
									<td class="key">{{executive.resultList[2].key}}</td>
									<td class="value">{{executive.resultList[2].val}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[3].key}}</td>
									<td class="value">{{executive.resultList[3].val}}</td>
									<td class="key">{{executive.resultList[4].key}}</td>
									<td class="value">{{executive.resultList[4].val}}</td>
									<td class="key">{{executive.resultList[5].key}}</td>
									<td class="value">{{executive.resultList[5].val}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[6].key}}</td>
									<td class="value">{{executive.resultList[6].val}}</td>
									<td class="key">{{executive.resultList[7].key}}</td>
									<td class="value">{{executive.resultList[7].val}}</td>
									<td class="key">{{executive.resultList[8].key}}</td>
									<td class="value">{{executive.resultList[8].val}}</td>
								</tr>
								<tr
									ng-if="executive.tName=='FINANCIAL DISCIPLINE'||executive.tName=='CIBIL'">
									<td class="key">{{executive.resultList[9].key}}</td>
									<td class="value">{{executive.resultList[9].val}}</td>
									<td class="key">{{executive.resultList[10].key}}</td>
									<td class="value">{{executive.resultList[10].val}}</td>
									<td class="key">{{executive.resultList[11].key}}</td>
									<td class="value">{{executive.resultList[11].val}}</td>
								</tr>
								<tr ng-if="executive.tName=='FINANCIAL DISCIPLINE'">
									<td class="key">{{executive.resultList[12].key}}</td>
									<td class="value">{{executive.resultList[12].val}}</td>
									<td class="key">{{executive.resultList[13].key}}</td>
									<td class="value">{{executive.resultList[13].val}}</td>
									<td class="key">{{executive.resultList[14].key}}</td>
									<td class="value">{{executive.resultList[14].val}}</td>
								</tr>
								<tr ng-if="executive.tName=='FINANCIAL DISCIPLINE'">
									<td class="key">{{executive.resultList[12].key}}</td>
									<td class="value">{{executive.resultList[12].val}}</td>
									<td class="key">{{executive.resultList[13].key}}</td>
									<td class="value">{{executive.resultList[13].val}}</td>
									<td class="key">{{executive.resultList[14].key}}</td>
									<td class="value">{{executive.resultList[14].val}}</td>
								</tr>
								<tr ng-if="executive.tName=='FINANCIAL DISCIPLINE'">
									<td class="key" style="background: white;"></td>
									<td class="value"></td>
									<td class="key">{{executive.resultList[15].key}}</td>
									<td class="value">{{executive.resultList[15].value}}</td>
									<td class="key">{{executive.resultList[16].key}}</td>
									<td class="value">{{executive.resultList[16].val}}</td>
								</tr>
							</tbody>

						</table>

						</md-card-content> </v-pane-content> </v-pane> </v-accordion> </md-card>

					</div>
					<div flex="100" style="padding-bottom: 5px;">
						<md-card flex="100" class="md-default-theme card execard"
							style="padding: 11px;font-family:sans-serif;font-size:.8em;line-height: 1.214em;font-weight:300;color: black;">
						<v-accordion id="accordionExec" class="vAccordion--default"
							multiple control="accordionExec"> <v-pane
							id="{{ ::pane.id }}" expanded> <v-pane-header
							id="{{ ::pane.id }}-header"
							aria-controls="{{ ::pane.id }}-header"> <span
							class="catExecheader">SOCIAL</span> </v-pane-header> <v-pane-content
							id="{{ ::pane.id }}-content"
							aria-labelledby="{{ ::pane.id }}-content"
							style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
						<md-card-content style="padding:0px;height:auto;">
						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key">Market Places</td>
									<td class="key">Flipkart</td>
									<td class="key">Snapdeal</td>
									<td class="key">Paytm</td>
									<td class="key">Shopclues</td>
									<td class="key">Amazon</td>
									<td class="key">Other</td>
								</tr>
								<tr ng-if="$index!=0&&$index==1" ng-repeat="result in social">
									<td style="width: 200px;" class="key">{{result.resultList[0].val}}</td>
									<td class="value">{{result.resultList[1].val}}</td>
									<td class="value">{{result.resultList[2].val}}</td>
									<td class="value">{{result.resultList[3].val}}</td>
									<td class="value">{{result.resultList[4].val}}</td>
									<td class="value">{{result.resultList[5].val}}</td>
									<td class="value">{{result.resultList[6].val}}</td>
								</tr>
								<tr ng-if="$index!=0&&$index!=1" ng-repeat="result in social">
									<td class="key">{{result.resultList[0].val}}</td>
									<td class="value">{{result.resultList[1].val}}</td>
									<td class="key">{{result.resultList[2].val}}</td>
									<td class="value">{{result.resultList[3].val}}</td>
									<td class="key">{{result.resultList[4].val}}</td>
									<td class="value">{{result.resultList[5].val}}</td>
								</tr>
							</tbody>
						</table>
						</md-card-content> </v-pane-content> </v-pane> </v-accordion> </md-card>

					</div>

					<div ng-if="executive.tName=='WORKING CAPITAL CYCLE'" flex="100"
						ng-repeat="executive in newexecutive.singleValList"
						style="padding-bottom: 5px;">
						<md-card flex="100" class="md-default-theme card execard"
							style="padding: 11px;font-family:sans-serif;font-size:.8em;line-height: 1.214em;font-weight:300;color: black;">
						<v-accordion id="accordionExec" class="vAccordion--default"
							multiple control="accordionExec"> <v-pane
							id="{{ ::pane.id }}" expanded> <v-pane-header
							id="{{ ::pane.id }}-header"
							aria-controls="{{ ::pane.id }}-header"> <span
							class="catExecheader">{{executive.tName}}</span> </v-pane-header> <v-pane-content
							id="{{ ::pane.id }}-content"
							aria-labelledby="{{ ::pane.id }}-content"
							style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
						<md-card-content style="padding:0px;height:auto;">

						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key">{{executive.resultList[0].key}}</td>
									<td class="value">{{executive.resultList[0].val}}</td>
									<td class="key">{{executive.resultList[1].key}}</td>
									<td class="value">{{executive.resultList[1].val}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[2].key}}</td>
									<td class="value">{{executive.resultList[2].val}}</td>
									<td class="key">{{executive.resultList[3].key}}</td>
									<td class="value">{{executive.resultList[3].val}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[4].key}}</td>
									<td class="value">{{executive.resultList[4].val}}</td>
									<td class="key">{{executive.resultList[5].key}}</td>
									<td class="value">{{executive.resultList[5].val}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[6].key}}</td>
									<td class="value">{{executive.resultList[6].val}}</td>
									<td class="key">{{executive.resultList[7].key}}</td>
									<td class="value">{{executive.resultList[7].val}}</td>
								</tr>
							</tbody>
						</table>
						</md-card-content> </v-pane-content> </v-pane> </v-accordion> </md-card>

					</div>
					<md-card flex="100" class="md-default-theme card execard">

					<v-accordion id="accordionExec" class="vAccordion--default"
						multiple control="accordionExec" style="
    width: 100%;
">

					<v-pane id="{{ ::pane.id }}"> <v-pane-header
						id="{{ ::pane.id }}-header" aria-controls="{{ ::pane.id }}-header">
					<span class="catExecheader">CMA & RATIOS</span> </v-pane-header> <v-pane-content
						id="{{ ::pane.id }}-content"
						aria-labelledby="{{ ::pane.id }}-content"
						style="background:#FFFFFF;box-shadow: inset 1px 1px 1px 1px #D7D7D7">
					<md-card flex="100" class="md-default-theme card"
						style="padding: 11px;font-family:sans-serif;font-size:.8em;line-height: 1.214em;font-weight:300;color: black;display: -webkit-box;">

					<div layout="row" ng-if="executive.tName!='LOAN ELIGIBILTY'"
						ng-repeat="executive in newexecutive.doubleValList"
						style="padding: 0px; height: auto; display: block; width: 50%;">
						<span class="catExecheader">{{executive.tName}}</span>
						<md-card-content style="padding:0px;height:auto;">
						<table flex-gt-sm="100" flex-gt-md="100"
							class="table table-bordered table-striped"
							style="height: auto; overflow-x: hidden; margin-bottom: 0px;">
							<tbody style="overflow-y: hidden;">
								<tr>
									<td class="key">{{executive.resultList[0].key}}</td>
									<td class="value">{{executive.resultList[0].val1}}</td>
									<td class="value">{{executive.resultList[0].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[1].key}}</td>
									<td class="value">{{executive.resultList[1].val1}}</td>
									<td class="value">{{executive.resultList[1].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[2].key}}</td>
									<td class="value">{{executive.resultList[2].val1}}</td>
									<td class="value">{{executive.resultList[2].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[3].key}}</td>
									<td class="value">{{executive.resultList[3].val1}}</td>
									<td class="value">{{executive.resultList[3].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[4].key}}</td>
									<td class="value">{{executive.resultList[4].val1}}</td>
									<td class="value">{{executive.resultList[4].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[5].key}}</td>
									<td class="value">{{executive.resultList[5].val1}}</td>
									<td class="value">{{executive.resultList[5].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[6].key}}</td>
									<td class="value">{{executive.resultList[6].val1}}</td>
									<td class="value">{{executive.resultList[6].val2}}</td>
								</tr>
								<tr>
									<td class="key">{{executive.resultList[7].key}}</td>
									<td class="value">{{executive.resultList[7].val1}}</td>
									<td class="value">{{executive.resultList[7].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[8].key}}</td>
									<td class="value">{{executive.resultList[8].val1}}</td>
									<td class="value">{{executive.resultList[8].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[9].key}}</td>
									<td class="value">{{executive.resultList[9].val1}}</td>
									<td class="value">{{executive.resultList[9].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[10].key}}</td>
									<td class="value">{{executive.resultList[10].val1}}</td>
									<td class="value">{{executive.resultList[10].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[11].key}}</td>
									<td class="value">{{executive.resultList[11].val1}}</td>
									<td class="value">{{executive.resultList[11].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[12].key}}</td>
									<td class="value">{{executive.resultList[12].val1}}</td>
									<td class="value">{{executive.resultList[12].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[13].key}}</td>
									<td class="value">{{executive.resultList[13].val1}}</td>
									<td class="value">{{executive.resultList[13].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[14].key}}</td>
									<td class="value">{{executive.resultList[14].val1}}</td>
									<td class="value">{{executive.resultList[14].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[15].key}}</td>
									<td class="value">{{executive.resultList[15].val1}}</td>
									<td class="value">{{executive.resultList[15].val2}}</td>
								</tr>
								<tr ng-if="executive.tName=='CMA'">
									<td class="key">{{executive.resultList[16].key}}</td>
									<td class="value">{{executive.resultList[16].val1}}</td>
									<td class="value">{{executive.resultList[16].val2}}</td>
								</tr>

							</tbody>
						</table>
					</div>

					</md-card> </v-pane-content> </v-pane> </v-accordion> </md-card>
				</div>


				<div flex="100" ng-repeat="data in executivemaster">
					<div layout="row" ng-repeat="(key,val) in data" layout-wrap>


						<md-card ng-if="(!newversion&&$index==0)" flex="100"
							class="md-default-theme card execard" ng-if="(val.keyval==false)">

						<md-card-content>
						<h2>Executive Summary Variable</h2>
						<table flex-gt-sm="100" st-safe-src="finalexecutivedata"
							flex-gt-md="100" st-table="executivedata"
							class="table table-bordered table-striped"
							style="width: 100%; height: auto; max-height: 36em; overflow-x: scroll;">
							<thead>
								<tr class="pattern">
									<th style="width: 315px;" st-sort="exectitle.title"
										ng-repeat="val in exectitle">{{val.title}}</th>
								</tr>
								<tr>
									<th colspan="8" style="width: 100%"><input st-search=""
										class="form-control" placeholder="search ..." type="text" /></th>
								</tr>
							</thead>
							<tbody ng-if="(ktr=='data')"
								ng-class="val.div==4?'bigtable':'cats'"
								ng-repeat="(ktr,vtr) in val"
								style="overflow-y: scroll; overflow-x: hidden;">
								<tr ng-repeat="vt in vtr">
									<td>{{$index+1}}</td>
									<td ng-repeat="(k,vd) in vt">{{vd.val|LKcomma}}</td>
								</tr>
							</tbody>
						</table>
						</md-card-content> </md-card>
						<md-card flex="100" class="md-default-theme card" layout-wrap
							style="height:auto !important;">

						<div flex layout="row">

							<md-card-content flex layout-wrap>
							<div ng-if="reasonss!=''" flex=""
								style="margin-top: 10px; background: #F9F9F9; border: 1px solid #DDDDDD;">
								<h2>Rejection Reason</h2>
								<ol style="font-size: 16px;">
									<li ng-repeat="reason in reasons">{{reason}}</li>
								</ol>
							</div>
							<h2 ng-if="commentshow">Comments & Approval</h2>

							<div flex=""
								style="margin-top: 10px; background: #F9F9F9; border: 1px solid #DDDDDD;"
								ng-repeat="comment in comments">
								<h3 style="padding-left: 20px;">{{comment.tag}}</h3>
								<p flex="" style="padding-left: 2%;">
									<b style="color: rgb(13, 93, 130);">Name</b>:&nbsp;{{comment.userId}}<span
										style="position: absolute; right: 50px;"><b
										style="color: rgb(13, 93, 130);">Commented Date & Time:</b>&nbsp;{{comment.lastModifiedDate}}</span>
								</p>
								<p flex="100" style="padding-left: 2%;"
									ng-bind-html="comment.remarks">
									<b style="color: rgb(13, 93, 130);">Comments</b>:&nbsp;
								<div style="margin: auto; margin-left: 3%;"
									ng-bind-html="TrustDangerousSnippet(comment.remarks)"></div>
								</p>
								<p ng-if="comment.tag=='CAT'" flex="100"
									style="padding-left: 2%;">
									<b style="color: rgb(13, 93, 130);">Status</b>:&nbsp;{{comment.status}}
								</p>
								<p ng-if="comment.tag=='CIBIL'" flex="100"
									style="padding-left: 2%;">
									<b style="color: rgb(13, 93, 130);">Director</b>:&nbsp;{{comment.dirId}}
								</p>
							</div>

							</md-card-content>
						</div>
						<div
							style="background: #F9F9F9; border: 1px solid #DDDDDD; padding-bottom: 15px; padding-top: 15px;"
							ng-if="!block && !isAnalyst">
							<div layout="row">
								<div flex="40" class="depend">Comment Box</div>
								<!-- <p ng-model="remarkscomment.value" medium-editor bind-options="{'toolbar': {'buttons': ['bold', 'italic', 'underline']}}" data-placeholder="Enter a description"></p> -->
								<div>
									<md-button ng-click="tc();"
										style="
    position: absolute;
    left: 67%;
    background: white;
    border: 1px solid #ccc;
    height: 31px;
    font-weight: 600;
">T
									& C</md-button>
									<trix-editor trix-initialize="trixInitialize(e, editor);"
										id="areacomment"
										style="width: 515px;overflow-y: auto;background: white;height: 212px;"
										ng-model="remarkscomment.value" angular-trix></trix-editor>
								</div>

								<span ng-if='!nochar' style="margin-left: 20px;">{{1024 -
									remarkscomment.value.length}} Characters left</span> <span
									ng-if='nochar' style="margin-left: 20px; color: red;">No
									Characters left</span>
								<p style="color: red;" ng-show="remarkscommentreq">This is
									required</p>
							</div>
							<div layout="row" id="statusdiv">
								<div flex="40" class="depend">Status</div>

								<md-button flex="10" ng-repeat="status in sta"
									ng-class="status.value ? 'md-raised md-primary' : 'md-raised'"
									ng-click="activeStatus(status,$event);" ng-model="$p.name"
									class="" id="status" ng-disabled="divstatus"
									style="margin:10px;">{{status.name}}</md-button>
								<p style="color: red;" ng-show="statusreq"
									style="margin-top: 17px;">This is required</p>

							</div>
							<!--Rejection Reasons  -->
							<div layout="row" ng-show="rejectedreason">
								<div layout="column" class="depend">Reasons</div>
								<div layout="column" style="margin-left: 34%; padding: 10px;">
									<div flex="2" ng-repeat="item in appitems">
										<input type="checkbox" id="application"
											ng-model="item.selected" value={{item.name}} class="blue"
											ng-disabled="dis"> {{ item.name }} <span
											ng-if="exists(item, selected)">selected</span>
									</div>
								</div>
							</div>
							<div layout="row" id="selectdiv" ng-show="dropdown">
								<div flex="40" class="depend">Please Select</div>
								<div layout="column" style="max-height: 100px; overflow: auto;">
									<select ng-model="user.value" size="1">
										<option value="">Please select</option>
										<option ng-repeat="send in sendbck track by $index"
											value="{{send.id}}">{{send.name}}</option>
									</select>
								</div>


							</div>

							<section layout="row" layout-sm="column"
								layout-align="center center" layout-wrap="">
								<md-button id="submitarea" class="md-raised md-warn"
									ng-click="confirmsubmit()" ng-disabled="divstatus">Submit</md-button>
								<div ng-if="divstatus"
									style="position: absolute; color: green; right: 463px; bottom: 29px;">{{showsubmitmsg}}</div>
							</section>
						</div>


						<!-- Credit Analyst -->
						<div
							style="background: #F9F9F9; border: 1px solid #DDDDDD; padding-bottom: 15px; padding-top: 15px;"
							ng-show="${showUpload}">
							<div layout="row">
								<div flex="40" class="depend">Comment Box</div>

								<trix-editor ng-keydown="false" id="areacomment"
									trix-initialize="trixInitialize(e, editor);"
									style="width: 515px;overflow-y: auto;background: white;height: 212px;"
									ng-model="analystcomment.value" angular-trix></trix-editor>
								<span style="margin-left: 20px;">{{1024 -
									analystcomment.value.length}} Characters left</span>
								<p style="color: red;" ng-show="remarkscommentreq">This is
									required</p>
							</div>

							<select ng-model="managerassign.name" size="1"
								style="margin-left: 42%; margin-top: 1%;">

								<option value="">Assign Manager</option>
								<option ng-repeat="manager in managerList" ng-model="managers"
									value="{{manager.id}}">{{manager.name}}</option>
							</select>

							<section layout="row" layout-sm="column"
								layout-align="center center" layout-wrap="">
								<button id="conid" class="btn btn-sm btn-warning"
									style="width: 143px; height: 30px; border-radius: 10px !important; font-size: 13px !important; margin-top: -24px; margin-left: 20%;"
									ng-disabled="(check)" ng-click="approved()">Submit for
									Approval</button>
								<div
									style="color: green; position: absolute; right: 300px; bottom: 29px;"
									ng-if="check">{{confirm}}</div>

							</section>
						</div>

						<!--End --> </md-card>
					</div>

				</div>

			</div>
			<div role="tabpanel" id="tab2-content" aria-labelledby="tab2"
				ng-switch-when="1" md-swipe-left="next()"
				md-swipe-right="previous()" layout="row">
				<div layout="row" layout-wrap style="width: 100%;"
					ng-controller="EditableTableCtrl">
					<md-card style="width:92%;margin:auto;max-width: 98%;" flex="100">
					<md-card-content>

					<h2>Quick Peek</h2>
					<!-- <div layout="row" layout-wrap> -->
					<div layout="row">
						<div class="col-md-12">
							<!-- <button class="btn btn-sm btn-warning" style="width: 143px;height: 30px;border-radius: 10px !important;font-size: 13px !important;position: absolute;right: 27px;top: -47px;"  ng-click="cibilpull()">Get CIBIL Data</button> -->
							<md-button flex="10" ng-repeat="btn in buttonlist"
								ng-class="btnr? 'changeclass' : 'md-raised'"
								ng-click="activebtns(btn.div,$event)" ng-model=""
								style="margin: 10px;width: 16% !important;height: 42px !important;border-radius: 12px !important;font-size: 12px !important;">{{btn.name}}
							</md-button>
						</div>
					</div>
					<div layout="row">
						<md-button ng-click="ShowHide();" class="md-raised"
							style="background-color: rgb(251, 132, 6);color:white;margin-left: 26px;">Show
						All</md-button>
					</div>
					</md-card-content> </md-card>
					<div flex="100" ng-repeat="data in analysismaster" layout-padding>
						<div layout="column"
							ng-repeat="(key,val) in data|object2Array|orderBy:'tableId'">
							<md-card flex="100" class="md-default-theme"
								ng-show="change==val.div||showall" ng-if="(val.keyval==true)"
								style="width:100%;margin:8px auto;max-width:100%;"> <md-card-content
								layout-align="center center">
							<h2>{{val.tabledesc}}</h2>
							<div ng-if="(val.tabledesc!='PRODUCT')" layout="column"
								ng-repeat="(k,v) in val" class="ng-scope" layout="center center">
								<div layout="row" ng-repeat="(ke,va) in v" layout-wrap>
									<div flex="15" ng-repeat="(kt,values) in va">
										<span flex=""> <b ng-if="($index%2==0)"
											style="color: rgb(13, 93, 130);">{{values.val|LKcomma}}</b>
											<p ng-if="($index%2!=0)" style="">{{values.val|LKcomma}}</p>
										</span>
									</div>
								</div>
							</div>
							<div ng-if="(val.tabledesc=='PRODUCT')" layout="column"
								ng-repeat="(k,v) in val" class="ng-scope" layout="center center">
								<div layout="row" ng-repeat="(ke,va) in v" layout-wrap>
									<div flex="15" ng-repeat="(kt,values) in va">
										<span flex=""> <b ng-if="($index%2==0)"
											style="color: rgb(13, 93, 130);">{{values.val}}</b>
											<p ng-if="($index%2!=0)" style="">{{values.val}}</p>
										</span>
									</div>
								</div>
							</div>
							</md-card-content> </md-card>
							<md-card flex="100" class="md-default-theme"
								ng-show="(change==val.div)||showall" ng-if="(val.keyval==false)"
								style="width:100%;margin:8px auto;max-width:100%;"> <md-card-content>
							<h2>{{val.tabledesc}}</h2>
							<table flex-gt-sm="100" st-safe-src="finalexecutivedata"
								flex-gt-md="100" st-table="executivedata"
								class="table table-bordered table-striped"
								style="width: 200%; height: auto; max-height: 44em; overflow-x: scroll;">
								<thead ng-class="val.div==4?'bigtable':val.div==2?'midwidth':''"
									ng-repeat="(kth,vth) in val">
									<tr ng-if="($index==0)" class="pattern">
										<th style="width: 5%;">#</th>
										<th st-sort="exectitle.title" ng-repeat="v in vth[0]">{{v.val|LKcomma}}</th>
									</tr>
								</thead>
								<tbody ng-if="(ktr=='data')"
									ng-class="val.div==4?'bigtable':val.div==2?'midwidth':''"
									ng-repeat="(ktr,vtr) in val">
									<tr ng-if="$index!=0" ng-repeat="vt in vtr">
										<td style="width: 15%;">{{$index}}</td>
										<td ng-repeat="(k,vd) in vt" editable-text="vd.val"
											e-form="tableform" onbeforesave="checkName($data,vd.val,k)">{{vd.val|LKcomma}}</td>
									</tr>
								</tbody>
							</table>
							</md-card-content> </md-card>
						</div>
					</div>
				</div>
			</div>
			<div role="tabpanel" id="tab3-content" aria-labelledby="tab3"
				ng-switch-when="2" md-swipe-left="next()"
				md-swipe-right="previous()" layout="row" layout-wrap
				layout="center center" ng-init="varinit()">

				<md-card layout="center center">
				<table flex-gt-sm="50" flex-gt-md="50" st-table="variablesdata"
					st-safe-src="variables" class="table table-bordered table-striped">
					<thead>
						<tr class="pattern">
							<th style="width: 266px;" st-sort="#">#</th>
							<th st-sort="variables" style="width: 266px;">Variable</th>
							<th st-sort="values" style="width: 266px;">values</th>
						</tr>
						<tr>
							<th colspan="8" style="width: 100%;"><input
								ng-model="search" class="form-control" placeholder="search ..."
								type="text" /></th>
						</tr>
					</thead>
					<tbody style="overflow: scroll;">
						<tr ng-repeat="row in variables|filter:search">
							<td>{{$index}}</td>
							<td ng-repeat="r in row">{{r}}</td>
						</tr>
					</tbody>
				</table>
				</md-card>
			</div>




			<%--Personal Discussion Tab --%>
			<div role="tabpanel" id="tab4-content" aria-labelledby="tab4"
				ng-switch-when="3" md-swipe-left="next()"
				md-swipe-right="previous()" layout="row" layout-wrap
				ng-controller="pdController" ng-init="pdinit()">
				
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableFormGeneric" onaftersave="saveGenericDetails()">

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Admin ID: </span> 
							<span e-name="adminId" e-required>{{ all.personalDetailsBean.adminid || '' }}</span>
						</div>

						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">CLS Application ID: </span> 
							<span e-name="clsAppId" e-required>{{ all.personalDetailsBean.clsappid || ''}}</span>
						</div>
						
					    
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">CL Contract ID: </span> 
							<span editable-text="all.personalDetailsBean.clcontractid" e-name="contractId" 
							e-required>{{ all.personalDetailsBean.clcontractid || '' }}</span>
						</div>


						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Product: </span> 
							<span 	editable-select="all.personalDetailsBean.product[0]" 
							 e-name="products" e-required e-ng-options="s as s for s in all.personalDetailsBean.product"> <b>{{
									all.personalDetailsBean.product[0] || '' }}</b>
							</span>
						</div>
					</div>

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Cycle: </span> 
							<span  editable-select="all.personalDetailsBean.cycle[0]"
								e-name="cycles" e-required e-ng-options="s as s for s in all.personalDetailsBean.cycle"> <b>{{
									all.personalDetailsBean.cycle[0] || '' }}</b>
							</span>
						</div>


						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Type: </span> <span
								
								editable-select="all.personalDetailsBean.type[0]"
								e-name="types" e-required
								e-ng-options="s as s for s in all.personalDetailsBean.type"> <b>{{
									all.personalDetailsBean.type[0] || '' }}</b>
							</span>
						</div>


						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">CaseType: </span> <span
								
								editable-select="all.personalDetailsBean.casetype[0]"
								e-name="caseTypes" e-required
								e-ng-options="s as s for s in all.personalDetailsBean.casetype"> <b>{{
									all.personalDetailsBean.casetype[0] || '' }}</b>
							</span>
						</div>


						<div flex="25" class="individual-layout">
							<!-- noneditable application created date (text with validation) -->
							<span class="title">Application Created Date: </span> <span
								editable-combodate="all.personalDetailsBean.appcreateddate"
								e-data-template="DD/MMM/YYYY" e-data-format="DD/MMM/YYYY"
								e-name="usappCreatedDate"><b>{{all.personalDetailsBean.appcreateddate|date:"dd/MM/yyyy"}}</b></span>
						</div>
					</div>


					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable date assigned to analyst (text with validation) -->
							<span class="title">Date Assigned to Analyst: </span> <span
								editable-combodate="all.personalDetailsBean.dateassigntoanalyst"
								e-data-template="DD/MMM/YYYY" e-data-format="DD/MMM/YYYY"
								e-name="all.personalDetailsBean.dateassigntoanalyst"><b>{{all.personalDetailsBean.dateassigntoanalyst|date:"dd/MM/yyyy"}}</b>
							</span>
						</div>

						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Date of Analysis: </span> <span
								editable-combodate="all.personalDetailsBean.dateofanalysis"
								e-data-template="DD/MMM/YYYY" e-data-format="DD/MMM/YYYY"
								e-name="all.personalDetailsBean.dateofanalysis"><b>{{all.personalDetailsBean.dateofanalysis|date:"dd/MM/yyyy"}}</b></span>
						</div>

						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Analyst Name: </span> <span
								editable-text="all.personalDetailsBean.analystname" e-name="analystName"
								e-required>{{ all.personalDetailsBean.analystname || '' }}</span>
						</div>

						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Lead Source: </span> <span e-name="name"
								e-required>{{ all.personalDetailsBean.leadsource || '' }}</span>
						</div>
					</div>

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Lead Source Others: </span> <span
								editable-text="all.personalDetailsBean.others" e-name="name" e-required>{{
								all.personalDetailsBean.others || '' }}</span>
						</div>

						<div flex="25" class="individual-layout">
							<!-- loan purpose status (select-local) -->
							<span class="title">Loan Purpose: </span> <span
								 editable-select="all.personalDetailsBean.loanpurpose[0]"
								e-required e-name="purpose"
								e-ng-options="s as s for s in all.personalDetailsBean.loanpurpose"> <b>{{
									all.personalDetailsBean.loanpurpose[0] || '' }}</b>
							</span>
						</div>

						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Loan Eligible: </span> <span
								editable-number="all.personalDetailsBean.loanapplied" e-step="any"
								e-name="name" e-required>{{ all.personalDetailsBean.loaneligible || ''
								}}</span>
						</div>


						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Loan Applied: </span> <span
								editable-number="all.personalDetailsBean.loanapplied" e-step="any"
								e-name="laon-applied" e-required>{{ all.personalDetailsBean.loanapplied || ''
								}}</span>
						</div>
					</div>


					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableFormGeneric.$show()" ng-show="!editableFormGeneric.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableFormGeneric.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableFormGeneric.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableFormGeneric.$waiting"
								ng-click="editableFormGeneric.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>
				
				
				
				<!-- Batch 3 -->
					
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableForm" onaftersave="saveGenericDetails()">

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Company Name: </span> <span
							e-name="companyname" e-required>{{ all.personalCompanyBean.companyname ||
							'' }}</span>
						</div>
					
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Client's Full Name: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.clientfullname ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Principal Contacted Person: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.contactperson ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Contact Details 1: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.contactdetails1 ||
								'' }}</span>
						</div>
					</div>
					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Email Address 1: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.emailaddress1 ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Relationship: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.relationship1 ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Contact Person 2: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.contactperson2 ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Contact Details 2: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.contactdetails2 ||
								'' }}</span>
						</div>	
					</div>


					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Email Address 2: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.emailaddress2 ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Relationship: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.relationship2 ||
								'' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
								<!-- editable username (text with validation) -->
								<span class="title">Constitution: </span> <span
								e-name="clsAppId" e-required>{{ all.personalCompanyBean.constitution ||
								'' }}</span>
						</div>
					</div>

					<div layout="row" class="row-layout">
						<div flex="25" class="individual_layout">
							<!-- editable username (text with validation) -->
							<span class="title">Client's Full Name: </span> <span
							e-name="clsAppId" e-required>{{ user.data.clsAppID ||
							'' }}</span>
						</div>
						
					</div>
					
					<div layout="row" class="row-layout">
					
					</div>

					<div layout="row" class="row-layout">
					
					</div>
					
					<div layout="row" class="row-layout">
					
					</div>
					
						
					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableForm.$show()" ng-show="!editableForm.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableForm.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableForm.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableForm.$waiting"
								ng-click="editableForm.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>	
					
				<!-- Batch 4 -->
				
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableFormKYC" onaftersave="saveKYCDetails()">

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Company PAN: </span> 
							<span editable-text="all.personalKYCBean.companypan" e-name="company-pan" 
							e-required>{{ all.personalKYCBean.companypan || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Registered Address: </span> 
							<span editable-text="all.personalKYCBean.registeredaddress" e-name="registeredaddress" 
							e-required>{{ all.personalKYCBean.registeredaddress || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Ownership: </span> 
							<span 	editable-select="all.personalKYCBean.ownership[0]" 
							 e-name="ownership" e-required e-ng-options="s as s for s in all.personalKYCBean.ownership"> <b>{{
									all.personalKYCBean.ownership[0] || '' }}</b>
							</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">PinCode: </span> 
							<span editable-text="all.personalKYCBean.pincode" e-name="pincode" e-pattern="[1-9]\d{5}" e-title="xxxxxx"
							e-required>{{ all.personalKYCBean.pincode || '' }}</span>
						</div>
					</div>

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">City: </span> 
							<span editable-text="all.personalKYCBean.clcontractid" e-name="contractId" 
							e-required>{{ all.personalKYCBean.clcontractid || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">State: </span> 
							<span editable-text="all.personalKYCBean.clcontractid" e-name="contractId" 
							e-required>{{ all.personalKYCBean.clcontractid || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Communication Address 1: </span> 
							<span editable-text="all.personalKYCBean.communicationaddress" e-name="registeredAddress" 
							e-required>{{ all.personalKYCBean.communicationaddress || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Warehouse Address 1: </span> 
							<span editable-text="all.personalKYCBean.warehouseaddress" e-name="wareHouseAddress" 
							e-required>{{ all.personalKYCBean.warehouseaddress || '' }}</span>
						</div>
					</div>


					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Ownership: </span> 
							<span 	editable-select="all.personalKYCBean.whownership[0]" 
							 e-name="whOwnership" e-required e-ng-options="s as s for s in all.personalKYCBean.whownership"> <b>{{
									all.personalKYCBean.whownership[0] || '' }}</b>
							</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">PinCode: </span> 
							<span editable-text="all.personalKYCBean.whPinCode" e-pattern="[1-9]\d{5}" e-name="whPinCode" 
							e-required>{{ all.personalKYCBean.whPinCode || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">City: </span> 
							<span editable-text="all.personalKYCBean.whCity" e-name="whCity" 
							e-required>{{ all.personalKYCBean.whCity || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">State: </span> 
							<span editable-text="all.personalKYCBean.whState" e-name="whState" 
							e-required>{{ all.personalKYCBean.whState || '' }}</span>
						</div>
					</div>

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">VAT Number: </span> 
							<span editable-text="all.personalKYCBean.vatnum" e-name="vat-number" 
							e-required>{{ all.personalKYCBean.vatnum || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Number of VAT: </span> <span
								editable-number="all.personalKYCBean.numofvat" e-step="any"
								e-name="number-vat" e-required>{{ all.personalKYCBean.numofvat || ''
								}}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Service Tax: </span> 
							<span editable-text="all.personalKYCBean.servicetax" e-name="servictax" 
							e-required>{{ all.personalKYCBean.servicetax || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Number of Service Tax: </span> <span
								editable-number="all.personalKYCBean.numofservicetax" e-step="any"
								e-name="number-servicetax" e-required>{{ all.personalKYCBean.numofservicetax || ''
								}}</span>
						</div>
					</div>


					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableFormKYC.$show()" ng-show="!editableFormKYC.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableFormKYC.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableFormKYC.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableFormKYC.$waiting"
								ng-click="editableFormKYC.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>	
				
				<!-- Batch 5 -->
				
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableForm" onaftersave="saveGenericDetails()">

					<div layout="row" class="row-layout">
						
					</div>

					<div layout="row" class="row-layout">
				
					</div>


					<div layout="row" class="row-layout">
					
					</div>

					<div layout="row" class="row-layout">
				
					</div>


					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableForm.$show()" ng-show="!editableForm.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableForm.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableForm.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableForm.$waiting"
								ng-click="editableForm.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>	
				
				<!-- Batch 6 -->
				
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableForm" onaftersave="saveGenericDetails()">

					<div layout="row" class="row-layout">
						
					</div>

					<div layout="row" class="row-layout">
				
					</div>


					<div layout="row" class="row-layout">
					
					</div>

					<div layout="row" class="row-layout">
				
					</div>


					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableForm.$show()" ng-show="!editableForm.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableForm.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableForm.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableForm.$waiting"
								ng-click="editableForm.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>	
				
				<!-- Batch 7 -->
				
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableFormSocialPresence" onaftersave="saveSocialPresenceDetails()">

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Social Presence: </span> 
							<span 	editable-select="all.pdSocialPresenceBean.socialpresence[0]" 
							 e-name="socialPresence" e-required e-ng-options="s as s for s in all.pdSocialPresenceBean.socialpresence"> <b>{{
									all.pdSocialPresenceBean.socialpresence[0] || '' }}</b>
							</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Own Website: </span> 
							<span 	editable-select="all.pdSocialPresenceBean.ownwebsite[0]" 
							 e-name="own-website" e-required e-ng-options="s as s for s in all.pdSocialPresenceBean.ownwebsite"> <b>{{
									all.pdSocialPresenceBean.ownwebsite[0] || '' }}</b>
							</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Number of Comments On Website: </span> <span
								editable-number="all.pdSocialPresenceBean.comments" e-step="any"
								e-name="number-comments" e-required>{{ all.pdSocialPresenceBean.comments || ''
								}}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">JustDial: </span> 
							<span editable-text="all.pdSocialPresenceBean.justdial" e-name="justdial" 
							e-required>{{ all.pdSocialPresenceBean.justdial || '' }}</span>
						</div>
					</div>

					<div layout="row" class="row-layout">
					
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Page On Facebook: </span> 
							<span 	editable-select="all.pdSocialPresenceBean.pageonfb[0]" 
							 e-name="page-fb" e-required e-ng-options="s as s for s in all.pdSocialPresenceBean.pageonfb"> <b>{{
									all.pdSocialPresenceBean.pageonfb[0] || '' }}</b>
							</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Page On Linkedin: </span> 
							<span 	editable-select="all.pdSocialPresenceBean.pageonlinkedin[0]" 
							 e-name="page-linkedin" e-required e-ng-options="s as s for s in all.pdSocialPresenceBean.pageonlinkedin"> <b>{{
									all.pdSocialPresenceBean.pageonlinkedin[0] || '' }}</b>
							</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable status (select-local) -->
							<span class="title">Related to any Political Party: </span> 
							<span 	editable-select="all.pdSocialPresenceBean.politicalparty[0]" 
							 e-name="political-party" e-required e-ng-options="s as s for s in all.pdSocialPresenceBean.politicalparty"> <b>{{
									all.pdSocialPresenceBean.politicalparty[0] || '' }}</b>
							</span>
						</div>
					
					
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Remarks: </span> 
							<span editable-text="all.pdSocialPresenceBean.remarks" e-name="remarks" 
							e-required>{{ all.pdSocialPresenceBean.remarks || '' }}</span>
						</div>
					</div>


					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableFormSocialPresence.$show()" ng-show="!editableFormSocialPresence.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableFormSocialPresence.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableFormSocialPresence.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableFormSocialPresence.$waiting"
								ng-click="editableFormSocialPresence.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>	
				
				<!-- Batch 8 -->
				
				<md-card style="width:100%"> <md-content>
				<form editable-form name="editableFormSocial" onaftersave="saveGenericDetails()">

					<div layout="row" class="row-layout">
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Facebook URL: </span> 
							<span editable-text="all.pdSocialPresenceBean.remarks" e-name="remarks" 
							e-required>{{ all.pdSocialPresenceBean.remarks || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Number of Facebook Friends: </span> <span
								editable-number="all.pdSocialPresenceBean.comments" e-step="any"
								e-name="number-comments" e-required>{{ all.pdSocialPresenceBean.comments || ''
								}}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Linkedin URL: </span> 
							<span editable-text="all.pdSocialPresenceBean.remarks" e-name="remarks" 
							e-required>{{ all.pdSocialPresenceBean.remarks || '' }}</span>
						</div>
						
						<div flex="25" class="individual-layout">
							<!-- editable username (text with validation) -->
							<span class="title">Number of LinkedIn Connection: </span> <span
								editable-number="all.pdSocialPresenceBean.comments" e-step="any"
								e-name="number-comments" e-required>{{ all.pdSocialPresenceBean.comments || ''
								}}</span>
						</div>
					</div>


					<div class="buttons">
						<!-- button to show form -->
						<button type="button" class="btn btn-default"
							ng-click="editableFormSocial.$show()" ng-show="!editableFormSocial.$visible">
							Edit</button>
						<!-- buttons to submit / cancel form -->
						<span ng-show="editableFormSocial.$visible">
							<button type="submit" class="btn btn-primary"
								ng-disabled="editableFormSocial.$waiting">Save</button>
							<button type="button" class="btn btn-default"
								ng-disabled="editableFormSocial.$waiting"
								ng-click="editableFormSocial.$cancel()">Cancel</button>
						</span>
					</div>
				</form>
				</md-content> 
				</md-card>		
				
				<!-- Batch 8 Ends -->	
			</div>
			<%--End of Personal Discussion Tab--%> 
			</ng-switch> 
			</ui-view> 
		</md-content>
		</div>
	</div>

</body>

<script>
	var vid = '${vid}'
	var appid = '${loanId}';
	var applicationId = '${appId}';
	var blocked = '${blocked}';
	var uid = '${uId}';
	var rname = '${rolename}';

	console.log("Blocked is" + blocked);
</script>
</html>
