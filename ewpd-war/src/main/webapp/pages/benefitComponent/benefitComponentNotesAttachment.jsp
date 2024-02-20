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

<TITLE>Benefit Component Notes</TITLE>
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
				<TD><h:form styleClass="form" id="benefitComponentNotesForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv">
								<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>
							</DIV>	
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
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="186" class="tabNormal"><h:commandLink
												action="#{benefitComponentCreateBackingBean.loadUpdateTab}"
												onmousedown="javascript:navigatePageAction(this.id);" id="thisId">
												<h:outputText value="General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="186" class="tabNormal"><h:commandLink
												action="#{BenefitComponentHierarchiesBackingBean.loadBenefitHierarchyTab}"
												onmousedown="javascript:navigatePageAction(this.id);" id="BenHierarchyID">

												<h:outputText value="Benefit Hierarchies" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Notes " /></TD>
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
																							
									<TD valign="top" width="137" ><h:outputText value="Name*" 
									styleClass="#{BenefitComponentNotesBackingBean.requiredNoteName?'mandatoryError': 'mandatoryNormal'}" ></h:outputText></TD>
									<TD  align="left" width="200"><h:inputText id="noteSearchCriteria" tabindex="1"
													styleClass="formInputField" onkeypress="return callNoteSelect();"></h:inputText>
										<h:inputHidden id="hidden_notes"  value="#{BenefitComponentNotesBackingBean.noteString}" /></TD>
									<TD align="left" valign="top" width="633">	
										<h:graphicImage url="" value="../../../images/autoComplete.gif"
										alt="Select" style="cursor:hand;" width="15" height="15" onclick="noteSelect();return false;"	>
										</h:graphicImage>
									</TD>																										
								</TR>
								<TR id="divRow" style="display:none;">
									<TD valign="top" width="125" ></TD>
									<TD  align="left" width="200"><DIV id="selectedNotesDiv" class="selectDataDisplayDiv"></DIV></TD>
								</TR>
								<tr>
									<TD width="110"></TD>
								</tr>

								<TR>
									<TD width="110"><h:commandButton value="Attach" id="attachNotesButton"
										styleClass="wpdButton" tabindex="2"
										onclick="unsavedDataFinder(this.id);return false;"
										>
									</h:commandButton></TD>

								</TR>

							</TABLE>
							<DIV id="noNotesAttachedDiv"
										style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000; height:2px;
						background-color:#FFFFFF;visibility:hidden;"><BR/>
							No Notes Attached.</DIV>
							<TABLE width="100%" id="topTable" cellspacing="0" cellpadding="0">

								<TR>
									<TD><BR />
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
												<TD align="left" width="80%"><h:outputText value="Note Name "></h:outputText>
												</TD>
												<TD width="10%">&nbsp;</TD>
												<TD width="10%"><h:outputText value="Delete"/></TD>
												</TbR>
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
										rendered="#{BenefitComponentNotesBackingBean.associatedNotesList != null}"
										value="#{BenefitComponentNotesBackingBean.associatedNotesList}"
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
											<h:commandButton alt="View" id="viewButton" rendered="#{BenefitComponentNotesBackingBean.securityAccess}"
												image="../../images/view.gif" tabindex="3"
												onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+noteVersionForView+'&temp=' + Math.random(),'dummyDiv','benefitComponentNotesForm:dummyHidden',1,1);return false;"></h:commandButton>
											<!-- end: for editing the selected details -->
											
										</h:column>
										<h:column>
											<h:selectBooleanCheckbox id="multiDeleteCheckBox"
												tabindex="3" onclick="enableDisableDelete('benefitComponentNotesForm:searchResultTable', 2, 0, 'benefitComponentNotesForm:deleteButton');">
											</h:selectBooleanCheckbox>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
								<TR>
									<TD valign="top"><h:commandButton id="deleteButton" value="Delete"
						styleClass="wpdbutton" tabindex="4"
						onclick="unsavedDataFinder(this.id);return false;" ></h:commandButton></TD></TR>
						<TR>
									<TD valign="top">&nbsp;</TD></TR>
							</TABLE>
							</FIELDSET>
							<BR>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
							<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
								<TR>
								
									<td ><h:selectBooleanCheckbox id="checkall"
										tabindex="5"
										
										value="#{BenefitComponentHierarchiesBackingBean.checkInOpted}">
									</h:selectBooleanCheckbox><h:outputText value="Check In" /></TD>
									
									<td align="right" >
									<TABLE >
										<TR>
											<td><h:outputText value="State" /></td>
											<td>: <h:outputText
												value="#{benefitComponentCreateBackingBean.state}" /></td>
											<h:inputHidden id="stateHidden"
												value="#{benefitComponentCreateBackingBean.state}" />
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<td>: <h:outputText
												value="#{benefitComponentCreateBackingBean.status}" /></td>
											<h:inputHidden id="statusHidden"
												value="#{benefitComponentCreateBackingBean.status}" />
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<td>: <h:outputText
												value="#{benefitComponentCreateBackingBean.version}" /></td>
											<h:inputHidden id="versionHidden"
												value="#{benefitComponentCreateBackingBean.version}" />
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<TABLE>
								<TBODY>
									<TR>
										<TD>
										<h:commandButton styleClass="wpdbutton" value="Done"
											tabindex="6"
											action="#{BenefitComponentHierarchiesBackingBean.doneFromNotesTab}">
										</h:commandButton></TD>
									</TR>
								</TBODY>
							</TABLE>
							<DIV id="dummyDiv" style="visibility:hidden"></DIV>

							<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
								id="targetHiddenToStoreNoteId"
								value="#{BenefitComponentNotesBackingBean.benefitComponentNoteId}"></h:inputHidden>
							<h:inputHidden id="panelData"  />
							<h:inputHidden id="inputTextData"  />
							<h:inputHidden id="viewNoteId"
								value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden> <h:inputHidden
								id="viewNoteName" value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
							<h:inputHidden id="viewNoteVersion"
								value="#{notesAttachmentBackingBean.version}"></h:inputHidden> <h:inputHidden
								id="dummyHidden"></h:inputHidden> <h:inputHidden
								id="benefitHierarchyId"
								value="#{BenefitComponentHierarchiesBackingBean.benefitHierarchyId}"></h:inputHidden>
							<h:inputHidden id="txtBenefits"
								value="#{BenefitComponentHierarchiesBackingBean.benefit}"></h:inputHidden>
							<h:inputHidden id="notesFlag"
								value="#{BenefitComponentHierarchiesBackingBean.notesFlag}"></h:inputHidden>
								<h:inputHidden id="duplicateData"
								value="#{BenefitComponentNotesBackingBean.duplicateData}"></h:inputHidden>
							<h:commandLink id="deleteBenefitComponentNotes"
								style="display:none; visibility: hidden;"
								action="#{BenefitComponentNotesBackingBean.deleteBenefitComponentNotes}">
							<f:verbatim />
							</h:commandLink>
							<h:commandLink id="attachNotesLink"
								style="display:none; visibility: hidden;"
								action="#{BenefitComponentNotesBackingBean.AttachNotesForBenefitComponent}">

								<f:verbatim />
							</h:commandLink> <!-- End of hidden fields  --></TD>
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


