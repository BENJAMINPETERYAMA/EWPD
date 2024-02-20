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
    
		$("#spsIdForCopyTo1").val('');
		$("#createIdLabel").text('');
			$("#ruleCopyToDialog").dialog({
				 		height:'auto',
						width:'450px',
						title: 'Copy to Rule ID',
                        show:'slide',
                        modal: true,
                        close: function(event, ui)
        				{
          				  //$("#ruleCopyToDialog").dialog('destroy').remove();
          				  document.getElementById('ruleCopyToDialog').style.display='none';
          				  // $("#ruleCopyToDialog").display('none');
        				}
			});						
			$("#ruleCopyToDialog").dialog('open');	
			 document.getElementById('ruleCopyToDialog').style.display='block';
					
	}	

function copyTo(){
		var ruleIdForCopyTo =$("#spsIdForCopyTo1").val();		
		var oldRuleID= $("#ruleIdentifier").val(); 
		if(ruleIdForCopyTo == null || ruleIdForCopyTo == ""){ 
			addErrorToNotificationTray('Rule ID is mandatory to create mapping');
			openTrayNotification();
		}	
		else{ 
		$.ajax({
				url: "${context}/copy/invalidRuleId.ajax",
				dataType: "json",
				type: "POST",			
				data: "ruleIdForCopyTo="+ruleIdForCopyTo+"&oldRuleID="+oldRuleID,
				success: function(data) {
				cache: false,
				copyToCreateRuleId(data, ruleIdForCopyTo, oldRuleID);
				}
			});	
		}	
} 
function copyToCreateRuleId(data, ruleIdForCopyTo, oldRuleID){
		$("#selectedruleIdCopyTo").val(ruleIdForCopyTo);	
		$("#selectedruleId").val(oldRuleID);		
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			openTrayNotification();
		}
		else{		
		
			document.forms['viewForm'].action="${context}/createoreditrulemapping/copyToMapping.html";
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
function retreiveAuditTrailInDetail(){
	var ruleId=$('#ruleIdentifier').val();
					$.ajax({
						url: "${context}/ajaxviewallruleiddetail.ajax",
						dataType: "html",
						type: "POST",
						data: "ruleId="+ruleId,
						success: function(data) {
							$("#viewAuditTrailInDetail").html(data);
							$("#viewAuditTrailInDetail").dialog({
									            minHeight : 150,
												maxHeight : 550,
												width : 890,
												position : 'center',
												resizable : false,
												title : 'Mapping Log',
									            modal : true
							});
						}
						});
				}

				function viewAllAuditTrail(ruleId, fromMappingView) { 
					var fromMappingView = $("#fromMappingView").val();
					$.ajax({
						url: "${context}/ajaxviewallruleid.ajax",
						dataType: "html",
						type: "POST",
						data: "ruleId="+ruleId+"&fromMappingView="+fromMappingView,
							success: function(data) {
														
								$("#viewAuditTrailId").html(data);
							}
						});
			    }
	function printPage(ruleIdToPrint, status){
	var url = "${context}/vieworcreatemapping/printRuleMapping.html?ruleId="+ruleIdToPrint+"&status="+status;
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
<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
<table border="0" cellpadding="0" cellspacing="0" width="880px"	class="Pd3">
<THEAD>
	<tr class="createEditTable1">
			<td width="90px" class="tableHeader">Header Rule</td>
			<td width="750px"  class="tableHeader">Description</td>
			<td width ="40px" class="tableHeader">
			<a HREF="#" onClick='printPage("${currentMapping.rule.headerRuleId}","${currentMapping.variableMappingStatus}");'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a> 	
			</td>	
	</tr>
</THEAD>
<TBODY>	
	<c:if test="${! empty currentMapping.rule}">
	<input type="hidden" id="ruleIdentifier" value="${currentMapping.rule.headerRuleId}"/>	
	<tr>
		<td>${currentMapping.rule.headerRuleId}</td>
		<td>${currentMapping.rule.ruleDesc}</td>
		<td></td>			
	</tr>
	</c:if>
</TBODY>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="880px" class="Pd3">
<c:if test="${! empty currentMapping.variableMappingStatus}">
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

<c:if test="${currentMapping.variableMappingStatus != 'UNMAPPED'}">	
<c:set var="currentMapping" value="${currentMapping}"/>				
	<div id="viewEditContainerPopup">	<!--viewEditContainer Starts-->
			<table width="320" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">
				<tr class="createEditTable1-Viewdata">
					
				<tr class="createEditTable1-Viewdata">
					<td class="headText" width="60px">Sensitive Benefit Indicator</td>
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
				<tr class="createEditTable1-Viewdata">
					<td class="headText" width="60px">Procedures Excluded indicator</td>
					<td>${currentMapping.procedureExcludedInd}</td>	
					<td>&#160;</td>
					<td>&#160;</td>			
				</tr>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
				<td class="headText">UM Rule</td>
				<td >${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}</td>
				<td style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="430px">${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}</td>			
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
			</table>
			<div id="viewAuditTrailId">
				<div class="mL10 link fL mR10"  title="View Audit Trail">
					<c:if test="${viewalllink == 'true'}">
					<a href="#" style="color:blue;" onclick ='viewAllAuditTrail("${currentMapping.rule.headerRuleId}")';>View All</a>
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
								</c:if>															
						</table>
						
				</div>
			</div>
			
			
									




<div>
	<table border="0" cellpadding="0" cellspacing="0" width="800px" >
		<tr> <td width="800px" colspan="2">&nbsp;</td> </tr>
	</table>
</div>
<c:if test="${! empty eb03Associations}">	
<div id="eb03AssnSectionId">
				<div class="mL10 link fL mR10"  title="">										
				</div>
				<div>
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="800px" class="Pd2 auditTrailTable" id="eb03AssnTable" >
								<tr class="createEditTableShade" >
									<td width="400px" align="left" class="headText">EB03</td>
									<td width="400px" align="left" class="headText">III02</td>
								</tr>
							
																
								<c:set var="viewHistoryRowCount" value="0" />
								<c:forEach items="${eb03Associations}" var="EB03Association"  >
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" height="20px" align="left" >	
										<td width="400px" align="left">${EB03Association.eb03.value} - ${EB03Association.eb03.description}</td>
										<c:choose>
										<c:when test="${! empty EB03Association.commaSeparatedIII02StringWithDesc}">
										
										<td width="400px" align="left">${EB03Association.commaSeparatedIII02StringWithDesc}</td>
										</c:when>
										<c:otherwise><td width="400px" align="left">-</td></c:otherwise>
										</c:choose>
									</tr>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
								
						</table>
						
				</div>
			</div>
<div>
	<table border="0" cellpadding="0" cellspacing="0" width="800px" >
		<tr> <td width="800px" colspan="2">&nbsp;</td> </tr>
	</table>
</div>		
</c:if>




			
			
			
			
			
		</div><!--viewEditContainer Ends-->
	
		<div class="">
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerPopup" id ="footerButton">
			   <tr>	
				<input type="hidden" id="selectedruleIdCopyTo" value="" name="selectedruleIdCopyTo"/>
 				<input type="hidden" id="selectedruleId" value="" name="selectedruleId"/>
 				
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
					<td id="sendToTestButton" width="0" height="20"><a href="#" onclick='openUserCommentsDialogForRule("${currentMapping.rule.headerRuleId}","Send2TestRuleFromView");'>&nbsp;Send to Test&nbsp;</a></td>
				</c:if>
				<c:if test="${sentToTest != 1 && ntapplicable != 1 && (published != 1)}">
						<c:if test="${userUIPermissions.authorizedToapprove || currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<td id="sendToTestButtonSep" width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>				
						<td width="0" id="approveButton" height="20"><a href="#" onclick='openUserCommentsDialogForRule("${currentMapping.rule.headerRuleId}","ApproveRuleFromView");'>&nbsp;Approve&nbsp;</a></td>
						</c:if>	        	
				</c:if>	
				<td id="3" width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
			  </tr>
			 </table>
		</div>

</c:if>	

<div id="viewAuditTrailInDetail"></div>
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<div id="ruleCopyToDialog" style="display:none;" >
	<%@include file="/WEB-INF/jsp/copytoruleid.jsp"%>
</div>
</form>
</body>
</html>
