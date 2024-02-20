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

<TITLE>Create Contract</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('contractForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpReq.setAttribute("breadCrumbText",
				"Contract Development >> Contract >> Create");
	%> <jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv" ><!-- Space for Tree  Data	-->

							</DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{contractBasicInfoBackingBean.validationMessages}"></w:message>
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
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<td align="left" class="sectionheading"><h:outputText
											value="Basic Information"></h:outputText></td>
									</TR>
									<TR>

										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:400">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="275"><h:outputText id="lineOfBusiness"
													value="Line Of Business*"
													styleClass="#{contractBasicInfoBackingBean.lobInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{contractBasicInfoBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="160">
												<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="123"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton" image="../../images/select.gif"
													onclick="clearProduct();ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','contractForm:lineOfBusinessHidden',2,2,'contractIdDiv','contractForm:contractIdHidden');return false;"
													tabindex="1">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="275"><h:outputText id="businessEntity"
													value="Business Entity*"
													styleClass="#{contractBasicInfoBackingBean.entityInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{contractBasicInfoBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="160">
												<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="123"><h:commandButton alt="businessEntity"
													id="businessEntityButton" image="../../images/select.gif"
													onclick="clearProduct();ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','contractForm:businessEntityHidden',2,2,'contractIdDiv','contractForm:contractIdHidden');
																				return false;"
													tabindex="2">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="275"><h:outputText id="businessGroup"
													value="Business Group*"
													styleClass="#{contractBasicInfoBackingBean.groupInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{contractBasicInfoBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="160">
												<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="123"><h:commandButton alt="businessGroup"
													id="businessGroupButton" image="../../images/select.gif"
													onclick="clearProduct();ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','contractForm:businessGroupHidden',2,2,'contractIdDiv','contractForm:contractIdHidden');
																				return false;"
													tabindex="3">
												</h:commandButton></TD>
											</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
											<TR>
												<TD width="275"><h:outputText id="MarketBusinessUnit"
													value="Market Business Unit*"
													styleClass="#{contractBasicInfoBackingBean.requiredMarketBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{contractBasicInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="160">
												<DIV id="marketBusinessUnit" class="selectDataDisplayDiv"></DIV>

												</TD>
												<TD width="123"><h:commandButton alt="select"
													id="MarketBusinessUnitButton" image="../../images/select.gif"
													onclick="clearProduct();ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnit','contractForm:marketBusinessUnitHidden',2,2,'contractIdDiv','contractForm:contractIdHidden');
																				return false;"
													tabindex="4">
												</h:commandButton></TD>
											</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>

									<TR id="contractImgRow" style="display:">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="contractId"
											value="Contract ID"
											styleClass="#{contractBasicInfoBackingBean.contractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="contractIdHidden"
											value="#{contractBasicInfoBackingBean.contractIdDiv}"></h:inputHidden>
										<TD width="26%">
										<div id="contractIdDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="301"><h:commandButton alt="Reserved Contract Id"
											id="contractIdButton" image="../../images/select.gif"
											onclick="getReservedContractId();return false;" tabindex="4">
										</h:commandButton></TD>

									</TR>

									<TR>
										<TD height="25" width="23%">&nbsp;&nbsp;<h:outputText
											id="contractType" value="Contract Type*"
											styleClass="#{contractBasicInfoBackingBean.contractTypeInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD height="25" width="26%"><h:selectOneMenu
											id="contractType_txt" styleClass="formInputField"
											tabindex="7"
											value="#{contractBasicInfoBackingBean.contractType}"
											onchange="hideBaseContract();" tabindex="5">
											<f:selectItems id="contractTypeList"
												value="#{ReferenceDataBackingBeanCommon.contractTypeListForCombo}" />
										</h:selectOneMenu></TD>
									</TR>
									<!-- Changes made for STANDARD Contract -->
									<TR id="baseContRowWithoutManditory" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText
											id="baseContractIdStandard" value="Base Contract Id" /></TD>
										<h:inputHidden id="baseContractIdStandardHidden"
											value="#{contractBasicInfoBackingBean.baseContractIdDivNonMaditory}"></h:inputHidden>
										<TD width="178">
										<div id="baseContractIdStandardDiv"
											class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="301"><h:commandButton alt="baseContractId"
											id="baseContractIdStandardButton"
											image="../../images/select.gif"
											onclick="baseContractPopupNonMaditory();
																				return false;"
											tabindex="6">
										</h:commandButton></TD>
									</TR>
									<TR id="baseContEfftDateRowWithoutManditory"
										style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractDt1"
											value="Base Effective Date"
											styleClass="#{contractBasicInfoBackingBean.baseContractDtStandardInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="baseContractDtStandardHidden"
											value="#{contractBasicInfoBackingBean.baseContractDtDivNonMaditory}"></h:inputHidden>
										<TD width="178">
										<div id="baseContractDtStandardDiv"
											class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="301"><h:commandButton alt="Effective Date"
											id="baseContractDtStandardButton"
											image="../../images/select.gif"
											onclick="popupactionNonMaditory();
																				return false;"
											tabindex="7">
										</h:commandButton></TD>
									</TR>
									<!-- Changes made for STANDARD Contract -->
									<TR id="baseContRow" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractId"
											value="Base Contract Id*"
											styleClass="#{contractBasicInfoBackingBean.baseContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="baseContractIdHidden"
											value="#{contractBasicInfoBackingBean.baseContractIdDiv}"></h:inputHidden>
										<TD width="178">
										<div id="baseContractIdDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="301"><h:commandButton alt="baseContractId"
											id="baseContractIdButton" image="../../images/select.gif"
											onclick="baseContractPopup();
																				return false;"
											tabindex="6">
										</h:commandButton></TD>
									</TR>
									<TR id="baseContEfftDateRow" style="display:none;">

										<TD width="210">&nbsp;&nbsp;<h:outputText id="baseContractDt"
											value="Base Effective Date*"
											styleClass="#{contractBasicInfoBackingBean.baseContractDtInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="baseContractDtHidden"
											value="#{contractBasicInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
										<TD width="178">
										<div id="baseContractDtDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="301"><h:commandButton alt="Effective Date"
											id="baseContractDtButton" image="../../images/select.gif"
											onclick="popupaction();
																				return false;"
											tabindex="7">
										</h:commandButton></TD>
									</TR>
									<TR id="headQuaterStateRow" style="display:none;">
										<TD valign="top" width="210">&nbsp; <h:outputText id="head"
											value="Head Quarters State*"
											styleClass="#{contractBasicInfoBackingBean.requiredHeadQuarters ? 'mandatoryError': 'mandatoryNormal'}">
										</h:outputText></TD>

										<TD width="178">
										<DIV id="headQuartersStateDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="headQuarters"
											value="#{contractBasicInfoBackingBean.headQuartersState}"></h:inputHidden>
										</TD>


										<TD width="301"><h:commandButton alt="Select"
											id="headQuartersStateButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="clearProduct();getSelectedDomainReferenceData('../contractpopups/HeadQuarterStatePopupForContract.jsp','contractForm','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','headQuartersStateDiv','contractForm:headQuarters',2,1,'State Code');setRefDataUseFlag('contractForm', 'headQuarters', 'headQuartersStateDiv'); return false;"
											tabindex="8"></h:commandButton></TD>
									</TR>
									<TR id="productRow" style="display:none;">
										<TD valign="top" width="210">&nbsp; <h:outputText id="product"
											value="Product*"
											styleClass="#{contractBasicInfoBackingBean.requiredProduct ? 'mandatoryError': 'mandatoryNormal'}">
										</h:outputText></TD>
										<TD width="178">
										<DIV id="productDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtProduct"
											value="#{contractBasicInfoBackingBean.product}"></h:inputHidden>
										</TD>
										<TD width="301"><h:commandButton alt="Select"
											id="productButton" image="../../images/select.gif"
											style="cursor: hand" onclick="productPopup();return false;"
											tabindex="9"></h:commandButton></TD>


									</TR>
									<TR>

										<TD height="25" width="210">&nbsp;&nbsp;<h:outputText
											id="groupSizeTxt" value="Group Size*"
											styleClass="#{contractBasicInfoBackingBean.groupSizeInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="groupSizeHidden"
											value="#{contractBasicInfoBackingBean.groupSizeDiv}"></h:inputHidden>
										<TD width="178">
										<div id="groupSizeDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="301"><h:commandButton alt="Group Size"
											id="groupSizeButton" image="../../images/select.gif"
											onclick="getSelectedDomainReferenceData('../contractpopups/groupSize.jsp','contractForm','marketBusinessUnitHidden','lineOfBusinessHidden','businessEntityHidden','businessGroupHidden','groupSizeDiv','contractForm:groupSizeHidden',2,1,'Group Size');setRefDataUseFlag('contractForm', 'groupSizeHidden', 'groupSizeDiv');
																				return false;"
											tabindex="10">
										</h:commandButton></TD>
									</TR>
									<TR valign="top">

										<TD width="23%">&nbsp;&nbsp;<h:outputText
											value="Effective Date*"
											styleClass="#{contractBasicInfoBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="26%"><h:inputText styleClass="formInputField"
											id="startDate_txt" maxlength="10"
											value="#{contractBasicInfoBackingBean.startDate}"
											onkeypress="clearProduct();" tabindex="11" /> <span
											class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="301"><A href="#"
											onclick="clearProduct();cal1.select('contractForm:startDate_txt','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1"
											title="cal1.select('contractForm:startDate_txt','anchor1','MM/dd/yyyy'); return false;"
											tabindex="12"> <h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" /> </A></TD>
									</TR>
									<TR valign="top">

										<TD width="23%">&nbsp;&nbsp;<h:outputText value="Expiry Date"
											styleClass="#{contractBasicInfoBackingBean.endDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="26%"><h:outputText id="expiryDate_txt"
											value="12/31/9999" /></TD>

									</TR>
									<TR valign="top">

										<TD width="23%">&nbsp;&nbsp;<h:outputText value="Termed Contract ID"/>
										</TD>
										<TD width="26%"><div id="termedcntDiv" class="selectDataDisplayDiv"></div>
												<h:inputHidden id="termedContractId"
													value="#{contractBasicInfoBackingBean.termedContractId}"/>
												
													
										</TD>
										<td><h:commandButton alt="" image="../../images/select.gif" 
													onclick="loadContactIdSearchPopup('searchContractId','Contract Id',
                                                         'Contract Id Popup','termedcntDiv','contractForm:termedContractId','effectiveDateDiv','contractForm:tempDateHidden'); return false;" />
													<div id="effectiveDateDiv" style="display:none">
													<h:inputHidden id="tempDateHidden"></h:inputHidden>
										</td>

									</TR>
									<TR>

										<TD width="210"><!-- After clicking on this action it will be moved to the ../product/save.jsp page -->
										&nbsp; <h:commandButton value="Create" id="createButton"
											styleClass="wpdButton" tabindex="13"
											action="#{contractBasicInfoBackingBean.saveBasicInfo}">
										</h:commandButton></TD>
										<TD width="178"><f:verbatim></f:verbatim></TD>
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->

					<%--<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
	document.getElementById('contractForm:contractIdButton').focus();	
	// Space for page realated scripts 

 copyHiddenToDiv_ewpd('contractForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:marketBusinessUnitHidden','marketBusinessUnit',2,2); 
 copyHiddenToDiv_ewpd('contractForm:contractIdHidden','contractIdDiv',2,1);
 copyHiddenToDiv_ewpd('contractForm:baseContractIdHidden','baseContractIdDiv',2,1);  
 copyHiddenToDiv_ewpd('contractForm:baseContractDtHidden','baseContractDtDiv',2,1); 
 copyHiddenToDiv_ewpd('contractForm:groupSizeHidden','groupSizeDiv',2,1);
 copyHiddenToDiv_ewpd('contractForm:headQuarters','headQuartersStateDiv',2,1);
 copyHiddenToDiv_ewpd('contractForm:txtProduct','productDiv',2,2); 
 copyHiddenToDiv_ewpd('contractForm:baseContractIdStandardHidden','baseContractIdStandardDiv',2,1);
 copyHiddenToDiv_ewpd('contractForm:baseContractDtStandardHidden','baseContractDtStandardDiv',2,1);
 copyHiddenToDiv_ewpd('contractForm:termedContractId','termedcntDiv',2,1); 
