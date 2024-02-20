<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
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
<f:view>
<TITLE><h:outputText value='#{popupBackingBean.headerName} '></h:outputText></TITLE>


</HEAD>

	<BODY>
	<h:form id="itemCodeForm">
	<h:inputHidden value="#{popupBackingBean.records}"></h:inputHidden>
	<h:outputText value="No Catalog is Available."
								rendered="#{popupBackingBean.searchList == null}"
								styleClass="dataTableColumnHeader"/>
	<DIV id="catalogAssociationDiv">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('itemCodeForm:catalogSelectPopupTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" id="headerTable"
						bgcolor="#cccccc">
						<tr>
							<td align="left" width="6%"> </TD>
							<TD align="center" width="94%"><strong> <h:outputText value='#{popupBackingBean.headerName} '>
							</h:outputText> </strong></td>
						</tr>
					</table>
					</TD>
				</TR>


				<tr>
					<td>
					
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%"
						id="catalogSelectPopupTable" var="indicativeDetails" rendered="#{popupBackingBean.searchList != null}"
						value="#{popupBackingBean.searchList}"
						cellpadding="0" bgcolor="#cccccc" >
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="indicative" id="minor"
									rendered="true"></wpd:singleRowSelect>					
							</f:verbatim>
						</h:column>
						<h:column>
							<f:verbatim>
							<h:inputHidden value="#{indicativeDetails.code}" />	
							<h:inputHidden value="#{indicativeDetails.description}" />		
							<h:outputText  value="#{indicativeDetails.description}"></h:outputText>
							
							
							<f:verbatim>&nbsp;</f:verbatim>
							</f:verbatim>
						</h:column>
					</h:dataTable></DIV></td>
				</tr>

			</TBODY>
		</table>
		</DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
initialize();
function initialize(){
	if(document.getElementById('itemCodeForm:catalogSelectPopupTable') != null) {
		setColumnWidth('itemCodeForm:catalogSelectPopupTable', '50:350:30');
		setColumnWidth('headerTable', '50:350:30');
	}else {
		document.getElementById("catalogAssociationDiv").style.visibility = 'hidden';
	}
}
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}

if(hiddenObj.value) {
	matchCheckboxItems_ewpd('itemCodeForm:catalogSelectPopupTable', window.dialogArguments.selectedValues, 2, 1, 2);
}

</script>
</HTML>

