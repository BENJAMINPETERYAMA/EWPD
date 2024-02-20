<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/adminoptionspopups/SelectOneTermPopup.java" --%><%-- /jsf:pagecode --%>
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
.selectOrOptionColumn1 {
}
.selectOrOptionColumn {
	width: 20%;
}

.shortDescriptionColumn {
	width: 75%;
}
</style>
<TITLE>Term Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="termSelectPopupForm">
	<h:inputHidden id="loadTerm" value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getSelected();return false;"></h:commandButton>
				</td>
			</tr>
		</table>


		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>                
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#cccccc">
					<tr>
						<td width="10%" align="center" valign="middle">&nbsp;</TD>
						<TD align="left" width="20%"></TD>
						<TD width="70%" align="LEFT"><strong><h:outputText
							value="Term">
						</h:outputText> </strong></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr id="tr1">
				<td>
                    <DIV id="popupDataTableDiv1" style="overflow:auto;" class=popupDataTableDiv>
                    <h:dataTable cellspacing="1" width="100%"
					id="termSelectPopupTable1" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses=" selectOrOptionColumn1,selectOrOptionColumn,shortDescriptionColumn">
					<h:column>
						<wpd:singleRowSelect groupName="term" id="minor" rendered="true"></wpd:singleRowSelect>				
					</h:column>
					<h:column>
					
						<h:inputHidden id="hiddenTerm" value="#{eachRow.description}"></h:inputHidden>
						<h:inputHidden id="hiddenTermCode" value="#{eachRow.primaryCode}"></h:inputHidden>						
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
					</h:column>
					<h:column>
					
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.description}"></h:outputText>
					</h:column>
				</h:dataTable></td>
		   </tr>
           <tr id="tr2">
				<td>
                    <DIV id="popupDataTableDiv2" style="overflow:auto;" class=popupDataTableDiv>
                    <h:dataTable cellspacing="1" width="100%" bgcolor="#cccccc"
					id="termSelectPopupTable2" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn1,shortDescriptionColumn,selectOrOptionColumn">
					<h:column>
						<wpd:singleRowSelect groupName="term" id="minor" rendered="true"></wpd:singleRowSelect>						
					</h:column>
					<h:column>
					
						<h:inputHidden id="hiddenTerm2" value="#{eachRow.description}"></h:inputHidden>
						<h:inputHidden id="hiddenTermCode2" value="#{eachRow.primaryCode}"></h:inputHidden>						
						<f:verbatim>&nbsp;</f:verbatim>
                        <h:outputText value="#{eachRow.description}"></h:outputText>						
					</h:column>
					<h:column>
						<f:verbatim>&nbsp;</f:verbatim>
                    
						<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
					</h:column>
				</h:dataTable></td>
			</tr>
		</table>
        <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
	</h:form>

	</BODY>

</f:view>
<script language="javascript">
	
	matchCheckboxItems_ewpd('termSelectPopupForm:termSelectPopupTable1',window.dialogArguments.selectedValues,2,2,2);
    matchCheckboxItems_ewpd('termSelectPopupForm:termSelectPopupTable2',window.dialogArguments.selectedValues,2,2,2);
	var sort = document.getElementById('termSelectPopupForm:hiddensortorder');
	var TR1 = document.getElementById('tr1');
	var TR2 = document.getElementById('tr2');
	var div1 = document.getElementById('popupDataTableDiv1');
	var div2 = document.getElementById('popupDataTableDiv2');
			
	if(sort != null && sort.value == 'code'){
		tigra_tables('termSelectPopupForm:termSelectPopupTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
		div2.style.visibility = 'hidden';
		TR2.style.visibility = 'hidden';
		div2.style.height = '1px';
		TR2.style.height = '1px';
		
	}
	else if(sort != null && sort.value == 'desc'){
	    tigra_tables('termSelectPopupForm:termSelectPopupTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
		div1.style.visibility = 'hidden';
		TR1.style.visibility = 'hidden';
		div1.style.height = '1px';
		TR1.style.height = '1px';
	}
	function getSelected(){
	if(sort != null && sort.value == 'code'){
	
	getCheckedItems_ewpd('termSelectPopupForm:termSelectPopupTable1',2);return false;
	}
	else if(sort != null && sort.value == 'desc'){
	
	getCheckedItems_ewpd('termSelectPopupForm:termSelectPopupTable2',2);return false;
	}
	}

</script>
</HTML>

