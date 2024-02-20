<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.singleRowSelectColumn {
      width: 10%;
      text-align: center;
      vertical-align: middle;
}
.singleRowValueColumn {
	width: 5%;
}	
.singleRowDescriptionColumn {
     width: 45%;
}
.singleRowCommentColumn {
	width: 50%;
}	
</style>
	
<TITLE>EB01 </TITLE>
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
	<BODY><hx:scriptCollector id="scriptCollector1">
		<h:form id="eb01form">
			<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">
		
				<TR>
					<TD align="left">&nbsp;
						<h:commandButton id="selectButton" value="Select"
						styleClass="wpdbutton"
						onclick="getSelected();return false;"></h:commandButton></TD>
				</TR>
		 	</TABLE>
			<TABLE width="97%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<TR>
								<TD width="10%" align="center" valign="middle"></TD>
								<TD width="20%"></TD>
								<TD align="left" width="70%" height="20px"><STRONG><h:outputText value="EB01"></h:outputText></STRONG></TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
				<TR id="tr1">
					<TD>
					<DIV class="popupDataTableDiv" id="popupDataTableDiv1">
						<h:dataTable cellspacing="1" width="100%" id="prodFamilyDataTable1" 
						rendered="#{ReferenceDataBackingBeanCommon.SPS_SearchString!=null}"
						value="#{ReferenceDataBackingBeanCommon.spsMappingSearchResults}" 
						var="eachRow" cellpadding="0" bgcolor="#cccccc" columnClasses="singleRowSelectColumn,singleRowValueColumn,singleRowDescriptionColumn,singleRowCommentColumn">
						 	<h:column>
								<f:verbatim> <wpd:singleRowSelect groupName="majorHeading" id="major" rendered="true"></wpd:singleRowSelect> </f:verbatim>
						 	</h:column>
						 	<h:column>
                                          
                                           <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.value}"></h:outputText>
							</h:column>
							<h:column>
										   
										   <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.desc }"></h:outputText>
							</h:column>
							<h:column>
										   
										   <f:verbatim>&nbsp;</f:verbatim>
                                          <h:outputText value="#{eachRow.comment}"></h:outputText>
							</h:column>
						</h:dataTable>
					</DIV>
					</TD>
				</TR>
			</TBODY>
			</TABLE>

		</h:form>
	</hx:scriptCollector></BODY>
</f:view>

<script language="javascript">
tigra_tables('eb01form:prodFamilyDataTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');

	/*setColumnWidth('prodFamilyForm:prodFamilyDataTable1','6%');
    setColumnWidth('prodFamilyForm:prodFamilyDataTable2','6%');
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}*/
//	if(hiddenObj.value) {
		 matchCheckboxItems_ewpd('prodFamilyForm:prodFamilyDataTable1',window.dialogArguments.selectedValues,2,2,2);
//	}
   


function getSelected(){
getCheckedItems_ewpd('prodFamilyForm:prodFamilyDataTable1',2);return false;
}
</script>
</HTML>
