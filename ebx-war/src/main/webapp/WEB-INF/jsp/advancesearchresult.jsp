<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<div style="height:83%;overflow-x: hidden; overflow-y: auto; width: 100%;">
<table id="advanceSearchTable" border="1" cellpadding="0" cellspacing="0" class="advanceSearchLocateTable locateT shadedText advancedSearchWidth" width="100%">

	<THEAD>
		<c:if test="${viewtype == 'SPS'}">
		<tr height="20">
			<th width="4%" style="padding-left: 20px;" class="tableHeader">
				<input type="checkbox" id="headerCheck" onclick="updateAllSelected('${pageContext.request.contextPath}',this,'${page.currentPage}')"/>
			</th>
			<th id="searchEb03" width="8%" class="tableHeader">SPS ID&#160;</th>
			<th id="searchVar" width="33%" class="tableHeader">Description&#160;</th>
			<th  width="8%" class="tableHeader">EB01&#160;</th>
			<th  width="8%" class="tableHeader">EB02&#160;</th>
			<th  width="8%" class="tableHeader">EB06&#160;</th>
			<th  width="8%" class="tableHeader">EB09&#160;</th>
			<th  width="8%" class="tableHeader">User&#160;</th>
			<th  width="15%" class="tableHeader">
				<div class="AccordionTitle" onClick="runAccordionSearch(1);"  style="text-align:right;padding-right:40px;width:30px;float:right;" onselectstart="return false;">
					 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-5px; left:-10px;height:5px;">
							<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:15px;"/>
					</span>
				</div>
				<div style="width:30px;text-align:right;float:right;">
					<a onclick='generateReport();' href ='#'><img src='${imageDir}/print.gif' class='printIt' id='printIcon' alt='Print' title='Print'></a>
				</div>
			</th>
		</tr>
		</c:if>
		<c:if test="${viewtype == 'RULE'}">
		<tr height="20">
			<th width="4%" style="padding-left: 20px;" class="tableHeader">
				<input type="checkbox" id="headerCheck" onclick="updateAllSelected('${pageContext.request.contextPath}',this,'${page.currentPage}')"/>
			</th>
			<th id="searchEb03" width="12%" class="tableHeader">Header Rule ID&#160;</th>
			<th id="searchVar" width="25%" class="tableHeader">Description&#160;</th>
			<th width="15%" class="tableHeader">EB03&#160;</th>
			<th width="10%" class="tableHeader">Date Created&#160;</th>
			<th  width="8%" class="tableHeader">Status&#160;</th>
			<th  width="8%" class="tableHeader">User&#160;</th>
			<th  width="15%" class="tableHeader">
			<div class="AccordionTitle" onClick="runAccordionSearch(1);" style="text-align:right;padding-right:40px;width:30px;float:right;" onselectstart="return false;">
					 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-5px; left:-10px;height:5px;">
							<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:15px;"/>
					</span>
				</div>
				<div style="width:30px;text-align:right;float:right;">
					<a onclick='generateReport();' href ='#'><img src='${imageDir}/print.gif' class='printIt' id='printIcon' alt='Print' title='Print'></a>
				</div>
			</th>
		</tr>
		</c:if>
		<c:if test="${viewtype == 'MSG'}">
		<tr height="20">
			<th width="4%" style="padding-left: 20px;" class="tableHeader">
				<input type="checkbox" id="headerCheck" onclick="updateAllSelected('${pageContext.request.contextPath}',this,'${page.currentPage}')"/>
			</th>
			<th id="searchEb03" width="15%" class="tableHeader">Header Rule ID&#160;</th>
			<th id="searchEb03" width="10%" class="tableHeader">SPS ID&#160;</th>
			<th id="searchVar" width="26%" class="tableHeader">Message Text&#160;</th>
			<th id="searchVar" width="20%" class="tableHeader">Note Type Code&#160;</th>
			<th  width="10%" class="tableHeader">User&#160;</th>
			<th  width="15%" class="tableHeader">
				<div class="AccordionTitle" onClick="runAccordionSearch(1);" style="text-align:right;padding-right:40px;width:30px;float:right;" onselectstart="return false;">
					 <span class="ui-widget-header" style="display:inline-block;background:none; border:0px; position:relative; top:-5px; left:-10px;height:5px;">
							<span class="ui-icon ui-icon-triangle-1-s" style="position:relative;height:15px;"/>
					</span>
				</div>
					<div style="width:30px;text-align:right;float:right;">
					<a onclick='generateReport();' href ='#'><img src='${imageDir}/print.gif' class='printIt' id='printIcon' alt='Print' title='Print'></a>
				</div>
			</th>
		</tr>
		</c:if>
	</THEAD>
	<TBODY>
		<c:if test="${! empty searchResults}">
		<c:forEach items="${searchResults}" var="searchResult">
		<c:if test="${viewtype == 'SPS'}">
		<c:if test="${searchResult.finalizedFlag =='N'}">
				<c:set var="rowClass" value="finalized" />
			</c:if>
			<c:if test="${searchResult.finalizedFlag =='Y'}">
				<c:set var="rowClass"  value="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"/>		
			</c:if>	
		<tr class="${rowClass}">
			<td  height="20px" style="padding-left: 20px;">
			<input <c:out value="${searchResult.checked}"/> type="checkbox" name="searchCheckBox" onclick="updateSelected('${pageContext.request.contextPath}',this,'${searchResult.spsId}','','','${page.currentPage}')"/></td>
			<td><span onclick='openViewPopForSpsAndRuleID("${searchResult.spsId}","${searchResult.status}","SPS","${searchResult.formattedDate}");' style="cursor:hand;" title="Click to view details">${searchResult.spsId}</span></td>
			<td >${searchResult.description}</td>	
			<td >${searchResult.EB01}</td>		
			<td >${searchResult.EB02}</td>
			<td >${searchResult.EB06}</td>
			<td >${searchResult.EB09}</td>	
			<td >${searchResult.lastUpdatedUser}</td>
			<td>
			<a href="#" onclick ='viewSpsMapping("${searchResult.spsId}","${searchResult.status}");'><img src="${imageDir}/search_icon.gif"  alt="View" title="View"  /></a>&#160;&#160;
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked != 'true'}">
				<c:set var="idEdit" value="${searchResult.spsId}_Edit" />
				<a href="#" onclick='editMappingForSps("${searchResult.spsId}");'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" /></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status == 'UNMAPPED'}">
				<a href="#" onclick='openCreateFromUnmappedSectionForSps("${searchResult.spsId}");'>
				<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create"/>
			</a>
			</c:if>
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked == 'true' && !userUIPermissions.authorizedToUnlock}">
				<img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked == 'true' && userUIPermissions.authorizedToUnlock}">
				<c:set var="idLock" value="${searchResult.spsId}_Lock" />
				<a href="#" id="${idLock}" onclick='unlockMappingFromLocate("","${searchResult.spsId}","unlockSpsFromAdvanceSearch","${searchResult.lockedUserId}");'><img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status == 'UNMAPPED' && userUIPermissions.authorizedToMarkAsNotApplicable}">
				<c:set var="idNotApplicable" value="${searchResult.spsId}_NotApp" />
				<a href="#" id="${idNotApplicable}" onclick='openUserCommentsNotApplicableDialogForSps("${searchResult.spsId}");'><img src="${imageDir}/markAsNotApp.gif"  alt="Mark as Not Applicable" title="Mark as Not Applicable"/></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status == 'NOT_APPLICABLE'}">
				<a href="#"	onclick='editMappingForSps("${searchResult.spsId}");'><img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>
			</c:if>
			<c:set var="idTest" value="${searchResult.spsId}_Test" />
			<c:set var="idApprove" value="${searchResult.spsId}_Approve" />	
			<c:if test="${searchResult.sendToTest == 'true'}">
				<a href="#" id ="${idTest}"  onclick='openUserCommentsSend2TestDialogForSps("${searchResult.spsId}");'><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" /></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.approve == 'true'}">
				<a href="#" id ="${idApprove}" onclick='openUserCommentsApproveDialogForSps("${searchResult.spsId}");'><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>
			</c:if>
		</td>
		</tr>
		</c:if>
		<c:if test="${viewtype == 'RULE'}">
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<td height="20px" style="padding-left: 20px;">
			<input <c:out value="${searchResult.checked}"/> type="checkbox" name="searchCheckBox" onclick="updateSelected('${pageContext.request.contextPath}',this,'','${searchResult.headerRuleId}','','${page.currentPage}')"/></td>
			<td >${searchResult.headerRuleId}</td>
			<td >${searchResult.description}</td>
			<td >${searchResult.EB03}</td>
			<td >${searchResult.formattedDate}</td>
			<td id="status_${searchResult.headerRuleId}">${searchResult.status}</td>
			<td>${searchResult.lastUpdatedUser}</td>
			<td>
			<a href="#" onclick ='viewRuleMapping("${searchResult.headerRuleId}","${searchResult.status}");'><img src="${imageDir}/search_icon.gif"  alt="View" title="View" /></a>&#160;&#160;
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked != 'true'}">
				<c:set var="idEdit" value="${searchResult.headerRuleId}_Edit" />
				<a href="#" onclick='editMappingForRule("${searchResult.headerRuleId}");'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit" /></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status == 'UNMAPPED'}">
				<a href="#" onclick='openCreateFromUnmappedSectionForRule("${searchResult.headerRuleId}");'>
				<img src="${imageDir}/create_icon.gif"  alt="Create" title="Create" />
				</a>
			</c:if>
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked == 'true' && !userUIPermissions.authorizedToUnlock}">
				<img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked == 'true' && userUIPermissions.authorizedToUnlock}">
				<c:set var="idLock" value="${searchResult.headerRuleId}_Lock" />
				<a href="#" id="${idLock}" onclick='unlockMappingFromLocate("${searchResult.headerRuleId}","","unlockRuleFromAdvanceSearch","${searchResult.lockedUserId}");'><img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/></a>&#160;&#160;
			</c:if>
			
			<c:if test="${searchResult.status == 'UNMAPPED' && userUIPermissions.authorizedToMarkAsNotApplicable}">
				<a href="#" onclick='openUserCommentsNotApplicableDialogForRule("${searchResult.headerRuleId}");' ><img src="${imageDir}/markAsNotApp.gif"  alt="Mark as Not Applicable" title="Mark as Not Applicable"/></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status == 'NOT_APPLICABLE'}">
				<a href="#"	onclick='editMappingForRule("${searchResult.headerRuleId}");'><img src="${imageDir}/Applicable.gif" alt="Mark as Applicable"  title="Mark as Applicable"/></a>&#160;&#160;
			</c:if>
			<c:set var="idTest" value="${searchResult.headerRuleId}_Test" />
			<c:set var="idApprove" value="${searchResult.headerRuleId}_Approve" />
			<c:if test="${searchResult.sendToTest == 'true'}">
				<a href="#" id ="${idTest}" onclick='openUserCommentsSend2TestDialogForRule("${searchResult.headerRuleId}");'><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test"/></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.approve == 'true'}">
				<a href="#" id ="${idApprove}" onclick='openUserCommentsApproveDialogForRule("${searchResult.headerRuleId}");'><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>&#160;&#160;
			</c:if>
			</td>
		</tr>
		</c:if>
		<c:if test="${viewtype == 'MSG'}">
		<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
			<td height="20px" style="padding-left: 20px;">
			<input <c:out value="${searchResult.checked}"/>  type="checkbox" name="searchCheckBox" onclick="updateSelected('${pageContext.request.contextPath}',this,'${searchResult.spsId}','${searchResult.headerRuleId}','','${page.currentPage}')"/></td>
			<td><span onclick='openViewPopCustomMsg("${searchResult.spsId}","${searchResult.headerRuleId}","${searchResult.formattedDate}");' style="cursor:hand;" title="Click to view details">${searchResult.headerRuleId}</span></td>
			<td><span onclick='openViewPopCustomMsg("${searchResult.spsId}","${searchResult.headerRuleId}","${searchResult.formattedDate}");' style="cursor:hand;" title="Click to view details">${searchResult.spsId}</span></td>
			<td>${searchResult.messageText}</td>
			<td>${searchResult.noteTypeCode}</td>
			<td>${searchResult.lastUpdatedUser}</td>			
			<td>
			<a href="#" onclick ='openViewMappingInProgressDialogForCustomMsg("${searchResult.headerRuleId}","${searchResult.spsId}");'><img src="${imageDir}/search_icon.gif" alt="View" title="View" /></a>&#160;&#160;
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked != 'true'}">
				<a href="#" onclick='editMappingForCustomMsg("${searchResult.headerRuleId}","${searchResult.spsId}");'><img src="${imageDir}/edit_icon.gif" alt="Edit" title="Edit"/></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked == 'true' && !userUIPermissions.authorizedToUnlock}">
				<img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.status != 'UNMAPPED' && searchResult.status != 'NOT_APPLICABLE' && searchResult.locked == 'true' && userUIPermissions.authorizedToUnlock}">
				<c:set var="idLock" value="${searchResult.headerRuleId}${searchResult.spsId}_Lock" />
				<a href="#" id="${idLock}" onclick='unlockMappingFromLocate("${searchResult.headerRuleId}","${searchResult.spsId}","unlockCustomMsgFromAdvanceSearch","${searchResult.lockedUserId}");'><img src="${imageDir}/lock_icon.jpg" alt="Lock" title="Lock"  style="height:15px;"/></a>&#160;&#160;
			</c:if>
			<c:set var="idTest" value="${searchResult.headerRuleId}${searchResult.spsId}_Test" />
			<c:set var="idApprove" value="${searchResult.headerRuleId}${searchResult.spsId}_Approve" />		
			<c:if test="${searchResult.sendToTest == 'true'}">
				<a href="#" onclick='openUserCommentsSend2TestDialogForCustomMsg("${searchResult.headerRuleId}","${searchResult.spsId}");'><img src="${imageDir}/test_icon.gif" alt="Send to Test" title="Send to Test" /></a>&#160;&#160;
			</c:if>
			<c:if test="${searchResult.approve == 'true'}">
				<a href="#" onclick='openUserCommentsApproveDialogForCustomMsg("${searchResult.headerRuleId}","${searchResult.spsId}");'><img src="${imageDir}/approve_icon.gif" alt="Approve" title="Approve"/></a>
			</c:if>
			</td>
		</tr>
		</c:if>
		<c:set var="rowCount" value="${rowCount + 1}"/>
		</c:forEach>
		</c:if>
		<c:if test="${empty searchResults}">
			<c:if test="${viewtype == 'SPS'}">
				<tr style="height: 100px">
					<td colspan="9" align="center" valign="middle" style="height: 100px;width:100%">No Results matching your search
					criteria</td>
				</tr>
			</c:if>
			<c:if test="${viewtype == 'RULE'}">
				<tr style="height: 100px">
					<td colspan="8" align="center" valign="middle" style="height: 100px">No Results matching your search
					criteria</td>
				</tr>
			</c:if>
			<c:if test="${viewtype == 'MSG'}">
				<tr style="height: 100px">
					<td colspan="7" align="center" valign="middle" style="height: 100px">No Results matching your search
					criteria</td>
				</tr>
			</c:if>
		</c:if>
	</TBODY>
</table>
</div>	
<c:if test="${! empty searchResults}">
		<div style="padding-left:350px;padding-top:5px;">
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
		</div>
	</c:if>