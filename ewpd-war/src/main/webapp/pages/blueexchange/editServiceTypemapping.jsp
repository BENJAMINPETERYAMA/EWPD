<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>Edit Service Type Mapping</TITLE>
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
<BODY onkeypress="return submitOnEnterKey('servTypMappingForm:createButton');">
	<table width="100%" cellpadding="0" cellspacing="0" border="0" >
		<tr>
			<td>
				<%
			        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
			                .getCurrentInstance().getExternalContext().getRequest();
			        javax.servlet.http.HttpSession httpSession = (javax.servlet.http.HttpSession) javax.faces.context.FacesContext.
			                getCurrentInstance().getExternalContext().getSession(true);
			        httpReq.setAttribute("breadCrumbText",
			                "Administration >> Blue Exchange >> Service Type Mapping (" + httpSession.getAttribute("ruleID") + ") >> Edit" );
			    %> 
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</td>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="servTypMappingForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->						
						 		</DIV>
							</TD>

						<TD colspan="2" valign="top" class="ContentArea" >
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
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
											value="General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%">
							<TR>
								<td width="28%"><h:outputText id="ruleIdLabel"
									value="Header Rule " /></td>
								<td width="72%"><h:outputText
									value="#{serviceTypeMappingBackingBean.ruleForEdit}"></h:outputText>
								<h:inputHidden id="ruleId"
									value="#{serviceTypeMappingBackingBean.headerRuleId}" /> <h:inputHidden
									id="hiddenruleForEdit"
									value="#{serviceTypeMappingBackingBean.ruleForEdit}" /> <h:commandButton
									alt="View" id="viewButton" image="../../images/view.gif"
									onclick="viewAction();return false;" tabindex="11" /></td>
							</TR>
							<TR>
								<TD width="28%"><h:outputText id="BlueAppl"
									value="Applicable to blue exchange">
								</h:outputText></TD>
								<TD width="72%"><h:selectOneRadio id="MsgInd"
									value="#{serviceTypeMappingBackingBean.blueExcahngeApplicable}"
									onclick="change(this);">
									<f:selectItem id="BlueApplYes" itemLabel="Yes" itemValue="Y" />
									<f:selectItem id="BlueApplNo" itemLabel="No" itemValue="N" />
								</h:selectOneRadio></TD>

							</TR>
						</table>
						<table width="100%">
							<TR id="EB03tr">
								<TD width="28%"><h:outputText value="EB03-Service Type Code*"
									styleClass="#{serviceTypeMappingBackingBean.eb03IdentifierValdn?'mandatoryError': 'mandatoryNormal'}" />
								</TD>
								<TD width="25%"><h:inputHidden id="eb03Hidden"
									value="#{serviceTypeMappingBackingBean.eb03Identifier}" />
								<div id="eb03Div" class="selectDataDisplayDiv"></div>
								</td>
								<td width="47%"><h:commandButton alt=""
									image="../../images/select.gif"
									onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp'+getUrl()+'?headerRuleId='+document.getElementById('servTypMappingForm:ruleId').value+'&lookUpAction='+'31'+'&parentCatalog='+'EB03'+'&title='+'EB03-Service Type Code'+'&temp='+Math.random(),'eb03Div','servTypMappingForm:eb03Hidden',2,2);return false;" />
								</TD>
							</TR>
							<tr>
								<td width="204">&nbsp;</td>
								<td width="179"></td>
								<td width="341"></td>
							</tr>
						</table>
						<table width="100%" id="codeListTable" cellspacing="0"
							cellpadding="0">
							<tr>
								<td width="221">
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="1"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><b><h:outputText value="Associated EB03/Service Type"></h:outputText></b>
										</TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left" width="25%"><h:outputText value="Code"></h:outputText></TD>
											<TD align="left" width="25%"><h:outputText
												value="Description "></h:outputText></TD>
											<TD align="left" width="25%"><h:outputText
												value="Send Dynamic Information "></h:outputText></TD>
											<TD align="left" width="25%"><h:commandButton
												id="deleteButton" tabindex="4" styleClass="wpdButton"
												value="Delete"  onclick="deleteAction();return false;"></h:commandButton></TD>

										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</td>
							</tr>
							<TR>
								<TD width="32%"><h:inputHidden
									value="#{serviceTypeMappingBackingBean.loadServiceTypes}">
								</h:inputHidden>
								<DIV id="searchResultdataTableDiv"
									style="height:90px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{serviceTypeMappingBackingBean.associatedCodeList != null}"
									value="#{serviceTypeMappingBackingBean.associatedCodeList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
									<h:column>
										<h:inputHidden id="serviceTypeCodeHidden"
											value="#{singleValue.serviceTypeCode}"></h:inputHidden>
										<h:outputText id="serviceTypeCodetext"
											value="#{singleValue.serviceTypeCode}">
										</h:outputText>
									</h:column>

									<h:column>
										<h:inputHidden id="serviceTypeDescriptionHidden"
											value="#{singleValue.serviceTypeDescription}"></h:inputHidden>

										<h:outputText id="serviceTypeDescriptionText"
											value="#{singleValue.serviceTypeSecCode}">
										</h:outputText>
									</h:column>
									<h:column>
										<h:inputHidden id="dynamicInfoHidden"
											value="#{singleValue.sendDynamicInfo}"></h:inputHidden>
										<h:selectBooleanCheckbox id="dynamicInfoChkBox" tabindex="3" />
									</h:column>
									<h:column>
										<h:selectBooleanCheckbox id="deleteChkBox" tabindex="3"
											onclick="enableDisableDelete('servTypMappingForm:searchResultTable',3,0,'servTypMappingForm:deleteButton');" />

									</h:column>
								</h:dataTable></DIV>

								</td>
							</tr>

						</table>
						<table>
							<tr>
								<td valign="top" width="29%"><span class="mandatoryNormal"
									id="createdBy"></span> <h:outputText value="Created By" /></td>
								<td width="45%"><h:outputText
									value="#{serviceTypeMappingBackingBean.createdUser}" /> <h:inputHidden
									id="createdUserHidden"
									value="#{serviceTypeMappingBackingBean.createdUser}">
								</h:inputHidden></td>
								<TD width="30%"></TD>
							</tr>
							<tr>
								<td valign="top" width="29%"><span class="mandatoryNormal"
									id="creationDate"></span> <h:outputText value="Created Date" /></td>
								<td width="45%"><h:outputText
									value="#{serviceTypeMappingBackingBean.createdTimestamp}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
								</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
								<h:inputHidden id="creationDateHidden"
									value="#{serviceTypeMappingBackingBean.createdTimestamp}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
								</h:inputHidden> <h:inputHidden id="time"
									value="#{requestScope.timezone}">
								</h:inputHidden></td>
								<TD width="30%"></TD>

							</tr>

							<tr>
								<td valign="top" width="29%"><h:outputText
									value="Last Updated By" /></td>
								<td width="45%"><h:outputText
									value="#{serviceTypeMappingBackingBean.lastUpdatedUser}" /> <h:inputHidden
									id="updatedUserHidden"
									value="#{serviceTypeMappingBackingBean.lastUpdatedUser}">
								</h:inputHidden></td>
								<TD width="30%"></TD>
							</tr>
							<tr>
								<td valign="top" width="29%"><span class="mandatoryNormal"
									id="updationDate"></span><h:outputText
									value="Last Updated Date" /></td>
								<td width="45%"><h:outputText
									value="#{serviceTypeMappingBackingBean.lastUpdatedTimestamp}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
								</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
								<h:inputHidden id="updationDateHidden"
									value="#{serviceTypeMappingBackingBean.lastUpdatedTimestamp}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
								</h:inputHidden></td>
								<TD width="30%"></TD>

							</tr>
							<tr>
								<TD width="210">&nbsp;</TD>
								<td width="320"></td>
								<td></td>

							</tr>
							<tr>
								<TD width="210" height="34"><h:commandButton value="Save"
									id="editButton" styleClass="wpdButton"
									onmousedown="checkUnsaved();return false;"
									onclick="saveAction();return false;">
								</h:commandButton></TD>
								<td width="320" height="34"></td>
								<td height="34"></td>

							</tr>
						</table>



						</fieldset>
						<!--	End of Page data	--></table>
							
					
