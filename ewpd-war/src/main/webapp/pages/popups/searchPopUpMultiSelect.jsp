<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 15%;
}
.longDescriptionColumn {
	width: 87%;
}
</style>
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
<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<h:form id="benefitTermSelectPopupForm">
		<h:inputHidden id="search" value="#{ReferenceDataBackingBeanCommon.searchTermQualifier}"></h:inputHidden>
		<div id="fullInfo"></div>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">

			<tr align="center">

			</tr>

		</table>

				<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
											<div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<input id="selectButtonId" type="button" class="wpdbutton" disabled="disabled"
					name="action" value="Select" onClick="getSelected();return false;" />
				</td>
			</tr>

		</table>

		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0">
			
			<tr>
				<td>

				<DIV id="popupTableDiv3" style="height:0px; overflow:auto;">
				<table id="businessEntityTable1" width="100%" cellpadding="0"
					cellspacing="0" bgcolor="#cccccc">
					<tr id="tr3">
						<td width="10%" align="center" height="24"><input
							name="checkbox" type="checkbox" id="checkall"
							onClick="checkAll_ewpd(this,'benefitTermSelectPopupForm:searchResultTable1');"></TD>
						<td width="15%" align="left" height="24"><strong><h:outputText value="Code "
							styleClass="outputText"></h:outputText></strong></TD>
						<td width="75%" align="left" height="24"><strong><h:outputText
							value="Description " styleClass="outputText"></h:outputText></strong></td>
					</tr>
					<tr>
						<td width="10%" align="center" valign="middle" height="16"></td>
						<td width="15%" align="center" valign="middle" height="16"></td>
						<TD width="75%" align="left" height="16"><h:inputText id="searchText"
							size="10" styleClass="formInputField"
							value="#{ReferenceDataBackingBeanCommon.searchValueForPopUp}"
							maxlength="34" tabindex="4"
							onkeypress="return expandcontract(event)" readonly = "true"/></TD>
					</tr>
				</table>
				</DIV>
				</td>
			</tr>
			<tr id="tr1">
				<td colspan="2" width="100%">
				<DIV id="popupTableDiv1" style="overflow:auto;"
					class=popupDataTableDiv><h:dataTable headerClass="dataTableHeader"
					id="searchResultTable1" var="providerArrangement" cellpadding="2"
					width="100%" cellspacing="1" bgcolor="#cccccc"
					rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					border="0" rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
					<h:column>
						
						<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							id="parCheckBox" value="#{providerArrangement.termCheckBx}" onclick="isCheckedAll();">
						</h:selectBooleanCheckbox>
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
						<div id="errorMessageDiv" class="popupDataTableDiv" style="height:300px;display:none;">
							<table cellpadding="2" 
								   cellspacing="1" bgcolor="#cccccc" 
									border="0" width="100%" >
								<tr>
									<td bgcolor="#e1ecf7" colspan="2" width="100%" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
								font-size:10px;"><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error occurred during processing.</font></td></tr>									              															              							
						   </table>
						</div>	        
				</td>
			</tr>
		</table>
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
		<h:commandLink id="searchLink"
			style="display:none; visibility: hidden;"
			action="#{ReferenceDataBackingBeanCommon.searchTermQualifier}">
			<f:verbatim />
		</h:commandLink>
	</h:form>
	</BODY>
</f:view>
<script language="JavaScript">
document.getElementById('selectButtonId').disabled  = false;
document.getElementById('benefitTermSelectPopupForm:searchText').readOnly = false; 
function getSelected(){
getCheckedItems_ewpd('benefitTermSelectPopupForm:searchResultTable1',2);return false;
}
function expandcontract(dis) {
	if(dis.keyCode=='13'){		
		document.getElementById('checkall').disabled = false;
		var catalogName = document.getElementById('benefitTermSelectPopupForm:parentCatalog').value;
		var lookUpAction = document.getElementById('benefitTermSelectPopupForm:lookUpAction').value;
		var lob = document.getElementById('benefitTermSelectPopupForm:lob').value;
		var be = document.getElementById('benefitTermSelectPopupForm:be').value;
		var bg = document.getElementById('benefitTermSelectPopupForm:be').value;
		var entityType ="";
		var searchField = document.getElementById('benefitTermSelectPopupForm:searchText');
		var divObj = document.getElementById('popupTableDiv1');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
		ajaxCall1('../popups/refDataAjaxHelperTable.jsp?parentCatalog='+catalogName+
					'&lookUpAction='+lookUpAction+'&lob='+lob+'&be='+be+'&bg='+bg+'&entityType='
					+entityType+'&temp='+Math.random(),searchField,divObj,
					'benefitTermSelectPopupForm:searchResultTable1',errorMsgDiv,attrObj,'','checkall','','');
		return false;
	}
}
matchCheckboxItems_ewpd('benefitTermSelectPopupForm:searchResultTable1',window.dialogArguments.selectedValues,2,2,2);

