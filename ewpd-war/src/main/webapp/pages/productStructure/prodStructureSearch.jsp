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
<TITLE>Product Structure Search</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<body onUnload="closeAction();"
	onkeypress="return submitOnEnterKey('searchForm:basicSearch')";>
<f:view>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy"
					value="#{productStructureSearchBackingBean.productStructureName}" />
				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>

			<TR>
				<TD><h:form id="searchForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TBODY>
							<TR>
								<td valign="top" class="ContentArea">
								<TABLE width="100%">
									<TR>
										<TD><w:message
											value="#{productStructureSearchBackingBean.validationMessage}"></w:message></TD>
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

								<DIV id="accordionTest" style="margin:5px;">
								<DIV id="searchPanel">
								<DIV id="searchPanelHeader" class="tabTitleBar"
									style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
								<DIV id="searchPanelContent" class="tabContentBox"
									style="position:relative;">

								<TABLE width="49%" cellpadding="2" border="0" class="outputText">
									<TBODY>
										<TR>
											<TD valign="top" width="34%"><h:outputText
												value="Line Of Business" /></TD>
											<h:inputHidden id="hiddenLob"
												value="#{productStructureSearchBackingBean.lineOfBusiness}"></h:inputHidden>
											<TD align="left" width="40%">
											<DIV id="divGroupSizeForLob" class="selectDataDisplayDiv"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForLob'), document.getElementById('searchForm:hiddenLob')); </SCRIPT>
											</TD>
											<TD width="26%"><h:commandButton alt="Line Of Business" title="Line Of Business"
												id="lobButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp?lookUpAction='+'1'+'&parentCatalog='+'Line of Business', 'divGroupSizeForLob', 'searchForm:hiddenLob',2,2,'');
																				return false;"
												tabindex="1">
											</h:commandButton></TD>
										</TR>
										<TR>
											<TD valign="top" width="34%"><h:outputText
												value="Business Entity" /></TD>
											<h:inputHidden id="hiddenEntity"
												value="#{productStructureSearchBackingBean.businessEntity}"></h:inputHidden>
											<TD align="left" width="40%">
											<DIV id="divGroupSizeForEntity" class="selectDataDisplayDiv"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForEntity'), document.getElementById('searchForm:hiddenEntity')); </SCRIPT>
											</TD>
											<TD width="26%"><h:commandButton alt="Business Entity" title="Business Entity"
												id="entityButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','divGroupSizeForEntity','searchForm:hiddenEntity',2,2,'');
																				return false;"
												tabindex="2">
											</h:commandButton></TD>
										</TR>
										<TR>
											<TD valign="top" width="34%"><h:outputText
												value="Business Group" /></TD>
											<h:inputHidden id="hiddenGroup"
												value="#{productStructureSearchBackingBean.businessGroup}"></h:inputHidden>
											<TD align="left" width="40%">
											<DIV id="divGroupSizeForGroup" class="selectDataDisplayDiv"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divGroupSizeForGroup'), document.getElementById('searchForm:hiddenGroup')); </SCRIPT>
											</TD>
											<TD width="26%"><h:commandButton alt="Business Group" title="Business Group"
												id="groupButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp?lookUpAction='+'1'+'&parentCatalog='+'business group','divGroupSizeForGroup','searchForm:hiddenGroup',2,2,'');
																				return false;"
												tabindex="3">
											</h:commandButton></TD>
										</TR>
<!-- CARS START -->
										<TR>
											<TD valign="top" width="34%"><h:outputText
												value="Market Business Unit" /></TD>
											<h:inputHidden id="hiddenMarketBusinessUnit"
												value="#{productStructureSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
											<TD align="left" width="40%">
											<DIV id="divGroupSizeFormarketBusinessUnit" class="selectDataDisplayDiv"></DIV>
											</TD>
											<TD width="26%"><h:commandButton alt="Market Business Unit" title="Market Business Unit"
												id="marketBusinessUnitButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','divGroupSizeFormarketBusinessUnit','searchForm:hiddenMarketBusinessUnit',2,2,'');
																				return false;"
												tabindex="3">
											</h:commandButton></TD>
										</TR>
