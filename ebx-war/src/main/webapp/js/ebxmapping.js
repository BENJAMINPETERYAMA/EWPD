//This function will be invoked to insert values for the dynamic textboxes on page reload
function insertEB03(tableID,cellId,cellName,text,isComingFromAddButton,labelId,description,descId){
      if($('#'+cellId).length > 0){
            $('#'+cellId).val(text);
      }else{      		
            addRowEB03ForRule(tableID,"EB03Id",cellName,text,isComingFromAddButton,labelId);
      }
        $('#'+labelId).text(description);
}
function addRowEB03ForRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
var table = document.getElementById(tableID);
	var tbody = document.getElementById("ebCodeTbody");
	var trClass = "";
	var cellId1;
	var labelId1;
	var cellId2;
	var labelId2;
	var cellId3;
	var labelId3;
	var clmnCount;
	var rowCount = $('#ebCodesTable > tbody > tr').length;
	var clmnLength = $('#ebCodesTable > tbody > tr > td').length;
	if (rowCount >= 33) {
		$('#eb03AddButton').hide();
		return false;
	}
	if (rowCount % 2 == 0) {
		trClass = "alternate";
	} else {
		trClass = "white-bg";
	}
	var row = document.createElement("TR");
	row.className=trClass;
	var td1 = document.createElement("TD");
	var td2 = document.createElement("TD");
	var td3 = document.createElement("TD");
	var td4 = document.createElement("TD");
	var td5 = document.createElement("TD");
	var td6 = document.createElement("TD");
	var td7 = document.createElement("TD");
	var td8 = document.createElement("TD");
	var td9 = document.createElement("TD");
	
	if(isComingFromAddButton){
		clmnCount = clmnLength/3;
		labelId1 = "EB03IdLabel"+clmnCount;
		cellId1 = cellId+clmnCount;
	}
	// else condition not handled
	A = document.createElement("input");
	A.setAttribute("type","text");
	A.setAttribute("id",cellId1);
	A.setAttribute("name",$.trim(cellName));
	A.className="inputbox60";
	A.setAttribute("value",text);
	td1.style.width = "95px";
	td1.appendChild(A);
	
	B = document.createElement("input");
	B.setAttribute("type","hidden");
	B.setAttribute("id","EB03Desc"+clmnCount);
	B.setAttribute("name",'EB03Desc');
	td1.appendChild(B);
	
	L = document.createElement("label");
	L.setAttribute("id",labelId1);
	L.setAttribute("for",cellId1);
	L.className="UnmappedVariables";
	td2.style.width = "140px";
	td2.appendChild(L);
		
	if(isComingFromAddButton){
		clmnCount = clmnCount+1;
		labelId2 = "EB03IdLabel"+clmnCount;
		cellId2 = cellId+clmnCount;
	}
	
	E = document.createElement("input");
	E.setAttribute("type","text");
	E.setAttribute("id",cellId2);
	E.setAttribute("name",$.trim(cellName));
	E.className="inputbox60";
	E.setAttribute("value","");
	td4.style.width = "95px";
	td4.appendChild(E);
	
	F = document.createElement("input");
	F.setAttribute("type","hidden");
	F.setAttribute("id","EB03Desc"+clmnCount);
	F.setAttribute("name",'EB03Desc');
	td4.appendChild(F);
	
	M = document.createElement("label");
	M.setAttribute("id",labelId2);
	M.setAttribute("for",cellId2);
	M.className="UnmappedVariables";
	td5.style.width = "140px";
	td5.appendChild(M);
		
	if(isComingFromAddButton){
		clmnCount = clmnCount+1;
		labelId3 = "EB03IdLabel"+clmnCount;
		cellId3 = cellId+clmnCount;
	}

	I = document.createElement("input");
	I.setAttribute("type","text");
	I.setAttribute("id",cellId3);
	I.setAttribute("name",$.trim(cellName));
	I.className="inputbox60";
	I.setAttribute("value","");
	td7.style.width = "95px";
	td7.appendChild(I);
	
	J = document.createElement("input");
	J.setAttribute("type","hidden");
	J.setAttribute("id","EB03Desc"+clmnCount);
	J.setAttribute("name",'EB03Desc');
	td7.appendChild(J);
	
	N = document.createElement("label");
	N.setAttribute("id",labelId3);
	N.setAttribute("for",cellId3);
	N.className="UnmappedVariables";
	td8.style.width = "140px";
	td8.appendChild(N);
		
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.appendChild(td5);
	row.appendChild(td6);
	row.appendChild(td7);
	row.appendChild(td8);	
	row.appendChild(td9);
	
	tbody.appendChild(row);	
	
	autoCompleteForDynamicEB033(cellId1, labelId1);
	autoCompleteForDynamicEB032(cellId2, labelId2);
	autoCompleteForDynamicEB031(cellId3, labelId3);
	
rowCount = $('#ebCodesTable > tbody > tr').length;	
if(rowCount>=33){
	$('#eb03AddButton').hide();
}
return true;
}

function autoCompleteForDynamicEB031(cellId,labelId){
	autoCompleteForDynamicEB03(cellId,labelId);	
}
function autoCompleteForDynamicEB032(cellId,labelId){
	autoCompleteForDynamicEB03(cellId,labelId);	
}
function autoCompleteForDynamicEB033(cellId,labelId){
	autoCompleteForDynamicEB03(cellId,labelId);	
}
function autoCompleteForDynamicEB03(cellId,labelId){
		$('#'+cellId).blur(function() {
   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
   				$('#'+labelId).text('');
   			}
   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
   			sensitiveBenefitCheck();
   			
		});
		
		$('#'+cellId).autocomplete({ 
			select: function(event, ui) { $('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocomplete.ajax",
							dataType: "json",
							data: "key="+request.term + "&name=EB03",
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
					displayLabelForSelectedItem(text,list,labelId,invalidHippaCodeValue);
				}
			}
		});
}

/**
will open a modal dialog with the rsponse of ajax call.
This is used for showing the possible values of hippa codes.
*/
function popupDivHippaSegment(hippaSegmentName){	
	popupDiv(hippaSegmentName, "../ajaxpopuphippasegment.ajax");	
}//end popupDiv

//For retaining the description on the page.
function setDescription(){
	var desc = $('#EB01IdLabel').text();
	$('#EB01Desc').val(desc);
	
	desc = $('#EB02IdLabel').text();
	$('#EB02Desc').val(desc);
	
	desc = $('#EB06IdLabel').text();
	$('#EB06Desc').val(desc);
	
	desc = $('#EB09IdLabel').text();
	$('#EB09Desc').val(desc);
	
	desc = $('#HSD01IdLabel').text();
	$('#HSD01Desc').val(desc);
	
	desc = $('#HSD02IdLabel').text();
	$('#HSD02Desc').val(desc);
	
	desc = $('#HSD03IdLabel').text();
	$('#HSD03Desc').val(desc);

	desc = $('#HSD04IdLabel').text();
	$('#HSD04Desc').val(desc);

	desc = $('#HSD05IdLabel').text();
	$('#HSD05Desc').val(desc);

	desc = $('#HSD06IdLabel').text();
	$('#HSD06Desc').val(desc);

	desc = $('#HSD07IdLabel').text();
	$('#HSD07Desc').val(desc);

	desc = $('#HSD08IdLabel').text();
	$('#HSD08Desc').val(desc);
	
	desc = $('#III02IdLabel').text();
	$('#III02Desc').val(desc);

	desc = $('#NOTETYPEIDLabel').text();
	$('#NoteTypeDesc').val(desc);

	desc = $('#accumSpsIdLabel').text();
	$('#accumDesc').val(desc);

	var rowLength = $('#ebCodesTable > tbody > tr').length;
	for(var i = 0; i < rowLength * 3; i++){	
		$('#EB03Desc'+i).val($('#EB03IdLabel'+i).text());
	}
	
	rowLength = $('#umRuleTable > tbody > tr').length;
	for(var i = 0; i < rowLength * 3; i++){ 
		$('#UMRuleDesc'+i).val($('#UMRuleIdLabel'+i).text());
	}
}

function setLabel(labelId,valueToBeSet){
	$('#'+labelId).html(valueToBeSet);
}
//validation for 'delete' button.
function confirmationDialogDeleteChanges(action, formName){
$("#confirmationDivDeleteChanges").html(warningMsgForDelete);
$("#confirmationDivDeleteChanges").addClass("UnmappedVariables");
$("#confirmationDivDeleteChanges").dialog({
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
		$("#confirmationDivDeleteChanges").dialog('open');
}

	function enableOrDisableEb02(){
	//EB02 is a hidden field. This field will be displayed only when the  EB01 value entered is either C,G,DW,A or B 
		if($('#EB01Id').val() == "C" ||$('#EB01Id').val() == "G" || $('#EB01Id').val() == "DW" || $('#EB01Id').val() == "A" || $('#EB01Id').val() == "B") {
				$('#EB02Id').show();
				$('#eb02td').show();
				$('#EB02IdLabel').show();				
			}
			else {
				$('#EB02Id').hide();
				$('#eb02td').hide();
				$('#EB02IdLabel').hide();
				$('#EB02Id').val("");		
				$('#EB02IdLabel').text('');					
			}
  }
$(document).ready(function(){
	enableOrDisableEb02();
	$('#EB01Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB01IdLabel").text('');
	   }
	   $('#EB01Id').val($('#EB01Id').val().toUpperCase());
	   enableOrDisableEb02();
	});
	var variableFormat = "";
	variableFormat = $("#variableFormat").val();
	var spsFormat = new Array();						
	spsFormat = getSPSformat();			
		
	$("#EB01Id").autocomplete({ 
		select: function(event, ui) { $("#EB01IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term +"&name=EB01"+"&varformat="+variableFormat+"&spsFormat="+spsFormat,
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
				displayLabelForSelectedItem(text,list,"EB01IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
  
 $(document).ready(function(){
	$('#EB02Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB02IdLabel").text('');
	   }
	   $('#EB02Id').val($('#EB02Id').val().toUpperCase());
	});
	$("#EB02Id").autocomplete({ 
		select: function(event, ui) { $("#EB02IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },				
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB02",
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
				displayLabelForSelectedItem(text,list,"EB02IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
 
$(document).ready(function(){
	$('#EB03Id0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel0").text('');
	   }
	   $('#EB03Id0').val($('#EB03Id0').val().toUpperCase());
	    $('#EB03IdLabel0').val($('#EB03IdLabel0').val().toUpperCase());
	    sensitiveBenefitCheck();
	});  
	$("#EB03Id0").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel0",invalidHippaCodeValue);
			}
		}
						
				
	})
  });
  // autocomplete
var list = new Array();
$(document).ready(function(){
	$('#EB03Id1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel1").text('');
	   }
	   $('#EB03Id1').val($('#EB03Id1').val().toUpperCase());
	    $('#EB03IdLabel1').val($('#EB03IdLabel1').val().toUpperCase());
	    sensitiveBenefitCheck();
	});
	$("#EB03Id1").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel1",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#EB03Id2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel2").text('');
	   }
	   $('#EB03Id2').val($('#EB03Id2').val().toUpperCase());
	    $('#EB03IdLabel2').val($('#EB03IdLabel2').val().toUpperCase());
	    sensitiveBenefitCheck();
	});
	$("#EB03Id2").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel2",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#EB03Id3').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel3").text('');
	   }
	   $('#EB03Id3').val($('#EB03Id3').val().toUpperCase());
	   $('#EB03IdLabel3').val($('#EB03IdLabel3').val().toUpperCase());
	   sensitiveBenefitCheck();
	});
	$("#EB03Id3").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel3").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel3",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#EB03Id4').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel4").text('');
	   }
	   $('#EB03Id4').val($('#EB03Id4').val().toUpperCase());
	   $('#EB03IdLabel4').val($('#EB03IdLabel4').val().toUpperCase());
	   sensitiveBenefitCheck();
	});
	$("#EB03Id4").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel4").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel4",invalidHippaCodeValue);
			}
		}
	})
  });
  $(document).ready(function(){
	$('#EB03Id5').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel5").text('');
	   }
	   $('#EB03Id5').val($('#EB03Id5').val().toUpperCase());
	   sensitiveBenefitCheck();
	});
	$("#EB03Id5").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel5").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel5",invalidHippaCodeValue);
			}
		}
	})
  });
  $(document).ready(function(){
		var table = document.getElementById('ebCodesTable');
		if(table){
			var rowCount = table.rows.length;
			if(rowCount>=33){
				$('#eb03AddButton').hide();
			}
		}

});


