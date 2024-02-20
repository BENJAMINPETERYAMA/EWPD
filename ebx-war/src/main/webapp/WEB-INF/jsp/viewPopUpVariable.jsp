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
<div id="viewEditContainerPopup1">	<!--viewEditContainer Starts-->
<c:set var="rowCount" value="0" />
<c:if test="${! empty searchResults}">
<c:forEach items="${searchResults}" var="searchResult">
<table width="600px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">
	
		<tr class="createEditTable1-Viewdata">
			<td width="15%" class="headText" align="left">Variable ID</td>
			<td width="18%" class="headText" align="left">Description</td>
			<c:if test="${! empty searchResult.pva}">
			<td width="5%" class="headText" align="left">PVA</td>
			</c:if>
			<c:if test="${! empty searchResult.dataType}">
			<td width="10%" class="headText" align="left">Data Type</td>
			</c:if>
			<c:if test="${! empty searchResult.categoryCode}">
			<td width="10%" class="headText" align="left">Category</td>
			</c:if>
			<c:if test="${! empty searchResult.majorHeading}">
				<td width="21%" class="headText" align="left">Major Heading</td>
			</c:if>
			<c:if test="${! empty searchResult.minorHeading}">
				<td width="21%" class="headText" align="left">Minor Heading</td>
			</c:if>
			<c:if test="${! empty searchResult.wpdAccumulator}">
				<td width="21%" class="headText" align="left">WPD Accumulator</td>
			</c:if>
	
		</tr>
	
		<c:set var="rowCount" value="0" />
		<c:set var="count" value="1" />
		
		<tr>
		<c:set var="rowCount" value="0" />
		<c:if test="${! empty searchResults}">
		<c:forEach items="${searchResults}" var="searchResult"> 
		<input type="hidden" id="spsIdentifier" value="${searchResult.variableId}"/>
			<td width="auto" align="left">${searchResult.variableId}
				<c:if test="${searchResult.auditLock == 'Y' && searchResult.status != 'UNMAPPED' }">
				<img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" style="height:9px;width:9px" />
				</c:if>
			</td>			
			<td width="auto" align="left">${searchResult.description}
			</td>
			<c:if test="${! empty searchResult.pva}">
			<td width="auto" align="left">${searchResult.pva}
			</td>
			</c:if>
			<c:if test="${! empty searchResult.dataType}">
			<td width="auto" align="left">${searchResult.dataType}
			</td>
			</c:if>
			<c:if test="${! empty searchResult.categoryCode}">
			<td width="auto" align="left">${searchResult.categoryCode}
			</td>
			</c:if>
			<c:if test="${! empty searchResult.majorHeading}">
			<td width="auto" align="left">${searchResult.majorHeading}
			</td>
			</c:if>
			<c:if test="${! empty searchResult.minorHeading}">
			<td width="auto" align="left">${searchResult.minorHeading}
			</td>
			</c:if>
			<c:if test="${! empty searchResult.wpdAccumulator}">
			<td width="auto" align="left">${searchResult.wpdAccumulator}
			</td>
			</c:if>
		  	</c:forEach></c:if>
		</tr>

</table></c:forEach></c:if>
</div>
<table><tr><td>&nbsp;</td></tr></table>
<div id="viewEditContainerPopup2">	<!-- viewEditContainer Starts-->
<c:set var="rowCount" value="0" />
<c:if test="${! empty searchResults}">
<c:forEach items="${searchResults}" var="searchResult">

<table width="600px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">	
<tr><td>&#160;</td></tr>
<tr class="createEditTable1-Viewdata">
<td  width="20%" class="headText">Status</td>
<c:if test="${searchResult.status != 'UNMAPPED'}">	
<c:if test="${! empty searchResult.EB02}"><td width="5%" align="center" class="headText">EB02</td></c:if>
<c:if test="${! empty searchResult.EB06}"><td width="5%" align="center" class="headText">EB06</td></c:if>
<c:if test="${! empty searchResult.EB09}"><td width="5%" align="center" class="headText">EB09</td></c:if>
<c:if test="${! empty searchResult.hsd01}"><td width="5%" align="center" class="headText">HSD01</td></c:if>
<c:if test="${! empty searchResult.hsd02}"><td width="5%" align="center" class="headText">HSD02</td></c:if>
<c:if test="${! empty searchResult.hsd03}"><td width="5%" align="center" class="headText">HSD03</td></c:if>
<c:if test="${! empty searchResult.hsd04}"><td width="5%" align="center" class="headText">HSD04</td></c:if>
<c:if test="${! empty searchResult.hsd05}"><td width="5%" align="center" class="headText">HSD05</td></c:if>
<c:if test="${! empty searchResult.hsd06}"><td width="5%" align="center" class="headText">HSD06</td></c:if>
<c:if test="${! empty searchResult.hsd07}"><td width="5%" align="center" class="headText">HSD07</td></c:if>
<c:if test="${! empty searchResult.hsd08}"><td width="5%" align="center" class="headText">HSD08</td></c:if>
<c:if test="${! empty searchResult.procedureExcludedInd}"><td width="5%" align="center" class="headText">Procedure Excluded Indicator</td></c:if>
<td  width="5%" align="center" class="headText">Finalized</td>
<c:if test="${! empty searchResult.startAge}"><td width="5%" align="center" class="headText">Start Age</td></c:if>
<c:if test="${! empty searchResult.endAge}"><td width="5%" align="center" class="headText">End Age</td></c:if>
<c:if test="${! empty searchResult.accumNotReqrdIndctr}"><td width="5%" align="center" class="headText">Accum Not Required Indicator</td></c:if>
<c:if test="${! empty searchResult.accumulator}"><td width="5%" align="center" class="headText">Accumulator</td></c:if>
<c:if test="${! empty searchResult.hsd01}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd02}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd03}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd04}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd05}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd06}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd07}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd08}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.procedureExcludedInd}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.startAge}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.endAge}"><td class="headText">&nbsp;</td></c:if>
    </c:if>
