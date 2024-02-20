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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->
<!--   WAS 6.0 Migration Changes - to fix alignment for data table -->	
<style type="text/css">
.selectOrOptionColumn {	
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {	
}
.longDescriptionColumn {	
}
.finalColumn{   
 }
 </style>
<TITLE>Note Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
<BODY>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="notesLookUpForm">
	<h:inputHidden value="#{notesAttachmentBackingBean.retrieveAllNotes}"></h:inputHidden>
	<w:messageForPopup/>
<!--  <div id="noNotesDiv">
		<h:outputText value="No Notes Available." 
					rendered="#{notesAttachmentBackingBean.notes == null}" 
					styleClass="dataTableColumnHeader"/></div>-->	
		<DIV id="notesDiv">
			<table border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td align="left">&nbsp;<h:commandButton id="select"  disabled="true" 
						value="Select" styleClass="wpdbutton"
						onclick="showDivChild();return false;"></h:commandButton>
					</td>
				</tr>				
			</table>
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR>
						<TD>
						<table id="headerTable" border="0" cellspacing="1" cellpadding="0" width="100%"
							bgcolor="#cccccc">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								<td align="center" valign="middle" width="10%"><h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'notesLookUpForm:notesDataTable'); "></h:selectBooleanCheckbox></TD>
								<TD width="30%" valign="middle" align="center"><strong> <h:outputText
									value="Note Id">
								</h:outputText> </strong></td>
								<TD width="40%" valign="middle" align="center"><strong> <h:outputText
									value="Note Name">
								</h:outputText> </strong></td>
								<TD width="20%" align="left"><strong> <h:outputText
									value="Version">
								</h:outputText> </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv"  style="height:313px;overflow:auto;">
						<!--   WAS 6.0 Migration Changes - to fix alignment for data table -->
						<h:dataTable cellspacing="1" id="notesDataTable" headerClass="fixedDataTableHeader"
							rendered="#{notesAttachmentBackingBean.notes != null}"
							value="#{notesAttachmentBackingBean.notes}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow"
							columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,finalColumn">
							<h:column>
							
								<h:selectBooleanCheckbox id="componentChkBox">
								</h:selectBooleanCheckbox>
							
							
						</h:column>
							<h:column>
							
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.version}"></h:inputHidden>
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteId}"></h:outputText>
							</h:column>
							<h:column>
								
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteName}"></h:outputText>
								
							</h:column>
							<h:column>
							
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.version}"></h:outputText>
								
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
		document.getElementById('notesLookUpForm:select').disabled  = false;
		//var tableObject=document.getElementById('notesLookUpForm:notesDataTable');
		//if(tableObject.rows.length > 0){
		//	document.getElementById("noNotesDiv").style.visibility = 'hidden';
		//	document.getElementById("noNotesDiv").style.height = "0px";;
		//}	
	function showDivChild(){
		//window.dialogArguments.parentWindow.document.getElementById('divRow').style.display='';
		getCheckedItems_ewpd('notesLookUpForm:notesDataTable',3);
	}	
	initialize();
	window.opener = window.dialogArguments.parentWindow;
	function initialize(){
		if(document.getElementById('notesLookUpForm:notesDataTable') != null) {
			setColumnWidth('notesLookUpForm:notesDataTable', '30:120:280:60');
			setColumnWidth('headerTable', '30:120:280:60');
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}
	

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_ewpd('notesLookUpForm:notesDataTable', window.dialogArguments.selectedValues, 3, 2, 2)
	}
</script>
</HTML>
