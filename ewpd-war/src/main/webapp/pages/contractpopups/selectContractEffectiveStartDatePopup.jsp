<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/SelectBaseContractStartDate.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 5%;
}
.shortDescriptionColumn {

}
</style>

<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<f:view>
	<BODY>
	<h:form id="popupForm">

<h:inputHidden id="records"
					value="#{popupBackingBean.records}"></h:inputHidden>
							<table width="100%">
								<TBODY>
									<TR>
										<TD><w:messageForPopup></w:messageForPopup>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
	<div id="header">
		<h:outputText value="Select a Contract" 
					styleClass="dataTableColumnHeader"/>
	</div>
	<div id="fullInfo">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
        
		     <tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton" 
					onclick="getCheckedItems_ewpd('popupForm:componentDataTable',1);return false;"></h:commandButton>
				</td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
	
				<tr>
					<TD> 
						<div id="searchResultdataTableDiv" class="popupDataTableDiv" style="height:300px;">
							<h:dataTable id="componentDataTable"  
								   value="#{popupBackingBean.searchList}" var="eachRow"  cellpadding="2" 
								   cellspacing="1" bgcolor="#cccccc" 
									rendered="#{popupBackingBean.searchList != null}" border="0" 
									rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn">
										
								        <h:column>
												<wpd:singleRowSelect groupName="effectiveDate" id="effectiveDate" rendered="true"></wpd:singleRowSelect>
											
										</h:column>
								        <h:column >
                                           <f:verbatim>&nbsp;</f:verbatim>
											<h:inputHidden value="#{eachRow.effectiveDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:inputHidden>										
											<h:outputText value="#{eachRow.effectiveDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
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

<h:commandLink id="searchLink"
					style="display:none; visibility: hidden;"
					action="#{popupBackingBean.filterResult}"><f:verbatim /></h:commandLink>
<h:commandLink id="loadPopUp"
					style="display:none; visibility: hidden;" 
					action="#{popupBackingBean.getRecords}"><f:verbatim /></h:commandLink>
<h:inputHidden id="entityId"
					value="#{popupBackingBean.entityid}"></h:inputHidden>
<h:inputHidden id="hiddenHeaderName"
					value="#{popupBackingBean.headerName}"></h:inputHidden>
<h:inputHidden id="queryName"
					value="#{popupBackingBean.queryName}"></h:inputHidden>

<h:inputHidden id="popAction"
					value="#{popupBackingBean.popAction}"></h:inputHidden>
<h:inputHidden id="benefitLvlIdHidden"
					value="#{popupBackingBean.benefitLvlId}"></h:inputHidden>
</h:form>
	</BODY>
</f:view>
<script>
	hide();
	function hide(){
		obj = getObj('popupForm:componentDataTable');
		obj3 = getObj('header');
		if(obj == null || obj.rows.length == 0) {
			obj2 = getObj('fullInfo');
			obj2.innerHTML = '';
			
		}
		else{
			obj3.style.visibility = "hidden";
		}
		
	}
	matchCheckboxItems_ewpd('popupForm:componentDataTable',window.dialogArguments.selectedValues,1,1,1);
</script>
	

</HTML>

