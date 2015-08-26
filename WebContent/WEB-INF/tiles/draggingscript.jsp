<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript">
var contents;
var class_id;
var top_left_size_counter = 0;
var top_right_size_counter = 0;
var bottom_left_size_counter = 0;
var bottom_right_size_counter = 0;

var counter_top_left = 0;
var counter_top_right = 0;
var counter_bottom_left = 0;
var counter_bottom_right = 0;

var total_sections_number = 0;

var elementsArray = new Array();

$(document).ready(function() {
	
    $('#sortContainer_top_left').sortable({
        change: function(){
            setTimeout(changeRowNumberTL,1000);
            setTimeout(sort, 1000);
        }
    });
    $('#sortContainer_top_right').sortable({
        change: function(){
            setTimeout(changeRowNumberTR,1000);
            setTimeout(sort, 1000);
        }
    });
    $('#sortContainer_bottom_left').sortable({
        change: function(){
            setTimeout(changeRowNumberBL,1000);
            setTimeout(sort, 1000);
        }
    });
    $('#sortContainer_bottom_right').sortable({
        change: function(){
            setTimeout(changeRowNumberBR,1000);
            setTimeout(sort, 1000);
        }
    });
    $('#table-elements').sortable({
    	cancel: '.nondraggable'
    });

    $('.drag').draggable({
        cursor: 'pointer',
        containment: 'document',
        revert: true,
        start: function(){
            contents = $(this).clone();
        }
    });

    $('.top_left_elem').draggable({
        cursor: 'pointer',
        containment: 'document',
        revert: true
    });


    $('.working_area').droppable({
        hoverClass: 'border',
        accept:'.drag',

        over: function(event, ui) {
            class_id = $(this);
        },

        drop: function(){
            if(class_id.is('#top_left')){
             if(contents.hasClass('drag')){ // если новый элемент - надо новую строку в таблице. используем prepend для левых
                    top_left_size_counter = top_left_size_counter + contents.context.clientWidth;
                    counter_top_left++;
                    total_sections_number++ ;
                    contents.removeClass('drag');
                    contents.addClass('sortable');
                    contents.addClass('ui-state-error');
                    if(top_left_size_counter < 320) {
                        contents.addClass('top_left_elem');
                        $('#sortContainer_top_left').prepend(contents);
                        var indTL;
                        if(getTopLeftContainerElementsArray()==null){
                            indTL = 101;
                        }else{
                            indTL = getTopLeftContainerElementsArray().length+101 ;
                        }
                        var uniqElemName = $(contents).get(0).id + total_sections_number;
                        $(contents).attr('uniqElemName', uniqElemName) ;
                        elementsArray.push(uniqElemName);
                        $('#table-elements').prepend("<tr class='nondraggable' id=" + uniqElemName + "><td style='width: 94px'>" + indTL + "</td><td style='width: 260px'>" + $(contents).get(0).title + '</td><td><textarea class="textareatd" maxlength="100" rows="1"/></td></tr>');
                        changeRowNumberTL();
                    } else {
                        top_left_size_counter = top_left_size_counter - contents.context.clientWidth;
                        counter_top_left--;
                        total_sections_number--;
                    }
                }
            }else if(class_id.is('#top_right')){
                    if(contents.hasClass('drag')) {
                    top_right_size_counter = top_right_size_counter + contents.context.clientWidth;
                    counter_top_right++;
                    total_sections_number++;
                    contents.removeClass('drag');
                    contents.addClass('sortable');
                    contents.addClass('ui-state-error');
                    if (top_right_size_counter < 320) {
                        contents.addClass('top_right_elem');
                        $('#sortContainer_top_right').append(contents);
                        var indTR;
                        if (getTopRightContainerElementsArray() == null) {
                            indTR = 201;
                        } else {
                            indTR = getTopRightContainerElementsArray().length + 201;
                        }
                        var uniqElemName = $(contents).get(0).id + total_sections_number;
                        $(contents).attr('uniqElemName', uniqElemName);
                        elementsArray.push(uniqElemName);
                        $('#table-elements').append("<tr class='nondraggable' id=" + uniqElemName + "><td style='width: 94px'>" + indTR + "</td><td style='width: 260px'>" + $(contents).get(0).title + '</td><td><textarea class="textareatd" maxlength="100" maxlength="100" rows="1"/></td></tr>');
                        setTimeout(sort, 1000);
                    } else {
                        top_right_size_counter = top_right_size_counter - contents.context.clientWidth;
                        counter_top_right--;
                        total_sections_number--;
                    }
                }
            }else if(class_id.is('#bottom_left')){
                    if(contents.hasClass('drag')) {
                    bottom_left_size_counter = bottom_left_size_counter + contents.context.clientWidth;
                    counter_bottom_left++;
                    total_sections_number++;
                    contents.removeClass('drag');
                    contents.addClass('sortable');
                    contents.addClass('ui-state-error');
                    if(bottom_left_size_counter < 320) {
                        contents.addClass('bottom_left_elem');
                        $('#sortContainer_bottom_left').prepend(contents);
                        var indBL;
                        if(getBotLeftContainerElementsArray()==null){
                            indBL=301;
                        }else{
                            indBL = getBotLeftContainerElementsArray().length + 301;
                        }
                        var uniqElemName = $(contents).get(0).id + total_sections_number;
                        $(contents).attr('uniqElemName', uniqElemName) ;
                        elementsArray.push(uniqElemName);
                        $('#table-elements').append("<tr class='nondraggable' id=" + uniqElemName + "><td style='width: 94px'>" + indBL + "</td><td style='width: 260px'>" + $(contents).get(0).title + '</td><td><textarea class="textareatd" maxlength="100" rows="1"/></td></tr>');
                        changeRowNumberBL();
                        setTimeout(sort, 1000);

                }else{
                    bottom_left_size_counter = bottom_left_size_counter - contents.context.clientWidth;
                    counter_bottom_left--;
                    total_sections_number--;
                }
            }
            }else if(class_id.is('#bottom_right')){
                    if(contents.hasClass('drag')) {
                    bottom_right_size_counter = bottom_right_size_counter + contents.context.clientWidth;
                    counter_bottom_right++;
                    total_sections_number++;
                    contents.removeClass('drag');
                    contents.addClass('sortable');
                    contents.addClass('ui-state-error');
                    if(bottom_right_size_counter < 320) {
                        contents.addClass('bottom_right_elem');
                        $('#sortContainer_bottom_right').append(contents);
                        var indBR;
                        if(getBotRightContainerElementsArray()==null){
                            indBR=401;
                        }else{
                            indBR=getBotRightContainerElementsArray().length+401;
                        }
                        var uniqElemName = $(contents).get(0).id + total_sections_number;
                        $(contents).attr('uniqElemName', uniqElemName) ;
                        elementsArray.push(uniqElemName);
                        $('#table-elements').append("<tr class='nondraggable' id=" + uniqElemName + "><td style='width: 94px'>" + indBR + "</td><td style='width: 260px'>" + $(contents).get(0).title + '</td><td><textarea class="textareatd" maxlength="100" rows="1"/></td></tr>');
                        changeRowNumberBR();
                        setTimeout(sort, 1000);
                }else{
                    bottom_right_size_counter = bottom_right_size_counter - contents.context.clientWidth;
                    counter_bottom_right--;
                    total_sections_number--;
                }
            }
        }}
    }) ;

    $('.trash_can').droppable({
        hoverClass: 'border',
        accept: '.sortable',
        drop: function(event, ui){
            total_sections_number--;
            if(ui.draggable.hasClass('top_left_elem')){
                top_left_size_counter = top_left_size_counter - ui.draggable.context.naturalWidth ;
            }else if(ui.draggable.hasClass('top_right_elem')){
                top_right_size_counter = top_right_size_counter - ui.draggable.context.naturalWidth ;
            }else if(ui.draggable.hasClass('bottom_left_elem')){
                bottom_left_size_counter = bottom_left_size_counter - ui.draggable.context.naturalWidth ;
            }else if(ui.draggable.hasClass('bottom_right_elem')){
                bottom_right_size_counter = bottom_right_size_counter - ui.draggable.context.naturalWidth ;
            }
            ui.draggable.remove();

            var uniqElemName = ui.draggable.attr('uniqElemName');
            deleteTableRow(uniqElemName) ;
            if(ui.draggable.hasClass('top_left_elem')){
                changeRowNumberTL();
            }else if(ui.draggable.hasClass('top_right_elem')){
                changeRowNumberTR();
            }else if(ui.draggable.hasClass('bottom_left_elem')){
                changeRowNumberBL();
            }else if(ui.draggable.hasClass('bottom_right_elem')){
                changeRowNumberBR();
            }
            setTimeout(sort, 1000);
        }
    })
});

