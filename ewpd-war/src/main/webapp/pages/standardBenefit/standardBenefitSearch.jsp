<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Search Benefit</TITLE>
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
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('StandarsBenefitsSearchForm:searchButton');">
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy"
				value="#{StandardBenefitSearchBackingBean.name}">
			</h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form id="StandarsBenefitsSearchForm">
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
							<table width="400" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<tr>
									<td width="200"></td>
									<td width="200"></td>
								</TR>
							</table>
							<!-- End of Tab table -->
							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;width=100%;" ><BR>

							<!--	Start of Table for actual Data	-->
							<TABLE width="47%" border="0" cellspacing="0" cellpadding="3">
								<TBODY>

									<TR>
										<TD width="15%"><h:outputText value="Line of Business" /></TD>
										<TD width="15%">
										<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtLob"
											value="#{StandardBenefitSearchBackingBean.lob}"></h:inputHidden>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select" id="lobButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','StandarsBenefitsSearchForm:txtLob',2,2); return false;"
											tabindex="1"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="15%"><h:outputText value="Business Entity" /></TD>
										<TD width="15%">
										<DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtBusinessEntity"
											value="#{StandardBenefitSearchBackingBean.businessEntity}"></h:inputHidden>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select"
											id="BusinessEntityButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','StandarsBenefitsSearchForm:txtBusinessEntity',2,2); return false;"
											tabindex="2"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="15%"><h:outputText value="Business Group" /></TD>
										<TD width="15%">
										<DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtBusinessGroup"
											value="#{StandardBenefitSearchBackingBean.businessGroup}"></h:inputHidden>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select"
											id="BusinessGroupButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','BusinessGroupDiv','StandarsBenefitsSearchForm:txtBusinessGroup',2,2); return false;"
											tabindex="3"></h:commandButton></TD>
									</TR>
<!--  -------------------------------------------------------------------------------- -->
									<TR>
										<TD width="15%"><h:outputText value="Market Business Unit" /></TD>
										<TD width="15%">
										<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtMarketBusinessUnit"
											value="#{StandardBenefitSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select"
											id="MarketBusinessUnitButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','StandarsBenefitsSearchForm:txtMarketBusinessUnit',2,2);return false;"
											tabindex="3"></h:commandButton></TD>
									</TR>
