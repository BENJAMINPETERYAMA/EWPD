<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Benefit Override Notes</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="standardBenefitNotesOverrideForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel"><jsp:include
								page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{BenefitComponentNotesBackingBean.validationMessages}"></w:message>

										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabNormal"><h:commandLink id ="generalInfoLink"
												action="#{benefitComponentCreateBackingBean.loadBenefitComponentforView}"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value=" General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink id ="StandardDefenitionLink"
												action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinition}"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Standard Definition" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>



									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive">
												<h:outputText value=" Notes " />
											</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">



								<TR>
									<TD valign="top" width="137"><h:outputText value="Name*"
										styleClass="#{BenefitComponentNotesBackingBean.requiredNoteName?'mandatoryError': 'mandatoryNormal'}"></h:outputText></TD>
									<TD align="left" width="200">
									<DIV id="benefitComponentNotesDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="hidden_notes"
										value="#{BenefitComponentNotesBackingBean.noteString}" /></TD>
									<TD align="left" valign="top" width="633"><h:commandButton
										image="../../../images/select.gif" alt="Select" tabindex="1"
										style="cursor:hand;" 
										onclick="ewpdModalWindow_ewpd( '../popups/notesPopUp.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=3&primaryEntityType='+primaryEntityType+ '&temp=' + Math.random(), 'benefitComponentNotesDiv', 'standardBenefitNotesOverrideForm:hidden_notes',3,1);return false;">
									</h:commandButton></TD>
								</TR>
								<tr>
									<TD width="110"></TD>
								</tr>

								<TR>
									
									<TD width="110"><h:commandButton id="saveButton" 
										styleClass="wpdButton" value="Attach" tabindex="2"
										onclick="unsavedDataFinder(this.id);return false;"
										></h:commandButton></TD>

								</TR>
							</TABLE>
							<TABLE id ="newTable" width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD><BR />
									<DIV id="noNotesDiv" style="font-family:Verdana, Arial, Helvetica, sans-serif;
									font-size:11px;font-weight:bold;text-align:left;color:#000000; 
									background-color:#FFFFFF;">No Notes Available</DIV>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="headerTable" border="0" width="100%">
										<TR>
											<TD><b> <h:outputText value="Associated Notes"></h:outputText>
											</b></TD>
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
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:280px;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList != null}"
										value="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

										<h:column>
											<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
											<h:inputHidden id="noteNameHidden"
												value="#{singleValue.noteName}"></h:inputHidden>
											<h:inputHidden id="noteVersionHidden"
												value="#{singleValue.version}"></h:inputHidden>
											<h:outputText id="notesName" value="#{singleValue.noteName}">
											</h:outputText>
										</h:column>
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<!-- start: for viewing the selected details -->
											<h:commandButton alt="View" id="viewButton"
												rendered="#{BenefitComponentNotesBackingBean.securityAccess}"
												image="../../images/view.gif" tabindex="3"
												onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+noteVersionForView+'&temp=' + Math.random(),'dummyDiv','standardBenefitNotesOverrideForm:dummyHidden',1,1);return false;"></h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<!-- end: for editing the selected details -->
											
										</h:column>
										<h:column>
											<h:selectBooleanCheckbox id="deleteChkBox" tabindex="3" onclick="enableDisableDelete('standardBenefitNotesOverrideForm:searchResultTable',2,0,'standardBenefitNotesOverrideForm:deleteButton');"> </h:selectBooleanCheckbox>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
								<tr>
								<td><h:commandButton id="deleteButton" styleClass="wpdButton" value="Delete" tabindex="4" onclick="unsavedDataFinder(this.id);return false;"></h:commandButton> </td>
							</tr>
							</TABLE>
							</FIELDSET>
							<BR>


							<DIV id="dummyDiv" style="visibility:hidden"></DIV>

							<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
								id="targetHiddenToStoreNoteId"
								value="#{BenefitComponentNotesBackingBean.benefitComponentNoteId}"></h:inputHidden>
							<h:commandLink id="deleteLink"
								style="display:none; visibility: hidden;"
								action="#{BenefitComponentNotesBackingBean.deleteStandardBenefitOverrideNotes}">
								<f:verbatim />
							</h:commandLink> 
							<h:commandLink id="saveLink"
								style="display:none; visibility: hidden;"
								action="#{BenefitComponentNotesBackingBean.AttachNotesForStandardBenefitOverride}">
							</h:commandLink>
							
							<h:inputHidden id="viewNoteId"
								value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden> 
							<h:inputHidden
								id="viewNoteName" value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
							<h:inputHidden id="viewNoteVersion"
								value="#{notesAttachmentBackingBean.version}"></h:inputHidden> 
							<h:inputHidden id="noteVersionForHide"
								value="#{BenefitComponentNotesBackingBean.noteVersion}"></h:inputHidden>
							<h:inputHidden id="duplicateData"
								value="#{notesAttachmentBackingBean.duplicateData}"></h:inputHidden>
							<h:inputHidden id="dummyHidden"></h:inputHidden>
							<h:inputHidden id="panelData"  />
							<h:inputHidden id="inputTextData"  />
							<h:inputHidden id="notesToDelete" value="#{BenefitComponentNotesBackingBean.deleteNoteIds}"> </h:inputHidden>
							 <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="JavaScript">	
