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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
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
}
.shortDescriptionColumn {
	width: 92%;
}
</style>

<TITLE>Contract Id Popup</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:inputHidden id="testDateSegments"
				value="#{contractPopupBackingBean.reserve}" />
	
	<h:form id="baseContractCodeForm">
<div id="msgDiv">
							<table width="100%">
								<TBODY>
									<TR>
										<TD><w:messageForPopup></w:messageForPopup>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
</div>
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('baseContractCodeForm:baseContractCodeTable',2);return false;" disabled="true">
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
							<TD width="6%" align="left"></TD>
							<TD width="94%" align="center"><strong><h:outputText
								value="Reserved Id"></h:outputText></strong></TD>
						</TR>
						
						<TR height="20">
							<TD width="6%"></TD>							
							<TD width="94%" align="center"><h:inputText styleClass="formInputField"
											id="txtSearch"
											value="#{contractPopupBackingBean.searchString}"
											maxlength="34" tabindex="4" onkeypress="return expandcontract(event)"/></TD>							
					</TR>


					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1"
						width="100%" id="baseContractCodeTable"
						value="#{contractPopupBackingBean.reserveIds}"
						rendered="#{contractPopupBackingBean.reserveIds!=null}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<wpd:singleRowSelect groupName="baseContract" id="minor1" rendered="true"></wpd:singleRowSelect>								
							</f:verbatim>

						</h:column>
						<h:column>
							
							<h:outputText value="#{eachRow.contractId}"></h:outputText>
							<h:inputHidden value="#{eachRow.contractId}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.reservePoolStatus}"></h:inputHidden> 
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
		<h:inputHidden id="txtLOB"
							value="#{contractPopupBackingBean.lob}"></h:inputHidden>
		<h:inputHidden id="txtBE"
							value="#{contractPopupBackingBean.be}"></h:inputHidden>
		<h:inputHidden id="txtBG"
							value="#{contractPopupBackingBean.bg}"></h:inputHidden>
		<h:inputHidden id="txtMBU"
							value="#{contractPopupBackingBean.mbu}"></h:inputHidden>

	</div>
		
	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">
document.getElementById('baseContractCodeForm:txtSelect').disabled  = false;
setColumnWidth('baseContractCodeForm:baseContractCodeTable','8%:92%');

matchCheckboxItems_ewpd('baseContractCodeForm:baseContractCodeTable', window.dialogArguments.selectedValues,2, 1, 1)
	hide();
	function hide(){
		obj = getObj('baseContractCodeForm:baseContractCodeTable');
		if(obj == null || obj.rows.length == 0) {
			obj2 = getObj('fullInfo');
			obj2.innerHTML = '';
		}
	}

function expandcontract(dis) {
if(dis.keyCode=='13'){
		var lob = document.getElementById('baseContractCodeForm:txtLOB');
		var be = document.getElementById('baseContractCodeForm:txtBE');
		var bg = document.getElementById('baseContractCodeForm:txtBG');
		var mbu = document.getElementById('baseContractCodeForm:txtMBU');
		var searchField = document.getElementById('baseContractCodeForm:txtSearch');
		var divObj = document.getElementById('popupDataTableDiv');

		var errorMsgDiv = document.getElementById('errorMessageDiv');
		var attrObj =new Array(3);
			attrObj[0]=1;
			attrObj[1]=1;
			attrObj[2]=1;
var tableobj = document.getElementById('baseContractCodeForm:baseContractCodeTable');
var url = '../popups/ajaxHelperContractIdTable.jsp';
url = url 	+ '?lob='+ escape(document.getElementById('baseContractCodeForm:txtLOB').value)
			+ '&be='+ escape(document.getElementById('baseContractCodeForm:txtBE').value)
			+ '&bg='+ escape(document.getElementById('baseContractCodeForm:txtBG').value)
			+ '&mbu='+ escape(document.getElementById('baseContractCodeForm:txtMBU').value)
			+ '&temp='+Math.random();	

		// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and form name.
		//ajaxCall('../popups/ajaxHelperBenefitRuleTable.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&temp='+Math.random(),searchField,divObj,'benefitRuleIdForm:stateCodeDataTable',errorMsgDiv,attrObj);
		ajaxCall(url,searchField,divObj,'baseContractCodeForm:baseContractCodeTable',errorMsgDiv,attrObj,'baseContractCodeForm:errorDataTable');	
//submitLink('benefitRuleIdForm:searchLink');
return false;
}
}




</script>

</HTML>