<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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
<TITLE>Reserved Contract Create</TITLE>
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
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('reservedContractForm:reserveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<% javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
					httpReq.setAttribute("breadCrumbText","Administration >> Contract Id >> Reserve");
				%>	
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
				<h:form styleClass="form" id="reservedContractForm">
					<TBODY>
						<TR>
							<TD width="230" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:100%;width:230"></DIV>

							</TD>
							<TD colspan="2" valign="top" class="ContentArea"><!--<p class="pageTitle">General Information </p>-->
							<TABLE>
								<TR>
									<TD><w:message
										value="#{reservedContractBackingBean.validationMessages}"></w:message>
									</TD>
								</TR>
							</TABLE>
							<TABLE width="73%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<td width="148">
									<TABLE width="148" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabActive">Basic Information</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:6px;padding-top:6px;width:100%">
							<table width="100%" border="0" cellspacing="2" cellpadding="2">
								<tr>
									<TD colspan="3">
									<FIELDSET
										style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:450">
									<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE border="0" cellspacing="5" cellpadding="1">
										<TR>
											<TD height="25" width="225"><h:outputText id="lineOfBusiness"
												value="Line Of Business*"
												styleClass="#{reservedContractBackingBean.lobInvalid ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
											<h:inputHidden id="lineOfBusinessHidden"
												value="#{reservedContractBackingBean.lineOfBusinessDiv}"></h:inputHidden>
											<TD height="25" width="150">
											<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

											</TD>
											<TD height="25" width="77"><h:commandButton
												alt="lineOfBusiness" id="lineOfBusinessButton"
												image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','reservedContractForm:lineOfBusinessHidden',2,2);	return false;"
												tabindex="1">
											</h:commandButton></TD>
										</TR>
										<TR>
											<TD height="27" width="225"><h:outputText id="businessEntity"
												value="Business Entity*"
												styleClass="#{reservedContractBackingBean.entityInvalid ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
											<h:inputHidden id="businessEntityHidden"
												value="#{reservedContractBackingBean.businessEntityDiv}"></h:inputHidden>
											<TD height="27" width="150">
											<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

											</TD>
											<TD height="27" width="77"><B> <h:commandButton
												alt="businessEntity" id="businessEntityButton"
												image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'Business Entity','businessEntityDiv','reservedContractForm:businessEntityHidden',2,2);	return false;"
												tabindex="2">
											</h:commandButton> </B></TD>
										</TR>
										<TR>
											<TD width="225"><h:outputText id="businessGroup"
												value="Business Group*"
												styleClass="#{reservedContractBackingBean.groupInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
											</TD>
											<h:inputHidden id="businessGroupHidden"
												value="#{reservedContractBackingBean.businessGroupDiv}"></h:inputHidden>
											<TD width="150">
											<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

											</TD>
											<TD width="77"><h:commandButton alt="businessGroup"
												id="businessGroupButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'business group','businessGroupDiv','reservedContractForm:businessGroupHidden',2,2);return false;"
												tabindex="3">
											</h:commandButton></TD>
										</TR>
<%--
										<TR>
											<TD width="225"><h:outputText id="marketSegment"
												value="Market Business Unit"
												styleClass="#{reservedContractBackingBean.marketInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
											</TD>
																					
											<TD align="left" width="285">
												<h:selectOneMenu
													value="#{reservedContractBackingBean.marketSegment}" id="marketSegmentSel" tabindex="4">
			
													<f:selectItems
														value="#{ReferenceDataBackingBeanCommon.marketSegmentList}" />
												</h:selectOneMenu>
											</TD>
										</TR>
