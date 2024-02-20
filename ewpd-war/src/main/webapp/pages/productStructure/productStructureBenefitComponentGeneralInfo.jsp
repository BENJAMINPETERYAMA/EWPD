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

<TITLE>Edit Product Structure Benefit Component General Info</TITLE>
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
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="productStructureBenefitCompForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD></TD>
								</tr>
							</TBODY>
						</TABLE>


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
								<td id="benefitTab" width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink
											action="#{productStructureGeneralInfoBackingBean.loadAssociatedBenefits}">
											<h:outputText value="Benefit" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</tr>
								</table>
								</td>
								<td id="notesTab" width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink
											action="#{productStructureGeneralInfoBackingBean.loadNotes}">
											<h:outputText value="Notes" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</tr>
								</table>
								</td>
							<TD width="100%"></TD>
							</tr>
						</table>


						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

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
												value="#{productStructureGeneralInfoBackingBean.lob}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForLob" class="selectDivReadOnly"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForLob'), document.getElementById('productStructureBenefitCompForm:hiddenLob')); </SCRIPT>
											</TD>
										</TR>
										<TR>
											<TD width="130"><h:outputText value="Business Entity" /></TD>
											<h:inputHidden id="hiddenEntity"
												value="#{productStructureGeneralInfoBackingBean.entity}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForEntity" class="selectDivReadOnly"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForEntity'), document.getElementById('productStructureBenefitCompForm:hiddenEntity')); </SCRIPT>
											</TD>
										</TR>
										<TR>
											<TD width="130"><h:outputText value="Business Group" /></TD>
											<h:inputHidden id="hiddenGroup"
												value="#{productStructureGeneralInfoBackingBean.group}"></h:inputHidden>
											<TD width="194">
											<DIV id="divForGroup" class="selectDivReadOnly"></DIV>
											<SCRIPT> //parseForDiv(document.getElementById('divForGroup'), document.getElementById('productStructureBenefitCompForm:hiddenGroup')); </SCRIPT>
											</TD>
										</TR>
										<!-- CARS START -->
										<TR>
											<TD width="130"><h:outputText value="Market Business Unit" /></TD>
											<h:inputHidden id="hiddenMarketBusinessUnit"
												value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
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
									<TD width="239"><h:outputText id="name"
										value="#{productStructureGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="nameHidden"
										value="#{productStructureGeneralInfoBackingBean.name}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:nameHidden','productStructureBenefitCompForm:name',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Component Type" /></TD>
									<TD width="239"><h:outputText id="compType"
										value="#{productStructureGeneralInfoBackingBean.componentType}" />
									<h:inputHidden id="compTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.componentType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:compTypeHidden','productStructureBenefitCompForm:compType',1); </SCRIPT>
									</TD>
								</TR>
								<tr id="sub1" style="display:none;">
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Mandate Type" /></TD>
									<TD width="239"><h:outputText id="mandType"
										value="#{productStructureGeneralInfoBackingBean.mandateType}" />
									<h:inputHidden id="mandTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.mandateType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:mandTypeHidden','productStructureBenefitCompForm:mandType',1); </SCRIPT>
									</TD>
								</TR>
								<tr id="sub2" style="display:none;">
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Jurisdiction" /></TD>
									<TD width="239"><h:outputText id="state"
										value="#{productStructureGeneralInfoBackingBean.stateCode}" />
									<h:inputHidden id="stateHidden"
										value="#{productStructureGeneralInfoBackingBean.stateCode}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:stateHidden','productStructureBenefitCompForm:state',1); </SCRIPT>
									</TD>
								</TR>
								<tr id="sub3" style="display:none;">
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Jurisdiction" /></TD>
									<TD width="239"><h:outputText id="stateFed" value="ALL" /></TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135" valign="top"><h:outputText value="Description" /></TD>
									<TD width="239"><h:outputText id="desc"
										value="#{productStructureGeneralInfoBackingBean.description}"
										styleClass="formTxtAreaReadOnly" /> <h:inputHidden
										id="descHidden"
										value="#{productStructureGeneralInfoBackingBean.description}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:descHidden','productStructureBenefitCompForm:desc',1); </SCRIPT>
									</TD>
									
								</TR>
								
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="239">
									<TABLE>
										<TR>
											<TD>
												<h:outputText id="ruleId"
												value="#{productStructureGeneralInfoBackingBean.ruleId}"
												styleClass="" />
											</TD>
											<TD align="left"> 
												<h:inputHidden id="ruleIdHidden"
												value="#{productStructureGeneralInfoBackingBean.ruleId}">										
												</h:inputHidden>
												<h:inputHidden id="txtStrRuleType" value="#{productStructureGeneralInfoBackingBean.ruleType}"></h:inputHidden>
												<SCRIPT>copyHiddenToInputText1('productStructureBenefitCompForm:ruleIdHidden','productStructureBenefitCompForm:ruleId',1); </SCRIPT>		
												<h:commandButton alt="View" id="viewButton"
														image="../../images/view.gif"
														rendered = "#{productStructureGeneralInfoBackingBean.ruleId != null}"
														onclick="viewAction();return false;" />
											</TD>
										</TR>
									</TABLE>		
									</TD>
								</TR>
                                <TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Effective Date" /></TD>
									<TD width="239"><h:outputText
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}"
										id="effDate" /> <h:inputHidden id="effDateHidden"
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:effDateHidden','productStructureBenefitCompForm:effDate',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Expiry Date" /></TD>
									<TD width="239"><h:outputText id="expiryDate"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}" />
									<h:inputHidden id="expiryDateHidden"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:expiryDateHidden','productStructureBenefitCompForm:expiryDate',1); </SCRIPT>

									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Version" /></TD>
									<TD width="239"><h:outputText id="bnftCmpntVersion"
										value="#{productStructureGeneralInfoBackingBean.bnftCmpntVersion}" />
									<h:inputHidden id="bnftCmpntVersionHidden"
										value="#{productStructureGeneralInfoBackingBean.bnftCmpntVersion}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:bnftCmpntVersionHidden','productStructureBenefitCompForm:bnftCmpntVersion',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Created By" /></TD>
									<TD width="239"><h:outputText id="createdUser"
										value="#{productStructureGeneralInfoBackingBean.createdUser}" />
									<h:inputHidden id="createdUserHidden"
										value="#{productStructureGeneralInfoBackingBean.createdUser}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:createdUserHidden','productStructureBenefitCompForm:createdUser',1); </SCRIPT>
									<h:inputHidden id="time" value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Created Date" /></TD>
									<TD width="239"><h:outputText id="creationDate"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="creationDateHidden"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:creationDateHidden','productStructureBenefitCompForm:creationDate',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Last Updated By" /></TD>
									<TD width="239"><h:outputText id="lastUpdatedUser"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="updatedUserHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:updatedUserHidden','productStructureBenefitCompForm:lastUpdatedUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Last Updated Date" /></TD>
									<TD width="239"><h:outputText id="lastUpdatedDate"
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
				<h:inputHidden id="benTypeHidden"
					value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
			</h:form></td>
		</tr>

		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitGenInfo" /></form>
