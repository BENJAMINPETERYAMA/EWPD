
var flagHSD02Numeric=false;
var ebMap = false;
function checkHSD02Validation(){
	
	var hsd01Error = "HSD02 and HSD05 are mandatory fields";
	var ebSegmentError = "EB Segment present along with HSD02 Limitaion values";
	var dupError = "Duplicate HSD02 with "
	var duplicateList = checkDuplicateHSD02();
	var dupListString ="";
	var hsd01Validation = checkHSD01Validation();
	var isEBSegmentEmp = isEBSegmentEmpty();
	if (!isHSD02LimitationValueEmpty()){
		if(!isHSD02InvalidValue()){
		if(isEBSegmentEmp){
			addErrorToNotificationTray(ebSegmentError);
			if(duplicateList.length > 0 ){
				duplicateList.forEach(function(element,index){
					dupListString = dupListString.concat(element+', ');
				});
				if(duplicateList.length==1){
					addErrorToNotificationTray(ebSegmentError + '<br>'+'#'+dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' is present. ');
					if(hsd01Validation.length > 0){
					addErrorToNotificationTray(ebSegmentError + '<br>'+'#'+dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' is present. ' + '<br>'+'#'+ hsd01Validation);
					}
				}else{
					addErrorToNotificationTray(ebSegmentError + '<br>'+'#'+dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' are present. ');
					if(hsd01Validation.length > 0){
					addErrorToNotificationTray(ebSegmentError + '<br>'+'#'+dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' are present. ' + '<br>'+'#'+ hsd01Validation);
					}
				}
			
			}else if(hsd01Validation.length > 0){
				addErrorToNotificationTray(ebSegmentError+'<br>'+'#'+hsd01Validation)
			}
			openTrayNotification();
			return false;
		}

		if(duplicateList.length > 0 ){
				duplicateList.forEach(function(element,index){
					dupListString = dupListString.concat(element+', ');
				});
				if(duplicateList.length==1){
					addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' is present. ');
					if(hsd01Validation.length > 0){
						addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' is present. ' + '<br>'+'#'+ hsd01Validation);
					}
				}else{
					addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' are present. ');
					if(hsd01Validation.length > 0){
						addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' are present. ' + '<br>'+'#'+ hsd01Validation);
					}
				}
			openTrayNotification();
			return false;
			}
		else if(hsd01Validation.length > 0){
				addErrorToNotificationTray(hsd01Validation);
				openTrayNotification();
				return false;
			}
		else if(ebMap){
			return false;
		}
		return true;
		}
		else{
			addErrorToNotificationTray("Mapping is not allowed for the Invalid HIPAA Variable for HSD02");
			openTrayNotification();
			exit();
		}
	}
		else{
		if(duplicateList.length > 0 ){
				duplicateList.forEach(function(element,index){
					dupListString = dupListString.concat(element+', ');
				});
				if(duplicateList.length==1){
					addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' is present. ');
					if(hsd01Validation.length > 0){
						addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' is present. ' + '<br>'+'#'+ hsd01Validation);
					}
				}else{
					addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' are present. ');
					if(hsd01Validation.length > 0){
						addErrorToNotificationTray(dupError + dupListString.substr(0,dupListString.lastIndexOf(',')) + ' are present. ' + '<br>'+'#'+ hsd01Validation);
					}
				}
			openTrayNotification();
			return false;
			}
		else if(hsd01Validation.length > 0){
				addErrorToNotificationTray(hsd01Validation);
				openTrayNotification();
				return false;
			}	
	}
	if((($.trim($('#EB01Id').val()) == "") && 
			($.trim($('#EB02Id').val()) == "") && 
			($.trim($('#EB03Id0').val()) == "") && 
			($.trim($('#EB03Id1').val()) == "") && 
			($.trim($('#EB03Id2').val()) == "") && 
			($.trim($('#EB03Id3').val()) == "") && 	
			($.trim($('#EB03Id4').val()) == "") && 
			($.trim($('#EB06Id').val()) == "") && 
			($.trim($('#EB09Id').val()) == "") )){
		return true;
	}
	if(!ebMap)
		return true;
	else
		return false;
	
}

