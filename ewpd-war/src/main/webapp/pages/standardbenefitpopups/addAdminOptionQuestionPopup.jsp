<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">

<TITLE>Add Question Popup</TITLE>
<base target=_self>
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
</HEAD>
<f:view>
	<BODY>
	<h:form id="addQuesPopupForm">
		<br />
		<TABLE width="600" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td><h:commandButton value="Save" styleClass="wpdbutton"
					id="quesButton"
					onclick="getCheckedItemsForQuestions('addQuesPopupForm:quesPanelPopupTable','addQuesPopupForm:hidQuesPanelTable')"></h:commandButton>
				</td>
			</tr>

		</TABLE>
		<br />
		<TABLE width="100%" cellpadding="0" border="0" id="tabheader"
			class="smallfont">
			<tr>
				<td class="ContentArea">
			<tr>
				<td>
				<div id="quesHeaderDiv"
					style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
					id="quesHeaderPopupTable"
					binding="#{AddAdminOptionQuestionPopupBackingBean.openQuestionHeaderPanel}"
					rowClasses="dataTableOddRow,dataTableEvenRow">
				</h:panelGrid></div>

				<div id="addQuesPanelDiv" style="position:relative;"><h:panelGrid
					id="quesPanelPopupTable"
					binding="#{AddAdminOptionQuestionPopupBackingBean.openQuestionPanel}"
					rowClasses="dataTableEvenRow,dataTableOddRow">
				</h:panelGrid></div>
				</td>
			</tr>
		</TABLE>

		<BR>
		<BR>

		<TABLE width="100%" cellpadding="0" border="0" id="tabheader"
			class="smallfont">
			<tr>
				<td class="ContentArea">
			<tr>
				<td>
				<div id="hidHeaderDiv"
					style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
					id="hidHeaderPopupTable"
					binding="#{AddAdminOptionQuestionPopupBackingBean.hiddenQuesHeaderPanel}"
					rowClasses="dataTableOddRow,dataTableEvenRow">
				</h:panelGrid></div>

				<div id="hidQuesPanelDiv" style="position:relative;"><h:panelGrid
					id="hidQuesPanelTable"
					binding="#{AddAdminOptionQuestionPopupBackingBean.hiddenQuesPanel}"
					rowClasses="dataTableEvenRow,dataTableOddRow">
				</h:panelGrid></div>
				</td>
			</tr>
		</TABLE>


	</h:form>
	</BODY>
</f:view>

<script language="JavaScript1.1">

	setColumnWidth('addQuesPopupForm:quesHeaderPopupTable','15%:60%:25%');
	setColumnWidth('addQuesPopupForm:quesPanelPopupTable','15%:60%:25%');

	setColumnWidth('addQuesPopupForm:hidHeaderPopupTable','15%:60%:25%');
	setColumnWidth('addQuesPopupForm:hidQuesPanelTable','15%:60%:25%');

	function checkAll(id,checkBoxControl){
	if (document.getElementById(id))
		{
			
			var tableObject = document.getElementById(id);
			for (var i=0;i<tableObject.rows.length;i++)
			{
				var checkboxObject = tableObject.rows[i].cells[0].children[0];
				checkboxObject.checked = checkBoxControl.checked;
			}	
		}
}
	
</script>


</HTML>