// passing 0 to the getBaseContractPopupUrl because same backingBean using for contract create and contract migration.
function baseContractPopup(){
		ewpdModalWindow_ewpd(getBaseContractPopupUrl('contractForm:lineOfBusinessHidden','contractForm:businessEntityHidden','contractForm:businessGroupHidden','contractForm:marketBusinessUnitHidden','0',
										 		'../contractpopups/searchBaseContractCode.jsp'+getUrl()+'?'),
							'baseContractIdDiv','contractForm:baseContractIdHidden',2,1,'baseContractDtDiv','contractForm:baseContractDtHidden');			
}
// passing 0 to the getBaseContractPopupUrl because same backingBean using for contract create and contract migration.
function baseContractPopupNonMaditory(){
		ewpdModalWindow_ewpd(getBaseContractPopupUrl('contractForm:lineOfBusinessHidden','contractForm:businessEntityHidden','contractForm:businessGroupHidden','contractForm:marketBusinessUnitHidden','0',
										 		'../contractpopups/searchBaseContractCode.jsp'+getUrl()+'?'),
							'baseContractIdStandardDiv','contractForm:baseContractIdStandardHidden',2,1,'baseContractDtStandardDiv','contractForm:baseContractDtStandardHidden');	
	baseContEfftDateRowWithoutManditory.style.display = '';
}

 function getBaseContractPopupUrl(lobId,beId,bgId,mbuId,productParentSysId,url){
   
	var lob = getObj(lobId).value;
	var be = getObj(beId).value;
	var bg = getObj(bgId).value;
	var mbu = getObj(mbuId).value;
    
    
	var popupUrl = url;
	popupUrl += 'lob=' + escape(lob) + '&be=' + escape(be) + '&bg=' + escape(bg)+ '&mbu=' + escape(mbu) + '&productParentSysId=' + escape(productParentSysId) + '&temp=' + Math.random();
	return popupUrl;
 }
	