<!-- Space for hidden fields -->
					<h:inputHidden id="blueExcahngeApplicableHidden" value="#{serviceTypeMappingBackingBean.blueExcahngeApplicablePrev}"/>
					<h:inputHidden id="codeToDelete" value="#{serviceTypeMappingBackingBean.deleteCodeIds}"></h:inputHidden>
					<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
					<h:commandLink id="deleteLink" action ="#{serviceTypeMappingBackingBean.deleteAction}"style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
					<h:commandLink id="saveLink" action ="#{serviceTypeMappingBackingBean.saveAction}"style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
					<h:inputHidden id="codeToSave" value="#{serviceTypeMappingBackingBean.codeToSave}"></h:inputHidden>
					<h:inputHidden id="sendDynamicInfoCode" value="#{serviceTypeMappingBackingBean.sendDynamicInfoCodes}"/>
					<h:inputHidden id="sendDynamicInfoValue" value="#{serviceTypeMappingBackingBean.sendDynamicInfoValues}"/>

<!-- End of hidden fields  -->					

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
					<%@include file="../navigation/bottom.jsp"%>
			</td>
		</tr>
	</table>
</BODY>
</f:view>

<script>
checkDynamicInfo();
function checkDynamicInfo(){
	tableObj = getObj('servTypMappingForm:searchResultTable');
	if(tableObj == null || tableObj.rows == 0) 
		return false;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			var hiddenDynamicInfo = cur_row.cells[2].children[0].value; 
			if(hiddenDynamicInfo=='Y'){
			cur_row.cells[2].children[1].checked=true;
			}
		}
}
function saveAction(){
var dynamicinfocode = getObj('servTypMappingForm:sendDynamicInfoCode');
var sendDynamicInfoValue = getObj('servTypMappingForm:sendDynamicInfoValue');
		dynamicinfocode.value = '';
		sendDynamicInfoValue.value='';
		var codeCount = 0;
 	
tableObj = getObj('servTypMappingForm:searchResultTable');
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
				if(codeCount != 0)
				dynamicinfocode.value += '~';
				dynamicinfocode.value += cur_row.cells[0].children[0].value;
				
				if(cur_row.cells[2].children[1].checked){
					if(codeCount != 0)
					sendDynamicInfoValue.value +='~';
					sendDynamicInfoValue.value += 'Y';
				}else{
					if(codeCount != 0)
					sendDynamicInfoValue.value +='~';
					sendDynamicInfoValue.value += 'N';
				}				
				codeCount ++;
		}
