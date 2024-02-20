/**
	This file contains the functions that are specific to Um rules.
	This js file will be used by createmapping.jsp/editmapping.jsp/
	createRulemapping.jsp and ediRulemapping.jsp.
*/
var list = new Array();
//Enabling Um Rule AutoComplete feature.
$(document).ready(function(){
	
	$('#UMRuleId0').blur(function(){
				if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
					$("#UMRuleIdLabel0").text('');
					$("#viewRuleId0").hide();
				}
				$('#UMRuleId0').val($('#UMRuleId0').val().toUpperCase());
			});
			$("#UMRuleId0").autocomplete({ 
				select: function(event, ui) { 
					$("#UMRuleIdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); 
					$("#viewRuleId0").show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=UMRULE",
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
						displayLabelForSelectedUmRule(text,list,"UMRuleIdLabel0",invalidUmRule,"viewRuleId0");
					}
				}
			})
			$('#UMRuleId1').blur(function(){
				if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
					$("#UMRuleIdLabel1").text('');
					$("#viewRuleId1").hide();
				}
				$('#UMRuleId1').val($('#UMRuleId1').val().toUpperCase());
			});
			$("#UMRuleId1").autocomplete({ 
				select: function(event, ui) { 
					$("#UMRuleIdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value'); 
					$("#viewRuleId1").show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=UMRULE",
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
						displayLabelForSelectedUmRule(text,list,"UMRuleIdLabel1",invalidUmRule,"viewRuleId1");
					}
				}
			})
			$('#UMRuleId2').blur(function(){
				if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
					$("#UMRuleIdLabel2").text('');
					$("#viewRuleId2").hide();
				}
				$('#UMRuleId2').val($('#UMRuleId2').val().toUpperCase());
			});
			$("#UMRuleId2").autocomplete({ 
				select: function(event, ui) { 
					$("#UMRuleIdLabel2").text(ui.item.id).removeClass('invalid_hippacode_value'); 
					$("#viewRuleId2").show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=UMRULE",
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
						displayLabelForSelectedUmRule(text,list,"UMRuleIdLabel2",invalidUmRule,"viewRuleId2");
					}
				}
			})
			$('#UMRuleId3').blur(function(){
				if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
					$("#UMRuleIdLabel3").text('');
					$("#viewRuleId3").hide();
				}
				$('#UMRuleId3').val($('#UMRuleId3').val().toUpperCase());
			});
			$("#UMRuleId3").autocomplete({ 
				select: function(event, ui) { 
					$("#UMRuleIdLabel3").text(ui.item.id).removeClass('invalid_hippacode_value');
					$("#viewRuleId3").show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=UMRULE",
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
						displayLabelForSelectedUmRule(text,list,"UMRuleIdLabel3",invalidUmRule,"viewRuleId3");
					}
				}
			})
			$('#UMRuleId4').blur(function(){
				if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
					$("#UMRuleIdLabel4").text('');
					$("#viewRuleId4").hide();
				}
				$('#UMRuleId4').val($('#UMRuleId4').val().toUpperCase());
			});
			$("#UMRuleId4").autocomplete({ 
				select: function(event, ui) { 
					$("#UMRuleIdLabel4").text(ui.item.id).removeClass('invalid_hippacode_value');
					$("#viewRuleId4").show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=UMRULE",
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
						displayLabelForSelectedUmRule(text,list,"UMRuleIdLabel4",invalidUmRule,"viewRuleId4");
					}
				}
			})
		
		if($('#UMRuleId5').length > 0){
			$('#UMRuleId5').blur(function(){
				if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
					$("#UMRuleIdLabel5").text('');
					$("#viewRuleId5").hide();
				}
				$('#UMRuleId5').val($('#UMRuleId5').val().toUpperCase());
			});
			$("#UMRuleId5").autocomplete({ 
				select: function(event, ui) { 
					$("#UMRuleIdLabel5").text(ui.item.id).removeClass('invalid_hippacode_value');
					$("#viewRuleId5").show();
				},
				source: function(request, response) {
							$.ajax({
								url: "../ajaxautocompletelistcreatepage.ajax",
								dataType: "json",
								type:"POST",
								data: "key="+request.term + "&name=UMRULE",
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
						displayLabelForSelectedUmRule(text,list,"UMRuleIdLabel5",invalidUmRule,"viewRuleId5");
					}
				}
			})
			isValidUMRuleEntered("UMRuleIdLabel5","viewRuleId5");
		}
		//Do the checking only after enabling the auto-complete feature
		isValidUMRuleEntered("UMRuleIdLabel0","viewRuleId0");
		isValidUMRuleEntered("UMRuleIdLabel1","viewRuleId1");
		isValidUMRuleEntered("UMRuleIdLabel2","viewRuleId2");
		isValidUMRuleEntered("UMRuleIdLabel3","viewRuleId3");
		isValidUMRuleEntered("UMRuleIdLabel4","viewRuleId4");	
		
		var pageName = $('#pageName').val();
		if(pageName == 'variableMappingPage'){
			var rowCount = $('#umRuleTable > tbody > tr').length;
			//alert("rowCount"+rowCount);
			if (rowCount >= 33) {
				$('#umRuleAddButton').hide();
			}
		}else if(pageName == 'headerRuleMappingPage'){
			var rowCount = $('#umRuleTable > tbody > tr').length;
			if (rowCount >= 10) {
				$('#umRuleAddButton').hide();
			}
		}	
		
		
	
});

