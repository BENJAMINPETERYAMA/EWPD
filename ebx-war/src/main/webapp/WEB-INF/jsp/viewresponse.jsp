<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>
<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">


</script>
<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height:96%;"><!-- innerContainer Starts-->
<div class="Level1"><!-- Level1 Starts-->
<div class="titleBar">
<div class="headerTitle">Simulation Response Info</div>
</div>
<form name="simulationResponseForm" action="${context}/simulationresponsepagecontroller.html">
<!--Starts submitterContinue-->
<table border="0" cellpadding="0" cellspacing="0"	id="unMappedTable" class="mappedTable Pd3 shadedText">	
	<TBODY>
	<TABLE width="100%" cellpadding="1" cellspacing="0">
		<TR>
			<td width="80%">
			<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:97%">									
				<table width="60%" cellpadding="0" cellspacing="0">	
				<TR>
					<TD>&nbsp;</TD>
				</TR>
<p><textarea name="response" rows="16" cols="65">${outputxml}</textarea></p>
</FIELDSET>
		</td>
	</TR>
</TABLE>
</TBODY>
</table>
</form>
</div>
</div>
<!--UnmappedVariables Ends-->
<!--unmapped Ends-->

<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>
