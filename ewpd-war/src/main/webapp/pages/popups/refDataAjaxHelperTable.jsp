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
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 15%;
}
.longDescriptionColumn {
	width: 87%;
}.messageForPopup{
width:100%;
colspan:2;
}
</style>
</HEAD>
<f:view>
	<BODY>
	<h:form id="benefitTermSelectPopupForm">
<h:outputText value="$#~"></h:outputText>
<h:dataTable headerClass="dataTableHeader"
					id="searchResultTable1" var="providerArrangement" cellpadding="2"
					width="100%" cellspacing="1" bgcolor="#cccccc"
					rendered="#{ReferenceDataBackingBeanCommon.refDataLookUpList!=null}"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					border="0" rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
					<h:column>
					
						<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							id="parCheckBox" value="#{providerArrangement}" onclick="isCheckedAll();">
						</h:selectBooleanCheckbox>
					</h:column>
					<h:column>
					
						<h:inputHidden id="hiddenTermValue"
							value="#{providerArrangement.description}" />
						<h:inputHidden id="hiddenTermValueDesc"
							value="#{providerArrangement.primaryCode}" />
						<h:outputText id="termValueId"
							value="#{providerArrangement.primaryCode}"
							style="padding-left: 5px"></h:outputText>
					</h:column>
					<h:column>
				
						<h:outputText id="termValue"
							value="#{providerArrangement.description}"
							style="padding-left: 5px"></h:outputText>
					</h:column>
				</h:dataTable>
<h:inputHidden id="recordsGreaterThanMaxSize" value="#{ReferenceDataBackingBeanCommon.recordsGreaterThanMaxSize}"></h:inputHidden> 
<h:outputText value="$$~"></h:outputText>
<h:outputText value="*#~"></h:outputText>
<h:dataTable id="errorDataTable"  
				   value="#{requestScope.messages}" var="item"  cellpadding="7" 
				   cellspacing="1" bgcolor="#cccccc" 
					rendered="#{requestScope.messages != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="messageForPopup">
				        <h:column >
							
							<w:messageForPopup></w:messageForPopup>
						</h:column>
</h:dataTable>
<h:outputText value="**~"></h:outputText>
	</BODY>
</h:form>
</f:view>
