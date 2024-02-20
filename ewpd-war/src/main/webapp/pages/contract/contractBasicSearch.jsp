<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@page session="false"%>	
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

<TITLE>Contract Search</TITLE>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
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
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>

<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('ContractBasicSearchForm:searchButton');"
		onUnload="closeAction();">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>

			<td><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Contract Development >> Contract Maintain>> Locate Criteria ");
    %> <jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form id="ContractBasicSearchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>

							<td valign="top" class="ContentArea">
							<TABLE width="100%">
								<TR>
									<TD>
									<TABLE>
										<TBODY>
											<tr>
												<TD><w:message></w:message></TD>
											</tr>
										</TBODY>
									</TABLE>
									</TD>

								</TR>
							</TABLE>

							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<tr>
									<td width="200"></td>
									<td width="200"></td>
									<td width="100%"></td>
								</tr>
							</TABLE>
							<!-- End of Tab table -->
							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><BR>

							<!--	Start of Table for actual Data	-->
							<TABLE width="900" border="0" cellspacing="0" cellpadding="3">
								<TBODY>

									<TR>
										<TD width="146"><h:outputText value="Contract ID" /></TD>
										<TD width="100"><h:inputText styleClass="formInputField"
											id="txtContractId"
											value="#{contractSearchBackingBean.contractId}" tabindex="1"
											maxlength="4" /></TD>
									</TR>


									<TR>
										<TD width="146"><h:outputText value="Line Of Business" /></TD>
										<h:inputHidden id="txtlob"
											value="#{contractSearchBackingBean.lob}"></h:inputHidden>
										<TD width="100">
										<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('lobDiv'), document.getElementById('ContractBasicSearchForm:txtlob')); </SCRIPT>
										</TD>
										<TD width="50"><h:commandButton alt="Select" title="Select" id="lobButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'line of Business','lobDiv','ContractBasicSearchForm:txtlob',2,2); return false;"
											tabindex="2"></h:commandButton></TD>
									</TR>

									<TR>
										<TD width="146"><h:outputText value="Business Entity" /></TD>
										<h:inputHidden id="txtBusinessEntity"
											value="#{contractSearchBackingBean.businessEntity}"></h:inputHidden>
										<TD width="100">
										<DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('BusinessEntityDiv'), document.getElementById('ContractBasicSearchForm:txtBusinessEntity')); </SCRIPT>
										</TD>
										<TD width="50"><h:commandButton alt="Select" title="Select"
											id="BusinessEntityButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','ContractBasicSearchForm:txtBusinessEntity',2,2); return false;"
											tabindex="3"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="146"><h:outputText value="Business Group" /></TD>
										<h:inputHidden id="txtBusinessGroup"
											value="#{contractSearchBackingBean.groupName}"></h:inputHidden>
										<TD width="100">
										<DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('BusinessGroupDiv'), document.getElementById('ContractBasicSearchForm:txtBusinessGroup')); </SCRIPT>
										</TD>
										<TD width="50"><h:commandButton alt="Select" title="Select"
											id="BusinessGroupButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','BusinessGroupDiv','ContractBasicSearchForm:txtBusinessGroup',2,2); return false;"
											tabindex="4"></h:commandButton></TD>
									</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
									<TR>
										<TD width="146"><h:outputText value="Market Business Unit" /></TD>
										<h:inputHidden id="marketBusinessUnitHidden"
											value="#{contractSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
										<TD width="100">
										<DIV id="marketBusinessUnit" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('marketBusinessUnit'), document.getElementById('ContractBasicSearchForm:marketBusinessUnitHidden')); </SCRIPT>
										</TD>
										<TD width="50"><h:commandButton alt="Select" title="Select"
											id="MarketBusinessUnitButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnit','ContractBasicSearchForm:marketBusinessUnitHidden',2,2); return false;"
											tabindex="5"></h:commandButton></TD>
									</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->

									<TR>
										<TD width="146"><h:outputText value="Contract Type" /></TD>
										<TD width="100">
										<DIV id="contractTypeDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtContractType"
											value="#{contractSearchBackingBean.contractType}"></h:inputHidden>
										<SCRIPT> parseForDiv(document.getElementById('contractTypeDiv'), document.getElementById('ContractBasicSearchForm:txtContractType')); </SCRIPT>
										</TD>
										<TD width="50"><h:commandButton alt="Select" title="Select"
											id="ContractTypeButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../contractpopups/searchContractType.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Contract Type','contractTypeDiv','ContractBasicSearchForm:txtContractType',2,1); return false;"
											tabindex="5"></h:commandButton></TD>
									</TR>



									<TR>
										<TD width="146"><h:outputText value="Effective Date" /></TD>
										<TD width="100"><h:inputText styleClass="formInputField"
											id="StartDate_txt"
											value="#{contractSearchBackingBean.startDate}" tabindex="6"
											maxlength="10" /> <span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<td valign="top" width="50"><a href="#"
											onclick="cal1.select('ContractBasicSearchForm:StartDate_txt','Strt_date_icon','MM/dd/yyyy'); return false;"
											title="cal1.select(document.forms[0].ContractBasicSearchForm:StartDate_txt,'Strt_date_icon','MM/dd/yyyy'); return false;"
											name="Strt_date_icon" id="Strt_date_icon" tabindex="7"> <h:graphicImage
											style="border:0" value="../../images/cal.gif" alt="Cal">
										</h:graphicImage> </a></TD>
										<TD width="27"><h:outputText value="To" /></TD>
										<TD width="175"><h:inputText styleClass="formInputField"
											id="ExpiryDate_txt"
											value="#{contractSearchBackingBean.endDate}" tabindex="8"
											maxlength="10" /> <span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<td valign="top" width="231"><a href="#"
											onclick="cal1.select('ContractBasicSearchForm:ExpiryDate_txt','Exp_date_icon','MM/dd/yyyy'); return false;"
											title="cal1.select(document.forms[1].ContractBasicSearchForm:ExpiryDate_txt,'Exp_date_icon','MM/dd/yyyy'); return false;"
											name="Exp_date_icon" id="Exp_date_icon" tabindex="9"> <h:graphicImage
											style="border:0" value="../../images/cal.gif" alt="Cal">
										</h:graphicImage> </a></TD>
									</TR>
									<TR>
										<TD width="146"><h:commandButton id="searchButton"
											value="Locate" styleClass="wpdButton"
											action="#{contractSearchBackingBean.performLocate}"
											tabindex="10">
										</h:commandButton></TD>
										<TD width="100">&nbsp;</TD>
										<TD width="50">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							</DIV>

							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Search Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" style="width: 100%;">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="15%"><h:outputText
													value="ContractId"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText
													value="No of Date Segments"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText
													value="Line Of Business"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText
													value="Contract Type"></h:outputText></TD>
												<TD align="left" width="15%"><h:outputText
													value="Contract Status"></h:outputText></TD>
                                                <TD align="left" width="10%"><h:outputText
													value="Effective Date"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText
													value="Expiry Date"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:100%;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{contractSearchBackingBean.locateResultList != null}"
										value="#{contractSearchBackingBean.locateResultList}">
										<h:column>
											<h:outputText id="ContractId"
												value="#{singleValue.contractId}"></h:outputText><h:outputText id="ContractSpace"
												value="  "></h:outputText>
											<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" rendered ="#{singleValue.state.lockedByUser}"></h:graphicImage>
										</h:column>
										<h:column>

											<h:outputText id="DateSegment"
												value="#{singleValue.dateSegmentCount}"></h:outputText>
										</h:column>

										<h:column>
											<h:outputText id="Version" value="#{singleValue.version}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="lob" value="#{singleValue.lob}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="ContractType"
												value="#{singleValue.contractType}"></h:outputText>

										</h:column>

										<h:column>
											<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>

										</h:column>
                                        										<h:column>
											<h:outputText id="StartDate" value="#{singleValue.startDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="EndDate" value="#{singleValue.endDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											<h:inputHidden id="contractKey"
												value="#{singleValue.contractKey}"></h:inputHidden>
											<h:inputHidden id="contractIdHid"
												value="#{singleValue.contractId}">
											</h:inputHidden>
											<h:inputHidden id="versionHid" value="#{singleValue.version}">
											</h:inputHidden>
											<h:inputHidden id="statusHid" value="#{singleValue.status}">
											</h:inputHidden>
											<h:inputHidden id="contractDateSegmentSysHId"
												value="#{singleValue.dateSegmentId}" />
											<h:inputHidden id="contractTypeHId"
												value="#{singleValue.contractType}" />
											<h:inputHidden id="dateSegmentCountHId"
												value="#{singleValue.dateSegmentCount}" />
											<h:inputHidden id="productSysHId"
												value="#{singleValue.productSysId}" />
											<h:inputHidden id="dateSegmentType"
												value="#{singleValue.testIndicator}" />
											<h:inputHidden id="effectiveDate"
												value="#{singleValue.startDate}">
											</h:inputHidden>
										</h:column>
										<h:column>

											<h:outputText value=" " id="a1spaceSpan05" rendered="true"></h:outputText>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="showPopupForContract('../contract/contractBasicInfoView.jsp');return false;"></h:commandButton>

											<h:outputText value=" " id="a1spaceSpan04" rendered="true"></h:outputText>
											<h:commandButton alt="View All Versions" title="View All Versions" id="basicViewAll"
												image="../../images/notes.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="showPopupForAllVersions('../contract/viewContractVersions.jsp');return false;"></h:commandButton>

											<h:outputText value=" " id="a1spaceSpan03" rendered="true"></h:outputText>
											<h:commandButton alt="Copy" title="Copy" id="copy"
												image="../../images/copy.gif"
												rendered="#{singleValue.validForCopyHere}"
												onclick="copyAction('../contract/contractCopySubstitute.jsp');return false;"></h:commandButton>
											<h:outputText value=" " id="a2spaceSpan" rendered="true"></h:outputText>
											<h:commandButton alt="View Date Segment" title="View Date Segment" id="ds02"
												image="../../images/ds_view.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="showPopupForViewDS('../contract/viewDateSegments.jsp');return false;"></h:commandButton>
											<h:outputText value=" " id="a1spaceSpan01"
												rendered="#{singleValue.state.editableByUser}"></h:outputText>
											<h:commandButton alt="Edit" title="Edit" id="basicEdit"
												image="../../images/edit.gif" value="Edit"
												onclick=" editAction();return false;"
												rendered="#{singleValue.state.editableByUser}">
											</h:commandButton>

											<h:outputText value=" " id="a1spaceSpan"
												rendered="#{singleValue.validForDS && singleValue.state.validForCreate}"></h:outputText>
											<h:commandButton alt="Create Date Segment" title="Create Date Segment" id="ds01"
												rendered="#{singleValue.validForDS && singleValue.state.validForCreate && !(singleValue.state.lockedByUser)}"
												image="../../images/ds.gif"
												onclick="callDSCreate('../contractpopups/dateSegmentPopup.jsp');return false;"></h:commandButton>

											<h:outputText value=" " id="a3spaceSpan"
												rendered="#{singleValue.state.validForTest}"></h:outputText>
											<h:commandButton alt="SendToTest" title="SendToTest" id="sendToTestButton"
												image="../../images/schedule_test.gif"
												onclick="sendToTestAction();return false;"
												rendered="#{singleValue.state.validForTest}"></h:commandButton>
											<h:outputText value=" " id="a4spaceSpan"
												rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
											<h:commandButton alt="TestPass" title="TestPass" id="testPassButton"
												image="../../images/test_successful.gif"
												onclick="testPassAction();return false;"
												rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
											<h:outputText value=" " id="a5spaceSpan"
												rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
											<h:commandButton alt="TestFail" title="TestFail" id="testFailButton"
												image="../../images/test_failed.gif"
												onclick="testFailAction();return false;"
												rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
											<h:outputText value=" " id="a6spaceSpan"
												rendered="#{singleValue.state.validForApproval}"></h:outputText>
											<h:commandButton alt="Schedule To Approve" title="Schedule To Approve" id="approveButton"
												image="../../images/scheduled_approval.gif"
												onclick="scheduleToApproveAction();return false;"
												rendered="#{singleValue.validForApproval}"></h:commandButton>
											<h:outputText value=" " id="a7spaceSpan"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
											<h:commandButton alt="Approve" title="Approve" id="approvalButton"
												image="../../images/approved.gif"
												onclick="approveAction();return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
											<h:outputText value=" " id="a8spaceSpan"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
											<h:commandButton alt="Reject" title="Reject" id="rejectButton"
												image="../../images/rejected.gif"
												onclick="rejectAction();return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
											<h:outputText value=" " id="a9spaceSpan"
												rendered="#{singleValue.state.validForPublish}"></h:outputText>
											<h:commandButton alt="Publish" title="Publish" id="publishButton"
												image="../../images/publish.gif"
												onclick="publishAction();return false;"
												rendered="#{singleValue.state.validForPublish}"></h:commandButton>
											<h:outputText value=" " id="a10spaceSpan"
												rendered="#{singleValue.state.validForCheckOut}"></h:outputText>
											<h:commandButton alt="CheckOut" title="CheckOut" id="checkoutButton"
												image="../../images/checkOut.gif"
												onclick="checkOutProcess();return false;"
												rendered="#{singleValue.state.validForCheckOut}">
											</h:commandButton>
											<h:outputText value=" " id="a11spaceSpan" rendered="false"></h:outputText>
											<h:commandButton alt="Schedule To Production" title="Schedule To Production"
												id="scheduleToProductionButton"
												image="../../images/scheduled_production1.gif"
												onclick="scheduleToProduction();return false;"
												rendered="false"></h:commandButton>
											<h:outputText value=" " id="a1spaceSpan02"
												rendered="#{singleValue.state.validForDelete}"></h:outputText>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												onclick="deleteContractParent();return false;"
												rendered="#{singleValue.state.validForDelete}">
											</h:commandButton>
											<h:outputText value=" " id="a1spaceSpan09"
												rendered="#{singleValue.state.validForUnlock}"></h:outputText>
											<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="unlockContract();return false;"
												rendered="#{singleValue.state.validForUnlock}">
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
		<tr>
			<h:inputHidden id="copyLegacyNotesid"
				value="#{dateSegmentBackingBean.copyLegacyNotes}" />
			<h:inputHidden id="selectedDateSegDelOption"
				value="#{contractSearchBackingBean.selectedDateSegDelOptionFromSearch}" />
			<h:inputHidden id="selectedContract"
				value="#{contractSearchBackingBean.selectedContractIDFromSearch}" />
			<h:inputHidden id="selectedContractKey"
				value="#{contractSearchBackingBean.selectedContractKeyFromSearch}" />
			<h:inputHidden id="selectedContractType"
				value="#{contractSearchBackingBean.selectedContractTypeFromSearch}" />
			<h:inputHidden id="selectedContractDateSegSysId"
				value="#{contractSearchBackingBean.selectedDateSegKeyFromSearch}" />
			<h:inputHidden id="selectedVerion"
				value="#{contractSearchBackingBean.selectedVerionFromSearch}" />
			<h:inputHidden id="selectedStatus"
				value="#{contractSearchBackingBean.selectedStatusFromSearch}" />
			<h:inputHidden id="selectedContractKeyForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedContractKeyFromSearch}" />
			<h:inputHidden id="selectedContractIdForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedContractIdFromSearch}" />
			<h:inputHidden id="selectedVersionForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedVersionFromSearch}" />
			<h:inputHidden id="selectedStatusForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedStatusFromSearch}" />
			<h:inputHidden id="selectedDateSegmentIdForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedDateSegmentIdFromSearch}" />
			<h:inputHidden id="hiddenDateSegmentType"
				binding="#{contractBasicInfoBackingBean.hiddenDateSegmentType}" />
			<h:inputHidden id="hiddenEffectiveDate"
				value="#{contractBasicInfoBackingBean.selectedEffectiveDate}" />
			<h:inputHidden id="contractDateSegmentSysForDS"
				value="#{dateSegmentBackingBean.dateSegmentId}" />
			<h:inputHidden id="selectedContractId"
				value="#{contractBasicInfoBackingBean.selectedIdFromSearch}" />
			<h:inputHidden id="contractKeyForDS"
				value="#{dateSegmentBackingBean.selectedContractSysId}" />
			<h:inputHidden id="testDateSegments"
				value="#{contractSearchBackingBean.testDateSegment}" />
			<h:inputHidden id="productStatus"
				value="#{contractBasicInfoBackingBean.productStatus}" />
			<h:inputHidden id="noteStatus"
				value="#{contractBasicInfoBackingBean.noteStatus}" />

			<h:inputHidden id="productStatusForDS"
				value="#{dateSegmentBackingBean.productStatus}" />
			<h:inputHidden id="noteStatusForDS"
				value="#{dateSegmentBackingBean.noteStatus}" />


			<h:inputHidden id="contractIdForDS"
				value="#{dateSegmentBackingBean.contractIdDS}" />
			<h:inputHidden id="verionForDS"
				value="#{dateSegmentBackingBean.versionDS}" />
			<h:inputHidden id="statusForDS"
				value="#{dateSegmentBackingBean.statusDS}" />
			<h:inputHidden id="dateEntered"
				value="#{dateSegmentBackingBean.dateEntered}" />
			<h:inputHidden id="comments"
				value="#{dateSegmentBackingBean.comments}" />
			<h:inputHidden id="type" value="#{dateSegmentBackingBean.dsType}" />
			<h:inputHidden id="selectedProductSysIdForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedProductSysIdFromSearch}" />

			<h:inputHidden id="productSysIdForCheckOut"
				value="#{dateSegmentBackingBean.selectedProductSysIdFromSearch}" />
			<h:commandLink id="linkToBasicInfo"
				style="display:none; visibility: hidden;"
				action="#{dateSegmentBackingBean.createDateSegment}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="deleteLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.deleteAction}">
				<f:verbatim />
			</h:commandLink>


			<h:commandLink id="linkToEdit"
				style="display:none; visibility: hidden;"
				action="#{dateSegmentBackingBean.editAction}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="sendToTestLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.sendToTest}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="testPassLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.testPass}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="testFailLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.testFail}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="scheduleToApproveLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.scheduleToApprove}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="approveLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.approve}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="rejectLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.reject}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="publishLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.publish}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="checkOutLink"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.checkOut}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="scheduleToProductionLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.scheduleToProduction}">
				<f:verbatim />
			</h:commandLink>
				<h:commandLink id="unlockLink"
				style="display:none; visibility: hidden;"
				action="#{contractSearchBackingBean.unlockContract}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="copyLink"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.copyAction}">
				<f:verbatim />
			</h:commandLink>
			<h:commandLink id="copyHeadingsLink"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.copyHeadingsAction}">
				<f:verbatim />
			</h:commandLink>
			<td> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
		<form name="printForm"><input id="currentPrintPage" type="hidden"
			name="currentPrintPage" value="contractSearchResultPrint" />
	</table>
	</BODY>
	</h:form>

