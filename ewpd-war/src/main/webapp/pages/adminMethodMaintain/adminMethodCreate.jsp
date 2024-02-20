<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %> 

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
<TITLE>Create Admin Method</TITLE>
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

	<BODY onkeydown="return submitOnEnterKey('createAdminMethodMaintainForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">

			<TR>
				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="createAdminMethodMaintainForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>
							</TD>
							<TD colspan="2" valign="top" class="ContentArea" width="100%">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>

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
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="3" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<FIELDSET>

							<TABLE border="0" cellspacing="9" cellpadding="0"
								class="outputText">
								<TBODY>

									<TR>
										<TD width="25%">&nbsp;<h:outputText
											styleClass="#{adminMethodMaintainBackingBean.adminMethodNoReq? 'mandatoryError': 'mandatoryNormal'}"
											id="amNoStar" value="Admin Method Number*" /></TD>

										<TD width="25%"><h:inputText id="amNoText"
											styleClass="formInputField"	maxlength="5"																			
											value="#{adminMethodMaintainBackingBean.adminMethodNo}"
											tabindex="1" onkeypress="return isNum(event);"></h:inputText></TD>
										<td width="50%"><f:verbatim></f:verbatim></td>

									</TR>

									<TR>
										<TD width="25%">&nbsp;<h:outputText
											styleClass="#{adminMethodMaintainBackingBean.descriptionReq? 'mandatoryError': 'mandatoryNormal'}"
											id="amDesc" value="Admin Method Description*" /></TD>
										<TD width="25%"><h:inputText id="desc" title="" onmouseover="return setToolTip();"
											styleClass="formInputField"						
											value="#{adminMethodMaintainBackingBean.description}"
											onkeypress="return isSpecialChar(event);"											
											tabindex="2"></h:inputText></TD>
										<td width="50%"><f:verbatim></f:verbatim></td>

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
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher_Description"
										textComponentName="createAdminMethodMaintainForm:desc"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Admin Method Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"										
										allowAnyCase="True" allowMixedCase="True"										
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>

									<TR>
										<TD width="27%">&nbsp;<h:outputText
											styleClass="#{adminMethodMaintainBackingBean.processMethodReq? 'mandatoryError': 'mandatoryNormal'}"
											id="BenefitTypeStar" value="Processing Method*" /></TD>
											
										<TD width="29%">
										<TABLE cellspacing="0" cellpadding="0" border="0">
											<TR>

												<h:inputHidden id="txtProcessingMethod"
													value="#{adminMethodMaintainBackingBean.processMethod}"></h:inputHidden>
												<h:inputHidden id="reqParamsList"
													value="#{adminMethodMaintainBackingBean.reqParamsAll}"></h:inputHidden>

												<TD height="15%"><h:selectOneMenu id="dropProcessingMethod"
													styleClass="formInputField" style="width:225px"
													value="#{adminMethodMaintainBackingBean.processMethod}"
													tabindex="3">
													<f:selectItems id="processMethodList"
														value="#{adminMethodMaintainBackingBean.processingMethodList}" />
												</h:selectOneMenu></TD>
											</TR>
										</TABLE>
										</TD>
									</TR>


									<TR>
										<TD width="24%">&nbsp;<h:outputText id="config"
											value="Configuration" /></TD>
											
										<TD width="28%">
										<DIV id="configDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="configid" 
											value="#{adminMethodMaintainBackingBean.configuration}"></h:inputHidden>
										</TD>
										
										<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="RuleButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadConfigurationPopup('searchSPSDetail','Reference description',
                                                         'Configuration Popup','configDiv','createAdminMethodMaintainForm:configid');return false;
											 "></h:commandButton>
										</TD>
										<TD width="42%"></TD>

									</TR>

									<TR>
										<TD width="25%" valign="top">&nbsp;<h:outputText
											styleClass="#{adminMethodMaintainBackingBean.commentsReq? 'mandatoryError': 'mandatoryNormal'}"
											id="descriptionStar" value="Comments" /></TD>

										<TD width="25%"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc" id="comments"											
											value="#{adminMethodMaintainBackingBean.comments}" 
											tabindex="5" ></h:inputTextarea></TD>
										<td width="50%"><f:verbatim></f:verbatim></td>
									</tr>																					
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher_AdminMethodMaintain"
										textComponentName="createAdminMethodMaintainForm:comments"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Comments"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										finishedListener="spellFin" 	
										allowAnyCase="True" allowMixedCase="True"										
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
									<TR>
										<TD colspan=2>
										<fieldset id='fieldsetID'><legend><font color="black">Required
										Parameter</font></legend>

										<TABLE border="0">
											<TR>
												<TD width="78%">

												<fieldset
													style="margin:0px
															auto;text-align:left;padding:10px;border:0px;">

												<DIV id="reqParamDiv1"
													style="margin:0px auto;text-align:left;padding:10px;border:0px;">
												Add Required Parameter Group
												</DIV>											
																								
												</fieldset>
												
												<h:inputHidden id="reqParmid"
													value="#{adminMethodMaintainBackingBean.reqParams}"></h:inputHidden>
												
												
												<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
													styleClass="wpdbutton" id="ReqButton" value="Add"
													onclick="loadReqParameterPopup('searchSPSDetail','Reference description',
                                                         'Required Parameter Popup','reqParamDiv','createAdminMethodMaintainForm:reqParmid');check();return false;"></h:commandButton>
												</TD>
												</tr>
												<tr style="display:none"> 
												<TD width="42%"><DIV id="reqParamDiv"
													style="display:none; visibility: hidden;">
												</DIV>
												
												</TD>
											</TR>
										</TABLE>
										
										<t:dataTable id="dt1" 
										value="#{adminMethodMaintainBackingBean.reqParamGroups}"
                                        rendered="#{adminMethodMaintainBackingBean.reqParamGroups != null}"
										binding="#{adminMethodMaintainBackingBean.createDataTable}"
										var="reqParam" cellpadding="3" width="95%"
										cellspacing="3" border="0" styleClass="requiredParam" columnClasses="param,icon">
										<t:column>
											<t:outputText value="#{reqParam}"></t:outputText>											
											<t:inputHidden id="productKey"
												value="#{adminMethodMaintainBackingBean.createDataTable.rowIndex}"></t:inputHidden>
										</t:column>
		
										<t:column>
										<t:commandButton alt="Delete" id="deleteButton"
										image="../../images/delete.gif"
										onclick="deleteAdminMethodGroups('createAdminMethodMaintainForm:dt1','productKey','createAdminMethodMaintainForm:selectedProductKey','createAdminMethodMaintainForm:deleteLink');return false"
										></t:commandButton></t:column>

									</t:dataTable>
										</fieldset>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<BR>

						
							
							<h:inputHidden id="selectedProductKey"
								value="#{adminMethodMaintainBackingBean.selectedRowNumber}" />
	
							<h:inputHidden id="createdAdminMethodSysNo"
								value="#{adminMethodMaintainBackingBean.adminMethodSysId}" />

							<h:commandLink id="deleteLink"
										style="display:none; visibility: hidden;"
										action="#{adminMethodMaintainBackingBean.deleteGroup}">
									</h:commandLink>
							<h:commandLink id="createAdminMethodMaintain"
									style="hidden" action="#{adminMethodMaintainBackingBean.createAdminMethod}">
							</h:commandLink>

							<TABLE border="0">
								<TR>
								   <TD width="23%">&nbsp;<h:commandButton value="Create"
										id="createButton" styleClass="wpdButton"
										onclick="return runSpellCheck();"
										tabindex="15"></h:commandButton>
									<TD width="23%">&nbsp; </TD>
									
									<TD width="23%">&nbsp;<h:commandButton value="Req Param"
										id="reqParamButton" style="display:none; visibility: hidden;"
										action="#{adminMethodMaintainBackingBean.addRequiredParameter}"
										tabindex="15">


									</h:commandButton>
 
								</TR>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>



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
	copyHiddenToDiv_ewpd('createAdminMethodMaintainForm:reqParmid','reqParamDiv',2,2);
	copyHiddenToDiv_ewpd('createAdminMethodMaintainForm:configid','configDiv',2,2);


function loadSPSSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/SearchReferencePopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}


function loadReqParameterPopup(queryName,headerName,titleName,displayDiv,outComp){
 
	ewpdModalWindow_ewpd_AM( '../popups/reqParameterPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadConfigurationPopup(queryName,headerName,titleName,displayDiv,outComp){
 
	ewpdModalWindow_ewpd( '../popups/searchSPSMultiSelectPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function deleteAdminMethodGroups(tableId,tableFieldId,targetId,linkName){
	var message = "Are you sure you want to delete?"		
	if(window.confirm(message)){
			getFromDataTableToHidden(tableId,tableFieldId,targetId);
			var link = submitLink(linkName);
	}
	else{			 
		return false;
	}
}

function check() {
	//document.getElementById('createAdminMethodMaintainForm:reqParamDiv').style.visibility="hidden"
if (!document.getElementById('createAdminMethodMaintainForm:reqParmid').value == "" ) 
document.getElementById('createAdminMethodMaintainForm:reqParamButton').click();
}
function checkIsNum(){
//alert('inside checkisnum');
	var adminMethodVal = document.getElementById('createAdminMethodMaintainForm:amNoText').value;
		if (!isNaN(adminMethodVal)){
			return true;		
		}
		else {
			document.getElementById('createAdminMethodMaintainForm:amNoText').value="";
			return false;
		}
}
function isNum(evt){
	
 	var k = document.all ? evt.keyCode : evt.which;
	if ((k == 17 ||(k > 47 && k < 58) || k == 8 || k == 0 || k==13)) {
		return true;
	} else {	
		evt.keyCode = 0;
		return false;
  	}
}
  	
function isSpecialChar(evt){
  	var k = document.all ? evt.keyCode : evt.which;
	if(((k >= 48 && k <= 57) || ( k>= 97 && k<= 122) || ( k>= 65 && k<= 90) || k == 47 || k == 8 || k == 0 || 
    	k == 38 || k ==32 || k == 45 || k == 95 || k == 127 || k == 13)){
		
		evt.returnValue=true;

	}else {
		evt.returnValue = false;
	}	
 }

function setToolTip(){

document.getElementById('createAdminMethodMaintainForm:desc').title=document.getElementById('createAdminMethodMaintainForm:desc').value;

}   
function countChars(id, count) {
	// if the length of the string in the input field is greater than the max value, trim it 
	var field = document.getElementById('createAdminMethodMaintainForm:comments').value.length;
	if (field > count)
	alert(" The comments cannot exceed more than 250 characters");
}

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher_Description","rapidSpellWebLauncher_AdminMethodMaintain"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}

function spellFin(cancelled){
	
		document.getElementById('createAdminMethodMaintainForm:createAdminMethodMaintain').click();
}


					
</script>