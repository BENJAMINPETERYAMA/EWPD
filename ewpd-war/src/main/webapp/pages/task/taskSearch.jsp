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
<TITLE>Search Task Page</TITLE>
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
	<body onkeypress="return submitOnEnterKey('searchForm:basicSearch');">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="dummy" value="#{taskSearchBackingBean.taskName}" />
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
														value="#{taskSearchBackingBean.validationMessages}"></w:message></TD>
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
															value="#{taskSearchBackingBean.srdaFlag}">

															<f:selectItem itemValue="eWDP" itemLabel="eWPD" />
															<f:selectItem itemValue="SRDA" itemLabel="SRDA" />

														</h:selectOneRadio>
														<TABLE width="100%" cellpadding="2" border="0"
															class="outputText">
															<TBODY>
																<TR>
																	<TD valign="top" width="20%"><h:outputText
																			id="taskName" value="Name"></h:outputText></TD>
																	<TD align="left" width="38%"><h:inputText
																			styleClass="formInputField" id="txtTaskName"
																			maxlength="30"
																			value="#{taskSearchBackingBean.taskName}"
																			tabindex="3"></h:inputText></TD>
																	<TD>&nbsp;</TD>
																</TR>

																<TR>
																	<TD width="20%">&nbsp;</TD>
																	<TD width="38%">&nbsp;</TD>
																	<TD>&nbsp;</TD>
																</TR>
																<TR>
																	<TD align="left" valign="top" colspan="3"><h:commandButton
																			styleClass="wpdbutton" id="basicSearch"
																			value="Locate"
																			action="#{taskSearchBackingBean.taskSearch}"
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
																					<TD align="left"><h:outputText value="Name"></h:outputText></TD>
																					<TD align="left"><h:outputText
																							value="Description"></h:outputText></TD>
																					<TD align="left"><h:outputText value="Type"></h:outputText></TD>
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
																			rendered="#{taskSearchBackingBean.searchResultList != null}"
																			value="#{taskSearchBackingBean.searchResultList}"
																			rowClasses="dataTableEvenRow,dataTableOddRow"
																			border="0" width="100%">
																			<h:column>
																				<h:outputText id="txttaskName"
																					value="#{singleValue.subTaskName}"></h:outputText>
																				<h:inputHidden id="subTaskId"
																					value="#{singleValue.subTaskId}"></h:inputHidden>
																			</h:column>

																			<h:column>
																				<h:outputText id="description"
																					value="#{singleValue.description}"></h:outputText>
																			</h:column>
																			<h:column>
																				<h:outputText id="type"
																					value="#{singleValue.taskType}"></h:outputText>
																				<h:inputHidden id="taskTypeHidden"
																					value="#{singleValue.taskType}"></h:inputHidden>
																			</h:column>
																			<h:column>
																				<f:verbatim>  &nbsp;&nbsp; </f:verbatim>
																				<h:commandButton alt="View" title="View" id="viewButton"
																					image="../../images/view.gif"
																					onclick="viewAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Edit" title="Edit" id="edit"
																					image="../../images/edit.gif"
																					onclick="editAction(); return false;">
																				</h:commandButton>
																				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
																				<h:commandButton alt="Delete" title="Delete" id="delete"
																					image="../../images/delete.gif"
																					onclick="confirmDeletion(); return false;">
																				</h:commandButton>

																			</h:column>

																		</h:dataTable>
																	</DIV>
																</td>
															</TR>
															<TR>
																<TD><h:commandLink id="editButton"
																		action="#{taskBackingBean.loadTaskForEdit}"
																		style="hidden">
																		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
																	</h:commandLink> <h:inputHidden id="selectedTaskId"
																		value="#{taskBackingBean.taskId}"></h:inputHidden> <h:inputHidden
																		id="selectedTaskIdForDelete"
																		value="#{taskSearchBackingBean.selectedTaskId}" /> <h:inputHidden
																		id="selectedTaskType"
																		value="#{taskSearchBackingBean.selectedType}" /> <h:inputHidden
																		id="selectedTaskTypeForEdit"
																		value="#{taskBackingBean.selectedTaskType}" /> <h:commandLink
																		id="deleteTask"
																		style="display:none; visibility: hidden;"
																		action="#{taskSearchBackingBean.deleteTask}">
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
		<script language="JavaScript">
			if (radioBtn1 = document.getElementById('searchForm:radioBtn:1').checked == true) {
				radioBtn1.checked = true;
			} else {
				document.getElementById('searchForm:radioBtn:0').checked = true
			}

			document.getElementById('searchForm:txtTaskName').focus(); // for on load default selection
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
						'subTaskId', 'searchForm:selectedTaskId');
				getFromDataTableToHidden('searchForm:searchResultTable',
						'taskTypeHidden', 'searchForm:selectedTaskTypeForEdit');
				submitLink('searchForm:editButton');
				return false;
			}

			function viewAction() {
				getFromDataTableToHidden('searchForm:searchResultTable',
						'subTaskId', 'searchForm:selectedTaskId');
				getFromDataTableToHidden('searchForm:searchResultTable',
						'taskTypeHidden', 'searchForm:selectedTaskTypeForEdit');
				var action = 'view';
				var taskType = document
						.getElementById('searchForm:selectedTaskTypeForEdit').value;
				if (taskType == 'Parent')
					window
							.showModalDialog(
									'../task/taskView.jsp'
											+ getUrl()
											+ '?taskId='
											+ document
													.getElementById('searchForm:selectedTaskId').value
											+ '&action=' + action
											+ '&taskType=' + taskType
											+ '&temp=' + Math.random(),
									'ViewTask',
									'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
				else if (taskType == 'Child')
					window
							.showModalDialog(
									'../task/subTaskView.jsp'
											+ getUrl()
											+ '?taskId='
											+ document
													.getElementById('searchForm:selectedTaskId').value
											+ '&action=' + action
											+ '&taskType=' + taskType
											+ '&temp=' + Math.random(),
									'ViewTask',
									'dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');

			}
			function confirmDeletion() {
				var message = "Are you sure you want to delete?"
				if (window.confirm(message)) {
					getFromDataTableToHidden('searchForm:searchResultTable',
							'taskTypeHidden', 'searchForm:selectedTaskType');
					getFromDataTableToHidden('searchForm:searchResultTable',
							'subTaskId', 'searchForm:selectedTaskIdForDelete');
					submitLink('searchForm:deleteTask');
				} else {
					return false;
				}
			}
		</script>


	</BODY>
</f:view>
</HTML>