document.getElementById('servTypMappingForm:sendDynamicInfoCode').value=dynamicinfocode.value;
document.getElementById('servTypMappingForm:sendDynamicInfoValue').value=sendDynamicInfoValue.value;
submitLink('servTypMappingForm:saveLink');
}
function checkUnsaved(){
var codeToretain = document.getElementById('eb03Div').innerHTML;
var options = document.getElementsByName('servTypMappingForm:MsgInd');
var flag ;
	if(options[1].checked) {
		flag =options[1].value
	}else{
		flag =options[2].value
	}

unsavedcheck();
	if(flag=='Y'){
	if(codeToretain==null || codeToretain =='' ){
	savePageAction(this.id);
	}
	}else{
	savePageAction(this.id);
	}
}

var codeToretain = document.getElementById('servTypMappingForm:codeToSave').value;
var ebcodeArray =new Array (); 
ebcodeArray = codeToretain.split('~') ;
tableObj = getObj('servTypMappingForm:searchResultTable');
for (var i=0;i<ebcodeArray.length;i++){
	for (var k=0;k<tableObj.rows.length;k++){
var cur_row = tableObj.rows[k];
	if(cur_row.cells[0].children[0].value==ebcodeArray[i]){
			cur_row.cells[3].children[0].checked=true;
}
		}
}
function unsavedcheck(){
tableObj = getObj('servTypMappingForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
codeToSave = getObj('servTypMappingForm:codeToSave');
		codeToSave.value = '';
		var codeCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			if(cur_row.cells[3].children[0].checked) {
				if(codeCount != 0) 
					codeToSave.value += '~';
				codeToSave.value += cur_row.cells[0].children[0].value;
				codeCount ++;

			}

		}
document.getElementById('servTypMappingForm:codeToSave').value = codeToSave.value;
}
 copyHiddenToDiv_ewpd('servTypMappingForm:eb03Hidden','eb03Div',2,2);
