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
<BASE target="_self" />	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 5%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 10%;
}
.longDescriptionColumn {
	width: 85%;
}
</style>
<TITLE>SPS Parameter Id Popup</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('bxSPSCodesForm:searchButn');"><hx:scriptCollector id="scriptCollector1">
		<h:form id="bxSPSCodesForm">
			<h:inputHidden value="#{ReferenceDataBackingBeanCommon.blueExchangeCode}"></h:inputHidden>			
			<BR>
			<div id="headerDiv">
			<TABLE border="0" cellpadding="5" cellspacing="0" width="97%">	
				<tr align="center">
					<TD width="15%"><h:outputText value="SPS Description"></h:outputText></TD>
					<TD width="10%"><h:inputText id="searchText" size="10"
						styleClass="formInputField" 
						value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
						maxlength="25"></h:inputText></TD>
					<TD width="10%"><h:commandButton id="searchButn"
						styleClass="wpdbutton" value="Search"
						action="#{ReferenceDataBackingBeanCommon.search}"></h:commandButton>
					</TD> 
				</tr>			
				<TR>					
					<TD align="left"><div id="selectButtonDiv">&nbsp;&nbsp;&nbsp;
						<h:commandButton id="selectButton" value="Select"
						styleClass="wpdbutton"
						onclick="getSelected();return false;"></h:commandButton></div>
				</TD>
				</TR>
		 	</TABLE>
			</div>
			<script type="text/javascript">
				//document.getElementById('headerDiv').style.visibility = 'hidden';
				document.getElementById('bxSPSCodesForm:searchText').readOnly = true;
				document.getElementById('bxSPSCodesForm:searchButn').disabled = true;				
			</script>
			
			<TABLE width="97%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>						
                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#cccccc">
							<TR>
								<TD width="10%" align="center" valign="middle"></TD>
								<TD width="20%"></TD>
								<TD align="left" width="70%" height="20px"><STRONG><h:outputText value="#{ReferenceDataBackingBeanCommon.blueExchangeHeader}"></h:outputText></STRONG></TD>
							</TR>
						</TABLE>						
					</TD>
				</TR>				
				<TR>
					<TD>
						<DIV id="noCodeListDiv" style="font-family:Verdana, Arial, Helvetica, sans-serif;
								font-size:11px;color:#000000; 
								background-color:#FFFFFF;visibility:hidden;" align="center"><br><STRONG>No codes available</STRONG></DIV>
						
					</TD>							
				</TR>
				<TR id="tr1">					
					<TD>	
						<Div id="resultList" style="height:270px;overflow:auto">				
							<h:dataTable cellspacing="1" width="100%" id="prodFamilyDataTable1" 
							rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}"
							value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
							var="eachRow" cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
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
	                                          <h:outputText value="#{eachRow.description}"></h:outputText>
								</h:column>
							</h:dataTable>	
						</div>				
					</TD>
				</TR>				
			</TBODY>
			</TABLE>
			<script type="text/javascript">
				//document.getElementById('headerDiv').style.visibility = 'visible';
				document.getElementById('bxSPSCodesForm:searchText').readOnly = false;
				document.getElementById('bxSPSCodesForm:searchButn').disabled = false;
			</script>
		</h:form>
	</hx:scriptCollector></BODY>
</f:view>

<script language="javascript">
if(null != document.getElementById('bxSPSCodesForm:prodFamilyDataTable1')){
	var listSize = document.getElementById('bxSPSCodesForm:prodFamilyDataTable1').rows.length;
	if(listSize == 0){
		document.getElementById('selectButtonDiv').style.visibility = 'hidden';
		document.getElementById('noCodeListDiv').style.visibility = 'visible';
	}else{
		document.getElementById('selectButtonDiv').style.visibility = 'visible';
		document.getElementById('noCodeListDiv').style.visibility = 'hidden';
		document.getElementById('noCodeListDiv').style.height = "0px";
		document.getElementById('noCodeListDiv').style.position = 'absolute';
	}
		matchCheckboxItems_ewpd('bxSPSCodesForm:prodFamilyDataTable1',window.dialogArguments.selectedValues,2,2,2);
		tigra_tables('bxSPSCodesForm:prodFamilyDataTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	}else{
		document.getElementById('selectButtonDiv').style.visibility = 'hidden';
		document.getElementById('noCodeListDiv').style.visibility = 'visible';
		document.getElementById('selectButtonDiv').style.height = "0px";
		document.getElementById('selectButtonDiv').style.position = 'absolute';
	}

function getSelected(){
	getCheckedItems_ewpd('bxSPSCodesForm:prodFamilyDataTable1',2);return false;
}

</script>
</HTML>

