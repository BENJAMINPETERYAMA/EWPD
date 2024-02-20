<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<script language="JavaScript" src="../../js/PopupWindow.js"></script>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.dataTableColumn {
      width: 5%;  
}

.claimLineColumn {
     width: 6%; 
}

.descriptionColumn {
     width: 12%; 
}
</style>
<TITLE>Create Test Case</TITLE>
</HEAD>
<f:view>
<body onkeydown="return submitTestCase();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
	</tr>
</table>
<h:form id="TCCreateForm">
<fieldset>
<table align="center" border="0" cellspacing="0" cellpadding="0" width="97%">
	<tr>
		<td valign="top" class="ContentArea" style="text-align: left;">
		<div><w:message></w:message></div>
		<div style="margin-bottom:9px"><fieldset>
		<legend>Claim Header</legend>
		<table width="100%"  border="0">
			<tr>
				<td  style="padding:13px 13px 0px 13px" width="30%" valign="top">
					<table border="0" cellspacing="0" cellpadding="3" height="100%" style="white-space: nowrap;vertical-align: top;" >
						<TR>
										<TD  valign="top" width="177"><h:outputText id="claimType"
											value="Claim Type"/>
										</TD>
										<TD><h:selectOneMenu id="claimTypeName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.claimType}">
											<f:selectItems id="claimTypeList"
												value="#{ReferenceDataBackingBeanCommon.claimTypeListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
						<TR>
										<TD  valign="top" width="177">
											<h:outputText value="Provider ID"/>
										</TD>
										<TD><h:inputText
											styleClass="formInputList" id="txtProviderIdNm"
											maxlength="30" value="#{testCaseBackingBean.providerId}"
											onkeyup="return ismaxlength(this,30);" />
										</TD>
						</TR>
						<TR>
										<TD  valign="top" width="177"><h:outputText id="medAssignIndicator"
											value="MED Assign IND"/>
										</TD>
										<TD><h:selectOneMenu id="medAssignIndicatorName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.medAssignIndicator}">
											<f:selectItems id="medAssignIndicatorList"
												value="#{ReferenceDataBackingBeanCommon.medAssignIndicatorListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
						<TR>
										<TD valign="top" width="177"><h:outputText value="Age"/>
										</TD>
										<TD><h:inputText
											styleClass="formInputList" id="txtAgeNm"
											maxlength="3" value="#{testCaseBackingBean.age}"
											onkeyup ="return isNumber(this);" /></TD>
						</TR>
					</table>
				</td>
				<td style="padding:13px 13px 0px 13px" width="36%" valign="top">
					<table border="0" cellspacing="0" cellpadding="3" style="text-align:left;">
						<TR>
										<TD valign="top" width="177"><h:outputText value="ITS/PROV Spec"/>
										</TD>
										<TD width="221"><h:inputText
											styleClass="formInputList" id="txtItsProvSpecNm"
											maxlength="30" value="#{testCaseBackingBean.itsProvSpec}" 
											onkeyup="return ismaxlength(this,30);" /></TD>
						</TR>
										<TR>
										<TD valign="top" width="177"><h:outputText id="hospitalFacilityCode"
											value="Hospital Facility Code"/>
										</TD>
										<TD width="221"><h:selectOneMenu id="hospitalFacilityCodeName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.hospitalFacilityCode}">
											<f:selectItems id="hospitalFacilityCodeList"
												value="#{ReferenceDataBackingBeanCommon.hospitalFacilityCodeListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
						
						<TR>
										<TD valign="top" width="177"><h:outputText id="gender"
											value="Gender"	 />
										</TD>
										<TD width="221"><h:selectOneMenu id="genderName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.gender}">
											<f:selectItems id="genderList"
												value="#{ReferenceDataBackingBeanCommon.genderListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
						<TR>
										<TD valign="top" width="177"><h:outputText id="groupState"
											value="Group State"/>
										</TD>
										<TD width="221"><h:selectOneMenu id="groupStateName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.groupState}">
											<f:selectItems id="groupStateList"
												value="#{ReferenceDataBackingBeanCommon.groupStateListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
					</table>
				</td>
				<td style="padding:13px 13px 0px 13px" valign="top">
					<table border="0" cellspacing="0" cellpadding="3" style="text-align:left;" style="hite-space: nowrap;vertical-align: top;">
						<TR>
										<TD width="168"><h:outputText value="Edit"/>
										</TD>
										<TD width="221"><h:inputText
											styleClass="formInputList" id="txtEditNm"
											maxlength="30" value="#{testCaseBackingBean.edit}" 
											onkeyup="return ismaxlength(this,30);"/></TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText value="Days From Injury"/>

										</TD>
										<TD width="221"><h:inputText
											styleClass="formInputList" id="txtDaysFromInjuryNm"
											maxlength="30" value="#{testCaseBackingBean.daysFromInjury}"
											onkeyup="return isNumber(this);"/></TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText id="memberRelationshipCode"
											value="Member Relationship Code"/>
											
										</TD>
										<TD width="221"><h:selectOneMenu id="memberRelationshipCodeName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.memberRelationshipCode}">
											<f:selectItems id="memberRelationshipCodeList"
												value="#{ReferenceDataBackingBeanCommon.memberRelationshipCodeListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
						<TR>
										<TD width="168"><h:outputText id="itsClaim"
											value="ITS Claim"/>
											
										</TD>
										<TD width="221"><h:selectBooleanCheckbox id="itsClaimName" value="#{testCaseBackingBean.itsClaim}">
										</h:selectBooleanCheckbox></TD>
						 </TR>

					</table>
				</td>
			</tr>
		</table>
		</fieldset></div>
		<div style="margin-bottom:9px"><fieldset>
		<legend>Claim Detail</legend>
		<table border="0" width="100%">
			<tr>
				<td style="padding:13px 13px 0px 13px" width="30%"  valign="top">
					<table  border="0" cellspacing="0" cellpadding="3" height="100%" style="white-space: nowrap;">
						<TR>
										<TD width="168">
											<h:outputText value="Diagnosis Code"
											styleClass="#{testCaseBackingBean.diagnoscodeEntry ? 'mandatoryError': 'mandatoryNormal'}"/>
										</TD>
										<TD><h:inputText
											styleClass="formInputList" id="txtDiagnosisCodeNm"
											maxlength="30" value="#{testCaseBackingBean.diagnosisCode}" 
											onkeyup="return ismaxlength(this,30);"/></TD>
						</TR>
						<TR>
										<TD width="168">
											<h:outputText value="HCPC Code"/>
										</TD>
										<TD><h:inputText
											styleClass="formInputList" id="txtHcpcCodeNm"
											maxlength="30" value="#{testCaseBackingBean.hcpcCode}" 
											onkeyup="return ismaxlength(this,30);"/></TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText value="Modifier Code"/>
										</TD>
										<TD><h:inputText
											styleClass="formInputList" id="txtModifierCodeNm"
											maxlength="30" value="#{testCaseBackingBean.modifierCode}" 
											onkeyup="return ismaxlength(this,30);"/></TD>
						</TR>
						<tr>
							<td colspan="3" width="177">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td  style="padding:13px 13px 0px 13px" width="36%"  valign="top">
					<table border="0" width="100%" cellspacing="0" cellpadding="3" style="text-align:left;white-space: nowrap;">
							<TR>
										<TD width="168"><h:outputText value="TT Code"/>
										</TD>
										<TD colspan="2" valign="top" nowrap><h:inputText
											styleClass="formInputList" id="txtTTCodeNm"
											maxlength="30" value="#{testCaseBackingBean.ttCode}" 
											onkeyup="return ismaxlength(this,30);"/></TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText value="Revenue Code"/>
										</TD>
										<TD colspan="2" valign="top" nowrap><h:inputText
											styleClass="formInputList" id="txtRevenueCodeNm"
											maxlength="30" value="#{testCaseBackingBean.revenueCode}" 
											onkeyup="return ismaxlength(this,30);"/></TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText id="expectedBenefitComponent"
											value="Expected Benefit Component*"   
											styleClass="#{testCaseBackingBean.exptdBnftCompEntry ? 'mandatoryError': 'mandatoryNormal'}"/>
										</TD>
										<TD width="221">
											<DIV id="benefitComponentDiv" class="formInputList"></DIV>
										</TD>
										<TD align="left">
										<h:commandButton
											 alt="Select"
											image="../../images/select.gif" style="cursor: hand"
											onclick="loadBenefitComponent(); return false;"
											 /> 
											 <h:inputHidden id="benefitComponentHidden"
											value="#{testCaseBackingBean.expectedBenefitComponent}"></h:inputHidden>
											<h:inputHidden id="benefitComponentHiddenDefault"></h:inputHidden>
											</TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText id="expectedRiderBenefit"
											value="Expected Rider Benefit"/>
										</TD>
										<TD>
										<DIV id="riderBenefitDiv" class="formInputList"></DIV>
										</TD>
										<TD align="left">
										<h:commandButton
											alt="Select"
											image="../../images/select.gif" style="cursor: hand"
											onclick="loadRiderBenefit('riderBenefit'); return false;"
											 /> <h:inputHidden id="riderBenefitHidden"
											value="#{testCaseBackingBean.expectedRiderBenefit}"></h:inputHidden>
										</TD>
						</TR>
					</table>
				</td>
				<td style="padding:13px 13px 0px 13px"  valign="top">
					<table border="0" width="100%" cellspacing="0" cellpadding="3" style="text-align:left;">
						<TR>
										<TD width="168"><h:outputText id="placeOfService"
											value="Place Of Service"/>
										</TD>
										<TD width="221"><h:selectOneMenu id="placeOfServiceName"
											styleClass="formInputList"
											value="#{testCaseBackingBean.placeOfService}">
											<f:selectItems id="placeOfServiceList"
												value="#{ReferenceDataBackingBeanCommon.placeOfServiceListForCombo}" />
										</h:selectOneMenu></TD>
						 </TR>
							<TR>
										<TD width="168"><h:outputText id="typeOfBill"
											value="Type Of Bill"/>
										</TD>
										<TD>
										<DIV id="typeOfBillDiv" class="formInputList"></DIV>
										</TD>
										<TD align="left"><h:commandButton
											 alt="Select"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../webtesttool/expectedTypeOfBillPopup.jsp'+getUrl()+'?temp='+ Math.random(),'typeOfBillDiv','TCCreateForm:tobHidden',2,2); return false;"
											/> <h:inputHidden id="tobHidden"
											value="#{testCaseBackingBean.typeOfBill}"></h:inputHidden></TD>
						</TR>
					
						<TR>
										<TD width="168"><h:outputText id="expectedBenefit"
											value="Expected Benefit"/>
										</TD>
										<TD>
										<DIV id="expectedBenefitDiv" class="formInputList"></DIV>
										</TD>
										<TD align="left"><h:commandButton
											 alt="Select"
											image="../../images/select.gif" style="cursor: hand"
											onclick="loadExpectedBenefit('expectedBenefit');return false;"
											/> <h:inputHidden id="expectedBenefitHidden"
											value="#{testCaseBackingBean.expectedBenefit}"></h:inputHidden>
										</TD>
						</TR>
						<TR>
										<TD width="168"><h:outputText id="expectedBasicBenefit"
											value="Expected Basic Benefit"/>
										</TD>
										<TD>
										<DIV id="basicBenefitDiv" class="formInputList"></DIV>
										</TD>
										<TD align="left"><h:commandButton
											alt="Select"
											image="../../images/select.gif" style="cursor: hand"
											onclick="loadBasicBenefit('basicBenefit');return false;"
											/> <h:inputHidden id="basicBenefitHidden"
											value="#{testCaseBackingBean.expectedBasicBenefit}"></h:inputHidden></TD>
						</TR>
					</table>
				</td>
			</tr>
			<tr>
				<TD width="100%" colspan="3" style="text-align: right; ">
											<h:commandButton value="Add" id="addClaimLine"
												styleClass="wpdButton" style="margin-right:23px;padding:0px 13px;"
												rendered="#{!testCaseBackingBean.isClaimLineEdit}" 
												action="#{testCaseBackingBean.addClaimLine}">
											</h:commandButton>
											<h:commandButton value="Update" id="updateClaimLine"
												styleClass="wpdButton" style="margin-right:23px;padding:0px 13px;"
												rendered="#{testCaseBackingBean.isClaimLineEdit}" 
												action="#{testCaseBackingBean.updateClaimLine}">
											</h:commandButton>
				</TD>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" style="text-align:center;" cellspacing="1" cellpadding="3">
						<tr>
							<td class="tdHeader" width="5%" nowrap>Line #</td>
							<td class="tdHeader" width="6%" nowrap>Diagnosis <br> Code</td>
							<td class="tdHeader" width="6%" nowrap>TT Code</td>
							<td class="tdHeader" width="6%" nowrap>Place of <br> Service</td>
							<td class="tdHeader" width="6%" nowrap>HCPC Code</td>
							<td class="tdHeader" width="6%" nowrap>Revenue <br> Code</td>
							<td class="tdHeader" width="6%" nowrap>Type of <br> Bill</td>
							<td class="tdHeader" width="6%" nowrap>Modifier <br> Code</td>
	   					    <td class="tdHeader" width="12%" nowrap>Expected Benefit <br> Component</td>
							<td class="tdHeader" width="12%" nowrap>Expected <br>Benefit</td>
							<td class="tdHeader" width="12%" nowrap>Expected Rider <br> Benefit</td>
							<td class="tdHeader" width="12%" nowrap>Expected Basic <br> Benefit</td>
							<td class="tdHeader" width="5%" nowrap>Action</td>
						</tr>
					</table>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
										 styleClass="outputText" headerClass="dataTableHeader"
										 id="searchResultTable" var="testCaseDT" cellpadding="3"
										 width="100%"
										 cellspacing="1" bgcolor="#cccccc" binding="#{testCaseBackingBean.dataTable}"
										 rendered="#{testCaseBackingBean.claimLineList != null}"
										 value="#{testCaseBackingBean.claimLineList}" var="claimLine" columnClasses="dataTableColumn,claimLineColumn,claimLineColumn,claimLineColumn,claimLineColumn,claimLineColumn,claimLineColumn,claimLineColumn,descriptionColumn,descriptionColumn,descriptionColumn,descriptionColumn,dataTableColumn">
						<h:column>
							
							<h:outputText value="#{testCaseBackingBean.dataTable.rowIndex + 1}"/>    
						</h:column>
						<h:column>
							
					        <h:outputText value="#{claimLine.diagnosisCode}"></h:outputText>
						</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.ttCode}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.placeOfService}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.hcpcCode}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.revenueCode}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.typeOfBillCode}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.modifierCode}"></h:outputText>
							</h:column>
	   					    <h:column>
								
								<h:outputText value="#{claimLine.expectedBenefitComponentDesc}" />
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.expectedMajBenefitDesc}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.expectedRiderBenefitDesc}"></h:outputText>
							</h:column>
							<h:column>
								
								<h:outputText value="#{claimLine.expectedBasicBenefitDesc}"></h:outputText>
							</h:column>
						<h:column>
							
							<h:commandButton alt="Edit Claim Line" action="#{testCaseBackingBean.editClaimLine}" image="../../images/edit.gif">
							</h:commandButton>
							<h:commandButton alt="Delete" action="#{testCaseBackingBean.deleteClaimLine}" image="../../images/delete.gif">
							</h:commandButton>
						</h:column>
					</h:dataTable>
				</td>
			</tr>
		</table>
		</fieldset></div>
		<fieldset  style="margin-top:10px;">
		<legend>Test Case</legend>
		<table border="0" cellspacing="0" cellpadding="3">
				<TR>
										<TD width="168">&nbsp;<h:outputText value="Test Case*"
											styleClass="#{testCaseBackingBean.testCaseNameEntry ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
											<TD width="221"><h:inputText
											styleClass="formInputField" id="txtTestCaseNm"
											maxlength="30" value="#{testCaseBackingBean.testCaseName}"
											onkeyup="return ismaxlength(this,70);"/></TD>
						</TR>
										<TR>
										<TD width="168">&nbsp;<h:outputText value="Description"/>
										</TD>
										<TD colspan="2" valign="top"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc" id="txtTestCaseDesc"
											value="#{testCaseBackingBean.testCaseDesc}"
											onkeypress="return validateText();"/> 
										</TD>
						</TR>
						

		</table>
		</fieldset>
		<table>
			<tr>
				<TD width="100%" colspan="3" style="text-align: right;" >&nbsp;
							<h:commandButton value="Create" id="createTestCase"
								styleClass="wpdButton"  style="margin-right:23px;padding:0px 13px;"
								rendered="#{!testCaseBackingBean.isCreate}" 
								action="#{testCaseBackingBean.createTestCase}">
							</h:commandButton>
							<h:commandButton value="Save" id="updateTestCase"
								styleClass="wpdButton" style="margin-right:23px;padding:0px 13px;"
								rendered="#{testCaseBackingBean.isCreate}" 
								action="#{testCaseBackingBean.updateTestCase}">
								
							</h:commandButton>
				</TD>
			</tr>
		</table>
		</h:form>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
	<tr>
		<td><%@ include file="../navigation/bottom.jsp"%></td>
	</tr>