enableDisableDelete('benefitComponentNotesForm:searchResultTable', 2, 0, 'benefitComponentNotesForm:deleteButton');
var tableObject = document.getElementById('benefitComponentNotesForm:searchResultTable');
if(null == tableObject){
	(document.getElementById('noNotesAttachedDiv')).style.visibility = "visible";
}


		

 IGNORED_FIELD1='benefitComponentNotesForm:duplicateData';
  var noteIdForView; 
  var noteNameForView; 
  var entityType = 'ATTACHCOMP';
  var lookUpAction = 2;
	
	copyToHidden(true,'benefitComponentNotesForm:notesFlag');
	
	
	//Hide table if no value is present
	
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitComponentNotesForm:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('topTable').width = "100%";
				var relTblWidth = document.getElementById('topTable').offsetWidth;
				if(document.getElementById('benefitComponentNotesForm:searchResultTable').rows.length < 13){
					document.getElementById('resultHeaderTable').width = relTblWidth+"px";
					document.getElementById('benefitComponentNotesForm:searchResultTable').width = relTblWidth+"px";
					setColumnWidth('benefitComponentNotesForm:searchResultTable','80%,10%,10%');
				}else{
					document.getElementById('resultHeaderTable').style.width = relTblWidth-17+"px";
					setColumnWidth('benefitComponentNotesForm:searchResultTable','80%,10%,10%');
				}
			setColumnWidth('benefitComponentNotesForm:searchResultTable', '80%,10%,10%');
			setColumnWidth('resultHeaderTable', '80%,10%,10%');

		}
	}
	
