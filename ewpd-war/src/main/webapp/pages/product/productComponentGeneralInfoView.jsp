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
<base target=_self>
<TITLE>View Product Benefit Component General Info</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:inputHidden id="dummy"
				value="#{productComponentGeneralInfoBackingBean.dummyVariable}"></h:inputHidden>
			<jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="productBenefitComponentForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
									<td id="benefitsTab" width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink
											action="#{productComponentGeneralInfoBackingBean.viewProductAssociatedBenefits}">
											<h:outputText value="Benefit" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</tr>
								</table>
								</td>
								<td width="200" id="notestab">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{productBenefitComponentNoteBackingBean.loadNotesView}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200"></td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
								<TR>
									<TD colspan="5">
									<FIELDSET
										style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
									<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE border="0" cellspacing="0" cellpadding="3">
										<tr>
											<TD width="130"><h:outputText value="Line of Business" /></TD>
											<h:inputHidden id="hiddenLob"
												value="#{productComponentGeneralInfoBackingBean.lineOfBusiness}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForLob" class="selectDivReadOnly"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForLob'), document.getElementById('productBenefitComponentForm:hiddenLob')); </SCRIPT>
											</TD>
										</TR>
										<TR>
											<TD width="130"><h:outputText value="Business Entity" /></TD>
											<h:inputHidden id="hiddenEntity"
												value="#{productComponentGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForEntity" class="selectDivReadOnly"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForEntity'), document.getElementById('productBenefitComponentForm:hiddenEntity')); </SCRIPT>
											</TD>
										</TR>
										<TR>
											<TD width="130"><h:outputText value="Business Group" /></TD>
											<h:inputHidden id="hiddenGroup"
												value="#{productComponentGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForGroup" class="selectDivReadOnly"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForGroup'), document.getElementById('productBenefitComponentForm:hiddenGroup')); </SCRIPT>
											</TD>
										</TR>
<!-- CARS START -->
										<TR>
											<TD width="130"><h:outputText value="Market Business Unit" /></TD>
											<h:inputHidden id="hiddenMarketBusinessUnit"
												value="#{productComponentGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForMarketBusinessUnit" class="selectDivReadOnly"></DIV>
											</TD>
										</TR>
<!-- CARS END -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Name" /></TD>
									<TD width="265"><h:outputText id="name"
										value="#{productComponentGeneralInfoBackingBean.name}"
										styleClass="" /> <h:inputHidden id="nameHidden"
										value="#{productComponentGeneralInfoBackingBean.name}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:nameHidden','productBenefitComponentForm:name',1); </SCRIPT>
									</TD>
								</TR>

								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Component Type" /></TD>
									<TD width="265"><h:outputText id="cmpType"
										value="#{productComponentGeneralInfoBackingBean.componentType}"
										styleClass="" /> <h:inputHidden id="cmpTypeHidden"
										value="#{productComponentGeneralInfoBackingBean.componentType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:cmpTypeHidden','productBenefitComponentForm:cmpType',1); </SCRIPT>
									</TD>
								</TR>
								<TR id="sub1" style="display:none;">
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Mandate Type" /></TD>
									<TD width="265"><h:inputTextarea styleClass="selectDivReadOnly"
										id="mandateType"
										value="#{productComponentGeneralInfoBackingBean.mandateType}"
										readonly="true" style="border:0"></h:inputTextarea><h:inputHidden
										id="mandateTypeHidden"
										value="#{productComponentGeneralInfoBackingBean.mandateType}">
									</h:inputHidden></TD>
								</TR>
								<TR id="sub2" style="display:none;">
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="State" /></TD>
									<TD width="265"><h:outputText id="state"
										value="#{productComponentGeneralInfoBackingBean.stateDesc}"
										styleClass="" /> <h:inputHidden id="stateHidden"
										value="#{productComponentGeneralInfoBackingBean.stateDesc}">
									</h:inputHidden></TD>
								</TR>
								<TR id="sub3" style="display:none;">
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="State" /></TD>
									<TD width="265"><h:outputText id="stateFederal" value="ALL"
										styleClass="" /></TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="200"><h:outputText id="ruleId" 
										value="#{productComponentGeneralInfoBackingBean.ruleIdForView}"
										styleClass="" />
									<h:inputHidden id="ruleIdHidden"
										value="#{productComponentGeneralInfoBackingBean.ruleIdForView}">
									</h:inputHidden>
									<h:inputHidden id="txhRuleType" value="#{productComponentGeneralInfoBackingBean.ruleType}"></h:inputHidden>
									 <SCRIPT>copyHiddenToInputText1('productBenefitComponentForm:ruleIdHidden','productBenefitComponentForm:ruleId',1); </SCRIPT>
									</TD>
									<TD width="65">
										<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												rendered = "#{productComponentGeneralInfoBackingBean.ruleIdForView != null}"
												onclick="viewAction();return false;" />
									</TD>
									
								</TR>

								<TR>
									<TD width="3"></TD>
									<TD width="135" valign="top"><h:outputText value="Description" /></TD>
									<TD width="265"><h:outputText id="desc"
										value="#{productComponentGeneralInfoBackingBean.description}" styleClass="formTxtAreaReadOnly"/>
								 		<h:inputHidden id="descHidden"
										value="#{productComponentGeneralInfoBackingBean.description}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:descHidden','productBenefitComponentForm:desc',1); </SCRIPT>
									</TD>
								</TR>
                                <TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Effective Date" /></TD>
									<TD width="265"><h:outputText
										value="#{productComponentGeneralInfoBackingBean.effectiveDate}"
										id="effDate" styleClass="" /> <h:inputHidden
										id="effDateHidden"
										value="#{productComponentGeneralInfoBackingBean.effectiveDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:effDateHidden','productBenefitComponentForm:effDate',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Expiry Date" /></TD>
									<TD width="265"><h:outputText id="expiryDate"
										value="#{productComponentGeneralInfoBackingBean.expiryDate}"
										styleClass="" /> <h:inputHidden id="expiryDateHidden"
										value="#{productComponentGeneralInfoBackingBean.expiryDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:expiryDateHidden','productBenefitComponentForm:expiryDate',1); </SCRIPT>

									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Version" /></TD>
									<TD width="239"><h:outputText id="bnftCmpntversion"
										value="#{productComponentGeneralInfoBackingBean.bnftCmpntversion}"
										styleClass="" /> <h:inputHidden id="bnftCmpntversionHidden"
										value="#{productComponentGeneralInfoBackingBean.bnftCmpntversion}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:bnftCmpntversionHidden','productBenefitComponentForm:bnftCmpntversion',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Created By" /></TD>
									<TD width="265"><h:outputText id="createdUser"
										value="#{productComponentGeneralInfoBackingBean.createdBy}"
										styleClass="" /> <h:inputHidden id="createdUserHidden"
										value="#{productComponentGeneralInfoBackingBean.createdBy}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:createdUserHidden','productBenefitComponentForm:createdUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Created Date" /></TD>
									<TD width="265"><h:outputText id="creationDate"
										value="#{productComponentGeneralInfoBackingBean.createdDate}"
										styleClass="">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="creationDateHidden"
										value="#{productComponentGeneralInfoBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:creationDateHidden','productBenefitComponentForm:creationDate',1); </SCRIPT>
									<h:inputHidden id="time" value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Last Updated By" /></TD>
									<TD width="265"><h:outputText id="lastUpdatedUser"
										value="#{productComponentGeneralInfoBackingBean.updatedBy}"
										styleClass="" /> <h:inputHidden id="updatedUserHidden"
										value="#{productComponentGeneralInfoBackingBean.updatedBy}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:updatedUserHidden','productBenefitComponentForm:lastUpdatedUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Last Updated Date" /></TD>
									<TD width="265"><h:outputText id="lastUpdatedDate"
										value="#{productComponentGeneralInfoBackingBean.lastUpdatedDate}"
										styleClass="">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updatedDateHidden"
										value="#{productComponentGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productBenefitComponentForm:updatedDateHidden','productBenefitComponentForm:lastUpdatedDate',1); </SCRIPT>
									</TD>
								</TR>
							</TBODY>
						</TABLE>

						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>

           copyHiddenToDiv_ewpd('productBenefitComponentForm:hiddenLob','divForLob',2,2); 
           copyHiddenToDiv_ewpd('productBenefitComponentForm:hiddenEntity','divForEntity',2,2); 
           copyHiddenToDiv_ewpd('productBenefitComponentForm:hiddenGroup','divForGroup',2,2);
           copyHiddenToDiv_ewpd('productBenefitComponentForm:hiddenMarketBusinessUnit','divForMarketBusinessUnit',2,2);

