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
<TITLE>SPS Parameter Id Popup </TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form id="spsIdForm">
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.blueExchangeCode}"></h:inputHidden>
		<DIV id="spsDiv">
			
			<div id='selectButtonDiv'><BR><table border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td align="left">&nbsp;<h:commandButton id="select"
						value="Select" styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('spsIdForm:spsDataTable',2);return false;"></h:commandButton>
				</tr>
			</table>
			</div>
			<table width="98%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc" align="center">
					<tr class="dataTableColumnHeader" style="background-color:#cccccc;">
						<TD align="center" width="70%" height="30%"><STRONG><h:outputText 
										value="#{ReferenceDataBackingBeanCommon.blueExchangeHeader}"></h:outputText></STRONG></TD>

					</tr>

				</table>
			<div id='noCodeListDiv'style="font-family:Verdana, Arial, Helvetica, sans-serif;
								font-size:11px;color:#000000; 
								background-color:#FFFFFF;visibility:hidden;" align="center"> 
				<P><STRONG> No Codes Available </STRONG></P>
			</div>
			<div id="resultTableDiv">
			<table width="98%" cellpadding="0" cellspacing="0"
				border="0"  align="center">
				<TBODY>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="height: 295px; overflow:auto;">
						<h:dataTable cellspacing="1" id="spsDataTable" 
							rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList != null}"
							value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc"
							rowClasses="dataTableOddRow,dataTableEvenRow" width="100%">
							<h:column>
									<f:verbatim><wpd:singleRowSelect groupName="reference" id="minor1"
								rendered="true"></wpd:singleRowSelect></f:verbatim>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.description}"></h:inputHidden>
								<h:inputHidden value="#{singleValue.primaryCode}"></h:inputHidden>								
								<h:outputText value="#{singleValue.primaryCode}"></h:outputText>
						                                
							</h:column>
							<h:column>
								<h:outputText value="#{singleValue.description}"></h:outputText>
								
							</h:column>
							
						</h:dataTable></DIV>
						</td>
					</tr>
				</TBODY>
			</table>
			</div>
	</DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
if(null != document.getElementById("spsIdForm:spsDataTable")){
	matchCheckboxItems_ewpd('spsIdForm:spsDataTable', window.dialogArguments.selectedValues, 2, 1, 1);
 	//tigra_tables('spsIdForm:spsDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
}

initialize();
	
	function initialize(){
		if(document.getElementById('spsIdForm:spsDataTable') != null) {
			setColumnWidth('spsIdForm:spsDataTable','5%:18%:75%');
			document.getElementById("noCodeListDiv").style.visibility = 'hidden';
			document.getElementById('noCodeListDiv').style.height = "0px";
			document.getElementById('noCodeListDiv').style.position = 'absolute';
			document.getElementById("selectButtonDiv").style.visibility = 'visible';
			document.getElementById("resultTableDiv").style.visibility = 'visible';
		}else {
			//document.getElementById("spsDiv").style.visibility = 'hidden'; 
			document.getElementById("selectButtonDiv").style.visibility = 'hidden';
			document.getElementById("resultTableDiv").style.visibility = 'hidden';
			document.getElementById('selectButtonDiv').style.height = "0px";
			document.getElementById('selectButtonDiv').style.position = 'absolute';
			document.getElementById('resultTableDiv').style.height = "0px";
			document.getElementById('resultTableDiv').style.position = 'absolute';
			document.getElementById("noCodeListDiv").style.visibility = 'visible';
		}
	}
		
	
</script>
</HTML>
