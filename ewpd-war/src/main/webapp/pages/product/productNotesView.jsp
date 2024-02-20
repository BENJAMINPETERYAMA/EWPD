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
<base target=_self>
<TITLE>Note View</TITLE>
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
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<h:inputHidden id="Hidden" ></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="formName">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="200" valign="top" class="leftPanel">

						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>


						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="141" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productGeneralInformationBackingBean.loadGeneralInfo}">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productComponentAssociationBackingBean.loadComponent}">
											<h:outputText value="Component Association" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productAdminAssociationBackingBean.loadComponent}">
											<h:outputText value="Admin Option" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText value="Notes" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.displayDenialAndExclusionTab}">
											<h:outputText value="Rules" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
							class="smallfont" border="0">
							<TBODY>
									<TR>
											<td>&nbsp;
											</td>
									</TR>
								<TR>
									<td colspan="4"> <DIV id="noBenefitComp"
										style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000; height:292px;
						background-color:#FFFFFF;"><br><br><br>No
									Notes Attached.</DIV> 

									<div id="dataTableDiv"
										style="height:292px; overflow:auto; width:100%;">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="left" width="100%" height="21" bgcolor="#cccccc"><strong><h:outputText
												value="Associated Notes" styleClass="outputText" /></strong></td>
										</TR>
										<tr>
											<td>
											<table bgcolor="#cccccc" width="100%" cellpadding="3"
												cellspacing="1" id="headert">
												<tr>
													<td width="70%" class="dataTableColumnHeader"><h:outputText
														value="Note Name" styleClass="outputText" />
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td>
											<div style="height:245px;overflow:auto;"><h:panelGrid
												id="panelTable" columns="2" width="678"
												styleClass="outputText" cellpadding="3" cellspacing="1"
												bgcolor="#cccccc"
												binding="#{productNoteAssociationBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow">
											</h:panelGrid></div>
											</td>
										</tr>
									</table>
									</div>

									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</TBODY>
						</TABLE>

						<!--	End of Page data	--></fieldset>

						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
										<td width="4%"></td>
										<td align="left"></td>
										<td align="left" width="127">
									<table width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productNoteAssociationBackingBean.state}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productNoteAssociationBackingBean.status}" /></td>
										</tr>
										<tr>
												<td><h:outputText value="Version" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productNoteAssociationBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						</FIELDSET>
						<BR>
						</TD>
					</TR>
				</TABLE>
				<DIV id="dummyDiv" style="visibility:hidden"></DIV>

				<!-- Space for hidden fields -->
				<h:inputHidden id="rowId"
					value="#{productNoteAssociationBackingBean.rowId}"></h:inputHidden>
				<h:inputHidden id="dummyHidden"></h:inputHidden>
				<h:inputHidden id="componentData"
					value="#{productNoteAssociationBackingBean.noteString}"></h:inputHidden>

				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productNoteAssociationBackingBean.deleteNote}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productNotes" /></form>
<script>
var entityType = 'ATTACHPRODUCT';
var lookUpAction = 2;
 var noteIdForView; 
 var noteNameForView; 

if(getObj('formName:panelTable') != null) {
	if (getObj('formName:panelTable').rows.length > 0)
		syncTables('headert','formName:panelTable');
}

function hideTable(){
	var tableObject = document.getElementById('formName:panelTable');
	if(tableObject.rows.length > 0){
		var divBnftDefn = document.getElementById('noBenefitComp');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('dataTableDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noBenefitComp').style.visibility = "visible";
	}
}
hideTable();
setColumnWidth('formName:panelTable','70%:30%');


</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>




