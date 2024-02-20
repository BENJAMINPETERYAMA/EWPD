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

<TITLE>Benefit Component General Information</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="getValue"
				value="#{productStructureGeneralInfoBackingBean.benefitComponentGenInfo}"></h:inputHidden>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="productStructureBenefitCompForm">
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
					
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
						<div id="panel2Header" class="tabTitleBar" style="position:relative;width:100% "><STRONG>General Information</STRONG></div>
						<TABLE border="0" cellspacing="0" cellpadding="3" width="100%"
							class="outputText">
							<TBODY>
								<TR>
									<TD colspan="5">
                                    <FIELDSET style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND><FONT color="black">Business Domain</FONT></LEGEND><br>
                                      	<TABLE border="0" cellspacing="0" cellpadding="0">
								<tr>
									<TD width="176">&nbsp;<h:outputText value="Line of Business" /></TD>
									<TD width="194"><h:outputText id="txtLob"
										value="#{productStructureGeneralInfoBackingBean.lob}"></h:outputText></TD>
								</TR>
                                <TR><TD width="176"><br></TD></TR>
								<TR>
									<TD width="176">&nbsp;<h:outputText value="Business Entity" /></TD>
									<TD width="194"><h:outputText id="txtBusinessEntity"
										value="#{productStructureGeneralInfoBackingBean.entity}"></h:outputText>
									</TD>
								</TR>
                                <TR><TD width="176"><br></TD></TR>
								<TR>
									<TD width="176">&nbsp;<h:outputText value="Business Group" /></TD>
									<TD width="194"><h:outputText id="txtBusinessGroup"
										value="#{productStructureGeneralInfoBackingBean.group}"></h:outputText>
									</TD>
								</TR>
<!-- CARS START -->
                                <TR><TD width="176"><br></TD></TR>
								<TR>
									<TD width="176">&nbsp;<h:outputText value="Market Business Unit" /></TD>
									<TD width="194"><h:outputText id="txtMarketBusinessUnit"
										value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>
								</TR>