<!-- CARS END -->
										<TR>
											<TD valign="top" width="34%"><h:outputText
												id="productStructName" value="Name"></h:outputText></TD>
											<TD align="left" width="40%"><h:inputHidden id="hidden_name"
												value="#{productStructureSearchBackingBean.productStructureName}">
											</h:inputHidden><h:inputText styleClass="formInputField"
												id="txtProductStructureName" maxlength="40"
												value="#{productStructureSearchBackingBean.productStructureName}"
												tabindex="4"></h:inputText></TD>
											<td width="26%"></td>
										</TR>
										<TR>
											<TD valign="top" width="34%"><h:outputText
												value="Effective Date" /></TD>
											<TD width="40%"><h:inputText styleClass="formInputField"
												id="effectiveDate_txt"
												value="#{productStructureSearchBackingBean.effectiveDate}"
												maxlength="10" tabindex="5" /><br class="brClass" />
											<SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
											<TD valign="top" width="26%"><A href="#"
												onclick="cal1.select('searchForm:effectiveDate_txt','anchor1','MM/dd/yyyy','anchor1','MM/dd/yyyy'); return false;"
												name="anchor1" id="anchor1"
												title="cal1.select('searchForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;">
											<h:commandButton image="../../images/cal.gif"
												style="cursor: hand" alt="Cal" tabindex="6" /> </A></TD>

										</TR>
										<TR>
											<TD valign="top" width="34%"><h:outputText
												value="Expiry Date" /></TD>
											<TD width="40%"><h:inputText styleClass="formInputField"
												id="expiryDate_txt"
												value="#{productStructureSearchBackingBean.expiryDate}"
												maxlength="10" tabindex="7" /><br class="brClass" />
											<SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
											<TD valign="top" height="30" width="26%"><A href="#"
												onclick="cal1.select('searchForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;"
												name="anchor2" id="anchor2"
												title="cal1.select('searchForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;">
											<h:commandButton image="../../images/cal.gif"
												style="cursor: hand" alt="Cal" tabindex="8" /></A></TD>
										</TR>

										<TR>
											<TD width="34%">&nbsp;</TD>
										</TR>
										<TR>
											<TD align="left" valign="top" colspan="3"><h:commandButton
												styleClass="wpdbutton" id="basicSearch" value="Locate"
												action="#{productStructureSearchBackingBean.productStructureSearch}"
												tabindex="9"></h:commandButton></TD>
										</TR>

									</TBODY>
								</TABLE>

								<TABLE border="0" cellpadding="3" cellspacing="1" width="100">
									<TR>
										<TD></TD>
										<TD align="right"></TD>
									</TR>
								</TABLE>
								</DIV>
								</DIV>
								<DIV id="panel2">
								<DIV id="panel2Header" class="tabTitleBar"
									style="position:relative; cursor:hand; overflow:auto;">Locate
								Results</DIV>
								<DIV id="panel2Content" class="tabContentBox"
									style="position:relative;font-size:10px;width:1300;overflow-x:hidden;overflow-y:scroll;"><BR>
								<table cellpadding="0" cellspacing="0" width="100%" border="0">
									<tr>
										<td>
										<div id="resultHeaderDiv">
										<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
											id="resultHeaderTable" border="0" width="100%">
											<TBODY>
												<TR class="dataTableColumnHeader">
													<TD align="left"><h:outputText value="Name"></h:outputText></TD>
													<TD align="left"><h:outputText value="Version"></h:outputText></TD>
													<TD align="left"><h:outputText value="Line Of Business"></h:outputText></TD>
													<TD align="left"><h:outputText value="Description"></h:outputText></TD>
													<TD align="left"><h:outputText value="Status"></h:outputText></TD>
													<TD align="left"><h:outputText value="Effective Date"></h:outputText></TD>
													<TD align="left"><h:outputText value="Expiry Date"></h:outputText></TD>
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
											style="height:280px;width:1300;"><h:dataTable
											headerClass="dataTableHeader" id="searchResultTable"
											var="singleValue" cellpadding="3" cellspacing="1"
											bgcolor="#cccccc"
											rendered="#{productStructureSearchBackingBean.searchResultList != null}"
											value="#{productStructureSearchBackingBean.searchResultList}"
											rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
											width="100%">


											<h:column>

												<h:outputText id="productStructureName"
													value="#{singleValue.productStructureName}"></h:outputText>
												<h:outputText value="  " id="lockSpace"
													rendered="#{singleValue.state.lockedByUser}"></h:outputText>
												<h:graphicImage id="lockImage"
													url="../../images/lock_icon.jpg" alt="Locked"
													rendered="#{singleValue.state.lockedByUser}">
												</h:graphicImage>
												<h:inputHidden id="hidden_productStructureId"
													value="#{singleValue.productStructureId}"></h:inputHidden>
												<h:inputHidden id="hidden_productStructureName"
													value="#{singleValue.productStructureName}"></h:inputHidden>
											</h:column>
											<h:column>

												<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
												<h:inputHidden id="hidden_version"
													value="#{singleValue.version}"></h:inputHidden>
											</h:column>
											<h:column>

												<h:outputText id="line_of_business"
													value="#{singleValue.lineOfBusiness}"></h:outputText>
												<h:inputHidden id="hidden_lob"
													value="#{singleValue.lineOfBusiness}"></h:inputHidden>
											</h:column>
											<h:column>

												<h:outputText id="description"
													value="#{singleValue.description}"></h:outputText>
												<h:inputHidden id="hidden_description"
													value="#{singleValue.description}"></h:inputHidden>
											</h:column>
											<h:column>

												<h:outputText id="status" value="#{singleValue.status}">
													<f:convertDateTime pattern="MM/dd/yyyy" />

												</h:outputText>
												<h:inputHidden id="hidden_status"
													value="#{singleValue.status}"></h:inputHidden>
											</h:column>
											<h:column>

												<h:outputText id="effectiveDate"
													value="#{singleValue.effectiveDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
												<h:inputHidden id="hidden_effectiveDate"
													value="#{singleValue.effectiveDate}"></h:inputHidden>
											</h:column>
											<h:column>

												<h:outputText id="expiryDate"
													value="#{singleValue.expiryDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />

												</h:outputText>
												<h:inputHidden id="hidden_expiryDate"
													value="#{singleValue.expiryDate}"></h:inputHidden>
											</h:column>



											<h:column>
												<h:outputText value="   " id="a2spaceSpan1"
													rendered="#{singleValue.state.validForView}"></h:outputText>
												<h:commandButton alt="View" title="View" id="viewButton"
													image="../../images/view.gif"
													rendered="#{singleValue.state.validForView}"
													onclick="setKeyForView(); return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a2spaceSpan2"
													rendered="#{singleValue.state.validForView}"></h:outputText>
												<h:commandButton alt="View All Versions" title="View All Versions"
													id="allVersionsButton" image="../../images/notes.gif"
													rendered="#{singleValue.state.validForView}"
													onclick="setKeyForViewAllVersions();return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a2spaceSpan3"
													rendered="#{singleValue.state.validForCopy}"></h:outputText>
												<h:commandButton alt="Copy" title="Copy" id="copyButton"
													image="../../images/copy.gif"
													rendered="#{singleValue.state.validForCopy}"
													onclick="setValueToHiddenFieldFromDataTable('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureIdForEdit', 'searchForm:copyLink');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a2spaceSpan4"
													rendered="#{singleValue.state.editableByUser}"></h:outputText>
												<h:commandButton alt="Edit" title="Edit" id="editButton"
													rendered="#{singleValue.state.editableByUser}"
													image="../../images/edit.gif"
													onclick="setValueToHiddenFieldFromDataTable('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureIdForEdit', 'searchForm:editLink');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="  " id="a3spaceSpan"
													rendered="#{singleValue.state.validForTest}"></h:outputText>
												<h:commandButton alt="Schedule For Test" title="Schedule For Test"
													id="scheduleToTestButton"
													rendered="#{singleValue.state.validForTest}"
													image="../../images/schedule_test.gif"
													onclick="setKeyForTest('SCHEDULE_TEST');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="  " id="a4spaceSpan"
													rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
												<h:commandButton alt="Test Pass" title="Test Pass" id="testPassButton"
													rendered="#{singleValue.state.validForTestCompletion}"
													image="../../images/test_successful.gif"
													onclick="setKeyForTest('TEST_PASS');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a5spaceSpan"
													rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
												<h:commandButton alt="Test Fail" title="Test Fail" id="testFailButton"
													rendered="#{singleValue.state.validForTestCompletion}"
													image="../../images/test_failed.gif"
													onclick="setKeyForTest('TEST_FAIL');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a6spaceSpan"
													rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
												<h:commandButton alt="Approve" title="Approve" id="approvalButton"
													image="../../images/approved.gif"
													onclick="setKeyForTest('APPROVE');return false;"
													rendered="#{singleValue.state.validForApprovalCompletion}">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a22spaceSpan"
													rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
												<h:commandButton alt="Reject" title="Reject" id="rejectButton"
													image="../../images/rejected.gif"
													onclick="setKeyForTest('REJECT');return false;"
													rendered="#{singleValue.state.validForApprovalCompletion}">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a25spaceSpan"
													rendered="#{singleValue.state.validForPublish}"></h:outputText>
												<h:commandButton alt="Publish" title="Publish" id="publishButton"
													rendered="#{singleValue.state.validForPublish}"
													image="../../images/publish.gif"
													onclick="setKeyForTest('PUBLISH');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="  " id="a7spaceSpan"
													rendered="#{singleValue.state.validForCheckOut}"></h:outputText>
												<h:commandButton alt="Checkout" title="Checkout" id="checkoutButton"
													rendered="#{singleValue.state.validForCheckOut}"
													image="../../images/checkOut.gif"
													onclick="setValueToHiddenFieldFromDataTable('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureIdForEdit', 'searchForm:checkoutLink');return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="a1spaceSpan"
													rendered="#{singleValue.state.validForDelete}"></h:outputText>
												<h:commandButton alt="Delete" title="Delete" id="deleteButton"
													rendered="#{singleValue.state.validForDelete}"
													image="../../images/delete.gif"
													onclick="deleteConfirm()
													;return false;">
													<f:verbatim>  &nbsp; </f:verbatim>
												</h:commandButton>
												<h:outputText value="   " id="unlockSpace"
													rendered="#{singleValue.state.validForUnlock}"></h:outputText>
												<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
													image="../../images/lockgreen.gif" value="Unlock"
													onclick="setKeyForTest('UNLOCK');return false;"
													rendered="#{singleValue.state.validForUnlock}">
													<f:verbatim> &nbsp; </f:verbatim>
												</h:commandButton>

											</h:column>
										</h:dataTable></DIV>
										</TD>
									</TR>
									<TR>
										<TD><h:inputHidden id="selectedStructureId"
											value="#{productStructureSearchBackingBean.selectedStructureId}" />
										</TD>
										<td><h:inputHidden id="actionForTest"
											value="#{productStructureSearchBackingBean.actionForTest}" />
										</td>
									</TR>
								</TABLE>
								</DIV>
								</DIV>
								</DIV>
								</DIV>
								</td>
							</tr>
					</TABLE>
					<h:commandLink id="actionLink"
						action="#{productStructureGeneralInfoBackingBean.versionDo}">
						<f:verbatim></f:verbatim>
					</h:commandLink>
					<h:commandLink id="deleteLink"
						action="#{productStructureSearchBackingBean.deleteProductStructure}">
						<f:verbatim></f:verbatim>
					</h:commandLink>
					<h:commandLink id="editLink"
						action="#{productStructureGeneralInfoBackingBean.editProductStructure}">
						<f:verbatim></f:verbatim>
					</h:commandLink>
					<h:commandLink id="copyLink"
						action="#{productStructureGeneralInfoBackingBean.copyProductStructure}">
						<f:verbatim></f:verbatim>
					</h:commandLink>

					<h:commandLink id="checkoutLink"
						action="#{productStructureGeneralInfoBackingBean.checkoutProductStructure}">
						<f:verbatim></f:verbatim>
					</h:commandLink>

					<h:commandLink id="searchLink"
						action="#{productStructureSearchBackingBean.productStructureSearch}">
						<f:verbatim></f:verbatim>
					</h:commandLink>

					<h:commandLink id="scheduleTestLink"
						action="#{productStructureSearchBackingBean.scheduleProductStructureForTest}">
						<f:verbatim></f:verbatim>
					</h:commandLink>

					<h:inputHidden id="productStructureId"
						value="#{productStructureSearchBackingBean.productStructureId}" />
					<h:inputHidden id="productStructureIdForEdit"
						value="#{productStructureGeneralInfoBackingBean.productStructureIdForEdit}" />
					<h:inputHidden id="actionStringFromVersion"
						value="#{productStructureGeneralInfoBackingBean.actionStringFromVersion}" />
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@ include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
</f:view>


