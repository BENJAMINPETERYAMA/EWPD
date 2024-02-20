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
<TITLE>Head Quarters State Popup</TITLE>
</HEAD>
<f:view>
<BODY>
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="HQStatePopupForm">
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
         
			<tr>
				<td align="left">&nbsp;				
					<h:commandButton id="txtSelect" styleClass="wpdbutton" value="Select" 
							onclick="getSelected();return false;">
					</h:commandButton>
				</td>
			</tr>
		</table>

		<table width="97%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="10%">
								
							</TD>
							<TD width="20%" />
							<TD width="70%" align="left">
								<strong><h:outputText value="Head-Quarter State"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr id="tr1">
					<td>
					<DIV id="popupDataTableDiv1" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="HQStatePopupTable1"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
						rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}" 
 						var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<wpd:singleRowSelect groupName="HQState" id="minor1" rendered="true"></wpd:singleRowSelect>
						</h:column>
						<h:column>
							<f:attribute name="width" value="20%" />
							<h:inputHidden id="hiddenHQStateId" value="#{eachRow.primaryCode}"></h:inputHidden>
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden id="hiddenHQStateDesc" value="#{eachRow.description}"></h:inputHidden>
								<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
							
						</h:column>
						<h:column>
							<f:attribute name="width" value="75%" />
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{eachRow.description}"></h:outputText>
							
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
                
                <tr id="tr2">
					<td>
					<DIV id="popupDataTableDiv2" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="HQStatePopupTable2"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
                        rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:attribute name="width" value="10%"/>
							<f:attribute name="align" value="center" />
							<f:attribute name="valign" value="middle" />
							<f:verbatim>
								<wpd:singleRowSelect groupName="HQState" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>
						</h:column>
						<h:column>
							<f:attribute name="width" value="75%" />
							<h:inputHidden id="hiddenHQStateId2" value="#{eachRow.primaryCode}"></h:inputHidden>
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden id="hiddenHQStateDesc2" value="#{eachRow.description}"></h:inputHidden>
								<h:outputText value="#{eachRow.description}"></h:outputText>
						</h:column>
						<h:column>
							<f:attribute name="width" value="20%" />
                                <f:verbatim>&nbsp;</f:verbatim>
								
                                <h:outputText value="#{eachRow.primaryCode}"></h:outputText>
							
							
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
         <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
	</h:form>
		
			
</BODY>
		
</f:view>
<script language="JavaScript">

matchCheckboxItems_ewpd('HQStatePopupForm:HQStatePopupTable1',window.dialogArguments.selectedValues,2,2,2);
matchCheckboxItems_ewpd('HQStatePopupForm:HQStatePopupTable2',window.dialogArguments.selectedValues,2,2,2);
var sort = document.getElementById('HQStatePopupForm:hiddensortorder');
var TR1 = document.getElementById('tr1');
var TR2 = document.getElementById('tr2');
var div1 = document.getElementById('popupDataTableDiv1');
var div2 = document.getElementById('popupDataTableDiv2');
	
	
if(sort != null && sort.value == 'code'){
	tigra_tables('HQStatePopupForm:HQStatePopupTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div2.style.visibility = 'hidden';
	TR2.style.visibility = 'hidden';    
	div2.style.height = '1px';
	TR2.style.height = '1px';
	
}
else if(sort != null && sort.value == 'desc'){
    tigra_tables('HQStatePopupForm:HQStatePopupTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div1.style.visibility = 'hidden';
	TR1.style.visibility = 'hidden';   
    div1.style.height = '1px';
	TR1.style.height = '1px';
    

}
function getSelected(){
if(sort != null && sort.value == 'code'){

getCheckedItems_ewpd('HQStatePopupForm:HQStatePopupTable1',2);return false;
}
else if(sort != null && sort.value == 'desc'){

getCheckedItems_ewpd('HQStatePopupForm:HQStatePopupTable2',2);return false;
}
}
</script>

</HTML>