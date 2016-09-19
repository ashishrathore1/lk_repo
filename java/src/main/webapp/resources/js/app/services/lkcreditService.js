app.factory('lkcreditDataservice', function($http) {
 return{
	getloandetails:function(){
    	return [
  	          {title:'Application Id',value:'LAI-100003141'},
  	          {title:'Email Id',value:'azharahmed41@yahoo.com'},
  	          {title:'Phone',value:'8652660661'},
  	          {title:'Company',value:'TRY ME'}
  	         
  	           ];	
    },
	gettableheaders:function(){
    	return [
  	          {title:'Number'},
  	          {title:'Variables'},
  	          {title:'Definition'},
  	          {title:'Derived Value'},
  	          {title:'Weightage'},
  	          {title:'Lk Credit Score'}
  	       /*   {title:'LK Credit Score'}
  	         */
  	           ];	
    },
    getsummarydetails:function(loanScores1){
    	return [
  	          {title:'LK Credit Score',value:loanScores1.score},
  	          {title:'Scale',value:''},
  	          {title:'Percentile',value:''}
  	       
  	           ];	
    },
    getsummaryheader:function(){
    	return [
    	       {title:'#'},
  	          {title:'Score'},
  	          {title:'Percentile'}
  	       
  	           ];
    },
    getsummarycolumn:function(loanScores){
    	return [
     	       {title:'Financial Health Score',value:loanScores.financialHealthScore,percentile:loanScores.financialPercentile},
   	          {title:'Marketplace performance score',value:loanScores.marketplacePerformanceScore,percentile:loanScores.marketplacePercentile},
	          {title:'Social Reliabilty Score',value:loanScores.socialReliabiltyScore,percentile:loanScores.socialPercentile},
	          {title:'Statutory Compliance',value:loanScores.statutoryCompliance,percentile:loanScores.statutoryPercentile}
   	       
   	           ];
    	
    	
    },
    getlkcredittable:function(){
    	return [
      	       {variable:'avgchqrtn',def:'Average cheque return',weight:'2.72',lkscore:'1033'},
      	     {variable:'busNatu',def:'Business nature: Manufacturing and selling, Trading online and offline, Trading offline only',weight:'2.78',lkscore:'1033'},
      	   {variable:'bustyp',def:'Business type: Partnership, Private Ltd., LLP, Proprietorship',weight:'1.47',lkscore:'1033'},
      	 {variable:'cashdep',def:'cash deposit',weight:'5.82',lkscore:'1033'},
      	{variable:'child',def:'number of children',weight:'2.48',lkscore:'1033'},
      	{variable:'CIBILn',def:'cibil score',weight:'5.05',lkscore:'1033'},
      	{variable:'CrDrR',def:'credit to debit ratio',weight:'4.83',lkscore:'1033'},
      	{variable:'eduqual',def:'educational qualification',weight:'1.65',lkscore:'1033'},
      	{variable:'empn',def:'number of employees',weight:'2.48',lkscore:'1033'},
    	{variable:'fbfrndn',def:'no. of friends in facebook',weight:'2.94',lkscore:'1033'},
    	{variable:'invt',def:'Total Inventory',weight:'3.93',lkscore:'1033'},
    	{variable:'InvtSalesR',def:'Inventory/sales ratio',weight:'1.43',lkscore:'1033'},
    	{variable:'linkedn',def:'No of friends in Linkedin',weight:'1.81',lkscore:'1033'},
    	{variable:'loanmt',def:'Loan amount',weight:'4.44',lkscore:'1033'},
    	{variable:'MarketavgSc',def:'Market Average Score',weight:'3.08',lkscore:'1033'}, 
    	{variable:'ownhouse',def:'Own house : owned,  Owned by parents, Rented',weight:'1.38',lkscore:'1033'}, 
    	{variable:'parentdp',def:'Parent dependency',weight:'1.13',lkscore:'1033'},
    	{variable:'pastJob',def:'Past Job',weight:'1.08',lkscore:'1033'},
    	{variable:'prodcat',def:'Product category',weight:'4.15',lkscore:'1033'},
    	{variable:'ref',def:'Reference: Family,Friend, No-ref, Vendor/supplier',weight:'1.79',lkscore:'1033'},
    	{variable:'salesavg3m',def:'Sales avg over 3months',weight:'3.61',lkscore:'1033'},
    	{variable:'salesg12',def:'sales grwoth of 1st over 2nd month',weight:'6.08',lkscore:'1033'},
    	{variable:'salesg23',def:'sales growth  of 2nd over 3rd month',weight:'2.84',lkscore:'1033'},
    	{variable:'SOACount',def:'Statement of account ',weight:'3.27',lkscore:'1033'},
    	{variable:'spouseemp',def:'Spouse employment: Not working, Salaried,Self employeed, Working ',weight:'1.13',lkscore:'1033'},
    	{variable:'totliab',def:'Total liability',weight:'5.48',lkscore:'1033'},
    	{variable:'VATn',def:'Number of VAT',weight:'4.14',lkscore:'1033'},
    	{variable:'vintoff',def:'Vintage Offline',weight:'5.84',lkscore:'1033'},
    	{variable:'vinton',def:'Vintage Online',weight:'6.24',lkscore:'1033'},
    	{variable:'DrRatioN',def:'debit to credit ratio',weight:'4.91',lkscore:'1033'},
    	           ];
    }
   
 }
});
app.factory('variabledata', function ($http) {
	//console.log($scope.id);
	  return $http.get('ds/getLoanVarScore?applicationId=LAI-100003141', { cache: true });
	});