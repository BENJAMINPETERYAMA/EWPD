<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional-//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>

<div class="LocateResultTableDiv">

  <div id="variableInfoDiv" class="overflowContainerVariable">
				<table border="0" cellspacing="0" cellpadding="0" class="Pd3">
                  <THEAD>
						<tr class="createEditTable1">
		                    <td width="250px" class="tableHeader">UM Rule ID</td>
		                    <td width="500px" class="tableHeader">Description</td>
		                   
	                  	</tr>
				</THEAD>
				<TBODY>
					<c:set var="rowCount" value="0" />					
					<tr>
					    <td width="250px" >${ruleIdWithInfoList.umRuleId}</td>
						<td width="500px">${ruleIdWithInfoList.umRuleDescription}</td>
						
					</tr>
					
				</TBODY>             
                </table>
              
			</div>

<br></br>
<br></br>
<br></br>


<div class="LocateResultTableDiv">
<form name="viewHistorySpider">

<table border="0" cellpadding="0" cellspacing="0" class="locateTable locateT shadedText" id="locateTableResults">
	<THEAD>
	<tr class="UnmappedVariables locateResultsTable">
		
		<th width="100px" class="tableHeader">&nbsp;Created&nbsp;By</th>
		<th width="100px" class="tableHeader">&nbsp;Created&nbsp;Date</th>
		<th width="80px" class="tableHeader">&nbsp;Status</th>		
		<th width="100px" class="tableHeader">&nbsp;Action</th>
		<th width="300px" class="tableHeader">&nbsp;Comment</th>
	
	</tr>
	</THEAD>	
	<TBODY>

	<c:set var="locateResultsCount" value="0" />
	<c:if test="${! empty locateResultsList}">
		<c:forEach items="${locateResultsList}" var="locateResultList">
			<tr class="${locateResultsCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							
				<td width="100px">${locateResultList.createdBy}</td>
				<td width="100px"><fmt:formatDate pattern="MM/dd/yyyy" value="${locateResultList.createdDate}" /></td>				
				<td width="100px">${locateResultList.status}</td>				
                <td width="100px">${locateResultList.systemStatus}</td>
		        <td width="160px">${locateResultList.comments}</td>

			</tr>
			<c:set var="locateResultsCount" value="${locateResultsCount + 1}" />
		</c:forEach>
	</c:if>
	<c:if test="${empty locateResultsList}">
		<tr>
			<td colspan="5" align="center">No Results matching your search
			criteria</td>
		</tr>
	</c:if>
</TBODY>
</table>
</form>
</div>
</div>

</body>
</html>