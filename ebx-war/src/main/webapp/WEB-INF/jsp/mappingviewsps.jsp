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
$(document).ready(function(){	
	//Scrollbar not implemented
	$('#spsId').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#spsIdLabel").text('');
	   }
		$('#spsId').val($('#spsId').val().toUpperCase());	
	});
	$("#spsId").autocomplete({ 
		select: function(event, ui) { $("#spsIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+escape(request.term)+ "&name=SPSID",                         
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
				displayLabelForSelectedItem(text,list,"spsIdLabel",inValidSPSID);
			}
		}

	})	
  });
  
    function copyToDialog(){		
		$("#spsIdForCopyTo").val('');
		$("#createIdLabel").text('');
			$("#spsCopyToDialog").dialog({
				 		height:'auto',
						width:'450px',
						title: 'Copy to SPS ID',
                        show:'slide',
                        modal: true, 
                         close: function(event, ui)
        				{
          				  $("#spsCopyToDialog").dialog('destroy').remove();
        				}
			});						
			$("#spsCopyToDialog").dialog('open');	
					
	}	

function copyTo(){
		var spsIdForCopyTo =$("#spsIdForCopyTo").val(); 
		var oldSPSID= $("#spsIdentifier").val(); 
		
		if(spsIdForCopyTo == null || spsIdForCopyTo == ""){ 
			addErrorToNotificationTray('SPS ID is mandatory to create mapping');
			openTrayNotification();
		}	
		else{ $.ajax({
				url: "${context}/copy/invalidSPSId.ajax",
				dataType: "json",
				type: "POST",			
				data: "spsIdForCopyTo="+spsIdForCopyTo+"&oldSPSID="+oldSPSID,
				success: function(data) {
				cache: false,
				copyToCreate(data,spsIdForCopyTo,oldSPSID);
				}
			});	
		}	
} 
function copyToCreate(data,spsIdForCopyTo,oldSPSID){	
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			openTrayNotification();
		}
		else{
			$("#selectedSpsIdCopyTo").val(spsIdForCopyTo);
			$("#selectedSpsId").val(oldSPSID);						
			document.forms['viewForm'].action="${context}/createoreditspsmapping/copyToMapping.html";
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
		var spsId=$('#spsIdentifier').val();
		$.ajax({
			url: "${context}/ajaxviewallspsaudittrail/retreiveSPSAuditTrailInDetail.ajax",
			dataType: "html",
			type: "POST",
			data: "spsId="+spsId,
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

				function viewAllAuditTrail(spsId) { 
					var fromMappingView = $("#fromMappingView").val();
					$.ajax({
						url: "${context}/ajaxviewallspsaudittrail/viewAllSPSAuditTrail.ajax",
						dataType: "html",
						type: "POST",
						data: "spsId="+spsId+"&fromMappingView="+fromMappingView,
							success: function(data) {
														
								$("#viewAuditTrailId").html(data);
							}
						});
			    }
    function printPage(spsIdToPrint, status){		
		var url = "${context}/vieworcreatemapping/printSPSMapping.html?spsId="+spsIdToPrint+"&status="+status;
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
<table width="880px" border="0" cellspacing="0" cellpadding="0"
	class="Pd3">
	<THEAD>
		<tr class="createEditTable1">
			<td width="120" class="tableHeader">SPS ID</td>
			<td width="280" class="tableHeader">Description</td>
			<td width="150" class="tableHeader">Type of SPS</td>
			<td width="150" class="tableHeader">PVA</td>
			<td width="150" class="tableHeader">Data type</td>
			<td width ="40px" class="tableHeader">
			<a HREF="#" onClick='printPage("${currentMapping.spsId.spsId}","${currentMapping.variableMappingStatus}");'> <img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"> </a> 
			
			</td>
		</tr>
	</THEAD>
	<TBODY>
		<c:set var="rowCount" value="0" />
		<c:set var="count" value="1" />
		
		<tr>
		<c:set var="pva" value="" />
		<c:set var="type" value="" />
		<c:set var="dataType" value="" />
		<input type="hidden" id="spsIdentifier" value="${currentMapping.spsId.spsId}"/>
		
			<c:if test="${!empty currentMapping.spsId.spsDetail}">
				<c:set var="pva" value="${currentMapping.spsId.spsDetail[0].spsPVA}" />
				<c:set var="type" value="${currentMapping.spsId.spsDetail[0].spsType}" />
				<c:set var="dataType" value="${currentMapping.spsId.spsDetail[0].spsDataType}" />
			</c:if>
			<td>${currentMapping.spsId.spsId}
				<input type="hidden" id="spsId" name="spsId" value="${currentMapping.spsId.spsId}" />
			</td>			
			<td>${currentMapping.spsId.spsDesc}
				<input type="hidden" id="spsIdDesc" name="spsIdDesc" value="${currentMapping.spsId.spsDesc}" />
			</td>
			<td>${type}
				<input type="hidden" id="spsType0" name="spsType" value="${type}" />
			</td>
			<td>${pva}
				<input type="hidden" id="spsPVA0" name="spsPVA" value="${pva}" />
			</td>
			<td>${dataType}
				<input type="hidden" id="spsDataType0" name="spsDataType" value="${dataType}" />
			</td>
			<td></td>
		</tr>
		<c:set var="variableInfoDivScroll" value="false" />	
					<c:forEach items="${currentMapping.spsId.spsDetail}" var="spsDetailList" begin ="1">
						<c:set var="variableInfoDivScroll" value="true" />		
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
							<td></td>
							<td></td>
							<td>${spsDetailList.spsType}
								<input type="hidden" id="spsType${count}" name="spsType" value="${currentMapping.spsId.spsDetail[count].spsType}" />
							</td>
							<td>${spsDetailList.spsPVA}
								<input type="hidden" id="spsPVA${count}" name="spsPVA" value="${currentMapping.spsId.spsDetail[count].spsPVA}" />
							</td>
							<td>${spsDetailList.spsDataType}
								<input type="hidden" id="spsDataType${count}" name="spsDataType" value="${currentMapping.spsId.spsDetail[count].spsDataType}" />
							</td>		
							<td></td>					 
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					<c:set var="count" value="${count + 1}"/>
										
					</c:forEach>   
					<input type="hidden" id="totalCount" name="totalCount" value="${count}" />
		
					<c:if test="${variableInfoDivScroll == 'true'}">
						<script>
						$('#variableInfoDiv').height('80px');
						</script>
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

<c:if test="${currentMapping.variableMappingStatus != 'UNMAPPED'}">	
	<div id="viewEditContainerPopup">	<!--viewEditContainer Starts-->
			<table width="320" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">				
				<tr class="createEditTable1-Viewdata">
					<td class="headText">EB01</td>
					<td width="100px">${currentMapping.eb01.hippaCodeSelectedValues[0].value}</td>
					<td width="450px">${currentMapping.eb01.hippaCodeSelectedValues[0].description}</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">EB02</td>
					<td >${currentMapping.eb02.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.eb02.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">EB06</td>
					<td >${currentMapping.eb06.hippaCodeSelectedValues[0].value}</td>
					<td>${currentMapping.eb06.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">EB09</td>
				<td >${currentMapping.eb09.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.eb09.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>	
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">Finalized</td>
					<c:if test="${currentMapping.finalized}">
					<td >Y</td>
					</c:if>
					<c:if test="${!currentMapping.finalized}">
					<td >N</td>
					</c:if>
					<td >&#160;</td>									
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>				
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD01</td>
					<td >${currentMapping.hsd01.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd01.hippaCodeSelectedValues[0].description}</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD02</td>
					<td >${currentMapping.hsd02.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd02.hippaCodeSelectedValues[0].description}</td>
				</tr>	
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD03</td>
					<td >${currentMapping.hsd03.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd03.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD04</td>
					<td>${currentMapping.hsd04.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd04.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
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
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td class="headText">HSD06</td>
					<td >${currentMapping.hsd06.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd06.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD07</td>
					<td >${currentMapping.hsd07.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd07.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">HSD08</td>
					<td >${currentMapping.hsd08.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.hsd08.hippaCodeSelectedValues[0].description}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td  class="headText">Accumulator SPS ID</td>
					<td >${currentMapping.accum.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.accum.hippaCodeSelectedValues[0].description}</td>									
				</tr>					
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>					
			</table>
			<div id="viewAuditTrailId">
				<div class="mL10 link fL mR10"  title="View Audit Trail">
					<c:if test="${viewalllink == 'true'}">
					<a href="#" style="color:blue;" onclick ='viewAllAuditTrail("${currentMapping.spsId.spsId}")';>View All</a>
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
										<c:choose>
											<c:when test="${viewAudit.mappingStatus == 'PUBLISHED'}">
												${viewAudit.mappingStatus}
												<a href="#" style="color:blue;" onclick="retreiveAuditTrailInDetail();" >View&nbsp;Details</a>
											</c:when>
											<c:otherwise>${viewAudit.systemComments}</c:otherwise>
										</c:choose>
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
		</div><!--viewEditContainer Ends-->
	
		<div class="">
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerPopup" id ="footerButton">
			   <tr>	
				<input type="hidden" id="selectedSpsIdCopyTo" value="" name="selectedSpsIdCopyTo"/>
 				<input type="hidden" id="selectedSpsId" value="" name="selectedSpsId"/>	
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
					<td id="sendToTestButton" width="0" height="20"><a href="#" onclick='openUserCommentsDialogForSps("${currentMapping.spsId.spsId}","Send2TestSpsFromView");'>&nbsp;Send to Test&nbsp;</a></td>
				</c:if>
				<c:if test="${sentToTest != 1 && ntapplicable != 1 && (published != 1)}">
					<c:if test="${userUIPermissions.authorizedToapprove || currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<td id="sendToTestButtonSep" width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>				
						<td width="0" id="approveButton" height="20"><a href="#" onclick='openUserCommentsDialogForSps("${currentMapping.spsId.spsId}","ApproveSpsFromView");'>&nbsp;Approve&nbsp;</a></td>
					</c:if>			        	
				</c:if>	
				<td id="3" width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
			  </tr>
			 </table>
		</div>

</c:if>	

<div id="viewAuditTrailInDetail"></div>
<div id="spsCopyToDialog" style="display:none;">         
	<%@include file="/WEB-INF/jsp/copytospsid.jsp"%>
</div>
</form>
</body>
</html>