</table>
</body>
</f:view>
<script language="JavaScript">
	if(document.getElementById("TCCreateForm:createTestCase") == null)
		document.getElementById("TCCreateForm:txtTestCaseNm").readOnly=true;

	copyHiddenToDiv_ewpd('TCCreateForm:benefitComponentHidden','benefitComponentDiv',2,2);
	copyHiddenToDiv_ewpd('TCCreateForm:riderBenefitHidden','riderBenefitDiv',2,2);
	copyHiddenToDiv_ewpd('TCCreateForm:tobHidden','typeOfBillDiv',2,2);
	copyHiddenToDiv_ewpd('TCCreateForm:expectedBenefitHidden','expectedBenefitDiv',2,2);
	copyHiddenToDiv_ewpd('TCCreateForm:basicBenefitHidden','basicBenefitDiv',2,2);
	
	document.getElementById('TCCreateForm:benefitComponentHiddenDefault').value = document.getElementById('TCCreateForm:benefitComponentHidden').value;

	 function submitTestCase()
	 {
		if(document.getElementById("TCCreateForm:createTestCase") != null)
			return submitOnEnterKey('TCCreateForm:createTestCase');
		else
			return submitOnEnterKey('TCCreateForm:updateTestCase');
	 }

	var validationMessage = "Description has extended special characters, which are copied directly from Microsoft Word document.\nExtended special characters are not supported by WPD application. Please correct and try again.";

	function validateText(){
		if(document.getElementById('TCCreateForm:txtTestCaseDesc').value.length > 299){
			return false;
		}else{
			return true;
		}
	}
	function loadBenefitComponent(){
	
		ewpdModalWindow_ewpd('../webtesttool/expectedBenefitComponentPopup.jsp'+getUrl()+'?temp='+ Math.random(),'benefitComponentDiv','TCCreateForm:benefitComponentHidden',2,2);		
		
		if(document.getElementById('TCCreateForm:benefitComponentHiddenDefault').value 
			!= document.getElementById('TCCreateForm:benefitComponentHidden').value){
			document.getElementById('expectedBenefitDiv').innerText = '';
			document.getElementById('TCCreateForm:expectedBenefitHidden').value ='';
			document.getElementById('basicBenefitDiv').innerText = '';
			document.getElementById('TCCreateForm:basicBenefitHidden').value ='';
			document.getElementById('riderBenefitDiv').innerText = '';
			document.getElementById('TCCreateForm:riderBenefitHidden').value ='';	
			document.getElementById('TCCreateForm:benefitComponentHiddenDefault').value = document.getElementById('TCCreateForm:benefitComponentHidden').value;
		}
		
		return false;
	}
	function loadExpectedBenefit(benefitType){
		var benefitCompId = document.getElementById('TCCreateForm:benefitComponentHidden').value;
		if(benefitCompId==null || benefitCompId=="")
		{	
			alert("Please select Expected Benefit Component");
			return false;
		}
		ewpdModalWindow_ewpd('../webtesttool/expectedBenefitPopup.jsp'+getUrl()+'?benefitType='+benefitType+'&benefitCompId=' +benefitCompId+ '&temp='+ Math.random(),'expectedBenefitDiv','TCCreateForm:expectedBenefitHidden',2,2);
		return false;

	}
	function loadBasicBenefit(benefitType){
		var benefitCompId = document.getElementById('TCCreateForm:benefitComponentHidden').value;
		if(benefitCompId==null || benefitCompId=="")
		{	
			alert("Please select Expected Benefit Component");
			return false;
		}
		ewpdModalWindow_ewpd('../webtesttool/expectedBenefitPopup.jsp'+getUrl()+'?benefitType='+benefitType+'&benefitCompId='+benefitCompId+ '&temp='+ Math.random(),'basicBenefitDiv','TCCreateForm:basicBenefitHidden',2,2);
		return false;

	}
	function loadRiderBenefit(benefitType){

		var benefitCompId = document.getElementById('TCCreateForm:benefitComponentHidden').value;

		if(benefitCompId==null || benefitCompId=="")
		{	
			alert("Please select Expected Benefit Component");
			return false;
		}
		ewpdModalWindow_ewpd('../webtesttool/expectedBenefitPopup.jsp'+getUrl()+'?benefitType='+benefitType+'&benefitCompId='+benefitCompId+ '&temp='+ Math.random(),'riderBenefitDiv','TCCreateForm:riderBenefitHidden',2,2);
		return false;
	}
	function isNumber(obj){
		var value = obj.value;
		var str = value.match("[^0-9]");
		if(str == null) 
			return true;
		else{
			alert("Please enter digits only");
		 	obj.value = "";
			return false;
		}
		if (obj.value && obj.value.length>30)
			obj.value=obj.value.substring(0,30)
	}
	
</script>
</html>