--%>
										<TR>
											<TD width="225"><h:outputText id="MarketBusinessUnit"
												value="Market Business Unit*"
												styleClass="#{reservedContractBackingBean.marketInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
											</TD>
											<h:inputHidden id="marketBusinessUnitHidden"
												value="#{reservedContractBackingBean.marketSegment}"></h:inputHidden>
											<TD width="150">
											<div id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></div>

											</TD>
											<TD width="77"><h:commandButton alt="Market Business Unit"
												id="marketBusinessUnitButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'99'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','reservedContractForm:marketBusinessUnitHidden',2,2);return false;"
												tabindex="3">
											</h:commandButton></TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</tr>

							<tr>
							<TD>
							<FIELDSET
										style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:500">
							<TABLE   border="0" cellspacing="0" cellpadding="3">
							<tr>
								<td>
									<h:panelGroup>
										<t:selectOneRadio id="buttons" layout="spread" onclick="hideCheckBox();return true;"
												value="#{reservedContractBackingBean.option}" >
												<f:selectItem id="item1" itemLabel="" itemValue="0" />
												<f:selectItem id="item2" itemLabel="" itemValue="1" />
												<f:selectItem id="item3" itemLabel="" itemValue="2" />
										</t:selectOneRadio>
										<h:panelGrid columns="1">
												<t:radio for="buttons" index="0" />
												<t:radio for="buttons" index="1" />
												<t:radio for="buttons" index="2" />
											</h:panelGrid>
									</h:panelGroup>
								</td>
								<td>
								<TABLE width="90%" border="0" cellspacing="0" cellpadding="3">
										<tr>
											
												<td width="30%"><h:outputText id="fromContractId" value="From Contract Id" styleClass="#{reservedContractBackingBean.fromIdInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
												<td width="8%"><h:inputText  id="fromContractIdInput" styleClass="formInputFieldForContractId" tabindex="6" maxlength="4"
													value="#{reservedContractBackingBean.fromContractId}" tabindex="5"/></td>
												<td width="35%"><h:outputText id="endContractId" value="Through Contract Id" styleClass="#{reservedContractBackingBean.endIdInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
												<td width="8%"><h:inputText id="endContractIdInput" styleClass="formInputFieldForContractId" tabindex="6" maxlength="4"
													 value="#{reservedContractBackingBean.endContractId}" tabindex="6"/></td>
												<td> 
													<TABLE width="10%" border="0" cellspacing="0" cellpadding="3">
													 <tr id="tr1">
														<td  id="checkboxId" align="right" width="15%" ><h:selectBooleanCheckbox styleClass="selectBooleanCheckbox" value="#{reservedContractBackingBean.continuousChecked}" 
												        id="parCheckBox" tabindex="7"></h:selectBooleanCheckbox></td>
												        <td id="checkboxLabel" align="left" width="10%">Consecutive Ids</td>	
													 </tr>
													</TABLE>
												</td>								
											
										</tr>
										<tr>
											
												<td width="30%"><h:outputText id="fromContractId1" value="Start Contract Id" styleClass="#{reservedContractBackingBean.startIdInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
												<td width="8%"><h:inputText id="startContractIdInput" styleClass="formInputFieldForContractId" tabindex="6" maxlength="4"
													value="#{reservedContractBackingBean.startContractId}" tabindex="8"/></td>
												
												<td width="35%"><h:outputText id="endContractId1" value="Number of Id(s) to Generate" styleClass="#{reservedContractBackingBean.noOfIds_1Invalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
												<td width="8%"><h:inputText id="noOfIdsInput"  styleClass="formInputFieldForContractId" tabindex="6" maxlength="4"
													 value="#{reservedContractBackingBean.noOfIds_start}" tabindex="9"/></td>
												
												<td> 
													<TABLE width="10%" border="0" cellspacing="0" cellpadding="3">
													 <tr id="tr2" >
														<td  id="checkboxId1" align="right" width="15%" ><h:selectBooleanCheckbox styleClass="selectBooleanCheckbox" value="#{reservedContractBackingBean.continuousChecked1}"
												        id="parCheckBox1" tabindex="10"></h:selectBooleanCheckbox></td>
												        <td id="checkboxLabel1" align="left" width="10%">Consecutive Ids</td>	
													 </tr>
													</TABLE>
												</td>	
											
										</tr>
										<tr>
											
												<td width="30%"><h:outputText id="fromContractId11" value="Next Available Contract Code" styleClass="#{reservedContractBackingBean.nextAvailableInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
												<td width="8%"><h:inputText id="nextAvailableContract" disabled="true" styleClass="formInputFieldForContractId" tabindex="6"
													  maxlength="4" value="#{reservedContractBackingBean.nextContractID}" tabindex="11"/></td>
																<h:inputHidden id="nextAvailableHidden"
					 								 				value="#{reservedContractBackingBean.nextContractID}" />
												<td width="35%"><h:outputText id="endContractId11" value="Number of Id(s) to Generate" styleClass="#{reservedContractBackingBean.noOfIds_2Invalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
												<td width="8%"><h:inputText id="idGenerated"  styleClass="formInputFieldForContractId" tabindex="6" value="#{reservedContractBackingBean.noOfIds_next}"  tabindex="12"/></td>
												
												<td> 
													<TABLE width="10%" border="0" cellspacing="0" cellpadding="3">
													 <tr id="tr3" >
														<td  id="checkboxId2" align="right" width="15%" >
														<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox" value="#{reservedContractBackingBean.continuousChecked2}"
												        id="parCheckBox2" tabindex="13"></h:selectBooleanCheckbox></td>
												        <td id="checkboxLabel2" align="left" width="10%">Consecutive Ids</td>	
													 </tr>
													</TABLE>
												</td>	
							  		  </tr>
								</TABLE>
								</td>
						   </tr>
						</TABLE>
						</FIELDSET>
									</TD>
								</tr>
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
								<tr>
								<TD colspan=3>
									<TABLE border="0" cellspacing="5" cellpadding="1">
								<TR>
									<TD align="left" width="43%"><h:outputText
										styleClass="#{reservedContractBackingBean.commentsInvalid ? 'mandatoryError': 'mandatoryNormal'}"
										id="commentsId" value="Comments *">

									</h:outputText></TD>
									<TD align="left" width="40%"><h:inputTextarea
											styleClass="formTxtAreaField_Comments"
											id="comment_txt"
											value="#{reservedContractBackingBean.comments}"
											tabindex="14"></h:inputTextarea></TD>
									<td align="left" width="17%"></td>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="reservedContractForm:comment_txt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Comments"
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
									<TD width="43%"><h:outputText
										value="Effective Date*"
										styleClass="#{Invalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="40%"><h:inputText styleClass="formInputField"
										id="startDate_txt" maxlength="10" tabindex="15"
										value="#{reservedContractBackingBean.startDate}"
										onkeypress="return selectStartDatePopUp();" /><BR class="brclass">
										<span class="dateFormat">(mm/dd/yyyy)</span></TD>
									<TD valign="top" width="17%"><A href="#"
										onclick="cal1.select('reservedContractForm:startDate_txt','anchor1','MM/dd/yyyy'); return false;"
										name="anchor1" id="anchor1"
										title="cal1.select('reservedContractForm:startDate_txt','anchor1','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="16" /> </A></TD>
								</TR>
								<TR>
									<TD width="43%"><h:outputText
										value="Expiry Date*"
										styleClass="#{reservedContractBackingBean.endDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="40%"><h:inputText styleClass="formInputField"
										id="endDate_txt" maxlength="10" tabindex="17"
										value="#{reservedContractBackingBean.endDate}"
										onkeypress="return selectEndDatePopUp();" /><BR class="brclass">
										<span class="dateFormat">(mm/dd/yyyy)</span></TD>
									<TD valign="top" width="17%"><A href="#"
										onclick="cal1.select('reservedContractForm:endDate_txt','anchor2','MM/dd/yyyy'); return false;"
										name="anchor2" id="anchor2"
										title="cal1.select('reservedContractForm:endDate_txt','anchor2','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="18" /> </A></TD>
								</TR>
								<TR>

									<TD valign="top" align ="left" width="30%"><h:commandButton
										value="Reserve" styleClass="wpdbutton" id="reserveButton" tabindex="19" 
										onclick="return runSpellCheck();">
										
										</h:commandButton>
									</TD>
									
								</TR>
								<tr>
								</tr>
							   </TABLE>
							</TD>
						   </tr>
	
							</table>
							</FIELDSET>
							</TD>
						</TR>
					</TBODY>
					<h:inputHidden id="allAvailable"
					  value="#{reservedContractBackingBean.allIdsAvailable}" />
					<h:inputHidden id="testCheck"  value="#{reservedContractBackingBean.continuousCheckedHidden}"></h:inputHidden>
					<h:inputHidden id="hiddenContinuous1"
					  value="#{reservedContractBackingBean.continuousChecked1Hidden}" />
					<h:inputHidden id="hiddenContinuous2"
					  value="#{reservedContractBackingBean.continuousChecked2Hidden}" />
					<h:inputHidden id="invalid"
					  value="#{reservedContractBackingBean.validation}" />
					<h:commandLink id="retrieveSysPoolIdLink"
						style="hidden" action="#{reservedContractBackingBean.retrieveContractDetails}">
					</h:commandLink>
					<h:commandLink id="saveSysPoolIdLink"
						style="hidden" action="#{reservedContractBackingBean.saveReservedContract}">
					</h:commandLink>
				
			</TABLE>   
			</td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/pageFooter.jsp"%></td>
		</tr>
	</table></h:form>
	</hx:scriptCollector>
	</BODY>
	<script language="javascript">



