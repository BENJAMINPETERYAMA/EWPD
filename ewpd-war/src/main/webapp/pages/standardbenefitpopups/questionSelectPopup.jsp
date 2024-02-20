<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../css/wpd.css">
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
}
.shortDescriptionColumn {
	width: 90%;
}
</style>

<TITLE>Question Select</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form id="questionSelectForm">

		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left"><input type="button" class="wpdbutton"
					name="action" value="Select"
					onClick="getCheckedItems_ewpd('questionSelectForm:questionTable',1);" />
				</td>
			</tr>
		</table>


		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#cccccc">
			<tr>
				<td>
				<div id="popHeading"></div>
				<table id="businessEntityTable" width="100%" cellpadding="0"
					cellspacing="1">
					<tr>
						<td width="6%"><input name="checkbox" type="checkbox"
							id="checkall"
							onClick="checkAll_ewpd(this,'questionSelectForm:questionTable',1,1);"></td>
						<td width="94%" align="center"><strong>Question</strong></td>

					</tr>

					<tr>
						<td colspan="2" width="100%"><h:dataTable styleClass="outputText"
							headerClass="dataTableHeader" id="questionTable" var="eachRow"
							cellpadding="0" width="100%" cellspacing="1"
							value="#{ReferenceDataBackingBeanCommon.questionList}"
							rowClasses="dataTableEvenRow,dataTableOddRow"
							columnClasses="selectOrOptionColumn,shortDescriptionColumn" >

							<h:column>
									<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
										id="QuestionChkBx" value="#{eachRow}"></h:selectBooleanCheckbox>
								
							</h:column>
							<h:column>
								<h:inputHidden id="questionHidden" value="#{eachRow}" />
								<h:outputText id="question" value="#{eachRow}"></h:outputText>
							</h:column>
						</h:dataTable></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<script language="javascript">
			tigra_tables('questionSelectForm:questionTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
			
			matchCheckboxItems_ewpd('questionSelectForm:questionTable', window.dialogArguments.selectedValues, 1, 1, 1);
		</script>
		<!--<input type="button" class="wpdbutton" name="something" value="somevalue" />-->
	</h:form>
	</BODY>
</f:view>
</HTML>

