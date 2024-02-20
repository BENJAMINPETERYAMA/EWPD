<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Enterprise Blue Exchange</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json.js"></script>

<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<script>
   $(document).ready(function() {   
     displayIndividualMappingPanel();

  	var rowCount = $('#ebCodesTable > tbody > tr').length;
		if(rowCount > 5){
			$('#eb03AddButton').hide();
		}else{
			$('#eb03AddButton').show();
		}
		
	var accumRowCount = $('#umRuleTable > tbody > tr').length;
	if(rowCount >= 33){
			$('#umRuleAddButton').hide();
		}else{
			$('#umRuleAddButton').show();
		}
   });
</script>

<script type="text/javascript">

	function sniffer() {
	   if(screen.height==1024) 
	   {
	      document.getElementById('createEditContainer').style.height = "510px";	   
	   } 
	   else if(screen.height==864)
	   {
	      document.getElementById("createEditContainer").style.height= "410px";
	   }
	}

// Method to submit the values in  'Create Button'
	function createMapping(){
	
	//Added as part of SSCR 19537
	var result = createEB03AssnObject();
	
	if((imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){		
			trimTextValues();
			setDescription();	
			var queryString="";
				//code to check the sensitive benefit check box for users to which  it is disabled --July Release T39
				if ($('#sensitiveBenefitChkBox').attr("disabled")) {
			 		if ($("#sensitiveBenefitChkBox").is(':checked')) {
			 			queryString = "?sensitiveBenefitChkBox=checked";
			 		}
				}
			document.forms['submitCreateForm'].action="${context}/createoreditrulemapping/create.html"+queryString;
			document.forms['submitCreateForm'].submit();
		}
		
	}
// Cancel action 
	function cancel(){
			var pageFrom = $("#pageFrom").val();
			var action ;
			//alert("Page from value:"+pageFrom);
			if(pageFrom == "advanceSearchEbx"){
				action = "${context}/createoreditrulemapping/cancelToAdvanceSearchPage.html";
			}else{
				action = "${context}/createoreditrulemapping/cancel.html";
			}
			openConfirmationDialog(action);
	
	}
// mark mapping as not applicable
	function markMappingAsNotApplicable(){
		if((imposeMaxLength('changeCommentsId', '250', 'Change Comments'))){
			document.forms['submitCreateForm'].action="${context}/createoreditrulemapping/notApplicable.html";
			document.forms['submitCreateForm'].submit();
		}
     }
// To check the maxlength
	function imposeMaxLength(elementId, MaxLen, element){
			var valueOfTextArea = $.trim($('#'+elementId).val());
			$('#'+elementId).val(valueOfTextArea);
			if(valueOfTextArea!=null && valueOfTextArea.length > MaxLen) {
				var message = "Text length cannot be greater than " +MaxLen+" for "+element;			
				addErrorToNotificationTray(message);
				openTrayNotification();	
				return false;
	  		}
			return true;
}
function printPage() {


  	    	
	var url = "${context}/createoreditrulemapping/printRuleMapping.html";

	  var individualMappingString = "";
	  var selectedruleId = $("#ruleIdHidden").val();
	  var iii02Val =  $("#III02Id").val();
     
  	  var eb03Val = getTheValuesInTextBox("EB03Id","VALUE");
  	  var EB03Desc = getTheValuesInTextBox("EB03Id","DESC")  
	 
	  var eb03Length = eb03Val.length;
	  var eb03String = "";
	  var eb03DescString = "";
	  for(var i=0;i<eb03Length;i++) {
		
		eb03String = eb03String+"**"+eb03Val[i];
		eb03DescString = eb03DescString+"**"+EB03Desc[i];
		individualMappingString = individualMappingString+"**"+eb03Val[i]+"_"+iii02Val;
	  }
	  
	  var umruleVal = getTheValuesInTextBox("UMRuleId","VALUE");
	  var umruleDesc = getTheValuesInTextBox("UMRuleId","DESC");
	  var umruleLength = umruleVal.length;
	  var unruleString = "";
	
	  var umruleDescString = "";
	  for(var i=0;i<umruleLength;i++) {
		
		unruleString = unruleString+"**"+umruleVal[i];
		umruleDescString = umruleDescString+"**"+umruleDesc[i];
	  }
	 //Added as part of SSCR 19537
	if ($('#individualMappingForEB03ChkBox').is(':checked') ){
		var eb03AssnTable = document.getElementById('eb03AssnTable');
		individualMappingString = ""; 
		
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				
				var eb03_0			=	(null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText
				? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText : "");
				
				var III02_0 =   (null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1) && 
				null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea")
				&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0)
				&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0).value ?
				eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0).value : "");
				individualMappingString = individualMappingString+"**"+eb03_0+"_"+III02_0;
				
				if(null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4)){
					var eb03_1			=	(null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).innerText
				? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).innerText : "");
					if( $.trim(eb03_1) != ""){
						var III02_1 =    (null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") 
						&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5) && 
						null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea")
						&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0)
						&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0).value ?
						eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0).value : "");
						individualMappingString = individualMappingString+"**"+eb03_1+"_"+III02_1;
					}
				}
				}
	}
	
	var param="?createPage=true&selectedruleId="+selectedruleId+"&eb03String="+eb03String+"&eb03DescString="+eb03DescString+"&unruleString="+encodeURIComponent(unruleString)+"&umruleDescString="+encodeURIComponent(umruleDescString)+"&individualMappingString="+encodeURIComponent(individualMappingString);
	
	if ($('#sensitiveBenefitChkBox:checked').val() == 'checked') {
		param = param + "&sensitiveBenefitChkBox=Y"
	}
	
	url=url+param;
	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  }	
  function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
  }	
  
