<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="normal">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<form name="viewgroupRule" method="post">
<div>
<input id="titleName" type="hidden" value="${title}"/>
<div id="sequenceViewDiv" style="height:60px;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" heigth="100%" class="Pd3">
		<THEAD>
			<tr class="createEditTable1">
				<td class="tableHeader" style="width: 90px">Rule ID</td>
				<td class="tableHeader">Rule Description</td>
				<td class="tableHeader">Rule Search Word1</td>
				<td class="tableHeader">Rule Search Word2</td>
				<td class="tableHeader">Rule Search Word3</td>
			</tr>
		</THEAD>
		<c:if test="${not empty info_messages}">
				<tr>
					<td nowrap colspan="5" style="vertical-align: middle;text-align:center;">
						<c:forEach items="${info_messages}" var="info_message">
				   			  <p ><b><font color='blue'> # <c:out value="${info_message}" /></font></b></p>
				        </c:forEach> 
					</td>
				</tr>
			</c:if>
		<tr>
			<td>${ruleInfo.hippaSegment.name}</td>
			<td style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;width:220px">${ruleInfo.hippaSegment.description}</td>
			<td>${ruleInfo.searchWord1}</td>
			<td>${ruleInfo.searchWord2}</td>
			<td>${ruleInfo.searchWord3}</td>
		</tr>
	</table>
</div>
<br/>
<br/>

<c:if test="${fn:length(groupRulesList) > 1}">
<div id="sequenceViewDiv" style="height:400px;">
<table cellpadding="0" cellspacing="0" style="width: 98%"
	class="Pd3 ruleIndicatorTable" id="ruleIndicatorTable">
	
	<tr class="createEditTableShade">
		<td class="headText">Rule ID</td>
		<td class="headText">Rule Description</td>
		<td class="headText">&nbsp;</td>
	</tr>


		<c:set var="rowCount" value="0" />
		<c:forEach items="${groupRulesList}" begin="0" var="grplistVar">
			<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
				<td  class="styleForTd135">${grplistVar.hippaSegment.name}</td>
				<td class="styleForTd135" >${grplistVar.hippaSegment.description}</td>
				<td  class="styleForTd135" ><img src="${imageDir}/search_icon.gif"
					alt="View Rule" title="View Rule" 
					onclick="viewRuleSequenceInDetail('${grplistVar.hippaSegment.name}','/ajaxruleview.ajax');" /></td>
			</tr>
			<c:set var="rowCount" value="${rowCount + 1}" />
		</c:forEach>
</table>
</div>
</c:if>
</div>
</form>
</body>
</html>