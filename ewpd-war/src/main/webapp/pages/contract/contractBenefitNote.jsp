<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractBenefitNote.java" --%><%-- /jsf:pagecode --%>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<h:inputHidden id="Hidden" value="#{contractBenefitNotesBackingBean.loadNotesForPrint}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="formName">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">

						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="contractTree.jsp"></jsp:include></DIV>


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
											action="#{contractBenefitGeneralInfoBackingBean.displayStandardBenefitGeneralInfo}"
											onmousedown="javascript:navigatePageAction(this.id);" id="thisId">
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
											action="#{contractCoverageBackingBean.getCoverage}"
											onmousedown="javascript:navigatePageAction(this.id);" id="coverageId">
											<h:outputText value="#{contractCoverageBackingBean.benefitTypeTab}"  />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								
								<td width="200"  id="tab2">
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
								<td width="200" id="tab3">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitMandateBackingBean.loadMandateInfo}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
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
									<td width="16%" align="left"><h:outputText value=" Name*" 
									styleClass="#{contractBenefitNotesBackingBean.noteValdn?'mandatoryNormal': 'mandatoryError'}"/>
									</td>
									<td width="24%" align="center">
									<div id="componentDataDiv" align="left"
										class="selectDataDisplayDiv"></div>
									</td>
									<td></td>
									<td width="8%" align="left" style="cursor:hand"><h:commandButton
										image="../../images/select.gif" alt="Select"
										onclick="ewpdModalWindow_ewpd('../contractpopups/contractBenefitComponentNotesPopup.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=3&primaryEntityType='+primaryEntityType+'&temp='+Math.random(),'componentDataDiv','formName:componentData',3,1);return false;"
										></h:commandButton></td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td height="6"></td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:commandButton
										styleClass="wpdButton" type="submit" value="Attach Notes"
										id="saveButton"
										action="#{contractBenefitNotesBackingBean.saveNote}"
										></h:commandButton></td>
									<td width="24%" align="center">&nbsp;</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;</td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td>&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
										<DIV id="noContractNote"
									     style="font-family:Verdana, Arial, Helvetica, sans-serif;
										 font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
										 background-color:#FFFFFF;">No Notes Attached.
									</DIV>
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
											<TD align="left"><h:outputText value=" Note ID "></h:outputText>
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
									style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> 
									
									<h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{contractBenefitNotesBackingBean.noteList != null}"
									value="#{contractBenefitNotesBackingBean.noteList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

									<h:column>
										<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
										<h:inputHidden id="version" value="#{singleValue.version}"></h:inputHidden>
										<h:inputHidden id="noteNameHidden"
											value="#{singleValue.noteName}"></h:inputHidden>
										<h:outputText id="notesName" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteName}">
										</h:outputText>
									</h:column>
									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<!-- start: for viewing the selected details -->
										<h:commandButton alt="View Note" id="viewButton"  rendered="#{contractBenefitNotesBackingBean.securityAccess}" 
											image="../../images/view.gif"
											onclick="viewAction();return false;"></h:commandButton>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<!-- end: for editing the selected details -->
										<h:commandButton alt="Hide Note" id="deleteButton"
											image="../../images/delete.gif"
											onclick="confirmDeletion(); return false;"></h:commandButton>
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
					value="#{contractBenefitNotesBackingBean.benefitComponentNoteId}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreVersion"
					value="#{contractBenefitNotesBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="dummyHidden" ></h:inputHidden>
				<h:inputHidden id="componentData"
					value="#{contractBenefitNotesBackingBean.noteString}"></h:inputHidden>
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{contractBenefitNotesBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="deleteBenefitComponentNotes"
					style="display:none; visibility: hidden;"
					action="#{contractBenefitNotesBackingBean.deleteNote}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			
				
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
	name="currentPrintPage" value="contractBenefitNotesPrint" /></form>
<script>
IGNORED_FIELD1='formName:duplicateData';
 copyHiddenToDiv_ewpd('formName:componentData','componentDataDiv',3,1); 
 var noteIdForView; 
  var noteNameForView; 
 	var primaryEntityType = 'contract';
	var entityType = 'ATTACHBENEFIT' ;
	var lookUpAction = 3;
	var viewNoteVersion;
 
	
	//setColumnWidth('formName:searchResultTable', '60%:40%');
	//setColumnWidth('resultHeaderTable', '40%:60%');
	
	//Hide table if no value is present
//copyHiddenToDiv_ewpd('formName:componentData','componentDataDiv',2,2); 
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noContractNote').style.visibility = 'hidden';
		}
	}

// For Deletion
	function confirmDeletion(){				
		var message = "Are you sure you want to detach the note?"		
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

function getFromDataTable(tableId,tableFieldId){
		
		var e = window.event;
		if(!e || e==undefined) {
			return false;
		}
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];

		var sourceFieldId = tableId + ':' + rowcount + ':' + tableFieldId;

		return document.getElementById(sourceFieldId).value;
		
	
}

	function viewAction(){

		noteIdForView = getFromDataTable('formName:searchResultTable','noteId');
		noteNameForView = getFromDataTable('formName:searchResultTable','noteNameHidden');
		viewNoteVersion = getFromDataTable('formName:searchResultTable','version');
		ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+viewNoteVersion+'&temp=' + Math.random(),'dummyDiv','formName:dummyHidden',1,1);
    
  }

disbaleAttatchButton('searchResultdataTableDiv');
	function disbaleAttatchButton(divId){	
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:noteId');
		if(hiddenIdObj == null ){
			document.getElementById('formName:saveButton').disabled = false;

		}
		else{
			document.getElementById('formName:saveButton').disabled = true;			
		}
		
	}
	i = document.getElementById("formName:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tab2.style.display='none';
	tab3.style.display='';
	}else{
	tab2.style.display='';
	tab3.style.display='none';
	}

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('formName:duplicateData').value == ''){
		document.getElementById('formName:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	}else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('formName:duplicateData').value;
	}
</script>
</HTML>








