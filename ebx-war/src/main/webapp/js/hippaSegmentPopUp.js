//This function will bind the onClick event to the um rule view icon
$('img[id^=viewRuleId]').live('click',function(event){
	var imageId = $(this).attr('id');
	var umRuleId = imageId.replace('viewRuleId','UMRuleId');
	umRuleId = $('#'+umRuleId).val();
	viewRuleSequenceInDetail(umRuleId,"/ajaxruleview.ajax");
});
/**
This function will fire an ajax call for search on hippa segment dialog
*/
function searchHippaCode(dis,url){
	if(dis.keyCode=='13'){

	var variableFormat = $("#variableFormat").val();
	var searchText = $("#searchText").val();
	var hippaSegmentName =$("#hippasegmentName").val();
	var spsFormat = new Array();
	if(pageName != 'variableMappingPage' && hippaSegmentName == "EB01"){					
		spsFormat = getSPSformat();			
	}
	
	var pageName = $('#pageName').val();
	if(pageName == 'variableMappingPage'){
		url = '../ajaxpopup.ajax';
	}else if(pageName == 'headerRuleMappingPage'){
		if(hippaSegmentName == 'UMRULE'){
			url = '../ajaxpopup.ajax';
		}else{
			url = url;
		}
	}
	
		$.ajax({
				url:url,
				type:"POST",
				dataType:"html",
				data: "hippaSegmentName="+hippaSegmentName+"&variableFormat="+variableFormat+"&searchText="+searchText+"&spsFormat="+spsFormat+"&pageName="+pageName,
				success: function(data){
						$("#hippaCodePopUpDiv").html(data);
						check(hippaSegmentName);	
				} 					
		});
		$("#searchText").value = searchText;
	}
}// end searchHippaCode

function searchAccum(dis,url){

	if(dis.keyCode=='13'){
	var searchText = $("#searchAccumName").val();
	var searchText1 = $("#searchAccumDesc").val();
	var hippaSegmentName =$("#accumName").val();
	var pageName = $('#pageName').val();
	$.ajax({
				url:url,
				type:"POST",
				dataType:"html",
				data: "hippaSegmentName="+hippaSegmentName+"&searchAccumName="+searchText+"&searchAccumDesc="+searchText1,
				success: function(data){
						$("#accumPopUpDiv").html(data);
						check(hippaSegmentName);	
				} 					
		});
		$("#searchAccumName").value = searchText;
		$("#searchAccumDesc").value = searchText1;
	}
}

/**
will open a modal dialog with the rsponse of ajax call.
This is used for showing the possible values of hippa codes.
*/
function popupDiv(hippaSegmentName, url){

		var spsFormat = new Array();
		if(hippaSegmentName == "EB01"){					
			spsFormat = getSPSformat();			
		}
		var variableFormat = "";
		variableFormat = $("#variableFormat").val();
		
		var providerArrangement = "";
		providerArrangement = $("#providerArrangement").val();
		
		var pageName = "";
		pageName = $("#pageName").val();
		
		var mappingItem = "";
		mappingItem = $("#mappingItem").val();
		$.ajax({
				url:url,
				dataType:"html",
				type:"POST",
				data: "hippaSegmentName="+hippaSegmentName+"&variableFormat="+variableFormat+"&spsFormat="+spsFormat+"&mappingItem="+mappingItem
						+"&providerArrangement="+providerArrangement+"&pageName="+pageName,
				success: function(data){
						$("#hippaCodePopUpDiv").html(data);
						$("#hippaCodePopUpDiv").dialog({
					            height:'350',
								minWidth:600,
								position:'center',
								resizable : 'false',
								zIndex: 9999,
								title:$("#hippasegmentName").val(),
					            modal: true,
					            open : function() {
		            							var newTitle = $("#hippasegmentName").val();
												if(newTitle != "UMRULE"){
													$('#hippaCodeTable tr td:first').css('width','30px');
												}
												if(newTitle.substring(0, 14) == "NOTE_TYPE_CODE"){
													newTitle = "Note Type";
												}
												if(newTitle == "UMRULE"){
													newTitle = "UM Rule";
												}
												if(newTitle.substring(0, 5) == 'III02'){
													newTitle = "III02";
												}
												if(newTitle == "2120_LOOP_NM1_MESSAGE_SEGMENT"){
													newTitle = "2120 Loop NM1 Message Segment";
												}
												$('#hippaCodePopUpDiv').dialog( "option" , "title" ,newTitle);
		        				}
					    });
						check(hippaSegmentName);			
				} 					
    	});
}//end popupDiv

function popupAccumDiv(hippaSegmentName, url){
		var spsFormat = new Array();
		if(hippaSegmentName == "EB01"){					
			spsFormat = getSPSformat();			
		}
		var variableFormat = "";
		variableFormat = $("#variableFormat").val();
		
		var mappingItem = "";
		mappingItem = $("#mappingItem").val();
		$.ajax({
				url:url,
				dataType:"html",
				type:"POST",
				data: "hippaSegmentName="+hippaSegmentName+"&variableFormat="+variableFormat+"&spsFormat="+spsFormat+"&mappingItem="+mappingItem,
				success: function(data){
						$("#accumPopUpDiv").html(data);
						$("#accumPopUpDiv").dialog({
					            height:'380',
					            width : '615',
								position:'center',
								resizable : 'false',
								zIndex: 9999,
								title:$("#accumName").val(),
					            modal: true,
					            open : function() {
		            							var newTitle = $("#accumName").val();
												if(newTitle != "UMRULE"){
													$('#hippaCodeTable tr td:first').css('width','30px');
												}
												if(newTitle == "NOTE_TYPE_CODE"){
													newTitle = "Note Type";
												}
												if(newTitle == "UMRULE"){
													newTitle = "UM Rule";
												}
												if(newTitle == "ACCUM"){
													newTitle = "Accumulator";
												}
												$('#accumPopUpDiv').dialog( "option" , "title" ,newTitle);
		        				}
					    });
						check(hippaSegmentName);			
				} 					
    	});	
}

