app.controller('creditController', ['$q','$scope','$sce','$mdBottomSheet','$mdSidenav','$mdDialog','creditDataservice','$window','$http','spinnerService',function($q,$scope,$sce,$mdBottomSheet, $mdSidenav, $mdDialog,creditDataService,$window,$http,spinnerService)
      {
$scope.check=false;
$scope.isAnalyst=$window.isAnalyst=="true"?true:false;
$scope.nochar=false;
$scope.analystcomment=[{value: ''}];
$scope.remarkscomment=[{value: ''}];
$scope.cibilpull=function(){
  window.open('http://app.lendingkart.com/lkart/adminlayout.html#!/loanview/'+$window.appid+'/'+$window.applicationId,'_blank');
 
  };
  if($window.vid>='4.6')
	{
	$scope.newversion=true;
creditDataService.getnewExec(function(response){
	$scope.newexecutive=response.data;
	console.log("this one "+JSON.stringify(response.data));
});
creditDataService.getTableData('SOCIAL',function(response){
	console.log(response);
	$scope.social=response.data;
	
	
	  }); 

}
	

  $scope.printDiv = function() {
	  data.selectedIndex=0;
	  
	  $window.print(function(){
		  $scope.ShowHide();
	  });
//	  var printContents = document.getElementById('printable').innerHTML;
//	  var popupWin = window.open('', '_blank', 'width=300,height=300');
//	  popupWin.document.open();
//	  popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="resources/css/print.css" /></head><body onload="window.print()">' + printContents + '</body></html>');
//	  popupWin.document.close();
	} 

  $scope.tc= function() {

   // Insert a bold “Hello” at the beginning of the document
  var element = document.querySelector("trix-editor")
 element.editor.insertHTML("<blockquote><!--block--><strong>T &amp; C</strong><br>Amount:<strong><br></strong>Rate:<br>Tenure:</blockquote>")
 element.editor.insertLineBreak()
}
  $scope.trixInitialize = function(e, editor) {
   // Select the first character in the document
   editor.setSelectedRange([0,1024])
}
$scope.toggleSidenav = function(menuId) 
 {
   $mdSidenav(menuId).toggle();
 };
 
 $scope.uploadFile = function(){
	 $scope.uploadPlaceHolder = "File Selected";
 }
 
 $scope.submitFile = function(){
	 spinnerService.show('html5spinner');
 }
 
 $scope.TrustDangerousSnippet = function(snippet) {
 return $sce.trustAsHtml(snippet);
};  

 
 $scope.managerassign=[{name:''}];
  $scope.con=$window.appid;
 $scope.approved=function(){
 $scope.check=true;
 var req={
"appId":$scope.con,
"status":"",
"userId":$window.uid,
"remarks":$scope.analystcomment.value,
"applicationId":$window.applicationId,
"assignedId": $scope.managerassign.name
};
if($scope.managerassign.name!=null||$scope.managerassign.name!='')
{
     $http.post('submitTemplate',req).success(function(response){
 console.log(response);
 $scope.confirm=response;
 if($scope.confirm != null){
	 window.location.reload();
 }
     });
             }
else{
$scope.confirm="Please assign the Manager before Approval";
}
 }

  $scope.executiveHeader=[];
     $scope.tablenme
$scope.tables = [];
var tables = [];
$scope.tablehead=[];
  var role=$window.rname;
$scope.rolecurrent=$window.rname;
$scope.analysismaster=[]
$scope.executivemaster=[];
 var finaltables = $q.defer()
 var thisone={};
 $scope.tablesk=[];
 $scope.exectitle=[
{'title':'#'},
                   {'title':'Variable'},
                   {'title':'Values'},
                   {'title':'CreditAnalyst'},
                   {'title':'CreditManager'},
                   {'title':'AVP'}, 
                   {'title':'COO'}, 
                   {'title':'Remarks'} 
                   ];
$scope.change=1;
 
$scope.buttonlist = [ 
                      //@TBD this need to redisned at will take dynamic data from the data of tablelist
                      { 
                        name: "Cycle & Product Summary",
                        div:'1' 
                           
                      }, 
                      { 
                        name: "Cibil/Non Cibil",
                        div:'2' 
                      },
                      { 
                      name: "Personal Details",
                       div:'3'
                      },
                      { 
                        name:"Bank Details",
                        div:'4'
                       },
                      { 
                       name: "Comments",
                        div:'5'
                       }];
 
$scope.showall=false;
$scope.ShowHide = function () {
    //If DIV is visible it will be hidden and vice versa.
    $scope.showall = $scope.showall ? false : true;
}
$scope.btnlist='';
$scope.tab=[];
      
$scope.keyval='';
$scope.div=[];
var getexecdat={};
var getvardat={};
var count=0;
creditDataService.getTablelist($window.vid,function(response){ ///Creation of table start here 
 
	if(response.call){
	 
		$scope.tab=response.data;
		angular.forEach($scope.tab,function(value){
			var tablenameval= value.tableName;
			var desc=value.desc;
			var div=value.div; 
			var tableId=value.tableId;
			var keyval=value.keyval;
			creditDataService.getTableData(tablenameval,function(response){
				count++;
				console.log(response);
				var tabledat=response.data;
				var tablename=tablenameval;
				console.log(tablename);
				console.log(tabledat);
				var finaldesc=desc;
				var keyvalues=keyval;
				var divvalues=div;
				var tablevalue=tableId;
				$scope.div.push({'div':divvalues});
				tabledatstring=JSON.stringify(tabledat);
				var thisone=[];
				if (tabledatstring != undefined) {
					$.each($.parseJSON(tabledatstring), function(k, v) {
						thisone.push(v.resultList);
					});
				}
				
				if(divvalues=='6'||tablename=='6'){
					getexecdat[tablename]={data:thisone,'keyval':keyvalues,'div':divvalues,'tabledesc':finaldesc};
				} else {
				// transactionaldetails Product personalDetails comment-1 ,comment-2,personalDetails,cumulative
					getvardat[tablename]={data:thisone,'keyval':keyvalues,'div':divvalues,'tableId':tablevalue,'tablename':tablename,'tabledesc':finaldesc};
				}
				if(keyvalues==false) {
					tables=thisone[0];
					for(var i=0;i<=thisone[0].length;i++) {
				         $scope.tablehead.push({'val':tables[i].val,'table':tablename});
					}
				}
			 }); 
		});
		$scope.executivemaster.push(getexecdat);
		    $scope.analysismaster.push(getvardat);
	}; 
});

$scope.varinit=function()
{
creditDataService.getVariable(function(response){
$scope.variables=response.data; 
});
}

$scope.execinit=function()
{
creditDataService.getVariable(function(response){
$scope.variables=response.data; 
});
}

$scope.download=function()
{
return window.location.href="/CreditTemplate/downloadExcel/"+$window.applicationId;
}
$scope.activebtns = function(btn,event) {
event.preventDefault();
$scope.change=btn;
   $scope.showall=false;

};
//$scope.pane.isExpanded=true;
$scope.expandCallback = function(index, id) {
	console.log('expand:', index, id);
};

$scope.collapseCallback = function(index, id) {
	console.log('collapse:', index, id);
};

$scope.$on('accordionExec:onReady', function() {
	console.log('accordionA is ready!');
});    
$scope.alert = '';
$scope.showListBottomSheet = function($event) {
$scope.alert = '';
$mdBottomSheet.show({
template: '<md-bottom-sheet style="padding-bottom:0px;"><table class="table table-striped comment" flex="100" style="height:300px;"><thead><tr><th style="width:100%;">Management & Position</th><th style="width:100%;">Comments</th><th style="width:100%;">Status</th></tr></thead><tbody><tr ng-repeat="item in items"><td  class="tdcomment" style="width:100%;">{{item.roleName}}</td><td ng-if="(item.roleName==role)" style="width:100%;" class="tdcomment" editable-textarea="item.remarks" onaftersave="updateComment(item)">{{item.remarks}}</td><td ng-if="(item.roleName!=role)" class="tdcomment">{{item.remarks}}</td><td style="width:100%;" ng-if="(item.roleName==role)"><a href="#" editable-radiolist="item.status" onaftersave="updateComment(item)" e-ng-options="s.value as s.text for s in statuses">{{item.status}}</a></td><td style="width:100%;"ng-if="(item.roleName!=role)"><a href="#" e-ng-options="s.value as s.text for s in statuses" style="text-decoration:none!important;">{{item.status}}</a></td></tr></tbody></table></md-bottom-sheet>',
 controller: 'CommentCtrl',
 targetEvent: $event
   })
 };
}]);

 app.controller('CommentCtrl',function($scope,$filter,$window,$mdBottomSheet,$filter,$timeout,creditDataservice,$http,spinnerService,$sce)
{ 
	$scope.commenterror=false;
	$scope.statusreq=false;
	$scope.remarkscommentreq=false;
	var app=$window.appid;
	 
	$scope.appi=$window.appid;
	$scope.loanid=$window.appid;
	$scope.vid=$window.vid;
	$scope.rname=$window.rname;
	$scope.user=[{value: ''}];
	console.log("Application id:"+$scope.appi);
	$scope.block=$window.blocked=="true"?true:false;
	 
	 
	var comments=[];
	
	$scope.role=$window.rname;
	$http.get('getComments/'+app).success(function(response){
		console.log(response);
		$scope.comments=response.unitCommentsList;
		console.log($scope.comments);
		$scope.reasonss=response.rejectionReason;
		$scope.reasons=$scope.reasonss.split(';');
		if(response == ''){
			$scope.commentshow=false;
		} else {
			$scope.commentshow=true;
		}
	}).error(function(response){
		responseData.call=false;
		responseData.data=response;
		callback(responseData);
	});
   
   
   
  /*spinnerService.show('html5spinner');
  $http.get('http://localhost:8080/lkart/api/invoices?loanId='+$scope.appi).success(function(response){
console.log(response);
$scope.invoice=response;
}).error(function(response){
console.log(response);
responseData.call=false;
responseData.data=response;
callback(responseData);

});
  spinnerService.hide('html5spinner');
*/ $scope.con=$window.appid;
 
$scope.alertfunc=function(role){
$scope.designation=role;
$scope.commenterror=true;
$timeout(function(){
$scope.commenterror=false;
},5000); 
}
$http.get('getStatusList/'+$window.rname)
.success(function(response){
$scope.sta=response;
});
$http.get('getManagerList/')
.success(function(response){
$scope.managerList=response;
});
$scope.appitems=[{
    id: 1,
    name: 'CIBIL overdue in past loan'
  },{
	  id:2,
	  name:'CIBIL overdue in current loan'
  },{
	  id:3,
	  name:'Financial Discipline: large number of cheque returns'  
  },{
	  id:4,
	  name:'Financial Discipline: over leveraged'
  },{
	  id:5,
	  name:'Financial Discipline: minimum balance low/negative'
  },{
	  id:6,
	  name:'Financial Discipline: MAB count'
  },{
	  id:7,
	  name:'Financial Discipline: CC/OD over utilized'
  },{
	  id:8,
	  name:'Financial Discipline: Decline in Credits/Revenue'
  },{
	  id:9,
	  name:'Negative verification'
  },{
	  id:10,
	  name:'Low business vintage'
  },{
	  id:11,
	  name:'Rejected due to delays in past cycle'
  },{
	  id:12,
	  name:'Politically exposed'
  },{
	  id:13,
	  name:'Fraud and willful defaulters'
  },{
	  id:14,
	  name:'Poor/Negative review on market places/social network'
  }];
   $scope.activeStatus = function(status,event) {
	   $scope.rejectedreason=false;
      event.preventDefault();
      
      if(status.name=='Rejected')
    	  $scope.rejectedreason=true;
      
      angular.forEach($scope.sta, function(value) {
      value.value = false;
     });
      $scope.statuss=status.name;
     $scope.dropdown=status.dropdown;
      status.value = !status.value;
      if($scope.dropdown==true){
      spinnerService.show('html5spinner');
      $http.get('getAssigneeList/'+$window.rname).success(function(response){
      $scope.sendbck=response; 
      console.log($scope.sendbck);
      spinnerService.hide('html5spinner');
      
      });
      
      }
      console.log($scope.dropdown);
     };
    
     
     $scope.rejectionreasonx=false;
 $scope.confirmsubmit=function()
 {
	 $scope.applicationRejectionArray = [];
	 if($scope.rejectedreason==true){
	    angular.forEach($scope.appitems, function(item){
	      if (item.selected)
	    	  $scope.applicationRejectionArray.push(item.name);
	      
	    });
	 }
console.log($scope.user.value);
$scope.divstatus=true;
if($scope.statuss==undefined){
$scope.statusreq=true;
}
else if($scope.remarkscomment.value==undefined && $scope.applicationRejectionArray.length<=0)
{
$scope.remarkscommentreq=true;
}
// else if($scope.applicationRejectionArray.length<=0)
//{
//$scope.rejectionreasonx=true;	
//}
else{
var req={
"appId":app,
"status":$scope.statuss,
"userId":$window.uid,
"remarks":$scope.remarkscomment.value,
"applicationId":$window.applicationId,
"assignedId":$scope.user.value,
"rejectionReasons":$scope.applicationRejectionArray
};
var headers={headers:{"Accept": "application/json", "Content-type":"application/json"}};
  $http.defaults.useXDomain = true;
  
  console.log(req);
$http.post('saveComments',req,headers)
  .success(function(response){
  $scope.divstatus=true;
  $scope.showsubmitmsg=response;
 
  })
}
 }
});

