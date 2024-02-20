<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<TITLE>Benefit Component Search</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript">
	 	var cal1 = new CalendarPopup();
		 cal1.showYearNavigation();
</script>
</HEAD>

<body onUnload="closeAction();"
	onkeypress="return submitOnEnterKey('benefitComponentSearchForm:searchButton');">
<f:view>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<h:inputHidden id="dummy"
				value="#{BenefitComponentSearchBackingBean.name}">
			</h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>

		<td><h:form id="benefitComponentSearchForm">
					<TBODY>
						<TR>
							<td>

							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<TR>


									<td valign="top" class="ContentArea">
									<TABLE width="100%">
										<TR>
											<TD><w:message></w:message></TD>
										</TR>
									</TABLE>

									<div>
									<table width="400" border="0" cellpadding="0" cellspacing="0"
										id="TabTable"
										style="position:relative; top:25px; left:5px; z-index:120;">
										<tr>
											<td width="200"></td>
											<td width="200"></td>
										</TR>
									</table>

									<!--Tab Ends-->
									<div id="accordionTest" style="margin:5px;">
									<div id="searchPanel">
									<div id="searchPanelHeader" class="tabTitleBar"
										style="position:relative; cursor:hand;"><b>Locate Criteria</b></div>
									<div id="searchPanelContent" class="tabContentBox"
										style="position:relative;">

							<!--	Start of Table for actual Data	-->
									<br>
									<TABLE width="52%" border="0" cellspacing="0" cellpadding="3">
										<TBODY>
											<TR>
												<TD width="15%"><h:outputText value="Line of Business" /></TD>
												<h:inputHidden id="txtLob"
													value="#{BenefitComponentSearchBackingBean.lob}"></h:inputHidden>
												<TD width="18%">
												<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('lobDiv'), document.getElementById('benefitComponentSearchForm:txtLob')); </SCRIPT>
												</TD>
												<TD width="5%"><h:commandButton alt="Select" title="Select" id="lobButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','benefitComponentSearchForm:txtLob',2,2); return false;"
													tabindex="1"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="15%"><h:outputText value="Business Entity" /></TD>
												<h:inputHidden id="txtBusinessEntity"
													value="#{BenefitComponentSearchBackingBean.businessEntity}"></h:inputHidden>
												<TD width="18%">
												<DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('BusinessEntityDiv'), document.getElementById('benefitComponentSearchForm:txtBusinessEntity')); </SCRIPT>
												</TD>
												<TD width="5%"><h:commandButton alt="Select" title="Select" id="BusinessEntityButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','benefitComponentSearchForm:txtBusinessEntity',2,2); return false;"
													tabindex="2"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="15%"><h:outputText value="Business Group" /></TD>
												<h:inputHidden id="txtBusinessGroup"
													value="#{BenefitComponentSearchBackingBean.businessGroup}"></h:inputHidden>
												<TD width="18%">
												<DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('BusinessGroupDiv'), document.getElementById('benefitComponentSearchForm:txtBusinessGroup')); </SCRIPT>
												</TD>
												<TD width="5%"><h:commandButton alt="Select" title="Select" id="BusinessGroupButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','BusinessGroupDiv','benefitComponentSearchForm:txtBusinessGroup',2,2); return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
<!-- ----------------------------------------------------------------------------------------------------- -->
											<TR>
												<TD width="15%"><h:outputText value="Market Business Unit" /></TD>
												<h:inputHidden id="txtMarketBusinessUnit"
													value="#{BenefitComponentSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="18%">
												<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('BusinessGroupDiv'), document.getElementById('benefitComponentSearchForm:txtBusinessGroup')); </SCRIPT>
												</TD>
												<TD width="5%"><h:commandButton alt="Select" title="Select" id="marketBusinessUnitButton"
													image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','benefitComponentSearchForm:txtMarketBusinessUnit',2,2);return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