/**The function will select the values in txtbx and mark those values in the pop up's onload as checked
*/
function check(hippaSegmentName){
	var selectedCode = "";
	if(hippaSegmentName == "EB01"){
		selectedCode = getTheValuesInTextBox("EB01Id","VALUE");
	}
	if(hippaSegmentName == "EB02"){
		selectedCode = getTheValuesInTextBox("EB02Id","VALUE");
	}
	if(hippaSegmentName == "EB03"){
		var pageName = $('#pageName').val();
		if(pageName == 'variableMappingPage'){
			selectedCode = getTheVarEB03ValuesInTextBox("EB03Id","VALUE");
		}else{
			selectedCode = getTheValuesInTextBox("EB03Id","VALUE");
		}
		
	}
	if(hippaSegmentName == "EB06"){
		selectedCode = getTheValuesInTextBox("EB06Id","VALUE");
	}
	if(hippaSegmentName == "EB09"){
		selectedCode = getTheValuesInTextBox("EB09Id","VALUE");
	}
	
	if(hippaSegmentName == "ACCUM"){
		selectedCode = getAccumValuesInTextBox("accumId","VALUE");
	}
	
	if(hippaSegmentName == "HSD01"){
		selectedCode = getTheValuesInTextBox("HSD01Id","VALUE");
	}
	if(hippaSegmentName == "HSD02"){
		selectedCode = getTheValuesInTextBox("HSD02Id","VALUE");
	}
	if(hippaSegmentName == "HSD03"){
		selectedCode = getTheValuesInTextBox("HSD03Id","VALUE");
	}
	if(hippaSegmentName == "HSD04"){
		selectedCode = getTheValuesInTextBox("HSD04Id","VALUE");
	}
	if(hippaSegmentName == "HSD05"){
		selectedCode = getTheValuesInTextBox("HSD05Id","VALUE");
	}
	if(hippaSegmentName == "HSD06"){
		selectedCode = getTheValuesInTextBox("HSD06Id","VALUE");
	}
	if(hippaSegmentName == "HSD07"){
		selectedCode = getTheValuesInTextBox("HSD07Id","VALUE");
	}
	if(hippaSegmentName == "HSD08"){
		selectedCode = getTheValuesInTextBox("HSD08Id","VALUE");
	}
	if(hippaSegmentName == "HSD08"){
		selectedCode = getTheValuesInTextBox("HSD08Id","VALUE");
	}
	if(hippaSegmentName == "III02"){
		selectedCode = getTheValuesInTextBox("III02Id","VALUE");
	}
	// changed for reference data values - removed check for NOTE TYPE CODE
	if(hippaSegmentName == "NOTE_TYPE_CODE"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID","VALUE");
	}
	if(hippaSegmentName == "UMRULE"){
		selectedCode = getTheValuesInTextBox("UMRuleId","VALUE");
	}
	if(hippaSegmentName == "startAge"){
		selectedCode = getAgeTierVarValuesInTextBox("startAge","VALUE");
	}
	if(hippaSegmentName == "endAge"){
		selectedCode = getAgeTierVarValuesInTextBox("endAge","VALUE");
	}
	// SSCR19537 - April 04 - Start
	if(hippaSegmentName == "III02_0"){
		selectedCode = getTheValuesInTextBox("III02Id0","VALUE");
	}
	if(hippaSegmentName == "III02_1"){
		selectedCode = getTheValuesInTextBox("III02Id1","VALUE");
	}
	if(hippaSegmentName == "III02_2"){
		selectedCode = getTheValuesInTextBox("III02Id2","VALUE");
	}
	if(hippaSegmentName == "III02_3"){
		selectedCode = getTheValuesInTextBox("III02Id3","VALUE");
	}
	if(hippaSegmentName == "III02_4"){
		selectedCode = getTheValuesInTextBox("III02Id4","VALUE");
	}
	if(hippaSegmentName == "III02_5"){
		selectedCode = getTheValuesInTextBox("III02Id5","VALUE");
	}
	if(hippaSegmentName == "III02_6"){
		selectedCode = getTheValuesInTextBox("III02Id6","VALUE");
	}
	if(hippaSegmentName == "III02_7"){
		selectedCode = getTheValuesInTextBox("III02Id7","VALUE");
	}
	if(hippaSegmentName == "III02_8"){
		selectedCode = getTheValuesInTextBox("III02Id8","VALUE");
	}
	if(hippaSegmentName == "III02_9"){
		selectedCode = getTheValuesInTextBox("III02Id9","VALUE");
	}
	if(hippaSegmentName == "III02_10"){
		selectedCode = getTheValuesInTextBox("III02Id10","VALUE");
	}
	if(hippaSegmentName == "III02_11"){
		selectedCode = getTheValuesInTextBox("III02Id11","VALUE");
	}
	if(hippaSegmentName == "III02_12"){
		selectedCode = getTheValuesInTextBox("III02Id12","VALUE");
	}
	if(hippaSegmentName == "III02_13"){
		selectedCode = getTheValuesInTextBox("III02Id13","VALUE");
	}
	if(hippaSegmentName == "III02_14"){
		selectedCode = getTheValuesInTextBox("III02Id14","VALUE");
	}
	if(hippaSegmentName == "III02_15"){
		selectedCode = getTheValuesInTextBox("III02Id15","VALUE");
	}
	
	
	if(hippaSegmentName == "NOTE_TYPE_CODE_0"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID0","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_1"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID1","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_2"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID2","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_3"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID3","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_4"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID4","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_5"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID5","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_6"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID6","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_7"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID7","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_8"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID8","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_9"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID9","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_10"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID10","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_11"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID11","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_12"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID12","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_13"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID13","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_14"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID14","VALUE");
	}
	if(hippaSegmentName == "NOTE_TYPE_CODE_15"){
		selectedCode = getTheValuesInTextBox("NOTETYPEID15","VALUE");
	}
		
	// SSCR19537 - April 04 - End
	
	//Format of hippacode value is value~description
	var hippaCodeValueArray = new Array();
	var splittedHippaCodeValueArray = splitHippaCodeValue();
	var hippalength = document.getElementsByName('hippaCode').length;
	if(hippalength > 0){
		for(var i=0;i<hippalength;i++) {
			hippaCodeValueArray[i] = splittedHippaCodeValueArray[i];
	}
	}else{
		hippaCodeValueArray[0] = splittedHippaCodeValueArray[0];
	}
	
	var selectedCodeLength = selectedCode.length;
	for(var i = 0;i<selectedCodeLength;i++){
		var index = $.inArray(selectedCode[i],hippaCodeValueArray);
		if(index > -1){
			document.getElementsByName('hippaCode')[index].checked=true;
		}
	}
}

