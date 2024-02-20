<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Print Benefit General Information</TITLE>
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
		<tr>
			<td><h:form styleClass="form" id="benefitGeneralInformationPrintForm">
				<h:inputHidden id="generalInfoPrint"
					value="#{benefitComponentCreateBackingBean.loadBenefitComponentforPrint}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
						<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:1px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:70%">
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
							<TD colspan=3>
							<FIELDSET style="width:70%">


							<TABLE width="100%">
								<TR>
									<TD width="325">
									<div id="panel3Header" style="position:relative;width:100% "><STRONG>General
									Information</STRONG></div>
									<br />
									</TD>
								</TR>
								<TR>
									<TD width="35%"><h:outputText id="LobStar"
										value="Line of Business " /></TD>
									<TD width="65%"><h:outputText id="txtLob"
										value="#{benefitComponentCreateBackingBean.lineOfBusiness}"></h:outputText>
									</TD>

								</TR>
								<TR>
									<TD width="35%"><h:outputText id="BusinessEntityStar"
										value="Business Entity " /></TD>
									<TD width="65%"><h:outputText id="txtBusinessEntity"
										value="#{benefitComponentCreateBackingBean.businessEntity}"></h:outputText>
									</TD>

								</TR>
								<TR>
									<TD width="35%"><h:outputText id="BusinessGroupStar"
										value="Business Group " /></TD>
									<TD width="65%"><h:outputText id="txtBusinessGroup"
										value="#{benefitComponentCreateBackingBean.businessGroup}"></h:outputText>
									</TD>
								</TR>
<!-- --------------------------------------------------------------------- -->
								<TR>
									<TD width="35%"><h:outputText
										value="Market Business Unit " /></TD>
									<TD width="65%"><h:outputText id="txtMarketBusinessUnit"
										value="#{benefitComponentCreateBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>
								</TR>
<!-- --------------------------------------------------------------------- -->
								<TR>
									<TD width="35%"><h:outputText value="Name" /></TD>
									<TD width="65%"><h:outputText
										value="#{benefitComponentCreateBackingBean.minorHeading}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%"><h:outputText value="Benefit Meaning" /></TD>
									<TD width="65%"><h:outputText
										value="#{benefitComponentCreateBackingBean.minorHeading}"></h:outputText>
									</TD>
								</TR>

								<TR>
									<TD width="35%"><h:outputText value="Benefit Type" /></TD>
									<TD width="65%"><h:outputText id="benType"
										value="#{benefitComponentCreateBackingBean.sbBenType}" /></TD>
								</TR>
								<TR id="sub1" ; style="display:none;">
									<TD width="35%"><h:outputText value="Mandate Type" /></TD>
									<TD width="65%"><h:outputText id="manType"
										value="#{benefitComponentCreateBackingBean.sbMandateType}" />
									</TD>
								</TR>
								<TR id="sub2" ; style="display:none;">
									<TD width="35%"><h:outputText value="State" /></TD>
									<TD width="65%"><h:outputText id="stateCde"
										value="#{benefitComponentCreateBackingBean.sbSelState}" /></TD>
								</TR>
								<TR>
									<TD width="35%"><h:outputText value="Benefit Category" /></TD>
									<TD width="65%"><h:outputText id="benefitCategoryId"
										value="#{benefitComponentCreateBackingBean.benefitCategory}" /></TD>
								</TR>
								<TR>
									<TD valign="top" width="35%"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="65%"><h:outputText id="txtDescription"
										value="#{benefitComponentCreateBackingBean.stdBenefitDescription}"></h:outputText>
									</TD>
								</TR>

								<TR>
									<TD width="35%"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="65%">
									<h:outputText id="ruleIdTxt"
										value="#{benefitComponentCreateBackingBean.sbRule}"></h:outputText>
									</TD>
								</TR>


								<TR>
									<TD width="35%"><h:outputText value="Tier Definition " /></TD>
									<TD width="65%">
									<h:outputText id="txtTier" 
											value="#{benefitComponentCreateBackingBean.tierBenefitComp}"></h:outputText>
									</TD>
								</TR>

								<TR>
									<TD width="35%" valign="top"><h:outputText id="termEditStar" value="Term " /></TD>
									<TD width="65%"><h:outputText id="txtTerm"
										value="#{benefitComponentCreateBackingBean.term}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%" valign="top"><h:outputText id="QualifierEditStar"
										value="Qualifier" /></TD>
									<TD width="65%"><h:outputText id="txtQualifier"
										value="#{benefitComponentCreateBackingBean.qualifier}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%" valign="top"><h:outputText id="ProviderArrangementEditStar"
										value="Provider Arrangement " /></TD>
									<TD width="65%" valign="top"><h:outputText id="txtProviderArrangement"
										value="#{benefitComponentCreateBackingBean.providerArrangement}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%"><h:outputText id="DataTypeEditStar"
										value="Data Type " /></TD>
									<TD width="65%"><h:outputText id="txtDataType"
										value="#{standardBenefitBackingBean.dataType}" /></TD>
								</TR>
								<TR>
									<TD width="42%"><span class="mandatoryNormal" id="version"></span><h:outputText
									value="Version" /></TD>
									<TD width="58%"><h:outputText id="VersionView"
									value="#{benefitComponentCreateBackingBean.benefitVersion}" /></TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText
										value="Created By" /></TD>
									<TD width="65%"><h:outputText id="createdUserView"
										value="#{benefitComponentCreateBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
										value="Created Date" /></TD>
									<TD width="65%"><h:outputText id="createdDateView"
										value="#{benefitComponentCreateBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText>
									<h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
										value="Last Updated By" /></TD>
									<TD width="65%"><h:outputText id="updatedUserView"
										value="#{benefitComponentCreateBackingBean.lastUpdatedUser}" />
									</TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="65%"><h:outputText id="updatedTimeView"
										value="#{benefitComponentCreateBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText>
									<h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
			</h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script>
