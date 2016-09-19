'use strict';
var app=angular.module('CreditApp', ['ngMaterial','ngAnimate','smart-table','ngRoute','xeditable','ui.bootstrap','angularSpinners','vAccordion','angularTrix']);
app.run(function(editableOptions, editableThemes) {
	  editableThemes.bs3.inputClass = 'input-sm';
	  editableThemes.bs3.buttonsClass = 'btn-sm';
	  editableOptions.theme = 'bs3';
	});

app.config([  
                '$locationProvider',
                function($locationProvider) {
                	$locationProvider.html5Mode({
                		enabled: true,
                		requireBase: false
                		})
                }
            ]);
app.directive('editableBootstrapDatepicker', ['editableDirectiveFactory',   function(editableDirectiveFactory) {
    return editableDirectiveFactory({
      directiveName: 'editableBsdateNew',
      inputTpl: '<span ng-datepicker ng-options="datepickerOptions"></span>'
    });   } ]);
app.filter('object2Array', function() {
    return function(input) {
        var out = []; 
        for(var i in input){
          out.push(input[i]);
        }
        return out;
      }
    });

app.directive('uiDate', function() {
    return {
      require: '?ngModel',
      link: function($scope, element, attrs, controller) {
          element.mask("99/99/9999",{completed: function() {
              controller.$setViewValue(Date.parse(this.val(),"dd/MM/yyyy"));
              $scope.$apply();
          }});
      }
    }
    });
app.filter('LKcomma', function() {
	  return function(input) {
		  var Exp ='/^([A-Za-z])+([A-Za-z0-9]+)$/i';
		 if(!input.match(Exp)&&(input.indexOf(',') > -1))
		 {
			 
			 var mid=input.replace(/[^\d\.\-\ ]/g,'');
			 var x=mid.toString();
			 var afterPoint = '';
			 if(x.indexOf('.') > 0)
			 afterPoint = x.substring(x.indexOf('.'),x.length);
			 x = Math.floor(x);
			 x=x.toString();
			 var lastThree = x.substring(x.length-3);
			 var otherNumbers = x.substring(0,x.length-3);
			 if(otherNumbers != '')
			 lastThree = ',' + lastThree;
			 var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
			 var output=res.match('^NaN')||res==0?input:res;
			 return output;
		 }
		 else
		 {
			 return input;
		 }
		 
	  };
	});
app.filter('LKcommanot', function() {
	  return function(input) {
		  var Exp ='/^([A-Za-z])+([A-Za-z0-9]+)$/i';
		// if(!input.match(Exp)&&(input.indexOf(',') > -1))
		 {
			 var mid=String(input).replace(/[^\d\.\-\ ]/g,'');
			 var x=mid.toString();
			 var afterPoint = '';
			 if(x.indexOf('.') > 0)
			 afterPoint = x.substring(x.indexOf('.'),x.length);
			 x = Math.floor(x);
			 x=x.toString();
			 var lastThree = x.substring(x.length-3);
			 var otherNumbers = x.substring(0,x.length-3);
			 if(otherNumbers != '')
			 lastThree = ',' + lastThree;
			 var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
			 var output=res.match('^NaN')||res==0?input:res;
			 return output;
		 }
		 
		 
	  };
	});
app.directive('laonHistory',[function(){
	return {
		restrict:'A',
		templateUrl :'/cibilloan.jsp'
	};
}]);

app.config(['$httpProvider', function ( $httpProvider) {        
           delete $httpProvider.defaults.headers.common['X-Requested-With'];
         //initialize get if not there
           if (!$httpProvider.defaults.headers.get) {
               $httpProvider.defaults.headers.get = {};    
           }    

           // Answer edited to include suggestions from comments
           // because previous version of code introduced browser-related errors

           //disable IE ajax request caching
           $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
           // extra
           $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
           $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
       }]);
app.directive('uiDate', function() {
    return {
      require: '?ngModel',
      link: function($scope, element, attrs, controller) {
          element.mask("99/99/9999",{completed: function() {
              controller.$setViewValue(Date.parse(this.val(),"dd/MM/yyyy"));
              $scope.$apply();
          }});
      }
    };
  });
app.directive('uimask', function() {
	  return {
		    require: 'ngModel',
		    scope: {
		      uiMask: 'evaluate'
		    },
		    link: function($scope, element, attrs, controller) {
		      controller.$render = function() {
		        var _ref;
		        element.val((_ref = controller.$viewValue) != null ? _ref : '');
		        return $(element).mask($scope.uiMask);
		      };
		      controller.$parsers.push(function(value) {
		        var isValid;
		        isValid = element.data('mask-isvalid');
		        controller.$setValidity('mask', isValid);
		        if (isValid) {
		          return element.mask();
		        } else {
		          return null;
		        }
		      });
		      return element.bind('blur', function() {
		        return $scope.$apply(function() {
		          return controller.$setViewValue($(element).mask());
		        });
		      });
		    }
		  };
		});