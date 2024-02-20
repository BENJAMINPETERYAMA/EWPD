<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />


<script type="text/javascript">

	
</script>
<div style="height:83%;overflow-x: hidden; overflow-y: auto; width:100%;">
<table id="advanceSearchTable"  border="1" cellpadding="0" cellspacing="0" 
			class="locateTable locateT shadedText advancedSearchWidth" width="100%">
	<THEAD>
		<tr height="20">
			<!--  	<th width="25px" class="tableHeader"><span style="width: 100px;">&#160;</span></th> -->
			<th width="4%" style="padding-left: 20px;" class="tableHeader">
				<input type="checkbox" id="headerCheck" onclick="updateAllSelected('${pageContext.request.contextPath}',this,'${page.currentPage}')"/></th>
			<th width="8%"
				class="tableHeader">System&#160;</th>
			<th id="searchEb03"
				width="12%" class="tableHeader">Variable&#160;</th>
			<th id="searchVar"
				width="25%" class="tableHeader">Description&#160;</th>
			<th width="8%"
				class="tableHeader">EB01&#160;</th>
			<th width="20%"
				class="tableHeader">EB03&#160;</th>
			<th width="8%"
				class="tableHeader">User&#160;</th>
			<th width="15%"
				class="tableHeader">
				<div class="AccordionTitle" onClick="runAccordionSearch(1);" style="text-align:right;padding-right:40px;width:30px;float:right;" onselectstart="return false;">
					 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-5px; left:-10px;height:5px;">
							<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:15px;"/>
					</span>
				</div>
				<div>
					<a onclick='generateReport();' href ='#'><img src='${imageDir}/print.gif' class='printIt' id='printIcon' alt='Print' title='Print'></a>
				</div>
			</th>
		</tr>
	</THEAD>

	<TBODY>
			<c:if test="${! empty searchResults}">
			<c:set var="rowCount" value="0" />
			<c:forEach items="${searchResults}" var="searchResult">
		  	<c:if test="${searchResult.notCompleteFlag =='N'}">
				<c:set var="rowClass" value="finalized" />
			</c:if> 
		 	<c:if test="${searchResult.notCompleteFlag == 'Y' || searchResult.status == 'UNMAPPED'}"> 
				<c:set var="rowClass"  value="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"/>		
			</c:if>	
				<tr class="${rowClass}">
					<td width="25px" height="20px" style="padding-left: 20px;"><input <c:out value="${searchResult.checked}"/> type="checkbox" name="searchCheckBox" onclick="updateSelected('${pageContext.request.contextPath}',this,'','','${searchResult.variableId}','${page.currentPage}')" /></td>
					<td>${searchResult.system}</td>

					<td style="cursor:hand;" title="Click to view details"
						onclick='openViewPopUpForMappedAndUnMapped("${searchResult.variableId}","${searchResult.status}","${searchResult.system}","${searchResult.formattedDate}");'>${searchResult.variableId} 
						<c:if test="${searchResult.auditLock == 'Y' && unmapped != 1 }">
						<img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" id="AuditLockImg_${searchResult.variableId}" style="height:9px;width:9px"  />
						</c:if>
						<c:if test="${searchResult.auditLock == 'N' && unmapped != 1 }">
						<div id="AuditLockImg_${searchResult.variableId}"  ></div>
						</c:if> 
						</td>

					<td>${searchResult.description}</td>
					<td>${searchResult.EB01}</td>
					<td>${searchResult.EB03}</td>
					<td>${searchResult.user}</td>
						<td width="115px"><!-- ICONS --> 
						<c:set var="unmapped" value="0" />
						<c:set var="published" value="0" />
						<c:set var="approve" value="0" /> 
						<c:set var="lock" value="0" /> 
						<c:if test="${searchResult.status == 'UNMAPPED'}">
						<c:set var="unmapped" value="1" />
					</c:if> <c:set var="notapp" value="0" /> <c:if
						test="${searchResult.status == 'NOT_APPLICABLE'}">
						<c:set var="notapp" value="1" />
					</c:if> <c:if test="${searchResult.status == 'APPROVE'}">
						<c:set var="approve" value="1" />
					</c:if> 
					 <c:if test="${searchResult.locked == 'true'}">
						<c:set var="lock" value="1" />
					</c:if> 
					<c:if test="${unmapped == 1}">
						<a href="#"
							onclick='openViewMappingDialog("${searchResult.variableId}");'><img
							src="${imageDir}/search_icon.gif" alt="View" title="View" /></a>
					</c:if> 
					<c:if test="${unmapped != 1}">
						<a href="#"
							onclick='openViewMappingInProgressDialog("${searchResult.variableId}");'><img
							src="${imageDir}/search_icon.gif" alt="View" title="View" /></a>
					</c:if>		
					<c:if test="${unmapped != 1 && notapp != 1 && lock != 1 }">
						<c:if test="${(searchResult.auditLock == 'N') || (userUIPermissions.authorizedEditLockVar && searchResult.auditLock == 'Y')}">
							<a href="#" id="locate_EditIcon_${searchResult.variableId}"
								onclick='editMappingVariable("${searchResult.variableId}");'>
							<img src="${imageDir}/edit_icon.gif"  alt="Edit" title="Edit" /></a>
						</c:if>
						<c:if test="${!userUIPermissions.authorizedEditLockVar && searchResult.auditLock == 'Y'}">
							<span id="${searchResult.variableId}_LocateSpace" style="display:none;"></span>
						</c:if>
					</c:if>
					<c:if test="${unmapped != 1 && notapp != 1 && lock == 1}">
					<input type="hidden" name="lastUpdatedUserId" id="lastUpdatedUserId" value="${locateResultList.user.lastUpdatedUserName}" />
						<c:if test="${userUIPermissions.authorizedToUnlock}">
							<a href="#" id="locate_UnlockIcon_${searchResult.variableId}"
								onclick='unlockMappingFromLocate("${searchResult.variableId}","unlockVariableFromLocate", "${searchResult.lockedUserId}","${searchResult.auditLock}","${searchResult.system}","${searchResult.description}");'><img
								src="${imageDir}/lock_icon.jpg"  alt="${searchResult.lockedUserId}" title="${searchResult.lockedUserId}" /></a>
						</c:if>
						<c:if test="${!userUIPermissions.authorizedToUnlock}">
							<img src="${imageDir}/lock_icon.jpg"  alt="${searchResult.lockedUserId}" title="${searchResult.lockedUserId}" />
						</c:if>
					</c:if>
					
					<c:if test="${notapp == 1}">
						<c:if test="${(userUIPermissions.authorizedEditLockVar) || (!userUIPermissions.authorizedEditLockVar && searchResult.auditLock == 'N')}">
							<a href="#" id="locate_NA_${searchResult.variableId}" onclick='editMappingVariable("${searchResult.variableId}");'>
							<img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>
						</c:if>
						<c:if test="${!userUIPermissions.authorizedEditLockVar && searchResult.auditLock == 'Y'}">
							<span id="${searchResult.variableId}_NASpace" style="display:none;"></span>
						</c:if>
					</c:if>
					<c:if test="${unmapped == 1 && lock != 1}">
						<a href="#"
							onclick='openCreateFromUnmappedSection("${searchResult.variableId}");'><img
							src="${imageDir}/create_icon.gif" 
							alt="Create"  title="Create"/></a>
					</c:if>
						<c:if test="${ notapp !=1 && lock != 1 }">
						<c:if test="${searchResult.status == 'UNMAPPED' && userUIPermissions.authorizedToMarkAsNotApplicable}">
					<a href="#"
								onclick='openUserCommentsNotApplicableDialog("${searchResult.variableId}","${searchResult.description}");'>
							<img src="${imageDir}/markAsNotApp.gif" 
							alt="Mark as Not Applicable"  title="Mark as Not Applicable" /></a>
						</c:if>
					</c:if>
					<c:set var="sentToTest" value="0" />
					<c:set var="objTransfrd" value="0" />
					<c:if
						test="${searchResult.status == 'SCHEDULED_TO_TEST'}">
						<c:set var="sentToTest" value="1" />
					</c:if>
					<c:if
						test="${searchResult.status == 'OBJECT_TRANSFERRED'}">
						<c:set var="objTransfrd" value="1" />
					</c:if>
					<c:if
						test="${searchResult.status == 'SCHEDULED_TO_PRODUCTION' || searchResult.status == 'PUBLISHED'}">
						<c:set var="published" value="1" />
					</c:if>
					<c:if test="${searchResult.sendToTest == 'true'}">
						<a href="#" id="locate_SendToTestIcon_${searchResult.variableId}"
							onclick='openUserCommentsDialog("${searchResult.variableId}","Send2TestFromLocate");')><img
							src="${imageDir}/test_icon.gif" 
							alt="Send to Test" title="Send to Test"/></a>&#160;&#160;
					</c:if>
					<!--  Only for the approver //To Do: Access check 	 -->
					<c:if test="${searchResult.approve == 'true'}">
							<a href="#" id="locate_approveIcon_${searchResult.variableId}"
								onclick='openUserCommentsDialog("${searchResult.variableId}","ApproveFromLocate");')><img
								src="${imageDir}/approve_icon.gif" 
								alt="Approve" title="Approve"/></a>
					</c:if> 
					<c:if test="${unmapped != 1}"> 
					<c:if
						test="${userUIPermissions.authorizedToAuditLock && searchResult.auditLock == 'Y' && lock != 1}">
						<c:set var="auditLock"
							value="AuditLock_${searchResult.variableId}" />
						<a href="#" id="${auditLock}"
							onclick='openUserCommentsLockDialog ("${searchResult.variableId}","${searchResult.system}","${searchResult.lockedUserId}","${searchResult.description}","Unlock");'><img
							src="${imageDir}/auditUnLockIndicator.GIF" alt="UnLock"
							title="UnLock" style="height: 15px;" /></a>
					</c:if> 
					<c:if
						test="${userUIPermissions.authorizedToAuditUnlock && searchResult.auditLock == 'N' && lock != 1}">
						<c:set var="auditUnLock"
							value="AuditUnLock_${searchResult.variableId}" />
						<a href="#" id="${auditUnLock}"
							onclick='openUserCommentsLockDialog ("${searchResult.variableId}","${searchResult.system}","${searchResult.lockedUserId}","${searchResult.description}","Lock");'><img
							src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock"
							title="Lock" style="height: 15px;" /></a>
					</c:if>
					<c:if test="${userUIPermissions.authorizedToAuditUnlock && searchResult.auditLock == 'N' && lock == 1}">
						<div id="AuditUnLock_${searchResult.variableId}"  ></div>
					</c:if>
					<c:if test="${userUIPermissions.authorizedToAuditUnlock && searchResult.auditLock == 'Y' && lock == 1}">
						<div id="AuditLock_${searchResult.variableId}"  ></div>
					</c:if>	
					</c:if>
					</td>
				</tr>
				<c:set var="rowCount" value="${rowCount + 1}" />
			</c:forEach>
		</c:if>

		<c:if test="${empty searchResults}">
			<tr style="height: 100px">
				<td colspan="8" align="center" valign="middle" style="height: 100px">No Results matching your search
				criteria</td>
			</tr>
		</c:if>
	</TBODY>
