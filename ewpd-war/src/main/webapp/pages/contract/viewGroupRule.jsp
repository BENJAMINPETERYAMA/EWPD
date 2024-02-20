<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ViewRule.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.ruleIdColumn {
	width: 25%;
}
.ruleDescriptionColumn {
	width: 50%;
}
.viewColumn {
	width: 25%;
}
</style>

<TITLE>WellPoint Product Database: Basic Information</TITLE>
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
</HEAD>
<f:view>
	<BODY>
		<h:inputHidden id="entityId" value="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>	
		<h:inputHidden id="exclusionRuleSelected" value="#{contractRuleBackingBean.exclusionRuleSelected}"></h:inputHidden>	
		<h:inputHidden id="denialRuleSelected" value="#{contractRuleBackingBean.denialRuleSelected}"></h:inputHidden>	
		<h:inputHidden id="umRuleSelected" value="#{contractRuleBackingBean.umRuleSelected}"></h:inputHidden>
		<h:inputHidden id="pnrRuleSelected" value="#{contractRuleBackingBean.pnrRuleSelected}"></h:inputHidden>
		
		<h:inputHidden id="headerRuleBCselected" value="#{contractRuleBackingBean.headerRuleBCselected}"></h:inputHidden>
		<h:inputHidden id="headerRuleBenefitSelected" value="#{contractRuleBackingBean.headerRuleBenefitSelected}"></h:inputHidden>
		<h:inputHidden id="benefitComponentName" value="#{contractRuleBackingBean.benefitComponentName}"></h:inputHidden>
		<h:inputHidden id="benefitComponentId" value="#{contractRuleBackingBean.benefitComponentId}"></h:inputHidden>
		<h:inputHidden id="benefitName" value="#{contractRuleBackingBean.benefitName}"></h:inputHidden>
		<h:inputHidden id="checkMode" value="#{contractRuleBackingBean.checkMode}"></h:inputHidden>
	<hx:scriptCollector id="scriptCollector1">
		<h:form styleClass="form" id="GroupRuleForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			
			<TR>
				<TD>
						<TABLE border="0" cellspacing="0" width="100%" cellpadding="0">
							<TR>
								<TD>
									<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
									<h:inputHidden id ="grouRuleID" value="#{contractRuleBackingBean.groupRuleHidden}"></h:inputHidden>
									<TABLE width="100%">
										<TR>
											<TD width="20%"><B> Rule ID</B></TD>
											<TD width="80%"><h:outputText value="#{contractRuleBackingBean.groupRule.ruleId}"/>
											</TD>
										</TR>
										<TR>
											<TD><B>Rule Description<B></TD>
											<TD><h:outputText value="#{contractRuleBackingBean.groupRule.ruleDesc}"/>
											</TD>
										</TR>
										<TR>
											<TD><B>Rule Version</B></TD>
											<TD><h:outputText value="#{contractRuleBackingBean.groupRule.ruleVersion}"  />
											</TD>
										</TR>
										<TR>
											<TD><B>Tag</B></TD>
											<TD><h:outputText value="#{contractRuleBackingBean.groupRule.tag}" />
											</TD>
										</TR>
										
									</TABLE>
								</TD>
							</TR>
							<TR><TD>&nbsp;</TD> </TR>
							<TR>
								<TD>
									<table border="0" cellspacing="1" cellpadding="0" width="100%"
										bgcolor="#cccccc" >
										<tr>
										<TD width="100%">
											<B>Associated Rules</B></TD>
										</tr>
									</table>
								</TD>
							</TR>
							<TR>
								<TD>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									bgcolor="#cccccc">
									<TR>
										<TD	width="25%"><B>Rule ID</B></td>
										<TD width="50%"><B>Rule Description</B></TD>
										<TD width="25%">&nbsp;</TD>
									</TR>
								</table>
								</TD>
							</TR>
							<TR>
								<TD>
									<div id="displayRulesDetails"
										 style="position:relative;overflow:auto;height:300px;width:100%">
										<h:dataTable headerClass="dataTableHeader"
											id="previousVersionsTable" var="rule" cellpadding="3"
											cellspacing="1" bgcolor="#cccccc" 
											rendered="#{contractRuleBackingBean.groupRule.rules != null}"
											value="#{contractRuleBackingBean.groupRule.rules}"
											rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="ruleIdColumn,ruleDescriptionColumn,viewColumn"
											border="0"
											width="100%">
										<h:column>
											
											<h:outputText value="#{rule.ruleId}"/>
										</h:column>
										<h:column>
											
											<h:outputText value="#{rule.ruleDesc}"/>
										</h:column>
										<h:column>
											
											<f:verbatim>&nbsp;</f:verbatim>
											<h:commandButton 
										  	 alt="View Rule Information"
										   	 image="../../images/view.gif"
                                             onclick="viewAction('#{rule.ruleId}');return false;" />
										</h:column>
										</h:dataTable>
									</div>
								</TD>
							</TR>
						</TABLE>
				</TD>
			</TR>
			<TR><TD>&nbsp;</TD></TR>
			
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>

<form name="printForm">
    <input id="currentPrintPage" type="hidden"
			name="currentPrintPage" value="contractgroupRuleView" />
	<input id="printRuleId" type="hidden"/>
</form>
<script>
	var ruleId = htmlEncode(document.getElementById('GroupRuleForm:RuleId').value);
	document.getElementById('GroupRuleForm:breadCrumb1').innerHTML ='Rule ('+ ruleId + ') >> View';
	document.getElementById('printRuleId').value = ruleId;	
	
	function viewAction(ruleId){
	
	var pnrRuleSelected = document.getElementById('pnrRuleSelected').value;
	var exclusionRuleSelected = document.getElementById('exclusionRuleSelected').value;
	var denialRuleSelected = document.getElementById('denialRuleSelected').value;
	var umRuleSelected = document.getElementById('umRuleSelected').value;
	var headerRuleBCselected = document.getElementById('headerRuleBCselected').value;
	var headerRuleBenefitSelected = document.getElementById('headerRuleBenefitSelected').value;
	var benefitComponentName = document.getElementById('benefitComponentName').value;
	var benefitComponentId = document.getElementById('benefitComponentId').value;
	var benefitName = document.getElementById('benefitName').value;
	var checkMode = document.getElementById('checkMode').value;
	
	
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' 
		+ Math.random()+'&pnrRuleSelected='+pnrRuleSelected+'&exclusionRuleSelected='+exclusionRuleSelected+'&denialRuleSelected='+denialRuleSelected
		+'&umRuleSelected='+umRuleSelected+'&headerRuleBCselected='+headerRuleBCselected+'&headerRuleBenefitSelected='+headerRuleBenefitSelected
		+'&benefitComponentName='+benefitComponentName+'&benefitComponentId='+benefitComponentId+'&benefitName='+benefitName+'&checkMode='+checkMode,
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}

</script>
</HTML>