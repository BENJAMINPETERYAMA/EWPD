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
	
	var desc = $('#nm1MessageSegmentIdLabel').text();
	$('#nm1MessageSegmentDesc').val(desc);

	var table = document.getElementById('ebCodesTable');
	var eb03Length = $('#ebCodesTable > tbody > tr').length;
	for(var i = 0; i < eb03Length ; i++){
		$('#EB03Desc'+i).val($('#EB03IdLabel'+i).text());
	}
	//Added by AF48640 for the Additional HSD02 values
	for(var i=1;i<7;i++){
		$("#HSD02-0"+i+"SysId").val($('#HSD02-0'+i+"IdLabel").text());
	}
	
	var table = document.getElementById('accumTable');
	var accumsLength = $('#accumTable > tbody > tr').length;
	accumsLength = accumsLength-1;
	for(var i = 0; i < accumsLength ; i++){
		$('#AccumDesc'+i).val($('#accumIdLabel'+i).text());
	}
	//fix for description not showing while Error Message displays. --BXNI June
	rowLength = $('#umRuleTable > tbody > tr').length;
	for(var i = 0; i < rowLength * 3; i++){ 
		$('#UMRuleDesc'+i).val($('#UMRuleIdLabel'+i).text());
	}
	// fetch all the minor heading and append the value to a String
	var minorHeadings = $('input[id^=minorheading]');
	var minorheading = "";
	minorHeadings.each(function(i) {
		text = $(this).val();
		if (minorheading != "") {
			minorheading = minorheading+"^"+text;
		} else {
			minorheading = text;
		}
	});
	$('#valuesOfMinorHeadings').val(minorheading);
	// fetch all the major heading and append the value to a String
	var majorHeadings = $('input[id^=majorheading]');
	var majorheading = "";
	majorHeadings.each(function(i) {
		text = $(this).val();
		if (majorheading != "") {
			majorheading = majorheading+"^"+text;
		} else {
			majorheading = text;
		}
	});
	$('#valuesOfMajorHeadings').val(majorheading);
}

function setLabel(labelId,valueToBeSet){
	$('#'+labelId).html(valueToBeSet);
}

// function for 'what is this' link 
function hippaSegementDesc(hippaSegnmentNmae){
	$.ajax({
		url: "hippaSegmentDescription/hippaSegementDesc.ajax",
		dataType: "html",
		type: "POST",			
		data: "hippaSegmentCode="+hippaSegnmentNmae,
		success: function(data) {
		cache: false,
		response(data.rows);}
	});
}
	
