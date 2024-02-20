<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
		width: 20%;
}
.shortDescriptionColumn {
     width: 40%;

}
.longDescriptionColumn {
	width: 15%;

}
.selectOptionColumn {

}	
</style>
<TITLE>Rule ID Popup</TITLE>
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
			<table  border="0" cellpadding="5" cellspacing="0" width="100%">
            
				<tr>
					<td align="left">&nbsp;
						<h:commandButton id="selectButton" value="Select" styleClass="wpdbutton" onclick="maxCheckedItems('ruleIDForm:ruleIDDataTable',2);return false;"></h:commandButton>
					</td>
				</tr>
	
		 	</table>
			<table width="98%"  align="right" cellpadding="0" cellspacing="0" >
			<TBODY>
				<TR>
					<TD>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
			  <td width="10%"><input name="checkbox" type="checkbox" id="checkall" onClick="checkAll_ewpd(this,'ruleIDForm:ruleIDDataTable');"></td>
			  <td width="20%" align="left"><strong><h:outputText value="Rule Id" styleClass="outputText"/></strong></td>
			  <td width="40%" align="left"><strong><h:outputText value="Rule Description" styleClass="outputText"/></strong></td>
			  <td width="15%" align="left"><strong><h:outputText value="Rule PVA" styleClass="outputText"/></strong></td>
			  <td width="15%" align="left"><strong><h:outputText value="Group Indicator" styleClass="outputText"/></strong></td>
							</tr>
						</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class="popupDataTableDiv">
						<h:dataTable  cellspacing="1" width="100%" columnClasses="selectOptionColumn,selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,longDescriptionColumn"
						id="ruleIDDataTable" value="#{migrationContractRulesBackingBean.ruleIDList}" var="ruleIDValue" cellpadding="0" bgcolor="#cccccc">
						 	<h:column >
								<f:verbatim> 
  								<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox" id="ruleIDCheckBox" value="#{ruleIDValue}"></h:selectBooleanCheckbox>
								</f:verbatim>									
						 	</h:column>
						 	<h:column>
                            	<f:verbatim>&nbsp;</f:verbatim>
								<h:inputHidden value="#{ruleIDValue.productRuleSysID}"></h:inputHidden>
								<h:inputHidden value="#{ruleIDValue.ruleDescription}"></h:inputHidden>
								<h:outputText value="#{ruleIDValue.ruleID}"></h:outputText><!-- extara added -->
							</h:column>
						 	<h:column>
                                <f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{ruleIDValue.ruleDescription}"></h:outputText>
							</h:column>
						 	<h:column>
                                	<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{ruleIDValue.providerArrangement}"></h:outputText>
							</h:column>
						 	<h:column>
                                	<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{ruleIDValue.groupIndicator}"></h:outputText>
							</h:column>
						</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
			</table>
		</div>
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
				var disabledValues = window.opener.document.getElementById('ruleIDForm:disabledStateCodes');
				matchCheckboxItems('ruleIDForm:ruleIDDataTable',hiddenObj,disabledValues);
		
			}
			matchCheckboxItems_ewpd('ruleIDForm:ruleIDDataTable',window.dialogArguments.selectedValues,2,2,2);
		}
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
						getCheckedItems_ewpd(id, attrCount)
						return true;
					}else{
						return false;				
					}
				}
			}
		}	
	}
	getCheckedItems_ewpd(id, attrCount);
	return true;
}

</script>
</HTML>
