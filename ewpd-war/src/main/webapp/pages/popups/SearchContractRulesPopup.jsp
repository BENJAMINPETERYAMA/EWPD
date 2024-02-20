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
<!-- This tag gets the title name from the request -->
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectCheckboxColumn {
      width: 5%;   
}

.nameColumn {

}

</style>
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<f:view>
	<BODY>
	<h:form id="popupForm">

<h:inputHidden id="records"
					value="#{contractRuleValidationBackingBean.ruleSet}"></h:inputHidden>
							<table width="100%">
								<TBODY>
									<TR>
										<TD><w:messageForPopup></w:messageForPopup>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
	<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
        
		     <tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton" 
					onclick="getCheckedItems_ewpd('popupForm:componentDataTable',2);return false;"></h:commandButton>
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
							<td width="6%" align="center" height="27"><h:selectBooleanCheckbox id="checkId"
								onclick="checkAllid(this,'popupForm:componentDataTable','componentChkBox'); "></h:selectBooleanCheckbox></TD>
							<TD width="94%" align="left" height="27"><strong> <h:outputText value="Contract Rules" styleClass="outputText"></h:outputText></strong></td>
						</tr>
						<tr>
							<td width="6%" align="center" height="13"></TD>
							<TD width="94%" align="left" height="13"></td>
						</tr>
					</table>
</div>
					</TD>
				</TR>
				
				<tr>
					<TD> 
						<div id="searchResultdataTableDiv" class="popupDataTableDiv" style="height:300px;">
							<h:dataTable id="componentDataTable"  
								   value="#{contractRuleValidationBackingBean.searchList}" var="eachRow"  cellpadding="2" 
								   cellspacing="1" bgcolor="#cccccc" 
									rendered="#{contractRuleValidationBackingBean.searchList != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectCheckboxColumn,nameColumn">
										
								        <h:column>
											<f:verbatim>
												<h:selectBooleanCheckbox id="componentChkBox" onclick="isCheckedAll();">
												</h:selectBooleanCheckbox>
											</f:verbatim>
										</h:column>
								        <h:column >
                                           <f:verbatim>&nbsp;</f:verbatim>
											<h:inputHidden value="#{eachRow.invoker}"></h:inputHidden>
											<h:inputHidden value="#{eachRow.name}"></h:inputHidden>										
											<h:outputText value="#{eachRow.name}"></h:outputText>
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


</h:form>
	</BODY>
</f:view>
<script>

//document.getElementById('popupForm:txtSearch').readOnly = false; 
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
		ajaxCall('../popups/ajaxHelperTableContractRules.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&benefitLvlId='+benefitLvlId+'&temp='+Math.random(),searchField,divObj,'popupForm:componentDataTable',errorMsgDiv,attrObj,'','popupForm:checkId');
		return false;
	}
}



matchCheckboxItems_ewpd('popupForm:componentDataTable',window.dialogArguments.selectedValues,2,2,2);

isCheckedAll();
function isCheckedAll(){
	tableObj = document.getElementById("popupForm:componentDataTable");
	obj1 = document.getElementById("popupForm:checkId");
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