/*The function will add row for eb03 and enable its autopopulation of hippa code values in textfield*/
function addRowForEb03(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
		var contextPath = $('#contextPath').val();
		var table = document.getElementById(tableID);
		var rowCount = 0;
		var idCount = -1;
		var tbody ="";
		var trClass= "";
		var autoSearch= "";
		var desc ="";
		
		if(tableID == 'ebCodesTable'){
			tbody = document.getElementById("ebCodeTbody");
			rowCount = $('#ebCodesTable >tbody >tr').length;
			//For EB03 Maximum row count is 8.
			if(rowCount>=8){
				$('#eb03AddButton').hide();
				return false;
			}
			autoSearch = "EB03";
			desc= "EB03Desc";
		}
		if(tableID == 'umRuleTable'){
			tbody = document.getElementById("umRuleTbody");
			rowCount = $('#umRuleTable >tbody >tr').length;
			//For UMRULE Maximum row count is 10.
			if(rowCount>=10){
				$('#umRuleAddButton').hide();
				return false;
			}
			autoSearch = "UMRULE";
			desc= "UMRuleDesc";
		}
		
		if(rowCount % 2 == 0){
			trClass = "alternate";
		}else{
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
		S = document.createElement("input");
		S.setAttribute("type","text");
		
		if(isComingFromAddButton){
			labelId = cellId+"Label"+rowCount;
			cellId = cellId+rowCount;
		}
				
		S.setAttribute("id",cellId);
		S.setAttribute("name",$.trim(cellName));
		S.className="inputbox60";
		S.setAttribute("value",text);
		
		
		T = document.createElement("input");
		T.setAttribute("type","hidden");
		T.setAttribute("id",desc+rowCount);
		T.setAttribute("name",desc);
		
		
		
		L = document.createElement("label");
		L.setAttribute("id",labelId);
		L.setAttribute("for",cellId);
		L.className="UnmappedVariables";
		
		
		if(desc == "EB03Desc"){
			td4.appendChild(S);
			td4.appendChild(T);
			td5.appendChild(L);
		}
		if(desc == "UMRuleDesc"){
			td1.style.width = "95px";
			td2.style.width = "140px";
			td1.appendChild(S);
			td1.appendChild(T);
			td2.appendChild(L); 
			I = document.createElement("img");
			I.setAttribute("id","viewRuleId"+rowCount);
			I.setAttribute("src", contextPath+"/images"+"/search_icon.gif");
			//I.setAttribute("onclick", "viewRuleSequenceInDetail("+cellId+");")
			I.style.display = 'none';
			td3.appendChild(I);
		}
		idCount = rowCount;
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		row.appendChild(td6);
		row.appendChild(td7);
		row.appendChild(td8);
		tbody.appendChild(row);	
		
		$(document).ready(function(){
			$('#'+cellId).blur(function() {
	   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   				$('#'+labelId).text('');
	   				$("#viewRuleId"+idCount).hide();
	   			}
	   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
	   			sensitiveBenefitCheck();
			});
			
			$('#'+cellId).autocomplete({ 
				select: function(event, ui) { 
					$('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); 
					$("#viewRuleId"+idCount).show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								data: "key="+request.term + "&name="+autoSearch,
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
						if(autoSearch == 'EB03'){
							displayLabelForSelectedItem(text,list,labelId,invalidHippaCodeValue);
						}
						if(autoSearch == 'UMRULE'){
							displayLabelForSelectedUmRule(text,list,labelId,invalidUmRule,"viewRuleId"+idCount);
						}
					}
				}
								
						
			});
		});
  	rowCount = table.rows.length;
  	//rowCount = rowCount+1;
	if(rowCount>=8 && tableID == 'ebCodesTable'){
		$('#eb03AddButton').hide();
	}
	if(rowCount>=10 && tableID == 'umRuleTable'){
			$('#umRuleAddButton').hide();
	}
	return true;	
	}//addRowForEb03 ends here


/*The function will add row for accums and enable its autopopulation of hippa code values in textfield*/
function addRowForAccum(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
		var table = document.getElementById(tableID);
		var tbody = document.getElementById("accumTbody");
		var rowCount = table.rows.length;
		
		//For Accum Maximum row count is 10.
		if(rowCount >= 3){
			$('#accumAddButton').hide();
			return false;
		}
		
		var trClass= "";
		if(rowCount % 2 == 0){
			trClass = "white-bg";
		}else{
			trClass = "alternate";
		}
		
		var row = document.createElement("TR");
		row.className=trClass;
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		S = document.createElement("input");
		S.setAttribute("type","text");
		
		var descId = 'AccumDesc';
		if(isComingFromAddButton){
			rowCount = rowCount-1;
			labelId = cellId+"Label"+rowCount;
			cellId = cellId+rowCount;
			descId = descId+rowCount;
			
		}
		
		S.setAttribute("id",cellId);
		S.setAttribute("name",$.trim(cellName));
		S.className="inputbox60";
		S.setAttribute("value",text);
		td1.appendChild(S);
		
		L = document.createElement("label");
		L.setAttribute("id",labelId);
		L.setAttribute("for",cellId);
		L.className="UnmappedVariables";
		td2.appendChild(L);
		
		M = document.createElement("input");
		M.setAttribute("type","hidden");
		M.setAttribute("id",descId);
		M.setAttribute("name",'AccumDesc');
		td2.appendChild(M);	
		
		
		
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		tbody.appendChild(row);
		
		$(document).ready(function(){
			$('#'+cellId).blur(function() {
	   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   				$('#'+labelId).text('');
	   			}
	   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
			});
	
			$('#'+cellId).autocomplete({ 
				select: function(event, ui) { $('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); },
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								data: "key="+request.term + "&name=ACCUM",
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
			})
 	 });
	 rowCount = table.rows.length;
	 rowCount = rowCount+1;
		if(rowCount >= 3){
			$('#accumAddButton').hide();
		}
 	 
	return true;	
	
	}//addRowForAccum ends here



	
//autocomplete
var list = new Array();
$(document).ready(function(){
	$('#EB01Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB01IdLabel").text('');
	   }
	   $('#EB01Id').val($('#EB01Id').val().toUpperCase());
		var selectedItem = $('#EB01Id').val();
		if(selectedItem != null && (selectedItem == "UM" || selectedItem == "um" 
			|| selectedItem == "Um" || selectedItem == "uM")){
			$("#excludeProceduresChkBox").show();
			$("#lblProceduresExcluded").show();
			$("#hrefProcedureExcluded").show();
			$("#excludeProceduresChkBox").val('checked');				
		}else{				
			$("#excludeProceduresChkBox").hide();
			$("#lblProceduresExcluded").hide();
			$("#hrefProcedureExcluded").hide();
			$("#excludeProceduresChkBox").val('');
		}
	});
	var variableFormat = "";
	variableFormat = $("#variableFormat").val();
	$("#EB01Id").autocomplete({ 
		select: function(event, ui) { 
			$("#EB01IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value');
			var selectedItem = ui.item.value;
			if(selectedItem != null && (selectedItem == "UM" || selectedItem == "um" 
				|| selectedItem == "Um" || selectedItem == "uM")){
				$("#excludeProceduresChkBox").show();
				$("#lblProceduresExcluded").show();
				$("#hrefProcedureExcluded").show();
				$("#excludeProceduresChkBox").val('checked');				
			}else{				
				$("#excludeProceduresChkBox").hide();
				$("#lblProceduresExcluded").hide();
				$("#hrefProcedureExcluded").hide();
				$("#excludeProceduresChkBox").val('');
			}},
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term +"&name=EB01"+"&varformat="+variableFormat,
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
				$("#excludeProceduresChkBox").hide();
				$("#lblProceduresExcluded").hide();
				$("#hrefProcedureExcluded").hide();
				$("#excludeProceduresChkBox").val('');				
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
	   sensitiveBenefitCheck();
	});  
	$("#EB03Id0").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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

$(document).ready(function(){
	$('#EB03Id1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel1").text('');
	   }
	   $('#EB03Id1').val($('#EB03Id1').val().toUpperCase());
	   sensitiveBenefitCheck();
	});
	$("#EB03Id1").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
	    sensitiveBenefitCheck();
	});
	$("#EB03Id2").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
	    sensitiveBenefitCheck();
	});
	$("#EB03Id3").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel3").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
	    sensitiveBenefitCheck();
	});
	$("#EB03Id4").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel4").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
	$('#EB03Id6').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel6").text('');
	   }
	   $('#EB03Id6').val($('#EB03Id6').val().toUpperCase());
	    sensitiveBenefitCheck();
	});
	$("#EB03Id6").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel6").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel6",invalidHippaCodeValue);
			}
		}
	})
  });
