<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">

</script>
<TITLE>Compare Note</TITLE>
<base target="_self">
</HEAD>
<f:view>
	<BODY onkeydown="return submitOnEnterKey('notesCompareForm:compareButton');"><hx:scriptCollector id="scriptCollector1">
	
	<TABLE width="100%" cellpadding="0" cellspacing="0">
<tr><td>
</td>
</tr>
		<TR>
			<TD><h:form styleClass="form" id="notesCompareForm">
						<h:inputHidden id="loadPage"
									value="#{notesCompareBackingBean.loadCompare}"></h:inputHidden>
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>



							<TD colspan="2" valign="top" class="ContentArea" >
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD width="168" height="40">&nbsp;<h:outputText value="Base Note Name "/>
										</TD>
										<TD width="221" height="40"><h:outputText id="baseNoteName" value="#{notesCompareBackingBean.name}"/></TD>
									</TR>
									<TR>
										<TD width="168" height="40">&nbsp;<h:outputText value="Note Name "/>
										</TD>
										<TD width="221" height="40"><h:inputText
											styleClass="formInputFieldForNotes" id="txtNoteEntered"
											tabindex="1" maxlength="30" value=" " /></TD>
									</TR>

									
										
									<TR>
										<TD width="139" height="40"><h:commandButton value="Compare" id="compareButton"
											styleClass="wpdButton" onclick="validateNoteName();return false;"
											 tabindex="5">
										</h:commandButton></TD>
										<TD width="221">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
				</TABLE>

		<h:inputHidden id="hiddenNoteNameCompare"
			value="#{notesCompareBackingBean.nameCompare}"></h:inputHidden>
		<h:inputHidden id="hiddenValidateNote"
			value="#{notesCompareBackingBean.validateNoteName}"></h:inputHidden>
		<h:commandLink id="validateNoteName" style="display:none; visibility: hidden;" action="#{notesCompareBackingBean.retrieveNotesForCompare}">
	<f:verbatim/>
	</h:commandLink>

			</h:form></TD>
		</TR>

	</TABLE>
	</hx:scriptCollector></BODY>
</f:view>

<script language="JavaScript">
	function validateNoteName(){
		var noteNameEntered  = document.getElementById('notesCompareForm:txtNoteEntered').value;
		var baseNoteName = document.getElementById('notesCompareForm:baseNoteName').value
		if(noteNameEntered == ''){
			alert('Please Enter Note Name');
			document.getElementById('notesCompareForm:txtNoteEntered').focus();
		}else{
			document.getElementById('notesCompareForm:hiddenNoteNameCompare').value = noteNameEntered;
			document.getElementById('notesCompareForm:validateNoteName').click();
		}
	}
	 function dummyMethod(){
	
		if(document.getElementById('notesCompareForm:hiddenValidateNote').value == 'true'){
			url = '../notes/notesCompareResult.jsp'+getUrl()+'?dt='+new Date();
			 window.open(url,"window","dialogHeight:650px;dialogWidth:1175px;resizable scrollbars menubar=true;status=no;");
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
		}
	}
	

	dummyMethod();


	document.getElementById('notesCompareForm:txtNoteEntered').value = '';

</script>
</HTML>
