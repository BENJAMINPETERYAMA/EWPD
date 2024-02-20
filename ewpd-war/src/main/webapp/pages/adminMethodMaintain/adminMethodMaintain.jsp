<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
}
.adminDescriptionColumn {
}
.processingMethodColumn {
}
.configurationColumn {
text-align: center;

}
.requiredParameterColumn {
text-align: center;

}
.viewColumn {
}
</style>

<TITLE>Admin Method Search</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>



</HEAD>

<f:view>
	<BODY
		onkeydown="return submitOnEnterKey('AdminMethodSearchForm:locateButton');">
	<table width="100%" cellpadding="0" cellspacing="0">


		<tr>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="AdminMethodSearchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>

						<TR>
							<td valign="top" class="ContentArea">
							<TABLE width="100%">
								<TR>
									<TD width="100%" colspan="2"><w:message
										value="#{adminMethodMaintainBackingBean.validationMessages}"></w:message></TD>
								</TR>
							</TABLE>

							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<TR>
									<TD width="200"></TD>
									<TD width="200"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->


							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><BR>

							<!--	Start of Table for actual Data	-->
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>

									<TR>
										<TD width="15%"><h:outputText styleClass="#{adminMethodMaintainBackingBean.adminMethodNoReq? 'mandatoryError': 'mandatoryNormal'}" id="adminMethodNoTxt"
											value="Admin Method Number"></h:outputText></TD>
										<TD colspan="15%"><h:inputText styleClass="formInputField"
											id="txtAdminMethodNo" maxlength="5" tabindex="1" 
											value="#{adminMethodMaintainBackingBean.locateAdminMethodNo}" onkeypress="return isNumeric(event);"></h:inputText>&nbsp;</TD>
										<td width="5%"></td>

									</TR>

									<TR>
										<TD width="15%"><h:outputText id="adminMethodDescTxt"
											styleClass="#{adminMethodMaintainBackingBean.descriptionReq? 'mandatoryError': 'mandatoryNormal'}"
											value="Admin Method Description"></h:outputText></TD>

										<TD colspan="15%"><h:inputText styleClass="formInputField" title="" onmouseover="return setToolTip();"
											id="txtAdminMethodDesc" value="#{adminMethodMaintainBackingBean.adminMethoddescription}"
											onkeypress="return isSpecialChar(event);" tabindex="2"></h:inputText>&nbsp;</TD>
										<td width="5%"></td>
									</TR>

									<TR>
										<TD width="15%"><h:outputText value="Processing Method" /></TD>
										<TD width="10%">
										<DIV id="processingMethodDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtProcessingMethod"
											value="#{adminMethodMaintainBackingBean.selectedProcessMethod}"></h:inputHidden>
										</TD>
										<TD colspan="1%">&nbsp;<h:commandButton
											alt="Select" title="Select" id="RuleButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadMultiSearchPopup('processingType','Processing method',
                                                         'Processing method Popup','processingMethodDiv','AdminMethodSearchForm:txtProcessingMethod');return false;
											 "></h:commandButton></TD>
									</TR>

									<TR>
										<TD colspan="3">&nbsp;</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="locateButton" value="Locate"
											style="cursor: hand"
											action="#{adminMethodMaintainBackingBean.locateAdminMethods}"
											tabindex="9"></h:commandButton></TD>

									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							</DIV>



							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
						         <h:commandLink id="getValuesForEdit"
							                     style="display:none; visibility: hidden;"
							                    action="#{adminMethodMaintainBackingBean.editAdminMethod}">
							
								</h:commandLink>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="fixedDataTableHeader"
										id="searchResultTable" var="AdminMethod" cellpadding="3"
										cellspacing="1" bgcolor="#cccccc"
										columnClasses=" selectOrOptionColumn, adminDescriptionColumn, processingMethodColumn, configurationColumn, requiredParameterColumn, viewColumn"
										rendered="#{adminMethodMaintainBackingBean.searchResults != null}"
										value="#{adminMethodMaintainBackingBean.searchResults}"
										width="100%">
										<h:column>
											<f:facet name="header">
														<f:verbatim>Admin Method Number</f:verbatim>
											</f:facet>	
											<h:inputHidden id="hiddenAdminMethodSysId"
												value="#{AdminMethod.adminMethodSysId}"></h:inputHidden>

											<h:inputHidden id="hiddenAdminMethodSysIdAll"
												value="#{AdminMethod.adminMethodSysIdAll}"></h:inputHidden>

											<h:inputHidden id="hiddenAdminMethodNo"
												value="#{AdminMethod.adminMethodNo}"></h:inputHidden>

											<h:outputText id="adminMethodNo"
												value="#{AdminMethod.adminMethodNo}"></h:outputText>
										</h:column>

										<h:column>
											<f:facet name="header">
													<f:verbatim>Admin Method Description</f:verbatim>
											</f:facet>		
											<h:outputText id="description"
												value="#{AdminMethod.description}"></h:outputText>
											<h:inputHidden id="hiddenDescription"
												value="#{AdminMethod.description}"></h:inputHidden>
										</h:column>

										<h:column>
											<f:facet name="header">
													<f:verbatim>Processing Method</f:verbatim>
											</f:facet>
											<h:outputText id="processMethod"
												value="#{AdminMethod.processMethodDesc}"></h:outputText>
											<h:inputHidden id="hiddenProcessMethodDesc"
												value="#{AdminMethod.processMethodDesc}"></h:inputHidden>
											<h:inputHidden id="hiddenProcessMethod"
												value="#{AdminMethod.processMethod}"></h:inputHidden>

										</h:column>

										<h:column > 
											<f:facet name="header">
													<f:verbatim>Configuration Set</f:verbatim>
											</f:facet>
											
											<h:outputText id="configuration" style="align:center"
												value="#{AdminMethod.configuration}"></h:outputText>
										</h:column>
										<h:column>
											<f:facet name="header">
													<f:verbatim>Required Parameter</f:verbatim>
											</f:facet>
											
											<h:outputText id="requiredParm"
												value="#{AdminMethod.requiredParm}"></h:outputText>

										</h:column>

										<h:column>
											<f:facet name="header">
													<f:verbatim>&nbsp</f:verbatim>
											</f:facet>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction1();return false;" rendered="#{adminMethodMaintainBackingBean.viewableForUser}"></h:commandButton>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>



											<h:commandButton alt="Edit" title="Edit" id="basicEdit"
												image="../../images/edit.gif" value="Edit" rendered="#{adminMethodMaintainBackingBean.editableForUser}"
												onclick="getValuesForEdit();return false;">
											</h:commandButton>



											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete" rendered="#{adminMethodMaintainBackingBean.deletableForUser}"
												onclick="deleteAdminMethods();return false;">
											</h:commandButton>

										</h:column>
									</h:dataTable></div>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
						</TR>
				</TABLE></td>
		</tr>
		<h:commandLink id="deleteAM" style="display:none; visibility: hidden;"
							style="display:none; visibility: hidden;"
							action="#{adminMethodMaintainBackingBean.deleteAdminMethod}">
							</h:commandLink>

		<h:inputHidden id="hiddenAdminMethodSysIdForView"
			value="#{adminMethodMaintainBackingBean.adminMethodSysId}"></h:inputHidden>

		<h:inputHidden id="hiddenAdminMethodSysIdAllForView"
			value="#{adminMethodMaintainBackingBean.adminMethodSysIdAll}"></h:inputHidden>

		<h:inputHidden id="hiddenAdminMethodNoForView"
			value="#{adminMethodMaintainBackingBean.adminMethodNo}"></h:inputHidden>

		<h:inputHidden id="hiddenDescriptionForView"
			value="#{adminMethodMaintainBackingBean.description}"></h:inputHidden>

		<h:inputHidden id="hiddenProcessMethodDescForView"
			value="#{adminMethodMaintainBackingBean.processMethodDesc}"></h:inputHidden>

		<h:inputHidden id="hiddenProcessMethodForView"
			value="#{adminMethodMaintainBackingBean.processMethod}"></h:inputHidden>
        <h:inputHidden id="hiddenAdminMethodDesc"
			value="#{adminMethodMaintainBackingBean.hiddenDescription}"></h:inputHidden>
		<tr>
			<td></h:form> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	<form name="printForm"><input id="currentPrintPage" type="hidden"
		name="currentPrintPage" value="AdminMethodSearchResultPrint"></form>
	</BODY>

