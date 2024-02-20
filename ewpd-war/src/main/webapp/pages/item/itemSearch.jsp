<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Search Item Page</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css"
	href="../../../theme/stylesheet.css" title="Style">

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
<script language="JavaScript" src="../../../js/date.js"></script>
<script language="JavaScript" src="../../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../../js/prototype.js"></script>
<script language="JavaScript" src="../../../js/rico.js"></script>
<script language="JavaScript" src="../../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<body onkeypress="return submitOnEnterKey('searchForm:basicSearch');">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy"
					value="#{itemSearchBackingBean.primaryCode}" />
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
														value="#{itemSearchBackingBean.validationMessage}"></w:message></TD>
											</TR>

										</TABLE>

										<div>
											<table width="400" border="0" cellpadding="0" cellspacing="0"
												id="TabTable"
												style="position: relative; top: 25px; left: 5px; z-index: 120;">
												<tr>
													<td width="200"></td>
													<td width="200"></td>
												</TR>
											</table>

											<!--Tab Ends-->

											<DIV id="accordionTest" style="margin: 5px;">
												<DIV id="searchPanel" onclick="enableRadio()">
													<DIV id="searchPanelHeader" class="tabTitleBar"
														style="position: relative; cursor: hand;">
														<B>Locate Criteria &nbsp;&nbsp;</B>

													</DIV>
													<DIV id="searchPanelContent" class="tabContentBox"
														style="position: relative;">
														<h:selectOneRadio id="radioBtn"
															value="#{itemSearchBackingBean.srdaFlag}">

															<f:selectItem itemValue="eWPD" itemLabel="eWPD" />
															<f:selectItem itemValue="SRDA" itemLabel="SRDA" />

														</h:selectOneRadio>
														<TABLE width="100%" cellpadding="2" border="0"
															class="outputText">
															<TBODY>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="catalogName" value="Catalog Name"></h:outputText></TD>
																	<TD width="15%">
																		<DIV id="catalogDiv" class="selectDataDisplayDiv"></DIV>
																	</TD>
																	<TD>&nbsp;&nbsp;&nbsp; <h:commandButton
																			alt="catalogName" id="catalogNameButton"
																			image="../../images/select.gif"
																			onclick="showSrdaDataPopup();
															return false;"
																			tabindex="1">
																		</h:commandButton> <h:inputHidden id="hidden_name"
																			value="#{itemSearchBackingBean.selectedCatalogName}">
																		</h:inputHidden></TD>

																</TR>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="primaryCode" value="Primary Code"></h:outputText></TD>
																	<TD align="left" width="15%"><h:inputHidden
																			id="hidden_primary"
																			value="#{itemSearchBackingBean.primaryCode}">
																		</h:inputHidden>
																		<h:inputText styleClass="formInputField"
																			id="txtPrimaryCode" maxlength="20"
																			value="#{itemSearchBackingBean.primaryCode}"
																			tabindex="2"></h:inputText></TD>
																	<TD>&nbsp;</TD>
																</TR>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="secondaryCode" value="Secondary Code"></h:outputText></TD>
																	<TD align="left" width="15%"><h:inputHidden
																			id="hidden_secondary"
																			value="#{itemSearchBackingBean.secondaryCode}">
																		</h:inputHidden>
																		<h:inputText styleClass="formInputField"
																			id="txtSecondaryCode" maxlength="30"
																			value="#{itemSearchBackingBean.secondaryCode}"
																			tabindex="3"></h:inputText></TD>
																	<TD>&nbsp;</TD>
																</TR>
																<TR>
																	<TD width="20%">&nbsp;</TD>
																	<TD width="15%">&nbsp;</TD>
																	<TD>&nbsp;</TD>
																</TR>
																<TR>
																	<TD align="left" valign="top" colspan="3"><h:commandButton
																			styleClass="wpdbutton" id="basicSearch"
																			value="Locate"
																			action="#{itemSearchBackingBean.itemSearch}"
																			tabindex="4"></h:commandButton></TD>
																	<TD width="15%">&nbsp;</TD>
																	<TD>&nbsp;</TD>
																</TR>

															</TBODY>
														</TABLE>

														<TABLE border="0" cellpadding="0" cellspacing="0"
															width="100%">
															<TR>
																<TD></TD>
																<TD align="right"></TD>
															</TR>
														</TABLE>
													</DIV>
												</DIV>
												<DIV id="panel2">
													<DIV id="panel2Header" class="tabTitleBar"
														style="position: relative; cursor: hand;">Locate
														Results</DIV>
													<DIV id="panel2Content" class="tabContentBox"
														style="position: relative; font-size: 10px;">
														<BR>
														<table cellpadding="0" cellspacing="0" width="100%"
															border="0">
															<tr>
																<td>
																	<div id="resultHeaderDiv">
																		<TABLE cellspacing="1" bgcolor="#cccccc"
																			cellpadding="3" id="resultHeaderTable" border="0"
																			width="100%">
																			<TBODY>
																				<TR class="dataTableColumnHeader">
																					<TD align="left"><h:outputText
																							value="Primary Code"></h:outputText></TD>
																					<TD align="left"><h:outputText
																							value="Secondary Code"></h:outputText></TD>
																					<TD align="left"><h:outputText
																							value="Description"></h:outputText></TD>
																					<TD align="left"><h:outputText value="Catalog"></h:outputText></TD>
																					<TD align="left"><h:outputText value=""></h:outputText></TD>
																				</TR>
																			</TBODY>
																		</TABLE>
																	</DIV>
																</TD>
															</TR>
															<TR>
																<td>
																	<!-- Search Result Data Table -->
																	<DIV id="searchResultdataTableDiv"
																		style="height: 281px; overflow: auto; width: 100%;">
																		<h:dataTable headerClass="dataTableHeader"
																			id="searchResultTable" var="singleValue"
																			cellpadding="3" cellspacing="1" bgcolor="#cccccc"
																			rendered="#{itemSearchBackingBean.searchResultList != null}"
																			value="#{itemSearchBackingBean.searchResultList}"
																			rowClasses="dataTableEvenRow,dataTableOddRow"
																			border="0" width="100%">
																			<h:column>
																				<h:outputText id="catName"
																					value="#{singleValue.primaryCode}"></h:outputText>
																				<h:inputHidden id="catalogId"
																					value="#{singleValue.catalogId}" />
																				<h:inputHidden id="catalogNm"
																					value="#{singleValue.primaryCode}" />
																				<h:inputHidden id="nameCat"
																					value="#{singleValue.catalogName}" />
																			</h:column>

																			<h:column>
																				<h:outputText id="description"
																					value="#{singleValue.secondaryCode}"></h:outputText>
																			</h:column>
																			<h:column>
																				<h:outputText id="descriptionId"
																					value="#{singleValue.description}"></h:outputText>
																			</h:column>

																			<h:column>
																				<h:outputText id="catalog"
																					value="#{singleValue.catalogName}"></h:outputText>
																			</h:column>
																			<h:column>
																				<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
																				<h:commandButton alt="View" title="View" id="viewButton"
																					image="../../images/view.gif"
																					onclick="viewAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Edit" title="Edit" id="edit"
																					image="../../images/edit.gif"
																					onclick="editAction(); return false;">
																				</h:commandButton>
																			</h:column>

																		</h:dataTable>
																	</DIV>
																</td>
															</TR>
															<TR>
																<TD><h:inputHidden id="catalogSelected"
																		value="#{itemBackingBean.catalogId}" /> <h:inputHidden
																		id="prmryCd" value="#{itemBackingBean.primaryCode}" />
																	<h:inputHidden id="nameCtlg"
																		value="#{itemBackingBean.catalogName}" /> <h:commandLink
																		id="deleteItem"
																		style="display:none; visibility: hidden;"
																		action="#{itemBackingBean.deleteItem}">
																		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
																	</h:commandLink> <h:commandLink id="editButton"
																		action="#{itemBackingBean.loadItemForEdit}"
																		style="hidden">
																		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
																	</h:commandLink></TD>
															</TR>
														</TABLE>
													</DIV>
												</DIV>
											</DIV>
										</DIV>
									</td>
								</tr>
								<TR>
									<TD><%@ include file="../navigation/bottom.jsp"%></TD>
								</TR>
						</TABLE>
					</h:form></TD>
			</TR>
		</TABLE>
		</hx:scriptCollector>
		<script language="JavaScript">
			if (radioBtn1 = document.getElementById('searchForm:radioBtn:1').checked === true) {
				radioBtn1.checked = true;
			} else {
				document.getElementById('searchForm:radioBtn:0').checked = true;
			}

			function showSrdaDataPopup() {
				if (document.getElementById('searchForm:radioBtn:0').checked === true) {
					ewpdModalWindow_ewpd(
							'../popups/selectCatalogPopupForItemCreate.jsp'
									+ getUrl(), 'catalogDiv',
							'searchForm:hidden_name', 3, 2);

				} else {
					ewpdModalWindow_ewpd(
							'../popups/selectSrdaCatalogPopupForItemCreate.jsp'
									+ getUrl(), 'catalogDiv',
							'searchForm:hidden_name', 3, 2);
				}

			}

			function enableRadio() {
				document.getElementById('searchForm:radioBtn:0').disabled = false;
				document.getElementById('searchForm:radioBtn:1').disabled = false;
			}

			if (document.getElementById('searchForm:catalogName').value != null) {
				document.getElementById('searchForm:catalogName').value = null;
			}

			document.getElementById('searchForm:txtPrimaryCode').focus(); // for on load default selection
			var newWinForView;
			var headerDiv;
			var accordTab = new Rico.Accordion('accordionTest', {
				panelHeight : '327',
				expandedBg : 'rgb(130,130,130)',
				collapsedBg : 'rgb(204,204,204)',
				hoverBg : 'rgb(130,130,130)',
				collapsedTextColor : 'rgb(130,130,130)',
				borderColor : 'rgb(204,204,204)'
			});
			if (document.getElementById('searchForm:searchResultTable') != null) {
				setColumnWidth('resultHeaderTable', '20%:20%:25%:20%:15%');
				setColumnWidth('searchForm:searchResultTable',
						'20%:20%:25%:20%:15%');
				showResultsTab();
			} else {
				headerDiv = document.getElementById('resultHeaderDiv');
				headerDiv.style.visibility = 'hidden';
			}

			if (document.getElementById('searchForm:searchResultTable') != null) {
				document.getElementById('searchForm:searchResultTable').onresize = syncTables;
				syncTables();
			}
			function syncTables() {
				var relTblWidth = document
						.getElementById('searchForm:searchResultTable').offsetWidth;
				document.getElementById('resultHeaderTable').width = relTblWidth
						+ 'px';
			}
			function viewAction() {

				getFromDataTableToHidden('searchForm:searchResultTable',
						'catalogId', 'searchForm:catalogSelected');
				getFromDataTableToHidden('searchForm:searchResultTable',
						'catalogNm', 'searchForm:prmryCd');
				getFromDataTableToHidden('searchForm:searchResultTable',
						'nameCat', 'searchForm:nameCtlg');
				var action = 'view';
				var catalogId = document
						.getElementById('searchForm:catalogSelected').value;
				var prmryCode = document.getElementById('searchForm:prmryCd').value;
				var ctalogName = document.getElementById('searchForm:nameCtlg').value;
				window
						.showModalDialog('../item/itemView.jsp' + getUrl()
								+ '?catalogId=' + catalogId + '&prmryCode='
								+ prmryCode + '&ctalogName=' + ctalogName
								+ '&action=' + action + '&temp='
								+ Math.random(), 'viewItem',
								'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
			}
			function editAction() {
				getFromDataTableToHidden('searchForm:searchResultTable',
						'catalogId', 'searchForm:catalogSelected');
				getFromDataTableToHidden('searchForm:searchResultTable',
						'catalogNm', 'searchForm:prmryCd');
				submitLink('searchForm:editButton');
				return false;
			}
			copyHiddenToDiv_ewpd('searchForm:hidden_name', 'catalogDiv', 3, 2);
		</script>


	</BODY>
</f:view>
</HTML>
