<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/product/ProductGeneralInformationDetailedPrint.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
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
<script>window.print();</script>
<TITLE>DetailedPrint For Product</TITLE>
</HEAD>

<f:view>
	<BODY>
	<h:form styleClass="form" id="productGenInfoDetailedForm">
		<h:inputHidden id="printRule"
			value="#{contractRuleBackingBean.mapForPrint}"></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD width="5"></TD>
				<TD width="5"></TD>
				<TD width="1"></TD>
			</TR>
		</TABLE>
		<!--	Start of Table for actual Data	-->
		<table width="95%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<!-- General information block starts -->
			<TR id="contractRulesInformation">
				<TD>
				<TABLE border="0" cellspacing="0" cellpadding="0" class="outputText"
					width="95%">
					<TBODY>
						<TR>
							<td colspan="1" valign="top" class="ContentArea">
							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:6px;padding-top:6px;width:100%">


							<br />
							<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
							<div id="panel2">
							<div id="resultInfo" class="dataTableColumnHeader"><br />
							No Rules Available.</div>

							<div id="resultHeaderDiv">
							<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo"
								style="width:100%">Associated Rule Records</div>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
								id="resultHeaderTable" border="0" width="100%">
								<TBODY>

									<TR class="dataTableColumnHeader">
										<td align="left" width = "16%"><h:outputText
											styleClass="formOutputColumnField" value="Generated Id"></h:outputText>
										</td>
										<td align="left"  width = "16%"><h:outputText
											styleClass="formOutputColumnField" value="Benefit Rule ID"></h:outputText>
										</td>
										<td align="left"  width = "40%"><h:outputText
											styleClass="formOutputColumnField" value="Description"></h:outputText>
										</td>
										<td align="left"  width = "14%"><h:outputText
											styleClass="formOutputColumnField" value="Rule Type"></h:outputText>
										</td>
										<td align="left"  width = "14%"><h:outputText
											styleClass="formOutputColumnField" value="Rule Comment"></h:outputText>
										</td>
									</TR>
								</TBODY>
							</TABLE>
							</div>
							<%--
<h:inputHidden id="loadPricingInfoList" value= "#{contractPricingInfoBackingBean.loadPrisingInfoList}"/> 
		--%>
							<div id="panel2Content"
								style="width:100%">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="left"><h:dataTable headerClass="tableHeader"
										id="resultsTable" border="0"
										value="#{contractRuleBackingBean.ruleInformationList}"
										rendered="#{contractRuleBackingBean.renderFlag}" var="eachRow"
										cellpadding="0" cellspacing="1" 
										width="100%">


										<h:column>

											<h:outputText id="generatedId"
												styleClass="formOutputColumnField"
												value="#{eachRow.generatedRuleId}"></h:outputText>
											<h:inputHidden id="ruleGeneratedHid"
												value="#{eachRow.generatedRuleId}"></h:inputHidden>
											<h:inputHidden id="productRuleSysId"
												value="#{eachRow.productRuleSysId}"></h:inputHidden>
										</h:column>
										<h:column>

											<h:outputText id="coverageInfoDesc"
												styleClass="formOutputColumnField" value="#{eachRow.ruleId}"></h:outputText>

											<h:inputHidden id="dateSegmentHid"
												value="#{eachRow.dateSegmentId}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="pricingInfoDesc"
												styleClass="formOutputColumnField"
												value="#{eachRow.ruleShortDescription}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="networkInfoDesc"
												styleClass="formOutputColumnField"
												value="#{eachRow.ruleTypeDescn}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="commentDesc"
												styleClass="formOutputColumnField"
												value="#{eachRow.ruleOverRideValue}"></h:outputText>

										</h:column>
									</h:dataTable></td>
								</tr>
							</table>

							</div>
							</div>

							</fieldset>
							</fieldset>
							<BR>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<!-- General information block ends -->
			<!-- ComponentAssociation block starts -->
			<TR id="productComponentAssociation">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Comp Assoc block ends-->
			<!-- Admin Option block starts-->
			<TR id="productAdminOption">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Admin Option block ends-->
			<!-- Notes block starts-->
			<TR  id="productNotes">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Notes block ends-->
			<!-- Denial and Exclusion block starts-->
			<TR id="productDenialExclusion">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Denial and Exclusion block ends-->
		</TABLE>

		<h:inputHidden id="printRulesGeneralInformation"
			value="#{contractRuleBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductComponentAssociation"
			value="#{productComponentAssociationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductNotes"
			value="#{productNoteAssociationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printAdminOption"
			value="#{productAdminAssociationBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printDenialExclusion"
			value="#{productDenialAndExclusionBackingBean.printValue}"></h:inputHidden>
		<h:commandLink action="#{contractRuleBackingBean.displayRules}" style="display:none; visibility: hidden;" 
				id="linkToRules">
									</h:commandLink>
	</h:form>
	</BODY>