function hideBaseContract()
{
var selectObj = getObj('contractForm:contractType_txt');    // Get the ComboBox for contract Type
var selIndex = selectObj.selectedIndex; 
                 // Get the Selected index in contract type
   var selectItem = selectObj[selIndex];                       // Get the item object which is selected in Combo.
    var baseContRow = getObj('baseContRow');                    // Get the Table row object which contain base contract.
  if(selectItem.value != 'STANDARD') {                           // unhide if the selected value is 'STANDARD'
            baseContRowWithoutManditory.style.display='none';
			baseContEfftDateRowWithoutManditory.style.display='none';
    }  else{ 
			baseContRowWithoutManditory.style.display='';
			baseContEfftDateRowWithoutManditory.style.display = '';
    }
  if(selectItem.value != 'CUSTOM') {                           // unhide if the selected value is 'CUSTOM'
            baseContRow.style.display='none';
			baseContEfftDateRow.style.display='none';
    }  else{ 
            baseContRow.style.display='';
			baseContEfftDateRow.style.display='';
    }
	if(selectItem.value != 'MANDATE'){
		headQuaterStateRow.style.display='none';
		productRow.style.display='none';
		
		
	}
		else{

		headQuaterStateRow.style.display='';
		productRow.style.display='';
		
	}
	if(selectItem.value == 'MANDATE'){
	contractImgRow.style.display = 'none';
	}else{
	contractImgRow.style.display =   '';
	}

}
var selectObj = getObj('contractForm:contractType_txt');    // Get the ComboBox for contract Type
var selIndex = selectObj.selectedIndex; 
                 // Get the Selected index in contract type
   var selectItem = selectObj[selIndex];                       // Get the item object which is selected in Combo.
    var baseContRow = getObj('baseContRow');                    // Get the Table row object which contain base contract.
  if(selectItem.value != 'STANDARD') {                           // unhide if the selected value is 'STANDARD'
            baseContRowWithoutManditory.style.display='none';
			baseContEfftDateRowWithoutManditory.style.display='none';
    }  else{			 
			baseContRowWithoutManditory.style.display='';
			baseContEfftDateRowWithoutManditory.style.display = '';
    }
  if(selectItem.value != 'CUSTOM') {                           // unhide if the selected value is 'CUSTOM'
            baseContRow.style.display='none';
			baseContEfftDateRow.style.display='none';
    }  else{ 
            baseContRow.style.display='';
			baseContEfftDateRow.style.display='';
    }
	if(selectItem.value != 'MANDATE'){
		headQuaterStateRow.style.display='none';
		productRow.style.display='none';
	}
		else{
		headQuaterStateRow.style.display='';
		productRow.style.display='';
	}

	if(selectItem.value == 'MANDATE'){
		contractImgRow.style.display = 'none';
	}else{
		contractImgRow.style.display =   '';
	}
