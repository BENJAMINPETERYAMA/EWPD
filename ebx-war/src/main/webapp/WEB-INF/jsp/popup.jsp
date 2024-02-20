<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<table border="0">
	<tr>
			<td style="vertical-align: middle;">Search&nbsp;<input style="vertical-align: middle;" id="searchText" type="text" class="inputbox65" value="${searchText}" onkeypress="searchHippaCode(event,'../ajaxpopuphippasegment.ajax');"/>&nbsp;</td>
			<td>&nbsp;<img style="vertical-align: middle;" src="${imageDir}/select_but.gif" onclick="selectedHippaCodeValues();" /></td>
	</tr>
</table>
<br/>

<form id="hippaCodeSelectedValues">
<div class="overflowContainer" style="width:99%;">
<table border="0" cellpadding="0" cellspacing="0" id="hippaCodeTable" class="Pd2 hippaSegmentPopupTable" style="width:100%;">
<tr class="createEditTableShade">	
			<td  class="headText" ></td>
			<td  class="headText" >Value</td>
			<td  class="headText">Description</td>
			<c:if test="${hippaSegment.name=='UMRULE'}"> 	
				<td  class="headText" style="width:30px;"></td>
			</c:if>
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
<input type="hidden" id="hippasegmentName" value="${hippaSegment.name}"/>
<input type="hidden" id="mappingItem" value="${mappingItem}"/>
<input type="hidden" id="pageName" name="pageName" value="${pageName}"/>
<c:set var="rowCount" value="0" />
<c:set var="ifHippaCodeExists" value="false" />
<!-- EB Codes -->
<!-- changed for reference data values - removed NOTE TYPE CODE check --> 
<c:if test="${(hippaSegment.name=='EB01' || (hippaSegment.name=='EB02') || (hippaSegment.name=='EB06') 
				|| (hippaSegment.name=='EB09') || (hippaSegment.name=='HSD01') || (hippaSegment.name=='HSD03')
				|| (hippaSegment.name=='HSD05') || (hippaSegment.name=='HSD07') || (hippaSegment.name=='HSD08')
				|| (hippaSegment.name=='NOTE_TYPE_CODE')
				|| (hippaSegment.name=='2120_LOOP_NM1_MESSAGE_SEGMENT')
				|| (hippaSegment.name=='ACCUMULATOR REFERENCE'))}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="hippaCode" id="hippaCode" type="radio" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td>${hippaCodeValue.value}</td>
			<td>${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>


<c:if test="${(hippaSegment.name=='EB03') || (pageName == 'headerRuleMappingPage' && (( hippaSegment.name=='III02') || (hippaSegment.name=='III02_0') || (hippaSegment.name=='III02_1') || (hippaSegment.name=='III02_2') || (hippaSegment.name=='III02_3')
		|| (hippaSegment.name=='III02_4') || (hippaSegment.name=='III02_5') || (hippaSegment.name=='III02_6') || (hippaSegment.name=='III02_7')
		|| (hippaSegment.name=='III02_8') || (hippaSegment.name=='III02_9') || (hippaSegment.name=='III02_10') || (hippaSegment.name=='III02_11')
		|| (hippaSegment.name=='III02_12') || (hippaSegment.name=='III02_13') || (hippaSegment.name=='III02_14') || (hippaSegment.name=='III02_15')))}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="hippaCode" id="hippaCode" type="checkbox" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td>${hippaCodeValue.value}</td>
			<td >${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>
<c:if test="${hippaSegment.name=='UMRULE'}">
<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td width="12%"><input name="hippaCode" id="hippaCode" type="checkbox" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td width="20%">${hippaCodeValue.value}</td>
			<td style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;" width="50%">${hippaCodeValue.description}</td>
			<td  width="20%" align = "left" ><img src="${imageDir}/search_icon.gif" alt="View Rule" title="View Rule" onclick="viewRuleSequenceInDetail('${hippaCodeValue.value}','/ajaxruleview.ajax');"/></td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>
<!-- SSCR19537 April04 -  Start -->
<c:if test="${(pageName == 'variableMappingPage' &&  ((hippaSegment.name=='III02') || (hippaSegment.name=='III02_0') || (hippaSegment.name=='III02_1') || (hippaSegment.name=='III02_2') || (hippaSegment.name=='III02_3')
		|| (hippaSegment.name=='III02_4') || (hippaSegment.name=='III02_5') || (hippaSegment.name=='III02_6') || (hippaSegment.name=='III02_7')
		|| (hippaSegment.name=='III02_8') || (hippaSegment.name=='III02_9') || (hippaSegment.name=='III02_10') || (hippaSegment.name=='III02_11')
		|| (hippaSegment.name=='III02_12') || (hippaSegment.name=='III02_13') || (hippaSegment.name=='III02_14') || (hippaSegment.name=='III02_15')))
		|| (hippaSegment.name=='NOTE_TYPE_CODE_0') || (hippaSegment.name=='NOTE_TYPE_CODE_1') || (hippaSegment.name=='NOTE_TYPE_CODE_2') || (hippaSegment.name=='NOTE_TYPE_CODE_3')
		|| (hippaSegment.name=='NOTE_TYPE_CODE_4') || (hippaSegment.name=='NOTE_TYPE_CODE_5') || (hippaSegment.name=='NOTE_TYPE_CODE_6') || (hippaSegment.name=='NOTE_TYPE_CODE_7')
		|| (hippaSegment.name=='NOTE_TYPE_CODE_8') || (hippaSegment.name=='NOTE_TYPE_CODE_9') || (hippaSegment.name=='NOTE_TYPE_CODE_10') || (hippaSegment.name=='NOTE_TYPE_CODE_11')
		|| (hippaSegment.name=='NOTE_TYPE_CODE_12') || (hippaSegment.name=='NOTE_TYPE_CODE_13') || (hippaSegment.name=='NOTE_TYPE_CODE_14') || (hippaSegment.name=='NOTE_TYPE_CODE_15')}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="hippaCode" id="hippaCode" type="radio" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td>${hippaCodeValue.value}</td>
			<td >${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>
<!-- SSCR19537 April04 -  End -->
<c:if test="${ifHippaCodeExists=='true'}">
	<input type="hidden" id="hippaCodeExists" value="true"/>
</c:if>
<c:if test="${ifHippaCodeExists=='false'}">
	<input type="hidden" id="hippaCodeExists" value="false"/>
</c:if>
</table>
</div>
</form>