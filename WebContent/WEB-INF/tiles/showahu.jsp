<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
<form id="ahuform">
	<div class="row">
		<table>
			<tr>
				<td><div style="display: inline">
						served premises:&nbsp;<input style="width: 250px" name="premises" readonly="readonly"
							type="text">
						<div class="error-message" id="premises"></div>
					</div></td>
				<td><div style="margin-left: 30px">
						supply system name:&nbsp;<input id="supplyname" style="width: 70px" readonly="readonly"
							name="supply_system" type="text">
						<div class="error-message" id="supplysystemnameer"></div>
					</div></td>
				<td><div style="margin-left: 30px">
						exhaust system name:&nbsp;<input id="exhaustname" style="width: 70px" readonly="readonly"
							name="exhaust_system" type="text">
						<div class="error-message" id="exhaustsystemnameer"></div>
					</div></td>
			</tr>
		</table>
	</div>

	<div class="row" style="margin-top: 10px">
		<div class="col-md-1" style="padding: 0; margin-left: 79px">
		</div>
		<div class="col-md-8" style="padding: 0">

			<div class="four_parts">
				<div id="top_left" class="working_area">
					<div id="sortContainer_top_left">
					</div>
				</div>
				<div id="top_right" class="working_area">
					<div id="sortContainer_top_right"></div>
				</div>
				<div id="bottom_left" class="working_area">
					<div id="sortContainer_bottom_left"></div>
				</div>
				<div id="bottom_right" class="working_area">
					<div id="sortContainer_bottom_right"></div>
				</div>
			</div>
		</div>
		<div class="col-md-1" style="padding: 0; margin-left: 12px">
		</div>
	</div>
	
	<div class="row basic_data_table">
		<div class="col-md-8" style="margin-left: 160px">
			<table id="param-table"
				class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Parameter</th>
						<th style="text-align: center">Winter</th>
						<th style="text-align: center">Summer</th>
					</tr>
				</thead>
				<tr>
					<td>Supply system air flow, m3/h</td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="SupplyAirFlowWinter" path="SupplyAirFlowWinter"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="safwer"></div></td>
					<!-- path attr. the same value as name -->
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="SupplyAirFlowSummer" path="SupplyAirFlowSummer"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="safser"></div></td>
				</tr>
				<tr>
					<td>Supply system pressure drop, Pa</td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="SupplyPressureDropWinter" path="SupplyPressureDropWinter"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="spdwer"></div></td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="SupplyPressureDropSummer" path="SupplyPressureDropSummer"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="spdser"></div></td>
				</tr>
				<tr>
					<td>Exhaust system air flow, m3/h</td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="ExhaustAirFlowWinter" path="ExhaustAirFlowWinter"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="eafwer"></div></td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="ExhaustAirFlowSummer" path="ExhaustAirFlowSummer"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="eafser"></div></td>
				</tr>
				<tr>
					<td>Exhaust system pressure drop, Pa</td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="ExhaustPressureDropWinter" path="ExhaustPressureDropWinter"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="epdwer"></div></td>
					<td style="text-align: center"><input type="text" readonly="readonly"
						name="ExhaustPressureDropSummer" path="ExhaustPressureDropSummer"
						style="width: 55px; margin: 0 auto" />
					<div class="error-message" id="epdser"></div></td>
			</table>
		</div>
	</div>

	<div class="row elem_data_table">
		<!--technical note-->
		<div class="col-md-12">
			<table id="main-table"
				class="table table-striped table-hover table-bordered"
				style="width: 820px; margin-left: 58px">
				<thead>
					<tr>
						<th style='width: 94px'>Number</th>
						<th style='width: 260px'>Name</th>
						<th>Description (max character number - 100)</th>
					</tr>
				</thead>
				<tbody id="table-elements">
				</tbody>
			</table>
		</div>
	</div>
</form>
<div style="margin-left:58px">AHU summary:</div>
	<div id="summary" class="col-md-12" id="summary" style="width: 820px; margin-left: 58px; padding: 0px"><textarea class="textareatd" style="width: 820px;"  maxlength="396" rows="1"></textarea></div>
