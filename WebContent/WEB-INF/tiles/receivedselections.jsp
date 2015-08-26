<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="projectname" style="padding: 0; color: black; font-weight: bolder; font-size: medium;" class="col-md-12"></div><br><br> <!-- Received AHU selections: -->

<table id="receivedselections" class="table table-bordered" style="color: #777777">
	<thead class="generalsort">
		<tr style="background: #fafafa">
			<td class="vert-align">Premises</td>
			<td class="vert-align" style="width: 60px">Supply</td>
			<td class="vert-align" style="width: 60px">Exhaust</td>
			<td class="vert-align" style="width: 60px">Company</td>
			<td class="vert-align" style="width: 60px">City</td>
			<td class="vert-align" style="width: 160px">AHU selections</td>
			<td class="vert-align markerclass" style="width: 60px">Notify<br>producer</td>
		</tr>
	</thead>
	<tbody id="ahustable">
	<c:forEach var="selectionsProducer" items="${selectionsProducers}">
			<tr>
				<td class="vert-align premises"><c:out value="${selectionsProducer.selectionpremises}"></c:out></td>
				<td class="vert-align supnames" style="text-align: center"><c:out
						value="${selectionsProducer.selectionsupname}"></c:out></td>
				<td class="vert-align exnames" style="text-align: center"><c:out
						value="${selectionsProducer.selectionexname}"></c:out></td>
				<td class="vert-align exnames" style="text-align: center"><c:out
						value="${selectionsProducer.producerscompanyname}"></c:out></td>
				<td class="vert-align exnames" style="text-align: center"><c:out
						value="${selectionsProducer.producerscity}"></c:out></td>				
				<td class="vert-align">
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> 
							<c:set var="uniquekey">
								<c:out value="${selectionsProducer.uniquekey}" />
							</c:set>
							<c:choose>
								<c:when test="${isVersionsEmpty[uniquekey]==true}">Empty</c:when>
								<c:otherwise>Uploaded selections</c:otherwise>
							</c:choose>
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<c:forEach var="ahuselection" items="${versionsmap[uniquekey]}">
								<li><a
									href="${pageContext.request.contextPath}/pdf/${ahuselection.id}"
									target="_blank"><c:out value="${ahuselection.uploadingdate}" /></a></li>
								<!-- target="_blank" -->
							</c:forEach>
						</ul>
					</div>
				</td>
				<td class="vert-align" style="text-align: center;"><a data-toggle="modal" class="ahusidlink" data-target="#myModal" 
				href="#?ahusid=${selectionsProducer.ahusid}&supname=${selectionsProducer.selectionsupname}&exname=${selectionsProducer.selectionexname}&premises=${selectionsProducer.selectionpremises}&companyname=${selectionsProducer.producerscompanyname}&city=${selectionsProducer.producerscity}">notify</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>			
		</tr>
	</tfoot>
</table>

<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">

		<form id="projectForm" method="post" action="#">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>

				<div class="modal-body" id="formdata">
					<div style="margin-left: 40px;">Type some notes if needed (not necessary):</div>
					<div style="margin-left: 40px; width: 490px; padding: 0px" id="note"><textarea style="width: 490px;" maxlength=300 rows="1"></textarea></div>
				</div>
				<div class="modal-footer">
					<button type="button" id="close_button" class="btn btn-default"
						data-dismiss="modal">Close</button>
					<span class="submit_button"><input type="button" class="btn btn-primary" id="sendemail" value="Send notification"></span>
				</div>
			</div>
			<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
var ahusid;
var supname;
var exname;
var premises;
var companyname;
var city;

function removesorting(){
	$("body").on("click", ".generalsort", function(){
	$('.markerclass').removeClass('sorting');
	$('.markerclass').removeClass('sorting_asc');
	$('.markerclass').removeClass('sorting_desc');
})}

function getURLParameter(url, name) {
	return (RegExp(name + '=' + '(.+?)(&|$)').exec(url) || [ , null ])[1];
}