/*
 * Hide the view icon if the label field is blank 
 * or if the label has the class  invalid_hippacode_value 
 * or if it has text invalid UMRULE.
 *Show otherwise
 */
function isValidUMRuleEntered(elementId,viewIconId){
	
	if($('#'+elementId).text()!=null && $.trim($('#'+elementId).text()).length > 0){ // if label text not empty
		var labelText = $('#'+elementId).text();
		labelText =  labelText.replace("\n/g","");
		if($('#'+elementId).hasClass( 'invalid_hippacode_value' ) || $.trim(labelText)==invalidUmRule){
			$('#'+elementId).addClass('invalid_hippacode_value');
			$('#'+viewIconId).hide();
		}else{
			$('#'+viewIconId).show();
		}
	}else{
		$('#'+viewIconId).hide();
	}
}
function addUmRuleForHeaderRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
	var contextPath = $('#contextPath').val();
	var table = document.getElementById(tableID);
	var tbody = document.getElementById("umRuleTbody");
	var trClass = "";
	var cellId1;
	var labelId1;
	var cellId2;
	var labelId2;
	var cellId3;
	var labelId3;
	
	var rowCount = $('#umRuleTable > tbody > tr').length;
	var clmnLength = $('#umRuleTable > tbody > tr > td').length;
	if (rowCount >= 33) {
		$('#umRuleAddButton').hide();
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
	td1.style.width = "95px";
	td1.appendChild(A);
	
	B = document.createElement("input");
	B.setAttribute("type","hidden");
	B.setAttribute("id","UMRuleDesc"+clmnCount);
	B.setAttribute("name",'UMRuleDesc');
	td1.appendChild(B);
	
	L = document.createElement("label");
	L.setAttribute("id",labelId1);
	L.setAttribute("for",cellId1);
	L.className="UnmappedVariables";
	td2.style.width = "140px";
	td2.appendChild(L);
	
	D = document.createElement("img");
	D.setAttribute("id","viewRuleId"+clmnCount);
	D.setAttribute("src", contextPath+"/images"+"/search_icon.gif");
	D.style.display = 'none';
	td3.appendChild(D);
	
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
	F.setAttribute("id","UMRuleDesc"+clmnCount);
	F.setAttribute("name",'UMRuleDesc');
	td4.appendChild(F);
	
	M = document.createElement("label");
	M.setAttribute("id",labelId2);
	M.setAttribute("for",cellId2);
	M.className="UnmappedVariables";
	td5.style.width = "140px";
	td5.appendChild(M);
	
	H = document.createElement("img");
	H.setAttribute("id","viewRuleId"+clmnCount);
	H.setAttribute("src", contextPath+"/images"+"/search_icon.gif");
	H.style.display = 'none';
	td6.appendChild(H);
	
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
	J.setAttribute("id","UMRuleDesc"+clmnCount);
	J.setAttribute("name",'UMRuleDesc');
	td7.appendChild(J);
	
	N = document.createElement("label");
	N.setAttribute("id",labelId3);
	N.setAttribute("for",cellId3);
	N.className="UnmappedVariables";
	td8.style.width = "140px";
	td8.appendChild(N);
	
	
	K = document.createElement("img");
	K.setAttribute("id","viewRuleId"+clmnCount);
	K.setAttribute("src", contextPath+"/images"+"/search_icon.gif");
	K.style.display = 'none';
	td9.appendChild(K);
	
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
	
		var clmnCount3 = clmnCount-2;
		autoCompleteForDynamicUmRule3(cellId1, labelId1,'viewRuleId'+clmnCount3);	
		var clmnCount2 = clmnCount-1;
		autoCompleteForDynamicUmRule2(cellId2, labelId2,'viewRuleId'+clmnCount2);
		autoCompleteForDynamicUmRule1(cellId3, labelId3,'viewRuleId'+clmnCount);

	rowCount = $('#umRuleTable > tbody > tr').length;	
	if(rowCount>=33){
		$('#umRuleAddButton').hide();
	}
	return true;	
}

