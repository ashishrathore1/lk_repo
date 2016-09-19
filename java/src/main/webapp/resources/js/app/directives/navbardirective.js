app.directive('lknav', function() { 
  return { 
  	transclude: true,
    restrict: 'E', 
    scope: { 
      info: '=' 
    }, 
    templateUrl: 'resources/js/navbar.jsp'
  }; 
});