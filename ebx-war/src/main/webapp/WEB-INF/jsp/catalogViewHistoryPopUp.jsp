<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>&nbsp;</div>	
<div class="overflowContainerHistory">
	<table border="0" cellpadding="0" cellspacing="0" width="560px" class="Pd2 auditTrailTable" >
		<tr class="createEditTableShade">
			<td width="163px" class="headText">Value</td>
			<td width="100px" class="headText">Updated Date</td>
			<td width="250px" class="headText">Status</td>
			<td width="93px" class="headText">User ID</td>
			<td width="250px" class="headText">User Comment</td>
		</tr>

		<c:if test="${! empty deletedDataValueList}" >
			<c:set var="viewHistoryRowCount" value="0" />
			<c:forEach items="${deletedDataValueList}" var="deletedDataValueVO">
				<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="100px">${deletedDataValueVO.primaryCode}</td>
					<td width="170px">${deletedDataValueVO.dateAndTime}</td>
					<td width="90px">${deletedDataValueVO.auditStatus}</td>
					<td width="240px">${deletedDataValueVO.userID}</td>
					<td width="240px">${deletedDataValueVO.additionalComments}</td>
				</tr>
				<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
			</c:forEach>
		</c:if>
		<c:if test="${empty deletedDataValueList}" >
			<tr> 
				<td colspan = "5" align="center" > No Data Values Found </td>
			</tr>
		</c:if>
	</table>
</div>