function deleteTableRow(uniqElemName){
    var tableRows = $('#table-elements').sortable("toArray");
    for(var i = 0; i < tableRows.length; i++){
        var element = document.getElementById(tableRows[i]);
        if(element.id == uniqElemName){
            $(element).remove();
        }
    }

}

function sort () {
    var $tbody = $('#table-elements');
    $tbody.find('tr').sort(function(a,b){
        var tda = $(a).find('td:eq(0)').text(); // can replace 1 with the column you want to sort on
        var tdb = $(b).find('td:eq(0)').text(); // this will sort on the second column
        return tda > tdb ? 1 : tda < tdb ? -1 : 0;
    }).appendTo($tbody);

};

function getTopLeftContainerElementsArray(){
    var elements = (document.getElementsByClassName('top_left_elem'));
    var topLeftAr = [];
    for(var i=0;typeof(elements[i])!='undefined';topLeftAr.push(elements[i++].getAttribute('uniqElemName')));
    if(topLeftAr[0]== null && topLeftAr.length==1){
        return null;
    }else{
        topLeftAr = $.grep(topLeftAr,function(n){ return(n) });

    } return topLeftAr;
}

function getTopRightContainerElementsArray(){
    var elements = (document.getElementsByClassName('top_right_elem'));
    var topRightAr = [];
    for(var i=0;typeof(elements[i])!='undefined';topRightAr.push(elements[i++].getAttribute('uniqElemName')));
    if(topRightAr[0]== null && topRightAr.length==1){
        return null;
    }else{
        topRightAr = $.grep(topRightAr,function(n){ return(n) });
    } return topRightAr;
}