//HSD01 = VS & HSD05 = 31, the eBX needs to do mandatory validation 
function checkHSD01Validation(){
	var errorMsg="";
	var errorMsg1 = "";
	if( $('#HSD01Id').val() == 'VS'){
		if(($('#HSD02Id').val()=="")){
			if(isHSD02LimitationValueEmpty()){
				errorMsg = 'HSD02 is a mandatory field';
			}
		}
		if(($('#HSD05Id').val()=="")){
			errorMsg1 = 'HSD05 is a mandatory field';
		}

		else if($.trim($('#HSD05Id').val()) == 31 && !isAccumulatorPresent()){
				errorMsg1 = "Accumulator Mandatory field";
		}
	}
	if($.trim(errorMsg) != ""){
		if($.trim(errorMsg1)!=""){
			return errorMsg + '<br>' + '#' + errorMsg1;
		}
		return errorMsg;
	}
	else if($.trim(errorMsg1)!=""){
		return errorMsg1;
	}
	else{
		return "";
	}
}

function isAccumulatorPresent(){
	for(var i=0;i<11;i++){
		if(typeof $('#accumId'+i) != "undefined")
		if($('#accumId'+i).val() != "" && !(typeof $('#accumId'+i).val() === "undefined")){
			return true;
		}
	}
	return false;
}
function isEBSegmentEmpty(){
	var uiEBSegmentVal = uiEBSegmentValues();
	//if(!uiEBSegmentVal)
	var movfrwd = ebMapAssocDetails();
	var checkEBSegmentHSD02LimtationValues = checkEBSegmentHSD02LimtationVal();
	return (uiEBSegmentVal && checkEBSegmentHSD02LimtationValues);
}

function uiEBSegmentValues(){
	return  ($.trim($('#EB01Id').val()) == "") && 
	($.trim($('#EB02Id').val()) == "") && 
	($.trim($('#EB03Id0').val()) == "") && 
	($.trim($('#EB03Id1').val()) == "") && 
	($.trim($('#EB03Id2').val()) == "") && 
	($.trim($('#EB03Id3').val()) == "") && 	
	($.trim($('#EB03Id4').val()) == "") && 
	($.trim($('#EB06Id').val()) == "") && 
	($.trim($('#EB09Id').val()) == "") ;
}
function isHSD02LimitationValueEmpty(){
	return (($.trim($('#HSD02-01Id').val()) == "") && 
			($.trim($('#HSD02-02Id').val()) == "") && 
			($.trim($('#HSD02-03Id').val()) == "") && 
			($.trim($('#HSD02-04Id').val()) == "") && 
			($.trim($('#HSD02-05Id').val()) == "") && 
			($.trim($('#HSD02-06Id').val()) == "") );
}

function isHSD02InvalidValue(){
	
	//var lbltext = document.getElementById('HSD02-02IdLabel').innerHTML;
	return (($.trim($('#HSD02-01IdLabel').text()) == "Invalid HIPAA Segment Value!") || 
			($.trim($('#HSD02-02IdLabel').text()) == "Invalid HIPAA Segment Value!") || 
			($.trim($('#HSD02-03IdLabel').text()) == "Invalid HIPAA Segment Value!") || 
			($.trim($('#HSD02-04IdLabel').text()) == "Invalid HIPAA Segment Value!") || 
			($.trim($('#HSD02-05IdLabel').text()) == "Invalid HIPAA Segment Value!") || 
			($.trim($('#HSD02-06IdLabel').text()) == "Invalid HIPAA Segment Value!") );
}

function ebMapAssocDetails(){
	var varId = $("#variableIdHidden").val();
	$.ajax({
		url: "../ajaxautocompletelistcreatepage.ajax",
		async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
		dataType: "json",
		type:"POST",
		data: "key="+varId+ "&name=VARIABLEID",
		success: function(resp){
			if(resp.ebMappingAssocDetails != "[]"){
				var errMsg = "EB Mapping already Associated with "+ resp.ebMappingAssocDetails;
					addErrorToNotificationTray(errMsg);
					openTrayNotification();
				ebMap = true;
				return false;
			}
		}
	});
	return true;
}
function checkEBSegmentHSD02LimtationVal(){
	var list =new Array();
	var issueVar = new Array();
	for(var i =1;i<7;i++){ 
		if($.trim($('#HSD02-0'+i+'Id').val()) != "")
		list.push($.trim($('#HSD02-0'+i+'Id').val()));
	}
	
	issueVar = callAjaxData(list);
	if(issueVar!=""){
		var errMsg = "EB Segment present in ["+issueVar.toString()+"]";
		addErrorToNotificationTray(errMsg);
		openTrayNotification();
		exit();//I know this is going to abruptly terminate the script and there is no function defined exit() in java script. I'm not an idiot.
		return false;
	}
	
	return false;
}