$(document).ready(function(){
	$('#EB06Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB06IdLabel").text('');
	   }
	   $('#EB06Id').val($('#EB06Id').val().toUpperCase());
	});
	$("#EB06Id").autocomplete({ 
		select: function(event, ui) { $("#EB06IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },	
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB06",
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
				displayLabelForSelectedItem(text,list,"EB06IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
$(document).ready(function(){
	$('#EB09Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB09IdLabel").text('');
	   }
	   $('#EB09Id').val($('#EB09Id').val().toUpperCase());
	});
	$("#EB09Id").autocomplete({ 
		select: function(event, ui) { $("#EB09IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },	
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB09",
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
				displayLabelForSelectedItem(text,list,"EB09IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
//apply css to invalid hippaSegment label
$(document).ready(function(){
		$("label").each(function(){
			var labelText = $.trim($(this).text());
			if(labelText!=null && labelText.length > 0){
				if(labelText == $.trim(invalidHippaCodeValue)){
					$(this).addClass('invalid_hippacode_value');
				}
			}
		});
});


$(document).ready(function(){
	$('#accumSpsId').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#accumSpsIdLabel").text('');
	   }
  	   $('#accumSpsId').val($('#accumSpsId').val().toUpperCase());	   
	});
	$("#accumSpsId").autocomplete({ 
		select: function(event, ui) { $("#accumSpsIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=ACCUMULATOR REFERENCE",
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
				displayLabelForSelectedItem(text,list,"accumSpsIdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#HSD01Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#HSD01IdLabel").text('');
	   }
	   $('#HSD01Id').val($('#HSD01Id').val().toUpperCase());
	});
	$("#HSD01Id").autocomplete({ 
		select: function(event, ui) { $("#HSD01IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD01",
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
				displayLabelForSelectedItem(text,list,"HSD01IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
 
 $(document).ready(function(){
	$('#HSD02Id').blur(function() {
	   $('#HSD02Id').val($('#HSD02Id').val().toUpperCase());
	});
});
$(document).ready(function(){
	$('#HSD03Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#HSD03IdLabel").text('');
	   }
	   $('#HSD03Id').val($('#HSD03Id').val().toUpperCase());
	});
	$("#HSD03Id").autocomplete({ 
		select: function(event, ui) { $("#HSD03IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD03",
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
				displayLabelForSelectedItem(text,list,"HSD03IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#HSD04Id').blur(function() {
	   $('#HSD04Id').val($('#HSD04Id').val().toUpperCase());
	});
});
  
$(document).ready(function(){
	$('#HSD05Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#HSD05IdLabel").text('');
	   }
	   $('#HSD05Id').val($('#HSD05Id').val().toUpperCase());
	});
	$("#HSD05Id").autocomplete({ 
		select: function(event, ui) { $("#HSD05IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD05",
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
				displayLabelForSelectedItem(text,list,"HSD05IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });  
  
$(document).ready(function(){
	$('#HSD06Id').blur(function() {
	   $('#HSD06Id').val($('#HSD06Id').val().toUpperCase());
	});
});
  
$(document).ready(function(){
	$('#HSD07Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#HSD07IdLabel").text('');
	   }
	   $('#HSD07Id').val($('#HSD07Id').val().toUpperCase());
	});
	$("#HSD07Id").autocomplete({ 
		select: function(event, ui) { $("#HSD07IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD07",
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
				displayLabelForSelectedItem(text,list,"HSD07IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#HSD08Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#HSD08IdLabel").text('');
	   }
	   $('#HSD08Id').val($('#HSD08Id').val().toUpperCase());
	});
	$("#HSD08Id").autocomplete({ 
		select: function(event, ui) { $("#HSD08IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD08",
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
				displayLabelForSelectedItem(text,list,"HSD08IdLabel",invalidHippaCodeValue);
			}
		}
	})
  });
  $(document).ready(function(){
	$('#NOTETYPEID').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#NOTETYPEIDLabel").text('');
	   }
	   	$('#NOTETYPEID').val($('#NOTETYPEID').val().toUpperCase());
	});
	$("#NOTETYPEID").autocomplete({ 
		select: function(event, ui) { $("#NOTETYPEIDLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=Note_Type_Code",
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
				displayLabelForSelectedItem(text,list,"NOTETYPEIDLabel",invalidHippaCodeValue);
			}
		}
	})
  });
 
function trimTextValues(){
		$('#EB01Id').val($.trim($('#EB01Id').val()));
		$('#EB02Id').val($.trim($('#EB02Id').val()));
		$('#EB03Id0').val($.trim($('#EB03Id0').val()));
		$('#EB03Id1').val($.trim($('#EB03Id1').val()));
		$('#EB03Id2').val($.trim($('#EB03Id2').val()));
		$('#EB03Id3').val($.trim($('#EB03Id3').val()));	
		$('#EB03Id4').val($.trim($('#EB03Id4').val()));
		$('#EB03Id5').val($.trim($('#EB03Id5').val()));
		$('#EB06Id').val($.trim($('#EB06Id').val()));
		$('#EB09Id').val($.trim($('#EB09Id').val()));
		$('#III02Id').val($.trim($('#III02Id').val()));
		$('#NOTETYPEID').val($.trim($('#NOTETYPEID').val()));
		$('#HSD01Id').val($.trim($('#HSD01Id').val()));
		$('#HSD02Id').val($.trim($('#HSD02Id').val()));
		$('#HSD03Id').val($.trim($('#HSD03Id').val()));
		$('#HSD04Id').val($.trim($('#HSD04Id').val()));
		$('#HSD05Id').val($.trim($('#HSD05Id').val()));
		$('#HSD06Id').val($.trim($('#HSD06Id').val()));
		$('#HSD07Id').val($.trim($('#HSD07Id').val()));
		$('#HSD08Id').val($.trim($('#HSD08Id').val()));
		$('#accumId0').val($.trim($('#accumId0').val()));
		$('#accumId1').val($.trim($('#accumId1').val()));
		$('#accumId2').val($.trim($('#accumId2').val()));
}
$(document).ready(function(){
		$("#customMessageCopyToDialog").hide();
		$("#ruleCopyToDialog").hide();
		$("#spsCopyToDialog").hide();
		$("#copyToDialog").hide();
		$("#copyToRuleDialog").hide();
		$("#messageTextDialog").hide();
		$('#changeCommentsId').blur(function() {
			$('#changeCommentsId').val($('#changeCommentsId').val().toUpperCase());
		});
		
});

//This function will populate the values from the pop up to the eb03 textboxes
function eb03PopulationOnHeaderRuleMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx){

	var table = document.getElementById('ebCodesTable');
	var rowCount = table.rows.length;
	var j = 0;
	var clmnLength ;
	var clmnCnt;
	
	for(var i=0;i<length;) {
		var text = valuesInTxBx[i];
		if(rowCount <= 33){
			clmnLength = $('#ebCodesTable > tbody > tr > td').length;
			clmnCnt = clmnLength/3;
			if(j >= clmnCnt){
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = 	hippaCodeDescArray[index];
				insertRuleEB03('ebCodesTable','EB03Id'+j,'eb03Val',text,true,'EB03IdLabel'+j,desc,'');							
				j++;	
				i++;
			}else{
				$('#'+ hippaSegmentId +j).val(text);
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = '';
				if(index < 0){
					index = $.inArray(text,copyOfValuesInTxBx);
					if(index > -1){
						desc = 	descForValuesInTxBx[index];
					}
				}else{
					desc = 	hippaCodeDescArray[index];
				}
				$('#'+ hippaSegmentId+'Label'+j).text(desc);
				
				if($.trim(desc) == $.trim(invalidHippaCodeValue)){
					$('#'+ hippaSegmentId+'Label'+j).addClass('invalid_hippacode_value');
				}else{
					$('#'+ hippaSegmentId+'Label'+j).removeClass('invalid_hippacode_value');
				}				
				j++;
				i++;
				
			}
		}
	}//end of for
	//after populating all the values to the textboxes , remove the txtboxes that comes after the newly populated list
	if(clmnCnt >=j){
		for(var k = j;k <clmnCnt;k++){
			$('#'+ hippaSegmentId +k).val('');
			$('#'+hippaSegmentId+'Label'+k).text('');			
			$('#'+hippaSegmentId+'Label'+k).removeClass('invalid_hippacode_value');
		}
	}
	
}

//added for message text search
function openMessageTextDialog(messageId){
	$('#msgTxtEb03Id').val('');
	$('#msgTxtVarId').val('');
	$('#msgTxtId').val('');
	$("#messageTextResult").html('');
	$('#hdMsgTxtId').val('');
	$('#hdMsgTxtEB03Id').val('');
	$('#hdMsgTxtHdrId').val('');
	$('#hdMessageId').val('');
	$('#hdMessageId').val(messageId);
	$("#messageTextDialog").dialog({
        height:'auto',
		resizable:false,
		width:'650px',	
        show:'slide',
		title: 'Search Message Text',
        modal: true
        });
}

function selectMessageText(){
	var messageId = $('#hdMessageId').val();
	$('#'+messageId).val($('#hdMessageDiscription').val());
	$('#messageTextDialog').dialog( "close" );
}
	
//message text search functions ends

/**********************EB01- Data Type Association April2012 - Start ******************/

//Data Type Auto complete for the first 6 rows.
var list = new Array();
$(document).ready(function(){
		$('#dataTypeId0').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#dataTypeIdLabel0").text('');
		   }
		   $('#dataTypeId0').val($('#dataTypeId0').val().toUpperCase());
		    $('#dataTypeIdLabel0').val($('#dataTypeIdLabel0').val().toUpperCase());
		 //   sensitiveBenefitCheck();
		});  
		$("#dataTypeId0").autocomplete({ 
			select: function(event, ui) { $("#dataTypeIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
							dataType: "json",
							type:"POST",
							data: "key="+escape(request.term) + "&name=DATATYPE",
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
					displayLabelForSelectedItem(text,list,"dataTypeIdLabel0",invalidDataType);
				}
			}
							
					
		})
	  }); 

$(document).ready(function(){
	$('#dataTypeId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#dataTypeIdLabel1").text('');
	   }
	   $('#dataTypeId1').val($('#dataTypeId1').val().toUpperCase());
	    $('#dataTypeIdLabel1').val($('#dataTypeIdLabel1').val().toUpperCase());
	 //   sensitiveBenefitCheck();
	});  
	$("#dataTypeId1").autocomplete({ 
		select: function(event, ui) { $("#dataTypeIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=DATATYPE",
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
				displayLabelForSelectedItem(text,list,"dataTypeIdLabel1",invalidDataType);
			}
		}
						
				
	})
}); 

$(document).ready(function(){
	$('#dataTypeId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#dataTypeIdLabel2").text('');
	   }
	   $('#dataTypeId2').val($('#dataTypeId2').val().toUpperCase());
	    $('#dataTypeIdLabel2').val($('#dataTypeIdLabel2').val().toUpperCase());
	 //   sensitiveBenefitCheck();
	});  
	$("#dataTypeId2").autocomplete({ 
		select: function(event, ui) { $("#dataTypeIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=DATATYPE",
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
				displayLabelForSelectedItem(text,list,"dataTypeIdLabel2",invalidDataType);
			}
		}
						
				
	})
}); 
$(document).ready(function(){
	$('#dataTypeId3').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#dataTypeIdLabel3").text('');
	   }
	   $('#dataTypeId3').val($('#dataTypeId3').val().toUpperCase());
	    $('#dataTypeIdLabel3').val($('#dataTypeIdLabel3').val().toUpperCase());
	 //   sensitiveBenefitCheck();
	});  
	$("#dataTypeId3").autocomplete({ 
		select: function(event, ui) { $("#dataTypeIdLabel3").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=DATATYPE",
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
				displayLabelForSelectedItem(text,list,"dataTypeIdLabel3",invalidDataType);
			}
		}
	})
}); 
$(document).ready(function(){
	$('#dataTypeId4').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#dataTypeIdLabel4").text('');
	   }
	   $('#dataTypeId4').val($('#dataTypeId4').val().toUpperCase());
	    $('#dataTypeIdLabel4').val($('#dataTypeIdLabel4').val().toUpperCase());
	 //   sensitiveBenefitCheck();
	});  
	$("#dataTypeId4").autocomplete({ 
		select: function(event, ui) { $("#dataTypeIdLabel4").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=DATATYPE",
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
				displayLabelForSelectedItem(text,list,"dataTypeIdLabel4",invalidDataType);
			}
		}
	})
}); 
$(document).ready(function(){
	$('#dataTypeId5').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#dataTypeIdLabel5").text('');
	   }
	   $('#dataTypeId5').val($('#dataTypeId5').val().toUpperCase());
	    $('#dataTypeIdLabel5').val($('#dataTypeIdLabel5').val().toUpperCase());
	 //   sensitiveBenefitCheck();
	});  
	$("#dataTypeId5").autocomplete({ 
		select: function(event, ui) { $("#dataTypeIdLabel5").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=DATATYPE",
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
				displayLabelForSelectedItem(text,list,"dataTypeIdLabel5",invalidDataType);
			}
		}
	})
}); 

