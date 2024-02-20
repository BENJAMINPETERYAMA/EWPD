<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/typography.css" rel="stylesheet" type="text/css" />
<TITLE>Print Mapping</TITLE>
</head>

<body onload="Print()" class="normal" style="OVERFLOW: auto; height: 650px; width:880px;WORD-WRAP: break-word">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
 function Print(){document.body.offsetHeight;window.print()};
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
</script>
<form name="viewForm"  method = "post">
<table border="0" cellpadding="0" cellspacing="0" width="880px"	class="Pd3">
<THEAD>
	<tr class="createEditTable1">
			<td width="100px" class="tableHeader">Variable ID</td>
			<td width="220px"  class="tableHeader">Description</td>
			<td width="50x"  class="tableHeader"> PVA</td>
			<td width="75px" class="tableHeader">Data type</td>
			<td width="75px" class="tableHeader">Category</td>
			<td width="70px" class="tableHeader">System</td>			
			<td width="150px" class="tableHeader">Minor Heading</td>
			<td width="150px" class="tableHeader">Major Heading</td>
			<td width="140px" class="tableHeader">WPD Accumulator</td>
	
	</tr>
	
	</tr>
</THEAD>
<TBODY>
	<c:set var="rowCount" value="0" />
	<c:if test="${! empty currentViewVariable}">
	<input type="hidden" id="variableIdentifier" value="${currentViewVariable[0].variableId}"/>
	<tr>
		<td>${currentViewVariable[0].variableId}</td>
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
				<td width="75px">${accumList[counter]}</td>
						
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
		</tr>
	<c:set var="rowCount" value="${rowCount + 1}"/>
	<c:set var="counter" value="${counter + 1}"/>
	</c:forEach>	
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

<c:if test="${! empty currentMapping}">
<c:if test="${currentMapping.variableMappingStatus != 'UNMAPPED'}">	
<c:set var="currentMapping" value="${currentMapping}"/>	
	<div style="border-top:2px solid #d9e5eb;margin-top:1px;">	<!--viewEditContainer Starts-->
			<table width="320" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">				
				<tr class="createEditTable1-Viewdata">
					<td width="50px" class="headText">EB01</td>
					<td width="100px">${currentMapping.eb01.hippaCodeSelectedValues[0].value}</td>
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
					<td width="50px" class="headText">Procedures Excluded indicator</td>
					<td width="100px">${currentMapping.procedureExcludedInd}</td>
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
					<td >${currentMapping.eb02.hippaCodeSelectedValues[0].value}</td>
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
					<td >${currentMapping.eb06.hippaCodeSelectedValues[0].value}</td>
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
				<td >${currentMapping.eb09.hippaCodeSelectedValues[0].value}</td>
					<td >${currentMapping.eb09.hippaCodeSelectedValues[0].description}</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr>
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
						
					</td>
					<c:forEach items="${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues}" var="umRuleVal" begin="1" varStatus="status">	
						<tr>
						<td  class="headText"></td>						
						<td>${umRuleVal.value}</td>
						<td  style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="430px">${umRuleVal.description}</td>	
						<td>&#160;</td>
						<td style="vertical-align:bottom;">
						</td>											
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
									
									
									<td colspan="4">No audit trail found</td>
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
		<tr> <td width="800px" colspan="5">&nbsp;</td> </tr>
	</table>
</div>

