<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>


	
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandatePrint.java" --%><%-- /jsf:pagecode --%>
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

<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Print Benefit Mandate</TITLE>
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
</head>


		
<f:view>
<BODY>
	<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
		<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:4px;margin-right:-14px;padding-bottom:1px;padding-top:1px;width:99%">
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
		<tr><td ><DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Mandate Information</DIV>
						</td>
		</tr>
		<TR>
			<TD>
				<h:form styleClass="form" id="benefitMandatePrintForm">	
					<h:inputHidden id="printComponentBenefitMandate"
					value="#{benefitMandateBackingBean.printComponentBenefitMandate}" />				

					<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
						<TR>
							<TD colspan=3>
									<FIELDSET style="width:100%">

									
<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">

							<tr>
								<td>
									<div id ="stateCodeDiv">
									<TABLE width="211%">
										<TBODY>
											<tr>
												<td width="48%">&nbsp;<h:outputText
													
													id="StateCodeStar" value="Jurisdiction " /></td>
												<td width="52%">
												<table cellspacing="0" cellpadding="0" border="0"
													width="100%">
													<tr>
														<td width="88%">
														
															<!-- <DIV id="StateDiv" ></DIV> -->
															<h:outputText id="state"
																value="#{benefitMandateBackingBean.stateCode}"></h:outputText>
															<h:inputHidden id="txtState"
																value="#{benefitMandateBackingBean.stateCode}"></h:inputHidden>
														
														</TD>
														
													</tr>
												</table>
												</td>
											</tr>
										</TBODY>
									</TABLE>
									</div></td></tr>

							<tr>
								<td width="32%">&nbsp;<h:outputText id="MandateCategoryStar"
									value="Mandate Category " /></td>
								<td width="68%">
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td><h:inputTextarea styleClass="selectDivReadOnly"
											tabindex="5" readonly="true" style="border:0"
											value="#{benefitMandateBackingBean.mandateCategory}"></h:inputTextarea>
										</td>
										<td width="63%" align="right"></td>
									</tr>
								</table>
								</td>
							</tr>
							<TR>
								<TD width="32%">&nbsp;<h:outputText id="CitationNumberStar"
									value="Citation Number" /></TD>
								<TD width="68%">
								<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
									<TR>
										<TD width="44%">
										<!-- <DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV> -->

										<h:outputText id="CitationNumber"
											value="#{benefitMandateBackingBean.citationNumber}"></h:outputText>
										<h:inputHidden id="txtCitationNumber"
											value="#{benefitMandateBackingBean.citationNumber}"></h:inputHidden>
										</TD>

									</TR>
								</TABLE>
								</TD>
							</TR>


							<TR>
								<TD width="32%">&nbsp;<h:outputText id="FundingArrangementStar"
									value="Funding Arrangement " /></TD>
								<TD width="68%">
								<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
									<TR>
										<TD width="44%"><h:outputText id="txtFundingArrangement"
											value="#{benefitMandateBackingBean.fundingArrangement}"></h:outputText>
										</TD>

									</TR>
								</TABLE>
								</TD>
							</TR>

							<TR>
								<TD width="32%">&nbsp;<h:outputText id="EffectivenessStar"
									value="Effectiveness " /></TD>
								<td width="68%">
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td><h:inputTextarea styleClass="selectDivReadOnly"
											tabindex="5" readonly="true" style="border:0"
											value="#{benefitMandateBackingBean.effectiveness}"></h:inputTextarea>
										<td width="63%" align="right"></td>
									</tr>
								</table>
								</td>
							</TR>


							<TR>
								<TD valign="top" width="32%">&nbsp;<h:outputText
									id="descriptionStar" value="Text " /></TD>
								<TD valign="top" width="68%"><h:outputText
									id="txtDescription"
									value="#{benefitMandateBackingBean.text}" 
									></h:outputText></TD>
								<td width="63%"><f:verbatim></f:verbatim></td>
							</TR>



							<TR>
								<TD width="206">&nbsp;</TD>
							</TR>
						</TABLE>

						</FIELDSET>		
								<br/>
								
								
<!--	End of Page data	-->							

							</TD>
						</TR>
					</table>
			</h:form>
			</td>
		</tr>
	</table>
</BODY>
</f:view>

<script language="JavaScript">
	// Space for page realated scripts
	// copyHiddenToDiv_ewpd('benefitMandatePrintForm:txtState','StateDiv',2,2); 	
	formatTildaToCommaForStateCode("benefitMandatePrintForm:state");
	formatTildaToComma("benefitMandatePrintForm:txtFundingArrangement");
	formatTildaToComma("benefitMandatePrintForm:CitationNumber");
	
	// parseForDiv(document.getElementById('CitationNumberDiv'), document.getElementById('benefitMandatePrintForm:txtCitationNumber')); 

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

		for(i=0, n = values.length; i < n; i+=2)
		{
			formattedString += values[i+1] ;
			if(i < n-3)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}
function formatTildaToComma(objName)
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
			formattedString += values[i+1] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}
hideIfNoValue('stateCodeDiv','txtState');

function hideIfNoValue(divId, txtName){
		hiddenIdObj = document.getElementById('benefitMandatePrintForm:'+txtName);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj.value == 'null' || hiddenIdObj.value == 'undefined'){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}	
</script>
<script>
	window.print();
</script>

</HTML>
