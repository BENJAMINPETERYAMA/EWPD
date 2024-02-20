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

<TITLE>Benefit Notes</TITLE>
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
	<!--  hx:scriptCollector id="scriptCollector" -->
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD><h:form styleClass="form" id="benefitNotesForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv">
						<jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include>
						</DIV>							
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD><w:message
										value="#{standardBenefitNotesBackingBean.validationMessages}"></w:message>

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
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink id="benefitId"
											action="#{standardBenefitBackingBean.loadStandardBenefit}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="General Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								
								<TD width="25%">
	                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                      <tr>
	                                            <td width="3" align="left"><img
	                                                  src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
	                                            <td width="100%" class="tabNormal" align="center"><h:commandLink
	                                                  action="#{benefitDefinitionBackingBean.loadBenefitDefinition}"
	                                                  id="linkToStandardDefinition"
	                                                  onmousedown="javascript:navigatePageAction(this.id);">
	                                                  <h:outputText
	                                                        value="#{standardBenefitBackingBean.benefitTypeTab}" />
	                                            </h:commandLink></td>
	                                            <td width="2" align="right"><img
	                                                  src="../../images/tabNormalRight.gif" width="4" height="21" /></td>
	                                      </tr>
	                                </table>
	                                </TD>

								<TD width="25%" id="manTab">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink
											action="#{benefitMandateBackingBean.redirectToBenefitMandate}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="25%" id="noteTab">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText value="Notes " />
										</TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>


								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">



							<TR >	
									
									<TD valign="top" width="125" ><h:outputText value="Name*" 
									styleClass="#{standardBenefitNotesBackingBean.requiredNoteName?'mandatoryError': 'mandatoryNormal'}" ></h:outputText></TD>
									<TD  align="left" width="180"><h:inputText id="noteSearchCriteria" tabindex="1"
													styleClass="formInputField" onkeypress="return callNoteSelect();"></h:inputText>
									
									<TD align="left" valign="top" width="290"><h:graphicImage url="" value="../../../images/autoComplete.gif"
										alt="Select" style="cursor:hand;" width="15" height="15" onclick="noteSelect();return false;" >
										</h:graphicImage>
									</TD>
							</TR>
							<TR id="divRow" style="display:none;">
									<TD valign="top" width="125" ></TD>
									<TD  align="left" width="180"><DIV id="selectedNotesDiv" class="selectDataDisplayDiv"></DIV></TD>
							</TR>																										
								
							<tr>
								<TD width="110"></TD>
							</tr>

							<TR>
								<TD width="110">
									<h:commandButton id="saveButton" 
										styleClass="wpdButton" tabindex="2" value="Attach" 
										onclick="unsavedDataFinder(this.id);return false;"
										></h:commandButton>
								</TD>

							</TR>

						</TABLE>
						<br />
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><b><h:outputText value="Associated Notes"></h:outputText></b>
										</TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
														<TD align="left" width="70%"><h:outputText
													value="Name"></h:outputText></TD>
												<TD align="left" width="19%"><h:outputText
													value="Actions "></h:outputText></TD>
												<TD align="left" width="11%"><h:outputText
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
									style="height:290px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{standardBenefitNotesBackingBean.associatedNotesList != null}"
									value="#{standardBenefitNotesBackingBean.associatedNotesList}"
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
										<h:commandButton alt="View" id="viewButton" rendered="#{standardBenefitNotesBackingBean.securityAccess}"
											image="../../images/view.gif" tabindex="3"
											onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+noteVersionForView+'&temp=' + Math.random(),'dummyDiv','benefitNotesForm:dummyHidden',1,1);return false;" ></h:commandButton>
										<f:verbatim>  &nbsp;  </f:verbatim>
									</h:column>
									<h:column>
										<h:selectBooleanCheckbox id="deleteChkBox" tabindex="3"
										onclick="enableDisableDelete('benefitNotesForm:searchResultTable',2,0,'benefitNotesForm:deleteButton');" > </h:selectBooleanCheckbox>
									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>
							<tr>

								<td><h:commandButton id="deleteButton" tabindex="4" styleClass="wpdButton" value="Delete"  onclick="unsavedDataFinder(this.id);return false;"></h:commandButton> </td>

							</tr>
						</TABLE>

						</FIELDSET>
						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"><h:selectBooleanCheckbox id="checkall"
									value="#{standardBenefitBackingBean.checkin}" tabindex="5" >
								</h:selectBooleanCheckbox></td>
								<td align="left"><h:outputText value="Check In" /></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<td>:&nbsp;<h:outputText
											value="#{standardBenefitNotesBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{standardBenefitNotesBackingBean.state}" />
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText
											value="#{standardBenefitNotesBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{standardBenefitNotesBackingBean.status}" />
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>:&nbsp;<h:outputText
											value="#{standardBenefitNotesBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{standardBenefitNotesBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						<BR>
						&nbsp;&nbsp;&nbsp; <h:commandButton styleClass="wpdbutton"
							value="Done" tabindex="6"
							action="#{standardBenefitBackingBean.doneFromNotesTab}">
						</h:commandButton></TD>
					</TR>

				</table>

				<!--	End of Page data	-->
				<DIV id="dummyDiv" style="visibility:hidden"></DIV>

				<!-- Space for hidden fields -->
				<h:inputHidden id="targetHiddenToStoreNoteId"
					value="#{standardBenefitNotesBackingBean.standardBenefitNoteId}"></h:inputHidden>
				<h:inputHidden id="selectedNotes"
					value="#{standardBenefitNotesBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="viewNoteId"
					value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden>
				<h:inputHidden id="viewNoteName"
					value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="viewNoteVersion"
					value="#{notesAttachmentBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="tabHidden"
					value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
				<h:inputHidden id="dummyHidden"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{standardBenefitNotesBackingBean.duplicateData}"></h:inputHidden> 
				
				<h:commandLink id="saveLink"
					style="display:none; visibility: hidden;"
					action="#{standardBenefitNotesBackingBean.attachNotesForStandardBenefit}">
				</h:commandLink>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{standardBenefitNotesBackingBean.deleteStandardBenefitNotes}"><f:verbatim /></h:commandLink>
				<h:inputHidden id="panelData"  />
				<h:inputHidden id="inputTextData"  />
				<h:inputHidden id="notesToDelete" value="#{standardBenefitNotesBackingBean.deleteNoteIds}"> </h:inputHidden>

				<!-- End of hidden fields  -->
			</h:form></td>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</table>
	</BODY>
