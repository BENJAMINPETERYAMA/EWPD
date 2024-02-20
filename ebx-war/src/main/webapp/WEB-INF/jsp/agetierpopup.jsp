<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<table border="0">
	<tr>
			<td style="vertical-align: middle;">Search&nbsp;<input style="vertical-align: middle;" id="searchText" type="text" class="inputbox65" value="${searchText}" onkeypress="searchAgeTierVariable(event,'../ajaxagetierpopup.ajax');"/>&nbsp;</td>
			<td>&nbsp;<img style="vertical-align: middle;" src="${imageDir}/select_but.gif" onclick="selectedAgeTierVariableValue();" /></td>
	</tr>
</table>
<br/>	

<form id="ageTierSelectedValues">
<div class="overflowContainer" style="width:99%;">
<table border="0" cellpadding="0" cellspacing="0" id="hippaCodeTable" class="Pd2 hippaSegmentPopupTable" style="width:100%;">
<tr class="createEditTableShade">	
			<td  class="headText" ></td>
			<td  class="headText" >Variable Id </td>
			<td  class="headText">Description</td>
</tr>
<c:if test="${not empty info_messages}">
	<tr>
		<td nowrap colspan="3" style="vertical-align: middle;text-align:center;">
			<c:forEach items="${info_messages}" var="info_message">
	   			  <p ><b><font color=black> # </font><font color='blue'><c:out value="${info_message}" /></font></b></p>
	        </c:forEach> 
		</td>
	</tr>
</c:if>
<input type="hidden" id="ageTierName" value="${ageTierName}"/>
<input type="hidden" id="variableIdHidden" value="${variableIdHidden}"/>


<c:set var="rowCount" value="0" />
<c:set var="ifHippaCodeExists" value="false" />

<!-- EB Codes -->
<!-- changed for reference data values - removed NOTE TYPE CODE check  --> 
	<c:forEach items="${tierVariables}" var="tierVariables">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="hippaCode" id="hippaCode" type="checkbox" value="${tierVariables.variableId}~${tierVariables.variableDescription}">&nbsp;&nbsp;</td>
			<td>${tierVariables.variableId}</td>
			<td>${tierVariables.variableDescription}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>


<c:if test="${ifHippaCodeExists=='true'}">
	<input type="hidden" id="hippaCodeExists" value="true"/>
</c:if>
<c:if test="${ifHippaCodeExists=='false'}">
	<input type="hidden" id="hippaCodeExists" value="false"/>
</c:if>
</table>
</div>
</form>