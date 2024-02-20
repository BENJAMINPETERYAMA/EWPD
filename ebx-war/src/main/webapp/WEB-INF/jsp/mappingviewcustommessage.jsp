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
    function copyToDialog(){
    	var individualMapping = $('#indvMapping').val();
    	var rule =  $('#ruleIdentifier').val();	
   	if(individualMapping == 'Y'){
   	 			addErrorToNotificationTray('The Rule Id '+rule+' has Individual Mapping.');
				openTrayNotification();
   	}else{
		$("#spsIdForCopyTo").val('');
		$("#createIdLabel1").text('');
	    $("#spsIdForCopyTo1").val('');
	    $("#createIdLabel").text('');
			$("#customMessageCopyToDialog").dialog({
				 		height:'auto',
						width:'450px',
						title: 'Copy to Custom Message',
                        show:'slide',
                        modal: true,
                        close: function(event, ui)
        				{
        					document.getElementById('customMessageCopyToDialog').style.display='none';
          				  //$("#customMessageCopyToDialog").dialog('destroy').remove();
        				}
			});	
			$("#customMessageCopyToDialog").dialog('open');	
			document.getElementById('customMessageCopyToDialog').style.display='block';
		}			
	}	

function copyTo(){
		var ruleIdForCopyTo =$("#spsIdForCopyTo1").val();
		var oldRuleDesc = $("#oldRuleDesc").val();	
		var oldRuleID= $("#ruleIdentifier").val();
		var spsIdForCopyTo =$("#spsIdForCopyTo").val(); 
		var oldSPSID= $("#spsIdentifier").val(); 
		var oldSpsDesc = $("#oldSpsDesc").val();
		var oldVarFormate = $("#oldVarFormate").val();
		if((ruleIdForCopyTo == null || ruleIdForCopyTo == "")&&(spsIdForCopyTo == null || spsIdForCopyTo == "")){ 
			addErrorToNotificationTray('Rule ID  and Sps ID is mandatory to create mapping');
			openTrayNotification();
		}
		else if(spsIdForCopyTo == null || spsIdForCopyTo == ""){ 
			addErrorToNotificationTray('Sps ID is mandatory to create mapping');
			openTrayNotification();
		}
		else if(ruleIdForCopyTo == null || ruleIdForCopyTo == ""){
		addErrorToNotificationTray('Rule ID is mandatory to create mapping');
			openTrayNotification();
		}
		else if((ruleIdForCopyTo==oldRuleID)&&(spsIdForCopyTo==oldSPSID)){
		addErrorToNotificationTray('Mapping already exists');
			openTrayNotification();
		}
		else {			
			$.ajax({
					url: "${context}/copy/invalidCustomMessage.ajax",
					dataType: "json",
					type: "POST",			
					data: "ruleIdForCopyTo="+ruleIdForCopyTo+"&oldRuleID="+oldRuleID+"&oldRuleDesc="+oldRuleDesc+ "&spsIdForCopyTo="+spsIdForCopyTo+"&oldSPSID="+oldSPSID+"&oldSpsDesc="+oldSpsDesc,
					success: function(data) {
					cache: false,
					copyToCreate(data,ruleIdForCopyTo, oldRuleID, oldRuleDesc,spsIdForCopyTo,oldSPSID,oldSpsDesc);
					}
				});	
		}
			
		}	

