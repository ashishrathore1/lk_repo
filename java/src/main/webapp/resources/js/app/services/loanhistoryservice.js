 /*app.factory('laonHistoryservice', ['$http','$window',function($http,$window) {

    var urlBase = 'tableList?vid=';
    var tableurlbase='tabledata?appId='+$window.applicationId+'&tablename=';
    var laonHistoryService = {};
    var responseData=[];
    var editurl='/CreditTemplate/edit';

 laonHistoryService.getcibildata=function(callback) {
    	
    	$http.get('/cibilData/'+$window.appid)
    	
		.success(function(response){
			
			responseData.call=true;
			responseData.data=response;
			callback(responseData);
		}).error(function(response){
//			console.log(response)
			responseData.call=false;
			responseData.data=response;
			callback(responseData);

		});
    };
   
      return loanHistoryService;
}]);
*/