<script language="JavaScript">

    copyHiddenToDiv_ewpd('searchForm:hiddenLob','divGroupSizeForLob',2,2);
	copyHiddenToDiv_ewpd('searchForm:hiddenEntity','divGroupSizeForEntity',2,2);
	copyHiddenToDiv_ewpd('searchForm:hiddenGroup','divGroupSizeForGroup',2,2);
	copyHiddenToDiv_ewpd('searchForm:hiddenMarketBusinessUnit','divGroupSizeFormarketBusinessUnit',2,2);

	document.getElementById('searchForm:txtProductStructureName').focus(); // for on load default selection
			
			function deleteConfirm(){
			var message = 'Are you sure you want to delete the selected product structure?';
				if (confirm(message) ){
					setValueToHiddenFieldFromDataTable('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureId', 'searchForm:deleteLink');
					return true;
				} else
				return false;
			}
			function setKeyForTest(value) {
		
			document.getElementById('searchForm:actionForTest').value = value;
			getFromDataTableToHidden('searchForm:searchResultTable', 'hidden_productStructureId', 'searchForm:productStructureId');
			document.getElementById( 'searchForm:scheduleTestLink').click();
			}
		
		var newWinForView;
		var headerDiv;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('searchForm:searchResultTable') != null) {			
		setColumnWidth('resultHeaderTable','19%:6%:9%:19%:13%:9%:8%:18%');
		setColumnWidth('searchForm:searchResultTable','19%:6%:9%:19%:13%:9%:8%:18%');	
			showResultsTab();
		syncTables('searchForm:resultHeaderTable','searchForm:searchResultTable');
		}else{
			headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
			// syncTables('searchForm:resultHeaderTable','searchForm:searchResultTable');
		}	
		// syncTables('searchForm:resultHeaderTable','searchForm:searchResultTable');		
		// if(document.getElementById('searchForm:searchResultTable')!= null){
		// document.getElementById('searchForm:searchResultTable').onresize = syncTables;
		// syncTables();
		// }
		function syncTables(){
			var relTblWidth = document.getElementById('searchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}
		
		
		function setKeyForView() {
			var currentDate = new Date();
			var currentTime = currentDate.getTime();
			getFromDataTableToHidden('searchForm:searchResultTable','hidden_productStructureId','searchForm:selectedStructureId');
			//Code change for product structure tree rendering optimization
			var url = "../productStructure/viewProductStructureGeneralInformation.jsp"+getUrl()+"?"+"id="+document.getElementById('searchForm:selectedStructureId').value+'&date='+currentTime+'&reloadTree=Y';
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=false;status=no;");	
		}	

	
		function setKeyForViewAllVersions() {
			var currentDate = new Date();
			var currentTime = currentDate.getTime();
			var e =  window.event;
			var button_id = e.srcElement.id;	
			var var1 = button_id.split(':');
			var rowcount = var1[2];
	
			var Name1 = "searchForm:searchResultTable:"+rowcount+":hidden_productStructureName";
			var PSName = document.getElementById(Name1).value;
			getFromDataTableToHidden('searchForm:searchResultTable','hidden_productStructureId','searchForm:selectedStructureId');
			//Code change for product structure tree rendering optimization
			var url = "../productStructure/fetchProductStructureVersions.jsp"+getUrl()+"?"+"id="+document.getElementById('searchForm:selectedStructureId').value+'&PSName='+PSName+'&date='+currentTime+'&reloadTree=Y';
			var retValueFromVersion = window.showModalDialog(url, window, "dialogHeight:650px;dialogWidth:1000px;resizable=false;status=no");
			if(retValueFromVersion == 'refresh'){
				document.getElementById('searchForm:searchLink').click();
			}
			else if(retValueFromVersion == undefined)
				return true;
			else{
					var values = retValueFromVersion.split("~");
					if(values != null){
						document.getElementById('searchForm:actionStringFromVersion').value = values[0];
						document.getElementById('searchForm:productStructureIdForEdit').value = values[1];
						document.getElementById('searchForm:actionLink').click();
					}
				}
		}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productStructureSearchResultPrint"></form>
</body>
</HTML>