function sendahusid() {
	$('.ahusidlink').bind('click', function() {
		$("#note").find('textarea').val('') ;
		$('#myModalLabel').text('') ;
		url = ($(this).attr('href'));
		ahusid = getURLParameter(url, 'ahusid');
		supname = getURLParameter(url, 'supname');
		if(supname.toLowerCase().indexOf("exname") >= 0){
			supname = "" ;	
		}
		exname = getURLParameter(url, 'exname');
		if(exname.toLowerCase().indexOf("premises") >= 0){
			exname = "" ;	
		}
		premises = getURLParameter(url, 'premises');
		companyname = getURLParameter(url, 'companyname');
		city = getURLParameter(url, 'city');
		$('#myModalLabel').append(supname+exname + " - " + premises) ;
	})
}

function sendahuemail() {
	$('#sendemail').bind('click', function() {
		var note = $("#note").find('textarea').val() ;
		var dosend = confirm('Confirm if you want to send notify emails.');
		if (dosend == true) {
			$.blockUI({ message: '<h4><img src="${pageContext.request.contextPath}/static/img/loading.gif" />&nbsp;Sending emails...</h4>' });
	    $.ajax({
		"type" : 'POST',
		"url" : '<c:url value="/receivedahusendemail" />',
		"data" : JSON.stringify({
			"ahusid" : ahusid,
			"companyname" : companyname,
			"city" : city,
			"note" : note 
		}),
		"success" : success,
		"error" : error,
		contentType : "application/json",
		dataType : "json"
	});  }
})}

function error(data) {
	$.unblockUI();
	alert('error occured while sending emails.');
}

function success(data) {
	$.unblockUI();
	alert('Your emails were sent successfully.');
}

 function resizetextarea() {
	    //  changes mouse cursor when highlighting loawer right of box
	    $("textarea").mousemove(function(e) {
	        var myPos = $(this).offset();
	        myPos.bottom = $(this).offset().top + $(this).outerHeight();
	        myPos.right = $(this).offset().left + $(this).outerWidth();
	        
	        if (myPos.bottom > e.pageY && e.pageY > myPos.bottom - 16 && myPos.right > e.pageX && e.pageX > myPos.right - 16) {
	            $(this).css({ cursor: "nw-resize" });
	        }
	        else {
	            $(this).css({ cursor: "" });
	        }
	    })
	    //  the following simple make the textbox "Auto-Expand" as it is typed in
	    .keyup(function(e) {
	        //  this if statement checks to see if backspace or delete was pressed, if so, it resets the height of the box so it can be resized properly
	        if (e.which == 8 || e.which == 46) {
	            $(this).height(parseFloat($(this).css("min-height")) != 0 ? parseFloat($(this).css("min-height")) : parseFloat($(this).css("font-size")));
	        }
	        //  the following will help the text expand as typing takes place
	        while($(this).outerHeight() < this.scrollHeight + parseFloat($(this).css("borderTopWidth")) + parseFloat($(this).css("borderBottomWidth"))) {
	            $(this).height($(this).height()+1);
	        };
	    });
} 
 
 function closenotification(){
		$("body").on("click", "#sendemail", function(){
			$('#close_button').click();
		})	
	}

 function removesorting2(){
	 $('body').on('change keyup keydown paste cut', '.text_filter', function(){
		$('.markerclass').removeClass('sorting');
		$('.markerclass').removeClass('sorting_asc');
		$('.markerclass').removeClass('sorting_desc');
	 })
 }
 
$(document).ready(function() {
	var projectname = '${projectname}' ;
	$('#projectname').append('Received AHU selections for project: ' + projectname) ;
	 $('#receivedselections').dataTable().columnFilter();
	$('.markerclass').removeClass('sorting');
	$('.markerclass').removeClass('sorting_asc');
	$('.markerclass').removeClass('sorting_desc'); 
		
	removesorting() ;
	sendahusid() ;
	sendahuemail() ;
	resizetextarea() ;
	closenotification() ;
	removesorting2() ;
		
})

</script>