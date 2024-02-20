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


<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.checkBoxColumn {
      width: 5%;
      
}
.descriptionColumn {
         
}
.anotherDescriptionColumn { 
      
}
.commandButtonColumn {
      width: 10%;
}

</style>
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>

<BASE target="_self" />
<f:view>
	<BODY>
	<h:form id="popupForm">
		<h:inputHidden id="records"
							value="#{popupBackingBean.records}"></h:inputHidden>

							<table>
								<TBODY>
									<TR>
										<TD><div id ="infoDivMessage"><w:messageForPopup></w:messageForPopup></div></TD>
									</TR>
								</TBODY>
							</TABLE>
	<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
        
		     <tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select" disabled="true"
					styleClass="wpdbutton" 
					onclick="getCheckedItems_ewpd('popupForm:ruleDataTable',2);return false;"></h:commandButton>
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
							<td align="center" width="4%"><h:selectBooleanCheckbox id="checkId"
								onclick="checkAllid(this,'popupForm:ruleDataTable','componentChkBox'); "></h:selectBooleanCheckbox></TD>
							<td align="left" width="19%"><STRONG><h:outputText value="Rule Id" styleClass="outputText"></h:outputText></STRONG></TD>
							<TD align="left" width="77%"><strong> <h:outputText value="Description" styleClass="outputText"></h:outputText></strong></td>
						</tr>
						<tr>
							<td align="center" width="4%"></TD>
							<TD width="19%"></TD>
							<TD align="left" width="77%"><h:inputText styleClass="formInputField"
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
							<h:dataTable id="ruleDataTable"  
								   value="#{popupBackingBean.searchList}" var="eachRow"  cellpadding="2" 
								   cellspacing="1" bgcolor="#cccccc" 
									rendered="#{popupBackingBean.searchList != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="checkBoxColumn,descriptionColumn,anotherDescriptionColumn,commandButtonColumn">
										
								        <h:column>
											<f:verbatim>
												<h:selectBooleanCheckbox id="componentChkBox" onclick="isCheckedAll();">
												</h:selectBooleanCheckbox>
											</f:verbatim>
											
										</h:column>
								        <h:column >
                                           <f:verbatim>&nbsp;</f:verbatim>
											<h:inputHidden id ="RuleIdHidden" value="#{eachRow.ruleId}"></h:inputHidden>
											<h:inputHidden value="#{eachRow.description}"></h:inputHidden>										
											<h:outputText id ="ruleId" value="#{eachRow.ruleId}"></h:outputText>
										</h:column>	
								        <h:column >
                                           <f:verbatim>&nbsp;</f:verbatim>
											<h:inputHidden value="#{eachRow.ruleId}"></h:inputHidden>
											<h:inputHidden value="#{eachRow.description}"></h:inputHidden>										
											<h:outputText value="#{eachRow.description}"></h:outputText>
										</h:column>	
										<h:column>
											
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="View" id="viewButton"
																image="../../images/view.gif"
																onclick="viewAction();return false;">
											</h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											
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
<h:inputHidden id ="RuleId"  value ="#{contractRuleBackingBean.ruleIdDetails}"></h:inputHidden>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>														
<h:inputHidden id="entityId"
					value="#{popupBackingBean.entityid}"></h:inputHidden>
<h:inputHidden id="hiddenHeaderName"
					value="#{popupBackingBean.headerName}"></h:inputHidden>
<h:inputHidden id="queryName"
					value="#{popupBackingBean.queryName}"></h:inputHidden>
<h:commandLink id="searchLink"
					style="display:none; visibility: hidden;"
					action="#{popupBackingBean.filterResult}"><f:verbatim /></h:commandLink>
<h:commandLink id="loadPopUp"
					style="display:none; visibility: hidden;" 
					action="#{popupBackingBean.getRecords}"><f:verbatim /></h:commandLink>
<h:inputHidden id="popAction"
					value="#{popupBackingBean.popAction}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>
<script>
document.getElementById('popupForm:selectButton').disabled  = false;
document.getElementById('popupForm:txtSearch').readOnly = false; 
function expandcontract(dis) {
if(dis.keyCode=='13'){
		document.getElementById('popupForm:checkId').disabled = false;
		var entityId = document.getElementById('popupForm:entityId').value;
		var queryName = document.getElementById('popupForm:queryName').value;
		var headerName = document.getElementById('popupForm:hiddenHeaderName').value;
		var searchField = document.getElementById('popupForm:txtSearch');
		var divObj = document.getElementById('searchResultdataTableDiv');
		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=1;
			attrObj[2]=1;
		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
		ajaxCall('../popups/ajaxHelperProductRuleTable.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&temp='+Math.random(),searchField,divObj,'popupForm:ruleDataTable',errorMsgDiv,attrObj,'','popupForm:checkId');
return false;
}
}

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}

	hide();
	function hide(){
		obj = getObj('popupForm:ruleDataTable');
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
function viewAction(){
	getFromDataTableToHidden('popupForm:ruleDataTable','RuleIdHidden','popupForm:ruleId');
	var ruleId = document.getElementById('popupForm:ruleId').value;	
	window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
}
matchCheckboxItems_ewpd('popupForm:ruleDataTable',window.dialogArguments.selectedValues,2,1,1);
isCheckedAll();
function isCheckedAll(){
	tableObj = document.getElementById("popupForm:ruleDataTable");
	if(null!=tableObj){
		for (var i=0;i<tableObj.rows.length;i++){
			var checkboxObject = tableObj.rows[i].cells[0].children[0];
				if(checkboxObject.checked==false){
					obj1 = document.getElementById("popupForm:checkId");
					obj1.checked=false;
					break;
				}else{
				obj1.checked=true;
			}
		}
	}
}
</script>
	
</HTML>