////////////dynamically inserting new rows

function insertDataType(tableID,cellId,cellName,text,isComingFromAddButton,labelId,description,descId){
   if($('#'+cellId).length > 0){
         $('#'+cellId).val(text);
   }else{      		
         addRowDataTypeForEB01(tableID,"dataTypeId",cellName,text,isComingFromAddButton,labelId);
   }
     $('#'+labelId).text(description);
}
function addRowDataTypeForEB01(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
var table = document.getElementById(tableID);
	var tbody = document.getElementById("dataTypeTbody");
	var trClass = "";
	var cellId1;
	var labelId1;
	var cellId2;
	var labelId2;
	var cellId3;
	var labelId3;
	var clmnCount;
	var rowCount = $('#dataTypeTable > tbody > tr').length;
	var clmnLength = $('#dataTypeTable > tbody > tr > td').length;
	
	//if (rowCount >= 33) {
		//$('#dataTypeAddButton').hide();
		//return false;
	//}
	if (rowCount % 2 == 0) {
		trClass = "alternate";
	} else {
		trClass = "white-bg";
	}
	var row = document.createElement("TR");
	row.className=trClass;
	var td1 = document.createElement("TD");
	var td2 = document.createElement("TD");
	var td3 = document.createElement("TD");
	var td4 = document.createElement("TD");
	var td5 = document.createElement("TD");
	var td6 = document.createElement("TD");
	var td7 = document.createElement("TD");
	var td8 = document.createElement("TD");
	var td9 = document.createElement("TD");
	
	if(isComingFromAddButton){
		clmnCount = clmnLength/3;
		labelId1 = "dataTypeIdLabel"+clmnCount;
		cellId1 = cellId+clmnCount;
	}
	// else condition not handled
	A = document.createElement("input");
	A.setAttribute("type","text");
	A.setAttribute("id",cellId1);
	A.setAttribute("name",$.trim(cellName));
	A.className="inputbox60";
	A.setAttribute("value",text);
	td1.style.width = "95px";
	td1.appendChild(A);
	
	B = document.createElement("input");
	B.setAttribute("type","hidden");
	B.setAttribute("id","dataTypeDesc"+clmnCount);
	B.setAttribute("name",'dataTypeDesc');
	td1.appendChild(B);
	
	L = document.createElement("label");
	L.setAttribute("id",labelId1);
	L.setAttribute("for",cellId1);
	L.className="UnmappedVariables";
	td2.style.width = "140px";
	td2.appendChild(L);
		
	if(isComingFromAddButton){
		clmnCount = clmnCount+1;
		labelId2 = "dataTypeIdLabel"+clmnCount;
		cellId2 = cellId+clmnCount;
	}
	
	E = document.createElement("input");
	E.setAttribute("type","text");
	E.setAttribute("id",cellId2);
	E.setAttribute("name",$.trim(cellName));
	E.className="inputbox60";
	E.setAttribute("value","");
	td4.style.width = "95px";
	td4.appendChild(E);
	
	F = document.createElement("input");
	F.setAttribute("type","hidden");
	F.setAttribute("id","dataTypeDesc"+clmnCount);
	F.setAttribute("name",'dataTypeDesc');
	td4.appendChild(F);
	
	M = document.createElement("label");
	M.setAttribute("id",labelId2);
	M.setAttribute("for",cellId2);
	M.className="UnmappedVariables";
	td5.style.width = "140px";
	td5.appendChild(M);
		
	if(isComingFromAddButton){
		clmnCount = clmnCount+1;
		labelId3 = "dataTypeIdLabel"+clmnCount;
		cellId3 = cellId+clmnCount;
	}

	I = document.createElement("input");
	I.setAttribute("type","text");
	I.setAttribute("id",cellId3);
	I.setAttribute("name",$.trim(cellName));
	I.className="inputbox60";
	I.setAttribute("value","");
	td7.style.width = "95px";
	td7.appendChild(I);
	
	J = document.createElement("input");
	J.setAttribute("type","hidden");
	J.setAttribute("id","dataTypeDesc"+clmnCount);
	J.setAttribute("name",'dataTypeDesc');
	td7.appendChild(J);
	
	N = document.createElement("label");
	N.setAttribute("id",labelId3);
	N.setAttribute("for",cellId3);
	N.className="UnmappedVariables";
	td8.style.width = "140px";
	td8.appendChild(N);
		
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.appendChild(td5);
	row.appendChild(td6);
	row.appendChild(td7);
	row.appendChild(td8);	
	row.appendChild(td9);
	
	tbody.appendChild(row);	
	
	autoCompleteForDynamicDataType3(cellId1, labelId1);
	autoCompleteForDynamicDataType2(cellId2, labelId2);
	autoCompleteForDynamicDataType1(cellId3, labelId3);
	
rowCount = $('#dataTypeTable > tbody > tr').length;	
//if(rowCount>=33){
	//$('#eb03AddButton').hide();
//}
return true;
}

