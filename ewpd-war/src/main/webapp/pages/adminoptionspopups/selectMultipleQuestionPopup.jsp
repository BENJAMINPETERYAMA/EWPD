<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 90%;
}

</style>
<TITLE>Question Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY 
	onkeypress="return submitOnEnterKey('questionSelectPopupForm:searchButn');" >
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="questionSelectPopupForm">
		<h:inputHidden id="adminIdToNextPage"
					value="#{ReferenceDataBackingBeanCommon.adminId}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr align="center">
				<TD width="15%"><h:outputText value="Question Description"></h:outputText></TD>
				<TD width="10%"><h:inputText id="searchText" size="10"
					styleClass="formInputField"
					value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
					maxlength="250"></h:inputText></TD>
				<TD width="10%"><h:commandButton id="searchButn"
					styleClass="wpdbutton" value="Search"
					action="#{ReferenceDataBackingBeanCommon.searchQuestions}"></h:commandButton>
				</TD> 
			</tr>
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpdForQuestions('questionSelectPopupForm:questionSelectPopupTable',4);return false;"></h:commandButton>
				</td>
			</tr>
		</table>

	<BR/>
		<table width="100%" border="0" align="right" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
			<div id="headerDiv">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr>
						
							<TD align="center" valign="middle" width="9%"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'questionSelectPopupForm:questionSelectPopupTable'); ">
							</h:selectBooleanCheckbox></TD>
							<TD align="center" width="90%"><strong><h:outputText
								value="Questions "></h:outputText></strong></TD>
						
					</tr>
				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<DIV id="popupDataTableDiv" class="popupDataTableDiv"
					style="position:relative;z-index:1;font-size:10px;overflow:auto;">
				<h:dataTable id="questionSelectPopupTable" cellspacing="1"
					width="100%" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.questionSearchResultList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn">
					<h:column>
						
						<f:verbatim>
							<h:selectBooleanCheckbox id="questionChkBox">
							</h:selectBooleanCheckbox>
						</f:verbatim>
					</h:column>
					<h:column>
						
                          <f:verbatim>&nbsp;</f:verbatim>
						<h:inputHidden id="hiddenDesc"
							value="#{eachRow.questionDescription}"></h:inputHidden>
                         <h:inputHidden id="hiddenQuestion"
							value="#{eachRow.questionNumber}"></h:inputHidden>
						<h:inputHidden id="hiddenSPSId"
							value="#{eachRow.spsId}"></h:inputHidden>	
						<h:inputHidden id="hiddenSPSDesc"
							value="#{eachRow.description}"></h:inputHidden>	
                          <f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.questionDescription}"></h:outputText>
					</h:column>
				</h:dataTable></DIV>
				</td>
			</tr>
		</table>
	</h:form>
	</BODY>

</f:view>
<script language="javascript">
	
	tigra_tables('questionSelectPopupForm:questionSelectPopupTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
 	
	matchCheckboxItems_ewpd('questionSelectPopupForm:questionSelectPopupTable', window.dialogArguments.selectedValues, 4, 2, 1);
	// setColumnWidth('questionSelectPopupForm:questionSelectPopupTable', '5:250');

if(document.getElementById('questionSelectPopupForm:questionSelectPopupTable').rows.length < 1){
divObject = document.getElementById('headerDiv');
divObject.style.visibility = 'hidden';
divObject.style.height = "0px";
divObject.style.position = 'absolute';
}
</script>
</HTML>
