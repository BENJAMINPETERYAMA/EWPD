<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractBenefitComponentGeneralInfo.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Contract Benefit General Info</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr><td>&nbsp; </td></tr>
		<TR>
					<TD>  <FIELDSET style="margin-left:6px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:90%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
		<tr>
			<td><h:form styleClass="form" id="contractBenefitGeneralInfoPrint">
				<br />
				<br />

				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">
				<div id="panel2Header" class="tabTitleBar"
					style="position:relative;width:100% ">General Information</div>
				<h:inputHidden id="loadForPrint"
					value="#{contractBenefitGeneralInfoBackingBean.valueForPrint}"></h:inputHidden>

				<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
					<TBODY>
						<TR>
						<TR>
							<TD colspan="5">
							<FIELDSET
								style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
							<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
							<TABLE border="0" cellspacing="0" cellpadding="3">
								<tr>


									<TD width="130"><h:outputText value="Line of Business" /></TD>
									<TD width="194"><h:outputText id="lobHidden"
										value="#{contractBenefitGeneralInfoBackingBean.lob}">
									</h:outputText></TD>
								</TR>
								<TR>
									<TD width="130"><h:outputText value="Business Entity" /></TD>
									<TD width="194"><h:outputText id="entityHidden"
										value="#{contractBenefitGeneralInfoBackingBean.businessEntity}">
									</h:outputText></TD>
								</TR>
								<TR>
									<TD width="130"><h:outputText value="Business Group" /></TD>
									<TD width="194"><h:outputText id="groupHidden"
										value="#{contractBenefitGeneralInfoBackingBean.businessGroup}">
									</h:outputText></TD>

								</TR>
								<TR>
									<TD width="130"><h:outputText value="Market Business Unit" /></TD>
									<TD width="194"><h:outputText id="marketBusinessUnitHidden"
										value="#{contractBenefitGeneralInfoBackingBean.marketBusinessUnit}">
									</h:outputText></TD>

								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<TR>
							<TD width="23%">&nbsp;<h:outputText id="MinorHeadingStar"
								value="Name " /></TD>
							<TD width="34%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.minorHeading}"></h:outputText>
							</TD>
							<TD width="42%"></TD>
						</TR>
						<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="ruleType">&nbsp;</span><h:outputText value="Benefit Meaning" /></td>
							<td width="34%"><h:outputText id="benefitMeaning"
								value="#{contractBenefitGeneralInfoBackingBean.benefitMeaning}" />
							</td>
							<TD width="288"></TD>

						</tr>
							<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="creationDateId">&nbsp;</span><h:outputText
								value="Benefit Type" /></td>
							<td width="34%"><h:outputText value="#{contractBenefitGeneralInfoBackingBean.benefitType}" />
							<h:inputHidden id="txtBenefitId" value="#{contractBenefitGeneralInfoBackingBean.benefitType}"></h:inputHidden>
							<TD width="288"></TD>

						</tr>
					    <tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="benefitCategoryId">&nbsp;</span><h:outputText
								value="Benefit Category" /></td>
							<td width="34%"><h:outputText value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}" />
							<h:inputHidden id="txtBenefitCategoryId" value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}"></h:inputHidden>
							<TD width="288"></TD>

						</tr>
					
						<TR>
							<TD width="23%" valign="top">&nbsp;<h:outputText id="descriptionStar"
								value="Description " /></TD>
							<TD width="34%"><h:outputText id="txtDescription"
								value="#{contractBenefitGeneralInfoBackingBean.description}"></h:outputText></TD>
							<TD width="42%"></TD>
						</TR>

						<TR>
							<TD width="23%" valign="top">&nbsp;<h:outputText value="Tier Definition " /></TD>
							<TD width="34%"><h:outputText id="txtTier"
								value="#{contractBenefitGeneralInfoBackingBean.tierDefinitionForprint}"></h:outputText></TD>
							<TD width="42%"></TD>
						</TR>

							<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="ruleType">&nbsp;</span><h:outputText value="Benefit Rule ID" /></td>
							<td width="34%"><h:outputText id="txtRuleType"
								value="#{contractBenefitGeneralInfoBackingBean.ruleType}"></h:outputText>
							<h:inputHidden id="ruleTypeHidden"
								value="#{contractBenefitGeneralInfoBackingBean.ruleIDFetched}"></h:inputHidden></td>

						</tr>
						
						<TR>
							<TD colspan="3">
							<FIELDSET style="width:50%"><LEGEND><FONT color="black">Benefit Level Scope</FONT></LEGEND>
							<TABLE width="119%">
								<TR>
									<TD width="41%"><h:outputText id="termEditStar" value="Term " /></TD>
									<TD width="58%"><h:outputText id="txtTerm"
										value="#{contractBenefitGeneralInfoBackingBean.term}" /></TD>
									<TD width="1%"></TD>
								</TR>
								<TR>
									<TD width="41%"><h:outputText id="QualifierEditStar"
										value="Qualifier" /></TD>
									<TD width="58%"><h:outputText id="txtQualifier"
										value="#{contractBenefitGeneralInfoBackingBean.qualifier}" />
									</TD>
									<TD width="1%"></TD>
								</TR>
								<TR>
									<TD width="41%"><h:outputText id="ProviderArrangementEditStar"
										value="Provider Arrangement " /></TD>
									<TD width="58%"><h:outputText id="txtProviderArrangement"
										value="#{contractBenefitGeneralInfoBackingBean.providerArrangement}" />
									</TD>
									<TD width="1%"></TD>
								</TR>
								<TR>
									<TD width="41%"><h:outputText id="DataTypeEditStar"
										value="Data Type " /></TD>
									<TD width="58%"><h:outputText id="txtDataType"
										value="#{contractBenefitGeneralInfoBackingBean.dataType}" />
									</TD>
									<TD width="1%"></TD>
								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						
						<tr>
								<td valign="top" width="24%"><span class="mandatoryNormal"
									id="versionId">&nbsp;<h:outputText
										value="Version" /></td>
									<td width="34%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}" />
									<h:inputHidden id="versionHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}"></h:inputHidden></td>
									<TD width="288"></TD>
								</tr>
						<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="creationDateId">&nbsp;</span><h:outputText
								value="Created By" /></td>
							<td width="34%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.createdUser}" />
							<h:inputHidden id="txtCreatedUser"
								value="#{contractBenefitGeneralInfoBackingBean.createdUser}"></h:inputHidden></td>
							<TD width="288"></TD>

						</tr>
						<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="createdBy">&nbsp;</span><h:outputText value="Created Date" /></td>
							<td width="34%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							<h:inputHidden id="txtCreatedDate"
								value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:inputHidden> <h:inputHidden id="time"
								value="#{requestScope.timezone}">
							</h:inputHidden></td>
							<TD width="288"></TD>
						</tr>
						<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="updationDate">&nbsp;</span><h:outputText
								value="Last Updated By" /></td>
							<td width="34%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}" />
							<h:inputHidden id="txtUpdatedUser"
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}"></h:inputHidden></td>
							<TD width="288"></TD>
						</tr>
						<tr>
							<td valign="top" width="24%"><span class="mandatoryNormal"
								id="updateBy">&nbsp;</span><h:outputText
								value="Last Updated Date" /></td>
							<td width="34%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							<h:inputHidden id="txtUpdatedDate"
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:inputHidden></td>
							<TD width="288"></TD>
						</tr>

					</TBODY>
				</TABLE>







				<!--	Start of Table for actual Data	--> <!--	End of Page data	--></fieldset></TD>
		</TR>
	</table>


	<!-- Space for hidden fields -->
	<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
	<h:commandLink id="hiddenLnk1"
		style="display:none; visibility: hidden;">
		<f:verbatim />
	</h:commandLink>
	<!-- End of hidden fields  -->

	</h:form>


	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitGenInfoPrint" /></form>
