<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<%@ page import = "org.apache.commons.lang.StringEscapeUtils" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet"
	type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<BASE target="_self" />
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 5%;
	
}.shortDescriptionColumn {
	
}
.longDescriptionColumn {
	
}

</style>
<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<f:view>
	<BODY>
	<h:form id="popupForm">

<h:inputHidden id="records"
					value="#{popupBackingBean.filteredRecords}"></h:inputHidden>
							<table width="100%">
								<TBODY>
									<TR>
										<TD><div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
	<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
        
		     <tr>
				<td align="left"><h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton" 
					onclick="getSelectedValues();return false;"></h:commandButton>
				</td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				
				<tr>
					<TD>
						<div id="headerTable">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="4%" align="center" height="10"><h:selectBooleanCheckbox id="checkId"
								onclick="selectAll(); "></h:selectBooleanCheckbox></TD>
							<td width="15%" align="center" height="27"><strong> 
                                <h:outputText value="Reference Id" styleClass="outputText"></h:outputText></strong></td>

							<TD width="90%" align="center" height="27"><strong> 
							<h:outputText value="#{popupBackingBean.headerName}" styleClass="outputText"></h:outputText></strong></td>
						</tr>
						<tr>
							<td width="4%" align="center" height="10"></TD>
							<td width="10%" align="center" height="27">
							<TD width="90%" align="center" height="27"><h:inputText styleClass="formInputField"
											id="txtSearch"
											value="#{popupBackingBean.searchString}"
											maxlength="34" tabindex="4" onkeypress="return expandcontract(event)" readonly = "true"/></td>
						</tr>
					</table>
</div>
					</TD>
				</TR>
				
				<tr>
					<TD> 
						<div id="searchResultdataTableDiv" class="popupDataTableDiv" style="height:300px;">
							<h:dataTable id="componentDataTable"  
								   value="#{popupBackingBean.searchList}" var="eachRow"  cellpadding="2" 
								   cellspacing="1" bgcolor="#cccccc" 
									rendered="#{popupBackingBean.searchList != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow"
									columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
										
								        <h:column>
												<h:selectBooleanCheckbox id="componentChkBox" onclick="isCheckedAll(this);">
												</h:selectBooleanCheckbox>
												<h:inputHidden value="#{eachRow.spsId}"></h:inputHidden>
   											    <h:inputHidden value="#{eachRow.description}"></h:inputHidden>	
										</h:column>
								        <h:column >
											<h:inputHidden value="#{eachRow.spsId}"></h:inputHidden>
											<h:inputHidden value="#{eachRow.description}"></h:inputHidden>	
                                            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
											<h:outputText value="#{eachRow.spsId}"></h:outputText>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
										</h:column>	
								        <h:column >
                                            <f:verbatim>&nbsp;</f:verbatim>							
											<h:outputText value="#{eachRow.description}"></h:outputText>
										</h:column>														              															              							
						   </h:dataTable>
						
						</div>
						<div id="errorMessageDiv" class="popupDataTableDiv" style="height:300px;display:none;">
							<table cellpadding="7" 
								   cellspacing="1" bgcolor="#cccccc" 
									border="0" width="100%" >
								<tr>
									<td bgcolor="#e1ecf7" colspan="2" width="100%" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
								font-size:9px;"><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error occurred during processing.</font></td></tr>									              															              							
						   </table>
						</div>	           						
					</TD>
				</tr>
		 </TABLE>	
	</div>																		

<h:commandLink id="searchLink"
					style="display:none; visibility: hidden;"
					action="#{popupBackingBean.filterResult}"><f:verbatim /></h:commandLink>
<h:commandLink id="loadPopUp"
					style="display:none; visibility: hidden;" 
					action="#{popupBackingBean.getRecords}"><f:verbatim /></h:commandLink>
<h:inputHidden id="entityId"
					value="#{popupBackingBean.entityid}"></h:inputHidden>
<h:inputHidden id="hiddenHeaderName"
					value="#{popupBackingBean.headerName}"></h:inputHidden>
<h:inputHidden id="queryName"
					value="#{popupBackingBean.queryName}"></h:inputHidden>

<h:inputHidden id="popAction"
					value="#{popupBackingBean.popAction}"></h:inputHidden>
<h:inputHidden id="benefitLvlIdHidden"
					value="#{popupBackingBean.benefitLvlId}"></h:inputHidden>
</h:form>
	</BODY>