function removeUncheckedCodeFromTxbBxArray(hippaSegmentId,arrayToBeReturned){
	var hippaCodeValueArray = new Array();
	hippaCodeValueArray = splitHippaCodeValue();//contains all the hippacode values in the pop up
	
	var valuesInTxBx = new Array();
	var descInTxBx = new Array();
	valuesInTxBx = getTheValuesInTextBox(hippaSegmentId,"VALUE");//contains the values in txtboxes.
	descInTxBx = getTheValuesInTextBox(hippaSegmentId,"DESC");//contains the labels for txtboxes.
	//check for all values in textbox ,whethr the value is unchecked ,if it is in the pop up.
	$.each(valuesInTxBx , function(i, value){
			var index = $.inArray(value,hippaCodeValueArray);
			if(index > -1){//the value is in popup
				var hippalength = document.getElementsByName('hippaCode').length;
				if(hippalength > 0){
					if(!document.getElementsByName('hippaCode')[index].checked){
						//remove from valuesInTxBx
							var valueToRemove = value;
							valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != valueToRemove; });

							var descToRemove = descInTxBx[i];
							descInTxBx = $.grep(descInTxBx, function(val) { return val != descToRemove; });
							
							//remove the corresponding label
							$('#'+hippaSegmentId+'Label'+i).text('');
					}
				}else{
					if(!document.getElementsByName('hippaCode').checked){
						//remove from valuesInTxBx
							var valueToRemove = valuesInTxBx[i];
							valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != valueToRemove; });
							var descToRemove = descInTxBx[i];
							descInTxBx = $.grep(descInTxBx, function(val) { return val != descToRemove; });
							
							//remove the corresponding label
							$('#'+hippaSegmentId+'Label'+i).text('');
					}
				}
			}
		}
	);
	if(arrayToBeReturned == "VALUE"){
		return valuesInTxBx;
	}else{
		return descInTxBx;
	}
}

/**Format of hippacode value is value~description.This function will return the values of all the 
hippacodes currently loaded in the popup.
*/
function splitHippaCodeValue(){
 	var hippaCodeValueArray = new Array();
 	var hippalength = document.getElementsByName('hippaCode').length;
	if(hippalength > 0){
		for(var i=0;i<hippalength;i++) {
			var value = document.getElementsByName('hippaCode')[i].value;
			value = value.split("~");
			hippaCodeValueArray[i] = value[0];
	}
	}else{
		var value = $('#hippaCode').val();
		if(value!=null && value.length > 0){
			value = value.split("~");
			hippaCodeValueArray[0] = value[0];
		}
	}
	return hippaCodeValueArray;
}

/**The function will return all the values in the textbox.
*/
function getTheValuesInTextBox(hippaSegmentId, arrayToBeReturned){ // The selected value in textbox.
	//get all values from the textbox.
	var selectedCodeValue = new Array();
	var selectedCodeDesc = new Array();
	if(hippaSegmentId == "EB03Id" || hippaSegmentId == "UMRuleId"){
		var table = "";
		if(hippaSegmentId == "EB03Id"){
			table = document.getElementById('ebCodesTable');
		}
		if(hippaSegmentId == "UMRuleId"){
			table = document.getElementById('umRuleTable');	
		}
		var tableLength = table.rows.length;
		var pageName = $('#pageName').val();
		if(pageName == 'headerRuleMappingPage'){
			tableLength = tableLength*3;
		}
		if(pageName == 'variableMappingPage' && hippaSegmentId == "UMRuleId"){
			tableLength = tableLength*3;
		}
		for(var i=0,j=0;i<tableLength;i++) {
			var val = $('#'+ hippaSegmentId +i).val();
			val =$.trim(val);
			if(val.length > 0){
				selectedCodeValue[j] = val;
				selectedCodeDesc[j] = $('#'+hippaSegmentId+'Label'+i).text();
				j++;
			}
		}//for
	}else{
			if(hippaSegmentId == "accumId"){
				var table = document.getElementById('accumTable');
				var accumsLength = table.rows.length;
				accumsLength = accumsLength-1;
				for(var i=0,j=0;i<accumsLength;i++) {
					var val = $('#accumId'+i).val();;
					val =$.trim(val);
					if(val.length > 0){
						selectedCodeValue[j] = val;
						selectedCodeDesc[j] = $('#'+hippaSegmentId+'Label'+i).text();
						j++;
					}
				}//for
			}
			else if (hippaSegmentId.indexOf("III02") >= 0){
				
				var iii02ValueFromTextBox = $('#'+hippaSegmentId).val();
				var iii02Values = iii02ValueFromTextBox.split(',');
				for(var i = 0; i < iii02Values.length; i++){
					var valuedesc = iii02Values[i];
					var splittedValuesForIII02ValDesc = valuedesc.split("(");
					selectedCodeValue[i] = splittedValuesForIII02ValDesc[0];
					selectedCodeDesc[i] = splittedValuesForIII02ValDesc[1];
				}
			}
			
			else{
				selectedCodeValue[0] = $('#'+hippaSegmentId).val();
				selectedCodeDesc[0] = $('#'+hippaSegmentId+'Label').text();
			}//else
	}//else
	
	
	if(arrayToBeReturned == "VALUE"){
		return selectedCodeValue;
	}else{
		return selectedCodeDesc;
	}
	
}//getTheValuesInTextBox ends here

