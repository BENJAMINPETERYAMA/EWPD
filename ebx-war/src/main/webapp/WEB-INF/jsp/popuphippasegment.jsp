<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript">

	function searchHippaCode(dis){

		if(dis.keyCode=='13'){		
		var searchText = $("#searchText").val();
		var hippaSegmentName =$("#hippasegmentName").val();
		$.ajax({
					url:"../ajaxpopuphippasegment.ajax",
					type:"POST",
					dataType:"html",
					data: "hippaSegmentName="+hippaSegmentName+"&searchText="+searchText,
					success: function(data){
							$("#hippaCodePopUpDiv").html(data);
							check(hippaSegmentName);	
					} 					
			});	
			$("#searchText").value = searchText;
		}
	}// end searchHippaCode

</script>
<table border="0">
	<tr>
		<td style="vertical-align: middle;">Search&nbsp;<input style="vertical-align: middle;" id="searchText" type="text" class="inputbox65" value="${searchText}" onkeypress="searchHippaCode(event);"/>&nbsp;</td>
		<td>&nbsp;<img style="vertical-align: middle;" src="${imageDir}/select_but.gif" onclick="selectedHippaCodeValues();" /></td>
	</tr>
</table>
<br/>	

<form id="hippaCodeSelectedValues">
<div class="overflowContainer">
<table border="0" width="100%" cellpadding="0" cellspacing="0" id="hippaCodeTable" class="Pd2 hippaSegmentPopupTable">
<tr class="createEditTableShade">	
		<td  class="headText" ></td>
		<td  class="headText" >Value</td>
		<td  class="headText" >Description</td>
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
<c:set var="rowCount" value="0" />
<c:set var="ifHippaCodeExists" value="false" />

<!-- EB Codes -->
<c:if test="${hippaSegment.name=='EB01'}">
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

<c:if test="${hippaSegment.name=='EB02'}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" id="trHippaCode">
		   	<td><input name="hippaCode" id="hippaCode" type="radio" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td id="tdHippaCodeValue">${hippaCodeValue.value}</td>
			<td id="tdHippaCodeDesc">${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>

<c:if test="${hippaSegment.name=='EB03'}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="hippaCode" id="hippaCode" type="checkbox" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td>${hippaCodeValue.value}</td>
			<td>${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>

<c:if test="${hippaSegment.name=='EB06'}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td ><input name="hippaCode" id="hippaCode" type="radio" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td>${hippaCodeValue.value}</td>
			<td>${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>

<c:if test="${hippaSegment.name=='EB09'}">
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


<!-- HSD COdes -->
<c:if test="${hippaSegment.name=='HSD01'}">
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

<c:if test="${hippaSegment.name=='HSD03'}">
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

<c:if test="${hippaSegment.name=='HSD05'}">
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

<c:if test="${hippaSegment.name=='HSD07'}">
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

<c:if test="${hippaSegment.name=='HSD08'}">
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

<c:if test="${hippaSegment.name=='ACCUM'}">
	<c:forEach items="${hippaSegment.hippaCodePossibleValues}" var="hippaCodeValue">
		<c:set var="ifHippaCodeExists" value="true" />
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
		   	<td><input name="hippaCode" id="hippaCode" type="checkbox" value="${hippaCodeValue.value}~${hippaCodeValue.description}">&nbsp;&nbsp;</td>
			<td>${hippaCodeValue.value}</td>
			<td>${hippaCodeValue.description}</td>
		</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
</c:if>

<c:if test="${hippaSegment.name=='III02'}">
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
<c:if test="${hippaSegment.name=='NOTE_TYPE_CODE'}">
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

<!-- changed for reference data values - removed NOTE TYPE CODE check  --> 

<c:if test="${hippaSegment.name=='ACCUMULATOR REFERENCE'}">
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
<c:if test="${ifHippaCodeExists=='true'}">
	<input type="hidden" id="hippaCodeExists" value="true"/>
</c:if>
<c:if test="${ifHippaCodeExists=='false'}">
	<input type="hidden" id="hippaCodeExists" value="false"/>
</c:if>
</table>
</div>
</form>
