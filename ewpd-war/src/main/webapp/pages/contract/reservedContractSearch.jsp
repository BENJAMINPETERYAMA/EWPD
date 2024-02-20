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
	<BODY onkeypress="return submitOnEnterKey('ReservedContractSearchForm:searchButton');" onUnload="closeAction();">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>

			<td>
				<%          javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
								httpReq.setAttribute("breadCrumbText","Administration >> Contract Id >> Locate ");
					 %>	
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td><h:form id="ReservedContractSearchForm">
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
													<TD>
														<w:message value="#{reservedContractSearchBackingBean.validationMessages}"></w:message> 
													</TD>
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
							<td width="200">
							
							</td>
							<td width="200">
							
							</td>
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
												<TD width="146"><h:outputText value="Line Of Business" /></TD>
												
												<h:inputHidden id="txtlob"
													value="#{reservedContractSearchBackingBean.lob}"></h:inputHidden>
													<TD width="193">
													<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
													
													<SCRIPT>//parseForDiv(document.getElementById('lobDiv'), document.getElementById('ReservedContractSearchForm:txtlob')); </SCRIPT>
												</TD>
												<TD width="553"><h:commandButton alt="Select"
													id="lobButton" image="../../images/select.gif"
													style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'Line of Business','lobDiv','ReservedContractSearchForm:txtlob',2,2); return false;"
													tabindex="1"></h:commandButton></TD>
											</TR>
											
											<TR>
												<TD width="146"><h:outputText value="Business Entity"/></TD>
													<h:inputHidden id="txtBusinessEntity"
													   	value="#{reservedContractSearchBackingBean.businessEntity}"></h:inputHidden>
												<TD width="193"><DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT>//parseForDiv(document.getElementById(''), document.getElementById('ReservedContractSearchForm:')); 
												</SCRIPT>
												</TD>
												<TD width="553"><h:commandButton alt="Select" id="BusinessEntityButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','ReservedContractSearchForm:txtBusinessEntity',2,2); return false;"
													tabindex="2"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Business Group"/></TD>
													<h:inputHidden id="txtBusinessGroup"
													   	value="#{reservedContractSearchBackingBean.businessGroup}"></h:inputHidden>
												<TD width="193"><DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT>//parseForDiv(document.getElementById('BusinessGroupDiv'), document.getElementById('ReservedContractSearchForm:txtBusinessGroup')); </SCRIPT>
												</TD>
												<TD width="553"><h:commandButton alt="Select" id="BusinessGroupButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'business group','BusinessGroupDiv','ReservedContractSearchForm:txtBusinessGroup',2,2); return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
<!-- -------------------------------------------------------------------- -->
											<TR>
												<TD width="146"><h:outputText value="Market Business Unit"/></TD>
													<h:inputHidden id="txtMarketBusinessUnit"
													   	value="#{reservedContractSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="193"><DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT>//parseForDiv(document.getElementById('BusinessGroupDiv'), document.getElementById('ReservedContractSearchForm:txtBusinessGroup')); </SCRIPT>
												</TD>
												<TD width="553"><h:commandButton alt="Select" id="MarketBusinessUnitButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','ReservedContractSearchForm:txtMarketBusinessUnit',2,2); return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
