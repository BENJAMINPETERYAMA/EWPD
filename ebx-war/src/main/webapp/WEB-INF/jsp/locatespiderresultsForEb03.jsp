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
function openLocateResultsDialog(currentPageNo, page,ruleId,ruleDesc) 
{
	var umRuleId = $("#"+ruleId).val();
	var eb03 = $("#eb03FrmLocate").val();
	var desc = $("#"+ruleDesc).val();
	var ruledesc = document.getElementById('ruleDesc').value;
	var currentPage = currentPageNo;
	var fromWhichSection = page;

	$.ajax({
			url : "${context}/locatespiderresults/locateRequest.ajax",
			dataType : "html",
			type : "POST",
			data : "umRuleId="
					+ encodeURIComponent(umRuleId)
					+ "&umRuleDescription=" + encodeURIComponent(desc)
					+ "&eb03=" + escape(eb03)
					+ "&currentPage=" + currentPage
					+ "&section=" + fromWhichSection,
			success : function(data) {
				$("#viewLoacteDialog").html(data);
				$("#viewLoacteDialog")
						.dialog(
								{
									height : 'auto',
									width : '750px',
									show : 'slide',
									modal : true,
									resizable : false,
									title : 'Locate Results',
									open : function() {
										
									},
									close : function() {
									
									}
								});
				$("#viewLoacteDialog").dialog();
			}
   });
}

function sendToTest(ruleId,userComments)
{		
     $.ajax({
			url : "${context}/ebxspiderworkflow/sendToTest.ajax",
			dataType: 'html',
	        type: "POST",
			data : "umRuleId=" + ruleId
					+ "&userComments=" + userComments,
			success : function(data) {
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
			success : function(data) {
				alert("mapping approved successfully");
	            cancel();
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
  
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
    <THEAD>
		<tr class="createEditTable1">
            <td width="150" class="tableHeader">EB03</td>
            <td width="200" class="tableHeader">EB03 DEFAULT</td>
		    <td width="170" class="tableHeader">MESSAGE</td>
        </tr>
    </THEAD>
	<TBODY>
		<c:set var="rowCount" value="0" />					
		<tr>
		    <td>${ruleIdWithInfoList.eb03Value}</td>
			<td>${ruleIdWithInfoList.eb03DefaultValue}</td>
			<td>${ruleIdWithInfoList.msgValue}</td>			
		</tr>		
	</TBODY>             
</table>
           
<br></br>

<form name="editSpiderRule" style="min-height: 300px">

<td><input type="hidden" name="eb03Hidden" id="eb03Hidden" value="${ruleIdWithInfoList.eb03Value}" /></td>

<table border="0" cellpadding="0" cellspacing="0" class="locateTable locateT shadedText" id="locateTableResults">
	<THEAD>
	<tr class="UnmappedVariables locateResultsTable">

		<th id="umEB03" onclick="displaySortMark(this.id)" width="80px" class="tableHeader">
		  UM_RULE_ID
		<span id="eb03Icon" class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<th id="umIII02" onclick="displaySortMark(this.id)" width="120px" class="tableHeader">
		  RULE_DESCRIPTION
		<span id="iii02Icon" class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<th id="umMsg" onclick="displaySortMark(this.id)" width="250px" class="tableHeader">
		&nbsp;&nbsp;&nbsp;STATUS
		<span id="msgIcon" class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<th id="dateId" onclick="displaySortMark(this.id)" width="110px" class="tableHeader">
		  ACTION
		<span id="dateIcon" class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		
	</tr>
	</THEAD>	

	<TBODY>

	<c:set var="locateResultsCount" value="0" />
	<c:if test="${! empty locateResultsList}">
		<c:forEach items="${locateResultsList}" var="locateResultList" varStatus="locateResults">
			<tr class="${locateResultsCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
				<td width="100px">${locateResultList.umRuleId}
				    <input type="hidden" name="ruleIdHidden" id="ruleIdHidden${locateResults.index+1}" value="${locateResultList.umRuleId}" />
				</td>
				<td width="320px">${locateResultList.umRuleDescription}
				    <input type="hidden" name="ruleDescHidden" id="ruleDescHidden${locateResults.index+1}" value="${locateResultList.umRuleDescription}" />
				</td>
			    <td width="100px">${locateResultList.status}</td>
                <td>						
					<a href="#" id="locate_EditIcon_${locateSResultList.umRuleId}" onclick='openLocateResultsDialog("0","fromLanding","ruleIdHidden${locateResults.index+1}","ruleDescHidden${locateResults.index+1}");'>
						<img src="${imageDir}/edit_icon.gif"  alt="Locate" title="Locate" />
					</a>
			    </td>
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
					<a href="#" onclick='openLocateResultsDialogLanding("${page.currentPage}","First");'>
						<img src="${imageDir}/First Page.gif"/>
					</a>
					<a href="#" onclick='openLocateResultsDialogLanding("${page.currentPage}","Previous");'>
					    <img src="${imageDir}/Previous.gif" />
					</a>
				</c:if>
			</td>

			<td valign="middle" style="padding-top:-70px;padding-right:10px;padding-left:10px;vertical-align:middle;">
			     Page ${page.currentPage} of ${page.totalNoOfPages}
			</td>

			<td>
				<c:if test="${page.totalNoOfPages != 1}">
					<a href="#" onclick='openLocateResultsDialogLanding("${page.currentPage}","Next");'>
					   <img src="${imageDir}/Next.gif" />
					</a>
					<a href="#" onclick='openLocateResultsDialogLanding("${page.totalNoOfPages}","Last");'>
					   <img src="${imageDir}/Last Page.gif" />
					</a>
					<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}" />
				</c:if>
			</td>			
		</tr>
	</table>
	
	</div>
</c:if>
</body>
</html>