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

<TITLE>Product Structure Detailed Information</TITLE>
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
		<h:form styleClass="form" id="productStructureGeneralInfoForm">
			<TABLE width="100%" cellpadding="0" cellspacing="0">
				<TR>
					<h:inputHidden id="getValue"
						value="#{productStructureGeneralInfoBackingBean.genInfoForPrint}"></h:inputHidden>
				</TR>
				<TR>
					<TD>

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
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
							<div id="panel2Header" class="tabTitleBar" style="position:relative;width:100% "><STRONG>General
										Information </STRONG></div>
							<TABLE border="0" cellspacing="0" cellpadding="2"
								class="outputText">
								<TBODY>
                                  <TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND><br>
                                      	<TABLE border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="130">&nbsp;<h:outputText value="Line of Business" /></TD>
										<TD width="194"><h:outputText id="txtLob"
											value="#{productStructureGeneralInfoBackingBean.lob}"></h:outputText>
										</TD>
									</TR>
                                    <TR><TD width="10"><br></TD></TR>
									<TR>
										<TD width="130">&nbsp;<h:outputText value="Business Entity" /></TD>
										<TD width="194"><h:outputText id="txtBusinessEntity"
											value="#{productStructureGeneralInfoBackingBean.entity}"></h:outputText>
										</TD>
									</TR>
                                    <TR><TD width="10"><br></TD></TR>
									<TR>
										<TD width="130">&nbsp;<h:outputText value="Business Group" /></TD>
										<TD width="194"><h:outputText id="txtBusinessGroup"
											value="#{productStructureGeneralInfoBackingBean.group}"></h:outputText>
										</TD>
									</TR>
                                    <TR><TD width="10"><br></TD></TR>
									<TR>
										<TD width="130">&nbsp;<h:outputText value="Market Business Unit" /></TD>
										<TD width="194"><h:outputText id="txtMarketBusinessUnit"
											value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:outputText>
										</TD>
									</TR>
                                    </TABLE></FIELDSET></TD></TR>

									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Name" /></TD>
										<TD width="239"><h:outputText id="structureName"
											value="#{productStructureGeneralInfoBackingBean.name}" /> <h:inputHidden
											id="nameHidden"
											value="#{productStructureGeneralInfoBackingBean.name}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:nameHidden','productStructureGeneralInfoForm:structureName',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Product Family" /></TD>	
										<TD width="239"><h:outputText id="productFamilyForView"
											value="#{productStructureGeneralInfoBackingBean.productFamilyForView}" />
										<h:inputHidden id="productFamilyForViewHidden"
											value="#{productStructureGeneralInfoBackingBean.productFamilyForView}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:productFamilyForViewHidden','productStructureGeneralInfoForm:productFamilyForView',1); </SCRIPT>
										</TD>							
									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Structure Type" /></TD>
										<TD width="239"><h:outputText id="strType"
											value="#{productStructureGeneralInfoBackingBean.structureType}" />
										<h:inputHidden id="strTypeHidden"
											value="#{productStructureGeneralInfoBackingBean.structureType}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:strTypeHidden','productStructureGeneralInfoForm:strType',1); </SCRIPT>
										</TD>
									</TR>
									<tr id="sub1" style="display:none;">
										<TD width="135"><h:outputText value="Mandate Type" /></TD>
										<TD width="239"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType"
											value="#{productStructureGeneralInfoBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandTypeHidden"
											value="#{productStructureGeneralInfoBackingBean.mandateType}">
										</h:inputHidden><SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:mandTypeHidden','productStructureGeneralInfoForm:mandateType',1);</SCRIPT>
										</TD>
									</TR>
									<tr id="sub2" style="display:none;">
										<TD width="135"><h:outputText value="Jurisdiction" /></TD>
										<TD width="239"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde"
											value="#{productStructureGeneralInfoBackingBean.stateCode}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="stateCdeHidden"
											value="#{productStructureGeneralInfoBackingBean.stateCode}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:stateCdeHidden','productStructureGeneralInfoForm:stateCde',2); </SCRIPT>
										</TD>
									</TR>
									<tr id="sub3" style="display:none;">
										<TD width="135"><h:outputText value="Jurisdiction" /></TD>
										<TD width="239"><h:outputText id="stateFed" value="ALL" /></TD>
									</TR>
									
									<TR>
										<TD width="135" valign="top">&nbsp;&nbsp;<h:outputText value="Description" /></TD>
										<TD width="239"><h:outputText id="structureDesc"
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"
											value="#{productStructureGeneralInfoBackingBean.description}">
										</h:outputText></TD>
									</TR>
                                    <TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Effective Date " /></TD>
										<TD width="239"><h:outputText id="effectiveDate"
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}" />
										<h:inputHidden id="effDateHidden"
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:effDateHidden','productStructureGeneralInfoForm:effectiveDate',1); </SCRIPT>
									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Expiry Date " /></TD>
										<TD width="239"><h:outputText id="expiryDate"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}" />
										<h:inputHidden id="expiryDateHidden"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:expiryDateHidden','productStructureGeneralInfoForm:expiryDate',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Created By" /></TD>
										<TD width="239"><h:outputText id="createdUser"
											value="#{productStructureGeneralInfoBackingBean.createdUser}" />
										<h:inputHidden id="createdUserHidden"
											value="#{productStructureGeneralInfoBackingBean.createdUser}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:createdUserHidden','productStructureGeneralInfoForm:createdUser',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Created Date" /></TD>
										<TD width="239"><h:outputText id="creationDate"
											value="#{productStructureGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{productStructureGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:creationDateHidden','productStructureGeneralInfoForm:creationDate',1); </SCRIPT>
										<h:inputHidden id="time" value="#{requestScope.timezone}">
										</h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Last Updated By" /></TD>
										<TD width="239"><h:outputText id="lastUpdatedUser"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
										<h:inputHidden id="updatedUserHidden"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:updatedUserHidden','productStructureGeneralInfoForm:lastUpdatedUser',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="135">&nbsp;&nbsp;<h:outputText value="Last Updated Date" /></TD>
										<TD width="239"><h:outputText id="lastUpdatedDate"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updatedDateHidden"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:updatedDateHidden','productStructureGeneralInfoForm:lastUpdatedDate',1); </SCRIPT>
										</TD>

									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
							<TABLE align="right" border="0" cellspacing="0" cellpadding="0"
								width="100%">
								<tr>
									<td width="4%"></td>
									<td align="left" width="71%"></td>
									<td align="left" width="25%">
									<table Width=100%>
										<tr>
										<TR>
											<TD>State</TD>
											<TD>:&nbsp;<h:outputText id="state"
												value="#{productStructureGeneralInfoBackingBean.state}"></h:outputText>
											</TD>
										</TR>
										<TR>
											<TD>Status</TD>
											<TD>:&nbsp;<h:outputText id="status"
												value="#{productStructureGeneralInfoBackingBean.status}"></h:outputText>
											</TD>
										</TR>
										<TR>
											<TD>Version</TD>
											<TD>:&nbsp;<h:outputText id="version"
												value="#{productStructureGeneralInfoBackingBean.version}"></h:outputText>
											</TD>
										</TR>
									</table>
									</td>
								</tr>
							</TABLE>
							</FIELDSET>
					</table>

					</td>
				</tr>
			</TABLE>

			
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>

							<td colspan="2" valign="top" class="ContentArea">

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">

							<div id="panel2Header" class="tabTitleBar" style="position:relative;width:100% "><strong>Associated
							Benefit Components</strong></div>
							<BR />
							<h:outputText value="No Benefit Components available."
								rendered="#{productStructureBenefitComponentBackingBean.selectBenefitComponents == null}"
								/>
							<table class="smallfont" id="resultsTable" width="100%"
								cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE id="headerTable" width="100%" border="0" cellpadding="2"
										cellspacing="1">
										<tr class="dataTableOddRow">
											<td><strong><h:outputText value="Sequence Number" /></strong></td>
											<td><strong><h:outputText value="Benefit Component" /></strong></td>
										</tr>
									</TABLE>
									</div>
									</td>
								</tr>
								<tr>
									<td bordercolor="#cccccc">
									<div id="searchResultdataTableDiv" style="overflow: auto;"><h:dataTable
										cellspacing="1" id="bComponentDataTable"
										rendered="#{productStructureBenefitComponentBackingBean.selectBenefitComponents != null}"
										value="#{productStructureBenefitComponentBackingBean.selectBenefitComponents}"
										rowClasses="dataTableOddRow" var="singleValue" cellpadding="3"
										width="100%">
										<h:column>
											<h:outputText id="id" value="#{singleValue.sequenceNum}" />
										</h:column>
										<h:column>
											<h:outputText id="name" value="#{singleValue.name}"></h:outputText>
											<h:inputHidden id="benefitComponentName"
												value="#{singleValue.name}"></h:inputHidden>
										</h:column>
									</h:dataTable></div>

									</td>
								</tr>
								<tr>
									<TD></TD>
								</tr>
								<tr>
									<TD></TD>
								</tr>
							</table>
							</fieldset>
							<br />
							</td>
						</tr>
					</table>
					<!--	End of Page data	-->
				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			
		</h:form>
		<script>
