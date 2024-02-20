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

<style type="text/css">
	.notesDivin{
		margin-top: 12px;
		border :0.5px;
		border-style: solid;
		padding:1px;
	}
	
	.marB10{
		margin-bottom: 10px;
	
	}

</style>
</HEAD>
<f:view>
	<BODY>
	<h:form id="viewNotesDetailsForm">

		<DIV id="notesDiv" class="notesDivin" >
		
			<div style="background-color:#cccccc" align="left">
				<strong>Note ID</strong>
			</div>
			<div class="marB10">
				<h:inputHidden id="componentData"
								value="#{notesAttachmentBackingBean.viewNoteName}"></h:inputHidden>
							<h:outputText value="#{notesAttachmentBackingBean.noteId}"></h:outputText>
			</div>
			
			<div style="background-color:#cccccc" align="left">
				<strong>Note Name</strong>
			</div>
			
			<div class="marB10">
				<h:outputText value="#{notesAttachmentBackingBean.viewNoteName}">
							</h:outputText>
			
			</div>
			
			<div style="background-color:#cccccc" align="left">
				<strong>Note Version</strong>
			</div>
			
			<div class="marB10">
				<h:outputText value="#{notesAttachmentBackingBean.version}">
							</h:outputText>
			
			</div>
			
			<div style="background-color:#cccccc" align="left">
				<strong>Note Text</strong>
			</div>
			
			<div id="viewNoteBoxNormal" class="noteViewNormalTextDiv marB10" style="width:100%"></div> 
			
			<div>
			
				<h:inputHidden id="descHidden"
								value="#{notesAttachmentBackingBean.noteDesc}"></h:inputHidden>
			</div>
		</DIV>



	</h:form>
	</BODY>
</f:view>
<script language="JavaScript">

copyFormattedNotesToViewDiv('viewNoteBoxNormal', 'viewNotesDetailsForm:descHidden' );
function copyFormattedNotesToViewDiv(noteDescDiv, noteDesc){
		var formattedNoteText = document.getElementById(noteDesc).value;
		if(trim(formattedNoteText) == '')
			return;
		var divObjNormal = document.getElementById(noteDescDiv);
		var textLines = formattedNoteText.split('\n');
		var divValue1 = '';
		var divValue2 = '';
		for(i=0; i<textLines.length; i++) {		
				divValue1 += (textLines[i]+"\n");			
		}
		divObjNormal.innerText = divValue1;
	}
</script>
</HTML>
