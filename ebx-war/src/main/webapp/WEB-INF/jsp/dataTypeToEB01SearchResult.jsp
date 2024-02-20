<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<div style="width: 100%">

<table class="mappedTable1 pd3 shadedText" border="0" width="100%">
	<TBODY>
		<c:set var="rowCount" value="0" />
		<c:if test="${! empty dataTypeValueList}">
			<c:forEach items="${dataTypeValueList}" var="eb01DataTypeAssn">
			
				<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="5%" style="padding-left: 10px;">${eb01DataTypeAssn.eb01value}</td>
					<td width="29%" style="padding-left: 10px;">${eb01DataTypeAssn.dataTypeValue}</td>
					<td width="7%">
						<c:if test="${userUIPermissions.authorizedToEditDataTypeToEB01Association}">				
						 <c:choose>
						      <c:when test="${empty eb01DataTypeAssn.dataTypeValue}">
						      <a href="#" onclick='editAssociationConfirmationDialog("${eb01DataTypeAssn.eb01value}","${eb01DataTypeAssn.eb01Description}");'>
									<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create"/></a>
						      </c:when>
						      <c:otherwise>
								      <a href="#" onclick='editAssociationConfirmationDialog("${eb01DataTypeAssn.eb01value}","${eb01DataTypeAssn.eb01Description}");'>
									<img src="${imageDir}/create_icon.gif"  alt="Update" title="Update"/></a>
						
						     		 <a href="#" onclick='deleteAssociationConfirmationDialog("${eb01DataTypeAssn.eb01value}","${eb01DataTypeAssn.dataTypeValue}");'>
									<img src="${imageDir}/markAsNotApp.gif"  alt="Delete All" title="Delete All"/></a>
						      </c:otherwise>
						    </c:choose>
						</c:if> 
				
					</td>
				</tr>

				<c:set var="rowCount" value="${rowCount + 1}" />
			</c:forEach>
		</c:if>
		<c:if test="${empty dataTypeValueList}">
			<tr>
				<td colspan="5" align="center">No Data Found</td>
			</tr>
		</c:if>
	</TBODY>


</table>
</div>
