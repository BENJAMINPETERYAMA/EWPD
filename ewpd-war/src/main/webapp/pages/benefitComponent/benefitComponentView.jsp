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

<TITLE>Benefit Component View</TITLE>
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
	<h:form styleClass="form" id="benefitComponentViewForm">
		<h:inputHidden id="viewBenefitComponentKey"
			value="#{benefitComponentBackingBean.viewBenefitComponentKey}" />
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>


			<tr>
				<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
						<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>



						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
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
											id="generalInformationTabTable" value=" General Information" />
										</td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<h:inputHidden id="hiddenTabValue"
									value="#{benefitComponentBackingBean.componentTypeTab}"></h:inputHidden>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{BenefitComponentHierarchiesBackingBean.loadBenefitHierarchyTabView}">
											<h:outputText id="benefitHierarchiesTabTable"
												value="Benefit Hierarchies" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="bcNotesTab">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{BenefitComponentNotesBackingBean.loadBenefitComponentNotesForView}">
											<h:outputText id="notesTabTable" value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="25%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="1" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD colspan="2">
									<FIELDSET style="width:70%"><lEGEND><FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE border="0" cellspacing="0" cellpadding="3">
										<TR>
											<TD width="162"><h:outputText id="lobLabel"
												value="Line of Business " /></TD>
											<TD width="192">
											<DIV id="lobDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtLob"
												value="#{benefitComponentBackingBean.lob}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="162"><h:outputText id="businessEntityLabel"
												value="Business Entity " /></TD>
											<TD width="192">
											<DIV id="BusinessEntityDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtBusinessEntity"
												value="#{benefitComponentBackingBean.businessEntity}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="162"><h:outputText id="businessGroupLabel"
												value="Business Group " /></TD>
											<TD width="192">
											<DIV id="BusinessGroupDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtBusinessGroup"
												value="#{benefitComponentBackingBean.businessGroup}"></h:inputHidden>
											</TD>
										</TR>
<!-- ------------------------------------------------------------------------------------------------ -->
										<TR>
											<TD width="162"><h:outputText
												value="Market Business Unit " /></TD>
											<TD width="192">
											<DIV id="MarketBusinessUnitDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{benefitComponentBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
										</TR>
<!-- ------------------------------------------------------------------------------------------------- -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD width="166">&nbsp;<h:outputText id="name_label"
										value="Name" /></TD>
									<TD width="447"><h:outputText id="name_txt"
										value="#{benefitComponentBackingBean.name}" />
									<h:inputHidden id="nameHidden" value="#{benefitComponentBackingBean.name}"></h:inputHidden>	
									</TD>
								</TR>
								<!-- **Enhancements** -->
								<TR>
									<TD width="166">&nbsp;<h:outputText value="Component Type" /></TD>
									<TD width="447"><h:inputTextarea id="CorpPlanCd_list2"
										styleClass="selectDivReadOnly"
										value="#{benefitComponentBackingBean.componentType}"
										readonly="true" style="border:0"></h:inputTextarea></TD>
								</TR>

								<TR id="sub1" style="display:none;">
									<TD width="166">&nbsp;<h:outputText value="Mandate Type" /></TD>
									<TD width="447"><h:inputTextarea styleClass="selectDivReadOnly"
										id="Mandate_type_list1"
										value="#{benefitComponentBackingBean.mandateType}"
										readonly="true" style="border:0"></h:inputTextarea></TD>
								</TR>

								<TR id="sub2" style="display:none;">
									<TD width="166">&nbsp;<h:outputText value="State" /></TD>
									<TD width="447">
									<DIV id="StateDiv" style="border:0"></DIV>
									<h:inputHidden id="txtState"
										value="#{benefitComponentBackingBean.selectedStateId}"></h:inputHidden></TD>
								</TR>
								<TR id="sub3" style="display:none;">
									<TD width="166">&nbsp;<h:outputText id="stateCde2"
										value="State" /></TD>
									<TD width="447"><h:outputText id="FederalLabel" value="ALL"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="166" valign="top">&nbsp;<h:outputText
										id="description_label" value="Description" /></TD>
									<TD width="447"><h:outputText id="description_txtarea"
										value="#{benefitComponentBackingBean.description}" styleClass="formTxtAreaReadOnly"/></TD>
								</TR>
								<TR>
									<TD width="166">&nbsp;<h:outputText value="Benefit Rule ID" /></TD>
									<TD width="447">
										<h:inputHidden id="ruleIdTxtHidden" value="#{benefitComponentBackingBean.ruleId}"></h:inputHidden>
										<h:outputText id="txtRule" value="#{benefitComponentBackingBean.ruleId}"></h:outputText>&nbsp;&nbsp; 
										<h:inputHidden id="txtStrRuleType" value="#{benefitComponentBackingBean.ruleType}"></h:inputHidden>
												<h:commandButton rendered = "#{benefitComponentBackingBean.ruleId !=null}"
												alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" /></TD>
								</TR>
								<!-- **End **-->
								<TR>
									<TD width="166">&nbsp;<h:outputText
										id="effectiveDate_label" value="Effective Date" /></TD>
									<TD width="447"><h:outputText
										id="effectiveDate_txt"
										value="#{benefitComponentBackingBean.effectiveDate}" /></TD>
								</TR>
								<TR>
									<TD width="166">&nbsp;<h:outputText
										id="expiryDate_label" value="Expiry Date" /></TD>
									<TD width="447"><h:outputText id="expiryDate_txt"
										value="#{benefitComponentBackingBean.expiryDate}" /></TD>
								</TR>
								
								<TR>
									<TD width="166">&nbsp;<h:outputText
										id="createdBy_label" value="Created By" /></TD>
									<TD width="447"><h:outputText id="createdBy_txt"
										value="#{benefitComponentBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="166">&nbsp;<h:outputText
										id="createDate_label" value="Created Date" /></TD>
									<TD width="447"><h:outputText id="createDate_txt"
										value="#{benefitComponentBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="166">&nbsp;<h:outputText
										id="updatedBy_label" value="Last Updated By" /></TD>
									<TD width="447"><h:outputText id="updatedBy_txt"
										value="#{benefitComponentBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="166">&nbsp;<h:outputText
										id="updationDate_label" value="Last Updated Date" /></TD>
									<TD width="447"><h:outputText id="updationDate_txt"
										value="#{benefitComponentBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR><TD>&nbsp;</TD></TR>
								<TR><TD>&nbsp;</TD></TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>

						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>								
								<td align="right">
								<table >
									<tr>
										<td ><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentBackingBean.state}" />
									</tr>
									<tr>
										<td ><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentBackingBean.status}" />
									</tr>
									<tr>
										<td ><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						

						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <h:inputHidden id="txtname"
					value="#{benefitComponentBackingBean.name}"></h:inputHidden> <h:inputHidden
					id="txtdescription"
					value="#{benefitComponentBackingBean.description}"></h:inputHidden>
				<h:inputHidden id="txtcreateduser"
					value="#{benefitComponentBackingBean.createdUser}"></h:inputHidden>
				<h:inputHidden id="txtcreatedTimestamp"
					value="#{benefitComponentBackingBean.createdTimestamp}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden> <h:inputHidden id="time"
					value="#{requestScope.timezone}">
				</h:inputHidden> <h:inputHidden id="txtlastUpdatedUser"
					value="#{benefitComponentBackingBean.lastUpdatedUser}"></h:inputHidden>
				<h:inputHidden id="txtlastUpdatedTimestamp"
					value="#{benefitComponentBackingBean.lastUpdatedTimestamp}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden> <!-- End of hidden fields  --> </td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
		</h:form>
	</BODY>
