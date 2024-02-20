<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Create SubCatalog</TITLE>
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
<BODY onkeypress="return submitOnEnterKey('subCatalogCreateForm:create');">
	<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
		 		<h:inputHidden id="dummy" value="#{subCatalogBackingBean.subCatalogName}"></h:inputHidden>
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="subCatalogCreateForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv" style="height:380px">	
<!-- Space for Tree  Data	-->					

						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<w:message value="#{subCatalogBackingBean.validationMessages}"></w:message> 
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="25%">
		          							<table width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabActive"> <h:outputText value=" General Information"/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="3" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="80%">
									<TBODY>
										<TR>
											<TD colspan=3>
												<FIELDSET style="width:100%">
													<LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE width="100%" border="0">
										<TR>
											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredLob ? 'mandatoryError': 'mandatoryNormal'}"
												id="LobStar" value="Line of Business*" /></TD>
											<TD width="35%">
											<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtLob"
												value="#{subCatalogBackingBean.lob}"></h:inputHidden></TD>
											<TD width="40%"><h:commandButton alt="Select" id="lobButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction=' + '1' + '&parentCatalog='+'Line of Business'+'&number=' + number,'lobDiv','subCatalogCreateForm:txtLob',2,2); return false;"
												tabindex="1"></h:commandButton></TD>
										</TR>
										<TR>
											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredBusinessEntity ? 'mandatoryError': 'mandatoryNormal'}"
												id="BusinessEntityStar" value="Business Entity*" /></TD>
											<TD width="35%">
											<DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtBusinessEntity"
												value="#{subCatalogBackingBean.businessEntity}"></h:inputHidden>
											</TD>
											<TD width="40%"><h:commandButton alt="Select"
												id="BusinessEntityButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','BusinessEntityDiv','subCatalogCreateForm:txtBusinessEntity',2,2); return false;"
												tabindex="2"></h:commandButton></TD>
										</TR>
										<TR>

											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredBusinessGroup ? 'mandatoryError': 'mandatoryNormal'}"
												id="BusinessGroupStar" value="Business Group*" /></TD>
											<TD width="35%">
											<DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtBusinessGroup"
												value="#{subCatalogBackingBean.businessGroup}"></h:inputHidden>
											</TD>
											<TD width="40%"><h:commandButton alt="Select"
												id="BusinessGroupButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','BusinessGroupDiv','subCatalogCreateForm:txtBusinessGroup',2,2); return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>
<!-- CARS START -->						
										<TR>

											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredMarketBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}"
												id="marketBusinessUnitStar" value="Market Business Unit*" /></TD>
											<TD width="35%">
											<DIV id="MarketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{subCatalogBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
											<TD width="40%"><h:commandButton alt="Select"
												id="MarketBusinessUnitButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','MarketBusinessUnitDiv','subCatalogCreateForm:txtMarketBusinessUnit',2,2); return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>
<!-- CARS END -->
									</TABLE>
									</FIELDSET>
											</TD>
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
										<TR>
											<TD width="30%">&nbsp;<h:outputText styleClass="#{subCatalogBackingBean.requiredSubCatalogName ? 'mandatoryError': 'mandatoryNormal'}"  
												id="CatalogNameStar" value="Name*"/> </TD>
											<TD align="left" width="30%"><h:inputText styleClass="formInputField" id="nameTxt" maxlength="30" tabindex="4" value="#{subCatalogBackingBean.subCatalogName}"/> </TD>
											<TD width="40%"></TD>
										</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="subCatalogCreateForm:nameTxt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Sub Catalog Name"
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
											<TD width="30%">&nbsp;<h:outputText
												styleClass="#{subCatalogBackingBean.requiredParentCatalogId ? 'mandatoryError': 'mandatoryNormal'}"
												id="ParentCatalogStar" value="Parent Catalog*" /></TD>
											
											<TD width="34%"><DIV id="ParentCatalogDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtParentCatalog"
												value="#{subCatalogBackingBean.parentCatalogId}"></h:inputHidden>
											<TD align="left" width="30%">&nbsp;<h:commandButton alt="ParentCatalogName"
												id="ParentCatalogButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/selectCatalogPopupForItemCreate.jsp'+getUrl()+'?lookUpAction=' + '1' + '&title=' + 'Parent Catalog Popup','ParentCatalogDiv','subCatalogCreateForm:txtParentCatalog',4,2); return false;"
												tabindex="5"></h:commandButton></TD>
									   </TR>                                       

										<TR>
											<TD width="30%" valign="top">&nbsp;<h:outputText  id = "descriptionStar" value="Description "/> </TD>
											<TD align="left" width="30%"><h:inputTextarea
												styleClass="formTxtAreaField_GeneralDesc" id="txtDescription" 
												value="#{subCatalogBackingBean.description}"
												tabindex="6"></h:inputTextarea></TD>
											<TD width="40%"></TD>
										</TR>
										<RapidSpellWeb:rapidSpellWebLauncher
											id="rapidSpellWebLauncher2"
											textComponentName="subCatalogCreateForm:txtDescription"
											rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Sub Catalog Description"
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
											<TD width="30%">
												<h:commandButton value="Create" id="create"
												styleClass="wpdButton" onclick="return validateName() && validateDesc() && runSpellCheck();" tabindex="7">
											</h:commandButton>
											<h:commandLink id="createSubCatalog"
												style="hidden" action="#{subCatalogBackingBean.create}">
											</h:commandLink>
											
											</TD>
											
										</TR>
									</TBODY>
									</TABLE>
<!--	End of Page data	-->
								</fieldset>		
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->



<!-- End of hidden fields  -->

				</h:form>
			</TD>
		</tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	</table>
</BODY>
</f:view>
<!-- space for script -->
<script>
document.getElementById('subCatalogCreateForm:nameTxt').focus(); // for on load default selection
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
 var nameValidationMessage = "Name allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateName(){
	if(validateChars('subCatalogCreateForm:nameTxt',nameValidationMessage))
			return true;
		else{
			document.getElementById('subCatalogCreateForm:nameTxt').focus();
			return false;
		}
}
 var descValidationMessage = "Description allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateDesc(){
	if(validateChars('subCatalogCreateForm:txtDescription',descValidationMessage))
			return true;
		else{
			document.getElementById('subCatalogCreateForm:txtDescription').focus();
			return false;
		}
}
function spellFin(cancelled){

		document.getElementById('subCatalogCreateForm:createSubCatalog').click();	
}
	var number = Math.random();
	copyHiddenToDiv_ewpd('subCatalogCreateForm:txtLob','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogCreateForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogCreateForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogCreateForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogCreateForm:txtParentCatalog','ParentCatalogDiv',3,2 );
  	</script>
</HTML>












