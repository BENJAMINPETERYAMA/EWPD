<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>


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

<TITLE>Component Benefit GeneralInfo Print</TITLE>
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
		<TR>
			<td>&nbsp;</td>
		</TR>
		<tr>
			<td><h:form styleClass="form"
				id="componentBenefitGeneralInfoPrintForm">
				<h:inputHidden id="init"
					value="#{benefitComponentBackingBean.viewBenefitComponentKey}"></h:inputHidden>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="#{benefitComponentBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<!--	Start of Table for actual Data	-->
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">General Information</div>
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="125">&nbsp;<h:outputText id="lobLabel"
										value="Line of Business " /></TD>
									<TD width="267"><h:outputText id="lobtxt"
										value="#{benefitComponentBackingBean.lob}" /></TD>
								</TR>
								<TR>
									<TD width="125">&nbsp;<h:outputText id="businessEntityLabel"
										value="Business Entity " /></TD>
									<TD width="267"><h:outputText id="businessEntitytxt"
										value="#{benefitComponentBackingBean.businessEntity}" /></TD>
								</TR>
								<TR>
									<TD width="125">&nbsp;<h:outputText id="businessGroupLabel"
										value="Business Group " /></TD>
									<TD width="267"><h:outputText id="businessGrouptxt"
										value="#{benefitComponentBackingBean.businessGroup}" /></TD>
								</TR>
								<TR>
									<TD width="125">&nbsp;<h:outputText id="marketBusinessUnitLabel"
										value="Market Business Unit  " /></TD>
									<TD width="267"><h:outputText id="marketBusinessUnittxt"
										value="#{benefitComponentBackingBean.marketBusinessUnit}" /></TD>
								</TR>
								<TR>
									<TD width="125">&nbsp;<h:outputText id="name_label"
										value="Name " /></TD>
									<TD width="267"><h:outputText id="name_txt"
										value="#{benefitComponentBackingBean.name}" /></TD>
								</TR>
								<TR>
									<TD width="125">&nbsp;<h:outputText value="Component Type" /></TD>
									<TD width="267"><h:outputText id="componentId"
										value="#{benefitComponentBackingBean.componentType}"></h:outputText>
									<h:inputHidden id="CorpPlanCd_list1"
										value="#{benefitComponentBackingBean.componentType}"></h:inputHidden></TD>
								</TR>

								<TR>
									<TD height="27" width="166" valign="top">&nbsp;<h:outputText
										id="description_label" value="Description" /></TD>
									<TD height="27" width="267"><h:outputText id="description_txtarea" 
										styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"
										value="#{benefitComponentBackingBean.description}" /></TD>
								</TR>

								<TR id="sub1" style="display:none;">
									<TD width="166">&nbsp;<h:outputText value="Mandate Type" /></TD>
									<TD width="267"><h:outputText id="mandateId"
										value="#{benefitComponentBackingBean.mandateType}"></h:outputText>
									<h:inputHidden id="Mandate_type_list1"
										value="#{benefitComponentBackingBean.mandateType}"></h:inputHidden></TD>

								</TR>

								<TR id="sub2" style="display:none;">
									<TD width="166">&nbsp;<h:outputText value="State" /></TD>
									<TD width="267">
									<DIV id="StateDiv"></DIV>
									<h:inputHidden id="txtState"
										value="#{benefitComponentBackingBean.selectedStateId}"></h:inputHidden></TD>
								</TR>
								<TR id="sub3" style="display:none;">
									<TD width="136">&nbsp;<h:outputText id="stateCde2"
										value="State" /></TD>
									<TD width="267"><h:outputText id="FederalLabel" value="ALL"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="166" valign="top">&nbsp;<h:outputText
										id="ruleEditStar" value="Benefit Rule ID" /></TD>
									<TD width="267"><h:outputText id="txtRule"
										value="#{benefitComponentBackingBean.ruleId}"></h:outputText></TD>
								</TR>
								<TR>
									<TD height="27" width="166">&nbsp;<h:outputText
										id="effectiveDate_label" value="Effective Date" /></TD>
									<TD height="27" width="267"><h:outputText
										id="effectiveDate_txt"
										value="#{benefitComponentBackingBean.effectiveDate}" /></TD>
								</TR>
								<TR>
									<TD height="27" width="166">&nbsp;<h:outputText
										id="expiryDate_label" value="Expiry Date" /></TD>
									<TD height="27" width="267"><h:outputText id="expiryDate_txt"
										value="#{benefitComponentBackingBean.expiryDate}" /></TD>
								</TR>
								<TR>
									<TD height="27" width="166">&nbsp;<h:outputText
										id="createdBy_label" value="Created By" /></TD>
									<TD height="27" width="267"><h:outputText id="createdBy_txt"
										value="#{benefitComponentBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD height="27" width="166">&nbsp;<h:outputText
										id="createDate_label" value="Created Date" /></TD>
									<TD height="27" width="267"><h:outputText id="createDate_txt"
										value="#{benefitComponentBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD height="27" width="166">&nbsp;<h:outputText
										id="updatedBy_label" value="Last Updated By" /></TD>
									<TD height="27" width="267"><h:outputText id="updatedBy_txt"
										value="#{benefitComponentBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD height="27" width="166">&nbsp;<h:outputText
										id="updationDate_label" value="Last Updated Date" /></TD>
									<TD height="27" width="267"><h:outputText id="updationDate_txt"
										value="#{benefitComponentBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>

						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="4%"></td>
								<td align="left" width="71%"></td>
								<td align="left" width="25%">
								<table Width=100%>
									<tr>
										<td width="2%"><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentBackingBean.state}" />
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentBackingBean.status}" />
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						<BR>
						</TD>
					</TR>
				</table>

			</h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script>