function copyToCreate(data,ruleIdForCopyTo, oldRuleID, oldRuleDesc,spsIdForCopyTo,oldSPSID,oldSpsDesc){
		$("#selectedruleIdCopyTo").val(ruleIdForCopyTo);
		$("#selectedruleId").val(oldRuleID);
		$("#ruleDesc").val(oldRuleDesc);	
		$("#selectedspsIdCopyTo").val(spsIdForCopyTo);
		$("#selectedspsId").val(oldSPSID);
		$("#spsDesc").val(oldSpsDesc);	
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			openTrayNotification();
		}
		else{					
			
			document.forms['viewForm'].action="${context}/createoreditcustommessagemapping/copyToMapping.html";
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
					$.ajax({
						url: "${context}/ajaxviewallcustommessagedetail.ajax",
						dataType: "html",
						type: "POST",
						data: "ruleId=${currentMapping.rule.headerRuleId}"+ "&spsId=${currentMapping.spsId.spsId}" ,
						success: function(data) {
							$("#viewAuditTrailInDetail").html(data);
							$("#viewAuditTrailInDetail").dialog({
									            minHeight : 150,
												maxHeight : 550,
												width : 899,
												position : 'center',
												resizable : true,
												title : 'Mapping Log',
									            modal : true
							});
						}
						});
				}

	function viewAllAuditTrail(ruleId,spsId,fromMappingView) { 
		var fromMappingView = $("#fromMappingView").val();
		$.ajax({
			url: "${context}/ajaxviewallcustommessage.ajax",
			dataType: "html",
			type: "POST",
			data: "ruleId="+ruleId+"&spsId="+spsId+ "&fromMappingView="+fromMappingView,
				success: function(data) {
											
					$("#viewAuditTrailId").html(data);
				}
			});
    }
    function printPage(){
	var oldRuleID= $("#ruleIdentifier").val(); 
	var oldSPSID= $("#spsIdentifier").val(); 
	var url = "${context}/vieworcreatemapping/printCustomMessageMapping.html?ruleId="+oldRuleID+"&spsId="+oldSPSID;
    //newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
	window.open(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
	}	
	function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
}	    
</script>
<form name="viewForm"  method = "post">
<div id="variableInfoDiv" class="overflowContainerVariableView">
<table border="0" cellpadding="0" cellspacing="0" width="880px"	class="Pd3">
<THEAD>
	<tr class="createEditTable1">			
			 <td width="90" class="tableHeader">Header Rule</td>
             <td width="200" class="tableHeader">Description</td>
             <td width="70" class="tableHeader">SPS ID</td>
             <td width="200" class="tableHeader">SPS Description</td>	
             <td width ="40px" class="tableHeader">
             <a HREF="#" onClick="printPage();"><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"> </a>  	
             </td>				
	</tr>
