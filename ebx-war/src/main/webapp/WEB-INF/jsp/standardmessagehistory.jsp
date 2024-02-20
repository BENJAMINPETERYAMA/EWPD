<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>&nbsp;</div>	
<div class="overflowContainerHistory">
	<table border="0" cellpadding="0" cellspacing="0" width="560px" class="Pd2 auditTrailTable" >
		<tr class="createEditTableShade">
			<!--<td width="30x" class="headText">EB01</td>
			--><td width="80px" class="headText">User ID</td>
			<td width="80px" class="headText">Updated Date</td>
			<td width="195px" class="headText">System Comment</td>
			<td width="195px" class="headText">User Comment</td>
		</tr>
		<c:if test="${! empty stdMsgHistoryList}" >
			<c:set var="viewHistoryRowCount" value="0" />
			<c:forEach items="${stdMsgHistoryList}" var="stdMsgHistoryList">
		<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="80px">${stdMsgHistoryList.lastUpdatedUser}</td>
					<td width="80px">${stdMsgHistoryList.updatedDateAndTime}</td>
					<td width="195px">${stdMsgHistoryList.systemComments}</td>
					<td width="195px">${stdMsgHistoryList.userComments}</td>
				</tr>
				<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
			</c:forEach>
		</c:if>

		<c:if test="${empty stdMsgHistoryList}" >
			<tr> 
				<td colspan = "5" align="center" > No Data Values Found </td>
			</tr>
		</c:if>
	</table>
</div>