</f:view>
<script language="javascript">
copyHiddenToDiv_ewpd('ContractBasicSearchForm:txtlob','lobDiv',2,2);
copyHiddenToDiv_ewpd('ContractBasicSearchForm:txtBusinessEntity','BusinessEntityDiv',2,2);
copyHiddenToDiv_ewpd('ContractBasicSearchForm:txtBusinessGroup','BusinessGroupDiv',2,2);
copyHiddenToDiv_ewpd('ContractBasicSearchForm:marketBusinessUnitHidden','marketBusinessUnit',2,2);
	if(null != document.getElementById('ContractBasicSearchForm:searchResultTable:0:lob')){
	      formatTildaToCommaForContract('ContractBasicSearchForm:searchResultTable');
	}
	function formatTildaToCommaForContract(value){
	      var i;
	      var n;
	      n = document.getElementById(value).rows.length;
	      for(i=0; i < n; i++)
	            {
	                  formatTildaToComma('ContractBasicSearchForm:searchResultTable:'+i+':lob');
	            }
	}
	document.getElementById('ContractBasicSearchForm:txtContractId').focus();	

// for delete contract
/* function deleteContract(){

			var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
		var myObject = new Object();
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractTypeHId','ContractBasicSearchForm:selectedContractType');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
			//		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedContractDateSegSysId');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');


		var url = '../contractpopups/selectOneContractDateSegmentsPopup.jsp'+getUrl();
			url = url +'?status='	+document.getElementById('ContractBasicSearchForm:selectedStatus').value;
		var returnvalue = window.showModalDialog(url, myObject, "dialogHeight:150px;dialogWidth:350px;resizable=false;status=no;");	

			if(!(typeof(returnvalue) == "undefined" ||returnvalue ==''))
			{ 
				document.getElementById('ContractBasicSearchForm:selectedDateSegDelOption').value = returnvalue;
				submitDataTableButton('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedContractDateSegSysId','ContractBasicSearchForm:deleteLink');
			}

		}else{
				return false;
		}
} */

