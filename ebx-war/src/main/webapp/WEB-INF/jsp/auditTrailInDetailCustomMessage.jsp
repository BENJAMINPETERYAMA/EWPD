<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="Pd2 popupTable" >
	<tr class="createEditTableShade">		
		<td class="headText">Message Text</td>
		<td class="headText" width="3%">Message Required</td>
		<td class="headText">Note Type Code</td>
				
	</tr>

	<c:if test="${!empty info_messages}">
		<tr rowspan="1" >
			<td colspan="19" align="center">
				<c:forEach items="${info_messages}" var="info_message">
		   			  <p ><b><font color=black> # </font><font color='blue'><c:out value="${info_message}" /></font></b></p>
		        </c:forEach> 
			</td>
		</tr>
	</c:if>
	<c:set var="rowCount" value="0" />
	<c:forEach items="${auditTrailInDetailList}" var="auditTrailDetail">
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">			
			<td>${auditTrailDetail.hippaSegmentMappingVO.message}</td>			
			<td>${auditTrailDetail.hippaSegmentMappingVO.messageTypeRequired}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.noteTypeCode}</td>		
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</table>