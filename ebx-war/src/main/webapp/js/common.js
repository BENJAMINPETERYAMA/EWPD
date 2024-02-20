function isElementDefined(id) {
	if(typeof(id)!= 'undefined'){
		return true;
	}
	return false;
}


function isElementDefined(id) {
	if(typeof(id)!= 'undefined'){
		return true;
	}
	return false;
}

/**
*Will display description accordinh to the item selecetd from the autocomplete list.
*/
function displayLabelForSelectedItem(text,list,labelId,descForInvalidMsg){
	var valid = false;
	var desc = "";
	text = $.trim(text);
		$.each(list , function(index, rows){
			var val = rows.value;
			if(val.toUpperCase()==text.toUpperCase()){
				valid = true;
				desc = rows.id;
			}
			}
		);
		if(text != null && text.length > 0){
			if(valid){
				$('#'+labelId).text(desc).removeClass('invalid_hippacode_value');
				return true;
			}else{
				$('#'+labelId).text(descForInvalidMsg).addClass('invalid_hippacode_value'); 
				return false;
			}
		}
}

function confirmationDialog(action){
$("#confirmationDiv").html(warningMsgForCancel);
$("#confirmationDiv").addClass("UnmappedVariables");
$("#confirmationDiv").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:130,
			modal: true,
			buttons: {			
			Cancel: function() {
					$(this).dialog('close');
				},		
			Ok: function() {
					window.location.href=action;
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDiv").dialog('open');
}



function confirmationDialogForDelete(action, formName){
$("#confirmationDivDelete").html(exclusionDeleteMessage);
$("#confirmationDivDelete").addClass("UnmappedVariables");
$("#confirmationDivDelete").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:130,
			modal: true,
			buttons: {
				Cancel: function() {
					$(this).dialog('close');
				},
				Ok: function() {
					document.forms[formName].action= action;
					$(this).dialog('close');
            		document.forms[formName].submit();
				}
			}
		});
		$("#confirmationDivDelete").dialog('open');
}

function confirmationDialogDiscardChanges(action, formName){
$("#confirmationDivDiscardChanges").html(warningMsgForDiscardChanges);
$("#confirmationDivDiscardChanges").addClass("UnmappedVariables");
$("#confirmationDivDiscardChanges").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:130,
			modal: true,
			buttons: {
				Cancel: function() {
					$(this).dialog('close');
				},
				Ok: function() {
					document.forms[formName].action= action;
					$(this).dialog('close');
            		document.forms[formName].submit();
				}
			}
		});
		$("#confirmationDivDiscardChanges").dialog('open');
}


      
/* To store the all the inputs field values */
function storeinputs(){
	var textbox = $("input[type='text']");
	var text='';
	var seperator='`';
	textbox.each(function(i) {
		text = $.trim($(this).val());
		inputsString = inputsString+seperator+text;
	});
	var textarea = $('textarea');
	var textareabox='';
	textarea.each(function(i) {
	var textareaname=$(this).attr('name');
		if(textareaname!='extndMessage1'&&textareaname!='extndMessage2'&&textareaname!='extndMessage3')
		{
			textareabox = $.trim($(this).val());
			inputsString = inputsString+seperator+textareabox;
		}
	});
	var checkbox = $('input:checkbox');	
	var checked="1";
	var unChecked="0";
	checkbox.each(function(i) {
		if(checkbox[i].checked){
			inputsString = inputsString+seperator+checked;
		}else{
			inputsString = inputsString+seperator+unChecked;
		}
	});
	inputsString=inputsString+$("#eb01ExtndMsgJsonObj").val();
	inputsString=inputsString+$("#eb03ExtndMsgJsonObj").val();
}

/*To compare the data stored in the 'inputsString' and the current value in the
'input' and navigating from the page according to the action performed.*/
function compareInputs(){
		var textbox = $("input[type='text']");
		var text="";
		var seperator="`";
		var newInputs ="";
		textbox.each(function(i) {
			text = $.trim($(this).val());
			newInputs = newInputs+seperator+text;
		});
		var textarea = $('textarea');
		var textareabox="";
		textarea.each(function(i) {
		var textareaname=$(this).attr('name');
			if(textareaname!='extndMessage1'&&textareaname!='extndMessage2'&&textareaname!='extndMessage3')
			{
				textareabox = $.trim($(this).val());
				newInputs = newInputs+seperator+textareabox;
			}
	});

		var checkbox = $('input:checkbox');	
		var checked="1";
		var unChecked="0";
		checkbox.each(function(i) {
			if(checkbox[i].checked){
				newInputs = newInputs+seperator+checked;
			}else{
				newInputs = newInputs+seperator+unChecked;
			}
		});
		
		newInputs=newInputs+$("#eb01ExtndMsgJsonObj").val();
		newInputs=newInputs+$("#eb03ExtndMsgJsonObj").val();
		
		if(inputsString != newInputs ){
			return true;
		}else{
			return false;
		}
}


