"undefined"!=typeof module&&"undefined"!=typeof exports&&module.exports===exports&&(module.exports="angularSpinners"),function(e,r,n){r.module("angularSpinners",[]).factory("spinnerService",function(){var e={};return{_register:function(r){if(!r.hasOwnProperty("name"))throw new Error("Spinner must specify a name when registering with the spinner service.");if(e.hasOwnProperty(r.name))throw new Error("A spinner with the name '"+r.name+"' has already been registered.");e[r.name]=r},_unregister:function(r){e.hasOwnProperty(r)&&delete e[r]},_unregisterGroup:function(r){for(var n in e)e[n].group===r&&delete e[n]},_unregisterAll:function(){for(var r in e)delete e[r]},show:function(r){var n=e[r];if(!n)throw new Error("No spinner named '"+r+"' is registered.");n.show()},hide:function(r){var n=e[r];if(!n)throw new Error("No spinner named '"+r+"' is registered.");n.hide()},showGroup:function(r){var n=!1;for(var o in e){var i=e[o];i.group===r&&(i.show(),n=!0)}if(!n)throw new Error("No spinners found with group '"+r+"'.")},hideGroup:function(r){var n=!1;for(var o in e){var i=e[o];i.group===r&&(i.hide(),n=!0)}if(!n)throw new Error("No spinners found with group '"+r+"'.")},showAll:function(){for(var r in e)e[r].show()},hideAll:function(){for(var r in e)e[r].hide()}}}),r.module("angularSpinners").directive("spinner",function(){return{restrict:"EA",replace:!0,transclude:!0,scope:{name:"@?",group:"@?",show:"=?",imgSrc:"@?",register:"@?",onLoaded:"&?",onShow:"&?",onHide:"&?"},template:['<div ng-show="show">','  <img ng-show="imgSrc" ng-src="{{imgSrc}}" />',"  <div ng-transclude></div>","</div>"].join(""),controller:["$scope","spinnerService",function(e,r){e.register=e.hasOwnProperty("register")&&"false"===e.register.toLowerCase()?!1:!0;var n={name:e.name,group:e.group,show:function(){e.show=!0},hide:function(){e.show=!1},toggle:function(){e.show=!e.show}};e.register===!0&&r._register(n),(e.onShow||e.onHide)&&e.$watch("show",function(o){o&&e.onShow?e.onShow({spinnerService:r,spinnerApi:n}):!o&&e.onHide&&e.onHide({spinnerService:r,spinnerApi:n})}),e.onLoaded&&e.onLoaded({spinnerService:r,spinnerApi:n}),e.$on("$destroy",function(){r._unregister(e.name)})}]}})}(window,window.angular);