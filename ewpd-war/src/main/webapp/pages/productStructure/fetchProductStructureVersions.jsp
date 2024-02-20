<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<TITLE>Product Structure All Versions</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<script language="JavaScript" src="../../js/prototype.js"></script>
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

<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<BASE target="_self" />
</HEAD>

<f:view>
	<BODY onkeypress="return false;">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<h:inputHidden id="dummy"
				value="#{productStructureSearchBackingBean.breadCrumbViewAllVersions}" />
			<TR>

				<TD><jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include></TD>
			</TR>
			<tr>
				<td><h:form id="searchForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">

						<TR>
							<TD colspan="2" valign="top" align="left" class="ContentArea"
								width="100%">
							<TABLE width="100%">
								<TR>
									<TD><w:message
										value="#{productStructureSearchBackingBean.validationMessage}"></w:message></TD>
								</TR>
							</TABLE>

							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="margin:8px;"><B>All Versions</B></DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:1300;height:510"><h:inputHidden
								id="fetchAllVersions"
								value="#{productStructureSearchBackingBean.fetchAllVersions}" />
							<TABLE id="newTable" align="left" cellpadding="0" cellspacing="0" width="1300"
								border="0">
								<h:outputText value="No More Versions"
									rendered="#{productStructureSearchBackingBean.versionList == null}"
									styleClass="dataTableColumnHeader">
								</h:outputText>
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="1300">
										<TBODY>
											<TR bgcolor="#ffffff">
												<TD align="left"><strong><h:outputText value="Name"></h:outputText></strong></TD>
												<TD align="left"><strong><h:outputText value="Version"></h:outputText></strong></TD>
												<TD align="left"><strong><h:outputText value="Line of Business"></h:outputText></strong></TD>
												<TD align="left"><strong><h:outputText value="Description"></h:outputText></strong></TD>
												<TD align="left"><strong><h:outputText value="Status"></h:outputText></strong></TD>
												<TD align="left"><strong><h:outputText
													value="Effective Date"></h:outputText></strong></TD>
												<TD align="left"><strong><h:outputText value="Expiry Date"></h:outputText></strong></TD>
												<TD align="left">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:1300;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{productStructureSearchBackingBean.versionList != null}"
										value="#{productStructureSearchBackingBean.versionList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="1300">

										<h:column>
											<h:outputText id="productStructureName"
												value="#{singleValue.productStructureName}">
											</h:outputText>
											<h:outputText value="  " id="lockSpace"
												rendered="#{singleValue.state.lockedByUser}"></h:outputText>
											<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   
												alt="Locked" rendered ="#{singleValue.state.lockedByUser}">
											</h:graphicImage>
											<h:inputHidden id="hidden_productStructureName"
												value="#{singleValue.productStructureName}"></h:inputHidden>
											<h:inputHidden id="hidden_productStructureId"
												value="#{singleValue.productStructureId}"></h:inputHidden>

										</h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>

										<h:column>
											<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
											<h:inputHidden id="hidden_version"
												value="#{singleValue.version}"></h:inputHidden>
										</h:column>

										<h:column>
											<h:outputText id="Line_Of_Buisness" value="#{singleValue.lineOfBusiness}"></h:outputText>
											<h:inputHidden id="hidden_lob"
												value="#{singleValue.lineOfBusiness}"></h:inputHidden>
										</h:column>

										<h:column>
											<h:outputText id="description" value="#{singleValue.description}"></h:outputText>
											<h:inputHidden id="hidden_description"
												value="#{singleValue.description}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="status" value="#{singleValue.status}"></h:outputText>
											<h:inputHidden id="hidden_status"
												value="#{singleValue.status}"></h:inputHidden>
										</h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>

										<h:column>
											<h:outputText id="effectiveDate"
												value="#{singleValue.effectiveDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											<h:inputHidden id="hidden_effectiveDate"
												value="#{singleValue.effectiveDate}"></h:inputHidden>
										</h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>

										<h:column>
											<h:outputText id="expiryDate"
												value="#{singleValue.expiryDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											<h:inputHidden id="hidden_expiryDate"
												value="#{singleValue.expiryDate}"></h:inputHidden>
										</h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>

										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="setKeyForView(); return false;"></h:commandButton>
											<f:verbatim>&nbsp; &nbsp;</f:verbatim>
											<h:commandButton alt="Copy" title="Copy" id="copyButton"
												image="../../images/copy.gif"
												rendered="#{singleValue.state.validForCopy}"
												onclick="setActionToSearchPage('copy'); return false;"></h:commandButton>
											<h:outputText value=" " id="a1spaceSpan"
												rendered="#{singleValue.state.validForDelete}"></h:outputText>
											<h:commandButton alt="Delete" title="Delete" id="deleteButton"
												rendered="#{singleValue.state.validForDelete}"
												image="../../images/delete.gif"
												onclick="deleteConfirm(); return false;">
											</h:commandButton>
											<h:outputText value="  " id="a2spaceSpan"
												rendered="#{singleValue.state.editableByUser}"></h:outputText>
											<h:commandButton alt="Edit" title="Edit" id="editButton"
												rendered="#{singleValue.state.editableByUser}"
												image="../../images/edit.gif"
												onclick="setActionToSearchPage('edit');return false;"></h:commandButton>
											<h:outputText value="  " id="a3spaceSpan"
												rendered="#{singleValue.state.validForTest}"></h:outputText>
											<h:commandButton alt="Schedule For Test" title="Schedule For Test"
												id="scheduleToTestButton"
												rendered="#{singleValue.state.validForTest}"
												image="../../images/schedule_test.gif"
												onclick="setKeyForTest('SCHEDULE_TEST');return false;">
											</h:commandButton>
											<h:outputText value="  " id="a5spaceSpan"
												rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
											<h:commandButton alt="Test Pass" title="Test Pass" id="testPassButton"
												rendered="#{singleValue.state.validForTestCompletion}"
												image="../../images/test_successful.gif"
												onclick="setKeyForTest('TEST_PASS');return false;">
											</h:commandButton>
											<h:outputText value="  " id="a6spaceSpan"
												rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
											<h:commandButton alt="Test Fail" title="Test Fail" id="testFailButton"
												rendered="#{singleValue.state.validForTestCompletion}"
												image="../../images/test_failed.gif"
												onclick="setKeyForTest('TEST_FAIL');return false;">
											</h:commandButton>
											<h:outputText value="  " id="a7spaceSpan"
												rendered="#{singleValue.state.validForPublish}"></h:outputText>
											<h:commandButton alt="Approve" title="Approve" id="approvalButton"
												image="../../images/approved.gif"
												onclick="setKeyForTest('APPROVE');return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
											<h:outputText value="" id="a22spaceSpan"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
											<h:commandButton alt="Reject" title="Reject" id="rejectButton"
												image="../../images/rejected.gif"
												onclick="setKeyForTest('REJECT');return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
											<h:outputText value="  " id="a25spaceSpan"
												rendered="#{singleValue.state.validForPublish}"></h:outputText>

											<h:commandButton alt="Publish" title="Publish" id="publishButton"
												rendered="#{singleValue.state.validForPublish}"
												image="../../images/publish.gif"
												onclick="setKeyForTest('PUBLISH');return false;">
											</h:commandButton>
											<h:outputText value="  " id="a8spaceSpan"
												rendered="#{singleValue.state.validForCheckOut}"></h:outputText>
											<h:commandButton alt="Checkout" title="Checkout" id="checkoutButton"
												rendered="#{singleValue.state.validForCheckOut}"
												image="../../images/checkOut.gif"
												onclick="setActionToSearchPage('checkout');return false;">
											</h:commandButton>
											<h:outputText value="  " id="spaceSpan"
												rendered="#{singleValue.state.validForUnlock}"></h:outputText>
											<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="setKeyForTest('UNLOCK');return false;"
												rendered="#{singleValue.state.validForUnlock}">
												<f:verbatim> &nbsp; &nbsp; </f:verbatim>
											</h:commandButton>
										</h:column>
									</h:dataTable></DIV>

									</TD>
								</TR>
								<TR>
									<TD><h:inputHidden id="selectedStructureId"
										value="#{productStructureSearchBackingBean.selectedStructureId}" /></TD>
									<TD><h:inputHidden id="productStructureId"
										value="#{productStructureSearchBackingBean.productStructureId}" /></TD>
									<TD><h:inputHidden id="productStructureIdForEdit"
										value="#{productStructureGeneralInfoBackingBean.productStructureIdForEdit}" /></TD>
									<TD><h:inputHidden id="actionForTest"
										value="#{productStructureSearchBackingBean.actionForTest}" /></TD>
								</TR>
							</TABLE>
							</DIV>
							</TD>

						</TR>
					</TABLE>
					<h:commandLink id="deleteLink"
						action="#{productStructureSearchBackingBean.deleteProductStructureVersion}">
						<f:verbatim></f:verbatim>
					</h:commandLink>
					<h:commandLink id="viewLink"
						action="#{productStructureSearchBackingBean.previousVersionView}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="toCreatePage"
						action="#{productStructureSearchBackingBean.copyProductStructure}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="checkoutLink"
						action="#{productStructureGeneralInfoBackingBean.checkoutProductStructure}">
						<f:verbatim></f:verbatim>
					</h:commandLink>
					<h:commandLink id="scheduleTestLink"
						action="#{productStructureSearchBackingBean.scheduleForTestView}">
						<f:verbatim></f:verbatim>
					</h:commandLink>

				</h:form></td>
			</tr>
			<TR>
				<TD><%@ include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
		var newWinForView;
