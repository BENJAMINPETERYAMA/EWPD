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

<TITLE>View All Versions of Administration Option</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<BASE target="_self" />
</HEAD>

<f:view>
	<BODY onkeypress="return false;">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><h:inputHidden id="view"
				value="#{searchAdminOptionBackingBean.view}" /> <jsp:include
				page="../navigation/top_viewAllVersions.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="viewAllVersionsForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{searchAdminOptionBackingBean.validationMessages}"></w:message>
								</tr>
							</TBODY>
						</TABLE>
						<!-- Table containing Tabs -->
						<DIV id="panel2">
						<DIV id="panel2Header" class="tabTitleBar"
							style="position:relative; cursor:hand;">View All Versions</DIV>
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
											<TD align="left"><h:outputText value="Administration Name"></h:outputText></TD>
											<TD align="left"><h:outputText value="Term"></h:outputText></TD>
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
									style="height:470;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									rowClasses="dataTableEvenRow,dataTableOddRow"
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{searchAdminOptionBackingBean.viewAllList != null}"
									value="#{searchAdminOptionBackingBean.viewAllList}">
									<h:column>
										<h:inputHidden id="adminOptionId"
											value="#{singleValue.adminOptionId}"></h:inputHidden>
										<h:outputText id="adminOptionName"
											value="#{singleValue.adminName}"></h:outputText>
										<h:inputHidden id="adminName" value="#{singleValue.adminName}"></h:inputHidden>
										<h:graphicImage id="lockImage"
											url="../../images/lock_icon.jpg" alt="Locked"
											rendered="#{singleValue.state.lockedByUser}"></h:graphicImage>
									</h:column>
									<h:column>
										<h:outputText id="term" value="#{singleValue.termDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="qualifier"
											value="#{singleValue.qualifierId}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
										<h:inputHidden id="adminOptionVersion"
											value="#{singleValue.version}"></h:inputHidden>
									</h:column>
									<h:column>
										<h:outputText id="status" value="#{singleValue.status}"></h:outputText>
										<h:inputHidden id="adminOptionStatus"
											value="#{singleValue.status}"></h:inputHidden>
									</h:column>
									<h:column>
										<h:commandButton alt="View" title="View" id="viewButton"
											image="../../images/view.gif"
											rendered="#{singleValue.state.validForView}"
											onclick="performAction(1); return false;">
										</h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>

										<h:commandButton alt="Edit" title="Edit" id="basicEdit"
											image="../../images/edit.gif" value="Edit"
											rendered="#{singleValue.state.editableByUser}"
											onclick="performAction(2); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Schedule For Test" title="Schedule For Test" id="scheduleForTest"
											image="../../images/schedule_test.gif"
											value="scheduleForTest"
											onclick="performAction(4); return false;"
											rendered="#{singleValue.state.validForTest && singleValue.status != 'SCHEDULED_FOR_TEST'}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Approve" title="Approve" id="approve"
											image="../../images/approved.gif" value="approve"
											onclick="performAction(10);return false;"
											rendered="#{singleValue.state.validForApprovalCompletion}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Reject" title="Reject" id="reject"
											image="../../images/rejected.gif" value="reject"
											onclick="performAction(11);return false;"
											rendered="#{singleValue.state.validForApprovalCompletion}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Test Pass" title="Test Pass" id="testPass"
											image="../../images/test_successful.gif" value="testPass"
											onclick="performAction(5); return false;"
											rendered="#{singleValue.state.validForTestCompletion}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Test Fail" title="Test Fail" id="testFail"
											image="../../images/test_failed.gif" value="testFail"
											onclick="performAction(6); return false;"
											rendered="#{singleValue.state.validForTestCompletion}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Publish" title="Publish" id="publish"
											image="../../images/publish.gif" value="publish"
											onclick="performAction(7); return false;"
											rendered="#{singleValue.state.validForPublish}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Check Out" title="Check Out" id="basicCheckOut"
											image="../../images/checkOut.gif" value="Check Out"
											onclick="performAction(8); return false;"
											rendered="#{singleValue.state.validForCheckOut}">
										</h:commandButton>

										<h:commandButton alt="Delete" title="Delete" id="basicDelete"
											image="../../images/delete.gif" value="Delete"
											onclick="performAction(3); return false;"
											rendered="#{singleValue.state.validForDelete}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Delete All Versions" title="Delete All Versions" id="deleteAll"
											image="../../images/deleteAll.gif" value="Delete All"
											onclick="performAction(13); return false;"
											rendered="#{singleValue.state.validForDeleteAll}">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>

										<h:outputText value=" " id="a1spaceSpan09"
											rendered="#{singleValue.state.validForUnlock}"></h:outputText>
										<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
											image="../../images/lockgreen.gif" value="Unlock"
											onclick="performAction(12);return false;"
											rendered="#{singleValue.state.validForUnlock}">
										</h:commandButton>


									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>

						</TABLE>
						</DIV>
						</DIV>
						</TD>
					</TR>
				</TABLE>

				<h:inputHidden id="hiddenAdminOptionId"
					value="#{searchAdminOptionBackingBean.adminOptionId}"></h:inputHidden>
				<h:inputHidden id="hiddenAdminOptionName"
					value="#{searchAdminOptionBackingBean.adminName}"></h:inputHidden>
				<h:inputHidden id="hiddenAdminOptionVersion"
					value="#{searchAdminOptionBackingBean.version}"></h:inputHidden>

				<h:inputHidden id="pageFlag"
					value="#{searchAdminOptionBackingBean.lastPage}"></h:inputHidden>

				<h:commandLink id="deleteAdminOption"
					style="display:none; visibility: hidden;"
					action="#{searchAdminOptionBackingBean.deleteAdminOption}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="deleteAllAdminOption"
					style="display:none; visibility: hidden;"
					action="#{searchAdminOptionBackingBean.deleteAllVersions}">
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
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script>
	// Space for page realated scripts

	if(document.getElementById('viewAllVersionsForm:searchResultTable')!= null) {
		setColumnWidth('viewAllVersionsForm:searchResultTable', '30%:15%:15%:10%:15%:25%');
		setColumnWidth('resultHeaderTable', '30%:15%:15%:10%:15%:25%');	
	} else {
		var divObj = document.getElementById('resultHeaderDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}


	/**
	Method used to call the particular action, when a button is clicked.
	For each button, a particular actionType is passing.
	*/
	function performAction(actionType) {
		
		copyValuesToHidden();
		switch(actionType) {
			case 1:
				var url = '../adminoptions/adminOptionsView.jsp'+getUrl()+'?'+'temp='+Math.random()+'&adminkey='+document.getElementById('viewAllVersionsForm:hiddenAdminOptionId').value+'&&'+'adminname='+ escape(document.getElementById('viewAllVersionsForm:hiddenAdminOptionName').value)+'&&'+ 'version='+document.getElementById('viewAllVersionsForm:hiddenAdminOptionVersion').value;
				newWinForView=window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
				break;			
			case 2:
				editAction();
				break;
			case 3:
				deleteAction();
				break;		
			case 4:
				document.getElementById('viewAllVersionsForm:scheduleTestAdminOption').click();
				window.returnValue = "REFRESH";
				break;			
			case 5:
				document.getElementById('viewAllVersionsForm:testPassAdminOption').click();
				window.returnValue = "REFRESH";
				break;			
			case 6:
				document.getElementById('viewAllVersionsForm:testFailAdminOption').click();
				window.returnValue = "REFRESH";
				break;			
			case 7:
				document.getElementById('viewAllVersionsForm:publishAdminOption').click();
				window.returnValue = "REFRESH";
				break;
			case 8:
				checkOutAction();
				break;
			case 10:
				document.getElementById('viewAllVersionsForm:approveAdminOption').click();
				window.returnValue = "REFRESH";
				break;
			case 11:
				document.getElementById('viewAllVersionsForm:rejectAdminOption').click();
				window.returnValue = "REFRESH";
				break;
			case 12:
				document.getElementById('viewAllVersionsForm:unlockAdminOption').click();
				window.returnValue = "REFRESH";
				break;
			case 13:
				deleteAllAction();
				break;
				
		}	
	}

	//Method fot edit action
	function editAction(){
		var arg1 = document.getElementById('viewAllVersionsForm:hiddenAdminOptionId').value;
		var arg2 = document.getElementById('viewAllVersionsForm:hiddenAdminOptionName').value;
		var arg3 = document.getElementById('viewAllVersionsForm:hiddenAdminOptionVersion').value;
		window.returnValue = "EDIT"+"~"+arg1+"~"+arg2+"~"+arg3;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:adminIdToEditPage').value = arg1;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:adminNameToEditPage').value = arg2;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:adminVersionToEditPage').value = arg3;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:editAdminOption').click();
		window.close();
	}

	//Method for check out action
	function checkOutAction(){
		var arg1 = document.getElementById('viewAllVersionsForm:hiddenAdminOptionId').value;
		var arg2 = document.getElementById('viewAllVersionsForm:hiddenAdminOptionName').value;
		var arg3 = document.getElementById('viewAllVersionsForm:hiddenAdminOptionVersion').value;
		window.returnValue = "CHECKOUT"+"~"+arg1+"~"+arg2+"~"+arg3;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:hiddenAdminOptionId').value = arg1;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:hiddenAdminOptionName').value = arg2;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:hiddenAdminOptionVersion').value = arg3;
		window.dialogArguments.document.getElementById('AdminOptionSearchForm:checkOutAdminOption').click();
		window.close();
	}

	//Method for delete action
	function deleteAction(){
		var message = 'Are you sure to delete the selected Admin Option version?';
		if (confirm(message) ){
			document.getElementById('viewAllVersionsForm:deleteAdminOption').click();
			window.returnValue = "REFRESH";
			return true;
		} else {
			return false;
		}
	}


	//Method for delete all action
	function deleteAllAction(){
		var message = 'Are you sure to delete all the versions of the selected Admin Option?';
		if (confirm(message) ){
			document.getElementById('viewAllVersionsForm:deleteAllAdminOption').click();
			window.returnValue = "REFRESH";
			return true;
		} else {
			return false;
		}
	}

	//Method to copy values to hidden variables
	function copyValuesToHidden() {
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable', 'adminOptionId', 'viewAllVersionsForm:hiddenAdminOptionId');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable', 'adminName', 'viewAllVersionsForm:hiddenAdminOptionName');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable', 'adminOptionVersion', 'viewAllVersionsForm:hiddenAdminOptionVersion');
		document.getElementById('viewAllVersionsForm:pageFlag').value = "viewAllPage";
	}

</script>
</HTML>
