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
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 75%;
}
</style>

<!-- This tag gets the title name from the request -->
<title><c_rt:out value="StringEscapeUtils.escapeXml(${param.titleName})"/></title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="benefitTermSelectPopupForm">
<h:inputHidden id="search" value="#{ReferenceDataBackingBeanCommon.searchTermQualifier}"></h:inputHidden>
			<TABLE id="message">
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
											<div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
											</TD>
										</tr>		
									</TBODY>
			</TABLE>
			<table border="0" cellpadding="5" cellspacing="0" width="100%">
                <tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getSelected();return false;"></h:commandButton>
				</td>
			</tr>
		</table>


		<table width="98%" border="0" align="right" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr>
						
							<TD width="10%" align="center" valign="middle"></TD>
							<TD width="20%"></TD>
							<TD align="left" width="70%" height="20px"><strong><h:outputText
								value="Qualifier"></h:outputText></strong></TD>

						
					</tr>
					<tr>
						
							<TD width="10%" align="center" valign="middle"></TD>
							<TD width="20%"></TD>
							<TD align="left" width="70%" height="20px"><h:inputText styleClass="formInputField"
											id="txtSearch"
											value="#{ReferenceDataBackingBeanCommon.searchString}"
											maxlength="34" tabindex="4" onkeypress="return expandcontract(event)" readonly = "true"/></TD>

						
					</tr>
				</table>
				</td>
			</tr>
			<tr id="tr1">
				<td>
				<DIV id="popupDataTableDiv1" style="overflow:auto;" class="popupDataTableDiv"
					style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<h:dataTable id="benefitTermSelectPopupTable1" cellspacing="1"
					width="100%" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}"
					cellpadding="0" bgcolor="#cccccc" rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn" >
					<h:column>
						
						<f:verbatim>
							<wpd:singleRowSelect groupName="qualifier" id="minor1"
								rendered="true"></wpd:singleRowSelect>
						</f:verbatim>
					</h:column>
					<h:column>
						
						<h:inputHidden id="hiddenQualifier" value="#{eachRow.description}"></h:inputHidden>
						<h:inputHidden id="hiddenQualifierCode" value="#{eachRow.primaryCode}"></h:inputHidden>
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
					</h:column>
					<h:column>
						
						<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText value="#{eachRow.description}"></h:outputText>
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
           
			
		</table>
        <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
		<h:inputHidden id="parentCatalog"
					value="#{ReferenceDataBackingBeanCommon.catalogNameForTermQualifier}"></h:inputHidden>
		<h:inputHidden id="lookUpAction"
					value="#{ReferenceDataBackingBeanCommon.lookUpAction}"></h:inputHidden>	
	</h:form>
	</BODY>
</f:view>
<script language="javascript">
	document.getElementById('benefitTermSelectPopupForm:txtSearch').readOnly = false; 
	matchCheckboxItems_ewpd('benefitTermSelectPopupForm:benefitTermSelectPopupTable1',window.dialogArguments.selectedValues,2,2,2);
 //   matchCheckboxItems_ewpd('benefitTermSelectPopupForm:termSelectPopupTable2',window.dialogArguments.selectedValues,2,2,2);
	var sort = document.getElementById('benefitTermSelectPopupForm:hiddensortorder');

	var TR1 = document.getElementById('tr1');
	//var TR2 = document.getElementById('tr2');
	//var div1 = document.getElementById('popupDataTableDiv1');
	//var div2 = document.getElementById('popupDataTableDiv2');
	//if(sort != null ){
	//	tigra_tables('benefitTermSelectPopupForm:benefitTermSelectPopupTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	//	div2.style.visibility = 'hidden';
	//	TR2.style.visibility = 'hidden';
	//	div2.style.height = '1px';
	//	TR2.style.height = '1px';
		
	//}
	//else if(sort != null && sort.value == 'desc'){
	//    tigra_tables('benefitTermSelectPopupForm:benefitTermSelectPopupTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	//	div1.style.visibility = 'hidden';
	//	TR1.style.visibility = 'hidden';
	//	div1.style.height = '1px';
	//	TR1.style.height = '1px'; 
//	}
	function getSelected(){
	if(sort != null){
	
		getCheckedItems_ewpd('benefitTermSelectPopupForm:benefitTermSelectPopupTable1',2);return false;
	}
	//else if(sort != null && sort.value == 'desc'){
	
	//getCheckedItems_ewpd('benefitTermSelectPopupForm:benefitTermSelectPopupTable2',2);return false;
	//}
	}
	
		function expandcontract(dis) {
			if(dis.keyCode=='13'){
    		var catalogName = document.getElementById('benefitTermSelectPopupForm:parentCatalog').value;
			var lookUpAction = document.getElementById('benefitTermSelectPopupForm:lookUpAction').value;
            var searchField = document.getElementById('benefitTermSelectPopupForm:txtSearch');
            var divObj = document.getElementById('popupDataTableDiv1');
            var errorMsgDiv = document.getElementById('errorMessageDiv');
            // call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.		
			var attrObj =new Array(3);
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;
            ajaxCall1('../popups/ajaxHelperAdminQulifierTable.jsp'+getUrl()+'?parentCatalog='
						+catalogName+'&lookUpAction='+lookUpAction+'&temp='+Math.random(),searchField,divObj,
						'benefitTermSelectPopupForm:benefitTermSelectPopupTable1',errorMsgDiv,attrObj,'','');
            return false;
			}
		}
</script>
</HTML>