<!-- ------------------------------------------------------------------------------------------------------------ -->
											<TR>
												<TD width="15%"><h:outputText value="Name" id="name" /></TD>
												<TD width="18%"><h:inputText styleClass="formInputField"
													id="txtName" maxlength="34"
													value="#{BenefitComponentSearchBackingBean.name}"
													tabindex="4" /></TD>
												<TD width="5%"></TD>
											</TR>
											<TR>
												<TD width="15%"><h:outputText value="Effective Date" /></TD>
												<TD width="18%"><h:inputText styleClass="formInputField"
													id="txtEffectiveDate"
													value="#{BenefitComponentSearchBackingBean.effectiveDate}"
													tabindex="5" /> <br class="brClass" />
												<span class="dateFormat">(mm/dd/yyyy)</span></TD>
												<TD width="5%"><A href="#"
													onclick="cal1.select('benefitComponentSearchForm:txtEffectiveDate','anchor1','MM/dd/yyyy');return false"
													title="cal1.select(document.forms[0].benefitComponentSearchForm:txtEffectiveDate,'anchor1','MM/dd/yyyy'); 
													 return false;"
													name="anchor1" id="anchor1" tabindex="6"> <IMG
													src="../../images/cal.gif" alt="Cal" border="0"> </A></TD>
											</TR>
											<TR>
												<TD width="15%"><h:outputText value="Expiry Date" /></TD>
												<TD width="18%"><h:inputText styleClass="formInputField"
													id="txtExpiryDate"
													value="#{BenefitComponentSearchBackingBean.expiryDate}"
													tabindex="7" /> <span class="dateFormat">(mm/dd/yyyy)</span></TD>
												<TD width="15%"><A href="#"
													onclick="cal1.select('benefitComponentSearchForm:txtExpiryDate','anchor2','MM/dd/yyyy');return false"
													title="cal1.select(document.forms[0].benefitComponentSearchForm:txtExpiryDate,'anchor2','MM/dd/yyyy'); 
													 return false;"
													name="anchor2" id="anchor2" tabindex="8"> <IMG
													src="../../images/cal.gif" alt="Cal" border="0"> </A></TD>
											</TR>
											<TR>
												<TD width="15%"><h:commandButton id="searchButton"
													value="Locate" styleClass="wpdButton"
													action="#{BenefitComponentSearchBackingBean.performSearch}"
													tabindex="9">
												</h:commandButton></TD>
												<TD width="18%">&nbsp;</TD>
												<TD width="5%"></TD>
											</TR>
										</TBODY>
									</TABLE>

									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td></td>
											<td align="right"></td>
										</tr>
									</table>

									</div>
									</div>
									<div id="panel2">
									<div id="panel2Header" class="tabTitleBar"
										style="position:relative; cursor:hand;">Locate Results</div>
									<div id="panel2Content" class="tabContentBox"
										style="position:relative;font-size:10px;width:100%"><br>
									<table cellpadding="0" cellspacing="0" width="100%" border="0">
										<tr>
											<td>


											<div id="resultHeaderDiv" style="width:100%;">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="1170">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<td align="left"><h:outputText value="Name"></h:outputText>
														</td>
														<td align="left"><h:outputText value="Version"></h:outputText>
														</td>
														<td align="left"><h:outputText value="Line Of Business"></h:outputText>
														</td>
														<td align="left"><h:outputText value="Description"></h:outputText>
														</td>
														<td align="left"><h:outputText value="Status"></h:outputText>
														</td>
														<td align="left"><h:outputText value="Effective Date"></h:outputText></td>
														<td align="left"><h:outputText value="Expiry Date"></h:outputText>
														</td>
														<td align="left">&nbsp;</td>
													</TR>
												</TBODY>
											</TABLE>
											</div>



											</td>
										</tr>
										<tr>
											<TD><!-- Search Result Data Table -->
											<div id="searchResultdataTableDiv"
												style="height:250px;overflow:auto;width:100%;"><h:dataTable
												headerClass="dataTableHeader" id="searchResultTable"
												var="singleValue" cellpadding="3" cellspacing="1"
												bgcolor="#cccccc"
												rendered="#{BenefitComponentSearchBackingBean.searchResultList != null}"
												value="#{BenefitComponentSearchBackingBean.searchResultList}"
												rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
												width="1170">
												<h:column>

													<h:outputText id="bcname" value="#{singleValue.name}"></h:outputText>
													<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" 
														rendered ="#{singleValue.state.lockedByUser}"></h:graphicImage>
													<h:inputHidden id="hiddenBCKey"
														value="#{singleValue.benefitComponentId}"></h:inputHidden>
													<h:inputHidden id="hiddenBCName"
														value="#{singleValue.name}"></h:inputHidden>
												</h:column>
												<h:column>

													<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
													<h:inputHidden id="hiddenBCVersion"
														value="#{singleValue.version}"></h:inputHidden>
													<h:inputHidden id="hiddenBCStatus"
														value="#{singleValue.version}"></h:inputHidden>
													<h:inputHidden id="hiddenBCParentId"
														value="#{singleValue.benefitComponentParentId}"></h:inputHidden>
													<h:inputHidden id="hiddenBCType"
														value="#{singleValue.componentType}"></h:inputHidden>
												</h:column>
												<h:column>
													<h:inputHidden id="lobId"
														value="#{singleValue.lob}"></h:inputHidden>
													<h:outputText id="lob" value="#{singleValue.lob}"></h:outputText>
												</h:column>
												<h:column>
													<h:inputHidden id="descriptionId"
														value="#{singleValue.description}"></h:inputHidden>
													<h:outputText id="description" value="#{singleValue.description}"></h:outputText>
												</h:column>	
                                                <h:column>
													<h:inputHidden id="BenefitStatus"
														value="#{singleValue.status}"></h:inputHidden>
													<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="effectiveDate"
														value="#{singleValue.effectiveDate}">
														<f:convertDateTime pattern="MM/dd/yyyy" />
													</h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="expiryDate"
														value="#{singleValue.expiryDate}">
														<f:convertDateTime pattern="MM/dd/yyyy" />
													</h:outputText>
												</h:column>
												
												<h:column>
												<!-- 	<f:verbatim>  &nbsp; &nbsp; </f:verbatim>   -->
													<h:commandButton alt="View" title="View" id="viewButton"
														image="../../images/view.gif" rendered="#{singleValue.state.validForView}"
														onclick="viewAction(); return false;"></h:commandButton>

												<!--	<f:verbatim>  &nbsp; &nbsp; </f:verbatim> -->
													<h:commandButton alt="View All Versions" title="View All Versions" id="versionButton"
														image="../../images/notes.gif" rendered="#{singleValue.state.validForView}"
														onclick="viewVersionAction(); return false;"></h:commandButton>

												<!--	<f:verbatim>  &nbsp; &nbsp; </f:verbatim>  -->
													<h:commandButton alt="Copy" title="Copy" id="copy"
														image="../../images/copy.gif" rendered="#{singleValue.state.validForCopy}"
														onclick="copyAction(); return false;"></h:commandButton>

												<!-- 	<f:verbatim>  &nbsp; &nbsp; </f:verbatim>   -->
													<h:commandButton alt="Edit" title="Edit" id="Edit"
														image="../../images/edit.gif" value="Edit"
														rendered="#{singleValue.state.editableByUser}"
														onclick="editAction(); return false;">
													<!-- 	<f:verbatim>  &nbsp; &nbsp; </f:verbatim> -->
													</h:commandButton>

													<h:commandButton alt="Schedule For Test" title="Schedule For Test" id="sendtotest"
														image="../../images/schedule_test.gif"
														value="Send To Test"
														rendered="#{singleValue.state.validForTest && singleValue.status != 'SCHEDULED_FOR_TEST'}"
														onclick="scheduleTest(); return false;">
												<!-- 		<f:verbatim>  &nbsp; &nbsp; </f:verbatim>  -->
													</h:commandButton>
													<h:commandButton alt="Approve" title="Approve" id="approve"
														image="../../images/approved.gif" value="Approve"
														rendered="#{singleValue.state.validForApprovalCompletion}"
														onclick="approve(); return false;">
												<!--		<f:verbatim>  &nbsp; &nbsp; </f:verbatim>   -->
													</h:commandButton>

													<h:commandButton alt="Reject" title="Reject" id="reject"
														image="../../images/rejected.gif" value="Approve"
														rendered="#{singleValue.state.validForApprovalCompletion}"
														onclick="reject(); return false;">
											<!--	<f:verbatim>  &nbsp; &nbsp; </f:verbatim>   -->
													</h:commandButton>

													<h:commandButton alt="Test Pass" title="Test Pass" id="testPass"
														image="../../images/test_successful.gif" value="Test Pass"
														onclick="testPass();return false;"
														rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
										  <!--	  	<f:verbatim>  &nbsp; &nbsp; </f:verbatim>  -->
													</h:commandButton>

													<h:commandButton alt="Test Fail" title="Test Fail" id="testFail"
														image="../../images/test_failed.gif" value="testFail"
														onclick="testFail();return false;"
														rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
											<!--			<f:verbatim>  &nbsp; &nbsp; </f:verbatim>   -->
													</h:commandButton>

													<h:commandButton alt="Publish" title="Publish" id="publish"
														image="../../images/publish.gif" value="Publish"
														rendered="#{singleValue.state.validForPublish}"
														onclick="publishBC(); return false;">
												<!--	<f:verbatim>  &nbsp; &nbsp; </f:verbatim>  -->
													</h:commandButton>

													<h:commandButton alt="Check Out" title="Check Out" id="checkout"
														image="../../images/checkOut.gif" value="Check Out"
														rendered="#{singleValue.state.validForCheckOut}"
														onclick="checkOut();return false;">
											<!--		<f:verbatim>  &nbsp; &nbsp; </f:verbatim>   -->
													</h:commandButton>
													<h:commandButton alt="Delete" title="Delete" id="deleteButton"
														image="../../images/delete.gif"
														onclick="confirmDeletion();return false;"
														rendered="#{singleValue.state.validForDelete}">
													</h:commandButton>
													<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
														image="../../images/lockgreen.gif" value="Unlock"
														onclick="unlockBenefitComponent();return false;"
														rendered="#{singleValue.state.validForUnlock}">
													</h:commandButton>
												</h:column>
											</h:dataTable></div>
											</TD>
										</tr>
										<TR>
											<TD><h:inputHidden id="fromPage"
												value="#{BenefitComponentSearchBackingBean.fromPage}"></h:inputHidden>
											<h:inputHidden id="selectedBenefitComponentKey"
												value="#{benefitComponentCreateBackingBean.selectedBenefitComponentKey}" />
											<h:inputHidden id="selectedBenefitComponentName"
												value="#{benefitComponentCreateBackingBean.selectedBenefitComponentName}" />
											<h:inputHidden id="selectedBenefitComponentVersion"
												value="#{benefitComponentCreateBackingBean.selectedBenefitComponentVersion}" />

											<h:inputHidden id="selectedBenefitComponentType"
												value="#{benefitComponentCreateBackingBean.selectedBenefitComponentType}" />

											<h:inputHidden id="selectedBenefitComponentParentId"
												value="#{benefitComponentCreateBackingBean.selectedBenefitComponentParentId}" />
											<h:inputHidden id="selectedBenefitComponentKeyForDelete"
												value="#{BenefitComponentSearchBackingBean.selectedBenefitComponentKey}" />
											<h:inputHidden id="selectedBenefitComponentNameForDelete"
												value="#{BenefitComponentSearchBackingBean.selectedBenefitComponentName}" />
											<h:inputHidden id="selectedBenefitComponentVersionForDelete"
												value="#{BenefitComponentSearchBackingBean.selectedBenefitComponentVersion}" />

											<h:commandLink id="editButton"
												action="#{benefitComponentCreateBackingBean.loadBenefitComponentForEdit}"
												style="hidden">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandLink> <h:commandLink
												id="deleteBenefitComponentButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.deleteBenefitComponent}">
												<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											</h:commandLink> <h:inputHidden id="hiddenBenefitComponentId"
												value="#{BenefitComponentSearchBackingBean.benefitComponentId}" />
											<h:inputHidden id="hiddenBenefitComponentParentId"
												value="#{BenefitComponentSearchBackingBean. benefitComponentParentId}" />
											<h:inputHidden id="hiddenBenefitComponentName"
												value="#{BenefitComponentSearchBackingBean.benefitComponentName}" />
											<h:inputHidden id="hiddenBenefitComponentVersion"
												value="#{BenefitComponentSearchBackingBean.version}" /> <h:commandLink
												id="checkOutButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.checkOutBenefitComponent}">
												<f:verbatim />
											</h:commandLink> <h:commandLink id="checkOutVersionButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.checkOutBenefitComponentVersion}">
												<f:verbatim />
											</h:commandLink><h:commandLink id="copyButton"
												action="#{benefitComponentCreateBackingBean.loadBenefitComponentForCopy}"
												style="hidden">
												<f:verbatim />
											</h:commandLink><h:commandLink id="publishButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.publishBenefitComponent}">
												<f:verbatim />
											</h:commandLink><h:commandLink id="scheduleTestButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.scheduleForTestBenefitComponent}">
												<f:verbatim />
											</h:commandLink> <h:commandLink id="approveButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.approveBenefitComponent}">
												<f:verbatim />
											</h:commandLink> <h:commandLink id="rejectButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.rejectBenefitComponent}">
												<f:verbatim />
											</h:commandLink> <h:commandLink id="testPassButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.testPassBenefitComponent}">
												<f:verbatim />
											</h:commandLink> <h:commandLink id="testFailButton"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.testFailBenefitComponent}">
												<f:verbatim />
											</h:commandLink> <h:commandLink id="searchLink"
												action="#{BenefitComponentSearchBackingBean.performSearch}">
												<f:verbatim></f:verbatim>
											</h:commandLink>
											<h:commandLink id="unlockLink"
												style="display:none; visibility: hidden;"
												action="#{BenefitComponentSearchBackingBean.unLockAction}">
												<f:verbatim />
											</h:commandLink></TD>
										</TR>

									</table>

									</div>
									</div>
									</div>
									</div>
									</TD>
								</TR>
							</TABLE>