//validation for 'cancel' button.
function openConfirmationDialog(action){
	if(compareInputs()){
		confirmationDialog(action);
	}else{
		window.location.href = action;
	}
}

/*The function is used for populating the hippasegment tooltip.*/
function displayInfo(hippaSegmentName,e,url){
	  var x = e.clientX;
	  var y = e.clientY;
	//alert($(context));
	$.ajax({
				url:url,
				dataType:"json",
				type:"POST",
				data: "hippaSegmentName="+hippaSegmentName,
				success: function(data){
					var hippaName = data.hippaSegmentName;
					if(hippaName!=null){
						
						if(hippaName == 'MSG'){
							hippaName = "Message";
						}
						if(hippaName == 'NOTE_TYPE_CODE'){
							hippaName = "Note Type";
						}
						if(hippaName == 'NOTE_TYPE_CODE_SRCH'){
							hippaName = "Note Type";
						}
						if(hippaName == 'III02_SRCH'){
							hippaName = "III02";
						}
						if(hippaName == 'EB03_SRCH'){
							hippaName = "EB03";
						}
						if(hippaName == 'MSG_SRCH'){
							hippaName = "Message";
						}
						if(hippaName == 'EB01_SRCH'){
							hippaName = "EB01";
						}
						//January Release
						if(hippaName == 'CONTSEARCH'){
							hippaName = "Contract";
						}
						//BXNI June 2012 Release
						if(hippaName == 'MAJ_HEADING_SRCH'){
							hippaName = "Major Heading";
						}
						if(hippaName == 'MIN_HEADING_SRCH'){
							hippaName = "Minor Heading";
						}
						if(hippaName == 'STARTAGE'){
							hippaName = "Start Age";
						}
						if(hippaName == 'ENDAGE'){
							hippaName = "End Age";
						}
						if(hippaName == '2120_LOOP_NM1_MESSAGE_SEGMENT'){
							hippaName =  "2120 LOOP NM1 MESSAGE SEGMENT";
						}
						
					}
					$("#toolTipSideHeader").html(data.hippaSegmentDesc);
					$("#toolTipData").html("<p>"+data.hippaSegmentDef+"</p>");
					$("#toolTipDiv").show();
					$("#toolTipDiv").dialog({
                              title: hippaName,
                              position: [x,y],
                              height: 'auto',
                              width:'350px',    
                              show:'clip',
                              hide:'clip',
                              modal: false,
                              resizable:false,
                              draggable: true,
                              autoOpen : true      
        			});
			  }
	});
     
  }// display info
 
$(document).ready(function(){
	$("#toolTipDiv").hide();
});

/*close the 'whatIsThisDiv' dialog*/
function closeWhatisthis(){
     $("#toolTipDiv").dialog("close");
     $("#toolTipDiv").hide();
}
var formats = new Array();
function getSPSformat(){
	var textbox = $("input[id^='spsFormat']");
	textbox.each(function(i){	
		formats[i] = $.trim($(this).val());			
	});
	return formats;
}

//BXNI Code Change
// Validation for Exclusion of Special Characters in Message Text. 
function checkMessageValidation(msgText){
	var spclChars = "*^~{}#_:;?![]<>-";
		for (var i = 0; i < msgText.length; i++) {
			if (spclChars.indexOf(msgText.charAt(i)) != -1) {
	             return false;
	         } 
	    }
	return true;
}

