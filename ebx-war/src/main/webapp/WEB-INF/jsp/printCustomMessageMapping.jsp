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
<TITLE>Print Custom Message Mapping</TITLE>
</head>

<body onload="Print()" class="normal" style="OVERFLOW: auto; height: 650px; width:880px;WORD-WRAP: break-word">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
function Print(){document.body.offsetHeight;window.print()};    
</script>
<form name="viewForm"  method = "post">
<table border="0" cellpadding="0" cellspacing="0" width="880px"	class="Pd3">
<THEAD>
	<tr class="createEditTable1">			
			 <td width="90" class="tableHeader">Header Rule</td>
             <td width="200" class="tableHeader">Description</td>
             <td width="70" class="tableHeader">SPS ID</td>
             <td width="200" class="tableHeader">SPS Description</td>	
            			
	</tr>
</THEAD>
<TBODY>	
	<c:if test="${! empty currentMapping.spsId}">
	<input type="hidden" id="ruleIdentifier" value="${currentMapping.rule.headerRuleId}"/>
	<input type="hidden" id="spsIdentifier" value="${currentMapping.spsId.spsId}"/>	
	<tr>
		<td width="90px">${currentMapping.rule.headerRuleId}</td>
		<td width="200px">${currentMapping.rule.ruleDesc}</td>
		<td width="70px">${currentMapping.spsId.spsId}</td>
		<td width="200px">${currentMapping.spsId.spsDesc}</td>	
	</tr>
			<c:if test="${! empty currentMapping.variableMappingStatus}">
				<tr>
					<td width="100px"></td>
					<td width="150px"></td>
					<td width="90x"></td>
					<td width="90px"></td>
					<td width="75px"></td>
					<td width="170px"></td>
					<td width="170px" id="statusImageContainer" align="right"><c:if
						test="${currentMapping.variableMappingStatus == 'BUILDING'}">
						<img src="${imageDir}/building.gif" />
					</c:if> <c:if
						test="${currentMapping.variableMappingStatus == 'BEING_MODIFIED'}">
						<img src="${imageDir}/modified.gif" />
					</c:if> <c:if
						test="${currentMapping.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
						<img src="${imageDir}/totest.gif" />
					</c:if> <c:if
						test="${currentMapping.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION'}">
						<img src="${imageDir}/toproduction.gif" />
					</c:if> <c:if
						test="${currentMapping.variableMappingStatus == 'PUBLISHED'}">
						<img src="${imageDir}/published.gif" />
					</c:if> <c:if
						test="${currentMapping.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<img src="${imageDir}/transferred.gif" />
					</c:if> <c:if
						test="${currentMapping.variableMappingStatus == 'NOT_APPLICABLE'}">
						<img src="${imageDir}/notapplicable.gif" />
					</c:if> <input type="hidden" name="fromMappingView" value="true"
						id="fromMappingView" /></td>
				</tr>
			</c:if>
		</c:if>
</TBODY>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="880px" class="Pd3">
</table>

		
	<div style="border-top:2px solid #d9e5eb;margin-top:10px;">	<!--viewEditContainer Starts-->
	
	
	<table border="0" cellpadding="0" cellspacing="0" width="880px" class="Pd2 auditTrailTable" id="viewMappingTable" >
								<tr class="createEditTableShade">
									<td width="220px" class="headText">EB03</td>
									<td width="330px" class="headText">Message</td>
									<td width="110px" class="headText">Msg Reqd</td>
									<td width="220px" class="headText">Note Type</td>
								</tr>
							
								<c:if test="${ empty eB03AssnList }">	
									<tr>
										<td colspan = "4" align="center" > No mapping found </td>
									</tr>
								</c:if>
								<c:if test="${not empty eB03AssnList }">								
								<c:set var="ebRowCount" value="0" />

								<c:forEach items="${eB03AssnList}" var="eb03Assn" begin="0">
									<tr class="${ebRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  >	
										
										<td width="220px">${eb03Assn.eb03.value} &nbsp;<c:out value = "-"/> &nbsp; ${eb03Assn.eb03.description}</td>
										<td width="330px">
										<c:if test="${empty eb03Assn.message}">
										-
										</c:if>
										${eb03Assn.message}</td>

										<td width="110px">${eb03Assn.msgReqdInd}</td>
										<td width="220px">${eb03Assn.noteType.value} &nbsp;<c:out value = "-"/> &nbsp; ${eb03Assn.noteType.description}</td>
									</tr>
								<c:set var="ebRowCount" value="${ebRowCount + 1}"/>
								</c:forEach>
								</c:if>
	</table>

	
			<div id="viewAuditTrailId" style="border-top:2px solid #d9e5eb;margin-top:10px;" align ="right">
				<div>
						<div>&nbsp;</div>
							<table border="0" cellpadding="0" cellspacing="0" width="520px" class="Pd2 auditTrailTable" id="auditTrailsTable" align ="right" >
								<tr class="createEditTableShade">
									<td width="90px" class="headText" align ="left">Date</td>
									<td width="93px" class="headText" align ="left">User ID</td>
									<td width="180px" class="headText" align ="left">System Comment</td>
									<td width="250px" class="headText" align ="left">User Comment</td>
								</tr>
							
								<c:if test="${empty currentMapping.auditTrails}">
									<tr>
									<td colspan="4" align = "center">No audit trail found</td>
									</tr>
								</c:if>
								<c:if test="${! empty currentMapping.auditTrails}">									
								<c:set var="viewHistoryRowCount" value="0" />
								<c:forEach items="${currentMapping.auditTrails}" var="viewAudit">
									<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  >	
										<fmt:timeZone value="PST">
										<td width="90px" align ="left"><c:out value="${viewAudit.createdAuditDate}"/></td>
										<td width="93px" align ="left">${viewAudit.createdUser}</td>
										<td width="180px" align ="left">
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
										<td width="250px" align ="left">${viewAudit.userComments}</td>
										</fmt:timeZone>	
									</tr>
								<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
								</c:forEach>
								</c:if>
						</table>
						
				</div>
			</div>
		
		</div><!--viewEditContainer Ends-->

</form>
</body>
</html>

