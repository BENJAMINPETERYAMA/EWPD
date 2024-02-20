<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/PrintContractBenefitCustomization.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Benefit Component Notes</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<tr><td>&nbsp; </td></tr>
									<TR>
										
					<TD colspan ="2">  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
									</TR>
							
			<TR>
				<TD><h:form styleClass="form" id="contractBenefitForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							

							<TD colspan="2" valign="top" class="ContentArea">
							


							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
							<h:inputHidden id="listGenerator"
								value="#{contractComponentGeneralInfoBackingBean.list}"></h:inputHidden>
								
									
							<table width="98%"  align="right" cellpadding="0" cellspacing="0" >
							<TBODY>
								<TR><td>
									<DIV id="noContractBenefits"
									     style="font-family:Verdana, Arial, Helvetica, sans-serif;
										 font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
										 background-color:#FFFFFF;">No Benefits Attached.
									</DIV>
								</td></TR>
								<TR>
									<TD>
									<DIV id="headerBenefits">
										<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc" id="resultHeaderTable">
											<TR>			
																				
												<TD width="90%" align="center"> <strong> <h:outputText value="Benefits"> </h:outputText> </strong> </td>
											</tr>
										</table>
									</DIV>	
									</TD>
								</TR>
								<tr>
									<td>
									<DIV class="popupDataTableDiv" id="popupDataTableDiv">
										<h:dataTable   cellspacing="1" rowClasses="dataTableEvenRow,dataTableOddRow"
											width="100%" id="contractTypeDataTable" 
											value="#{contractComponentGeneralInfoBackingBean.benefitList}"
											rendered = "#{contractComponentGeneralInfoBackingBean.benefitList != null}"
											var="singleValue" cellpadding="0">
										 	
										 	<h:column>
				                                <f:verbatim>&nbsp;</f:verbatim>
										 		<h:inputHidden value="#{singleValue.standardBenefitDesc}"></h:inputHidden>
												<h:outputText value="#{singleValue.standardBenefitDesc}"></h:outputText>
											</h:column>
										</h:dataTable>
									</DIV>	
									</td>
								</tr>
							</TBODY>
							</table>						
							</FIELDSET>
							<BR>


							<DIV id="dummyDiv" style="visibility:hidden"></DIV>

							<!--	End of Page data	--> <!-- Space for hidden fields --> 
							 <!-- End of hidden fields  --></TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
		
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBenefitCustomizationPrint" /></form>

<script language="JavaScript">	
	hideIfNoValue('popupDataTableDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('contractBenefitForm:contractTypeDataTable');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractBenefits').style.visibility = 'visible';
			document.getElementById('headerBenefits').style.visibility = 'hidden';

		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noContractBenefits').style.visibility = 'hidden';
			document.getElementById('headerBenefits').style.visibility = 'visible';
			setColumnWidth('contractBenefitForm:contractTypeDataTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');

		}
	}
</script>
<script>window.print();</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>