function unlockContract(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:unlockLink');
}

		
//Open a popup window to show contract.
function showPopupForContract(page){

		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractTypeHId','ContractBasicSearchForm:selectedContractType');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedContractDateSegSysId');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
	var url=page+getUrl()+"?contractID="+escape(document.getElementById('ContractBasicSearchForm:selectedContract').value)
				+'&contractSysId='+document.getElementById('ContractBasicSearchForm:selectedContractKey').value
				+'&contractDateSegmentSysId='+document.getElementById('ContractBasicSearchForm:selectedContractDateSegSysId').value
				+'&type='+document.getElementById('ContractBasicSearchForm:selectedContractType').value
				+'&status='	+document.getElementById('ContractBasicSearchForm:selectedStatus').value
				+'&version='+document.getElementById('ContractBasicSearchForm:selectedVerion').value
				+'&temp='+Math.random()
				+'&reloadTree=Y';	
				
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}


//Open a popup window to show all the version of the selected standard benefit.


function syncTables(){
			var relTblWidth = document.getElementById('ContractBasicSearchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}		

var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('ContractBasicSearchForm:searchResultTable') != null) {
			//tigra_tables('ContractBasicSearchForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)');
			
			setColumnWidth('ContractBasicSearchForm:searchResultTable','8.5%:8.5%:6%:8%:8%:20%:10%:9%:20%');
			setColumnWidth('resultHeaderTable','8.5%:8.5%:6%:8%:8%:20%:10%:9%:20%');	
			showResultsTab();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		

		if(document.getElementById('ContractBasicSearchForm:searchResultTable') != null){
			document.getElementById('ContractBasicSearchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}

	<%-- function showPopupForViewDS(page)
		{	
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:contractKeyForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:contractIdForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:verionForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:statusForDS');
		var val ="1";
		var url=page+getUrl()+"?contractKey="+document.getElementById('ContractBasicSearchForm:contractKeyForDS').value+'&contractId='+escape(document.getElementById('ContractBasicSearchForm:contractIdForDS').value)+'&version='+document.getElementById('ContractBasicSearchForm:verionForDS').value+'&status='+document.getElementById('ContractBasicSearchForm:statusForDS').value+'&pageId='+val+'&temp='+Math.random();
		
		
		var myObject = new Object();
		
	    myObject.dateEntered = document.getElementById('ContractBasicSearchForm:dateEntered').value;
	    myObject.comments = document.getElementById('ContractBasicSearchForm:comments').value;
		myObject.type = document.getElementById('ContractBasicSearchForm:type').value;
		// var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
		
		<%
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				 var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no;");
				<%
				}
				else {
				%>
				 var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no;");
				
				<%
				}
			%>
		
		if(returnvalue == '1'){
			document.getElementById('ContractBasicSearchForm:comments').value = myObject.comments;
			document.getElementById('ContractBasicSearchForm:dateEntered').value = myObject.dateEntered;
			document.getElementById('ContractBasicSearchForm:type').value = myObject.type;
			document.getElementById('ContractBasicSearchForm:copyLegacyNotesid').value = myObject.copylegacyNote; 
			
 			var status=document.getElementById('ContractBasicSearchForm:statusForDS').value;
			var i =1;
			if(( status != 'BUILDING')&&(status != 'CHECKED_OUT')){
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKeyForCheckOut');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedDateSegmentIdForCheckOut');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:selectedProductSysIdForCheckOut');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:productSysIdForCheckOut');
	
				var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
				var dateSegmentSysId = document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value;
				var prodid = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
				url = "../contractpopups/checkoutProductPopup.jsp'"+getUrl()+"'?contractKey="+contractSysId+'&dateId='+dateSegmentSysId+'&productid='+prodid+'&temp='+Math.random();
				var myObject = new Object();
				var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
				if(returnvalue == undefined){
					returnvalue=""+"~"+"";
					i =10;
					getObj('ContractBasicSearchForm:searchButton').click();
				}
				else{
					var array = returnvalue.split("~");
					var productStatus = array[0];
					var notesStatus = returnvalue;
					document.getElementById('ContractBasicSearchForm:productStatusForDS').value = productStatus;
					document.getElementById('ContractBasicSearchForm:noteStatusForDS').value = notesStatus;
				}
			}
			if(i != 10){
				submitLink('ContractBasicSearchForm:linkToBasicInfo')
			}
		}
		if(returnvalue =='2' ){
			document.getElementById('ContractBasicSearchForm:contractKeyForDS').value = myObject.contractKey;
			document.getElementById('ContractBasicSearchForm:contractIdForDS').value = myObject.contractId;
			document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value = myObject.dateSegementId;
			document.getElementById('ContractBasicSearchForm:verionForDS').value = myObject.version;
			document.getElementById('ContractBasicSearchForm:statusForDS').value = myObject.status;
			submitLink('ContractBasicSearchForm:linkToEdit');

		}
		
		if(returnvalue == '3'){
			document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value = myObject.contractKey;
			document.getElementById('ContractBasicSearchForm:selectedContractIdForCheckOut').value = myObject.contractId;
			document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value = myObject.dateSegementId;
			document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value = myObject.dateSegementId;
			document.getElementById('ContractBasicSearchForm:selectedVersionForCheckOut').value = myObject.version;
			document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value = myObject.status;
			document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value = myObject.productSysId;
			var dsType = myObject.dateSegmentType;
			var contractSysId1 = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
			var dateSegmentSysId1 = document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value;
			var prodid1 = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
			url = "../contractpopups/checkoutProductPopup.jsp"+getUrl()+"?contractKey="+contractSysId1+'&dateId='+dateSegmentSysId1+'&productid='+prodid1+'&dsType='+dsType+'&temp='+Math.random();
			var myObject1 = new Object();
			var returnvalue1=window.showModalDialog(url,myObject1,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
			if(returnvalue1 == undefined){
				returnvalue1=""+"~"+"";
				getObj('ContractBasicSearchForm:searchButton').click();
			}
			else{
				var array1 = returnvalue1.split("~");
				var productStatus1 = array1[0];
				var notesStatus1 = returnvalue1;
				document.getElementById('ContractBasicSearchForm:productStatus').value = productStatus1;
				document.getElementById('ContractBasicSearchForm:noteStatus').value = notesStatus1;
				submitLink('ContractBasicSearchForm:checkOutLink');
			}
		}
		
		if(returnvalue=='undefined'){
			getObj('ContractBasicSearchForm:searchButton').click();
		}
		
		if(returnvalue=='copy')
		submitLink('ContractBasicSearchForm:copyLink');
				
		if(returnvalue=='copyHeadings')
		submitLink('ContractBasicSearchForm:copyHeadingsLink');
	} --%>
		<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				function showPopupForAllVersions(page)
				<%
				}
				else {
				%>
				
				async function showPopupForAllVersions(page)
				<%
				}
			%>

		{	
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:contractKeyForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:contractIdForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:verionForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:statusForDS');
        var url=page+getUrl()+"?contractKey="+document.getElementById('ContractBasicSearchForm:contractKeyForDS').value+'&currentPage=1'+'&contractId='+escape(document.getElementById('ContractBasicSearchForm:contractIdForDS').value)+'&version='+document.getElementById('ContractBasicSearchForm:verionForDS').value+'&status='+document.getElementById('ContractBasicSearchForm:statusForDS').value+'&temp='+Math.random();
			    var myObject = new Object();
		
	    myObject.dateEntered = document.getElementById('ContractBasicSearchForm:dateEntered').value;
	    myObject.comments = document.getElementById('ContractBasicSearchForm:comments').value;
		myObject.type = document.getElementById('ContractBasicSearchForm:type').value;
		// var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no;");
		
		<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				 var  returnvalue=window.showModalDialog(url,myObject,"dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no;");
				<%
				}
				else {
				%>
				  //returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no;");
				 var  returnvalue =  await window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no;");
							 
				
				<%
				}
			%>

		var retValArr = returnvalue.split("|");
		
		if(retValArr[0] == '1'){
			document.getElementById('ContractBasicSearchForm:comments').value = retValArr[1];
			document.getElementById('ContractBasicSearchForm:dateEntered').value = retValArr[2];
			document.getElementById('ContractBasicSearchForm:type').value = retValArr[3];
			document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value = retValArr[10];
			document.getElementById('ContractBasicSearchForm:copyLegacyNotesid').value = retValArr[4]; 
 			var status=document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value;

			var i =1;
			if(( status != 'BUILDING')&&(status != 'CHECKED_OUT')){
		//		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKeyForCheckOut');
		//		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedDateSegmentIdForCheckOut');
		//		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:selectedProductSysIdForCheckOut');
		//		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:productSysIdForCheckOut');

				//setting values from the window object
				document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value = retValArr[6];
				document.getElementById('ContractBasicSearchForm:selectedContractIdForCheckOut').value = retValArr[7];
				document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value = retValArr[8];
				document.getElementById('ContractBasicSearchForm:selectedVersionForCheckOut').value = retValArr[9];
				document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value = retValArr[10];
				document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value = retValArr[11];
				
				var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
				var dateSegmentSysId = document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value;
				var prodid = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
				url = "../contractpopups/checkoutProductPopup.jsp"+getUrl()+"?contractKey="+contractSysId+'&dateId='+dateSegmentSysId+'&productid='+prodid+'&temp='+Math.random();
				var myObject = new Object();
				<%
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				var returnvalue= window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
				<%
				}
				else {
				%>
				var returnvalue= await window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
				<%
				}
				%>
				
				
				
				if(returnvalue == undefined){
					returnvalue=""+"~"+"";
 					i =  10;
					//getObj('ContractBasicSearchForm:searchButton').click();
				}
				else{
					var array = returnvalue.split("~");
					var productStatus = array[0];
					var notesStatus = returnvalue;
					document.getElementById('ContractBasicSearchForm:productStatusForDS').value = productStatus;
					document.getElementById('ContractBasicSearchForm:noteStatusForDS').value = notesStatus;
				}
			}
			if( i != 10){
				submitLink('ContractBasicSearchForm:linkToBasicInfo');
			}
		}
		if(retValArr[0] == '2'){
			
			document.getElementById('ContractBasicSearchForm:contractKeyForDS').value = myObject.contractKey;
			document.getElementById('ContractBasicSearchForm:contractIdForDS').value = myObject.contractId;
			document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value = myObject.dateSegementId;
			document.getElementById('ContractBasicSearchForm:verionForDS').value = myObject.version;
			document.getElementById('ContractBasicSearchForm:statusForDS').value = myObject.status;
			submitLink('ContractBasicSearchForm:linkToEdit');
		}
		if(retValArr[0] == '3'){
			
			document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value = retValArr[1];;
			document.getElementById('ContractBasicSearchForm:selectedContractIdForCheckOut').value = retValArr[2];
			document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value = retValArr[3];
			document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value = retValArr[3];
			document.getElementById('ContractBasicSearchForm:selectedVersionForCheckOut').value = retValArr[4];
			document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value = retValArr[5];
			document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value = retValArr[6];
			var dsType = retValArr[7];
			var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
			var dateSegmentSysId = document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value;
			var prodid = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
			url = "../contractpopups/checkoutProductPopup.jsp"+getUrl()+"?contractKey="+contractSysId+'&dateId='+dateSegmentSysId+'&productid='+prodid+'&dsType='+dsType+'&temp='+Math.random();
			var myObject = new Object();
			
			
			<%
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				var returnvalue= window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
			<%
				}
				else {
				%>
				var returnvalue= await window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
			<%
				}
				%>
			
			if(returnvalue == undefined){
				returnvalue=""+"~"+"";
				//getObj('ContractBasicSearchForm:searchButton').click();
			}
			else{
				var array = returnvalue.split("~");
				var productStatus = array[0];
				var notesStatus = returnvalue;
				document.getElementById('ContractBasicSearchForm:productStatus').value = productStatus;
				document.getElementById('ContractBasicSearchForm:noteStatus').value = notesStatus;
				submitLink('ContractBasicSearchForm:checkOutLink');
			}
		}
		if(returnvalue == 'showPopupForContract'){
			showPopupForContract('../contract/contractBasicInfoView.jsp');
		}
		if(returnvalue == undefined){
			//getObj('ContractBasicSearchForm:searchButton').click();
		}

		if(returnvalue=='copy')
		submitLink('ContractBasicSearchForm:copyLink');

		if(returnvalue=='copyHeadings')
		submitLink('ContractBasicSearchForm:copyHeadingsLink');
		
	}
	

	function editAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:contractKeyForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:contractDateSegmentSysForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:contractIdForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:verionForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:statusForDS');
		submitLink('ContractBasicSearchForm:linkToEdit');
	}

	function sendToTestAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKey').value;
		var url = '../contractpopups/selectTestDateSegmentsPopup.jsp'+getUrl()+'?contractkey='+contractSysId;
		var myObject = new Object();
		// commented since scheduling test date segments has been disabled
		// var returnvalue = window.showModalDialog(url, myObject, "dialogHeight:126px;dialogWidth:330px;resizable=false;status=no;");	
			var returnvalue ='';
		
				document.getElementById('ContractBasicSearchForm:testDateSegments').value = returnvalue;
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
				submitLink('ContractBasicSearchForm:sendToTestLink');
		
	}
	function testPassAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:testPassLink');
	}
	function testFailAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:testFailLink');
	}
	function scheduleToApproveAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:scheduleToApproveLink');
	}
	function approveAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:approveLink');
	}
	function rejectAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:rejectLink');
	}
	function publishAction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:publishLink');
	}

	function scheduleToProduction(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKey');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContract');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVerion');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatus');
		submitLink('ContractBasicSearchForm:scheduleToProductionLink');
	} 
	
	/* function checkOutProcess(){
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKeyForCheckOut');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedDateSegmentIdForCheckOut');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:selectedProductSysIdForCheckOut');
		var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
		var dateSegmentSysId = document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value;
		var prodid = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
		url = "../contractpopups/checkoutProductPopup.jsp"+getUrl()+"?contractKey="+contractSysId+'&dateId='+dateSegmentSysId+'&productid='+prodid+'&temp='+Math.random();
		var myObject = new Object();
		var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
		if(returnvalue == undefined ){
			
			returnvalue=""+"~"+"";
			getObj('ContractBasicSearchForm:searchButton').click();
		}
		else {
			var array = returnvalue.split("~");
			var productStatus = array[0];
			var notesStatus = returnvalue;
	    	document.getElementById('ContractBasicSearchForm:productStatus').value = productStatus;
    		document.getElementById('ContractBasicSearchForm:noteStatus').value = notesStatus;
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKeyForCheckOut');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContractIdForCheckOut');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVersionForCheckOut');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatusForCheckOut');
			getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedDateSegmentIdForCheckOut');
			submitLink('ContractBasicSearchForm:checkOutLink');
			} 
	} */

	/* function callDSCreate(page)
		{	
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:contractKeyForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:contractIdForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:verionForDS');
		getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:statusForDS');
		var url=page+getUrl()+"?contractKey="+document.getElementById('ContractBasicSearchForm:contractKeyForDS').value+'&contractId='+escape(document.getElementById('ContractBasicSearchForm:contractIdForDS').value)+'&version='+document.getElementById('ContractBasicSearchForm:verionForDS').value+'&status='+document.getElementById('ContractBasicSearchForm:statusForDS').value+'&temp='+Math.random();
		var myObject = new Object();
	    myObject.dateEntered = document.getElementById('ContractBasicSearchForm:dateEntered').value;
	    myObject.comments = document.getElementById('ContractBasicSearchForm:comments').value;
		myObject.type = document.getElementById('ContractBasicSearchForm:type').value;
		var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
		document.getElementById('ContractBasicSearchForm:comments').value = myObject.comments;
		document.getElementById('ContractBasicSearchForm:dateEntered').value = myObject.dateEntered;
        document.getElementById('ContractBasicSearchForm:type').value = myObject.type;
		document.getElementById('ContractBasicSearchForm:copyLegacyNotesid').value = myObject.copylegacyNote; 
		if(returnvalue == '1'){
            var status=document.getElementById('ContractBasicSearchForm:statusForDS').value;
		    var i =1;
			if(( status != 'BUILDING')&&(status != 'CHECKED_OUT')){
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKeyForCheckOut');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedDateSegmentIdForCheckOut');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:selectedProductSysIdForCheckOut');
				getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:productSysIdForCheckOut');
	
				var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
				var dateSegmentSysId = document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value;
				var prodid = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
				url = "../contractpopups/checkoutProductPopup.jsp"+getUrl()+"?contractKey="+contractSysId+'&dateId='+dateSegmentSysId+'&productid='+prodid+'&temp='+Math.random();
				var myObject = new Object();
				var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
				if(returnvalue == undefined){
					returnvalue=""+"~"+"";
					i =10;
					getObj('ContractBasicSearchForm:searchButton').click();
				}
				else{
					var array = returnvalue.split("~");
					var productStatus = array[0];
					var notesStatus = returnvalue;
					document.getElementById('ContractBasicSearchForm:productStatusForDS').value = productStatus;
					document.getElementById('ContractBasicSearchForm:noteStatusForDS').value = notesStatus;
				}
			}
			if(i != 10){ 
				submitLink('ContractBasicSearchForm:linkToBasicInfo')
			}
		}
		if(returnvalue == undefined){
			getObj('ContractBasicSearchForm:searchButton').click();
		}
	} */

	/* async function copyAction(page)
{	
var productStatus;
var notesStatus;	
//	alert(page);
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractKey','ContractBasicSearchForm:selectedContractKeyForCheckOut');
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractIdHid','ContractBasicSearchForm:selectedContractIdForCheckOut');
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','versionHid','ContractBasicSearchForm:selectedVersionForCheckOut');
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','statusHid','ContractBasicSearchForm:selectedStatusForCheckOut');
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','contractDateSegmentSysHId','ContractBasicSearchForm:selectedDateSegmentIdForCheckOut');
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','productSysHId','ContractBasicSearchForm:selectedProductSysIdForCheckOut');
getFromDataTableToHidden('ContractBasicSearchForm:searchResultTable','dateSegmentType','ContractBasicSearchForm:hiddenDateSegmentType');

//testing validations for copy
var contractSysId = document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value;
var dateSegmentSysId = document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value;
var prodid = document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value;
//alert("consysid"+contractSysId);
//alert("datesgmnt"+dateSegmentSysId);
//alert("prodid"+prodid);
//var url=page+"?dateSegmentId="+document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value+'&contractKey='+document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value+'&contractId='+document.getElementById('ContractBasicSearchForm:selectedContractIdForCheckOut').value+'&version='+document.getElementById('ContractBasicSearchForm:selectedVersionForCheckOut').value+'&status='+document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value+'&productId='+document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value+'&temp='+Math.random();
//alert(document.getElementById('ContractBasicSearchForm:hiddenDateSegmentType').value);
var ifclosed;
if(document.getElementById('ContractBasicSearchForm:hiddenDateSegmentType').value=='Y'){
	productStatus="";
    notesStatus="";
	
}
else{
var url1 = "../contractpopups/validateCopyPopup.jsp"+getUrl()+"?contractKey="+contractSysId+'&dateSegmentId='+dateSegmentSysId+'&productId='+prodid+'&temp='+Math.random();
var myObject = new Object();

var returnvalue1 = await window.showModalDialog(url1,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
//alert(returnvalue1);

if(returnvalue1 == undefined){
	returnvalue1=""+"~"+"";
	ifclosed = 'true';
}
var array = returnvalue1.split("~");
productStatus = array[0];
notesStatus = array[1];

}
if(ifclosed == 'true'){
	getObj('ContractBasicSearchForm:searchButton').click();
}		
else{
var url=page+getUrl()+"?dateSegmentId="+document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value+'&contractKey='+document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value+'&contractId='+escape(document.getElementById('ContractBasicSearchForm:selectedContractIdForCheckOut').value)+'&version='+document.getElementById('ContractBasicSearchForm:selectedVersionForCheckOut').value+'&status='+document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value+'&productId='+document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value+'&productStatus='+productStatus+'&noteStatus='+notesStatus+'&temp='+Math.random();
var returnvalue= await window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
//	alert('returnvalue'+returnvalue);
if(returnvalue=='copy')
submitLink('ContractBasicSearchForm:copyLink');
else if(returnvalue=='copyHeadings')
submitLink('ContractBasicSearchForm:copyHeadingsLink');
}

}


	
	
 */

</script>
</HTML>
