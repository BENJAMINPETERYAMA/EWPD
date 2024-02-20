<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>

<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>
<script>
	function toggle(){
	 	$("#message").slideToggle("slow");					
	}
	function showStackTrace(){	
		 $("#stackPanel").css("visibility", "visible");	
		// $("#stacktrace").slideToggle("slow");	
	}
</script>
</head>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<div>
<h1></h1>
</div>
<div>
<h3>An internal error has occurred. (500, Internal Server Error)</h3>
<div>&nbsp;&nbsp;</div>
<h6 id='infoHeader' class='ui-accordion-header ui-helper-reset ui-state-default ui-corner-all' role='tab' aria-expanded='false' tabindex='-1'>
<a href='#' tabindex='-1' >Message</a></h6>
<div id='infoPanel' class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom' style='display: block;height:25px;' role='tabpanel'>
 <p id="message"><font color='red'>${message}</font></p>
</div>
<h6 id='stackHeader' class='ui-accordion-header ui-helper-reset ui-state-default ui-corner-all' role='tab' aria-expanded='false' tabindex='-1'>
<a href='#' tabindex='-1' onclick = "showStackTrace();">Details</a></h6>
<div id='stackPanel' class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom' style='visibility:hidden;display:block;height:315px;overflow:auto;' role='tabpanel'>
 <p id="stacktrace"><font color='blue'>${stackTrace}</font></p>
</div>
<div style="visibility:hidden">$#~${message}$$~</div>
</div>
</div>
<!-- container Ends-->

<div class="footer"><!-- footer Starts-->
<!-- footer Ends-->
<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="${context}/viewlandingpage.html">Contract Varaible Mapping</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="${context}/simulation/viewSimulationRequest.html">Simulation Tool</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>	
			</tr>
</table>
</div>	
</div>
<!-- wrapper Ends-->