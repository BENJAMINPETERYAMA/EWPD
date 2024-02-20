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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	}
.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>

<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<BASE target="_self" />
<f:view>
<BODY>

	<h:form id="RegAgencyPopupForm">
		<h:inputHidden id="search" value="#{ReferenceDataBackingBeanCommon.searchTermQualifier}"></h:inputHidden>
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
							onclick="getSelected();return false;" disabled="true">
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
							<td align="left" width="10%" height="21"></td>
							<TD align="left" width="20%" height="21">
								<strong><h:outputText value="Code"></h:outputText></strong>								
							</TD>
							<TD align="left" width="70%" height="21">
								<strong><h:outputText value="Description"></h:outputText></strong>
							</TD>
						</TR>
						<tr>	
							<td align="left" width="10%" height="19"></td>
							<TD align="left" width="20%" height="19"></TD>					
							<TD align="left" width="70%" height="19"><h:inputText id="searchText"
								size="10" styleClass="formInputField"
								value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
								maxlength="34" tabindex="4"
								onkeypress="return expandcontract(event)"  readonly = "true"/></TD>
					</tr>
						
					</table>
					</TD>
				</TR>
				
               <tr id="tr1">
					<td>
					<DIV id="popupDataTableDiv1" class=popupDataTableDiv>
					<h:dataTable 
					rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						cellspacing="1" width="100%" id="RegAgencyPopupTable1"var="eachRow"                      
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
                        rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}" 
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="RegulatoryAgency"  id="minor1" rendered="true"></wpd:singleRowSelect>							
							</f:verbatim>
						</h:column>
						<h:column>
                        	
							<h:inputHidden id="hiddenRegulatoryAgencyId" value="#{eachRow.primaryCode}"></h:inputHidden>
							<h:inputHidden id="hiddenRegulatoryAgencyDesc" value="#{eachRow.description}"></h:inputHidden>
  							<f:verbatim>&nbsp;</f:verbatim>
                           		<h:outputText value="#{eachRow.primaryCode}"></h:outputText>	
							</h:column>
						<h:column>
  							<f:verbatim>&nbsp;</f:verbatim>				
                              
                                 <h:outputText value="#{eachRow.description}"></h:outputText>						
                                
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
                <!-- <tr id="tr2">
					<td>
					<DIV id="popupDataTableDiv2" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
						cellspacing="1" width="100%" id="RegAgencyPopupTable2"  var="eachRow"                      
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
                        rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}" 
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="RegulatoryAgency"  id="minor1" rendered="true"></wpd:singleRowSelect>							
							</f:verbatim>
						</h:column>
						<h:column>
                        	<f:attribute name="width" value="80%" />
							<h:inputHidden id="hiddenRegulatoryAgencyId2" value="#{eachRow.primaryCode}"></h:inputHidden>
							<h:inputHidden id="hiddenRegulatoryAgencyDesc2" value="#{eachRow.description}"></h:inputHidden>
  							<f:verbatim>&nbsp;</f:verbatim>
                           			<h:outputText value="#{eachRow.description}"></h:outputText>
							</h:column>
						<h:column>
  							<f:verbatim>&nbsp;</f:verbatim>				
                               	<f:attribute name="width" value="15%" />							
                                <h:outputText value="#{eachRow.primaryCode}"></h:outputText>
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr> -->
			</TBODY>
		</table>
        <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
		<h:inputHidden id="parentCatalog"
					value="#{ReferenceDataBackingBeanCommon.catalogNameForTermQualifier}"></h:inputHidden>
		<h:inputHidden id="lookUpAction"
					value="#{ReferenceDataBackingBeanCommon.lookUpAction}"></h:inputHidden>		
	</h:form>
		
			
</BODY>
		
</f:view>
<script language="JavaScript">
document.getElementById('RegAgencyPopupForm:txtSelect').disabled  = false;
document.getElementById('RegAgencyPopupForm:searchText').readOnly = false; 
function expandcontract(dis) {
	if(dis.keyCode=='13'){
		//submitLink('RegAgencyPopupForm:searchLink');
		var catalogName = document.getElementById('RegAgencyPopupForm:parentCatalog').value;
		var lookUpAction = document.getElementById('RegAgencyPopupForm:lookUpAction').value;
		var searchField = document.getElementById('RegAgencyPopupForm:searchText');
		var divObj = document.getElementById('popupDataTableDiv1');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.		
		ajaxCall1('../popups/ajaxContractRegulatoryHelperTable.jsp?parentCatalog='+catalogName+'&lookUpAction='
						+lookUpAction+'&temp='+Math.random(),searchField,divObj,'RegAgencyPopupForm:RegAgencyPopupTable1',
						errorMsgDiv,attrObj,'','');		
		return false;		
	}
}
matchCheckboxItems_ewpd('RegAgencyPopupForm:RegAgencyPopupTable1',window.dialogArguments.selectedValues,2,2,2);
//matchCheckboxItems_ewpd('RegAgencyPopupForm:RegAgencyPopupTable2',window.dialogArguments.selectedValues,2,2,2);

var sort = document.getElementById('RegAgencyPopupForm:hiddensortorder');
var TR1 = document.getElementById('tr1');
//var TR2 = document.getElementById('tr2');
var div1 = document.getElementById('popupDataTableDiv1');
//var div2 = document.getElementById('popupDataTableDiv2');

	
if(sort != null && sort.value == 'code'){
	tigra_tables('RegAgencyPopupForm:RegAgencyPopupTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
/*	div2.style.visibility = 'hidden';
	TR2.style.visibility = 'hidden';      
	div2.style.height = '1px';
	TR2.style.height = '1px';
*/	

}
else if(sort != null && sort.value == 'desc'){
//            tigra_tables('RegAgencyPopupForm:RegAgencyPopupTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div1.style.visibility = 'hidden';
	TR1.style.visibility = 'hidden';    
    div1.style.height = '1px';
	TR1.style.height = '1px';   

}
function getSelected(){
if(sort != null && sort.value == 'code'){

getCheckedItems_ewpd('RegAgencyPopupForm:RegAgencyPopupTable1',2);return false;
}
else if(sort != null && sort.value == 'desc'){

//getCheckedItems_ewpd('RegAgencyPopupForm:RegAgencyPopupTable2',2);return false;
}
}
</script>

</HTML>