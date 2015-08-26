<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	
		<sf:form id="projectForm" method="post" action="${pageContext.request.contextPath}/showerrors" 
		commandName="project">  
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><!-- Create new project --></h4>
				</div>

				<div class="modal-body" id="formdata">
					<p>
						<span style="margin-left: 40px;">Project name: </span>
						
						<sf:input id="projectnameinput" name ="projectname" path="projectname" style="width: 250px" type="text" /><br/>
							<div style="margin-left: 40px" class="error-message"><sf:errors path="projectname"></sf:errors></div>						
							
							<c:if test="${sessionScope.dublicateProjName == true}">
							<div id="dublicateprojname" style="margin-left: 40px"><div class="error-message">This project name already exist.</div></div>
							</c:if>
					
					<span style="margin-left: 40px">Climatic parameters:</span>

					<table class="table table-bordered"
						style="color: #777777; width: 340px; margin-left: 40px">
						<thead>
							<tr style="background: #fafafa">
								<td>Season</td>
								<td style="width: 135px">Air temperature, &#176;C</td>
								<td>Relative humidity, %</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Winter</td>
								
								<td><sf:input name ="winterairtemp" path="winterairtemp" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
							<div class="error-message"><sf:errors path="winterairtemp"></sf:errors></div></td>
							
								<td><sf:input name="winterhumid" path="winterhumid" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
							<div class="error-message"><sf:errors path="winterhumid"></sf:errors></div></td>
							
							</tr>
							<tr>
								<td>Summer</td>
								
								<td><sf:input name ="summerairtemp" path="summerairtemp" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
							<div class="error-message"><sf:errors path="summerairtemp"></sf:errors></div></td>
							
								<td><sf:input name ="summerhumid" path="summerhumid" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
							<div class="error-message"><sf:errors path="summerhumid"></sf:errors></div></td>
							
							</tr>
						</tbody>
					</table>

					<div class="row">
						<div class="col-md-4"
							style="margin-left: 40px; margin-top: 8px; width: 210px">Select
							the city of producers:</div>
						<div class="dropdown dropdown-select col-md-4">
							<button class="btn btn-default dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
								Select city <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="dropdownMenu1">
								<c:forEach var="city" items="${sessionScope.distinctCities}">
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#"><c:out value="${city}"></c:out></a></li>
								</c:forEach>
							</ul>							
						</div>
					</div>
							<c:if test="${sessionScope.cityNotSelected == true}">
							<div id="citynotselected" style="margin-left: 250px"><div class="error-message">City not selected.</div></div>
							</c:if>				
					<p />
					<p>
						<span style="margin-left: 40px">Choose available producers
							for sending project to:</span>
								<c:if test="${sessionScope.producersEmptyList == true}">
							<div id="producersemptylist" style="margin-left: 350px"><div class="error-message">Choose at least one producer.</div></div>
							</c:if>
						<p />
					<div class="row">
					<div class="col-md-4" style="margin-left: 40px">
						<select name="from" id="undo_redo" class="form-control" size="13"
								multiple="multiple" style="height: 231px">
							
						</select>
					</div>

					<div class="col-md-2">
						<button type="button" id="undo_redo_undo"
								class="btn btn-primary btn-block">undo</button>
						<button type="button" id="undo_redo_rightAll"
								class="btn btn-default btn-block">
							<i class="glyphicon glyphicon-forward"></i>
						</button>
						<button type="button" id="undo_redo_rightSelected"
								class="btn btn-default btn-block">
							<i class="glyphicon glyphicon-chevron-right"></i>
						</button>
						<button type="button" id="undo_redo_leftSelected"
								class="btn btn-default btn-block">
							<i class="glyphicon glyphicon-chevron-left"></i>
						</button>
						<button type="button" id="undo_redo_leftAll"
								class="btn btn-default btn-block">
							<i class="glyphicon glyphicon-backward"></i>
						</button>
						<button type="button" id="undo_redo_redo"
								class="btn btn-warning btn-block">redo</button>
					</div>

					<div class="col-md-4">
						<select name="to" id="undo_redo_to" class="form-control" size="13"
								multiple="multiple" style="height: 231px"></select>
					</div>					
				</div>
			</div>			
			<div class="modal-footer">
				<button type="button" id="close_button" class="btn btn-default" data-dismiss="modal">Close</button>
				<span class="submit_button"><!-- <input type="button" id="save_data" class="btn btn-primary" value="Save data"> --></span>
			</div>		
		</div><!-- /.modal-content -->
		</sf:form>
		
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="bs-example" style="padding-bottom: 24px;">
	<button class="btn btn-primary btn-lg main_button" data-toggle="modal" id="createnewproject"
		data-target="#myModal"><!-- Create new project -->
		<c:choose>
		<c:when test="${sessionScope.valid == false}">Fix inputed data!!!</c:when>
		<c:otherwise>Create new project</c:otherwise> </c:choose>
		</button>