<!-- CARS START -->				</TABLE></FIELDSET></TD></TR>

								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Name" /></TD>
									<TD width="326"><h:outputText id="name"
										value="#{productStructureGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="nameHidden"
										value="#{productStructureGeneralInfoBackingBean.name}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:nameHidden','productStructureBenefitCompForm:name',1); </SCRIPT>
									</TD>
								</TR>
                                
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Component Type" /></TD>
									<TD width="326"><h:outputText id="strType"
										value="#{productStructureGeneralInfoBackingBean.componentType}" />
									<h:inputHidden id="strTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.componentType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:strTypeHidden','productStructureBenefitCompForm:strType',1); </SCRIPT>
									</TD>

								</TR>

								<tr id="sub1" style="display:none;">
									<TD width="122"><h:outputText value="Mandate Type" /></TD>
									<TD width="326"><h:outputText id="mandateType"
										value="#{productStructureGeneralInfoBackingBean.mandateType}" />
									<h:inputHidden id="mandTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.mandateType}">
									</h:inputHidden><SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:mandTypeHidden','productStructureBenefitCompForm:mandateType',1);</SCRIPT>
									</TD>
								</TR>
								<tr id="sub2" style="display:none;">
									<TD width="122"><h:outputText value="Jurisdiction" /></TD>
									<TD width="326"><h:inputTextarea styleClass="selectDivReadOnly"
										id="stateCde"
										value="#{productStructureGeneralInfoBackingBean.stateCode}"
										readonly="true" style="border:0"></h:inputTextarea><h:inputHidden
										id="stateCdeHidden"
										value="#{productStructureGeneralInfoBackingBean.stateCode}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:stateCdeHidden','productStructureBenefitCompForm:stateCde',1); </SCRIPT>
									</TD>
								</TR>
								<tr id="sub3" style="display:none;">
									<TD width="122"><h:outputText value="Jurisdiction" /></TD>
									<TD width="326"><h:outputText id="stateFed" value="ALL" /></TD>
								</TR>
								
								<TR>
								
								<TR>
									<TD valign="top" width="122">&nbsp;&nbsp;<h:outputText value="Description" /></TD>
									<TD width="326"><h:outputText id="desc"
										value="#{productStructureGeneralInfoBackingBean.description}" />
									<h:inputHidden id="descHidden"
										value="#{productStructureGeneralInfoBackingBean.description}">
									</h:inputHidden> 
									</TD>
								</TR>
                                <TR>

									<TD width="122">&nbsp;&nbsp;<h:outputText value="Benefit Rule ID" /></TD>
									<TD width="326"><h:outputText id="ruleId"
										value="#{productStructureGeneralInfoBackingBean.ruleId}"
										styleClass="" /> <h:inputHidden id="ruleIdHidden"
										value="#{productStructureGeneralInfoBackingBean.ruleId}">
									</h:inputHidden></TD>
								</TR>
                                <TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Effective Date" /></TD>
									<TD width="326"><h:outputText
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}"
										id="effDate" /> <h:inputHidden id="effDateHidden"
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:effDateHidden','productStructureBenefitCompForm:effDate',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Expiry Date" /></TD>
									<TD width="326"><h:outputText id="expiryDate"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}" />
									<h:inputHidden id="expiryDateHidden"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:expiryDateHidden','productStructureBenefitCompForm:expiryDate',1); </SCRIPT>

									</TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Version" /></TD>
									<TD width="326"><h:outputText id="bnftCmpntVersion"
										value="#{productStructureGeneralInfoBackingBean.bnftCmpntVersion}" />
									<h:inputHidden id="bnftCmpntVersionHidden"
										value="#{productStructureGeneralInfoBackingBean.bnftCmpntVersion}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:bnftCmpntVersionHidden','productStructureBenefitCompForm:bnftCmpntVersion',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Created By" /></TD>
									<TD width="326"><h:outputText id="createdUser"
										value="#{productStructureGeneralInfoBackingBean.createdUser}" />
									<h:inputHidden id="createdUserHidden"
										value="#{productStructureGeneralInfoBackingBean.createdUser}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:createdUserHidden','productStructureBenefitCompForm:createdUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Created Date" /></TD>
									<TD width="326"><h:outputText id="creationDate"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="creationDateHidden"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:creationDateHidden','productStructureBenefitCompForm:creationDate',1); </SCRIPT>
									<h:inputHidden id="time" value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Last Updated By" /></TD>
									<TD width="326"><h:outputText id="lastUpdatedUser"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="updatedUserHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:updatedUserHidden','productStructureBenefitCompForm:lastUpdatedUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Last Updated Date" /></TD>
									<TD width="326"><h:outputText id="lastUpdatedDate"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updatedDateHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:updatedDateHidden','productStructureBenefitCompForm:lastUpdatedDate',1); </SCRIPT>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						
						</fieldset>
						</TD>
						</TR>
				</table>
			</h:form></td>
		</tr>
	</table>
	<SCRIPT>
		formatTildaToComma("productStructureBenefitCompForm:txtLob");
		formatTildaToComma("productStructureBenefitCompForm:txtBusinessEntity");
		formatTildaToComma("productStructureBenefitCompForm:txtBusinessGroup");
		formatTildaToComma("productStructureBenefitCompForm:txtMarketBusinessUnit");

getStructureType();
getMandateType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("productStructureBenefitCompForm:strType");
		if(obj.value== "Mandate" || obj.value == "MANDATE" ||obj.value== "Mandate")
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
		}
	}

function getMandateType()
	{
	var obj;
	obj = document.getElementById("productStructureBenefitCompForm:mandateType");
	if( obj.value == "ET" || obj.value == "ST" || obj.value== "Extra-Territorial" || obj.value== "State"){
				if(obj.value=="ST")
					document.getElementById("productStructureBenefitCompForm:mandateType").value = "State";
				else
					document.getElementById("productStructureBenefitCompForm:mandateType").value = "Extra-Territorial";

		sub2.style.display='';
		sub3.style.display='none';
	}else if(obj.value=="Federal"){
		document.getElementById("productStructureBenefitCompForm:mandateType").value = "Federal";
		sub3.style.display='';
		sub2.style.display='none';
	}else {
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
	}
	}

</SCRIPT>
	<script>window.print();</script>
	</BODY>
</f:view>

</HTML>
