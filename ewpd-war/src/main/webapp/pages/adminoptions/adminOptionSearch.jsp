<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Search Admin options</TITLE>
<%
String browser = request.getHeader("user-agent");
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
<script language="JavaScript" src="../../js/wpd.js"></script>
<%
}
else {
%>
<script language="JavaScript" src="../../js/browserCompatible.js"></script>
<script language="JavaScript" src="../../js/showModalDialog.js"></script>
<%
}
%>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</HEAD>



<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('AdminOptionSearchForm:locateButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy"
				value="#{searchAdminOptionBackingBean.adminName}">
			</h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form id="AdminOptionSearchForm">
				<TABLE border="0" cellspacing="0" cellpadding="0" width="100%">
					<TBODY>
						<TR>
							<td valign="top" class="ContentArea">


							<TABLE width="100%">
								<TR>
									<TD><w:message
										value="#{searchAdminOptionBackingBean.validationMessages}"></w:message>
									</TD>
								</TR>
							</TABLE>

							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<TR>
									<TD width="200"></TD>
									<TD width="200"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->


							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><BR>

							<!--	Start of Table for actual Data	-->
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD width="110"><h:outputText value="Name" /></TD>
										<TD width="182"><h:inputText styleClass="formInputField"
											id="txtAdminName" tabindex="1" maxlength="250"
											value="#{searchAdminOptionBackingBean.adminNameCriteria}" />
										</TD>
									</TR>

									<TR>
										<TD width="110"><h:outputText value="Term" /></TD>
										<TD width="182">
										<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtTerm"
											value="#{searchAdminOptionBackingBean.criteriaTerm}"></h:inputHidden>
										</TD>
										<TD width="660"><h:commandButton alt="Select" title="Select" id="TermButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/benefitTermPopUp.jsp?lookUpAction='+1+'&parentCatalog='+'term',
											 'TermDiv','AdminOptionSearchForm:txtTerm',2,1);return false;"
											tabindex="2"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="110"><h:outputText value="Qualifier" /></TD>
										<TD width="182">
										<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtQualifier"
											value="#{searchAdminOptionBackingBean.criteriaQualifier}"></h:inputHidden>
										</TD>
										<TD width="660"><h:commandButton alt="Select" title="Select" 
											id="QualifierButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/benefitQualifierPopUp.jsp?lookUpAction='+1+'&parentCatalog='+'qualifier',
											 'QualifierDiv','AdminOptionSearchForm:txtQualifier',2,2);return false;"
											tabindex="3"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="110"></TD>
									</TR>

									<TR>
										<TD width="110"><h:commandButton value="Locate"
											styleClass="wpdButton" tabindex="4" id="locateButton"
											action="#{searchAdminOptionBackingBean.searchAdminOption}">
										</h:commandButton></TD>
										<TD width="182">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							</DIV>

							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="25%"><h:outputText
													value="Administration Name"></h:outputText></TD>
												<TD align="left"><h:outputText value="Term"></h:outputText>
												</TD>
												<TD align="left"><h:outputText value="Qualifier"></h:outputText>
												</TD>
												<TD align="left"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left"><h:outputText value="Status"></h:outputText></TD>
												<TD align="left"><h:outputText></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="adminOption" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{searchAdminOptionBackingBean.adminOptionSearchResultList != null}"
										value="#{searchAdminOptionBackingBean.adminOptionSearchResultList}">
										<h:column>
											<h:inputHidden id="adminOptionId"
												value="#{adminOption.adminOptionId}"></h:inputHidden>
											<h:outputText id="adminOptionName"
												value="#{adminOption.adminName}"></h:outputText>
											<h:graphicImage id="lockImage"
												url="../../images/lock_icon.jpg" alt="Locked"
												rendered="#{adminOption.state.lockedByUser}"></h:graphicImage>
											<h:inputHidden id="adminName"
												value="#{adminOption.adminName}"></h:inputHidden>
											<h:inputHidden id="adminOptionVersion"
												value="#{adminOption.version}"></h:inputHidden>
										</h:column>
										<h:column>

											<h:outputText id="Term" value="#{adminOption.termDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="Qualifier"
												value="#{adminOption.qualifierId}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="adminVersion"
												value="#{adminOption.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="adminStatus" value="#{adminOption.status}"></h:outputText>
										</h:column>
										<h:column>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{adminOption.state.validForView}"
												onclick="performAction(1); return false;"></h:commandButton>

											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="View All Versions" title="View All Versions" id="basicViewAll"
												image="../../images/notes.gif"
												rendered="#{adminOption.state.validForView}"
												onclick="performAction(2); return false;">
											</h:commandButton>

											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="Edit" title="Edit" id="basicEdit"
												image="../../images/edit.gif" value="Edit"
												onclick="performAction(3);return false;"
												rendered="#{adminOption.state.editableByUser}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>


											<h:commandButton alt="Schedule For Test" title="Schedule For Test" id="scheduleForTest"
												image="../../images/schedule_test.gif"
												value="scheduleForTest"
												onclick="performAction(5);return false;"
												rendered="#{adminOption.state.validForTest && adminOption.status != 'SCHEDULED_FOR_TEST'}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Approve" title="Approve" id="approve"
												image="../../images/approved.gif" value="approve"
												onclick="performAction(10);return false;"
												rendered="#{adminOption.state.validForApprovalCompletion && adminOption.status != 'APPROVED'}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Reject" title="Reject" id="reject"
												image="../../images/rejected.gif" value="reject"
												onclick="performAction(11);return false;"
												rendered="#{adminOption.state.validForApprovalCompletion && adminOption.status != 'APPROVED'}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>


											<h:commandButton alt="Test Pass" title="Test Pass" id="testPass"
												image="../../images/test_successful.gif" value="testPass"
												onclick="performAction(6);return false;"
												rendered="#{adminOption.state.validForTestCompletion}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Test Fail" title="Test Fail" id="testFail"
												image="../../images/test_failed.gif" value="testFail"
												onclick="performAction(7);return false;"
												rendered="#{adminOption.state.validForTestCompletion}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Publish" title="Publish" id="publish"
												image="../../images/publish.gif" value="publish"
												onclick="performAction(8);return false;"
												rendered="#{adminOption.state.validForPublish}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Check Out" title="Check Out" id="basicCheckOut"
												image="../../images/checkOut.gif" value="Check Out"
												onclick="performAction(9);return false;"
												rendered="#{adminOption.state.validForCheckOut}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												onclick="performAction(4);return false;"
												rendered="#{adminOption.state.validForDelete}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>
											<h:commandButton alt="Delete All Versions" title="Delete All Versions" id="deleteAll"
												image="../../images/deleteAll.gif" value="Delete All"
												onclick="performAction(13);return false;"
												rendered="#{adminOption.state.validForDeleteAll}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>
											<h:outputText value=" " id="a1spaceSpan09"
												rendered="#{adminOption.state.validForUnlock}"></h:outputText>
											<h:commandButton alt="Unlock" title="unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="performAction(12);return false;"
												rendered="#{adminOption.state.validForUnlock}">
											</h:commandButton>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>
							</TD>
						</TR>
				</TABLE></td>
		</tr>

		<h:inputHidden id="adminIdToEditPage"
			value="#{createAdminOptionBackingBean.adminOptionId}"></h:inputHidden>
		<h:inputHidden id="adminNameToEditPage"
			value="#{createAdminOptionBackingBean.adminName}"></h:inputHidden>
		<h:inputHidden id="adminVersionToEditPage"
			value="#{createAdminOptionBackingBean.version}"></h:inputHidden>
		<h:commandLink id="editAdminOption"
			style="display:none; visibility: hidden;"
			action="#{createAdminOptionBackingBean.displayAdminOptionTab}">
			<f:verbatim />
		</h:commandLink>


		<h:inputHidden id="hiddenAdminOptionId"
			value="#{searchAdminOptionBackingBean.adminOptionId}"></h:inputHidden>
		<h:inputHidden id="hiddenAdminOptionName"
			value="#{searchAdminOptionBackingBean.adminName}"></h:inputHidden>
		<h:inputHidden id="hiddenAdminOptionVersion"
			value="#{searchAdminOptionBackingBean.version}"></h:inputHidden>

		<h:commandLink id="deleteAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.deleteAdminOption}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="deleteAllVersions"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.deleteAllVersions}">
			<f:verbatim />
		</h:commandLink>

		<h:commandLink id="searchAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.searchAdminOption}">
			<f:verbatim />
		</h:commandLink>

		<h:commandLink id="checkOutAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.checkOutAdminOption}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="scheduleTestAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.scheduleTest}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="publishAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.publish}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="testPassAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.testPass}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="testFailAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.testFail}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="approveAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.approve}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="rejectAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.reject}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="unlockAdminOption"
			style="display:none; visibility: hidden;"
			action="#{searchAdminOptionBackingBean.unlock}">
			<f:verbatim />
		</h:commandLink>
		<tr>
			<td></h:form> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>