</THEAD>
<TBODY>	
	<c:if test="${! empty currentMapping.spsId}">
	<input type="hidden" id="ruleIdentifier" value="${currentMapping.rule.headerRuleId}"/>
	<input type="hidden" id="spsIdentifier" value="${currentMapping.spsId.spsId}"/>	
	<input type="hidden" id="indvMapping" value="${currentMapping.indvdlEb03AssnIndicator}"/>	
	<tr>
		<td width="90px">${currentMapping.rule.headerRuleId}</td>
		<td width="200px">${currentMapping.rule.ruleDesc}</td>
		<td width="70px">${currentMapping.spsId.spsId}</td>
		<td width="200px">${currentMapping.spsId.spsDesc}</td>	
	</tr>
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




	
			<!-- Message labels and values removed from here -->
		<div id="viewAuditTrailId" style="margin-top:10px;" >
			
			<div id="viewEditContainerPopup" >	<!--viewEditContainer Starts-->
			
			
			
				<div style="margin-top:10px;">
					<table border="0" cellpadding="0" cellspacing="0" width="880px" class="Pd2 auditTrailTable" id="viewMappingTable" >
												
											
												<c:if test="${ empty eB03AssnList }">	
													<tr>
														<td colspan = "4" align="center" > No mapping found </td>
													</tr>
												</c:if>
												<c:if test="${not empty eB03AssnList }">								
												<c:set var="viewEB03AssnRowCount" value="0" />
												<tr class="createEditTableShade">
													<td width="220px" class="headText">EB03</td>
													<td width="330px" class="headText">Message</td>
													<td width="110px" class="headText">Msg Reqd</td>
													<td width="220px" class="headText">Note Type</td>
												</tr>
												<c:forEach items="${eB03AssnList}" var="eb03Assn" begin="0">
													<tr class="${viewEB03AssnRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  >	
														
														<td width="220px">${eb03Assn.eb03.value} &nbsp;<c:out value = "-"/> &nbsp; ${eb03Assn.eb03.description}</td>
														<td width="330px">
														<c:if test="${empty eb03Assn.message}">
														-
														</c:if>
														${eb03Assn.message}</td>
														<td width="110px">${eb03Assn.msgReqdInd}</td>
														<td width="220px">${eb03Assn.noteType.value} &nbsp;<c:out value = "-"/> &nbsp; ${eb03Assn.noteType.description}</td>
													</tr>
												<c:set var="viewEB03AssnRowCount" value="${viewEB03AssnRowCount + 1}"/>
												</c:forEach>
												</c:if>
					</table>
				</div>
			
			<div  style="border-top:2px solid #d9e5eb;margin-top:10px;">
				<div style="margin-left:350px" class="mL10 link fL mR10"  title="View Audit Trail" >
					<c:if test="${viewalllink == 'true'}">
					<a href="#" style="color:blue;" onclick ='viewAllAuditTrail("${currentMapping.rule.headerRuleId}","${currentMapping.spsId.spsId}")';>View All</a>
					</c:if>
				</div>
				<div style="margin-left:350px">
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="520px" class="Pd2 auditTrailTable" id="auditTrailsTable"  >
								<tr class="createEditTableShade">
									<td width="90px" class="headText" >Date</td>
									<td width="93px" class="headText" >User ID</td>
									<td width="180px" class="headText" >System Comment</td>
									<td width="250px" class="headText" >User Comment</td>
								</tr>
							
								<c:if test="${empty currentMapping.auditTrails}">
									<tr>
										<td colspan = "4" align="center" > No audit trail found </td>
									</tr>
								</c:if>
								<c:if test="${! empty currentMapping.auditTrails}">									
								<c:set var="viewHistoryRowCount" value="0" />
								<c:forEach items="${currentMapping.auditTrails}" var="viewAudit">
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  >	
										<fmt:timeZone value="PST">
										<td width="90px" ><c:out value="${viewAudit.createdAuditDate}"/></td>
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
										<td width="250px" >${viewAudit.userComments}</td>
										</fmt:timeZone>	
									</tr>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
								</c:if>
						</table>
						
				</div>
				</div>
			</div>
		</div><!--viewEditContainer Ends-->
	
		<div class="">
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerPopup" id ="footerButton">
			   <tr>	
				<input type="hidden" id="selectedspsIdCopyTo" value="" name="selectedspsIdCopyTo"/>
 				<input type="hidden" id="selectedspsId" value="" name="selectedspsId"/>	
 				<input type="hidden" id="selectedruleIdCopyTo" value="" name="selectedruleIdCopyTo"/>
 				<input type="hidden" id="selectedruleId" value="" name="selectedruleId"/>
 				<input type="hidden" id="ruleDesc" value="" name="ruleDesc"/>
 				<input type="hidden" id="spsDesc" value="" name="spsDesc"/>	
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
				<td width="0" height="20"><a href="#" onclick ='copyToDialog();'>Copy To</a></td>		              

				<c:if test="${(sentToTest != 1) && (ntapplicable != 1) && (published != 1) && (objTrnsfrd != 1)}">
					<td id="copyToSep" width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>				
					<td id="sendToTestButton" width="0" height="20"><a href="#" onclick='openUserCommentsDialogForCustomMsg("${currentMapping.rule.headerRuleId}","${currentMapping.spsId.spsId}","Send2TestCustomMsgFromView");'>&nbsp;Send to Test&nbsp;</a></td>
				</c:if>
				<c:if test="${sentToTest != 1 && ntapplicable != 1 && (published != 1)}">
					<c:if test="${userUIPermissions.authorizedToapprove || currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<td id="sendToTestButtonSep" width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>				
						<td width="0" id="approveButton" height="20"><a href="#" onclick='openUserCommentsDialogForCustomMsg("${currentMapping.rule.headerRuleId}","${currentMapping.spsId.spsId}","ApproveCustomMsgFromView");'>&nbsp;Approve&nbsp;</a></td>
					</c:if>			        	
				</c:if>	
				<td id="3" width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
			  </tr>
			 </table>
		</div>



<div id="viewAuditTrailInDetail"></div>
<div id="customMessageCopyToDialog" style="display:none;">         
	<%@include file="/WEB-INF/jsp/copytocustommsg.jsp"%>
</div>
</form>
</body>
</html>

