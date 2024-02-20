<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<TITLE>Edit Admin Method</TITLE>
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

	<BODY
		onkeypress="return submitOnEnterKey('customMessageCreateForm:createButton');"><hx:scriptCollector id="scriptCollector2">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
               	
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="editAdminForm">
					<TABLE border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data    --></DIV>
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
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
								<TR>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:outputText value=" General Information" /></TD>
											<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="3" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<FIELDSET>

							<TABLE border="0" cellspacing="9" cellpadding="0" class="outputText">
								<TBODY>

									<TR>
										<TD width="25%">&nbsp;<h:outputText id="amNoStar" value="Admin Method Number*" /></TD>
										<TD width="25%"><h:outputText id="amNoText" value="#{adminMethodMaintainBackingBean.adminMethodNo}"></h:outputText>

										<h:inputHidden id="hiddenAdminNo" value="#{adminMethodMaintainBackingBean.adminMethodNo}"></h:inputHidden>
										<h:inputHidden id="hiddenAdminSysIdAll" value="#{adminMethodMaintainBackingBean.adminMethodSysIdAll}"></h:inputHidden>
										<h:inputHidden id="hiddenAdminSysId" value="#{adminMethodMaintainBackingBean.adminMethodSysId}"></h:inputHidden></TD>
										
										<TD width="50%"><f:verbatim></f:verbatim></TD>

									</TR>

									<TR>
										<TD width="25%">&nbsp;<h:outputText styleClass="#{adminMethodMaintainBackingBean.descriptionReq? 'mandatoryError': 'mandatoryNormal'}" id="amDesc" value="Admin Method Description*" /></TD>
										<TD width="25%"><h:inputText id="desc" title="" onmouseover="return setToolTip();" styleClass="formInputField" value="#{adminMethodMaintainBackingBean.description}" onkeypress="return isSpecialChar(event);" tabindex="9"></h:inputText> 
											</TD>
										<TD width="50%"><f:verbatim></f:verbatim></TD>
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
										textComponentName="editAdminForm:desc"
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
										<TD width="27%">&nbsp;<h:outputText id="BenefitTypeStar" value="Processing Method*" /></TD>
										<TD width="29%"><h:outputText id="txtProcessingMethod" value="#{adminMethodMaintainBackingBean.processMethodDesc}"></h:outputText>
										<h:inputHidden id="hiddenProcessDesc" value="#{adminMethodMaintainBackingBean.processMethodDesc}"></h:inputHidden></TD>
										<h:inputHidden id="hiddenProcessMethod" value="#{adminMethodMaintainBackingBean.processMethod}"></h:inputHidden><TD></TD>

									</TR>
									<TR>
										<TD width="25%">&nbsp;<h:outputText id="config" value="Configuration" /></TD>
										<TD width="28%">
										<DIV id="configDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="configid" value="#{adminMethodMaintainBackingBean.configuration}"></h:inputHidden>
										<h:inputHidden id="reqParamsList" value="#{adminMethodMaintainBackingBean.reqParamsAll}"></h:inputHidden>
										</TD>
										<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select" id="RuleButton" image="../../images/select.gif" style="cursor: hand" onclick="loadConfigurationPopup('searchSPSDetail','Reference','Configuration Popup','configDiv','editAdminForm:configid');return false;
                                                                   "></h:commandButton>
										</TD>
										<TD width="41%"></TD>
									</TR>

									<TR>
										<TD width="25%" valign="top">&nbsp;<h:outputText styleClass="#{adminMethodMaintainBackingBean.commentsReq? 'mandatoryError': 'mandatoryNormal'}" id="commentsLabel" value="Comments" />
										<h:inputHidden id="hiddenComments" value="#{adminMethodMaintainBackingBean.comments}"></h:inputHidden></TD>

										<TD width="25%"><h:inputTextarea styleClass="formTxtAreaField_GeneralDesc" id="comments" value="#{adminMethodMaintainBackingBean.comments}" tabindex="9">
										</h:inputTextarea></TD>
										<TD width="50%"><f:verbatim></f:verbatim></TD>
									</TR>																							
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher_AdminMethodMaintain"
										textComponentName="editAdminForm:comments"
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
										<TD colspan="2">
										<FIELDSET id="fieldsetID"><LEGEND><FONT color="black">Required
										Parameter</FONT></LEGEND>

										<TABLE border="0">
											<TR>
												<TD width="78%">

												<FIELDSET style="margin:0pxauto;text-align:left;padding:10px;border:0px;">

												<DIV id="reqParamDiv1" style="margin:0pxauto;text-align:left;padding:10px;border:0px;">
												Add Required Parameter Group</DIV>

												
												</FIELDSET>
												<h:inputHidden id="reqParmid" value="#{adminMethodMaintainBackingBean.reqParams}"></h:inputHidden>
												</TD>
												<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select" styleClass="wpdbutton" id="ReqButton" value="Add" onclick="loadReqParameterPopup('searchSPSDetail','Reference',
                                                         'Required Parameter Popup','reqParamDiv','editAdminForm:reqParmid');check();return false;"></h:commandButton>
												</TD>
												<TD width="42%"></TD>
											</TR>
											<TR style="display:none"> 
												<DIV id="reqParamDiv" style="display:none; visibility: hidden;"></DIV>
											</TR>
										</TABLE>

									<t:dataTable id="dt2" value="#{adminMethodMaintainBackingBean.reqParamGroups}" headerClass="dataTableHeader"
									rendered="#{adminMethodMaintainBackingBean.reqParamGroups != null}" binding="#{adminMethodMaintainBackingBean.editDataTable}" 
									var="reqParam" width="95%" cellpadding="3" cellspacing="3" border="0" styleClass="requiredParam" columnClasses = "param,icon">
											<t:column>
												<t:outputText value="#{reqParam}"></t:outputText>
												<t:inputHidden id="productKey" value="#{adminMethodMaintainBackingBean.editDataTable.rowIndex}"></t:inputHidden>
											</t:column>
											<t:column>
												<t:commandButton alt="Delete" id="deleteButton" image="../../images/delete.gif" style="align:right;" 
													onclick=" deleteAdminMethodGroups('editAdminForm:dt2','productKey','editAdminForm:selectedProductKey','editAdminForm:deleteLink');return false"></t:commandButton>													
											</t:column>
										</t:dataTable>
										</FIELDSET>
										</TD>
									</TR>
									<TR>						

										<TABLE border="0" cellspacing="7" cellpadding="0" class="outputText">
											<TR>
												<TD valign="top" width="25%"><SPAN class="mandatoryNormal" id="creationDateId">&nbsp;</SPAN><h:outputText value="Created By" /></TD>
												<TD width="25%"><h:outputText value="#{adminMethodMaintainBackingBean.createdUser}" /> <h:inputHidden id="createdUserHidden" value="#{adminMethodMaintainBackingBean.createdUser}" /></TD>
												<TD width="50%"><f:verbatim></f:verbatim></TD>
											</TR>


											<TR>
												<TD valign="top" width="27%"><SPAN class="mandatoryNormal" id="createdBy">&nbsp;</SPAN><h:outputText value="Created Date" /></TD>
												<TD width="29%"><h:outputText value="#{adminMethodMaintainBackingBean.createdDate}">
													<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
												</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText> <h:inputHidden id="createdTimestampHidden" value="#{adminMethodMaintainBackingBean.createdDate}">
													
												</h:inputHidden> <h:inputHidden id="time" value="#{requestScope.timezone}">
												</h:inputHidden></TD>
												<TD width="44%"><f:verbatim></f:verbatim></TD>
											</TR>

											<TR>
												<TD valign="top" width="27%"><SPAN class="mandatoryNormal" id="updationDate">&nbsp;</SPAN><h:outputText value="Last Updated By" /></TD>
												<TD width="29%"><h:outputText value="#{adminMethodMaintainBackingBean.lastUpdatedUser}" />
												<h:inputHidden id="lastUpdatedUserHidden" value="#{adminMethodMaintainBackingBean.lastUpdatedUser}" /></TD>
												<TD width="44%"><f:verbatim></f:verbatim></TD>
											</TR>


											<TR>
												<TD valign="top" width="25%"><SPAN class="mandatoryNormal" id="updateBy">&nbsp;</SPAN><h:outputText value="Last Updated Date" /></TD>
												<TD width="25%"><h:outputText value="#{adminMethodMaintainBackingBean.lastUpdatedDate}">
													<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
												</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText> <h:inputHidden id="lastUpdatedTimestampHidden" value="#{adminMethodMaintainBackingBean.lastUpdatedDate}">
													
												</h:inputHidden></TD>
												<TD width="50%"><f:verbatim></f:verbatim></TD>
											</TR>
											<TD width="25%">&nbsp; <h:commandButton value="Save" id="saveButton" styleClass="wpdButton" onclick="return checkQuestionAnswerChangedAndSubmit(this.id);"
												tabindex="15"></h:commandButton></TD>											
										</TABLE>
									</TR>
									<TR>

										<TD width="25%">&nbsp;<h:commandButton value="Req Param" id="reqParamButton" style="display:none; visibility: hidden;" action="#{adminMethodMaintainBackingBean.addRequiredParameter}" tabindex="15">
										</h:commandButton></TD>
                                        <td><DIV id="reqPramModifiedDiv" style="display:none; visibility: hidden;"><h:outputText
													value="#{adminMethodMaintainBackingBean.reqParamModifiedStaus}" /></DIV></td>
										
									</TR>
								</TBODY>
							</TABLE>
					
							</FIELDSET>
							<!--  End of Page data  --></TD>
						</TR>
					</TABLE>
						
					<h:commandLink id="saveAdminMethodMaintain"
						style="hidden" action="#{adminMethodMaintainBackingBean.saveAdminMethod}">
					</h:commandLink>

					<h:inputHidden id="selectedProductKey" value="#{adminMethodMaintainBackingBean.selectedRowNumber}" />
                    <h:inputHidden id="hiddenDescription" value="#{adminMethodMaintainBackingBean.hiddenDescription}" />
					<h:inputHidden id="hiddenConfiguration" value="#{adminMethodMaintainBackingBean.hiddenConfiguration}" />
					<h:inputHidden id="commentsHidden" value="#{adminMethodMaintainBackingBean.hiddenComments}" />					
                    <h:inputHidden id="questionAnswerModifiedStatus" ></h:inputHidden>
					<h:commandLink id="deleteLink" style="display:none; visibility: hidden;" action="#{adminMethodMaintainBackingBean.deleteGroup}">
					</h:commandLink>


				</h:form>
			</TD></TR><TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</hx:scriptCollector>
