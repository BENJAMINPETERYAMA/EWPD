
$(document).ready(function(){
		$.ajaxSetup({
			error: function(xhr, options, error) {
				var message;
				if(xhr.status == 0) 
					message = 'Request failed due to bad or no network connection.';
				else if(xhr.status == 404)
					message = 'Requested action not found.';
				else if(xhr.status == 500){
					message = 'Request failed !<br/>&nbsp;&nbsp;&nbsp;';
					message = message + xhr.responseText.substring((xhr.responseText.indexOf('$#~'))+3,xhr.responseText.indexOf('$$~'));
				}	
				else 
					message = 'An unknow error('+xhr.status+') has occured. Please contact your system administrator.';
				addErrorToNotificationTray(message);
				openTrayNotification();
				showTrayNotificationErrorPanel();
			},
			beforeSend: function(xhr) {
				$('#ajaxIdleIcon').css('visibility', 'hidden');
				$('#ajaxActiveIcon').css('visibility', 'visible');
			},
			complete: function() {
				$('#ajaxIdleIcon').css('visibility', 'visible');
				$('#ajaxActiveIcon').css('visibility', 'hidden');
			}
		});
	});

function addErrorToNotificationTray(message) {
	var contentHtml;
	if($('#errorHeader').length == 0 ) {
		contentHtml = "<h6 id='errorHeader' class='ui-accordion-header ui-helper-reset ui-state-active ui-corner-top' role='tab' aria-expanded='true' tabindex='0'><span class='ui-icon ui-icon-triangle-1-s'></span><a href='#' tabindex='0'><font color='red'>Error(s)</font></a></h6>" +
	 "<div id='errorPanel' class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active' style='padding-top: 11px; padding-bottom: 11px; height:51px;' role='tabpanel'>" +
          "<p ><b><font color='red'> # </font><font color='red'>"+ message +"</font></b></p>" +
	 "</div>";
		$('#messageAaccordion').html(contentHtml);
	}else {
		contentHtml = "<p ><b><font color='red'> # </font><font color='red'>"+message+"</font></b></p>";
		$('#errorPanel').html(contentHtml);
	}
}

function addWarnToNotificationTray(message) {
	var contentHtml;
	if($('#warningHeader').length == 0 ) {
		contentHtml = "<h6 id='warningHeader' class='ui-accordion-header ui-helper-reset ui-state-default ui-corner-all' role='tab' aria-expanded='false' tabindex='-1'><span class='ui-icon ui-icon-triangle-1-e'></span><a href='#' tabindex='0'><font color='orange'>Warning(s)</font></a></h6>" +
	 "<div id='warningPanel' class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom' style='display: block; padding-top: 11px; padding-bottom: 11px;height:51px;' role='tabpanel'>" +
          "<p ><b><font color='orange'> # </font><font color='orange'>"+ message +"</font></b></p>" +
	 "</div>";
		$('#messageAaccordion').html(contentHtml);
	}else {
		contentHtml = "<p ><b><font color='orange'> # </font><font color='orange'>"+message+"</font></b></p>";
		$('#warningPanel').html(contentHtml);
	}
}

function addInfoToNotificationTray(message) {
	var contentHtml;
	if($('#infoHeader').length == 0 ) {
		contentHtml = "<h6 id='infoHeader' class='ui-accordion-header ui-helper-reset ui-state-default ui-corner-all' role='tab' aria-expanded='false' tabindex='-1'><span class='ui-icon ui-icon-triangle-1-e'></span><a href='#' tabindex='0'><font color='blue'>Information</font></a></h6>" +
		"<div id='infoPanel' class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom' style='display: block;height:51px;' role='tabpanel'>" +
          "<p ><b><font color='blue'> # </font><font color='blue'>"+ message +"</font></b></p>" +
	 "</div>";
		$('#messageAaccordion').html(contentHtml);
	}else {
		contentHtml = "<p ><b><font color='blue'> # </font><font color='blue'>"+message+"</font></b></p>";
		$('#infoPanel').html(contentHtml);
	}
}

function openTrayNotification(){
	if($('#messageDialog').dialog('isOpen')){
		$('#messageDialog').dialog('destroy');
		registerTrayNotification();
	}
	$('#messageDialog').dialog('open');
}

//function to clear previous messages from the message tary 
function resetMessages() {
	addErrorToNotificationTray('');
	addInfoToNotificationTray('');
	addWarnToNotificationTray('');
	$('#messageDialog').dialog('destroy');
}


function showTrayNotificationInfoPanel(){
	$("#infoHeader").trigger('mouseover');
}

function showTrayNotificationWarnPanel(){
	$("#warningHeader").trigger('mouseover');
}

function showTrayNotificationErrorPanel(){
	$("#errorHeader").trigger('mouseover');
}

function registerTrayNotification() {
     
     var screenheight = screen.height;
     var dialogStartX = screen.width - 409;      
	$("#messageAaccordion").accordion({event: "mouseover"});
	  $("#messageDialog").dialog({
							title: '',
							position: [dialogStartX,'bottom'],
	                        height:'auto',
							width:'380px',	
	                        show:'drop',
							hide:'drop',
	                        modal: false,
							resizable:false,
							draggable: true,
							autoOpen : false		
	  });
    $("#messageDialog").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").append('<a id ="minimizeBar" title="Click to Hide Messages" onClick="minimiseDialog();" href ="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Messages&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>');         

    $("#messageDialogMinimised").dialog({
							title: '',
							position: ['right','bottom'],
	                        height:0,
							width:'130px',	
	                        show:'drop',
							hide:'drop',
	                        modal: false,
							resizable:false,
							draggable: false,
							autoOpen : false		
	  });
    $("#messageDialogMinimised").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").append('<a id ="minimizeBar" title="Click to View Messages" onClick="restoreDialog();" href ="#">&nbsp;Messages&nbsp;</a>');          
}

function minimiseDialog()
{ 
    $("#messageDialog").dialog('close'); 
    $("#messageDialogMinimised").dialog('open');
}
function restoreDialog()
{ 
    $("#messageDialogMinimised").dialog('close'); 
    $("#messageDialog").dialog('open');
} 
