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

<TITLE>Admin Method Mapping Search</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('searchForm:basicSearch');">
	<table width="100%" cellpadding="0" cellspacing="0">


		<tr>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="searchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>

						<TR>
							<td valign="top" class="ContentArea">
							<TABLE width="100%">
								<TR>
									<TD width="100%" colspan="2"><w:message></w:message></TD>
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

							<!--Tab Ends-->
							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><br />
							<TABLE width="500" cellpadding="2" border="0" class="outputText">
								<TBODY>
									<TR>
										<TD width="30%"><h:outputText styleClass="#{adminMethodMappingBackingBean.adminMethodNoReq? 'mandatoryError': 'mandatoryNormal'}" id="config"
											value="Admin Method Number" /></TD>
										<TD width="28%">
										<h:inputText id="adminNoId" 
											styleClass="formInputField"	maxlength="5"					
											value="#{adminMethodMappingBackingBean.adminMethodNoCriteria}"										
											tabindex="2" onkeypress="return isNum(event);"></h:inputText>
										
										</TD>
										<TD width="6%">
										</TD>
										<TD width="36%"></TD>

									</TR>
									<TR>
										<TD width="50%"><h:outputText id="Description"
											styleClass="#{adminMethodMappingBackingBean.descriptionReq? 'mandatoryError': 'mandatoryNormal'}"
											value="Admin Method Description" /></TD>
										<TD width="28%">
										<h:inputText id="admindesc"
											styleClass="formInputField"	styleClass="formInputField" title="" onmouseover="return setToolTip();"		
			
											value="#{adminMethodMappingBackingBean.descriptionCriteria}"										
											tabindex="2" onkeypress="return isSpecialChar(event);"></h:inputText>
										
										</TD>
										<TD width="22%"></TD>
									</TR>

									<TR>
										<TD width="30%"><h:outputText value="Processing Method" /></TD>
										<TD width="28%">
										<DIV id="processingMethodDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtProcessingMethod"
											value="#{adminMethodMappingBackingBean.processMethod}"></h:inputHidden>
										</TD>
										<TD colspan="6%">&nbsp;&nbsp;<h:commandButton alt="Select" title="Select" 
											id="RuleButton1" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadMultiSearchPopup('processingTypeSearch','ProcessingMethod',
                                                         'Processing method Popup','processingMethodDiv','searchForm:txtProcessingMethod');return false;
											 "></h:commandButton></TD>
										<TD width="36%"></TD>
									</TR>

									<TR>
										<TD width="24%"><h:outputText id="TermStar" 
										styleClass="#{adminMethodMappingBackingBean.termReq? 'mandatoryError': 'mandatoryNormal'}"				
										value="Term" /></TD>
										<TD width="28%">

										<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtTerm"
											value="#{adminMethodMappingBackingBean.termCriteria}"></h:inputHidden>
										</TD>
										<TD width="375">&nbsp;&nbsp;<h:commandButton alt="Select" title="Select" 
											id="Term" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/SearchMultiSelect.jsp'+getUrl(),'searchTerm','Term',
                                                         'Term Popup','TermDiv','searchForm:txtTerm'); return false;">
										</h:commandButton></TD>

										<td align="left"><h:selectBooleanCheckbox
											styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
											value="#{adminMethodMappingBackingBean.aggregateTerm}"
											tabindex="2" readonly></h:selectBooleanCheckbox>Combine</td>

									</TR>
									<TR>
										<TD width="24%"><h:outputText id="Qualifier" value="Qualifier" /></TD>
										<TD width="28%">
										<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtQualifier"
											value="#{adminMethodMappingBackingBean.qualifierCriteria}"></h:inputHidden>
										</TD>
										<TD width="9%">&nbsp;&nbsp;<h:commandButton alt="Select" title="Select" 
											id="QualifierButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/searchMultipleQualifierPopup.jsp','searchQualifier','Qualifier',
                                                         'Qualifier Search Popup','QualifierDiv','searchForm:txtQualifier'); return false;"
											tabindex="13"></h:commandButton></TD>

									</TR>
									<TR>
										<TD width="144"><h:outputText id="pva"
											value="Provider Arrangement" /></TD>
										<TD width="177">
										<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="providerArrangement"
											value="#{adminMethodMappingBackingBean.pvaCriteria}"></h:inputHidden>
										</TD>
										<TD width="375">&nbsp; <h:commandButton alt="Select" title="Select" 
											id="ProviderArrangementButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="pvaClick('../standardbenefitpopups/providerArrangementPopUp.jsp','ProviderArrangementDiv','searchForm:providerArrangement',2,2); return false;"
											tabindex="14"></h:commandButton></TD>
									</TR>

									<TR>
										<TD colspan="3">&nbsp;</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Locate"
											style="cursor: hand"
											action="#{adminMethodMappingBackingBean.searchAdminMethodMapping}"
											tabindex="11"></h:commandButton></TD>
									</TR>
								</TBODY>
							</TABLE>


							</DIV>
							</DIV>


							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;overflow:none;"><BR>

							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:300px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">

									<h:dataTable headerClass="fixedDataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										cellspacing="1" width="100%" bgcolor="#cccccc"
										rendered="#{adminMethodMappingBackingBean.searchAdminMethodList != null}"
										value="#{adminMethodMappingBackingBean.searchAdminMethodList}" 
										rowClasses="dataTableEvenRow,dataTableOddRow">
										
										<h:column>
											<f:facet name="header">
												<f:verbatim>Admin Method Number</f:verbatim>
											</f:facet>
											<h:outputText id="adminMethodNo"
												value="#{singleValue.adminMethodNo}"></h:outputText>
											<h:inputHidden id="hiddenAdminMethodSysId"
												value="#{singleValue.adminMethodSysId}"></h:inputHidden>
										</h:column>

										<h:column>
                                         	<f:facet name="header">
												<f:verbatim>Admin Method Description</f:verbatim>
											</f:facet>
											<h:outputText id="adminMethodDesc"
												value="#{singleValue.description}"></h:outputText>
										</h:column>

										<h:column>
                                           <f:facet name="header">
												<f:verbatim>Processing Method</f:verbatim>
											</f:facet>
											<h:outputText id="proccessMethod"
												value="#{singleValue.processMethodDesc}"></h:outputText>
										</h:column>
				
										<h:column>
                                           <f:facet name="header">
												<f:verbatim>Term</f:verbatim>
											</f:facet>
											<h:outputText id="adminMethodTerm"
												value="#{singleValue.term}"></h:outputText>
											
										</h:column>
			
										<h:column>
                                            <f:facet name="header">
												<f:verbatim>Qualifier</f:verbatim>
											</f:facet>
											<h:outputText id="adminMethodQualifier"
												value="#{singleValue.qualifier}"></h:outputText>
										</h:column>
									
										<h:column>
                                            <f:facet name="header">
												<f:verbatim>Provider Arrangement</f:verbatim>
											</f:facet>
											<h:outputText id="adminMethodPva"
												value="#{singleValue.pvaid}"></h:outputText>
										</h:column>

										<h:column>
                                             <f:facet name="header">
												<f:verbatim>Data type</f:verbatim>
											</f:facet>
											<h:outputText id="adminMethodDataType"
												value="#{singleValue.datatype}"></h:outputText>
										</h:column>
										
										<h:column>
                                             <f:facet name="header">
												<f:verbatim>&nbsp</f:verbatim>
											</f:facet>
										
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction1();return false;"
												rendered="#{adminMethodMappingBackingBean.viewableForUser}"></h:commandButton>
											<f:verbatim>   &nbsp;&nbsp; </f:verbatim>



											<h:commandButton alt="Edit" title="Edit" id="basicEdit"
												image="../../images/edit.gif" value="Edit"
												rendered="#{adminMethodMappingBackingBean.editableForUser}"
												onclick="editMapping();return false;">
											</h:commandButton>


											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												rendered="#{adminMethodMappingBackingBean.deletableForUser}"
												onclick="deleteAdminMethodMapping();return false;">
											</h:commandButton>
											
										</h:column>


									</h:dataTable></DIV>
									</TD>
								</TR>
								
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>

							<h:commandLink id="editMappingButton"
								style="display:none; visibility: hidden;"
								action="#{adminMethodMappingBackingBean.retriveAdminMethodForEdit}">
							</h:commandLink> <h:commandLink id="deleteAM"
								style="display:none; visibility: hidden;"
								action="#{adminMethodMappingBackingBean.deleteAdminMethodMapping}">
							</h:commandLink></TD>
						</TR>
				</TABLE>
				<h:inputHidden id="hiddenAdminMethodSysIdForView"
					value="#{adminMethodMappingBackingBean.adminMethodsysID}"></h:inputHidden>
			</h:form></TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>

	</body>
