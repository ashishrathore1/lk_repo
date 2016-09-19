<div role="tabpanel"
id="tab2-content"
aria-labelledby="tab2"
ng-switch-when="1"
md-swipe-left="next()"
md-swipe-right="previous()"
layout="row">
<div layout="row" layout-wrap style="width:100%;" ng-controller="EditableTableCtrl">
<md-card style="width:92%;margin:auto;max-width: 98%;" flex="100">
<md-card-content>

<h2>Quick Peek</h2>
<!-- <div layout="row" layout-wrap> -->
<div layout="row">
<div class="col-md-12">
<!-- <button class="btn btn-sm btn-warning" style="width: 143px;height: 30px;border-radius: 10px !important;font-size: 13px !important;position: absolute;right: 27px;top: -47px;"  ng-click="cibilpull()">Get CIBIL Data</button> -->
<md-button flex="10" ng-repeat="btn in buttonlist"
ng-class="btnr? 'changeclass' : 'md-raised'"
ng-click="activebtns(btn.div,$event)"
ng-model="" style="margin: 10px;width: 16% !important;height: 42px !important;border-radius: 12px !important;font-size: 12px !important;">{{btn.name}}
</md-button>
</div>
</div>
<div layout="row">
<md-button ng-click="ShowHide();" class="md-raised" style="background-color: rgb(251, 132, 6);color:white;margin-left: 26px;">Show All</md-button>
</div>
</md-card-content>
</md-card>
<div flex="100" ng-repeat="data in analysismaster" layout-padding>
<div layout="column" ng-repeat="(key,val) in data|object2Array|orderBy:'tableId'">
<md-card flex="100" class="md-default-theme" ng-show="change==val.div||showall" ng-if="(val.keyval==true)" style="width:98%;margin:8px auto;max-width: 98%;">
<md-card-content layout-align="center center">
<h2>{{val.tabledesc}}</h2>
<div ng-if="(val.tabledesc!='PRODUCT')" layout="column" ng-repeat="(k,v) in val" class="ng-scope" layout="center center">
<div layout="row" ng-repeat="(ke,va) in v" layout-wrap>
<div flex="15" ng-repeat="(kt,values) in va">
<span flex="">
<b ng-if="($index%2==0)" style="color:rgb(13, 93, 130);">{{values.val|LKcomma}}</b>
<p ng-if="($index%2!=0)" style="">{{values.val|LKcomma}}</p>
</span>
</div>
</div>
</div>
<div ng-if="(val.tabledesc=='PRODUCT')" layout="column" ng-repeat="(k,v) in val" class="ng-scope" layout="center center">
<div layout="row" ng-repeat="(ke,va) in v" layout-wrap>
<div flex="15" ng-repeat="(kt,values) in va">
<span flex="">
<b ng-if="($index%2==0)" style="color:rgb(13, 93, 130);">{{values.val}}</b>
<p ng-if="($index%2!=0)" style="">{{values.val}}</p>
</span>
</div>
</div>
</div>
</md-card-content>
</md-card>
<md-card flex="100" class="md-default-theme" ng-show="(change==val.div)||showall" ng-if="(val.keyval==false)" style="width:98%;margin:8px auto;max-width: 98%;">
<md-card-content>
<h2>{{val.tabledesc}}</h2>
<table flex-gt-sm="100" st-safe-src="finalexecutivedata" flex-gt-md="100" st-table="executivedata" class="table table-bordered table-striped" style="width:200%;height:auto;max-height:44em;overflow:auto;">
<thead ng-class="val.div==4?'bigtable':''" ng-class="" ng-repeat="(kth,vth) in val"><tr ng-if="($index==0)" class="pattern"><th style="width:5%;">#</th><th st-sort="exectitle.title" ng-repeat="v in vth[0]">{{v.val|LKcomma}}</th></tr>
<tr ng-if="(kth=='data')"><th colspan="8" style="width:100%"><input st-search="" class="form-control" placeholder="search ..." type="text"/></th></tr></thead>
<tbody ng-if="(ktr=='data')" ng-class="val.div==4?'bigtable':''" ng-repeat="(ktr,vtr) in val" >
<tr ng-if="$index!=0" ng-repeat="vt in vtr">
<td style="width:15%;">{{$index}}</td>
<td ng-repeat="(k,vd) in vt" editable-text="vd.val" e-form="tableform" onbeforesave="checkName($data,vd.val,k)">{{vd.val|LKcomma}}</td>
</tr>
</tbody>
</table>
</md-card-content>
</md-card>
</div>
</div>
</div>
</div>