var eb03inputsString;
  //Added as part of SSCR 19537
  var eb03Values = new Array();

  

	function storeeb03values() {
	eb03inputsString = "";
	var textbox = $("input[type='text'][id^='EB03Id']"); 
		var text="";
		var seperator="`";
		textbox.each(function(i) {
		var textboxid = textbox[i].id;

			text = $.trim($(this).val());
			eb03inputsString = eb03inputsString+seperator+text;

		});
	}
	function isEb03Modified(){
		var textbox = $("input[type='text'][id^='EB03Id']"); 
		var text="";
		var seperator="`";
		var newInputs ="";
		textbox.each(function(i) {
			var textboxid = textbox[i].id;
			text = $.trim($(this).val());
			newInputs = newInputs+seperator+text;
			document.getElementById('eb03List').value = newInputs;
		});	
		if(eb03inputsString != newInputs ){
		eb03inputsString = newInputs;
		populateEb03ValuesToEB03AssnList();
			return true;
		}else{
			return false;
		}
	}

 // Function to set the sensitive benefit indicator if a sensitive EB03 is entered
   function sensitiveBenefitCheck(){
	  if (isEb03Modified()) {
		   var eb03Val = getTheValuesInTextBox("EB03Id","VALUE");
		   //logic is changed so as to retrieve the sensitive benefits list from the errorvalidator.properties proeprties file. 
		   //BXNI June2012 Release
		   var sensitiveBenefitList = new Array();
		  	$.ajax({
			url: "${context}/createoreditrulemapping/populateSensitiveBeenfitsList.ajax",
			dataType: "json",
			type: "POST",
			success: function(data) {
			cache: false;
			//Commented to remove Sens Bnft Ind -POR Wave 2
			//sensitiveBenefitList = data.sensitive;
			$("#sensitiveBenefitChkBox").attr('checked',false);
			for(var j=0;j<sensitiveBenefitList.length;j++){
				if($.inArray(sensitiveBenefitList[j], eb03Val) != -1) {
					$("#sensitiveBenefitChkBox").attr('checked',true);
						break;
					}
				}
			  }
			});
		}
 }
	 //senstive benefit warning for 'save/done' button.