<!-- -------------------------------------------------------------------- -->
											<TR>
												<TD width="146"><h:outputText value="Contract ID" /></TD>
												<TD width="193">
													<h:inputText styleClass="formInputField" id="txtContractId"
														value="#{reservedContractSearchBackingBean.contractId}" tabindex="4" maxlength="4"/>
												</TD>
											</TR>
											<TR>
												<TD width="146"><h:commandButton id="searchButton" value="Locate"
													styleClass="wpdButton"
													action="#{reservedContractSearchBackingBean.searchContract}" tabindex="5">
													</h:commandButton></TD>
												<TD width="193">&nbsp;</TD>
												<TD width="553">&nbsp;</TD>
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
															<TD align="left"><h:outputText
																value="ContractId"></h:outputText></TD>
															<TD align="left"><h:outputText
																value="Effective Date"></h:outputText></TD>
															<TD align="left"><h:outputText
																value="Expiry Date"></h:outputText></TD>
															<TD align="left"><h:outputText
																value="Last Updated By"></h:outputText></TD>
															<TD align="left"><h:outputText
																value="Last Updated Date"></h:outputText></TD>
															<TD align="left"> </TD>
															
															
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
										<h:dataTable
												rowClasses="dataTableEvenRow,dataTableOddRow"
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" bgcolor="#cccccc"
												rendered="#{reservedContractSearchBackingBean.locateResultList != null}"
												value="#{reservedContractSearchBackingBean.locateResultList}" width="1050">
												<h:column>
													<h:outputText id="ContractId"
														value="#{singleValue.contractId}"></h:outputText>
													<h:inputHidden id="contractKey" value="#{singleValue.contractId}">
													</h:inputHidden>	
												</h:column>
												<h:column>
													<h:outputText id="StartDate"
														value="#{singleValue.effectiveDate_String}"></h:outputText>
													
												</h:column>
												<h:column>
													<h:outputText id="EndDate"
														value="#{singleValue.expiryDate_String}"> </h:outputText>
												
													<h:inputHidden id="contractIdHid"
														value="#{singleValue.contractId}">
													</h:inputHidden>	
												</h:column>
												<h:column>
													<h:outputText id="lastUpdatedUser"
														value="#{singleValue.lastUpdatedUser}"></h:outputText>
													
												</h:column>
												<h:column>
													<h:outputText id="lastUpdatedDateTime"
														value="#{singleValue.lastUpdatedTimeStamp}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
													
												</h:column>
												<h:column>
													<h:commandButton alt="Edit" id="basicEdit" image="../../images/edit.gif" value="Edit" 
													rendered="#{singleValue.reservePoolStatus != 'U'}"		
													onclick=" editAction();return false;">
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
													</h:commandButton>
													
											
										 
													<h:commandButton alt="Release" id="basicRelease" image="../../images/release_icon.gif" value="Release" 
															rendered="#{singleValue.reservePoolStatus != 'U'}"
															onclick=" releaseAction();return false;">
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
													</h:commandButton>
													
											</h:column>
												</h:dataTable>


									</DIV>
									</TD>
								</TR>

								<tr>
									<TD>
									<h:inputHidden id="contractKeyForEdit"	value="#{reservedContractBackingBean.contractKeyForEdit}" />
									<h:inputHidden id="contractKeyForRelease"	value="#{reservedContractSearchBackingBean.contractID}" />
									<h:inputHidden id="contractIdForEdit" 	value="#{reservedContractBackingBean.contractIdForEdit}" />
									<h:inputHidden id="selectedContract"	value="#{contractSearchBackingBean.selectedContractIDFromSearch}" />
									<h:inputHidden id="selectedContractId" 	value="#{contractBasicInfoBackingBean.selectedIdFromSearch}" />
									<h:inputHidden id="dateEntered"    		value="#{dateSegmentBackingBean.dateEntered}" />
										
									<h:commandLink id="linkToEdit" 	style="display:none; visibility: hidden;" 		action="#{reservedContractBackingBean.editAction}">
												<f:verbatim />
									</h:commandLink>
									<h:commandLink id="linkToRelease" 	style="display:none; visibility: hidden;" 		action="#{reservedContractSearchBackingBean.releaseAction}">
												<f:verbatim />
									</h:commandLink>
									</TD>
								</tr>
							</TABLE>
							</DIV>
							</DIV>
						</DIV>

						</DIV>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!-- WAS 6.0 migration changes - Javascript error Invalid arguement thrown, when html tags are not placed properly -->
		</h:form>
		</td>
		</tr>
		<tr>
			<td>
			<%@ include file="../navigation/bottom.jsp"%></td>
		</tr>	
	</table>
	</BODY>

</f:view>
<script language="javascript">
copyHiddenToDiv_ewpd('ReservedContractSearchForm:txtlob','lobDiv',2,2);
copyHiddenToDiv_ewpd('ReservedContractSearchForm:txtBusinessEntity','BusinessEntityDiv',2,2);
copyHiddenToDiv_ewpd('ReservedContractSearchForm:txtBusinessGroup','BusinessGroupDiv',2,2);
copyHiddenToDiv_ewpd('ReservedContractSearchForm:txtMarketBusinessUnit', 'MarketBusinessUnitDiv',2,2);

	document.getElementById('ReservedContractSearchForm:contractKeyForRelease').value = document.getElementById('ReservedContractSearchForm:txtContractId').value;	
	document.getElementById('ReservedContractSearchForm:txtContractId').focus(); // for on load default selection

initialize();
	function initialize(){
		var tableobject = document.getElementById('ReservedContractSearchForm:searchResultTable');
		if(tableobject!=null){
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		var rowlength = document.getElementById('ReservedContractSearchForm:searchResultTable').rows.length;
				document.getElementById('ReservedContractSearchForm:searchResultTable').width = relTblWidth+"px";
				syncTables('resultHeaderTable','ReservedContractSearchForm:searchResultTable');
				setColumnWidth('ReservedContractSearchForm:searchResultTable','15%:15%:15%:15%:15%:15%:10%');
				setColumnWidth('resultHeaderTable','15%:15%:15%:15%:15%:15%:10%');
		}
	}

var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});

if(document.getElementById('ReservedContractSearchForm:searchResultTable') != null) {
	//setColumnWidth('ReservedContractSearchForm:searchResultTable','15%:15%:15%:15%:15%:15%:10%');
	//setColumnWidth('resultHeaderTable','15%:15%:15%:15%:15%:15%:10%');	
	showResultsTab();
}else {
	var headerDiv = document.getElementById('resultHeaderDiv');
	headerDiv.style.visibility = 'hidden';
}	

	function syncTables(){
		var relTblWidth = document.getElementById('ReservedContractSearchForm:searchResultTable').offsetWidth;
		document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
	}	
		
		if(document.getElementById('ReservedContractSearchForm:searchResultTable') != null){
			document.getElementById('ReservedContractSearchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}


function editAction(){
		getFromDataTableToHidden('ReservedContractSearchForm:searchResultTable','contractIdHid','ReservedContractSearchForm:contractIdForEdit');
		submitLink('ReservedContractSearchForm:linkToEdit');
	}

  function releaseAction(){
		getFromDataTableToHidden('ReservedContractSearchForm:searchResultTable','contractKey','ReservedContractSearchForm:contractKeyForRelease');
		submitLink('ReservedContractSearchForm:linkToRelease');
	}


</script>
</HTML>
