<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"><!-- Create new project --></h4>
				</div>

				<div class="modal-body" id="formdata">
					<p>
						<span style="margin-left: 115px">Climatic parameters:</span>
						<form id="formdata">
					<table class="table table-bordered"
						style="color: #777777; width: 340px; margin-left: 115px">
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
								
								<td><input name ="winterairtemp" style="width: 100px; margin-left: 10px"
									type="text"><br/>
								</td>
							
								<td><input name="winterhumid" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
								</td>
								</tr>
							<tr>
								<td>Summer</td>
								
								<td><input name ="summerairtemp" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
								</td>
							
								<td><input name ="summerhumid" style="width: 100px; margin-left: 10px"
									type="text"/><br/>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					<div class="modal-footer">
				<button type="button" id="close_button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>		
				</div>
			</div>			
		</div>
	</div>


<div style="padding: 0; color: black; font-weight: bolder; font-size: medium;" class="col-md-12">Received projects:</div><br><br>
<table id="incomingtable" class="table table-bordered" style="color: #777777">
	<thead class="generalsort">
		<tr style="background: #fafafa">
			<td class="vert-align">Project name</td>
			<td class="vert-align" style="text-align: center">Company<br>name</td>
			<td class="vert-align" style="text-align: center">Company<br>city</td>
			<td class="vert-align" style="text-align: center; width: 130px">Date</td>
			<td class="markerclass vert-align" style="text-align: center; width: 30px">Project<br>data</td>
			<td class="markerclass vert-align" style="text-align: center; width: 30px">AHU<br>requests</td>			
			<td class="markerclass vert-align" style="text-align: center; width: 30px">Uploaded<br>AHUs</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="currentproject" items="${producersIncomingProjects}"> <!-- !!! -->
			<tr>
				<td class="vert-align"><c:out value="${currentproject.projectname}"></c:out></td>
				<td class="vert-align" style="text-align: center"><c:out value="${currentproject.user.name}"></c:out></td>
				<td class="vert-align" style="text-align: center"><c:out value="${currentproject.user.city}"></c:out></td>
				<td class="vert-align" style="text-align: center"><c:out value="${currentproject.creationdate}"></c:out></td>
				<td class="vert-align" style="text-align: center"><a class="editlink" href="#?projects_id=${currentproject.projects_id}" data-toggle="modal" data-target="#myModal">Show</a></td>
				<td class="vert-align" style="text-align: center"><a class="ahuslink" href="#?projects_id=${currentproject.projects_id}" data-toggle="modal" data-target="#ahusModal">request</a></td>
				<td class="vert-align" style="text-align: center"><a id="uploadedahus" href="${pageContext.request.contextPath}/uploadedahus?projects_id=${currentproject.projects_id}" target="_blank">AHUs</a></td>
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
					<form id="new-tab-opener" method="get" target="_blank"></form>
					<div style="padding: 0; color: black; font-weight: bolder; font-size: medium;" class="col-md-12">Available AHUs:</div><br><br>
					<div>
					<table id="ahustable" class="table table-bordered" style="color: #777777">
						<thead class="generalsort">
							<tr style="background: #fafafa">
								<td>Supply</td>
								<td>Exhaust</td>
								<td>Premises</td>
								<td class="markerclass">Show AHU/Upload selection</td>
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

	function setIncomingActive() {
		$('#fixednav ul li').removeClass('active');
		$('#incoming').addClass('active');
	}
	
	function ahuslink(){
		$("body").on("click", ".ahuslink", function(){
			$('#ahustablebody').html("");
			$.getJSON('<c:url value="/geteditedproject4"/>', fillahustable);
		})
	}
	
	function fillahustable (data){
		$('#ahustablebody').html("");
		$('.modal-title').html("");
		var projectname = data.projectname ;
		$('.modal-title').append('AHUs of project: ' + projectname) ;
		var projects_id = data.projects_id ;
		var premises = data.premises ;
		var ahusids = data.ahusids ;
		var ahusupplynames = data.ahusupplynames ;
		var ahuexhaustnames = data.ahuexhaustnames ;
		
		var url = '<c:url value="/createnewahu"/>' + "?projects_id=" + projects_id;		
		$('#createnewahu').attr('href', url);
		for(var i =0; i < premises.length; i++){
			$('#ahustablebody').append(
					'<tr>'+
					'<td>' + ahusupplynames[i] + '</td>' +
					'<td>' + ahuexhaustnames[i] + '</td>' +
					'<td>' + premises[i] + '</td>' +
					'<td><a href="${pageContext.request.contextPath}/showahu?projects_id=' + projects_id + '&ahusid=' + ahusids[i] + '" target="_blank">Show AHU/Upload selection</a></td>' +
					'</tr>') ;
		}
		$('#ahustable').DataTable();
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
	}
	
	function getURLParameter(url, name) {
	    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url)||[,null])[1];
	}
	
	function sendprojectid(){
		$('.editlink, .ahuslink').bind('click', function(){
			   var url = ($(this).attr('href'));			   
			   var projects_id = getURLParameter(url, 'projects_id');
			   			   
			   $.ajax({
					"type" : 'POST',
					"url" : '<c:url value="/sendprojectid2" />',
					"data" : JSON.stringify({
						"projects_id" : projects_id,
					}),
					"success" : success,
					"error" : error,
					contentType : "application/json",
					dataType : "json"
				});
			
		})		
	}
	
	function success(data) {}

	function error(data) {
		alert("error");		
	}
	
	function editproject(){
		$('.editlink').click(function(){
			$.getJSON('<c:url value="/geteditedproject3"/>', fillform);
		})
	}
	
	function fillform(data){
		var winterairtemp = data.winterairtemp ;
		var summerairtemp = data.summerairtemp ;
		var winterhumid = data.winterhumid ;
		var summerhumid = data.summerhumid ;
		$('#formdata').find('input[name="winterairtemp"]').val(winterairtemp);
		$('#formdata').find('input[name="summerairtemp"]').val(summerairtemp);
		$('#formdata').find('input[name="winterhumid"]').val(winterhumid);
		$('#formdata').find('input[name="summerhumid"]').val(summerhumid);
		}
	
	function removesorting(){
		$("body").on("click", ".generalsort", function(){
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
	})}
	
	$(document).ready(function() {
		$('#incomingtable').DataTable({
	        "order": [[ 3, "desc" ]]
	    });	
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
		removesorting() ;
		setIncomingActive() ;
		sendprojectid() ;
		editproject() ;
		sendprojectid() ;
		ahuslink() ;
		
	})
	
</script>