function getBotLeftContainerElementsArray(){
    var elements = (document.getElementsByClassName('bottom_left_elem'));
    var botLeftAr = [];
    for(var i=0;typeof(elements[i])!='undefined';botLeftAr.push(elements[i++].getAttribute('uniqElemName')));
    if(botLeftAr[0]== null && botLeftAr.length==1){
        return null;
    }else{
        botLeftAr = $.grep(botLeftAr,function(n){ return(n) });
    } return botLeftAr;
}

function getBotRightContainerElementsArray(){
    var elements = (document.getElementsByClassName('bottom_right_elem'));
    var botrightAr = [];
    for(var i=0;typeof(elements[i])!='undefined';botrightAr.push(elements[i++].getAttribute('uniqElemName')));
    if(botrightAr[0]== null && botrightAr.length==1){
        return null;
    }else{
        botrightAr = $.grep(botrightAr,function(n){ return(n) });
    } return botrightAr;
}

function changeRowNumberTL(){
    var tableRows = $('#table-elements').sortable("toArray");
    var TopLeftContainerElementsArray = getTopLeftContainerElementsArray();
    if(TopLeftContainerElementsArray != null){
    for(var i = 0; i < tableRows.length; i++){
        for(var j = 0; j < TopLeftContainerElementsArray.length; j++ ){
            if(tableRows[i] == TopLeftContainerElementsArray[j]){
                var element = document.getElementById(tableRows[i]).getElementsByTagName('td')[0];
                $(element).html(j+101);
            }}
        }
    }
}

function changeRowNumberTR(){
    var tableRows = $('#table-elements').sortable("toArray");
    var TopRightContainerElementsArray = getTopRightContainerElementsArray();
    if(TopRightContainerElementsArray != null){
    for(var i = 0; i < tableRows.length; i++){
        for(var j = 0; j < TopRightContainerElementsArray.length; j++ ){
            if(tableRows[i] == TopRightContainerElementsArray[j]){
                var element = document.getElementById(tableRows[i]).getElementsByTagName('td')[0];
                $(element).html(j+201);
            }}
        }
    }
}

function changeRowNumberBL(){
    var tableRows = $('#table-elements').sortable("toArray");
    var BotLeftContainerElementsArray = getBotLeftContainerElementsArray();
    if(BotLeftContainerElementsArray != null){
    for(var i = 0; i < tableRows.length; i++){
        for(var j = 0; j < BotLeftContainerElementsArray.length; j++ ){
            if(tableRows[i] == BotLeftContainerElementsArray[j]){
                var element = document.getElementById(tableRows[i]).getElementsByTagName('td')[0];
                $(element).html(j+301);
            }}
        }
    }
}

function changeRowNumberBR(){
    var tableRows = $('#table-elements').sortable("toArray");
    var BotRightContainerElementsArray = getBotRightContainerElementsArray();
    if(BotRightContainerElementsArray != null){
    for(var i = 0; i < tableRows.length; i++){
        for(var j = 0; j < BotRightContainerElementsArray.length; j++ ){
            if(tableRows[i] == BotRightContainerElementsArray[j]){
                var element = document.getElementById(tableRows[i]).getElementsByTagName('td')[0];
                $(element).html(j+401);
            }}
        }
    }
}

</script> 
	