var today=new Date();
var startDate = document.getElementById('reservedContractForm:startDate_txt').value;
if(startDate == today.getMonth()+1+"/"+today.getDate()+"/"+(today.getYear()) || startDate==null || startDate == '' ){
	todayStr();	
}
function todayStr() {
var today=new Date();
document.getElementById('reservedContractForm:startDate_txt').value = today.getMonth()+1+"/"+today.getDate()+"/"+(today.getYear());

}

	copyHiddenToDiv_ewpd('reservedContractForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
    copyHiddenToDiv_ewpd('reservedContractForm:businessEntityHidden','businessEntityDiv',2,2); 
    copyHiddenToDiv_ewpd('reservedContractForm:businessGroupHidden','businessGroupDiv',2,2);
    copyHiddenToDiv_ewpd('reservedContractForm:marketBusinessUnitHidden','MarketBusinessUnitDiv',2,2);

var lob = document.getElementById('reservedContractForm:lineOfBusinessHidden').value;
var be = document.getElementById('reservedContractForm:businessEntityHidden').value;
var bg = document.getElementById('reservedContractForm:businessGroupHidden').value;
var bu = document.getElementById('reservedContractForm:marketBusinessUnitHidden').value;
//var marketSeg = document.getElementById('reservedContractForm:marketSegmentSel').value;
if('' == lob){
	var divObjLob = document.getElementById('lineOfBusinessDiv');
	divObjLob.innerText = '--Select--';
}
if('' == be){
	var divObjBe = document.getElementById('businessEntityDiv');
	divObjBe.innerText = '--Select--';
}
if('' == bg){
	var divObjLobBg = document.getElementById('businessGroupDiv');
	divObjLobBg.innerText = '--Select--';
}
if('' == bu){
	var divObjBu = document.getElementById('MarketBusinessUnitDiv');
	divObjBu.innerText = '--Select--';
}

	hideCheckBox();   
	
	checkBoxChecked();
	var allAvailable = document.getElementById('reservedContractForm:allAvailable').value;
	if(allAvailable=="notAvailable"){
		//checkBoxChecked();
		reserveContractNotAvailable();
		//var ret = window.showModalDialog('../contractpopups/reserveContractIdPopUp.jsp'+getUrl()+ '?temp=' + Math.random(),"",'dialogHeight:650px;dialogWidth:800px;resizable=false;status=no;');
		/* checkBoxChecked();
	
		if(ret == 'confirm'){
			document.getElementById('reservedContractForm:saveSysPoolIdLink').click();
		} */
		
	}
	else if(allAvailable=="available"){
		document.getElementById('reservedContractForm:saveSysPoolIdLink').click();
	}


function selectStartDatePopUp(){
	if(window.event.keyCode == 13){
		cal1.select('reservedContractForm:startDate_txt','anchor1','MM/dd/yyyy'); 
		return false;
	}else{
		return true;
	}
}
function selectEndDatePopUp(){
	if(window.event.keyCode == 13){
		cal1.select('reservedContractForm:endDate_txt','anchor1','MM/dd/yyyy'); 
		return false;
	}else{
		return true;
	}
}

function hideCheckBox()
	{
		
		var invalid = document.getElementsByName('reservedContractForm:invalid');
		
		var options = document.getElementsByName('reservedContractForm:buttons');

		if(invalid != "Invalid"){

			//options[0].checked = true;
		}
		if(options[0].checked){
			var input1=document.getElementById('reservedContractForm:startContractIdInput');
			var output1=document.getElementById('reservedContractForm:noOfIdsInput');
			var output2=document.getElementById('reservedContractForm:idGenerated');
			var input3=document.getElementById('reservedContractForm:fromContractIdInput');
			var output3=document.getElementById('reservedContractForm:endContractIdInput');
			var checkBox = document.getElementById('reservedContractForm:parCheckBox');
			checkBox.checked=true;
			checkBox.disabled=true;
			input1.disabled=true; 
			output1.disabled=true; 
			output2.disabled=true; 
			input3.disabled=false; 
			output3.disabled=false; 
			tr2.style.display='none'; 
			tr3.style.display='none'; 
			tr1.style.display=''; 
		}
		else if (options[1].checked){
			var input1=document.getElementById('reservedContractForm:fromContractIdInput');
			var output1=document.getElementById('reservedContractForm:endContractIdInput');
			var output2=document.getElementById('reservedContractForm:idGenerated');
			var input3=document.getElementById('reservedContractForm:startContractIdInput');
			var output3=document.getElementById('reservedContractForm:noOfIdsInput');
			var checkBox = document.getElementById('reservedContractForm:parCheckBox1');
			checkBox.checked=true;
			input1.disabled=true; 
			output1.disabled=true; 
			output2.disabled=true; 
			input3.disabled=false; 
			output3.disabled=false; 
			tr2.style.display=''; 
			tr3.style.display='none'; 
			tr1.style.display='none'; 

		}
		else if (options[2].checked){
			var input1=document.getElementById('reservedContractForm:fromContractIdInput');
			var output1=document.getElementById('reservedContractForm:endContractIdInput');
			var input2=document.getElementById('reservedContractForm:startContractIdInput');
			var output2=document.getElementById('reservedContractForm:noOfIdsInput');
			var output3=document.getElementById('reservedContractForm:idGenerated');
			var checkBox = document.getElementById('reservedContractForm:parCheckBox2');
			checkBox.checked=true;
			input1.disabled=true; 
			output1.disabled=true; 
			input2.disabled=true; 
			output2.disabled=true; 
			output3.disabled=false; 	
			tr2.style.display='none'; 
			tr3.style.display=''; 
			tr1.style.display='none'; 

		}

	}
/*
function retrieveContractDetails(){
	var ret = document.getElementById('reservedContractForm:retrieveSysPoolIdLink').click();
}*/

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
	var options = document.getElementsByName('reservedContractForm:buttons');
	if(options[1].checked){
		var checkBox = document.getElementById('reservedContractForm:parCheckBox1');
		var hiddenCheckBox = document.getElementById('reservedContractForm:hiddenContinuous1');
		hiddenCheckBox.value = checkBox.checked;
		
	}else if(options[2].checked){
		var checkBox = document.getElementById('reservedContractForm:parCheckBox2');
		var hiddenCheckBox = document.getElementById('reservedContractForm:hiddenContinuous2');
		hiddenCheckBox.value = checkBox.checked;
	}
		document.getElementById('reservedContractForm:retrieveSysPoolIdLink').click();
}

