app.controller('loanhistoryController', [
		'$q',
		'$scope',
		'$mdBottomSheet',
		'$mdSidenav',
		'$mdDialog',
		'creditDataservice',
		'$window',
		'$http',
		'spinnerService',
		'$timeout',
		function($q, $scope, $mdBottomSheet, $mdSidenav, $mdDialog,
				creditDataService, $window, $http, spinnerService,$timeout) {
		//	$scope.experiment=new Array();
			$scope.cibiltype=true;
			$scope.cumlative = 0;
			$scope.showcibil=false;
			$scope.cflag = false;
			var v = [];
			var calccumlative = function() {
				var tot = 0;
				for (var i = 0; i < v.length; i++) {
					tot += v[i];
				}
				v = [];
				console.log(v);
				return tot;
			}
			$scope.cibilcancel=function(cibildata)
			{
				for(var k in cibildata){
					if(cibildata[k].newRecord==true){
						cibildata.pop(cibildata[k]);
				}
				
			}
				}
			$scope.loanhistory = [ {
				"key" : "loanenq",
				"name" : " No.of loan Enquiry Made By Applicant",
				"val" : 0
			}, {
				"key" : "overloancount",
				"name" : "Overdue Loan Count",
				"val" : 0
			}, {
				"key" : "outstandloancount",
				"name" : "Outstanding Laon Count",
				"val" : 0
			}, {
				"key" : "settledloancount",
				"name" : "Settled Loan Count",
				"val" : 0
			}, {
				"key" : "writtenoffloancount",
				"name" : "Written-off Loan Count",
				"val" : 0
			}, {
				"key" : "cumlative",
				"name" : "Cumlative Outstanding",
				"val" : $scope.cumlative
			} ];
			$scope.init = function() {

				spinnerService.show('html5spinner');
//                creditDataService.getsavedata(function(response) {
//                       console.log(response);
//                       
//              });

				creditDataService.Loanenq(function(response){
					
					$scope.Enq=response.data;
				
					});
				
				creditDataService.getcibildata(function(response) {
					console.log(response);

				$scope.experiment = response.data;
				angular.forEach($scope.experiment, function(value) {
					console.log(value);
					console.log(value.cibilSummaryBean.outstandingTotal);

					v.push(value.cibilSummaryBean.outstandingTotal);

					// calccumlative(parseInt(value.cibilSummaryBean.outstandingTotal));

				});
				$scope.cumlative = calccumlative();
			});
				
	
				
				creditDataService.getloantype(function(response) {
					console.log(response);
					$scope.exptypeofloan = response.data;

				});
				creditDataService.getloantypes(function(response) {
					console.log(response);
					$scope.loantypes = response.data;

				});
				creditDataService.getnoncibildata(function(response) {
					console.log(response);
					$scope.expnoncibil = response.data;
					v.push($scope.expnoncibil.totaloutstandingAmt);
					
					$scope.cumlative = calccumlative();
					
					console.log($scope.expnoncibil);
					spinnerService.hide('html5spinner');
				});
				
			}
			$scope.calculate = function() {
				$scope.cflag = true;

				creditDataService.getcalculate(function(response) {

					console.log(response);
					$scope.init();
					$scope.cflag = false;
				});

			}
			
			$scope.status = [ {
				text : 'Sanctioned',
				value : 'Sanctioned'
			}, {
				text : 'Settled',
				value : 'Settled'
			}, {
				text : 'Written-off',
				value : 'Written-off'
			}, {
				text : 'Closed',
				value : 'Closed'
			}, {
				text : 'Sub-standard',
				value : 'Sub-standard'
			}, {
				text : 'Other',
				value : 'Other'
			},
			{
				text : 'Overdue',
				value : 'Overdue'
			}];
			
			
			$scope.groups = [];
			$scope.loadGroups = function() {
				return $scope.groups.length ? null : $http.get('/groups')
						.success(function(data) {
							$scope.groups = data;
						});
			};

			$scope.showGroup = function(user) {
				if (user.group && $scope.groups.length) {
					var selected = $filter('filter')($scope.groups, {
						id : user.group
					});
					return selected.length ? selected[0].text : 'Not set';
				} else {
					return user.groupName || 'Not set';
				}
			};

			$scope.showStatus = function(user) {
				var selected = [];
				if (user.status) {
					selected = $filter('filter')($scope.statuses, {
						value : user.status
					});
				}
				return selected.length ? selected[0].text : 'Not set';
			};
       
			$scope.savenoncibil = function(dataz) {
				
				var validdate = true;
				var gg;
				for (var i = 0; i < dataz.length; i++) {
					
					if(!dataz[i]["openeddate"].match('^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)$')){
		        		validdate=false;
		        		gg = i+1; 
		        		break;
		        	} 
					
					dataz[i]["loanId"] = $window.appid;
				}
				var responseData;
				if(validdate){
				$http.post('/CreditTemplate/saveNonCibil', dataz)
				.success(function(response) {
					responseData = response;
					console.log(responseData);
				}).error(function(response) {
					responseData = response;
					console.log(responseData);

				});
				}
				else{
					alert("Could not save , due to incorrect date pattern"+gg);
				}
			};
			$scope.savecibil = function(dataz, applicantid) 
			{
				console.log(dataz);
				
				for (var i = 0; i < dataz.length; i++) 
				{
					
					dataz[i]["applicantId"] = applicantid;
					if(dataz[i]['newRecord']==true)
					{
						if(dataz[i]['type']==''||dataz[i]['status']=='')
							{
							$scope.cibiltype=false;
							break;
							}
						else{
							$scope.cibiltype=true;
						}
					}

				}

				var responseData
               	console.log(dataz);
				$scope.sample = dataz;
				if($scope.cibiltype)
				{	
				
					console.log("GGGGGGGGGGGGGGGGGG");
				$http.post('/CreditTemplate/saveCibil/'+$window.appid, dataz)
				.success(function(response) {
					   
                      $scope.cibilMessage=response;
                      $scope.showcibil=true;
                      $timeout(function(){$scope.showcibil=true;},3000);
					responseData = response;
					console.log(responseData);
				}).error(function(response) {
					// console.log(response)
					$scope.showcibil=true;
					$scope.cibilMessage=response;
					responseData = response;
					console.log(responseData);

				});
			}
				else{
					
					console.log("NHP");
					$scope.showcibil=true;
					$scope.cibilMessage="Could Not Save either the Type of loan or Status is empty";
					$timeout(function(){
						$scope.showcibil=false;
						$scope.cibilMessage='';
					},10000);
				
				}
				return responseData
			};

			// remove user
			$scope.removeUser = function(index) {
				$scope.users.splice(index, 1);
			};

			// add user
			$scope.addUser = function() {
				$scope.inserted = {
					"particulars" : "",
					"typeofloan" : "",
					"openeddate" : "",
					"outstanding" : '',
					"emi" : '',
					"category" : "",
					"duration" : '',
					"loanId" : '',
					"sanctionedamt" : ''
				};
				$scope.expnoncibil.nonCibilBeanList.push($scope.inserted);
			};
			
			$scope.addUsernon = function(applicantid,index) {
				//$scope.experiment[0].cibilDataList={};
				$scope.insertcibil = {
					"type" :'',
					"sancAmt" :'',
					"status" : '',
					"outStnding":'',
					"maxLateDays":0,
					"amtOverdue" :0,
					"delayMonthsCount" :0,
					"lastDelay" :999,
					"tenure" :0,
					"rate" :0,
					"category":'',
					"curnonCur":'',
					"applicantId":applicantid,
					"date":'',
					"newRecord":true,
					"cibilId":-1
				};
				//console.log($scope.experiment[0]);
//				if()
			$scope.experiment[index].cibilDataList.push($scope.insertcibil);
			};

			$scope.cibil = [ {
				"title" : "Type Of Loan"
			}, {
				"title" : "Sanctioned Amount"
			}, {
				"title" : "Outstanding"
			}, {
				"title" : "Status"
			}, {
				"title" : "Max Late Days"
			}, {
				"title" : "Amount Overdue"
			}, {
				"title" : "Delay Month Count"
			}, {
				"title" : "Month Since Last Delay"
			}, {
				"title" : "Tenure"
			}, {
				"title" : "Intrerest Rate"
			}, {
				"title" : "Date(Mandatory)"
			}, {
				"title" : "Category"
			}, {
				"title" : "CurrentNon."
			} ];
			$scope.noncibil = [ {
				"title" : "Type Of Loan"
			}, {
				"title" : "Sanctioned Amount"
			}, {
				"title" : "Outstanding"
			}, {
				"title" : "EMI"
			}, {
				"title" : "Duration"
			}, {
				"title" : "Particulars"
			}, {
				"title" : "Date(Mandatory)"
			}, {
				"title" : "Category"
			}, {
				"title" : "Current/Non-Current"
			} ]
			$scope.typeofloan = [ {
				"title" : "Type Of Loan"
			},{
				"title" : "Sanctioned Amount"
			},{
				"title" : "Outstanding"
			},{
				"title" : "Weighted Amount A"
			},{
				"title" : "Remark 1"
			},{
				"title" : "Weighted Amount B"
			},{
				"title" : "Remark 2"
			} ]
			$scope.expandCallback = function(index, id) {
				console.log('expand:', index, id);
			};

			$scope.collapseCallback = function(index, id) {
				console.log('collapse:', index, id);
			};

			$scope.$on('accordionB:onReady', function() {
				console.log('accordionA is ready!');
			});

		} ]);
