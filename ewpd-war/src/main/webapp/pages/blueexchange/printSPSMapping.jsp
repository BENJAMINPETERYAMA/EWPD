<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Print SPSMapping</TITLE>
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
	
		<h:inputHidden id="spsMappingViewHidden"
			value="#{spsMappingSearchBackingBean.spsMappingView}"></h:inputHidden>
		
		<TABLE cellpadding="0" cellspacing="0">	
			<tr><td>&nbsp;</td></tr>
			<tr>
						<td width="1000"><FIELDSET style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%"><h:outputText id="breadcrumb" 
		                              value="#{spsMappingSearchBackingBean.spsMappingPrintBreadCrumb}">
		                        </h:outputText> 
		                   </FIELDSET>
						</td>		
			</tr>
			<TR>
				<TD width="1000"><br/><h:form styleClass="form" id="spsMappingViewPrintForm">
							<FIELDSET
								style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%">
						
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR valign="top">
										<TD width="300"><h:outputText value="SPS Parameter ID" /></TD>
										<TD width="500">
											<h:outputText id="spsParameterValue"
											value="#{spsMappingSearchBackingBean.spsParameter}" />										
										   <h:inputHidden id="spsParameterValueHidden"
											value="#{spsMappingSearchBackingBean.spsParameter}"></h:inputHidden>
											
										</TD>
									
									</TR>

									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="SPS Description" /></TD>
										<TD width="500">
											<h:outputText id="spsParameterDesc"
											value="#{spsMappingSearchBackingBean.spsParameterDesc}" />												
										   <h:inputHidden id="spsParameterDescHidden"
											value="#{spsMappingSearchBackingBean.spsParameterDesc}"></h:inputHidden>
										</TD>										
									</TR>

									<TR>
										<TD width="300"><h:outputText id="productTyp"
											value="EB01 - Eligibility or Benefit Information" /></TD>
										<TD width="500">
											<h:outputText id="eb01Value"
											value="#{spsMappingSearchBackingBean.eb01Value}" />	
										   <h:inputHidden id="eb01ValueHidden"
											value="#{spsMappingSearchBackingBean.eb01Value}"></h:inputHidden>
											<h:outputText  value=" - " rendered="#{spsMappingSearchBackingBean.eb01Value != null}" />
											<h:outputText id="eb01ValueDesc"
											value="#{spsMappingSearchBackingBean.eb01ValueDesc}" />		
										   <h:inputHidden id="eb01ValueDescHidden"
											value="#{spsMappingSearchBackingBean.eb01ValueDesc}"></h:inputHidden>	
										</TD>
									</TR>
									
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="EB02 - Coverage Level Code"
											/></TD>
										<TD width="500">
											<h:outputText id="eb02Value"
											value="#{spsMappingSearchBackingBean.eb02Value}" />		
										   <h:inputHidden id="eb02ValueHidden"
											value="#{spsMappingSearchBackingBean.eb02Value}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.eb02Value != null}" />
											<h:outputText id="eb02ValueDesc"
											value="#{spsMappingSearchBackingBean.eb02ValueDesc}" />		
										   <h:inputHidden id="eb02ValueDescHidden"
											value="#{spsMappingSearchBackingBean.eb02ValueDesc}"></h:inputHidden>	
										</TD>
									</TR>
                                    <TR valign="top" height="20">
										<TD width="300"><h:outputText value="EB06  - Time Period Qualifier"
											/></TD>
										<TD width="500">
											<h:outputText id="eb06Value"
											value="#{spsMappingSearchBackingBean.eb06Value}" />	
										   <h:inputHidden id="eb06ValueHidden"
											value="#{spsMappingSearchBackingBean.eb06Value}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.eb06Value != null}" />
											<h:outputText id="eb06ValueDesc"
											value="#{spsMappingSearchBackingBean.eb06ValueDesc}" />		
										   <h:inputHidden id="eb06ValueDescHidden"
											value="#{spsMappingSearchBackingBean.eb06ValueDesc}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="EB09   - Quantity Qualifier"
											/></TD>
										<TD width="500">
											<h:outputText id="eb09Value"
											value="#{spsMappingSearchBackingBean.eb09Value}" />		
										   <h:inputHidden id="eb09ValueHidden"
											value="#{spsMappingSearchBackingBean.eb09Value}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.eb09Value != null}" />
											<h:outputText id="eb09ValueDesc"
											value="#{spsMappingSearchBackingBean.eb09ValueDesc}" />		
										   <h:inputHidden id="eb09ValueDescHidden"
											value="#{spsMappingSearchBackingBean.eb09ValueDesc}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="HSD1  - Quantity Qualifier"
											/></TD>
										<TD width="500">
											<h:outputText id="hsd1Value"
											value="#{spsMappingSearchBackingBean.hsd1Value}" />	
										   <h:inputHidden id="hsd1ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd1Value}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.hsd1Value != null}"/>
											<h:outputText id="hsd1ValueDesc"
											value="#{spsMappingSearchBackingBean.hsd1ValueDesc}" />		
										   <h:inputHidden id="hsd1ValueDescHidden"
											value="#{spsMappingSearchBackingBean.hsd1ValueDesc}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="HSD2      - Benefit Quantity"
											/></TD>
										<TD width="500">
											<h:outputText id="hsd2Value"
											value="#{spsMappingSearchBackingBean.hsd2Value}" />	
										   <h:inputHidden id="hsd2ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd2Value}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="275"><h:outputText value="HSD3    - Unit or Basis for measurement Code"
											styleClass="" /></TD>
										<TD width="150">
											<h:outputText id="hsd3Value"
											value="#{spsMappingSearchBackingBean.hsd3Value}" />	
										   <h:inputHidden id="hsd3ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd3Value}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.hsd3Value != null}"/>
											<h:outputText id="hsd3ValueDesc"
											value="#{spsMappingSearchBackingBean.hsd3ValueDesc}" />		
										   <h:inputHidden id="hsd3ValueDescHidden"
											value="#{spsMappingSearchBackingBean.hsd3ValueDesc}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="HSD4      - Sample Selection Modulus"
											/></TD>
										<TD width="500">
											<h:outputText id="hsd4Value"
											value="#{spsMappingSearchBackingBean.hsd4Value}" />		
										   <h:inputHidden id="hsd4ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd4Value}"></h:inputHidden>
									</TD>
										
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="HSD5  - Time Period Qualifier"
											/></TD>
										<TD width="500">
											<h:outputText id="hsd5Value"
											value="#{spsMappingSearchBackingBean.hsd5Value}" />		
										   <h:inputHidden id="hsd5ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd5Value}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.hsd5Value != null}"/>
											<h:outputText id="hsd5ValueDesc"
											value="#{spsMappingSearchBackingBean.hsd5ValueDesc}" />		
										   <h:inputHidden id="hsd5ValueDescHidden"
											value="#{spsMappingSearchBackingBean.hsd5ValueDesc}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="HSD6      - Number of Periods"
											/></TD>
										<TD width="500">
											<h:outputText id="hsd6Value"
											value="#{spsMappingSearchBackingBean.hsd6Value}" />	
										   <h:inputHidden id="hsd6ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd6Value}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="Accumulator SPS ID"
											/></TD>
										<TD width="500">
											<h:outputText id="accumulatorSPSID_text"
											value="#{spsMappingSearchBackingBean.accumulatorSPSID}" />	
										   <h:inputHidden id="accumulatorSPSID_Hidden"
											value="#{spsMappingSearchBackingBean.accumulatorSPSDesc}"></h:inputHidden>
											<h:outputText value=" - " rendered="#{spsMappingSearchBackingBean.accumulatorSPSID != null}"/>
											<h:outputText id="accumulatorSPSDesc"
											value="#{spsMappingSearchBackingBean.accumulatorSPSDesc}" />		
										   <h:inputHidden id="accumulatorSPSDescHidden"
											value="#{spsMappingSearchBackingBean.accumulatorSPSDesc}"></h:inputHidden>
										</TD>
									</TR>
									<TR>
										<TD width="275"><h:outputText value="Created By" /></TD>
										<TD width="150">
											<h:outputText id="createdUser"
											value="#{spsMappingSearchBackingBean.createdUser}" />													
										   <h:inputHidden id="createdUserHidden"
											value="#{spsMappingSearchBackingBean.createdUser}"></h:inputHidden>	
										</TD>

									</TR>
									
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="Created Date" /></TD>
										<TD width="500">
											<h:outputText id="createdDate"
											value="#{spsMappingSearchBackingBean.createdDate}" >	
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />										
										</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										   <h:inputHidden id="createdDateHidden"
											value="#{spsMappingSearchBackingBean.createdDate}"></h:inputHidden>											
										</TD>

									</TR>
									<TR>
										<TD width="275"><h:outputText value="Last Updated By" /></TD>
										<TD width="150">
											<h:outputText id="lastChangedUser"
											value="#{spsMappingSearchBackingBean.lastChangedUser}" />													
										   <h:inputHidden id="lastChangedUserHidden"
											value="#{spsMappingSearchBackingBean.lastChangedUser}"></h:inputHidden>	
										</TD>

									</TR>
								<TR valign="top" height="20">
										<TD width="300"><h:outputText value="Last Updated Date" /></TD>
										<TD width="500">
											<h:outputText id="lastChangedDate"
											value="#{spsMappingSearchBackingBean.lastChangedDate}" >
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />													
											</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										   <h:inputHidden id="lastChangedDateHidden"
											value="#{spsMappingSearchBackingBean.lastChangedDate}"></h:inputHidden>	
										</TD>

									</TR>
									
								</TBODY>
							</TABLE>
					</FIELDSET>
						</TD>
						</TR>				
					</TABLE>
				</h:form>				
	</BODY>
</f:view>
<script>
window.print();
</script>
</HTML>
