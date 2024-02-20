<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
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
<TITLE>Product Popup</TITLE>
</HEAD>
<f:view>
<BODY>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="ProductPopupForm">
		<h:outputText value="No Products Available." 
					rendered="#{migrationProductInfoBackingBean.validProducts == null}" 
						styleClass="dataTableColumnHeader"/>
	<DIV id="productDiv">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
            <tr>
				<td>
				<TABLE>
					<TBODY>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</td>
			</tr>
			<tr>
				<td align="left">			
					<h:commandButton id="txtSelect" styleClass="wpdbutton" value="Select" 
							onclick="getCheckedItems_ewpd('ProductPopupForm:ProductPopupTable',3);return false;">
					</h:commandButton>
				</td>
			</tr>
		</table>

		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table id="headerTable" width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR class="dataTableColumnHeader" style="background-color:#cccccc;">
							<TD width="6%" align="left">
								<f:verbatim></f:verbatim>
							</TD>
							<TD width="94%" align="center">
								<strong><h:outputText value="Product"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="ProductPopupTable" rendered="#{migrationProductInfoBackingBean.validProducts != null}"
							value="#{migrationProductInfoBackingBean.validProducts}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="Product" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>
						</h:column>
						<h:column>
							
							<h:inputHidden id="hiddenProductId" value="#{eachRow.productKey}"></h:inputHidden>
							<h:inputHidden id="hiddenProductDesc" value="#{eachRow.productName}"></h:inputHidden>
							<h:inputHidden id="hiddenProductFamilyDesc" value="#{eachRow.productFamilyDesc}"></h:inputHidden>

							<f:verbatim>
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{eachRow.productName}"></h:outputText>
							</f:verbatim>
						</h:column>
						
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
</DIV>
	</h:form>
		
			
</BODY>
		
</f:view>
<script language="JavaScript">
initialize();
function initialize(){
	
		if(document.getElementById('ProductPopupForm:ProductPopupTable') != null) {
			setColumnWidth('ProductPopupForm:ProductPopupTable', '8%:92%');
		}else {
			document.getElementById("productDiv").style.visibility = 'hidden';
		}
	}

matchCheckboxItems_ewpd('ProductPopupForm:ProductPopupTable', window.dialogArguments.selectedValues, 2, 2, 2)
</script>

</HTML>