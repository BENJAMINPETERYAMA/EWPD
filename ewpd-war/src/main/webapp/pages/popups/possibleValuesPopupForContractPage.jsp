<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%> 
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	text-align:center;
	vertical-align: middle;
	 
}.longDescriptionColumn {
	text-align:left;
	vertical-align: middle;
}
</style>
<TITLE>Possible Values for Tiering</TITLE>
</HEAD>
<f:view>
	<body>
	    <h:form styleClass="form" id="psvlPopupContract">
		<h:inputHidden value="#{contractCoverageBackingBean.psvlLookupRecords}" id="psvlLookupRecords"></h:inputHidden>
		<table width="100%">
			<tr>
				<td align="left" >&nbsp;<h:commandButton id="selectButton"
					value="Select" styleClass="wpdbutton"
					onclick="getSelectedValue();return false;"></h:commandButton>
				</td>
			</tr>
			<tr bgcolor="#cccccc">
				<TD align="center" width="100%" height="10px" colspan="2">
					<strong><h:outputText value="Eligibility Criteria"> </h:outputText> </strong> 
				</TD>
			</tr>
		</table>
		<div id="tierDiv" style="height:350px;overflow:auto;" class="popupDataTableDiv" >
		<table width="100%">
			<tr>
				<td>
					<h:dataTable width="100%" id="tierDataTable" value="#{contractCoverageBackingBean.pbvlDefList}" var="eachRow" 
						bgcolor="#cccccc" cellpadding="2" cellspacing="1"
        				rendered="#{contractCoverageBackingBean != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow"
        				columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
						 	<h:column>
							
								<f:facet name="header" >
		                          <h:outputText value="Select"/>
        		              </f:facet> 
								<t:selectOneRadio id="selectRadio" forceId="true" forceIdIndex="false" required="true" onclick="getSelectedPsvl(this);">
							        <f:selectItem itemValue="#{eachRow.benefitTierCtrPsblValue}" itemLabel=""/>
						      </t:selectOneRadio>
						 	</h:column>
							<h:column>
								
								<f:facet name="header" >
		                          <h:outputText value="Eligibility Code"/>
        			            </f:facet> 
								<h:outputText value="#{eachRow.benefitTierCtrPsblValue}"></h:outputText>
							</h:column>
							<h:column>
								
								<f:facet name="header" >
		                          <h:outputText value="Description"/>
        			            </f:facet> 
								<h:outputText value="#{eachRow.benefitTierCtrPsblDesc}"></h:outputText>
							</h:column>
					</h:dataTable>
				</td>
			</tr>
			<tr>
				<td><f:verbatim>&nbsp;</f:verbatim></td>
			</tr>
		</table>			
		</div>
</h:form>
</body>
</f:view>
<script language="javascript">
var selectedValue ='';
function getSelectedPsvl(objId){
selectedValue = ''+objId.value;
}

function getSelectedValue(){
window.returnValue = ''+selectedValue;
	<%
	String browser = request.getHeader("user-agent");
	if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
	%>
	window.close();
	<%
	}
	else {
	%>
	parent.document.getElementsByTagName('dialog')[0].close();
	<%
	}
	%>	
	if(selectedValue !='')
		return true;
	return false;
}
function stateMaintain(){
	var selectedValue = window.dialogArguments.selectedValues;
	if(selectedValue !=null && selectedValue !=''){
	var tabObj = document.getElementById("psvlPopupContract:tierDataTable").childNodes[1];
	var tbSize = document.getElementById('psvlPopupContract:tierDataTable').rows.length;
			for(var i=0;i<tbSize;i++){
				if(tabObj.getElementsByTagName('input')[i]!=null && (tabObj.getElementsByTagName('input')[i].value == selectedValue)){
					tabObj.getElementsByTagName('input')[i].click();
				}
			}
	}
}

stateMaintain();
</script>
</HTML>
