<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<table border="0">
	<tr>
		<c:if test="${(hippaSegmentName =='ACCUM')}">
				<td class="headText" style="vertical-align: middle; ">Name&nbsp;
				<input style="vertical-align: middle;width: 50px;" id="searchAccumName" type="text" class="inputbox65" value="${searchAccumName}" onkeypress="searchAccum(event,'../ajaxaccumpopup.ajax');"/>&nbsp;</td>
				<td class="headText" style="vertical-align: middle;">Description&nbsp;
				<input style="vertical-align: middle;" id="searchAccumDesc" type="text" class="inputbox65" value="${searchAccumDesc}" onkeypress="searchAccum(event,'../ajaxaccumpopup.ajax');"/>&nbsp;
				</td>
			<td>&nbsp;<img style="vertical-align: middle;" src="${imageDir}/select_but.gif" onclick="selectAccumValues();" /></td>
			<td></td>
		</c:if>
	</tr>
</table>
<br/>	

<form id="accumsSelectedValues">
<table>
<tr class="createEditTableShade" style="font-size: 11px;">	
		<c:if test="${(hippaSegmentName =='ACCUM')}">
			<td>
				<DIV class="headText" style="float: left; width: 25px;">&nbsp;</DIV>
				<DIV class="headText" style="float: left; width: 81px;">Name</DIV>
				<DIV class="headText" style="float: left; width: 150px;">Description</DIV>
				<DIV class="headText" style="float: left; width: 50px;">Root Mbr Code</DIV>
				<DIV class="headText" style="float: left; width: 50px;">Lookup Ind</DIV>
				<DIV class="headText" style="float: left; width: 50px;">Occurs Flag</DIV>
				<DIV class="headText" style="float: left; width: 50px;">Days Flag</DIV>
				<DIV class="headText" style="float: left; width: 50px;">Monies Flag</DIV>
				<DIV class="headText" style="float: left; width: 60px;">System</DIV>
			</td>
<!--			<td  class="headText" width = "40px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
<!--			<td  class="headText" width = "70px" >Name</td>-->
<!--			<td  class="headText" width = "110px">Description</td>-->
<!--			<td  class="headText" width = "65px">Root Mbr Code</td>-->
<!--			<td  class="headText" width = "65px">Lookup Ind</td>-->
<!--			<td  class="headText" width = "65px">Occurs Flag</td>-->
<!--			<td  class="headText" width = "60px">Days Flag</td>-->
<!--			<td  class="headText" width = "65px">Monies Flag</td>-->
<!--			<td  class="headText" width = "70px">System</td>-->
		</c:if>
</tr>
</table>
<div class="overflowContainerAccum" style="width :580px; " >
<table border="0" cellpadding="0" cellspacing="0" width="580px" id="hippaCodeTable" class="Pd2 hippaSegmentPopupTable">

<c:if test="${not empty info_messages}">
	<tr>
		<td nowrap colspan="6" style="vertical-align: middle;text-align:center;">
			<c:forEach items="${info_messages}" var="info_message">
	   			  <p ><b><font color=black> # </font><font color='blue'><c:out value="${info_message}" /></font></b></p>
	        </c:forEach> 
		</td>
	</tr>
</c:if>
<input type="hidden" id="accumName" value="${hippaSegmentName}"/>
<input type="hidden" id="mappingItem" value="${mappingItem}"/>
<c:set var="rowCount" value="0" />
<c:set var="ifHippaCodeExists" value="false" />

<!-- EB Codes -->
<c:if test="${(hippaSegmentName =='ACCUM')}">
	<c:forEach items="${accumulators}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<TD>
			<DIV class="accumPopupTableStyle" style="float: left; width: 18px; height: 27px;"  ><input name="accumCode" id="accumCode" type="checkbox" value="${hippaCodeValue.svcCode}~${hippaCodeValue.name}">&nbsp;</DIV>
			<DIV class="accumPopupTableStyle" style="float: left; width: 75px; height: 27px;" >&nbsp;${hippaCodeValue.svcCode}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 150px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.name}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 50px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.rootMbrCde}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 50px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.lookupInd}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 50px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.occursFlg}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 50px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.daysFlg}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 50px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.moniesFlg}</DIV>
			<DIV class="accumPopupTableStyle" style="width : 60px; float: left; height: 27px;" >&nbsp;${hippaCodeValue.system}</DIV>
</TD>
<!--		   	<td width = "30px"><input name="accumCode" id="accumCode" type="checkbox" value="${hippaCodeValue.svcCode}~${hippaCodeValue.name}">&nbsp;</td>-->
<!--			<td width = "60px">${hippaCodeValue.svcCode}</td>-->
<!--			<td width = "130px">${hippaCodeValue.name}</td>-->
<!--			<td width = "80px">${hippaCodeValue.rootMbrCde}</td>-->
<!--			<td width = "80px">${hippaCodeValue.lookupInd}</td>-->
<!--			<td width = "80px">${hippaCodeValue.occursFlg}</td>-->
<!--			<td width = "80px">${hippaCodeValue.daysFlg}</td>-->
<!--			<td width = "80px">${hippaCodeValue.moniesFlg}</td>-->
<!--			<TD width = "60px">${hippaCodeValue.system}</TD>-->
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>
<c:if test="${ifHippaCodeExists=='true'}">
	<input type="hidden" id="hippaCodeExists" value="true"/>
</c:if>
<c:if test="${ifHippaCodeExists=='false'}">
	<input type="hidden" id="hippaCodeExists" value="false"/>
</c:if>
</table>
</div>
</form>