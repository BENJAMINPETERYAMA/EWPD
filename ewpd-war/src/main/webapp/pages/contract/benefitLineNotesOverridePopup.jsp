<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="changeNoteImage()">



	<h:inputHidden id="loadNotes"
		value="#{contractCoverageNotesOverrideBackingBean.loadCntNotes}"></h:inputHidden>
	<h:form id="notesOverrideForm">
		<h:outputText value="No Notes Available."
			rendered="#{contractCoverageNotesOverrideBackingBean.contractNoteObj == null}"
			styleClass="dataTableColumnHeader" />
		<DIV id="notesDiv">
		<table border="0" cellpadding="5" cellspacing="0">

			<tr>
				<td align="left" width="25">&nbsp;<h:commandButton id="select"
					value="Save" styleClass="wpdbutton"
					onmousedown="javascript:savePageAction(this.id);"
					onclick="saveAction();return false;" disabled="true"></h:commandButton></td>
			</tr>
			<tr>
				<TD>
				<TABLE>
					<TBODY>
						<tr>
							<TD><!-- Insert WPD Message Tag --> <w:messageForPopup></w:messageForPopup>
							</TD>
						</tr>
					</TBODY>
				</TABLE>
				</TD>
			</tr>

		</table>

		<TABLE border="0">
			<tr>
				<td>
				<DIV id="legacyNoteDiv">
				<table width="98%" align="right" cellpadding="0" cellspacing="0"
					border="0">
					<TBODY>
						<TR>
							<TD>
							<table border="0" cellspacing="1" cellpadding="0" width="100%"
								bgcolor="#7f9db9">
								<tr>
									<TD width="100%"><strong> <h:outputText value="Legacy Note">
									</h:outputText> </strong></TD>
								</tr>
							</table>
							</TD>
						</TR>
						<TR>
							<TD>
							<table id="headerTable1" border="0" cellspacing="1"
								cellpadding="0" bgcolor="#cccccc">
								<tr class="dataTableColumnHeader"
									style="background-color:#cccccc;">
									<td align="left"></TD>
									<TD align="left"><strong> <h:outputText value="Note Name">
									</h:outputText> </strong></td>
									<TD align="left"><strong> <h:outputText value="Note Id">
									</h:outputText> </strong></td>
								</tr>
							</table>
							</TD>
						</TR>

						<tr>
							<td>
							<DIV id="popupDataTableDiv1" style="overflow:auto;"><h:dataTable
								cellspacing="1" id="legacyNotesDataTable"
								rendered="#{contractCoverageNotesOverrideBackingBean.legacyNotesList != null}"
								value="#{contractCoverageNotesOverrideBackingBean.legacyNotesList}"
								styleClass="outputText" var="singleValue" cellpadding="0"
								bgcolor="#cccccc" rowClasses="dataTableEvenRow,dataTableOddRow">
								<h:column>
									<f:verbatim>
										<wpd:singleRowSelect groupName="ContractNotes" id="noteIdRow"
											rendered="true"></wpd:singleRowSelect>
									</f:verbatim>
								</h:column>

								<h:column>
									<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
									<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
									<h:inputHidden
										value="#{contractCoverageNotesOverrideBackingBean.checkedNote}"></h:inputHidden>
									<f:verbatim>&nbsp;</f:verbatim>
									<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
								</h:column>
								<h:column>
									<f:verbatim>&nbsp;</f:verbatim>
									<h:outputText value="#{singleValue.noteId}"></h:outputText>
								</h:column>
								<h:column>
									<f:verbatim>&nbsp;</f:verbatim>
									<h:commandButton alt="View Legacy Note" id="viewButton"
										image="../../images/view.gif"
										onclick="viewAction('#{singleValue.noteId}','#{singleValue.version}','#{singleValue.noteName}'); return false;"></h:commandButton>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								</h:column>
							</h:dataTable></DIV>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</TBODY>
				</table>
				<BR>
				</DIV>
				</td>
			</tr>
			<tr>
				<td>
				<DIV id="ewpdNoteDiv">
				<table width="98%" align="right" cellpadding="0" cellspacing="0"
					border="0">
					<TBODY>
						<TR>
							<TD>
							<table border="0" cellspacing="1" cellpadding="0" width="100%"
								bgcolor="#7f9db9">
								<tr>
									<TD width="100%"><strong> <h:outputText value="EWPD Notes">
									</h:outputText> </strong></TD>
								</tr>
							</table>
							</TD>
						</TR>
						<TR>
							<TD>

							<table id="headerTable2" border="0" cellspacing="1"
								cellpadding="0" bgcolor="#cccccc">
								<tr class="dataTableColumnHeader"
									style="background-color:#cccccc;">
									<td align="left"></TD>
									<TD align="left"><strong> <h:outputText value="Note Name">
									</h:outputText> </strong></td>
									<TD align="left"><strong> <h:outputText value="Note Id">
									</h:outputText> </strong></td>
								</tr>
							</table>
							</TD>
						</TR>
						<tr>
							<td>
							<DIV id="popupDataTableDiv2" style="height:200;overflow:auto;"><h:dataTable
								cellspacing="1" id="wpdNotesDataTable"
								rendered="#{contractCoverageNotesOverrideBackingBean.ewpdNotesList != null}"
								value="#{contractCoverageNotesOverrideBackingBean.ewpdNotesList}"
								var="singleValue" cellpadding="0" bgcolor="#cccccc"
								rowClasses="dataTableEvenRow,dataTableOddRow">
								<h:column>
									<f:verbatim>
										<wpd:singleRowSelect groupName="ContractNotes" id="minor1"
											rendered="true"></wpd:singleRowSelect>
									</f:verbatim>
								</h:column>
								<h:column>
									<h:inputHidden id="noteNameHidden"
										value="#{singleValue.noteName}"></h:inputHidden>
									<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
									<h:inputHidden
										value="#{contractCoverageNotesOverrideBackingBean.checkedNote}"></h:inputHidden>
									<h:inputHidden id="noteVersionHidden"
										value="#{singleValue.version}"></h:inputHidden>
									<f:verbatim>&nbsp;</f:verbatim>
									<h:outputText value="#{singleValue.noteName}"></h:outputText>
								</h:column>
								<h:column>
									<f:verbatim>&nbsp;</f:verbatim>
									<h:outputText value="#{singleValue.noteId}"></h:outputText>
								</h:column>
								<h:column>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
									<!-- start: for viewing the selected details -->
									<h:commandButton alt="View" id="viewEwpdButton"
										image="../../images/view.gif"
										onclick="viewActions();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='
											+document.getElementById('notesOverrideForm:viewNoteId').value+'&noteName='
											+escape(document.getElementById('notesOverrideForm:viewNoteName').value)+'&version='
											+document.getElementById('notesOverrideForm:viewNoteVersion').value+'&temp='
											+Math.random(),'dummyDiv','notesOverrideForm:dummyHidden',1,1);return false;">
									</h:commandButton>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								</h:column>
							</h:dataTable></DIV>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</TBODY>
				</table>
				</DIV>
				</td>
			</tr>
		</TABLE>
		</DIV>
		<h:inputHidden id="dummyHidden"></h:inputHidden>
		<DIV id="dummyDiv"></DIV>
		<h:inputHidden id="viewNoteId" />
		<h:inputHidden id="viewNoteName" />
		<h:inputHidden id="viewNoteVersion" />
		<h:inputHidden id="tierSysId"
			value="#{contractCoverageNotesOverrideBackingBean.tierSysId}"></h:inputHidden>
		<h:inputHidden id="checkedNote"
			value="#{contractCoverageNotesOverrideBackingBean.checkedNote}"></h:inputHidden>
		<h:inputHidden id="selectedNotesHidden"
			value="#{contractCoverageNotesOverrideBackingBean.selectedNotes}" />
		<h:inputHidden id="hiddenLineId"
			value="#{contractCoverageNotesOverrideBackingBean.secEntityId}"></h:inputHidden>
		<h:inputHidden id="hiddenPrimaryEntityType"
			value="#{contractCoverageNotesOverrideBackingBean.primaryEntityType}"></h:inputHidden>
		<h:commandLink id="saveButton"
			action="#{contractCoverageNotesOverrideBackingBean.saveNotes}"
			style="display:none; visibility: hidden;">
			<f:verbatim> &nbsp;&nbsp;</f:verbatim>
		</h:commandLink>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
