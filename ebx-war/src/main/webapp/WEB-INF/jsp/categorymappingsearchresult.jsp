<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<div style="width:100%">

<table  class="mappedTable1 pd3 shadedText"  border="0"  width="100%" >
				
			<TBODY >
	<c:set var="rowCount" value="0" />
	
	<c:if test="${! empty categoryCodeMappingList}" >
	
	<c:forEach items="${categoryCodeMappingList}" var="unmappedVar">
	
		<tr  class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<td width="10%" style="padding-left:3px;">${unmappedVar.categoryCode}</td>
			<td width="29%" style="padding-left:3px;">${unmappedVar.categoryDesc}</td>
			<td width="47%"  style="padding-left:3px;">${unmappedVar.serviceType}</td>
				<td width="7%"   style="padding-left:3px;"   >${unmappedVar.system}</td>
			<td width="7%" >
				
					 <c:choose>
				      <c:when test="${empty unmappedVar.serviceType}">
				      <a href="#" onclick='createMapping("${unmappedVar.categoryCode}","${unmappedVar.system}","${unmappedVar.serviceType}","${unmappedVar.categoryDesc}");'>
									<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create"/>
								</a>
				      </c:when>
				
				      <c:otherwise>
				      <a href="#" onclick='editMapping("${unmappedVar.categoryCode}","${unmappedVar.system}","${unmappedVar.serviceType}","${unmappedVar.categoryDesc}");'>
									<img src="${imageDir}/create_icon.gif"  alt="Update" title="Update"/>
								</a>
				    
					<a href="#" onclick='deleteMapping("${unmappedVar.categoryCode}","${unmappedVar.system}");'>
					<img src="${imageDir}/markAsNotApp.gif"  alt="Delete" title="Delete"/>
					</a>
				      
				      </c:otherwise>
				      
				     
				    </c:choose>
				
			</td>
		</tr>
		
	<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
	</c:if>
	<c:if test="${empty categoryCodeMappingList}" >
	<tr><td colspan = "5" align="center" >No Data Found </td></tr>
	</c:if>
	</TBODY>
						
		
		</table>
	</div>
	