</f:view>
<script>
document.getElementById('popupForm:txtSearch').readOnly = false; 
var checkValueArray = new Array();
function expandcontract(dis) {
	if(dis.keyCode=='13'){
		//submitLink('popupForm:searchLink');
		document.getElementById('popupForm:checkId').disabled = false;
		var entityId = document.getElementById('popupForm:entityId').value;
		var queryName = document.getElementById('popupForm:queryName').value;
		var headerName = document.getElementById('popupForm:hiddenHeaderName').value;
		var searchField = document.getElementById('popupForm:txtSearch');
		var benefitLvlId = document.getElementById('popupForm:benefitLvlIdHidden').value;
		var divObj = document.getElementById('searchResultdataTableDiv');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object,table id and error div.
		ajaxCall1('../popups/ajaxHelperTableReqParameterPopup.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&benefitLvlId='+benefitLvlId+'&temp='+Math.random(),searchField,divObj,'popupForm:componentDataTable',errorMsgDiv,attrObj,'','popupForm:checkId');
		var tableObject = document.getElementById("popupForm:componentDataTable");
			if(null!=tableObject){
				maintainState();
			}
		return false;
	}
}
function maintainState(){
	var tableObj = document.getElementById("popupForm:componentDataTable");
	var obj1 = document.getElementById("popupForm:checkId");
	var count = 0;
	obj1.checked = false;
		for (var x=0;x<tableObj.rows.length;x++){
			var checkboxObject = tableObj.rows[x].cells[0].children[0];
			var checkedCode = checkboxObject.parentNode.children[1].value;
			checkedCode = checkedCode.replace('~','/w0p8d');
			for(var y=0;y<checkValueArray.length;y++){
			var arrayCodeValue = checkValueArray[y].split('~');
				if(checkedCode == arrayCodeValue[0]){
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

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}

	hide();
	function hide(){
		obj = getObj('popupForm:componentDataTable');
		obj1 = getObj('popupForm:checkId');
		var actionvalue = document.getElementById('popupForm:popAction').value;

		if(obj == null || obj.rows.length == 0) {
			if(actionvalue ==1){
				obj3 = getObj('fullInfo');
				obj3.innerHTML = '';
			}else{
				obj2 = getObj('searchResultdataTableDiv');
				obj2.innerHTML = '';
				obj1.disabled = "true";
				
			}

		}
	}
//loadAvluesToArray();
matchCheckboxItems_ewpd('popupForm:componentDataTable',window.dialogArguments.selectedValues,2,2,2);

function isCheckedAll(chkObj){
	var tableObj = document.getElementById("popupForm:componentDataTable");
	var obj1 = document.getElementById("popupForm:checkId");
	var element;
	if(null!=tableObj){
		var checkedCode = chkObj.parentNode.children[1].value;
		var checkedDecs = chkObj.parentNode.children[2].value;	
		checkedCode = checkedCode.replace('~','/w0p8d');
		checkedDecs = checkedDecs.replace('~','/w0p8d');
		if(chkObj.checked){
			element = checkedCode + "~" + checkedDecs;
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
					if(!flag)checkValueArray.push(codeValue + '~' + codeDesc); 
				}else {
					popUnckeckedValue(codeValue);
				}
			}
		}
	}
}

function selectAll(){
	checkAllid1(document.getElementById('popupForm:checkId'),'popupForm:componentDataTable','componentChkBox');
}

/*function loadAvluesToArray(){
	var dialogArgs = window.dialogArguments.selectedValues;
	var selectedVals;
	if(dialogArgs !=null && dialogArgs !=''){
		selectedVals = dialogArgs.split('~');
		for(var i=0; i< selectedVals.length; i+=2){
			checkValueArray.push(selectedVals[i] + '~' + selectedVals[i+1]);
		 }
	}
}*/

function popUnckeckedValue(checkedCode){
	for(var i=0;i<checkValueArray.length;i++){
		var arrayCodeValue = checkValueArray[i].split('~');
		if(checkedCode == arrayCodeValue[0]){
			checkValueArray.splice(i,1); 
		}					
	}
}

function getSelectedValues(){
	var selectedValues = '';
	for(var i=0;i<(checkValueArray.length);i++){
		if(i==(checkValueArray.length-1)) {
			selectedValues = selectedValues + checkValueArray[i];
		}else
		selectedValues = selectedValues + checkValueArray[i] + '~';
	}
window.returnValue = selectedValues;
	window.close();	
	if(checkValueArray.length > 0)
		return true;
	return false;
}

</script>
	
</HTML>



