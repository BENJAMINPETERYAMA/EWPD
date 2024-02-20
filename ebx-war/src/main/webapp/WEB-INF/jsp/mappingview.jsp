<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>

<body class="normal">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
//copyto starts
var diaClose = false;
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
	//$('#auditTrailsTable tr:last').css('border:bottom','1px solid #d9e5eb');
  });

    function copyTo(){		
		$("#variableIdForCopyTo").val('');
		$("#createIdLabel").text('');
			$("#copyToDialog").dialog({
				 		height:'auto',
						width:'450px',
						title: 'Copy to Variable',
                        show:'slide',
                        modal: true,
                        close: function(event, ui)
        				{
        					document.getElementById('copyToDialog').style.display='none';
          				 // $("#copyToDialog").dialog('destroy').remove();
        				}
			});
			//$("#copyToDialog").css("display","block");			
			$("#copyToDialog").dialog('open');	
			document.getElementById('copyToDialog').style.display='block';
					
	}	
  

function callCopyTo(){
		
		var variableIdForCopyTo =$("#variableIdForCopyTo").val();
		var oldVarID= $("#variableIdentifier").val();
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
				document.forms['viewForm'].action="${context}/createpage/copyTo.html";
	        	document.forms["viewForm"].submit();
			}
		}

     

function addMessages(data){
	
		var infoMessages = data.info_messages;
		var errorMessages = data.error_messages;
		
		if(typeof(infoMessages) != 'undefined' && infoMessages.length >0) {
			for(i=0; i< infoMessages.length; i++) {
				addInfoToNotificationTray(infoMessages[i]);
			}		
		}
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
		}
		openTrayNotification();

	}
//copyto ends
	function retreiveAuditTrailInDetail(){
	
		var variableId=$('#variableIdentifier').val();
		
		$.ajax({
			url: "${context}/ajaxviewaudittrailindetail.ajax",
			dataType: "html",
			type: "POST",
			data: "variableId="+encodeURIComponent(variableId),
			success: function(data) {
				$("#viewAuditTrailInDetail").html(data);
				$("#viewAuditTrailInDetail").dialog({
						            minHeight : 150,
									maxHeight : 650,
									width : 999,
									position : 'center',
									resizable : true,
									title : 'Mapping Log',
						            modal : true
				});
			}
			});
	}

	function viewAllAuditTrail(variableId) { 
	
		var fromMappingView = $("#fromMappingView").val();
		$.ajax({
			url: "${context}/ajaxviewallaudittrail.ajax",
			dataType: "html",
			type: "POST",
			data: "variableId="+encodeURIComponent(variableId)+"&fromMappingView="+fromMappingView,
				success: function(data) {
											
					$("#viewAuditTrailId").html(data);
				}
			});
    }
    
    function showExtndMessagesScreen(actionId)  {
    
    $("#extendedMsgScreen").dialog({
	                       height:'400',
						width:'690px',	
						resizable:false,
	                       show:'slide',
						title: 'Extended Message Screen',
						autoOpen:false,
	                       modal: true
	                      
	});
	
	 
	 if(actionId=="extdMsgEBO1"){
	 var msg1 = $("#eb01ExtndMsg1").val();
			var msg2 = $("#eb01ExtndMsg2").val();
			var msg3 = $("#eb01ExtndMsg3").val();
			var networkInd = $("#eb01NetworkInd").val();
			setExtndMsgs(msg1,msg2,msg3,networkInd);
			
	 }
	 else{
    var extdMsg=$("#eb03ExtndMsgJsonObj").val();
      
    var eb03Value=$("#EB03Label" + actionId.substring(11)).val();
   
 
   // var flag=true;
		if(extdMsg !== ""){			
			var msgObj = JSON.parse(extdMsg);
			for(i=0; i< msgObj.length; i++) {
				var obj = msgObj[i];
				if(obj.eb03Val === eb03Value){
					var ms1 = obj.eb03ExtndMessage1;
					if(ms1 === "null"){ms1 = ""}
					var ms2 = obj.eb03ExtndMessage2;
					if(ms2 === "null"){ms2 = ""}
					var ms3 = obj.eb03ExtndMessage3;
					if(ms3 === "null"){ms3 = ""}
					var netwInd = obj.eb03NetworkInd;
					if(netwInd === "null"){netwInd = ""}
					setExtndMsgs(ms1,ms2,ms3,netwInd);
					//flag=false;
					break;
				}
			}
		}}//if(flag){
			//setExtndMsgs("","","","");
		//}
     $("#extendedMsgScreen").dialog('open');
    }
    
    function setExtndMsgs(msg1,msg2,msg3,networkInd){
    
    var varId=$('#variableIdentifier').val();;
    
    $("#varIdDiag").text(varId);
	$("#extndMessage1").text(msg1);
	$("#extndMessage2").text(msg2);
	$("#extndMessage3").text(msg3);
	$("#networkInd").text(networkInd);
}
function showHpnMapgMessagesScreen(){
	$("#hpnMapgMsgScreen").dialog({
	                       height:'530',
						width:'690px',	
						resizable:false,
	                       show:'slide',
						title: 'HPN Mapping Message Screen',
						autoOpen:false,
	                       modal: true
	});
	$("#hpnMapgMsgScreen").dialog('open');
 }

