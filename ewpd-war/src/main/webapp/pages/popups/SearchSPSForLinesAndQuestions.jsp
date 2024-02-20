<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "org.apache.commons.lang.StringEscapeUtils" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
.singleRowSelectColumn {
      width: 5%;  
}

.descriptionColumn {
     width: 20%;  
}
.anotherDescriptionColumn {
	width: 75%; 
}	
</style>

<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})" /></title>
</HEAD>
<f:view>
	<BODY>
	<h:form id="popupForm">

		<h:inputHidden id="records"
			value="#{popupBackingBean.filteredRecords}"></h:inputHidden>
		<table width="100%">
			<TBODY>
				<TR>
					<TD>
					<div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">

			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton"
					value="Select" styleClass="wpdbutton"
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
							<td width="5%" align="center" height="27"></TD>
							<td align="center" height="13" width="16%"><strong> <h:outputText
								value="SPS Id" styleClass="outputText"></h:outputText></strong></TD>
							<!--<TD width="20%"></TD>
							-->
							<TD align="center" height="27" width="79%"><strong> <h:outputText
								value="#{popupBackingBean.headerName}" styleClass="outputText"></h:outputText></strong></td>
						</tr>
						<tr>
							<td width="5%" align="center" height="13"></TD>
							<TD width="16%"></TD>
							<TD align="center" height="13" width="79%"><h:inputText
								styleClass="formInputField" id="txtSearch"
								value="#{popupBackingBean.searchString}" maxlength="34"
								tabindex="4" onkeypress="return expandcontract(event)"
								readonly="true" /></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>

				<tr>
					<TD>
					<div id="searchResultdataTableDiv" class="popupDataTableDiv"
						style="height:300px;"><h:dataTable id="componentDataTable"
						value="#{popupBackingBean.searchList}" var="eachRow"
						cellpadding="2" cellspacing="1" bgcolor="#cccccc"
						rendered="#{popupBackingBean.searchList != null}" border="0"
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="singleRowSelectColumn,descriptionColumn,anotherDescriptionColumn">

						<h:column>
							<wpd:singleRowSelect groupName="coverage" id="coverageId"
								rendered="true"></wpd:singleRowSelect>
							
						</h:column>
						<h:column>
							<h:inputHidden value="#{eachRow.spsId}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.description}"></h:inputHidden>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.spsId}"></h:outputText>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
							
						</h:column>
						<h:column>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.description}"></h:outputText>
							
						</h:column>
					</h:dataTable></div>
					<div id="errorMessageDiv" class="popupDataTableDiv"
						style="height:300px;display:none;">
					<table cellpadding="7" cellspacing="1" bgcolor="#cccccc" border="0"
						width="100%">
						<tr>
							<td bgcolor="#e1ecf7" colspan="2" width="100%"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
								font-size:9px;"><font
								color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error
							occurred during processing.</font></td>
						</tr>
					</table>
					</div>
					</TD>
				</tr>
		</TABLE>
		</div>

		<h:commandLink id="searchLink"
			style="display:none; visibility: hidden;"
			action="#{popupBackingBean.filterResult}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="loadPopUp"
			style="display:none; visibility: hidden;"
			action="#{popupBackingBean.getRecords}">
			<f:verbatim />
		</h:commandLink>
		<h:inputHidden id="entityId" value="#{popupBackingBean.entityid}"></h:inputHidden>
		<h:inputHidden id="hiddenHeaderName"
			value="#{popupBackingBean.headerName}"></h:inputHidden>
		<h:inputHidden id="queryName" value="#{popupBackingBean.queryName}"></h:inputHidden>

		<h:inputHidden id="popAction" value="#{popupBackingBean.popAction}"></h:inputHidden>
		<h:inputHidden id="benefitLvlIdHidden"
			value="#{popupBackingBean.benefitLvlId}"></h:inputHidden>

		<h:inputHidden id="termHidden" value="#{popupBackingBean.term}"></h:inputHidden>

		<h:inputHidden id="qualifierHidden"
			value="#{popupBackingBean.qualifier}"></h:inputHidden>

		<h:inputHidden id="dataTypeHidden"
			value="#{popupBackingBean.dataType}"></h:inputHidden>

		<h:inputHidden id="pvaHidden" value="#{popupBackingBean.pva}"></h:inputHidden>

		<h:inputHidden id="spsHidden" value="#{popupBackingBean.spsType}"></h:inputHidden>
		<h:inputHidden id="filterHidden" value="#{popupBackingBean.filter}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>
<script>
document.getElementById('popupForm:txtSearch').readOnly = false; 
function expandcontract(dis) {
	if(dis.keyCode=='13'){
		//submitLink('popupForm:searchLink');
		//document.getElementById('popupForm:checkId').disabled = false;
		var entityId = document.getElementById('popupForm:entityId').value;
		var queryName = document.getElementById('popupForm:queryName').value;
		var headerName = document.getElementById('popupForm:hiddenHeaderName').value;
		var searchField = document.getElementById('popupForm:txtSearch');
		var benefitLvlId = document.getElementById('popupForm:benefitLvlIdHidden').value;
		var divObj = document.getElementById('searchResultdataTableDiv');
		var errorMsgDiv = document.getElementById('errorMessageDiv');

		var term = document.getElementById('popupForm:termHidden').value;
		var qual = document.getElementById('popupForm:qualifierHidden').value;
		var dt = document.getElementById('popupForm:dataTypeHidden').value;
		var pva = document.getElementById('popupForm:pvaHidden').value;
		var spsTy = document.getElementById('popupForm:spsHidden').value;
		var filter = document.getElementById('popupForm:filterHidden').value;




		var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
		// call ajax with the entityid, queryname, header name, search field, div object,table id and error div.
		ajaxCallForSingleSelect('../popups/ajaxHelperTableSPSSingle.jsp?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&benefitLvlId='+benefitLvlId+'&temp='+Math.random()+'&term='+term+'&qualifier='+qual+'&pva='+pva+'&dataType='+dt+'&spsType='+spsTy+'&filter='+filter,searchField,divObj,'popupForm:componentDataTable',errorMsgDiv,attrObj,'','popupForm:checkId');
		return false;
	}
}

	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}


matchCheckboxItems_ewpd('popupForm:componentDataTable',window.dialogArguments.selectedValues,2,1,1);

function matchSingleSelect(){
matchCheckboxItems_ewpd('popupForm:componentDataTable',window.dialogArguments.selectedValues,2,1,1);
}
function stateMaintain(){
	var selectedValue = window.dialogArguments.selectedValues;
	if(null!=selectedValue)
    {
       var spsId = selectedValue.split('~');
	   if(spsId.length ==2)
       {
          var tabObj = document.getElementById("popupForm:componentDataTable").childNodes[0]; 
     	  var tbSize = document.getElementById('popupForm:componentDataTable').rows.length;
		  for(var i=0;i<tbSize;i++)
          {
         	if(tabObj.childNodes[i].childNodes[1].childNodes[0]!=null && tabObj.childNodes[i].childNodes[1].childNodes[0].value==spsId[1])
            {
			   tabObj.childNodes[i].childNodes[0].childNodes[0].click();
			}
		  }
      }
	}
}
stateMaintain();
</script>

</HTML>