enableDisableDelete('servTypMappingForm:searchResultTable',3,0,'servTypMappingForm:deleteButton');
	var options = document.getElementsByName('servTypMappingForm:MsgInd');
	if(options[1].checked) {
		EB03tr.style.display='';
		codeListTable.style.display='';
	} else {
		EB03tr.style.display='none'; 
		codeListTable.style.display='none'; 		
	}



function change(obj){
var val = obj.value;

if(val=='N')
{
	EB03tr.style.display='none';   
	codeListTable.style.display='none';  
}else{
	EB03tr.style.display='';
	codeListTable.style.display=''; 
}
}
function checkDelUnsaved(){
savePageAction(this.id);
}
function deleteAction(){
	
	if(confirmDelete()){
	submitLink('servTypMappingForm:deleteLink');
	}
}
function viewAction(){
	<!--
String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
}
// end hiding contents -->

	var ruleIdObject = document.getElementById('servTypMappingForm:ruleId').value;
	var splitRuleId = ruleIdObject.split('-');
	var ruleId = splitRuleId[0];
	ruleId = ruleId.trim();
	if(ruleId.length <=1){
			alert('Benefit Rule ID need to be selected.');
		}
	else{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}	

function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to delete associated service type code?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('servTypMappingForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;

		delcodeId = getObj('servTypMappingForm:codeToDelete');
		delcodeId.value = '';
		var codeCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			if(cur_row.cells[3].children[0].checked) {
				if(codeCount != 0) 
					delcodeId.value += '~';
				delcodeId.value += cur_row.cells[0].children[0].value;
				codeCount ++;
			}
		}
		if(codeCount > 0)
			return true;
		return false;
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
		window.showModalDialog('mappedEBCodesPopup.jsp'+getUrl()+'?eb03val='+eb03val+'&eb03desc='+eb03desc+'&temp=' + Math.random(),'hiddenDiv','dialogHeight:400px;dialogWidth:700px;resizable=true;status=no;');
	}
}
if(document.getElementById('servTypMappingForm:searchResultTable').rows.length != 0 && document.getElementById('servTypMappingForm:searchResultTable') != null) {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'visible';
				document.getElementById('servTypMappingForm:searchResultTable').style.visibility = 'visible';
				setColumnWidth('servTypMappingForm:searchResultTable','25%:25%:25%:25%');
				syncTables('resultHeaderTable','servTypMappingForm:searchResultTable');
				var relTblWidth = document.getElementById('servTypMappingForm:searchResultTable').offsetWidth;
				document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
				document.getElementById('headerTable').width = relTblWidth + 'px';
			}else {
				document.getElementById('searchResultdataTableDiv').style.display = 'none';
				document.getElementById('servTypMappingForm:searchResultTable').style.display = 'none';
			    document.getElementById('servTypMappingForm:searchResultTable').style.height = "0px";
			}
if(document.getElementById('servTypMappingForm:searchResultTable').rows.length < 1){
	document.getElementById('resultHeaderDiv').style.display ='none';
}else{
	document.getElementById('resultHeaderDiv').style.visibiity ='visible';
}
		
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="serviceTypeMapping" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
