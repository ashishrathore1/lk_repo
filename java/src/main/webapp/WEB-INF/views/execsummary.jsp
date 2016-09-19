<div role="tabpanel"
					id="tab1-content"
                   aria-labelledby="tab1"
                   ng-switch-when="0"
                   md-swipe-left="next()"
                   md-swipe-right="previous()"
                   layout="row" layout-wrap ng-controller="CommentCtrl">

          <div flex="100"  ng-repeat="data in executivemaster">
          <div layout="row" ng-repeat="(key,val) in data" layout-wrap>
                     
           <md-card flex="100" class="md-default-theme card" ng-if="(val.keyval==true)">
                 
                    <md-card-content layout-align="center center">
                    <h2>Executive Summary Information</h2>
                    <div layout="column" ng-repeat="(k,v) in val" class="ng-scope"  layout="center center">	
                      <div layout="row" ng-repeat="(ke,va) in v"  layout-wrap>
                      
                      <div flex="15" ng-repeat="(kt,values) in va">
                      <span flex=""><b  style="color:rgb(13, 93, 130);" ng-if="($index%2==0)">{{values.val}}:</b><p style=""ng-if="($index%2!=0)">{{values.val}}</p></span>
                      </div></div>
                      </div>
                      </md-card-content>
                  </md-card>
                  <md-card flex="100" class="md-default-theme card" ng-if="(val.keyval==false)">
               
                      <md-card-content>
                      <h2>Executive Summary Variable</h2>
                      <table flex-gt-sm="100" st-safe-src="finalexecutivedata" flex-gt-md="100" st-table="executivedata" class="table table-bordered table-striped" style="width:100%;height:auto;max-height:36em;">
                      <thead><tr class="pattern"><th style="width:315px;" st-sort="exectitle.title" ng-repeat="val in exectitle">{{val.title}}</th></tr>
		 	          <tr><th colspan="8" style="width:100%"><input st-search="" class="form-control" placeholder="search ..." type="text"/></th></tr></thead>
			        <tbody ng-if="(ktr=='data')" ng-class="val.div==4?'bigtable':'cats'" ng-repeat="(ktr,vtr) in val" >
			        <tr ng-if="$index!=0"ng-repeat="vt in vtr">
			        <td>{{$index}}</td>
			        <td ng-if="(k<3)"  ng-repeat="(k,vd) in vt">{{vd.val|LKcomma}}</td>
			        </tr>
			        </tbody>
			          </table>
                      </md-card-content>
                  </md-card>
                  <md-card ng-if="(key=='execSummValues') || commentshow"flex="100" class="md-default-theme card" layout-wrap style="height:auto !important;">                 
				 
				  <div flex layout="row">

				  <md-card-content flex layout-wrap >
                <h2 ng-if="commentshow">Comments & Approval</h2>
            
                      <div flex="" style="margin-top:10px;background: #F9F9F9;border: 1px solid #DDDDDD;" ng-repeat="comment in comments">
                      <h3 style="padding-left: 20px;">{{comment.tag}}</h3>
                      <p flex="" style="padding-left:2%;"><b style="color:rgb(13, 93, 130);">Name</b>:&nbsp;{{comment.userId}}<span style="position: absolute;right: 50px;"><b style="color:rgb(13, 93, 130);">Commented Date & Time:</b>&nbsp;{{comment.lastModifiedDate}}</span></p>
                      <p flex="100" style="padding-left: 2%;"><b style="color:rgb(13, 93, 130);">Comments</b>:&nbsp;{{comment.remarks}}</p>
                      <p ng-if="comment.tag=='CAT'" flex="100" style="padding-left: 2%;"><b style="color:rgb(13, 93, 130);">Status</b>:&nbsp;{{comment.status}}</p>
                      <p ng-if="comment.tag=='CIBIL'" flex="100" style="padding-left: 2%;"><b style="color:rgb(13, 93, 130);">Director</b>:&nbsp;{{comment.dirId}}</p>
                      </div>
                                     
           </md-card-content>
           </div>
         <div style="background: #F9F9F9;border: 1px solid #DDDDDD;padding-bottom: 15px;padding-top: 15px;" ng-if="!block && !isAnalyst">
             <div layout="row">
             <div flex="40" class="depend">Comment Box</div>
             <textarea id="areacomment" style="width: 564px;height: 63px;" ng-model="remarkscomment.value" ng-disabled="divstatus" required maxlength="1024"></textarea>
             <span style="margin-left: 20px;">{{1024 - remarkscomment.value.length}} Characters left</span>
             <p style="color:red;" ng-show="remarkscommentreq">This is required</p>
             </div>
								<div layout="row" id="statusdiv">
								<div flex="40" class="depend">Status</div>
								
								<md-button flex="10" ng-repeat="status in sta"
									ng-class="status.value ? 'md-raised md-primary' : 'md-raised'"
									ng-click="activeStatus(status,$event);" ng-model="$p.name"
									class=""  id="status" ng-disabled="divstatus" style="margin:10px;">{{status.name}}</md-button>
									<p style="color:red;" ng-show="statusreq" style="margin-top: 17px;">This is required</p>
									
									</div>
									
							<div layout="row" id="selectdiv" ng-show="dropdown">
									<div flex="40" class="depend">Please Select</div>
									<div layout="column" style=" max-height: 100px;
       overflow: auto;">
   								    <select ng-model="user.value" size="1">
   								    <option value="">Please select</option>
                                    <option ng-repeat="send in sendbck track by $index" value="{{send.id}}">{{send.name}}</option>
    								</select>
  </div>
  
		
								</div>
									
			<section layout="row" layout-sm="column" layout-align="center center" layout-wrap="">
			<md-button id="submitarea" class="md-raised md-warn" ng-click="confirmsubmit()" ng-disabled="divstatus">Submit</md-button>
			<div ng-if="divstatus" style="position: absolute;color: green;right: 463px;bottom: 29px;">{{showsubmitmsg}}</div>
			</section>
			</div>
			
			
	<!-- Credit Analyst -->
			<div style="background: #F9F9F9;border: 1px solid #DDDDDD;padding-bottom: 15px;padding-top: 15px;" ng-show="${isAnalyst} && !${submit}">
             <div layout="row">
             <div flex="40" class="depend">Comment Box</div>
             <textarea id="areacomment" style="width: 564px;height: 63px;" ng-model="analystcomment.value" ng-disabled="divstatus" required maxlength="1024"></textarea>
             <span style="margin-left: 20px;">{{1024 - analystcomment.value.length}} Characters left</span>
             <p style="color:red;" ng-show="remarkscommentreq">This is required</p>
             </div>
			<!--  <md-input-container class="md-block" flex-gt-sm>
            <label>Select Manager</label>
            <md-select ng-model="user.state">
              <md-option ng-repeat="manager in managerList" value="{{manager.id}}">
                {{manager.name}}
              </md-option>
            </md-select>
          </md-input-container> -->
		 <select ng-model="managerassign.name" size="1" style="margin-left: 42%;margin-top: 1%;">
			
   								    <option value="">Assign Manager</option>
                                    <option ng-repeat="manager in managerList" ng-model="managers" value="{{manager.id}}">{{manager.name}}</option>
    								</select> 
									
			<section layout="row" layout-sm="column" layout-align="center center" layout-wrap="">
			<button id="conid" class="btn btn-sm btn-warning" style="width: 143px;height: 30px;border-radius: 10px !important;font-size: 13px !important;margin-top: -24px;margin-left:220px;"  ng-disabled="(check)" ng-click="approved()">Submit for Approval</button>
			<div style="color: green;position: absolute;right: 300px;bottom: 29px;" ng-if="check">{{confirm}}</div>
			
			</section>
			</div>
          
    <!--End -->       
           </md-card>
                   </div>
     		 
                   </div>
               
		</div>
		