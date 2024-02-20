<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>Mandate Information</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<h:inputHidden id="printHidden"
				value="#{productStructureBenefitMandatesBackingBean.printMandateInformation}" />
			<td><h:form styleClass="form" id="benefitAdmnViewForm">
				<!--	Start of Table for actual Data	-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{productStructureGeneralInfoBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
                  <TR>
                        <TD>
                              &nbsp;
                        </TD>
                  </TR>
					<tr>
						<td colspan="2" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<TR>
								<TD colspan="2" valign="top" class="ContentArea"><!--	Start of Table for actual Data	-->
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>

										<td colspan="2" valign="top" class="ContentArea">
										<div id="panel2Header" style="position:relative;width:100% "><STRONG>Mandate
										Information</STRONG></div>
										<br />
										<fieldset
											style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

										<!--	Start of Table for actual Data	-->
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR id="stateRow">
												<TD width="24%" valign="top" height="20">&nbsp;<h:outputText
													id="stateCd" value="Jurisdiction " /></TD>
												<TD width="42%" valign="top" height="20">
												<!-- <DIV id="StateDiv" class="selectDivReadOnly"></DIV> -->
												<h:outputText id="state"
													value="#{productStructureBenefitMandatesBackingBean.stateCode}"></h:outputText>
												<h:inputHidden id="txtState"
													value="#{productStructureBenefitMandatesBackingBean.stateCode}"></h:inputHidden></TD>
												<td width="63%" height="20"><f:verbatim></f:verbatim></td>
											</TR>
											<tr>
												<td width="24%" height="20">&nbsp;<h:outputText id="MandateTypeStar"
													value="Mandate Category " /></td>
												<td width="42%" height="20"><h:outputText
													value="#{productStructureBenefitMandatesBackingBean.mandateCategory}"></h:outputText></td>

											</tr>
											<TR>
												<TD width="24%" height="20">&nbsp;<h:outputText id="CitationNumberStar"
													value="Citation Number " /></TD>
												<TD width="42%" height="20">
												<!-- <DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV> -->
												<h:outputText id="citationNumber"
													value="#{productStructureBenefitMandatesBackingBean.citationNumber}"></h:outputText>
												<h:inputHidden id="CitationNumberHidden"
													value="#{productStructureBenefitMandatesBackingBean.citationNumber}"></h:inputHidden>
												</TD>
											</TR>

											<TR>
												<TD width="24%" height="20">&nbsp;<h:outputText
													id="FundingArrangementStar" value="Funding Arrangement " /></TD>
												<TD width="42%" height="20">
												<DIV id="FundingArrangementDiv" class="selectDivReadOnly" style="border:0"></DIV>
												<h:inputHidden id="FundingArrangementHidden"
													value="#{productStructureBenefitMandatesBackingBean.fundingArrangement}"></h:inputHidden>

												</TD>
											</TR>

											<TR>
												<TD width="24%" height="20">&nbsp;<h:outputText id="EffectivenessStar"
													value="Effectiveness " /></TD>
												<td width="42%" height="20"><h:outputText
													value="#{productStructureBenefitMandatesBackingBean.effectiveness}"></h:outputText>

												</td>
											</TR>


											<TR>
												<TD width="24%" valign="top" height="20">&nbsp;<h:outputText
													id="descriptionStar" value="Text" /></TD>
												<TD width="42%" valign="top" height="20"><h:outputText
													value="#{productStructureBenefitMandatesBackingBean.text}"></h:outputText></TD>
												<td width="63%" height="20"><f:verbatim></f:verbatim></td>
											</TR>


											<TR>

											</TR>
											<TR>
												<TD width="221">&nbsp;</TD>
											</TR>
										</table>
										</fieldset>
										</td>
									</tr>
								</table>
								<!--	End of Page data	--></TD>
							</TR>
						</table>

						<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
							value="value1"></h:inputHidden> <h:inputHidden id="statHidden"
							value="#{productStructureBenefitMandatesBackingBean.stateCode}" />
						<h:commandLink id="hiddenLnk1"
							style="display:none; visibility: hidden;">

							<f:verbatim />
						</h:commandLink> <!-- End of hidden fields  --> <script>
	// copyHiddenToDiv_ewpd('benefitAdmnViewForm:txtState','StateDiv',2,2);
	formatTildaToCommaForStateCode('benefitAdmnViewForm:state');
	copyHiddenToDiv_ewpd('benefitAdmnViewForm:FundingArrangementHidden','FundingArrangementDiv',2,2); 
	// copyHiddenToDiv_ewpd('benefitAdmnViewForm:CitationNumberHidden','CitationNumberDiv',2,2); 
	formatTildaToCommaForStateCode('benefitAdmnViewForm:citationNumber');

	// displayState();
	function displayState(){
	var state = document.getElementById('benefitAdmnViewForm:statHidden').value;
	if(state=="" || state==null)
		stateRow.style.display='none';

	else
		stateRow.style.display='';

	}
	function formatTildaToCommaForStateCode(objName)
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
</script> <script>window.print();</script> </h:form>
	</BODY>
</f:view>
</HTML>
