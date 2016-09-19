<div role="tabpanel"
                   id="tab3-content"
                   aria-labelledby="tab3"
                   ng-switch-when="2"
                   md-swipe-left="next()"
                   md-swipe-right="previous()"
                   layout="row" layout-wrap layout="center center">
                  
                 <md-card layout="center center" >
                 <table flex-gt-sm="50" flex-gt-md="50" st-table="variablesdata" st-safe-src="variables" class="table table-bordered table-striped">
			<thead>
			<tr class="pattern">
				<th style="width:266px;" st-sort="#">#</th>
				<th st-sort="variables" style="width:266px;">Variable</th>
				<th st-sort="values" style="width:266px;">values</th>
				
			</tr>
			<tr>
				<th colspan="8" style="width:100%;"><input st-search="" class="form-control" placeholder="search ..." type="text"/></th>
			</tr>
			</thead>
			<tbody style="overflow:scroll;">
			<tr ng-repeat="row in variables">
			<td>{{$index}}</td>
				<td ng-repeat="r in row">{{r}}</td>
				</tr>
			</tbody>
		</table>
		</md-card>
		</div>