</f:view>


<script language="JavaScript">	
IGNORED_FIELD1='benefitNotesForm:duplicateData';
IGNORED_FIELD2='benefitNotesForm:panelData';
IGNORED_FIELD3='benefitNotesForm:inputTextData';
enableDisableDelete('benefitNotesForm:searchResultTable',2,0,'benefitNotesForm:deleteButton');

	var tableObject = document.getElementById('benefitNotesForm:searchResultTable');
		if(tableObject != null) {
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		var rowlength = document.getElementById('benefitNotesForm:searchResultTable').rows.length;
				if(document.getElementById('benefitNotesForm:searchResultTable').rows.length < 13){
					document.getElementById('resultHeaderTable').width = relTblWidth+"px";
					document.getElementById('benefitNotesForm:searchResultTable').width = relTblWidth+"px";
					setColumnWidth('benefitNotesForm:searchResultTable','70%,15%,15%');
				}else{
					document.getElementById('resultHeaderTable').style.width = relTblWidth-17+"px";
					setColumnWidth('benefitNotesForm:searchResultTable','70%,15%,15%');
				}
		}
			
//syncTables('resultHeaderTable','benefitNotesForm:searchResultTable');
  var entityType = 'ATTACHBENEFIT';
  var lookUpAction = 2;
	

	//Hide table if no value is present

	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitNotesForm:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			getObj('benefitNotesForm:deleteButton').style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		//	setColumnWidth('benefitNotesForm:searchResultTable', '40%:60%');
		//	setColumnWidth('resultHeaderTable', '40%:60%');

		}
	}

	
	function viewAction(){
 		getFromDataTableToHidden('benefitNotesForm:searchResultTable','noteId','benefitNotesForm:viewNoteId');
		getFromDataTableToHidden('benefitNotesForm:searchResultTable','noteNameHidden','benefitNotesForm:viewNoteName');
		getFromDataTableToHidden('benefitNotesForm:searchResultTable','noteVersionHidden','benefitNotesForm:viewNoteVersion');
		noteIdForView = document.getElementById('benefitNotesForm:viewNoteId').value;
		noteNameForView = document.getElementById('benefitNotesForm:viewNoteName').value;
		noteVersionForView = document.getElementById('benefitNotesForm:viewNoteVersion').value;
reloadData();
  }
