<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>&nbsp;</div>
	
		<table border="0" cellpadding="0" cellspacing="0" width="520px" class="Pd2 auditTrailTable" >
			<tr class="createEditTableShade">
				<td width="90px" class="headText">Date</td>
				<td width="93px" class="headText">User ID</td>
				<td width="163px" class="headText">System Comment</td>
				<td width="250px" class="headText">User Comment</td>
			</tr>
			<c:if test="${empty auditTrailList}">
				No audit trail found
			</c:if>
			<c:if test="${! empty auditTrailList}">
			<c:set var="viewHistoryRowCount" value="0" />
			<c:forEach items="${auditTrailList}" var="viewAudit">
				<tr  class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="80px"><c:out value="${viewAudit.createdAuditDate}"/></td>
					<td width="90px">${viewAudit.createdUser}</td>
					<td width="180px">
						<c:if test="${viewAudit.systemComments != 'MODIFIED'}">
						<c:choose>
							<c:when test="${viewAudit.mappingStatus == 'PUBLISHED'}">
												${viewAudit.mappingStatus}
												<a href="#" style="color: blue;"
							onclick="retreiveAuditTrailInDetail();">View&nbsp;Details</a>
							</c:when>
							<c:otherwise>${viewAudit.systemComments}</c:otherwise>
						</c:choose>
						</c:if>
						<c:if test="${viewAudit.systemComments == 'MODIFIED'}">
							${viewAudit.systemComments}
						</c:if>
					</td>
					<td width="250px">${viewAudit.userComments}</td>
				</tr>
			<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
			</c:forEach>
		</table>
	</c:if>	
	


