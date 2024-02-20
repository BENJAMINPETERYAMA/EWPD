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

<TITLE>View Comment Popup</TITLE>
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

				<td align="right"><a href="#"><img src="../../images/print.gif"
					alt="Print" width="19" height="14" border="0"
					onclick="printPage();return false;" /> </a></td>
			</tr>
		</table>
		<h:inputHidden id="commentIdHidden"
			value="#{contractCommentBackingBean.viewSelectedComment}"></h:inputHidden>

		<table>
			<tr>
				<TD valign="top"><h:outputText id="date" value="Date"></h:outputText>
				</td>
				<td><h:outputText styleClass="formOutputColumnField" id="time"
					value="#{contractCommentBackingBean.createdTimeStampForView}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" ></f:convertDateTime>
				</h:outputText><h:outputText id ="space" value =" "> </h:outputText><h:outputText id ="timezone" styleClass="formOutputColumnField" value="#{requestScope.timezone}"></h:outputText></td>

			</tr>
			<tr>
				<TD valign="top"><h:outputText id="userId" value="User"></h:outputText></td>
				<td><h:outputText styleClass="formOutputColumnField" id ="user" value="#{contractCommentBackingBean.viewuserId}">
				</h:outputText></td>

			</tr>
			<tr>
				<TD valign="top"><h:outputText id="comment" value="Comment"></h:outputText>
				</td>
				<td valign="top"><h:inputTextarea
					styleClass="formTxtAreaField_CommentTextView" readonly="true"
					id="txtNewComment"
					value="#{contractCommentBackingBean.viewcomment}" tabindex="5"></h:inputTextarea><BR>
				</td>

			</tr>

		</table>


<DIV id="dummyDiv" style="visibility:hidden"></DIV>

<h:inputHidden id="dateSegmentIdHidden"
								value="#{contractCommentBackingBean.dateSegmentId}"></h:inputHidden>
<h:inputHidden id="commentIdHiddenval"
								value="#{contractCommentBackingBean.commentId}"></h:inputHidden>
<h:inputHidden id="timezoneComment"
											value="#{requestScope.timezone}">	</h:inputHidden>




	</h:form>
	</BODY>
</f:view>
<script>
function printPage(){
	var commentId = document.getElementById('viewNotesDetailsForm:commentIdHiddenval').value;
	var dateSegmentId = document.getElementById('viewNotesDetailsForm:dateSegmentIdHidden').value;
	var timezone = document.getElementById('viewNotesDetailsForm:timezoneComment').value;
	window.showModalDialog('../contract/viewCommentPrint.jsp'+getUrl()+'?commentId='+commentId+'&dateSegmentId='+dateSegmentId+'&timezone='+ timezone+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:950px;resizable=false;status=no');
}
</script>

</HTML>
