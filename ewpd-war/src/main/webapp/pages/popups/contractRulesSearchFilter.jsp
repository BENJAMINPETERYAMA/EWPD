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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 5%;
	
}.shortDescriptionColumn {
	width: 18%;
	
}
.longDescriptionColumn {
	width: 67%;
	
}
.eachRow {
	width: 10%;
	
}
</style>
<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>

	<h:form id="baseContractCodeForm">
	<h:inputHidden id="records"
					value="#{popupBackingBean.records}"></h:inputHidden>
		<table>
			<tr>
				<td><w:messageForPopup>
					</w:messageForPopup></td>
			</tr>
		</table>
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="maxCheckedItems('baseContractCodeForm:baseContractCodeTable',3);return false;" disabled="true">
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
								<h:selectBooleanCheckbox id="checkId" onclick="checkAll_ewpd(this,'baseContractCodeForm:baseContractCodeTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD width="18%" align="left"><STRONG><h:outputText
								value="Rule Generated Id"></h:outputText></STRONG></TD>
							<TD width="57%" align="left"><STRONG><h:outputText
								value="Description"></h:outputText></STRONG></TD>
							<TD width="10%" align="left"><STRONG><h:outputText
								value="Rule PVA"></h:outputText></STRONG></TD>
							<TD width="10%" align="center"><STRONG><h:outputText
								value=""></h:outputText></STRONG></TD>
						</tr>
						<tr>
							<TD width="5%" align="left">
								
							</TD>
							<td align="center" width="18%"></TD>
							<TD align="left" width="57%"><h:inputText styleClass="formInputField"
											id="txtSearch"
											value="#{popupBackingBean.searchString}"
											maxlength="34" tabindex="4" onkeypress="return expandcontract(event)" readonly = "true"/></td>
							<TD width="10%"></TD>
							<TD width="110%"></TD>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,eachRow"
						width="100%" id="baseContractCodeTable"
						value="#{popupBackingBean.searchList}"
						rendered="#{popupBackingBean.searchList != null}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
						
								<h:selectBooleanCheckbox id="lineOfBusinessChkBox" onclick="isCheckedAll();">
								</h:selectBooleanCheckbox>

						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.generatedRuleId}"></h:outputText>
							<h:inputHidden id ="RuleDescHidden" value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden id ="ProductRuleIdHidden" value="#{eachRow.productRuleSysId}"></h:inputHidden>
							<h:inputHidden id ="RuleIdHidden" value="#{eachRow.ruleId}"></h:inputHidden>  
						</h:column>
						<h:column>
						
                              <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.description}"></h:outputText>
							
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
		</div>
		<h:inputHidden id="entityId"
					value="#{popupBackingBean.entityid}"></h:inputHidden>
		<h:inputHidden id="hiddenHeaderName"
							value="#{popupBackingBean.headerName}"></h:inputHidden>
		<h:inputHidden id="queryName"
							value="#{popupBackingBean.queryName}"></h:inputHidden>
		<h:commandLink id="searchLink"
							style="display:none; visibility: hidden;"
							action="#{popupBackingBean.filterResult}"><f:verbatim /></h:commandLink>
		<h:inputHidden id="popAction"
							value="#{popupBackingBean.popAction}"></h:inputHidden>
		<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
				<DIV id="dummyDiv" style="visibility:hidden">
	</DIV>
		
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
document.getElementById('baseContractCodeForm:txtSelect').disabled  = false;
document.getElementById('baseContractCodeForm:txtSearch').readOnly = false; 
function expandcontract(dis) {
if(dis.keyCode=='13'){
//submitLink('baseContractCodeForm:searchLink');
		document.getElementById('baseContractCodeForm:checkId').disabled = false;
		var entityId = document.getElementById('baseContractCodeForm:entityId').value;
		var queryName = document.getElementById('baseContractCodeForm:queryName').value;
		var headerName = document.getElementById('baseContractCodeForm:hiddenHeaderName').value;
		var searchField = document.getElementById('baseContractCodeForm:txtSearch');
		var divObj = document.getElementById('popupDataTableDiv');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=3;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
		ajaxCall('../popups/ajaxContractRulesHelperTable.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&temp=' + Math.random(),searchField,divObj,'baseContractCodeForm:baseContractCodeTable',errorMsgDiv,attrObj,'','baseContractCodeForm:checkId');
		return false;
}
}
checkTableAlignemnt();
function checkTableAlignemnt(){
var x = document.getElementById("baseContractCodeForm:baseContractCodeTable");
if(null!=x){
setColumnWidth('baseContractCodeForm:baseContractCodeTable','5%:18%:57%:10%:10%');
}
}

window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {

	matchCheckboxItems_ewpd('baseContractCodeForm:baseContractCodeTable', window.dialogArguments.selectedValues, 3, 3, 2);
	}

	
function viewAction(){
	getFromDataTableToHidden('baseContractCodeForm:baseContractCodeTable','RuleIdHidden','baseContractCodeForm:RuleId');
	var ruleId = document.getElementById('baseContractCodeForm:RuleId').value;
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp='+Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
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

hide();
	function hide(){
		obj = getObj('baseContractCodeForm:baseContractCodeTable');
		obj1 = getObj('baseContractCodeForm:checkId');
		var actionvalue = document.getElementById('baseContractCodeForm:popAction').value;

		var actionvalue = document.getElementById('baseContractCodeForm:popAction').value;
		if(obj == null || obj.rows.length == 0) {
			if(actionvalue ==2){
				obj2 = getObj('popupDataTableDiv');
				obj2.innerHTML = '';
				obj1.disabled = "true";
		
			}else{
					obj3 = getObj('fullInfo');
					obj3.innerHTML = '';
			}
		}
	}
isCheckedAll();
function isCheckedAll(){
	tableObj = document.getElementById("baseContractCodeForm:baseContractCodeTable");
	if(null!=tableObj){
		for (var i=0;i<tableObj.rows.length;i++){
			var checkboxObject = tableObj.rows[i].cells[0].children[0];
				if(checkboxObject.checked==false){
					obj1 = document.getElementById("baseContractCodeForm:checkId");
					obj1.checked=false;
					break;
				}else{
				obj1.checked=true;
			}
		}
	}
}
matchCheckboxItems_ewpd('baseContractCodeForm:baseContractCodeTable', window.dialogArguments.selectedValues, 3, 2, 2);

</script>

</HTML>