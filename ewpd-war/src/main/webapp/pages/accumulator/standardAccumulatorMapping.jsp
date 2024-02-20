<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
<TITLE>Standard Accumulator Mapping</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery-1.4.2.min.js"></script>
<script language="JavaScript" src="../../MenuResources/ThemeOffice/theme.js"></script>
<script>
function PopupCenter(pageURL, title,w,h) {
var left = (screen.width/2)-(w/2);
var top = (screen.height/2)-(h/2);
var myObject = new Object();
var targetWin = window.open (pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);

}</script>
</HEAD>
<f:view>
	<body>
	<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		<jsp:include page="../navigation/topStyle.jsp"></jsp:include>
		<h:form styleClass="form" id="standardAccumulatorMapForm">
			<tr>
				<td>
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>
							<TD height="16" colspan="3" valign="bottom" bgcolor="#7670B3"><SPAN
								class="breadcrumb">Administration
							&nbsp;&gt;&gt;&nbsp;Standard Accumulator&nbsp;&gt;&gt;&nbsp;<h:outputText
								value="#{standardAccumBackingBean.operation}"/></SPAN></TD>

						</TR>
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"></DIV>
							</TD>
							<TD colspan="2" valign="top" class="ContentArea"><!--<p class="pageTitle">General Information </p>-->
							<TABLE width="400" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TBODY>
									<TR>
										<w:message
											value="#{standardAccumBackingBean.validationMessages}"></w:message>
									</TR>
									<TR>
										<TD width="200">
										<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
											<TBODY>
												<TR>
													<TD width="3" align="left"><IMG
														src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
														width="3" height="21"></TD>
													<TD class="tabActive">Standard Accumulator Mapping</TD>
													<TD width="2" align="right"><IMG
														src="../../images/activeTabRight.gif"
														alt="Tab Right Normal" width="2" height="21"></TD>
												</TR>
											</TBODY>
										</TABLE>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							
							
							<FIELDSET style="margin-left: 6px; margin-right: 6px"><BR>
							
							<table>
								<TR>

										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:400">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="275"><h:outputText id="lineOfBusiness"
													value="Line Of Business*"
													styleClass="#{standardAccumBackingBean.requiredLOB ? 
                               		'mandatoryError': 'mandatoryNormal'}"
													 />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{standardAccumBackingBean.lineOfBusiness}"></h:inputHidden>
												<TD width="160">
												<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="123"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton" image="../../images/select.gif"
													
													
													onclick="ewpdModalWindow_ewpdforlob('../popups/singleSelectLineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','standardAccumulatorMapForm:lineOfBusinessHidden',2,2);fetchFromDB();return false;"
	                                              tabindex="1">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="275"><h:outputText id="businessEntity"
													value="Business Entity*" 
													styleClass="#{standardAccumBackingBean.requiredBE ? 
                               		'mandatoryError': 'mandatoryNormal'}"
													 />
												</TD>
												<h:inputHidden id="businessEntityHidden" 
													value="#{standardAccumBackingBean.be}"></h:inputHidden>
												<TD width="160">
												<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="123"><h:commandButton alt="businessEntity"
													id="businessEntityButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/singleSelectBusinessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','standardAccumulatorMapForm:businessEntityHidden',2,2);fetchFromDB();
																				return false;"
													tabindex="2">
												</h:commandButton></TD>
											</TR>
											
											<TR>
												<TD width="275"><h:outputText id="businessGroup"
													value="Business Group*"
													styleClass="#{standardAccumBackingBean.requiredBG ? 
                               		'mandatoryError': 'mandatoryNormal'}"
													 />
												</TD>
												<h:inputHidden id="businessGroupHidden" 
													value="#{standardAccumBackingBean.group}"></h:inputHidden>
												<TD width="160">
												<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="123"><h:commandButton alt="businessGroup"
													id="businessGroupButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../popups/singleSelectBusinessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','standardAccumulatorMapForm:businessGroupHidden',2,2);fetchFromDB();
																				return false;"
													tabindex="3">
												</h:commandButton></TD>
											</TR>

											 <TR>
												<TD width="275"><h:outputText id="MarketBusinessUnit"
													value="Market Business Unit*"
													styleClass="#{standardAccumBackingBean.requiredMBU ? 
                               						'mandatoryError': 'mandatoryNormal'}"
													 />
												</TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{standardAccumBackingBean.mbu}"></h:inputHidden>
												<TD width="160">
												<DIV id="marketBusinessUnit" class="selectDataDisplayDiv"></DIV>

												</TD>
												<TD width="123"><h:commandButton alt="select"
													id="MarketBusinessUnitButton" image="../../images/select.gif"
													
													onclick="ewpdModalWindow_ewpd('../popups/singleSelectMarketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnit','standardAccumulatorMapForm:marketBusinessUnitHidden',2,2);fetchFromDB();
																				return false;"
													tabindex="4">
												</h:commandButton></TD>
											</TR>
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
							
							</TBODY>
							
							
						<TR>
									<TD colspan="3" style="width:30%" height="0">
									<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
										
							<TR>
									<TD width="30%">&nbsp;&nbsp;<h:outputText id="bntext"
										value="Benefit Name* "
										styleClass="#{standardAccumBackingBean.requiredBEN? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="bnHidden"
										value="#{standardAccumBackingBean.benefitString}"></h:inputHidden>
									<TD width="20%">
									<div id="bnDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="123"><h:commandButton alt="Benefit Name"
										id="bn_Button" image="../../images/select.gif"
										onclick="loadSearchPopup('searchBenefitName','Benefit',
                                                         'Benefit Name Popup','bnDiv','standardAccumulatorMapForm:bnHidden');fetchFromDB();return false;"
										tabindex="5">
									</h:commandButton></TD>
								</TR>
								
										<TR>
											<TD width="30%">&nbsp;&nbsp;<h:outputText id="quesSelect"
												value="Question *" 
												styleClass="#{standardAccumBackingBean.requiredQUES? 'mandatoryError': 'mandatoryNormal'}" /></TD>
											<h:inputHidden id="selectQuesHidden"
												value="#{standardAccumBackingBean.questionString}"></h:inputHidden>
											<TD width="20%">
											<div id="questionSelectDiv" class="selectDataDisplayDiv"></div>
											</TD>
											<TD width="123"><h:commandButton alt="SelectQuestion"
												id="selectQues" image="../../images/select.gif"
												
												onclick="ewpdModalWindow_ewpd('../adminoptionspopups/QuestionPopupForStandardAccumulator.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Question',
												'questionSelectDiv','standardAccumulatorMapForm:selectQuesHidden',2,1
												);fetchFromDB();return false;">
												
											</h:commandButton></TD>

										</TR>
									
							
								<TR>
										<TD height="25" width="30%">&nbsp;&nbsp;<h:outputText
											id="contractType" value="BY/CY "
											/>
										</TD>
										<TD width="20%"><h:selectOneMenu
											id="contractType_txt" styleClass="formInputField"
											tabindex="7"
											value="#{standardAccumBackingBean.byOrCy}"
											onchange="fetchFromDB();"
											tabindex="6">
											<f:selectItems id="contractTypeList"
												value="#{ReferenceDataBackingBeanCommon.contractTypeListForAccumCombo}"/>
												
										</h:selectOneMenu></TD>
									</TR>
									
									
								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{standardAccumBackingBean.requiredAccum ? 
                               		'mandatoryError': 'mandatoryNormal'}"
										id="accumulator" value="Accumulator">
									</h:outputText></TD>
									<TD width="20%">
									<div id="accumDiv" class="selectDataDisplayDiv"></div>
									<h:inputHidden id="accumHidden"
										value="#{standardAccumBackingBean.accumulatorName}"></h:inputHidden>
									</TD>
									<TD width="123"><h:commandButton alt="Select" id="accumulatorButton"
										image="../../images/select.gif" style="cursor:hand"
										onclick="modalWindowforAccumPopup('../accumulator/accumulatorPopup.jsp'+getUrl()+'?hiddenBenefitandQuestionForAccum='+getElementById('standardAccumulatorMapForm:selectQuesHidden').value+'@'+getElementById('standardAccumulatorMapForm:bnHidden').value+'@edit','accumDiv','standardAccumulatorMapForm:accumHidden');return false;">
									</h:commandButton>&nbsp;
									<h:commandButton
										value="ADD" styleClass="wpdbutton" id="addButton" 
										onclick="getMappedAccumsforADD();return false;" type="button"></h:commandButton>
									</TD>

								</TR>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><h:outputText id="claimsysmsg"
										styleClass="messageType" value="" /></td>
								</tr>
								
								
								
								</TABLE>
									</TD>
								</TR>	
							
							<table width="100%">
								<tr>
									<td>
									<div id="searchResultdataTableDiv"
										style="height:100px; overflow:auto; width:100%;">
										<!--  WAS 6.0 Migration Changes - Code changes for Table format issue in  Standard Accumulator -->
										<h:dataTable
										cellspacing="1" rowClasses="dataTableEvenRow,dataTableOddRow" headerClass="fixedDataTableHeader"
										width="100%" id="varDataTable"
										border="0" 
										rendered="#{!empty(standardAccumBackingBean.searchResultList)}"
										value="#{standardAccumBackingBean.searchResultList}"
										var="singleValue" 
										bgcolor="#cccccc">
										
										<h:column>
        										<f:facet name="header">
           										 <h:outputText  value="LOB"/>
        										</f:facet>
        										<h:outputText id="lob" value="#{singleValue.lineOfBusiness}"/>
        										<h:inputHidden id="hiddenLOB" value="#{singleValue.lineOfBusiness}"></h:inputHidden>
    									</h:column>
										
										<h:column>
        										<f:facet name="header">
           										 <h:outputText value="BE"/>
        										</f:facet>
        										<h:outputText id="be" value="#{singleValue.be}"/>
        										
												<h:inputHidden id="hiddenbe" value="#{singleValue.be}"></h:inputHidden>
    									</h:column>
    									
    									<h:column>
        										<f:facet name="header">
           										 <h:outputText value="Group"/>
        										</f:facet>
        										<h:outputText id="group" value="#{singleValue.group}"/>
        										<h:inputHidden id="hiddengroup" value="#{singleValue.group}"></h:inputHidden>
												
    									</h:column>
    									
    										<h:column>
        										<f:facet name="header">
           										 <h:outputText value="MBU"/>
        										</f:facet>
        										<h:outputText id="mbu" value="#{singleValue.mbu}"/>
        										<h:inputHidden id="hiddenmbu" value="#{singleValue.mbu}"></h:inputHidden>
												
    									</h:column>
    									<h:column>
        										<f:facet name="header">
           										 <h:outputText value="Benefit"/>
        										</f:facet>
        										<h:outputText id="benefit" value="#{singleValue.benefitDescription}"/>
        										<h:inputHidden id="hiddenbenefit" value="#{singleValue.benefit}"></h:inputHidden>
												
    									</h:column>
    									<h:column>
        										<f:facet name="header">
           										 <h:outputText value="Question"/>
        										</f:facet>
        										<h:outputText id="question" value="#{singleValue.questionDescription}"/>
        										<h:inputHidden id="hiddenques" value="#{singleValue.question}"></h:inputHidden>
    									</h:column>
    									
    									<h:column>
        										<f:facet name="header">
           										 <h:outputText value="BY/CY"/>
        										</f:facet>
        										<h:outputText id="type" value="#{singleValue.byOrCy}"/>
        										<h:inputHidden id="hiddentype" value="#{singleValue.byOrCy}"></h:inputHidden>
    									</h:column>
    									
    									<h:column>
        										<f:facet name="header">
           										 <h:outputText value="Accumulator Mapped"/>
        										</f:facet>
        										<h:outputText id="acc" value="#{singleValue.standardAccumulatorStr}"/>
        										<h:inputHidden id="hiddenACC" value="#{singleValue.standardAccumulatorStr}"></h:inputHidden>
    									</h:column>
																				
										<h:column>
												<f:facet name="header">
           										 <h:outputText value=" "/>
        										</f:facet>
											<h:commandButton alt="Delete" id="deleteButton"
												image="../../images/delete.gif"
												onclick="delBeAcMap();return false;"></h:commandButton>
										</h:column>
									</h:dataTable></div>
									</td>
								</tr>
							</table>
							<BR>
							<TABLE width="100%" border="0">
								<TBODY>
									<TR>
										<TD width="79"><SPAN
											style="margin-left:6px;margin-right:6px;"> <h:commandButton
											value="Save" styleClass="wpdbutton" id="saveButton"
											action="#{standardAccumBackingBean.saveStdAccumMapping}"
											onclick=""></h:commandButton> </SPAN> <h:inputHidden
											id="buttonDummy" value="#{standardAccumBackingBean.buttonId}"></h:inputHidden>
										<h:inputHidden id="deleteHappened" ></h:inputHidden> <h:inputHidden
											id="successVal" value="#{standardAccumBackingBean.success}"></h:inputHidden>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<BR>

							</FIELDSET>
							</TD>

						</TR>



					</TBODY>
				</TABLE>




				</td>
			</tr>
			<h:inputHidden id="operationInput" value="#{standardAccumBackingBean.operationInput}"></h:inputHidden>
			<h:inputHidden id="operationMode" value="#{lgContractRefDataBean.operationmode}"></h:inputHidden>
			<h:inputHidden id="lobInput" value="#{standardAccumBackingBean.lobInput}"></h:inputHidden>
			<h:inputHidden id="beInput" value="#{standardAccumBackingBean.beInput}"></h:inputHidden>
			<h:inputHidden id="bgInput" value="#{standardAccumBackingBean.bgInput}"></h:inputHidden>
			<h:inputHidden id="mbuInput" value="#{standardAccumBackingBean.mbuInput}"></h:inputHidden>
			<h:inputHidden id="questInput" value="#{standardAccumBackingBean.questInput}"></h:inputHidden>
			<h:inputHidden id="accumInput" value="#{standardAccumBackingBean.accumInput}"></h:inputHidden>
			<h:inputHidden id="typeInput" value="#{standardAccumBackingBean.typeInput}"></h:inputHidden>
			<h:inputHidden id="benefitInput" value="#{standardAccumBackingBean.benefitInput}"></h:inputHidden>
			<h:inputHidden id="lobDelLstInput" value="#{standardAccumBackingBean.lobDelLstInput}"></h:inputHidden>
			<h:inputHidden id="beDelLstInput" value="#{standardAccumBackingBean.beDelLstInput}"></h:inputHidden>
			<h:inputHidden id="bgDelLstInput" value="#{standardAccumBackingBean.bgDelLstInput}"></h:inputHidden>
			<h:inputHidden id="mbuDelLstInput" value="#{standardAccumBackingBean.mbuDelLstInput}"></h:inputHidden>
			<h:inputHidden id="quesDelLstInput" value="#{standardAccumBackingBean.quesDelLstInput}"></h:inputHidden>
			<h:inputHidden id="typeDelLstInput" value="#{standardAccumBackingBean.typeDelLstInput}"></h:inputHidden>
			<h:inputHidden id="accumDelLstInput" value="#{standardAccumBackingBean.accumDelLstInput}"></h:inputHidden>
			<h:inputHidden id="benefitDelLstInput" value="#{standardAccumBackingBean.benefitDelLstInput}"></h:inputHidden>
			<h:inputHidden id="anyModification" value="#{standardAccumBackingBean.anyModification}"></h:inputHidden>
			<h:commandLink id="operationActionInput"
										style="display:none; visibility: hidden;"
										action="#{standardAccumBackingBean.getFetchFromDB}">
										<f:verbatim />
									</h:commandLink>
			
             <h:inputHidden id="tempLOB"></h:inputHidden>
			<h:inputHidden id="tempBE"></h:inputHidden>
			<h:inputHidden id="tempBG"></h:inputHidden>
			<h:inputHidden id="tempMBU"></h:inputHidden>
			<h:inputHidden id="temQUES"></h:inputHidden>
			<h:inputHidden id="tempTYPE"></h:inputHidden>
			<h:inputHidden id="tempACC"></h:inputHidden>
			<h:inputHidden id="tempBEN"></h:inputHidden>
		</h:form>

		<tr>
			<td><%@ include file="../navigation/pageFooter.jsp"%>
			</td>
		</tr>

	</TABLE>
	</body>
	<script language="javascript">
	
	var varInTxt = document.getElementById('standardAccumulatorMapForm:contractType_txt').value;
    var values = varInTxt.split("~");
	copyToScreen(values[0]);
	
	
	
	function getMappedAccums(thisObj){
		if(document.getElementById('standardAccumulatorMapForm:beHidden').value != ""){
	
			var elementId = thisObj.id;
	    	var varText = thisObj.value;
    		var flagvalue = "FETCH";
   	 		var be = document.getElementById('standardAccumulatorMapForm:beHidden').value;
   	 	
	     	document.getElementById('standardAccumulatorMapForm:operationInput').value = flagvalue;
    	 	document.getElementById('standardAccumulatorMapForm:beInput').value = be;
     		document.getElementById('standardAccumulatorMapForm:varInput').value = varText;
     		
     		document.getElementById('standardAccumulatorMapForm:operationActionInput').click();
			}
		}
		
		
    function fetchFromDB(){
    
        if((document.getElementById('standardAccumulatorMapForm:businessEntityHidden').value != "") &&
		(document.getElementById('standardAccumulatorMapForm:lineOfBusinessHidden').value != "")&&
		(document.getElementById('standardAccumulatorMapForm:businessGroupHidden').value != "")&&
		(document.getElementById('standardAccumulatorMapForm:marketBusinessUnitHidden').value != "") &&
		(document.getElementById('standardAccumulatorMapForm:selectQuesHidden').value != "") &&
		(document.getElementById('standardAccumulatorMapForm:bnHidden').value != "") 
		) {
	
	       
			document.getElementById("standardAccumulatorMapForm:accumulatorButton").disabled = true;
			document.getElementById("standardAccumulatorMapForm:lineOfBusinessButton").disabled = true;
			document.getElementById("standardAccumulatorMapForm:businessEntityButton").disabled = true;
			document.getElementById("standardAccumulatorMapForm:businessGroupButton").disabled = true;
			document.getElementById("standardAccumulatorMapForm:MarketBusinessUnitButton").disabled = true;
			document.getElementById("standardAccumulatorMapForm:bn_Button").disabled = true;
			document.getElementById("standardAccumulatorMapForm:selectQues").disabled = true;
			
    		var flagvalue = "FETCH";
    	
   	 	var lob = document.getElementById('standardAccumulatorMapForm:lineOfBusinessHidden').value
    	var be = document.getElementById('standardAccumulatorMapForm:businessEntityHidden').value
    	var bg = document.getElementById('standardAccumulatorMapForm:businessGroupHidden').value
    	var mbu = document.getElementById('standardAccumulatorMapForm:marketBusinessUnitHidden').value
    	var ques = document.getElementById('standardAccumulatorMapForm:selectQuesHidden').value
    	var ben = document.getElementById('standardAccumulatorMapForm:bnHidden').value
    	var type = document.getElementById('standardAccumulatorMapForm:contractType_txt').value
   	 	
   	 	
   	 	
         document.getElementById('standardAccumulatorMapForm:operationInput').value = flagvalue;
     	document.getElementById('standardAccumulatorMapForm:lobInput').value = lob;
     	document.getElementById('standardAccumulatorMapForm:beInput').value = be;
    	 	document.getElementById('standardAccumulatorMapForm:bgInput').value =bg;
    	 	document.getElementById('standardAccumulatorMapForm:mbuInput').value =mbu;
     	document.getElementById('standardAccumulatorMapForm:questInput').value = ques;
     	document.getElementById('standardAccumulatorMapForm:benefitInput').value = ben;
     	document.getElementById('standardAccumulatorMapForm:typeInput').value = type;
     	document.getElementById('standardAccumulatorMapForm:anyModification').value = 'true';
     	document.getElementById('standardAccumulatorMapForm:operationActionInput').click();
			}
		}
		
	function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/benefitindicativepopupForStandardAccumulator.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,1);
}

		
	function getMappedAccumsFromBE(thisVal){
	if(document.getElementById('standardAccumulatorMapForm:autoCompleteVariableHidden').value != ""){
		var flagvalue = "FETCH";
   	 	var be = thisVal;
   	  	var vVal = document.getElementById('standardAccumulatorMapForm:autoCompleteVariableHidden').value
    	var varText = vVal.split("~");
   	   	document.getElementById('standardAccumulatorMapForm:operationInput').value = flagvalue;
     	document.getElementById('standardAccumulatorMapForm:beInput').value = be;
     	document.getElementById('standardAccumulatorMapForm:varInput').value = varText[0];
     	
     	document.getElementById('standardAccumulatorMapForm:operationActionInput').click();
		}
	}

	function getMappedAccumsforADD(){
    	message='More than one accumulator is already mapped as a standard accumulator. Would you like to modify?';
    	var checkflag = 'false';
   	 	if(document.getElementById('standardAccumulatorMapForm:accumHidden').value == ""){
    		alert("Please select an accumulator to map");
    		return false;    
    	}
    
    	var accumInput = document.getElementById('standardAccumulatorMapForm:accumHidden').value
			var replacedaccumVal = accumInput.replace(/~~/g,"~");
			splitVal = replacedaccumVal.split("~");
			var p = 0;
			for(var h = 0; h<splitVal.length; h++)
			 {
			 p = p + 1;
				acToCompare = splitVal[h];	
				if(acToCompare.indexOf('@') > -1){
					acToCompare = acToCompare.substring(0,acToCompare.indexOf('@'));
				}
			}
             if(p > 2){
             checkflag = 'true';
             if(window.confirm(message)){
			//return true;
	          }else
		     return false;
             
             }
      	if(checkflag == 'false'){ 
    	/*if(validateAccumsB4Insert()){
  	   		if(window.confirm(message)){
			//return true;
	   		}else
		     	return false;
    
    	}*/}
    	
											
										
											
    	var flagvalue = "ADD";
    	var lob = document.getElementById('standardAccumulatorMapForm:lineOfBusinessHidden').value
    	var be = document.getElementById('standardAccumulatorMapForm:businessEntityHidden').value
    	var bg = document.getElementById('standardAccumulatorMapForm:businessGroupHidden').value
    	var mbu = document.getElementById('standardAccumulatorMapForm:marketBusinessUnitHidden').value
    	var ques = document.getElementById('standardAccumulatorMapForm:selectQuesHidden').value
    	var accum = document.getElementById('standardAccumulatorMapForm:accumHidden').value
    	var type = document.getElementById('standardAccumulatorMapForm:contractType_txt').value
   	    var ben = document.getElementById('standardAccumulatorMapForm:bnHidden').value
   	 	
   	 	
         document.getElementById('standardAccumulatorMapForm:operationInput').value = flagvalue;
     	document.getElementById('standardAccumulatorMapForm:lobInput').value = lob;
     	document.getElementById('standardAccumulatorMapForm:beInput').value = be;
    	 	document.getElementById('standardAccumulatorMapForm:bgInput').value =bg;
    	 	document.getElementById('standardAccumulatorMapForm:mbuInput').value =mbu;
     	document.getElementById('standardAccumulatorMapForm:questInput').value = ques;
     	document.getElementById('standardAccumulatorMapForm:accumInput').value = accum;
     	document.getElementById('standardAccumulatorMapForm:typeInput').value = type;
     	document.getElementById('standardAccumulatorMapForm:benefitInput').value = ben;
     	document.getElementById('standardAccumulatorMapForm:anyModification').value = 'true';
     	document.getElementById('standardAccumulatorMapForm:accumHidden').value = '';
     	document.getElementById('standardAccumulatorMapForm:operationActionInput').click();

	}



	function deleteMappedAccums(){
    	var flagvalue = "DELETE";
    	var lob = document.getElementById('standardAccumulatorMapForm:lineOfBusinessHidden').value
    	var be = document.getElementById('standardAccumulatorMapForm:businessEntityHidden').value
    	var bg = document.getElementById('standardAccumulatorMapForm:businessGroupHidden').value
    	var mbu = document.getElementById('standardAccumulatorMapForm:marketBusinessUnitHidden').value
    	var ques = document.getElementById('standardAccumulatorMapForm:selectQuesHidden').value
    	var accum = document.getElementById('standardAccumulatorMapForm:accumHidden').value
    	var type = document.getElementById('standardAccumulatorMapForm:contractType_txt').value
    	var ben = document.getElementById('standardAccumulatorMapForm:bnHidden').value

    	var beForDel = document.getElementById('standardAccumulatorMapForm:tempBE').value
    	var accumForDel = document.getElementById('standardAccumulatorMapForm:tempACC').value
    	var lobForDel = document.getElementById('standardAccumulatorMapForm:tempLOB').value
    	var bgForDel = document.getElementById('standardAccumulatorMapForm:tempBG').value
    	var mbuForDel = document.getElementById('standardAccumulatorMapForm:tempMBU').value
    	var quesForDel = document.getElementById('standardAccumulatorMapForm:temQUES').value
    	var typeForDel = document.getElementById('standardAccumulatorMapForm:tempTYPE').value
    	var benForDel = document.getElementById('standardAccumulatorMapForm:tempBEN').value
 
      	 	
     	document.getElementById('standardAccumulatorMapForm:operationInput').value = flagvalue;
     	document.getElementById('standardAccumulatorMapForm:lobInput').value = lob;
     	document.getElementById('standardAccumulatorMapForm:beInput').value = be;
    	 	document.getElementById('standardAccumulatorMapForm:bgInput').value =bg;
    	 	document.getElementById('standardAccumulatorMapForm:mbuInput').value =mbu;
     	document.getElementById('standardAccumulatorMapForm:questInput').value = ques;
     	document.getElementById('standardAccumulatorMapForm:accumInput').value = accum;
     	document.getElementById('standardAccumulatorMapForm:typeInput').value = type;
     	document.getElementById('standardAccumulatorMapForm:benefitInput').value = ben;
     	
     	document.getElementById('standardAccumulatorMapForm:beDelLstInput').value = beForDel;
     	document.getElementById('standardAccumulatorMapForm:accumDelLstInput').value = accumForDel;
     	document.getElementById('standardAccumulatorMapForm:lobDelLstInput').value = lobForDel;
     	document.getElementById('standardAccumulatorMapForm:bgDelLstInput').value = bgForDel;
     	document.getElementById('standardAccumulatorMapForm:mbuDelLstInput').value = mbuForDel;
     	document.getElementById('standardAccumulatorMapForm:quesDelLstInput').value = quesForDel;
     	document.getElementById('standardAccumulatorMapForm:typeDelLstInput').value = typeForDel;
     	document.getElementById('standardAccumulatorMapForm:benefitDelLstInput').value = benForDel;
     	
     	
     	document.getElementById('standardAccumulatorMapForm:anyModification').value = 'true';
     	document.getElementById('standardAccumulatorMapForm:operationActionInput').click();
	}
 
    	
	//copyHiddenToDiv('standardAccumulatorMapForm:accumHidden','accumDiv');
	copyHiddenToDiv_ewpd('standardAccumulatorMapForm:businessEntityHidden','businessEntityDiv',2,1);
	copyHiddenToDiv_ewpd('standardAccumulatorMapForm:lineOfBusinessHidden','lineOfBusinessDiv',2,1);
	copyHiddenToDiv_ewpd('standardAccumulatorMapForm:businessGroupHidden','businessGroupDiv',2,1);
	copyHiddenToDiv_ewpd('standardAccumulatorMapForm:marketBusinessUnitHidden','marketBusinessUnit',2,1);
	copyHiddenToDiv_ewpd('standardAccumulatorMapForm:selectQuesHidden','questionSelectDiv',2,1);
	copyHiddenToDiv_ewpd('standardAccumulatorMapForm:bnHidden','bnDiv',2,1);
	

	function copyToScreen(value1){
	   
		document.getElementById('standardAccumulatorMapForm:contractType_txt').value = value1;
		document.getElementById('standardAccumulatorMapForm:operationMode').value="create";
	}

	
	function validateAccumsB4Insert(){
	        var table = document.getElementById('standardAccumulatorMapForm:varDataTable'),rows = table.getElementsByTagName('tr'),i, j, cells, accumName;
			if(rows.length > 0){
			var beInput = document.getElementById('standardAccumulatorMapForm:beHidden').value
			var replacedVal = beInput.replace(/~~/g,"~");
			splittedVal = replacedVal.split("~");
			for(var j = 0; j<splittedVal.length; j++)
			 {
				beToCompare = splittedVal[j];	
				if(beToCompare.indexOf('@') > -1){
					beToCompare = beToCompare.substring(0,beToCompare.indexOf('@'));
				}
				
				
			   for (i = 0, j = rows.length; i < j; ++i) {
    			cells = rows[i].getElementsByTagName('td');
    			if (!cells.length) {
       			 continue;
    			}
    				beName = cells[0].children[0].innerHTML;
    				if(beToCompare == beName) {
    				   return true;
    				}
		       }
					
			}
			return false;
			}else{
			return false;
			}
		}
</script>
</f:view>
</html>
