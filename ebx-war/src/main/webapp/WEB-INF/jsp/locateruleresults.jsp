<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

function displaySortMark(id)
   {
  
    switch(id)
	   {
	     case 'ruleresId' :
	     {
		     document.getElementById('ruleresIcon').style.display= 'block';
		     document.getElementById('descruleresIcon').style.display= 'none';
		     document.getElementById('dateruleresIcon').style.display= 'none';  
		     document.getElementById('statusruleresIcon').style.display= 'none';   
		     break;
	     }
	      case 'descruleresId' :
	     {
		     document.getElementById('ruleresIcon').style.display= 'none';
		     document.getElementById('descruleresIcon').style.display= 'block';
		     document.getElementById('dateruleresIcon').style.display= 'none';  
		     document.getElementById('statusruleresIcon').style.display= 'none';   
		     break;
	     }
	      case 'dateruleresId' :
	     {
		     document.getElementById('ruleresIcon').style.display= 'none';
		     document.getElementById('descruleresIcon').style.display= 'none';
		     document.getElementById('dateruleresIcon').style.display= 'block';  
		     document.getElementById('statusruleresIcon').style.display= 'none';   
		     break;
	     }
	      case 'statusruleresId' :
	     {
		     document.getElementById('ruleresIcon').style.display= 'none';
		     document.getElementById('descruleresIcon').style.display= 'none';
		     document.getElementById('dateruleresIcon').style.display= 'none';  
		     document.getElementById('statusruleresIcon').style.display= 'block';   
	   
	     }
	   }
}	     
	     
$(document).ready(function() {  
	    	 document.getElementById('ruleresIcon').style.display= 'none';
		     document.getElementById('descruleresIcon').style.display= 'none';
		     document.getElementById('dateruleresIcon').style.display= 'none';  
		     document.getElementById('statusruleresIcon').style.display= 'none'; 
		     
		     var strLocateRule=$("#locateRule tbody tr td").html();
		     
		     if($.trim(strLocateRule) != 'No Results matching your search criteria')
             {  
	   			 $('#locateRule').dataTable(
					{ 			   
							"bPaginate": false ,				    
							"bFilter": false, 
						 	"bSearchable":false, 
							"bInfo":false,
							"bSort": true,
							"bStateSave": false,
							"bJQueryUI": false,
							"bProcessing": false,
							"aaSorting": [],
							"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
									             
																	if (iDisplayIndex % 2  == 0 )
																	{  
																	    $(nRow).removeClass( 'alternate' );
																		$(nRow).addClass( 'white-bg' );
																	}
																	else
																	{
																	    $(nRow).removeClass( 'white-bg' );
																	    $(nRow).addClass( 'alternate' );
																	}
																	return nRow;
																},
							"bAutoWidth": false
	 				} ); 
	 		}						  
}); 
</script> 