hideTab();
	function hideTab(){
		var tab;
		tab = document.getElementById("benefitNotesForm:tabHidden").value ;
		if(tab=="Standard Definition"){
			manTab.style.display='none';
			noteTab.style.display='';
		}
		else{
			manTab.style.display='';
			noteTab.style.display='none';
		}
	}

function noteSelect(){
		var noteName= document.getElementById('benefitNotesForm:noteSearchCriteria').value;;
		ewpdModalWindow_ewpd( '../popups/notesPopUp.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=2&'+ 'temp=' + Math.random()+'&noteSearchCriteria='+noteName, 'selectedNotesDiv','benefitNotesForm:selectedNotes',3,1);
		showDiv();
	}
	function callNoteSelect(){
	if(window.event.keyCode == 13){
		noteSelect();
		return false;
	}else{
		return true;
	}
	}
showDiv();
copyHiddenToDiv_ewpd("benefitNotesForm:selectedNotes", "selectedNotesDiv", 3, 1);
function showDiv(){
var selNote = document.getElementById("benefitNotesForm:selectedNotes").value ;

	if(null==selNote || ""==selNote)
		divRow.style.display='none';
	else
		divRow.style.display='';
}
function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to detach the selected Notes?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('benefitNotesForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;

		delNoteId = getObj('benefitNotesForm:notesToDelete');
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



//checkForOnLoadData();
function checkForOnLoadData(){
	var tableObject = document.getElementById('benefitNotesForm:searchResultTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('benefitNotesForm:searchResultTable');
			document.getElementById('benefitNotesForm:panelData').value = onLoadPanelData;
		}
	}
	if(null != document.getElementById('benefitNotesForm:selectedNotes')){
		var inputTexts = checkForTextData();
		document.getElementById('benefitNotesForm:inputTextData').value = inputTexts;
	}

}

function checkForTextData(){
	var inputTexts = document.getElementById('benefitNotesForm:selectedNotes').value; 
	return inputTexts;

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


function unsavedDataFinder(objectId){
		var buttonId = objectId;
		if(buttonId == 'benefitNotesForm:saveButton'){
		var tableObject = document.getElementById('benefitNotesForm:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('benefitNotesForm:searchResultTable');
			if(document.getElementById('benefitNotesForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('benefitNotesForm:saveLink');	
				}
			}else{
				submitLink('benefitNotesForm:saveLink');
			}
		}else{
			submitLink('benefitNotesForm:saveLink');
		}
		}else if (buttonId == 'benefitNotesForm:deleteButton'){
			var textData = checkForTextData();
			if(document.getElementById('benefitNotesForm:inputTextData').value != textData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
						if(confirmDelete()){
							submitLink('benefitNotesForm:deleteLink');	
						}
				}
			}else{
				if(confirmDelete()){
							submitLink('benefitNotesForm:deleteLink');	
				}
			}
		}
			
}		


</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="notes" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitNotesForm:duplicateData').value == ''){
		document.getElementById('benefitNotesForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForOnLoadData();
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitNotesForm:duplicateData').value;
</script>
</HTML>

