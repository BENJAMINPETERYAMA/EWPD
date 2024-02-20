<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>&nbsp;</div>	
<div class="overflowContainerHistory">
	<table border="0" cellpadding="0" cellspacing="0" width="560px" class="Pd2 auditTrailTable" >
		<tr class="createEditTableShade">
			<td width="30px" class="headText">EB03</td>
			<td width="80px" class="headText">Updated Date</td>
			<td width="80px" class="headText">User ID</td>
			<td width="160px" class="headText">System Comment</td>
			<td width="200px" class="headText">User Comment</td>
		</tr>

		<c:if test="${! empty headerRuleToEB03HistoryList}" >
			<c:set var="viewHistoryRowCount" value="0" />
			<c:forEach items="${headerRuleToEB03HistoryList}" var="headerRuleToEB03HistoryList">
				<tr class="${viewHistoryRowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="30px">${headerRuleToEB03HistoryList.eb03Value}</td>
					<td width="80px">${headerRuleToEB03HistoryList.updatedDateAndTime}</td>
					<td width="80px">${headerRuleToEB03HistoryList.lastUpdatedUser}</td>
					<td width="160px">${headerRuleToEB03HistoryList.systemComments}</td>
					<td width="200px">${headerRuleToEB03HistoryList.userComments}</td>
				</tr>
				<c:set var="viewHistoryRowCount" value="${viewHistoryRowCount + 1}"/>
			</c:forEach>
		</c:if>
		<c:if test="${empty headerRuleToEB03HistoryList}" >
			<tr> 
				<td colspan = "5" align="center" > No Data Values Found </td>
			</tr>
		</c:if>
	</table>
</div>


