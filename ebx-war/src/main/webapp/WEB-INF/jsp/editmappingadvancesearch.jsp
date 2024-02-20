<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/createeditpage.js"></script>
<script type="text/javascript">
	function sniffer() {
	   if(screen.height==1024) 
	   {
	      document.getElementById('createEditContainer').style.height = "510px";	   
	   }else if(screen.height==960)
	   {
	      document.getElementById("createEditContainer").style.height= "440px";
	   }
	   else if(screen.height==864)
	   {
	      document.getElementById("createEditContainer").style.height= "400px";
	   }
	   else if(screen.height==720)
	   {
	      document.getElementById("createEditContainer").style.height= "290px";
	   }
	}

	 function sendToTest(variableId, variableFormat){
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
				return false;
			}
			if(!imposeMaxLength('messageValueId' ,40,'Message')){
				return false;
			}
			setDescription();
			document.forms['stateFlowForm'].action="../stateflow/saveAndsendToTest.html";
            document.forms["stateFlowForm"].submit();
      }
	function approve(variableId, variableFormat){
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
				return false;
			}
			if(!imposeMaxLength('messageValueId' ,40,'Message')){
				return false;
			}
			setDescription();
			document.forms['stateFlowForm'].action="../stateflow/saveAndApprove.html";
            document.forms["stateFlowForm"].submit();
			
      }
	function notApplicable(variableId){
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			trimTextValues();
			setDescription();
			document.forms['stateFlowForm'].action="../stateflow/notApplicable.html";
            document.forms["stateFlowForm"].submit();
			
      }

	function discardChanges(variableId, mappingSysId){
			$("#selectedvariableId").val(variableId);			
			$("#mappingSysId").val(mappingSysId);
			var action = "${context}/editmapping/discardChanges.html";
			confirmationDialogDiscardChanges(action, 'stateFlowForm');			
	}
	function cancel(){
			var action = "${context}/editmapping/cancel.html";
			openConfirmationDialog(action);
	}
	function save(variableId, variableFormat){
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
				return false;
			}
			if(!imposeMaxLength('messageValueId' ,40,'Message')){
				return false;
			}
			setDescription();
			document.forms['stateFlowForm'].action="${context}/editmapping/saveMapping.html";
	        document.forms["stateFlowForm"].submit();
			
      }	
	function done(variableId, variableFormat){
			$("#selectedvariableId").val(variableId);			
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
				return false;
			}
			if(!imposeMaxLength('messageValueId' ,40,'Message')){
				return false;
			}
			setDescription();
			document.forms['stateFlowForm'].action="${context}/editmapping/done.html";
            document.forms["stateFlowForm"].submit();
			
      }	
	function openViewHistoryDialog(variableId) {
		$.ajax({
			url: "${context}/editmapping/viewHistory.ajax",
			dataType: "html",
			type: "POST",
			data: "variableId="+variableId,
			success: function(data) {
				$("#viewHistoryDialog").html(data);
				$("#viewHistoryDialog").dialog({
					height:'450',
					width:'600',
					resizable : 'false',
					modal: true
				});
				$("#viewHistoryDialog").dialog();
			}
		});
	}
    function copyTo(){		
				$("#copyToDialog").dialog({
					 		height:'auto',
							width:'450px',
							title: 'Copy to Variable',
	                        show:'slide',
	                        modal: true
				});
				$("#copyToDialog").dialog('open');			
	}
	
	function openAlertDialog(){
      if(compareInputs()){
            alertDialog();
      }else{
            copyTo();
      }
	}
	
	function alertDialog(){
	$("#alertDiv").html(warningMsgForCopyTo);
	$("#alertDiv").addClass("UnmappedVariables");
	$("#alertDiv").dialog({
				autoOpen: false,
				title : 'Alert',
				resizable: false,
				height:150,
				modal: true,
				buttons: {
				Ok: function() {
						$(this).dialog('close');
					}
				}
			});
			$("#alertDiv").dialog('open');
}

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

	//Validation for 'Message'
	function checkMsgRqdInd(){
		var checked = $('#msgRqdChkBox').attr('checked');
		if(checked){
			var msgValue = $('#messageValueId').val();
			if(msgValue == null || $.trim(msgValue) == "" ){
				addErrorToNotificationTray("Enter message");
				openTrayNotification();	
				$('#messageValueId').focus();
				return false;
			}
		}
		return true;
	}

// for copy to function for doing save
//openCopyToDialog("${mapping.variable.variableId}","${mapping.variable.description}","${variableList[0].variableFormat}");
$(document).ready(function(){

	$("#variableId").autocomplete({ 
		
		select: function(event, ui) { $("#createIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxvariablelist.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term,
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
				displayLabelForSelectedItem(text,list,"createIdLabel",inValidVariable);
			}
		}

	})
  });