</TBODY>
							</td>
						<tr>
							<td><%@ include file="../navigation/bottom.jsp"%></td>
						</tr>
				</TABLE>

<script language="JavaScript">

copyHiddenToDiv_ewpd('benefitComponentSearchForm:txtBusinessEntity','BusinessEntityDiv' ,2,2);
copyHiddenToDiv_ewpd('benefitComponentSearchForm:txtLob','lobDiv' ,2,2);
copyHiddenToDiv_ewpd('benefitComponentSearchForm:txtBusinessGroup','BusinessGroupDiv' ,2,2);
copyHiddenToDiv_ewpd('benefitComponentSearchForm:txtMarketBusinessUnit','MarketBusinessUnitDiv', 2,2);
if(null != document.getElementById('benefitComponentSearchForm:searchResultTable:0:lob')){
	formatTildaToCommaForBenefit('benefitComponentSearchForm:searchResultTable');
}
function formatTildaToCommaForBenefit(value){
	var i;
	var n;
	n = document.getElementById(value).rows.length;
	for(i=0; i < n; i++)
		{
			formatTildaToComma('benefitComponentSearchForm:searchResultTable:'+i+':lob');
		}
}

document.getElementById('benefitComponentSearchForm:txtName').focus(); // for on load default selection

		document.getElementById('benefitComponentSearchForm:fromPage').value ="search";
		var newWinForView;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('benefitComponentSearchForm:searchResultTable') != null) {
			setColumnWidth('resultHeaderTable','20%:6%:6%:18%:12%:7%:7%:17%');
			setColumnWidth('benefitComponentSearchForm:searchResultTable','20%:6%:6%:18%:12%:7%:7%:17%');
			showResultsTab();
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		
		if(document.getElementById('benefitComponentSearchForm:searchResultTable')!= null){
		 document.getElementById('benefitComponentSearchForm:searchResultTable').onresize = syncTables;
		 syncTables();
		}
		function syncTables(){
			var relTblWidth = document.getElementById('benefitComponentSearchForm:searchResultTable').offsetWidth;

			if(relTblWidth != 0)
				document.getElementById('resultHeaderTable').width = relTblWidth +'px';
		}
		
		function unlockBenefitComponent(){

			getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:selectedBenefitComponentKeyForDelete');
			getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:selectedBenefitComponentNameForDelete');
			getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:selectedBenefitComponentVersionForDelete');
			
			submitLink('benefitComponentSearchForm:unlockLink');
		}
	
		function editAction(){			
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:selectedBenefitComponentKey');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:selectedBenefitComponentName');
			getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:selectedBenefitComponentVersion');
			getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCParentId','benefitComponentSearchForm:selectedBenefitComponentParentId');			
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCType','benefitComponentSearchForm:selectedBenefitComponentType');
			 submitLink('benefitComponentSearchForm:editButton');
		}
		function copyAction(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:selectedBenefitComponentKey');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:selectedBenefitComponentName');
			getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:selectedBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:copyButton');
		}
		
		function viewAction(){			
			var e =  window.event;
			var button_id = e.srcElement.id;			
			var var1 = button_id.split(':');			
			var rowcount = var1[2];			
			var bcId = "benefitComponentSearchForm:searchResultTable:"+rowcount+":hiddenBCKey";
			var bcIdValue = document.getElementById(bcId).value;
			var type= 'view';		
			var bcName = "benefitComponentSearchForm:searchResultTable:"+rowcount+":hiddenBCName";
			var bcNameValue = document.getElementById(bcName).value;			
			var bcVersion = "benefitComponentSearchForm:searchResultTable:"+rowcount+":hiddenBCVersion";
			var bcVersionValue = document.getElementById(bcVersion).value;	
			var bcComponentType = "benefitComponentSearchForm:searchResultTable:"+rowcount+":hiddenBCType";
			var bcComponentTypeValue = document.getElementById(bcComponentType).value;				
			//Code change for benefit component tree rendering optimization
		 	var url = '../benefitComponent/benefitComponentView.jsp'+getUrl()+'?benefitcomponentkey='+bcIdValue +'&&'+'benfitName='+escape(document.getElementById(bcName).value)+ '&&'+ 'benfitVersion='+bcVersionValue + '&&'+ 'bcType='+ bcComponentTypeValue+'&&'+'type='+type+'&reloadTree=Y';
			newWinForView=window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");		
		}
		function confirmDeletion(){
			var message = "Are you sure you want to delete?"
			if(window.confirm(message)){
				getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:selectedBenefitComponentKeyForDelete');
				getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:selectedBenefitComponentNameForDelete');
				getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:selectedBenefitComponentVersionForDelete');				
				submitLink('benefitComponentSearchForm:deleteBenefitComponentButton');					
			}else{
				return false;
			}
		}


		function checkOut(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCParentId','benefitComponentSearchForm:hiddenBenefitComponentParentId');
			 submitLink('benefitComponentSearchForm:checkOutButton');
		}

		function scheduleTest(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:scheduleTestButton');
		}
		function approve(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:approveButton');
		}
		function reject(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:rejectButton');
		}

		function publishBC(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:publishButton');
		}

		function testPass(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:testPassButton');
		}

		function testFail(){
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCKey','benefitComponentSearchForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCName','benefitComponentSearchForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentSearchForm:searchResultTable','hiddenBCVersion','benefitComponentSearchForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentSearchForm:testFailButton');
		}
	
		 
		<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				function viewVersionAction()
				<%
				}
				else {
				%>
				
				async function viewVersionAction()
				<%
				}
			%>
			{
			var e = window.event;
			var button_id = e.srcElement.id;
			var var1 = button_id.split(':');
			var rowcount = var1[2];
			var bcId = "benefitComponentSearchForm:searchResultTable:"+rowcount+":hiddenBCKey";
			var Name = "benefitComponentSearchForm:searchResultTable:"+rowcount+":hiddenBCName";

			var bcName = document.getElementById(Name).value;
			var bcIdValue = document.getElementById(bcId).value;
			//Code change for benefit component tree rendering optimization
			var url = '../benefitComponent/benefitComponentViewAllVersions.jsp'+getUrl()+'?id='+bcIdValue+'&benefitCName='+bcName+'&reloadTree=Y';
			//retValueFromVersion=window.showModalDialog(url+ "&temp=" + Math.random(),window,"dialogHeight:650px;dialogWidth:1050px;resizable=true;status=no;");
			<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				 retValueFromVersion=window.showModalDialog(url+ "&temp=" + Math.random(),window,"dialogHeight:650px;dialogWidth:1050px;resizable=true;status=no;");
				<%
				}
				else {
				%>
				 retValueFromVersion= await window.showModalDialog(url+ "&temp=" + Math.random(),window,"dialogHeight:650px;dialogWidth:1050px;resizable=true;status=no;");
				
				<%
				}
			%>	
			if(retValueFromVersion == 'refresh'){
				document.getElementById('benefitComponentSearchForm:searchLink').click();
			}
			else if(retValueFromVersion == undefined)
				return true;
			else{
				var values = retValueFromVersion.split("~");
				if(values != null){
					var action = values[0];
					if(action == 'checkout'){
						document.getElementById('benefitComponentSearchForm:hiddenBenefitComponentId').value = values[1];
						document.getElementById('benefitComponentSearchForm:hiddenBenefitComponentName').value = values[2];
						document.getElementById('benefitComponentSearchForm:hiddenBenefitComponentVersion').value = values[3];
						document.getElementById('benefitComponentSearchForm:checkOutVersionButton').click();
					}
					if(action == 'copy'){
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentKey').value = values[1];
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentName').value = values[2];
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentVersion').value = values[3];
						document.getElementById('benefitComponentSearchForm:copyButton').click();
					}
					if(action == 'edit'){
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentKey').value = values[1];
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentName').value = values[2];
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentVersion').value = values[3];
						document.getElementById('benefitComponentSearchForm:selectedBenefitComponentParentId').value = values[4];						
						document.getElementById('benefitComponentSearchForm:editButton').click();
					}
				}
			}

		}


</script>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="benefitComponentSearchResultPrint" >
	</h:form>
</BODY>
</f:view>
</HTML>