/**This function will return the array of selected hippa code values or an array of slected hippacode description.
*/
function arrayOfSelectedHippaCodes(arrayToBeReturned){
		var selectedHippaCodeValueArray = new Array();
		var selectedHippaCodeDescriptionArray = new Array();
		var length =document.forms['hippaCodeSelectedValues'].hippaCode.length;
		
		if(length > 0){
			for(var i=0,j=0;i<length;i++) {
				if(document.forms['hippaCodeSelectedValues'].hippaCode[i].checked){
					var val =document.forms['hippaCodeSelectedValues'].hippaCode[i].value;
					val = val.split("~");
					selectedHippaCodeValueArray[j] = val[0];
					selectedHippaCodeDescriptionArray[j] = val[1];
					j++;
				}
			}
		}else{
			if(document.forms['hippaCodeSelectedValues'].hippaCode.checked){
				var value =$('#hippaCode').val();
				value = value.split("~");
				selectedHippaCodeValueArray[0] = value[0];
				selectedHippaCodeDescriptionArray[0] = value[1];
			}
		}
		if(arrayToBeReturned == "VALUE"){
			return selectedHippaCodeValueArray;
		}else
			return selectedHippaCodeDescriptionArray;
}
function arrayOfSelectedAccums(arrayToBeReturned){
		var selectedHippaCodeValueArray = new Array();
		var selectedHippaCodeDescriptionArray = new Array();
		var length =document.forms['accumsSelectedValues'].accumCode.length;
		if(length > 0){
			for(var i=0,j=0;i<length;i++) {
				if(document.forms['accumsSelectedValues'].accumCode[i].checked){
					var val =document.forms['accumsSelectedValues'].accumCode[i].value;
					val = val.split("~");
					selectedHippaCodeValueArray[j] = val[0];
					selectedHippaCodeDescriptionArray[j] = val[1];
					j++;
				}
			}
		}else{
			if(document.forms['accumsSelectedValues'].accumCode.checked){
				var value =$('#accumCode').val();
				value = value.split("~");
				selectedHippaCodeValueArray[0] = value[0];
				selectedHippaCodeDescriptionArray[0] = value[1];
			}
		}
		if(arrayToBeReturned == "VALUE"){
			return selectedHippaCodeValueArray;
		}else
			return selectedHippaCodeDescriptionArray;
}

