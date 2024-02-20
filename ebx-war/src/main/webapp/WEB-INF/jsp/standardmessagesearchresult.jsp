<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<div style="width: 100%">
<input type="hidden" name="stdMsgSearch" id="stdMsgSearch" value="${StdMsgFetch}" />
<table class="mappedTable1 pd3 shadedText" border="0" width="100%">
	<TBODY>
		<c:set var="rowCount" value="0" />
		<c:if test="${! empty standardMsgList}">
			<c:forEach items="${standardMsgList}" var="standardMsgList">
			
				<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="90%" style="padding-left: 10px; padding-top: 7px">${standardMsgList.standardMessage}</td>
					
						<c:if test="${userUIPermissions.authorizedToEditStandardMessage}">
						<td width="10%">
							<a href="#" onclick='editStandardMessage("${standardMsgList.standardMessageId}");'>
										<img src="${imageDir}/create_icon.gif"  alt="Update" title="Update"/></a>
							
							<a href="#" onclick='deleteStandardMessageConfirmationDialog("${standardMsgList.standardMessageId}");'>
										<img src="${imageDir}/markAsNotApp.gif"  alt="Delete" title="Delete"/></a>
						</c:if> 
					</td>
				</tr>
				<c:set var="rowCount" value="${rowCount + 1}" />
			</c:forEach>
		</c:if>
		<c:if test="${empty standardMsgList}">
			<tr>
				<td colspan="5" align="center">No Data Found</td>
			</tr>
		</c:if>
	</TBODY>


</table>
</div>