</div>

<div class="row" style="width: 930px; margin: 0px">
<div style="padding: 0; color: black; font-weight: bolder; font-size: medium;" class="col-md-12">Available projects:</div><br><br>

</div>
<table id="projectstable" class="table table-bordered" style="color: #777777">
	<thead class="generalsort">
		<tr style="background: #fafafa">
			<td style="width:150px" class="vert-align">Creation date</td>
			<td class="vert-align">Project name</td>
			<td style="width:40px" class="markerclass vert-align">Ahus</td>
			<td style="width:110px" class="markerclass vert-align">Save project as</td>
			<td style="width:115px" class="markerclass vert-align">Show/Edit project</td>
			<td class="markerclass vert-align" style="padding:2px; width: 62px; text-align:center;"><input class="selectall"  type="button" value="All"><br><input id="delete" type="button" value="Delete"></td>
			<td class="markerclass vert-align" style="padding:2px; width: 62px; text-align:center;">Notify<br>producer</td>			
		</tr>
	</thead>
	<tbody>
		<c:forEach var="currentproject" items="${usersProjects}">
			<tr>
				<td class="vert-align"><c:out value="${currentproject.creationdate}"></c:out></td>
				<td class="vert-align"><c:out value="${currentproject.projectname}"></c:out></td>
				<td class="vert-align"><a class="ahuslink" href="#?projects_id=${currentproject.projects_id}" data-toggle="modal" data-target="#ahusModal">Ahus</a></td>
				<td class="vert-align"><a class="savelink" href="#?projects_id=${currentproject.projects_id}&type=saveas" data-toggle="modal"
		data-target="#myModal">Save project as&nbsp;<span class="glyphicon glyphicon-floppy-save"></span></a></td>
				<td class="vert-align"><a class="editlink" href="#?projects_id=${currentproject.projects_id}&type=edit" data-toggle="modal"
		data-target="#myModal">Show/Edit project&nbsp;<span class="glyphicon glyphicon-edit"></span></a></td>
				<%-- <td><a class="deletelink" href="${pageContext.request.contextPath}/delete?projects_id=${currentproject.projects_id}">Delete project&nbsp;<span
					class="glyphicon glyphicon-remove-circle"></span></a></td> --%>
					<td class="vert-align" style="text-align: center;"><input class="check" type="checkbox" value="${currentproject.projects_id}"></td>
					<td class="vert-align" style="text-align: center;"><a class="sendemail" href="#?projects_id=${currentproject.projects_id}">notify</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div id="ahusModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ahusModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 800px">
			<div class="modal-content" style="width: 800px">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="ahusModalLabel"></h4> <!-- Ahus of '' -->
				</div>

				<div class="modal-body" id="ahusdata">
					<a class="btn btn-primary btn-lg" id="createnewahu" href="">Create new AHU</a>&nbsp;
					<a class="btn btn-primary btn-lg" id="recievedselections" href="" target="_blank">Recieved selections</a>
					<form id="new-tab-opener" method="get" target="_blank"></form>
					
					<br/><br/>
					<div class="row" style="width: 760px; margin: 0px">
					<div style="padding: 0; color: black; font-weight: bolder; font-size: medium;" class="col-md-11">AHUs requests:</div><br><br>
					</div>
					<div>
					<table id="ahustable" class="table table-bordered" style="color: #777777">
						<thead class="generalsort">
							<tr style="background: #fafafa">
								<td class="vert-align">Supply</td>
								<td class="vert-align">Exhaust</td>
								<td class="vert-align">Premises</td>
								<td class="markerclass vert-align">Save AHU as</td>
								<td class="markerclass vert-align">Show/Edit AHU</td>
								<td class="markerclass vert-align" style="padding:2px; width: 62px; text-align:center;"><input class="selectallahu" type="button" value="All"><br><input id="deleteahus" type="button" value="Delete"></td>
								<td class="markerclass vert-align">Notify<br>producer</td>
							</tr>
						</thead>
						<tbody id="ahustablebody">		
						</tbody>
					</table>
					
					</div>				
				</div>			
			<div class="modal-footer">
				<button type="button" id="close_button2" class="btn btn-default" data-dismiss="modal">Close</button>				
			</div>		
		</div><!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<script type="text/javascript">
