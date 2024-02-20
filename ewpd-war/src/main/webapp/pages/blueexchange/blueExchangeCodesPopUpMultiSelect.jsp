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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 5%;
	text-align: center;
	vertical-align: middle;
}
.primaryCodeColumn {
	width: 10%;
}
.subCatalogColumn {
	width: 25%;
}
.shortDescriptionColumn {
	width: 60%;
}

</style>	
<TITLE>Blue Exchange Codes Popup</TITLE>
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
		<h:form id="bxSPSCodesForm">
			<h:inputHidden value="#{ReferenceDataBackingBeanCommon.blueExchangeCode}"></h:inputHidden>
			<div id="selectButtonDiv">
			<TABLE border="0" cellpadding="5" cellspacing="0" width="100%"> 
		
				<TR>
					<TD align="left">&nbsp;
						<h:commandButton id="selectButton" value="Select"
						styleClass="wpdbutton"
						onclick="getSelected();return false;"></h:commandButton></TD>
				</TR>
		 	</TABLE> 
			</div>
			<TABLE width="97%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<TR>
								<TD width="10%" align="left" valign="middle">
							<h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'bxSPSCodesForm:prodFamilyDataTable1'); ">
							</h:selectBooleanCheckbox>
								</TD>
								<TD width="20%"></TD>
								<TD align="left" width="70%" height="30%"><STRONG><h:outputText value="#{ReferenceDataBackingBeanCommon.blueExchangeHeader}"></h:outputText></STRONG></TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
				<TR>
					<TD>
						<div id='noCodeListDiv'style="font-family:Verdana, Arial, Helvetica, sans-serif;
								font-size:11px;color:#000000; 
								background-color:#FFFFFF;visibility:hidden;" align="center"> <br>
							<P><STRONG> No Codes Available </STRONG></P>
						</div>
					</TD>
				</TR>
				
				<TR id="tr1">
					<TD>
					<DIV class="popupDataTableDiv" id="popupDataTableDiv1">
						<h:dataTable cellspacing="1" width="100%" id="prodFamilyDataTable1" 
						rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
						var="eachRow" cellpadding="0" bgcolor="#cccccc"
						columnClasses="selectOrOptionColumn,primaryCodeColumn,subCatalogColumn,shortDescriptionColumn">
						 	<h:column>
								<h:selectBooleanCheckbox id="lineOfBusinessChkBox">
							                   </h:selectBooleanCheckbox>
						 	</h:column>
						 	<h:column>
                                          
											<h:inputHidden value="#{eachRow.subCatalogName}"></h:inputHidden>
											<h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                                           <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.primaryCode}"></h:outputText>
							</h:column>
							<h:column>
										   
										   <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.subCatalogName }"></h:outputText>
							</h:column>
							<h:column>
										   
										   <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.description}"></h:outputText>
							</h:column>
						</h:dataTable>
					</DIV>
					</TD>
				</TR>
			</TBODY>
			</TABLE>

		</h:form>
	</hx:scriptCollector></BODY>
</f:view>

<script language="javascript">
if(null != document.getElementById("bxSPSCodesForm:prodFamilyDataTable1")){
	tigra_tables('bxSPSCodesForm:prodFamilyDataTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	matchCheckboxItems_ewpd('bxSPSCodesForm:prodFamilyDataTable1',window.dialogArguments.selectedValues,2,2,2);
	document.getElementById("noCodeListDiv").style.visibility = 'hidden';
	document.getElementById('noCodeListDiv').style.height = "0px";
	document.getElementById('noCodeListDiv').style.position = 'absolute';
	document.getElementById("selectButtonDiv").style.visibility = 'visible';
	document.getElementById("popupDataTableDiv1").style.visibility = 'visible';
}
else{
	document.getElementById("selectButtonDiv").style.visibility = 'hidden';
	document.getElementById("popupDataTableDiv1").style.visibility = 'hidden';
	document.getElementById('selectButtonDiv').style.height = "0px";
	document.getElementById('selectButtonDiv').style.position = 'absolute';
	document.getElementById('popupDataTableDiv1').style.height = "0px";
	document.getElementById('popupDataTableDiv1').style.position = 'absolute';
	document.getElementById("noCodeListDiv").style.visibility = 'visible';
}
   
function getSelected(){
	getCheckedItems_ewpd('bxSPSCodesForm:prodFamilyDataTable1',2);return false;
}

</script>
</HTML>
