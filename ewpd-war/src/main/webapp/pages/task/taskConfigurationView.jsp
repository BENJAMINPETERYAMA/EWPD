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

<TITLE>Sub-Task Configuration View</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="viewSubTaskConfigurationForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<!-- Space for tree -->

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<TD class="tabNormal"><h:commandLink
											action="#{taskBackingBean.loadGeneralInfo}"
											id="linkToGeneralInfo">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>


								<TD width="200">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TBODY>
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
												width="3" height="21"></TD>
											<TD class="tabActive"><h:outputText id="labelBC"
												value="Sub-Task Configuration"></h:outputText></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TBODY>
								</TABLE>
								</TD>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>

								<td colspan="2" valign="top" class="ContentArea">

								<fieldset>

								<div id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Sub-Task Configuration</div>
								<h:outputText value="No Configured Sub-Tasks Available."
									rendered="#{taskBackingBean.associatedSubTask == null}"
									styleClass="dataTableColumnHeader" />
								<table class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td>
										<div id="resultHeaderDiv">
										<TABLE id="headerTable" width="100%" border="0"
											bgcolor="#cccccc" cellpadding="1" cellspacing="1">
											<tr class="dataTableOddRow">
												<TD></TD>
												<td><strong><h:outputText value="Sub-Task Configuration" /></strong></td>
											</tr>
										</TABLE>
										</div>
										</td>
									</tr>
									<tr>
										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv"
											style="height:165px; overflow: auto;"><h:dataTable
											cellspacing="1" id="subTaskAssociationDataTable"
											rendered="#{taskBackingBean.associatedSubTask != null}"
											value="#{taskBackingBean.associatedSubTask}"
											rowClasses="dataTableEvenRow,dataTableOddRow"
											var="singleValue" cellpadding="3" width="100%"
											bgcolor="#cccccc">
											<h:column>
												<h:inputText id="name" value="#{singleValue.entityName}"/>
												<h:inputHidden id="subTaskId" value="#{singleValue.entityId}"></h:inputHidden>
											</h:column>
											<h:column>
												<h:commandButton alt="Delete" id="delete"
												image="../../images/delete.gif" onclick="confirmDeletion(); return false;">
											</h:commandButton>
											</h:column>
										</h:dataTable></div>

										</td>
									</tr>
									<tr>
										<TD></TD>
									</tr>
									<tr>
										<TD></TD>
									</tr>

								</table>



								</fieldset>
								<br />
								
								</td>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>
						
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"	style="display:none; visibility: hidden;"><f:verbatim />
				</h:commandLink>
				<h:inputHidden id="selectedSubTaskIdForDelete" value= "#{taskSearchBackingBean.selectedSecurityId}"/> 
				<h:commandLink id="deleteSubTask" style="display:none; visibility: hidden;" action="#{taskSearchBackingBean.deleteSubTask}"> 
                                            <f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>	
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom_view.jsp"%>
			</TD>
		</TR>
	</table>
	</BODY>
</f:view>

<script>
initialize();
function initialize(){
	if(document.getElementById('viewSubTaskConfigurationForm:subTaskAssociationDataTable') != null) {
		setColumnWidth('headerTable','21%:51%');		
		setColumnWidth('viewSubTaskConfigurationForm:subTaskAssociationDataTable','21%:51%');
	}else {
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
	}
}
function confirmDeletion(){				
			var message = "Are you sure you want to delete?"	
			if(window.confirm(message)){	
				getFromDataTableToHidden('searchForm:searchResultTable','subTaskId','searchForm:selectedSubTaskIdForDelete');
			 	submitLink('searchForm:deleteTask');
			} 	
			else{			
				return false;
			}
      }
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="subTaskConfiguration" /></form>
</HTML>
