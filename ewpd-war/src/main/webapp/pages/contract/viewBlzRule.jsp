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

<TITLE>WellPoint Product Database: Rule View</TITLE>
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

			<TR>
				<h:form styleClass="form" id="contractForm">

				<TD>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<tr>
			<td>
								&nbsp;&nbsp;
			</td>
			</tr>
			<TR>
				<TD>
				<FIELDSET
							style="margin-left:6px;margin-right:-10x;margin-top:20px;padding-bottom:1px;padding-top:20px;width:100%">
					

					
						<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
							<h:inputHidden id ="hidId" value="#{contractRuleBackingBean.sequenceHidden}"></h:inputHidden>	
							<div id ="noresultdiv">	
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<strong><h:outputText value="Given RuleID is not valid" style="color:blue;"></h:outputText></strong>
											</TD>

										</tr>		
									</TBODY>
								</TABLE>
						</div>
									
								<DIV id = viewtableDiv>
								<TABLE  width="50%" cellpadding="0" cellspacing="0">
									<TR>
										<TD width="150">&nbsp;&nbsp;<h:outputText id="contractId"
											value="Rule ID" style="font-weight:bold;" /></TD>
										<h:inputHidden id="contractIdHidden"
											value="#{contractRuleBackingBean.ruleId}"></h:inputHidden>
										<TD width="285"><h:outputText id="contractId_txt"
											value="#{contractRuleBackingBean.ruleId}" /></TD>
									</TR>
									<TR>
										<TD  width="150">&nbsp;&nbsp;<h:outputText
											id="contractType" value="Rule Description "
											style="font-weight:bold;" /></TD>
										<TD  width="285"><h:outputText
											id="contractType_txt"
											value="#{contractRuleBackingBean.ruleDescription}" rendered="#{null != contractRuleBackingBean.ruleDescription}" /> <h:inputHidden
											id="contractTypeHidden"
											value="#{contractRuleBackingBean.ruleDescription}">
										</h:inputHidden>
									</TR>
								<!-- <TR>

										<TD  width="150">&nbsp;&nbsp;<h:outputText id="baseContractId"
											value="Rule Search Word 1" style="font-weight:bold;" /></TD>

										<TD  width="285"><h:outputText id="base_contractId_txt"
											value="#{contractRuleBackingBean.ruleSearchWord1}"  rendered="#{null != contractRuleBackingBean.ruleSearchWord1}" />
										<h:inputHidden id="baseContractIdHidden"
											value="#{contractRuleBackingBean.ruleSearchWord1}"></h:inputHidden>
										</TD>
									</TR>
									<TR>

										<TD  width="150">&nbsp;&nbsp;<h:outputText id="baseContractDt"
											value="Rule Search Word 2"
											style="font-weight:bold;" /></TD>

										<TD  width="285"><h:outputText id="base_contractDt_txt"
											value="#{contractRuleBackingBean.ruleSearchWord2}" rendered="#{null != contractRuleBackingBean.ruleSearchWord2}"/>
										<h:inputHidden id="baseContractDtHidden"
											value="#{contractRuleBackingBean.ruleSearchWord2}"></h:inputHidden>
										</TD>
									</TR>
									<TR>

										<TD  width="150">&nbsp;&nbsp;<h:outputText id="head"
											value="Rule Search Word 3" style="font-weight:bold;"/></TD>

										<TD width="285"><h:outputText id="head_txt"
											value="#{contractRuleBackingBean.ruleSearchWord3}" rendered="#{null != contractRuleBackingBean.ruleSearchWord3}"/>
										<h:inputHidden id="headHidden"
											value="#{contractRuleBackingBean.ruleSearchWord3}"></h:inputHidden>
										</TD>
									</TR> --> 	

								<TR>
									<td colspan="2">&nbsp;&nbsp</td>
								</TR>
								<TR>
									<td colspan="2"> &nbsp;&nbsp</td></TR>
								<TR>
						
						<h:inputHidden id="rmaHidden" value="#{contractRuleBackingBean.strRmaLink}"></h:inputHidden>

						<TD  width="550" colspan="2">&nbsp;&nbsp<h:outputText value="For More Information Vist" style="font-weight:"/>
					 	&nbsp;&nbsp<a href="#" onclick="openRma();" ><u><FONT color="Blue"> RMA (Rule Maintenance Application)</FONT></u></a>
  	</td>

										
									</TR>




								</table>

						<TABLE  border="0" cellspacing="0" width="100%" style="margin-left:6px;margin-right:-20px;margin-top:20px;margin-bottom:20px;padding-bottom:20px;padding-top:20px;width:98%">

								<!-- 	<TR>
										<TD>
											
											<div id="displayRulesDetails"
													style="position:relative;overflow:auto;height:300px;width:100%">
												<h:panelGrid id="panelTable" width="100%"
													binding="#{contractRuleBackingBean.panelRuleSequences}">
												</h:panelGrid>
												</div>
										</TD>
									</TR> -->
						</TABLE>
			</DIV>
					</FIELDSET>
					
				</h:form></TD>
			</TR>
			<TR>
				<td>&nbsp;&nbsp;
				</td>
			</tr>

				<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
 

function openRma()
{
var rmaUrl = document.getElementById('contractForm:rmaHidden').value

window.open(rmaUrl,"_self","toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes");

}

var ruleId = encodeURI(document.getElementById('contractForm:RuleId').value);
document.getElementById('contractForm:breadCrumb1').innerHTML ='Rule ('+ ruleId + ') >> View';
	realruleid = document.getElementById('contractForm:contractIdHidden').value;
	if(realruleid == null || realruleid ==''){
	viewtableDiv.style.display='none';
	noresultdiv.style.display='';

	}else{
	viewtableDiv.style.display='';
	noresultdiv.style.display='none';
	
	}
 
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBlazeRuleSequenceView" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>