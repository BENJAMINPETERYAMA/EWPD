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
	<BODY>
	<h:form id="notesOverrideForm">
<h:inputHidden id="loadNotes"
					value="#{productStructureBenefitDefenitionBackingBean.benefitLineNotes}" />
		<h:outputText value="No Notes Available." 
					rendered="#{productStructureBenefitDefenitionBackingBean.benefitLineNotesObj  == null}" 
					styleClass="dataTableColumnHeader"/>
		<DIV id="notesDiv">
											
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR><TD>&nbsp;</TD></TR>
					<TR><TD>&nbsp;</TD></TR>
					<TR>
						<TD>
						<TABLE id="headerTable" width="100%" border="0"
							bgcolor="#cccccc" cellpadding="2" cellspacing="1">
							<tr class="dataTableOddRow">
								<td width="100%" bgcolor="#cccccc"><center><strong><h:outputText
									value="Notes" styleClass="outputText" /></strong></center></td>
							</tr>
						</TABLE>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="height: 337px; overflow:auto;">
						<h:dataTable cellspacing="1" id="notesDataTable" 
							rendered="#{productStructureBenefitDefenitionBackingBean.benefitLineNotesObj != null}"
							value="#{productStructureBenefitDefenitionBackingBean.benefitLineNotesObj }"
							var="singleValue" cellpadding="1" bgcolor="#cccccc" width="100%"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
								<h:inputHidden id="noteNameHidden" value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden  id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden  id="noteVersionHidden" value="#{singleValue.version}"></h:inputHidden>
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
	<h:inputHidden id="viewNoteId" /> 
	<h:inputHidden id="viewNoteName" /> 
	<h:inputHidden id="viewNoteVersion" /> 
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	function viewAction(){
 		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteId','notesOverrideForm:viewNoteId');
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteNameHidden','notesOverrideForm:viewNoteName');				
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteVersionHidden','notesOverrideForm:viewNoteVersion');	

  }
 initialize();
	
	function initialize(){
	
		if(document.getElementById('notesOverrideForm:notesDataTable') != null) {
			setColumnWidth('notesOverrideForm:notesDataTable', '350:150');
			setColumnWidth('headerTable', '100%');
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}
</script>
</HTML>
