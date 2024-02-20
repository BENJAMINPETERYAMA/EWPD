<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ViewCommentPrint.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Print Comment Popup</TITLE>
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
	<h:form id="viewNotesDetailsForm">

		<BR />
		<table width="100%" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<h:inputHidden id="commentIdHidden"
			value="#{contractCommentBackingBean.viewSelectedComment}"></h:inputHidden>

		<table>
			<tr>
				<TD valign="top"><h:outputText id="date" value="Date :"></h:outputText>
				</td>
				<td><h:outputText styleClass="formOutputColumnField"
					value="#{contractCommentBackingBean.createdTimeStampForView}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" ></f:convertDateTime>
				</h:outputText><h:outputText id ="space" value =" "> </h:outputText><h:outputText id ="timezone" styleClass="formOutputColumnField" value="#{requestScope.timezone}"></h:outputText></td>

			</tr>
			<tr>
				<TD valign="top"><h:outputText id="userId" value="User :"></h:outputText></td>
				<td><h:outputText styleClass="formOutputColumnField"  value="#{contractCommentBackingBean.viewuserId}">
				</h:outputText></td>

			</tr>
			<tr>
				<TD valign="top"><h:outputText id="comment" value="Comment :"></h:outputText>
				</td>
				<td valign="top"><h:outputText
					id="txtNewComment"
					value="#{contractCommentBackingBean.viewcomment}" ></h:outputText><BR>
				</td>

			</tr>

		</table>


<DIV id="dummyDiv" style="visibility:hidden"></DIV>






	</h:form>
	</BODY>
</f:view>
<script>
	window.print();
</script>

</HTML>