$(document).ready(function(){
	$('#EB03Id7').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel7").text('');
	   }
	   $('#EB03Id7').val($('#EB03Id7').val().toUpperCase());
	    sensitiveBenefitCheck();
	});
	$("#EB03Id7").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel7").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel7",invalidHippaCodeValue);
			}
		}
	})
  });
$(document).ready(function(){
	$('#EB03Id8').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel8").text('');
	   }
	   $('#EB03Id8').val($('#EB03Id8').val().toUpperCase());
	    sensitiveBenefitCheck();
	});
	$("#EB03Id8").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel8").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel8",invalidHippaCodeValue);
			}
		}
	})
  });
$(document).ready(function(){
	$('#EB03Id9').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel9").text('');
	   }
	   $('#EB03Id9').val($('#EB03Id9').val().toUpperCase());
	    sensitiveBenefitCheck();
	});
	$("#EB03Id9").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel9").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
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
				displayLabelForSelectedItem(text,list,"EB03IdLabel9",invalidHippaCodeValue);
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
	$('#accumId0').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#accumIdLabel0").text('');
	   }
  	   $('#accumId0').val($('#accumId0').val().toUpperCase());	   
	});
	$("#accumId0").autocomplete({ 
		select: function(event, ui) { $("#accumIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=ACCUM",
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
				displayLabelForSelectedItem(text,list,"accumIdLabel0",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#accumId1').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#accumIdLabel1").text('');
	   }
  	   $('#accumId1').val($('#accumId1').val().toUpperCase());
	});
	$("#accumId1").autocomplete({ 
		select: function(event, ui) { $("#accumIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=ACCUM",
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
				displayLabelForSelectedItem(text,list,"accumIdLabel1",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#accumId2').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#accumIdLabel2").text('');
	   }
   	   $('#accumId2').val($('#accumId2').val().toUpperCase());
	});
	$("#accumId2").autocomplete({ 
		select: function(event, ui) { $("#accumIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=ACCUM",
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
				displayLabelForSelectedItem(text,list,"accumIdLabel2",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
	$('#III02Id').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#III02IdLabel").text('');
	   }
	   $('#III02Id').val($('#III02Id').val().toUpperCase());
	});
	$("#III02Id").autocomplete({ 
		select: function(event, ui) { $("#III02IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
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
				displayLabelForSelectedItem(text,list,"III02IdLabel",invalidHippaCodeValue);
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
				displayLabelForSelectedItem(text,list,"NOTETYPEIDLabel",invalidHippaCodeValue);
			}
		}
	})
  });
  
$(document).ready(function(){
		var table = document.getElementById('ebCodesTable');
		var umRuletable = document.getElementById('umRuleTable');
		var umRulerowCount = $('#umRuleTable > tbody > tr').length;
		var rowCount = $('#ebCodesTable > tbody > tr').length;
		if(rowCount>8){
			$('#eb03AddButtonforVariable').hide();
		}
		if(umRulerowCount>=33){
			$('#umRuleAddButton').hide();
		}

});

$(document).ready(function(){
		var table = document.getElementById('accumTable');
		var rowCount = $('#accumTable > tbody > tr').length;
		if(rowCount> 3){
			$('#accumAddButton').hide();
		}
});

//apply css to invalid hippaSegment label
$(document).ready(function(){
		$("label").each(function(){
			var labelText = $.trim($(this).text());
			if(labelText!=null && labelText.length > 0){
				if(labelText == $.trim(invalidHippaCodeValue) || labelText == $.trim(invalidHippaCodeValue).toUpperCase()){
					$(this).text(invalidHippaCodeValue).addClass('invalid_hippacode_value');
				}
			}
		});
});

$(document).ready(function(){
	$('#HSD02Id').blur(function() {
	   $('#HSD02Id').val($('#HSD02Id').val().toUpperCase());
	});
});

$(document).ready(function(){
	$('#HSD04Id').blur(function() {
	   $('#HSD04Id').val($('#HSD04Id').val().toUpperCase());
	});
});

$(document).ready(function(){
	$('#HSD06Id').blur(function() {
	   $('#HSD06Id').val($('#HSD06Id').val().toUpperCase());
	});
});
function trimTextValues(){
		$('#EB01Id').val($.trim($('#EB01Id').val()));
		$('#EB02Id').val($.trim($('#EB02Id').val()));
		$('#EB03Id0').val($.trim($('#EB03Id0').val()));
		$('#EB03Id1').val($.trim($('#EB03Id1').val()));
		$('#EB03Id2').val($.trim($('#EB03Id2').val()));
		$('#EB03Id3').val($.trim($('#EB03Id3').val()));	
		$('#EB03Id4').val($.trim($('#EB03Id4').val()));
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
	$("#copyToDialog").hide();
	$('#changeCommentsId').blur(function() {
		$('#changeCommentsId').val($('#changeCommentsId').val().toUpperCase());
	});
	$('#messageValueId').blur(function() {
		$('#messageValueId').val($('#messageValueId').val().toUpperCase());
	});	
});

//autopoupulate NM1 Message Segment
$(document).ready(function(){
	$('#nm1MessageSegmentId').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#nm1MessageSegmentIdLabel").text('');
	   }
	   $('#nm1MessageSegmentId').val($('#nm1MessageSegmentId').val().toUpperCase());
	});
	$("#nm1MessageSegmentId").autocomplete({ 
		select: function(event, ui) { $("#nm1MessageSegmentIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },	
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=2120_LOOP_NM1_MESSAGE_SEGMENT",
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
				displayLabelForSelectedItem(text,list,"nm1MessageSegmentIdLabel",invalidHippaCodeValue);
			}
		}
	})
  });

