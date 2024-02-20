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

<TITLE>Contract Benefit Component General Info</TITLE>
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
			<td><h:form styleClass="form" id="contractBenefitComponentFormPrint">
				<br />
				<br />

				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">
				<div id="panel2Header" class="tabTitleBar"
					style="position:relative;width:100% ">General Information</div>
				<h:inputHidden id="loadForPrint"
					value="#{contractComponentGeneralInfoBackingBean.valueForPrint}"></h:inputHidden>

				<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
					<TBODY>
						
						<TR>
							<TD colspan="5">
							<FIELDSET
								style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
							<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
							<TABLE border="0" cellspacing="0" cellpadding="3">
								<tr>


									<TD width="130"><h:outputText value="Line of Business" /></TD>
									<TD width="194"><h:outputText id="lobHidden"
										value="#{contractComponentGeneralInfoBackingBean.lineOfBusiness}">
									</h:outputText></TD>
								</TR>
								<TR>
									<TD width="130"><h:outputText value="Business Entity" /></TD>
									<TD width="194"><h:outputText id="entityHidden"
										value="#{contractComponentGeneralInfoBackingBean.businessEntity}">
									</h:outputText></TD>
								</TR>
								<TR>
									<TD width="130"><h:outputText value="Business Group" /></TD>
									<TD width="194"><h:outputText id="groupHidden"
										value="#{contractComponentGeneralInfoBackingBean.businessGroup}">
									</h:outputText></TD>

								</TR>
								<TR>
									<TD width="130"><h:outputText value="Market Business Unit" /></TD>
									<TD width="194"><h:outputText id="marketBusinessUnitHidden"
										value="#{contractComponentGeneralInfoBackingBean.marketBusinessUnit}">
									</h:outputText></TD>

								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Name" /></TD>
							<TD width="239"><h:outputText id="name"
								value="#{contractComponentGeneralInfoBackingBean.name}" />
							</TD>
						</TR>
						

						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Component Type" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.componentType}"
								id="compType" /></TD>
						</TR>
						
						<TR>
							<TD width="3"></TD>
							<TD width="135" valign="top"><h:outputText value="Description" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.description}"></h:outputText>
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Benefit Rule ID" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.ruleIdForView}"
								id="ruleId" /></TD>
						</TR>
                        <TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Effective Date" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.effectiveDate}"
								id="effDate" /></TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Expiry Date" /></TD>
							<TD width="239"><h:outputText id="expiryDate"
								value="#{contractComponentGeneralInfoBackingBean.expiryDate}" />
							</TD>
						</TR>
						<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Version" /></TD>
									<TD width="239"><h:outputText id="versionId"
										value="#{contractComponentGeneralInfoBackingBean.benefitComponentVersion}" />
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.benefitComponentVersion}"></h:inputHidden>
									</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Created By" /></TD>
							<TD width="239"><h:outputText id="createdUser"
								value="#{contractComponentGeneralInfoBackingBean.createdBy}" />
							</TD>
						</TR>
                        
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Created Date" /></TD>
							<TD width="239"><h:outputText id="creationDate"
								value="#{contractComponentGeneralInfoBackingBean.createdDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Last Updated By" /></TD>
							<TD width="239"><h:outputText id="lastUpdatedUser"
								value="#{contractComponentGeneralInfoBackingBean.updatedBy}" />

							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Last Updated Date" /></TD>
							<TD width="239"><h:outputText id="lastUpdatedDate"
								value="#{contractComponentGeneralInfoBackingBean.lastUpdatedDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							</TD>
						</TR>
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

<script>

		formatTildaToComma('contractBenefitComponentFormPrint:lobHidden');
		formatTildaToComma('contractBenefitComponentFormPrint:entityHidden');
		formatTildaToComma('contractBenefitComponentFormPrint:groupHidden');
		formatTildaToComma('contractBenefitComponentFormPrint:marketBusinessUnitHidden');


					 	
	

</script>
<script>window.print();</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComponentGeneralInfo" /></form>
</HTML>