function autoCompleteForDynamicDataType1(cellId,labelId){
	autoCompleteForDynamicDataType(cellId,labelId);	
}
function autoCompleteForDynamicDataType2(cellId,labelId){
	autoCompleteForDynamicDataType(cellId,labelId);	
}
function autoCompleteForDynamicDataType3(cellId,labelId){
	autoCompleteForDynamicDataType(cellId,labelId);	
}
function autoCompleteForDynamicDataType(cellId,labelId){
		$('#'+cellId).blur(function() {
			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
				$('#'+labelId).text('');
			}
			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
			$('#'+labelId).val($('#'+labelId).val().toUpperCase());
		//	sensitiveBenefitCheck();
			
		});
		
		$('#'+cellId).autocomplete({ 
			select: function(event, ui) { $('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
				$.ajax({
					url: "../referencedata/fetchDataTypeForAutoComplete.ajax",
					dataType: "json",
					type:"POST",
					data: "key="+escape(request.term) + "&name=DATATYPE",
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
					displayLabelForSelectedItem(text,list,labelId,invalidDataType);
				}
			}
		});
}
/**********************EB01- Data Type Association April2012 - End ******************/

/**********************EB03- Header Rule Association April2012 - Start **************/

function insertHeaderRule(tableID,cellId,cellName,headerRuleValue,isComingFromAddButton,description){
	if($('#'+cellId).length > 0){

          $('#'+cellId).val(headerRuleValue);
          $('#'+cellId).attr('alt', description);
          $('#'+cellId).attr('title', description);
    }else{      		
          addRowForHeaderRule(tableID,"ruleId",cellName,headerRuleValue,isComingFromAddButton,description);
    }
}

function addRowForHeaderRule(tableID,cellId,cellName,headerRuleValue,isComingFromAddButton,description){
	var table = document.getElementById(tableID);
		var tbody = document.getElementById("headerRulebody");
		var trClass = "";
		var cellId1;
		var cellId2;
		var cellId3;
		var cellId4;
		var cellId5;
		var cellId6;
		var clmnCount;
		var rowCount = $('#headerRuletable > tbody > tr').length;
		var clmnLength = $('#headerRuletable > tbody > tr > td').length;
		
		if (rowCount % 2 == 0) {
			trClass = "alternate";
		} else {
			trClass = "white-bg";
		}
		var row = document.createElement("TR");
		row.className=trClass;
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");
		var td5 = document.createElement("TD");
		var td6 = document.createElement("TD");
		var td7 = document.createElement("TD");
		var td8 = document.createElement("TD");
		var td9 = document.createElement("TD");
		var td10 = document.createElement("TD");
		var td11 = document.createElement("TD");
		var td12 = document.createElement("TD");
	
		if(isComingFromAddButton){
			clmnCount = clmnLength/2;
			cellId1 = cellId+clmnCount;
		}
		
		A = document.createElement("input");
		A.setAttribute("type","text");
		A.setAttribute("id",cellId1);
		A.setAttribute("name",$.trim(cellName));
		A.className="inputbox60";
		A.setAttribute("value",headerRuleValue);
		A.setAttribute("alt",description);
		A.setAttribute("title",description);
		td1.style.width = "95px";
		td1.appendChild(A);
		
		U = document.createElement("input");
		U.setAttribute("type","hidden");
		U.setAttribute("id","headerRuleDesc"+clmnCount);
		U.setAttribute("name",'headerRuleDesc');
		td1.appendChild(U);
		
		td2.style.width = "120px";
			
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			cellId2 = cellId+clmnCount;
		}
		
		B = document.createElement("input");
		B.setAttribute("type","text");
		B.setAttribute("id",cellId2);
		B.setAttribute("name",$.trim(cellName));
		B.className="inputbox60";
		B.setAttribute("value","");
		B.setAttribute("alt","");
		B.setAttribute("title","");
		td3.style.width = "95px";
		td3.appendChild(B);
		
		V = document.createElement("input");
		V.setAttribute("type","hidden");
		V.setAttribute("id","headerRuleDesc"+clmnCount);
		V.setAttribute("name",'headerRuleDesc');
		td3.appendChild(V);
		
		td4.style.width = "120px";
			
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			cellId3 = cellId+clmnCount;
		}
		
		C = document.createElement("input");
		C.setAttribute("type","text");
		C.setAttribute("id",cellId3);
		C.setAttribute("name",$.trim(cellName));
		C.className="inputbox60";
		C.setAttribute("value","");
		C.setAttribute("alt","");
		C.setAttribute("title","");
		td5.style.width = "95px";
		td5.appendChild(C);
		
		W = document.createElement("input");
		W.setAttribute("type","hidden");
		W.setAttribute("id","headerRuleDesc"+clmnCount);
		W.setAttribute("name",'headerRuleDesc');
		td5.appendChild(W);
		
		td6.style.width = "120px";
		
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			cellId4 = cellId+clmnCount;
		}
	
		D = document.createElement("input");
		D.setAttribute("type","text");
		D.setAttribute("id",cellId4);
		D.setAttribute("name",$.trim(cellName));
		D.className="inputbox60";
		D.setAttribute("value","");
		D.setAttribute("alt","");
		D.setAttribute("title","");
		td7.style.width = "95px";
		td7.appendChild(D);
		
		X = document.createElement("input");
		X.setAttribute("type","hidden");
		X.setAttribute("id","headerRuleDesc"+clmnCount);
		X.setAttribute("name",'headerRuleDesc');
		td7.appendChild(X);
		
		td8.style.width = "120px";
		
		
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			cellId5 = cellId+clmnCount;
		}
	
		E = document.createElement("input");
		E.setAttribute("type","text");
		E.setAttribute("id",cellId5);
		E.setAttribute("name",$.trim(cellName));
		E.className="inputbox60";
		E.setAttribute("value","");
		E.setAttribute("alt","");
		E.setAttribute("title","");
		td9.style.width = "95px";
		td9.appendChild(E);
		
		Y = document.createElement("input");
		Y.setAttribute("type","hidden");
		Y.setAttribute("id","headerRuleDesc"+clmnCount);
		Y.setAttribute("name",'headerRuleDesc');
		td9.appendChild(Y);
		
		td10.style.width = "120px";
		
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			cellId6 = cellId+clmnCount;
		}
	
		F = document.createElement("input");
		F.setAttribute("type","text");
		F.setAttribute("id",cellId6);
		F.setAttribute("name",$.trim(cellName));
		F.className="inputbox60";
		F.setAttribute("value","");
		F.setAttribute("alt","");
		F.setAttribute("title","");
		td11.style.width = "95px";
		td11.appendChild(F);
		
		Z = document.createElement("input");
		Z.setAttribute("type","hidden");
		Z.setAttribute("id","headerRuleDesc"+clmnCount);
		Z.setAttribute("name",'headerRuleDesc');
		td11.appendChild(Z);
		
		td12.style.width = "60px";
		
			
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		row.appendChild(td6);
		row.appendChild(td7);
		row.appendChild(td8);	
		row.appendChild(td9);
		row.appendChild(td10);
		row.appendChild(td11);
		row.appendChild(td12);
		
		tbody.appendChild(row);	
		
		autoCompleteForDynamicHeaderRule1(cellId1);
		autoCompleteForDynamicHeaderRule2(cellId2);
		autoCompleteForDynamicHeaderRule3(cellId3);
		autoCompleteForDynamicHeaderRule4(cellId4);
		autoCompleteForDynamicHeaderRule5(cellId5);
		autoCompleteForDynamicHeaderRule6(cellId6);
		
	rowCount = $('#headerRuletable > tbody > tr').length;	
	return true;
	}
function autoCompleteForDynamicHeaderRule1(cellId){
	autoCompleteForDynamicHeaderRule(cellId);	
}
function autoCompleteForDynamicHeaderRule2(cellId){
	autoCompleteForDynamicHeaderRule(cellId);	
}
function autoCompleteForDynamicHeaderRule3(cellId){
	autoCompleteForDynamicHeaderRule(cellId);	
}
function autoCompleteForDynamicHeaderRule4(cellId){
	autoCompleteForDynamicHeaderRule(cellId);	
}
function autoCompleteForDynamicHeaderRule5(cellId){
	autoCompleteForDynamicHeaderRule(cellId);	
}
function autoCompleteForDynamicHeaderRule6(cellId){
	autoCompleteForDynamicHeaderRule(cellId);	
}

