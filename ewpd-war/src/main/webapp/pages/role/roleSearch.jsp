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
<TITLE>Search Role Page</TITLE>
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
					value="#{roleSearchBackingBean.selectedRoleId}" />
				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>

			<TR>
				<TD><h:form id="searchForm">
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
							<TBODY>
								<TR>
									<h:inputHidden value="#{roleSearchBackingBean.selectedRoleId}" />
									<td valign="top" class="ContentArea">
										<TABLE width="100%">
											<TR>
												<TD><w:message
														value="#{roleSearchBackingBean.validationMessage}"></w:message></TD>
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
														<h:selectOneRadio onclick="hideTaskPopup();" id="console"
															value="#{roleSearchBackingBean.srdaFlag}">
															<f:selectItem itemValue="eWPD" itemLabel="eWPD" />
															<f:selectItem itemValue="SRDA" itemLabel="SRDA" />
														</h:selectOneRadio>
														<TABLE width="100%" cellpadding="2" border="0"
															class="outputText">
															<TBODY>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="roleName" value="Name"></h:outputText></TD>
																	<TD align="left" width="15%"><h:inputHidden
																			id="hidden_role"
																			value="#{roleSearchBackingBean.roleName}">
																		</h:inputHidden>
																		<h:inputText styleClass="formInputField"
																			id="txtRoleName" maxlength="30"
																			value="#{roleSearchBackingBean.roleName}"
																			tabindex="1"></h:inputText></TD>
																	<TD>&nbsp;</TD>
																</TR>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="moduleName" value="Module"></h:outputText></TD>
																	<TD width="15%">
																		<DIV id="moduleDiv" class="selectDataDisplayDiv"></DIV>
																	</TD>
																	<TD>&nbsp;&nbsp;&nbsp; <h:commandButton
																			alt="moduleName" title="moduleName"
																			id="moduleNameButton" image="../../images/select.gif"
																			onclick="invokemodulePopup();
															return false;"
																			tabindex="2">
																		</h:commandButton> <h:inputHidden id="hidden_name"
																			value="#{roleSearchBackingBean.selectedModuleName}">
																		</h:inputHidden>
																	</TD>

																</TR>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="taskName" value="Task"></h:outputText></TD>
																	<TD width="15%">
																		<DIV id="taskDiv" class="selectDataDisplayDiv"></DIV>
																	</TD>
																	<TD>&nbsp;&nbsp;&nbsp; <h:commandButton
																			alt="taskName" title="taskName" id="taskNameButton"
																			image="../../images/select.gif"
																			onclick="ewpdModalWindow_ewpd('../popups/selectTaskPopup.jsp'+getUrl(),'taskDiv','searchForm:hidden_taskname',2,1);
															return false;"
																			tabindex="3">
																		</h:commandButton> <h:inputHidden id="hidden_taskname"
																			value="#{roleSearchBackingBean.selectedTaskName}">
																		</h:inputHidden>
																	</TD>

																</TR>

																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="subTaskName" value="SubTask"></h:outputText></TD>
																	<TD width="15%">
																		<DIV id="subTaskDiv" class="selectDataDisplayDiv"></DIV>
																	</TD>
																	<TD>&nbsp;&nbsp;&nbsp; <h:commandButton
																			alt="subTaskName" title="subTaskName"
																			id="subTaskNameButton"
																			image="../../images/select.gif"
																			onclick="ewpdModalWindow_ewpd('../popups/selectSubTaskPopUp.jsp'+getUrl(),'subTaskDiv','searchForm:hidden_subTaskName',2,1);
															return false;"
																			tabindex="4">
																		</h:commandButton> <h:inputHidden id="hidden_subTaskName"
																			value="#{roleSearchBackingBean.selectedSubTaskName}">
																		</h:inputHidden>
																	</TD>

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
																			action="#{roleSearchBackingBean.roleSearch}"
																			tabindex="5"></h:commandButton></TD>
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
																					<TD align="left"><h:outputText value="Role"></h:outputText></TD>
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
																<td>
																	<!-- Search Result Data Table -->
																	<DIV id="searchResultdataTableDiv"
																		style="height: 252px; overflow: auto; width: 100%;">
																		<h:dataTable headerClass="dataTableHeader"
																			id="searchResultTable" var="singleValue"
																			cellpadding="3" cellspacing="1" bgcolor="#cccccc"
																			rendered="#{roleSearchBackingBean.searchResultList != null}"
																			value="#{roleSearchBackingBean.searchResultList}"
																			rowClasses="dataTableEvenRow,dataTableOddRow"
																			border="0" width="100%">
																			<h:column>
																				<h:outputText id="rolName"
																					value="#{singleValue.roleName}"></h:outputText>
																				<h:inputHidden id="roleId"
																					value="#{singleValue.roleId}"></h:inputHidden>

																			</h:column>

																			<h:column>
																				<h:outputText id="description"
																					value="#{singleValue.description}"></h:outputText>
																			</h:column>

																			<h:column>
																				<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
																				<h:commandButton alt="View" title="View"
																					id="viewButton" image="../../images/view.gif"
																					onclick=" viewAction();return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Edit" title="Edit" id="edit"
																					image="../../images/edit.gif"
																					onclick="editAction();return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Delete" title="Delete"
																					id="delete" image="../../images/delete.gif"
																					onclick="confirmDeletion(); return false;">
																				</h:commandButton>
																			</h:column>

																		</h:dataTable>
																	</DIV>
																</td>
															</TR>
															<TR>
																<TD><h:commandLink id="editButton"
																		action="#{roleBackingBean.loadRoleForEdit}"
																		style="hidden">
																		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
																	</h:commandLink> <h:inputHidden id="selectedRoleId"
																		value="#{roleBackingBean.roleId}"></h:inputHidden> <h:inputHidden
																		id="selectedRoleIdForDelete"
																		value="#{roleSearchBackingBean.selectedRoleId}" /> <h:commandLink
																		id="deleteRole"
																		style="display:none; visibility: hidden;"
																		action="#{roleSearchBackingBean.deleteRole}">
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
			if (radioBtn1 = document.getElementById('searchForm:console:1').checked == true) {
				radioBtn1.checked = true;
				document.getElementById('searchForm:subTaskNameButton').style.display = "none";
				document.getElementById('searchForm:taskNameButton').style.display = "none";

			} else {
				document.getElementById('searchForm:console:0').checked = true;
				document.getElementById('searchForm:taskNameButton').style.display = "inline";
				document.getElementById('searchForm:subTaskNameButton').style.display = "inline";

			}

			function enableRadio() {
				document.getElementById('searchForm:console:0').disabled = false;
				document.getElementById('searchForm:console:1').disabled = false;
			}

			function hideTaskPopup() {
				if (document.getElementById('searchForm:console:1').checked === true) {
					document.getElementById('searchForm:subTaskNameButton').style.display = "none";
					document.getElementById('searchForm:taskNameButton').style.display = "none";
				} else {
					document.getElementById('searchForm:taskNameButton').style.display = "inline";
					document.getElementById('searchForm:subTaskNameButton').style.display = "inline";
				}
			}

			function invokemodulePopup() {
				if (document.getElementById('searchForm:console:0').checked === true) {
					ewpdModalWindow_ewpd(
							'../popups/modulePopUp.jsp' + getUrl(),
							'moduleDiv', 'searchForm:hidden_name', 2, 1);
				} else {
					ewpdModalWindow_ewpd('../popups/selectModulePopup.jsp'
							+ getUrl(), 'moduleDiv', 'searchForm:hidden_name',
							2, 1);
				}
			}

			document.getElementById('searchForm:txtRoleName').focus(); // for on load default selection
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
				setColumnWidth('resultHeaderTable', '25%:25%:25%:25%');
				setColumnWidth('searchForm:searchResultTable',
						'25%:25%:25%:25%');
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
						'roleId', 'searchForm:selectedRoleId');
				submitLink('searchForm:editButton');
				return false;
			}

			function confirmDeletion() {
				var message = "Are you sure you want to delete?"
				if (window.confirm(message)) {
					getFromDataTableToHidden('searchForm:searchResultTable',
							'roleId', 'searchForm:selectedRoleIdForDelete');
					submitLink('searchForm:deleteRole');
				} else {
					return false;
				}
			}
			copyHiddenToDiv_ewpd('searchForm:hidden_name', 'moduleDiv', 2, 1);
			copyHiddenToDiv_ewpd('searchForm:hidden_subTaskName', 'subTaskDiv',
					2, 1);
			copyHiddenToDiv_ewpd('searchForm:hidden_taskname', 'taskDiv', 2, 1);
			function viewAction() {
				getFromDataTableToHidden('searchForm:searchResultTable',
						'roleId', 'searchForm:selectedRoleId');
				var action = 'view';
				window
						.showModalDialog(
								'../role/roleGeneralInformationView.jsp'
										+ getUrl()
										+ '?roleId='
										+ document
												.getElementById('searchForm:selectedRoleId').value
										+ '&action=' + action + '&temp='
										+ Math.random(), 'View',
								'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
			}
		</script>


	</BODY>
</f:view>
</HTML>