<form name="printForm"><input id="currentPrintPage" type="hidden"
		name="currentPrintPage" value="adminMethodEditPrint"></form>
</BODY>
</f:view>



<script>
copyHiddenToDiv_ewpd('editAdminForm:configid','configDiv',2,2);
copyHiddenToDiv_ewpd('editAdminForm:reqParmid','reqParamDiv',2,2);

function loadSPSSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
      ewpdModalWindow_ewpd( '../popups/SearchReferencePopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}


function loadReqParameterPopup(queryName,headerName,titleName,displayDiv,outComp){      
      ewpdModalWindow_ewpd_AM( '../popups/reqParameterPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
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

function isNumeric() {
var elem = document.getElementById('editAdminForm:amNoText');

var numericExpression = /^[0-9]+$/;

if(elem.value.match(numericExpression)){        
            return true;
      }else{      
            elem.value = elem.value.substring(0, elem.value.length-1);        
            elem.focus();
            return true;
      }
}   

function setToolTip(){

document.getElementById('editAdminForm:desc').title=document.getElementById('editAdminForm:desc').value;

}                   

function check() {
      //document.getElementById('editAdminForm:reqParamDiv').style.visibility="hidden"
if (!document.getElementById('editAdminForm:reqParmid').value == "" ) 
document.getElementById('editAdminForm:reqParamButton').click();
}
function deleteGroup(test) {
      //document.getElementById('editAdminForm:reqParamDiv').style.visibility="hidden"
	var  t = document.getElementById('editAdminForm:selectedRowIndex2');
	document.getElementById('editAdminForm:deleteButton').click();

}

function loadConfigurationPopup(queryName,headerName,titleName,displayDiv,outComp){ 
	ewpdModalWindow_ewpd( '../popups/searchSPSMultiSelectPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function isSpecialChar(evt){
  	var k = document.all ? evt.keyCode : evt.which;
	if(((k >= 48 && k <= 57) || ( k>= 97 && k<= 122) || ( k>= 65 && k<= 90) || k == 47 || k == 8 || k == 0 || 
    	k == 38 || k ==32 || k == 45 || k == 95 || k == 127 )){
		
		evt.returnValue=true;

	}else {
		evt.returnValue = false;
	}	
 }
function countChars(id, count) {
// if the length of the string in the input field is greater than the max value, trim it 
	var field = document.getElementById('editAdminForm:comments').value.length;
	if (field > count)
	alert(" The comments cannot exceed more than 250 characters");
}


function checkQuestionAnswerChangedAndSubmit(objectId){
  
   document.getElementById('editAdminForm:questionAnswerModifiedStatus').value=document.getElementById('reqPramModifiedDiv').innerHTML;
   var obj = document.getElementById(objectId);
	if(!isEwpdbDataModifed())
	{
		alert('No modifications to be updated.');
		return false;
	}else {
		return runSpellCheck();
	}
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
	document.getElementById('editAdminForm:saveAdminMethodMaintain').click();
}
</script>

<form name="ewpdDataChangedForm">
<input type="hidden" name="ewpdOnloadData" value="" />
<script>
document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
</script> 
</form>