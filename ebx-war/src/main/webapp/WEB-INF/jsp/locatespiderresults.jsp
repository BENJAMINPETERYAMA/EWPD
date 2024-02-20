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

<script type="text/javascript">

function sendToTest(ruleId,userComments)
{ 		
    $.ajax({
		url : "${context}/ebxspiderworkflow/sendToTest.ajax",
		dataType: 'html',
        type: "POST",
		data : "umRuleId=" + ruleId
				+ "&userComments=" + userComments,
		success : function(data) 
		{
			alert("mapping sent to test successfully");
            location.reload();
		}
    });
}

function approve(ruleId,userComments)
{					
    $.ajax({
		url : "${context}/ebxspiderworkflow/saveAndApprove.ajax",
		dataType: 'html',
        type: "POST",
		data : "umRuleId=" + ruleId
				+ "&userComments=" + userComments,
		success : function(data) 
		{
			alert("mapping approved successfully");
            location.reload();
		}
    });		
}

function cancel()
{	
	document.forms['editSpiderRule'].action="../ebxspiderworkflow/cancel.html";
    document.forms["editSpiderRule"].submit();	
} 
  
function openEditSpider(ruleId)
{
	$("#ruleIdHidden").val(ruleId);
	document.forms['editSpiderRule'].action = "${context}/locatespiderresults/viewFromEditSpider.html";
	document.forms["editSpiderRule"].submit();
} 

function openViewHistory(umRuleId,umRuleDesc)
{
	$.ajax({
		url : "${context}/locatespiderresults/viewHistory.ajax",
		dataType : "html",
		type : "POST",
		data : "umRuleId="
				+ encodeURIComponent(umRuleId)+"&umRuleDesc="
				+ encodeURIComponent(umRuleDesc),
				
		success : function(data)
              {
			$("#viewHistoryDialog").html(data);
			$("#viewHistoryDialog")
					.dialog(
							{
								height : 'auto',
								width : '750px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'View History',
								open : function() {
									
								},
								close : function() {
								$('.printIt').remove();	
								}
							});
			
		}
	});
			
}

function markAsApplicable(ruleId)
{
    $.ajax({
		url : "${context}/ebxspiderworkflow/markAsApplicable.ajax",
		dataType: 'html',
        type: "POST",
		data : "&umRuleId=" + encodeURIComponent(ruleId),
		success : function(data) 
		{
			alert("mapping changed to Applicable");
            location.reload();
		}
    });
}

</script>

<table border="0" cellspacing="0" cellpadding="0" class="Pd3">
     <THEAD>
		<tr class="createEditTable1">
                  <td width="150px" class="tableHeader">UM Rule ID</td>
                  <td width="280px" class="tableHeader">Description</td>
                  <td width="150px" class="tableHeader">Status</td>	
		    <td  width="150px" class="tableHeader">Actions</td>
		</tr>
     </THEAD>
     <TBODY>
	    <c:set var="rowCount" value="0" />					
			<tr>
			    <td>${ruleIdWithInfoList.umRuleId}</td>
				<td>${ruleIdWithInfoList.umRuleDesc}</td>
				<td>${ruleIdWithInfoList.umStatus}</td>
				<td>
					<a href="#" id="locate_searchIcon_${locateSResultList.umRuleId}" onclick='openViewHistory("${ruleIdWithInfoList.umRuleId}","${ruleIdWithInfoList.umRuleDesc}");'>
						<img src="${imageDir}/search_icon.gif"  alt="View History" title="View History" />
					</a>&#160;&#160;
					<c:if test="${ruleIdWithInfoList.umStatus != 'NOT_APPLICABLE' &&
					              ruleIdWithInfoList.umStatus != 'MARK_NOT_APPLICABLE'}">
						<a href="#" id="locate_EditIcon_${locateSResultList.umRuleId}" onclick='openEditSpider("${locateResultList.umRuleId}");'>
							<img src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" />
						</a>&#160;&#160;
					</c:if>
					<c:if test="${ruleIdWithInfoList.umStatus == 'MARK_NOT_APPLICABLE'}">
						<a href="#" id="locate_ApplicableIcon_${locateSResultList.umRuleId}" onclick='markAsApplicable("${ruleIdWithInfoList.umRuleId}");'>
							<img src="${imageDir}/Applicable.gif"  alt="Mark as Applicable" title="Mark as Applicable" />
						</a>&#160;&#160;
					</c:if>
					<c:if test="${ruleIdWithInfoList.umStatus != 'SCHEDULED_TO_PRODUCTION' &&
								  ruleIdWithInfoList.umStatus != 'SCHEDULED_TO_TEST' &&
								  ruleIdWithInfoList.umStatus != 'PUBLISHED' &&
								  ruleIdWithInfoList.umStatus != 'NOT_APPLICABLE' &&
						          ruleIdWithInfoList.umStatus != 'MARK_NOT_APPLICABLE'}">
						<a href="#" id="locate_SendToTestIcon_${ruleIdWithInfoList.umRuleId}" onclick='sendToTest("${ruleIdWithInfoList.umRuleId}","Send2TestFromLocate");')>
							<img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test"/>
						</a>&#160;&#160;
			        </c:if> 
			        <c:if test="${ruleIdWithInfoList.umStatus != 'SCHEDULED_TO_PRODUCTION' &&
						          ruleIdWithInfoList.umStatus != 'PUBLISHED' &&
						          ruleIdWithInfoList.umStatus != 'NOT_APPLICABLE' &&
						          ruleIdWithInfoList.umStatus != 'MARK_NOT_APPLICABLE'}">
						<a href="#" id="locate_approveIcon_${ruleIdWithInfoList.umRuleId}" onclick='approve("${ruleIdWithInfoList.umRuleId}","ApproveFromLocate");')>
							<img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/>
						</a>			
				    </c:if> 
		        </td>				
			</tr>	
      </TBODY>             
