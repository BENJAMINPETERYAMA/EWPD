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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 20%;
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
<TITLE>Notes Look Up Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onunload="changeParentImage()">

	<h:form id="notesLookUpForm">
		<div id="noNotesDiv"><h:inputHidden id="records"
			value="#{contractQuestionTierNotesAttachmentBackingBean.viewRecords}"></h:inputHidden>
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
							<TD width="20%"><strong> <h:outputText value="Note Id">
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
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						width="100%"
						value="#{contractQuestionTierNotesAttachmentBackingBean.questionNotes}"
						var="singleValue"
						rendered="#{contractQuestionTierNotesAttachmentBackingBean.questionNotes!=null}"
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
			value="#{contractQuestionTierNotesAttachmentBackingBean.tierSysId}"></h:inputHidden>
		<h:inputHidden id="questionId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.questionId}"></h:inputHidden>
		<h:inputHidden id="primaryentityId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.primaryEntityID}"></h:inputHidden>
		<h:inputHidden id="primaryEntytyType"
			value="#{contractQuestionTierNotesAttachmentBackingBean.primaryType}"></h:inputHidden>
		<h:inputHidden id="benefitComponentId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.benefitComponentId}"></h:inputHidden>
		<h:inputHidden id="benefitDefnId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.benefitDefnId}"></h:inputHidden>
		<h:inputHidden id="adminLvlOptionId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.adminLvlOptionAssnSysId}"></h:inputHidden>
		<h:inputHidden id="postBackId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.postBack}"></h:inputHidden>
		<h:inputHidden id="prevVersionId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.previousNoteVersion}"></h:inputHidden>

		<h:inputHidden id="viewNoteId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.noteId}"></h:inputHidden>
		<h:inputHidden id="viewNoteName"
			value="#{contractQuestionTierNotesAttachmentBackingBean.noteName}"></h:inputHidden>
		<h:inputHidden id="viewNoteVersion"
			value="#{contractQuestionTierNotesAttachmentBackingBean.version}"></h:inputHidden>
		<h:inputHidden id="dummyHidden"></h:inputHidden> <h:inputHidden
			id="prevNoteIdSelectedId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.prevNoteIdSelected}"></h:inputHidden>

		<h:inputHidden id="newNoteId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.newNoteIdSelected}"></h:inputHidden>
		<h:inputHidden id="questionToProcessId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.questionId}"></h:inputHidden>
		<h:inputHidden id="noteAction"
			value="#{contractQuestionTierNotesAttachmentBackingBean.noteAction}"></h:inputHidden>
		<h:inputHidden id="versionId"
			value="#{contractQuestionTierNotesAttachmentBackingBean.noteVersion}"></h:inputHidden>
		<h:commandLink id="saveLink" style="display:none; visibility: hidden;"
			action="#{contractQuestionTierNotesAttachmentBackingBean.saveAction}">
		</h:commandLink> <h:commandLink id="refreshId"
			style="display:none; visibility: hidden;">
		</h:commandLink>
		<h:inputHidden id ="notepresentId"  value ="#{contractQuestionTierNotesAttachmentBackingBean.noteAttached}"></h:inputHidden>
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
