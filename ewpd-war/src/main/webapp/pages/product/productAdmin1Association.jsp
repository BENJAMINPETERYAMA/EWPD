


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

<TITLE>AdminAssociation</TITLE>
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
<!-- WAS 7.0 Changes - Hidden id hiddenInit value loaded using binding instead of value -->

<f:view>
	<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<h:inputHidden id="Hidden"
		value="#{productAdminAssociationBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1"
			binding="#{productAdminAssociationBackingBean.hiddenInit}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="formName">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


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
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
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
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productComponentAssociationBackingBean.loadComponent}">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Admin Option" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productNoteAssociationBackingBean.loadNotes}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200"></td>
								<td width="100%"></td>
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
									<td>&nbsp;</td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:outputText value="Component" />
									</td>
									<td width="24%" align="center">
									<div id="adminDataDiv" align="left"
										class="selectDataDisplayDiv"></div>
									</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;&nbsp;&nbsp;<h:commandButton
										image="../../images/select.gif" alt="Select"
										onclick="ewpdModalWindow_ewpd('productAdminPopup.jsp'+getUrl()+'?temp='+Math.random(),'adminDataDiv','formName:adminData',2,2)
								;return false;" tabindex = "1"></h:commandButton>
									</td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td height="2"></td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:commandButton
										styleClass="wpdButton" type="submit" value="Save"
										id="saveButton"
										action="#{productAdminAssociationBackingBean.saveProductAdmin}" tabindex = "2"></h:commandButton></td>
									<td width="24%" align="center">&nbsp;</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;</td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<td colspan="4">
									<fieldset
										style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<DIV id="noBenefitComp"
										style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000; height:292px;
						background-color:#FFFFFF;">No
									Admin Associated.</DIV>
									<div id="dataTableDiv"
										style="height:292px; overflow:auto; width:100%;">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="left" width="100%" height="21" bgcolor="#cccccc"><h:outputText
												value="Associated Benefit Components"
												styleClass="outputText" /></td>
										</TR>
										<tr>
											<td>
											<table bgcolor="#cccccc" width="100%" cellpadding="0"
												cellspacing="1" id="headert">
												<tr>
													<td width="15%" class="dataTableOddRow" align="left"><h:commandButton
														type="submit" value="Update" styleClass="wpdButton"
														id="updateButton"
														rendered="#{productAdminAssociationBackingBean.emptyList}"
														action="#{productAdminAssociationBackingBean.updateBenefitComponents}"></h:commandButton>
													</td>
													<td width="57%" class="dataTableColumnHeader"><h:outputText
														value="Benefit Component" styleClass="outputText" />
													<td width="28%" class="dataTableOddRow">&nbsp;</td>
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td>
											<div style="height:245px;overflow:auto;"><h:panelGrid
												id="panelTable" columns="3" width="685"
												styleClass="outputText" cellpadding="1" cellspacing="1"
												bgcolor="#cccccc"
												binding="#{productAdminAssociationBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow">
											</h:panelGrid></div>
											</td>
										</tr>
									</table>
									</div>
									</fieldset>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</TBODY>
						</TABLE>

						<!--	End of Page data	--></fieldset>

						<table width="100%">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
								<table border="0" width="100%" class="tableBorder">
									<tr>
										<td width="2%">&nbsp;</td>
										<td width="72%"><h:selectBooleanCheckbox id="checkall"
											value="#{productAdminAssociationBackingBean.checkin}"
											 tabindex = "3">
										</h:selectBooleanCheckbox> <h:outputText value="Check In"
											></h:outputText>

										</td>
										<td width="26%">
										<table Width=100%>
											<tr>
												<td>State</td>
												<td>:<h:outputText
													value="#{productAdminAssociationBackingBean.state}"
													styleClass="outputText" /></td>
											</tr>
											<tr>
												<td>Status</td>
												<td>:<h:outputText
													value="#{productAdminAssociationBackingBean.status}"
													styleClass="outputText" /></td>
											</tr>
											<tr>
												<td>Version</td>
												<td>:<h:outputText
													value="#{productAdminAssociationBackingBean.version}"
													styleClass="outputText" /></td>
											</tr>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><h:commandButton value="Done" styleClass="wpdButton"
									id="doneButton"
									action="#{productAdminAssociationBackingBean.done}"
									 tabindex = "4">
								</h:commandButton></td>
							</tr>
						</table>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="rowId"
					value="#{productAdminAssociationBackingBean.rowId}"></h:inputHidden>
				<h:inputHidden id="adminData"
					value="#{productAdminAssociationBackingBean.adminString}"></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productAdminAssociationBackingBean.deleteAdmin}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productAdminAssociation" /></form>
<script>

if(getObj('formName:panelTable') != null) {
	if (getObj('formName:panelTable').rows.length > 0)
		syncTables('headert','formName:panelTable');
}
function getRow(row){
document.getElementById('formName:rowId').value = row;
submitLink('formName:deleteLink');
return true;
}
function deleteConfirm(){
			var message = 'Are you sure you want to delete the Admin?';
				if (confirm(message) ){
					return true;
				} else
				return false;
			}
setColumnWidth('formName:panelTable','15%:57%:28%');
copyHiddenToDiv_ewpd('formName:adminData','adminDataDiv',2,2); 
function hideTable(){
	var tableObject = document.getElementById('formName:panelTable');
	if(tableObject.rows.length > 0){
		var divBnftDefn = document.getElementById('noAdmin');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('dataTableDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noAdmin').style.visibility = "visible";
	}
}
hideTable();
</script>
</HTML>