</table>
              
<br></br>
<br></br>
<br></br>

<form name="editSpiderRule"style="min-height: 300px">

<td><input type="hidden" name="ruleIdHidden" id="ruleIdHidden" value="${ruleIdWithInfoList.umRuleId}" /> 
    <input type="hidden" name="ruleDescHidden" id="ruleDescHidden" value="${ruleIdWithInfoList.umRuleDesc}" /> 
    <input type="hidden" name="ruleStatusHidden" id="ruleStatusHidden" value="${ruleIdWithInfoList.umStatus}" /> 
</td>

<table border="0" cellpadding="0" cellspacing="0" class="locateTable locateT shadedText" id="locateTableResults">
	<THEAD>
		<tr class="UnmappedVariables locateResultsTable">			
			<th width="80px" class="tableHeader">EB03</th>
			<th  width="120px" class="tableHeader">EB03&nbsp;Default</th>
			<th width="250px" class="tableHeader">&nbsp;&nbsp;&nbsp;Message</th>
			<th width="110px" class="tableHeader">Date&nbsp;Created</th>			
		</tr>
	</THEAD>	

	<TBODY>
		<c:set var="locateResultsCount" value="0" />
		<c:if test="${! empty locateResultsList}">
			<c:forEach items="${locateResultsList}" var="locateResultList">
				<tr class="${locateResultsCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					<td width="100px">${locateResultList.eb03Value}</td>
					<td width="100px">${locateResultList.eb03DefaultValue}</td>
					<td width="350px">${locateResultList.msgValue}</td>
					<td width="150px"><fmt:formatDate pattern="MM/dd/yyyy" value="${locateResultList.createdDate}" /></td>
				</tr>
				<c:set var="locateResultsCount" value="${locateResultsCount + 1}" />
			</c:forEach>
		</c:if>
		<c:if test="${empty locateResultsList}">
			<tr>
				<td colspan="5" align="center">No Results matching your search criteria</td>
			</tr>
		</c:if>
    </TBODY>
</table>
</form>

<c:if test="${! empty locateResultsList}">
	<div style="padding-left:275px;padding-top:10px;">
		<table>
			<tr>
				<td>
					<c:if test="${page.totalNoOfPages != 1}">
						<a href="#"onclick='openLocateResultsDialogLanding("${page.currentPage}","First");'><img
							src="${imageDir}/First Page.gif"/>
					    </a>
						<a href="#"
							onclick='openLocateResultsDialogLanding("${page.currentPage}","Previous");'><img
							src="${imageDir}/Previous.gif" />
							</a>
					</c:if>
				</td>
	
				<td valign="middle" style="padding-top:-70px;padding-right:10px;padding-left:10px;vertical-align:middle;">
	                Page ${page.currentPage} of ${page.totalNoOfPages}
	            </td>
	
				<td><c:if test="${page.totalNoOfPages != 1}">
					<a href="#"
						onclick='openLocateResultsDialogLanding("${page.currentPage}","Next");'><img
						src="${imageDir}/Next.gif" /></a>
					<a href="#"
						onclick='openLocateResultsDialogLanding("${page.totalNoOfPages}","Last");'><img
						src="${imageDir}/Last Page.gif" /></a>
					<input type="hidden" name="currentPage" id="currentPage"
						value="${page.currentPage}" />
				</c:if></td>			
			</tr>
		</table>
	</div>
</c:if>
<div id="viewHistoryDialog" title="View History"></div>
</body>
</html>