<!--  -------------------------------------------------------------------------------- -->
									<TR>
										<TD width="15%"><h:outputText value="Name" /></TD>
										<TD width="15%"><h:inputText styleClass="formInputField"
											id="txtMinorHeading"
											value="#{StandardBenefitSearchBackingBean.name}"
											maxlength="34" tabindex="4" /></TD>
										<TD width="5%"></TD>
									</TR>

									<TR>
										<TD width="15%"><h:outputText value="Term" /></TD>
										<h:inputHidden id="txtTerm"
											value="#{StandardBenefitSearchBackingBean.term}"></h:inputHidden>
										<TD width="15%">
										<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('TermDiv'), document.getElementById('StandarsBenefitsSearchForm:txtTerm')); </SCRIPT>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select" id="TermButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/benefitTermPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'term','TermDiv','StandarsBenefitsSearchForm:txtTerm',2,2); return false;"
											tabindex="5"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="15%"><h:outputText value="Qualifier" /></TD>
										<h:inputHidden id="txtQualifier"
											value="#{StandardBenefitSearchBackingBean.qualifier}"></h:inputHidden>
										<TD width="15%">
										<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('QualifierDiv'), document.getElementById('StandarsBenefitsSearchForm:txtQualifier')); </SCRIPT>

										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select"
											id="QualifierButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/searchPopUpMultiSelect.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'qualifier','QualifierDiv','StandarsBenefitsSearchForm:txtQualifier',2,2); return false;"											tabindex="6"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="15%"><h:outputText value="Provider Arrangement" />
										</TD>
										<h:inputHidden id="txtProviderArrangement"
											value="#{StandardBenefitSearchBackingBean.providerArrangement}"></h:inputHidden>
										<TD width="15%">
										<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('ProviderArrangementDiv'), document.getElementById('StandarsBenefitsSearchForm:txtProviderArrangement')); </SCRIPT>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select"
											id="ProviderArrangementButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/providerArrangementPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'provider arrangement','ProviderArrangementDiv','StandarsBenefitsSearchForm:txtProviderArrangement',2,2); return false;"
											tabindex="7"></h:commandButton></TD>
									</TR>
									<TR>
										<TD width="15%"><h:outputText value="Data Type" /></TD>
										<h:inputHidden id="txtDataType"
											value="#{StandardBenefitSearchBackingBean.dataType}"></h:inputHidden>

										<TD width="15%">
										<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('DataTypeDiv'), document.getElementById('StandarsBenefitsSearchForm:txtDataType')); </SCRIPT>
										</TD>
										<TD width="5%"><h:commandButton alt="Select" title="Select"
											id="DataTypeButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/benefitDataTypePopup.jsp'+getUrl(),'DataTypeDiv','StandarsBenefitsSearchForm:txtDataType',2,1); return false;"
											tabindex="8"></h:commandButton></TD>
									</TR>

									<TR>
										<TD width="15%"><h:outputText value="Benefit Type" /></TD>
										<h:inputHidden id="txtbenefitType"
											value="#{StandardBenefitSearchBackingBean.benefitType}"></h:inputHidden>
										<TD width="15%"><h:selectOneMenu id="dropBenefitType"
											styleClass="formInputField" tabindex="9"
											value="#{StandardBenefitSearchBackingBean.benefitType}">
											<f:selectItems id="benefitTypeList"
												value="#{ReferenceDataBackingBeanCommon.entityTypeListForBenefitCombo}" />

										</h:selectOneMenu> <!--  <DIV id="BenefitTypeDiv" class="selectDataDisplayDiv"></DIV>
										<SCRIPT> parseForDiv(document.getElementById('BenefitTypeDiv'), document.getElementById('StandarsBenefitsSearchForm:txtbenefitType')); </SCRIPT>-->
										</TD>
										<!--<TD width="5%"><h:commandButton alt="Select"
											id="BenefitTypeButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/benefitTypePopup.jsp','BenefitTypeDiv','StandarsBenefitsSearchForm:txtbenefitType',2,1); return false;"
											></h:commandButton></TD>
									-->
									</TR>
									<TR>
										<TD width="15%"><h:outputText value="Benefit Category" /></TD>
										<h:inputHidden id="txtbenefitCategory"
											value="#{StandardBenefitSearchBackingBean.benefitCategory}"></h:inputHidden>
										<TD height="15%"><h:selectOneMenu id="dropBenefitCategory"
											styleClass="formInputField"
											value="#{StandardBenefitSearchBackingBean.benefitCategory}"
											tabindex="10">
											<f:selectItems id="benCatList"
												value="#{ReferenceDataBackingBeanCommon.benefitCategoryListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>

									<TR>
										<TD width="15%"><h:commandButton id="searchButton"
											value="Locate" styleClass="wpdButton"
											action="#{StandardBenefitSearchBackingBean.performLocate}"
											tabindex="11">
										</h:commandButton></TD>
										<TD width="15%">&nbsp;</TD>
										<TD width="5%">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td></td>
									<td align="right"></td>
								</tr>
							</table>
							</DIV>
							</DIV>

							<DIV id="panel2" >
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv" style="width:95%;">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="1050">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" ><h:outputText value="Name"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Version"></h:outputText>
												</TD>
												<TD align="left" ><h:outputText value="Line Of Business"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Description"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Status"></h:outputText>
												<TD align="left" ><h:outputText></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:252px;width:100%;overflow-y:auto;"><!-- Search Result Data Table -->
									<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{StandardBenefitSearchBackingBean.locateResultList != null}"
										value="#{StandardBenefitSearchBackingBean.locateResultList}" width=1050>
										<h:column>
											<h:outputText id="Minorheading"
												value="#{singleValue.benefitIdentifier}"></h:outputText>
											<h:graphicImage id="lockImage"
												url="../../images/lock_icon.jpg" alt="Locked"
												rendered="#{singleValue.state.lockedByUser}"></h:graphicImage>
										</h:column>
										<h:column >
											<h:inputHidden id="standardBenefitVersion"
												value="#{singleValue.version}"></h:inputHidden>
											<h:outputText id="Verisonnumber"
												value="#{singleValue.version}"></h:outputText>
										</h:column>
										<h:column >
											<h:inputHidden id="standardBenefitLobValue"
												value="#{singleValue.lob}"></h:inputHidden>
											<h:outputText id="lobValue" value="#{singleValue.lob}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="Updateddate"
												value="#{singleValue.description}"></h:outputText>
											<h:inputHidden id="standardBenefitKey"
												value="#{singleValue.standardBenefitKey}"></h:inputHidden>
											<h:inputHidden id="standardBenefitName"
												value="#{singleValue.benefitIdentifier}"></h:inputHidden>
											<h:inputHidden id="parentSystemId"
												value="#{singleValue.standardBenefitParentKey}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:inputHidden id="standardBenefitStatus"
												value="#{singleValue.status}"></h:inputHidden>
											<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
										</h:column>
										<h:column>
											<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="viewAction(); return false;"></h:commandButton>

											<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<h:commandButton alt="View All Versions" title="View All Versions" id="basicViewAll"
												image="../../images/notes.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="viewAllAction(); return false;"></h:commandButton>

											<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<h:commandButton alt="Copy" title="Copy" id="basicCopy"
												image="../../images/copy.gif" value="Copy"
												rendered="#{singleValue.state.validForCopy}"
												onclick="copyAction(); return false;"></h:commandButton>

											<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<h:commandButton alt="Edit" title="Edit" id="Edit"
												image="../../images/edit.gif" value="Edit"
												rendered="#{singleValue.state.editableByUser}"
												onclick="editAction(); return false;">
												<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Schedule For Test" title="Schedule For Test"
												id="sendToTestButton" image="../../images/schedule_test.gif"
												onclick="scheduleForTest();return false;"
												rendered="#{singleValue.state.validForTest}">
												<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											</h:commandButton>
											<h:commandButton alt="Approve" title="Approve" id="approveButton"
												image="../../images/approved.gif"
												onclick="approve();return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}">
												<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											</h:commandButton>
											<h:commandButton alt="Reject" title="Reject" id="rejectButton"
												image="../../images/rejected.gif"
												onclick="reject();return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}">
												<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Publish" title="Publish" id="publish"
												onclick="publishAction();return false;"
												image="../../images/publish.gif" value="Publish"
												rendered="#{singleValue.state.validForPublish}">
												<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Checkout" title="Checkout" id="checkout"
												image="../../images/checkOut.gif" value="Checkout"
												rendered="#{singleValue.state.validForCheckOut}"
												onclick="checkoutAction(); return false;">
												<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Test Pass" title="Test Pass" id="testPass"
												image="../../images/test_successful.gif" value="testPass"
												onclick="testPassAction(); return false;"
												rendered="#{singleValue.state.validForTestCompletion}">
												<f:verbatim> &nbsp; &nbsp; </f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Test Fail" title="Test Fail" id="testFailButton"
												rendered="#{singleValue.state.validForTestCompletion}"
												image="../../images/test_failed.gif"
												onclick="testFailAction(); return false;">
												<f:verbatim> &nbsp; &nbsp; </f:verbatim>
											</h:commandButton>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												onclick="confirmDeletion(); return false;"
												rendered="#{singleValue.state.validForDelete}">
											</h:commandButton>
											<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="unlockBenefit();return false;"
												rendered="#{singleValue.state.validForUnlock}">
												<f:verbatim> &nbsp; &nbsp; </f:verbatim>
											</h:commandButton>

										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
								<tr>
									<TD><h:inputHidden id="actionForTest"
										value="#{standardBenefitBackingBean.actionForTest}" /> <h:inputHidden
										id="selectedStandardBenefitKey"
										value="#{standardBenefitBackingBean.selectedStandardBenefitKey}" />
									<h:inputHidden id="selectedStandardBenefitName1"
										value="#{standardBenefitBackingBean.selectedStandardBenefitName}" />
									<h:inputHidden id="selectedParentSystemId"
										value="#{standardBenefitBackingBean.selectedParentSystemId}" />
									<h:inputHidden id="selectedStandardBenefitVersion"
										value="#{standardBenefitBackingBean.selectedStandardBenefitVersion}" />

									<h:inputHidden id="selectedStandardBenefitKeyForDelete"
										value="#{StandardBenefitSearchBackingBean.selectedStandardBenefitKey}" />
									<h:inputHidden id="selectedStandardBenefitNameForDelete"
										value="#{StandardBenefitSearchBackingBean.selectedStandardBenefitName}" />
									<h:commandLink id="editButton"
										action="#{standardBenefitBackingBean.loadStandardBenefitForEdit}"
										style="hidden">
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
									</h:commandLink> <h:commandLink id="copyButton1"
										action="#{standardBenefitBackingBean.loadStandardBenefitForCopy}"
										style="hidden">
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
									</h:commandLink> <h:commandLink id="submitButton"
										action="#{standardBenefitBackingBean.loadStandardBenefitForEdit}"
										style="hidden">
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
									</h:commandLink> <h:commandLink id="checkoutButton"
										action="#{standardBenefitBackingBean.checkOutStandardBenefit}"
										style="hidden">
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
									</h:commandLink> <h:commandLink id="deleteStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.deleteStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink id="publishStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.publishStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink
										id="scheduleForTestStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.scheduleForTestStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink id="approveStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.approveStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink id="rejectStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.rejectStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink id="testPassStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.testPassStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink id="testFailStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.testFailStandardBenefit}">
										<f:verbatim />
									</h:commandLink> <h:commandLink id="searchLink"
										action="#{StandardBenefitSearchBackingBean.performLocate}">
										<f:verbatim></f:verbatim>
									</h:commandLink> <h:commandLink id="actionLink"
										action="#{standardBenefitBackingBean.backToSearch}">
										<f:verbatim></f:verbatim>
									</h:commandLink><h:commandLink id="unlockStandardBenefit"
										style="display:none; visibility: hidden;"
										action="#{StandardBenefitSearchBackingBean.unlockStandardBenefit}">
										<f:verbatim /></TD>
									</h:commandLink>
								</tr>
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>
							</TD>
						</TR>
				</TABLE></h:form></td>
		</tr>
		<tr>
			<td> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	
	</BODY>

