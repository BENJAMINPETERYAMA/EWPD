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

<div style="width:500px;">	<!--viewEditContainer Starts-->
			<table style="width:500px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">				
				<tr class="createEditTable1-Viewdata">
					<td width="75px" class="headText">Rule ID</td>
					<td width="150px" class="headText">Rule Description</td>
					<td width="75px" class="headText">SPS ID</td>
					<td width="150px" class="headText">SPS Description</td>
					<td class="headText">Status</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td >${currentMapping.rule.headerRuleId}</td>
					<td >${currentMapping.rule.ruleDesc}</td>
					<td>${currentMapping.spsId.spsId}</td>
					<td >${currentMapping.spsId.spsDesc}</td>
					<td>${currentMapping.variableMappingStatus}</td>
				</tr>
				<tr>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				<td>&#160;</td>
				</tr>
				<tr class="createEditTable1-Viewdata">
					<td class="headText" colspan="3"></td>
					<td class="headText" colspan="2"></td>
				</tr>			
														
			</table>
			
			<div style="margin-top:10px;">
					<table border="0" cellpadding="0" cellspacing="0" width="525px" class="Pd2 auditTrailTable" id="viewMappingTable" >
												
											
												<c:if test="${ empty eB03AssnList }">	
													<tr>
														<td colspan = "4" align="center" > No mapping found </td>
													</tr>
												</c:if>
												<c:if test="${not empty eB03AssnList }">								
												<c:set var="viewEB03AssnRowCount" value="0" />
												<tr class="createEditTableShade">
													<td width="125px" class="headText">EB03</td>
													<td width="245px" class="headText">Message</td>
													<td width="45px" class="headText">Msg Reqd</td>
													<td width="100px" class="headText">Note Type</td>
												</tr>
												<c:forEach items="${eB03AssnList}" var="eb03Assn" begin="0">
													<tr class="${viewEB03AssnRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"  >	
														
														<td width="125px">${eb03Assn.eb03.value} &nbsp;<c:out value = "-"/> &nbsp; ${eb03Assn.eb03.description}</td>
														<td width="245px">
														<c:if test="${empty eb03Assn.message}">
														-
														</c:if>
														${eb03Assn.message}</td>
														<td width="45px">${eb03Assn.msgReqdInd}</td>
														<td width="100px">${eb03Assn.noteType.value} &nbsp;<c:out value = "-"/> &nbsp; ${eb03Assn.noteType.description}</td>
													</tr>
												<c:set var="viewEB03AssnRowCount" value="${viewEB03AssnRowCount + 1}"/>
												</c:forEach>
												</c:if>
					</table>
				</div>
</div>			
</body>
</html>