function callAjaxData(list){
	var issueVar = new Array();
	for(var i =0;i<list.length;i++){
		var varId = list[i];
		$.ajax({
			url: "../ajaxautocompletelistcreatepage.ajax",
			async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
			dataType: "json",
			type:"POST",
			data: "key="+varId+ "&name=VARIABLE",
			success: function(resp){
				if(resp.isEBSegmentPresent){
					issueVar.push(resp.variableId);
				}
			}
		});
	}
	
	return issueVar;
	
}


function checkDuplicateHSD02(){
	var list =new Array();
	var duplicate = new Array();
	var HSD02addlst = new Array();
	var varblId = $("#variableIdHidden").val();
	for(var i =1;i<7;i++){ 
		list.push($.trim($('#HSD02-0'+i+'Id').val()));
		if($.trim($('#HSD02-0'+i+'Id').val())!=""){
			HSD02addlst.push($.trim($('#HSD02-0'+i+'Id').val()));
		}
	}
	var hsd02Val = $.trim($('#HSD02Id').val());
	var pattern = /[^0-9]/g;
	var result = hsd02Val.match(pattern);
	if(null==result){
		result="";
	}
	if(hsd02Val.length>0 && HSD02addlst.length>0){
		addErrorToNotificationTray("Please enter Either of the HSD02 values");
		openTrayNotification();
		exit();//I know this is going to abruptly terminate the script and there is no function defined exit() in java script. I'm not an idiot.

	}
	for(var i =1;i<7;i++){ 
		if(varblId==($.trim($('#HSD02-0'+i+'Id').val()))){
			addErrorToNotificationTray("Mapping is not allowed for the same Variable");
			openTrayNotification();
			exit();
		}
	}
	list.forEach(function(element,index){
		if (list.indexOf(element, index + 1) > -1) {
			if(duplicate.indexOf(element) === -1){
				if(element != "")
				duplicate.push(element);
			}
		}
	});
	return duplicate;
}

function addRowForVariableHSD02(tableID,cellId,cellName,text,isComingFromAddButton,labelId,linkId){
	var cellNumberToShow = getNextCellIdToShow();
	var initialVariable = "HSD02-0";
	//alert(cellNumberToShow);
	var contextPath = $('#contextPath').val();
	var table = document.getElementById(tableID);
	var rowCount = 0;
	var idCount = -1;
	var tbody ="";
	var trClass= "";
	var autoSearch= "";
	var desc ="";

	var hsd02orig = document.getElementById(cellId);
	//checking if the value is Numeric
	toggleInputCell(initialVariable,cellNumberToShow);
	toggleAddButton(initialVariable,cellNumberToShow+1); 
	
	
	
	if(tableID == 'hsdTable'){
			tbody = document.getElementById("hsdTbody");
			rowCount = $('#hsdTable >tbody >tr').length;
			if(cellNumberToShow >=6){
				$('#hsd02ddButtonforVariable').hide();
			}
			
		}
}

function toggleAddButton(initialVariable,firstBlockNumber){
		$('#'+initialVariable+firstBlockNumber+'Id-td').attr("align", "right");

		$('#'+initialVariable+firstBlockNumber+'Id-td').attr("style", "padding-right: 7px;");		
		$('#hsd02ddButtonforVariable-0'+firstBlockNumber).show();
		
}



function hideAllAdditionalFields(){
	var firstBlockNumber = 2;
	var initialVariable = "HSD02-0";
	while(firstBlockNumber<7){
		//alert($("#"+initialVariable+firstBlockNumber+"Id").attr('type'));
		//if($("#"+initialVariable+firstBlockNumber+"Id").attr('type') == "hidden"){
			$('#hsd02ddButtonforVariable-0'+firstBlockNumber).hide();
			$("#HSD02-0"+firstBlockNumber+"Id").hide();
			document.getElementById(initialVariable+firstBlockNumber+"Id").type="hidden";
			//$("#"+initialVariable+nextBlockNumber+"Id").attr("type","hidden");
		firstBlockNumber++;
	}
}
function getNextCellIdToShow(){
	var initialVariable = "HSD02-0";
	// we are Selecting 2 because HSD02-0"2"Id we increment 
	var firstBlockNumber = 2;
	while(firstBlockNumber<7){
		//alert($("#"+initialVariable+firstBlockNumber+"Id").attr('type'));
		if($("#"+initialVariable+firstBlockNumber+"Id").attr('type') == "hidden"){
			return firstBlockNumber;
		}
		
		firstBlockNumber++;
	}
}