</f:view>
<script language="javascript">

copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtLob','lobDiv',2,2); 
copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2); 
copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtTerm','TermDiv',2,2); 
copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtQualifier','QualifierDiv',2,2); 
copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtProviderArrangement','ProviderArrangementDiv',2,2); 
if(null != document.getElementById('StandarsBenefitsSearchForm:searchResultTable:0:lobValue')){
	formatTildaToCommaForBenefit('StandarsBenefitsSearchForm:searchResultTable');
}
function formatTildaToCommaForBenefit(value){
	var i;
	var n;
	n = document.getElementById(value).rows.length;
	for(i=0; i < n; i++)
		{
			formatTildaToComma('StandarsBenefitsSearchForm:searchResultTable:'+i+':lobValue');
		}
}
document.getElementById('StandarsBenefitsSearchForm:txtMinorHeading').focus(); // for on load default selection

	initialize();
	function initialize(){
		var tableobject = document.getElementById('StandarsBenefitsSearchForm:searchResultTable');
		if(tableobject!=null){
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		var rowlength = document.getElementById('StandarsBenefitsSearchForm:searchResultTable').rows.length;
				document.getElementById('StandarsBenefitsSearchForm:searchResultTable').width = relTblWidth+"px";
				syncTables('resultHeaderTable','StandarsBenefitsSearchForm:searchResultTable');
				setColumnWidth('StandarsBenefitsSearchForm:searchResultTable','26%:6%:6%:21%:16%:25%');
				setColumnWidth('resultHeaderTable','26%:6%:6%:21%:16%:25%');
		}
	}
		var cal = new CalendarPopup();
		cal.showYearNavigation();
		