//arrayOfSelectedHippaCodes ends here
function enableOrHideEb02(){
	//EB02 is a hidden field. This field will be displayed only when the  EB01 value entered is either C,G,DW,A or B
		if($('#EB01Id').val() == "C" ||$('#EB01Id').val() == "G" || $('#EB01Id').val() == "DW" ||$('#EB01Id').val() == "A"||$('#EB01Id').val() == "B") {
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
/**
The function will populate the values in popup to textfield.
*/

function selectAccumValues(){
	var selectedCode = "";
	setAccumValue('accumId');
	$('#accumPopUpDiv').dialog( "close" );
}

function selectedHippaCodeValues(){
	
		var isVariableMappingPage = false;
		if($('#pageName').val() == 'variableMappingPage'){
			isVariableMappingPage = true;
		}
	
		var selectedCode = "";
		if($('#hippasegmentName').val() == "EB01"){
			setHippaSegmentValue('EB01Id');
			checkIfProceduresToBeExcluded();
			if ($('#mappingItem').val() == "SPS"){
				enableOrHideEb02();
			}
		}
		if($('#hippasegmentName').val() == "EB02"){
			setHippaSegmentValue('EB02Id');
		}
		if($('#hippasegmentName').val() == "EB03"){
			var pageName = $('#pageName').val();
			
			if(pageName == 'variableMappingPage'){
				setHippaSegmentEB03Value('EB03Id');
				sensitiveBenefitCheck();
			}else{
				setHippaSegmentValue('EB03Id');
				sensitiveBenefitCheck();
			}
		}
		if($('#hippasegmentName').val() == "EB06"){
			setHippaSegmentValue('EB06Id');
		}
		if($('#hippasegmentName').val() == "EB09"){
			setHippaSegmentValue('EB09Id');
		}
		
		if($('#hippasegmentName').val() == "HSD01"){
			setHippaSegmentValue('HSD01Id');
		}
		if($('#hippasegmentName').val() == "HSD02"){
			setHippaSegmentValue('HSD02Id');
		}
		if($('#hippasegmentName').val() == "HSD03"){
			setHippaSegmentValue('HSD03Id');
		}
		if($('#hippasegmentName').val() == "HSD04"){
			setHippaSegmentValue('HSD04Id');
		}
		if($('#hippasegmentName').val() == "HSD05"){
			setHippaSegmentValue('HSD05Id');
		}
		if($('#hippasegmentName').val() == "HSD06"){
			setHippaSegmentValue('HSD06Id');
		}
		if($('#hippasegmentName').val() == "HSD07"){
			setHippaSegmentValue('HSD07Id');
		}
		if($('#hippasegmentName').val() == "HSD08"){
			setHippaSegmentValue('HSD08Id');
		}
		
		if($('#hippasegmentName').val() == "III02"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id');
			}else{
				setAgeTierValue('III02Id');	
			}

		}
		if($('#hippasegmentName').val() == "ACCUM"){
			setHippaSegmentValue('accumId');
		}
		if($('#hippasegmentName').val() == "ACCUMULATOR REFERENCE"){
			setHippaSegmentValue('accumSpsId');
		}
		// changed for reference data values - removed check for NOTE TYPE CODE
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE"){
			setHippaSegmentValue('NOTETYPEID');
		}
		if($('#hippasegmentName').val() == "UMRULE"){
			setHippaSegmentValue('UMRuleId');
		}
		
		// Added for NM1 Message Segment
		if($('#hippasegmentName').val() == "2120_LOOP_NM1_MESSAGE_SEGMENT"){
			setHippaSegmentValue('nm1MessageSegmentId');
		}						  
		
		
		$('#hippaCodePopUpDiv').dialog( "close" );
		
		// SSCR19537 April04 - Start
		if($('#hippasegmentName').val() == "III02_0"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id0');
			}else{
				setAgeTierValue('III02Id0');	
			}
		}
		if($('#hippasegmentName').val() == "III02_1"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id1');
			}else{
				setAgeTierValue('III02Id1');	
			}
		}
		if($('#hippasegmentName').val() == "III02_2"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id2');
			}else{
				setAgeTierValue('III02Id2');	
			}
		}
		if($('#hippasegmentName').val() == "III02_3"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id3');
			}else{
				setAgeTierValue('III02Id3');	
			}
		}
		if($('#hippasegmentName').val() == "III02_4"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id4');
			}else{
				setAgeTierValue('III02Id4');	
			}
		}
		if($('#hippasegmentName').val() == "III02_5"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id5');
			}else{
				setAgeTierValue('III02Id5');	
			}
		}
		if($('#hippasegmentName').val() == "III02_6"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id6');
			}else{
				setAgeTierValue('III02Id6');	
			}
		}
		if($('#hippasegmentName').val() == "III02_7"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id7');
			}else{
				setAgeTierValue('III02Id7');	
			}
		}
		if($('#hippasegmentName').val() == "III02_8"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id8');
			}else{
				setAgeTierValue('III02Id8');	
			}
		}
		if($('#hippasegmentName').val() == "III02_9"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id9');
			}else{
				setAgeTierValue('III02Id9');	
			}
		}
		if($('#hippasegmentName').val() == "III02_10"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id10');
			}else{
				setAgeTierValue('III02Id10');	
			}
		}
		if($('#hippasegmentName').val() == "III02_11"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id11');
			}else{
				setAgeTierValue('III02Id11');	
			}
		}
		if($('#hippasegmentName').val() == "III02_12"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id12');
			}else{
				setAgeTierValue('III02Id12');	
			}
		}
		if($('#hippasegmentName').val() == "III02_13"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id13');
			}else{
				setAgeTierValue('III02Id13');	
			}
		}
		if($('#hippasegmentName').val() == "III02_14"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id14');
			}else{
				setAgeTierValue('III02Id14');	
			}
		}
		if($('#hippasegmentName').val() == "III02_15"){
			if(isVariableMappingPage){
				setHippaSegmentValue('III02Id15');
			}else{
				setAgeTierValue('III02Id15');	
			}
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_0"){
			setHippaSegmentValue('NOTETYPEID0');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_1"){
			setHippaSegmentValue('NOTETYPEID1');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_2"){
			setHippaSegmentValue('NOTETYPEID2');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_3"){
			setHippaSegmentValue('NOTETYPEID3');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_4"){
			setHippaSegmentValue('NOTETYPEID4');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_5"){
			setHippaSegmentValue('NOTETYPEID5');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_6"){
			setHippaSegmentValue('NOTETYPEID6');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_7"){
			setHippaSegmentValue('NOTETYPEID7');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_8"){
			setHippaSegmentValue('NOTETYPEID8');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_9"){
			setHippaSegmentValue('NOTETYPEID9');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_10"){
			setHippaSegmentValue('NOTETYPEID10');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_11"){
			setHippaSegmentValue('NOTETYPEID11');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_12"){
			setHippaSegmentValue('NOTETYPEID12');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_13"){
			setHippaSegmentValue('NOTETYPEID13');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_14"){
			setHippaSegmentValue('NOTETYPEID14');
		}
		if($('#hippasegmentName').val() == "NOTE_TYPE_CODE_15"){
			setHippaSegmentValue('NOTETYPEID15');
		}
		// SSCR19537 April04 - End
		return true;
		
	}//selectedHippaCodeValues ends here
	
	/**
	The function will populate the selected values in the popup to the corresponding textboxes.
	*/
	function setHippaSegmentValue(hippaSegmentId){
			if($('#hippaCodeExists').val()=="false"){
				return false;
			}
			//Remove the unchecked value and get the values in txt box.
			var valuesInTxBx = new Array();
			valuesInTxBx = removeUncheckedCodeFromTxbBxArray(hippaSegmentId,"VALUE");
			var copyOfValuesInTxBx = new Array();
			copyOfValuesInTxBx = valuesInTxBx;
			
			//Remove the desc only for unchecked value and get the description or label for the value in txt boxes.
			var descForValuesInTxBx = new Array();
			descForValuesInTxBx = removeUncheckedCodeFromTxbBxArray(hippaSegmentId,"DESC");
			//Get the selected hippa code values from popup
			var hippaCodeValueArray = new Array();
			hippaCodeValueArray = arrayOfSelectedHippaCodes("VALUE");
			
			//Get the selected hippa code desc from popup		
			var hippaCodeDescArray =  new Array();
			hippaCodeDescArray = arrayOfSelectedHippaCodes("DESC");
			
			//add selected values to  values in textbox
			var length =hippaCodeValueArray.length;
			if(length > 0){
				for(var i=0;i<length;i++) {
						var val =hippaCodeValueArray[i];
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
					var val = hippaCodeValueArray[0];
					
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
				var pageName = $('#pageName').val();
				if(hippaSegmentId == "EB03Id"){
					if(pageName == 'headerRuleMappingPage'){					
						eb03PopulationOnHeaderRuleMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
					}else if(pageName == 'variableMappingPage'){
						eb03PopulationOnVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
					}
				}else if(hippaSegmentId == "accumId"){
					if(pageName == 'variableMappingPage'){
						accumPopulationOnVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
					}
				}else if(hippaSegmentId == "UMRuleId"){
					if(pageName == 'headerRuleMappingPage'){
						umRulePopulationOnHearderRuleMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
					}else if(pageName == 'variableMappingPage'){
						//reusing the logic for header rule um rule population. BXNI June 2012 Release
						//umRulePopulationOnContractVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
						umRulePopulationOnHearderRuleMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);

					}	
				}else{

						var text = valuesInTxBx[length-1];
						var index = $.inArray(text,hippaCodeValueArray);
						var desc = '';
						if(index == -1){
							index = $.inArray(text,copyOfValuesInTxBx);
							if(index > -1){
								desc = 	descForValuesInTxBx[index];
							}
						}else{
							desc = 	hippaCodeDescArray[index];
						}
						$('#'+hippaSegmentId+'Label').text(desc);
						if($('#'+hippaSegmentId+'Label').text() == invalidHippaCodeValue){
								$('#'+hippaSegmentId+'Label').addClass('invalid_hippacode_value');
						}else{
								$('#'+hippaSegmentId+'Label').removeClass('invalid_hippacode_value');
						}
						if(hippaSegmentId == "EB01Id" && text != "D"){
							$("#eb01ExtndMsgChkBox").attr("disabled","true");
						}else{
							$("#eb01ExtndMsgChkBox").removeAttr("disabled");
						}
						$('#'+hippaSegmentId).val(text);
				}
		}else if(length == 0){
			$("[id^='"+hippaSegmentId+"']").each(function () {
				var id = $(this).attr('id');
				var index = id.indexOf("Label");
				if(index > -1){
					$(this).text('');
				}else{
					$(this).val('');
					if(hippaSegmentId=='UMRuleId'){
						id = id.replace('UMRuleId','viewRuleId');
						$('#'+id).hide();
					}
				}
	        });//end of each

		}	
	}
	