function sensitiveBnftConfirmationDialog(){
   var eb03Val = getTheValuesInTextBox("EB03Id","VALUE");
   isEb03Modified();
   var sensitiveBenefitList = new Array();
   var flag = 0;
   var warningMsgForcefullyUnchecked = "EB03 mapping has at least one Sensitive Benefit."  
 						   +" Do you want to review Sensitive Benefit Indicator ?";
    var warningMsgForcefullyChecked = "There are no Sensitive Benefits mapped to EB03."  
 						   +" Do you want to review Sensitive Benefit Indicator ?";						   
   //checks if the senstitive benefit indicator is checked
   //logic is changed so as to retrieve the sensitive benefits list from the errorvalidator.properties proeprties file. 
   //BXNI June2012 Release
   	$.ajax({
		url: "${context}/createoreditrulemapping/populateSensitiveBeenfitsList.ajax",
		dataType: "json",
		type: "POST",
		success: function(data) {
		cache: false;
		//Commented to remove Sens Bnft Ind -POR Wave 2
		//sensitiveBenefitList = data.sensitive;
	if (($('#sensitiveBenefitChkBox').is(':checked'))){
		for(var j=0;j<sensitiveBenefitList.length;j++){
			if($.inArray(sensitiveBenefitList[j], eb03Val) != -1) {
				flag = 1;
				break;
			}
		}
		//if forcefully the indicator is removed, the conformation dialog warningMsgForcefullyChecked box is called
		  if(flag != 1){
		  confirmationDialogSensitiveBenefit(warningMsgForcefullyChecked);
		  }
		  else{
		  createMapping();
		  
		  }
		}
	//senstitive benefit indicator is not checked
	else {
		for(var j=0;j<sensitiveBenefitList.length;j++){
			if($.inArray(sensitiveBenefitList[j], eb03Val) != -1) {
					flag = 1;
					break;
			}
		 }
		 //if forcefully the indicator is checked, the conformation dialog box with warningMsgForSave2 is called
		  if(flag == 1){
		  confirmationDialogSensitiveBenefit(warningMsgForcefullyUnchecked);
		  }
		  else{
		  createMapping();
		  }
		}
	  }
	});
} 
	function confirmationDialogSensitiveBenefit(warningMsg){
	$("#confirmationDivSensitiveBnft").html(warningMsg);
	$("#confirmationDivSensitiveBnft").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:140,
			width:400,
			modal: true,
			buttons: {			
			No: function() {
		  			createMapping();
					$(this).dialog('close');
				},		
			Yes: function() {
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivSensitiveBnft").dialog('open');
	}
	
	// Added as part of SSCR 19537 -- Individual Mapping for EB03
	function displayIndividualMappingPanel(){
		if ($('#individualMappingForEB03ChkBox').is(':checked') ){
				$('#III02Content').hide();
				$('#III02Heading').hide();
				$('#eb03AssnTable').show();
		}
		if (!$('#individualMappingForEB03ChkBox').is(':checked') ){
				$('#III02Content').show();
				$('#III02Heading').show();
				$('#eb03AssnTable').hide();
		}
	}
	
	//Added as part of SSCR 19537
	function createEB03AssnObject(){
		var eb03AssnTable = document.getElementById('eb03AssnTable');
		var eb03AssnJSON = [];
		var newList = document.getElementById('eb03List').value;
		var newValue = newList.replace(/`/g, '');
		if((newValue != ""))
		{
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
			
			var eb03_0			=	(null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText
			? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText : "");
			
			var III02_0 =   (null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1) && 
			null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea")
			&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0)
			&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0).value ?
			eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(1).getElementsByTagName("textarea").item(0).value : "");
			
			//push the value to the json array
			eb03AssnJSON.push({"EB03": eb03_0,"III02":III02_0});
		
			if(null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4)){
				var eb03_1			=	(null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).innerText
			? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).innerText : "");
				if( $.trim(eb03_1) != ""){
					var III02_1 =    (null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD") 
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5) && 
					null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea")
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0)
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0).value ?
					eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0).value : "");
					eb03AssnJSON.push({"EB03": eb03_1,"III02":III02_1});
				}
			}
			}
		//after the iteration, stringify the json array and assign the same to a hidden request parameter
		eb03AssnJSON = JSON.stringify(eb03AssnJSON);
		document.getElementById('hdeb03AssnJSON').value=eb03AssnJSON;
		return true;
		}else{
			eb03AssnJSON.push({"EB03": '' ,"III02": ''});
			eb03AssnJSON = JSON.stringify(eb03AssnJSON);
			document.getElementById('hdeb03AssnJSON').value=eb03AssnJSON;
			return true;
		}
	}


	 function populateEb03ValuesToEB03AssnList(){
		  var newEB03List = document.getElementById('eb03List').value;
		 var result = createEB03AssnObject();
		//	if(result == true){
		  var associatedEB03Mapping = document.getElementById('hdeb03AssnJSON').value;
				  
		   $.ajax({
			url: "${context}/createoreditrulemapping/populateEb03ValuesToEB03AssnList.ajax",
			dataType: "json",
			type: "POST",
			data: "newEB03List="+newEB03List+"&associatedEB03Mapping="+associatedEB03Mapping,
			success: function(data) {
					cache: false,
				//	eB03AssnList = data.eb03AssnObjAsJsonArray;
					createNewTable(data.eb03AssnObjAsJsonArray);
					}
			});
		//	}
	}
	

	function createNewTable(eb03AssnList){
		var eb03AssnObjCount = countProperties(eb03AssnList);
		var table = document.getElementById("eb03AssnTable");               
		var rowCount = table.rows.length; 
		
		 if (rowCount % 2 == 0) {
						trClass = "alternate";
					}else {
						trClass = "white-bg";
					}
	
		 // Delete all rows:
		   try {             
		   for(var i=0; i <= rowCount; i++) {                
		    	var row = table.rows[i];                 
                table.deleteRow(i);                    
		      	rowCount--;                     
		      	i--;                 
            }             
		      }catch(e) {                 
		      } 
		  
		 
		 var count = 0;
		 
		 var row = table.insertRow(count); 
		 row.className = "createEditTable1-Listdata";
          
		 var headCell0 = row.insertCell(0); 
		 var headElement0 = document.createElement("Label");
		 headElement0.style.fontSize = "11px";  
		 headElement0.type = "Label";             
		 headElement0.className = "headText";
		 headElement0.innerHTML =  "EB03";	         
		 headCell0.appendChild(headElement0);  
		 
		 var headCell1 = row.insertCell(1); 
		 var headElement1 = document.createElement("Label"); 
		 headElement1.type = "Label";             
		 headElement1.className = "headText";
		  headElement1.style.fontSize = "11px"; 
		 headElement1.innerHTML =  "III02 		";	 
		 headCell1.appendChild(headElement1); 
		 
		 var headElement2 = document.createElement("a"); 
		 headElement2.setAttribute("href", "#");            
		 headElement2.onclick = function(){displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');};
		 headElement2.innerHTML =  "  what is this?";	 
		 headElement2.setAttribute("colspan","3");    
		 headCell1.appendChild(headElement2); 

		 
		 var headCell3 = row.insertCell(3); 
		 var headCell4 = row.insertCell(4); 
		 if(null != eb03AssnList[1] && null != eb03AssnList[1].eb03 && eb03AssnList[1].eb03.value != ""){
			 var headCell5 = row.insertCell(5); 
			 var headElement5 = document.createElement("Label"); 
			 headElement5.type = "Label";             
			 headElement5.className = "headText";
			 headElement5.style.fontSize = "11px";
			 headElement5.innerHTML =  "EB03";	         
			 headCell5.appendChild(headElement5);  
			 
			 var headCell6 = row.insertCell(6); 
			 var headElement6 = document.createElement("Label"); 
			 headElement6.type = "Label";             
			 headElement6.className = "headText";
			 headElement6.innerHTML =  "III02	 	";
			 headElement6.style.fontSize = "11px";	 
			 headCell6.appendChild(headElement6); 
			 
			 var headElement7 = document.createElement("a"); 
			 headElement7.setAttribute("href", "#");            
			 headElement7.onclick = function(){displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');};
			 headElement7.innerHTML =  "  what is this?";	 
			 headCell6.appendChild(headElement7); 
		 } else{
			var cell4 = row.insertCell(4); 
		 	var cell5 = row.insertCell(5);
		 	cell5.setAttribute("width","500");
		    var cell6 = row.insertCell(6);
		
		}
		 
		  for(var i=0; i<eb03AssnObjCount; i++){
		  count++;
					if (count % 2 != 0) {
						trClass = "alternate";
					}else {
						trClass = "white-bg";
					}
					var eb03 = eb03AssnList[i].eb03.value;				
					var iii02 = eb03AssnList[i].commaSeparatedIII02StringWithDesc;
	           
		 var row = table.insertRow(count); 
		 
          
		 var cell0 = row.insertCell(0); 
		 row.className = trClass;
		 var element0 = document.createElement("Label"); 
		 element0.type = "Label";             
		 element0.name="EB03Label"+i;
		 element0.id = "EB03Label"+i; 
		 element0.style.fontSize = "11px";
		 element0.innerHTML =  eb03AssnList[i].eb03.value;	         
		 cell0.appendChild(element0);  
		 
		 var cell1 = row.insertCell(1);
		 var element1 = document.createElement("textarea"); 
		 element1.name="III02Label"+i;
		 element1.id = "III02Id"+i; 
		 element1.style.width = "300px";
		 element1.border = "1px";
		 element1.border = "solid";
		 element1.border = "grey";
		 element1.rows = "1";
		 element1.onclick =  function(){this.rows = '3';};
		 element1.onblur = function(){this.rows = '1';};
		 element1.value =  eb03AssnList[i].commaSeparatedIII02StringWithDesc;
		 cell1.appendChild(element1);
 		cell1.getElementsByTagName("textarea").item(0).readOnly = true;
		 
		 
		 var element2 = document.createElement("img");
		 element2.name = "III02_"+i;  
		 element2.setAttribute("width","15"); 
		 element2.setAttribute("height","14");             
		 element2.setAttribute ("src", "${imageDir}/icon-popup.gif");
		
		 element2.onclick =  function(){ popupDiv(this.name,'../ajaxpopup.ajax');};	
		 element2.style.paddingLeft = "5px";	         
		 cell1.appendChild(element2);  
		 
		 var cell2 = row.insertCell(2);  
		 var cell3 = row.insertCell(3);
		 i++;
		  if(null != eb03AssnList[i] && null != eb03AssnList[i].eb03 && eb03AssnList[i].eb03.value != ""){
		  
		  
				 var cell4 = row.insertCell(4); 
				 row.className = trClass;
				 var element4 = document.createElement("Label"); 
				 element4.type = "Label";             
				 element4.name="EB03Label"+i;
				 element4.id = "EB03Label"+i; 
				 element4.style.fontSize = "11px";
				 element4.innerHTML =  eb03AssnList[i].eb03.value;	         
				 cell4.appendChild(element4);  
				 
				 
				 var cell5 = row.insertCell(5);
				 var element5 = document.createElement("textarea"); 
				 element5.setAttribute('readonly','readonly');
				 element5.name="III02Label"+i;
				 element5.id = "III02Id"+i; 
				 element5.style.width = "300px";
				 element5.border = "solid";
				 element5.border = "1px";
				 element5.border = "grey";
				 element5.rows = "1";
				 element5.onclick =  function(){this.rows = '3';};
				 element5.onblur = function(){this.rows = '1';};
				 element5.value =  eb03AssnList[i].commaSeparatedIII02StringWithDesc;	
				 cell5.appendChild(element5); 
                                 cell5.getElementsByTagName("textarea").item(0).readOnly = true;
				 
				 
				
				 var element6 = document.createElement("img");
				 element6.name = "III02_"+i;  
			
				 element6.setAttribute("width","15"); 
				 element6.setAttribute("height","14");             
				 element6.setAttribute ("src", "${imageDir}/icon-popup.gif");
				 element6.onclick =   function(){ popupDiv(this.name,'../ajaxpopup.ajax');}; 
				 element6.style.paddingLeft = "5px";          
				 cell5.appendChild(element6);  
				 
				 var cell6 = row.insertCell(6);
				 var cell7 = row.insertCell(7);
		  }else{
		 		 var cell4 = row.insertCell(4);
		 		 
		 		 var cell5 = row.insertCell(5);
		 		 var cell6 = row.insertCell(6);
		  }
		}
	}
 
 	function countProperties(obj) {
		var prop;
		var propCount = 0;
		for (prop in obj) {
		propCount++;
		}
		return propCount;
	}
</script>
</head>
<body onload="storeinputs(); storeeb03values(); displayIndividualMappingPanel(); " >
	<form name="submitCreateForm"  method="post">
	<input type="hidden" id="pageName" value="headerRuleMappingPage"/>
	<input type="hidden" id="hdeb03AssnJSON" name = "hdeb03AssnJSON1" />
	<input type="hidden" id="eb03List" name = "eb03List1" />
	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
	<input type="hidden" id="pageFrom" name="pageFrom" value="${mapping.pageFrom}" /> 

	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>
		
          <div class="innerContainer" style="height:96%;">
            <!-- innerContainer Starts-->
               <div id="variableInfoDiv" class="overflowContainerVariable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
                  <THEAD>
						<tr class="createEditTable1">
		                    <td width="70" class="tableHeader">Header Rule</td>
		                    <td width="200" class="tableHeader">Description</td>	
		                    <td align="right"><a href="#" onclick='printPage();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a></td>	   	                   
	                  	</tr>
				</THEAD>
				<TBODY>									
					<tr>			
						<input type="hidden" id="token" value="${TRANSACTION_TOKEN_KEY}" name="tokenKey" />	
						<input type="hidden" name="selectedruleId" id="ruleIdHidden" value="${mapping.rule.headerRuleId}"/>	
						<input type="hidden" name="ruleDesc" id="ruleDescHidden" value="${mapping.rule.ruleDesc}"/>	
								
						<td width="65px">${mapping.rule.headerRuleId}</td>
						<td width="300px">${mapping.rule.ruleDesc}</td>	
						<td></td>					
					</tr>
				</TBODY>             
                </table>
			</div><!-- variableInfoDiv closed -->
		
		  <div id="createEditContainer" style="margin-top:10px;">	<!--createEditContainer Starts-->			
				<!--First Table-->					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3">
	                  <tr class="createEditTable1-Listdata">
	                    <td class="headText" width="475">EB03 <a href="#" onclick="displayInfo('EB03',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
	                    &#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB03" 
	                    onclick="popupDiv('EB03', '../ajaxpopuphippasegment.ajax')"/></a></td>	
	                    
	                    <td class="headText" nowrap style="width:475px;" align ="right">
							<input align ="right" type="checkbox" onclick = "displayIndividualMappingPanel()"<c:if test="${mapping.indvdlEb03AssnIndicator == 'Y'}">checked</c:if> 
							name="individualMappingForEB03ChkBox" id="individualMappingForEB03ChkBox" />&#160;Individual EB03 Mapping Reqd&#160;&#160;&#160;						
						</td>                  
	                  </tr>
					</table>					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="accumTable"/>
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="ebCodesTable">
					<tbody id="ebCodeTbody">
						<tr class="createEditTable1-Listdata alternate">												                
	                    <td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id0" value="${mapping.eb03.hippaCodeSelectedValues[0].value}" />
						</td>
	                    <td style="width: 140px">
							<label id="EB03IdLabel0" for="EB03Id0" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}" />
							</label>
							<input type="hidden" id="EB03Desc0" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/>
						</td>
						<td width="2px"></td>
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id1" value="${mapping.eb03.hippaCodeSelectedValues[1].value}"/>
						</td>
						<td style="width: 140px">
							<label id="EB03IdLabel1" for="EB03Id1" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[1].description}" />
							</label>
							<input type="hidden" id="EB03Desc1" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[1].description}"/>
						</td>	
						<td width="2px"></td>
						<td style="width: 95px">
		              		<input type="text" name="eb03Val"  class="inputbox60" id="EB03Id2" value="${mapping.eb03.hippaCodeSelectedValues[2].value}"/>
						</td>
	                    <td style="width: 140px">
							<label id="EB03IdLabel2" for="EB03Id2" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[2].description}" />
							</label>
								<input type="hidden" id="EB03Desc2" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[2].description}"/>
						</td>   
						<td width="2px"></td>          
	                  </tr>
					 <tr class="createEditTable1-Listdata">
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id3" value="${mapping.eb03.hippaCodeSelectedValues[3].value}"/>
						</td>
	                    <td style="width: 140px">
							<label id="EB03IdLabel3" for="EB03Id3" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[3].description}" />
							</label>
								<input type="hidden" id="EB03Desc3" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[3].description}"/>
						</td>
						<td width="2px"></td>
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id4" value="${mapping.eb03.hippaCodeSelectedValues[4].value}"/>
						</td>
						<td style="width: 140px">
							<label id="EB03IdLabel4" for="EB03Id4" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[4].description}" />
							</label>
							<input type="hidden" id="EB03Desc4" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[4].description}"/>
						</td>
						<td width="2px"></td>
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id5" value="${mapping.eb03.hippaCodeSelectedValues[5].value}"/>
						</td>
						<td style="width: 140px">
							<label id="EB03IdLabel5" for="EB03Id5" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[5].description}" />
							</label>
							<input type="hidden" id="EB03Desc5" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[5].description}"/>
						</td>
						<td width="2px"></td> 
	              	</tr>
	              	<c:if test="${fn:length(mapping.eb03.hippaCodeSelectedValues) > 6}">
							<c:set var="count" value="6"/>
						<c:forEach items="${mapping.eb03.hippaCodeSelectedValues}"	var="eb03Values" begin="6">
							<script type="text/javascript">
								insertRuleEB03('ebCodesTable','EB03Id${count}','eb03Val','${eb03Values.value}',true,'EB03IdLabel${count}','${eb03Values.description}','EB03Desc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
					</tbody>
					</table>
					<div>
						<table border="0">
							<tr>								
								<td width="90px"><a href="#"><img id="eb03AddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForRuleEb03('ebCodesTable','EB03Id','eb03Val','',true,'');"/></a></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					<table width="950px">					
					 <tr class="createEditTable1-Listdata alternate">
					  	 <td class="headText" nowrap style="width:307px;">
							<input type="checkbox" <c:if test="${mapping.sensitiveBenefitIndicator== 'true'}">checked</c:if>
							<c:if test="${userUIPermissions.authorizedToEditSensitiveBnftIndctr !='true'}"> disabled </c:if>
							 name="sensitiveBenefitChkBox" id="sensitiveBenefitChkBox" value="checked"/>&#160;Sensitive Benefit Indicator&#160;&#160;&#160;<a href="#" onclick="displayInfo('SENSITIVE BENEFIT',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>						
						</td> 
						<td class=headText nowrap align=left>&nbsp;<INPUT  <c:if test="${mapping.procedureExcludedInd == 'Y'}">checked</c:if> 
							id=excludeProceduresChkBox type=checkbox name=excludeProceduresChkBox value="checked">&#160;Procedures Excluded From Pre-authorization 
						&#160;&#160;&#160;<a href="#"  tabIndex="-1" onclick="displayInfo('EXCLUDE PROCEDURE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
						</td>
						
					</tr>
					</table>
					
					
					<!-- III02 section begins -->
					<table width="950px" name = "III02Heading" id = "III02Heading">
					<tr class="createEditTable1-Listdata">
						<td class="headText">III02 <a href="#" onclick="displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; <img
							src="${imageDir}/icon-popup.gif" width="15" height="14" id="III02" onclick="popupDiv('III02', '../ajaxpopup.ajax')" /></td>
					</tr>
					</table>	
					<table  width="950" name = "III02Content" id = "III02Content">
						<tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >						
					<c:choose>
										<c:when test="${mapping.indvdlEb03AssnIndicator == 'N'}">
											<textarea readonly style="width: 300px;border:1px solid grey;"
					 						colspan="2" name="ii02Val" id="III02Id" rows="1" onclick="this.rows = '3';" onblur="this.rows = '1';"
											>${eB03AssnList[0].commaSeparatedIII02StringWithDesc}</textarea>
											<input type="hidden"  name="ii02SysId" id="II02SysId" value="${eB03AssnList[0].commaSeparatedIII02StringWithDesc}" />
										</c:when>
										<c:otherwise>
											<textarea  readonly style="width: 300px;border:1px solid grey;"
					 						colspan="2" name="ii02Val" id="III02Id" rows="1" onclick="this.rows = '3';" onblur="this.rows = '1';"
											>${mapping.ii02.hippaCodeSelectedValues[0].hippaCodeValueSysId}</textarea>
											<input type="hidden"  name="ii02SysId" id="II02SysId" value="${mapping.ii02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
										</c:otherwise>
							</c:choose>
						</td>
	                    <td >
							
						</td>
					</tr>
					</table>	
				<!-- III02 section ends -->	
				  <!-- III02 section for Individual mapping --> 
				  
				  	<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT" id = "eb03AssnTable">
					<tr class="createEditTable1-Listdata">
						<td class="headText">EB03</td>
					
						<td class="headText" colspan = "3">III02 <a href="#" onclick="displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; </td>
					<c:if test= "${!empty eB03AssnList[1].eb03.value}">
						<td class="headText">EB03</td>
					
						<td class="headText"  colspan = "3">III02 <a href="#" onclick="displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; </td>
					</c:if>
					<c:set var="count" value="0"/>
					<c:set var="rowCount" value="2"/>
					
					<c:forEach items="${eB03AssnList}" begin="0">
						<c:if test= "${!empty eB03AssnList[count].eb03.value}">											
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
						
						<td style="width: 100px">
							<label id="EB03Label${count}" for="EB03Label${count}" style="font-size: 11px">
									${eB03AssnList[count].eb03.value}
								</label>
							</td>
							<td style="width: 100px">
								<textarea readonly style="width: 300px;border:1px solid grey;"
					 						colspan="2" name="III02Id${count}" id="III02Id${count}" rows="1" onclick="this.rows = '3';" onblur="this.rows = '1';"
											>${eB03AssnList[count].commaSeparatedIII02StringWithDesc}</textarea>
							</td>
							<td style="width: 25px">
								<img src="${imageDir}/icon-popup.gif" width="15" height="14" id="III02_${count}" name="III02_${count}" onclick= "popupDiv(this.name,'../ajaxpopup.ajax');" />
							</td>
							
							<td style="width: 250px">
							
							</td>
							
							<c:set var="count" value="${count + 1}"/>
							<c:if test= "${!empty eB03AssnList[count].eb03.value}">
								<td style="width: 100px">
									<label id="EB03Label${count}" for="EB03Label${count}" style="font-size: 11px">
										${eB03AssnList[count].eb03.value}
									</label>
								</td>
								<td style="width: 100px">
									<textarea readonly style="width: 300px;border:1px solid grey;"
					 						colspan="2" name="III02Id${count}" id="III02Id${count}" rows="1" onclick="this.rows = '3';" onblur="this.rows = '1';"
											>${eB03AssnList[count].commaSeparatedIII02StringWithDesc}</textarea>
							
								</td>

								
								<td style="width: 25px">
									<img src="${imageDir}/icon-popup.gif" width="15" height="14" id="III02_${count}" name="III02_${count}" onclick= "popupDiv(this.name,'../ajaxpopup.ajax');" />
								</td>
								<td style="width: 250px">
								
								</td>
							</c:if>
							<c:if test= "${empty eB03AssnList[count].eb03.value}">
								<td style="width: 100px">
									
								</td>
								<td style="width: 100px">
								</td>
								
								<td style="width: 25px">
								</td>
								<td style="width: 250px">
								
								</td>
							</c:if>
	
							<c:set var="count" value="${count + 1}"/>
							<c:set var="rowCount" value="${rowCount + 1}"/>
			
						</tr>
						</c:if>
						
		
			
		
				</c:forEach>	
					</tr>
				</table>
			
					<!-- Individual mapping ends here -->
					
					<!--  UM Rule Table starts here )-->
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="Pd3 mT5 bT">
						<tr class="createEditTable1-Listdata">
							<td class="headText">UM Rule <a href="#" onclick="displayInfo('UMRULE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; <img
								src="${imageDir}/icon-popup.gif" width="15" height="14" id="UMRULE" onclick="popupDiv('UMRULE', '../ajaxpopup.ajax')" /></td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="Pd3" id="umRuleTable">
					<tbody id="umRuleTbody">
						<tr class="createEditTable1-Listdata alternate">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId0" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}"/>
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel0" for="UMRuleId0" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}
								</label>
								<input type="hidden" id="UMRuleDesc0" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}" />
							</td>
							<td>
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId0" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId1" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].value}"/>
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel1" for="UMRuleId1" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].description}
								</label>
								<input type="hidden" id="UMRuleDesc1" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].description}" />
							</td>
							<td>
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId1" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId2" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].value}"/>
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel2" for="UMRuleId2" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].description}
								</label>
								<input type="hidden" id="UMRuleDesc2" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].description}" />
							</td>
							<td>
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId2" title="View Rule" />
							</td>
						</tr>
						<tr class="createEditTable1-Listdata white-bg">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId3" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].value}"/>
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel3" for="UMRuleId3" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].description}
								</label>
								<input type="hidden" id="UMRuleDesc3" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].description}" />
							</td>
							<td>
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId3" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId4" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].value}"/>
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel4" for="UMRuleId4" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].description}
								</label>
								<input type="hidden" id="UMRuleDesc4" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].description}" />
							</td>
							<td>
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId4" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId5" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].value}"/>
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel5" for="UMRuleId5" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].description}
								</label>
								<input type="hidden" id="UMRuleDesc5" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].description}" />
							</td>
							<td>
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId5" title="View Rule" />
							</td>
						</tr>
							<c:if test="${fn:length(mapping.utilizationMgmntRule.hippaCodeSelectedValues) > 6}">
							<c:set var="count" value="6"/>
						<c:forEach items="${mapping.utilizationMgmntRule.hippaCodeSelectedValues}"	var="umRuleValues" begin="6">
							<script type="text/javascript">
								insertUmRule('umRuleTable','UMRuleId${count}','umRuleVal','${umRuleValues.value}',true,'UMRuleIdLabel${count}','${umRuleValues.description}','UMRuleDesc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
						</c:if>
						</tbody>
					</table>
	
					<div class="fL pT5">
						<img id="umRuleAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForUmRule('umRuleTable','UMRuleId','umRuleVal','',true,'')" />
					</div>					
					<!--  UM Rule Table ends here )-->
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
	                  <tr class="">
	                    <td width="900px" class="headText">Change Comments</td>
					  </tr>
					  <tr class="">
	                    <td><textarea name="changeComments" id="changeCommentsId" rows="5" cols="110" >${changeComments}</textarea></td>
					  </tr> 			
					</table>
						
				</div><!--createEditContainer Ends-->		
         
          </div>
		  <!-- innerContainer Ends-->
		</div>
		<!-- container Ends-->
		 <div class="footer">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
		        <td width="20" height="20"><img src="${imageDir}/footer_left.gif" width="20" height="20" /></td>
		        <td width="0" height="20"><a href="#" onClick="sensitiveBnftConfirmationDialog();">Create</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick ='cancel();'>Cancel</a></td>
		        <c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
				<td width="0" height="20"><a href="#" onclick='markMappingAsNotApplicable();'>Not Applicable</a></td>				 					
				 </c:if>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
		      </tr>
		    </table>					
		</div>
</div>
	<!-- wrapper Ends-->


</form>
<div id="hippaCodePopUpDiv"></div>
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<script type="text/javascript">
	sniffer();
</script>
<div id="confirmationDiv"></div>
<div id="confirmationDivSensitiveBnft"></div>
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>