</f:view>

<script>
	var printForrulesGenInfo = document.getElementById("productGenInfoDetailedForm:printRulesGeneralInformation").value;
	var printForCompAss = document.getElementById("productGenInfoDetailedForm:printproductComponentAssociation").value;
	var printForNotes = document.getElementById("productGenInfoDetailedForm:printproductNotes").value;
	var printForAdminOption = document.getElementById("productGenInfoDetailedForm:printAdminOption").value;
	var printForDenialExclusion = document.getElementById("productGenInfoDetailedForm:printDenialExclusion").value;

	alert("1="+printForrulesGenInfo);
	if( null == printForrulesGenInfo || "" == printForrulesGenInfo ){
		var genInfoDivObj = document.getElementById('contractRulesInformation');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	} 
	else{
		alert("2");
		if(document.getElementById('productGenInfoDetailedForm:resultsTable') != null) {
			var tableObj = document.getElementById('productGenInfoDetailedForm:resultsTable');
			if(tableObj.rows.length == 0){
				alert("3");
				var divObj = document.getElementById('resultInfo');
				divObj.style.visibility = 'visible';
				divObj.style.height = "10px";
				
				var tableObjResult = document.getElementById('resultHeaderDiv');
				tableObjResult.style.visibility = 'hidden';
				tableObjResult.style.height = "0px";
				tableObjResult.style.position = 'absolute';
			}
			else{
				alert("4");
				var divObj = document.getElementById('resultInfo');
				divObj.style.visibility = 'hidden';
				divObj.style.height = "0px";
				divObj.style.position = 'absolute';
					
				setColumnWidth('productGenInfoDetailedForm:resultsTable','16%:16%:40%:14%:14%');
				setColumnWidth('resultHeaderTable','16%:16%:40%:14%:14%');
				
			}
		}
		else{
			var divObj = document.getElementById('resultInfo');
			divObj.style.visibility = 'visible';
			divObj.style.height = "10px";
			divObj.style.position = 'absolute';
		}

	}
	if( null == printForCompAss || "" == printForCompAss ){
		var compAssDivObj = document.getElementById('productComponentAssociation');
		compAssDivObj.style.visibility = "hidden";
		compAssDivObj.style.height = "0px";
		compAssDivObj.innerText = null;
	} 
	if( null == printForNotes || "" == printForNotes ){
		var proNoteDivObj = document.getElementById('productNotes');
		proNoteDivObj.style.visibility = "hidden";
		proNoteDivObj.style.height = "0px";
		proNoteDivObj.innerText = null;
	} 
	if( null == printForAdminOption || "" == printForAdminOption ){
		var proAdminDivObj = document.getElementById('productAdminOption');
		proAdminDivObj.style.visibility = "hidden";
		proAdminDivObj.style.height = "0px";
		proAdminDivObj.innerText = null;
	}
	if( null == printForDenialExclusion || "" == printForDenialExclusion ){
		var proDenialDivObj = document.getElementById('productDenialExclusion');
		proDenialDivObj.style.visibility = "hidden";
		proDenialDivObj.style.height = "0px";
		proDenialDivObj.innerText = null;
	}

	window.print();

	

</script>
</HTML>