initialize();
//allElementForStructure("init");
function initialize(){
	if(document.getElementById('productStructureGeneralInfoForm:bComponentDataTable') != null) {
		setColumnWidth('headerTable','21%:51%');
		setColumnWidth('productStructureGeneralInfoForm:bComponentDataTable','21%:51%');
	}else {
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
	}
}
		formatTildaToComma("productStructureGeneralInfoForm:txtLob");
		formatTildaToComma("productStructureGeneralInfoForm:txtBusinessEntity");
		formatTildaToComma("productStructureGeneralInfoForm:txtBusinessGroup");
		formatTildaToComma("productStructureGeneralInfoForm:txtMarketBusinessUnit");
getStructureType();
getMandateType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("productStructureGeneralInfoForm:strType");
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
	obj = document.getElementById("productStructureGeneralInfoForm:mandateType");
	if( obj.value == "ET" || obj.value == "ST"){
				if(obj.value=="ST")
					document.getElementById("productStructureGeneralInfoForm:mandateType").value = "State";
				else
					document.getElementById("productStructureGeneralInfoForm:mandateType").value = "Extra-Territorial";

		sub2.style.display='';
		sub3.style.display='none';
	}else if(obj.value=="FED"){
		document.getElementById("productStructureGeneralInfoForm:mandateType").value = "Federal";
		sub3.style.display='';
		sub2.style.display='none';
	}else {
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
	}
	}

</script>
		<script>window.print();</script>
	</hx:scriptCollector>
	</BODY>
</f:view>
</HTML>
