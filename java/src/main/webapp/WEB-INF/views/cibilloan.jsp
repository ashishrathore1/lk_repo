<div role="tabpanel"
					id="tab4-content"
                   aria-labelledby="tab4"
                   ng-switch-when="3"
                   md-swipe-left="next()"
                   md-swipe-right="previous()"
                   layout="row" layout-wrap ng-controller="CommentCtrl">
					
					<md-card flex="100" class="md-default-theme card" layout-wrap style="height:auto !important;">                 
				 
				  <div flex layout="row">

				  <md-card-content flex layout-wrap >
                <h2>Invoice Summary Information</h2>
            			<!-- <p flex="" style="padding-left: 2%;"><b style="color:rgb(13, 93, 130);">Name</b>:&nbsp;{{comment.userName}}<span style="position: absolute;right: 50px;"><b style="color:rgb(13, 93, 130);">Commented Date & Time:</b>&nbsp;{{comment.lastModifiedDate}}</span></p>
                      <p flex="100" style="padding-left: 2%;"><b style="color:rgb(13, 93, 130);">Comments</b>:&nbsp;{{comment.remarks}}</p>
                      <p flex="100" style="padding-left: 2%;"><b style="color:rgb(13, 93, 130);">Status</b>:&nbsp;{{comment.status}}</p> -->
            		 <!-- <div  layout="column" class="ng-scope"  layout="center center" ng-repeat="invoices in invoice"> -->
                     <div layout="row" layout-wrap ng-repeat="invoices in invoice">
                      
                      	
                      <div flex="" style="margin-top:10px;background: #F9F9F9;border: 1px solid #DDDDDD;">
                      <p flex="90" style="padding-left: 2%;margin-top: 25px;">
                      <span flex="30"><b style="color:rgb(13, 93, 130);">Invoice Number</b>:&nbsp;{{invoices.invoiceNumber}}</span>
                      <span flex="30" style="position:absolute;left: 590px;"><b  style="color:rgb(13, 93, 130);">Contact Person:</b>&nbsp;{{invoices.contactPerson}}</span>
                      <span flex="30" style="position:absolute;right:50px;"><b  style="color:rgb(13, 93, 130);">Email Id:</b>{{invoices.cemailid}}</span></p>
                      <p flex="90" style="padding-left: 2%;">
                      <span flex="30"><b style="color:rgb(13, 93, 130);">Amount</b>:&nbsp;{{invoices.amount}}</span>
                      <span flex="30" style="position:absolute;left: 590px;"><b  style="color:rgb(13, 93, 130);">Landline Number:</b>&nbsp;{{invoices.landline}}</span>
                      <span flex="30" style="position:absolute;right:50px;"><b  style="color:rgb(13, 93, 130);">Issue Date:</b>{{invoices.issueDate}}</span></p>
          
                      <p flex="90" style="padding-left: 2%;margin-bottom: 25px;">
                      <span flex="50"><b  style="color:rgb(13, 93, 130);">Pay Date:</b>{{invoices.payDate}}</span></p>
                      </div>
                       
                       </div>
                       <!-- </div>       -->      
           </md-card-content>
           </div>
           </md-card>
                 <!--   <h2>Invoice Summary Information</h2>  -->
           
                   <!-- <div flex="100"  >
          <div layout="row" layout-wrap>
                 
           <md-card flex="100" class="md-default-theme card">
                 <h2>Invoice Summary Information</h2>    
                    <md-card-content layout-align="center center" >
                    <div layout="column" class="ng-scope"  layout="center center">	
                      <div layout="row" layout-wrap>
          			<div flex="15">
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Invoice Number:</b><p style=""></p></span>
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Contact Person:</b><p style=""></p></span>
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Email Id:</b><p style=""></p></span>
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Amount:</b><p style=""></p></span>
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Landline Number:</b><p style=""></p></span>
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Issue Date:</b><p style=""></p></span>
                      <span flex=""><b  style="color:rgb(13, 93, 130);">Pay Date:</b><p style=""></p></span>
                      </div>
                      </div>
                      </div>
                      </md-card-content>
                  </md-card>
                  </div>
                  </div> -->
         
         </div>