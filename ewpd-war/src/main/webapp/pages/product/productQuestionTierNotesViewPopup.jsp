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

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selecthiddenNoteNameColumn {
	width: 30%;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 60%;
	vertical-align: middle;
}
.longDescriptionColumn {
	width: 10%;
	vertical-align: middle;
}
</style>

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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<TITLE>Notes Look Up Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="changeParentImage()">

	<h:form id="notesLookUpForm">
		<div id="noNotesDiv"><h:inputHidden id="records"
			value="#{productQuestionTierNotesAttachmentBackingBean.viewRecords}"></h:inputHidden>
		<h:outputText value="No Notes Available."
			styleClass="dataTableColumnHeader" /></div>
							<TABLE id="message">
								<TBODY>
									<tr>
										<TD><!-- Insert WPD Message Tag --> <w:messageForPopup></w:messageForPopup>
										</TD>
									</tr>
								</TBODY>
							</TABLE>
		<table width="98%" align="right" cellpadding="0" cellspacing="0"
			border="0">
			<TBODY>
				<TR>
					<TD>
					<div id="headerTable">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR height="20">
							<TD width="30%"><strong> <h:outputText value="Note Id">
							</h:outputText> </strong></td>
							<TD width="60%"><strong><h:outputText value="Note Name "></h:outputText></strong></TD>
							<TD width="10%"></TD>
						</TR>
					</table>
					</div>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" style="overflow:auto;"
						class="popupDataTableDiv"><h:dataTable id="notesDataTable"
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selecthiddenNoteNameColumn,shortDescriptionColumn,longDescriptionColumn" cellspacing="1"
						width="100%"
						value="#{productQuestionTierNotesAttachmentBackingBean.questionNotes}"
						var="singleValue"
						rendered="#{productQuestionTierNotesAttachmentBackingBean.questionNotes!=null}"
						cellpadding="0" bgcolor="#cccccc">


						
						<h:column>
							<h:inputHidden id="hiddenNoteName"
								value="#{singleValue.noteName}"></h:inputHidden>
							<h:inputHidden id="statusHidden"
								value="#{singleValue.overrideStatus}"></h:inputHidden>
							<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
							<h:inputHidden id="hiddenVersion" value="#{singleValue.version}"></h:inputHidden>
							<h:inputHidden id="isdelete" value="#{singleValue.isdelete}"></h:inputHidden>
							<h:inputHidden id="isInsert" value="#{singleValue.isInsert}"></h:inputHidden>
							<h:inputHidden id="isUpdate" value="#{singleValue.isUpdate}"></h:inputHidden>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.noteId}"></h:outputText>
						</h:column>
						<h:column>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.noteName}"></h:outputText>
						</h:column>
						<h:column>
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							<h:commandButton alt="View" id="viewButton"
								image="../../images/view.gif"
								onclick="viewAction();return false;">
							</h:commandButton>
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>

						</h:column>
					</h:dataTable></DIV>
					<div id="errorMessageDiv" class="popupDataTableDiv"
						style="height:300px;display:none;">
					<table cellpadding="7" cellspacing="1" bgcolor="#cccccc" border="0"
						width="100%">
						<tr>
							<td bgcolor="#e1ecf7" colspan="2" width="100%"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
												font-size:9px;"><font
								color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error
							occurred during processing.</font></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</TBODY>

		</table>
		<h:inputHidden id="tierSysId"
			value="#{productQuestionTierNotesAttachmentBackingBean.tierSysId}"></h:inputHidden>
		<h:inputHidden id="questionId"
			value="#{productQuestionTierNotesAttachmentBackingBean.questionId}"></h:inputHidden>
		<h:inputHidden id="primaryentityId"
			value="#{productQuestionTierNotesAttachmentBackingBean.primaryEntityID}"></h:inputHidden>
		<h:inputHidden id="primaryEntytyType"
			value="#{productQuestionTierNotesAttachmentBackingBean.primaryType}"></h:inputHidden>
		<h:inputHidden id="benefitComponentId"
			value="#{productQuestionTierNotesAttachmentBackingBean.benefitComponentId}"></h:inputHidden>
		<h:inputHidden id="benefitDefnId"
			value="#{productQuestionTierNotesAttachmentBackingBean.benefitDefnId}"></h:inputHidden>
		<h:inputHidden id="adminLvlOptionId"
			value="#{productQuestionTierNotesAttachmentBackingBean.adminLvlOptionAssnSysId}"></h:inputHidden>
		<h:inputHidden id="postBackId"
			value="#{productQuestionTierNotesAttachmentBackingBean.postBack}"></h:inputHidden>
		<h:inputHidden id="prevVersionId"
			value="#{productQuestionTierNotesAttachmentBackingBean.previousNoteVersion}"></h:inputHidden>

		<h:inputHidden id="viewNoteId"
			value="#{productQuestionTierNotesAttachmentBackingBean.noteId}"></h:inputHidden>
		<h:inputHidden id="viewNoteName"
			value="#{productQuestionTierNotesAttachmentBackingBean.noteName}"></h:inputHidden>
		<h:inputHidden id="viewNoteVersion"
			value="#{productQuestionTierNotesAttachmentBackingBean.version}"></h:inputHidden>
		<h:inputHidden id="dummyHidden"></h:inputHidden> <h:inputHidden
			id="prevNoteIdSelectedId"
			value="#{productQuestionTierNotesAttachmentBackingBean.prevNoteIdSelected}"></h:inputHidden>

		<h:inputHidden id="newNoteId"
			value="#{productQuestionTierNotesAttachmentBackingBean.newNoteIdSelected}"></h:inputHidden>
		<h:inputHidden id="questionToProcessId"
			value="#{productQuestionTierNotesAttachmentBackingBean.questionId}"></h:inputHidden>
		<h:inputHidden id="noteAction"
			value="#{productQuestionTierNotesAttachmentBackingBean.noteAction}"></h:inputHidden>
		<h:inputHidden id="versionId"
			value="#{productQuestionTierNotesAttachmentBackingBean.noteVersion}"></h:inputHidden>
		<h:commandLink id="saveLink" style="display:none; visibility: hidden;"
			action="#{productQuestionTierNotesAttachmentBackingBean.saveAction}">
		</h:commandLink> <h:commandLink id="refreshId"
			style="display:none; visibility: hidden;">
		</h:commandLink>
		<h:inputHidden id ="notepresentId"  value ="#{productQuestionTierNotesAttachmentBackingBean.noteAttached}"></h:inputHidden>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
	</h:form>
	</BODY>
