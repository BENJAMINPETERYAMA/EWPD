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
	<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<h:inputHidden id="Hidden" ></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td>
			<h:form styleClass="form" id="formName">
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
											action="#{productComponentGeneralInfoBackingBean.retrieveBenefitComponent}"
											onmousedown="javascript:navigatePageAction(this.id);"
												id="genId">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab2">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{productComponentGeneralInfoBackingBean.loadAssociatedBenefits}"
											onmousedown="javascript:navigatePageAction(this.id);"
												id="benId">
											<h:outputText value="Benefits" />
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
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
							class="smallfont" border="0">
							<TBODY>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:outputText value="Name*"
										styleClass="#{productBenefitComponentNoteBackingBean.noteValdn?'mandatoryNormal': 'mandatoryError'}" />
									</td>
									<td width="24%" align="center">
									<div id="componentDataDiv" align="left"
										class="selectDataDisplayDiv"></div>
									</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;<h:commandButton
										image="../../images/select.gif" alt="Select"
										onclick="ewpdModalWindow_ewpd('../popups/notesPopUp.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=3&primaryEntityType='+primaryEntityType+'&temp='+Math.random(),'componentDataDiv','formName:componentData',3,1)
								;return false;"
										tabindex="1"></h:commandButton></td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td height="6"></td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:commandButton
										styleClass="wpdButton" type="submit" value="Attach Notes"
										id="saveButton"
										onclick="unsavedDataFinder(this.id);return false;"
										tabindex="2"></h:commandButton></td>
									<td width="24%" align="center">&nbsp;</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;</td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td>&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE id="newTable" width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
								<DIV id="noNotesDiv"
									style="font-family:Verdana, Arial, Helvetica, sans-serif;
								font-size:11px;font-weight:bold;text-align:left;color:#000000; 
								background-color:#FFFFFF;">No Notes Available</DIV>
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
														<TD align="left" width="41%"><h:outputText
													value="Name"></h:outputText></TD>
												<TD align="left" width="25%"><h:outputText
													value="Actions "></h:outputText></TD>
												<TD align="left" width="21%"><h:outputText
													value="Delete "></h:outputText></TD>
										
											</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<td colspan="4">

								<DIV id="searchResultdataTableDiv"
									style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{productBenefitComponentNoteBackingBean.noteList != null}"
									value="#{productBenefitComponentNoteBackingBean.noteList}"
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
											rendered="#{productBenefitComponentNoteBackingBean.securityAccess}"
											image="../../images/view.gif"
											onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+viewNoteVersion+'&temp=' + Math.random(),'dummyDiv','formName:dummyHidden',1,1);return false;"></h:commandButton>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<!-- end: for editing the selected details -->
									</h:column>
									<h:column>
											<h:selectBooleanCheckbox id="deleteChkBox" onclick="enableDisableDelete('formName:searchResultTable',2,0,'formName:deleteButton');"> </h:selectBooleanCheckbox>
									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>
							
							<tr>
							
								<td><BR>
								<h:commandButton id="deleteButton" styleClass="wpdButton" value="Delete"  onclick="unsavedDataFinder(this.id);return false;"></h:commandButton> <BR>
								<BR>
								</td>
							</tr>
						</TABLE>
						<!--	End of Page data	-->
						
				</table>

				<DIV id="dummyDiv" style="visibility:hidden"></DIV>


				<!-- Space for hidden fields -->
				<h:inputHidden id="componentData"
					value="#{productBenefitComponentNoteBackingBean.noteString}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreNoteId"
					value="#{productBenefitComponentNoteBackingBean.benefitComponentNoteId}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreVersion"
					value="#{productBenefitComponentNoteBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{productBenefitComponentNoteBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productBenefitComponentNoteBackingBean.deleteNote}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="saveLink"
								style="display:none; visibility: hidden;"
								action="#{productBenefitComponentNoteBackingBean.saveNote}">
				</h:commandLink>
				<h:inputHidden id="viewNoteId"
					value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden>
				<h:inputHidden id="viewNoteName"
					value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="viewNoteVersion"
					value="#{notesAttachmentBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="dummyHidden"></h:inputHidden>
				<h:inputHidden id="panelData"  />
				<h:inputHidden id="inputTextData"  />
				<h:inputHidden id="notesToDelete" value="#{productBenefitComponentNoteBackingBean.deleteNoteIds}"></h:inputHidden>
				<!-- End of hidden fields  -->

			</h:form>
		</td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productComponentNotes" /></form>