<div class="LocateResultTableDiv">
<input type="hidden" name="authorizedToapprove" id="authorizedToapprove" value="${userUIPermissions.authorizedToapprove}"/>
<table style="width: 708px;" border="0" cellpadding="0" cellspacing="0" id="locateRule" class="locateTable locateT shadedText">
	<THEAD>
	<tr class="UnmappedVariables locateResultsTable">
		<th id="ruleresId" onclick="displaySortMark(this.id)" width="130px" class="tableHeader">
		Rule ID
		<span id="ruleresIcon"  class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<th id="descruleresId" onclick="displaySortMark(this.id)" width="250px" class="tableHeader">
		Description
		<span id="descruleresIcon"  class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<th id="dateruleresId" onclick="displaySortMark(this.id)" width="80px" class="tableHeader">
		Date&nbsp;Created
		<span id="dateruleresIcon"  class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<th id="statusruleresId" onclick="displaySortMark(this.id)" width="130px" class="tableHeader">
		Status
		<span id="statusruleresIcon"  class="ui-widget-header" style="padding-left:5px;display:inline-block;background:none; border:0px; position:relative; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
		</th>
		<td width="90px" class="tableHeader">&nbsp;</td> 
	</tr>
	</THEAD>	

	<TBODY>

	<c:set var="locateResultsCount" value="0" />
	<c:if test="${! empty locateResultsList}">
		<c:forEach items="${locateResultsList}" var="locateResultList">
			<tr
				class="${locateResultsCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
				
				<td width="130px">${locateResultList.rule.headerRuleId} </td>
				<td width="250px">${locateResultList.rule.ruleDesc}</td>
				<td width="80px"><fmt:formatDate pattern="MM/dd/yyyy"
					value="${locateResultList.lastChangedTmStamp}" /></td>
				<td width="130px" id="status_${locateResultList.rule.headerRuleId}">${locateResultList.variableMappingStatus}</td> 
				
				<td width="115px">
				<c:set var="unmapped" value="0" /> 
				<c:set
					var="published" value="0" /> 
				<c:set var="approve" value="0" /> 
				<c:set var="lock" value="0" /> 
				<c:if test = "${not empty locateResultList.lock.lockUserId && locateResultList.lock.lockUserId != userId}">
					<c:set var="lock" value="1" />
				</c:if>
				<c:if
					test="${locateResultList.variableMappingStatus == 'UNMAPPED'}">
					<c:set var="unmapped" value="1" />
				</c:if> 
				<c:set var="notapp" value="0" /> 
				<c:if
					test="${locateResultList.variableMappingStatus == 'NOT_APPLICABLE'}">
					<c:set var="notapp" value="1" />
				</c:if>
					<c:if test="${locateResultList.variableMappingStatus == 'APPROVE'}">
						<c:set var="approve" value="1" />
					</c:if>
					<c:if test="${unmapped == 1}">
						<a href="#"
							onclick='openViewMappingDialogForRule("${locateResultList.rule.headerRuleId}");'><img
							src="${imageDir}/search_icon.gif" 
							alt="View" title="View"/></a>
					</c:if>
					<c:if test="${unmapped != 1}">
						<a href="#"
							onclick='openViewMappingInProgressDialogForRule("${locateResultList.rule.headerRuleId}");'><img
							src="${imageDir}/search_icon.gif" 
							alt="View"  title="View"/></a>
					</c:if>

					<c:if test="${unmapped != 1 && notapp != 1 && lock != 1}">
						<a href="#" id="locate_EditIcon_${locateResultList.rule.headerRuleId}"
							onclick='editMappingForRule("${locateResultList.rule.headerRuleId}");'><img
							src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" /></a>
					</c:if>
					<c:if test="${unmapped != 1 && notapp != 1 && lock == 1}">
						<input type="hidden" name="lastUpdatedUserId" id="lastUpdatedUserId" value="${locateResultList.user.lastUpdatedUserName}" />
						<c:if test="${userUIPermissions.authorizedToUnlock}">
							<a href="#" id="locate_UnlockIcon_${locateResultList.rule.headerRuleId}"
								onclick='unlockMappingFromLocate("${locateResultList.rule.headerRuleId}","","unlockRuleFromLocate", "${locateResultList.lock.lockUserId}");'><img
								src="${imageDir}/lock_icon.jpg"  alt="${locateResultList.lock.lockUserId}" title="${locateResultList.lock.lockUserId}" /></a>
						</c:if>
						<c:if test="${!userUIPermissions.authorizedToUnlock}">
							<img src="${imageDir}/lock_icon.jpg"  alt="${locateResultList.lock.lockUserId}" title="${locateResultList.lock.lockUserId}" /></a>
						</c:if>
					</c:if>
					<c:if test="${notapp == 1}">
						<a href="#"
							onclick='editMappingForRule("${locateResultList.rule.headerRuleId}");'><img
							src="${imageDir}/Applicable.gif" 
							alt="Mark as Applicable"  title="Mark as Applicable"/></a>
					</c:if>
					<c:if test="${unmapped == 1}">
						<a href="#"
							onclick='openCreateFromUnmappedSectionForRule("${locateResultList.rule.headerRuleId}");'><img
							src="${imageDir}/create_icon.gif" 
							alt="Create"  title="Create"/></a>
						<c:if
							test="${userUIPermissions.authorizedToMarkAsNotApplicable && unmapped == 1 && notapp !=1}"> 
				<a href="#"
								onclick='openUserCommentsNotApplicableDialogForRule("${locateResultList.rule.headerRuleId}");'>
							<img src="${imageDir}/markAsNotApp.gif" 
								alt="Mark as Not Applicable"  title="Mark as Not Applicable" /></a>
						  </c:if>   
					</c:if>
					<c:set var="sentToTest" value="0" />
					<c:set var="objTransfrd" value="0" />
					<c:if
						test="${locateResultList.variableMappingStatus == 'SCHEDULED_TO_TEST'}">
						<c:set var="sentToTest" value="1" />
					</c:if>
					<c:if
						test="${locateResultList.variableMappingStatus == 'OBJECT_TRANSFERRED'}">
						<c:set var="objTransfrd" value="1" />
					</c:if>
					<c:if
						test="${locateResultList.variableMappingStatus == 'SCHEDULED_TO_PRODUCTION' || locateResultList.variableMappingStatus == 'PUBLISHED'}">
						<c:set var="published" value="1" />
					</c:if>
					<c:if
						test="${sentToTest != 1 && objTransfrd != 1 && unmapped != 1 && notapp !=1 && published != 1 && approve != 1 && lock != 1}">
						<a href="#" id="locate_SendToTestIcon_${locateResultList.rule.headerRuleId}"
							onclick='openUserCommentsDialogForRule("${locateResultList.rule.headerRuleId}","Send2TestRuleFromLocate");')><img
							src="${imageDir}/test_icon.gif" 
							alt="Send to Test" title="Send to Test"/></a>&#160;&#160;
			</c:if>
					<!--  Only for the approver //To Do: Access check 	 -->
					<c:if
						test="${approve != 1 && unmapped != 1 && notapp !=1 && sentToTest!=1 && published != 1 && lock != 1}">
						<c:if test="${userUIPermissions.authorizedToapprove || objTransfrd==1}"> 
							<a href="#" id="locate_approveIcon_${locateResultList.rule.headerRuleId}"
								onclick='openUserCommentsDialogForRule("${locateResultList.rule.headerRuleId}","ApproveRuleFromLocate");')><img
								src="${imageDir}/approve_icon.gif" 
								alt="Approve" title="Approve"/></a>
						</c:if>  
					</c:if></td>

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
</div>
<c:if test="${! empty locateResultsList}">
	<div style="padding-left:275px;padding-top:10px;">
	<form>
	<table>
		<tr>
			<td><c:if test="${page.totalNoOfPages != 1}">
				<a href="#"
					onclick='openPagntnResultsDialog("${page.currentPage}","First");'><img
					src="${imageDir}/First Page.gif" /></a>
				<a href="#"
					onclick='openPagntnResultsDialog("${page.currentPage}","Previous");'><img
					src="${imageDir}/Previous.gif" /></a>
			</c:if></td>

			<td valign="middle"
				style="padding-top:-70px;padding-right:10px;padding-left:10px;vertical-align:middle;">
			Page ${page.currentPage} of ${page.totalNoOfPages}</td>

			<td><c:if test="${page.totalNoOfPages != 1}">
				<a href="#"
					onclick='openPagntnResultsDialog("${page.currentPage}","Next");'><img
					src="${imageDir}/Next.gif" /></a>
				<a href="#"
					onclick='openPagntnResultsDialog("${page.totalNoOfPages}","Last");'><img
					src="${imageDir}/Last Page.gif" /></a>
				<input type="hidden" name="currentPage" id="currentPage"
					value="${page.currentPage}" />
			</c:if></td>

		</tr>
	</table>
	</form>
	</div>
</c:if>


<!--Ends viewHistoryVariables -->