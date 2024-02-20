<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/ReservedIdPopup.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 5%;
}
.ruleIdColumn {
	width: 18%;
}
.ruleDescriptionColumn {
	width: 67%;
}
.viewColumn {
	width: 10%;
}

</style>

<TITLE>Rule Id Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<h:form id="baseContractCodeForm">
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="maxCheckedItems('baseContractCodeForm:baseContractCodeTable',3);return false;">
				</h:commandButton></td>
			</tr>

		</table>

		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="5%" align="left">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'baseContractCodeForm:baseContractCodeTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD width="18%" align="center"><STRONG><h:outputText
								value="Rule Generated Id"></h:outputText></STRONG></TD>
							<TD width="57%" align="center"><STRONG><h:outputText
								value="Description"></h:outputText></STRONG></TD>
							<TD width="10%" align="center"><STRONG><h:outputText
								value="Rule PVA"></h:outputText></STRONG></TD>
							<TD width="10%" align="center"><STRONG><h:outputText
								value=""></h:outputText></STRONG></TD>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,ruleIdColumn,ruleDescriptionColumn,viewColumn"
						cellspacing="1"
						width="100%" id="baseContractCodeTable" 
						value="#{contractRuleBackingBean.ruleResultList}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
								<h:selectBooleanCheckbox id="lineOfBusinessChkBox">
								</h:selectBooleanCheckbox>

						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.generatedRuleId}"></h:outputText>
							<h:inputHidden id ="RuleDescHidden" value="#{eachRow.ruleShortDescription}"></h:inputHidden>
							<h:inputHidden id ="ProductRuleIdHidden" value="#{eachRow.productRuleSysId}"></h:inputHidden>
							<h:inputHidden id ="RuleIdHidden" value="#{eachRow.ruleId}"></h:inputHidden>  
						</h:column>
						<h:column>
							
                              <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.ruleShortDescription}"></h:outputText>
							
						</h:column>
						<h:column>
							
                              <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.rulePVA}"></h:outputText>
							
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
				<td><w:messageForPopup>
					</w:messageForPopup></td>
			</tr>
		</table>
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
setColumnWidth('baseContractCodeForm:baseContractCodeTable','5%:18%:57%:10%:10%');
window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {

	matchCheckboxItems_ewpd('baseContractCodeForm:baseContractCodeTable', window.dialogArguments.selectedValues, 3, 3, 2);
	}

	hide();
	function hide(){
		obj = getObj('baseContractCodeForm:baseContractCodeTable');
		if(obj == null || obj.rows.length == 0) {
			obj2 = getObj('fullInfo');
			obj2.innerHTML = '';
		}
	}

function viewAction(){
	getFromDataTableToHidden('baseContractCodeForm:baseContractCodeTable','RuleIdHidden','baseContractCodeForm:RuleId');
	var ruleId = document.getElementById('baseContractCodeForm:RuleId').value;
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
}

</script>

</HTML>