<script>
	IGNORED_FIELD1='formName:duplicateData';
	IGNORED_FIELD2='formName:panelData';
	IGNORED_FIELD3='formName:inputTextData';
	copyHiddenToDiv_ewpd('formName:componentData','componentDataDiv',3,1); 
	enableDisableDelete('formName:searchResultTable',2,0,'formName:deleteButton');
	var entityType = 'ATTACHCOMP';
	var lookUpAction = 3;
	var primaryEntityType = 'product';
	var noteIdForView; 
	var noteNameForView;
	var viewNoteVersion;
		
		
	//Hide table if no value is present

	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:noteId');
		var relTblWidth = document.getElementById('formName:searchResultTable').offsetWidth;
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noNotesDiv').style.visibility = 'visible';
		}else{
			document.getElementById('noNotesDiv').style.visibility = 'hidden';
			if(document.getElementById('formName:searchResultTable').rows.length < 8){
				document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
				document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
				setColumnWidth('formName:searchResultTable', '41%:25%:21%');
		 		setColumnWidth('resultHeaderTable', '41%:25%:21%')
			}else{
				document.getElementById('resultHeaderDiv').style.width = relTblWidth-17+"px";
				setColumnWidth('formName:searchResultTable', '41%:25%:21%');
		 		setColumnWidth('resultHeaderTable', '41%:25%:21%')
			}
				document.getElementById(divId).style.visibility = 'visible';
				setColumnWidth('formName:searchResultTable', '41%:25%:21%');
		 		setColumnWidth('resultHeaderTable', '41%:25%:21%');
		}
	}

	

// For Deletion
	function confirmDeletion(){				
		var message = "Are you sure you want to hide the notes?"		
		if(window.confirm(message)){		

			//submitDataTableButton('formName:searchResultTable', 'noteId', 'formName:targetHiddenToStoreNoteId', 'formName:deleteBenefitComponentNotes');
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

	function checkForOnLoadData(){
		var tableObject = document.getElementById('formName:searchResultTable');
		if(null != tableObject){
			if(tableObject.rows.length >0){
				var onLoadPanelData = getPanelData('formName:searchResultTable');
				document.getElementById('formName:panelData').value = onLoadPanelData;
			}
		}
		if(null != document.getElementById('formName:componentData')){
			var inputTexts = checkForTextData();
			document.getElementById('formName:inputTextData').value = inputTexts;
		}

	}
	
	function getPanelData(list){
      var tagNames = list.split(',');
      var dataOnScreen = "";
      var tableObject = document.getElementById(tagNames[0]);
      var rows = tableObject.rows.length;
      if(rows >0){
            var columns = tableObject.rows[0].cells.length;
            for(var i=0;i<rows;i++){
                  for(var j=0;j<columns;j++){
                        if(null != tableObject.rows[i].cells[j].children[0]){
                              if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].checked);
                              }else{
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML);       
                              }
                        }
                  }
            }
            return dataOnScreen;
      }else{
            return dataOnScreen;
      }
}
	function checkForTextData(){
		var inputTexts = document.getElementById('formName:componentData').value; 
		return inputTexts;
	
	}


	function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to hide the notes?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('formName:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;

		delNoteId = getObj('formName:notesToDelete');
		delNoteId.value = '';
		var noteCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			if(cur_row.cells[2].children[0].checked) {
				if(noteCount != 0) 
					delNoteId.value += '~';
				delNoteId.value += cur_row.cells[0].children[0].value;
				noteCount ++;
			}
		}
		if(noteCount > 0)
			return true;
		return false;
	}


	
 	function unsavedDataFinder(objectId){
		var buttonId = objectId;
		if(buttonId == 'formName:saveButton'){
		var tableObject = document.getElementById('formName:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('formName:searchResultTable');
			if(document.getElementById('formName:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('formName:saveLink');	
				}
			}else{
				submitLink('formName:saveLink');
			}
		}else{
			submitLink('formName:saveLink');
		}
		}else if (buttonId == 'formName:deleteButton'){
			var textData = checkForTextData();
			if(document.getElementById('formName:inputTextData').value != textData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
						if(confirmDelete()){
							submitLink('formName:deleteLink');	
						}
				}
			}else{
				if(confirmDelete()){
							submitLink('formName:deleteLink');	
				}
			}
		}
			
	}		

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('formName:duplicateData').value == ''){
		document.getElementById('formName:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForOnLoadData();
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('formName:duplicateData').value;
</script>
</HTML>