</f:view>

<script>
	
	// Space for page realated scripts
	copyHiddenToDiv_ewpd('benefitComponentViewForm:txtLob','lobDiv',2,2); 
    copyHiddenToDiv_ewpd('benefitComponentViewForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
    copyHiddenToDiv_ewpd('benefitComponentViewForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
    copyHiddenToDiv_ewpd('benefitComponentViewForm:txtMarketBusinessUnit','MarketBusinessUnitDiv', 2,2);
	formatTildaToComma1("benefitComponentViewForm:txtRule");
    copyHiddenToDiv_ewpd('benefitComponentViewForm:txtState','StateDiv',2,2);  


var i;
var obj;
obj = document.getElementById("benefitComponentViewForm:CorpPlanCd_list2");
i= obj.value;
	
	if(i=="STANDARD")
	{	
	sub1.style.display='none';
	sub2.style.display='none';
	}
	else 
	{
	sub1.style.display='';
	
	}
var i;
var obj;
obj = document.getElementById("benefitComponentViewForm:Mandate_type_list1");
i= obj.value;
		//alert(i);
		if(i == "State" || i == "ExtraTerritorial")
		{
	
		sub2.style.display='';
		sub3.style.display='none';
		}
		else if(i == "Federal")
		{
		// Federal
        // alert('federal');
		sub2.style.display='none';
		sub3.style.display='';
		}else{
		sub2.style.display='none';
		sub3.style.display='none';
		}

// Enhancement	
	// To display the notes tab only if the componentType is standard
	hideNotesTab();
	function hideNotesTab(){
	var tab = document.getElementById('benefitComponentViewForm:hiddenTabValue').value;	
	// alert('tab:'+ tab);
	if(tab == "Standard Definition"){
		bcNotesTab.style.display = '';
	}else{
		bcNotesTab.style.display = 'none';
	}
}

function viewAction(){
	
	var ruleIdStr = document.getElementById('benefitComponentViewForm:ruleIdTxtHidden').value;
	var ruleType  =	document.getElementById('benefitComponentViewForm:txtStrRuleType').value;
	if(ruleIdStr.length <=1){
			alert('Benefit Rule ID need to be selected.');
			return false;
		}
	
	var ruleArray = ruleIdStr.split('~');
	var ruleId = ruleArray[1];
	//ICD10 Enhancement -- Individual Rule Extract
	var checkMode = 'view';     
	var headerRuleBCselected = 'true';
	var benefitComponentName = document.getElementById('benefitComponentViewForm:nameHidden').value;
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




// End - Enhancement
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponent" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
