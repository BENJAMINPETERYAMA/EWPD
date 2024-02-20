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

<TITLE>Notes Detail Popup</TITLE>
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
    <h:form id="viewLegacyNotesDetailsForm">
        <table width="100%" cellpadding="0" cellspacing="0">
            <TBODY>
				<TR><TD align = "center"> <h:outputText styleClass="legacyPagetitle" value="Legacy Note Text"> </h:outputText> </TD></TR>
                <TR>
                    <TD align = "center">
                    <h:inputTextarea styleClass="formTxtAreaField_Legacy_NoteText" readonly ="true"
                        value="#{notesAttachmentBackingBean.legacyNoteText}">
                    </h:inputTextarea>
                    </TD>
                </TR>
            </TBODY>
        </table>
    </h:form>
    </BODY>
</f:view>
</HTML>

