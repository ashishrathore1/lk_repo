app.controller('coordinateController', ['$scope', '$mdBottomSheet','$mdSidenav','$http','$mdDialog','coordinateDataservice',function($scope, $mdBottomSheet, $mdSidenav, $http,$mdDialog,coordinateDataservice){

	$scope.tab=[{versionid:''},{execXcoordinate:''},{execYcoordinate:''},{cycXcoordinate:''},{cycYcoordinate:''},{cibXcoordinate:''},{cibYcoordinate:''},{noncibXcoordinate:''},{noncibYcoordinate:''},{appXcoordinate:''},{appYcoordinate:''},{bankXcoordinate:''},{bankYcoordinate:''},{revXcoordinate:''},{revYcoordinate:''},{conXcoordinate:''},{conYcoordinate:''},{prevXcoordinate:''},{prevYcoordinate:''},{comXcoordinate:''},{comYcoordinate:''},{commXcoordinate:''},{commYcoordinate:''},{cmtXcoordinate:''},{cmtYcoordinate:''},{commentXcoordinate:''},{commentYcoordinate:''}]
	$scope.tablename=coordinateDataservice.gettablenames();
	//console.log($scope.tablename);
	/*$scope.xcoordinates=[
	                     {xcord:$scope.tab.execXcoordinate},
	                     {xcord:$scope.tab.cycXcoordinate},
	                     {xcord:$scope.tab.cibXcoordinate},
	                     {xcord:$scope.tab.noncibXcoordinate},
	                     {xcord:$scope.tab.appXcoordinate},
	                     {xcord:$scope.tab.bankXcoordinate},
	                     {xcord:$scope.tab.revXcoordinate},
	                     {xcord:$scope.tab.conXcoordinate},
	                     {xcord:$scope.tab.comXcoordinate},
	                     {xcord:$scope.tab.commXcoordinate},
	                     {xcord:$scope.tab.cmtXcoordinate},
	                     {xcord:$scope.tab.commentXcoordinate},
						  ];
	console.log($scope.xcoordinates);
	$scope.ycoordinates=[
	                     {ycord:$scope.tab.execYcoordinate},
	                     {ycord:$scope.tab.cycYcoordinate},
	                     {ycord:$scope.tab.cibYcoordinate},
	                     {ycord:$scope.tab.noncibYcoordinate},
	                     {ycord:$scope.tab.appYcoordinate},
	                     {ycord:$scope.tab.bankYcoordinate},
	                     {ycord:$scope.tab.revYcoordinate},
	                     {ycord:$scope.tab.conYcoordinate},
	                     {ycord:$scope.tab.comYcoordinate},
	                     {ycord:$scope.tab.commYcoordinate},
	                     {ycord:$scope.tab.cmtYcoordinate},
	                     {ycord:$scope.tab.commentYcoordinate},
						  ];
	console.log($scope.ycoordinates);*/
	
	$scope.submit = function(event) { 
		
		var tabledata=[
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Executive Summary",
		                	"X-coord":$scope.tab.execXcoordinate,
		                	"Y-coord":$scope.tab.execYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Cycle",
		                	"X-coord":$scope.tab.cycXcoordinate,
		                	"Y-coord":$scope.tab.cycYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Cibil",
		                	"X-coord":$scope.tab.cibXcoordinate,
		                	"Y-coord":$scope.tab.cibYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Non Cibil",
		                	"X-coord":$scope.tab.noncibXcoordinate,
		                	"Y-coord":$scope.tab.noncibYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Applicants",
		                	"X-coord":$scope.tab.appXcoordinate,
		                	"Y-coord":$scope.tab.appYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Bank Details Data",
		                	"X-coord":$scope.tab.bankXcoordinate,
		                	"Y-coord":$scope.tab.bankYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Revenue",
		                	"X-coord":$scope.tab.revXcoordinate,
		                	"Y-coord":$scope.tab.revYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Prevenue",
		                	"X-coord":$scope.tab.prevXcoordinate,
		                	"Y-coord":$scope.tab.prevYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Comment 1",
		                	"X-coord":$scope.tab.comXcoordinate,
		                	"Y-coord":$scope.tab.comYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Comment 1",
		                	"X-coord":$scope.tab.commXcoordinate,
		                	"Y-coord":$scope.tab.commYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Comment 1",
		                	"X-coord":$scope.tab.cmtXcoordinate,
		                	"Y-coord":$scope.tab.cmtYcoordinate
		               },
		               {
		                	"versionId":$scope.tab.versionid,
		                	"tablename":"Comment 2",
		                	"X-coord":$scope.tab.commentXcoordinate,
		                	"Y-coord":$scope.tab.commentYcoordinate
		               }
		                ];
	
			       
			     
			        console.log(tabledata);
			        var response = $http.post('form/business', tabledata );
			        $scope.list.push(tabledata);
			          response.success(function(data, status, headers, config) {
			            
			          });
			         
			          $scope.list = [];
	}
}]);