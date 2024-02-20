<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "org.apache.commons.lang.StringEscapeUtils" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 25%;
}
.longDescriptionColumn {
	width: 65%;
}	
</style>

<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})" /></title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<h:form id="benefitTermSelectPopupForm">
			<h:inputHidden id="search"
				value="#{ReferenceDataBackingBeanCommon.searchTermQualifier}"></h:inputHidden>	
		
					
			<TABLE id="message" width=98%>
								<TBODY>
									<tr>
										<TD>
											<div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
										</TD>
									</tr>
								</TBODY>
			</TABLE>
		

			<TABLE border="0" width="98%">
				<TR>
					<TD align="left" height="19">&nbsp;<INPUT type="button" id="selectButton" 
						class="wpdbutton" name="action" value="Select"
						onclick="getSelectedValues();return false;" disabled="disabled"></TD>
				</TR>

			</TABLE>

			<TABLE width="98%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<TR>
					<TD>

					<div id="headerTable">
					<TABLE id="businessEntityTable1" width="100%" cellpadding="0"
						cellspacing="0" bgcolor="#cccccc">
						<TR id="tr3">
							<TD width="10%" align="center" valign="middle" height="23"></TD>						
							<TD width="25%" align="left" height="22"><STRONG><h:outputText
								value="Code " styleClass="outputText"></h:outputText></STRONG></TD>
							<TD width="65%" align="left" height="22"><STRONG><h:outputText
								value="Description " styleClass="outputText"></h:outputText></STRONG></TD>
						</TR>
						<TR>
							<td width="10%" align="center" height="27"><h:selectBooleanCheckbox id="checkId"
								onclick="selectAll();"></h:selectBooleanCheckbox></TD>

							<TD width="20%" align="left" valign="middle" height="23"><h:inputText
								id="searchText" size="10"
								styleClass="formInputFieldForSequenceNo"
								value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
								maxlength="34" tabindex="4"
								onkeypress="return expandcontract(event)" readonly="true" /></TD>
							<TD width="65%" align="center" valign="middle" height="23"></TD>
						</TR>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<TR id="tr1">
					<TD colspan="2" width="100%">
					<DIV id="popupTableDiv1" style="overflow:auto;"
						class="popupDataTableDiv"><h:dataTable
						headerClass="dataTableHeader" id="searchResultTable1test"
						var="providerArrangement" cellpadding="2" width="100%"
						cellspacing="1" bgcolor="#cccccc"
						rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
						border="0" 
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
						<h:column>
							
							<h:selectBooleanCheckbox id="componentChkBox"
								onclick="isCheckedAll(this);">
							</h:selectBooleanCheckbox>
							<h:inputHidden id="hiddenPrimaryCode"
								value="#{providerArrangement.primaryCode}" />
							<h:inputHidden id="hiddenTermVal"
								value="#{providerArrangement.description}"/>
						</h:column>
						<h:column>
							
							<h:inputHidden id="hiddenTermValue"
								value="#{providerArrangement.description}" />
							<h:inputHidden id="hiddenTermValueDesc"
								value="#{providerArrangement.primaryCode}" />
							<h:outputText id="termValueId"
								value="#{providerArrangement.primaryCode}"
								style="padding-left: 5px"></h:outputText>
						</h:column>
						<h:column>
							
							<h:outputText id="termValue"
								value="#{providerArrangement.description}"
								style="padding-left: 5px"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					<div id="errorMessageDiv" class="popupDataTableDiv"
						style="height:300px;display:none;">
					<table cellpadding="7" cellspacing="1" bgcolor="#cccccc" border="0"
						width="100%">
						<tr>
							<td bgcolor="#e1ecf7" colspan="2" width="100%"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
                                                font-size:9px;"><font
								color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error
							occurred during processing.</font></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
			</TABLE>
		
			<h:inputHidden id="hiddensortorder"
				value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
			<h:inputHidden id="parentCatalog"
				value="#{ReferenceDataBackingBeanCommon.catalogNameForTermQualifier}"></h:inputHidden>
			<h:inputHidden id="lob" value="#{ReferenceDataBackingBeanCommon.lob}"></h:inputHidden>
			<h:inputHidden id="be"
				value="#{ReferenceDataBackingBeanCommon.businessEntity}"></h:inputHidden>
			<h:inputHidden id="bg"
				value="#{ReferenceDataBackingBeanCommon.businessGroup}"></h:inputHidden>
			<h:inputHidden id="lookUpAction"
				value="#{ReferenceDataBackingBeanCommon.lookUpAction}"></h:inputHidden>
			<h:inputHidden id="entityId"
				value="#{ReferenceDataBackingBeanCommon.entityId}"></h:inputHidden>
			<h:inputHidden id="entityType"
				value="#{ReferenceDataBackingBeanCommon.entityType}"></h:inputHidden>

		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
document.getElementById('selectButton').disabled  = false;
var checkValueArray = new Array();
var sort = document.getElementById('benefitTermSelectPopupForm:hiddensortorder');
document.getElementById('benefitTermSelectPopupForm:searchText').readOnly = false;   

initialize();
	function initialize(){
		if(document.getElementById('benefitTermSelectPopupForm:searchResultTable1test') != null)	{			
			if(document.getElementById('benefitTermSelectPopupForm:searchResultTable1test').rows.length==0){		
				document.getElementById("message").style.display = 'none';		
			}	
			else{			
				document.getElementById("message").style.display = 'block';	
			}		
		}
	}