</table>
</div>
<c:if test="${! empty searchResults}">
		<div style="padding-left:350px;padding-top:5px;">
		<form>
		<table>
			<tr>
				<td width="70">
				<c:if test="${page.totalNoOfPages != 1}">
					<a href="#"
						onclick='searchMessageText("${page.currentPage}","First");'><img
						src="${imageDir}/First Page.gif" /></a>
					<a href="#"
						onclick='searchMessageText("${page.currentPage}","Previous");'><img
						src="${imageDir}/Previous.gif" /></a>
				</c:if></td>
	
				<td valign="middle"
					style="padding-top:-70px;padding-right:10px;padding-left:10px;vertical-align:middle;">
				${page.currentPage} of ${page.totalNoOfPages}</td>
	
				<td width="70">
					<c:if test="${page.totalNoOfPages != 1}">
					<a href="#"
						onclick='searchMessageText("${page.currentPage}","Next");'><img
						src="${imageDir}/Next.gif" /></a>
					<a href="#"
						onclick='searchMessageText("${page.totalNoOfPages}","Last");'><img
						src="${imageDir}/Last Page.gif" /></a>
					<input type="hidden" name="currentPage" id="currentPage"
						value="${page.currentPage}" />
				</c:if></td>
				<td width="200px" align="left" style="padding-left: 10;vertical-align: middle;">
					<div id="selectedPageDisplayContainer" align="left">
						<c:out value="${selectedPageInfo}"></c:out>
					</div>
				</td>
			</tr>
		</table>
		</form>
		</div>
	</c:if>		
					
					