var tableObj = document.getElementById('searchForm:searchResultTable');
if(tableObj == null)
window.close();	
		if(document.getElementById('searchForm:searchResultTable') != null) {	
		var relTblWidth = document.getElementById('newTable').offsetWidth;	
		setColumnWidth('resultHeaderTable','19%:6%:7%:19%:13%:9%:9%:19%');
		setColumnWidth('searchForm:searchResultTable','19%:6%:7%:19%:13%:9%:9%:19%');
		if(document.getElementById('searchForm:searchResultTable').rows.length < 11){
		document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
		document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
		setColumnWidth('resultHeaderTable','19%:6%:7%:19%:13%:9%:9%:19%');
		setColumnWidth('searchForm:searchResultTable','19%:6%:7%:19%:13%:9%:9%:19%');
		}else{
			document.getElementById('resultHeaderDiv').style.width = relTblWidth-17+"px";
		setColumnWidth('resultHeaderTable','19%:6%:7%:19%:13%:9%:9%:19%');
		setColumnWidth('searchForm:searchResultTable','19%:6%:7%:19%:13%:9%:9%:19%');
		}
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		if(document.getElementById('searchForm:searchResultTable')!= null){
		document.getElementById('searchForm:searchResultTable').onresize = syncTables;
		}
		function syncTables(){
			var relTblWidth = document.getElementById('searchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}

		function deleteConfirm(){
			var message = 'Are you sure you want to delete the selected product structure version?';
				if (confirm(message) ){
					setValueToHiddenFieldFromDataTable('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureId', 'searchForm:deleteLink');
					window.returnValue = "refresh";
					return true;
				} else
				return false;
			}

		function setKeyForView() {
			var currentDate = new Date();
			var currentTime = currentDate.getTime();
			getFromDataTableToHidden('searchForm:searchResultTable','hidden_productStructureId','searchForm:selectedStructureId');
			var url = "../productStructure/viewProductStructureGeneralInformation.jsp"+getUrl()+"?"+"id="+document.getElementById('searchForm:selectedStructureId').value+'&date='+currentTime+'&reloadTree=Y';
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=false;status=no;");	
		}			

		function setActionToSearchPage(action) {
			getFromDataTableToHidden('searchForm:searchResultTable','hidden_productStructureId','searchForm:selectedStructureId');
			idToSearchPage = document.getElementById('searchForm:selectedStructureId').value;
			window.returnValue = action+"~"+idToSearchPage;
			window.dialogArguments.document.getElementById('searchForm:actionStringFromVersion').value = action;
			window.dialogArguments.document.getElementById('searchForm:productStructureIdForEdit').value = idToSearchPage;
			window.dialogArguments.document.getElementById('searchForm:actionLink').click();
			window.close();
		}
		
		function setKeyForTest(value) {
		window.returnValue = "refresh";
		document.getElementById('searchForm:actionForTest').value = value;
		getFromDataTableToHidden('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureId');
		document.getElementById( 'searchForm:scheduleTestLink').click();
		}

</script>
</HTML>
