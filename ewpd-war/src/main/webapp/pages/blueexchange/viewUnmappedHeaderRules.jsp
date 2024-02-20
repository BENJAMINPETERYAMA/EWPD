<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
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

<TITLE>View UnMapped Header Rules</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<BASE target="_self"/>
</HEAD>
<f:view>
	<BODY>
	<h:inputHidden id="dummy" value="#{serviceTypeMappingSearchBackingBean.loadUnmappedRules}" />

	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="viewServiceTypeCodeForm">
					<br/>
					<w:message></w:message>
					<DIV id="resultHeaderDiv" style="margin-left:10px;width:960;">
					<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%" >
						
							<TR bgcolor="#ffffff">
								<TD align="left" height="26"><B><STRONG><h:outputText value="Header Rule Id"></h:outputText></STRONG></B></TD>
								<TD align="left" height="26"><B><STRONG><h:outputText value="Rule Description"></h:outputText></STRONG></B></TD>
								<TD align="left" height="26"><B><STRONG>&nbsp;</STRONG></B></TD>												
							</TR>
						
					</TABLE>
					</DIV>
					<DIV id="searchSpsDiv" style="height:350px;overflow:auto;margin-left:10px;width:960;">
						<h:dataTable headerClass="dataTableHeader"
								id="searchResultTable" var="singleValue" cellpadding="3"
								cellspacing="1" bgcolor="#cccccc"
								rendered="#{serviceTypeMappingSearchBackingBean.unMappedRules != null}"
								value="#{serviceTypeMappingSearchBackingBean.unMappedRules}"
								rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
								width="100%">

								<h:column>
									<h:outputText id="productStructureName" value="#{singleValue.ruleId}"/>
									<h:inputHidden id="hidden_headerRuleId" value="#{singleValue.ruleId}"/>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								</h:column>
								<h:column>
									<h:outputText value="#{singleValue.ruleShortDesc}"/>
								</h:column>

								<h:column>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
									<h:commandButton alt="View Header Rule Id Details" id="viewRuleId" image="../../images/view.gif"
										onclick="viewAction(); return false;" />
								</h:column>
							</h:dataTable>
						</DIV>	
			<h:inputHidden id="selectedHiddenHeaderRuleId"></h:inputHidden>
			</h:form>
			<br/>
		</TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>
</BODY>
</f:view>
<script>
if(document.getElementById('viewServiceTypeCodeForm:searchResultTable') != null) {	
	setColumnWidth('resultHeaderTable','230:630');
	setColumnWidth('viewServiceTypeCodeForm:searchResultTable','230:630');	
}
hiddenIdObj = document.getElementById('viewServiceTypeCodeForm:searchResultTable:0:hidden_headerRuleId');
if (hiddenIdObj == null) {
	document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
}

function viewAction(){
	getFromDataTableToHidden('viewServiceTypeCodeForm:searchResultTable','hidden_headerRuleId','viewServiceTypeCodeForm:selectedHiddenHeaderRuleId');
	var ruleIdStr = document.getElementById('viewServiceTypeCodeForm:selectedHiddenHeaderRuleId').value;
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleIdStr)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
}

</script>
</HTML>