function eb03PopulationOnVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx){
				var table = document.getElementById('ebCodesTable');
				var rowCount = table.rows.length;
				var j = 0;
				for(var i=0;i<length;i++) {
					var text = valuesInTxBx[i];
					if(rowCount <= 8){
								if(j >= rowCount){
									//insert row
									var index = $.inArray(text,hippaCodeValueArray);
									var desc = 	hippaCodeDescArray[index];
									var isRowAdded = addRowForEb03('ebCodesTable','EB03Id','eb03Val',text,'true','');
									if(isRowAdded){
										document.getElementById('EB03IdLabel'+j).innerHTML = desc;
										$('#EB03IdLabel'+j).removeClass('invalid_hippacode_value');
									}
									j++;
								}else{
									$('#EB03Id'+j).val(text);
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
									$('#'+hippaSegmentId+'Label'+j).text(desc);
									
									if($.trim(desc) == $.trim(invalidHippaCodeValue)){
										$('#'+hippaSegmentId+'Label'+j).addClass('invalid_hippacode_value');
									}else{
										$('#'+hippaSegmentId+'Label'+j).removeClass('invalid_hippacode_value');
									}
									j++;
								}
					}//end of if
				}// end of for loop
				
				//after populating all the values to the textboxes , remove the txtboxes that comes after the newly populated list
				if(rowCount >=j){
					for(var k = j;k <rowCount;k++){
						$('#EB03Id'+k).val('');
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
$(document).ready(function(){
		$("#messageTextDialog").hide();
});	
//message text search functions ends

/********************************************BXNI June 2012 Release******************************************************************/

//Dynamic Insert of Accums Start

function insertAccum(tableID,cellId,cellName,text,isComingFromAddButton,labelId,description,descId){
	   if($('#'+cellId).length > 0){
	         $('#'+cellId).val(text);
	   }else{      		
		   addRowForAccummulators(tableID,"accumId",cellName,text,isComingFromAddButton,labelId);
	   }
	     $('#'+labelId).text(description);
	}
function addRowForAccummulators(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
		var table = document.getElementById(tableID);
		var tbody = document.getElementById("accumTbody");
		var trClass = "";
		var cellId1;
		var labelId1;
		var cellId2;
		var labelId2;
		var cellId3;
		var labelId3;
		var clmnCount;
		var rowCount = $('#accumTable > tbody > tr').length;
		var clmnLength = $('#accumTable > tbody > tr > td').length;
		
		if(rowCount > 3){
			$('#accumAddButton').hide();
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
			clmnCount = (clmnLength)/3;
			
			labelId1 = cellId+"Label"+clmnCount;
			cellId1 = cellId+clmnCount;
		}
		// else condition not handled
		A = document.createElement("input");
		A.setAttribute("type","text");
		A.setAttribute("id",cellId1);
		A.setAttribute("name",$.trim(cellName));
		A.className="inputbox60";
		A.setAttribute("value",text);
		td1.style.width = "95";
		td1.appendChild(A);
		
		B = document.createElement("input");
		B.setAttribute("type","hidden");
		B.setAttribute("id","AccumDesc"+clmnCount);
		B.setAttribute("name",'AccumDesc');
		td2.appendChild(B);
		
		L = document.createElement("label");
		L.setAttribute("id",labelId1);
		L.setAttribute("for",cellId1);
		L.className="UnmappedVariables";
		td2.style.width = "140px";
		td2.appendChild(L);
	if(clmnCount != 9){
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			labelId2 = cellId+"Label"+clmnCount;
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
		F.setAttribute("id","AccumDesc"+clmnCount);
		F.setAttribute("name",'AccumDesc');
		td5.appendChild(F);
		
		M = document.createElement("label");
		M.setAttribute("id",labelId2);
		M.setAttribute("for",cellId2);
		M.className="UnmappedVariables";
		td5.style.width = "140px";
		td5.appendChild(M);
			
		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			labelId3 = cellId+"Label"+clmnCount;
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
		J.setAttribute("id","AccumDesc"+clmnCount);
		J.setAttribute("name",'AccumDesc');
		td8.appendChild(J);
		
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
		
		
		autoCompleteForDynamicAccumulator3(cellId1, labelId1);
		autoCompleteForDynamicAccumulator2(cellId2, labelId2);
		autoCompleteForDynamicAccumulator1(cellId3, labelId3);
		
	rowCount = $('#accumTable > tbody > tr').length;	
	if(rowCount > 3){
		$('#accumAddButton').hide();
	}
	return true;
	}



function autoCompleteForDynamicAccumulator1(cellId,labelId){
	autoCompleteForDynamicAccumulator(cellId,labelId);	
}
function autoCompleteForDynamicAccumulator2(cellId,labelId){
	autoCompleteForDynamicAccumulator(cellId,labelId);	
}
function autoCompleteForDynamicAccumulator3(cellId,labelId){
	autoCompleteForDynamicAccumulator(cellId,labelId);	
}

function autoCompleteForDynamicAccumulator(cellId,labelId){

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
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						data: "key="+request.term + "&name=ACCUM",
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

//This function will populate the values from the pop up to the accum textboxes
function accumPopulationOnVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx){
	
	var table = document.getElementById('accumTable');
	var rowCount = table.rows.length;
	var j = 0;
	var clmnLength ;
	var clmnCnt;
	
	for(var i=0;i<length;) {
		var text = valuesInTxBx[i];
			clmnLength = $('#accumTable > tbody > tr > td').length;
			clmnCnt = clmnLength/3;
			if(j >= clmnCnt){
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = 	hippaCodeDescArray[index];
				insertAccum('accumTable','accumId'+j,'accumulator',text,true,'accumIdLabel'+j,desc,'AccumDesc'+j);							
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
//Dynamic Insert of Accums End

//Dynamic Insert of EB03 Start
function insertVariableEBO3(tableID,cellId,cellName,text,isComingFromAddButton,labelId,description,descId){
	   if($('#'+cellId).length > 0){
	         $('#'+cellId).val(text);
	   }else{      		
		   addRowForVariableEb03(tableID,"EB03Id",cellName,text,isComingFromAddButton,labelId);
	   }
	     $('#'+labelId).text(description);
	     $('#'+descId).val(description);
	}
function addRowForVariableEb03(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
		var table = document.getElementById(tableID);
		var tbody = document.getElementById("ebTbody");
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
		if(rowCount > 8){
			$('#eb03AddButtonforVariable').hide();
			return false;
		}
		if (rowCount % 2 == 0) {
			trClass = "white-bg";
		} else {
			trClass = "alternate";
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
		if(isComingFromAddButton){
			//column length 66 is initialized based on the column position in the html page.
			//A row in the table contains 11 columns, so the next eb03 textbox should have to be loaded on the 6th row (6*11= 66)
			if(clmnLength == 63){
				clmnCount = 10;
			}
			else if(clmnLength == 63+11){
				clmnCount = 10+2;
			}else if(clmnLength == 63+11+11){
				clmnCount = 10+2+2;
			}
			
			labelId1 = cellId+"Label"+clmnCount;
			cellId1 = cellId+clmnCount;
		}
		// else condition not handled
		A = document.createElement("input");
		A.setAttribute("type","text");
		A.setAttribute("id",cellId1);
		A.setAttribute("name",$.trim(cellName));
		A.className="inputbox60";
		A.setAttribute("value",text);
		A.onblur= function() {isEb03Modified();};
		td7.width = "70px";
		td7.appendChild(A);
		
		B = document.createElement("input");
		B.setAttribute("type","hidden");
		B.setAttribute("id","EB03Desc"+clmnCount);
		B.setAttribute("name",'EB03Desc');
		td8.appendChild(B);
		
		L = document.createElement("label");
		L.setAttribute("id",labelId1);
		L.setAttribute("for",cellId1);
		L.className="UnmappedVariables";
		td8.width = "100px";
		td8.appendChild(L);

		if(isComingFromAddButton){
			clmnCount = clmnCount+1;
			labelId2 = cellId+"Label"+clmnCount;
			cellId2 = cellId+clmnCount;
		}
		
		E = document.createElement("input");
		E.setAttribute("type","text");
		E.setAttribute("id",cellId2);
		E.setAttribute("name",$.trim(cellName));
		E.className="inputbox60";
		E.setAttribute("value","");
		E.onblur= function() {isEb03Modified();};
		td10.width = "70px";
		td10.appendChild(E);
		
		F = document.createElement("input");
		F.setAttribute("type","hidden");
		F.setAttribute("id","EB03Desc"+clmnCount);
		F.setAttribute("name",'EB03Desc');
		td11.appendChild(F);
		
		M = document.createElement("label");
		M.setAttribute("id",labelId2);
		M.setAttribute("for",cellId2);
		M.className="UnmappedVariables";
		td11.style.width = "120px";
		td11.appendChild(M);
		
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
		
		tbody.appendChild(row);	
		
		
		autoCompleteForDynamicVariableEB032(cellId1, labelId1);
		autoCompleteForDynamicVariableEB031(cellId2, labelId2);
		
	rowCount = $('#ebCodesTable > tbody > tr').length;	
	if(rowCount > 8){
		$('#eb03AddButtonforVariable').hide();
	}
	return true;
	}



function autoCompleteForDynamicVariableEB032(cellId,labelId){
	autoCompleteForDynamicVariableEB03(cellId,labelId);	
}
function autoCompleteForDynamicVariableEB031(cellId,labelId){
	autoCompleteForDynamicVariableEB03(cellId,labelId);	
}

function autoCompleteForDynamicVariableEB03(cellId,labelId){


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
						url: "../ajaxautocompletelistcreatepage.ajax",
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
//This function will populate the values from the pop up to the eb03 textboxes
function eb03PopulationOnVarMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx){
	var j = 0;
	var clmnLength ;
	var clmnCnt;
	for(var i=0;i<length;) {
		var text = valuesInTxBx[i];
			clmnLength = $('#ebCodesTable > tbody > tr > td').length;
			if(j >= 10){
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = 	hippaCodeDescArray[index];
				insertVariableEBO3('ebCodesTable','EB03Id'+j,'eb03Val',text,true,'EB03IdLabel'+j,desc,'EB03Desc'+j);					
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
//Dynamic Insert of EB03 End

//Start Age/End Age page side Validations
function ageTierVariableValidation(startAge,endAge,variableId){
		ageValidation(variableId,startAge,endAge);
	
}
//method does an ajax call to check whether an entered start age/end age string is a valid variable
function ageValidation(variableId,startAge,endAge){
	$.ajax({
		url: "../editmapping/isAgeTierVariableValid.ajax",
		dataType: "json",
		type: "POST",			
		data: "variableIdHidden="+escape(variableId)+"&startAge="+escape(startAge)+"&endAge="+escape(endAge),
		success: function(data) {
			cache: false,
			errorMessageInvalidAgeVariable(data);
		}
	});
}
//method populates the errormessages returned from the controller method. If no error messages, then proceeds with the next action
function errorMessageInvalidAgeVariable(data){
	if(data.error_messages != null && data.error_messages != ""){
		var errorMessages = data.error_messages;
		var message = "";
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				if(message == ""){
					message = errorMessages[i];
				}else{
					message = message +"<br> # "+errorMessages[i];
				}
				addErrorToNotificationTray(message);
			}
			openTrayNotification();
		}
		 return false; 
	}else{
		var isMsgValid = messageCheck();

		if(isMsgValid){
			displayCommentsDialog();	
		}
	}
}
//method loads the change comments popup if all the start age/end age and message check validations are passed
function displayCommentsDialog(){
	$("#changeCommentsDialog").dialog({
	                       height:'auto',
						width:'450px',	
						resizable:false,
	                       show:'slide',
						title: 'Change Comments',
	                       modal: true
	                 });
			$("#changeCommentsDialog").dialog();
	}
//Validation for Exclusion of Special Characters in Message Text.
// Modified as part of SSCR19537
function messageCheck() {
		var indEb03AssnChd = $('#indEB03AssnCheckBoxId').attr('checked');
		if (indEb03AssnChd) {
			var eb03AssnTable = document.getElementById('eb03AssnTable');
			var eb03ListForSpclChar  = "";
		
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				var eb03Value = eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
				var msgValue = $.trim($('#MESSAGEVALUEId'+(i-1)).val()); 
				$('#MESSAGEVALUEId'+(i-1)).val(msgValue)
				var isMsgvalid = checkMessageValidation(msgValue);
				if(!isMsgvalid) {
					if(eb03ListForSpclChar  == ""){
						eb03ListForSpclChar  = eb03Value;
                    } else {
                    	eb03ListForSpclChar  = eb03ListForSpclChar  +", "+eb03Value;
                    }
				}
			}
			if(eb03ListForSpclChar  != ""){
	            var message = "* ^ - ~ { } _ : ; ? ! [ ] < > are invalid characters for Message Text. Review Message Text mapped to EB03(s) - " +eb03ListForSpclChar;                 
	            addErrorToNotificationTray(message);
	            openTrayNotification(); 
	            return false;
            }
				
		} else if (!indEb03AssnChd) {
			var msgValue = $.trim($('#messageValueId').val());
			$('#messageValueId').val(msgValue);
			var isMsgvalid = checkMessageValidation(msgValue);
			if(!isMsgvalid) {
				var message = "* ^ - ~ { } _ : ; ? ! [ ] < > are invalid characters for Message Text";			
				addErrorToNotificationTray(message);
				openTrayNotification();	
				return false;
	  		}
		}
		return true;
	}

/* CR29. Function to check if selected eb03 value is UM and based on that check box excludeProceduresChkBox, its value and label are toggled */
function checkIfProceduresToBeExcluded(){
	var selectedItem = $("#EB01Id").val();
	if(selectedItem != null && (selectedItem == "UM" || selectedItem == "um" ||
		selectedItem == "Um" || selectedItem == "uM" )){
		$("#excludeProceduresChkBox").show();
		$("#lblProceduresExcluded").show();
		$("#hrefProcedureExcluded").show();
		$("#excludeProceduresChkBox").val('checked');				
	}else{
		$("#excludeProceduresChkBox").hide();
		$("#lblProceduresExcluded").hide();
		$("#hrefProcedureExcluded").hide();
		$("#excludeProceduresChkBox").val('');
	}
}

/********************************************BXNI June 2012 Release******************************************************************/

// SSCR 19537 - April 04 - Start

/** Auto complete function for III02*/
	function autocompleteIII02 () {
		$('#III02Id0').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id0Label").text('');
		   }
		   $('#III02Id0').val($('#III02Id0').val().toUpperCase());
		});
			
		$("#III02Id0").autocomplete({ 
			select: function(event, ui) { $("#III02Id0Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id0Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id1').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id1Label").text('');
		   }
		   $('#III02Id1').val($('#III02Id1').val().toUpperCase());
		});
			
		$("#III02Id1").autocomplete({ 
			select: function(event, ui) { $("#III02Id1Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id1Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id2').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id2Label").text('');
		   }
		   $('#III02Id2').val($('#III02Id2').val().toUpperCase());
		});
			
		$("#III02Id2").autocomplete({ 
			select: function(event, ui) { $("#III02Id2Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id2Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id3').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id3Label").text('');
		   }
		   $('#III02Id3').val($('#III02Id3').val().toUpperCase());
		});
			
		$("#III02Id3").autocomplete({ 
			select: function(event, ui) { $("#III02Id3Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id3Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id4').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id4Label").text('');
		   }
		   $('#III02Id4').val($('#III02Id4').val().toUpperCase());
		});
			
		$("#III02Id4").autocomplete({ 
			select: function(event, ui) { $("#III02Id4Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id4Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id5').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id5Label").text('');
		   }
		   $('#III02Id5').val($('#III02Id5').val().toUpperCase());
		});
			
		$("#III02Id5").autocomplete({ 
			select: function(event, ui) { $("#III02Id5Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id5Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id6').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id6Label").text('');
		   }
		   $('#III02Id6').val($('#III02Id6').val().toUpperCase());
		});
			
		$("#III02Id6").autocomplete({ 
			select: function(event, ui) { $("#III02Id6Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id6Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id7').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id7Label").text('');
		   }
		   $('#III02Id7').val($('#III02Id7').val().toUpperCase());
		});
			
		$("#III02Id7").autocomplete({ 
			select: function(event, ui) { $("#III02Id7Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id7Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id8').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id8Label").text('');
		   }
		   $('#III02Id8').val($('#III02Id8').val().toUpperCase());
		});
			
		$("#III02Id8").autocomplete({ 
			select: function(event, ui) { $("#III02Id8Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id8Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id9').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id9Label").text('');
		   }
		   $('#III02Id9').val($('#III02Id9').val().toUpperCase());
		});
			
		$("#III02Id9").autocomplete({ 
			select: function(event, ui) { $("#III02Id9Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id9Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id10').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id10Label").text('');
		   }
		   $('#III02Id10').val($('#III02Id10').val().toUpperCase());
		});
			
		$("#III02Id10").autocomplete({ 
			select: function(event, ui) { $("#III02Id10Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id10Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id11').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id11Label").text('');
		   }
		   $('#III02Id11').val($('#III02Id11').val().toUpperCase());
		});
			
		$("#III02Id11").autocomplete({ 
			select: function(event, ui) { $("#III02Id11Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id11Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id12').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id12Label").text('');
		   }
		   $('#III02Id12').val($('#III02Id12').val().toUpperCase());
		});
			
		$("#III02Id12").autocomplete({ 
			select: function(event, ui) { $("#III02Id12Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id12Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id13').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id13Label").text('');
		   }
		   $('#III02Id13').val($('#III02Id13').val().toUpperCase());
		});
			
		$("#III02Id13").autocomplete({ 
			select: function(event, ui) { $("#III02Id13Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id13Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id14').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id14Label").text('');
		   }
		   $('#III02Id14').val($('#III02Id14').val().toUpperCase());
		});
			
		$("#III02Id14").autocomplete({ 
			select: function(event, ui) { $("#III02Id14Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id14Label",invalidHippaCodeValue);
				}
			}
		});
		$('#III02Id15').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#III02Id15Label").text('');
		   }
		   $('#III02Id15').val($('#III02Id15').val().toUpperCase());
		});
			
		$("#III02Id15").autocomplete({ 
			select: function(event, ui) { $("#III02Id15Label").text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							type:"POST",
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
					displayLabelForSelectedItem(text,list,"III02Id15Label",invalidHippaCodeValue);
				}
			}
		});
  };
  