document.getElementById('notesOverrideForm:select').disabled  = false;

	function viewActions(){

 		getFromDataTableToHidden('notesOverrideForm:wpdNotesDataTable','noteId','notesOverrideForm:viewNoteId');
		getFromDataTableToHidden('notesOverrideForm:wpdNotesDataTable','noteNameHidden','notesOverrideForm:viewNoteName');				
		getFromDataTableToHidden('notesOverrideForm:wpdNotesDataTable','noteVersionHidden','notesOverrideForm:viewNoteVersion');	

  }

	function changeNoteImage(){
		var noteStatus = document.getElementById('notesOverrideForm:checkedNote').value;
		if(noteStatus != ''){
			window.returnValue = "notes_exists";
		} else{
			window.returnValue = "no_notes";
		}
	}

function getSelectedNote(id){
	var tableObject;
	if (document.getElementById(id)){
		tableObject = document.getElementById(id);
	
		for (var i=0;i<tableObject.rows.length;i++){
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			chkbox = tableObject.rows[i].cells[0].children[0];
			if(currentCell.children[2].value == currentCell.children[1].value){
				chkbox.checked=true;
			}
		}
	}
}

function getCheckedItems_ewpd(id, attrCount)
{	
	var tableObject;	
	var selectedValues = '';
	if (document.getElementById(id))
	{	
		tableObject = document.getElementById(id);
		
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
getSelectedNote('notesOverrideForm:legacyNotesDataTable');	
getSelectedNote('notesOverrideForm:wpdNotesDataTable');	

function initialize(){
		var tableObject1=document.getElementById('notesOverrideForm:legacyNotesDataTable');
		var tableObject2=document.getElementById('notesOverrideForm:wpdNotesDataTable');
		var legacyNote = false ;
		var ewpdNote = false ;
		if(tableObject1)
			legacyNote =  true;
		if(tableObject2)
			ewpdNote =  true;

		if(legacyNote || ewpdNote){
			if(legacyNote){
				setColumnWidth('notesOverrideForm:legacyNotesDataTable', '40:150:300');
				setColumnWidth('headerTable1', '40:150:300');	
				if(!ewpdNote){
					document.getElementById("ewpdNoteDiv").style.visibility = 'hidden';
					document.getElementById("ewpdNoteDiv").style.height = "0px";
					document.getElementById("ewpdNoteDiv").innerText = null;
				}
			}
			if(ewpdNote){
				setColumnWidth('notesOverrideForm:wpdNotesDataTable', '40:150:300');
				setColumnWidth('headerTable2', '40:150:300');
				if(!legacyNote){
					document.getElementById("legacyNoteDiv").style.visibility = 'hidden';
					document.getElementById("legacyNoteDiv").style.height = "0px";
					document.getElementById("legacyNoteDiv").innerText = null;
				}
			}
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
}
function saveAction(){
	var selectedNotes = getCheckedItems_ewpd('notesOverrideForm:legacyNotesDataTable',2);
	if(selectedNotes == ''){
		selectedNotes = getCheckedItems_ewpd('notesOverrideForm:wpdNotesDataTable',2);
	}
	document.getElementById('notesOverrideForm:selectedNotesHidden').value = selectedNotes;
	document.getElementById('notesOverrideForm:saveButton').click();
}
function viewAction(noteId,noteVersion,noteName){
	var url = '../popups/viewLegacyNoteDetails.jsp'+getUrl()+'?noteId='+noteId +'&&'+'noteName='+escape(noteName)+'&&version='+noteVersion;
	window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:450px;resizable=false;status=no;");		
}	
matchCheckboxItems_ewpd('notesOverrideForm:legacyNotesDataTable', window.dialogArguments.selectedValues, 2, 2, 2)
matchCheckboxItems_ewpd('notesOverrideForm:wpdNotesDataTable', window.dialogArguments.selectedValues, 2, 2, 2)

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