</f:view>
<script language="javascript">
copyHiddenToDiv_ewpd('AdminMethodSearchForm:txtProcessingMethod','processingMethodDiv',2,2);

function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchProcessingMethodPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}
function setToolTip(){

document.getElementById('AdminMethodSearchForm:txtAdminMethodDesc').title=document.getElementById('AdminMethodSearchForm:txtAdminMethodDesc').value;

}   

function viewAction1(){
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodSysId','AdminMethodSearchForm:hiddenAdminMethodSysIdForView');
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodSysIdAll','AdminMethodSearchForm:hiddenAdminMethodSysIdAllForView');
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodNo','AdminMethodSearchForm:hiddenAdminMethodNoForView');
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenDescription','AdminMethodSearchForm:hiddenDescriptionForView');
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenProcessMethodDesc','AdminMethodSearchForm:hiddenProcessMethodDescForView');

	var hiddenAdminMethodSysId  = document.getElementById('AdminMethodSearchForm:hiddenAdminMethodSysIdForView').value; 
	var hiddenAdminMethodSysIdAll  = document.getElementById('AdminMethodSearchForm:hiddenAdminMethodSysIdAllForView').value; 
	var hiddenAdminMethodNo  = document.getElementById('AdminMethodSearchForm:hiddenAdminMethodNoForView').value; 
    var hiddenDescription  = document.getElementById('AdminMethodSearchForm:hiddenDescriptionForView').value;    
    var hiddenProcessMethodDesc  = document.getElementById('AdminMethodSearchForm:hiddenProcessMethodDescForView').value;
	
 	var url = '../adminMethodMaintain/adminMethodView.jsp'+getUrl()+'?adminMethodSysIdAll='+hiddenAdminMethodSysIdAll+ '&adminMethodNo='+hiddenAdminMethodNo+'&adminMethodSysId='+hiddenAdminMethodSysId+ '&description=' +hiddenDescription+ '&processMethodDesc='+hiddenProcessMethodDesc; 
	window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");		 

}

