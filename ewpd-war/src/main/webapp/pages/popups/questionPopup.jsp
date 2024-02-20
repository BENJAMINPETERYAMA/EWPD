<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 8%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 92%;
	vertical-align: middle;
	 style: text-indent: 2px;
}
</style>
<TITLE>Questions Popup</TITLE>
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
	<h:form id="questionForm">
	<h:inputHidden id="questionKey"
					value="#{notesBackingBean.questionValues}">
				</h:inputHidden>
	<table >
			<tr>
				<td>
					<w:messageForPopup></w:messageForPopup>
				</td>
			</tr>
	</table>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('questionForm:questionTable',2);return false;"></h:commandButton>
				</td>
			</tr>
			
		</table>

		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="8%" align="center" valign="middle">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'questionForm:questionTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD width="94%" align="center">
								<strong><h:outputText value="Questions"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						cellspacing="1" width="100%" id="questionTable"
						value="#{notesBackingBean.questionList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
						
								<h:selectBooleanCheckbox id="questionChkBox">
								</h:selectBooleanCheckbox>
							
							
						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden value="#{eachRow.name}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.id}"></h:inputHidden>
							<h:outputText value="#{eachRow.name}"></h:outputText>
						
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {

		matchCheckboxItems_ewpd('questionForm:questionTable', window.dialogArguments.selectedValues, 2, 2, 2);
	}
</script>
</HTML>



