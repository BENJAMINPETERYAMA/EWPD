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

<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Mandate Definition Create</TITLE>

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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>


</head>


<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/header.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><jsp:include page="../navigation/MenuComponent.jsp"></jsp:include>
				</TD>
			</TR>
			<TR>
				<TD>
				<TABLE width="100%" id="breadCumbTable" bgcolor="#7670B3">
					<TR>

						<TD height="16" valign="middle" bgcolor="#7670B3"
							class="breadcrumb">Product Configuration&gt;&gt; Benefit (MM
						deductible)&gt;&gt; Mandate Definition</TD>

					</TR>
				</TABLE>
				</TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="MandateDefinitionForm">
					<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD valign="top" class="leftPanel" width="273">
							<DIV class="treeDiv" style="height:445px; overflow:auto;"><!-- Space for Tree  Data	-->
							<SCRIPT language="JavaScript">
										
									</SCRIPT></DIV>


							</TD>
							<TD colspan="1" valign="top" class="ContentArea" width="963">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{MandateDefinitionBackingbean.validationMessages}" /></TD>
									</TR>
								</TBODY>
							</TABLE>




							<!-- Table containing Tabs -->



							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabNormal"><h:commandLink
												action="#{StandardBenifitEditBackingBean.loadStandardBenefit}"
												id="linkToStandardBenefit">
												<h:outputText value=" Standard Benefit" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{benefitDefinitionBackingBean.loadBenefitDefinition}"
												id="linkToStandardDefinition">
												<h:outputText value="Standard Definition" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Adj Benefit Mandates" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabNormal"><h:commandLink
												action="#{benefitMandateBackingBean.loadBenefitMandate}"
												id="linkToBenefitMandate">
												<h:outputText value="Non Adj Benefit Mandates" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellpadding="0" cellspacing="0" width="45%"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="2">&nbsp;</TD>
									</TR>
									<TR>
										<TD width="41%"><h:outputText id="effectiveDateStar"
											value="Effective Date *"
											styleClass="#{MandateDefinitionBackingbean.requiredEffectiveDate ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD width="51%"><h:inputText id="effectiveDate_txt"
											value="#{MandateDefinitionBackingbean.effectiveDate}"
											styleClass="formInputField" /> <SPAN class="dateFormat">(mm/dd/yyyy)</SPAN>
										</TD>

										<TD align="left" valign="top" width="8%"><A href="#"
											onclick="cal.select('MandateDefinitionForm:effectiveDate_txt','anchor1','MM/dd/yyyy');return false"
											title="cal.select(document.forms[0].MandateDefinitionForm:effectiveDate_txt,'anchor1','MM/dd/yyyy'); 
													 return false;"
											name="anchor1" id="anchor1" tabindex="10"> <IMG
											src="../../images/cal.gif" alt="Cal" border="0"> </A></TD>

									</TR>

									<TR>
										<TD width="41%"><h:outputText id="expiryDateStar"
											value="Expiry Date *"
											styleClass="#{MandateDefinitionBackingbean.requiredExpiryDate ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD width="51%"><h:inputText id="expiryDate_txt"
											value="#{MandateDefinitionBackingbean.expiryDate}"
											styleClass="formInputField" /> <SPAN class="dateFormat">(mm/dd/yyyy)</SPAN>
										</TD>

										<TD align="left" valign="top" width="8%"><A href="#"
											onclick="cal.select('MandateDefinitionForm:expiryDate_txt','anchor1','MM/dd/yyyy');return false"
											title="cal.select(document.forms[0].MandateDefinitionForm:expiryDate_txt,'anchor1','MM/dd/yyyy'); 
													 return false;"
											name="anchor1" id="anchor1" tabindex="10"> <IMG
											src="../../images/cal.gif" alt="Cal" border="0"> </A></TD>
									</TR>
									<TR>
										<TD width="41%"><h:outputText id="mandate_txt"
											value="Mandate *"
											styleClass="#{MandateDefinitionBackingbean.requiredMandate ? 'mandatoryError': 'mandatoryNormal' }" /></TD>

										<TD align="left" width="51%">
										<DIV id="MandatePopupDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="hidden_mandates"
											value="#{MandateDefinitionBackingbean.mandate}" /></TD>
										<TD><h:commandButton alt="Select" id="MandateButton"
											image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/mandatePopup.jsp'+getUrl(),'MandatePopupDiv','MandateDefinitionForm:hidden_mandates',1,1);return false;">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD colspan="3" height="10">&nbsp;</TD>
									</TR>
									<TR>
										<TD height="48" width="41%" valign="top"><h:outputText id="txtdescription"
											value="Description *"
											styleClass="#{MandateDefinitionBackingbean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
										<TD height="48" width="51%"><h:inputTextarea
											id="descriptiontxtid"
											styleClass="formTxtAreaField_mandateDescription"
											value="#{MandateDefinitionBackingbean.description}"></h:inputTextarea>
										</TD>
									</TR>
									<TR>
										<TD align="left" height="35" width="41%"><h:commandButton
											value="Save and Add" styleClass="wpdbutton"
											id="saveAndAddButton"
											disabled="#{MandateDefinitionBackingbean.disabledSaveAndAdd == true ? true : false}"
											action="#{MandateDefinitionBackingbean.saveAndAdd}"></h:commandButton>
										&nbsp;</TD>

										<TD align="left" height="35" width="51%">&nbsp;<h:commandButton
											value="Save" styleClass="wpdbutton" id="saveButton"
											disabled="#{MandateDefinitionBackingbean.disabledSave == true ? true : false}"
											action="#{MandateDefinitionBackingbean.save}"></h:commandButton>
										</TD>
									</TR>
									<TR>
										<TD colspan="3" height="19">&nbsp;</TD>
									</TR>

								</TBODY>
							</TABLE>

							<DIV id="panel2">
							<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">

								<tbody>

									<tr>
										<td>
										<DIV id="panel2Header" class="tabTitleBar"
											style="position:relative;width:100% "><h:outputText
											value="Associated Mandate Definitions"></h:outputText></DIV>
										</td>
									</tr>

									<tr>
										<td><h:outputText value="No Mandate Definitions Available"
											rendered="#{MandateDefinitionBackingbean.mandateList == null ? true :false}"
											styleClass="dataTableColumnHeader" /></td>
									</tr>

									<TR>
										<TD>
										<div id="resultHeaderDiv">
										<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
											id="resultHeaderTable" border="0" width="100%">
											<TBODY>
												<TR class="dataTableColumnHeader">
													<td align="left" width="20%"><h:outputText
														value="Description"></h:outputText></td>
													<td align="left" width="20%"><h:outputText
														value="Effective Date"></h:outputText></td>
													<td align="left" width="20%"><h:outputText
														value="Expiry Date"></h:outputText></td>
													<td align="left" width="20%"><h:outputText value="Mandate"></h:outputText>
													</td>
													<td align="left" width="20%"><h:outputText></h:outputText>
													</td>
												</TR>
											</TBODY>
										</TABLE>
										</div>
										</TD>
									</TR>
									<TR>
										<TD>
										<DIV id="searchResultdataTableDiv"
											style="height:100px; overflow: auto;"><!-- Search Result Data Table -->
										<h:dataTable
											rendered="#{MandateDefinitionBackingbean.mandateList != null}"
											rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
											styleClass="outputText" headerClass="dataTableHeader"
											id="mandateDefinitionsTable" var="singleValue"
											cellpadding="3" width="100%" cellspacing="1"
											value="#{MandateDefinitionBackingbean.mandateList}">
											<h:column>
												<h:outputText id="desc" value="#{singleValue.description}"></h:outputText>


											</h:column>
											<h:column>
												<h:outputText id="effDate"
													value="#{singleValue.effectiveDate}"></h:outputText>

											</h:column>
											<h:column>
												<h:outputText id="expDate" value="#{singleValue.expiryDate}"></h:outputText>

											</h:column>

											<h:column>
												<h:outputText id="mandate" value="#{singleValue.mandate}"></h:outputText>

											</h:column>
											<h:column>

												<h:commandButton styleClass="wpdbutton" id="basicEdit"
													image="../../images/edit.gif" alt="edit" value="Edit"
													onclick="getEdit();return false;"></h:commandButton>
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>

												<h:commandButton styleClass="wpdbutton" id="basicDelete"
													image="../../images/delete.gif" alt="delete" value="Delete"
													onclick="getDelete();return false;"></h:commandButton>

											</h:column>

										</h:dataTable></DIV>
										<h:commandLink id="mandateEdit"
											style="display:none; visibility: hidden;"
											action="#{MandateDefinitionBackingbean.edit}">
										</h:commandLink> <h:commandLink id="mandateDelete"
											style="display:none; visibility: hidden;"
											action="#{MandateDefinitionBackingbean.delete}">
										</h:commandLink></TD>
									</TR>

								</tbody>
							</TABLE>
							</DIV>
							</FIELDSET>
							<BR />




							<FIELDSET
								style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE align="right" border="0" cellspacing="0" cellpadding="0"
								width="100%">

								<TR>
									<TD><INPUT type="checkbox" id="checkall">Check In</TD>

									<TD align="left" width="127">
									<TABLE width="100%">
										<TR>
											<TD>State</TD>
											<TD>: New</TD>
										</TR>
										<TR>
											<TD>Status</TD>
											<TD>: Building</TD>
										</TR>
										<TR>
											<TD>Version</TD>
											<TD>: 1.0</TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>



							<TABLE>
								<tr>
									<td><h:commandButton id="button" value="Done"
										styleClass="wpdbutton" onclick="generalInfoFieldVal();"></h:commandButton>
									</td>
								</tr>
							</TABLE>
							</TD>
						</TR>
					</TABLE>






					<!--	End of Page data	-->


					<!-- Space for hidden fields -->

					<!-- End of hidden fields  -->





					<h:message for="saveButton"></h:message>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/pageFooter.jsp"%></TD>
			</TR>

		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="javascript">
		var cal = new CalendarPopup();
		cal.showYearNavigation();
		
initialize();

function initialize(){

	if(document.getElementById('MandateDefinitionForm:mandateDefinitionsTable') != null) {
	
		setColumnWidth('resultHeaderTable','20%:20%:20%:20%:20%');
		setColumnWidth('MandateDefinitionForm:mandateDefinitionsTable','20%:20%:20%:20%:20%');
	}else {
	
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
	}
}
function getEdit() {
		

		document.getElementById("MandateDefinitionForm:mandateEdit").click();
		return true;
}
function getDelete() {
		

		document.getElementById("MandateDefinitionForm:mandateDelete").click();
		return true;
}
copyHiddenToDiv_ewpd('MandateDefinitionForm:hidden_mandates','MandatePopupDiv',1,1); </SCRIPT>

</HTML>