<script>

		 
		formatTildaToComma('contractBenefitGeneralInfoPrint:lobHidden');
		formatTildaToComma('contractBenefitGeneralInfoPrint:entityHidden');
		formatTildaToComma('contractBenefitGeneralInfoPrint:groupHidden');;
		formatTildaToComma('contractBenefitGeneralInfoPrint:marketBusinessUnitHidden');
		formatTildaToComma('contractBenefitGeneralInfoPrint:txtTerm');
		formatTildaToComma('contractBenefitGeneralInfoPrint:txtQualifier');
		formatTildaToComma('contractBenefitGeneralInfoPrint:txtProviderArrangement');
		formatTildaToCommaFotDatatype('contractBenefitGeneralInfoPrint:txtDataType');
		formatTildaToComma1('contractBenefitGeneralInfoPrint:txtRuleType');
		formatTildaToCommaFotDatatype('contractBenefitGeneralInfoPrint:txtTier');
	
function formatTildaToCommaFotDatatype(objName)
{

	var formattedString = "";
	var obj = document.getElementById(objName);

	var val = obj.innerHTML;

	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{

		for(i=0, n = values.length; i < n; i+=2)
		{
			formattedString += values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}



</script>
<script>window.print();</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComponentGeneralInfo" /></form>
</HTML>
