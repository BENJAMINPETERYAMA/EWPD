<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<table  id="dataValueResultTab"  class="dvmappedTable pd3 shadedText" border="0" cellpadding="0" cellspacing="0" width="100%">
		 					
	<TBODY>
		<c:if test="${! empty dataValueList}" >
			<c:forEach items="${dataValueList}" var="dataValueVO">
				<tr  class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" id="${dataValueVO.primaryCode}">
				<td width="5%" style="padding-left:14px;padding-bottom:3px;" >${dataValueVO.primaryCode}</td>
				<td width="15%" style="padding-left:7px;padding-bottom:3px;">${dataValueVO.secondaryCode}</td>
				<td width="70%" style="padding-left:5px;padding-bottom:3px;">${dataValueVO.description}</td>
				<c:if test="${userUIPermissions.authroizedToManageDataValues}">
				<td width="10%" nowrap style="padding-left:3px;padding-bottom:3px;">
					
						<a href="#" onclick='editDataValue("${dataValueVO.primaryCode}","${dataValueVO.secondaryCode}","${dataValueVO.description}");'>
							<img src="${imageDir}/edit_icon.gif"  alt="Create" title="Edit"/>
						</a>
						&nbsp;
						<a href="#" onclick='deleteDataValue("${dataValueVO.primaryCode}");'>
							<img src="${imageDir}/markAsNotApp.gif"  alt="Delete" title="Delete"/>
						</a>				
				</td>
				</c:if>
				
			</tr>
			<c:set var="rowCount" value="${rowCount + 1}"/>
			</c:forEach>
		</c:if>
		<c:if test="${empty dataValueList}" >
			<tr> 
				<td colspan = "3" align="center" > No Data Values Found </td>
			</tr>
		</c:if>
		
	</TBODY>
</table>