function viewAction(){

 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKey');
 window.showModalDialog('../standardBenefit/standardBenefitView.jsp'+getUrl()+'?benefitkey='+document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitKey').value + '&temp=' + Math.random(),'ViewStandardBenefit','dialogHeight:800px;dialogLeft:100px;dialogTop:100px;dialogWidth:1000px;scrollbars=false;resizable=false;status=no;');

}

//Open a popup window to show all the version of the selected standard benefit.
function viewAllAction(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKey');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitName1');
 	var url = '../standardBenefit/standardBenefitViewAllVersions.jsp'+getUrl()+'?benefitkey='+document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitKey').value + '&benfitNm=' + document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitName1').value + '&temp=' + Math.random();
	var retValueFromVersion = window.showModalDialog(url,window,'dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	  // alert('retval in main page :'+ retValueFromVersion);
	if(retValueFromVersion == 'refresh'){
		 // alert('inside ');	
		 document.getElementById('StandarsBenefitsSearchForm:searchLink').click();
		// getObj('StandarsBenefitsSearchForm:searchLink').click();
	
	}else if(retValueFromVersion == undefined){
		// alert('else');
		// document.getElementById('StandarsBenefitsSearchForm:searchLink').click();
		return true;
	}
	else{
		// alert('inside else');
		var values = retValueFromVersion.split("~");
		if(values != null){
			
			document.getElementById('StandarsBenefitsSearchForm:actionForTest').value = values[0];
			document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitKey').value = values[1];
			document.getElementById('StandarsBenefitsSearchForm:selectedStandardBenefitName1').value = values[2];
			document.getElementById('StandarsBenefitsSearchForm:actionLink').click();
		}
	}
}
function editAction(){
 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKey');
 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','parentSystemId','StandarsBenefitsSearchForm:selectedParentSystemId');
 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitName1');
 submitLink('StandarsBenefitsSearchForm:editButton');
	return false;
}
function copyAction(){
 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKey');
 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','parentSystemId','StandarsBenefitsSearchForm:selectedParentSystemId');
 getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitName1');
 submitLink('StandarsBenefitsSearchForm:copyButton1');
}