</f:view>
<script language="javascript"><!--

document.getElementById('AdminOptionSearchForm:txtAdminName').focus(); // for on load default selection
	function syncTables(){
			var relTblWidth = document.getElementById('AdminOptionSearchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}		
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('AdminOptionSearchForm:searchResultTable') != null) {
			// setColumnWidth('AdminOptionSearchForm:searchResultTable','25%:10%:10%:10%:20%:25%');
			// setColumnWidth('resultHeaderTable','25%:10%:10%:10%:20%:25%');	
			showResultsTab();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	

		var tableObject = document.getElementById('AdminOptionSearchForm:searchResultTable');
		if(tableObject!= null){
		var size = document.getElementById('AdminOptionSearchForm:searchResultTable').rows.length;
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		if(size>=1){
				document.getElementById('AdminOptionSearchForm:searchResultTable').width = relTblWidth-17+"px";
				syncTables('resultHeaderTable','AdminOptionSearchForm:searchResultTable');
				setColumnWidth('AdminOptionSearchForm:searchResultTable','25%:12%:12%:11%:20%:20%');
				setColumnWidth('resultHeaderTable','25%:12%:12%:11%:20%:20%');
		}else{
			setColumnWidth('resultHeaderTable','25%:10%:10%:10%:20%:25%');
			setColumnWidth('AdminOptionSearchForm:searchResultTable','25%:10%:10%:10%:20%:25%');
		}
	}

		if(document.getElementById('AdminOptionSearchForm:searchResultTable') != null){
			document.getElementById('AdminOptionSearchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}



	/**
	Method used to call the particular action, when a button is clicked in the page.
	For each button, a particular actionType is passing.
	*/
	function performAction(actionType) {
		copyValuesToHidden();
		switch(actionType) {
			case 1:
				var url = '../adminoptions/adminOptionsView.jsp'+getUrl()+'?'+'temp='+Math.random()+'&adminkey='+document.getElementById('AdminOptionSearchForm:hiddenAdminOptionId').value+'&&'+'adminname='+ escape(document.getElementById('AdminOptionSearchForm:hiddenAdminOptionName').value)+'&&'+ 'version='+document.getElementById('AdminOptionSearchForm:hiddenAdminOptionVersion').value;
				newWinForView=window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");
				break;			
			case 2:
				viewAllAction();
				break;			
			case 3:
				editSelectedAdminOption();
				break;
			case 4:
				deleteSelectedAdminOption();
				break;		
			case 5:
				document.getElementById('AdminOptionSearchForm:scheduleTestAdminOption').click();
				break;			
			case 6:
				document.getElementById('AdminOptionSearchForm:testPassAdminOption').click();
				break;			
			case 7:
				document.getElementById('AdminOptionSearchForm:testFailAdminOption').click();
				break;			
			case 8:
				document.getElementById('AdminOptionSearchForm:publishAdminOption').click();
				break;
			case 9:
				document.getElementById('AdminOptionSearchForm:checkOutAdminOption').click();
				break;
			case 10:
				document.getElementById('AdminOptionSearchForm:approveAdminOption').click();
				break;
			case 11:
				document.getElementById('AdminOptionSearchForm:rejectAdminOption').click();
				break;
			case 12:
				document.getElementById('AdminOptionSearchForm:unlockAdminOption').click();
				break;
			case 13:
				deleteAllAdminOptions();
				break;		
		}	
	}


	function copyValuesToHidden() {
		getFromDataTableToHidden('AdminOptionSearchForm:searchResultTable', 'adminOptionId', 'AdminOptionSearchForm:hiddenAdminOptionId');
		getFromDataTableToHidden('AdminOptionSearchForm:searchResultTable', 'adminName', 'AdminOptionSearchForm:hiddenAdminOptionName');
		getFromDataTableToHidden('AdminOptionSearchForm:searchResultTable', 'adminOptionVersion', 'AdminOptionSearchForm:hiddenAdminOptionVersion');
	}

	function editSelectedAdminOption() {
		getFromDataTableToHidden('AdminOptionSearchForm:searchResultTable', 'adminOptionId', 'AdminOptionSearchForm:adminIdToEditPage');
		getFromDataTableToHidden('AdminOptionSearchForm:searchResultTable', 'adminName', 'AdminOptionSearchForm:adminNameToEditPage');
		getFromDataTableToHidden('AdminOptionSearchForm:searchResultTable', 'adminOptionVersion', 'AdminOptionSearchForm:adminVersionToEditPage');
		document.getElementById('AdminOptionSearchForm:editAdminOption').click();
		return false;
	}

	function deleteSelectedAdminOption(){
		var message = 'Are you sure to delete the selected Admin Option?';
		if (confirm(message) ){
			document.getElementById('AdminOptionSearchForm:deleteAdminOption').click();
			return true;
		} else
			return false;
	}

	function deleteAllAdminOptions(){
		var message = 'Are you sure to delete all the versions of the selected Admin Option?';
		if (confirm(message) ){
			document.getElementById('AdminOptionSearchForm:deleteAllVersions').click();
			return true;
		} else
			return false;
	}

	function viewAllAction() {
		var url = '../adminoptions/adminOptionViewAll.jsp'+getUrl()+'?temp='+Math.random()+'&adminName='+escape(document.getElementById('AdminOptionSearchForm:hiddenAdminOptionName').value)+'&action=viewAll'
		var retValueFromChild = window.showModalDialog(url, window, "dialogHeight:650px;dialogWidth:1050px;resizable=false;status=no");

		if(retValueFromChild == 'REFRESH'){
		document.getElementById('AdminOptionSearchForm:searchAdminOption').click();
		} else if(retValueFromChild == undefined) {
			return true;
		} else {
			var values = retValueFromChild.split("~");
			var action = values[0];
			var arg1 = values[1];
			var arg2 = values[2];
			var arg3 = values[3];

			if (action == 'EDIT') {
				document.getElementById('AdminOptionSearchForm:adminIdToEditPage').value = arg1;
				document.getElementById('AdminOptionSearchForm:adminNameToEditPage').value = arg2;
				document.getElementById('AdminOptionSearchForm:adminVersionToEditPage').value = arg3;
				document.getElementById('AdminOptionSearchForm:editAdminOption').click();
			} else if (action == 'CHECKOUT') {
				document.getElementById('AdminOptionSearchForm:hiddenAdminOptionId').value = arg1;
				document.getElementById('AdminOptionSearchForm:hiddenAdminOptionName').value = arg2;
				document.getElementById('AdminOptionSearchForm:hiddenAdminOptionVersion').value = arg3;
				document.getElementById('AdminOptionSearchForm:checkOutAdminOption').click();
			}
		}
	}



	copyHiddenToDiv_ewpd('AdminOptionSearchForm:txtQualifier','QualifierDiv',2,2); 
	copyHiddenToDiv_ewpd('AdminOptionSearchForm:txtTerm','TermDiv',2,1); 
		
--></script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOptionSearchResultPrint"></form>
</HTML>