function getValuesForEdit(){

	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodSysId','AdminMethodSearchForm:hiddenAdminMethodSysIdForView');
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodSysIdAll','AdminMethodSearchForm:hiddenAdminMethodSysIdAllForView');
	getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodNo','AdminMethodSearchForm:hiddenAdminMethodNoForView');
    getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenDescription','AdminMethodSearchForm:hiddenDescriptionForView');
    getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenProcessMethod','AdminMethodSearchForm:hiddenProcessMethodForView');

	submitLink('AdminMethodSearchForm:getValuesForEdit');
}

function deleteAdminMethods(){
	var message = "Are you sure you want to delete?"		
		if(window.confirm(message)){
			getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodSysId','AdminMethodSearchForm:hiddenAdminMethodSysIdForView');
			getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenAdminMethodNo','AdminMethodSearchForm:hiddenAdminMethodNoForView');
    		getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenDescription','AdminMethodSearchForm:hiddenDescriptionForView');
			getFromDataTableToHidden('AdminMethodSearchForm:searchResultTable','hiddenProcessMethod','AdminMethodSearchForm:hiddenProcessMethodForView');	
		    submitLink('AdminMethodSearchForm:deleteAM');
		}
		else{			 
			return false;
		}
}

var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'329',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('AdminMethodSearchForm:searchResultTable') != null) {
         
			setColumnWidth('AdminMethodSearchForm:searchResultTable','10%:26%:16%:16%:16%:16%');
			showResultsTab();
		}
				
document.getElementById('AdminMethodSearchForm:txtAdminMethodNo').focus(); // for on load default selection

	// For checking the key pressed is digit 
  function isNumeric(evt){	

  	var k = document.all ? evt.keyCode : evt.which;
	if ((k == 17 ||(k > 47 && k < 58) || k == 8 || k == 0 || k==13)) {
		return true;
	} else {	
		evt.keyCode = 0;
		return false;
  	}
}
	function checkIsNum(){

	var adminMethodVal = document.getElementById('AdminMethodSearchForm:txtAdminMethodNo').value;
		if (!isNaN(adminMethodVal)){
			return true;		
		}
		else {
			document.getElementById('AdminMethodSearchForm:txtAdminMethodNo').value="";
			return false;
		}
}
//For checking the key pressed is special character
function isSpecialChar(evt){
	var k = document.all ? evt.keyCode : evt.which;
    return ((k >= 48 && k <= 57) || ( k>= 97 && k<= 122) || ( k>= 65 && k<= 90) || k == 47 || k == 8 || k == 0 || 
        k == 38 || k ==32 || k == 45 || k == 95 || k == 127 );	
}
</script>
</HTML>
