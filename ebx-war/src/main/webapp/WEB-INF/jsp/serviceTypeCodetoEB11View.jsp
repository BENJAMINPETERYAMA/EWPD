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

</script>
<form name="viewForm"  method = "post">
<div id="viewEditContainerPopup1">	<!--viewEditContainer Starts-->
<c:set var="rowCount" value="0" />
	<c:if test="${! empty resultList}">
		<c:forEach items="${resultList}" var="resultList">
			<table width="600px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">
				<tr class="createEditTable1-Viewdata">
					<td width="28%" class="headText" align="left">LOB/State</td>
					<td width="5%">&nbsp;</td>
					<td width="28%" class="headText" align="left">MBU</td>
					<td width="5%">&nbsp;</td>
					<td width="28%" class="headText" align="left">
					<input type="checkbox" <c:if test="${resultList.ssbIndicator == 'Y'}">checked</c:if>
					 disabled name="ssbIndicator" id="ssbIndicator" />
					SSB</td>
					<td width="6%">&nbsp;</td>
				</tr>
				<tr>
					<td width="auto" align="left">${resultList.lineOfBusiness}</td>
					<td width="auto"></td>
					<td width="auto" align="left">${resultList.commaSeperatedMbu}</td>
					<td width="auto"></td>
				<tr>
		
				<c:set var="rowCount" value="0" />
				<c:set var="count" value="1" />
			</table>
		</c:forEach>
	</c:if>
</div>
<table><tr><td>&nbsp;</td></tr></table>
		<table width="600px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">
				<tr class="createEditTable1-Viewdata">
					<td width="28%" class="headText" align="left">EB03</td>
					<td width="5%">&nbsp;</td>
					<td width="28%" class="headText" align="left">EB11</td>
					<td width="5%">&nbsp;</td>
					<td width="28%" class="headText" align="left">Place Of Service</td>
					<td width="6%">&nbsp;</td>
				</tr>
				
		</table>				
<div id="viewEditContainerPopup2" style="overflow-x: hidden;  overflow: auto; height: 100px" >	<!--viewEditContainer Starts-->
<c:set var="rowCount" value="0" />
	
			<table width="600px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">
				
			<c:if test="${! empty servicetypeMappingsList}">
				<c:forEach items="${servicetypeMappingsList}" var="servicetypeMappingsList">
					<tr>
					<td width="28%" align="left">${servicetypeMappingsList.serviceTypeCode}</td>
					<td width="5%"></td>
					<td width="28%" align="left">${servicetypeMappingsList.eb11}</td>
					<td width="5%"></td>
					<td width="28%" align="left">${servicetypeMappingsList.placeOfService}</td>
					<td width="5%"></td>
					</tr>
				</c:forEach>
				<c:set var="rowCount" value="0" />
				<c:set var="count" value="1" />
			</c:if>	
			</table>
</div>
<div id="viewEditContainerPopup3">
<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		<tr>
			<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
			<td width="0" height="20">
				<c:if test="${! empty resultList}">
					<c:forEach items="${resultList}" var="resultList">
						<a href="#"  onclick='viewHistoryofServiceTypeMappings("${resultList.lineOfBusiness}");'>View&nbsp;History</a>
				</c:forEach>
				</c:if>
			</td>
			<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
		</tr>
	</table>
</div>
</form>
</body>
</html>