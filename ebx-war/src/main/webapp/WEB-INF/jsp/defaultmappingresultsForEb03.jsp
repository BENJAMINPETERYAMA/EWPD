<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional-//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<body>
	<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
    <c:set var="persisttype" value="${persisttype}" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
		
$(document).ready(function()
{
  var persistType ="${persisttype}";
      if(persistType === 'save')
	  {  
	   $("#showSaveDialog").css("display", "block");
	   $("#showSaveDialog").delay(640000).fadeOut(300);
	   
	   // alert("Default Mapping Saved Successfully");
	    exclusionPage();
	  }
});

function downloadExcel()
{
	var eb03Val = document.getElementById("eb03Hidden").value;
	var eb03Default = document.getElementById("eb03ExcludeHidden").value;
	var url = "${context}/managespidereb03defaultmapping/defaultMappedEb03Excel.ajax?eb03Val="+eb03Val+"&eb03Defaultval="+eb03Default;
	console.log(url);
	location.href = url;
}

function cancel() 
{
	document.forms['editSpiderRule'].action = "../ebxspiderworkflow/cancel.html";
	document.forms["editSpiderRule"].submit();

}

function exclusionPage()
{
      document.forms['categoryeb03DefaultMappingForm'].action="${context}/managespidereb03defaultmapping/showManageEB03DefaultMappingPage.html";
	  document.forms['categoryeb03DefaultMappingForm'].submit();
}

</script>

<c:if test="${persisttype == 'duplicate'}">

			<p style="color: red;font-size: 18px;">
			Please update these UM mappings before setting the default value for the service type.
			</p>

		<br></br>
		
		<form name="editSpiderRule">
			<td>
				<input type="hidden" name="eb03Hidden" id="eb03Hidden" value="${ruleIdWithInfoList.eb03Value}" />
			</td>
			<td>
			    <input type="hidden" name="eb03ExcludeHidden" id="eb03ExcludeHidden" value="${eb03Exclude}" /> 
			        <span style="padding-left: 600px; position: right;"><img src="${imageDir}/export_but.gif" width="78" height="18" alt="Download Mapped Rules" title="Download Mapped Rules" onclick="downloadExcel();" /> 
				    </span>
		    </td>
		    
			<br></br>
			
			<table border="0" cellpadding="0" cellspacing="0"
				class="locateTable locateT shadedText" id="locateTableResults">
				<THEAD>
					<tr class="UnmappedVariables locateResultsTable">
						<th width="80px"  class="tableHeader">UM&nbsp;RULE&nbsp;ID</th>
						<th width="120px" class="tableHeader">RULE&nbsp;DESCRIPTION</th>
						<th width="250px" class="tableHeader">&nbsp;&nbsp;&nbsp;STATUS </th>
						<th width="250px" class="tableHeader">&nbsp;&nbsp;&nbsp;EB03 </th>
						<th width="250px" class="tableHeader">&nbsp;&nbsp;&nbsp;EB03&nbsp;DEFAULT</th>
					</tr>
				</THEAD>

				<TBODY>
					<c:set var="locateResultsCount" value="0" />
					<c:if test="${! empty locateResultsList}">
						<c:forEach items="${locateResultsList}" var="locateResultList"
							varStatus="locateResults">
							<tr class="${locateResultsCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
								<td width="100px">${locateResultList.umRuleId}
								  <input type="hidden" name="ruleIdHidden" id="ruleIdHidden${locateResults.index+1}" value="${locateResultList.umRuleId}" />
								</td>
								<td width="320px">${locateResultList.umRuleDescription}
								  <input type="hidden" name="ruleDescHidden" id="ruleDescHidden${locateResults.index+1}" value="${locateResultList.umRuleDescription}" />
								</td>
								<td width="100px">${locateResultList.status}</td>
								<td width="100px">${ruleIdWithInfoList.eb03Value}</td>
								<td width="100px">${ruleIdWithInfoList.eb03DefaultValue}</td>
							</tr>
							<c:set var="locateResultsCount"
								value="${locateResultsCount + 1}" />
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
		<div style="padding-left: 275px; padding-top: 10px;">
			<table>
				<tr>
					<td>
						<c:if test="${page.totalNoOfPages != 1}">
							<a href="#" onclick='createEb03Exclusion("${page.currentPage}","First");'>
							   <img src="${imageDir}/First Page.gif" />
							</a>
							<a href="#" onclick='createEb03Exclusion("${page.currentPage}","Previous");'>
							   <img src="${imageDir}/Previous.gif" />
							</a>
						</c:if>
					</td>

					<td valign="middle" style="padding-top: -70px; padding-right: 10px; padding-left: 10px; vertical-align: middle;">
						Page ${page.currentPage} of ${page.totalNoOfPages}
					</td>

					<td>
						<c:if test="${page.totalNoOfPages != 1}">
								<a href="#" onclick='createEb03Exclusion("${page.currentPage}","Next");'>
								    <img src="${imageDir}/Next.gif" />
								</a>
		 						<a href="#" onclick='createEb03Exclusion("${page.totalNoOfPages}","Last");'>
		 						    <img src="${imageDir}/Last Page.gif" />
		 						</a>
								<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}" />
						</c:if>
					</td>
				</tr>
			</table>

		</div>
	</c:if>
</c:if>	

<div id="showSaveDialog" style="display:none;">			
<br></br>
	<h3 style="color: green">EB03 Default Mapping saved successfully</h3>
</div>

</body>
</html>