</f:view>
<script language="JavaScript"><!--
copyHiddenToDiv_ewpd('searchForm:txtProcessingMethod','processingMethodDiv',2,2);

function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchProcessingMethodPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
	}

function viewAction1(){
            
            getFromDataTableToHidden('searchForm:searchResultTable','hiddenAdminMethodSysId','searchForm:hiddenAdminMethodSysIdForView');
            
			var hiddenAdminMethodSysId  = document.getElementById('searchForm:hiddenAdminMethodSysIdForView').value;
            var url = '../adminmethodmapping/adminMethodMappingView.jsp'+getUrl()+'?adminMethodSysId='+hiddenAdminMethodSysId; 
			window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");		 
}

    copyHiddenToDiv_ewpd('searchForm:providerArrangement','ProviderArrangementDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:txtTerm','TermDiv',2,2);

function pvaClick(url,targetDiv,targetHidden,attrCount,attrPosn){
	var lob = 'ALL~ALL';
	var be = 'ALL~ALL';
	var bg = 'ALL~ALL';
	var parentCatalog = 'provider arrangement';
	url = url +getUrl()+ '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
}
function setToolTip(){
document.getElementById('searchForm:admindesc').title=document.getElementById('searchForm:admindesc').value;
}                   
var newWinForView;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'345',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
if(document.getElementById('searchForm:searchResultTable') != null) {

		setColumnWidth('searchForm:searchResultTable','8%:20%:12%:14%:14%:12%:12%:8%');	
		showResultsTab();

		}
function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp){

      ewpdModalWindow_ewpd( popupName+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

    function editMapping(){       
           
            getFromDataTableToHidden('searchForm:searchResultTable','hiddenAdminMethodSysId','searchForm:hiddenAdminMethodSysIdForView');      
             submitLink('searchForm:editMappingButton');

      }
function deleteAdminMethodMapping(){
      var message = "Are you sure you want to delete?"           
            if(window.confirm(message)){
                   getFromDataTableToHidden('searchForm:searchResultTable','hiddenAdminMethodSysId','searchForm:hiddenAdminMethodSysIdForView');      
                     
                 submitLink('searchForm:deleteAM');
            }
            else{             
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
    	k == 38 || k ==32 || k == 45 || k == 95 || k == 127 ||k==13 )){
		
		evt.returnValue=true;

	}else {
		evt.returnValue = false;
	}	
 }			
function checkIsNum(){

	var adminMethodVal = document.getElementById('searchForm:adminNoId').value;
		if (!isNaN(adminMethodVal)){
			return true;		
		}
		else {
			document.getElementById('searchForm:adminNoId').value="";
			return false;
		}
}
</script>
</HTML>



