app.controller('mainController', ['$q','$scope','$mdBottomSheet','$mdSidenav','$mdDialog','creditDataservice','$window','$http','spinnerService',function($q,$scope,$mdBottomSheet, $mdSidenav, $mdDialog,creditDataService,$window,$http,spinnerService)
{
	$scope.check=false;
	 $scope.isAnalyst=$window.isAnalyst=="true"?true:false;
	 
	 $scope.analystcomment=[{value: ''}];
	 
	 $scope.cibilpull=function(){
		   window.open('http://app.lendingkart.com/lkart/adminlayout.html#!/loanview/'+$window.appid+'/'+$window.applicationId,'_blank');
		  
	   };
	$scope.toggleSidenav = function(menuId) 
	  {
	    $mdSidenav(menuId).toggle();
	  };
	 
	  $scope.uploadFile = function(){
		  $scope.uploadPlaceHolder = "File Selected";
	  }
	  
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
	      $http.post('submitTemplate',req).success(function(response){
		  console.log(response);
		  $scope.confirm=response;
		      });
		  }

	   $scope.executiveHeader=[];
	   $scope.sbmt=$window.submit=="true"?false:true;
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
	                    {'title':'CreditAnalyst'}                  
	                    
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
					var tabledat=response.data;
					var tablename=tablenameval;
					console.log(tablename);
					console.log(tabledat);
					var finaldesc=desc;
					var keyvalues=keyval;
					var divvalues=div;
					var tablevalue=tableId;
					$scope.div.push({'div':divvalues});
					tabledatstring=JSON.stringify(tabledat)
					var thisone=[];
					
					$.each($.parseJSON(tabledatstring), function(k, v) {
					thisone.push(v.resultList);
	
                							});
					
				
							if(divvalues=='6'||tablename=='6')
							{
								getexecdat[tablename]={data:thisone,'keyval':keyvalues,'div':divvalues,'tabledesc':finaldesc};
								   
							}
							else
							{
//								transactionaldetails Product personalDetails comment-1 ,comment-2,personalDetails,cumulative
								
									getvardat[tablename]={data:thisone,'keyval':keyvalues,'div':divvalues,'tableId':tablevalue,'tablename':tablename,'tabledesc':finaldesc};
								
								
							}
							if(keyvalues==false)
							{
								tables=thisone[0];
									for(var i=0;i<=thisone[0].length;i++)
									{
                             	$scope.tablehead.push({'val':tables[i].val,'table':tablename});
									}
									}
							
					   });						
					});
					 $scope.executivemaster.push(getexecdat);
				     $scope.analysismaster.push(getvardat);
					}; 
				
		 });
		 creditDataService.getVariable(function(response){
			 $scope.variables=response.data; 
						
		 });
	   		
	
	$scope.download=function()
	{
		 return window.location.href="/CreditTemplate/downloadExcel/"+$window.applicationId;
	}
	
	 $scope.activebtns = function(btn,event) {
		 event.preventDefault();
		$scope.change=btn;
	    $scope.showall=false;

	};
	     
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

 app.controller('CommentCtrl',function($scope,$filter,$window,$mdBottomSheet,$filter,$timeout,creditDataservice,$http,spinnerService)
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
 	  $http.get('getComments/'+app)
		.success(function(response){
			console.log(response);
			$scope.comments=response;
			console.log($scope.comments);
			if(response == ''){
			$scope.commentshow=false;
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
*/ 	 $scope.con=$window.appid;
 
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
	 
 	  $scope.activeStatus = function(status,event) {
 	      
 	     event.preventDefault();
 	     
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
 	   
 	    $scope.remarkscomment=[{value: ''}];

 $scope.confirmsubmit=function()
 {
	 console.log($scope.user.value);
	 $scope.divstatus=true;
	 if($scope.statuss==undefined){
		 $scope.statusreq=true;
	 }
	 else if($scope.remarkscomment.value==undefined){
		 $scope.remarkscommentreq=true;
	 }
	 else{
		
		 var req={
	 		 "appId":app,
			 "status":$scope.statuss,
			 "userId":$window.uid,
			 "remarks":$scope.remarkscomment.value,
			 "applicationId":$window.applicationId,
			 "assignedId":$scope.user.value
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