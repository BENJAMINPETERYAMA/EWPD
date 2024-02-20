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
	width: 8%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 92%;
	vertical-align: middle;
	style: text-indent: 2px
}

.longDescriptionColumn2 {
	width: 100%;
	colspan: 2;
}
</style>
</HEAD>
<f:view>
	<BODY>
	<h:form id="expiredContractIdForm">
<h:outputText value="$#~"></h:outputText>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1" width="100%" id="expiredContractIdTable"
						rendered="#{contractExpiredIdsBackingBean.expiredContractIds != null}"
						value="#{contractExpiredIdsBackingBean.expiredContractIds}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc" >
						<h:column>
							
								<h:selectBooleanCheckbox id="expiredContractIdChkBox" disabled= "#{eachRow.reservePoolStatus == 'U' || eachRow.reservePoolStatus == 'C'  || eachRow.privilege == 'No'}" >
								</h:selectBooleanCheckbox>
							
							
						</h:column>
						<h:column>
							
							<h:inputHidden value="#{eachRow.contractId}"></h:inputHidden>							
							<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.contractId}"></h:outputText>
							

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
