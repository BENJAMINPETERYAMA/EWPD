<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<base target=_self>
<TITLE>Notes View Popup</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<h:form id="notesLookUpForm">
		<h:inputHidden id="loadAssociatedNotes" value="#{benefitLevelNotesAttachmentBackingBean.loadAssociatedNotesForView}" ></h:inputHidden>
<br>
		<h:outputText value="No Notes Available." 
					rendered="#{benefitLevelNotesAttachmentBackingBean.associatedNotesList == null}" 
					styleClass="dataTableColumnHeader"/>
<br><br>
		<DIV id="notesDiv">
			<%--<table border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td align="left" width="25"><h:commandButton id="select"
						value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('notesLookUpForm:notesDataTable',3);return false;"></h:commandButton>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
	
			</table> --%>
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR>
						<TD>
						<table id="headerTable" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc" width="100%">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								<%--<td align="left"><h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'notesLookUpForm:notesDataTable'); "></h:selectBooleanCheckbox></TD>--%>
								<TD align="left"><strong> <h:outputText
									value="Note Name">
								</h:outputText> </strong></td>
								<TD align="left"><strong> <h:outputText
									value="Note ID">
								</h:outputText> </strong></td>
								<TD align="left"><strong> <h:outputText
									value="Version">
								</h:outputText> </strong></td>
								<TD align="left"><strong> 
								 </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="overflow:auto;">
						<h:dataTable cellspacing="1" id="notesDataTable" 
							rendered="#{benefitLevelNotesAttachmentBackingBean.associatedNotesList != null}"
							value="#{benefitLevelNotesAttachmentBackingBean.associatedNotesList}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow" width="100%">
							<%--<h:column>
								<f:verbatim>
									<h:selectBooleanCheckbox id="componentChkBox">
									</h:selectBooleanCheckbox>
								</f:verbatim>
							</h:column>--%>
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.version}"></h:inputHidden>
								<h:outputText value="#{singleValue.noteName}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{singleValue.noteId}"></h:outputText>
								
							</h:column>
							<h:column>
								
								<h:outputText value="#{singleValue.version}"></h:outputText>
								
							</h:column>
							<h:column>
								
							<h:column>
                                <f:verbatim>&nbsp;</f:verbatim>
								
								<h:commandButton  alt="View Notes" id="viewButton"
														image="../../images/view.gif"  
								onclick="viewActionForEwpd('#{singleValue.noteId}','#{singleValue.version}','#{singleValue.noteName}'); return false;"></h:commandButton>
								
							</h:column>
								
							</h:column>
						</h:dataTable></DIV>
						</td>
					</tr>
				</TBODY>
			</table>
	</DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
function viewActionForEwpd(noteId,noteVersion,noteName){
	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteId +'&&'+'noteName='+noteName+'&&version='+noteVersion;
	window.showModalDialog(url+getUrl()+ "&temp=" + Math.random(), "","dialogHeight:465px;dialogWidth:827px;resizable=false;status=no;");		
}	
	initialize();
	window.opener = window.dialogArguments.parentWindow;
	function initialize(){
		if(document.getElementById('notesLookUpForm:notesDataTable') != null) {
			setColumnWidth('notesLookUpForm:notesDataTable', '45%:25%:20%:10%');
			setColumnWidth('headerTable', '45%:25%:20%:10%');
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}
	

	/*window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('notesLookUpForm:notesDataTable', window.dialogArguments.selectedValues, 2, 2, 2)
	}*/
</script>
</HTML>

