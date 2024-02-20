<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractCoverageBenefitLevelOverridePopup.java" --%><%-- /jsf:pagecode --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractCoverageBenefitLevelOverridePopup.java" --%><%-- /jsf:pagecode --%>
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

	<h:inputHidden id="loadNotes" value="#{contractCoverageNotesOverrideBackingBean.viewNotes}"></h:inputHidden>
		<h:outputText value="No Notes Available." 
					styleClass="dataTableColumnHeader"/>
		</div>
		<h:inputHidden id ="init" value = "#{contractCoverageNotesOverrideBackingBean.notes}"></h:inputHidden>
		<DIV id="notesDiv">
										<w:messageForPopup></w:messageForPopup>
								
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
						<DIV id="popupDataTableDiv" style="height: 300px; overflow:auto;">
						<h:dataTable cellspacing="1" id="notesDataTable" 
							rendered="#{contractCoverageNotesOverrideBackingBean.viewNotesObj != null}"
							value="#{contractCoverageNotesOverrideBackingBean.viewNotesObj}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc" width="100%"
							rowClasses="dataTableEvenRow,dataTableOddRow">
						
							
							<h:column>
                            <h:inputHidden id="noteNameHidden" value="#{singleValue.noteName}"></h:inputHidden>
							<h:inputHidden id="noteIdHidden" value="#{singleValue.noteId}"></h:inputHidden> 
                            <h:inputHidden id="noteVersionHidden" value="#{singleValue.version}"></h:inputHidden>
								
                                	<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
							</h:column>
							<h:column>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText id="noteId" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteId}"></h:outputText>
							</h:column>
							<h:column >
								<f:verbatim>&nbsp;</f:verbatim>
								<h:commandButton  alt="View Legacy Note" id="viewButton"
														image="../../images/view.gif"  rendered = "#{'Y' eq singleValue.legacyIndicator}"
								onclick="viewActions();return false;"></h:commandButton>
								<h:commandButton  alt="View Ewpd Note" id="ewpdButton"
														image="../../images/view.gif"  rendered = "#{'N' eq singleValue.legacyIndicator}"
								onclick="viewActionForEwpd('#{singleValue.noteId}','#{singleValue.version}','#{singleValue.noteName}'); return false;"></h:commandButton>
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




initialize();
function initialize(){
        
		if(document.getElementById('notesOverrideForm:notesDataTable') != null) {
			setColumnWidth('notesOverrideForm:notesDataTable', '150:100:50');
			setColumnWidth('headerTable', '150:100:50');
			document.getElementById("noNotesDiv").style.visibility = 'hidden';
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}
function viewActions(){

 		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteIdHidden','notesOverrideForm:viewNoteId');
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteNameHidden','notesOverrideForm:viewNoteName');				
		getFromDataTableToHidden('notesOverrideForm:notesDataTable','noteVersionHidden','notesOverrideForm:viewNoteVersion');	
        ewpdModalWindow_NotesView('../popups/viewLegacyNoteDetails.jsp'+getUrl()+'?noteId='
											+document.getElementById('notesOverrideForm:viewNoteId').value+'&noteName='
											+escape(document.getElementById('notesOverrideForm:viewNoteName').value)+'&version='
											+document.getElementById('notesOverrideForm:viewNoteVersion').value+'&temp='
											+Math.random(),'dummyDiv','notesOverrideForm:dummyHidden',1,1);

  }

function viewActionForEwpd(noteId,noteVersion,noteName){
	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteId + '&noteName='+escape(noteName)+ '&version='+noteVersion;
	window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:465px;dialogWidth:827px;resizable=false;status=no;");		
}		
</script>
</HTML>
