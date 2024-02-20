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
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Create Service Type Mapping</TITLE>
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
<BODY onkeypress="return submitOnEnterKey('servTypMappingForm:createButton');"><hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<%
			        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
			                .getCurrentInstance().getExternalContext().getRequest();
			        httpReq.setAttribute("breadCrumbText",
			                "Administration >> Blue Exchange >> Service Type Mapping >> Create");
			    %> 
				<jsp:include page="../navigation/top.jsp" />
			</TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="servTypMappingForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:300px"><!-- Space for Tree  Data	--></DIV>
							</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD><w:message></w:message></TD>
								</TR>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
							<TR>
								<TD width="200">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText value="General Information" /></TD>
										<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
							<TBODY>
										<TR>
											<TD width="205"><h:outputText id="ruleIdLabel"
													value="Header Rule *"
													styleClass="#{serviceTypeMappingBackingBean.headerRuleIdValdn?'mandatoryError': 'mandatoryNormal'}" />&nbsp;
											</TD>
											<TD width="194">
													<h:inputText id="ruleIdSearchCriteria" tabindex="1" styleClass="formInputField" value="#{serviceTypeMappingBackingBean.headerRuleId}" onkeypress="return callRuleIdSelect(); return false">
													</h:inputText>
													<h:inputHidden id="ruleIdTxtHidden"></h:inputHidden>
											</TD>
											<TD width="356"><h:commandButton id="ruleIdButton" alt="Select" image="../../images/autoComplete.gif" style="cursor: hand" onclick="ruleIdSelect();return false" tabindex="10" />&nbsp;
												<h:commandButton alt="View" id="viewButton" image="../../images/view.gif" onclick="viewAction();return false;" tabindex="11" />
											</TD>
										</TR>
										<TR>
											<TD width="30%"><h:outputText id="BlueAppl" value="Applicable to blue exchange">
											</h:outputText></TD>
											<TD width="28%"><h:selectOneRadio id="MsgInd" value="#{serviceTypeMappingBackingBean.blueExcahngeApplicable}" onclick="change(this);">
													<f:selectItem id="BlueApplYes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="BlueApplNo" itemLabel="No" itemValue="N" />
											</h:selectOneRadio></TD>
											<TD width="50%"><f:verbatim></f:verbatim></TD>
										</TR>
										<TR id="divRow" style="display:none;">
												<TD valign="top" width="205"></TD>
												<TD align="left" width="194"><DIV id="ruleIdDiv" class="selectDataDisplayDiv"></DIV></TD>
										</TR>
<!-- EB03 Identifiers  -->
										<TR  id="EB03tr">
											<TD width="165"> <h:outputText value="EB03-Service Type Code *"
											styleClass="#{serviceTypeMappingBackingBean.eb03IdentifierValdn?'mandatoryError': 'mandatoryNormal'}" /> </TD>
											<TD width="192"> 
												<h:inputHidden id="eb03Hidden" value="#{serviceTypeMappingBackingBean.eb03Identifier}"/>
												<div id="eb03Div" class="selectDataDisplayDiv"></div>
											</TD>
											<TD width="535">
												<h:commandButton alt="" image="../../images/select.gif" 
												onclick="callUrl();return false;" />
												</TD>
										</TR>
										<TR>
											<TD width="30%"><h:outputText id="sendDynamicInfo" value="Send Dynamic Information ">
											</h:outputText></TD>
											<TD width="28%"><h:selectOneRadio id="sendDyanaInfo" value="#{serviceTypeMappingBackingBean.sendDynamicInfo}">
													<f:selectItem id="dynamicInfoyes" itemLabel="Yes" itemValue="Y" />
													<f:selectItem id="dynamicInfoNo" itemLabel="No" itemValue="N" />
											</h:selectOneRadio></TD>
											<TD width="50%"><f:verbatim></f:verbatim></TD>
										</TR>



								<TR>
									<TD width="205"><h:commandButton value="Create" id="createButton" styleClass="wpdButton" action="#{serviceTypeMappingBackingBean.createAction}">
									</h:commandButton></TD>
									<TD width="194">&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></FIELDSET>
						</TD>
					</TR>
					</TABLE>
<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;"> <f:verbatim /></h:commandLink>
					<DIV id="hiddenDiv" style="visibility:hidden"></DIV>
<!-- End of hidden fields  -->					
				</h:form>
			</TD>
		</TR>
		<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>
<hx:scriptCollector></hx:scriptCollector>
</hx:scriptCollector></BODY>
</f:view>

<script>
//	copyHiddenToDiv_ewpd1('servTypMappingForm:eb03Hidden','eb03Div',2,1);
	 copyHiddenToDiv_ewpd('servTypMappingForm:eb03Hidden','eb03Div',2,2);
	
	var options = document.getElementsByName('servTypMappingForm:MsgInd');
	if(options[1].checked) {
		if(options[1].value == 'N')
			EB03tr.style.display='none';
	}
	if(options[2].checked) {
		if(options[2].value == 'N')
			EB03tr.style.display='none';
	}
	
	function ruleIdSelect(){
		var ruleId= document.getElementById('servTypMappingForm:ruleIdSearchCriteria').value;
		ewpdModalWindow_ewpd('../blueexchange/HeaderRulePopup.jsp'+getUrl()+'?ruleSearchId='+ruleId+'&action='+1+'&temp='+Math.random(),'servTypMappingForm:ruleIdSearchCriteria','servTypMappingForm:ruleIdTxtHidden',2,2);
	}
	function callRuleIdSelect(){
	if(window.event.keyCode == 13){
		ruleIdSelect();
		return false;
	}else{
		return true;
	}
	}
function viewAction(){
	
	var ruleIdStr = document.getElementById('servTypMappingForm:ruleIdSearchCriteria').value;
	if(ruleIdStr == '' || trim(ruleIdStr) == ''){
			alert('Rule ID need to be selected.');
		}
	else{
	//	var ruleArray = ruleIdStr.split('~');
	//	var ruleId = ruleArray[1];
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleIdStr)+'&temp='+Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}
function viewComb(){

	var eb03tilda = document.getElementById('servTypMappingForm:eb03Hidden').value;
	if(eb03tilda.length <=1){
			alert('Please select the EB03-Service Type Code.');
		}
	else{
		var eb03array = eb03tilda.split('~');
		var eb03desc = eb03array[0];
		var eb03val= eb03array[1];
		window.showModalDialog('mappedEBCodesPopup.jsp?eb03val='+eb03val+'&eb03desc='+eb03desc+'&temp=' + Math.random(),'hiddenDiv','dialogHeight:400px;dialogWidth:700px;resizable=true;status=no;');
	}
}
function change(obj){
var val = obj.value;

if(val=='N')
{
	EB03tr.style.display='none';    
}else{
	EB03tr.style.display='';
}
}
function callUrl(){

var url = '../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp'+getUrl()+'?lookUpAction=31&parentCatalog=EB03&title=EB03-Service Type Code&temp='+Math.random();
ewpdModalWindow_ewpd(url,'eb03Div','servTypMappingForm:eb03Hidden',2,2);
	}
</script>


<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
