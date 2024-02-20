<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<HEAD>

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

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



<TITLE>WellPoint Product Database: Migration Product Information</TITLE>

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
		onkeypress="return submitOnEnterKey('productInfoForm:nextButton');">

	<hx:scriptCollector id="scriptCollector1">

		<h:inputHidden id="hidden1" ></h:inputHidden>

		<TABLE width="100%" cellpadding="0" cellspacing="0">

			<TR>

				<TD><jsp:include page="../navigation/top_migration.jsp"></jsp:include>

				</TD>

			</TR>

			<TR>

				<TD><h:form styleClass="form" id="productInfoForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">

						<TR>

							<TD width="273" valign="top" class="leftPanel">

							<DIV class="treeDiv"><!-- Space for Tree  Data     --> <!--  <jsp:include page="migrationTree.jsp"></jsp:include>   -->

							<jsp:include page="../migration/migrationWizardTree.jsp">
							</jsp:include></DIV>

							</TD>



							<TD colspan="2" valign="top" class="ContentArea">

							<TABLE>

								<TR>

									<TD><w:message
										value="#{migrationProductInfoBackingBean.validationMessages}"></w:message>

									</TD>

								</TR>

							</TABLE>

							<!-- Table containing Tabs -->

							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
<%--
								<tr>
									<td width="18%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabActive">Step 5</td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								</tr>
--%>		

										<tr>
											<td width="100%"><b>Step5 : Product Information</b> </td>
										</tr>
										<tr>
											 <td>To select the product based on which the legacy contract needs to be migrated.</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

							</table>

							<!-- End of Tab table --> <!--  Start of Table for actual Data      -->


<!--
							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:6px;padding-top:6px;width:100%">
-->


							<table width="100%" border="0" cellspacing="0" cellpadding="2">

								<tr>



									<TD width="150" align="left"><h:outputText id="coverageOutText"
										value="Legacy Structure"></h:outputText></TD>



									<td align="left" width="180"><h:outputText id="coverageHidden"
										value="#{migrationProductInfoBackingBean.legacyStructure}"></h:outputText>

									<h:inputHidden id="txtProductHidden"
										value="#{migrationProductInfoBackingBean.legacyStructure}"></h:inputHidden>

									</td>
									<td><br></td>



								</tr>

								<tr>

									<td width="160" align="left"><h:outputText
										styleClass="#{migrationProductInfoBackingBean.productInvalid ? 'mandatoryError': 'mandatoryNormal'}"
										id="pricingOutText" value="ET/Auto bagging Product *"></h:outputText></td>

									<td align="left" width="180">
									<DIV id="productDiv" class="selectDataDisplayDiv"></DIV>



									<h:inputHidden id="txtProduct"
										value="#{migrationProductInfoBackingBean.productName}"></h:inputHidden>

									</td>

									<td align="left"><h:commandButton id="productButton"
										image="../../images/select.gif" style="cursor: hand"
										onclick="productPopup();return false;" alt="Select" action="#{migrationProductInfoBackingBean.retrieveBenefitYearConflictMessage}"></h:commandButton></td>

								</tr>
								<tr>
									<td width="160" align="left"><h:outputText id="networkOutText"
										value="Product Family"></h:outputText></td>
									<td align="left" width="180">&nbsp;
									<h:inputHidden id="txtProductFamilyHidden"	value="#{migrationProductInfoBackingBean.productFamily}"></h:inputHidden>
									<DIV id="productFamilyDiv" class="selectDataDisplayDivForContract"></DIV>
<%--
				<h:outputText id="networkHidden"	value="#{migrationProductInfoBackingBean.productFamily}"></h:outputText>
--%>

									</td><td></td>

								</tr>

								<tr></tr>

								<tr></tr>

								<tr></tr>

								<tr>

									<h:inputHidden id="oldProduct"
										value="#{migrationProductInfoBackingBean.oldProductName}"></h:inputHidden>

								</tr>

							</table>

							<f:verbatim>  &nbsp; &nbsp; </f:verbatim> <f:verbatim>  &nbsp; &nbsp; </f:verbatim>



							<TABLE>

								<TBODY>

									<TR>

										<TD><h:commandButton styleClass="wpdbutton" value="Back"
											id="productId" 
											onclick="clickBackToNavigate();return false;">

										</h:commandButton> <h:commandLink id="productLink"
											style="display:none; visibility: hidden;"
											action="#{migrationProductInfoBackingBean.back}">
											<f:verbatim />

										</h:commandLink></TD>

										<TD>&nbsp;</TD>

										<TD><h:commandButton styleClass="wpdbutton" id="nextButton" value="Next"
											 onclick="clickNext();return false;">
										</h:commandButton></TD>

										<TD>&nbsp;</TD>

										<TD><h:commandButton styleClass="wpdbutton" value="Cancel"
											 onclick="deleteContract();return false;">
										</h:commandButton></TD>

										<h:inputHidden id="contractSysIdHid"
											value="#{migrationProductInfoBackingBean.ewpdContractSysID}" />



										<h:commandLink id="cancelMigrationLink"
											style="display:none; visibility: hidden;"
											action="#{migrationGeneralInfoBackingBean.cancelMigration}">

											<f:verbatim />

										</h:commandLink>

										<h:commandLink id="nextLink"
											style="display:none; visibility: hidden;"
											action="#{migrationProductInfoBackingBean.saveProductInfo}">

											<f:verbatim />

										</h:commandLink>

										<h:commandLink id="backLink"
											style="display:none; visibility: hidden;"
											action="#{migrationProductInfoBackingBean.back}">

											<f:verbatim />

										</h:commandLink>

										<h:commandLink id="doneLink"
											style="display:none; visibility: hidden;"
											action="#{migrationProductInfoBackingBean.done}">
											<f:verbatim />
										</h:commandLink>

									</TR>

								</TBODY>

							</TABLE>

							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>

							<TABLE>

								<TBODY>

									<TR>

										<TD><h:commandButton styleClass="wpdbutton" value="Done"
											 onclick="clickDone();return false;">
										</h:commandButton></TD>

									</TR>

								</TBODY>

							</TABLE>
