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

<TITLE>ProductComponentAssociationView</TITLE>
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
	<h:inputHidden id="Hidden"
		value="#{productComponentAssociationBackingBean.dummyVar}"></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD><h:form styleClass="form" id="viewComponentAssociationForm">
				<w:message></w:message>

				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD><w:message></w:message></TD>
								</TR>
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
										<TD class="tabNormal"><h:commandLink id="linkToGeneralInfo"
											action="#{productGeneralInformationBackingBean.loadGeneralInfo}">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="200">
								<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
									<TBODY>
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
												width="3" height="21"></TD>
											<TD class="tabActive">
												<h:outputText id="labelBC" value="Component Association"></h:outputText>
											</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="2" height="21"></TD>
										</TR>
									</TBODY>
								</TABLE>
								</TD>

								<td width="200" id="adminopttab">
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

								<TD width="100%" id="notestab">
								<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"
											onclick="loadNotes();return false;"><h:outputText
											style="cursor:hand;" id="generalInfoTable" value="Notes" />
										</TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>

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
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td></td>
							</tr>
							<tr>

								<td colspan="2" valign="top" class="ContentArea">
								<div id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Associated Benefit
								Components</div>
								<div id="InformationDiv"></div>




								<table class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td>
										<div id="resultHeaderDiv">
										<TABLE id="headerTable" width="100%" border="0"
											bgcolor="#cccccc" cellpadding="2" cellspacing="1">
											<tr class="dataTableOddRow">
												<td><strong><h:outputText value="Sequence Number" /></td>

												<td><strong><h:outputText value="Benefit Component" /></strong></td>

											</tr>
										</TABLE>
										</div>

										</td>
									</tr>
									<tr>
									<h:inputHidden id="hiddenComponentId"
										value="#{productComponentAssociationBackingBean.hiddenComponent}"></h:inputHidden>

										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv"
											style="height:261px; overflow: auto;"><h:dataTable
											cellspacing="1" id="bComponentDataTable"
											rendered="#{productComponentAssociationBackingBean.benefitComponentList != null}"
											value="#{productComponentAssociationBackingBean.benefitComponentList}"
											rowClasses="dataTableEvenRow,dataTableOddRow"
											var="singleValue" cellpadding="3" width="100%"
											bgcolor="#cccccc">
											<h:column>
												<h:outputText id="id_view" value="#{singleValue.sequence}"></h:outputText>
												<h:inputHidden id="id" value="#{singleValue.sequence}"></h:inputHidden>
												<%--  <h:inputText id="id" value="#{singleValue.sequence}"
													maxlength="3" onkeypress="return isNumberKey(event);"
													styleClass="sequenceNumberReadOnly" readonly="true" /> --%>
											</h:column>
											<h:column>
												<h:outputText id="name" value="#{singleValue.componentDesc}"></h:outputText>
												<h:inputHidden id="benefitComponentName"
													value="#{singleValue.componentDesc}"></h:inputHidden>
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
								<br />

								</td>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>
						<BR>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
									<td width="4%"></td>
									<td align="left"></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<TD>:</TD>
											<td><h:outputText
												value="#{productComponentAssociationBackingBean.stateOfObject}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<TD>:</TD>
											<td><h:outputText
												value="#{productComponentAssociationBackingBean.statusOfObject}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<TD>:</TD>
											<td><h:outputText
												value="#{productComponentAssociationBackingBean.versionOfObject}" /></td>
										</tr>
									</table>
									</td>
								</tr>
						</table>
						</fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:inputHidden id="hiddenProductKey"
					value="#{productGeneralInformationBackingBean.productKey}"></h:inputHidden>
				<h:inputHidden id="hiddenProductKeyValue"
					value="#{productNoteAssociationBackingBean.productKey}"></h:inputHidden>
				<h:commandLink id="notesLink"
					action="#{productNoteAssociationBackingBean.loadNotesForView}">
				</h:commandLink>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="hiddenProductType"
					value="#{productComponentAssociationBackingBean.mandateType}"></h:inputHidden>
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
	name="currentPrintPage" value="productComponentAssociation" /></form>
<script>
//initialize();
//function initialize(){
//	if(document.getElementById('viewComponentAssociationForm:bComponentDataTable') != null) {
//		setColumnWidth('headerTable','21%:51%:23%');		
//		setColumnWidth('viewComponentAssociationForm:bComponentDataTable','21%:51%:23%');
//	}else {
//		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
//		document.getElementById('searchResultdataTableDiv').style.height = '1px';
//		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
//	}
//}
function hideTable(){
	var tableObject = document.getElementById('viewComponentAssociationForm:bComponentDataTable');
	if(tableObject.rows.length > 0){
		var divBnftDefn = document.getElementById('noComponentDiv');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('searchResultdataTableDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noComponentDiv').style.visibility = "visible";
		document.getElementById('panel2Header').style.visibility = "hidden";
		document.getElementById('resultHeaderDiv').style.visibility = "hidden";
		document.getElementById('noComponentDiv').style.visibility = "hidden";



	}
}
//hideTable();

function loadNotes(){
	copyToHidden('viewComponentAssociationForm:hiddenProductKey','viewComponentAssociationForm:hiddenProductKeyValue');
	submitLink('viewComponentAssociationForm:notesLink');
}

// Script for Admin Option/Notes tab hide for mandate
var j;
j = document.getElementById("viewComponentAssociationForm:hiddenProductType").value;
if(j== "MANDATE")
{
	adminopttab.style.display='none';
	notestab.style.display='none';	
}else{
	adminopttab.style.display='';
	notestab.style.display='';
}

initialize();
	function initialize(){
	if(document.getElementById('viewComponentAssociationForm:bComponentDataTable').rows.length == 0){
				document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('panel2Header').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDiv').style.height = '1px';
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('InformationDiv').innerHTML = "<center><B>No Benefit Components Associated</B></center>";
			}
	else if(null != document.getElementById('viewComponentAssociationForm:bComponentDataTable')){
		var relTblWidth = document.getElementById('viewComponentAssociationForm:bComponentDataTable').offsetWidth;
		if(document.getElementById('viewComponentAssociationForm:bComponentDataTable').offsetHeight <= 100) {
				document.getElementById('viewComponentAssociationForm:bComponentDataTable').width = relTblWidth+"px";
				setColumnWidth('headerTable','21%:51%');	
				setColumnWidth('viewComponentAssociationForm:bComponentDataTable','21%:51%');
			}else{
				document.getElementById('headerTable').width = relTblWidth;
			setColumnWidth('headerTable','21%:51%');	
			setColumnWidth('viewComponentAssociationForm:bComponentDataTable','21%:51%');
			}		
			
		}
	}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productComponentAssociation" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
