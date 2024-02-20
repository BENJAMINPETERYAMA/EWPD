<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>
	
<TITLE>Product Family Popup </TITLE>
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
	<BODY><hx:scriptCollector id="scriptCollector1">
		<h:form id="prodFamilyForm">
<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
			<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">
		
				<TR>
					<TD align="left">&nbsp;
						<h:commandButton id="selectButton" value="Select"  disabled="true" 
						styleClass="wpdbutton"
						onclick="getSelected();return false;"></h:commandButton></TD>
				</TR>
		 	</TABLE>
			<TABLE width="97%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<TR>
								<TD width="10%" align="center" valign="middle"></TD>
								<TD width="20%"></TD>
								<TD align="left" width="70%" height="20px"><STRONG><h:outputText value="Product Family"></h:outputText></STRONG></TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
				<TR id="tr1">
					<TD>
					<DIV class="popupDataTableDiv" id="popupDataTableDiv1">
						<h:dataTable cellspacing="1" width="100%" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn" id="prodFamilyDataTable1" 
						rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						 	<h:column>
								<f:verbatim> <wpd:singleRowSelect groupName="majorHeading" id="major" rendered="true"></wpd:singleRowSelect> </f:verbatim>
						 	</h:column>
						 	<h:column>
                                          
                                          <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
                                          <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                                           <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.primaryCode}"></h:outputText>
							</h:column>
							<h:column>
										  
										   <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.description }"></h:outputText>
							</h:column>
						</h:dataTable>
					</DIV>
					</TD>
				</TR>
                <TR id="tr2">
					<TD>
					<DIV class="popupDataTableDiv" id="popupDataTableDiv2">
						<h:dataTable cellspacing="1" width="100%" id="prodFamilyDataTable2" columnClasses="selectOrOptionColumn,longDescriptionColumn,shortDescriptionColumn"
							value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
						rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}"
						 var="eachRow" cellpadding="0" bgcolor="#cccccc">
						 	<h:column>
								<f:verbatim> <wpd:singleRowSelect groupName="majorHeading" id="major" rendered="true"></wpd:singleRowSelect> </f:verbatim>
						 	</h:column>
						 	<h:column>
                                         
										  <f:verbatim>&nbsp;</f:verbatim>
                                          <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
                                          <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                                           <f:verbatim>&nbsp;</f:verbatim>
                                           
                                           <h:outputText value="#{eachRow.description }"></h:outputText>
                                          
							</h:column>
							<h:column>										
										   <f:verbatim>&nbsp;</f:verbatim>
										 
                                           <h:outputText value="#{eachRow.primaryCode}"></h:outputText>
							</h:column>
						</h:dataTable>
					</DIV>
					</TD>
				</TR>
			</TBODY>
			</TABLE>
             <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
		</h:form>
	</hx:scriptCollector></BODY>
</f:view>

<script language="javascript">
document.getElementById('prodFamilyForm:selectButton').disabled  = false;
	/*setColumnWidth('prodFamilyForm:prodFamilyDataTable1','6%');
    setColumnWidth('prodFamilyForm:prodFamilyDataTable2','6%');
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}*/
//	if(hiddenObj.value) {
		 matchCheckboxItems_ewpd('prodFamilyForm:prodFamilyDataTable1',window.dialogArguments.selectedValues,2,2,2);
		 matchCheckboxItems_ewpd('prodFamilyForm:prodFamilyDataTable2',window.dialogArguments.selectedValues,2,2,2);
//	}
   
var sort = document.getElementById('prodFamilyForm:hiddensortorder');
var TR1 = document.getElementById('tr1');
var TR2 = document.getElementById('tr2');
var div1 = document.getElementById('popupDataTableDiv1');
var div2 = document.getElementById('popupDataTableDiv2');
	
	
if(sort != null && sort.value == 'code'){
var table = document.getElementById('prodFamilyForm:prodFamilyDataTable1');
if(table != null )
			tigra_tables('prodFamilyForm:prodFamilyDataTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div2.style.visibility = 'hidden';
	TR2.style.visibility = 'hidden';
   	div2.style.height = '1px';
	TR2.style.height = '1px';	

}
else if(sort != null && sort.value == 'desc'){
var table = document.getElementById('prodFamilyForm:prodFamilyDataTable2');
if(table != null)
            tigra_tables('prodFamilyForm:prodFamilyDataTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div1.style.visibility = 'hidden';
	TR1.style.visibility = 'hidden';       
	div1.style.height = '1px';
	TR1.style.height = '1px'; 
	
}
function getSelected(){
if(sort != null && sort.value == 'code'){

getCheckedItems_ewpd('prodFamilyForm:prodFamilyDataTable1',2);return false;
}
else if(sort != null && sort.value == 'desc'){

getCheckedItems_ewpd('prodFamilyForm:prodFamilyDataTable2',2);return false;
}
}
</script>
</HTML>

