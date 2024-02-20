<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>

<body class="normal">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<form name="viewForm"  method = "post">
<c:if test="${viewtype == 'SPS'}">
<div id="variableInfoDiv">
<table width="550px" border="0" cellspacing="0" cellpadding="0"
	class="Pd3">
	<THEAD>
		<tr class="createEditTable1-Viewdata">
			<td width="120" class="headText">SPS ID</td>
			<td width="280" class="headText">Description</td>
			<td width="150" class="headText">Created Date</td>
			<td width="150" class="headText">Status</td>
			<td width="150" class="headText">PVA</td>
			<td width="150" class="headText">Data type</td>
			<td width ="40px" class="headText"></td>
		</tr>
	</THEAD>
	<TBODY>
		<c:set var="rowCount" value="0" />
		<c:set var="count" value="1" />
		
		<tr>
		<c:set var="pva" value="" />
		<c:set var="type" value="" />
		<c:set var="dataType" value="" />
		<input type="hidden" id="spsIdentifier" value="${currentMapping.spsId.spsId}"/>
		
			<c:if test="${!empty currentMapping.spsId.spsDetail}">
				<c:set var="pva" value="${currentMapping.spsId.spsDetail[0].spsPVA}" />
				<c:set var="type" value="${currentMapping.spsId.spsDetail[0].spsType}" />
				<c:set var="dataType" value="${currentMapping.spsId.spsDetail[0].spsDataType}" />
			</c:if>
			<td>${currentMapping.spsId.spsId}
				<input type="hidden" id="spsId" name="spsId" value="${currentMapping.spsId.spsId}" />
			</td>			
			<td>${currentMapping.spsId.spsDesc}
				<input type="hidden" id="spsIdDesc" name="spsIdDesc" value="${currentMapping.spsId.spsDesc}" />
			</td>
			<td>${currentMapping.formattedStringDate}
				<input type="hidden" id="createDatehd" name="createDatehd" value="${currentMapping.formattedStringDate}" />
			</td>
			<td>${currentMapping.variableMappingStatus}
				<input type="hidden" id="statushd" name="statushd" value="${currentMapping.variableMappingStatus}" />
			</td>
			<td>${pva}
				<input type="hidden" id="spsPVA0" name="spsPVA" value="${pva}" />
			</td>
			<td>${dataType}
				<input type="hidden" id="spsDataType0" name="spsDataType" value="${dataType}" />
			</td>
			<td></td>
		</tr>
		<c:set var="variableInfoDivScroll" value="false" />	
					<c:forEach items="${currentMapping.spsId.spsDetail}" var="spsDetailList" begin ="1">
						<c:set var="variableInfoDivScroll" value="true" />		
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>${spsDetailList.spsPVA}
								<input type="hidden" id="spsPVA${count}" name="spsPVA" value="${currentMapping.spsId.spsDetail[count].spsPVA}" />
							</td>
							<td>${spsDetailList.spsDataType}
								<input type="hidden" id="spsDataType${count}" name="spsDataType" value="${currentMapping.spsId.spsDetail[count].spsDataType}" />
							</td>		
							<td></td>					 
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					<c:set var="count" value="${count + 1}"/>
										
					</c:forEach>   
					<input type="hidden" id="totalCount" name="totalCount" value="${count}" />
		
					<c:if test="${variableInfoDivScroll == 'true'}">
						<script>
						$('#variableInfoDiv').height('80px');
						</script>
					</c:if>

	</TBODY>
</table>
</div>
<!-- by subash -->
<c:if test="${currentMapping.variableMappingStatus != 'UNMAPPED'}">	
<div id="viewEditContainerPopup1">	<!--viewEditContainer Starts-->
<table width="550px" border="0" cellpadding="0" cellspacing="0" class="Pd2 fL">	

<!-- Space row for EB values -->
<tr>
<c:if test="${! empty currentMapping.eb01.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.eb02.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.eb06.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.eb09.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
</tr>	
<!-- Space row for EB values ENDS -->

