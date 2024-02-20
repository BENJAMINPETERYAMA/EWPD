<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<div style="width: 100%">

<table class="mappedTable1 pd3 shadedText" border="0" width="100%">
	<TBODY>
		<c:set var="rowCount" value="0" />
		<c:if test="${! empty fetchResultList}">
			<c:forEach items="${fetchResultList}" var="fetchResultList">
			
				<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="5%" style="padding-left: 10px;">${fetchResultList.lineOfBusiness}</td>
					<td width="29%" style="padding-left: 10px;">${fetchResultList.commaSeperatedMbu}</td>
					<td width="7%">
						<a href="#" onclick='viewServiceTypeMappings("${fetchResultList.lobId}");'>
							<img src="${imageDir}/search_icon.gif" alt="View" id="View" title="View"/>
						</a>
							
						<a href="#" onclick='editServiceTypeMappings("${fetchResultList.lobId}");'>
							<img src="${imageDir}/create_icon.gif" alt="Update" id="Update" title="Update"/>
						</a>
						<a href="#" onclick='deleteAssociationConfirmationDialog("${fetchResultList.lobId}","${fetchResultList.lineOfBusiness}");'>
							<img src="${imageDir}/markAsNotApp.gif" alt="Delete" id="Delete" title="Delete"/>
						</a>
						
					</td>
				</tr>
				<c:set var="rowCount" value="${rowCount + 1}" />
			</c:forEach>
		</c:if>
		<c:if test="${empty fetchResultList}">
			<tr>
				<td colspan="5" align="center">No Data Found</td>
			</tr>
		</c:if>
	</TBODY>
</table>
</div>
