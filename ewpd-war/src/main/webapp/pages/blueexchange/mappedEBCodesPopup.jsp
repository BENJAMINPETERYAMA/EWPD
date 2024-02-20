<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>WellPoint Product Database: Mapped EBCode Values</TITLE>
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

<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
	
<%-- 
		<w:message></w:message>
--%>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR><TD>&nbsp;</TD></TR>
			<TR>
				<TD>&nbsp;&nbsp;&nbsp;</TD>
				<TD ><h:form styleClass="form" id="mappedEBCodesForm">
								<h:inputHidden id ="hidId" value="#{serviceTypeMappingSearchBackingBean.listforView}"></h:inputHidden>
								<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD >
									<div id="noDataDiv" >
									<h:outputText value="No Mapping Available."  rendered="#{serviceTypeMappingSearchBackingBean.mappingList == null}"
         							styleClass="dataTableColumnHeader"/>
    								</div>
									<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo" style="width:670px;height: 15"  >
									<center><STRONG>EB03-Service Type Code (<%=ESAPI.encoder().encodeForHTML(request.getParameter("eb03val"))%> - <%=ESAPI.encoder().encodeForHTML(request.getParameter("eb03desc"))%>) </STRONG></center>
									</div>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="670px">
										<TBODY>
											<TR class="dataTableColumnHeader">
												
												<TD align="left"><h:outputText value="SVC Code"></h:outputText></TD>
												<TD align="left"><h:outputText value="SVC CLS"></h:outputText></TD>
												<TD align="left"><h:outputText value="LMT CLS"></h:outputText></TD>
												<TD align="left"><h:outputText value="DIA Code"></h:outputText></TD>
												<TD align="left"><h:outputText value="HCP Code"></h:outputText></TD>
												<TD align="left"><h:outputText value="Proc Mod Code"></h:outputText></TD>
												<TD align="left"><h:outputText value="Place of Service"></h:outputText></TD>
												<TD align="left"><h:outputText value="Provider Speciality"></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{serviceTypeMappingSearchBackingBean.mappingList != null}"
										value="#{serviceTypeMappingSearchBackingBean.mappingList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="670px">
										<h:column>
											<h:outputText id="SVCOut"
												value="#{singleValue.serviceCode}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="SVC_CLSOut"
												value="#{singleValue.serviceClassLow}"></h:outputText>
											<h:outputText id="hyp1"
												value=" - " rendered ="#{!((singleValue.serviceClassLow == null) && (singleValue.serviceClassHigh == null))}"></h:outputText>
											<h:outputText id="SVC_CLSOutH"
												value="#{singleValue.serviceClassHigh}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="LMT_CLSOut"
												value="#{singleValue.limitClassLow}"></h:outputText>
											<h:outputText id="hyp2"
												value=" - " rendered ="#{!((singleValue.limitClassLow == null) && (singleValue.limitClassHigh == null))}"></h:outputText>
											<h:outputText id="LMT_CLSOutH"
												value="#{singleValue.limitClassHigh}"></h:outputText>
										</h:column>
                                        <h:column>
											<h:outputText id="DIA_CODEOut"
												value="#{singleValue.diagnosisLow}"></h:outputText>
											<h:outputText id="hyp3"
												value=" - "  rendered ="#{!((singleValue.diagnosisLow == null) && (singleValue.diagnosisHigh == null))}"></h:outputText>
											<h:outputText id="DIA_CODEOutH"
												value="#{singleValue.diagnosisHigh}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="HCP_CODEOut"
												value="#{singleValue.hcpLow}"></h:outputText>
											<h:outputText id="hyp4"
												value=" - " rendered ="#{!((singleValue.hcpLow == null) && (singleValue.hcpHigh == null))}"></h:outputText>
											<h:outputText id="HCP_CODEOutH"
												value="#{singleValue.hcpHigh}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="ProcModCode"
												value="#{singleValue.procModifierCode}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="PlsOfServ"
												value="#{singleValue.placeOfService}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="PrvdrSpeclity"
												value="#{singleValue.providerSpeciality}"></h:outputText>
										</h:column>
										
									</h:dataTable></DIV>
									</TD>
								</TR>
								<TR>
									<TD></TD>
								</TR>
							</TABLE>
				</h:form></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
		
		if(document.getElementById('mappedEBCodesForm:searchResultTable') != null) {
			setColumnWidth('resultHeaderTable','12%:13%:13%:13%:13%:12%:12%:12%');
			setColumnWidth('mappedEBCodesForm:searchResultTable','12%:13%:13%:13%:13%:12%:12%:12%');	
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
			document.getElementById('resultHeaderTableInfo').style.visibility = 'hidden';
		}
		
		if(document.getElementById('mappedEBCodesForm:searchResultTable') != null){
			
			document.getElementById('mappedEBCodesForm:searchResultTable').onresize = syncTables;
			
			syncTables();
		}
		
		function syncTables(){
			
			var relTblWidth = document.getElementById('mappedEBCodesForm:searchResultTable').width;
			
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
			
		}	

 
</script>
</HTML>
