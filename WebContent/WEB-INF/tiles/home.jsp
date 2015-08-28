<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<div class="row">
<div class="container">
<div class="col-md-12">
<p style="font-size: small; color: grey">For the reasons of demonstration, air handling unit (AHU) demo-constructor is provided below. 
There are separate AHU-elements located above and under the constructing zone. 
You can drag them and drop into the constructing zone which consist of four areas. 
Elements in each one of them can be relocated by simple dragging left or right. For deleting element drag and drop it to the trash can.</p>
<p style="font-size: small; color: grey">Demo-constructor already contains elements, corresponding to the AHU which is shown on the picture below.</p>
<p style="font-size: small; color: grey">To construct new AHU schemes and send them to the producer(s) for further receiving resulting selections, first of all 
you will be required to 
	<sec:authorize access="isAuthenticated()">
		link <a href="${pageContext.request.contextPath}/projects">Projects </a>page for creating new project.
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<a href="${pageContext.request.contextPath}/login">log in</a> and after that create new project in 'Projects' page.
	</sec:authorize>
	 
</p>

<h5 style="color: grey; font-weight: bold;">AHU demo-constructor:</h5>
</div>
</div>
</div>
<div class="row" style="margin-top: 10px">
		<!--elements-->
		<div class="container">		
			<div class="col-md-10">
				<div class="resources">
					<img id="valve_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/valve.jpg" title="valve">
					<img id="filter_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/filter.jpg"
						title="filter"> <img id="electric_heater_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/heater_eo.jpg"
						title="electric heater"> <img id="water_heater_top"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/heater_water.jpg"
						title="water heater"> <img id="water_cooler_top"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/cooler_water.jpg"
						title="water cooler"> <img id="dx_cooler_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/cooler_freon.jpg"
						title="dx cooler"> <img id="mixing_chamber_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/mixing_chamber_top.jpg"
						title="mixing chamber"> <img id="fan_left_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/fan2.jpg"
						title="fan"> <img id="fan_right_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/fan1.jpg"
						title="fan"> <img id="plate_recuperator_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/recuperator_top.jpg"
						title="plate recuperator"> <img id="wheel_recuperator_top"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/recuperator_rot_top.jpg"
						title="wheel recuperator"> <img id="empty_section_big_top"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/empty_big.jpg"
						title="empty section"> <img id="empty_section_small_top"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/empty_small.jpg"
						title="empty section"> <img id="silencer_top" class="drag"
						src="${pageContext.request.contextPath}/static/img/silenser.jpg"
						title="silencer">
				</div>
			</div>
		</div>
	</div>

	<div class="row" style="margin-top: 10px">
		<div class="col-md-1" style="padding: 0; margin-left: 79px">
			<!--AHU, trash cans-->
			<div class="trash_can">
				<img
					src="${pageContext.request.contextPath}/static/img/trash_can_left.jpg">
			</div>
		</div>
		<!-- <form class="save_ahu"> -->
		<div class="col-md-8" style="padding: 0">

			<div class="four_parts">
				<div id="top_left" class="working_area ui-droppable">
					<div id="sortContainer_top_left" class="ui-sortable"></div>
				</div>
				<div id="top_right" class="working_area ui-droppable">
					<div id="sortContainer_top_right" class="ui-sortable"></div>
				</div>
				<div id="bottom_left" class="working_area ui-droppable">
					<div id="sortContainer_bottom_left" class="ui-sortable"></div>
				</div>
				<div id="bottom_right" class="working_area ui-droppable">
					<div id="sortContainer_bottom_right" class="ui-sortable"></div>
				</div>
			</div>
			
		</div>
		<div class="col-md-1" style="padding: 0; margin-left: 12px">
			<div class="trash_can">
				<img
					src="${pageContext.request.contextPath}/static/img/trash_can_right.jpg">
			</div>
		</div>
	</div>

	<div class="row" style="margin-top: 10px">
		<!--elements-->
		<div class="container">
			<div class="col-md-10">
				<div class="resources">
					<img id="valve_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/valve.jpg"
						title="valve"> <img id="filter_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/filter.jpg"
						title="filter"> <img id="electric_heater_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/heater_eo.jpg"
						title="electric heater"> <img id="water_heater_bot"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/heater_water.jpg"
						title="water heater"> <img id="water_cooler_bot"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/cooler_water.jpg"
						title="water cooler"> <img id="dx_cooler_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/cooler_freon.jpg"
						title="dx cooler"> <img id="mixing_chamber_top_bot"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/mixing_chamber_bottom.jpg"
						title="mixing chamber"> <img id="fan_left_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/fan2.jpg"
						title="fan"> <img id="fan_right_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/fan1.jpg"
						title="fan"> <img id="plate_recuperator_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/recuperator_bottom.jpg"
						title="plate recuperator"> <img id="wheel_recuperator_bot"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/recuperator_rot_bot.jpg"
						title="wheel recuperator"> <img id="empty_section_big_bot"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/empty_big.jpg"
						title="empty section"> <img id="empty_section_small_bot"
						class="drag"
						src="${pageContext.request.contextPath}/static/img/empty_small.jpg"
						title="empty section"> <img id="silencer_bot" class="drag"
						src="${pageContext.request.contextPath}/static/img/silenser.jpg"
						title="silencer">
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<h5 style="color: grey; font-weight: bold;">Example of AHU:</h5>
	<div class="container" style="text-align: center;">
		<img style="width: 400px" src="${pageContext.request.contextPath}/static/img/AHU-example.jpg">
	</div>
	<br>
	<h5 style="color: grey; font-weight: bold;">Promo video:</h5>
	<div class="container" style="text-align: center;">
		<iframe width="640" height="360" src="https://www.youtube.com/embed/n_k9oPyaipw" frameborder="0" allowfullscreen></iframe>
	</div>
	
