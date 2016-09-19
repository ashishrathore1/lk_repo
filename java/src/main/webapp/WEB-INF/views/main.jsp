<div layout="row" ng-controller="creditController">

    <div layout="column" class="relative" layout-fill role="main">
     <md-toolbar ng-show="!showSearch" style="position:fixed;">
        <div class="md-toolbar-tools" style="background:white;">
          <h3>
            <md-icon md-svg-src="${context}/resources/img/1.svg" style="width: 222px;
    height: 143px;
    margin-top: 11%;" class="email"></md-icon> 
          </h3>
            <md-button ng-click="download()" aria-label="Menu">
            <md-icon md-svg-src="${context}/resources/img/down.svg"class="email" style="color: #00568D;"></md-icon>
            <md-tooltip>Click to download Excel template</md-tooltip> 
          </md-button>
        <p style="color:#00568D;position:absolute;right:7%;top:24%">${uName}<p>
        
        </div>
        
      </md-toolbar>
    <div style="
   background: #F1F1F1;
    /* padding: 0%; */
    padding-left: 4%;
    font-size: larger;
    font-weight: 800;
    /* font-family: monospace; */
    padding-top: 4%;
    font: -webkit-small-control;
"><h2>Credit Template:<i>${appId}</i></h2>

	<form method="post" action="/CreditTemplate/uploadCreditExcel" enctype="multipart/form-data" >
 				<div class="form-group">
 				<input type="hidden" name="appId"  class="form-control" value="${appId}">
		  	<input type="hidden" name="vid"  class="form-control" value="${vid}">
		  	<input type="hidden" name="uid"  class="form-control" value="${uId} ">
 				</div>
				<div class="col-md-12" ng-if="(${isAnalyst} && !${blocked}) || !${submit}">
 				<input id="uploadFile" placeholder="{{uploadPlaceHolder}}" disabled="disabled" />
				<div class="fileUpload btn btn-primary">
				<span>Choose File</span>
				<input id="uploadBtn" name="nhp" type="file" ng-click="uploadFile()" class="upload" />
				</div>
				<button class="btn btn-warning" type="submit" value="Upload Excel" name="submit">
				Submit</button>
				</div>
				
 		
            </form> 
            
<button class="btn btn-sm btn-warning" style="width: 143px;height: 30px;border-radius: 10px !important;font-size: 13px !important;position: absolute;right: 27px;top:70px;"  ng-click="cibilpull()">Get CIBIL Data</button>
<!-- <button id="conid" class="btn btn-sm btn-warning" style="width: 143px;height: 30px;border-radius: 10px !important;font-size: 13px !important;position: absolute;right: 27px;top: 70px;"  ng-disabled="check" ng-if="(sbmt && isAnalyst)" ng-click="approved()">Submit for Approval</button>
<div style="position: absolute;top:86px;right: 175px;color: green;" ng-if="check">{{confirm}}</div> -->
</div>
      <md-content flex>
          
        <ui-view layout="column" layout-fill layout-padding>
         <div class="inset" hide-sm></div>
            <ng-switch on="data.selectedIndex" class="tabpanel-container">
            <md-tabs class="md-primary" md-selected="data.selectedIndex" style="border-top-left-radius: 25px;
    border-top-right-radius: 25px;
    /* margin-left: 1%; */
    display: block;
    width:98%;
    font-weight: 500;
    overflow: auto;
    margin: 0 auto;">
          <md-tab id="tab1" aria-controls="tab1-content" ui-sref="main.execsummary">
            Executive Summary
          </md-tab>
          <md-tab id="tab2" aria-controls="tab2-content" ui-sref="main.analysis">
            Analysis report
          </md-tab>
          <md-tab id="tab3" aria-controls="tab3-content" ui-sref="main.variable">
            Variables
          </md-tab>
          <md-tab id="tab4" aria-controls="tab4-content" ui-sref="main.clbilloan">
            Loan History 
          </md-tab>
              
          
        </md-tabs>
            <div ui-view></div>  
                
		
          </ng-switch>
          
        </ui-view>
      </md-content>
    </div>
   </div> 