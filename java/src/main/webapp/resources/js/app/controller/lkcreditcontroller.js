
app.controller('lkCreditController', ['$scope', '$rootScope','$location', '$http','$compile','lkcreditDataservice','variabledata','$location','$window','$rootElement','$routeParams', '$route',
	function ($scope, $rootScope,$location,$http ,$compile,lkcreditDataservice,variabledata,$location,$window,$rootElement,$routeParams,$route) {

	$scope.appdetails='';
	$scope.id=$location.search().id; 
	$scope.email=$location.search().email;
	$scope.phn=$location.search().tel;
	$scope.company=$location.search().com;
	
	//console.log(id);

	
$scope.appdetails=lkcreditDataservice.getloandetails();

/*var res=$http.get('ds/getLoanVarScore?applicationId=$scope.id', { cache: true })
console.log(res);*/

$scope.cibilheader=lkcreditDataservice.gettableheaders();


//$scope.summary=lkcreditDataservice.getsummarydetails();
$scope.sheader=lkcreditDataservice.getsummaryheader();
$scope.tabledata=lkcreditDataservice. getlkcredittable();

$scope.arrvariable=[];
$scope.rowcmt=[];
var y;


$http.get('ds/getLoanVarScore?applicationId='+$scope.id).success(function(response) {
	/*console.log(response);*/
    $scope.variabledata1 = response;
    $scope.val1=$scope.variabledata1.data;
    /*console.log($scope.val1);*/
   if($scope.val1!=undefined){
    $scope.scolumn=lkcreditDataservice.getsummarycolumn($scope.val1.loanScores);
   
    $scope.summary=lkcreditDataservice.getsummarydetails($scope.val1.loanScores);
    $scope.values = $scope.val1.loanVarScores;
    }

    
   

 });
/*console.log( $scope.variabledata1);*/
//variabledata.then(function(response){
	
	  /*$scope.datavar=response.data;
	  $scope.val1=$scope.datavar.data;
	  //varScore
	  $scope.scolumn=lkcreditDataservice.getsummarycolumn($scope.val1.loanScores);
	  console.log($scope.val1.loanScores);
	  $scope.summary=lkcreditDataservice.getsummarydetails($scope.val1.loanScores);
	
	  //console.log($scope.val1.loanScores);
	 
	  
	angular.forEach($scope.val1.loanVarScores,function(key,value){
		
		//console.log(key.varScoreBean);
		$scope.arrvariable.push(key.varScoreBean);
		
		
	});
	$scope.values = $scope.val1.loanVarScores;
	console.log($scope.summary);
*/
	  
//});
//$scope.datavar= $scope.variabledata1;
/*$scope.val1=$scope.variabledata1.data;
console.log($scope.val1);
$scope.scolumn=lkcreditDataservice.getsummarycolumn($scope.val1.loanScores);
console.log($scope.val1.loanScores);
$scope.summary=lkcreditDataservice.getsummarydetails($scope.val1.loanScores);


$scope.values = $scope.val1.loanVarScores;*/
//console.log($scope.summary);

}]);	
