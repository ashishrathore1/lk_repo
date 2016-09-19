app.factory('coordinateDataservice', function($http) {
	return{
		 gettablenames:function(){
		    	return [
		  	          {name:'Executive Summary'},
		  	          {name:'Cycle'},
		  	          {name:'Cibil'},
		  	          {name:'Non Cibil'},
		  	          {name:'Applicants'},
		  	          {name:'Bank Details Data'},
		  	          {name:'Revenue'},
		  	          {name:'Prevenue'},
		  	          {name:'Comment 1'},
		  	          {name:'Comment 1'},
		  	          {name:'Comment 1'},
		  	          {name:'Comment 2'}
		  	         
		  	           ];	
		    }
	}
});