var i;
var obj;
obj = document.getElementById("componentBenefitGeneralInfoPrintForm:CorpPlanCd_list1");

i= obj.value;
	
	if(i=="MANDATE")
	{
	sub1.style.display='';
	}
	else 
	{
	sub1.style.display='none';
	sub2.style.display='none';
	}
var i;
var obj;
obj = document.getElementById("componentBenefitGeneralInfoPrintForm:Mandate_type_list1");
i= obj.value;
		
		if(i== "ExtraTerritorial" || i=="State")
		{
		sub2.style.display='';
		sub3.style.display='none';				
		}
		else if(i == "Federal")
		{
		// Federal
        // alert('federal');
			sub2.style.display='none';
			sub3.style.display='';
		}else{
			sub2.style.display='none';
			sub3.style.display='none';
		}


formatTildaToComma("componentBenefitGeneralInfoPrintForm:lobtxt");
formatTildaToComma("componentBenefitGeneralInfoPrintForm:businessEntitytxt");
formatTildaToComma("componentBenefitGeneralInfoPrintForm:businessGrouptxt");
formatTildaToComma("componentBenefitGeneralInfoPrintForm:marketBusinessUnittxt");
formatTildaToComma1("componentBenefitGeneralInfoPrintForm:txtRule");

formatTildaToComma("componentBenefitGeneralInfoPrintForm:txtState");
copyHiddenToDiv_ewpd('componentBenefitGeneralInfoPrintForm:txtState','StateDiv',2,2);  
copyHiddenToInputText('componentBenefitGeneralInfoPrintForm:CorpPlanCd_list1','componentBenefitGeneralInfoPrintForm:componentId',1);
copyHiddenToInputText('componentBenefitGeneralInfoPrintForm:Mandate_type_list1','componentBenefitGeneralInfoPrintForm:mandateId',1);
//copyHiddenToInputText1('componentBenefitGeneralInfoPrintForm:txtRule','rule');	

</script>
<script>
window.print();

</script>

</HTML>
