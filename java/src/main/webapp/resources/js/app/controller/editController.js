app.controller('EditableTableCtrl',['$scope','$filter','$http','$q','creditDataservice','$window',function($scope,$filter, $http,$q,creditDataService,$window) {
    $scope.groups = [];
    $scope.tabledata=[];
    $scope.currenttable='';
    var counter=0;
   $scope.loadGroups = function() {
    return $scope.groups.length ? null : $http.get('/groups').success(function(data) {
      $scope.groups = data;
    });
  };

  $scope.showGroup = function(user) {
    if(user.group && $scope.groups.length) {
      var selected = $filter('filter')($scope.groups, {id: user.group});
      return selected.length ? selected[0].text : 'Not set';
    } else {
      return user.groupName || 'Not set';
    }
  };

  $scope.showStatus = function(user) {
    var selected = [];
    if(user.status) {
      selected = $filter('filter')($scope.statuses, {value: user.status});
    }
    return selected.length ? selected[0].text : 'Not set';
  };

  var i =0;
  $scope.checkName = function(data, id,k)
  {   
	   
	 
	 $scope.tabledata.push({'val':data});
		
	  
	  
    
  };
  
  // filter users to show
  $scope.filterUser = function(user) {
    return user.isDeleted !== true;
  };

  // mark user as deleted
  $scope.deleteUser = function(id) {
    var filtered = $filter('filter')($scope.users, {id: id});
    if (filtered.length) {
      filtered[0].isDeleted = true;
    }
  };

  // add user
  $scope.addUser = function() {
    $scope.users.push({
      id: $scope.users.length+1,
      name: '',
      status: null,
      group: null,
      isNew: true
    });
  };

  // cancel all changes
  $scope.cancel = function() {
    for (var i = $scope.tabledata.length; i--;) {
      var user = $scope.tabledata[i];    
      // undelete
      if (user.isDeleted) {
        delete user.isDeleted;
      }
      // remove new 
      if (user.isNew) {
        $scope.tabledata.splice(i, 1);
      }      
    };
  };
  $scope.updateVal=function(data,kt,table)
	  {
	  console.log(table);
	  var valpos=(kt+1)*2;
	  var app=$window.appid;
	  var headers={headers:{"Accept": "application/json", "Content-type":"application/json"}};
		 $http.defaults.useXDomain = true;
		
		 console.log(app);
	 var data={'appId':app,'val':data,'pos':valpos,'tableName':table};
	 console.log(data);
	 $http.post('editCard',data,headers)
		.success(function(response){
		console.log(response);
		responseData.call=true;
		responseData.data=response;
		callback(responseData);
		}).error(function(response){
			console.log(response);
			responseData.call=false;
			responseData.data=response;
			callback(responseData);
			
		});
};
  // save edits
  $scope.saveTable = function(table) {
	  $scope.currenttable=table;
	 		  $scope.finaldata={'resultList':$scope.tabledata,'appId':$window.appid,'tableName':$scope.currenttable};
	  var tabledata=JSON.stringify($scope.finaldata);
	   creditDataService.saveTableData(tabledata,function(response){
		  $scope.tabledata=[];
			  });

    
  };
}]);