<script type="text/javascript">
function setHomeActive() {
    $('#fixednav ul li').removeClass('active');
    $('#home').addClass('active');
}

$(document).ready(function(){
	setHomeActive() ;
	
	$('#sortContainer_top_left').append('<img id="valve_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_left_elem" src="${pageContext.request.contextPath}/static/img/valve.jpg" title="valve" style="position: relative;" uniqelemname="valve_top4">') ;
	$('#sortContainer_top_left').append('<img id="filter_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_left_elem" src="${pageContext.request.contextPath}/static/img/filter.jpg" title="filter" style="position: relative;" uniqelemname="filter_top3">') ;
	$('#sortContainer_top_left').append('<img id="plate_recuperator_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_left_elem" src="${pageContext.request.contextPath}/static/img/recuperator_top.jpg" title="plate recuperator" style="position: relative;" uniqelemname="plate_recuperator_top1">') ;

	$('#sortContainer_top_right').append('<img id="fan_left_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_right_elem" src="${pageContext.request.contextPath}/static/img/fan2.jpg" title="fan" style="position: relative; left: 0px; top: 0px;" uniqelemname="fan_left_top7">') ;
	$('#sortContainer_top_right').append('<img id="filter_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error top_right_elem" src="${pageContext.request.contextPath}/static/img/filter.jpg" title="filter" style="position: relative; left: 0px; top: 0px;" uniqelemname="filter_top8">') ;

	$('#sortContainer_bottom_left').append('<img id="valve_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_left_elem" src="${pageContext.request.contextPath}/static/img/valve.jpg" title="valve" style="position: relative; left: 0px; top: 0px;" uniqelemname="valve_top6">') ;
	$('#sortContainer_bottom_left').append('<img id="empty_section_small_bot" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_left_elem" src="${pageContext.request.contextPath}/static/img/empty_small.jpg" title="empty section" style="position: relative;" uniqelemname="empty_section_small_bot5">') ;
	$('#sortContainer_bottom_left').append('<img id="plate_recuperator_bot" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_left_elem" src="${pageContext.request.contextPath}/static/img/recuperator_bottom.jpg" title="plate recuperator" style="position: relative;" uniqelemname="plate_recuperator_bot2">') ;

	$('#sortContainer_bottom_right').append('<img id="water_heater_bot" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_right_elem" src="${pageContext.request.contextPath}/static/img/heater_water.jpg" title="water heater" style="position: relative;" uniqelemname="water_heater_bot9">') ;
	$('#sortContainer_bottom_right').append('<img id="fan_right_top" class="ui-draggable ui-draggable-handle ui-draggable-dragging sortable ui-state-error bottom_right_elem" src="${pageContext.request.contextPath}/static/img/fan1.jpg" title="fan" style="position: relative; left: 0px; top: 0px;" uniqelemname="fan_right_top10">') ;
	
});
</script>