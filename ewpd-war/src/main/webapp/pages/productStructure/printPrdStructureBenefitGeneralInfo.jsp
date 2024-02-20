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

<TITLE>Benefit General Information</TITLE>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><h:form styleClass="benefitForm" id="benefitForm">
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
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">

							<TABLE border="0" cellspacing="0" cellpadding="2"
								class="outputText">
								<TBODY>

									<TR>
										<h:inputHidden id="getValue"
											value="#{productStructureBenefitDefenitionBackingBean.printBenefitDefValues}"></h:inputHidden>
										<TD colspan="5">
										<TABLE border="0" cellspacing="0" cellpadding="3">
											<TBODY>
												<TR>
													<TD width="33%">
													<div id="panel2Header" class="tabTitleBar"
														style="position:relative;width:400% "><STRONG>General
													Information</STRONG></div>
													<br />
													</TD>
												</TR>
                                                <TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND><br>
                                      	<TABLE border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="153">&nbsp;<h:outputText value="Line of Business" /></TD>
													<TD width="194"><h:outputText id="txtLob"
														value="#{productStructureBenefitDefenitionBackingBean.lob}"></h:outputText></TD>
												</TR>
                                                <TR><TD width="153"><br></TD></TR>
												<TR>
													<TD width="153">&nbsp;<h:outputText value="Business Entity" /></TD>
													<TD width="194"><h:outputText id="txtBusinessEntity"
														value="#{productStructureBenefitDefenitionBackingBean.entity}"></h:outputText></TD>
												</TR>
                                                <TR><TD width="153"><br></TD></TR>
												<TR>
													<TD width="153">&nbsp;<h:outputText value="Business Group" /></TD>
													<TD width="194"><h:outputText id="txtBusinessGroup"
														value="#{productStructureBenefitDefenitionBackingBean.group}"></h:outputText></TD>
												</TR>
<!-- CARS START -->								<TR><TD width="153"><br></TD></TR>
												<TR>
													<TD width="153">&nbsp;<h:outputText value="Market Business Unit" /></TD>
													<TD width="194"><h:outputText id="txtMarketBusinessUnit"
														value="#{productStructureBenefitDefenitionBackingBean.marketBusinessUnit}"></h:outputText></TD>
												</TR>
