<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	vertical-align: middle;
}
.ruleidDescriptionColumn {
	width: 20%;
	vertical-align: middle;
}
.ruleDescriptionColumn {
	width: 60%;
	vertical-align: middle;
}
.viewDescriptionColumn {
	width: 10%;
	vertical-align: middle;
}
</style>
<TITLE>Header Rule Popup</TITLE>
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
	<h:form id="benefitRuleIdForm">
		<h:inputHidden value="#{standardBenefitBackingBean.ruleResultRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('benefitRuleIdForm:stateCodeDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR height="20">
							<TD width="10%"><!--<h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'benefitRuleIdForm:stateCodeDataTable'); ">
							</h:selectBooleanCheckbox>--></TD>
							<TD width="20%"><strong> <h:outputText
								value="Benefit Rule ID">
							</h:outputText> </strong></td>
							<TD width="60%"><strong><h:outputText
								value="Benefit Rule ID Description "></h:outputText></strong></TD>
							<TD width="10%"></TD>
						</TR>
					</table>
					</TD>
				</TR>
				
				<tr>
					<td>
					<DIV id="popupDataTableDiv"  style="overflow:auto;" class="popupDataTableDiv"><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,ruleidDescriptionColumn,ruleDescriptionColumn,viewDescriptionColumn "
						cellspacing="1" width="100%" id="stateCodeDataTable"
						value="#{standardBenefitBackingBean.ruleResultList}"
						var="singleValue" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
							<f:verbatim>
								<wpd:singleRowSelect groupName="majorHeading" 
								id="stateCodeRadioButton" rendered="true"></wpd:singleRowSelect>
							</f:verbatim>
						</h:column>
				
						<h:column>
							
							<h:inputHidden value="#{singleValue.ruleDesc}"></h:inputHidden>
                            
							<h:inputHidden  id ="RuleIdHidden" value="#{singleValue.ruleId}"></h:inputHidden>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.ruleId}"></h:outputText>
						</h:column>
						<h:column>
							
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.ruleDesc}"></h:outputText>
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
		<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">

	tigra_tables('benefitRuleIdForm:stateCodeDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		var disabledValues = window.opener.document.getElementById('genInfoForm:disabledStateCodes');
		matchCheckboxItems('benefitRuleIdForm:stateCodeDataTable',hiddenObj,disabledValues);

	}

	matchCheckboxItems_ewpd('benefitRuleIdForm:stateCodeDataTable',window.dialogArguments.selectedValues,2,2,2);

function viewAction(){
	getFromDataTableToHidden('benefitRuleIdForm:stateCodeDataTable','RuleIdHidden','benefitRuleIdForm:RuleId');
	var ruleId = document.getElementById('benefitRuleIdForm:RuleId').value;
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
}

</script>
</HTML>
