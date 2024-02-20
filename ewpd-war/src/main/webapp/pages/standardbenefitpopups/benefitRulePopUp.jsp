<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "org.apache.commons.lang.StringEscapeUtils" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
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
vertical-align: middle;
}
.ruleIdColumn {
	width: 20%;
vertical-align: middle;
}

.ruleDescriptionColumn {
	width: 60%;
vertical-align: middle;
}

.viewColumn {
	width: 10%;
vertical-align: middle;
}
</style>
<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<h:form id="benefitRuleIdForm">
		<h:inputHidden id="records"
					value="#{popupBackingBean.filteredRecords}"></h:inputHidden>

							<table>
								<TBODY>
									<TR>
										<TD><div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div></TD>
									</TR>
								</TBODY>
							</TABLE>				
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('benefitRuleIdForm:stateCodeDataTable',3);return false;"></h:commandButton>


				</td>
			</tr>
		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
			<div id="headerTable">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR height="20">
							<TD width="10%"><!--<h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'benefitRuleIdForm:stateCodeDataTable'); ">
							</h:selectBooleanCheckbox>--></TD>
							<TD width="20%"><strong> <h:outputText
								value="Rule ID">
							</h:outputText> </strong></td>
							<TD width="60%"><strong><h:outputText
								value="Description "></h:outputText></strong></TD>
							<TD width="10%"></TD>
						</TR>
						<TR height="20">
							<TD width="10%"><!--<h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'benefitRuleIdForm:stateCodeDataTable'); ">
							</h:selectBooleanCheckbox>--></TD>
							<TD width="20%"></td>
							<TD width="60%"><h:inputText styleClass="formInputField"
											id="txtSearch"
											value="#{popupBackingBean.searchString}"
											maxlength="34" tabindex="4" onkeypress="return expandcontract(event)" readonly = "true"/></TD>
							<TD width="10%"></TD>
						</TR>
					</table>
</div>
					</TD>
				</TR>
				
				<tr>
					<td>
					<DIV id="popupDataTableDiv"  style="overflow:auto;" class="popupDataTableDiv">
					<h:dataTable id="stateCodeDataTable"
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1" width="100%" 
						value="#{popupBackingBean.searchList}"
						var="singleValue" rendered="#{popupBackingBean.searchList!=null}" cellpadding="0" bgcolor="#cccccc"
						columnClasses="selectOrOptionColumn, ruleIdColumn, ruleDescriptionColumn, viewColumn" >
						<h:column>
							
							<f:verbatim>
								<wpd:singleRowSelect groupName="majorHeading" 
								id="stateCodeRadioButton" rendered="true"></wpd:singleRowSelect>
							</f:verbatim>
						</h:column>
				
						<h:column>
							
							<h:inputHidden value="#{singleValue.description}"></h:inputHidden>
							<h:inputHidden  id ="RuleIdHidden" value="#{singleValue.ruleId}"></h:inputHidden>
							<h:inputHidden  id ="RuleTypeHidden" value="#{singleValue.ruleType}"></h:inputHidden>

							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.ruleId}"></h:outputText>
						</h:column>
						<h:column>
							
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{singleValue.description}"></h:outputText>
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
					 
						<div id="errorMessageDiv" class="popupDataTableDiv" style="height:300px;display:none;">
							<table cellpadding="7" 
								   cellspacing="1" bgcolor="#cccccc" 
									border="0" width="100%" >
								<tr>
									<td bgcolor="#e1ecf7" colspan="2" width="100%" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
								font-size:9px;"><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error occurred during processing.</font></td></tr>									              															              							
						   </table>
						</div>	 
					</td>
				</tr>
			</TBODY>
		</table>
		<h:inputHidden id ="RuleType"  value ="#{contractRuleBackingBean.blzRuleType}"></h:inputHidden>
		<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden> 
		<h:inputHidden id="queryName"
					value="#{popupBackingBean.queryName}"></h:inputHidden>
		<h:commandLink id="loadPopUp"
					style="display:none; visibility: hidden;" 
					action="#{popupBackingBean.getRecords}"><f:verbatim /></h:commandLink>
		<h:commandLink id="searchLink"
					style="display:none; visibility: hidden;"
					action="#{popupBackingBean.filterResult}"><f:verbatim /></h:commandLink>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
		<h:inputHidden id="popAction"
					value="#{popupBackingBean.popAction}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
document.getElementById('benefitRuleIdForm:txtSearch').readOnly = false; 
	


	
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
	getFromDataTableToHidden('benefitRuleIdForm:stateCodeDataTable','RuleTypeHidden','benefitRuleIdForm:RuleType');
	var ruleId = document.getElementById('benefitRuleIdForm:RuleId').value;
	var ruleType = document.getElementById('benefitRuleIdForm:RuleType').value;
	
	if(ruleType=="BLZWPDAB")
	{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {

		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}


	
}
function expandcontract(dis) {
if(dis.keyCode=='13'){
		var entityId = "0";
		var queryName = document.getElementById('benefitRuleIdForm:queryName').value;
		var headerName = "";
		var searchField = document.getElementById('benefitRuleIdForm:txtSearch');
		var divObj = document.getElementById('popupDataTableDiv');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
		ajaxCall1('../popups/ajaxHelperBenefitRuleTable.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&temp='+Math.random(),searchField,divObj,'benefitRuleIdForm:stateCodeDataTable',errorMsgDiv,attrObj,'','');
//submitLink('benefitRuleIdForm:searchLink');
return false;
}
}
</script>
</HTML>
