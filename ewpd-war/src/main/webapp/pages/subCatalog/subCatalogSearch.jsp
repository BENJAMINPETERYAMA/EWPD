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
<TITLE>Search Sub-Catalog</TITLE>
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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('searchForm:basicSearch');">

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy"
					value="#{subCatalogSearchBackingBean.subCatalogName}" />
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
														value="#{subCatalogSearchBackingBean.validationMessage}"></w:message></TD>
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
												<DIV id="searchPanel">
													<DIV id="searchPanelHeader" class="tabTitleBar"
														style="position: relative; cursor: hand;">
														<B>Locate Criteria</B>
													</DIV>
													<DIV id="searchPanelContent" class="tabContentBox"
														style="position: relative;">

														<TABLE width="75%" cellpadding="2" border="0"
															class="outputText">
															<TBODY>
																<TR>
																	<TD valign="top" width="196"><h:outputText
																			id="catalogName" value="Name"></h:outputText></TD>
																	<TD align="left" width="188"><h:inputHidden
																			id="hidden_name"
																			value="#{subCatalogSearchBackingBean.subCatalogName}">
																		</h:inputHidden>
																		<h:inputText styleClass="formInputField"
																			id="txtSubCatalogName" maxlength="30"
																			value="#{subCatalogSearchBackingBean.subCatalogName}"
																			tabindex="1"></h:inputText></TD>
																</TR>
																<TR>
																	<TD valign="top" width="196"><h:outputText
																			id="catalogDesc" value="Parent Catalog"></h:outputText></TD>
																	<TD width="188">
																		<DIV id="catalogDiv" class="selectDataDisplayDiv"></DIV>
																		<h:inputHidden id="catalogString"
																			value="#{subCatalogSearchBackingBean.catalogId}"></h:inputHidden>
																	</TD>
																	<TD valign="top" width="684"><h:commandButton
																			alt="catalogName" title="catalogName"
																			id="catalogNameButton"
																			image="../../images/select.gif"
																			onclick="ewpdModalWindow_ewpd('../popups/selectCatalogPopupForItemCreate.jsp?lookUpAction=' + '1' + '&title=' + 'Parent Catalog Popup','catalogDiv','searchForm:catalogString',4,2); return false;"
																			tabindex="1">
																		</h:commandButton></TD>
																</TR>
																<TR>
																	<TD width="24%">&nbsp;</TD>
																</TR>
																<TR>
																	<TD align="left" valign="top" colspan="3"><h:commandButton
																			styleClass="wpdbutton" id="basicSearch"
																			value="Locate"
																			action="#{subCatalogSearchBackingBean.search}"
																			tabindex="3"></h:commandButton></TD>
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
														<table cellpadding="0" cellspacing="0" width="100%"
															border="0">
															<tr>
																<td>
																	<div id="resultHeaderDiv">
																		<TABLE cellspacing="1" bgcolor="#cccccc"
																			cellpadding="2" id="resultHeaderTable" border="0"
																			width="100%">
																			<TBODY>
																				<TR class="dataTableColumnHeader">
																					<TD align="left"><h:outputText value="Name"></h:outputText></TD>
																					<TD align="left"><h:outputText
																							value="Description"></h:outputText></TD>
																					<TD align="left"><h:outputText value=" "></h:outputText></TD>
																				</TR>
																			</TBODY>
																		</TABLE>
																	</DIV>
																</TD>
															</TR>
															<TR>
																<TD>
																	<!-- Search Result Data Table -->
																	<DIV id="searchResultdataTableDiv"
																		style="height: 282px; overflow: auto; width: 100%;">
																		<h:dataTable headerClass="dataTableHeader"
																			id="searchResultTable" var="singleValue"
																			cellpadding="3" cellspacing="1" bgcolor="#cccccc"
																			rendered="#{subCatalogSearchBackingBean.searchResultList != null}"
																			value="#{subCatalogSearchBackingBean.searchResultList}"
																			rowClasses="dataTableEvenRow,dataTableOddRow"
																			border="0" width="100%">

																			<h:column>
																				<h:outputText id="catName"
																					value="#{singleValue.catalogName}"></h:outputText>
																				<h:inputHidden id="subCtlgId"
																					value="#{singleValue.catalogId}"></h:inputHidden>
																			</h:column>
																			<h:column>

																				<h:outputText id="description"
																					value="#{singleValue.description}"></h:outputText>

																			</h:column>
																			<h:column>
																				<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
																				<h:commandButton alt="View" title="View"
																					id="viewButton" image="../../images/view.gif"
																					onclick="viewAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Edit" title="Edit" id="Edit"
																					image="../../images/edit.gif"
																					onclick="editAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Delete" title="Delete"
																					id="basicDelete" image="../../images/delete.gif"
																					value="Delete"
																					onclick="confirmDeletion(); return false;">
																					<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																				</h:commandButton>


																			</h:column>
																		</h:dataTable>
																	</DIV>
																</TD>
															</TR>
															<h:commandLink id="editButton"
																action="#{subCatalogBackingBean.loadEditPage}"
																style="hidden">
																<f:verbatim> &nbsp;&nbsp;</f:verbatim>
															</h:commandLink>
															<h:inputHidden id="selectedSubCatalogId"
																value="#{subCatalogBackingBean.subCatalogId}"></h:inputHidden>
															<h:inputHidden id="selectedSubCatalogIdForDelete"
																value="#{subCatalogSearchBackingBean.selectedSubCatalogId}" />
															<h:commandLink id="deleteSubCatalog"
																style="display:none; visibility: hidden;"
																action="#{subCatalogSearchBackingBean.deleteSubCatalog}">
																<f:verbatim> &nbsp;&nbsp;</f:verbatim>
															</h:commandLink>

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
			document.getElementById('searchForm:txtSubCatalogName').focus(); // for on load default selection
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
				setColumnWidth('resultHeaderTable', '35%:35%:30%');
				setColumnWidth('searchForm:searchResultTable', '35%:35%:30%');
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
			function editAction() {
				getFromDataTableToHidden('searchForm:searchResultTable',
						'subCtlgId', 'searchForm:selectedSubCatalogId');
				submitLink('searchForm:editButton');
				return false;
			}

			function viewAction() {
				getFromDataTableToHidden('searchForm:searchResultTable',
						'subCtlgId', 'searchForm:selectedSubCatalogId');
				var action = 'view';
				window
						.showModalDialog(
								'../subCatalog/subCatalogView.jsp'
										+ getUrl()
										+ '?subCatalogId='
										+ document
												.getElementById('searchForm:selectedSubCatalogId').value
										+ '&action=' + action + '&temp='
										+ Math.random(), 'ViewSubCatalog',
								'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
			}
			function confirmDeletion() {
				var message = "Are you sure you want to delete?"
				if (window.confirm(message)) {
					getFromDataTableToHidden('searchForm:searchResultTable',
							'subCtlgId',
							'searchForm:selectedSubCatalogIdForDelete');
					submitLink('searchForm:deleteSubCatalog');
				} else {
					return false;
				}
			}

			copyHiddenToDiv_ewpd('searchForm:catalogString', 'catalogDiv', 3, 2);
		</script>


	</BODY>
</f:view>
</HTML>