<!-- CARS END -->
												</TABLE></FIELDSET></TD></TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Name" /></TD>
													<TD width="325"><h:outputText id="structureName"
														value="#{productStructureBenefitDefenitionBackingBean.minorHeading}" />
													<h:inputHidden id="structureNameHidden"
														value="#{productStructureBenefitDefenitionBackingBean.minorHeading}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:structureNameHidden','benefitForm:structureName',1); </SCRIPT>
													</TD>

												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Benefit Meaning" /></TD>
													<TD width="325"><h:outputText id="meaning"
														value="#{productStructureBenefitDefenitionBackingBean.minorHeading}" />
													<h:inputHidden id="meaningHidden"
														value="#{productStructureBenefitDefenitionBackingBean.minorHeading}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:meaningHidden','benefitForm:meaning',1); </SCRIPT>
													</TD>

												</TR>

												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Benefit Type" /></TD>
													<TD width="325"><h:outputText id="strType"
														value="#{productStructureBenefitDefenitionBackingBean.benefitType}" />
													<h:inputHidden id="strTypeHidden"
														value="#{productStructureBenefitDefenitionBackingBean.benefitType}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:strTypeHidden','benefitForm:strType',1); </SCRIPT>
													</TD>

												</TR>
												
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Benefit Category" /></TD>
													<TD width="325"><h:outputText id="benCategory"
														value="#{productStructureBenefitDefenitionBackingBean.benefitCategory}" />
													<h:inputHidden id="benCategoryHidden"
														value="#{productStructureBenefitDefenitionBackingBean.benefitCategory}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:benCategoryHidden','benefitForm:benCategory',1); </SCRIPT>
													</TD>

												</TR>

												<tr id="sub1" style="display:none;">
													<TD width="161"><h:outputText value="Mandate Type" /></TD>
													<TD width="325"><h:outputText id="mandateType"
														value="#{productStructureBenefitDefenitionBackingBean.mandateType}" />
													<h:inputHidden id="mandTypeHidden"
														value="#{productStructureBenefitDefenitionBackingBean.mandateType}">
													</h:inputHidden><SCRIPT>copyHiddenToInputText('benefitForm:mandTypeHidden','benefitForm:mandateType',1);</SCRIPT>
													</TD>
												</TR>
												
												<TR>
													<TD valign="top" width="161">&nbsp;&nbsp;<h:outputText value="Description" /></TD>
													<TD width="325"><h:outputText
														value="#{productStructureBenefitDefenitionBackingBean.description}">
													</h:outputText></TD>
												</TR>

                                                <TR>
													<TD valign="top" width="161">&nbsp;&nbsp;<h:outputText value="Benefit Rule ID" /></TD>
													<TD width="325"><h:outputText id="ruleId"
														value="#{productStructureBenefitDefenitionBackingBean.ruleId}"
														styleClass="" /> <h:inputHidden id="ruleIdHidden"
														value="#{productStructureBenefitDefenitionBackingBean.ruleId}">
													</h:inputHidden></TD>
												</TR>
			
												<TR>
													<TD valign="top" width="161">&nbsp;&nbsp;<h:outputText value="Tier Definition" /></TD>													
													<TD width="325">
													<h:outputText id="txtTier" 
															value="#{productStructureBenefitDefenitionBackingBean.tierProductStructure}"></h:outputText>
													</TD>
												</TR>

												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Term" /></TD>
													<TD width="325"><h:outputText id="txtTerm"
														value="#{productStructureBenefitDefenitionBackingBean.term}"></h:outputText></TD>
												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Qualifier" /></TD>
													<TD width="325"><h:outputText id="txtQualifier"
														value="#{productStructureBenefitDefenitionBackingBean.qualifier}"></h:outputText></TD>
												</TR>
												<TR>
													<TD valign="top" width="161">&nbsp;&nbsp;<h:outputText value="Provider Arrangement" /></TD>
													<TD width="325"><h:outputText id="txtProviderArrangement"
														value="#{productStructureBenefitDefenitionBackingBean.providerArrangement}"></h:outputText></TD>
												</TR>
												<TR>
													<TD valign="top" width="161">&nbsp;&nbsp;<h:outputText value="Data Type" /></TD>
													<TD width="325"><h:outputText id="txtDataType"
														value="#{productStructureBenefitDefenitionBackingBean.dataType}"></h:outputText></TD>
												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Version" /></TD>
													<TD width="325"><h:outputText id="versionId"
														value="#{productStructureBenefitDefenitionBackingBean.benefitVersion}" />
													<h:inputHidden id="benefitVersionHidden"
														value="#{productStructureBenefitDefenitionBackingBean.benefitVersion}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:benefitVersionHidden','benefitGeneralInfoForm:versionId',1); </SCRIPT>
													</TD>
												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Created By" /></TD>
													<TD width="325"><h:outputText id="createdBy"
														value="#{productStructureBenefitDefenitionBackingBean.createdBy}" />
													<h:inputHidden id="createdByHidden"
														value="#{productStructureBenefitDefenitionBackingBean.createdBy}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:createdByHidden','benefitForm:createdBy',1); </SCRIPT>
													</TD>
												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Created Date" /></TD>
													<TD width="325"><h:outputText id="creationDate"
														value="#{productStructureBenefitDefenitionBackingBean.creationDate}">
														<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
													</h:outputText> <h:outputText
														value="#{requestScope.timezone}"></h:outputText> <h:inputHidden
														id="creationDateHidden"
														value="#{productStructureBenefitDefenitionBackingBean.creationDate}">
														<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:creationDateHidden','benefitForm:creationDate',1); </SCRIPT>
													<h:inputHidden id="time" value="#{requestScope.timezone}">
													</h:inputHidden></TD>
												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Last Updated By" /></TD>
													<TD width="325"><h:outputText id="updatedBy"
														value="#{productStructureBenefitDefenitionBackingBean.updatedBy}" />
													<h:inputHidden id="updatedByHidden"
														value="#{productStructureBenefitDefenitionBackingBean.updatedBy}">
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:updatedByHidden','benefitForm:updatedBy',1); </SCRIPT>
													</TD>

												</TR>
												<TR>
													<TD width="161">&nbsp;&nbsp;<h:outputText value="Last Updated Date" /></TD>
													<TD width="325"><h:outputText id="updatedDate"
														value="#{productStructureBenefitDefenitionBackingBean.updatedDate}">
														<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
													</h:outputText> <h:outputText
														value="#{requestScope.timezone}"></h:outputText> <h:inputHidden
														id="updatedDateHidden"
														value="#{productStructureBenefitDefenitionBackingBean.updatedDate}">
														<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
													</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitForm:updatedDateHidden','benefitForm:updatedDate',1); </SCRIPT>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
					</table>
					<script>
		formatTildaToComma("benefitForm:txtLob");
		formatTildaToComma("benefitForm:txtBusinessEntity");
		formatTildaToComma("benefitForm:txtBusinessGroup");
		formatTildaToComma("benefitForm:txtMarketBusinessUnit");
		formatTildaToComma("benefitForm:txtTerm");
		formatTildaToComma("benefitForm:txtQualifier");
		formatTildaToComma("benefitForm:txtProviderArrangement");
		formatTildaToComma("benefitForm:txtDataType");
		//for tier definitions
		formatTilda("benefitForm:txtTier"); 

getStructureType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("benefitForm:strType");
		if(obj.value== "Mandate" || obj.value == "MANDATE")
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		}
	}
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
					<script>window.print();</script>
				</h:form></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
</HTML>

