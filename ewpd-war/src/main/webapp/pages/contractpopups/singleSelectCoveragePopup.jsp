<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectcoverageIdColumn {
	
}.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}

.selectcoverageIdColumn1 {
	
}.shortDescriptionColumn1 {
	width: 80%;
}
.longDescriptionColumn1 {
	width: 15%;
}
</style>

<TITLE>Coverage Popup</TITLE>
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
	<h:form id="coverageForm">
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getSelected();return false;" disabled="true"></h:commandButton>
				</td>
			</tr>

		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="2"
						bgcolor="#cccccc">
						<tr height=200%>
							<td width="94%" align="center"><strong> <h:outputText
								value="Coverage">
							</h:outputText> </strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="tr1">
					<td>
					<DIV id="popupDataTableDiv1" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectcoverageIdColumn,shortDescriptionColumn,longDescriptionColumn"
						cellspacing="1" width="100%" id="coverageDataTable1"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
                        rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}" var="coverageValue"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<wpd:singleRowSelect groupName="coverage" id="coverageId"
								rendered="true"></wpd:singleRowSelect>
						</h:column>
						<h:column>
						    
							<h:inputHidden value="#{coverageValue.description}"></h:inputHidden>
							<h:inputHidden value="#{coverageValue.primaryCode}"></h:inputHidden>
  							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{coverageValue.primaryCode}"></h:outputText>
						</h:column>
						<h:column>
  							<f:verbatim>&nbsp;</f:verbatim>
                           
							<h:outputText value="#{coverageValue.description}"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
                <tr id="tr2">
					<td>
					<DIV id="popupDataTableDiv2" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"  columnClasses="selectcoverageIdColumn1,shortDescriptionColumn1,longDescriptionColumn1"
						cellspacing="1" width="100%" id="coverageDataTable2"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
                        rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}" 
                        var="coverageValue"                       
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<wpd:singleRowSelect groupName="coverage" id="coverageId"
								rendered="true"></wpd:singleRowSelect>
						</h:column>
						<h:column>
				         	
							<h:inputHidden value="#{coverageValue.description}"></h:inputHidden>
							<h:inputHidden value="#{coverageValue.primaryCode}"></h:inputHidden>
  							<f:verbatim>&nbsp;</f:verbatim>
                            <h:outputText value="#{coverageValue.description}"></h:outputText>							
						</h:column>
						<h:column>
  							<f:verbatim>&nbsp;</f:verbatim>
                          
							<h:outputText value="#{coverageValue.primaryCode}"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
        <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
document.getElementById('coverageForm:selectButton').disabled  = false;
matchCheckboxItems_ewpd('coverageForm:coverageDataTable1',window.dialogArguments.selectedValues,2,2,2);
matchCheckboxItems_ewpd('coverageForm:coverageDataTable2',window.dialogArguments.selectedValues,2,2,2);
var sort = document.getElementById('coverageForm:hiddensortorder');
var TR1 = document.getElementById('tr1');
var TR2 = document.getElementById('tr2');
var div1 = document.getElementById('popupDataTableDiv1');
var div2 = document.getElementById('popupDataTableDiv2');

	
if(sort != null && sort.value == 'code'){
	tigra_tables('coverageForm:coverageDataTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div2.style.visibility = 'hidden';
	TR2.style.visibility = 'hidden';      
	div2.style.height = '1px';
	TR2.style.height = '1px';
	

}
else if(sort != null && sort.value == 'desc'){
    tigra_tables('coverageForm:coverageDataTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div1.style.visibility = 'hidden';
	TR1.style.visibility = 'hidden';    
    div1.style.height = '1px';
	TR1.style.height = '1px';   

}
function getSelected(){
if(sort != null && sort.value == 'code'){

getCheckedItems_ewpd('coverageForm:coverageDataTable1',2);return false;
}
else if(sort != null && sort.value == 'desc'){

getCheckedItems_ewpd('coverageForm:coverageDataTable2',2);return false;
}
}

</script>
</HTML>