<script language="javascript">


 copyHiddenToDiv_ewpd('productStructureBenefitCompForm:hiddenLob','divForLob',2,2); 
 copyHiddenToDiv_ewpd('productStructureBenefitCompForm:hiddenEntity','divForEntity',2,2); 
 copyHiddenToDiv_ewpd('productStructureBenefitCompForm:hiddenGroup','divForGroup',2,2);
 copyHiddenToDiv_ewpd('productStructureBenefitCompForm:hiddenMarketBusinessUnit','divForMarketBusinessUnit',2,2);
getStructureType();
getMandateType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("productStructureBenefitCompForm:compType");
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
	obj = document.getElementById("productStructureBenefitCompForm:mandType");
	if( obj.value == "ET" || obj.value == "ST" || obj.value== "Extra-Territorial" || obj.value== "State"){
				if(obj.value=="ST")
					document.getElementById("productStructureBenefitCompForm:mandType").value = "State";
				else
					document.getElementById("productStructureBenefitCompForm:mandType").value = "Extra-Territorial";

		sub2.style.display='';
		sub3.style.display='none';
	}else if(obj.value=="Federal"){
		document.getElementById("productStructureBenefitCompForm:mandType").value = "Federal";
		sub3.style.display='';
		sub2.style.display='none';
	}else {
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
	}
	}

displayNotesTab();
	function displayNotesTab(){
	var benType = document.getElementById('productStructureBenefitCompForm:benTypeHidden').value;
	if(benType=="Mandate Definition"){
		notesTab.style.display='none';
		//benefitTab.style.display='none';
	}
	else{
		notesTab.style.display='';
		//benefitTab.style.display='';
	}
}

//CARS START
//DESCRIPTION : This method calls the benefit rule view popup
function viewAction(){			
	var ruleIdStr = document.getElementById('productStructureBenefitCompForm:ruleIdHidden').value;
	var ruleType = document.getElementById('productStructureBenefitCompForm:txtStrRuleType').value;
	
	var ruleArray = ruleIdStr.split('-');
	var ruleId = ruleArray[0];

	if(ruleType=="BLZWPDAB")
	{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}

//var ruleId = ruleArray[0];
//	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp?ruleId='+ruleId+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');



document.getElementById('divForLob').style.height= "17px";
document.getElementById('divForEntity').style.height= "17px";
document.getElementById('divForGroup').style.height= "17px";
document.getElementById('divForMarketBusinessUnit').style.height= "17px";
</script>
</HTML>
