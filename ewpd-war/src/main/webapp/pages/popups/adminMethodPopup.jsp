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

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;					
}.shortDescriptionColumn {
	width: 60%;
}
.longDescriptionColumn {
	width: 15%;
}
</style>

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
<TITLE><h:outputText value='#{adminMethodPopupBackingBean.spsName} '/>Popup</TITLE>
</HEAD>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="adminMethodPopupForm">
	<h:inputHidden value="#{adminMethodPopupBackingBean.loadAdminMethodPopup}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('adminMethodPopupForm:adminMethodTable',2);return false;" disabled="true">
				</h:commandButton></td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR height="100%">
					<TD>
					<table width="100%" border="0" cellspacing="2" cellpadding="3"
						bgcolor="#cccccc">
						<TR>
							<TD width="10%" align="left" valign="middle"></TD>
							<TD width="60%" align="center"><strong><h:outputText
								value="Admin Method"></h:outputText></strong><br></TD>
							<TD width="15%" align="center"><strong><h:outputText
								value="Value"></h:outputText></strong></TD>
							<TD width="15%"><h:outputText
								></h:outputText></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr >
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,longDescriptionColumn" cellspacing="1"
						width="100%" id="adminMethodTable"
						value="#{adminMethodPopupBackingBean.adminMethods}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
							<f:verbatim>
								<wpd:singleRowSelect groupName="baseContract" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>

						</h:column>
						<h:column>
							
							<h:inputHidden id = "adminMethodValue" value="#{eachRow.adminMethodDesc}"></h:inputHidden>
							<h:inputHidden id = "adminMethodIdValue" value="#{eachRow.adminMethodId}"></h:inputHidden>
                            <h:outputText escape="false" value="#{eachRow.actualAdminMthdDesc}"></h:outputText>
						</h:column>
						<h:column>
							
							<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.adminMethodNumber}"></h:outputText>
						</h:column>
						<h:column>
							
							<f:verbatim>&nbsp;</f:verbatim>
							<h:commandButton alt="View" id="view"
									image="../../images/view.gif" 
									onclick="getViewDetails();return false;">
									
							</h:commandButton>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
<h:inputHidden id = "validation" value = "#{adminMethodPopupBackingBean.validation}"/>
<h:inputHidden id = "countHidden" value = "#{adminMethodPopupBackingBean.count}"/>
<h:inputHidden id = "adminId" value = "#{adminMethodPopupBackingBean.adminId}"/>
<h:inputHidden id = "entityId" value = "#{adminMethodPopupBackingBean.entityId}"/>
<h:inputHidden id = "entityType" value = "#{adminMethodPopupBackingBean.entityType}"/>
<h:inputHidden id = "adminMethodSysId" value = "#{adminMethodPopupBackingBean.adminMethodSysId}"/>
<h:inputHidden id = "adminMethodSysIdValue" value = "#{adminMethodPopupBackingBean.adminMethodSysIdValue}"/>

	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
document.getElementById('adminMethodPopupForm:txtSelect').disabled  = false;
setColumnWidth('adminMethodPopupForm:adminMethodTable','10%:60%:15%:15%');

matchCheckboxItems_ewpd('adminMethodPopupForm:adminMethodTable', window.dialogArguments.selectedValues,2, 2, 2)


function load(){
	var count = document.getElementById('adminMethodPopupForm:countHidden').value;
	if(count == 1){
		var value = document.getElementById('adminMethodPopupForm:adminMethodTable:0:adminMethodValue').value;
		var idValue = document.getElementById('adminMethodPopupForm:adminMethodTable:0:adminMethodIdValue').value;
		window.returnValue = value+'~'+idValue;
		window.close();
	}
}

function getViewDetails(){
	getFromDataTableToHidden('adminMethodPopupForm:adminMethodTable','adminMethodIdValue','adminMethodPopupForm:adminMethodSysId');
	getFromDataTableToHidden('adminMethodPopupForm:adminMethodTable','adminMethodValue','adminMethodPopupForm:adminMethodSysIdValue');
	var validation = document.getElementById('adminMethodPopupForm:validation').value;
	window.showModalDialog('../popups/adminMethodViewPopup.jsp'+getUrl()+'?&adminId=' + document.getElementById('adminMethodPopupForm:adminId').value + '&entityId=' + document.getElementById('adminMethodPopupForm:entityId').value + '&entityType=' + document.getElementById('adminMethodPopupForm:entityType').value + '&adminMethodSysId=' + document.getElementById('adminMethodPopupForm:adminMethodSysId').value + '&validation=' + validation + '&temp=' + Math.random(),'ViewAdminMethodConfiguration','dialogHeight:400px;dialogWidth:500px;resizable=true;status=no;');
}
</script>

</HTML>