function callCopyTo(){
		var variableIdForCopyTo =$("#variableIdForCopyTo").val();
		var oldVarID= $("#oldVarID").val();
		var oldVarDesc = $("#oldVarDesc").val();
		var oldVarFormate = $("#oldVarFormate").val();
		$.ajax({
				url: "${context}/copyto/invalidVariable.ajax",
				dataType: "json",
				type: "POST",			
				data: "variableIdForCopyTo="+variableIdForCopyTo+"&oldVarID="+oldVarID+"&oldVarDesc="+oldVarDesc+"&oldVarFormate="+oldVarFormate,
				success: function(data) {
				cache: false,
				copyToCreate(data,variableIdForCopyTo,oldVarID,oldVarDesc,oldVarFormate);
				}
			});	
}
function copyToCreate(data,variableIdForCopyTo,oldVarID,oldVarDesc,oldVarFormate){
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			openTrayNotification();
		}
		else{

			$("#selectedvariableIdCopyTo").val(variableIdForCopyTo);
			$("#selectedvariableId").val(oldVarID);			
			if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
				return false;
			}
			if(!imposeMaxLength('messageValueId' ,40,'Message')){
				return false;
			}
			trimTextValues();
			setDescription();
			document.forms['stateFlowForm'].action="${context}/createpage/copyTo.html";
	        document.forms["stateFlowForm"].submit();
		    //document.forms["copyToform"].submit();
		}

      }	
 function searchMessageText(currentPageNo, page,context){
	if(page == 'Init'){
		$('#hdMsgTxtId').val($('#msgTxtId').val());
		$('#hdMsgTxtEB03Id').val($('#msgTxtEb03Id').val());
		$('#hdMsgTxtHdrId').val($('#msgTxtVarId').val());
		page = 'First';
	}
	var message = $('#hdMsgTxtId').val();
	var eb03 = $('#hdMsgTxtEB03Id').val();
	var variableId = $('#hdMsgTxtHdrId').val();
	
	var fromWhichSection = page;
	var currentPage = currentPageNo;		
	$.ajax({
		url: "${context}/createpage/existingMsgTexts.ajax",
		dataType: "html",
		type: "POST",
		data: "variableId="+variableId+"&eb03="+eb03+"&message="+message+"&currentPage="+currentPage+"&section="+fromWhichSection,
		success: function(data) {
			$("#messageTextResult").html(data);
		}
	});
 }
 
  	var tempVariableId = "";
	var tempVariableFormat = "";
	var tempActionName = "";
	
	function runSpellCheck(variableId, variableFormat,actionName){
		tempVariableId = variableId;
		tempVariableFormat = variableFormat;
		tempActionName = actionName;
		var rswlCntrls = ["rapidSpellWebLauncher1"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
		
	}
	
	function spellFinMappingAction(cancelled){
			if(tempActionName == "approve"){
				approve(tempVariableId, tempVariableFormat);
			}else if(tempActionName == "done"){
				done(tempVariableId, tempVariableFormat);
			}else if(tempActionName == "save"){
				save(tempVariableId, tempVariableFormat);
			}else if(tempActionName == "sendToTest"){
				sendToTest(tempVariableId, tempVariableFormat);
			}
			tempVariableId = "";
			tempVariableFormat = "";
			tempActionName = "";
			
	}
	
	function RSCustomInterface(tbElementName){
		this.tbName = tbElementName;
		this.getText = getText;
		this.setText = setText;
	}
	function getText(){
		if(!document.getElementById(this.tbName)) {
			alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
			return '';
		} else return document.getElementById(this.tbName).value;
	}
	function setText(text){
		if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
	}    
//ends copy to for doing save
function printPage(){
  	    	
	var url = "${context}/viewmappingpage/printMapping.html";

	
	var variableId = $("#variableIdHidden").val();   

  	var eb01Val = getTheValuesInTextBox("EB01Id","VALUE");
  	var EB01Desc = getTheValuesInTextBox("EB01Id","DESC");
  	var eb02Val = getTheValuesInTextBox("EB02Id","VALUE");
  	var EB02Desc = getTheValuesInTextBox("EB02Id","DESC");
  	
  	var eb03Val =  getTheValuesInTextBox("EB03Id","VALUE");
  	var EB03Desc=  getTheValuesInTextBox("EB03Id","DESC");
  	
  	var eb03Length = eb03Val.length;
	var eb03String = "";
	var eb03DescString = "";
	for(var i=0;i<eb03Length;i++) {
		
		eb03String = eb03String+"**"+eb03Val[i];
		eb03DescString = eb03DescString+"**"+EB03Desc[i];
	}
  	
  	
  	var eb06Val = getTheValuesInTextBox("EB06Id","VALUE");
  	var EB06Desc = getTheValuesInTextBox("EB06Id","DESC");
  	var eb09Val = getTheValuesInTextBox("EB09Id","VALUE");
  	var EB09Desc = getTheValuesInTextBox("EB09Id","DESC");
  	var hsd01 = getTheValuesInTextBox("HSD01Id","VALUE");
  	var HSD01Desc = getTheValuesInTextBox("HSD01Id","DESC");
  	var hsd02 = getTheValuesInTextBox("HSD02Id","VALUE");
  	var HSD02Desc = getTheValuesInTextBox("HSD02Id","DESC");
  	var hsd03 = getTheValuesInTextBox("HSD03Id","VALUE");
  	var HSD03Desc = getTheValuesInTextBox("HSD03Id","DESC");
  	var hsd04 = getTheValuesInTextBox("HSD04Id","VALUE");
  	var HSD04Desc = getTheValuesInTextBox("HSD04Id","DESC");
  	var hsd05 = getTheValuesInTextBox("HSD05Id","VALUE");
  	var HSD05Desc = getTheValuesInTextBox("HSD05Id","DESC");
  	var hsd06 = getTheValuesInTextBox("HSD06Id","VALUE");
  	var HSD06Desc = getTheValuesInTextBox("HSD06Id","DESC");
  	var hsd07 = getTheValuesInTextBox("HSD07Id","VALUE");
  	var HSD07Desc = getTheValuesInTextBox("HSD07Id","DESC");
  	var hsd08 =  getTheValuesInTextBox("HSD08Id","VALUE");
  	var HSD08Desc = getTheValuesInTextBox("HSD08Id","DESC");
  	var ii02Val = getTheValuesInTextBox("III02Id","VALUE");
  	var III02Desc = getTheValuesInTextBox("III02Id","DESC");
  	var accumulator = getTheValuesInTextBox("accumId","VALUE");
  	var AccumDesc = getTheValuesInTextBox("accumId","DESC");
  	
  	 var accumLength = accumulator.length;
	  var accumString = "";
	
	  for(var i=0;i<accumLength;i++) {
			accumString = accumString + "&accumulator="+accumulator[i]+"&AccumDesc="+AccumDesc[i];
	  }
  	
  	
  	var umRuleVal = getTheValuesInTextBox("UMRuleId","VALUE");
  	var UMRuleDesc = getTheValuesInTextBox("UMRuleId","DESC");

	  var umruleLength = umRuleVal.length;
	  var unruleString = "";
	
	  for(var i=0;i<umruleLength;i++) {
			unruleString = unruleString + "&umRuleVal="+umRuleVal[i]+"&UMRuleDesc="+UMRuleDesc[i];
	  }
  	
  	
  	var noteType = getTheValuesInTextBox("NOTETYPEID","VALUE");
  	var NoteTypeDesc = getTheValuesInTextBox("NOTETYPEID","DESC");
  	var msgRqdChkBox = "false";
  	if ($('#msgRqdChkBox:checked').val() == 'checked') {
  		msgRqdChkBox = "true";
  	}
 	var accumNtReqdChkBox = "false";
  	if ($('#accumNtReqdChkBox:checked').val() == 'checked') {
  		accumNtReqdChkBox = "true";
  	}
  	var messageValue = $("#messageValueId").val();
  	var variableIdHidden = $("#variableIdHidden").val();
  	var variableDescHidden = $("#variableDescHidden").val();
  	var variableFormat = $("#variableFormat").val();
  	var lgCatagory = $("#lgCatagory").val();
  	var isgCatagory = $("#isgCatagory").val();
  	var system = $("#system").val();
  	var notFinalizedChkBox = $('#notFinalizedChkBox:checked').val();
  


	var param="?currentpage=edit&variableId="+variableId+"&eb01Val="+eb01Val+"&EB01Desc="+EB01Desc+"&eb02Val="+eb02Val+"&EB02Desc="+EB02Desc+"&eb03String="+eb03String+"&eb03DescString="+eb03DescString+"&eb06Val="+eb06Val+"&EB06Desc="+EB06Desc+"&eb09Val="+eb09Val+"&EB09Desc="+EB09Desc+"&hsd01="+hsd01+"&HSD01Desc="+HSD01Desc+"&hsd02="+hsd02+"&HSD02Desc="+HSD02Desc+"&hsd03="+hsd03+"&HSD03Desc="+HSD03Desc+"&hsd04="+hsd04+"&HSD04Desc="+HSD04Desc+"&hsd05="+hsd05+"&HSD05Desc="+HSD05Desc+"&hsd06="+hsd06+"&HSD06Desc="+HSD06Desc+"&hsd07="+hsd07+"&HSD07Desc="+HSD07Desc+"&hsd08="+hsd08+"&HSD08Desc="+HSD08Desc+"&ii02Val="+ii02Val+"&III02Desc="+III02Desc+accumString+unruleString+"&noteType="+noteType+"&NoteTypeDesc="+NoteTypeDesc+"&msgRqdChkBox="+msgRqdChkBox+"&accumNtReqdChkBox="+accumNtReqdChkBox+"&messageValue="+messageValue+"&variableIdHidden="+variableIdHidden+"&variableDescHidden="+variableDescHidden+"&variableFormat="+variableFormat+"&lgCatagory="+lgCatagory+"&isgCatagory="+isgCatagory+"&system="+system+"&notFinalizedChkBox="+notFinalizedChkBox;
  
	url=url+param;
	//newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  	window.open(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  }	
  function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
  }	
</script>
</head>

<body onload="storeinputs();">
	<form name="stateFlowForm"  method = "POST">
	<input type="hidden" id="pageName" value="variableMappingPage"/>
	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
		<%@include file="/WEB-INF/jspf/pageTop.jspf"%>

		<input type="hidden" id="EB01Desc" name="EB01Desc" value=""/>
		<input type="hidden" id="EB02Desc" name="EB02Desc" value=""/>
		<input type="hidden" id="EB06Desc" name="EB06Desc" value=""/>
		<input type="hidden" id="EB09Desc" name="EB09Desc" value=""/>

		<input type="hidden" id="HSD01Desc" name="HSD01Desc" value=""/>
		<input type="hidden" id="HSD02Desc" name="HSD02Desc" value=""/>
		<input type="hidden" id="HSD03Desc" name="HSD03Desc" value=""/>
		<input type="hidden" id="HSD04Desc" name="HSD04Desc" value=""/>
		<input type="hidden" id="HSD05Desc" name="HSD05Desc" value=""/>
		<input type="hidden" id="HSD06Desc" name="HSD06Desc" value=""/>
		<input type="hidden" id="HSD07Desc" name="HSD07Desc" value=""/>
		<input type="hidden" id="HSD08Desc" name="HSD08Desc" value=""/>

		<input type="hidden" id="III02Desc" name="III02Desc" value=""/>
		<input type="hidden" id="NoteTypeDesc" name="NoteTypeDesc" value=""/>
		
          <!-- container Starts-->
          <div class="innerContainer" style="height:96%;">
            <!-- innerContainer Starts-->
              <div  id="variableInfoDiv" class="overflowContainerVariable">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="Pd3">
					<THEAD>
						<tr class="createEditTable1">
		                    <td width="100" class="tableHeader">Variable ID</td>
		                    <td width="266" class="tableHeader">Description</td>
		                    <td width="55" class="tableHeader">PVA</td>	
							<td width="70" class="tableHeader">Data Type</td>
							<td width="73" class="tableHeader">System</td>
		                    <td width="165" class="tableHeader">Minor Heading</td>
		                    <td width="165" class="tableHeader">Major Heading</td>
		                     <td align="right"><a href="#" onclick='printPage();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a></td>
		                    
	                  	</tr>
				</THEAD>
				<TBODY>
					<c:set var="viewHistoryRowCount" value="0" />
					<input type="hidden" id="variableFormat" value="${variableList[0].variableFormat}" name="variableFormat"/>
					<input type="hidden" name="variableIdHidden" id="variableIdHidden" value="${variableList[0].variableId}"/>
					<input type="hidden" name="variableDescHidden" id="variableDescHidden" value="${variableList[0].description}"/>
					
					<input type="hidden" id="isgCatagory" name="isgCatagory" value="${variableList[0].isgCatagory}"/>
					<input type="hidden" id="lgCatagory" name="lgCatagory" value="${variableList[0].lgCatagory}"/>
					<input type="hidden" id="system" name="system" value="${variableList[0].variableSystem}"/>
					<tr>
						<td>${variableList[0].variableId}</td>
						<td>${variableList[0].description}</td>
						<td>${variableList[0].pva}</td>
						<td>${variableList[0].dataType}</td>
						<td>${variableList[0].variableSystem}</td>						
						<td>${variableList[0].minorHeadings}</td>
						<td>${variableList[0].majorHeadings}</td>	
						<td></td>
					</tr>
					
					<c:set var="variableInfoDivScroll" value="false" />	
					<c:forEach items="${variableList}" var="variable" begin="1">
						<c:set var="variableInfoDivScroll" value="true" />
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
							<td></td>
							<td></td>
							<td></td>	
							<td></td>										
							<td></td>
							<td>${variable.minorHeadings}</td>
							<td>${variable.majorHeadings}</td>	
							<td></td>											 
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					</c:forEach>
					<c:if test="${variableInfoDivScroll == 'true'}">
						<script>
						$('#variableInfoDiv').height('80px');
						</script>
					</c:if>
				</TBODY>
                </table>
				</div>
			
	  <div id="createEditContainer" style="margin-top:5px;">	<!--createEditContainer Starts-->
				<c:if test="${variableInfoDivScroll == 'true'}">
					<script>
						$('#createEditContainer').height('330px');							
					</script>
				</c:if>
				<!--First Table-->
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" >
						<tr class="createEditTable1-Listdata">
	                    <td width="143" class="headText">EB01 <a href="#"  onclick="displayInfo('EB01',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB01" onclick="popupDiv('EB01','../ajaxpopup.ajax')"/></a></td>
	                    <td width="95">&#160;</td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" width="134">EB03 <a href="#"  onclick="displayInfo('EB03',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB03" onclick="popupDiv('EB03','../ajaxpopup.ajax')"/></a></td>
	                    <td width="84">&#160;</td>
	                    <td width="140">&#160;</td>
	                    <td class="headText" nowrap>EB02 <a href="#" onclick="displayInfo('EB02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB02" onclick="popupDiv('EB02','../ajaxpopup.ajax')"/></a></td>
						<td width="121">&#160;</td>
	                  </tr>
					</table>
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="ebCodesTable">
	                  <tbody id="ebCodeTbody">
						<tr class="createEditTable1-Listdata alternate">
	                    
						<td width="90px" >						
								<input type="text" name="eb01Val" id="EB01Id" class="inputbox60" value="${mapping.eb01.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb01SysId" id="EB01SysId" value="${mapping.eb01.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="140px">
							<label id="EB01IdLabel" for="EB01Id" style="font-size:11px">								
									<c:out value="${mapping.eb01.hippaCodeSelectedValues[0].description}" />								
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px" >
							<input type="text" name="eb03Val"  id="EB03Id0" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}"/>
							<input type="hidden"  name="eb03SysId" id="EB03SysId" value="${mapping.eb03.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />	
						</td>
	                    <td width="140px"> 
							<label id="EB03IdLabel0" for="EB03Id0" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}" />
							</label>
							<input type="hidden" id="EB03Desc0" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/>
						</td>
	                    
						<td width="105px">&#160;</td>
	                    <td width="4%" >					
								<input type="text" name="eb02Val" id="EB02Id" class="inputbox60" value="${mapping.eb02.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb02SysId" id="EB02SysId" value="${mapping.eb02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
						<td width="134px">
							<label id="EB02IdLabel" for="EB02Id" style="font-size:11px">								
									<c:out value="${mapping.eb02.hippaCodeSelectedValues[0].description}" />								
							</label>
						</td>
	                  </tr>
					
					  <tr class="createEditTable1-Listdata">
		                    <td>&#160;</td>
		                    <td>&#160;</td>
							<td>&#160;</td>
							<td >
								<input type="text" name="eb03Val" id="EB03Id1" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[1].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId1" value="${mapping.eb03.hippaCodeSelectedValues[1].hippaCodeValueSysId}" />
							</td>
							<td>
								<label id="EB03IdLabel1" for="EB03Id1" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[1].description}"/>
								</label>
							<input type="hidden" id="EB03Desc1" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[1].description}"/>
							</td>	                    
		                    <td>&#160;</td>
		                    <td class="headText" colspan="2">EB06 <a href="#"  onclick="displayInfo('EB06',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB06" onclick="popupDiv('EB06','../ajaxpopup.ajax')"/></a></td>
	                  </tr>
					  <tr class="createEditTable1-Listdata alternate">
		                    <td></td>
		                    <td>&#160;</td>
		                    <td>&#160;</td>						
							<td >
								<input type="text" name="eb03Val" id="EB03Id2" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[2].value}" />
								<input type="hidden"  name="eb03SysId" id="EB03SysId2" value="${mapping.eb03.hippaCodeSelectedValues[2].hippaCodeValueSysId}" />
								
							</td>
		                    <td>
								<label id="EB03IdLabel2" for="EB03Id2" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[2].description}"/>
								</label>
							<input type="hidden" id="EB03Desc2" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[2].description}"/>
							</td>
		                    <td>&#160;</td>
	
		                  	<td width="4%" >							
								<input type="text" name="eb06Val" id="EB06Id" class="inputbox60" value="${mapping.eb06.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb06SysId" id="EB06SysId" value="${mapping.eb06.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
							<td>
								<label id="EB06IdLabel" for="EB06Id" style="font-size:11px">
										<c:out value="${mapping.eb06.hippaCodeSelectedValues[0].description}" />
								</label>
							</td>
	                  </tr>

					  <tr class="createEditTable1-Listdata ">
		                    <td>&#160;</td>
		                    <td>&#160;</td>
		                    <td>&#160;</td>
							<td >
								<input type="text" name="eb03Val" id="EB03Id3" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[3].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId3" value="${mapping.eb03.hippaCodeSelectedValues[3].hippaCodeValueSysId}" />
							</td>
		                    <td>
								<label id="EB03IdLabel3" for="EB03Id3" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[3].description}"/>
								</label>
							<input type="hidden" id="EB03Desc3" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[3].description}"/>
							</td>
		                    <td>&#160;</td>
		                    <td class="headText" colspan="2">EB09 <a href="#"  onclick="displayInfo('EB09',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB09" onclick="popupDiv('EB09','../ajaxpopup.ajax')"/></a></td>
	                  </tr>

					 <tr class="createEditTable1-Listdata alternate">
							<td></td>
							<td></td>
							<td></td>
							<td >
								<input type="text" name="eb03Val" id="EB03Id4" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[4].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId4" value="${mapping.eb03.hippaCodeSelectedValues[4].hippaCodeValueSysId}" />
								<input type="hidden"  name="eb03Seq" id="EB03SeqId4" value="${mapping.eb03.hippaCodeSelectedValues[0].seq_num}" />								
							</td>
	                    	<td>
								<label id="EB03IdLabel4" for="EB03Id4" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[4].description}"/>
								</label>
							<input type="hidden" id="EB03Desc4" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[4].description}"/>
							</td>
							<td></td>
							<td width="4%" >
								<input type="text" name="eb09Val" id="EB09Id" class="inputbox60" value="${mapping.eb09.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb09SysId" id="EB09SysId" value="${mapping.eb09.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
							<td width="134">
								<label id="EB09IdLabel" for="EB09Id" style="font-size:11px">									
										<c:out value="${mapping.eb09.hippaCodeSelectedValues[0].description}" />
								</label>
							</td>		
					</tr>
					<c:if test="${fn:length(mapping.eb03.hippaCodeSelectedValues) > 5}">
						<c:set var="count" value="5"/>
						<c:set var="rowCount" value="0"/>	
					<c:forEach items="${mapping.eb03.hippaCodeSelectedValues}"	var="eb03Values" begin="5">
						<tr class ="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
						<td width="90px"></td>
							<td width="140px"></td>
							<td width="115px">&#160;</td>
							<td width="90px">
								<input type="text" name="eb03Val" id="EB03Id${count}" class="inputbox60" value="${eb03Values.value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId${count}" value="${eb03Values.hippaCodeValueSysId}" />
								<script>
								$(document).ready(function(){
									$('#EB03Id${count}').blur(function() {
									   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
									   	$("#EB03IdLabel${count}").text('');
									   }
										$('#EB03Id${count}').val($.trim($('#EB03Id${count}').val()));
										$('#EB03Id${count}').val($('#EB03Id${count}').val().toUpperCase());
									});
									$("#EB03Id${count}").autocomplete({ 
										select: function(event, ui) { $("#EB03IdLabel${count}").text(ui.item.id).removeClass('invalid_hippacode_value'); },
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
												displayLabelForSelectedItem(text,list,"EB03IdLabel${count}",invalidHippaCodeValue);
											}
										}
									})
								  });
							</script>
							</td>
							<td width="140px">
								<label id="EB03IdLabel${count}" for="EB03Id${count}" style="font-size:11px">
									<c:out value="${eb03Values.description}"/>
								</label>
							<input type="hidden" id="EB03Desc${count}" name="EB03Desc" value="${eb03Values.description}"/>
							</td>
							<td width="105px">&#160;</td>
							<td width="134px"></td>
							<td width="134px">&#160;</td>	
						</tr>
							<c:set var="count" value="${count + 1}"/>
							<c:set var="rowCount" value="${rowCount + 1}"/>	
					</c:forEach>
					</c:if>
				</tbody>
				</table>
				<div>
					<table border="0">
						<tr>
							<td width="90px"></td>
							<td width="140px"></td>
							<td width="115px">&#160;</td>
							<td width="90px"><a href="#"><img id="eb03AddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForEb03('ebCodesTable','EB03Id','eb03Val','',true,'');"/></a></td>
							<td width="140px">&#160;</td>
							<td width="105px">&#160;</td>
							<td width="134px"></td>
							<td width="134px">&#160;</td>
						</tr>
					</table>
				</div>
			
			<!--Second Table-->		
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 bT mT5">
	                  <tr class="createEditTable1-Listdata">
	                    <td nowrap colspan="2" class="headText">III02 <a href="#"  onclick="displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"  id="III02" onclick="popupDiv('III02','../ajaxpopup.ajax')"/></a></td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
						<td width="100px">&#160;</td>
	                  </tr>
					  <tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >						
								<input type="text" name="ii02Val" class="inputbox60" id="III02Id" value="${mapping.ii02.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="ii02SysId" id="II02SysId" value="${mapping.ii02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td >
							<label id="III02IdLabel" for="III02Id" style="font-size:11px">
									<c:out value="${mapping.ii02.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
						<td width="100px">&#160;</td>
	                  </tr>
					</table>
					
		    		<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3">
		    			<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="messageValueId"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMappingAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
	                  	<tr class="createEditTable1-Listdata">
		                    <td width="500" class="headText">Message &#160;
	                        <input type="checkbox" <c:if test="${mapping.msg_type_required == 'true'}">checked</c:if> name="msgRqdChkBox" id="msgRqdChkBox"/>
							&#160;Required <a href="#"  onclick="displayInfo('MSG',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
                        	<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" onclick="openMessageTextDialog()" id="MESSAGE_TEXT" /></a>
                        	</td>
		                    <td width="92">&#160;</td>
							<td width="69">&#160;</td>
		                    <td colspan="2" nowrap class="headText">Note Type <a href="#"  onclick="displayInfo('NOTE_TYPE_CODE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="NOTE_TYPE_CODE" onclick="popupDiv('NOTE_TYPE_CODE','../ajaxpopup.ajax')"/></a></td>
              			</tr>
					 	<tr class="alternate">
		                    <td width="500">						
									<input type="text" id="messageValueId" name="messageValue" class="inputbox470" value="${mapping.message}" />
							</td>
		                    <td width="92">&#160;</td>
							<td width="69">&#160;</td>
		                    <td width="3%" >						
									<input type="text" name="noteTypeCodeVal" id="NOTETYPEID" class="inputbox60" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].value}" />
									<input type="hidden"  name="noteTypeCodeSysId" id="NOTETYPESysId" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
							<td >
								<label id="NOTETYPEIDLabel" for="NOTETYPEID" style="font-size:11px">
										<c:out value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].description}" />								
								</label>
							</td>
              			</tr>
				  </table>
					
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT10"  id="accumTable">
					<tbody id="accumTbody">     
	                  <tr class="createEditTable1-Listdata">
	                    <td colspan="2" nowrap class="headText">Accumulators &#160;
						
							<input type="checkbox" <c:if test="${mapping.sensitiveBenefitIndicator== 'true'}">checked</c:if> name="accumNtReqdChkBox" id="accumNtReqdChkBox" />
							&#160;Not Required&#160;&#160;&#160;<a href="#"   onclick="displayInfo('ACCUM',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
							<a href="#">
								<img src="${imageDir}/icon-popup.gif" width="15" height="14" onclick="popupAccumDiv('ACCUM','../ajaxaccumpopup.ajax')"/>
							</a>
						</td>
	                  </tr>
					 <c:set var="accumLength" value="${fn:length(accumValues)}"/>
						<tr class="alternate" >
							<td width="3%">
								<input type="text" name="accumulator" id="accumId0" class="inputbox60" value="${mapping.accum.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId" value="${mapping.accum.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td> 
							<td >
								<label id="accumIdLabel0" for="accumId0" style="font-size:11px">
									<c:out value="${mapping.accum.hippaCodeSelectedValues[0].description}" />
								</label>
								<input type="hidden" id="AccumDesc0" name="AccumDesc" value="${mapping.accum.hippaCodeSelectedValues[0].description}"/>
							</td>
							<td ></td>	
						</tr>
						<tr class="">
							<td width="3%"><input type="text" name="accumulator" id="accumId1" class="inputbox60" value="${mapping.accum.hippaCodeSelectedValues[1].value}" />
								<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId1" value="${mapping.accum.hippaCodeSelectedValues[1].hippaCodeValueSysId}" />
							</td> 
							<td >
								<label id="accumIdLabel1" for="accumId1" style="font-size:11px">
									<c:out value="${mapping.accum.hippaCodeSelectedValues[1].description}" />
								</label>
								<input type="hidden" id="AccumDesc1" name="AccumDesc" value="${mapping.accum.hippaCodeSelectedValues[1].description}"/>
							</td>
							<td ></td>
						</tr>
						<tr class="alternate">
							<td width="3%"><input type="textbox" name="accumulator" id="accumId2" class="inputbox60" value="${mapping.accum.hippaCodeSelectedValues[2].value}" />
									<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId2" value="${mapping.accum.hippaCodeSelectedValues[2].hippaCodeValueSysId}" />
							 </td>
							<td >
							<label id="accumIdLabel2" for="accumId2" style="font-size:11px">
								<c:out value="${mapping.accum.hippaCodeSelectedValues[2].description}" />
							</label>
								<input type="hidden" id="AccumDesc2" name="AccumDesc" value="${mapping.accum.hippaCodeSelectedValues[2].description}"/>
							</td>
							<td></td>
						</tr>

					<c:if test="${ fn:length(mapping.accum.hippaCodeSelectedValues) > 3}">
					<c:set var="accumCount" value="3"/>			
					<c:set var="rowCount" value="0"/>	
					<c:forEach items="${mapping.accum.hippaCodeSelectedValues}"	var="accumValues" begin="3">
						<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">						
							<td width="3%">
								<input type="text" name="accumulator" id="accumId${accumCount}" class="inputbox60" value="${accumValues.value}"/>
								<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId${accumCount}" value="${accumValues.hippaCodeValueSysId}" />
								<script>
								$(document).ready(function(){
									$('#accumId${accumCount}').blur(function() {
									   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
									   	$("#accumIdLabel${accumCount}").text('');
									   }
										$('#accumId${accumCount}').val($.trim($('#accumId${accumCount}').val()));
										$('#accumId${accumCount}').val($('#accumId${accumCount}').val().toUpperCase());
									});
									$("#accumId${accumCount}").autocomplete({ 
										select: function(event, ui) { $("#accumIdLabel${accumCount}").text(ui.item.id).removeClass('invalid_hippacode_value'); },
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
												displayLabelForSelectedItem(text,list,"accumIdLabel${accumCount}",invalidHippaCodeValue);
											}
										}
									})
								  });
							</script>
							</td>
							<td width="140px">
								<label id="accumIdLabel${accumCount}" for="accumId${accumCount}" style="font-size:11px">
									<c:out value="${accumValues.description}"/>
								</label>
								<input type="hidden" id="AccumDesc${accumCount}" name="AccumDesc" value="${accumValues.description}"/>
							</td>	
							<td/>						
						</tr>
							<c:set var="accumCount" value="${accumCount + 1}"/>
							<c:set var="rowCount" value="${rowCount + 1}"/>	
					</c:forEach>
					</c:if>						 	
				</tbody>					
				</table>
				<div class="fL mR10 pT5"><a href="#"><img id="accumAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForAccum('accumTable','accumId','accumulator','',true,'');"/></a></div>
				
					<!--  UM Rule Table starts here )-->
					
					<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="Pd3 mT5 bT">
						<tr class="createEditTable1-Listdata">
							<td class="headText">UM Rule <a href="#" onclick="displayInfo('UMRULE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
								<img src="${imageDir}/icon-popup.gif" width="15" height="14" id="UMRULE" onclick="popupDiv('UMRULE','../ajaxpopup.ajax')" />
							</td>
						</tr>
					</table>
	
					<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="Pd3" id="umRuleTable">
					<tbody id="umRuleTbody">
						<tr class="createEditTable1-Listdata alternate">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId0" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId0" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
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
							 <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						</tr>
						<tr class="createEditTable1-Listdata white-bg">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId1" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId1" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].hippaCodeValueSysId}" />
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
							 <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						</tr>
						<tr class="createEditTable1-Listdata alternate">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId2" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId2" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].hippaCodeValueSysId}" />
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
							 <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						</tr>
						<tr class="createEditTable1-Listdata white-bg">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId3" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId3" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].hippaCodeValueSysId}" />
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
							 <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						</tr>
						<tr class="createEditTable1-Listdata alternate">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId4" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId4" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].hippaCodeValueSysId}" />
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
							 <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						     <td>&#160;</td>
						</tr>
						<c:if test="${fn:length(mapping.utilizationMgmntRule.hippaCodeSelectedValues) > 5}">
						
							<c:set var="count" value="5"/>
						<c:forEach items="${mapping.utilizationMgmntRule.hippaCodeSelectedValues}"	var="umRuleValues" begin="5">
							<input type="hidden"  name="umRuleSysId" id="umRuleSysId${count}" value="${umRuleValues.hippaCodeValueSysId}" />
							<script type="text/javascript">
								insertUmRule('umRuleTable','UMRuleId${count}','umRuleVal','${umRuleValues.value}',false,'UMRuleIdLabel${count}','${umRuleValues.description}','UMRuleDesc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
						</c:if>
						</tbody>
					</table>
	
					<div class="fL pT5">
						<img id="umRuleAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForUmRule('umRuleTable','UMRuleId','umRuleVal','',true,'')" />
					</div>
										
				<!--Third Table-->	

				<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
					<tr class="createEditTable1-Listdata">
	                    <td nowrap colspan="2" class="headText">HSD01 <a href="#"  onclick="displayInfo('HSD01',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD01" onclick="popupDiv('HSD01','../ajaxpopup.ajax')" /></a></td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" nowrap colspan="2">HSD05 <a href="#" onclick="displayInfo('HSD05',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD05" onclick="popupDiv('HSD05','../ajaxpopup.ajax')"/></a></td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
	                  <tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >							
								<input type="text" name="hsd01" id="HSD01Id" class="inputbox60" value="${mapping.hsd01.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd01SysId" id="HSD01SysId" value="${mapping.hsd01.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="110px">
							<label id="HSD01IdLabel" for="HSD01Id" style="font-size:11px">
									<c:out value="${mapping.hsd01.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="3%" >						
								<input type="text" name="hsd05" id="HSD05Id" class="inputbox60" value="${mapping.hsd05.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd05SysId" id="HSD05SysId" value="${mapping.hsd05.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td >
							<label id="HSD05IdLabel" for="HSD05Id" style="font-size:11px">
									<c:out value="${mapping.hsd05.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>
					<tr class="createEditTable1-Listdata">
	                    <td nowrap colspan="2" class="headText">HSD02 <a href="#" onclick="displayInfo('HSD02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" nowrap colspan="2">HSD06 <a href="#" onclick="displayInfo('HSD06',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >						
								<input type="text" maxlength="5" name="hsd02" id="HSD02Id" class="inputbox60" value="${mapping.hsd02.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd02SysId" id="HSD02SysId" value="${mapping.hsd02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td>
							<label id="HSD02IdLabel" for="HSD02Id" style="font-size:11px">
									<c:out value="${mapping.hsd02.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="3%" >						
								<input type="text" maxlength="5" name="hsd06" id="HSD06Id" class="inputbox60" value="${mapping.hsd06.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd06SysId" id="HSD06SysId" value="${mapping.hsd06.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
	                    <td>
							<label id="HSD06IdLabel" for="HSD06Id" style="font-size:11px">
									<c:out value="${mapping.hsd06.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>	
					 <tr class="createEditTable1-Listdata">
	                    <td nowrap colspan="2" class="headText">HSD03 <a href="#" onclick="displayInfo('HSD03',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD03" onclick="popupDiv('HSD03','../ajaxpopup.ajax')"/></a></td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" nowrap colspan="2">HSD07 <a href="#" onclick="displayInfo('HSD07',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD07" onclick="popupDiv('HSD07','../ajaxpopup.ajax')"/></a></td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >						
								<input type="text" name="hsd03" id="HSD03Id"  class="inputbox60" value="${mapping.hsd03.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd03SysId" id="HSD03SysId" value="${mapping.hsd03.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td>
							<label id="HSD03IdLabel" for="HSD03Id" style="font-size:11px">
									<c:out value="${mapping.hsd03.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="3%" >					
								<input type="text" name="hsd07" id="HSD07Id" class="inputbox60" value="${mapping.hsd07.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd07SysId" id="HSD07SysId" value="${mapping.hsd07.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
	                    <td>
							<label id="HSD07IdLabel" for="HSD07Id" style="font-size:11px">
									<c:out value="${mapping.hsd07.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>	
					 <tr class="createEditTable1-Listdata">
	                    <td nowrap colspan="2" class="headText">HSD04 <a href="#" onclick="displayInfo('HSD04',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" nowrap colspan="2">HSD08 <a href="#" onclick="displayInfo('HSD08',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD08" onclick="popupDiv('HSD08','../ajaxpopup.ajax')"/></a></td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >						
								<input type="text" maxlength="5" name="hsd04" id="HSD04Id" class="inputbox60" value="${mapping.hsd04.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd04SysId" id="HSD04SysId" value="${mapping.hsd04.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td >
							<label id="HSD04IdLabel" for="HSD04Id" style="font-size:11px">
									<c:out value="${mapping.hsd04.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="3%" >							
								<input type="text" name="hsd08" id="HSD08Id" class="inputbox60" value="${mapping.hsd08.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd08SysId" id="HSD08SysId" value="${mapping.hsd08.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td >
							<label id="HSD08IdLabel" for="HSD08Id" style="font-size:11px">
									<c:out value="${mapping.hsd08.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>			
					</table>	
		    <!--Fourth Table-->	
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
					
                    <tr>
	                    <td  class="headText" colspan="2" nowrap>&#160;
	                   <input type="checkbox" <c:if test="${mapping.finalized==true}">checked</c:if> name="notFinalizedChkBox" id="notFinalizedChkBox" value="checked"/>&#160;Not Finalized&#160;&#160;&#160;
						</td>
	                  </tr>
	                  <tr class="">
	                    <td width="900px" class="headText">Change Comments</td>
					  </tr>
					  <tr class="">
	                    <td>
						<textarea id="changeCommentsId" name="changeComments"  rows="5" cols="110" >${changeComments}</textarea></td>
							
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
				<input type="hidden" id="selectedvariableIdCopyTo" value="" name="selectedvariableIdCopyTo"/>
 				<input type="hidden" id="selectedvariableId" value="" name="selectedvariableId"/>
				<input type="hidden" id="selectedvariableDesc" value="" name="selectedvariableDesc"/>
				<input type="hidden" id="selectedvariableIdForDone" value="" name="selectedvariableIdForDone"/>
				<input type="hidden" id="selectedvariableDescForDone" value="" name="selectedvariableDescForDone"/>
				<input type="hidden" id="mappingSysId" value="${mapping.variableSystemId}" name="mappingSysId" />
				<input type="hidden" id="token" value="${TRANSACTION_TOKEN_KEY}" name="tokenKey" />
				<c:set var="discardChange" value="0"/>				
				<c:if test="${mapping.inTempTable == 'N'}">
					<c:set var="discardChange" value="1"/>
				</c:if>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='openAlertDialog();'>Copy To</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>		       
		        <td width="0" height="20"><a href="#" onclick ='openViewHistoryDialog("${mapping.variable.variableId}");'>View history</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.variable.variableId}","${variableList[0].variableFormat}","save");'>Save</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.variable.variableId}","${variableList[0].variableFormat}","done");'>Done</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick ='cancel();'>Cancel</a></td>
		       

				<c:if test="${mapping.variableMappingStatus !='NOT_APPLICABLE'}" > 
					<c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='notApplicable("${mapping.variable.variableId}");'>Not Applicable</a></td>
					</c:if>
				</c:if>
				
				<c:if test="${mapping.variableMappingStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mapping.variableMappingStatus != 'SCHEDULED_TO_TEST' &&
							mapping.variableMappingStatus != 'NOT_APPLICABLE' &&
							mapping.variableMappingStatus != 'PUBLISHED'}">		
						 <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.variable.variableId}","${variableList[0].variableFormat}","sendToTest");'>Send to Test</a></td>
				</c:if>

				<c:if test="${mapping.variableMappingStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mapping.variableMappingStatus != 'SCHEDULED_TO_TEST' &&
							mapping.variableMappingStatus != 'NOT_APPLICABLE' &&
							mapping.variableMappingStatus != 'PUBLISHED'}">						
					<c:if test="${userUIPermissions.authorizedToapprove}">
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='runSpellCheck("${mapping.variable.variableId}","${variableList[0].variableFormat}","approve");'>Approve</a></td>				
					</c:if>
				</c:if>

				<c:if test="${discardChange != 1}">
					<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
			        <td width="0" height="20"><a href="#" onclick='discardChanges("${mapping.variable.variableId}","${mapping.variableSystemId}");' class="dicard">Discard Changes</a></td>				
					<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</c:if>

				<c:if test="${discardChange == 1}">					
					<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</c:if>

		      </tr>
		    </table>					
		</div>	
	
	</div>
	
	<!-- wrapper Ends-->
