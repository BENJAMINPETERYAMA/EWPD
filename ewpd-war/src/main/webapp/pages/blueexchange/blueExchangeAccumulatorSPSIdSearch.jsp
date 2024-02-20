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
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>

<TITLE>Accumulator SPS Id Popup</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('referenceSelectPopupForm:searchButn');">
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="referenceSelectPopupForm">
		<w:messageForPopup />
		<table border="0" cellpadding="5" cellspacing="0" width="100%">

			<tr align="center">
				<TD width="15%"><h:outputText value="Description"></h:outputText></TD>
				<TD width="10%"><h:inputText id="searchText" size="10"
					styleClass="formInputField"
					value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
					maxlength="25"></h:inputText></TD>
				<TD width="10%"><h:commandButton id="searchButn"
					styleClass="wpdbutton" value="Search"
					action="#{ReferenceDataBackingBeanCommon.accumulatorSPSIdSearch}"></h:commandButton>
				</TD> 
			</tr>
			<tr>
				
				<td width="10%">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('referenceSelectPopupForm:referenceSelectPopupTable',2);return false;"></h:commandButton>
				</td>
				<td width="10%"></td>
				<td width="10%"></td>
			</tr>
		</table>

		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0">

			<tr>
				<td>
<div id="headerDiv">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr>

					<!-- 	<TD align="center" valign="middle" width="10%"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'referenceSelectPopupForm:referenceSelectPopupTable'); ">
							</h:selectBooleanCheckbox></TD> -->
						<TD align="center" width="10%"><strong><h:outputText>&nbsp;</h:outputText></strong></TD>	
						<TD align="center" width="20%"><strong><h:outputText
							value="SPS Identifier"></h:outputText></strong></TD>	
						<TD align="center" width="70%"><strong><h:outputText
							value="Description"></h:outputText></strong></TD>

					</tr>

				</table>
</div>
				</td>
			</tr>
			<tr>
				<td>

				<DIV id="popupDataTableDiv" style="overflow:auto;" class="popupDataTableDiv"
					style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
	<!--   WAS 6.0 Migration Changes - removed ',' symbol from columnClasses -->
				<h:dataTable id="referenceSelectPopupTable" cellspacing="1"
					width="100%" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
					<h:column>
											
						<f:verbatim><wpd:singleRowSelect groupName="reference" id="lineOfBusinessChkBox"
								rendered="true"></wpd:singleRowSelect></f:verbatim>
					</h:column>
					<h:column>
						
						<h:inputHidden id="hiddenReference" value="#{eachRow.description}"></h:inputHidden>	
						<h:inputHidden id="hiddenReferenceCode"
							value="#{eachRow.primaryCode}"></h:inputHidden>
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
					</h:column>
					<h:column>
									
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.description}"></h:outputText>
					</h:column>
				</h:dataTable></DIV>
				</td>
			</tr>
		</table>						
	</h:form>
	</BODY>

</f:view>
<script language="javascript">
tigra_tables('referenceSelectPopupForm:referenceSelectPopupTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
if(null != document.getElementById('referenceSelectPopupForm:referenceSelectPopupTable'))
	matchCheckboxItems_ewpd('referenceSelectPopupForm:referenceSelectPopupTable',window.dialogArguments.selectedValues,2,2,2);
if(document.getElementById('referenceSelectPopupForm:referenceSelectPopupTable').rows.length < 1){
divObject= document.getElementById('headerDiv');
divObject.style.visibility = 'hidden';
divObject.style.height = "0px";
divObject.style.position = 'absolute';
}	

</script>

</HTML>
