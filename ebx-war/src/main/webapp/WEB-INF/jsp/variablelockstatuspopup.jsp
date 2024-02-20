<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<input type="hidden" id="hiddenTrigger" value="${trigger}"/>
<input type="hidden" id="varnumberstatus" value="${varexist}"/>
<body>
<div  style ="padding: 5px;">
<table border="0">
	<tr>
		<td>${confirmmessage}</td>
	</tr>
	<tr><td><br/></td></tr>
</table>
</div>
<div style="height: 60px;overflow: auto; border-bottom: #a6c9e2 1px solid; border-top: #a6c9e2 1px solid; padding: 5px;">
<table>
	
	<c:forEach items="${variableList}" var="variable">
	<tr>
		<td>${variable}</td>
	</tr>
	</c:forEach>
</table>
</div>
<div style ="padding: 5px;">
<table>
	<tr>
		<td>${messagefoter}<br/> Do you want to continue?</td>
	</tr>
</table>
</div>

<table width="100%">
	<tr align="right">
		<td align="right">
		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" type="button" onclick="closevariableerrorpopup();">
		<span class="ui-button-text" >
			Cancel
		</span>
		</button>
		&nbsp;&nbsp;&nbsp;
		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" type="button" onclick="perfomemassupdate();">
		<span class="ui-button-text" >
			Ok
		</span>
		</button>
		</td>
	</tr>
</table>
</body>
<script>
	function perfomemassupdate(){
		var trigger = document.getElementById('hiddenTrigger').value;
		openMassUpdateConfirmation(trigger);
	}
</script>