/**
	Function makes a Tilda String depends on the argument.
	
	Parameters
	id			- Id of the data table.
	attrCount	- The number of attributes of the tilda String that constitues  a single data.
	
	The function assumes the first column of data table contains a checkbox/radiobutton and
	all the attributes that need to be included in the tilda string will be available at 
	second column as input hidden field in correct order. So depending on the parameter "attrCount" 
	that much number of values from the second column will be added to tilda String.
*/
function getCheckedItems_ewpd(id, attrCount)
{
	var tableObject;
	var val = window.dialogArguments.selectedValues;
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			if (checkboxObject && checkboxObject.checked) {
				// replacing '~' to '^*#'
				var firstCell = currentCell.children[0].value;
//
				var previousSelected = (window.dialogArguments.selectedValues).split('~');
				var alreadyExist = false;
				for(var j = 0; j < previousSelected.length; j = j+2){
					if(firstCell == previousSelected[j]){
						alreadyExist = true;
						break;
					}
				}
				if(alreadyExist == false){
//
					firstCell = firstCell.replace('~','/w0p8d');
					if(attrCount > 1){
						var secondCell = currentCell.children[1].value;
						while(secondCell.indexOf('~') >= 0){
							secondCell = secondCell.replace('~','/w0p8d');
						}
					}
					if(attrCount == 3){
					
						var thirdCell = currentCell.children[2].value;
						thirdCell = thirdCell.replace('~','/w0p8d');
					}
					if(cnt > 0)
						selectedValues += '~';
					
					switch(attrCount){
						case 1: selectedValues += firstCell; 
								break;
						case 2: selectedValues += firstCell + '~' + secondCell;	
								break;
						case 3: selectedValues += firstCell + '~' + secondCell + '~' + thirdCell;
								break;
						default: alert('invalid attrCount parameter for function getCheckedItems');
					}	
					cnt++;
				}
			}else{
				var first = currentCell.children[0].value;
				var second = currentCell.children[1].value;
				var previous = (window.dialogArguments.selectedValues).split('~');
				var already = false;
				for(var j = 0; j < previous.length; j = j+2){
					if(first == previous[j])
						already = true;
				}
				if(already == true){
					var replaceValue = first + '~' + second;
					var position = val.indexOf(replaceValue);
					var subString = val.substr(position);
					if(position > 0)			
						val = val.replace('~'+replaceValue, '');
					else if(position == 0){
						val = val.replace(replaceValue, '');
						if(val.length > 0)
							val = val.substr(1);
					}
				}
			}
		}	
	}
	if(selectedValues.length > 0){
		selectedValues += '~';
	}else if(val.length > 0){
		cnt = 1;
}
	selectedValues += val;
if((selectedValues.charAt(selectedValues.length-1,1) == "~")){
	selectedValues=selectedValues.substring(0,selectedValues.length-1) 
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
	if(cnt > 0)
		return true;
	return false;
}
isCheckedAll();
function isCheckedAll(){
	tableObj = document.getElementById('benefitTermSelectPopupForm:searchResultTable1');
	if(null!=tableObj){
		for (var i=0;i<tableObj.rows.length;i++){
			var checkboxObject = tableObj.rows[i].cells[0].children[0];
				if(checkboxObject.checked==false){
					document.getElementById('checkall').checked=false;
//					object = document.getElementById('checkall').checked=false;
	//				object.checked=false;
					break;
				}
				else{
		//		object.checked=true;
				document.getElementById('checkall').checked=true;
			}
		}
	}
}
</script>
</HTML>
