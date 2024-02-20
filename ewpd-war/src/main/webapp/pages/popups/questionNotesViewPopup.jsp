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
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
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
	<BODY>
	<h:form id="notesOverrideForm">
		<div id ="noNotesDiv">

	<h:inputHidden id="loadNotes" value="#{viewAttachedNotesBackingBean.viewNotes}"></h:inputHidden>
		<h:outputText value="No Notes Available." 
					styleClass="dataTableColumnHeader"/>
		</div>
		<DIV id="notesDiv">
								
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0" >
				<TBODY>
					<TR>
						
						<TD>
						<table id="headerTable" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc" width="100%">
							<tr class="dataTableColumnHeader" 
								style="background-color:#cccccc;" >
								<TD align="left" width="50%"><strong> <h:outputText
									value="Note Name">
								</h:outputText>	</strong></td>
								<TD align="left" width="30%">
									<strong> <h:outputText	value="Note Id">
											 </h:outputText>	</strong>
									</TD>
								<TD align="left" width="20%">
									
									</TD>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="overflow:auto;"
						class="popupDataTableDiv"><h:dataTable id="notesDataTable"
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						width="100%" value="#{viewAttachedNotesBackingBean.notes}"
						var="singleValue"
						rendered="#{viewAttachedNotesBackingBean.notes!=null}"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
							<
							<h:inputHidden id="hiddenNoteName"
								value="#{singleValue.noteName}"></h:inputHidden>
							<h:inputHidden id="statusHidden"
								value="#{singleValue.overrideStatus}"></h:inputHidden>
							<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
							<h:inputHidden id="hiddenVersion" value="#{singleValue.version}"></h:inputHidden>
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
								onclick="viewActions();">
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
     <h:inputHidden id="viewNoteId" value="#{viewAttachedNotesBackingBean.noteId}" /> 
	 <h:inputHidden id="viewNoteName" value="#{viewAttachedNotesBackingBean.noteName}"/> 
	 <h:inputHidden id="viewNoteVersion" value="#{viewAttachedNotesBackingBean.version}"/> 
	
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
initialize();
	
	function initialize(){
		if(document.getElementById('notesOverrideForm:notesDataTable') != null)	{
			if(document.getElementById('notesOverrideForm:notesDataTable').rows.length==0){
				document.getElementById("headerTable").style.visibility = 'hidden';
			}	
			else{
				document.getElementById("headerTable").style.visibility = 'visible';		
				document.getElementById("noNotesDiv").style.visibility = 'hidden';		
			}		
		}
	}   
        
function viewActions(){

		
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteId','notesOverrideForm:viewNoteId');
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','hiddenNoteName','notesOverrideForm:viewNoteName');
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','hiddenVersion','notesOverrideForm:viewNoteVersion');	
		noteIdForView = document.getElementById('notesOverrideForm:viewNoteId').value;
		noteNameForView = document.getElementById('notesOverrideForm:viewNoteName').value;
		noteVersionForView = document.getElementById('notesOverrideForm:viewNoteVersion').value;
		ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+
					'&noteName='+noteNameForView+
					'&version='+noteVersionForView+
					'&temp=' + Math.random(),'dummyDiv','notesOverrideForm:dummyHidden',1,1);
  }
	
</script>
</HTML>
