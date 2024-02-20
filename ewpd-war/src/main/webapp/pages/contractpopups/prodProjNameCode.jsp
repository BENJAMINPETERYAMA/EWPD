<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "org.apache.commons.lang.StringEscapeUtils" %>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<BASE target="_self" />
<f:view>
<BODY>

	<h:form id="NameCodePopupForm">	
		<h:inputHidden id="search" value="#{ReferenceDataBackingBeanCommon.searchTermQualifier}"></h:inputHidden>
							<TABLE id="message">
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
											<div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect" styleClass="wpdbutton" value="Select" 
							onclick="getCheckedItems_ewpd('NameCodePopupForm:NameCodePopupTable',2);return false;" disabled="true">
					</h:commandButton>
				</td>
			</tr>
		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="10%" align="left" height="23">
								<f:verbatim></f:verbatim>
							</TD>
							<TD width="90%" align="left" height="23">
								<strong><h:outputText value="Product/Project Name Code"></h:outputText></strong>
							</TD>
						</TR>
						<tr>	
							<TD align="left" width="10%" height="19">
								<f:verbatim></f:verbatim>
							</TD>					
						<TD align="left" width="90%" height="19"><h:inputText id="searchText"
							size="10" styleClass="formInputField"
							value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
							maxlength="34" tabindex="4"
							onkeypress="return expandcontract(event)" readonly = "true"/></TD>
					</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="NameCodePopupTable"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
						rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="NameCode"  id="minor1" rendered="true"></wpd:singleRowSelect>							
							</f:verbatim>
						</h:column>
						<h:column>
							<h:inputHidden id="hiddenNameCodeId" value="#{eachRow.primaryCode}"></h:inputHidden>
							<h:inputHidden id="hiddenNameCodeDesc" value="#{eachRow.description}"></h:inputHidden>
  							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>
								<h:outputText value="#{eachRow.description}"></h:outputText>
							</f:verbatim>
						</h:column>
					</h:dataTable>
					</DIV>
				 
						<div id="errorMessageDiv" class="popupDataTableDiv" style="height:300px;display:none;">
							<table cellpadding="2" 
								   cellspacing="1" bgcolor="#cccccc" 
									border="0" width="100%" >
								<tr>
									<td bgcolor="#e1ecf7" colspan="2" width="100%" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
								font-size:10px;"><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error occurred during processing.</font></td></tr>									              															              							
						   </table>
						</div>	     
					</td>
				</tr>
			</TBODY>
		</table>
		<h:inputHidden id="parentCatalog"
					value="#{ReferenceDataBackingBeanCommon.catalogNameForTermQualifier}"></h:inputHidden>
		<h:inputHidden id="lookUpAction"
					value="#{ReferenceDataBackingBeanCommon.lookUpAction}"></h:inputHidden>
	</h:form>			
</BODY>
		
</f:view>
<script language="JavaScript">
document.getElementById('NameCodePopupForm:txtSelect').disabled  = false;
document.getElementById('NameCodePopupForm:searchText').readOnly = false; 
function expandcontract(dis) {
	if(dis.keyCode=='13'){
		var catalogName = document.getElementById('NameCodePopupForm:parentCatalog').value;
		var lookUpAction = document.getElementById('NameCodePopupForm:lookUpAction').value;
		var searchField = document.getElementById('NameCodePopupForm:searchText');
		var divObj = document.getElementById('popupDataTableDiv');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
		ajaxCall1('../popups/ajaxProjectCodesHelperTable.jsp?parentCatalog='+catalogName+
					'&lookUpAction='+lookUpAction+'&temp='+Math.random(),searchField,divObj,
					'NameCodePopupForm:NameCodePopupTable',errorMsgDiv,attrObj,'','');
		return false;		
	}

}
checkTableAlignemnt();
function checkTableAlignemnt(){
var x = document.getElementById("NameCodePopupForm:NameCodePopupTable");
if(null!=x){
setColumnWidth('NameCodePopupForm:NameCodePopupTable','10%:90%');
}
}

matchCheckboxItems_ewpd('NameCodePopupForm:NameCodePopupTable', window.dialogArguments.selectedValues, 2, 2, 2)

</script>

</HTML>