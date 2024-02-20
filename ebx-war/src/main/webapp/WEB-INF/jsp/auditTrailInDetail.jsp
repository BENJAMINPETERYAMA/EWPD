<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="Pd2 popupTable" >
	<tr class="createEditTableShade">
		<td class="headText">EB01</td>
		<td class="headText">EB02</td>
		<td class="headText">EB03</td>
		<td class="headText">EB06</td>
		<td class="headText">EB09</td>

		<td class="headText">HSD01</td>
		<td class="headText">HSD02</td>
		<td class="headText">HSD03</td>
		<td class="headText">HSD04</td>
		<td class="headText">HSD05</td>
		<td class="headText">HSD06</td>
		<td class="headText">HSD07</td>
		<td class="headText">HSD08</td>

		<td class="headText">Accum</td>
		<td class="headText">UM Rule</td>
		<td class="headText" width="3%">Message Type Required</td>
		<td class="headText" width="3%">Sensitive Benefit Indicator</td>
		<td class="headText">NoteType Code</td>
		<td class="headText">Message</td>
		<td class="headText">2120 Loop NM1 Message Segment</td>
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
			<td>${auditTrailDetail.hippaSegmentMappingVO.eb01}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.eb02}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.eb03}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.eb06}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.eb09}</td>
			
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd01}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd02}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd03}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd04}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd05}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd06}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd07}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.hsd08}</td>
			
			<td>${auditTrailDetail.hippaSegmentMappingVO.accum}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.utilizationMgmntRule}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.messageTypeRequired}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.sensitiveBenefitIndicator}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.noteTypeCode}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.message}</td>
			<td>${auditTrailDetail.hippaSegmentMappingVO.nm1MessageSegment}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</table>