<!--
							</fieldset>
-->
							<br />



							<br />

							<br>







							<table>

								<tr>

									<h:inputHidden id="option"
										value="#{migrationProductInfoBackingBean.option}" />

									<h:inputHidden id="system"
										value="#{migrationProductInfoBackingBean.system}" />

									<h:inputHidden id="contractID"
										value="#{migrationProductInfoBackingBean.contractID}" />

									<h:inputHidden id="contractType"
										value="#{migrationProductInfoBackingBean.contractType}" />

									<h:inputHidden id="migratedContractSysID"
										value="#{migrationProductInfoBackingBean.migratedContractSysID}" />

									<h:inputHidden id="lineOfBusiness"
										value="#{migrationProductInfoBackingBean.lineOfBusiness}" />

									<h:inputHidden id="businessEntity"
										value="#{migrationProductInfoBackingBean.businessEntity}" />

									<h:inputHidden id="businessGroup"
										value="#{migrationProductInfoBackingBean.businessGroup}" />

									<h:inputHidden id="effectiveDate"
										value="#{migrationProductInfoBackingBean.effectiveDate}" />

									<h:inputHidden id="expiryDate"
										value="#{migrationProductInfoBackingBean.expiryDate}" />

									<h:inputHidden id="hideReplaceProductPopup"
										value="#{migrationProductInfoBackingBean.hideReplaceProductPopup}" />


									<h:inputHidden id="disableField"
										value="#{migrationProductInfoBackingBean.disableField}" />
									<h:inputHidden id="hideProductField"
										value="#{migrationProductInfoBackingBean.hideProductPopUp}" />

									<h:inputHidden id="ewpdContractSysIDHid"
										value="#{migrationProductInfoBackingBean.ewpdContractSysID}" />
									<h:inputHidden id="benefitYrIndWarningMessage" value="#{migrationProductInfoBackingBean.benefitYrIndWarningMessage}"></h:inputHidden>
									<h:commandLink id="retrieveBenefitYearConflictMessage" style="display:none; visibility: hidden;" action="#{migrationProductInfoBackingBean.retrieveBenefitYearConflictMessage}"> <f:verbatim/></h:commandLink>																
									<h:inputHidden id="duplicateData" value="#{migrationProductInfoBackingBean.duplicateData}"></h:inputHidden>
								</tr>

							</table>
					</TABLE>



				</h:form></td>

			</tr>

			<tr>

				<td><%@ include file="../navigation/pageFooter.jsp"%></td>

			</tr>

		</TABLE>

	</hx:scriptCollector>
		<%@include file="../template/freeze.html" %>
	</body>

</f:view>



<script language="JavaScript">

IGNORED_FIELD1='productInfoForm:duplicateData';

if(document.getElementById('productInfoForm:disableField').value == 'true'){ 

	document.getElementById('productInfoForm:productButton').style.visibility = 'hidden';
      

}else if(document.getElementById('productInfoForm:hideProductField').value == 'true'){
	
	document.getElementById('productInfoForm:productButton').style.visibility = 'hidden';
}

 

function clickBackToNavigate(){

      navigatePageActionSubmit('productInfoForm:productLink');

}

function deleteContract(){

            var message = "Are you sure you want to cancel? All data saved during this migration will be lost"

            if(window.confirm(message)){

                  submitLink('productInfoForm:cancelMigrationLink');

            }else{

                        return false;

            }

}

 
function productPopup(){
	var url = '../migration/productPopup.jsp'+getUrl()+'?'+'&temp='+Math.random();
// 	ewpdModalWindow_ewpd(url,'productDiv','productInfoForm:txtProduct',3,2);


	var hiddenId = 'productInfoForm:txtProduct';
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	param.selectedValues = getObj(hiddenId).value;

	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var values = retValue.split("~");
	getObj('productInfoForm:txtProduct').value =values[0]+"~"+values[1];
	getObj('productDiv').value =values[1];
//	getObj('productFamilyDiv').value =values[2];
	getObj('productInfoForm:txtProductFamilyHidden').value =values[2];

	if(getObj('productInfoForm:txtProduct').value != '') {
		submitLink('productInfoForm:retrieveBenefitYearConflictMessage');
		freezeScreen();
	} else {
		getObj('productInfoForm:benefitYrIndWarningMessage').value = '';
	}
}

 
copyHiddenToDiv('productInfoForm:txtProductFamilyHidden', 'productFamilyDiv');
copyHiddenToDiv_ewpd('productInfoForm:txtProduct','productDiv',2,2); 

 