<div id="copyToDialog" >
	<%@include file="/WEB-INF/jsp/copyToPage.jsp"%>
</div>
</form>
<div id="viewHistoryDialog" title="View History">
</div>
<div id="hippaCodePopUpDiv">
<div id="accumPopUpDiv">
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
</div>
<script type="text/javascript">
	sniffer();
</script>

<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<div id="confirmationDiv"></div>
<div id="messageTextDialog">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="Pd3">
        <tr class="createEditTable1-Listdata">
          <td colspan="6" nowrap class="headText">EBO3 &#160;</td>
          <td><input type="text" name="msgTxtEb03" class="inputbox60" id="msgTxtEb03Id" value=""/></td>
          <td colspan="6" nowrap class="headText">&#160;Variable ID &#160;</td>
          <td><input type="text" name="msgTxtVar" class="inputbox60" id="msgTxtVarId" value=""/></td>
          <td colspan="6" nowrap class="headText">&#160;Message Text &#160;</td>
          <td><input type="text" name="msgTxt" class="inputbox60" id="msgTxtId" value=""/></td>
        </tr>
    </table>     
    <table width="100%" height="20" ><tr><td></td></tr></table>
	<table  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>  
		<td>
			<a href="#" onclick="searchMessageText('1','Init')"><img src="${imageDir}/search_but.png" 
			alt="Search" title="Search"/></a>
		</td>	
      </tr>
	</table>
	<table width="100%" height="20" ><tr><td>
		<input type="hidden" id="hdMsgTxtId" name="hdMsgTxt"/>
		<input type="hidden" id="hdMsgTxtEB03Id" name="hdMsgEB03Txt"/>
		<input type="hidden" id="hdMsgTxtHdrId" name="hdMsgHdrTxt"/>
			</td></tr></table>
	<div id="messageTextResult"></div>
</div>
<div id="alertDiv"></div>
<div id="confirmationDivDiscardChanges"></div>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>