var list = new Array();
function autoCompleteForDynamicHeaderRule(cellId){
	
	$('#'+cellId).blur(function() {
			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
			
	});
	$('#'+cellId).autocomplete({ 
			select: function(event, ui) { $('#'+cellId).attr('alt',ui.item.id); $('#'+cellId).attr('title',ui.item.id);},
			source: function(request, response) {
						$.ajax({
							url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
							dataType: "json",
							type:"POST",
							data: "key="+escape(request.term) + "&name=RULEID",
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
							displayToolTipForSelectedItem(text,list,cellId);
						}
					}
									
							
				});
}

function displayToolTipForSelectedItem(text,list,cellId){



		var valid = false;
		var desc = "";
		text = $.trim(text);
			$.each(list , function(index, rows){
				var val = rows.value;
				if(val==text.toUpperCase()){
					valid = true;
					desc = rows.id;
				}
				}
			);
			if(text != null && text.length > 0){
				if(valid){

				
					 $('#'+cellId).attr('alt', desc);
			          $('#'+cellId).attr('title', desc);
					return true;
				}else{

					 $('#'+cellId).attr('alt', "In Valid Rule");
			          $('#'+cellId).attr('title', "In Valid Rule");
					return false;
				}
				
			}
}

var list = new Array();
$(document).ready(function(){
	$("#ruleId0").autocomplete({ 
		select: function(event, ui) { $("#ruleId0").attr('alt',ui.item.id); $("#ruleId0").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId0");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId1").autocomplete({ 
		select: function(event, ui) { $("#ruleId1").attr('alt',ui.item.id); $("#ruleId1").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId1");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId2").autocomplete({ 
		select: function(event, ui) { $("#ruleId2").attr('alt',ui.item.id); $("#ruleId2").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId2");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId3").autocomplete({ 
		select: function(event, ui) { $("#ruleId3").attr('alt',ui.item.id); $("#ruleId3").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId3");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId4").autocomplete({ 
		select: function(event, ui) { $("#ruleId4").attr('alt',ui.item.id); $("#ruleId4").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId4");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId5").autocomplete({ 
		select: function(event, ui) { $("#ruleId5").attr('alt',ui.item.id); $("#ruleId5").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId5");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId6").autocomplete({ 
		select: function(event, ui) { $("#ruleId6").attr('alt',ui.item.id); $("#ruleId6").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId6");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId7").autocomplete({ 
		select: function(event, ui) { $("#ruleId7").attr('alt',ui.item.id); $("#ruleId7").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId7");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId8").autocomplete({ 
		select: function(event, ui) { $("#ruleId8").attr('alt',ui.item.id); $("#ruleId8").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId8");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId9").autocomplete({ 
		select: function(event, ui) { $("#ruleId9").attr('alt',ui.item.id); $("#ruleId9").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId9");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId10").autocomplete({ 
		select: function(event, ui) { $("#ruleId10").attr('alt',ui.item.id); $("#ruleId10").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId10");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId11").autocomplete({ 
		select: function(event, ui) { $("#ruleId11").attr('alt',ui.item.id); $("#ruleId11").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId11");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId12").autocomplete({ 
		select: function(event, ui) { $("#ruleId12").attr('alt',ui.item.id); $("#ruleId12").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId12");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId13").autocomplete({ 
		select: function(event, ui) { $("#ruleId13").attr('alt',ui.item.id); $("#ruleId13").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId13");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId14").autocomplete({ 
		select: function(event, ui) { $("#ruleId14").attr('alt',ui.item.id); $("#ruleId14").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId14");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId15").autocomplete({ 
		select: function(event, ui) { $("#ruleId15").attr('alt',ui.item.id); $("#ruleId15").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId15");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId16").autocomplete({ 
		select: function(event, ui) { $("#ruleId16").attr('alt',ui.item.id); $("#ruleId16").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId16");
			}
		}
						
				
	})
  }); 

var list = new Array();
$(document).ready(function(){
	$("#ruleId17").autocomplete({ 
		select: function(event, ui) { $("#ruleId17").attr('alt',ui.item.id); $("#ruleId17").attr('title',ui.item.id);},
		source: function(request, response) {
					$.ajax({
						url: "../referencedata/fetchHeaderRuleForAutoComplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=RULEID",
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
				displayToolTipForSelectedItem(text,list,"ruleId17");
			}
		}
						
				
	})
  }); 
/**********************EB03- Header Rule Association April2012 - End **************/

/**************SSCR 14181 April 2012 Release, Header Rule and Data Type Pop up****************************/

function popupDivReferanceData(referanceDataName){

	$.ajax({
			url:"../referencedata/populateReferanceDataPopUp.ajax",
			dataType:"html",
			type:"POST",
			data: "referanceDataName="+referanceDataName,
			success: function(data){
					$("#referanceDataPopUpDiv").html(data);
					$("#referanceDataPopUpDiv").dialog({
				            height:'350',
							minWidth:600,
							position:'center',
							resizable : 'false',
							zIndex: 9999,
							title:$("#referanceDataName").val(),
				            modal: true,
				            open : function() {
				            	var newTitle = $("#referanceDataName").val();
								$('#referanceDataPopUpDiv').dialog( "option" , "title" ,newTitle);
	        				}
				    });
					checkReferenceData(referanceDataName);			
			} 					
	});	
}

function searchReferenceDataPopUp(dis){
	if(dis.keyCode=='13'){

	var searchText = $("#searchText").val();
	var referanceDataName =$("#referanceDataName").val();
	url = "../referencedata/populateReferanceDataPopUp.ajax"
		$.ajax({
				url:url,
				type:"POST",
				dataType:"html",
				data: "referanceDataName="+referanceDataName+"&searchText="+searchText,
				success: function(data){
						$("#referanceDataPopUpDiv").html(data);
						checkReferenceData(referanceDataName);	
				} 					
		});
		$("#searchText").value = searchText;
	}
}

function checkReferenceData(referanceDataName){
	var selectedData = "";
	if(referanceDataName == "RULEID"){
		selectedData = getRefDataValuesInTextBox("ruleId","VALUE");
	}	
	if(referanceDataName == "DATATYPE"){
		selectedData = getRefDataValuesInTextBox("dataTypeId","VALUE");
	}	
	//Format of ref Data value is value~description
	var referanceDataValueArray = new Array();
	var splittedRefDataValueArray = splitReferanceDataValue();
	var refDataLength = document.getElementsByName('referanceData').length;
	if(refDataLength > 0){
		for(var i=0;i<refDataLength;i++) {
			referanceDataValueArray[i] = splittedRefDataValueArray[i];
	}
	}else{
		referanceDataValueArray[0] = splittedRefDataValueArray[0];
	}
	
	var selectedCodeLength = selectedData.length;
	for(var i = 0;i<selectedCodeLength;i++){
		var index = $.inArray(selectedData[i],referanceDataValueArray);
		if(index > -1){
			document.getElementsByName('referanceData')[index].checked=true;
		}
	}
}
/**The function will return all the values in the textbox.
*/
function getRefDataValuesInTextBox(referanceDataName, arrayToBeReturned){ // The selected value in textbox.
	//get all values from the textbox.
	var selectedCodeValue = new Array();
	var selectedCodeDesc = new Array();

	var table = "";
	if(referanceDataName == "ruleId"){
		table = document.getElementById('headerRuletable');
	}
	if(referanceDataName == "dataTypeId"){
		table = document.getElementById('dataTypeTable');	
	}

	var tableLength = table.rows.length;

	if(referanceDataName == "ruleId"){
		tableLength = tableLength*6;
	}
	else {
		tableLength = tableLength*3;
	}
	
	for(var i=0,j=0;i<tableLength;i++) {
		var val = $('#'+ referanceDataName +i).val();
		val =$.trim(val);
		if(val.length > 0){
			selectedCodeValue[j] = val;
			if (referanceDataName == "ruleId") {
				selectedCodeDesc[j] = $('#'+referanceDataName+i).attr('alt');
			}
			else {
				selectedCodeDesc[j] = $('#'+referanceDataName+'Label'+i).text();
			}
			j++;
		}
	}//for
	
	if(arrayToBeReturned == "VALUE"){
		return selectedCodeValue;
	}else{
		return selectedCodeDesc;
	}
	
}
/**Format of ref data value is value~description.This function will return the values of all the 
ref data currently loaded in the popup.
*/
function splitReferanceDataValue(){
 	var referanceDataValue = new Array();
 	var referanceData = document.getElementsByName('referanceData').length;
	if(referanceData > 0){
		for(var i=0;i<referanceData;i++) {
			var value = document.getElementsByName('referanceData')[i].value;
			value = value.split("~");
			referanceDataValue[i] = value[0];
	}
	}else{
		var value = $('#referanceData').val();
		if(value!=null && value.length > 0){
			value = value.split("~");
			referanceDataValue[0] = value[0];
		}
	}
	return referanceDataValue;
}

function selectedReferanceDataValues(){
	var selectedCode = "";
	
	if($('#referanceDataName').val() == "RULEID"){
		setReferanceDataValue('ruleId');
	}
	if($('#referanceDataName').val() == "DATATYPE"){
		setReferanceDataValue('dataTypeId');
	}
	$('#referanceDataPopUpDiv').dialog( "close" );

	return true;
	
}
function setReferanceDataValue(referanceDataId){
	if($('#referenceDataExists').val()=="false"){
		return false;
	}
	//Remove the unchecked value and get the values in txt box.
	var valuesInTxBx = new Array();
	valuesInTxBx = removeUncheckedRefDataFromTxbBxArray(referanceDataId,"VALUE");
	var copyOfValuesInTxBx = new Array();
	//copyOfValuesInTxBx = valuesInTxBx;
	jQuery.extend(true, copyOfValuesInTxBx, valuesInTxBx);
	//Remove the desc only for unchecked value and get the description or label for the value in txt boxes.
	var descForValuesInTxBx = new Array();
	descForValuesInTxBx = removeUncheckedRefDataFromTxbBxArray(referanceDataId,"DESC");
	//Get the selected hippa code values from popup
	var refDataValueArray = new Array();
	refDataValueArray = arrayOfSelectedReferanceData("VALUE");
	//Get the selected hippa code desc from popup		
	var refDataDescArray =  new Array();
	refDataDescArray = arrayOfSelectedReferanceData("DESC");
	//add selected values to  values in textbox
	var length =refDataValueArray.length;
	if(length > 0){
		for(var i=0;i<length;i++) {
				var val =refDataValueArray[i];
				var index = $.inArray(val,valuesInTxBx);
				if(index < 0){ //hava to add this to listOfValuesFromTxtBx
					var j = valuesInTxBx.length;
					valuesInTxBx[j] = val;
				}else{//contains value & its desc that are not in popup
					var valueToBeRemoved = val;
					var descIndex = $.inArray(val,copyOfValuesInTxBx);
					copyOfValuesInTxBx = $.grep(copyOfValuesInTxBx, function(val) { return val != valueToBeRemoved; });
					var descToBeRemoved = descForValuesInTxBx[descIndex];
					descForValuesInTxBx = $.grep(descForValuesInTxBx, function(val) { return val != descToBeRemoved; });
				}
		}
	}else{
			var val = refDataValueArray[0];
			var index = $.inArray(val,valuesInTxBx);
			if(index < 0){ //have to add this to listOfValuesFromTxtBx
				var j = valuesInTxBx.length;
				valuesInTxBx[j] = val;
			}else{
				var valueToBeRemoved = val;
				var descIndex = $.inArray(val,copyOfValuesInTxBx);
				copyOfValuesInTxBx = $.grep(copyOfValuesInTxBx, function(val) { return val != valueToBeRemoved; });
					
				var descToBeRemoved = descForValuesInTxBx[descIndex];
				descForValuesInTxBx = $.grep(descForValuesInTxBx, function(val) { return val != descToBeRemoved; });
			}
	}
	//remove all the blank or empty or undefined values
	$.each(valuesInTxBx , function(i, value){
		var valueToRemove = null;
		valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != valueToRemove; });
					
	});
	//now valuesInTxBx contains the final list to be populated.
	length =valuesInTxBx.length;
	if(length > 0){		
		if(referanceDataId == "ruleId"){	
			headerRulePopulationOnReferanceDataPage(referanceDataId,length,valuesInTxBx,refDataValueArray,refDataDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
		}	
		if(referanceDataId == "dataTypeId"){	
			dataTypePopulationOnReferanceDataPage(referanceDataId,length,valuesInTxBx,refDataValueArray,refDataDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
		}
	}else if(length == 0){
		$("[id^='"+referanceDataId+"']").each(function () {
			var id = $(this).attr('id');
			var index = id.indexOf("Label");
			if(index > -1){
				$(this).text('');
			}else{
				$(this).val('');				
			}
	    });//end of each	
	}
}
//This function will populate the values from the pop up to the eb03 textboxes
function dataTypePopulationOnReferanceDataPage(referanceDataId,length,valuesInTxBx,refDataValueArray,refDataDescArray,copyOfValuesInTxBx,descForValuesInTxBx){

	var table = document.getElementById('dataTypeTable');
	var rowCount = table.rows.length;
	var j = 0;
	var clmnLength ;
	var clmnCnt;
	
	for(var i=0;i<length;) {
		var text = valuesInTxBx[i];
			clmnLength = $('#dataTypeTable > tbody > tr > td').length;
			clmnCnt = clmnLength/3;
			if(j >= clmnCnt){
				var index = $.inArray(text,refDataValueArray);
				var desc = 	refDataDescArray[index];
				insertDataType('dataTypeTable','dataTypeId'+j,'dataTypeVal',text,true,'dataTypeIdLabel'+j,desc,'');							
				j++;	
				i++;
			}else{
				$('#'+ referanceDataId +j).val(text);
				var index = $.inArray(text,refDataValueArray);
				var desc = '';
				if(index < 0){
					index = $.inArray(text,copyOfValuesInTxBx);
					if(index > -1){
						desc = 	descForValuesInTxBx[index];
					}
				}else{
					desc = 	refDataDescArray[index];
				}
				$('#'+ referanceDataId+'Label'+j).text(desc);
				
				if($.trim(desc) == $.trim(invalidHippaCodeValue)){
					$('#'+ referanceDataId+'Label'+j).addClass('invalid_hippacode_value');
				}else{
					$('#'+ referanceDataId+'Label'+j).removeClass('invalid_hippacode_value');
				}				
				j++;
				i++;				
			}

	}//end of for
	//after populating all the values to the textboxes , remove the txtboxes that comes after the newly populated list
	if(clmnCnt >=j){
		for(var k = j;k <clmnCnt;k++){
			$('#'+ referanceDataId +k).val('');
			$('#'+referanceDataId+'Label'+k).text('');			
			$('#'+referanceDataId+'Label'+k).removeClass('invalid_hippacode_value');
		}
	}
	
}
function arrayOfSelectedReferanceData(arrayToBeReturned){
	var selectedRefDataValueArray = new Array();
	var selectedRefDataDescriptionArray = new Array();

	var length =document.forms['referanceDataSelectedValues'].referanceData.length;

	if(length > 0){
		for(var i=0,j=0;i<length;i++) {
			if(document.forms['referanceDataSelectedValues'].referanceData[i].checked){
				var val =document.forms['referanceDataSelectedValues'].referanceData[i].value;
				val = val.split("~");
				selectedRefDataValueArray[j] = val[0];
				selectedRefDataDescriptionArray[j] = val[1];
				j++;
			}
		}
	}else{
		if(document.forms['referanceDataSelectedValues'].referanceData.checked){
			var value =$('#referanceData').val();
			value = value.split("~");
			selectedRefDataValueArray[0] = value[0];
			selectedRefDataDescriptionArray[0] = value[1];
		}
	}

	if(arrayToBeReturned == "VALUE"){
		return selectedRefDataValueArray;
	}else
		return selectedRefDataDescriptionArray;
}

//This function will populate the values from the pop up to the header rule textboxes
function headerRulePopulationOnReferanceDataPage(referanceDataId,length,valuesInTxBx,refDataValueArray,refDataDescArray,copyOfValuesInTxBx,descForValuesInTxBx){

	var table = document.getElementById('headerRuletable');
	var rowCount = table.rows.length;
	var j = 0;
	var clmnLength ;
	var clmnCnt;
	for(var i=0;i<length;) {
		var text = valuesInTxBx[i];
		clmnLength = $('#headerRuletable > tbody > tr > td').length;
		clmnCnt = clmnLength/2;
		if(j >= clmnCnt){
			var index = $.inArray(text,refDataValueArray);
			var desc = 	refDataDescArray[index];
			insertHeaderRule('headerRuletable','ruleId'+j,'headerRuleVal',text,true,desc);	
			j++;	
			i++;
		}else{
			$('#'+ referanceDataId +j).val(text);
			var index = $.inArray(text,refDataValueArray);
			var desc = '';
			if(index < 0){
				index = $.inArray(text,copyOfValuesInTxBx);
				if(index > -1){
					desc = 	descForValuesInTxBx[index];
				}
			}else{
				desc = 	refDataDescArray[index];
			}

			$('#'+ referanceDataId +j).attr('alt',desc);
			$('#'+ referanceDataId +j).attr('title',desc);
			
			j++;
			i++;			
		}
	}//end of for
	//after populating all the values to the textboxes , remove the txtboxes that comes after the newly populated list
	if(clmnCnt >=j){
		for(var k = j;k <clmnCnt;k++){
			$('#'+ referanceDataId +k).val('');
			$('#'+ referanceDataId +k).removeAttr('alt');
			$('#'+ referanceDataId +k).removeAttr('title');
		}
	}
	
}

function removeUncheckedRefDataFromTxbBxArray(referanceDataId,arrayToBeReturned){
	var splittedRefDataValueArray = new Array();
	splittedRefDataValueArray = splitReferanceDataValue();//contains all the hippacode values in the pop up
	
	var valuesInTxBx = new Array();
	var descInTxBx = new Array();
	valuesInTxBx = getRefDataValuesInTextBox(referanceDataId,"VALUE");//contains the values in txtboxes.
	descInTxBx = getRefDataValuesInTextBox(referanceDataId,"DESC");//contains the labels for txtboxes.	

	//check for all values in textbox ,whethr the value is unchecked ,if it is in the pop up.
	$.each(valuesInTxBx , function(i, value){
			//var index = $.inArray(value,splittedRefDataValueArray);
			var index = $.inArray(valuesInTxBx[i],splittedRefDataValueArray);
			if(index > -1){//the value is in popup
				var referanceDataLength = document.getElementsByName('referanceData').length;
				if(referanceDataLength > 0){
					if(!document.getElementsByName('referanceData')[index].checked){					
					   //remove from valuesInTxBx
						valuesInTxBx[i] = "ToRemove";
						descInTxBx[i] = "ToRemove";							
						//remove the corresponding label
						if (referanceDataId == "ruleId") {
							///$('#'+referanceDataId+i).attr('alt',' ');
							//$('#'+referanceDataId+i).attr('title',' ');
						}
						else {
							$('#'+referanceDataId+'Label'+i).text('');
						}
					}
				}else{
					if(!document.getElementsByName('referanceData').checked){
						//remove from valuesInTxBx
						valuesInTxBx[i] = "ToRemove";
						descInTxBx[i] = "ToRemove";
						var descToRemove = descInTxBx[i];
						
						//remove the corresponding label
						if (referanceDataId == "ruleId") {
							//$('#'+referanceDataId+i).attr('alt',' ');
							//$('#'+referanceDataId+i).attr('title',' ');
						}
						else {
							$('#'+referanceDataId+'Label'+i).text('');
						}
					}
				}
			}
		}
	);
	valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != "ToRemove"; });
	descInTxBx = $.grep(descInTxBx, function(val) { return val != "ToRemove"; });
	if(arrayToBeReturned == "VALUE"){
		return valuesInTxBx;
	}else{
		return descInTxBx;
	}
}

/**BXNI November Release Reference Data STC-Eb11 mapping Scripts START*/

////////////dynamically inserting new rows

function insertServiceTypeMappingsRow(tableID,isComingFromAddButton,cellIdForServiceTypeCode,cellNameServiceTypeCode,serviceTypeCodeValue,labelIdSrviceTypeCode,serviceTypeCodeDescription,serviceTypeCodeDescId,
										cellIdForEB11,cellNameEB11,EB11Value,labelIdEB11,EB11Description,EB11DescId,
										cellIdForPlaceOfService,cellNamePlaceOfService,placeOfServiceValue,labelIdPlaceOfService,placeOfServiceDescription,placeOfServiceDescId){
	if($('#'+cellIdForServiceTypeCode).length > 0){
         $('#'+cellIdForServiceTypeCode).val(serviceTypeCodeValue);
         $('#'+cellIdForEB11).val(EB11Value);
         $('#'+cellIdForPlaceOfService).val(placeOfServiceValue);
         
   }else{      		
	   addRowForServiceTypeMappings(tableID,isComingFromAddButton,"serviceTypeCodeId",cellNameServiceTypeCode,serviceTypeCodeValue,labelIdSrviceTypeCode,
			   									"eb11Id",cellNameEB11,EB11Value,labelIdEB11,
			   									"placeOfServiceId",cellNamePlaceOfService,placeOfServiceValue,labelIdPlaceOfService);
   }
	
     $('#'+labelIdSrviceTypeCode).text(serviceTypeCodeDescription);
     $('#'+labelIdEB11).text(EB11Description);
     $('#'+labelIdPlaceOfService).text(placeOfServiceDescription);
}

function addRowForServiceTypeMappings(tableID,isComingFromAddButton,cellIdForServiceTypeCode,cellNameServiceTypeCode,serviceTypeCodeValue,labelIdSrviceTypeCode,
												cellIdForEB11,cellNameEB11,EB11Value,labelIdEB11,
												cellIdForPlaceOfService,cellNamePlaceOfService,placeOfServiceValue,labelIdPlaceOfService){
	var table = document.getElementById(tableID);
	var tbody = document.getElementById("serviceTypeMappingsTbody");
	var trClass = "";
	var cellId1;
	var labelId1;
	var cellId2;
	var labelId2;
	var cellId3;
	var labelId3;
	var clmnCount;
	var rowCount = $('#serviceTypeMappingsTable > tbody > tr').length;
	var clmnLength = $('#serviceTypeMappingsTable > tbody > tr > td').length;
	
	if (rowCount % 2 == 0) {
		trClass = "alternate";
	} else {
		trClass = "white-bg";
	}
	var row = document.createElement("TR");
	row.className=trClass;
	var td1 = document.createElement("TD");
	var td2 = document.createElement("TD");
	var td3 = document.createElement("TD");
	var td4 = document.createElement("TD");
	var td5 = document.createElement("TD");
	var td6 = document.createElement("TD");
	var td7 = document.createElement("TD");
	var td8 = document.createElement("TD");
	var td9 = document.createElement("TD");
	
	if(isComingFromAddButton){
		clmnCount = clmnLength/9;
		labelId1 = "serviceTypeCodeIdLabel"+clmnCount;
		cellId1 = cellIdForServiceTypeCode+clmnCount;
	}
	// else condition not handled
	A = document.createElement("input");
	A.setAttribute("type","text");
	A.setAttribute("id",cellId1);
	A.setAttribute("name",$.trim(cellNameServiceTypeCode));
	A.className="inputbox60";
	A.setAttribute("value",serviceTypeCodeValue);
	td1.style.width = "95px";
	td1.appendChild(A);
	
	B = document.createElement("input");
	B.setAttribute("type","hidden");
	B.setAttribute("id","serviceTypeCodeDesc"+clmnCount);
	B.setAttribute("name",'serviceTypeCodeDesc');
	td1.appendChild(B);
	
	L = document.createElement("label");
	L.setAttribute("id",labelId1);
	L.setAttribute("for",cellId1);
	L.className="UnmappedVariables";
	td2.style.width = "140px";
	td2.appendChild(L);
		
	if(isComingFromAddButton){
		labelId2 = "eb11IdLabel"+clmnCount;
		cellId2 = cellIdForEB11+clmnCount;
	}
	E = document.createElement("input");
	E.setAttribute("type","text");
	E.setAttribute("id",cellId2);
	E.setAttribute("name",$.trim(cellNameEB11));
	E.className="inputbox60";
	E.setAttribute("value",EB11Value);
	td4.style.width = "95px";
	td4.appendChild(E);
	
	F = document.createElement("input");
	F.setAttribute("type","hidden");
	F.setAttribute("id","eb11Desc"+clmnCount);
	F.setAttribute("name",'eb11Desc');
	td4.appendChild(F);
	
	M = document.createElement("label");
	M.setAttribute("id",labelId2);
	M.setAttribute("for",cellId2);
	M.className="UnmappedVariables";
	td5.style.width = "140px";
	td5.appendChild(M);
		
	if(isComingFromAddButton){
		labelId3 = "placeOfServiceIdLabel"+clmnCount;
		cellId3 = cellIdForPlaceOfService+clmnCount;
	}

	I = document.createElement("input");
	I.setAttribute("type","text");
	I.setAttribute("id",cellId3);
	I.setAttribute("name",$.trim(cellNamePlaceOfService));
	I.className="inputbox60";
	I.setAttribute("value",placeOfServiceValue);
	td7.style.width = "95px";
	td7.appendChild(I);
	
	J = document.createElement("input");
	J.setAttribute("type","hidden");
	J.setAttribute("id","placeOfServiceDesc"+clmnCount);
	J.setAttribute("name",'placeOfServiceDesc');
	td7.appendChild(J);
	
	N = document.createElement("label");
	N.setAttribute("id",labelId3);
	N.setAttribute("for",cellId3);
	N.className="UnmappedVariables";
	td8.style.width = "140px";
	td8.appendChild(N);
		
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.appendChild(td5);
	row.appendChild(td6);
	row.appendChild(td7);
	row.appendChild(td8);	
	row.appendChild(td9);
	
	tbody.appendChild(row);	
	
	  autoCompleteForDynamicServiceType(cellId1, labelId1);
	  autoCompleteForDynamicEB11(cellId2, labelId2);
	  autoCompleteForDynamicPlaceOfService(cellId3, labelId3);
rowCount = $('#serviceTypeMappingsTable > tbody > tr').length;	
//if(rowCount>=33){
	//$('#eb03AddButton').hide();
//}
return true;
}

/*****************************Autopopulate  EB03,EB11,POS Columns START******************************************************/
$(document).ready(function(){
	$('#serviceTypeCodeId0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#serviceTypeCodeIdLabel0").text('');
	   }
	   $('#serviceTypeCodeId0').val($('#serviceTypeCodeId0').val().toUpperCase());
	    $('#serviceTypeCodeIdLabel0').val($('#serviceTypeCodeIdLabel0').val().toUpperCase());
	});  
	$("#serviceTypeCodeId0").autocomplete({ 
		select: function(event, ui) { $("#serviceTypeCodeIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"serviceTypeCodeIdLabel0",invalidHippaCodeValue);
			}
		}
	})
  }); 


$(document).ready(function(){
	$('#serviceTypeCodeId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#serviceTypeCodeIdLabel1").text('');
	   }
	   $('#serviceTypeCodeId1').val($('#serviceTypeCodeId1').val().toUpperCase());
	    $('#serviceTypeCodeIdLabel1').val($('#serviceTypeCodeIdLabel1').val().toUpperCase());
	});  
	$("#serviceTypeCodeId1").autocomplete({ 
		select: function(event, ui) { $("#serviceTypeCodeIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"serviceTypeCodeIdLabel1",invalidHippaCodeValue);
			}
		}
	})
  }); 


$(document).ready(function(){
	$('#serviceTypeCodeId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#serviceTypeCodeIdLabel2").text('');
	   }
	   $('#serviceTypeCodeId2').val($('#serviceTypeCodeId2').val().toUpperCase());
	    $('#serviceTypeCodeIdLabel2').val($('#serviceTypeCodeIdLabel2').val().toUpperCase());
	});  
	$("#serviceTypeCodeId2").autocomplete({ 
		select: function(event, ui) { $("#serviceTypeCodeIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"serviceTypeCodeIdLabel2",invalidHippaCodeValue);
			}
		}
	})
  }); 


$(document).ready(function(){
	$('#eb11Id0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#eb11IdLabel0").text('');
	   }
	   $('#eb11Id0').val($('#eb11Id0').val().toUpperCase());
	    $('#eb11IdLabel0').val($('#eb11IdLabel0').val().toUpperCase());
	});  
	$("#eb11Id0").autocomplete({ 
		select: function(event, ui) { $("#eb11IdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB11",
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
				displayLabelForSelectedItem(text,list,"eb11IdLabel0",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#eb11Id1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#eb11IdLabel1").text('');
	   }
	   $('#eb11Id1').val($('#eb11Id1').val().toUpperCase());
	    $('#eb11IdLabel1').val($('#eb11IdLabel1').val().toUpperCase());
	});  
	$("#eb11Id1").autocomplete({ 
		select: function(event, ui) { $("#eb11IdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB11",
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
				displayLabelForSelectedItem(text,list,"eb11IdLabel1",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#eb11Id2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#eb11IdLabel2").text('');
	   }
	   $('#eb11Id2').val($('#eb11Id2').val().toUpperCase());
	    $('#eb11IdLabel2').val($('#eb11IdLabel2').val().toUpperCase());
	});  
	$("#eb11Id2").autocomplete({ 
		select: function(event, ui) { $("#eb11IdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB11",
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
				displayLabelForSelectedItem(text,list,"eb11IdLabel2",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#placeOfServiceId0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#placeOfServiceIdLabel0").text('');
	   }
	   $('#placeOfServiceId0').val($('#placeOfServiceId0').val().toUpperCase());
	    $('#placeOfServiceIdLabel0').val($('#placeOfServiceIdLabel0').val().toUpperCase());
	});  
	$("#placeOfServiceId0").autocomplete({ 
		select: function(event, ui) { $("#placeOfServiceIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=III02",
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
				displayLabelForSelectedItem(text,list,"placeOfServiceIdLabel0",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#placeOfServiceId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#placeOfServiceIdLabel1").text('');
	   }
	   $('#placeOfServiceId1').val($('#placeOfServiceId1').val().toUpperCase());
	    $('#placeOfServiceIdLabel1').val($('#placeOfServiceIdLabel1').val().toUpperCase());
	});  
	$("#placeOfServiceId1").autocomplete({ 
		select: function(event, ui) { $("#placeOfServiceIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=III02",
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
				displayLabelForSelectedItem(text,list,"placeOfServiceIdLabel1",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#placeOfServiceId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#placeOfServiceIdLabel2").text('');
	   }
	   $('#placeOfServiceId2').val($('#placeOfServiceId2').val().toUpperCase());
	    $('#placeOfServiceIdLabel2').val($('#placeOfServiceIdLabel2').val().toUpperCase());
	});  
	$("#placeOfServiceId2").autocomplete({ 
		select: function(event, ui) { $("#placeOfServiceIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=III02",
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
				displayLabelForSelectedItem(text,list,"placeOfServiceIdLabel2",invalidHippaCodeValue);
			}
		}
	})
  });

//Auto populate dynamic EB03 column
function autoCompleteForDynamicServiceType(cellId,labelId){
		$('#'+cellId).blur(function() {
   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
   				$('#'+labelId).text('');
   			}
   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
   			$('#'+labelId).val($('#'+labelId).val().toUpperCase());
		});
		
		$('#'+cellId).autocomplete({ 
			select: function(event, ui) { $('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocomplete.ajax",
							dataType: "json",
							data: "key="+request.term + "&name=EB03",
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
					displayLabelForSelectedItem(text,list,labelId,invalidHippaCodeValue);
				}
			}
		});
}
//Auto populate dynamic EB11 column
function autoCompleteForDynamicEB11(cellId,labelId){
		$('#'+cellId).blur(function() {
   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
   				$('#'+labelId).text('');
   			}
   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
   			$('#'+labelId).val($('#'+labelId).val().toUpperCase());
		});
		
		$('#'+cellId).autocomplete({ 
			select: function(event, ui) { $('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocomplete.ajax",
							dataType: "json",
							data: "key="+request.term + "&name=EB11",
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
					displayLabelForSelectedItem(text,list,labelId,invalidHippaCodeValue);
				}
			}
		});
 }
//Auto populate dynamic EB11 column
function autoCompleteForDynamicPlaceOfService(cellId,labelId){
		$('#'+cellId).blur(function() {
   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
   				$('#'+labelId).text('');
   			}
   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
   			$('#'+labelId).val($('#'+labelId).val().toUpperCase());
		});
		
		$('#'+cellId).autocomplete({ 
			select: function(event, ui) { $('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocomplete.ajax",
							dataType: "json",
							data: "key="+request.term + "&name=III02",
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
					displayLabelForSelectedItem(text,list,labelId,invalidHippaCodeValue);
				}
			}
		});
 }



/*****************************Autopopulate  EB03,EB11,POS Columns in the PopOut ******************************************************/
$(document).ready(function(){
	$('#serviceTypeCodePopOutId0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#serviceTypeCodePopOutIdLabel0").text('');
	   }
	   $('#serviceTypeCodePopOutId0').val($('#serviceTypeCodePopOutId0').val().toUpperCase());
	    $('#serviceTypeCodePopOutIdLabel0').val($('#serviceTypeCodePopOutIdLabel0').val().toUpperCase());
	});  
	$("#serviceTypeCodePopOutId0").autocomplete({ 
		select: function(event, ui) { $("#serviceTypeCodePopOutIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"serviceTypeCodePopOutIdLabel0",invalidHippaCodeValue);
			}
		}
	})
  }); 


$(document).ready(function(){
	$('#serviceTypeCodePopOutId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#serviceTypeCodePopOutIdLabel1").text('');
	   }
	   $('#serviceTypeCodePopOutId1').val($('#serviceTypeCodePopOutId1').val().toUpperCase());
	    $('#serviceTypeCodePopOutIdLabel1').val($('#serviceTypeCodePopOutIdLabel1').val().toUpperCase());
	});  
	$("#serviceTypeCodePopOutId1").autocomplete({ 
		select: function(event, ui) { $("#serviceTypeCodePopOutIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"serviceTypeCodePopOutIdLabel1",invalidHippaCodeValue);
			}
		}
	})
  }); 



$(document).ready(function(){
	$('#serviceTypeCodePopOutId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#serviceTypeCodePopOutIdLabel2").text('');
	   }
	   $('#serviceTypeCodePopOutId2').val($('#serviceTypeCodePopOutId2').val().toUpperCase());
	    $('#serviceTypeCodePopOutIdLabel2').val($('#serviceTypeCodePopOutIdLabel2').val().toUpperCase());
	});  
	$("#serviceTypeCodePopOutId2").autocomplete({ 
		select: function(event, ui) { $("#serviceTypeCodePopOutIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB03",
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
				displayLabelForSelectedItem(text,list,"serviceTypeCodePopOutIdLabel2",invalidHippaCodeValue);
			}
		}
	})
  }); 



$(document).ready(function(){
	$('#eb11PopOutId0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#eb11IdPopOutLabel0").text('');
	   }
	   $('#eb11PopOutId0').val($('#eb11PopOutId0').val().toUpperCase());
	    $('#eb11IdPopOutLabel0').val($('#eb11IdPopOutLabel0').val().toUpperCase());
	});  
	$("#eb11PopOutId0").autocomplete({ 
		select: function(event, ui) { $("#eb11IdPopOutLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB11",
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
				displayLabelForSelectedItem(text,list,"eb11IdPopOutLabel0",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#eb11PopOutId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#eb11IdPopOutLabel1").text('');
	   }
	   $('#eb11PopOutId1').val($('#eb11PopOutId1').val().toUpperCase());
	    $('#eb11IdPopOutLabel1').val($('#eb11IdPopOutLabel1').val().toUpperCase());
	});  
	$("#eb11PopOutId1").autocomplete({ 
		select: function(event, ui) { $("#eb11IdPopOutLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB11",
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
				displayLabelForSelectedItem(text,list,"eb11IdPopOutLabel1",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#eb11PopOutId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#eb11IdPopOutLabel2").text('');
	   }
	   $('#eb11PopOutId2').val($('#eb11PopOutId2').val().toUpperCase());
	    $('#eb11IdPopOutLabel2').val($('#eb11IdPopOutLabel2').val().toUpperCase());
	});  
	$("#eb11PopOutId2").autocomplete({ 
		select: function(event, ui) { $("#eb11IdPopOutLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=EB11",
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
				displayLabelForSelectedItem(text,list,"eb11IdPopOutLabel2",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#placeOfServicePopOutId0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#placeOfServicePopOutIdLabel0").text('');
	   }
	   $('#placeOfServicePopOutId0').val($('#placeOfServicePopOutId0').val().toUpperCase());
	    $('#placeOfServicePopOutIdLabel0').val($('#placeOfServicePopOutIdLabel0').val().toUpperCase());
	});  
	$("#placeOfServicePopOutId0").autocomplete({ 
		select: function(event, ui) { $("#placeOfServicePopOutIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=III02",
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
				displayLabelForSelectedItem(text,list,"placeOfServicePopOutIdLabel0",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#placeOfServicePopOutId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#placeOfServicePopOutIdLabel1").text('');
	   }
	   $('#placeOfServicePopOutId1').val($('#placeOfServicePopOutId1').val().toUpperCase());
	    $('#placeOfServicePopOutIdLabel1').val($('#placeOfServicePopOutIdLabel1').val().toUpperCase());
	});  
	$("#placeOfServicePopOutId1").autocomplete({ 
		select: function(event, ui) { $("#placeOfServicePopOutIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=III02",
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
				displayLabelForSelectedItem(text,list,"placeOfServicePopOutIdLabel1",invalidHippaCodeValue);
			}
		}
	})
  });

$(document).ready(function(){
	$('#placeOfServicePopOutId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#placeOfServicePopOutIdLabel2").text('');
	   }
	   $('#placeOfServicePopOutId2').val($('#placeOfServicePopOutId2').val().toUpperCase());
	    $('#placeOfServicePopOutIdLabel2').val($('#placeOfServicePopOutIdLabel2').val().toUpperCase());
	});  
	$("#placeOfServicePopOutId2").autocomplete({ 
		select: function(event, ui) { $("#placeOfServicePopOutIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term) + "&name=III02",
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
				displayLabelForSelectedItem(text,list,"placeOfServicePopOutIdLabel2",invalidHippaCodeValue);
			}
		}
	})
  });
/*****************************Autopopulate EB03,EB11,POS Columns START******************************************************/

function insertServiceTypeMappingsRowPopOut(tableID,isComingFromAddButton,cellIdForServiceTypeCode,cellNameServiceTypeCode,serviceTypeCodeValue,labelIdSrviceTypeCode,serviceTypeCodeDescription,serviceTypeCodeDescId,
		cellIdForEB11,cellNameEB11,EB11Value,labelIdEB11,EB11Description,EB11DescId,
		cellIdForPlaceOfService,cellNamePlaceOfService,placeOfServiceValue,labelIdPlaceOfService,placeOfServiceDescription,placeOfServiceDescId){
	if($('#'+cellIdForServiceTypeCode).length > 0){
		$('#'+cellIdForServiceTypeCode).val(serviceTypeCodeValue);
		$('#'+cellIdForEB11).val(EB11Value);
		$('#'+cellIdForPlaceOfService).val(placeOfServiceValue);

	}else{      		
		addRowForServiceTypeMappingsPopOut(tableID,isComingFromAddButton,"serviceTypeCodePopOutId",cellNameServiceTypeCode,serviceTypeCodeValue,labelIdSrviceTypeCode,
					"eb11PopOutId",cellNameEB11,EB11Value,labelIdEB11,
					"placeOfServicePopOutId",cellNamePlaceOfService,placeOfServiceValue,labelIdPlaceOfService);
	}
$('#'+labelIdSrviceTypeCode).text(serviceTypeCodeDescription);
$('#'+labelIdEB11).text(EB11Description);
$('#'+labelIdPlaceOfService).text(placeOfServiceDescription);

}

function addRowForServiceTypeMappingsPopOut(tableID,isComingFromAddButton,cellIdForServiceTypeCode,cellNameServiceTypeCode,serviceTypeCodeValue,labelIdSrviceTypeCode,
				cellIdForEB11,cellNameEB11,EB11Value,labelIdEB11,
				cellIdForPlaceOfService,cellNamePlaceOfService,placeOfServiceValue,labelIdPlaceOfService){
var table = document.getElementById(tableID);
var tbody = document.getElementById("serviceTypeMappingsTbodyPopOut");
var trClass = "";
var cellId1;
var labelId1;
var cellId2;
var labelId2;
var cellId3;
var labelId3;
var clmnCount;
var rowCount = $('#serviceTypeMappingsTablePopOut > tbody > tr').length;
var clmnLength = $('#serviceTypeMappingsTablePopOut > tbody > tr > td').length;

if (rowCount % 2 == 0) {
trClass = "alternate";
} else {
trClass = "white-bg";
}
var row = document.createElement("TR");
row.className=trClass;
var td1 = document.createElement("TD");
var td2 = document.createElement("TD");
var td3 = document.createElement("TD");
var td4 = document.createElement("TD");
var td5 = document.createElement("TD");
var td6 = document.createElement("TD");
var td7 = document.createElement("TD");
var td8 = document.createElement("TD");
var td9 = document.createElement("TD");

if(isComingFromAddButton){
clmnCount = clmnLength/9;
labelId1 = "serviceTypeCodePopOutIdLabel"+clmnCount;
cellId1 = cellIdForServiceTypeCode+clmnCount;
}
// else condition not handled
A = document.createElement("input");
A.setAttribute("type","text");
A.setAttribute("id",cellId1);
A.setAttribute("name",$.trim(cellNameServiceTypeCode));
A.className="inputbox60";
A.setAttribute("value",serviceTypeCodeValue);
td1.style.width = "95px";
td1.appendChild(A);

B = document.createElement("input");
B.setAttribute("type","hidden");
B.setAttribute("id","serviceTypeCodeDescPopOut"+clmnCount);
B.setAttribute("name",'serviceTypeCodeDescPopOut');
td1.appendChild(B);

L = document.createElement("label");
L.setAttribute("id",labelId1);
L.setAttribute("for",cellId1);
L.className="UnmappedVariables";
td2.style.width = "140px";
td2.appendChild(L);

if(isComingFromAddButton){
labelId2 = "eb11IdPopOutLabel"+clmnCount;
cellId2 = cellIdForEB11+clmnCount;
}
E = document.createElement("input");
E.setAttribute("type","text");
E.setAttribute("id",cellId2);
E.setAttribute("name",$.trim(cellNameEB11));
E.className="inputbox60";
E.setAttribute("value",EB11Value);
td4.style.width = "95px";
td4.appendChild(E);

F = document.createElement("input");
F.setAttribute("type","hidden");
F.setAttribute("id","eb11DescPopOut"+clmnCount);
F.setAttribute("name",'eb11DescPopOut');
td4.appendChild(F);

M = document.createElement("label");
M.setAttribute("id",labelId2);
M.setAttribute("for",cellId2);
M.className="UnmappedVariables";
td5.style.width = "140px";
td5.appendChild(M);

if(isComingFromAddButton){
labelId3 = "placeOfServicePopOutIdLabel"+clmnCount;
cellId3 = cellIdForPlaceOfService+clmnCount;
}

I = document.createElement("input");
I.setAttribute("type","text");
I.setAttribute("id",cellId3);
I.setAttribute("name",$.trim(cellNamePlaceOfService));
I.className="inputbox60";
I.setAttribute("value",placeOfServiceValue);
td7.style.width = "95px";
td7.appendChild(I);

J = document.createElement("input");
J.setAttribute("type","hidden");
J.setAttribute("id","placeOfServiceDescPopOut"+clmnCount);
J.setAttribute("name",'placeOfServiceDescPopOut');
td7.appendChild(J);

N = document.createElement("label");
N.setAttribute("id",labelId3);
N.setAttribute("for",cellId3);
N.className="UnmappedVariables";
td8.style.width = "140px";
td8.appendChild(N);

row.appendChild(td1);
row.appendChild(td2);
row.appendChild(td3);
row.appendChild(td4);
row.appendChild(td5);
row.appendChild(td6);
row.appendChild(td7);
row.appendChild(td8);	
row.appendChild(td9);

tbody.appendChild(row);	

autoCompleteForDynamicServiceType(cellId1, labelId1);
autoCompleteForDynamicEB11(cellId2, labelId2);
autoCompleteForDynamicPlaceOfService(cellId3, labelId3);
rowCount = $('#serviceTypeMappingsTablePopOut > tbody > tr').length;	
return true;
}

/**BXNI November Release Reference Data STC-Eb11 mapping Scripts END*/

//New script function to be added for addition of only 16 service type code as part of BXNI June 2013 Release

function insertRuleEB03(tableID,cellId,cellName,text,isComingFromAddButton,labelId,description,descId){
	   if($('#'+cellId).length > 0){
	         $('#'+cellId).val(text);
	   }else{      		
		   addRowForRuleEb03(tableID,"EB03Id",cellName,text,isComingFromAddButton,labelId);
	   }
	     $('#'+labelId).text(description);
	}

//New script function added for addition of only 16 service type code as part of BXNI June 2013 Release

function addRowForRuleEb03(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
		var table = document.getElementById(tableID);
		var tbody = document.getElementById("ebCodeTbody");
		var trClass = "";
		var cellId1;
		var labelId1;
		var cellId2;
		var labelId2;
		var cellId3;
		var labelId3;
		var clmnCount;
		var rowCount = $('#ebCodesTable > tbody > tr').length;
		var clmnLength = $('#ebCodesTable > tbody > tr > td').length;
		if(rowCount > 5){
			$('#eb03AddButton').hide();
			return false;
		}
		if (rowCount % 2 == 0) {
			trClass = "alternate";
		} else {
			trClass = "white-bg";
		}
		var row = document.createElement("TR");
		row.className=trClass;
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");
		var td5 = document.createElement("TD");
		var td6 = document.createElement("TD");
		
		var td7 = document.createElement("TD");
		var td8 = document.createElement("TD");
		var td9 = document.createElement("TD");
		if(isComingFromAddButton){
		clmnCount = clmnLength/3;
		labelId1 = "EB03IdLabel"+clmnCount;
		cellId1 = cellId+clmnCount;
		}
		// else condition not handled
		A = document.createElement("input");
	A.setAttribute("type","text");
	A.setAttribute("id",cellId1);
	A.setAttribute("name",$.trim(cellName));
	A.className="inputbox60";
	A.setAttribute("value",text);
	td1.style.width = "95px";
	td1.appendChild(A);
	
	B = document.createElement("input");
	B.setAttribute("type","hidden");
	B.setAttribute("id","EB03Desc"+clmnCount);
	B.setAttribute("name",'EB03Desc');
	td1.appendChild(B);
	
	L = document.createElement("label");
	L.setAttribute("id",labelId1);
	L.setAttribute("for",cellId1);
	L.className="UnmappedVariables";
	td2.style.width = "140px";
	td2.appendChild(L);
	if(clmnCount != 15){
	if(isComingFromAddButton){
		clmnCount = clmnCount+1;
		labelId2 = "EB03IdLabel"+clmnCount;
		cellId2 = cellId+clmnCount;
	}
	
	E = document.createElement("input");
	E.setAttribute("type","text");
	E.setAttribute("id",cellId2);
	E.setAttribute("name",$.trim(cellName));
	E.className="inputbox60";
	E.setAttribute("value","");
	td4.style.width = "95px";
	td4.appendChild(E);
	
	F = document.createElement("input");
	F.setAttribute("type","hidden");
	F.setAttribute("id","EB03Desc"+clmnCount);
	F.setAttribute("name",'EB03Desc');
	td4.appendChild(F);
	
	M = document.createElement("label");
	M.setAttribute("id",labelId2);
	M.setAttribute("for",cellId2);
	M.className="UnmappedVariables";
	td5.style.width = "140px";
	td5.appendChild(M);
		
	if(isComingFromAddButton){
		clmnCount = clmnCount+1;
		labelId3 = "EB03IdLabel"+clmnCount;
		cellId3 = cellId+clmnCount;
	}

	I = document.createElement("input");
	I.setAttribute("type","text");
	I.setAttribute("id",cellId3);
	I.setAttribute("name",$.trim(cellName));
	I.className="inputbox60";
	I.setAttribute("value","");
	td7.style.width = "95px";
	td7.appendChild(I);
	
	J = document.createElement("input");
	J.setAttribute("type","hidden");
	J.setAttribute("id","EB03Desc"+clmnCount);
	J.setAttribute("name",'EB03Desc');
	td7.appendChild(J);
	
	N = document.createElement("label");
	N.setAttribute("id",labelId3);
	N.setAttribute("for",cellId3);
	N.className="UnmappedVariables";
	td8.style.width = "140px";
	td8.appendChild(N);
 }	
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.appendChild(td5);
	row.appendChild(td6);
	row.appendChild(td7);
	row.appendChild(td8);	
	row.appendChild(td9);
		
		tbody.appendChild(row);	
		
		
	autoCompleteForDynamicEB033(cellId1, labelId1);
	autoCompleteForDynamicEB032(cellId2, labelId2);
	autoCompleteForDynamicEB031(cellId3, labelId3);
		
	rowCount = $('#ebCodesTable > tbody > tr').length;	
	if(rowCount > 5){
		$('#eb03AddButton').hide();
	}
	return true;
	}




