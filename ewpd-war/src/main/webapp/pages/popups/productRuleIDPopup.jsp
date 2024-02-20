<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
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
	width: 66%;
}
.finalColumn{
    width: 10%;
}
</style>
<TITLE>Rule Id Popup</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<h:form id="ruleIDForm">
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
         
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="maxCheckedItems('ruleIDForm:ruleIDDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>

		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="10%" align="center" valign="middle"><input name="checkbox" type="checkbox"
								id="checkall"
								onClick="checkAll_ewpd(this,'ruleIDForm:ruleIDDataTable');"></td>
							<td width="15%" align="left"><strong><h:outputText
								value="Rule Id" styleClass="outputText"></h:outputText></strong></td>
							<td width="66%" align="left"><strong><h:outputText
								value="Rule Description" styleClass="outputText"></h:outputText></strong></td>
							<td width="10%" align="left"></td>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class="popupDataTableDiv"><h:dataTable
						cellspacing="1" width="100%" id="ruleIDDataTable" rowClasses="dataTableEvenRow,dataTableOddRow"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,finalColumn"
						value="#{productDenialAndExclusionBackingBean.ruleIDList}"
						var="ruleIDValue" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
								<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
									id="ruleIDCheckBox" value="#{ruleIDValue}"></h:selectBooleanCheckbox>
						
						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden  id ="RuleIdHidden"  value="#{ruleIDValue.ruleCode}"></h:inputHidden>
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden value="#{ruleIDValue.ruleDescription}"></h:inputHidden>
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{ruleIDValue.ruleCode}"></h:outputText>
							<!-- extara added -->
						</h:column>
						<h:column>
							
                              <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{ruleIDValue.ruleDescription}"></h:outputText>
						</h:column>
						<h:column>
						
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;">
							</h:commandButton>
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
		</div>
			<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
		<table>
			<tr>
				<td><w:message></w:message></td>
			</tr>

		</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	hide();
	function hide(){
		obj = getObj('ruleIDForm:ruleIDDataTable');
		if(obj == null || obj.rows.length == 0) {
			obj2 = getObj('fullInfo');
			obj2.innerHTML = '';
		}else{
			tigra_tables('ruleIDForm:ruleIDDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
			
			window.opener = window.dialogArguments.parentWindow;
			var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
			if(hiddenObj == null || hiddenObj == undefined) {
				alert("Hidden field not available");
			}
			if(hiddenObj.value) {
				var disabledValues = window.opener.document.getElementById('denialAndExclusionForm:txtRule');
				matchCheckboxItems('ruleIDForm:ruleIDDataTable',hiddenObj,disabledValues);
			}
			matchCheckboxItems_ewpd('ruleIDForm:ruleIDDataTable',window.dialogArguments.selectedValues,2,1,1);
		}
	}

function viewAction(){
	getFromDataTableToHidden('ruleIDForm:ruleIDDataTable','RuleIdHidden','ruleIDForm:RuleId');
	var ruleId = document.getElementById('ruleIDForm:RuleId').value;
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
}

function maxCheckedItems(id, attrCount)
{
	var tableObject;	
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
				cnt++;
				if(cnt>50){
					var message = "You have selected more than 50 rules which might result in a large combination of Rules to Provider Arrangement. Click OK to continue or Cancel to reenter"; 
				    if(window.confirm(message)){
						getTildaSeparatedForCheckedItems(id, attrCount)
						return true;
					}else{
//					alert("Please select less than or equal to 10 items only.");
						return false;				
					}
				}
			}
		}	
//		if(cnt<=10){
//			getCheckedItems_ewpd(id, attrCount)
//		}
	}
	getTildaSeparatedForCheckedItems(id, attrCount);
}

function getTildaSeparatedForCheckedItems(id, attrCount)
{
	var tableObject;	
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
				if(cnt > 0)
					selectedValues += '~';
				// replacing '~' to '/w0p8d' to avoid already ~ may present in the description
				var firstCell = currentCell.children[0].value;
				firstCell = firstCell.replace('~','/w0p8d');
				if(attrCount > 1){
					var secondCell = currentCell.children[1].value;
					if(secondCell.length == 0)
					secondCell = ' ';
					while(secondCell.indexOf('~') >= 0){
						secondCell = secondCell.replace('~','/w0p8d');
					}
				}
				if(attrCount == 3){
				
					var thirdCell = currentCell.children[2].value;
					thirdCell = thirdCell.replace('~','/w0p8d');
				}
				
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
		}	
	}
	window.returnValue = selectedValues;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}
</script>
</HTML>