function popupaction()
{
	var baseContractid = document.getElementById('contractForm:baseContractIdHidden').value;
	var url ='../contractpopups/SelectBaseContractStartDate.jsp'+getUrl()+'?sysId='+ baseContractid;
	var retValue = ewpdModalWindow_ewpd(url,'baseContractDtDiv','contractForm:baseContractDtHidden',2,1);
	var SrtDateObj = getObj('baseContractDtDiv');
	var startDate = SrtDateObj.innerText;
	document.getElementById('contractForm:startDate_txt').value = startDate;
	return retValue;
}
//
function popupactionNonMaditory()
{
	var baseContractid = document.getElementById('contractForm:baseContractIdStandardHidden').value;
	var url ='../contractpopups/SelectBaseContractStartDate.jsp'+getUrl()+'?sysId='+ baseContractid;
	var retValue = ewpdModalWindow_ewpd(url,'baseContractDtStandardDiv','contractForm:baseContractDtStandardHidden',2,1);
	var SrtDateObj = getObj('baseContractDtStandardDiv');
	var startDate = SrtDateObj.innerText;
	document.getElementById('contractForm:startDate_txt').value = startDate;
	return retValue;
}
function getProdPopupUrl(lobId,beId,bgId,mbuId,effDateId,expDateId,url,stateId){
	var lob = getObj(lobId).value;
	var be = getObj(beId).value;
	var bg = getObj(bgId).value;
	var mbu = getObj(mbuId).value;
	var effDate = getObj(effDateId).value;
	var expDate = getObj(expDateId).value;
	var state = getObj(stateId).value;
	var popupUrl = url;
	popupUrl += 'lob=' + escape(lob) + '&be=' + escape(be) + '&bg=' + escape(bg)+ '&mbu=' + escape(mbu) + '&effDate=' + escape(effDate) + '&expDate=' + escape(expDate) + '&temp=' + Math.random()+'&state='+state;
	return popupUrl;
 }