IGNORED_FIELD1='standardBenefitNotesOverrideForm:duplicateData';
IGNORED_FIELD2='standardBenefitNotesOverrideForm:panelData';
IGNORED_FIELD3='standardBenefitNotesOverrideForm:inputTextData';
  	var noteIdForView; 
  	var noteNameForView; 
	var primaryEntityType = 'benftComp';
	var entityType = 'ATTACHBENEFIT' ;
	var lookUpAction = 3;
	
	copyHiddenToDiv_ewpd('standardBenefitNotesOverrideForm:hidden_notes','benefitComponentNotesDiv',3,1);
	enableDisableDelete('standardBenefitNotesOverrideForm:searchResultTable',2,0,'standardBenefitNotesOverrideForm:deleteButton');
	//Hide table if no value is present
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('standardBenefitNotesOverrideForm:searchResultTable:0:noteId');
		var relTblWidth = document.getElementById('newTable').offsetWidth;
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noNotesDiv').style.visibility = 'visible';
		}else{
			document.getElementById('noNotesDiv').style.visibility = 'hidden';
		if(document.getElementById('standardBenefitNotesOverrideForm:searchResultTable').rows.length < 13){
			document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
			document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
			setColumnWidth('standardBenefitNotesOverrideForm:searchResultTable', '41%:25%:21%');
	 		setColumnWidth('resultHeaderTable', '41%:25%:21%')
		}else{
			document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
			setColumnWidth('standardBenefitNotesOverrideForm:searchResultTable', '41%:25%:21%');
	 		setColumnWidth('resultHeaderTable', '41%:25%:21%')
		}
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('standardBenefitNotesOverrideForm:searchResultTable', '41%:25%:21%');
	 		setColumnWidth('resultHeaderTable', '41%:25%:21%');
		}
	}	
	
	
	function viewAction(){
 		getFromDataTableToHidden('standardBenefitNotesOverrideForm:searchResultTable','noteId','standardBenefitNotesOverrideForm:viewNoteId');
		getFromDataTableToHidden('standardBenefitNotesOverrideForm:searchResultTable','noteNameHidden','standardBenefitNotesOverrideForm:viewNoteName');				
		getFromDataTableToHidden('standardBenefitNotesOverrideForm:searchResultTable','noteVersionHidden','standardBenefitNotesOverrideForm:viewNoteVersion');	
		noteIdForView = document.getElementById('standardBenefitNotesOverrideForm:viewNoteId').value;
		noteNameForView = document.getElementById('standardBenefitNotesOverrideForm:viewNoteName').value;
		noteVersionForView = document.getElementById('standardBenefitNotesOverrideForm:viewNoteVersion').value;		
		
  }


	
	function checkForOnLoadData(){
		var tableObject = document.getElementById('standardBenefitNotesOverrideForm:searchResultTable');
		if(null != tableObject){
			if(tableObject.rows.length >0){
				var onLoadPanelData = getPanelData('standardBenefitNotesOverrideForm:searchResultTable');
				document.getElementById('standardBenefitNotesOverrideForm:panelData').value = onLoadPanelData;
			}
		}
		if(null != document.getElementById('standardBenefitNotesOverrideForm:hidden_notes')){
			var inputTexts = checkForTextData();
			document.getElementById('standardBenefitNotesOverrideForm:inputTextData').value = inputTexts;
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
		var inputTexts = document.getElementById('standardBenefitNotesOverrideForm:hidden_notes').value; 
		return inputTexts;
	
	}


	function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to hide the notes?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('standardBenefitNotesOverrideForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;

		delNoteId = getObj('standardBenefitNotesOverrideForm:notesToDelete');
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
		if(buttonId == 'standardBenefitNotesOverrideForm:saveButton'){
		var tableObject = document.getElementById('standardBenefitNotesOverrideForm:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('standardBenefitNotesOverrideForm:searchResultTable');
			if(document.getElementById('standardBenefitNotesOverrideForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('standardBenefitNotesOverrideForm:saveLink');	
				}
			}else{
				submitLink('standardBenefitNotesOverrideForm:saveLink');
			}
		}else{
			submitLink('standardBenefitNotesOverrideForm:saveLink');
		}
		}else if (buttonId == 'standardBenefitNotesOverrideForm:deleteButton'){
			var textData = checkForTextData();
			if(document.getElementById('standardBenefitNotesOverrideForm:inputTextData').value != textData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
						if(confirmDelete()){
							submitLink('standardBenefitNotesOverrideForm:deleteLink');	
						}
				}
			}else{
				if(confirmDelete()){
							submitLink('standardBenefitNotesOverrideForm:deleteLink');	
				}
			}
		}
			
	}		

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitOverriddenNotesPrint" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('standardBenefitNotesOverrideForm:duplicateData').value == ''){
		document.getElementById('standardBenefitNotesOverrideForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForOnLoadData();
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('standardBenefitNotesOverrideForm:duplicateData').value;
</script>
</HTML>