function checkoutAction(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKey');
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitName1');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','parentSystemId','StandarsBenefitsSearchForm:selectedParentSystemId');
 	submitLink('StandarsBenefitsSearchForm:checkoutButton');
}

function syncTables(){
			var relTblWidth = document.getElementById('StandarsBenefitsSearchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}		
var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('StandarsBenefitsSearchForm:searchResultTable') != null) {
			//tigra_tables('StandarsBenefitsSearchForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)');
//			setColumnWidth('StandarsBenefitsSearchForm:searchResultTable','33%:7%:12%:12%:18%:18%');
//			setColumnWidth('resultHeaderTable','33%:7%:12%:12%:18%:18%');	
			showResultsTab();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		

		if(document.getElementById('StandarsBenefitsSearchForm:searchResultTable') != null){
			document.getElementById('StandarsBenefitsSearchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}

	copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtLob','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
	copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2); 
	// copyHiddenToDiv_ewpd('StandarsBenefitsSearchForm:txtbenefitType','BenefitTypeDiv',2,1); 

function confirmDeletion(){				
	var message = "Are you sure you want to delete?"	
	if(window.confirm(message)){			
		getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
		submitDataTableButton('StandarsBenefitsSearchForm:searchResultTable', 'standardBenefitKey', 'StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete', 'StandarsBenefitsSearchForm:deleteStandardBenefit');

	}
	else{			
		return false;
	}
}

function publishAction(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:publishStandardBenefit');		
}
function scheduleForTest(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:scheduleForTestStandardBenefit');
}
function approve(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:approveStandardBenefit');
}
function reject(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:rejectStandardBenefit');
}
function testPassAction(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:testPassStandardBenefit');
}
function testFailAction(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
 	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:testFailStandardBenefit');
}
function unlockBenefit(){
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitKey','StandarsBenefitsSearchForm:selectedStandardBenefitKeyForDelete');
	getFromDataTableToHidden('StandarsBenefitsSearchForm:searchResultTable','standardBenefitName','StandarsBenefitsSearchForm:selectedStandardBenefitNameForDelete');
	submitLink('StandarsBenefitsSearchForm:unlockStandardBenefit');		
}		
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="standardBenefitSearch" /></form>
</HTML>