function expandcontract(dis) {	
      if(dis.keyCode=='13'){			
            var parentCatalog = document.getElementById('benefitTermSelectPopupForm:parentCatalog').value;
            var lookUpAction = document.getElementById('benefitTermSelectPopupForm:lookUpAction').value;
            var lob = document.getElementById('benefitTermSelectPopupForm:lob').value;
            var be = document.getElementById('benefitTermSelectPopupForm:be').value;
            var bg = document.getElementById('benefitTermSelectPopupForm:bg').value;
			var entityType = document.getElementById('benefitTermSelectPopupForm:entityType').value;
            var searchField = document.getElementById('benefitTermSelectPopupForm:searchText');
            var divObj = document.getElementById('popupTableDiv1');
            var errorMsgDiv = document.getElementById('errorMessageDiv');
			var msgDiv = document.getElementById('infoDivMessage');
			var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;		
            // call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
            ajaxCall1('../popups/ajaxCoverageHelperTableMulti.jsp?parentCatalog='+parentCatalog+'&lookUpAction='+lookUpAction+'&lob='+lob+'&be='+be+'&bg='+bg+'&entityType='+entityType+'&temp='+Math.random(),searchField,divObj,'benefitTermSelectPopupForm:searchResultTable1test',errorMsgDiv,attrObj,'','popupForm:checkId',msgDiv);
			var tableObject = document.getElementById("benefitTermSelectPopupForm:searchResultTable1test");
			if(null!=tableObject){
				maintainState();
			}
    	  return false;
      }
}

function maintainState(){
	var tableObj = document.getElementById("benefitTermSelectPopupForm:searchResultTable1test");
	var obj1 = document.getElementById("benefitTermSelectPopupForm:checkId");
	var count = 0;
	obj1.checked = false;
		for (var x=0;x<tableObj.rows.length;x++){
			var checkboxObject = tableObj.rows[x].cells[0].children[0];
			var checkedCode = checkboxObject.parentNode.children[1].value;
			checkedCode = checkedCode.replace('~','/w0p8d');
			for(var y=0;y<checkValueArray.length;y++){
			var arrayCodeValue = checkValueArray[y].split('~');
				if(checkedCode == arrayCodeValue[1]){
					checkboxObject.checked = true;
					count++;
					break;
				}		
			}
		}
	if((tableObj.rows.length > 0) && (count == tableObj.rows.length)){
		 obj1.checked = true;
	  }
}

function isCheckedAll(chkObj){
	var tableObj = document.getElementById("benefitTermSelectPopupForm:searchResultTable1test");
	var obj1 = document.getElementById("benefitTermSelectPopupForm:checkId");
	var element;
	if(null!=tableObj){
		var checkedCode = chkObj.parentNode.children[1].value;
		var checkedDecs = chkObj.parentNode.children[2].value;	
		checkedCode = checkedCode.replace('~','/w0p8d');
		checkedDecs = checkedDecs.replace('~','/w0p8d');
		if(chkObj.checked){
			element = checkedDecs + "~" + checkedCode;
			checkValueArray.push(element);
		}else{
			popUnckeckedValue(checkedCode);
		}
		for (var i=0;i<tableObj.rows.length;i++){
			var checkboxObject = tableObj.rows[i].cells[0].children[0];
				if(!checkboxObject.checked){
					obj1.checked=false;
					break;
				}else{
				obj1.checked=true;
			}
		}
	}
}
// This function pop the uncheck value from Array.
function popUnckeckedValue(checkedCode){
	var tempArray = new Array();
			for(var i=0;i<checkValueArray.length;i++){
				var arrayCodeValue = checkValueArray[i].split('~');
					if(checkedCode == arrayCodeValue[1])continue;					
						tempArray.push(checkValueArray[i])
				}
			checkValueArray = tempArray;
}

// This function checks for null also
function checkAllid1(controller, table, name) {
	var tableObject = document.getElementById(table);
	var chkname;
	var chkbox;	
	var flag;
	if(null!=tableObject){
		for(var i=0; i<tableObject.rows.length; i++) {
			chkbox = tableObject.rows[i].cells[0].children[0];
			var codeValue = chkbox.parentNode.children[1].value;
			var codeDesc  = chkbox.parentNode.children[2].value;
			codeValue = codeValue.replace('~','/w0p8d');
			codeDesc = codeDesc.replace('~','/w0p8d');
			flag = false;
			if (!(chkbox.disabled == true)) {
				chkbox.checked = controller.checked;
				if(chkbox.checked){
					for(var j=0;j<checkValueArray.length;j++){
						var arrayCodeValue = checkValueArray[j].split('~');
						if(arrayCodeValue[1]==codeValue) flag = true;
					}	
					if(!flag)checkValueArray.push(codeDesc + '~' + codeValue); 
				}else {
					popUnckeckedValue(codeValue);
				}
			}
		}
	}
}

function selectAll(){
	checkAllid1(document.getElementById('benefitTermSelectPopupForm:checkId'),'benefitTermSelectPopupForm:searchResultTable1test','componentChkBox');
}

function loadAvluesToArray(){
	var dialogArgs = window.dialogArguments.selectedValues;
	var selectedVals;
	if(dialogArgs !=null && dialogArgs !=''){
		selectedVals = dialogArgs.split('~');
		for(var i=0; i< selectedVals.length; i+=2){
			checkValueArray.push(selectedVals[i] + '~' + selectedVals[i+1]);
		 }
	}
}

matchCheckboxItems_ewpd('benefitTermSelectPopupForm:searchResultTable1test',window.dialogArguments.selectedValues,2,2,2);
loadAvluesToArray();

function getSelectedValues(){
	var selectedValues = '';
	for(var i=0;i<(checkValueArray.length);i++){
		if(i==(checkValueArray.length-1)) {
			selectedValues = selectedValues + checkValueArray[i];
		}else
		selectedValues = selectedValues + checkValueArray[i] + '~';
	}
window.returnValue = selectedValues;
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();	
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
	
	if(checkValueArray.length > 0)
		return true;
	return false;
}
</script>
</HTML>