function productPopup(){
	ewpdModalWindow_ewpd(getProdPopupUrl('contractForm:lineOfBusinessHidden','contractForm:businessEntityHidden','contractForm:businessGroupHidden','contractForm:marketBusinessUnitHidden',
									 		'contractForm:startDate_txt','contractForm:expiryDate_txt','../contractpopups/productPopup.jsp'+getUrl()+'?','contractForm:headQuarters'),
						'productDiv','contractForm:txtProduct',2,2);
	
}

function getReservedContractId(){
var url = '../contractpopups/reservedIdPopup.jsp'+getUrl();
url = url 	+ '?lineOfBusiness='+ escape(document.getElementById('contractForm:lineOfBusinessHidden').value)
			+ '&businessEntity='+ escape(document.getElementById('contractForm:businessEntityHidden').value)
			+ '&businessGroup='+ escape(document.getElementById('contractForm:businessGroupHidden').value)
			+ '&marketBusinessUnit='+ escape(document.getElementById('contractForm:marketBusinessUnitHidden').value)
			+ '&temp='+Math.random();	
ewpdModalWindow_ewpd(url,'contractIdDiv','contractForm:contractIdHidden',2,1)
}
 function clearProduct(){
	document.getElementById('productDiv').innerHTML = "";
	document.getElementById('contractForm:txtProduct').value = "";
}
function loadContactIdSearchPopup(queryName,headerName,titleName,displayDiv,outComp,peerDiv,peerHiddenComp){
	ewpdModalWindow_termed_contract( '../popups/searchFilterPopupSingle.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2,peerDiv,peerHiddenComp);
}
</script>
</HTML>

