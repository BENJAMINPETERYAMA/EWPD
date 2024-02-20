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

<TITLE>Print Benefit</TITLE>
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
	<table width="100%" cellpadding="0" >
			<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{standardBenefitBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
		<tr>
			<td><h:form styleClass="form" id="benefitPrintForm">
				<h:inputHidden id="viewStandardBenefitKey"
					value="#{standardBenefitBackingBean.printStandardBenefitKey}" />

					
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText" width="100%">
					<TBODY>
							<TR>

							<TD colspan=3 valign="top" class="ContentArea">
							<FIELDSET style="width:70%">
							<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">General Information</div>

							<TABLE width="71%">
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="0">
								<TR>
								 <TD width="5"></TD>
									<TD width="42%"><h:outputText id="LobStar"
										value="Line of Business " styleClass="outputText" /></TD>
									<TD width="58%"><h:outputText id="txtLob"
										value="#{standardBenefitBackingBean.lob}"></h:outputText></TD>
								</TR>
								<TR>
								 <TD width="5"></TD>
									<TD width="42%"><h:outputText id="BusinessEntityStar"
										value="Business Entity " styleClass="outputText" /></TD>
									<TD width="58%"><h:outputText id="txtBusinessEntity"
										value="#{standardBenefitBackingBean.businessEntity}"></h:outputText>
									</TD>
								</TR>

								<TR>
								 <TD width="5"></TD>
									<TD width="42%"><h:outputText id="BusinessGroupStar"
										value="Business Group " styleClass="outputText" /></TD>
									<TD width="58%"><h:outputText id="txtBusinessGroup"
										value="#{standardBenefitBackingBean.businessGroup}"></h:outputText>
									</TD>
								</TR>
<!-- ---------------------------------------------------------------------------------------- -->
								<TR>
								 <TD width="5"></TD>
									<TD width="42%"><h:outputText id="MarketBusinessUnitStar"
										value="Market Business Unit " styleClass="outputText" /></TD>
									<TD width="58%"><h:outputText id="txtMarketBusinessUnit"
										value="#{standardBenefitBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>
								</TR>
<!-- ---------------------------------------------------------------------------------------- -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
								<TR>
									<TD width="35%"><h:outputText value="Name" /></TD>
									<TD width="68%"><h:outputText
										value="#{standardBenefitBackingBean.minorHeading}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%"><h:outputText value="Benefit Meaning" /></TD>
									<TD width="68%"><h:outputText
										value="#{standardBenefitBackingBean.minorHeading}"></h:outputText>
									</TD>
								<TR>
									<TD width="35%"><h:outputText value="Benefit Type" /></TD>
									<TD width="68%"><h:outputText id="benefitId"
										value="#{standardBenefitBackingBean.benefitType}"></h:outputText>
									<h:inputHidden id="CorpPlanCd_list1"
										value="#{standardBenefitBackingBean.benefitType}"></h:inputHidden></TD>
								</TR>

								<TR id="sub1" style="display:none;">
									<TD width="35%"><h:outputText value="Mandate Type" /></TD>
									<TD width="68%"><h:outputText id="mandateId"
										value="#{standardBenefitBackingBean.mandateType}"></h:outputText>
									<h:inputHidden id="Mandate_type_list1"
										value="#{standardBenefitBackingBean.mandateType}"></h:inputHidden></TD>

								</TR>
								<TR>
									<TD width="35%"><h:outputText value="Benefit Category" /></TD>
									<TD width="68%"><h:outputText id="benefitCategoryId"
										value="#{standardBenefitBackingBean.benefitCategoryHidden}"></h:outputText>
									<!--<h:inputHidden id="Benefit_Category_list1"
										value="#{standardBenefitBackingBean.benefitCategory}"></h:inputHidden> -->
									</TD>
								</TR>
								<TR>
									<TD valign="top" width="35%"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="68%"><h:outputText id="txtDescription" styleClass="formTxtAreaReadOnly"  
										style="border:none;width:250px;"
										value="#{standardBenefitBackingBean.description}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%"><h:outputText value="Benefit Rule ID " /></TD>
									<TD width="68%"><h:outputText id="txtRule"
										value="#{standardBenefitBackingBean.rule}"></h:outputText>
									<TD width="1%"></TD>
								</TR>

								<TR>
									<TD valign="top" width="35%"><h:outputText id="termEditStar"
										value="Term " /></TD>
									<TD width="68%"><h:outputText id="txtTerm"
										value="#{standardBenefitBackingBean.term}"></h:outputText></TD>
								</TR>
								<TR>
									<TD valign="top" width="35%"><h:outputText
										id="QualifierEditStar" value="Qualifier" /></TD>
									<TD width="68%"><h:outputText id="txtQualifier"
										value="#{standardBenefitBackingBean.qualifier}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD valign="top" width="35%"><h:outputText
										id="ProviderArrangementEditStar" value="Provider Arrangement " /></TD>
									<TD width="68%"><h:outputText id="txtProviderArrangement"
										value="#{standardBenefitBackingBean.providerArrangement}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD valign="top" width="35%"><h:outputText
										id="DataTypeEditStar" value="Data Type " /></TD>
									<TD width="68%"><h:outputText id="txtDataType"
										value="#{standardBenefitBackingBean.dataType}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText value="Created By" /></TD>
									<TD width="68%"><h:outputText id="createdUserView"
										value="#{standardBenefitBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
										value="Created Date" /></TD>
									<TD width="68%"><h:outputText id="createdDateView"
										value="#{standardBenefitBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
									</h:outputText><h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
										value="Last Updated By" /></TD>
									<TD width="68%"><h:outputText id="updatedUserView"
										value="#{standardBenefitBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="35%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="68%"><h:outputText id="updatedTimeView"
										value="#{standardBenefitBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
									</h:outputText><h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<BR>
							<FIELDSET style="width:70%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"></td>
								<td align="left"></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText value="#{standardBenefitBackingBean.state}" /></td>

									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText value="#{standardBenefitBackingBean.status}" /></td>

									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText
											value="#{standardBenefitBackingBean.version}" /></td>

									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>



							</TD>
						</TR>



					</TBODY>
				</TABLE></td>
		</tr>
		
			
			</h:form>
			</table>
	</BODY>
</f:view>

</HTML>
<script>

var i;
var obj;
obj = document.getElementById("benefitPrintForm:CorpPlanCd_list1");

i= obj.value;

	if(i=="MANDATE")
	{
	sub1.style.display='';
	}
	else 
	{
	sub1.style.display='none';
	//sub2.style.display='none';
	}
var i;
var obj;
obj = document.getElementById("benefitPrintForm:Mandate_type_list1");
i= obj.value;

		

formatTildaToComma("benefitPrintForm:txtLob");
formatTildaToComma("benefitPrintForm:txtBusinessEntity");
formatTildaToComma("benefitPrintForm:txtBusinessGroup");
formatTildaToComma("benefitPrintForm:txtMarketBusinessUnit");
formatTildaToComma("benefitPrintForm:txtTerm");
formatTildaToComma("benefitPrintForm:txtQualifier");
formatTildaToComma("benefitPrintForm:txtProviderArrangement");
formatTildaToCommaForDatatype("benefitPrintForm:txtDataType"); 
formatTildaToComma1("benefitPrintForm:txtRule");

copyHiddenToInputText('benefitPrintForm:CorpPlanCd_list1','benefitPrintForm:benefitId',1);
copyHiddenToInputText('benefitPrintForm:Mandate_type_list1','benefitPrintForm:mandateId',1);
//copyHiddenToInputText('benefitPrintForm:Benefit_Category_list1','benefitPrintForm:benefitCategoryId',1);
//copyHiddenToDiv_ewpd1('benefitPrintForm:txtRule','RuleDiv',2,1);	




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



</script>
<script>window.print();</script>
