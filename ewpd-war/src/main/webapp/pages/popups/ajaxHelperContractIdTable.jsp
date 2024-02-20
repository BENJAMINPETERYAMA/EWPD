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
	
}.shortDescriptionColumn {
	width: 92%;
	
}
.longDescriptionColumn2 {
	width: 100%;
	colspan: 2;
}
</style>
</HEAD>
<f:view>
	<BODY>
	<h:form id="baseContractCodeForm">
<h:outputText value="$#~"></h:outputText>
					<h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn" cellspacing="1"
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
					</h:dataTable>
<h:outputText value="$$~"></h:outputText>
<h:outputText value="*#~"></h:outputText>
<h:dataTable id="errorDataTable"  
				   value="#{requestScope.messages}" var="item"  cellpadding="7" 
				   cellspacing="1" bgcolor="#cccccc" 
					rendered="#{requestScope.messages != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="longDescriptionColumn2">
				        <h:column >
							
							<w:messageForPopup></w:messageForPopup>
						</h:column>
</h:dataTable>
<h:outputText value="**~"></h:outputText>
	</BODY>
</h:form>
</f:view>
