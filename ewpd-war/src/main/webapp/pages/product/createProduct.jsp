<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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

<TITLE>Create Product</TITLE>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('productForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden1"
			value="#{productGeneralInformationBackingBean.hiddenInit}"></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Product Configuration >> Product >> Create");
    %> <jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{productGeneralInformationBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE width="98%">
								<TBODY>
									<TR>
										<TD colspan=3>
										<FIELDSET
											style="width:54%">
										<LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3" width="99%">
											<TR>
												<TD width="39%"><h:outputText id="lineOfBusiness"
													value="Line Of Business*"
													styleClass="#{productGeneralInformationBackingBean.lobInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','productForm:lineOfBusinessHidden',2,2);
																				return false;"
													tabindex="1">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="39%"><h:outputText id="businessEntity"
													value="Business Entity*"
													styleClass="#{productGeneralInformationBackingBean.entityInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="businessEntity"
													id="businessEntitButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','productForm:businessEntityHidden',2,2);
																				return false;"
													tabindex="2">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="39%"><h:outputText id="businessGroup"
													value="Business Group*"
													styleClass="#{productGeneralInformationBackingBean.groupInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="businessGroup"
													id="businessGroupButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','productForm:businessGroupHidden',2,2);
																				return false;"
													tabindex="3">
												</h:commandButton></TD>
											</TR>
<!-- CARS START -->							
											<TR>
												<TD width="39%"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit*"
													styleClass="#{productGeneralInformationBackingBean.marketBusinessUnitInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productGeneralInformationBackingBean.marketBusinessUnitDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="marketBusinessUnitDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="marketBusinessUnit"
													id="marketBusinessUnitButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnitDiv','productForm:marketBusinessUnitHidden',2,2);
																				return false;"
													tabindex="3">
												</h:commandButton></TD>
											</TR>
<!-- CARS END -->

										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<script type="text/javascript">
										function RSCustomInterface(tbElementName){
											this.tbName = tbElementName;
											this.getText = getText;
											this.setText = setText;
									
										function getText(){
											if(!document.getElementById(this.tbName)) {
												alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
												return '';
											} else return document.getElementById(this.tbName).value;
										}
										function setText(text){
											if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
										}
									}
									</script>

									<TR>
                                    <!-- <TD width="10"></TD> -->
									<TD width="21%">&nbsp;<h:outputText value="Name*"
											styleClass="#{productGeneralInformationBackingBean.nameInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%"><h:inputText styleClass="formInputField"
											id="productName_txt" maxlength="30"
											value="#{productGeneralInformationBackingBean.productName}"
											tabindex="4"></h:inputText></TD>
										<TD width="50%"></TD><!-- <f:verbatim></f:verbatim> -->
									</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="productForm:productName_txt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Name"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>	
<!--  prodcut faily removed Septmber relase-->							
									<TR id="proType1" style="display:none;">
										<!-- <TD width="6" height="30"></TD> -->
										<TD width="21%">&nbsp;<h:outputText id="productTyp"
											value="Product Type*"
											styleClass="#{productGeneralInformationBackingBean.requiredProductType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="27%"><h:selectOneMenu id="productType"
											styleClass="formInputField"
											value="#{productGeneralInformationBackingBean.productType}"
											onchange="getProductType();prodcutStructClean();" tabindex="6">
											<f:selectItems id="productTypeList"
												value="#{ReferenceDataBackingBeanCommon.entityTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>

									<TR id="proType2" style="display:none;">
										<!--<TD width="6" height="30"></TD>-->
										<TD width="21%"><h:outputText id="productTypeCopy"
											value="Product Type*"
											styleClass="#{productGeneralInformationBackingBean.requiredProductType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="27%"><h:inputText id="productType_copy"
												styleClass="formInputField" tabindex="7" readonly="true"
												value="#{productGeneralInformationBackingBean.productType}"></h:inputText> </TD>
									</TR>

									<tr id="sub1" style="display:none;">
										<!--<TD width="6" height="30"></TD>-->
										<TD width="21%">&nbsp;<h:outputText id="mandType"
											value="Mandate Type*"
											styleClass="#{productGeneralInformationBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="9" width="27%"><h:selectOneMenu id="mandateType"
											styleClass="formInputField"
											value="#{productGeneralInformationBackingBean.mandateType}"
											onchange="getMandateType();prodcutStructClean();"
											tabindex="8">
											<f:selectItems id="mandateTypeList"
												value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>

									<tr id="sub2" style="display:none;">
										<!--<TD width="6" height="30"></TD>-->
										<TD height="9" width="21%">&nbsp;<h:outputText id="state"
											value="Jurisdiction*"
											styleClass="#{productGeneralInformationBackingBean.requiredState  ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="hid_state"
											value="#{productGeneralInformationBackingBean.stateCode}"></h:inputHidden>
										<TD width=27%">
										<div id="stateDiv" class="selectDataDisplayDiv"></div>
										<SCRIPT> parseForDiv(document.getElementById('stateDiv'), document.getElementById('productForm:hid_state')); </SCRIPT>
										</TD>
										<TD width="50%"><h:commandButton alt="State" id="stateButton"
											image="../../images/select.gif"
											onclick="prodcutStructClean();getSelectedDomainReferenceData('../contractpopups/HeadQuarterstatePopup.jsp','productForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','stateDiv','productForm:hid_state',2,2,'State Code');
																					setRefDataUseFlag('productForm', 'hid_state', 'stateDiv');return false;"
											tabindex="9">
										</h:commandButton></TD>
									</TR>

									<TR id="sub3" style="display:none;">
										<TD width="10"></TD>
										<TD width="21%"><h:outputText id="stateCde1" value="Jurisdiction*" />
										</TD>
										<TD width="27%"><h:outputText id="FederalLabel" value="ALL"></h:outputText>
										</TD>
									</TR>

									

									<TR>
										<!-- <TD width="10"></TD> -->
										<TD width="21%">&nbsp;<h:outputText value="Description*"
											styleClass="#{productGeneralInformationBackingBean.descInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc"
											id="productDescription_txt"
											value="#{productGeneralInformationBackingBean.productDescription}"
											tabindex="11"></h:inputTextarea></TD>
										<TD width="50%"></TD><!--<f:verbatim></f:verbatim>-->
									</TR>

									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="productForm:productDescription_txt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Product Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>

                                    <TR>
										<!-- <TD width="10"></TD> -->
										<TD width="21%">&nbsp;<h:outputText value="Effective Date*"
											styleClass="#{productGeneralInformationBackingBean.effectiveDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%"><h:inputText styleClass="formInputField"
											id="effectiveDate_txt" maxlength="10" tabindex="12"
											value="#{productGeneralInformationBackingBean.effectiveDate}" />
                                        <BR class="brclass">
										<span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="50%"><A href="#"
											onclick="cal1.select('productForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1"
											title="cal1.select('productForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="13" /></A></TD>
                                    </TR>

									<TR>
										<!-- <TD width="10"></TD> -->
										<TD width="21%">&nbsp;<h:outputText value="Expiry Date*"
											styleClass="#{productGeneralInformationBackingBean.expiryDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="27%"><h:inputText styleClass="formInputField"
											id="expiryDate_txt" maxlength="10" tabindex="14"
											value="#{productGeneralInformationBackingBean.expiryDate}" />
                                        <BR class="brclass">
										<span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="459"><A href="#"
											onclick="cal1.select('productForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;"
											name="anchor2" id="anchor2"
											title="cal1.select(document.forms[0].productForm:expiryDate_txt,'anchor2','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="15" /> </A></TD>
                                       <TD width="63%"></TD>
									</TR>

									<TR>
										<!-- <TD width="10"></TD> -->
										<TD width="21%">&nbsp;<h:outputText id="productStructure"
											value="Product Structure*"
											styleClass="#{productGeneralInformationBackingBean.structureInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="productStructHidden"
											value="#{productGeneralInformationBackingBean.productStructDiv}"></h:inputHidden>
										<TD width="27%">
										<div id="productStructDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="50%"><h:commandButton alt="productStructure"
											id="productStructureButton" image="../../images/select.gif"
											onclick="productStructurePopup();return false;" tabindex="10">
										</h:commandButton></TD>
                                        <TD width="63%"></TD>
									</TR>


									<TR>
										<!-- <TD width="10"></TD> -->
										<TD width="12%"><!-- After clicking on this action it will be moved to the ../product/save.jsp page -->
										<h:commandButton value="Create" styleClass="wpdButton"
											id="createButton" tabindex="16" onclick="return runSpellCheck();">
										</h:commandButton></TD>
										
									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="copyHidden"
					value="#{productGeneralInformationBackingBean.copyFlag}" />
					<%--<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->
					<h:commandLink id="createProduct"
						style="hidden" action="#{productGeneralInformationBackingBean.saveGeneralInfo}">
					</h:commandLink>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>


<script language="javascript">
document.getElementById('productForm:productName_txt').focus(); //for onload default selection

	var i;
	var obj;
	obj = document.getElementById("productForm:productType");
	i= obj.selectedIndex;
var type = obj.value;
		if(type == 'MANDATE')
		// if(i== 1)
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
		}
	var i;
	var obj;
	obj = document.getElementById("productForm:mandateType");
	i= obj.selectedIndex;
var type = obj.value;
if(type == 'MANDATE')
	// 	if(i== 1 || i==3)
		{
		sub2.style.display='';
		sub3.style.display='none';		
		}
		else if(type == 'STANDARD')
		{
		sub2.style.display='';
		sub3.style.display='none';
		}
		else 
		{
		sub2.style.display='none';
		sub3.style.display='none';
		}
function getProductType()
	{
	var i;
	var obj;
	obj = document.getElementById("productForm:productType");
	i= obj.selectedIndex;
var type = obj.value;
		if(type == 'MANDATE')
		// if(i== 1)
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
	var i;
	var obj;
	obj = document.getElementById("productForm:mandateType");
	i= obj.selectedIndex;
		// i = 1 or 3 :If Extra Territorial or State 
		if(i== 1 || i==3)
		{
		sub2.style.display='';	
		sub3.style.display='none';
		}
		// i = 2 : Federal	
		else if(i == 2)
		{
		sub2.style.display='';
		sub3.style.display='none';
		}
		else 
		{
		sub2.style.display='none';
		sub3.style.display='none';
		}
	}


	// Space for page realated scripts
 copyHiddenToDiv_ewpd('productForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:marketBusinessUnitHidden','marketBusinessUnitDiv',2,2);  
// copyHiddenToDiv_ewpd('productForm:productFamHidden','productFamilyDiv',2,2);
 copyHiddenToDiv_ewpd('productForm:productStructHidden','productStructDiv',2,1);  

 function productStructurePopup(){
	ewpdModalWindow_ewpd(getProdStrPopupUrl('productForm:lineOfBusinessHidden','productForm:businessEntityHidden','productForm:businessGroupHidden','productForm:marketBusinessUnitHidden',
									 		'productForm:effectiveDate_txt','productForm:expiryDate_txt','productForm:productType','productForm:mandateType','productForm:hid_state','../product/productStructurePopup.jsp'+getUrl()+'?'),
						'productStructDiv','productForm:productStructHidden',2,1);
 }

function prodcutStructClean(){
document.getElementById("productForm:productStructHidden").value = "";
copyHiddenToDiv_ewpd('productForm:productStructHidden','productStructDiv',2,1); 
}
//appendToRefDataVariablesSelectedRefDataName('productFamHidden', 'productFamilyDiv'); 

var copyFlag = document.getElementById("productForm:copyHidden").value;
if(copyFlag=='true'){
	proType2.style.display='';	
	proType1.style.display='none';	
}else{
	proType2.style.display='none';	
	proType1.style.display='';	
}
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
	
		document.getElementById('productForm:createProduct').click();
}
</script>
</HTML>