var buttonflag = false ;
var buttonflagahu = false ;

var ahusupplynames = new Array() ; 
var ahuexhaustnames = new Array() ;
var premises = new Array() ;
var projects_id = null; 
var ahusids = new Array() ;

	function setbuttonactive(){
		$("body").on("click", ".selectall", function(){
		if(buttonflag == true){
			$('.selectall').removeClass('pressed');			
			buttonflag = false;
		}else{
			$('.selectall').addClass('pressed');
			buttonflag = true;
		}
	})}
	
	function setbuttonahuactive(){
		$("body").on("click", ".selectallahu", function(){
			$('#ahustablebody').html("");
			for(var i =0; i < premises.length; i++){
				$('#ahustablebody').append(
						'<tr>'+
						'<td class="vert-align">' + ahusupplynames[i] + '</td>' +
						'<td class="vert-align">' + ahuexhaustnames[i] + '</td>' +
						'<td class="vert-align">' + premises[i] + '</td>' +
						'<td class="vert-align"><a class="saveasahulink" href="${pageContext.request.contextPath}/saveasahu?projects_id=' + projects_id + '&ahusid=' + ahusids[i] + '" target="_blank">Save AHU as&nbsp;<span class="glyphicon glyphicon-floppy-save"></span></a></td>' +
						'<td class="vert-align"><a class="editahulink" href="${pageContext.request.contextPath}/showeditahu?projects_id=' + projects_id + '&ahusid=' + ahusids[i] + '" target="_blank">Show/Edit AHU&nbsp;<span class="glyphicon glyphicon-edit"></span></a></td>' +
						'<td class="vert-align" style="text-align: center;"><input class="checkahus" type="checkbox" value="' + projects_id + "spl" + ahusids[i] + '"></td>' +
						'<td class="vert-align" style="text-align: center;"><a class="sendoneahuemail" href="#?ahusid=' + ahusids[i]+ '">notify</a></td>' +
						'</tr>') ;
			}
			
		if(buttonflagahu == true){
			$('.selectallahu').removeClass('pressed');	
			$('.checkahus:checkbox').prop('checked',false);
			buttonflagahu = false;
		}else{
			$('.selectallahu').addClass('pressed');
			$('.checkahus:checkbox').prop('checked',true);
			buttonflagahu = true;
		}
	})}
	
	function setallchecked(){
		 $('.selectall').click(function(){
			    if(!$('.check').prop('checked')){
			        $('.check:checkbox').prop('checked',true);
			    }
			    else{
			        $('.check:checkbox').prop('checked',false);
			    }
			});
		}
	
	function setProjectsActive() {
		$('#fixednav ul li').removeClass('active');
		$('#projects').addClass('active');
	}

	function multyselect($) {
		$('#undo_redo').multiselect();
	}

	function sitySelection() {
		$('.dropdown-select').on(
				'click',
				'.dropdown-menu li a',
				function() {
					var target = $(this).html();

					//Adds active class to selected item
					$(this).parents('.dropdown-menu').find('li').removeClass(
							'active');
					$(this).parent('li').addClass('active');

					//Displays selected text on dropdown-toggle button
					$(this).parents('.dropdown-select')
							.find('.dropdown-toggle').html(
									target + ' <span class="caret"></span>');
					sendCity();
					getproducers();
				});
	}

	function success(data) {
		/* alert("success"); */
	}

	function error(data) {
		alert("error");		
	}

	function getproducers() {
		$.getJSON('<c:url value="/getproducers"/>', showproducers);
	}

	function showproducers(data) {
		$("#undo_redo").html("");
		$("#undo_redo_to").html("");
		for (var i = 0; i < data.producers.length; i++) {
			$('#undo_redo').append(
					"<option value=" + i+1 + ">" + data.producers[i]
							+ "</option>");

		}
	}

	function sendCity() {
		var city = $("#dropdownMenu1").text();
		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="/sendcity" />',
			"data" : JSON.stringify({
				"city" : city
			}),
			"success" : success,
			"error" : error,
			contentType : "application/json",
			dataType : "json"
		});
	}
	
		
	
	function saveProjectData(){	
	 		$("body").on("click", "#save_data", function(){
			var projectname = $('#formdata').find('input[name="projectname"]').val();
			var winterairtemp = $('#formdata').find('input[name="winterairtemp"]').val();
			var summerairtemp = $('#formdata').find('input[name="summerairtemp"]').val();
			var winterhumid = $('#formdata').find('input[name="winterhumid"]').val();
			var summerhumid = $('#formdata').find('input[name="summerhumid"]').val();
			var producerscity = $("#dropdownMenu1").text() ;
			
			var producerselements = $("#undo_redo_to").children().toArray();
			var producerslist = new Array() ;
			for(var j = 0; j < producerselements.length; j++){
				producerslist[j] = $(producerselements[j]).text() ;				
			}
				$.ajax({
				"type" : 'POST',
				"url" : '<c:url value="/saveprojectdata" />',
				"data" : JSON.stringify({
					"projectname" : projectname,
					"winterairtemp" : winterairtemp,
					"summerairtemp" : summerairtemp,
					"winterhumid" : winterhumid,
					"summerhumid" : summerhumid,
					"producerscity" : producerscity,
					"producerslist" : producerslist
				}),
				"success" : success2,
				"error" : error2,
				contentType : "application/json",
				dataType : "json"
			}); 
		}) 
	}
	
	function updateProjectData(){	
	 	$("body").on("click", "#edit_data", function(){		
			var winterairtemp = $('#formdata').find('input[name="winterairtemp"]').val();
			var summerairtemp = $('#formdata').find('input[name="summerairtemp"]').val();
			var winterhumid = $('#formdata').find('input[name="winterhumid"]').val();
			var summerhumid = $('#formdata').find('input[name="summerhumid"]').val();
			var producerscity = $("#dropdownMenu1").text() ;
			
			var producerselements = $("#undo_redo_to").children().toArray();
			var producerslist = new Array() ;
			for(var j = 0; j < producerselements.length; j++){
				producerslist[j] = $(producerselements[j]).text() ;				
			}
				$.ajax({
				"type" : 'POST',
				"url" : '<c:url value="/updateprojectdata" />',
				"data" : JSON.stringify({
					"winterairtemp" : winterairtemp,
					"summerairtemp" : summerairtemp,
					"winterhumid" : winterhumid,
					"summerhumid" : summerhumid,
					"producerscity" : producerscity,
					"producerslist" : producerslist
				}),
				"success" : success2,
				"error" : error2,
				contentType : "application/json",
				dataType : "json"
			}); 
		}) 
	}
	
	function success2(data) {
		$('.main_button').text('Create new project') ;
		$('#projectForm').submit();		
	}

	function error2(data) {
		typeSettings() ;
		alert("Invalid data inputed");		
		$.post('<c:url value="/setvalidfalse" />');		
		$('#projectForm').submit();				
	}
	
	function deleteahualert() {
		$("body").on("click", ".deleteahulink", function(event){		
			var dodelete = confirm ('Are you sure you want to delete this AHU?') ;
			if(dodelete == false) {
				event.preventDefault() ;
			}
		})
	}
	
	function getURLParameter(url, name) {
	    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url)||[,null])[1];
	}
	
	function sendprojectid(){
		$('.editlink, .savelink, .ahuslink').bind('click', function(){
			   buttonflagahu = false ;
			   var url = ($(this).attr('href'));
			   var projects_id = getURLParameter(url, 'projects_id');
			   var type = getURLParameter(url, 'type');
			   			   
			   $.ajax({
					"type" : 'POST',
					"url" : '<c:url value="/sendprojectid" />',
					"data" : JSON.stringify({
						"projects_id" : projects_id,
						"type" : type
					}),
					"success" : success,
					"error" : error,
					contentType : "application/json",
					dataType : "json"
				});
			
		})		
	}
	
		
	function fillform(data){
		$('#projectnameinput').attr("readonly", false) ;
		var projectname = data.projectname ;
		var winterairtemp = data.winterairtemp ;
		var summerairtemp = data.summerairtemp ;
		var winterhumid = data.winterhumid ;
		var summerhumid = data.summerhumid ;
		var producerscity = data.producerscity;
		$('#formdata').find('input[name="projectname"]').val(projectname);
		$('#formdata').find('input[name="winterairtemp"]').val(winterairtemp);
		$('#formdata').find('input[name="summerairtemp"]').val(summerairtemp);
		$('#formdata').find('input[name="winterhumid"]').val(winterhumid);
		$('#formdata').find('input[name="summerhumid"]').val(summerhumid);
		$("#dropdownMenu1").text(producerscity);
		$('#dropdownMenu1').append(' <span class="caret"></span>');
		
		$("#undo_redo").html("");
		$("#undo_redo_to").html("");
		 for (var i = 0; i < data.unchosenproducers.length; i++) {			 
			$('#undo_redo').append(
					"<option value=" + i+1 + ">" + data.unchosenproducers[i]
							+ "</option>");

		}
		
		 for (var i = 0; i < data.chosenproducers.length; i++) {
			$('#undo_redo_to').append(
					"<option value=" + i+1 + ">" + data.chosenproducers[i]
							+ "</option>");

		}
	}
	
	function fillform2(data){		
		var projectname = data.projectname ;
		var winterairtemp = data.winterairtemp ;
		var summerairtemp = data.summerairtemp ;
		var winterhumid = data.winterhumid ;
		var summerhumid = data.summerhumid ;
		var producerscity = data.producerscity;
		$('#formdata').find('input[name="projectname"]').val(projectname);		
 		$('#projectnameinput').attr("readonly", true) ; 
		$('#formdata').find('input[name="winterairtemp"]').val(winterairtemp);
		$('#formdata').find('input[name="summerairtemp"]').val(summerairtemp);
		$('#formdata').find('input[name="winterhumid"]').val(winterhumid);
		$('#formdata').find('input[name="summerhumid"]').val(summerhumid);
		$("#dropdownMenu1").text(producerscity);
		$('#dropdownMenu1').append(' <span class="caret"></span>');
		
		$("#undo_redo").html("");
		$("#undo_redo_to").html("");
		 for (var i = 0; i < data.unchosenproducers.length; i++) {
			$('#undo_redo').append(
					"<option value=" + i+1 + ">" + data.unchosenproducers[i]
							+ "</option>");

		}
		
		 for (var i = 0; i < data.chosenproducers.length; i++) {
			$('#undo_redo_to').append(
					"<option value=" + i+1 + ">" + data.chosenproducers[i]
							+ "</option>");
		}
	}

	function removebutton(){
		$('.submit_button').html("") ;
	}
	
	function editproject(){
		$('.editlink').click(function(){		
			$('#myModalLabel').text('Edit project') ; 
			removebutton() ;			 
			$.getJSON('<c:url value="/geteditedproject"/>', fillform2);
			$('.submit_button').append('<input type="button" id="edit_data" class="btn btn-primary" value="Save changes">') ;
		})
	}
		
	function saveprojectas(){
		$('.savelink').click(function(){
			$('#myModalLabel').text('Save project as') ; 
			removebutton() ;		 
			
			$.getJSON('<c:url value="/geteditedproject"/>', fillform);
			 $('.submit_button').append('<input type="button" id="save_data" class="btn btn-primary" value="Save copy">') ;  
		})
	}
	
	function ahuslink(){
		$('.ahuslink').click(function(){
			$('#ahustablebody').html("");
			$.getJSON('<c:url value="/geteditedproject2"/>', fillahustable);
		})
	}
	
	function fillahustable (data){
		$('#ahustablebody').html("");
		$('.modal-title').html("");
		var projectname = data.projectname ;
		$('.modal-title').append('AHUs of project: ' + projectname) ;
		projects_id = data.projects_id ;
		premises = data.premises ;
		ahusids = data.ahusids ;
		ahusupplynames = data.ahusupplynames ;
		ahuexhaustnames = data.ahuexhaustnames ;
		
		var url = '<c:url value="/createnewahu"/>' + "?projects_id=" + projects_id;		
		var url2 = '<c:url value="/received"/>' + "?projects_id=" + projects_id;
		$('#createnewahu').attr('href', url);
		$('#recievedselections').attr('href', url2);
		for(var i =0; i < premises.length; i++){
			$('#ahustablebody').append(
					'<tr>'+
					'<td class="vert-align">' + ahusupplynames[i] + '</td>' +
					'<td class="vert-align">' + ahuexhaustnames[i] + '</td>' +
					'<td class="vert-align">' + premises[i] + '</td>' +
					'<td class="vert-align"><a class="saveasahulink" href="${pageContext.request.contextPath}/saveasahu?projects_id=' + projects_id + '&ahusid=' + ahusids[i] + '" target="_blank">Save AHU as&nbsp;<span class="glyphicon glyphicon-floppy-save"></span></a></td>' +
					'<td class="vert-align"><a class="editahulink" href="${pageContext.request.contextPath}/showeditahu?projects_id=' + projects_id + '&ahusid=' + ahusids[i] + '" target="_blank">Show/Edit AHU&nbsp;<span class="glyphicon glyphicon-edit"></span></a></td>' +
					'<td class="vert-align" style="text-align: center;"><input class="checkahus" type="checkbox" value="' + projects_id + "spl" + ahusids[i] + '"></td>' +
					'<td class="vert-align" style="text-align: center;"><a class="sendoneahuemail" href="#?ahusid=' + ahusids[i]+ '">notify</a></td>' +
					'</tr>') ;
		}
		$('#ahustable').DataTable();
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
	}
	
	function newahuredirect(){
		$('#createnewahu').prop('target', '_blank');		 
	}
	
	function closeahus(){
		$("body").on("click", "#createnewahu, .editahulink, .saveasahulink", function(){
			$('#close_button2').click();
		})	
	}
	
	
	function setcreatenewproject(){
		if($('.main_button').text().indexOf('Fix inputed data!!!')< 0){
		$('.main_button').click(function(){
			$('#myModalLabel').text('Create new project') ;
			 removebutton() ;
			 
			$('#projectnameinput').attr("readonly", false) ;
			var projectname = $('#formdata').find('input[name="projectname"]').val('');
			var winterairtemp = $('#formdata').find('input[name="winterairtemp"]').val('');
			var summerairtemp = $('#formdata').find('input[name="summerairtemp"]').val('');
			var winterhumid = $('#formdata').find('input[name="winterhumid"]').val('');
			var summerhumid = $('#formdata').find('input[name="summerhumid"]').val('');
			$("#dropdownMenu1").text('Select city');
			$('#dropdownMenu1').append(' <span class="caret"></span>');
			$("#undo_redo").html("");
			$("#undo_redo_to").html("");
			$('.submit_button').append('<input type="button" id="save_data" class="btn btn-primary" value="Save project">') ;
		})
	}
	}
	
	function typeSettings(){
		var type = '<%= session.getAttribute("type") %>';
		removebutton() ;
		if(type == 'edit'){			
			$('#myModalLabel').text('Edit project') ;
			$('.submit_button').append('<input type="button" id="edit_data" class="btn btn-primary" value="Save changes">') ;			
		}else if(type == 'saveas'){
			$('#myModalLabel').text('Save project as') ;
			$('.submit_button').append('<input type="button" id="save_data" class="btn btn-primary" value="Save copy">') ;
		}else{
			$('#myModalLabel').text('Create new project') ;			
			$('.submit_button').append('<input type="button" id="save_data" class="btn btn-primary" value="Save project">') ;
		}
	}
	
	
	function deleteprojects(){
		$("#delete").click(function(event){
		    event.preventDefault();
		    var projects_ids = $(".check:checkbox:checked").map(function(){
		        return this.value;
		    }).toArray();
		    
		    var dodelete = confirm ('Are you sure you want to delete this project(s)?') ;
		    if(projects_ids.length > 0 && dodelete==true){		    	
		    	$.ajax({
					"type" : 'POST',
					"url" : '<c:url value="/deleteprojects" />',
					"data" : JSON.stringify({
						"projects_ids" : projects_ids
					}),
					"success" : success3,
					"error" : error3,
					contentType : "application/json",
					dataType : "json"
				  });	
		    }
	})}
	
	function deleteahus(){
		$("#deleteahus").click(function(event){
		    event.preventDefault();
		    var ahus_ids = $(".checkahus:checkbox:checked").map(function(){
		        return this.value;
		    }).toArray();
		    
		    var dodelete = confirm ('Are you sure you want to delete this AHU(s)?') ;
		    if(ahus_ids.length > 0 && dodelete==true){		    	
		    	$.ajax({
					"type" : 'POST',
					"url" : '<c:url value="/deleteahus" />',
					"data" : JSON.stringify({
						"ahus_ids" : ahus_ids
					}),
					"success" : success3,
					"error" : error4,
					contentType : "application/json",
					dataType : "json"
				  });	
		    }
	})}
	
	function error3(data) {
		alert('error occured while deleting project(s).') ;
	}
	
	function error4(data) {
		alert('error occured while deleting AHU(s).') ;
	}
	
	function success3(data) {
		location.reload() ;
	}
	
	function removesorting(){
		$("body").on("click", ".generalsort", function(){
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
	})}
	
	function sendemail(){
		$('.sendemail').bind('click', function(){
			   var url = ($(this).attr('href'));
			   var projects_id = getURLParameter(url, 'projects_id');
			   var dosend = confirm ('Confirm if you want to send notify emails.') ;
				if(dosend == true) {
					$.blockUI({ message: '<h4><img src="${pageContext.request.contextPath}/static/img/loading.gif" />&nbsp;Sending emails...</h4>' });
					$.ajax({
						"type" : 'POST',
						"url" : '<c:url value="/projectsendemail" />',
						"data" : JSON.stringify({
							"projects_id" : projects_id
						}),
						"success" : success4,
						"error" : error5,
						contentType : "application/json",
						dataType : "json"
					});	
				} 
		})		
	}
	
	function sendoneahuemail(){
		$("body").on("click", ".sendoneahuemail", function(){
			   var url = ($(this).attr('href'));
			   var ahusid = getURLParameter(url, 'ahusid');
			   var dosend = confirm ('Confirm if you want to send notify emails.') ;
				if(dosend == true) {
					$.blockUI({ message: '<h4><img src="${pageContext.request.contextPath}/static/img/loading.gif" />&nbsp;Sending emails...</h4>' });
					$('#close_button2').click();
					$.ajax({
						"type" : 'POST',
						"url" : '<c:url value="/projectsendoneahuemail" />',
						"data" : JSON.stringify({
							"ahusid" : ahusid
						}),
						"success" : success4,
						"error" : error5,
						contentType : "application/json",
						dataType : "json"
					});	
				} 
		})		
	}
	
	function error5(data) {
		$.unblockUI();
		alert('error occured while sending emails.') ;
	}
	
	function success4(data) {
		$.unblockUI();
		alert('Your emails were sent successfully.') ;
	}
	
	$(document).ready(function() {
		$('#projectstable').DataTable({
	        "order": [[ 0, "desc" ]]
	    } );	
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
		
		removesorting() ;
		setProjectsActive();
		sitySelection();
		multyselect($);
		saveProjectData();
		updateProjectData();
		sendprojectid();
		editproject();
		setcreatenewproject();
		saveprojectas();
		typeSettings();
		ahuslink() ;
		newahuredirect();
		closeahus() ;
		deleteahualert() ;
		setbuttonactive() ;
		setallchecked() ; 
		deleteprojects() ;
		deleteahus() ;
		setbuttonahuactive() ;
		sendemail() ;
		sendoneahuemail() ;
	});
</script> 