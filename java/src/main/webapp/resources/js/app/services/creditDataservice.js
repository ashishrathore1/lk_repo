 app.factory('creditDataservice', ['$http','$window',function($http,$window) {
	 
    var urlBase = 'tableList?vid=';
    var tableurlbase='tabledata?appId='+$window.applicationId+'&tablename=';
    var creditDataService = {};
    var responseData=[];
    var editurl='/CreditTemplate/edit';
    var newexec='/CreditTemplate/getnewExecSumm/'+$window.applicationId;
    var calcbank='/CreditTemplate/bank/calAnalysisAndRevenue?appId='+$window.applicationId;
    var bankurl='/CreditTemplate/bank/getRawStatement?appId='+$window.applicationId+'&loanId='+$window.appid;
    var analysis='/CreditTemplate/bank/getAnalysisAndRevenueData?appId='+$window.applicationId+'&loanId='+$window.appid;
    creditDataService.getTablelist=function(vid,callback) {
           	
    	console.log(urlBase+vid);
    	$http.get(urlBase+vid)
    	
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
    creditDataService.getnewExec=function(callback) {
        
    	
    	$http.get(newexec).success(function(response){
    			
    			responseData.call=true;
    			responseData.data=response;
    			callback(responseData);
    		}).error(function(response){
//    			console.log(response)
    			responseData.call=false;
    			responseData.data=response;
    			callback(responseData);

    		});
     	   
        
    };
    creditDataService.getcreditlist=function(vid,callback) {
    
    	$http.get(newexec)
    	
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
    creditDataService.getsavedata=function(callback){
    	console.log('/CreditTemplate/savecibildetails/'+$window.appid);
        	$http.post('/CreditTemplate/savecibildetails/'+$window.appid)
        	
    		.success(function(response){
    			
    			responseData.call=true;
    			responseData.data=response;
    			callback(responseData);
    			console.log(response);
    		}).error(function(response){
//    			console.log(response)
    			responseData.call=false;
    			responseData.data=response;
    			callback(responseData);

    		});
        };
creditDataService.getcibildata=function(callback){
	console.log('/CreditTemplate/cibilData/'+$window.appid);
    	$http.get('/CreditTemplate/cibilData/'+$window.appid)
    	
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
    creditDataService.getrecalculatedata=function(callback){
    	console.log('/CreditTemplate/cibildetails/'+$window.appid);
        	$http.post('/CreditTemplate/cibildetails/'+$window.appid)
        	
    		.success(function(response){
    			
    			responseData.call=true;
    			responseData.data=response;
    			callback(responseData);
    		}).error(function(response){
//    			console.log(response)
    			responseData.call=false;
    			responseData.data=response;
    			callback(responseData);

    		});
        };
    creditDataService.getnoncibildata=function(callback){
    	console.log('/CreditTemplate/getNonCibilData/'+$window.appid);
        	$http.get('/CreditTemplate/getNonCibilData/'+$window.appid)
        	
    		.success(function(response){
    			
    			responseData.call=true;
    			responseData.data=response;
    			callback(responseData);
    		}).error(function(response){
//    			console.log(response)
    			responseData.call=false;
    			responseData.data=response;
    			callback(responseData);

    		});
        };
        creditDataService.getloantypes=function(callback)
        {
        $http.get('/CreditTemplate/getLoanTypes')
        .success(function(response){
        			
        			responseData.call=true;
        			responseData.data=response;
        			callback(responseData);
        		}).error(function(response){
//        			console.log(response)
        			responseData.call=false;
        			responseData.data=response;
        			callback(responseData);

        		});
            };
        creditDataService.getloantype=function(callback){
        	console.log('/CreditTemplate/getNonCibilData/'+$window.appid);
            	$http.get('/CreditTemplate/gettypeOfLoanData/'+$window.appid)
            	
        		.success(function(response){
        			
        			responseData.call=true;
        			responseData.data=response;
        			callback(responseData);
        		}).error(function(response){
//        			console.log(response)
        			responseData.call=false;
        			responseData.data=response;
        			callback(responseData);

        		});
            };
            creditDataService.getcalculate=function(callback){
            	console.log('/CreditTemplate/getNonCibilData/'+$window.appid);
                	$http.get('/CreditTemplate/reCalData/'+$window.appid)
                	
            		.success(function(response){
            			
            			responseData.call=true;
            			responseData.data=response;
            			callback(responseData);
            		}).error(function(response){
//            			console.log(response)
            			responseData.call=false;
            			responseData.data=response;
            			callback(responseData);

            		});
                };
                creditDataService.Loanenq=function(callback){
                	console.log('/CreditTemplate/getNonCibilData/'+$window.appid);
                    	$http.get('/CreditTemplate/getLoanEnq/'+$window.appid)
                    	
                		.success(function(response){
                			
                			responseData.call=true;
                			responseData.data=response;
                			callback(responseData);
                		}).error(function(response){
//                			console.log(response)
                			responseData.call=false;
                			responseData.data=response;
                			callback(responseData);

                		});
                    };
    creditDataService.getComments=function(app,response){
    	
    	$http.get('getComments/'+app)
    	
		.success(function(response){
			
			responseData.call=true;
			responseData.data=response;
			console.log(response);
			callback(responseData);
		}).error(function(response){
//			console.log(response)
			responseData.call=false;
			responseData.data=response;
			callback(responseData);

		});
    	
    };

    creditDataService.getTableData = function (tablenameval,callback) {
    	
    	$http.get(tableurlbase+tablenameval)
    	
		.success(function(response){
//			console.log(response);
			responseData.call=true;
			responseData.data=response;
			callback(responseData);
		}).error(function(response){
			responseData.call=false;
			responseData.data=response;
			callback(responseData);

		});
    };
    creditDataService.saveTableData = function (data,callback) {
    	var headers={headers:{"Accept": "application/json", "Content-type":"application/json"}};
		$http.defaults.useXDomain = true;
		console.log(editurl);
		console.log(data);
		$http.post(editurl,data,headers)
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
    creditDataService.saveComments = function (data,callback) {
    	var headers={headers:{"Accept": "application/json", "Content-type":"application/json"}};
		$http.defaults.useXDomain = true;
		console.log(editurl);
		console.log(data);
		$http.post('saveComments',data,headers)
		.success(function(response){
			
			responseData.call=true;
			responseData.data=response;
			callback(responseData);
		}).error(function(response){
			
			responseData.call=false;
			responseData.data=response;
			callback(responseData);
			
		});
		
    };

    creditDataService.getVariable = function (callback) {
    	$http.get('variables/'+$window.applicationId)    	
		.success(function(response){
			responseData.call=true;
			responseData.data=response;
			callback(responseData);
		}).error(function(response){
			
			responseData.call=false;
			responseData.data=response;
			callback(responseData);

		});
    };
    creditDataService.getbankdata=function(callback){
    	//console.log('/CreditTemplate/c /'+$window.appid);
        	$http.post(bankurl)
        	.success(function(response){
    		responseData.call=true;
    		responseData.data=response;
    		callback(responseData);
    		}).error(function(response){
    			responseData.call=false;
    			responseData.data=response;
    			
    		});
        };
        creditDataService.getanalysisandrevenue=function(callback){
        	//console.log('/CreditTemplate/c /'+$window.appid);
            	$http.post(analysis)
            	.success(function(response){
        		responseData.call=true;
        		responseData.data=response;
        		callback(responseData);
        		}).error(function(response){
        			responseData.call=false;
        			responseData.data=response;
        			callback(responseData);
        		});
            };
            creditDataService.getcalculatebank=function(databank,response){
            	//console.log('/CreditTemplate/c /'+$window.appid);
            	console.log(calcbank);
            	console.log(JSON.stringify(databank));
                	$http.post(calcbank,databank)
                	.success(function(response){
                		console.log(response)
            		responseData.call=true;
            		responseData.data=response;
            		
            		}).error(function(response){
            			responseData.call=false;
            			responseData.data=response;
            			
            		});
                };
      return creditDataService
}]);
