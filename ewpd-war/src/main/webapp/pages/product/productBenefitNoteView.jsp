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
<TITLE>Note Attachment</TITLE>
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
	<BODY>
	<h:inputHidden id="Hidden" ></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>
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
											action="#{productBenefitGeneralInfoBackingBean.getProductBenefitGenaralInfo}">
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
											action="#{productBenefitDetailBackingBean.getBenefitDefinitionsPage}">
											<h:outputText value="Standard Definition" />
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
										<td width="186" class="tabActive"><h:outputText value="Notes" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
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
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD><BR />
								<div id="InformationDiv"></div>
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><strong><h:outputText value="Associated Notes"></h:outputText></strong></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left"><h:outputText value="Note Name "></h:outputText>
											</TD>

											</TbR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<td colspan="4">

								<DIV id="searchResultdataTableDiv"
									style="height:350px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{productBenefitNoteBackingBean.noteList != null}"
									value="#{productBenefitNoteBackingBean.noteList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

									<h:column>
										<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
										<h:inputHidden id="version" value="#{singleValue.version}"></h:inputHidden>
										<h:inputHidden id="noteNameHidden"
											value="#{singleValue.noteName}"></h:inputHidden>
										<h:outputText id="notesName" value="#{singleValue.noteName}">
										</h:outputText>
									</h:column>
									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<!-- start: for viewing the selected details -->
										<h:commandButton alt="View Note" id="viewButton"
											rendered="#{productBenefitNoteBackingBean.securityAccess}"
											image="../../images/view.gif"
											onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+viewNoteVersion+'&temp=' + Math.random(),'dummyDiv','formName:dummyHidden',1,1);return false;"></h:commandButton>

									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>
						</TABLE>



						<!--	End of Page data	-->
						</TD>
					</TR>
				</table>
				<DIV id="dummyDiv" style="visibility:hidden"></DIV>

				<!-- Space for hidden fields -->
				
				<h:inputHidden id="viewNoteId" value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden>
				<h:inputHidden id="viewNoteName" value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="viewNoteVersion" value="#{notesAttachmentBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreNoteId"
					value="#{productBenefitNoteBackingBean.benefitComponentNoteId}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreVersion"
					value="#{productBenefitNoteBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="dummyHidden" ></h:inputHidden>
				<h:inputHidden id="componentData"
					value="#{productBenefitNoteBackingBean.noteString}"></h:inputHidden>

				<h:commandLink id="deleteBenefitComponentNotes"
					style="display:none; visibility: hidden;"
					action="#{productBenefitNoteBackingBean.deleteNote}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			
				
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
	name="currentPrintPage" value="productBenefitNotes" /></form>
<script>

 
 var noteIdForView; 
  var noteNameForView; 
 	var primaryEntityType = 'product';
	var entityType = 'ATTACHBENEFIT' ;
	var lookUpAction = 3;
	var viewNoteVersion;
 
	
	setColumnWidth('formName:searchResultTable', '60%:40%');
	setColumnWidth('resultHeaderTable', '40%:60%');
	
	//Hide table if no value is present
//copyHiddenToDiv_ewpd('formName:componentData','componentDataDiv',2,2); 
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}

// For Deletion
	function confirmDeletion(){				
		var message = "Are you sure you want to hide the notes?"		
		if(window.confirm(message)){		

		//	submitDataTableButton('formName:searchResultTable', 'noteId', 'formName:targetHiddenToStoreNoteId', 'formName:deleteBenefitComponentNotes');
			getFromDataTableToHidden('formName:searchResultTable', 'noteId', 'formName:targetHiddenToStoreNoteId');
			getFromDataTableToHidden('formName:searchResultTable', 'version', 'formName:targetHiddenToStoreVersion');
			submitLink('formName:deleteBenefitComponentNotes');
		}
		else{			
				return false;
		}
	}

	function viewAction(){
 		getFromDataTableToHidden('formName:searchResultTable','noteId','formName:viewNoteId');
		getFromDataTableToHidden('formName:searchResultTable','noteNameHidden','formName:viewNoteName');
		getFromDataTableToHidden('formName:searchResultTable','version','formName:viewNoteVersion');
		noteIdForView = document.getElementById('formName:viewNoteId').value;
		noteNameForView = document.getElementById('formName:viewNoteName').value;
		viewNoteVersion = document.getElementById('formName:viewNoteVersion').value;

  }

initialize();
	function initialize(){
	if(null != document.getElementById('formName:searchResultTable')){
		if(document.getElementById('formName:searchResultTable').rows.length == 0){
				document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDiv').style.height = '1px';
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('InformationDiv').innerHTML = "<center><B>No Notes Attached.</B></center>";
			}
		}
	}
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>