function toggleInputCell(initialVariable,firstBlockNumber){
	if(firstBlockNumber<=6){
		$("#hsd02ddButtonforVariable-0"+firstBlockNumber).hide();
		$('#'+initialVariable+firstBlockNumber+'Id-td').removeAttr('style');
		$('#'+initialVariable+firstBlockNumber+'Id-td').removeAttr('align');
		$("#"+initialVariable+firstBlockNumber+"Id").show();
		document.getElementById(initialVariable+firstBlockNumber+"Id").type="text";
		nextBlockNumber = firstBlockNumber+1;
		if(!nextBlockNumber>=6)
		$("#"+initialVariable+nextBlockNumber+"Id").attr("type","text");
	}
	
}

$(document).ready(function(){
	
	var hsd02Val = $('#HSD02Id').val();
	var hsd02Desc = $('#HSD02IdLabel').text();
	if(hsd02Val.length > 3){
		moveHSD02Values();
	}
	/*else if(hsd02Val.length < 1){
		moveHSD02Values();
	}
	else{
		
	}*/
});

function moveHSD02Values(){
	var hsd02Val = $('#HSD02Id').val();
	var hsdConstVal = 'HSD02-0';
	var hsdValAppender = 'Id';
	var hsdDescAppender = 'IdLabel';
	var prevVal = hsd02Val;
	var nextVal = hsd02Val;
	var constVarName = '';
	//careful about the loop decreasing value
	var valToToggleAddButton=0;
	var temp;
	for(var i=5;i>0;i--){
		temp = i+1;
		constVarName = '#'+hsdConstVal+ i + hsdValAppender;
		nxtVarName = '#'+hsdConstVal+ temp + hsdValAppender
		if($(constVarName).val().length > 1){
			//showing the cell
			var tempVal = $(constVarName).val();
			toggleInputCell(hsdConstVal,i+1);
			
			$(nxtVarName).val($(constVarName).val());
			setDescriptionLabel(temp,tempVal);
			if(valToToggleAddButton <i+2)
			valToToggleAddButton = i+2;
		}
	}
	
		constVarName = '#'+hsdConstVal+ 1 + hsdValAppender;
		$(constVarName).val($('#HSD02Id').val());
		setDescriptionLabel(1,$('#HSD02Id').val());
		$('#HSD02Id').val('');
	if(valToToggleAddButton<7)
	toggleAddButton(hsdConstVal,valToToggleAddButton);
	
	
}

function setDescriptionLabel(id,varId){
	var constVar = '#HSD02-0'+id+'IdLabel';
	$(constVar).text('');
	$.ajax({
				url: "../ajaxautocompletelistcreatepage.ajax",
				dataType: "json",
				type:"POST",
				data: "key="+varId+ "&name=HSD02",
				success: function(resp){
				$(constVar).text(resp.rows[0].id);

				}
			});
}

// Below this modifications are made to make the letters uppercase in the respective fields it will make ajax call to get variables
$(document).ready(function(){
	$('#HSD02-01Id').blur(function() {
		if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			
		   	$("#HSD02-01IdLabel").text('');
		   }
		   else{
			   
		   }
	   $('#HSD02-01Id').val($('#HSD02-01Id').val().toUpperCase());
	});
	$("#HSD02-01Id").autocomplete({ 
		select: function(event, ui) { $("#HSD02-01IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD02",
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
				displayLabelForSelectedItem(text,list,"HSD02-01IdLabel",invalidHippaCodeValue);
				$('#HSD02-01SysId').val(text);
			}
			if(!isNaN($(this).val()) && $("#HSD02-01IdLabel").text().length > 1 && $("#HSD02-01IdLabel").text() == invalidHippaCodeValue ){
				   displayLabelForSelectedItem(text,list,"HSD02-01IdLabel",invalidHSD02Value);
				   $("#HSD02-01Id").autocomplete("close");
			   }
		}
	});
});

