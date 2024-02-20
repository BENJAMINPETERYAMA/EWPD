<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:set var="context" value="${pageContext.request.contextPath}"/>
</head>
<body>	
<script type="text/javascript">
function generateExcel() {	
	document.forms['view271Form'].action="${context}/simulation/generate271Excel.html";
	document.forms['view271Form'].submit();	
}

function saveText() {	
	document.forms['view271Form'].action="${context}/simulation/generate271Text.html";
	document.forms['view271Form'].submit();	
}

</script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<form name="view271Form" method="POST">
<div id="271Div" class="generate271Div" align="left"  >

<c:choose>
	<c:when test="<%=request.getHeader(\"User-Agent\").indexOf(\"MSIE 7.0\")!=-1%>">
		<textarea id="x12Response" name="x12Response" rows="25" cols="120">${x12Response}</textarea>
	</c:when>
	<c:otherwise>
		<textarea id="x12Response" name="x12Response" rows="25" cols="121">${x12Response}</textarea>
	</c:otherwise>
</c:choose>
	<font color="red" >${x12AAACode}</font>
	<br>
	<table width="492" height="45">
		<tr class="createEditTable1-Listdata">
			<td width="120"></td>
			<td height="20" align="center" width="100"><a href="#" onclick="saveText()"><img
				border="0" src="${imageDir}/Save_text.GIF" width="79" height="23"></a></td>
			<td height="20" width="150"><a href="#" onclick="generateExcel()"><img
				src="${imageDir}/Generate_excel.GIF" height="23" width="144"></a></td>
		</tr>
	</table>
</div>
</form>
</body>
</html>

