<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">

<TITLE>Notes Look Up Popup</TITLE>
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
<BASE target="_self"/>
</HEAD>
<f:view>
	<BODY onunload="changeNoteImage()">
	<h:form id="notesOverrideForm">
	<h:inputHidden id="loadNotes" value= "#{productLineNotesOverrideBackingBean.loadNotes}"/> 
		<h:outputText value="No Notes Available." 
					rendered="#{productLineNotesOverrideBackingBean.notesObj == null}" 
					styleClass="dataTableColumnHeader"/>
		<DIV id="notesDiv">
			<table border="0" cellpadding="5" cellspacing="0">
       
				<tr>
						<TD>
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
											<w:messageForPopup></w:messageForPopup>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>
					</TD>
							</tr>
				<tr>
					<td align="left">&nbsp;<h:commandButton id="select"
						value="Save" styleClass="wpdbutton" 
						onclick="saveAction();return false;"></h:commandButton>
					</td>
				</tr>


			</table>
								
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR>
						
						<TD>
						<table id="headerTable" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								<td align="left"><h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'notesOverrideForm:notesDataTable'); " ></h:selectBooleanCheckbox></TD>
								<TD align="center"><strong> <h:outputText
									value="Notes">
								</h:outputText> </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="height: 337px; overflow:auto;">
						<h:dataTable cellspacing="1" id="notesDataTable" 
							rendered="#{productLineNotesOverrideBackingBean.notesObj != null}"
							value="#{productLineNotesOverrideBackingBean.notesObj}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc" width="100%"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
								<f:verbatim>
									<h:selectBooleanCheckbox id="componentChkBox" value="#{singleValue.checkedNoteId}">
									</h:selectBooleanCheckbox>
									<h:inputHidden value="#{singleValue.checkedNoteId}"></h:inputHidden>
								</f:verbatim>
							</h:column>
							<h:column>
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:inputHidden id="noteNameHidden" value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden  id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden  id="noteVersionHidden" value="#{singleValue.version}"></h:inputHidden>
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteName}"></h:outputText>
								
							</h:column>
							<h:column>
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								<!-- start: for viewing the selected details -->
								<h:commandButton alt="View" id="viewButton"
									image="../../images/view.gif"
									onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='
											+document.getElementById('notesOverrideForm:viewNoteId').value+'&noteName='
											+document.getElementById('notesOverrideForm:viewNoteName').value+'&version='
											+document.getElementById('notesOverrideForm:viewNoteVersion').value+'&temp='
				 							+Math.random(),'dummyDiv','notesOverrideForm:dummyHidden',1,1);return false;">
								</h:commandButton>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							</h:column>
						</h:dataTable></DIV>
						</td>
					</tr>
				</TBODY>
			</table>
	</DIV>
	<h:inputHidden id="dummyHidden" ></h:inputHidden>
	<DIV id="dummyDiv" ></DIV>
	<h:inputHidden id="tierSysId" value= "#{productLineNotesOverrideBackingBean.tierSysId}"/> 
	<h:inputHidden id="viewNoteId" /> 
	<h:inputHidden id="viewNoteName" /> 
	<h:inputHidden id="viewNoteVersion" /> 
	<h:inputHidden id="selectedNotesHidden" value= "#{productLineNotesOverrideBackingBean.selectedNotes}"/> 
	<h:inputHidden id="hiddenLineId" value="#{productLineNotesOverrideBackingBean.secEntityId}"></h:inputHidden>
	<h:inputHidden id="hiddenPrimaryEntityType" value="#{productLineNotesOverrideBackingBean.primaryEntityType}"></h:inputHidden>
	<h:commandLink id="saveButton" action="#{productLineNotesOverrideBackingBean.saveNotes}" style="hidden"><f:verbatim></f:verbatim></h:commandLink> 
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	function viewAction(){
 		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteId','notesOverrideForm:viewNoteId');
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteNameHidden','notesOverrideForm:viewNoteName');				
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteVersionHidden','notesOverrideForm:viewNoteVersion');	

  }
function getCheckedItems_ewpd(id, attrCount)
{	
	var tableObject;	
	
	if (document.getElementById(id))
	{	
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';
				
				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}
	return selectedValues;
	

}
 initialize();
	
	function initialize(){
	
		if(document.getElementById('notesOverrideForm:notesDataTable') != null) {
			setColumnWidth('notesOverrideForm:notesDataTable', '40:300:150');
			setColumnWidth('headerTable', '40:450');
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}
	 	matchCheckboxItems_ewpd('notesOverrideForm:notesDataTable',window.dialogArguments.selectedValues, 2, 2, 2)


function saveAction(){
var selectedNotes = getCheckedItems_ewpd('notesOverrideForm:notesDataTable',2);
document.getElementById('notesOverrideForm:selectedNotesHidden').value = selectedNotes;
document.getElementById('notesOverrideForm:saveButton').click();
}

	// Function to identify whether the notes is attached or not
	function changeNoteImage(){
		var tableObject = document.getElementById('notesOverrideForm:notesDataTable');
		var noteStatus = '';
		if(tableObject != null){
		for (var i = 0; i < tableObject.rows.length; i++)
		{
			var hiddenCheckboxObject = tableObject.rows[i].cells[0].children[1];
			if(hiddenCheckboxObject && (hiddenCheckboxObject.value == 'true')){
				noteStatus = "notes_exists";
				break;
			}
		}
		}
		if(noteStatus != ''){
			window.returnValue = "notes_exists";
		} else{
			window.returnValue = "no_notes";
		}
	}
</script>
</HTML>
