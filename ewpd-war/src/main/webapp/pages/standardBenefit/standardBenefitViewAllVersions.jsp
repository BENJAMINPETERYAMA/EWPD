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

<TITLE>Benefit View All Versions</TITLE>
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

			<h:inputHidden id="dummy"
				value="#{standardBenefitViewAllVersionBackingBean.hiddenVar}" />
			<tr>
				<td><jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include>
				</td>
			</tr>
			<tr>
				<td><h:form styleClass="form" id="viewAllVersionsForm">
						<table width="1000" border="0" cellspacing="0" cellpadding="0">
							<TR>
								<TD colspan="2" valign="top" class="ContentArea">
									<TABLE>
										<TBODY>
											<tr>
												<TD><w:message></w:message></TD>
											</tr>
										</TBODY>
									</TABLE> <!-- Table containing Tabs -->
									<DIV id="panel2">
										<DIV id="panel2Header" class="tabTitleBar"
											style="position: relative; cursor: hand;">View All
											Versions</DIV>
										<DIV id="panel2Content" class="tabContentBox"
											style="position: relative; font-size: 10px; width: 980; overflow-x: hidden; overflow-y: hidden;">
											<BR>
											<h:inputHidden id="viewStandardBenefitKey"
												value="#{standardBenefitViewAllVersionBackingBean.viewStandardBenefitKey}" />
											<TABLE id="newTable" cellpadding="0" cellspacing="0"
												border="0" width="100%">
												<TR>
													<TD>
														<DIV id="resultHeaderDiv" style="">
															<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
																id="resultHeaderTable" border="0" width="100%">
																<TBODY>
																	<TR class="dataTableColumnHeader">
																		<TD align="left"><h:outputText value="Name"></h:outputText></TD>
																		<TD align="left"><h:outputText value="Version"></h:outputText>
																		</TD>
																		<TD align="left"><h:outputText
																				value="Line Of Business"></h:outputText></TD>
																		<TD align="left"><h:outputText
																				value="Description"></h:outputText></TD>
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
															style="height: 252px; width =100%; overflow-y: scroll; overflow-x: hidden; position: relative; z-index: 1; font-size: 10px;">
															<!-- Search Result Data Table -->
															<h:dataTable
																rowClasses="dataTableEvenRow,dataTableOddRow"
																styleClass="outputText" headerClass="dataTableHeader"
																id="searchResultTable" var="singleValue" cellpadding="3"
																width="100%" cellspacing="1" bgcolor="#cccccc"
																rendered="#{standardBenefitViewAllVersionBackingBean.locateResultList != null}"
																value="#{standardBenefitViewAllVersionBackingBean.locateResultList}">
																<h:column>
																	<h:outputText id="Minorheading"
																		value="#{singleValue.benefitIdentifier}"></h:outputText>
																	<h:graphicImage id="lockImage"
																		url="../../images/lock_icon.jpg" alt="Locked"
																		rendered="#{singleValue.state.lockedByUser}">
																	</h:graphicImage>
																</h:column>
																<h:column>
																	<h:outputText id="Verisonnumber"
																		value="#{singleValue.version}"></h:outputText>
																</h:column>
																<h:column>
																	<h:outputText id="lobValue" value="#{singleValue.lob}"></h:outputText>
																</h:column>
																<h:column>
																	<h:outputText id="Updateddate"
																		value="#{singleValue.description}"></h:outputText>
																	<h:inputHidden id="standardBenefitKey"
																		value="#{singleValue.standardBenefitKey}"></h:inputHidden>
																	<h:inputHidden id="standardBenefitName"
																		value="#{singleValue.benefitIdentifier}"></h:inputHidden>
																</h:column>
																<h:column>
																	<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
																</h:column>
																<h:column>
																	<f:verbatim>  &nbsp; &nbsp;</f:verbatim>
																	<h:commandButton alt="View" title="View" id="basicView"
																		image="../../images/view.gif" value="View"
																		rendered="#{singleValue.state.validForView}"
																		onclick="viewAction(); return false;"></h:commandButton>
																	<f:verbatim> &nbsp; &nbsp;</f:verbatim>

																	<h:commandButton alt="Copy" title="Copy" id="basicCopy"
																		image="../../images/copy.gif" value="Copy"
																		rendered="#{singleValue.state.validForCopy}"
																		onclick="setValuesToSearch('COPY'); return false;"></h:commandButton>
																	<f:verbatim> &nbsp; &nbsp;</f:verbatim>

																	<h:commandButton alt="Delete" title="Delete"
																		id="basicDelete" image="../../images/delete.gif"
																		value="Delete"
																		onclick="confirmDeletion(); return false;"
																		rendered="#{singleValue.state.validForDelete}">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>


																	<h:commandButton alt="Edit" title="Edit" id="basicEdit"
																		image="../../images/edit.gif" value="Edit"
																		rendered="#{singleValue.state.editableByUser}"
																		onclick="setValuesToSearch('EDIT'); return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Unlock" title="Unlock"
																		id="unlockAdmin" image="../../images/lockgreen.gif"
																		value="Unlock"
																		onclick="setKeyForTest('UNLOCK_BENEFIT');return false;"
																		rendered="#{singleValue.state.validForUnlock}">
																		<f:verbatim> &nbsp; &nbsp; </f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Schedule For Test"
																		title="Schedule For Test" id="sendtotest"
																		image="../../images/schedule_test.gif"
																		value="Send To Test"
																		rendered="#{singleValue.state.validForTest}"
																		onclick="setKeyForTest('SCHEDULE_TEST');return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Approve" title="Approve"
																		id="approve" image="../../images/approved.gif"
																		value="Approve"
																		rendered="#{singleValue.state.validForApprovalCompletion}"
																		onclick="setKeyForTest('APPROVE');return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Reject" title="Reject"
																		id="rejectButton" image="../../images/rejected.gif"
																		onclick="setKeyForTest('REJECT');return false;"
																		rendered="#{singleValue.state.validForApprovalCompletion}">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>

																	<h:commandButton alt="Test Pass" title="Test Pass"
																		id="testPassButton"
																		rendered="#{singleValue.state.validForTestCompletion}"
																		image="../../images/test_successful.gif"
																		onclick="setKeyForTest('TEST_PASS');return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Test Fail" title="Test Fail"
																		id="testFailButton"
																		rendered="#{singleValue.state.validForTestCompletion}"
																		image="../../images/test_failed.gif"
																		onclick="setKeyForTest('TEST_FAIL');return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Publish" title="Publish"
																		id="publish" image="../../images/publish.gif"
																		value="Publish"
																		rendered="#{singleValue.state.validForPublish}"
																		onclick="setKeyForTest('PUBLISH');return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>
																	<h:commandButton alt="Checkout" title="Checkout"
																		id="Checkout" image="../../images/checkOut.gif"
																		value="Checkout"
																		rendered="#{singleValue.state.validForCheckOut}"
																		onclick="setValuesToSearch('CHECKOUT');return false;">
																		<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																	</h:commandButton>

																</h:column>
															</h:dataTable>
														</DIV>
													</TD>
												</TR>
												<tr>
													<TD><h:inputHidden id="selectedStandardBenefitKey"
															value="#{standardBenefitBackingBean.selectedStandardBenefitKey}" />
														<h:inputHidden id="benefitkey"
															value="#{standardBenefitBackingBean.selectedStandardBenefitKey}" />
														<h:inputHidden id="benfitNm"
															value="#{standardBenefitBackingBean.selectedStandardBenefitName}" />
														<h:inputHidden id="selectedStandardBenefitKeyForDelete"
															value="#{StandardBenefitSearchBackingBean.selectedStandardBenefitKey}" />
														<h:inputHidden id="selectedStandardBenefitNameForDelete"
															value="#{StandardBenefitSearchBackingBean.selectedStandardBenefitName}" />
														<h:inputHidden id="actionForTest"
															value="#{StandardBenefitSearchBackingBean.actionForTest}" />
														<h:commandLink id="editButton"
															action="#{standardBenefitBackingBean.loadStandardBenefitForEdit}"
															style="hidden">
															<f:verbatim> &nbsp;&nbsp;</f:verbatim>
														</h:commandLink> <h:commandLink id="deleteStandardBenefit"
															action="#{StandardBenefitSearchBackingBean.deleteStandardBenefitFromViewAllVersions}">
															<f:verbatim> &nbsp;&nbsp;</f:verbatim>
														</h:commandLink> <h:commandLink id="copyButton1"
															action="#{standardBenefitBackingBean.loadStandardBenefitForCopy}"
															style="hidden">
															<f:verbatim> &nbsp;&nbsp;</f:verbatim>
														</h:commandLink> <h:commandLink id="lifeCycleVersionsLink"
															action="#{StandardBenefitSearchBackingBean.lifeCycleFromVersions}">
															<f:verbatim></f:verbatim>
														</h:commandLink></TD>
												</tr>
											</TABLE>

										</DIV>
									</DIV>
								</TD>
							</TR>
						</table>
					</h:form></td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom.jsp"%></td>
			</tr>
		</table>
	</BODY>
