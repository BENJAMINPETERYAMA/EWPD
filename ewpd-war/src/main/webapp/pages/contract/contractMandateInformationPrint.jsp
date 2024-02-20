<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>ContractBenefitMandateInformation</TITLE>
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
<BASE target="_self" />
<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		
		
		<tr>
			<td>
				<h:form styleClass="form" id="nonAdjBenCompForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr><td>&nbsp; </td></tr>
						<TR>
					<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
						<TR>

	                		

	                		<TD colspan="2" valign="top" class="ContentArea">
<!-- End of Tab table -->
<!--								<h:inputHidden id="Hidden" value="#{contractBenefitMndateInfoBacingBean.loadForPrint}"></h:inputHidden>-->
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									<div id="panel2Header" class="tabTitleBar"
					style="position:relative;width:100% ">Mandate Information</div>
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
</br>
<tr>
										<td width="32%">&nbsp;<h:outputText										
										id="StateCodeStar" value="Jurisdiction " /></td>
									<td width="68%">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">
										<tr>
											<td width="44%">
											<h:outputText id="txtState"
												value="#{contractBenefitMndateInfoBacingBean.stateCode}"></h:outputText>
											</TD>											
										</tr>
									</table>
									</td>
									</tr>
									<tr>
										<td width="24%">&nbsp;<h:outputText											
											id="MandateTypeStar" value="Mandate Category " /></td>
										<td width="42%"><h:outputText value="#{contractBenefitMndateInfoBacingBean.mandateCategory}"></h:outputText></td>										
										
									</tr>
									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="CitationNumberStar" value="Citation Number " /></TD>
										<td><h:outputText id="CitationNumberHidden"
												value="#{contractBenefitMndateInfoBacingBean.citationNumber}"></h:outputText>										
										</TD>
									</TR>
										
									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="FundingArrangementStar" value="Funding Arrangement " /></TD>
										<td><h:outputText id="FundingArrangementHidden" value="#{contractBenefitMndateInfoBacingBean.fundingArrangement}"></h:outputText>
										</td>
									</TR>

									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="EffectivenessStar" value="Effectiveness " /></TD>
										<td width="42%"><h:outputText value="#{contractBenefitMndateInfoBacingBean.effectiveness}"></h:outputText>
										
										</td>
									</TR>


									<TR>
									<TD width="24%" valign="top">&nbsp;<h:outputText										
										id="descriptionStar" value="Text " /></TD>
									<TD width="42%" valign="top"><h:outputText
										 id="txtDescription"
										value="#{contractBenefitMndateInfoBacingBean.text}" ></h:outputText></TD>
									<td width="63%"><f:verbatim></f:verbatim></td>
									</TR>

																					
									<TR>							
										
									</TR>
									<TR>
										<TD width="221">&nbsp;</TD>
									</TR>
            					
        
</TABLE>
								
								</FIELDSET>		
								<BR>
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1" ></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		
	</table>
</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitMandateInfoPrint" /></form>
<script>
		formatTildaToComma('nonAdjBenCompForm:FundingArrangementHidden');
		formatTildaToComma('nonAdjBenCompForm:CitationNumberHidden');
		formatTildaToCommaState('nonAdjBenCompForm:txtState');

function formatTildaToCommaState(objName)
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

		window.print();
</script>
</HTML>