function checkBoxChecked(){
	var options = document.getElementsByName('reservedContractForm:buttons');
		if(options[1].checked){
			var test = document.getElementById('reservedContractForm:testCheck');
			var checkBox = document.getElementById('reservedContractForm:parCheckBox1');
			var hiddenCheckBox = document.getElementById('reservedContractForm:hiddenContinuous1');
		
				if(checkBox.checked){
					test.value = true;
				}else {
					test.value = false;
				}
				if(hiddenCheckBox.value == 'true' && test.value == 'true'){
					checkBox.checked = true;
				}else if(hiddenCheckBox.value == 'false' && test.value == 'false'){
					checkBox.checked =  false;
				}else if(hiddenCheckBox.value == 'true' && test.value == 'false'){
					checkBox.checked = 	true;
				}else if(hiddenCheckBox.value == 'false' && test.value == 'true'){
					checkBox.checked = 	false;
				}
		}else if(options[2].checked){
			var test = document.getElementById('reservedContractForm:testCheck');
			var checkBox = document.getElementById('reservedContractForm:parCheckBox2');
			var hiddenCheckBox = document.getElementById('reservedContractForm:hiddenContinuous2');
		
				if(checkBox.checked){
					test.value = true;
				}else {
					test.value = false;
				}
				if(hiddenCheckBox.value == 'true' && test.value == 'true'){
					checkBox.checked = true;
				}else if(hiddenCheckBox.value == 'false' && test.value == 'false'){
					checkBox.checked =  false;
				}else if(hiddenCheckBox.value == 'true' && test.value == 'false'){
					checkBox.checked = 	true;
				}else if(hiddenCheckBox.value == 'false' && test.value == 'true'){
					checkBox.checked = 	false;
				}
		}
}
</script>
</f:view>
</HTML>