function clickDone(){
saveProdauct('productInfoForm:doneLink');
} 
function clickNext(){
saveProdauct('productInfoForm:nextLink');
} 

function saveProdauct(link){
		// If legacy contract's PlanRenewalType (ie BY/CY) and Selected Product's BY/CY answer differs
		// 'benefitYrIndWarningMessage' field will have the corresponding warning message.
		var benefitYearIndConflicts = getObj('productInfoForm:benefitYrIndWarningMessage').value != '' ? true : false;
		if(benefitYearIndConflicts) {
			if(!window.confirm(getObj('productInfoForm:benefitYrIndWarningMessage').value))
				return false;
		}
      if(document.getElementById('productInfoForm:disableField').value == 'true'
			&& document.getElementById('productInfoForm:hideProductField').value != 'true'){

            var contractSysId = document.getElementById('productInfoForm:ewpdContractSysIDHid').value;
			var contractId = document.getElementById('productInfoForm:contractID').value;		
			var oldProduct = document.getElementById('productInfoForm:oldProduct').value.split('~');

            var   url = "../migration/checkoutProductPopup.jsp"+getUrl()+"?contractKey="+contractSysId+
                        '&migRequest=requestFromMigrationWizard'+
						'&contractId='+contractId+
						'&productId='+oldProduct[0]+
                        //'&dateId='+dateSegmentSysId+
            	        //'&productid='+prodid+
                        '&temp='+Math.random();
		
            var myObject = new Object();
            var returnvalue=window.showModalDialog(url,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
		
            if((typeof returnvalue == 'undefined') 
					|| (returnvalue == 'autoClose')
					|| (returnvalue == 'continueProduct~')
					||(returnvalue == '~')){
				if(returnvalue == 'autoClose'){
                  document.getElementById(link).click();
                  return false;
				}else if(returnvalue == 'continueProduct~'){
                  document.getElementById(link).click();
                  return false;
				}else{			
                  return false;
				}
            }else{
//                var newProductVer = returnvalue.split('~');
//                var oldProductVer = document.getElementById('productInfoForm:oldProduct').value.split('~');
//                if(oldProductVer[0] != newProductVer[0]){
                  	
		
                   
                    	var oldProductVer = document.getElementById('productInfoForm:oldProduct').value.split('~');
                        document.getElementById('productInfoForm:txtProduct').value = returnvalue+oldProductVer[1];
                        document.getElementById(link).click();
                        return true;
                   
						//var temp = document.getElementById('productInfoForm:oldProduct').value;
//                      copyHiddenToDiv_ewpd('productInfoForm:oldProduct','productDiv',2,2); 
//                      copyHiddenToDiv_ewpd('productInfoForm:oldProduct','productInfoForm:txtProduct',2,2); 
//                      return false;
                       
//                }else{
//                      document.getElementById(link).click();
//                      return true;
//                }
            }
      }
		else{
	      if(null != document.getElementById('productInfoForm:oldProduct') && null != document.getElementById('productInfoForm:txtProduct')){
            var oldProductValue = document.getElementById('productInfoForm:oldProduct').value;
            var newProductValue = document.getElementById('productInfoForm:txtProduct').value;
            if(null != oldProductValue && '' != oldProductValue){
                  if(oldProductValue != newProductValue){
                        var result = confirm("All the mappings related to the old product will be deleted. Are you sure to continue?");
                        if(result){
                              document.getElementById(link).click();
                              return true;
                        }
                        else
                              copyHiddenToDiv_ewpd('productInfoForm:oldProduct','productDiv',2,2); 
document.getElementById('productInfoForm:txtProduct').value = document.getElementById('productInfoForm:oldProduct').value;
//                              copyHiddenToDiv_ewpd('productInfoForm:oldProduct','productInfoForm:txtProduct',2,2); 
                              return false;
                  }
                  else{
                        document.getElementById(link).click();
                        return true;
                  }
            } 
            else{
                        document.getElementById(link).click();
                        return true;
                  }
      }
      }
}

function clickBack(){
            if((navigatePageAction('productInfoForm:productLink'))==false){
                  submitLink('productInfoForm:backLink');
            }
}

 

</script>

<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('productInfoForm:duplicateData').value == '')
        document.getElementById('productInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
        document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('productInfoForm:duplicateData').value;
</script>
</HTML>

