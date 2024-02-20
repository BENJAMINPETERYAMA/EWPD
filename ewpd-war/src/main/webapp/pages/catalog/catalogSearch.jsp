<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/CatalogSearch.java" --%><%-- /jsf:pagecode --%>
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
<TITLE>Search Catalog</TITLE>
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
	<BODY>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy"
					value="#{catalogSearchBackingBean.description}" />
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
														value="#{catalogSearchBackingBean.validationMessages}"></w:message></TD>
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
															value="#{catalogSearchBackingBean.srdaFlag}">


															<f:selectItem itemValue="eWPD" itemLabel="eWPD" />
															<f:selectItem itemValue="SRDA" itemLabel="SRDA" />

														</h:selectOneRadio>
														<TABLE width="75%" cellpadding="2" border="0"
															class="outputText">
															<TBODY>
																<TR>
																	<TD valign="top" width="196"><h:outputText
																			id="catalogName" value="Name"></h:outputText></TD>
																	<TD align="left" width="184"><h:inputHidden
																			id="hidden_name"
																			value="#{catalogSearchBackingBean.catalogName}">
																		</h:inputHidden>
																		<h:inputText styleClass="formInputField"
																			id="txtCatalogName" maxlength="30"
																			value="#{catalogSearchBackingBean.catalogName}"
																			tabindex="1"></h:inputText></TD>
																</TR>
																<TR>
																	<TD valign="top" width="196"><h:outputText
																			id="catalogDesc" value="Description"></h:outputText></TD>
																	<TD align="left" width="184"><h:inputHidden
																			id="hidden_desc"
																			value="#{catalogSearchBackingBean.description}">
																		</h:inputHidden>
																		<h:inputText styleClass="formTxtAreaField"
																			id="txtCatalogDesc" maxlength="250"
																			value="#{catalogSearchBackingBean.description}"
																			tabindex="2"></h:inputText></TD>
																</TR>
																<TR>
																	<TD width="24%">&nbsp;</TD>
																</TR>
																<TR>
																	<TD align="left" valign="top" colspan="3"><h:commandButton
																			styleClass="wpdbutton" id="basicSearch"
																			value="Locate"
																			action="#{catalogSearchBackingBean.catalogSearch}"
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
														<BR>
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
																					<TD align="left"><h:outputText
																							value="Catalog Name"></h:outputText></TD>
																					<TD align="left"><h:outputText
																							value="Description"></h:outputText></TD>
																					<TD align="left"><h:outputText value=""></h:outputText></TD>
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
																			rendered="#{catalogSearchBackingBean.searchResultList != null}"
																			value="#{catalogSearchBackingBean.searchResultList}"
																			rowClasses="dataTableEvenRow,dataTableOddRow"
																			border="0" width="100%">

																			<h:column>
																				<h:outputText id="catName"
																					value="#{singleValue.catalogName}"></h:outputText>
																			</h:column>
																			<h:column>

																				<h:outputText id="description"
																					value="#{singleValue.description}"></h:outputText>
																				<h:inputHidden id="catalogId"
																					value="#{singleValue.catalogId}"></h:inputHidden>
																			</h:column>
																			<h:column>
																				<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
																				<h:commandButton alt="View" title="View" id="viewButton"
																					image="../../images/view.gif"
																					onclick="viewAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Edit" title="Edit" id="Edit"
																					disabled="#{(catalogSearchBackingBean.srdaFlag=='SRDA')}"
																					image="../../images/edit.gif"
																					onclick="editAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Delete" title="Delete" id="basicDelete"
																					image="../../images/delete.gif" value="Delete"
																					onclick="confirmDeletion(); return false;"
																					disabled="#{(catalogSearchBackingBean.srdaFlag=='SRDA')}">
																					<f:verbatim> &nbsp; &nbsp;</f:verbatim>
																				</h:commandButton>


																			</h:column>
																		</h:dataTable>
																	</DIV>
																</TD>
															</TR>
															<tr>
																<TD><h:inputHidden id="selectedCatalogId"
																		value="#{catalogBackingBean.selectedCatalogId}" /> <h:commandLink
																		id="editButton"
																		action="#{catalogBackingBean.loadCatalogForEdit}"
																		style="hidden">
																		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
																	</h:commandLink> <h:inputHidden id="selectedCatalogIdForDelete"
																		value="#{catalogSearchBackingBean.selectedCatalogId}" />
																	<h:commandLink id="deleteCatalog"
																		style="display:none; visibility: hidden;"
																		action="#{catalogSearchBackingBean.deleteCatalog}">
																		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
																	</h:commandLink></TD>
															</tr>
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
			if (radioBtn1 = document.getElementById('searchForm:radioBtn:1').checked == true) {
				radioBtn1.checked = true;
			} else {
				document.getElementById('searchForm:radioBtn:0').checked = true;
			}

			function enableRadio() {
				document.getElementById('searchForm:radioBtn:0').disabled = false;
				document.getElementById('searchForm:radioBtn:1').disabled = false;
			}
			document.getElementById('searchForm:txtCatalogName').focus(); // for on load default selection
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
						'catalogId', 'searchForm:selectedCatalogId');
				submitLink('searchForm:editButton');
				return false;
			}

			function viewAction() {
				getFromDataTableToHidden('searchForm:searchResultTable',
						'catalogId', 'searchForm:selectedCatalogId');
				var action = 'view';
				var catalogId = document
						.getElementById('searchForm:selectedCatalogId').value;
				window
						.showModalDialog('../catalog/catalogView.jsp'
								+ getUrl() + '?catalogId=' + catalogId
								+ '&action=' + action + '&temp='
								+ Math.random(), 'ViewCatalog',
								'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
			}
			function confirmDeletion() {
				var message = "Are you sure you want to delete?"
				if (window.confirm(message)) {
					getFromDataTableToHidden('searchForm:searchResultTable',
							'catalogId',
							'searchForm:selectedCatalogIdForDelete');
					submitLink('searchForm:deleteCatalog');
				} else {
					return false;
				}
			}

			/* function srdaFunAct(){
			
			var srdaFlag = document.getElementById('searchForm:radioBtn').value;
			
			document.getElementById("userFlag").value = srdaFlag.value();
			
			}
			 */
		</script>


	</BODY>
</f:view>
</HTML>
