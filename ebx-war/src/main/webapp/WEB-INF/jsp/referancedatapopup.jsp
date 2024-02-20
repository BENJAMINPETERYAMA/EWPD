<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<table border="0">
	<tr>
			<td style="vertical-align: middle;">Search&nbsp;<input style="vertical-align: middle;" id="searchText" type="text" class="inputbox65" value="${searchText}" onkeypress="searchReferenceDataPopUp(event);"/>&nbsp;</td>
			<td>&nbsp;<img style="vertical-align: middle;" src="${imageDir}/select_but.gif" onclick="selectedReferanceDataValues();" /></td>
	</tr>
</table>
<br/>	

<form id="referanceDataSelectedValues">
<div class="overflowContainer" style="width:99%;">
<table border="0" cellpadding="0" cellspacing="0" id="hippaCodeTable" class="Pd2 hippaSegmentPopupTable" style="width:100%;">
<tr class="createEditTableShade">	
			<td  class="headText" ></td>
			<td  class="headText" >Value</td>
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
<input type="hidden" id="referanceDataName" value="${referanceDataName}"/>
<c:set var="rowCount" value="0" />
<c:set var="ifReferanceDataExists" value="false" />

<!-- changed for reference data values --> 



<c:if test="${(referanceDataName=='RULEID') || (referanceDataName=='DATATYPE')}">
	<c:forEach items="${referanceDataList}" var="referanceDataPopUpVO">
		<c:set var="ifReferanceDataExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="referanceData" id="referanceData" type="checkbox" value="${referanceDataPopUpVO.value}~${referanceDataPopUpVO.desc}">&nbsp;&nbsp;</td>
			<td>${referanceDataPopUpVO.value}</td>
			<td >${referanceDataPopUpVO.desc}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>

<c:if test="${ifReferanceDataExists=='true'}">
	<input type="hidden" id="referenceDataExists" value="true"/>
</c:if>
<c:if test="${ifReferanceDataExists=='false'}">
	<input type="hidden" id="referenceDataExists" value="false"/>
</c:if>
</table>
</div>
</form>