function autoCompleteForDynamicUmRule(cellId,labelId,imageId){
		$('#'+cellId).blur(function() {
   			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
   				$('#'+labelId).text('');
   				$('#'+imageId).hide();
   			}
   			$('#'+cellId).val($('#'+cellId).val().toUpperCase());
		});
		$('#'+cellId).autocomplete({ 
			select: function(event, ui) {$('#'+labelId).text(ui.item.id).removeClass('invalid_hippacode_value'); $('#'+imageId).show();},
			source: function(request, response) {
						$.ajax({
							url: "../ajaxautocompletelistcreatepage.ajax",
							dataType: "json",
							data: "key="+request.term + "&name=UMRULE",
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
					displayLabelForSelectedUmRule(text,list,labelId,invalidUmRule,imageId);
				}
			}
							
					
		});
		
}
function autoCompleteForDynamicUmRule1(cellId,labelId,imageId){
	autoCompleteForDynamicUmRule(cellId,labelId,imageId);	
}
function autoCompleteForDynamicUmRule2(cellId,labelId,imageId){
	autoCompleteForDynamicUmRule(cellId,labelId,imageId);	
}
function autoCompleteForDynamicUmRule3(cellId,labelId,imageId){
	autoCompleteForDynamicUmRule(cellId,labelId,imageId);	
}
//This function will be invoked to insert values for the dynamic textboxes on page reload
function insertUmRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId,description,descId){
      if($('#'+cellId).length > 0){
            $('#'+cellId).val(text);
      }else{
    	  var pageName = $('#pageName').val();
    		if(pageName == 'variableMappingPage'){
            //addRowForUmRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId);
            //variable can also hold 99 Um Rules. So reusing the Header Rule logic 
            addRowForUmRule(tableID,"UMRuleId",cellName,text,isComingFromAddButton,labelId);
    		}else if(pageName == 'headerRuleMappingPage'){
    		addRowForUmRule(tableID,"UMRuleId",cellName,text,isComingFromAddButton,labelId);
    		}
      }
      $('#'+labelId).text(description); 
      $('#'+descId).val(description); 
      isValidUMRuleEntered(labelId,labelId.replace('UMRuleIdLabel','viewRuleId'));
}

//This function will populate the values from the pop up to the um rule textboxes
function umRulePopulationOnHearderRuleMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx){
	var table = document.getElementById('umRuleTable');
	var rowCount = table.rows.length;
	var j = 0;
	var clmnLength ;
	var clmnCnt;
	
	for(var i=0;i<length;) {
		var text = valuesInTxBx[i];
		if(rowCount <= 33){
			clmnLength = $('#umRuleTable > tbody > tr > td').length;
			clmnCnt = clmnLength/3;
			if(j >= clmnCnt){
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = 	hippaCodeDescArray[index];
				insertUmRule('umRuleTable','UMRuleId'+j,'umRuleVal',text,true,'UMRuleIdLabel'+j,desc,'UMRuleDesc'+j);							
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
				isValidUMRuleEntered(hippaSegmentId+'Label'+j,'viewRuleId'+j);
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
			$('#viewRuleId'+k).hide('');
			$('#'+hippaSegmentId+'Label'+k).removeClass('invalid_hippacode_value');
		}
	}
	
}

function umRulePopulationOnContractVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx){
	var table = document.getElementById('umRuleTable');
	var rowCount = table.rows.length;
	var j = 0;

	for(var i=0;i<length;i++) {
		var text = valuesInTxBx[i];
		if(rowCount <= 33){
			if(j >= rowCount){
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = 	hippaCodeDescArray[index];
				var isRowAdded =  addRowForUmRule('umRuleTable','UMRuleId','umRuleVal',text,true,'')
				if(isRowAdded){
					document.getElementById('UMRuleIdLabel'+j).innerHTML = desc;
					$('#UMRuleIdLabel'+j).removeClass('invalid_hippacode_value');
				}
				isValidUMRuleEntered(hippaSegmentId+'Label'+j,'viewRuleId'+j);
				j++;
			}else{
				$('#'+ hippaSegmentId +j).val(text);
				var index = $.inArray(text,hippaCodeValueArray);
				var desc = '';
				if(index < 0){
					index = $.inArray(text,copyOfValuesInTxBx);
					if(index > -1){
						desc = 	descForValuesInTxBx[index];
						desc = desc.replace(/<BR>/g,'');
					}
				}else{
					desc = 	hippaCodeDescArray[index];
					desc = desc.replace(/<BR>/g,'');
				}
				$('#'+ hippaSegmentId+'Label'+j).text(desc);
				
				if($.trim(desc) == $.trim(invalidHippaCodeValue)){
					$('#'+ hippaSegmentId+'Label'+j).addClass('invalid_hippacode_value');
				}else{
					$('#'+ hippaSegmentId+'Label'+j).removeClass('invalid_hippacode_value');
				}
				isValidUMRuleEntered(hippaSegmentId+'Label'+j,'viewRuleId'+j);
				j++;
			}
		}
	}
	//after populating all the values to the textboxes , remove the txtboxes that comes after the newly populated list
	if(rowCount >=j){
		for(var k = j;k <rowCount;k++){
			$('#'+ hippaSegmentId +k).val('');
			$('#'+hippaSegmentId+'Label'+k).text('');
			$('#viewRuleId'+k).hide('');
			$('#'+hippaSegmentId+'Label'+k).removeClass('invalid_hippacode_value');
		}
	}

}
var ruleViewFlag = false;
var groupRuleViewFlag = false;
//For view rule pop up
function viewRuleSequenceInDetail(umRuleId,url){
	var contextPath = $('#contextPath').val();
	var element = $("[id='titleName']");
	element.each(function(){
			$(this).val(null);
		});
	$.ajax({
		url:contextPath+url,
		dataType:"html",
		type:"POST",
		data: "umRuleId="+encodeURIComponent(String(umRuleId)),
		success: function(data){
			if($("#ruleViewPopUpDiv").length == 0){
				$('#hippaCodeSelectedValues').append('<div id="ruleViewPopUpDiv"></div>');
				ruleViewFlag = true;
			}
			setDataToDiv(data);
		}
	});
}