</tr>	
<tr>
<td>${searchResult.status}</td>
<c:if test="${searchResult.status != 'UNMAPPED'}">	
<c:if test="${! empty searchResult.EB02}"><td align="center">${searchResult.EB02}</td></c:if>
<c:if test="${! empty searchResult.EB06}"><td align="center">${searchResult.EB06}</td></c:if>
<c:if test="${! empty searchResult.EB09}"><td align="center">${searchResult.EB09}</td></c:if>
<c:if test="${! empty searchResult.hsd01}"><td align="center">${searchResult.hsd01}</td></c:if>
<c:if test="${! empty searchResult.hsd02}"><td align="center">${searchResult.hsd02}</td></c:if>
<c:if test="${! empty searchResult.hsd03}"><td align="center">${searchResult.hsd03}</td></c:if>
<c:if test="${! empty searchResult.hsd04}"><td align="center">${searchResult.hsd04}</td></c:if>
<c:if test="${! empty searchResult.hsd05}"><td align="center">${searchResult.hsd05}</td></c:if>
<c:if test="${! empty searchResult.hsd06}"><td align="center">${searchResult.hsd06}</td></c:if>
<c:if test="${! empty searchResult.hsd07}"><td align="center">${searchResult.hsd07}</td></c:if>
<c:if test="${! empty searchResult.hsd08}"><td align="center">${searchResult.hsd08}</td></c:if>
<c:if test="${! empty searchResult.procedureExcludedInd}"><td align="center">${searchResult.procedureExcludedInd}</td></c:if>
<td align="center" >${searchResult.notCompleteFlag}</td>
<c:if test="${! empty searchResult.startAge}"><td align="center">${searchResult.startAge}</td></c:if>
<c:if test="${! empty searchResult.endAge}"><td align="center">${searchResult.endAge}</td></c:if>
<c:if test="${! empty searchResult.accumNotReqrdIndctr}"><td align="center">${searchResult.accumNotReqrdIndctr}</td></c:if>
<c:if test="${! empty searchResult.accumulator}"><td align="center">${searchResult.accumulator}</td></c:if>
<c:if test="${! empty searchResult.hsd01}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd02}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd03}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd04}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd05}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd06}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd07}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.hsd08}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.procedureExcludedInd}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.startAge}"><td class="headText">&nbsp;</td></c:if>
<c:if test="${! empty searchResult.endAge}"><td class="headText">&nbsp;</td></c:if>
  </c:if>
</tr>	
</table>

</c:forEach></c:if>
</div>





<div>
	<table border="0" cellpadding="0" cellspacing="0" width="600px" >
		<tr> <td width="600px" colspan="5">&nbsp;</td> </tr>
	</table>
</div>

<div id="viewAuditTrailId">
				<div class="mL10 link fL mR10"  title="">										
				</div>
				<div>
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="600px" class="Pd2 auditTrailTable" id="auditTrailsTable" >
								<c:if test="${! empty eb03Associations}">	
								<tr class="createEditTableShade">
									<td width="100px" class="headText">EB03</td>
									<td width="100px" class="headText">III02</td>
									<td width="200px" class="headText">Message</td>
									<td width="75px" class="headText">Msg Reqd</td>
									<td width="125px" class="headText">Note Type</td>
								</tr>
																
								<c:set var="viewHistoryRowCount" value="0" />
								<c:forEach items="${eb03Associations}" var="EB03Association">
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" height="20px" valign="middle" >
									
										<c:choose>
											<c:when test="${! empty EB03Association.eb03.value}">
												<td width="100px">${EB03Association.eb03.value} - ${EB03Association.eb03.description}</td>
											</c:when>
											<c:otherwise><td width="100px">-</td></c:otherwise>
										</c:choose>	
										<c:choose>
											<c:when test="${! empty EB03Association.iii02List[0].value}">
												<td width="100px">${EB03Association.iii02List[0].value} - ${EB03Association.iii02List[0].description}</td>
											</c:when>
											<c:otherwise><td width="100px">-</td></c:otherwise>
										</c:choose>	
										<c:choose>	
											<c:when test="${! empty EB03Association.message}">
												<td width="200px">${EB03Association.message}</td>
											</c:when>
											<c:otherwise><td width="200px">-</td></c:otherwise>
										</c:choose>	
										<c:choose>	
											<c:when test="${! empty EB03Association.msgReqdInd}">
												<td width="75px">${EB03Association.msgReqdInd}</td>
											</c:when>
											<c:otherwise><td width="75px">-</td></c:otherwise>
										</c:choose>	
										<c:choose>	
											<c:when test="${! empty EB03Association.noteType.value}">
												<td width="125px" >${EB03Association.noteType.value} - ${EB03Association.noteType.description}</td>
											</c:when>
											<c:otherwise><td width="125px" >-</td></c:otherwise>
										</c:choose>
									</tr>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
								</c:if>
						</table>
						
				</div>
			</div>
<div>
	<table border="0" cellpadding="0" cellspacing="0" width="600px" >
		<tr> <td width="600px" colspan="5">&nbsp;</td> </tr>
	</table>
</div>		







</form>
</body>
</html>