<div class="container" style="display:inline-block; float: right; margin-top: 10px">
<div class="row">
<div class="col-sm-6"></div>
<div class="col-sm-6">

<form role ="form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload" method="post">
	<input type="hidden" name="projects_id" value="${projects_id}"/>
	<input type="hidden" name="ahusid" value="${ahu.id}"/><%-- value="${ahu.id}" --%>
	Upload pdf selection file: <input style="display: inline;" type="file" name="pdf"/>
	<p/>
	<div style="float: right; margin-right: 173px"><input class="btn btn-primary btn-lg main_button" style="width: 122px" type="submit" value="Upload"/></div>
</form>

</div>
</div>
</div>
</div>
<script type="text/javascript">

	function resizetextarea() {
		$('body').on('change keyup keydown paste cut', '.textareatd',
				function() {
					$(this).height(0).height(this.scrollHeight);
				}).find('textarea').change();
	}
	
	function fillahuerrors(data) {
		$(".error-message").html("");

		var premerror = data.premises;
		if (premerror != null) {
			$('#premises').append(premerror);
		}
		var swaferror = data.supplywinterairflow;
		if (swaferror != null) {
			$('#safwer').append(swaferror);
		}
		var ssaferror = data.supplysummerairflow;
		if (ssaferror != null) {
			$('#safser').append(ssaferror);
		}
		var swpderror = data.supplywinterpressuredrop;
		if (swpderror != null) {
			$('#spdwer').append(swpderror);
		}
		var sspderror = data.supplysummerpressuredrop;
		if (sspderror != null) {
			$('#spdser').append(sspderror);
		}
		var ewaferror = data.exhaustwinterairflow;
		if (ewaferror != null) {
			$('#eafwer').append(ewaferror);
		}
		var esaferror = data.exhaustsummerairflow;
		if (esaferror != null) {
			$('#eafser').append(esaferror);
		}
		var ewpderror = data.exhaustwinterpressuredrop;
		if (ewpderror != null) {
			$('#epdwer').append(ewpderror);
		}
		var espderror = data.exhaustsummerpressuredrop;
		if (espderror != null) {
			$('#epdser').append(espderror);
		}
	}
	
	$(document).ready(function() {
		var premises = "${ahu.premises}";
		var supplysystemname = "${ahu.supplysystemname}";
		var exhaustsystemname = "${ahu.exhaustsystemname}";
		var supplywinterairflow = "${ahu.supplywinterairflow}";
		var supplysummerairflow = "${ahu.supplysummerairflow}";
		var supplywinterpressuredrop = "${ahu.supplywinterpressuredrop}";
		var supplysummerpressuredrop = "${ahu.supplysummerpressuredrop}";
		var exhaustwinterairflow = "${ahu.exhaustwinterairflow}";
		var exhaustsummerairflow = "${ahu.exhaustsummerairflow}";
		var exhaustwinterpressuredrop = "${ahu.exhaustwinterpressuredrop}";
		var exhaustsummerpressuredrop = "${ahu.exhaustsummerpressuredrop}";
		var topleftresult = "${ahu.topleftresult}";
		var toprightresult = "${ahu.toprightresult}";
		var bottomleftresult = "${ahu.bottomleftresult}";
		var bottomrightresult = "${ahu.bottomrightresult}";
		var descriptions = "${ahu.descriptions}";
		var summary = "${ahu.summary}";
		var descriptionsarr = descriptions.split("+spl+") ;
		var counter = 0 ;
		
		$('#ahuform').find('input[name="premises"]').val(premises);
		$('#ahuform').find('input[name="supply_system"]').val(supplysystemname);
		$('#ahuform').find('input[name="exhaust_system"]').val(exhaustsystemname);
		$('#ahuform').find('input[name="SupplyAirFlowWinter"]').val(supplywinterairflow);
		$('#ahuform').find('input[name="SupplyAirFlowSummer"]').val(supplysummerairflow);
		$('#ahuform').find('input[name="SupplyPressureDropWinter"]').val(supplywinterpressuredrop);
		$('#ahuform').find('input[name="SupplyPressureDropSummer"]').val(supplysummerpressuredrop);
		$('#ahuform').find('input[name="ExhaustAirFlowWinter"]').val(exhaustwinterairflow);
		$('#ahuform').find('input[name="ExhaustAirFlowSummer"]').val(exhaustsummerairflow);
		$('#ahuform').find('input[name="ExhaustPressureDropWinter"]').val(exhaustwinterpressuredrop);
		$('#ahuform').find('input[name="ExhaustPressureDropSummer"]').val(exhaustsummerpressuredrop);
		
		var topleftarr = topleftresult.split("++ext++") ;	
		if(topleftarr[0]!=""){
		for(var i = 0; i < topleftarr.length; i++){
				var temp = topleftarr[i].split("+int+") ;
				var img = $('<img/>').attr('id', temp[0]).attr('src', temp[1]).attr("uniqelemname", temp[2]).attr("title", temp[3]).addClass("ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_left_elem").css("position", "relative");				
				$("#sortContainer_top_left").append(img) ;
				$('#table-elements').append("<tr class='nondraggable' id=" + temp[2] + "><td style='width: 94px'>" + (101+i) + "</td><td style='width: 260px'>" + temp[3] + '</td><td><textarea class="textareatd" maxlength="100" rows="1">' + descriptionsarr[counter] + '</textarea></td></tr>');
				counter++ ;
		}}
		
		var toprightarr = toprightresult.split("++ext++") ;
		if(toprightarr[0]!=""){
		for(var i = 0; i < toprightarr.length; i++){
				var temp = toprightarr[i].split("+int+") ;
				var img = $('<img/>').attr('id', temp[0]).attr('src', temp[1]).attr("uniqelemname", temp[2]).attr("title", temp[3]).addClass("ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_right_elem").css("position", "relative");
				$("#sortContainer_top_right").append(img) ;
				$('#table-elements').append("<tr class='nondraggable' id=" + temp[2] + "><td style='width: 94px'>" + (201+i) + "</td><td style='width: 260px'>" + temp[3] + '</td><td><textarea class="textareatd" maxlength="100" rows="1">' + descriptionsarr[counter] + '</textarea></td></tr>');
				counter++ ;
		}}
		
		var bottomleftarr = bottomleftresult.split("++ext++") ;
		if(bottomleftarr[0]!=""){
		for(var i = 0; i < bottomleftarr.length; i++){
				var temp = bottomleftarr[i].split("+int+") ;
				var img = $('<img/>').attr('id', temp[0]).attr('src', temp[1]).attr("uniqelemname", temp[2]).attr("title", temp[3]).addClass("ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_left_elem").css("position", "relative");
				$("#sortContainer_bottom_left").append(img) ;
				$('#table-elements').append("<tr class='nondraggable' id=" + temp[2] + "><td style='width: 94px'>" + (301+i) + "</td><td style='width: 260px'>" + temp[3] + '</td><td><textarea class="textareatd" maxlength="100" rows="1">' + descriptionsarr[counter] + '</textarea></td></tr>');
				counter++ ;
		}}
		
		var bottomrightarr = bottomrightresult.split("++ext++") ;
		if(bottomrightarr[0]!=""){
		for(var i = 0; i < bottomrightarr.length; i++){
				var temp = bottomrightarr[i].split("+int+") ;
				var img = $('<img/>').attr('id', temp[0]).attr('src', temp[1]).attr("uniqelemname", temp[2]).attr("title", temp[3]).addClass("ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_right_elem").css("position", "relative");
				$("#sortContainer_bottom_right").append(img) ;
				$('#table-elements').append("<tr class='nondraggable' id=" + temp[2] + "><td style='width: 94px'>" + (401+i) + "</td><td style='width: 260px'>" + temp[3] + '</td><td><textarea class="textareatd" maxlength="100" rows="1">' + descriptionsarr[counter] + '</textarea></td></tr>');
				counter++ ;
		}}
		$('#summary').find('textarea').val(summary);
		resizetextarea() ;
	});
</script>