var i;
var obj;
obj = document.getElementById("productBenefitComponentForm:cmpType");
i= obj.value;

//script for mandate rel fields display
	var j;
	if(i=="MANDATE")
	{
	sub1.style.display='';
	j=document.getElementById("productBenefitComponentForm:mandateType").innerHTML;
			if(j == 'ST'){
				document.getElementById("productBenefitComponentForm:mandateType").value="State";
				}else if( j == 'ET'){
				document.getElementById("productBenefitComponentForm:mandateType").value="ET";
				}else if(j=='FED'){
				document.getElementById("productBenefitComponentForm:mandateType").value="Federal";
				}
	if(j== 'ET' || j=='ST')
	{
	sub2.style.display='';
	sub3.style.display='none';
	}else if(j== 'FED'){
	sub3.style.display='';
	sub2.style.display='none';
	}else{
	sub2.style.display='none';
	sub3.style.display='none';
	}
	}
	else 
	{
	sub1.style.display='none';
	}

//script for notes tab hide for mandate
	if(i=="MANDATE")
	{
		notestab.style.display='none';
		benefitsTab.style.display='none';
	}else{
		notestab.style.display='';
		benefitsTab.style.display='';
	}
document.getElementById('divForLob').style.height= "17px";
document.getElementById('divForEntity').style.height= "17px";
document.getElementById('divForGroup').style.height= "17px";
document.getElementById('divForMarketBusinessUnit').style.height= "17px";

function viewAction(){
	
	var ruleIdStr = document.getElementById('productBenefitComponentForm:ruleIdHidden').value;
	var ruleType = document.getElementById('productBenefitComponentForm:txhRuleType').value;
	var ruleArray = ruleIdStr.split('-');
	var ruleId = ruleArray[0];
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view';     
	var headerRuleBCselected = 'true';
	var benefitComponentName = document.getElementById('productBenefitComponentForm:nameHidden').value;
	//ICD10 End
		if(ruleType=="BLZWPDAB")
	{
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='
	+ Math.random()+'&checkMode='+checkMode+'&headerRuleBCselected='+headerRuleBCselected+'&benefitComponentName='+escape(benefitComponentName),
	'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' 
		+ Math.random()+'&checkMode='+checkMode+'&headerRuleBCselected='+headerRuleBCselected+'&benefitComponentName='+escape(benefitComponentName),
		'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productComponentGeneralInfo" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
