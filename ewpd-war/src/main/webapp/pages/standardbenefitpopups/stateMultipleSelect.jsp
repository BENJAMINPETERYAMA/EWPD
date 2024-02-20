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
.selectCheckboxColumn {
      width: 10%;
      text-align: center;
      vertical-align: middle; 
}

.descriptionColumn {
     width: 20%;
}
.anotherDescriptionColumn {
	width: 70%;
}	
</style>
<TITLE>Jurisdiction Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="StateMultipleSelectPopupForm">
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>	
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
				<td align="left"><h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getSelected();return false;">
				</h:commandButton></td>
			</tr>
		</table>

		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0"  bgcolor="#cccccc">
			<tr>
				<td>
				<!--  <table id="businessEntityTable" width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>-->
						
						<DIV id="popupTableDiv3" style="height:0px; overflow:auto;"> 
        <table id="businessEntityTable1" width="100%" cellpadding="0"
							cellspacing="0"  bgcolor="#cccccc">

		<tr id="tr3">
                  
								<td width="10%" align="center" valign="middle"><input name="checkbox" type="checkbox"
									id="checkall"
									onClick="checkAll_ewpd(this,'StateMultipleSelectPopupForm:StateMultipleSelectPopupTable1');"></td>
								<td width="20%"></td>
								<td width="70%" align="left"><strong><h:outputText
									value="Provider Arrangement" styleClass="outputText"></h:outputText></strong></td>
							</tr>
                          
						</table>
			
</DIV>
				<!--  <table id="businessEntityTable" width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>-->
<DIV id="popupTableDiv4" style="height:0px; overflow:auto;" class=popupDataTableDiv> 
						<table id="businessEntityTable2"  width="100%" cellpadding="0"
							cellspacing="0" bgcolor="#cccccc">
							<tr id="tr4">
								<td  width="8%" align="center" valign="middle"><input name="checkbox" type="checkbox"
									id="checkall"
									onClick="checkAll_ewpd(this,'StateMultipleSelectPopupForm:StateMultipleSelectPopupTable2');"></td>
								
								<td width="70%" align="left"><strong><h:outputText
									value="Provider Arrangement" styleClass="outputText"></h:outputText></strong></td>
							</tr>
                            
						</table>
</DIV>
						</td>
				<tr id="tr1">
					<td colspan="2" width="100%">
					<DIV id="popupDataTableDiv1" style="height:277px; overflow:auto;" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" styleClass="outputText"
						headerClass="dataTableHeader" 
						cellspacing="1" width="100%" id="StateMultipleSelectPopupTable1"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" var="eachRow"
						cellpadding="1" bgcolor="#cccccc" columnClasses="selectCheckboxColumn,descriptionColumn,anotherDescriptionColumn">
						<h:column>
							
							<h:selectBooleanCheckbox id="stateMultipleSelectChkBox1">
							</h:selectBooleanCheckbox>

						</h:column>
						<h:column>
							
                            <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                            <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden id="hiddenFlag1" value="#{benefitMandateBackingBean.flag}"></h:inputHidden>
							<f:verbatim>
								<h:outputText value="#{eachRow.primaryCode}" style="padding-left: 5px"></h:outputText>
							</f:verbatim>
						</h:column>
						<h:column>
							<f:verbatim>
								
								<h:outputText value="#{eachRow.description}" style="padding-left: 5px"></h:outputText>
							</f:verbatim>
						</h:column>

					</h:dataTable></DIV>
					</td>
				</tr>
				<tr id="tr2">
					<td colspan="2" width="100%">
					<DIV id="popupDataTableDiv2" style="height:277px; overflow:auto;" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" styleClass="outputText"
						headerClass="dataTableHeader" 
						cellspacing="1" width="100%" id="StateMultipleSelectPopupTable2"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" var="eachRow"
						cellpadding="1" bgcolor="#cccccc" columnClasses="selectCheckboxColumn,descriptionColumn,anotherDescriptionColumn">
						<h:column>
							
							<h:selectBooleanCheckbox id="stateMultipleSelectChkBox2">
							</h:selectBooleanCheckbox>

						</h:column>
						<h:column>
							
                            <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                            <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden id="hiddenFlag2" value="#{benefitMandateBackingBean.flag}"></h:inputHidden>
							<f:verbatim>
								<h:outputText value="#{eachRow.description}" style="padding-left: 5px"></h:outputText>
							</f:verbatim>
						</h:column>
						<h:column>
							<f:verbatim>
								
								<h:outputText value="#{eachRow.primaryCode}" style="padding-left: 5px"></h:outputText>
							</f:verbatim>
						</h:column>

					</h:dataTable></DIV>
					</td>
				</tr>
		</table>
                <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
// matchCheckboxItems_ewpd_mandate('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable', window.dialogArguments.selectedValues, 3, 2, 2);
matchCheckboxItems_ewpd_mandate('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable1',window.dialogArguments.selectedValues,3,2,2);
matchCheckboxItems_ewpd_mandate('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable2',window.dialogArguments.selectedValues,3,2,2);
var sort = document.getElementById('StateMultipleSelectPopupForm:hiddensortorder');
var TR1 = document.getElementById('tr1');
var TR2 = document.getElementById('tr2');
var TR3 = document.getElementById('tr3');
var TR4 = document.getElementById('tr4');
var div1 = document.getElementById('popupDataTableDiv1');
var div2 = document.getElementById('popupDataTableDiv2');
var div3 = document.getElementById('popupTableDiv3');
var div4 = document.getElementById('popupTableDiv4');
	
	
if(sort != null && sort.value == 'code'){
			tigra_tables('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div2.style.visibility = 'hidden';
	TR2.style.visibility = 'hidden';
    TR4.style.visibility = 'hidden';
    div4.style.visibility = 'hidden';
    TR4.style.height = '1px';
	div2.style.height = '1px';
	TR2.style.height = '1px';
	div4.style.height = '1px';

}
else if(sort != null && sort.value == 'desc'){
            tigra_tables('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div1.style.visibility = 'hidden';
	TR1.style.visibility = 'hidden';
    TR3.style.visibility = 'hidden';
    div3.style.visibility = 'hidden';
	div1.style.height = '1px';
	TR1.style.height = '1px';
    TR3.style.height = '1px';
	div3.style.height = '1px';

}
function getSelected(){
if(sort != null && sort.value == 'code'){

getCheckedItems_ewpd('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable1',3);return false;
}
else if(sort != null && sort.value == 'desc'){

getCheckedItems_ewpd('StateMultipleSelectPopupForm:StateMultipleSelectPopupTable2',3);return false;
}
}

</script>

</HTML>