function setDataToDiv(data){
	$("#ruleViewPopUpDiv").html(data);
	var title = '';
	var element = $("[id='titleName']");
	element.each(function(){
		if($(this).val() != null){
			title = $(this).val();
		}	
	});
	
	if(title == 'View Group Rule'){
		if($("#groupPagePopUpDiv").length == 0){
			$('#hippaCodeSelectedValues').append('<div id="groupPagePopUpDiv"></div>');
			groupRuleViewFlag = true;
		}
		$("#ruleViewPopUpDiv").html('');
		$("#ruleViewPopUpDiv").hide();
		$("#groupPagePopUpDiv").html(data);
		openViewRuleDialog(title,'groupPagePopUpDiv');
	}else{
		var titleName = $('#titleName').val();
		if(titleName == 'View Group Rule'){
			title = 'View Group Rule';
		}else{
			title= "View Rule";
		}
		$("#ruleViewPopUpDiv").show();
		openViewRuleDialog(title,'ruleViewPopUpDiv');
	}
}

function openViewRuleDialog(title,divId){
	$("#"+divId).dialog({
		height : '540',
		width : '890px',
		show : 'slide',
		modal : true,
		resizable : false,
		title : title,
		autoOpen : true,
		close : function(){
			if(ruleViewFlag){
				groupRuleViewFlag = false;
				$("#ruleViewPopUpDiv").remove();
			}
			if(groupRuleViewFlag){
				$("#groupPagePopUpDiv").remove();
			}
		}
	});
	var isOpen = $('#'+divId).dialog( "isOpen" );
	if(!isOpen){
		$("#"+divId).dialog( "open" );
	}
}

/**
*Will display view rule icon according to the item selected from the auto complete list.
*/
function displayLabelForSelectedUmRule(text,list,labelId,descForInvalidUmRule,viewRuleIconId){
	var isValid = displayLabelForSelectedItem(text,list,labelId,descForInvalidUmRule);
	if(isValid){
		$('#'+viewRuleIconId).show();
	}else{
		$('#'+viewRuleIconId).hide();
	}
}

/*The function will add row for umRule and enable its autopopulation of hippa code values in textfield*/
function addRowForUmRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId){
	var pageName = $('#pageName').val();
	if(pageName == 'variableMappingPage'){
		//return addRowForEb03(tableID,cellId,cellName,text,isComingFromAddButton,labelId);
		//can enter max 99 um rules for variables also
		return addUmRuleForHeaderRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId);
	}else if(pageName == 'headerRuleMappingPage'){
		return addUmRuleForHeaderRule(tableID,cellId,cellName,text,isComingFromAddButton,labelId);
	}
}
/*Start : Added for Spider Mapping in EBX*/
function spiderPageLoad()
{
	document.getElementById('link3').style.visibility = 'hidden';
}
/*End : Added for Spider Mapping in EBX*/