$(window).ready(function()
    {
	var eb03ExtndMsgJSON = [];
	var arr = ${currentMapping.eb03.extendedMsgsForSelectedValues}; 
	
	for (i = 0; i < arr.length; i++) {				
		eb03ExtndMsgJSON.push({"eb03Val": arr[i].value, "eb03ExtndMsgSysId": arr[i].extendedMsgValueSysId, "eb03NetworkInd": arr[i].networkInd, "changeInd": "",
								 "eb03ExtndMessage1": arr[i].extndMsg1,"eb03ExtndMessage2":arr[i].extndMsg2, "eb03ExtndMessage3":arr[i].extndMsg3});
	}
	eb03ExtndMsgJSON = JSON.stringify(eb03ExtndMsgJSON);
	 
	if($("#eb03ExtndMsgJsonObj").val() == ""){
		$("#eb03ExtndMsgJsonObj").val(eb03ExtndMsgJSON);
	}
});
    
    
function printPage(){

	var url = "${context}/viewmappingpage/printMapping.html";

	
	var variableId = $("#variableIdentifier").val();
	
	var param="?variableId="+encodeURIComponent(variableId);
	
	var mappingstatus = $("#mappingstatus").val()
	
	if (typeof(mappingstatus) != 'undefined') {
		param = param+"&mappingstatus="+$("#mappingstatus").val();
	}
	console.log(param);
	url=url+param;
	//newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  	window.open(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  }	
  function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
  }
  $(document).ready(function(){
  		var hsd02Val = $('#hsd02').text();
		if(hsd02Val.length > 3){
			moveHSD02Values();
		}
	});
  function moveHSD02Values(){
	var hsd02Val = $('#hsd02').text();
	var hsdConstVal = 'hsd02-0';
	var hsdDescAppender = '-Desc';
	var prevVal = hsd02Val;
	var nextVal = hsd02Val;
	var constVarName = '';
	//careful about the loop decreasing value
	var valToToggleAddButton=0;
	var temp;
	for(var i=5;i>0;i--){
		temp = i+1;
		constVarName = '#'+hsdConstVal+ i;
		nxtVarName = '#'+hsdConstVal+ temp;
		if($(constVarName).text().length > 1){
			//showing the cell
			var tempVal = $(constVarName).text();
			
			
			$(nxtVarName).text($(constVarName).text());
			setDescriptionLabel(temp,tempVal);
		}
	}
	
		constVarName = '#'+hsdConstVal+ 1;
		$(constVarName).text($('#hsd02').text());
		setDescriptionLabel(1,$('#hsd02').text());
		$('#hsd02').text('');
	}
	function setDescriptionLabel(id,varId){
	var constVar = '#hsd02-0'+id+'-Desc';
	$(constVar).text('');
	$.ajax({
				url: "${context}/ajaxautocompletelistcreatepage.ajax",
				dataType: "json",
				type:"POST",
				data: "key="+varId+ "&name=HSD02",
				success: function(resp){
				$(constVar).text(resp.rows[0].id);

				}
			});
	}
		
	function isEBSegVal(){
		var viewEBSegValues = viewEBSegVal();
		//if(!viewEBSegValues)
		var flag = ebMapAssoc();
		return (chkEBSegHSD02LimVal() && flag);
	}
	
	function viewEBSegVal(){
		return  ($.trim("${currentMapping.eb01.hippaCodeSelectedValues[0].value}") == "") && 
				($.trim("${currentMapping.eb02.hippaCodeSelectedValues[0].value}") == "") &&
				($.trim("${currentMapping.eb06.hippaCodeSelectedValues[0].value}") == "") && 
				($.trim("${currentMapping.eb09.hippaCodeSelectedValues[0].value}") == "") ;
	}
	function ebMapAssoc(){
	var varId = $("#variableIdentifier").val();
	$.ajax({
		url: "${context}/ajaxautocompletelistcreatepage.ajax",
		async: false,//sorry couldn't see any option. i would have a function call in success but u guess...
		dataType: "json",
		type:"POST",
		data: "key="+varId+ "&name=VARIABLEID",
		success: function(resp){
			if(resp.ebMappingAssocDetails != "[]"){
				var errMsg = "EB Mapping already Associated with "+ resp.ebMappingAssocDetails;
					addErrorToNotificationTray(errMsg);
					openTrayNotification();
					$('#userCommentsDialog').dialog("destroy");
					diaClose = true;
				return false;
			}
		}
	});
	return true;
	}
	function chkEBSegHSD02LimVal(){
	var list =new Array();
	var issueVar = new Array();
	for(var i =1;i<7;i++){ 
		if($.trim($('#hsd02-0'+i).text()) != "")
		list.push($.trim($('#hsd02-0'+i).text()));
	}
	
	issueVar = callAjaxData(list);
	if(issueVar!=""){
		var errMsg = "EB Segment present in ["+issueVar.toString()+"]";
		addErrorToNotificationTray(errMsg);
		openTrayNotification();
		exit();//I know this is going to abruptly terminate the script and there is no function defined exit() in java script. I'm not an idiot.
		return false;
	}
	
	return true;
	}
	
	function callAjaxData(list){
	var issueVar = new Array();
	for(var i =0;i<list.length;i++){
		var varId = list[i];
		$.ajax({
			url: "${context}/ajaxautocompletelistcreatepage.ajax",
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
</script>
<form name="viewForm"  method ="post">
<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
<div id="variableInfoDiv" class="overflowContainerVariableView">
<table border="0" cellpadding="0" cellspacing="0" width="900px"	class="Pd3">
<THEAD>
	<tr class="createEditTable1">
			<td width="100px" class="tableHeader">Variable ID</td>
			<td width="220px"  class="tableHeader">Description</td>
			<td width="50x"  class="tableHeader"> PVA</td>
			<td width="100px" class="tableHeader">Data type</td>
			<td width="75px" class="tableHeader">Category</td>
			<td width="70px" class="tableHeader">System</td>			
			<td width="150px" class="tableHeader">Minor Heading</td>
			<td width="160px" class="tableHeader">Major Heading</td>
			<td width="140px" class="tableHeader">WPD Accumulator</td>
			<td align="center"><a href="#" onclick='printPage();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a></td>
	
	</tr>
</THEAD>
<TBODY>
	<c:set var="rowCount" value="0" />
	<c:if test="${! empty currentViewVariable}">
	<input type="hidden" id="variableIdentifier" value="${currentViewVariable[0].variableId}"/>
	<tr>
		<td>${currentViewVariable[0].variableId}
				<c:if test="${currentMapping.auditLock == 'Y' && currentMapping.variableMappingStatus != 'UNMAPPED' }">
				<img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" id="AuditLockViewImg_${currentViewVariable[0].variableId}" style="height:9px;width:9px" />
				</c:if>
				<c:if test="${currentMapping.auditLock == 'N' && currentMapping.variableMappingStatus != 'UNMAPPED' }">
				<div id="AuditLockViewImg_${currentViewVariable[0].variableId}"  ></div>
				</c:if>
		</td>
		<td>${currentViewVariable[0].description}</td>
		<td>${currentViewVariable[0].pva}</td>
		<td>${currentViewVariable[0].dataType}</td>	
		<td>						
			<c:if test="${currentViewVariable[0].lgCatagory != null }">
					${currentViewVariable[0].lgCatagory}
			</c:if>
			<c:if test="${currentViewVariable[0].lgCatagory == null && currentViewVariable[0].isgCatagory != null}">
					${currentViewVariable[0].isgCatagory}
			</c:if>
		</td>
		<td>${currentViewVariable[0].variableSystem}</td>		
		<td>${currentViewVariable[0].minorHeadings}</td>
		<td>${currentViewVariable[0].majorHeadings}</td>
		<td>${accumList[0]}</td>
		<td></td>	
	</tr>	
	<c:set var="variableInfoDivScroll" value="false" />	
	 <c:set var="counter" value="1" />
	
	<c:forEach items="${currentViewVariable}" var="viewVar"  begin="1">
		<c:set var="variableInfoDivScroll" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
				<td width="100px"></td>
				<td width="150px"></td>
				<td width="90x"></td>
				<td width="90px"></td>
				<td width="75px"></td>
				<td width="75px"></td>
				<td width="170px">${viewVar.minorHeadings}</td>
				<td width="170px">${viewVar.majorHeadings}</td>	
				<td width="75px">
					${accumList[counter]}					
				</td>	
				<td width="75px"></td>		
		</tr>
	<c:set var="rowCount" value="${rowCount + 1}"/>
	<c:set var="counter" value="${counter + 1}"/>
	</c:forEach>
	
	<c:forEach items="${accumList}" var="accum"  begin="${counter}">
		<c:set var="variableInfoDivScroll" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
				<td width="100px"></td>
				<td width="150px"></td>
				<td width="90x"></td>
				<td width="90px"></td>
				<td width="75px"></td>
				<td width="75px"></td>
				<td width="170px"></td>
				<td width="170px"></td>	
				<td width="75px">
					${accum}					
				</td>	
				<td width="75px"></td>		
		</tr>
	<c:set var="rowCount" value="${rowCount + 1}"/>
	<c:set var="counter" value="${counter + 1}"/>
	</c:forEach>
	<c:if test="${variableInfoDivScroll == 'true'}">
			<script>
			$('#variableInfoDiv').height('80px');
			</script>
	</c:if>
	</c:if>
</TBODY>
</table>
</div>
<table border="0" cellpadding="0" cellspacing="0" width="880px" class="Pd3">
<c:if test="${! empty currentMapping}">
	<tr>
		<td width="100px"></td>
		<td width="150px"></td>
		<td width="90x"></td>
		<td width="90px"></td>
		<td width="75px"></td>
		<td width="170px"></td>
		<td width="170px" id="statusImageContainer" align="right">		
			<input type="hidden" id="mappingstatus" value="${currentMapping.variableMappingStatus}"/>
			<c:if test="${currentMapping.variableMappingStatus == 'BUILDING'}">
				<img src="${imageDir}/building.gif" />
			</c:if>
			<c:if test="${currentMapping.variableMappingStatus == 'BEING_MODIFIED'}">
				<img src="${imageDir}/modified.gif" />
			</c:if>
			<c:if test="${currentMapping.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
				<img src="${imageDir}/totest.gif" />
			</c:if>
			<c:if test="${currentMapping.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION'}">
				<img src="${imageDir}/toproduction.gif" />
			</c:if>
			<c:if test="${currentMapping.variableMappingStatus == 'PUBLISHED'}">
				<img src="${imageDir}/published.gif" />
			</c:if>	
			<c:if test="${currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
				<img src="${imageDir}/transferred.gif" />	
			</c:if>	
			<c:if test="${currentMapping.variableMappingStatus == 'NOT_APPLICABLE'}">
				<img src="${imageDir}/notapplicable.gif" />
			</c:if>
			<input type="hidden" name="fromMappingView" value="true" id="fromMappingView"/>
		</td>			
	</tr>
</c:if>
</table>

<c:if test="${! empty currentMapping}">
<c:set var="currentMapping" value="${currentMapping}"/>				
	<div id="viewEditContainerPopup">	<!--viewEditContainer Starts-->
	<input type="hidden" id="eb01ExtndMsg1" name="eb01ExtndMsg1" value="${currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg1}"/>
	<input type="hidden" id="eb01ExtndMsg2" name="eb01ExtndMsg2" value="${currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg2}"/>
	<input type="hidden" id="eb01ExtndMsg3" name="eb01ExtndMsg3" value="${currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg3}"/>
	<input type="hidden" id="eb01NetworkInd" name="eb01NetworkInd" value="${currentMapping.eb01.extendedMsgsForSelectedValues[0].networkInd}"/>
			<table width="320" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">				
				<tr class="createEditTable1-Viewdata">
					<td width="50px" class="headText">EB01</td>
					
					<td width="100px" id="EB01">${currentMapping.eb01.hippaCodeSelectedValues[0].value}</td>		
					
					<td width="450px">${currentMapping.eb01.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td class="headText" colspan="1">Procedures Excluded indicator</td>
					<td>${currentMapping.procedureExcludedInd}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">EB02</td>
					<td id="EB02">${currentMapping.eb02.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.eb02.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">EB06</td>
					<td id="EB06">${currentMapping.eb06.hippaCodeSelectedValues[0].value}</td>
					<td>${currentMapping.eb06.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">EB09</td>
				<td id="EB09">${currentMapping.eb09.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.eb09.hippaCodeSelectedValues[0].description}</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">Start Age</td>
				<td >${currentMapping.startAge.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.startAge.hippaCodeSelectedValues[0].description}</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">End Age</td>
				<td >${currentMapping.endAge.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.endAge.hippaCodeSelectedValues[0].description}</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				
				
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td class="headText" colspan="1">Finalized</td>
					<td>${currentMapping.mappingComplete}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td class="headText" colspan="1">Accumulator not required indicator</td>
					<td >${currentMapping.sensitiveBenefitIndicator}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">Accumulators</td>
					<td >${currentMapping.accum.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.accum.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
					<c:forEach items="${currentMapping.accum.hippaCodeSelectedValues}" var="accumulatorVal" begin="1">	
						<tr>
						<td  class="headText"></td>						
						<td >${accumulatorVal.value}</td>
						<td >${accumulatorVal.description}</td>			
						<td>&#160;</td>
						<td>&#160;</td>									
						</tr>
					</c:forEach>					
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>	
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">UM Rule</td>
					<td>${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}</td>
					<td   style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="430px">${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td style="vertical-align:bottom;">
						<c:if test="${not empty currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}">
							<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId0" title="View Rule" height="13px" onclick="viewRuleSequenceInDetail('${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}','/ajaxruleview.ajax');"/>
						</c:if>
					</td>
					<c:forEach items="${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues}" var="umRuleVal" begin="1" varStatus="status">	
						<tr>
						<td  class="headText"></td>						
						<td>${umRuleVal.value}</td>
						<td  style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="430px">${umRuleVal.description}</td>	
						<td>&#160;</td>
						<td style="vertical-align:bottom;"><img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId${status.index}" title="View Rule" height="13px" onclick="viewRuleSequenceInDetail('${umRuleVal.value}','/ajaxruleview.ajax');"/></td>											
						</tr>
					</c:forEach>					
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>		
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD01</td>
					<td >${currentMapping.hsd01.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd01.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02</td>
					<td id="hsd02">${currentMapping.hsd02.hippaCodeSelectedValues[0].value}</td>
					<td id="hsd02-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02-01</td>
					<td id="hsd02-01">${currentMapping.hsd02.hippaCodeSelectedValues[1].value}</td>
					<td id="hsd02-01-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[1].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02-02</td>
					<td id="hsd02-02">${currentMapping.hsd02.hippaCodeSelectedValues[2].value}</td>
					<td id="hsd02-02-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[2].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02-03</td>
					<td id="hsd02-03">${currentMapping.hsd02.hippaCodeSelectedValues[3].value}</td>
					<td id="hsd02-03-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[3].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02-04</td>
					<td id="hsd02-04">${currentMapping.hsd02.hippaCodeSelectedValues[4].value}</td>
					<td id="hsd02-04-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[4].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02-05</td>
					<td id="hsd02-05">${currentMapping.hsd02.hippaCodeSelectedValues[5].value}</td>
					<td id="hsd02-05-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[5].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02-06</td>
					<td id="hsd02-06">${currentMapping.hsd02.hippaCodeSelectedValues[6].value}</td>
					<td id="hsd02-06-Desc">${currentMapping.hsd02.hippaCodeSelectedValues[6].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD03</td>
					<td >${currentMapping.hsd03.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd03.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD04</td>
					<td>${currentMapping.hsd04.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd04.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>	
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD05</td>
					<td >${currentMapping.hsd05.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd05.hippaCodeSelectedValues[0].description}</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td class="headText">HSD06</td>
					<td >${currentMapping.hsd06.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd06.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD07</td>
					<td >${currentMapping.hsd07.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd07.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD08</td>
					<td >${currentMapping.hsd08.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd08.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText" colspan="1">2120 Loop NM1 Message Segment</td>
					<td >${currentMapping.nm1MessageSegment.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.nm1MessageSegment.hippaCodeSelectedValues[0].description}</td>
					<td>&#160;</td>
					<td>&#160;</td>
				</tr>				
			</table>
			
			<div id="viewAuditTrailId">
				<div class="mL10 link fL mR10" >
				
				<c:if test="${(currentMapping.eb01.hippaCodeSelectedValues[0].value=='D')&&
				(! empty currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg1||
				! empty currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg2||
				! empty currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg3)}">
				
				
				
					
					<a href="#" style="color: #077eed; font-size: 11px;" id="extdMsgEBO1" onclick="showExtndMessagesScreen(this.id)">Ext msg</a>	
					</c:if>
					<c:if test="${!empty hpnMapgList}">
					<a href="#" style="color: #077eed; font-size: 10px;" id="hpnMapgMsgLink" onclick="showHpnMapgMessagesScreen()">&#160;&#160;HPN Mapg</a>
                   	</c:if>
				</div>	
				<div class="mL10 link fR mR10"  title="View Audit Trail">
					<c:if test="${sizeOfAuditTrail == 20}">
					<a href="#"  style="color:blue;" onclick ='viewAllAuditTrail("${currentViewVariable[0].variableId}")';>View All</a>
					</c:if>
				</div>
				<div>
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="520px" class="Pd2 auditTrailTable" id="auditTrailsTable" >
								<tr class="createEditTableShade">
									<td width="90px" class="headText">Date</td>
									<td width="93px" class="headText">User ID</td>
									<td width="180px" class="headText">System Comment</td>
									<td width="250px" class="headText">User Comment</td>
								</tr>
							
								<c:if test="${empty auditTrailList}">
									No audit trail found
								</c:if>
								<c:if test="${! empty auditTrailList}">									
								<c:set var="viewHistoryRowCount" value="0" />
								<c:forEach items="${auditTrailList}" var="viewAudit">
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  >	
										<fmt:timeZone value="PST">
										<td width="90px"><c:out value="${viewAudit.createdAuditDate}"/></td>
										<td width="93px">${viewAudit.createdUser}</td>
										<td width="180px">
										<c:if test="${viewAudit.systemComments != 'MODIFIED'}">
										<c:choose>
											<c:when test="${viewAudit.mappingStatus == 'PUBLISHED'}">
												${viewAudit.mappingStatus}
												<a href="#" style="color:blue;" onclick="retreiveAuditTrailInDetail();" >View&nbsp;Details</a>
											</c:when>
											<c:otherwise>${viewAudit.systemComments}</c:otherwise>
										</c:choose>
										</c:if>
										<c:if test="${viewAudit.systemComments == 'MODIFIED'}">
											${viewAudit.systemComments}
										</c:if>
										</td>
										<td width="250px">${viewAudit.userComments}</td>
										</fmt:timeZone>	
									</tr>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
						</table>
						</c:if>
				</div>
			</div>
						




<div>
	<table border="0" cellpadding="0" cellspacing="0" width="800px" >
		<tr> <td width="800px" colspan="5">&nbsp;</td> </tr>
	</table>
</div>
<c:if test="${! empty eb03Associations}">	
<div id="viewAuditTrailId">
				<div class="mL10 link fL mR10"  title="">										
				</div>
				<div>
						<div>&nbsp;</div>
						<input type="hidden" id="eb03ExtndMsgJsonObj" name="eb03ExtndMsgJsonObj" />
							<table border="0" cellpadding="0" cellspacing="0" width="800px" class="Pd2 auditTrailTable" id="auditTrailsTable" >
								<tr class="createEditTableShade" >
									<td width="150px" valign="middle" class="headText">EB03</td>
									<td width="100px" valign="middle" class="headText">III02</td>
									<td width="300px" valign="middle" class="headText">Message</td>
									<td width="100px" valign="middle" class="headText">Msg Reqd</td>
									<td width="100px" valign="middle" class="headText">Extnd Msg</td>
									<td width="150px" valign="middle" class="headText">Note Type</td>
								</tr>
							
								<c:set var="count" value="0"/>								
								<c:set var="viewHistoryRowCount" value="0" />
								
								<c:forEach items="${eb03Associations}" var="EB03Association"  >
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" height="20px" valign="middle" >
										<c:choose>
											<c:when test="${! empty EB03Association.eb03.value}">
											
										<input type="hidden" id="EB03Label${count}" value="${EB03Association.eb03.value}"/>
												<td width="150px">${EB03Association.eb03.value} - ${EB03Association.eb03.description}</td>
											</c:when>
											<c:otherwise><td width="150px">-</td></c:otherwise>
										</c:choose>	
										<c:choose>
											<c:when test="${! empty EB03Association.iii02List[0].value}">
												<td width="100px">${EB03Association.iii02List[0].value} - ${EB03Association.iii02List[0].description}</td>
											</c:when>
											<c:otherwise><td width="100px">-</td></c:otherwise>
										</c:choose>	
										<c:choose>	
											<c:when test="${! empty EB03Association.message}">
												<td width="300px">${EB03Association.message}</td>
											</c:when>
											<c:otherwise><td width="300px">-</td></c:otherwise>
										</c:choose>	
									
									
									
										
										
										<c:choose>	
											<c:when test="${! empty EB03Association.msgReqdInd}">
												<td width="100px">${EB03Association.msgReqdInd}</td>
											</c:when>
											<c:otherwise><td width="100px">-</td></c:otherwise>
										</c:choose>	
										
											
											<c:set var="ExtdLinkPresentStatus" value="F"/>
											<c:forEach items="${currentMapping.eb03.extendedMsgsForSelectedValues}" var="eb03ExtMsgsFromDB" >
											
											<c:if test="${(eb03ExtMsgsFromDB.value == EB03Association.eb03.value) && (eb03ExtMsgsFromDB.changeInd != 'D') && (eb03ExtMsgsFromDB.extndMsg1 != '' 
				                    				 || eb03ExtMsgsFromDB.extndMsg2 != '' || eb03ExtMsgsFromDB.extndMsg3 != '' )}">
												<td width="300px">
												<a href="#" style="color: #077eed; font-size: 11px;" id="extdMsgEBO3${count}" onclick="showExtndMessagesScreen(this.id)">&#160;&#160;Ext msg</a>
											</td>
											<c:set var="ExtdLinkPresentStatus" value="T"/>
											</c:if>
										</c:forEach>
										 <c:if test="${ExtdLinkPresentStatus=='F'}">
										<td width="300px">-</td>
										</c:if> 
											
												
										
										<c:choose>	
											<c:when test="${! empty EB03Association.noteType.value}">
												<td width="150px" >${EB03Association.noteType.value} - ${EB03Association.noteType.description}</td>
											</c:when>
											<c:otherwise><td width="150px" >-</td></c:otherwise>
										</c:choose>
									</tr>
									<c:set var="count" value="${count + 1}"/>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
								
						</table>
						
				</div>
			</div>
<div>
	<table border="0" cellpadding="0" cellspacing="0" width="800px" >
		<tr> <td width="800px" colspan="5">&nbsp;</td> </tr>
	</table>
</div>		
</c:if>




			
			
		</div><!--viewEditContainer Ends-->
	
		<div class="">
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerPopup" id ="footerButton">
			   <tr>	
				<input type="hidden" id="selectedvariableIdCopyTo" value="" name="selectedvariableIdCopyTo"/>
 				<input type="hidden" id="selectedvariableId" value="" name="selectedvariableId"/>
				<c:set var="sentToTest" value="0"/>
				<c:set var="approve" value="0"/>
				<c:set var = "ntapplicable" value = "0" />
				<c:set var = "objTrnsfrd" value = "0"/>
				<c:set var = "published" value = "0"/>
				<c:if test="${currentMapping.variableMappingStatus == 'NOT_APPLICABLE'}">
					<c:set var="ntapplicable" value="1"/>
				</c:if>	
				<c:if test="${currentMapping.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
					<c:set var="sentToTest" value="1"/>
				</c:if>
				<c:if test="${currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
					<c:set var="objTrnsfrd" value="1"/>
				</c:if>
				<c:if test="${currentMapping.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION' || currentMapping.variableMappingStatus == 'PUBLISHED'}">
					<c:set var="published" value="1"/>
				</c:if>

				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='copyTo();'>Copy To</a></td>		              
				<c:if test="${(sentToTest != 1) && (ntapplicable != 1) && (published != 1) && (objTrnsfrd != 1)}">
					<td id="copyToSep" width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>				
					<td id="sendToTestButton" width="0" height="20"><a href="#" onclick='openUserCommentsDialog("${currentViewVariable[0].variableId}","Send2TestFromView");'>&nbsp;Send to Test&nbsp;</a></td>
				</c:if>
				<c:if test="${sentToTest != 1 && ntapplicable != 1 && (published != 1)}">
					<c:if test="${userUIPermissions.authorizedToapprove || currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<td id="sendToTestButtonSep" width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>				
						<td width="0" id="approveButton" height="20"><a href="#" onclick='openUserCommentsDialog("${currentViewVariable[0].variableId}","ApproveFromView");'>&nbsp;Approve&nbsp;</a></td>
					</c:if>			        	
				</c:if>	
				<c:if test="${userUIPermissions.authorizedToAuditLock && currentMapping.auditLock == 'Y'}"> 
				<c:set var="auditLock" value="AuditLockView_${currentViewVariable[0].variableId}" />   
				<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" id="${auditLock}" onclick ='openUserCommentsLockDialog("${currentViewVariable[0].variableId}","${currentViewVariable[0].variableSystem}","${currentMapping.user.lastUpdatedUserName}","${currentViewVariable[0].description}","UnlockView");'>Unlock</a></td>
		         </c:if>
		    
		        <c:if test="${userUIPermissions.authorizedToAuditUnlock && currentMapping.auditLock == 'N'}">
		        <c:set var="auditUnLock" value="AuditUnLockView_${currentViewVariable[0].variableId}" />
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a id="${auditUnLock}" href="#" onclick ='openUserCommentsLockDialog("${currentViewVariable[0].variableId}","${currentViewVariable[0].variableSystem}","${currentMapping.user.lastUpdatedUserName}","${currentViewVariable[0].description}","LockView");'>Lock</a></td>
		        
		        </c:if>
				<td id="3" width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
			  </tr>
			 </table>
		</div>

</c:if>	

<div id="viewAuditTrailInDetail"></div>
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<div id="copyToDialog" style="display:none;" >


	<%@include file="/WEB-INF/jsp/copyToPage.jsp"%>
</div>
<div id="extendedMsgScreen" style="display:none;">
	<table border="0" cellspacing="0" width="100%">	
		<tr height="30px">
			<td colspan="3"></td>
		</tr>
		<tr height="40px">
			<td width ="200px" align="right" > Variable ID</td>
			<td width="20px"></td>
			<td id="variableId"><p id="varIdDiag"></p></td>
		</tr>				
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Extend Message 1-A</td>
			<td width="20px"></td>
			<td align="left"><p 
					id="extndMessage1"  ></p>
					
					</td>
		</tr>
		
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Extend Message 1-B</td>
			<td width="20px"></td>
			<td align="left"><p
					id="extndMessage2" ></p></td>
		</tr>
		
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Extend Message 1-C</td>
			<td width="20px"></td>
			<td align="left"><p
					id="extndMessage3" ></p></td>
		</tr>				
			
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Network Required</td>
			<td width="20px"></td>
			<td align="left"><p id="networkInd"></p> 
					
			</td>
		</tr>
		
	</table>
</div>
<div id="hpnMapgMsgScreen" style="display:none;">
	<table border="0" cellspacing="0" width="100%">
		<tr>
		<th>Service TyCd</th>
		<th>HPN Mapping</th>
		</tr>
		<tr><td></br></td></tr>
		<c:forEach items="${hpnMapgList}" var="hpnvariable">
		<tr height="40px">
			<td style="text-align: center;"><span id="serviceTyCdVal">${hpnvariable.serviceTyCd}</span></td>
			<td style="text-align: center;"><span id="highPrfrmnNonTierdMsgTxtVal">${hpnvariable.highPrfrmnNonTierdMsgTxt}</span></br><span id="highPrfrmnTierdMsgTxtVal">${hpnvariable.highPrfrmnTierdMsgTxt}</span></td>
		</tr>
		</c:forEach>
	</table>
</div>


</form>
</body>
</html>