function autoPopulateHSD01(){
	var variableId = $("#variableIdHidden").val();
	var hsd01 = $("#HSD01Id").val();
	var startAge = $("#startAge").val();
	var endAge = $("#endAge").val();
	var format = $("#variableFormat").val();
	var pageFrom = $("#pageFrom").val();
	var eb09 = $("#EB09Id").val();
	if(null == hsd01 || hsd01 == ""){
		if((null != startAge && startAge != "") || (null != endAge && endAge != "")){
		if( format in formatArray(['VST','VISIT','VISITS','HR','HRS','HOUR','HOURS','DAY','DAYS','LEN','DY','DYS','MTH','MTHS','MONTH','MONTHS','OCC','OCRS','OCR']) ){
	$.ajax({
			url: "../editmapping/autopopulateHSD.ajax",
			dataType: "json",
			type: "POST",			
			data: "variableId="+variableId+"&format="+format+"&pageFrom="+pageFrom+"&eb09="+eb09,
			success: function(data) {
				
				$("#HSD01Id").val(data.HsdValue);
				$("#HSD01IdLabel").html(data.HsdDescription);
				$("#EB09Id").val("");
				$("#EB09IdLabel").val("");
				$("#EB09IdLabel").html("");
				
			}
		});	
	}
	}
	}
	}

	function formatArray(arrayOfFormats)
	{
	var outputArray = {};
	for(var i=0;i<arrayOfFormats.length;i++)
	{
	  outputArray[arrayOfFormats[i]]='';
	}
	return outputArray;
	}


	
	/** Auto complete function for Note Type Code for Individual EB03 Association*/
	  function autocompleteNoteType() {
		  $(document).ready(function(){
			$('#NOTETYPEID0').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID0Label").text('');
			   }
			   	$('#NOTETYPEID0').val($('#NOTETYPEID0').val().toUpperCase());
			});
			$("#NOTETYPEID0").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID0Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID0Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID1').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID1Label").text('');
			   }
			   	$('#NOTETYPEID1').val($('#NOTETYPEID1').val().toUpperCase());
			});
			$("#NOTETYPEID1").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID1Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID1Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  $(document).ready(function(){
			$('#NOTETYPEID2').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID2Label").text('');
			   }
			   	$('#NOTETYPEID2').val($('#NOTETYPEID2').val().toUpperCase());
			});
			$("#NOTETYPEID2").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID2Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID2Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  $(document).ready(function(){
			$('#NOTETYPEID3').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID3Label").text('');
			   }
			   	$('#NOTETYPEID3').val($('#NOTETYPEID3').val().toUpperCase());
			});
			$("#NOTETYPEID3").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID3Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID3Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID4').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID4Label").text('');
			   }
			   	$('#NOTETYPEID4').val($('#NOTETYPEID4').val().toUpperCase());
			});
			$("#NOTETYPEID4").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID4Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID4Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  $(document).ready(function(){
			$('#NOTETYPEID5').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID5Label").text('');
			   }
			   	$('#NOTETYPEID5').val($('#NOTETYPEID5').val().toUpperCase());
			});
			$("#NOTETYPEID5").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID5Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID5Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID6').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID6Label").text('');
			   }
			   	$('#NOTETYPEID6').val($('#NOTETYPEID6').val().toUpperCase());
			});
			$("#NOTETYPEID6").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID6Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID6Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  $(document).ready(function(){
			$('#NOTETYPEID7').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID7Label").text('');
			   }
			   	$('#NOTETYPEID7').val($('#NOTETYPEID7').val().toUpperCase());
			});
			$("#NOTETYPEID7").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID7Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID7Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID8').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID8Label").text('');
			   }
			   	$('#NOTETYPEID8').val($('#NOTETYPEID8').val().toUpperCase());
			});
			$("#NOTETYPEID8").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID8Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID8Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID9').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID9Label").text('');
			   }
			   	$('#NOTETYPEID9').val($('#NOTETYPEID9').val().toUpperCase());
			});
			$("#NOTETYPEID9").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID9Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID9Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID10').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID10Label").text('');
			   }
			   	$('#NOTETYPEID10').val($('#NOTETYPEID10').val().toUpperCase());
			});
			$("#NOTETYPEID10").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID10Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID10Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID11').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID11Label").text('');
			   }
			   	$('#NOTETYPEID11').val($('#NOTETYPEID11').val().toUpperCase());
			});
			$("#NOTETYPEID11").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID11Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID11Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID12').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID12Label").text('');
			   }
			   	$('#NOTETYPEID12').val($('#NOTETYPEID12').val().toUpperCase());
			});
			$("#NOTETYPEID12").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID12Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID12Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID13').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID13Label").text('');
			   }
			   	$('#NOTETYPEID13').val($('#NOTETYPEID13').val().toUpperCase());
			});
			$("#NOTETYPEID13").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID13Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID13Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  
		  $(document).ready(function(){
			$('#NOTETYPEID14').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID14Label").text('');
			   }
			   	$('#NOTETYPEID14').val($('#NOTETYPEID14').val().toUpperCase());
			});
			$("#NOTETYPEID14").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID14Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID14Label",invalidHippaCodeValue);
					}
				}
			})
		  });
		  
		  $(document).ready(function(){
			$('#NOTETYPEID15').blur(function() {
			   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			   	$("#NOTETYPEID15Label").text('');
			   }
			   	$('#NOTETYPEID15').val($('#NOTETYPEID15').val().toUpperCase());
			});
			$("#NOTETYPEID15").autocomplete({ 
				select: function(event, ui) { $("#NOTETYPEID15Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=NOTE_TYPE_CODE",
								success: function(data) {
								cache: false,
								response(data.rows);
								list = data.rows;
								}
							});
						},
				change: function(event, ui) {
					var text = $(this).val();
					if(!ui.item){
						displayLabelForSelectedItem(text,list,"NOTETYPEID15Label",invalidHippaCodeValue);
					}
				}
			})
		  });
	  };
	  
	  function changeTextToUpperCase(id){
		  if(null != $('#'+id).val()){
			$('#'+id).val($('#'+id).val().toUpperCase());
		  }
		}
	  

	//SSCR 19537 - April 04 - End