formatTildaToComma("benefitGeneralInformationPrintForm:txtLob");
formatTildaToComma("benefitGeneralInformationPrintForm:txtBusinessEntity");
formatTildaToComma("benefitGeneralInformationPrintForm:txtBusinessGroup");
formatTildaToComma("benefitGeneralInformationPrintForm:txtMarketBusinessUnit");
formatTildaToComma("benefitGeneralInformationPrintForm:txtTerm");
formatTildaToComma("benefitGeneralInformationPrintForm:txtQualifier");
formatTildaToComma("benefitGeneralInformationPrintForm:txtProviderArrangement");
formatTildaToComma1("benefitGeneralInformationPrintForm:ruleIdTxt");

//for tier definitions
formatTilda("benefitGeneralInformationPrintForm:txtTier"); 

// For Rule Id
	
	//copyHiddenToDiv_ewpd('benefitGeneralInformationPrintForm:ruleIdHidden','ruleIdDiv',2,1);
	//copyHiddenToDiv_ewpd1('benefitGeneralInformationPrintForm:ruleIdHidden','ruleIdDiv');
	formatTildaToCommaForDatatype("benefitGeneralInformationPrintForm:txtDataType"); 

function formatTildaToCommaForDatatype(objName)
{
	var formattedString = "";
	var obj = document.getElementById(objName);
	var val = obj.innerHTML;
	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{

		for(i=1, n = values.length; i < n; i+=2)
		{
			formattedString += values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}

getBenefitType();
getMandateType();

function getBenefitType()
	{
	var benType;	
	benType = document.getElementById("benefitGeneralInformationPrintForm:benType");
	if(benType.innerHTML =="MANDATE")
	{
		sub1.style.display = '';
	}
	else
	{
		sub1.style.display = 'none';
		sub2.style.display = 'none';
	}
} 

function getMandateType(){
	var manType;
	manType = document.getElementById("benefitGeneralInformationPrintForm:manType");
	if(manType.innerHTML =="State" || manType.innerHTML=="ET"){
		sub2.style.display = '';
	}
	else{
		sub2.style.display = 'none';
	}
}
window.print();

//input will be Tier4~4~Tier2~2~Tier3~3
//output will be Tier4,Tier2,Tier3
function formatTilda(objName)
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

</HTML>


