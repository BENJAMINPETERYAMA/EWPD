<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
function deleteOperation()	{
	
		document.forms['referenceForm'].action="${context}/referencedata/deleteCategoryCodeMapping.html";
		document.forms['referenceForm'].submit();
	
}
function cancelOperation()	{
	
		self.close();

}
</script>




<form name="referenceForm" method="POST">
<input type="hidden" name="categorydelete" id="categorydelete" value="${categorydelete}"/>
			<input type="hidden" name="systemdelete" id="systemdelete" value="${systemdelete}"/>
			<input type="hidden" name="categoryCode" id="categoryCode" value="${categoryCodefetch}"/>
			<input type="hidden" name="system" id="system" value="${systemfetch}"/>
			<input type="hidden" name="EB03" id="EB03" value="${EB03fetch}"/>
			<input type="hidden" name="categorydesc" id="categorydesc" value="${categoryDescfetch}"/>
<div id="deleteCategoryPopupContent">

<table border="0" cellspacing="0" width="100%" class="mappedTable Pd3 shadedText">
	<tr height="20">
	<th id="systemId" width="20%" class="tableHeader">
			Variable ID 
				
			</th> 
			<th id="variableWpdId" width="80%" class="tableHeader">
			Description
				
			</th>
		
	</tr>
	
	<tr height="20">
		<td colspan="2"></td>
	</tr>
			<TBODY>
	<c:set var="rowCount" value="0" />
	
	<c:if test="${! empty categoryCodeMappingList}" >
	
	<c:forEach items="${categoryCodeMappingList}" var="unmappedVar">
	
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<input type="hidden" id="unmappedVariableId" value=""/>
			<input type="hidden" name="variableIdHidden" id="variableIdHidden" value="" />
			<td style="word-wrap:break-word">${unmappedVar.variable}</td>
			<td>${unmappedVar.variableDesc}</td>
				
		
		</tr>
		
	<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
	</c:if>
	<c:if test="${empty categoryCodeMappingList}" >
	<tr><td colspan = "2" align="center" >No Data Found </td></tr>
	</c:if>
	</TBODY>



</table>
</div>
<table width ="100%">
	<tr height="20">
		<td align="center"><IMG src="${imageDir}/delete_but.gif" width="70px"
			onclick="deleteOperation();"></td>
			
	</tr>
</table>


</form>
</body>
</html>
