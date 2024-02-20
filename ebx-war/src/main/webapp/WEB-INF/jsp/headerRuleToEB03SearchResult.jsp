<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<div style="width:100%">

<table  class="mappedTable1 pd3 shadedText"  border="0"  width="100%" >
				
			<TBODY >
	<c:set var="rowCount" value="0" />
	
	<c:if test="${! empty headerRuleToEB03SearchResultList}" >
	
	<c:forEach items="${headerRuleToEB03SearchResultList}" var="eb03HeaderRuleAssn">
	
		<tr  class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<td width="5%" style="padding-left:3px;">${eb03HeaderRuleAssn.eb03Value}</td>
			<td width="89%" style="padding-left:3px; word-wrap:break-word;WORD-BREAK:BREAK-ALL;">${eb03HeaderRuleAssn.commaSeperatedHeaderRules}</td>
			<td width="6%" >
			<c:if test="${userUIPermissions.authorizedToEditHeaderRuleToEB03Association}">				
				 <c:choose>
				      <c:when test="${empty eb03HeaderRuleAssn.commaSeperatedHeaderRules}">
				      <a href="#" onclick='editActionConfirmationDialog("${eb03HeaderRuleAssn.eb03Value}","${eb03HeaderRuleAssn.eb03Description}",
				      				"${eb03HeaderRuleAssn.commaSeperatedHeaderRules}");'>
									<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create"/></a>
				      </c:when>
				      <c:otherwise>
						      <a href="#" onclick='editActionConfirmationDialog("${eb03HeaderRuleAssn.eb03Value}","${eb03HeaderRuleAssn.eb03Description}",
				      				"${eb03HeaderRuleAssn.commaSeperatedHeaderRules}");'>
									<img src="${imageDir}/create_icon.gif"  alt="Update" title="Update"/></a>
				
				     		 <a href="#" onclick='deleteAllActionConfirmationDialog("${eb03HeaderRuleAssn.eb03Value}");'>
									<img src="${imageDir}/markAsNotApp.gif"  alt="Delete All" title="Delete All"/></a>
				      </c:otherwise>
				    </c:choose>
			</c:if> 
			</td>
		</tr>
	<c:set var="rowCount" value="${rowCount + 1}"/>
	</c:forEach>
	</c:if>
	<c:if test="${empty headerRuleToEB03SearchResultList}" >
	<tr><td colspan = "5" align="center" >No Data Found </td></tr>
	</c:if>
	</TBODY>
						
		
		</table>
	</div>
	