/********************************************BXNI June 2012 Release******************************************************************/
	function setAccumValue(hippaSegmentId){
			if($('#hippaCodeExists').val()=="false"){
				return false;
			}
			//Remove the unchecked value and get the values in txt box.
			var valuesInTxBx = new Array();
			valuesInTxBx = removeUncheckedAccumFromTxbBxArray(hippaSegmentId,"VALUE");

			var copyOfValuesInTxBx = new Array();
			copyOfValuesInTxBx = valuesInTxBx;
			
			//Remove the desc only for unchecked value and get the description or label for the value in txt boxes.
			var descForValuesInTxBx = new Array();
			descForValuesInTxBx = removeUncheckedAccumFromTxbBxArray(hippaSegmentId,"DESC");
			
			//Get the selected hippa code values from popup
			var hippaCodeValueArray = new Array();
			hippaCodeValueArray = arrayOfSelectedAccums("VALUE");

			//Get the selected hippa code desc from popup		
			var hippaCodeDescArray =  new Array();
			hippaCodeDescArray = arrayOfSelectedAccums("DESC");
			
			//add selected values to  values in textbox
			var length =hippaCodeValueArray.length;
			if(length > 0){
				for(var i=0;i<length;i++) {
						var val =hippaCodeValueArray[i];
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
					var val = hippaCodeValueArray[0];
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
				var pageName = $('#pageName').val();
				if(hippaSegmentId == "accumId"){
					if(pageName == 'variableMappingPage'){
						accumPopulationOnVariableMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
					}
				}
			}else if(length == 0){
				$("[id^='"+hippaSegmentId+"']").each(function () {
					var id = $(this).attr('id');
					var index = id.indexOf("Label");
					if(index > -1){
						$(this).text('');
					}else{
						$(this).val('');				
					}
			    });//end of each
		}
	//setHippaSegmentValue ends here
	}
	
function removeUncheckedAccumFromTxbBxArray(hippaSegmentId,arrayToBeReturned){
	var splittedHippaCodeValueArray = new Array();
	splittedHippaCodeValueArray = splitHippaCodeValue();//contains all the accum values in the pop up
	
	var valuesInTxBx = new Array();
	var descInTxBx = new Array();
	valuesInTxBx = getAccumValuesInTextBox(hippaSegmentId,"VALUE");//contains the values in txtboxes.
	descInTxBx = getAccumValuesInTextBox(hippaSegmentId,"DESC");//contains the labels for txtboxes.	

	//check for all values in textbox ,whethr the value is unchecked ,if it is in the pop up.
	$.each(valuesInTxBx , function(i, value){
			//var index = $.inArray(value,splittedRefDataValueArray);
			var index = $.inArray(valuesInTxBx[i],splittedHippaCodeValueArray);
			if(index > -1){//the value is in popup
				var hippalength = document.getElementsByName('hippaCode').length;
				if(hippalength > 0){
					if(!document.getElementsByName('hippaCode')[index].checked){					
					   //remove from valuesInTxBx
						valuesInTxBx[i] = "ToRemove";
						descInTxBx[i] = "ToRemove";							
						//remove the corresponding label
						if (hippaSegmentId == "accumId") {
							$('#'+hippaSegmentId+'Label'+i).text('');
						}
					}
				}else{
					if(!document.getElementsByName('hippaCode').checked){
						//remove from valuesInTxBx
						valuesInTxBx[i] = "ToRemove";
						descInTxBx[i] = "ToRemove";
						var descToRemove = descInTxBx[i];
						
						//remove the corresponding label
						if (hippaSegmentId == "accumId") {
							$('#'+hippaSegmentId+'Label'+i).text('');
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
/**The function will return all the values in the textbox.
*/
function getAccumValuesInTextBox(hippaSegmentId, arrayToBeReturned){ // The selected value in textbox.
	//get all values from the textbox.
	var selectedCodeValue = new Array();
	var selectedCodeDesc = new Array();

	var table = "";
	if(hippaSegmentId == "accumId"){
		table = document.getElementById('accumTable');
	}

	var tableLength = table.rows.length;

	if(hippaSegmentId == "accumId"){
		tableLength = tableLength*3;
	}
	
	for(var i=0,j=0;i<tableLength;i++) {
		var val = $('#'+ hippaSegmentId +i).val();
		val =$.trim(val);
		if(val.length > 0){
			selectedCodeValue[j] = val;
			if (hippaSegmentId == "accumId") {
				selectedCodeDesc[j] = $('#'+hippaSegmentId+'Label'+i).text();
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


function popupAgeTierVariableDiv(ageTierName, url){
	var variableFormat = "";
	
	variableIdHidden = $("#variableIdHidden").val();
	$.ajax({
			url:url,
			dataType:"html",
			type:"POST",
			data: "ageTierName="+ageTierName+"&variableIdHidden="+variableIdHidden,
			success: function(data){
					$("#popupAgeTierVarDiv").html(data);
					$("#popupAgeTierVarDiv").dialog({
				            height:'380',
				            width : '615',
							position:'center',
							resizable : 'false',
							zIndex: 9999,
							title:$("#ageTierName").val(),
				            modal: true,
				            open : function() {
	            							var newTitle = "Age Variable";
											$('#popupAgeTierVarDiv').dialog( "option" , "title" ,newTitle);
	        				}
				    });
					check(ageTierName);			
			} 					
	});	
}

function searchAgeTierVariable(dis,url){

	if(dis.keyCode=='13'){
	var searchText = $("#searchText").val();
	var ageTierName =$("#ageTierName").val();
	var pageName = $('#pageName').val();
	var variableIdHidden = $("#variableIdHidden").val();
	$.ajax({
				url:url,
				type:"POST",
				dataType:"html",
				data: "ageTierName="+ageTierName+"&searchText="+searchText+"&variableIdHidden="+variableIdHidden,
				success: function(data){
						$("#popupAgeTierVarDiv").html(data);
						check(ageTierName);	
				} 					
		});
		$("#searchText").value = searchText;
	}
}

function selectedAgeTierVariableValue(){
	var selectedCode = "";
	if($('#ageTierName').val() == "startAge"){
		setAgeTierValue('startAge');
	}
	if($('#ageTierName').val() == "endAge"){
		setAgeTierValue('endAge');
	}
	$('#popupAgeTierVarDiv').dialog( "close" );
	autoPopulateHSD01();
}
		
function setAgeTierValue(hippaSegmentId){
	if($('#hippaCodeExists').val()=="false"){
		return false;
	}
	//Remove the unchecked value and get the values in txt box.
	var valuesInTxBx = new Array();
	valuesInTxBx = removeUncheckedAgeTierVarFromTxbBxArray(hippaSegmentId,"VALUE");
	var copyOfValuesInTxBx = new Array();
	copyOfValuesInTxBx = valuesInTxBx;
	
	//Remove the desc only for unchecked value and get the description or label for the value in txt boxes.
	var descForValuesInTxBx = new Array();
	//Get the selected hippa code values from popup
	var hippaCodeValueArray = new Array();
	//Description Array
	var hippaCodeDescArray =  new Array();

	if (hippaSegmentId.indexOf("III02") >= 0){
		hippaCodeValueArray = arrayOfSelectedHippaCodes("VALUE");
		hippaCodeDescArray = arrayOfSelectedHippaCodes("DESC");
		
	}else{
		hippaCodeValueArray = arrayOfSelectedAgeTierVariables("VALUE");
	}
		

	//add selected values to  values in textbox
	var length =hippaCodeValueArray.length;
	if(length > 0){
		for(var i=0;i<length;i++) {
				var val =hippaCodeValueArray[i];
				var index = $.inArray(val,valuesInTxBx);
				if(index < 0){ //have to add this to listOfValuesFromTxtBx
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
			var val = hippaCodeValueArray[0];
			
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
	if (hippaSegmentId.indexOf("III02") >= 0){
		var valueToBePopulated = '';
		var arrayLength = hippaCodeValueArray.length;
		if(arrayLength <= 3){
		for(var i = 0; i < arrayLength ; i++){
			
				if(valueToBePopulated == ''){
					valueToBePopulated = hippaCodeValueArray[i] + "(" + hippaCodeDescArray[i]+")";
				}else{
					valueToBePopulated =  valueToBePopulated +","+ hippaCodeValueArray[i] + "(" + hippaCodeDescArray[i]+")";
				}
			
		}$('#'+hippaSegmentId).val(valueToBePopulated);
		}else{
			var message = "More than 3 III02 cannot be mapped to an EB03";			
			addErrorToNotificationTray(message);
			openTrayNotification();	
		}
		
	}
	else{
		length =valuesInTxBx.length;
		var i = 0;
		var textToBeAdded = "";
		if(length > 0){
		for(i = 0; i < length; i++){
			valuesInTxBx[i] =$.trim(valuesInTxBx[i]);
			if(textToBeAdded == "" && valuesInTxBx[i] != "" ){
				textToBeAdded = valuesInTxBx[i];
			}else if(valuesInTxBx[i] != ""){
				textToBeAdded = textToBeAdded+", "+valuesInTxBx[i];
			}
			
		}$('#'+hippaSegmentId).val(textToBeAdded);
		}
		else if(length == 0){
			$("[id^='"+hippaSegmentId+"']").each(function () {
				var id = $(this).attr('id');
				var index = id.indexOf("Label");
				if(index > -1){
					$(this).text('');
				}else{
					$(this).val('');
				}
		    });
	}
	}
}

function removeUncheckedAgeTierVarFromTxbBxArray(hippaSegmentId,arrayToBeReturned){
var splittedHippaCodeValueArray = new Array();
splittedHippaCodeValueArray = splitHippaCodeValue();//contains all the accum values in the pop up

var valuesInTxBx = new Array();
var descInTxBx = new Array();

if(hippaSegmentId.indexOf("III02") >= 0){
	valuesInTxBx = getIII02ValuesInTextBox(hippaSegmentId,"VALUE");
}
else{
	valuesInTxBx = getAgeTierVarValuesInTextBox(hippaSegmentId,"VALUE");//contains the values in txtboxes.
}

//check for all values in textbox ,whethr the value is unchecked ,if it is in the pop up.
$.each(valuesInTxBx , function(i, value){
	//var index = $.inArray(value,splittedRefDataValueArray);
	var index = $.inArray(valuesInTxBx[i],splittedHippaCodeValueArray);
	if(index > -1){//the value is in popup
		var hippalength = document.getElementsByName('hippaCode').length;
		if(hippalength > 0){
			if(!document.getElementsByName('hippaCode')[index].checked){					
			   //remove from valuesInTxBx
				valuesInTxBx[i] = "ToRemove";
				//remove the corresponding label
			}
		}else{
			if(!document.getElementsByName('hippaCode').checked){
				//remove from valuesInTxBx
				valuesInTxBx[i] = "ToRemove";
				
			}
		}
	}
}
);
valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != "ToRemove"; });
return valuesInTxBx;
}
/**The function will return all the values in the textbox.
*/
function getAgeTierVarValuesInTextBox(hippaSegmentId, arrayToBeReturned){ // The selected value in textbox.
	//get all values from the textbox.
	var selectedCodeValue = new Array();
	var i = 0;
	selectedCodeValue = $('#'+hippaSegmentId).val();
	selectedCodeValue = selectedCodeValue.split(",");
	var length = selectedCodeValue.length;
	for(i = 0; i < length; i++){
	selectedCodeValue[i] = $.trim(selectedCodeValue[i]);
	}
	return selectedCodeValue;
}

/**The function will return all the values in the textbox.
*/
function getIII02ValuesInTextBox(hippaSegmentId, arrayToBeReturned){ // The selected value in textbox.
	//get all values from the textbox.
	var selectedCodeValue = new Array();
	var i = 0;
	selectedCodeValue = $('#'+hippaSegmentId).val();
	selectedCodeValue = selectedCodeValue.split(",");
	var length = selectedCodeValue.length;
	for(i = 0; i < length; i++){
		
	var val1 = $.trim(selectedCodeValue[i]);
	var valDesc = val1.split("(");
	selectedCodeValue[i] = valDesc[0];
	}
	return selectedCodeValue;
}


function arrayOfSelectedAgeTierVariables(){
	var selectedHippaCodeValueArray = new Array();
	var length =document.forms['ageTierSelectedValues'].hippaCode.length;
	
	if(length > 0){
		for(var i=0,j=0;i<length;i++) {
			if(document.forms['ageTierSelectedValues'].hippaCode[i].checked){
				var val =document.forms['ageTierSelectedValues'].hippaCode[i].value;
				val = val.split("~");
				selectedHippaCodeValueArray[j] = val[0];
				j++;
			}
		}
	}else{
		if(document.forms['ageTierSelectedValues'].hippaCode.checked){
			var value =$('#hippaCode').val();
			value = value.split("~");
			selectedHippaCodeValueArray[0] = value[0];
		}
	}
	return selectedHippaCodeValueArray;
}

function getTheVarEB03ValuesInTextBox(hippaSegmentId,arrayToBeReturned){
		//get all values from the textbox.
		var selectedCodeValue = new Array();
		var selectedCodeDesc = new Array();

		var table = "";
		if(hippaSegmentId == "EB03Id"){
			table = document.getElementById('ebCodesTable');
		}

		var tableLength = table.rows.length;
		if(hippaSegmentId == "EB03Id"){
			tableLength = tableLength*4;
		}
		for(var i=0,j=0;i<tableLength;i++) {
			var val = $('#'+ hippaSegmentId +i).val();
			val =$.trim(val);
			if(val.length > 0){
				selectedCodeValue[j] = val;
				if (hippaSegmentId == "EB03Id") {
					selectedCodeDesc[j] = $('#'+hippaSegmentId+'Label'+i).text();
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
function setHippaSegmentEB03Value(hippaSegmentId){
	if($('#hippaCodeExists').val()=="false"){
		return false;
	}
	//Remove the unchecked value and get the values in txt box.
	var valuesInTxBx = new Array();
	valuesInTxBx = removeUncheckedEB03FromTxbBxArray(hippaSegmentId,"VALUE");

	var copyOfValuesInTxBx = new Array();
	copyOfValuesInTxBx = valuesInTxBx;
	
	//Remove the desc only for unchecked value and get the description or label for the value in txt boxes.
	var descForValuesInTxBx = new Array();
	descForValuesInTxBx = removeUncheckedEB03FromTxbBxArray(hippaSegmentId,"DESC");
	
	//Get the selected hippa code values from popup
	var hippaCodeValueArray = new Array();
	hippaCodeValueArray = arrayOfSelectedHippaCodes("VALUE");

	//Get the selected hippa code desc from popup		
	var hippaCodeDescArray =  new Array();
	hippaCodeDescArray = arrayOfSelectedHippaCodes("DESC");
	
	//add selected values to  values in textbox
	var length =hippaCodeValueArray.length;
	if(length > 0){
		for(var i=0;i<length;i++) {
				var val =hippaCodeValueArray[i];
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
			var val = hippaCodeValueArray[0];
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
		var pageName = $('#pageName').val();
		if(hippaSegmentId == "EB03Id"){
			if(pageName == 'variableMappingPage'){
				eb03PopulationOnVarMappingPage(hippaSegmentId,length,valuesInTxBx,hippaCodeValueArray,hippaCodeDescArray,copyOfValuesInTxBx,descForValuesInTxBx);
			}
		}
	}else if(length == 0){
		$("[id^='"+hippaSegmentId+"']").each(function () {
			var id = $(this).attr('id');
			var index = id.indexOf("Label");
			if(index > -1){
				$(this).text('');
			}else{
				$(this).val('');				
			}
	    });//end of each
}
//setHippaSegmentValue ends here
}

function removeUncheckedEB03FromTxbBxArray(hippaSegmentId,arrayToBeReturned){
	var hippaCodeValueArray = new Array();
	hippaCodeValueArray = splitHippaCodeValue();//contains all the hippacode values in the pop up
	
	var valuesInTxBx = new Array();
	var descInTxBx = new Array();
	valuesInTxBx = getTheVarEB03ValuesInTextBox(hippaSegmentId,"VALUE");//contains the values in txtboxes.
	descInTxBx = getTheVarEB03ValuesInTextBox(hippaSegmentId,"DESC");//contains the labels for txtboxes.
	//check for all values in textbox ,whethr the value is unchecked ,if it is in the pop up.
	$.each(valuesInTxBx , function(i, value){
			var index = $.inArray(value,hippaCodeValueArray);
			if(index > -1){//the value is in popup
				var hippalength = document.getElementsByName('hippaCode').length;
				if(hippalength > 0){
					if(!document.getElementsByName('hippaCode')[index].checked){
						//remove from valuesInTxBx
							var valueToRemove = value;
							valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != valueToRemove; });

							var descToRemove = descInTxBx[i];
							descInTxBx = $.grep(descInTxBx, function(val) { return val != descToRemove; });
							
							//remove the corresponding label
							$('#'+hippaSegmentId+'Label'+i).text('');
					}
				}else{
					if(!document.getElementsByName('hippaCode').checked){
						//remove from valuesInTxBx
							var valueToRemove = valuesInTxBx[i];
							valuesInTxBx = $.grep(valuesInTxBx, function(val) { return val != valueToRemove; });
							var descToRemove = descInTxBx[i];
							descInTxBx = $.grep(descInTxBx, function(val) { return val != descToRemove; });
							
							//remove the corresponding label
							$('#'+hippaSegmentId+'Label'+i).text('');
					}
				}
			}
	}
	);
	if(arrayToBeReturned == "VALUE"){
		return valuesInTxBx;
	}else{
		return descInTxBx;
	}
}
/********************************************BXNI June 2012 Release******************************************************************/
