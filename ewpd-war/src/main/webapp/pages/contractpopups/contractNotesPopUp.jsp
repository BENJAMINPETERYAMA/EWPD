<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">

<TITLE>Note Popup</TITLE>
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
	<h:inputHidden value = "#{ContractNotesBackingBean.retrieveContractNotesRecords}"></h:inputHidden>
	<div id="noNotesDiv">
		<h:outputText value="No Notes Available."  rendered="#{ContractNotesBackingBean.noteListForPopup == null}"
         styleClass="dataTableColumnHeader"
		/>
    </div>
		<DIV id="notesDiv"  style="overflow-y:hidden;">
			<table border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td align="left" width="100%">&nbsp;<h:commandButton id="select"
						value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems();return false;" disabled="true"></h:commandButton>
					</td>
				</tr>
			</table>
			
			<TABLE border="0" > 
			<tr>
			<td>
			<DIV id ="legacyNoteDiv" >
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR>
						<TD>
						<table border="0" cellspacing="1" cellpadding="0" width="100%"
							bgcolor="#7f9db9">
							<tr>
								<TD width="100%">
									<strong> <h:outputText value="Legacy Note">
									</h:outputText> </strong></TD>
							</tr>
						</table>
						</TD>
					</TR>
					<TR>
						<TD>
						<table id="headerTable1" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								<td align="left"></TD>
								<TD align="left"><strong> <h:outputText
									value="Description">
								</h:outputText> </strong></td>
								<TD align="left"><strong>&nbsp; </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					
					<tr>
						<td>
						<DIV id="popupDataTableDiv1" style=" overflow:auto;">
						<h:dataTable cellspacing="1" id="legacyNotesDataTable" 
							rendered="#{ContractNotesBackingBean.legacyNotesList != null}"
							value="#{ContractNotesBackingBean.legacyNotesList}" styleClass="outputText"
							var="singleValue" cellpadding="0" bgcolor="#cccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="ContractNotes" id="minor2" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.version}"></h:inputHidden>
 	  							<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteName}"></h:outputText>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.version}"></h:inputHidden>
	  							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:commandButton  alt="View Legacy Note" id="viewButton"
														image="../../images/view.gif"
								onclick="viewAction('#{singleValue.noteId}','#{singleValue.version}','#{singleValue.noteName}'); return false;"></h:commandButton>
							</h:column>
						</h:dataTable></DIV>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</TBODY>
			</table>
			</DIV>
			</td>
			</tr>
			<tr>
			<td>
			<DIV id ="ewpdNoteDiv" >
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<tr>
						<TD>
						<table border="0" cellspacing="1" cellpadding="0" width="100%"
							bgcolor="#7f9db9">
							<tr>
								<TD width="100%">
									<strong> <h:outputText value="EWPD Notes">
									</h:outputText> </strong></TD>
							</tr>
						</table>
						</TD>
					<tr>
					<TR>
						<TD>
						
						<table id="headerTable2" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								<td align="left"></TD>
								<TD align="left"><strong> <h:outputText
									value="Note Id">
								</h:outputText> </strong></td>
								<TD align="left"><strong> <h:outputText
									value="Note Name">
								</h:outputText> </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv2" style="height:280;overflow:auto;">
						<h:dataTable cellspacing="1" id="wpdNotesDataTable" 
							rendered="#{ContractNotesBackingBean.ewpdNotesList != null}"
							value="#{ContractNotesBackingBean.ewpdNotesList}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="ContractNotes" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.version}"></h:inputHidden>
 	  							<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteId}"></h:outputText>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.version}"></h:inputHidden>
	  							<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteName}"></h:outputText>
								
							</h:column>
						</h:dataTable></DIV>
						</td>
					</tr>
				</TBODY>
			</table>
			</DIV>
			</td>
			</tr>
			</TABLE>
			<!-- /DIV-->
	</DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	initialize();
	window.opener = window.dialogArguments.parentWindow;
	function initialize(){
		var tableObject1=document.getElementById('notesLookUpForm:legacyNotesDataTable');
		var tableObject2=document.getElementById('notesLookUpForm:wpdNotesDataTable');
		var legacyNote = false ;
		var ewpdNote = false ;
		if(tableObject1)
			legacyNote =  true;
		if(tableObject2)
			ewpdNote =  true;

		if(legacyNote || ewpdNote){
			if(legacyNote){
				setColumnWidth('notesLookUpForm:legacyNotesDataTable', '40:150:300');
				setColumnWidth('headerTable1', '40:150:300');	
				if(!ewpdNote){
					document.getElementById("ewpdNoteDiv").style.visibility = 'hidden';
					document.getElementById("ewpdNoteDiv").style.height = "0px";
					document.getElementById("ewpdNoteDiv").innerText = null;
				}
			}
			if(ewpdNote){
				setColumnWidth('notesLookUpForm:wpdNotesDataTable', '40:150:300');
				setColumnWidth('headerTable2', '40:150:300');
				if(!legacyNote){
					document.getElementById("legacyNoteDiv").style.visibility = 'hidden';
					document.getElementById("legacyNoteDiv").style.height = "0px";
					document.getElementById("legacyNoteDiv").innerText = null;
				}
			}
			document.getElementById("noNotesDiv").style.visibility = 'hidden';
			document.getElementById("noNotesDiv").style.height = "0px";
		}else {

			document.getElementById("notesDiv").style.visibility = 'hidden';
			document.getElementById("noNotesDiv").style.visibility = 'visible';
		}
	}
	
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('notesLookUpForm:legacyNotesDataTable', window.dialogArguments.selectedValues, 3, 2, 2)
		matchCheckboxItems_ewpd('notesLookUpForm:wpdNotesDataTable', window.dialogArguments.selectedValues, 3, 2, 2)
	}
	function viewAction(noteId,noteVersion,noteName){
		var url = '../popups/viewLegacyNoteDetails.jsp?noteId='+noteId +'&&'+'noteName='+noteName+'&&version='+noteVersion;
		window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:450px;resizable=false;status=no;");		
	}	

	function getCheckedItems()
	{
		var legacyTableObj = document.getElementById('notesLookUpForm:legacyNotesDataTable');
		var ewpdTableObj = document.getElementById('notesLookUpForm:wpdNotesDataTable');

		var selectedValue ='';
		if(legacyTableObj){
       		selectedValue = getSelectedValue(legacyTableObj);
		}
		if(selectedValue == ''){
			if(ewpdTableObj){
				selectedValue =  getSelectedValue(ewpdTableObj);
			}
		}
		if(selectedValue != ''){
			window.returnValue = selectedValue;
			 window.dialogArguments.parentWindow.document.getElementById('divRow').style.display='';
			<%
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				window.close();	
				<%
				}
				else {
				%>
				parent.document.getElementsByTagName('dialog')[0].close();
				
				<%
				}
			%>
			return window.returnValue;
		}else if(selectedValue == ''){
			window.returnValue = selectedValue;
			<%
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				window.close();	
				<%
				}
				else {
				%>
				parent.document.getElementsByTagName('dialog')[0].close();
				<%
				}
			%>	
			return window.returnValue;
		}
		return  false;
	}

	function getSelectedValue(tableObj)
	{
		var selectedValues = '';
		var currentCell;
		for (var i=0;i<tableObj.rows.length;i++)
		{
			var checkboxObject = tableObj.rows[i].cells[0].children[0];
			currentCell = tableObj.rows[i].cells[1];
			if (checkboxObject && checkboxObject.checked) {
				selectedValues = currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
				return selectedValues;
			}	
		}
		return '';
	}
	document.getElementById('notesLookUpForm:select').disabled = false;

</script>
</HTML>