</f:view>
<script>
	if (null != document
			.getElementById('viewAllVersionsForm:searchResultTable:0:lobValue')) {
		formatTildaToCommaForBenefit('viewAllVersionsForm:searchResultTable');
	}
	function formatTildaToCommaForBenefit(value) {
		var i;
		var n;
		n = document.getElementById(value).rows.length;
		for (i = 0; i < n; i++) {
			formatTildaToComma('viewAllVersionsForm:searchResultTable:' + i
					+ ':lobValue');
		}
	}
	// Space for page realated scripts
	if (document.getElementById('viewAllVersionsForm:searchResultTable') != null) {
		//setColumnWidth('viewAllVersionsForm:searchResultTable', '27%:7%:6%:24%:15%:21%');
		//		setColumnWidth('resultHeaderTable','27%:7%:6%:24%:15%:21%');
		var relTblWidth = document
				.getElementById('viewAllVersionsForm:searchResultTable').offsetWidth;
		if (document.getElementById('viewAllVersionsForm:searchResultTable').rows.length < 11) {
			//document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
			document.getElementById('resultHeaderDiv').style.width = relTblWidth
					+ "px";
			setColumnWidth('viewAllVersionsForm:searchResultTable',
					'27%:6%:7%:22%:16%:22%');
			setColumnWidth('resultHeaderTable', '27%:6%:7%:22%:16%:22%');
		} else {
			//document.getElementById('searchResultdataTableDiv').width = relTblWidth-17+"px";
			//document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
			document.getElementById('resultHeaderDiv').style.width = relTblWidth
					+ "px";
			setColumnWidth('viewAllVersionsForm:searchResultTable',
					'27%:6%:7%:22%:16%:22%');
			setColumnWidth('resultHeaderTable', '27%:6%:7%:22%:16%:22%');

		}

		tableObj = document
				.getElementById('viewAllVersionsForm:searchResultTable');
		if (tableObj == null) {
			// alert(tableObj.rows.length + 'tableObj.rows.length');
			window.close();
		}
	}

	function confirmDeletion() {
		var message = "Are you sure you want to delete?"
		if (window.confirm(message)) {
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
					'standardBenefitName',
					'viewAllVersionsForm:selectedStandardBenefitNameForDelete');
			setValueToHiddenFieldFromDataTable(
					'viewAllVersionsForm:searchResultTable',
					'standardBenefitKey',
					'viewAllVersionsForm:selectedStandardBenefitKeyForDelete',
					'viewAllVersionsForm:deleteStandardBenefit');
			window.returnValue = 'refresh';

			// window.close();					
			return true;
		} else {
			return false;
		}
	}

	function setKeyForTest(value) {
		window.returnValue = "refresh";
		document.getElementById('viewAllVersionsForm:actionForTest').value = value;
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
				'standardBenefitKey',
				'viewAllVersionsForm:selectedStandardBenefitKeyForDelete');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
				'standardBenefitName',
				'viewAllVersionsForm:selectedStandardBenefitNameForDelete');
		document.getElementById('viewAllVersionsForm:lifeCycleVersionsLink')
				.click();
	}

	function viewAction() {
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
				'standardBenefitKey',
				'viewAllVersionsForm:selectedStandardBenefitKeyForDelete');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
				'standardBenefitName',
				'viewAllVersionsForm:selectedStandardBenefitNameForDelete');

		window
				.showModalDialog(
						'../standardBenefit/standardBenefitView.jsp'
								+ getUrl()
								+ '?benefitkey='
								+ document
										.getElementById('viewAllVersionsForm:selectedStandardBenefitKeyForDelete').value
								+ '&temp=' + Math.random(),
						'ViewStandardBenefit',
						'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
	}

	function setValuesToSearch(value) {
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
				'standardBenefitKey',
				'viewAllVersionsForm:selectedStandardBenefitKeyForDelete');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable',
				'standardBenefitName',
				'viewAllVersionsForm:selectedStandardBenefitNameForDelete');
		idToSearchPage = document
				.getElementById('viewAllVersionsForm:selectedStandardBenefitKeyForDelete').value;
		nameToSearchPage = document
				.getElementById('viewAllVersionsForm:selectedStandardBenefitNameForDelete').value;
		window.returnValue = value + "~" + idToSearchPage + "~"+ nameToSearchPage;
		window.dialogArguments.document.getElementById('StandarsBenefitsSearchForm:actionForTest').value = value;
		window.dialogArguments.document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitKey').value = idToSearchPage;
		window.dialogArguments.document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitName1').value = nameToSearchPage;
		window.dialogArguments.document.getElementById('StandarsBenefitsSearchForm:actionLink').click();
		window.close();
	}
</script>
</HTML>