<div id="viewAuditTrailId">
				<div class="mL10 link fL mR10"  title="">										
				</div>
				<div>
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="800px" class="Pd2 auditTrailTable" id="auditTrailsTable" >
								<tr class="createEditTableShade">
									<td width="150px" class="headText">EB03</td>
									<td width="100px" class="headText">III02</td>
									<td width="300px" class="headText">Message</td>
									<td width="75px" class="headText">Msg Reqd</td>
									<td width="175px" class="headText">Note Type</td>
								</tr>
								
								<c:if test="${empty eb03Associations}">
									<tr>
										<td colspan="5">No EB03 values found</td>
									</tr>
									
								</c:if>
								<c:if test="${! empty eb03Associations}">									
								<c:set var="viewHistoryRowCount" value="0" />
								<c:forEach items="${eb03Associations}" var="EB03Association">
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  height="20px" valign="middle"  >
									
										<c:choose>
												<c:when test="${! empty EB03Association.eb03.value}">
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
													<td width="75px">${EB03Association.msgReqdInd}</td>
												</c:when>
												<c:otherwise><td width="75px">-</td></c:otherwise>
											</c:choose>	
											<c:choose>	
												<c:when test="${! empty EB03Association.noteType.value}">
													<td width="175px" >${EB03Association.noteType.value} - ${EB03Association.noteType.description}</td>
												</c:when>
												<c:otherwise><td width="150px" >-</td></c:otherwise>
											</c:choose>
									</tr>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
								</c:if>
						</table>
				</div>
			</div>
			
			<div>
						<div>&nbsp;</div>
						
							<table border="0" cellpadding="0" cellspacing="0" width="800px" class="Pd2 auditTrailTable" id="EBO1ExtdMsg" >
								<tr class="createEditTableShade">
									<td width="150px" class="headText">EB01/EB03</td>
									<td width="300px" class="headText">Extend Message 1-A</td>
									<td width="300px" class="headText">Extend Message 1-B</td>
									<td width="300px" class="headText">Extend Message 1-C</td>
									<td width="100px" class="headText">Network Required</td>
								</tr>
								<c:set var="ExtdMsgPresentStatus" value= "F"/>
								<c:if test="${(currentMapping.eb01.hippaCodeSelectedValues[0].value=='D')&&
											(! empty currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg1||
											! empty currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg2||
											! empty currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg3)}">
								<c:set var="ExtdMsgPresentStatus" value= "T"/>
								<tr>
								<td width="150px">${currentMapping.eb01.hippaCodeSelectedValues[0].value}</td>
								<td width="300px">${currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg1}</td>
								<td width="300px">${currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg2}</td>
								<td width="300px">${currentMapping.eb01.extendedMsgsForSelectedValues[0].extndMsg3}</td>
								<td width="100px">${currentMapping.eb01.extendedMsgsForSelectedValues[0].networkInd}</td>
								</tr>
								</c:if>
								
								<c:set var="viewExtdMsgRowCount" value="0" />
								<c:if test="${!empty currentMapping.eb03.extendedMsgsForSelectedValues}">
								<c:forEach items="${eb03Associations}" var="EB03AssociationExtdMsg">
									
										
											<c:forEach items="${currentMapping.eb03.extendedMsgsForSelectedValues}" var="eb03ExtMsgsFromDB" >
											
											<c:if test="${(eb03ExtMsgsFromDB.value == EB03AssociationExtdMsg.eb03.value) && (eb03ExtMsgsFromDB.changeInd != 'D') && (eb03ExtMsgsFromDB.extndMsg1 != '' 
				                    				 || eb03ExtMsgsFromDB.extndMsg2 != '' || eb03ExtMsgsFromDB.extndMsg3 != '' )}">
												<tr class="${viewExtdMsgRowCount mod 2 == 0 ?  'alternate':'white-bg' }"  height="20px" valign="middle"  >
												<td width="150px">${EB03AssociationExtdMsg.eb03.value}</td>	
												<td width="300px">${eb03ExtMsgsFromDB.extndMsg1}</td>
												<td width="300px">${eb03ExtMsgsFromDB.extndMsg2}</td>
												<td width="300px">${eb03ExtMsgsFromDB.extndMsg3}</td>
												<td width="100px">${eb03ExtMsgsFromDB.networkInd}</td>
												</tr>
												<c:set var="ExtdMsgPresentStatus" value= "T"/>
											</c:if> 
											
											</c:forEach>
									 
									 
									<c:set var="viewExtdMsgRowCount" value="${viewExtdMsgRowCount + 1}"/>
							</c:forEach>
							</c:if>
								<c:if test="${ExtdMsgPresentStatus=='F'}">
								<tr>
										<td colspan="5">No Extended Message found</td>
									</tr>	
								</c:if> 	
										
				</table>
				</div>			
				
				
				<div>
						<div>&nbsp;</div>
						
							<table border="0" cellpadding="0" cellspacing="0" width="800px" class="Pd2 auditTrailTable" id="EBO3HPNMsg" >
								<tr class="createEditTableShade">
									<td width="100px" class="headText">EB03</td>
									<td colspan="2" width="200px" class="headText">HPN Mapping</td>
								</tr>
						<c:set var="viewHPNMsgRowCount" value="0" />	
						
							<c:forEach items="${hpnMapgList}" var="hpnvariable">
							<tr class="${viewExtdMsgRowCount mod 2 == 0 ?  'white-bg':'alternate' }"  height="20px" valign="middle"  >
								
								<td width="100px">${hpnvariable.serviceTyCd}</td>
								<td width="100px">${hpnvariable.highPrfrmnNonTierdMsgTxt}</td>
								<td width="100px">${hpnvariable.highPrfrmnTierdMsgTxt}</td>
								</tr>
								<c:set var="viewExtdMsgRowCount" value="${viewHPNMsgRowCount + 1}"/>
							</c:forEach>
							<c:if test="${empty hpnMapgList}">
							<td colspan="2">No HPN Mapping found</td>
							</c:if>
							</table>	
				</div>						
					
<div>
	<table border="0" cellpadding="0" cellspacing="0" width="800px" >
		<tr> <td width="800px" colspan="5">&nbsp;</td> </tr>
	</table>
</div>		





			

			
		</div><!--viewEditContainer Ends-->
</c:if>	
</c:if>
</form>
</body>
</html>