<tr>
<c:if test="${! empty currentMapping.eb01.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.eb02.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.eb06.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.eb09.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
</tr>
<tr class="createEditTable1-Viewdata">
<c:if test="${! empty currentMapping.hsd01.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD01</td>
</c:if>
<c:if test="${! empty currentMapping.hsd02.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD02</td>
</c:if>
<c:if test="${! empty currentMapping.hsd03.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD03</td>
</c:if>
<c:if test="${! empty currentMapping.hsd04.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD04</td>
</c:if>
<c:if test="${! empty currentMapping.hsd05.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD05</td>
</c:if>
<c:if test="${! empty currentMapping.hsd06.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD06</td>
</c:if>
<c:if test="${! empty currentMapping.hsd07.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD07</td>
</c:if>
<c:if test="${! empty currentMapping.hsd08.hippaCodeSelectedValues[0].value}">
<td class="headText">HSD08</td>
</c:if>
</tr>	
<tr>
<c:if test="${! empty currentMapping.hsd01.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd02.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd03.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd04.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd05.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd06.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd07.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd08.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
</tr>	
<tr class="createEditTable1-Viewdata">
<c:if test="${! empty currentMapping.hsd01.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd01.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd02.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd02.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd03.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd03.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd04.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd04.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd05.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd05.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd06.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd06.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd07.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd07.hippaCodeSelectedValues[0].value}</td>
</c:if>
<c:if test="${! empty currentMapping.hsd08.hippaCodeSelectedValues[0].value}">
<td>${currentMapping.hsd08.hippaCodeSelectedValues[0].value}</td>
</c:if>
</tr>	
<tr>
<c:if test="${! empty currentMapping.hsd01.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd02.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd03.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd04.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd05.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd06.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd07.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
<c:if test="${! empty currentMapping.hsd08.hippaCodeSelectedValues[0].value}">
<td>&#160;</td>
</c:if>
</tr>	
<tr class="createEditTable1-Viewdata">
					<td  class="headText">Finalized</td>					
					<td >&#160;</td>									
</tr>
<tr class="createEditTable1-Viewdata">
					<td>
					<c:if test="${currentMapping.finalized}">
					Y
					</c:if>
					<c:if test="${!currentMapping.finalized}">
					N
					</c:if>
					</td>
					<td >&#160;</td>									
</tr>
<c:if test="${! empty currentMapping.accum.hippaCodeSelectedValues[0].value}">
<tr class="createEditTable1-Viewdata">
					<td  class="headText">Accumulator SPS ID</td>
					<td>${currentMapping.accum.hippaCodeSelectedValues[0].value}</td>
					<td colspan="5">${currentMapping.accum.hippaCodeSelectedValues[0].description}</td>
</tr>
</c:if>
</table>
</div>
</c:if>
<!-- end by subash -->
</c:if>
<!-- <c:if test="${viewtype == 'RULE'}">
	<table width="450px" border="0" cellspacing="0" cellpadding="0"
		class="Pd3">
		
		<thead>
			<tr class="createEditTable1-Viewdata">
				<td width="120" class="headText">Rule ID</td>
				<td width="280" class="headText">Description</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="120">${currentMapping.rule.headerRuleId}</td>
				<td width="280">${currentMapping.rule.ruleDesc}</td>
			</tr>
			<tr>
			<td class="headText">
			EB03
			</td>
			<td>
			<c:set var="rowCount" value="0" />
			<c:set var="ebo3Length" value= "${fn:length(currentMapping.eb03.hippaCodeSelectedValues)}"/>
			<c:forEach begin="0" items="${currentMapping.eb03.hippaCodeSelectedValues}" var="eb03List">
			${eb03List.value}
			<c:if test="${rowCount != ebo3Length-1}">
			,
			</c:if>
			<c:set var="rowCount" value="${rowCount + 1}"/>
			</c:forEach>
			</td>
			</tr>
		</tbody>
	</table>
</c:if> -->

</form>
</body>
</html>