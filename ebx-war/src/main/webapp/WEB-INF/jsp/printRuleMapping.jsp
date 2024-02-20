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
<TITLE>Print Rule Mapping</TITLE>
</head>

<body onload="Print()" class="normal" style="OVERFLOW: auto; height: 650px; width:880px;WORD-WRAP: break-word">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
 function Print(){
 document.body.offsetHeight;window.print()};
</script>

<form name="viewForm"  method = "post">
<table border="0" cellpadding="0" cellspacing="0" width="880px"	class="Pd3">
<THEAD>
	<tr class="createEditTable1">
			<td width="90px" class="tableHeader">Header Rule</td>
			<td width="220px"  class="tableHeader">Description</td>
	</tr>
</THEAD>
<TBODY>	
	<c:if test="${! empty currentMapping.rule}">
	<input type="hidden" id="ruleIdentifier" value="${currentMapping.rule.headerRuleId}"/>	
	<tr>
		<td>${currentMapping.rule.headerRuleId}</td>
		<td>${currentMapping.rule.ruleDesc}</td>			
	</tr>
	</c:if>
</TBODY>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="880px" class="Pd3">
<c:if test="${! empty currentMapping.variableMappingStatus}">
	<tr>
		<td width="150px"></td>
		<td width="150px"></td>
		<td width="90px"></td>
		<td width="90px"></td>
		<td width="75px"></td>
		<td width="180px"></td>
		<td width="135px" id="statusImageContainer" align="right">		
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
	<div style="border-top:2px solid #d9e5eb;margin-top:1px;">	<!--viewEditContainer Starts-->
			<table width="320" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">
				<tr class="createEditTable1-Viewdata">
				
				<tr class="createEditTable1-Viewdata">
					<td class="headText" width="60px">Procedures Excluded indicator</td>
					<td >${currentMapping.procedureExcludedInd}</td>	
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
				</tr>
				<tr class="createEditTable1-Viewdata">
				<td class="headText">UM Rule</td>
				<td >${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}</td>
				<td style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="430px">${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}</td>			
				<td>&#160;</td>
				<c:forEach items="${currentMapping.utilizationMgmntRule.hippaCodeSelectedValues}" var="umRuleVal" begin="1" varStatus="status">	
					<tr>
						<td  class="headText"></td>						
						<td>${umRuleVal.value}</td>
						<td  style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="430px">${umRuleVal.description}</td>	
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
				<tr> <td width="800px" colspan="2">&nbsp;</td> </tr>
			</table>
		</div>
<c:if test="${! empty eB03AssnList}">	
<div id="eb03AssnSectionId" >
				<div class="mL10 link fL mR10"  title="">										
				</div>
				<div>
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="800px" align ="center" class="Pd2 auditTrailTable" id="eb03AssnTable" >
								<tr class="createEditTableShade" >
									<td width="400px" align="left" class="headText">EB03</td>
									<td width="400px" align="left" class="headText">III02</td>
								</tr>
							
								<c:if test="${ empty eB03AssnList }">	
									<tr>
										<td colspan = "4" align="center" > No mapping found </td>
									</tr>
								</c:if>
								
								<c:if test="${not empty eB03AssnList }">								
								<c:set var="ebRowCount" value="0" />
								<c:forEach items="${eB03AssnList}" var="EB03Association"  >
									<tr class="${ebRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" height="20px" align="left" >	
										<td width="400px" align="left">${EB03Association.eb03.value} - ${EB03Association.eb03.description}</td>
										<c:choose>
										<c:when test="${! empty EB03Association.commaSeparatedIII02StringWithDesc}">
										
										<td width="400px" align="left">${EB03Association.commaSeparatedIII02StringWithDesc}</td>
										</c:when>
										<c:otherwise><td width="400px" align="left">-</td></c:otherwise>
										</c:choose>
									</tr>
								<c:set var="ebRowCount" value="${ebRowCount + 1}"/>
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
		</c:if>
		
		
		
		
					
					
					
					
			
		</div><!--viewEditContainer Ends-->	
</c:if>
</form>
</body>
</html>