</f:view>
<script language="javascript">
//isCheckedAll();

initialize();
	
	function initialize(){
		if(document.getElementById('notesLookUpForm:notesDataTable') != null)	{
			if(document.getElementById('notesLookUpForm:notesDataTable').rows.length==0){
				document.getElementById("headerTable").style.visibility = 'hidden';
				document.getElementById("message").style.visibility = 'hidden';		
			}	
		else{
				document.getElementById("headerTable").style.visibility = 'visible';		
				document.getElementById("noNotesDiv").style.visibility = 'hidden';	
				document.getElementById("message").style.visibility ='visible';			
			}		
		}
	}


function viewAction(){

 		getFromDataTableToHidden('notesLookUpForm:notesDataTable','noteId','notesLookUpForm:viewNoteId');
		getFromDataTableToHidden('notesLookUpForm:notesDataTable','hiddenNoteName','notesLookUpForm:viewNoteName');
		getFromDataTableToHidden('notesLookUpForm:notesDataTable','hiddenVersion','notesLookUpForm:viewNoteVersion');	
		noteIdForView = document.getElementById('notesLookUpForm:viewNoteId').value;
		noteNameForView = document.getElementById('notesLookUpForm:viewNoteName').value;
		noteVersionForView = document.getElementById('notesLookUpForm:viewNoteVersion').value;
		ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+
					'&noteName='+noteNameForView+
					'&version='+noteVersionForView+
					'&temp=' + Math.random(),'dummyDiv','notesLookUpForm:dummyHidden',1,1);

  }
function saveAction(){
	
	tableObj = document.getElementById("notesLookUpForm:notesDataTable");
	if(null!=tableObj){
		var noteIId=null;
		var prevNote = null;
		var prevVersion = null;
		for (var i=0;i<tableObj.rows.length;i++){
			checkboxObject = tableObj.rows[i].cells[0].children[0];
			deleteValue=tableObj.rows[i].cells[1].children[4].value;
			InsertValue=tableObj.rows[i].cells[1].children[5].value;
			updateValue=tableObj.rows[i].cells[1].children[6].value;
			document.getElementById('notesLookUpForm:questionToProcessId').value=document.getElementById('notesLookUpForm:questionId').value;
			prevNote = document.getElementById('notesLookUpForm:prevNoteIdSelectedId').value;
			prevVersion = document.getElementById('notesLookUpForm:prevVersionId').value;
			if(checkboxObject.checked){
			noteIId = tableObj.rows[i].cells[1].children[2].value;
			var currVersion = tableObj.rows[i].cells[1].children[3].value;
			document.getElementById('notesLookUpForm:newNoteId').value = noteIId
			document.getElementById('notesLookUpForm:versionId').value = currVersion;
			}
		}
			if((null== prevNote || ('')== prevNote)&& (null== noteIId||noteIId=='undefined'||('')== noteIId)){
			alert('Please select a value')
			return false;
			}
				if(null!= noteIId && null!= prevNote &&('')!= noteIId && ('')!= prevNote){
					if(prevNote!=noteIId){
					document.getElementById('notesLookUpForm:noteAction').value='update';
					if(updateValue=='Y'){
					submitLink('notesLookUpForm:saveLink');
					}else{
						alert('No Modifications Done');
					}
					}
					else{
					alert('No Modifications Done');
					}
				}else if((null!= noteIId && ('')!= noteIId) && (null== prevNote||prevNote=='undefined')||('')== prevNote){
					document.getElementById('notesLookUpForm:noteAction').value='insert';
					if(InsertValue=='Y'){
					submitLink('notesLookUpForm:saveLink');
					}else{
						alert('No Modifications Done');
					}
				}else if((null!= prevNote && ('')!= prevNote)&& (null== noteIId||noteIId=='undefined'||('')== noteIId)){
						document.getElementById('notesLookUpForm:noteAction').value='delete';
						document.getElementById('notesLookUpForm:newNoteId').value = prevNote
						document.getElementById('notesLookUpForm:versionId').value = prevVersion;
						if(deleteValue=='Y'){
						submitLink('notesLookUpForm:saveLink');
						}else{
						alert('No Modifications Done');
					}
				}
	}

}
function changeParentImage(){
notevalue = document.getElementById("notesLookUpForm:notepresentId").value;
				if(notevalue=='Y'){
					window.returnValue = "notes_exists";
				//	break;
				}else{
					window.returnValue = "no_notes";
			}
	}
</script>
</HTML>