$(document).ready(function(){
	$('#HSD02-02Id').blur(function() {
		if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
			
		   	$("#HSD02-02IdLabel").text('');
		   }
		   else{
			   
		   }
	   $('#HSD02-02Id').val($('#HSD02-02Id').val().toUpperCase());
	});
	$("#HSD02-02Id").autocomplete({ 
		select: function(event, ui) { $("#HSD02-02IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD02",
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
				displayLabelForSelectedItem(text,list,"HSD02-02IdLabel",invalidHippaCodeValue);
				$('#HSD02-02SysId').val(text);
			}
			if(!isNaN($(this).val()) && $("#HSD02-02IdLabel").text().length > 1 && $("#HSD02-02IdLabel").text() == invalidHippaCodeValue ){
				   displayLabelForSelectedItem(text,list,"HSD02-02IdLabel",invalidHSD02Value);
				   $("#HSD02-02Id").autocomplete("close");
			   }
		}
	});
});

$(document).ready(function(){
	$('#HSD02-03Id').blur(function() {
		if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#HSD02-03IdLabel").text('');
		   }
	   $('#HSD02-03Id').val($('#HSD02-03Id').val().toUpperCase());
	});
	$("#HSD02-03Id").autocomplete({ 
		select: function(event, ui) { $("#HSD02-03IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD02",
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
				displayLabelForSelectedItem(text,list,"HSD02-03IdLabel",invalidHippaCodeValue);
				$('#HSD02-03SysId').val(text);
			}
			if(!isNaN($(this).val()) && $("#HSD02-03IdLabel").text().length > 1 && $("#HSD02-03IdLabel").text() == invalidHippaCodeValue ){
				   displayLabelForSelectedItem(text,list,"HSD02-03IdLabel",invalidHSD02Value);
				   $("#HSD02-03Id").autocomplete("close");
			   }
		}
	});
});

$(document).ready(function(){
	$('#HSD02-04Id').blur(function() {
		if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#HSD02-04IdLabel").text('');
		   }
	   $('#HSD02-04Id').val($('#HSD02-04Id').val().toUpperCase());
	});
	$("#HSD02-04Id").autocomplete({ 
		select: function(event, ui) { $("#HSD02-04IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD02",
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
				displayLabelForSelectedItem(text,list,"HSD02-04IdLabel",invalidHippaCodeValue);
				$('#HSD02-04SysId').val(text);
			}
			if(!isNaN($(this).val()) && $("#HSD02-04IdLabel").text().length > 1 && $("#HSD02-04IdLabel").text() == invalidHippaCodeValue ){
				   displayLabelForSelectedItem(text,list,"HSD02-04IdLabel",invalidHSD02Value);
				   $("#HSD02-04Id").autocomplete("close");
			   }
		}
	});
});

$(document).ready(function(){
	$('#HSD02-05Id').blur(function() {
		if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#HSD02-05IdLabel").text('');
		   }
	   $('#HSD02-05Id').val($('#HSD02-05Id').val().toUpperCase());
	});
	$("#HSD02-05Id").autocomplete({ 
		select: function(event, ui) { $("#HSD02-05IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD02",
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
				displayLabelForSelectedItem(text,list,"HSD02-05IdLabel",invalidHippaCodeValue);
				$('#HSD02-05SysId').val(text);
			}
			if(!isNaN($(this).val()) && $("#HSD02-05IdLabel").text().length > 1 && $("#HSD02-05IdLabel").text() == invalidHippaCodeValue ){
				   displayLabelForSelectedItem(text,list,"HSD02-05IdLabel",invalidHSD02Value);
				   $("#HSD02-05Id").autocomplete("close");
			   }
		}
	});
});

$(document).ready(function(){
	$('#HSD02-06Id').blur(function() {
		if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#HSD02-06IdLabel").text('');
		   }
	   $('#HSD02-06Id').val($('#HSD02-06Id').val().toUpperCase());
	});
	$("#HSD02-06Id").autocomplete({ 
		select: function(event, ui) { $("#HSD02-06IdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "../ajaxautocompletelistcreatepage.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=HSD02",
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
				displayLabelForSelectedItem(text,list,"HSD02-06IdLabel",invalidHippaCodeValue);
				$('#HSD02-06SysId').val(text);
			}
			if(!isNaN($(this).val()) && $("#HSD02-06IdLabel").text().length > 1 && $("#HSD02-06IdLabel").text() == invalidHippaCodeValue ){
				   displayLabelForSelectedItem(text,list,"HSD02-06IdLabel",invalidHSD02Value);
				   $("#HSD02-06Id").autocomplete("close");
			   }
		}
	});
});
