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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
}.shortDescriptionColumn {
	width: 90%;
}
</style>
<TITLE>AdminAssociationView</TITLE>
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
	<h:inputHidden id="Hidden"
		value="#{productAdminAssociationBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1"
			value="#{productAdminAssociationBackingBean.hiddenInit}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include>
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
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Admin Option" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="100%">
								<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD align="left" width="3"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal" onclick="loadNotes();return false;"
											width="97%"><h:outputText style="cursor:hand;"
											id="generalInfoTable" value="Notes" /></TD>
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
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
							class="smallfont" border="0">
							<TBODY>


								<TR>
									<td colspan="4">
									<TABLE width="100%" cellspacing="0" cellpadding="0">


										<TR>
											<TD><BR /><br><br>
											<div id="InformationDiv"></div>
											<DIV id="resultHeaderDiv">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%">
												<TR>
													<TD height=15><b>&nbsp;<h:outputText value="Associated Admin Options"></h:outputText>
													</b></TD>
												</TR>
											</TABLE>
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="left"><h:outputText value="Sequence Number"></h:outputText>
														</TD>
														<TD align="left"><h:outputText value="Admin Options"></h:outputText>
														</TD>
												</TBODY>
											</TABLE>
											</DIV>
											</TD>
										</TR>

										<tr>

										<h:inputHidden id="hiddenAdminId"
										value="#{productAdminAssociationBackingBean.hiddenAdmin}"></h:inputHidden>
											<td>
											<DIV id="searchResultdataTableDiv"
												style="height:250px;position:relative;z-index:1;font-size:10px;overflow:auto;">
											<!-- Search Result Data Table --> <h:dataTable
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" bgcolor="#cccccc"
												rendered="#{productAdminAssociationBackingBean.adminList != null}"
												value="#{productAdminAssociationBackingBean.adminList}"
												rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
												columnClasses="selectOrOptionColumn,shortDescriptionColumn">
												<h:column>
												
													<h:outputText id="seqid_view" value="#{singleValue.sequence}"></h:outputText>
													<h:inputHidden id="seqid" value="#{singleValue.sequence}"></h:inputHidden>
													<%-- <h:inputText id="seqid" value="#{singleValue.sequence}"
														maxlength="3" 
														styleClass="sequenceNumberReadOnly" readonly="true" /> --%>
												</h:column>
												<h:column>
												
													<h:inputHidden id="adminId" value="#{singleValue.adminKey}"></h:inputHidden>
													<h:outputText id="adminDesc"
														value="#{singleValue.adminDesc}">
													</h:outputText>
												</h:column>



											</h:dataTable></DIV>
											</td>
										</tr>
									</TABLE>


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
										<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productAdminAssociationBackingBean.state}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productAdminAssociationBackingBean.status}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productAdminAssociationBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						</FIELDSET>
						<BR>
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
									<td width="1%"><f:verbatim /></td>
									<td width="6%"></td>
									<td width="93%"><f:verbatim /></td>
								</tr>
							</TBODY>
						</TABLE>

						</TD>
					</TR>
				</TABLE>

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
				<h:inputHidden id="hiddenProductKey"
					value="#{productGeneralInformationBackingBean.productKey}"></h:inputHidden>
				<h:inputHidden id="hiddenProductKeyValue"
					value="#{productNoteAssociationBackingBean.productKey}"></h:inputHidden>
				<h:commandLink id="notesLink"
					action="#{productNoteAssociationBackingBean.loadNotesForView}">
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
	name="currentPrintPage" value="productAdminOption" /></form>
<script>

hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:adminId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('formName:searchResultTable', '40%:60%');
	 		setColumnWidth('resultHeaderTable', '40%:60%');
		}
	}	
initialize();
	function initialize(){
	if(null != document.getElementById('formName:searchResultTable')){

var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
if(document.getElementById('formName:searchResultTable').offsetHeight <= 250)
{
	document.getElementById('formName:searchResultTable').width = relTblWidth+"px";
	document.getElementById('resultHeaderTable').width = relTblWidth+"px";
	setColumnWidth('resultHeaderTable','20%:80%');
	setColumnWidth('formName:searchResultTable','20%:80%');
}
else
{
	document.getElementById('formName:searchResultTable').width = (relTblWidth-17)+"px";
	document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('resultHeaderTable','20%:80%');
	setColumnWidth('formName:searchResultTable','20%:80%');
}
		if(document.getElementById('formName:searchResultTable').rows.length == 0){
				document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDiv').style.height = '1px';
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('InformationDiv').innerHTML = "<center><B>No Admin Options Available</B></center>";
			}
		}
	}	
function loadNotes(){
	copyToHidden('formName:hiddenProductKey','formName:hiddenProductKeyValue');
	submitLink('formName:notesLink');
}



function confirmDeletion(){				
		var message = "Are you sure you want to hide the Admin Option?"		
		if(window.confirm(message)){		

			submitDataTableButton('formName:searchResultTable', 'adminId', 'formName:adminData', 'formName:deleteLink');
			
		}
		else{			
				return false;
		}
	}

//if(getObj('formName:panelTable') != null) {
//	if (getObj('formName:panelTable').rows.length > 0)
//		syncTables('headert','formName:panelTable');
//}
//function getRow(row){
//document.getElementById('formName:rowId').value = row;
//submitLink('formName:deleteLink');
//return true;
//}
//function deleteConfirm(){
//			var message = 'Are you sure you want to delete the Admin?';
//				if (confirm(message) ){
//					return true;
//				} else
//				return false;
//			}



//setColumnWidth('formName:panelTable','15%:57%:28%');

//function hideTable(){
//	var tableObject = document.getElementById('formName:panelTable');
//	if(tableObject.rows.length > 0){
//		var divBnftDefn = document.getElementById('noAdminDiv');
//		divBnftDefn.style.visibility = "hidden";
//		divBnftDefn.style.height = "2px";
//	}else{
//		var divObj = document.getElementById('dataTableDiv');
//		divObj.style.visibility = "hidden";
//		divObj.style.height = "2px";
//		document.getElementById('noAdminDiv').style.visibility = "visible";
//	}
//}
//hideTable();
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>




