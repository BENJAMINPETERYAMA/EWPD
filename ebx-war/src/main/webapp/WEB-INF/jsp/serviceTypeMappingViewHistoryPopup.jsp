<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>&nbsp;</div>	
<div class="overflowContainerHistory">
	<table border="0" cellpadding="0" cellspacing="0" width="560px" class="Pd2 auditTrailTable" >
		<tr class="createEditTableShade">
			<td width="30x" class="headText">LOB/State</td>
			<td width="80px" class="headText">Updated Date</td>
			<td width="80px" class="headText">User ID</td>
			<td width="160px" class="headText">System Comment</td>
			<td width="200px" class="headText">User Comment</td>
		</tr>

		<c:if test="${! empty serviceTypeCodeMappingAudtTrlList}" >
			<c:set var="viewHistoryRowCount" value="0" />
			<c:forEach items="${serviceTypeCodeMappingAudtTrlList}" var="serviceTypeCodeMappingAudtTrlList">
				<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="30px">${serviceTypeCodeMappingAudtTrlList.lineOfBusiness}</td>
					<td width="80px">${serviceTypeCodeMappingAudtTrlList.lastUpdatedDate}</td>
					<td width="80px">${serviceTypeCodeMappingAudtTrlList.lastUpdatedUser}</td>
					<td width="160px">${serviceTypeCodeMappingAudtTrlList.systemComments}</td>
					<td width="200px">${serviceTypeCodeMappingAudtTrlList.userComments}</td>
				</tr>
				<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
			</c:forEach>
		</c:if>
		<c:if test="${empty serviceTypeCodeMappingAudtTrlList}" >
			<tr> 
				<td colspan = "5" align="center" > No Data Values Found </td>
			</tr>
		</c:if>
	</table>
</div>