function copyToHidden(sourceField,hiddenField){
	var sourceObj = sourceField;
	var targetObj = document.getElementById(hiddenField);
	targetObj.value = sourceObj;
}

	
	// For Deletion
	function confirmDeletion(){				
		var message = "Are you sure you want to detach the selected notes?"		
		if(window.confirm(message)){			
			//submitDataTableButton('benefitComponentNotesForm:searchResultTable', 'noteId', 'benefitComponentNotesForm:targetHiddenToStoreNoteId', 'benefitComponentNotesForm:deleteBenefitComponentNotes');
			storeItemsToBeDeleted();
			document.getElementById('benefitComponentNotesForm:deleteBenefitComponentNotes').click();
		}
		else{			
			return false;
		}
	}
	
	function storeItemsToBeDeleted(){
		var selectedNoteIds;
		var tableObject = document.getElementById('benefitComponentNotesForm:searchResultTable');
		if(null != tableObject && tableObject.rows.length > 0){
			var rows = tableObject.rows;
			for(var i = 0; i < rows.length; i++){
				if(rows[i].cells[2].children[0].checked) {
					if(i == 0){
						selectedNoteIds = rows[i].cells[0].children[0].value;
					}
					else{
						selectedNoteIds = selectedNoteIds +'~'+ rows[i].cells[0].children[0].value;
					}
				}
			}
		
		}
		var storeId = document.getElementById('benefitComponentNotesForm:targetHiddenToStoreNoteId');
		storeId.value = selectedNoteIds;
	}

	function viewAction(){
 		getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable','noteId','benefitComponentNotesForm:viewNoteId');
		getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable','noteNameHidden','benefitComponentNotesForm:viewNoteName');
		getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable','noteVersionHidden','benefitComponentNotesForm:viewNoteVersion');		
		noteIdForView = document.getElementById('benefitComponentNotesForm:viewNoteId').value;
		noteNameForView = document.getElementById('benefitComponentNotesForm:viewNoteName').value;
		noteVersionForView = document.getElementById('benefitComponentNotesForm:viewNoteVersion').value;
reloadData();
  }
 function noteSelect(){
		var noteName= document.getElementById('benefitComponentNotesForm:noteSearchCriteria').value;;
		ewpdModalWindow_ewpd( '../popups/notesPopUp.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=2&'+ 'temp=' + Math.random()+'&noteSearchCriteria='+noteName, 'selectedNotesDiv', 'benefitComponentNotesForm:hidden_notes',3,1);
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
copyHiddenToDiv_ewpd("benefitComponentNotesForm:hidden_notes", "selectedNotesDiv", 3, 1);
function showDiv(){
var selNote = document.getElementById("benefitComponentNotesForm:hidden_notes").value ;
	if(null==selNote || ""==selNote)
		divRow.style.display='none';
	else
		divRow.style.display='';
}

if(document.getElementById('benefitComponentNotesForm:searchResultTable') == null)
	document.getElementById('benefitComponentNotesForm:deleteButton').style.visibility="hidden";


checkForOnLoadData();
function checkForOnLoadData(){
	var tableObject = document.getElementById('benefitComponentNotesForm:searchResultTable');
if(null != tableObject){
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('benefitComponentNotesForm:searchResultTable');
		document.getElementById('benefitComponentNotesForm:panelData').value = onLoadPanelData;
	}
}
	var inputTexts = document.getElementById('benefitComponentNotesForm:hidden_notes').value; 
	document.getElementById('benefitComponentNotesForm:inputTextData').value = inputTexts;
}

function checkForTextData(){
	var inputTexts = document.getElementById('benefitComponentNotesForm:hidden_notes').value; 
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
		if(buttonId == 'benefitComponentNotesForm:attachNotesButton'){
			var tableObject = document.getElementById('benefitComponentNotesForm:searchResultTable');
			if(null != tableObject){
				var panelData = getPanelData('benefitComponentNotesForm:searchResultTable');
					if(document.getElementById('benefitComponentNotesForm:panelData').value != panelData){
						var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
						if (retValue){
							submitLink('benefitComponentNotesForm:attachNotesLink');	
						}
					}else{
				submitLink('benefitComponentNotesForm:attachNotesLink');
			}
		}else{
			submitLink('benefitComponentNotesForm:attachNotesLink');
		}
}
else if (buttonId == 'benefitComponentNotesForm:deleteButton'){
			var textData = checkForTextData();
			if(document.getElementById('benefitComponentNotesForm:inputTextData').value != textData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
						confirmDeletion();
				}
			}else{
				confirmDeletion();
			}
		}
			
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponentNotes" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitComponentNotesForm:duplicateData').value == '')
		document.getElementById('benefitComponentNotesForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitComponentNotesForm:duplicateData').value;
</script>
</HTML>

