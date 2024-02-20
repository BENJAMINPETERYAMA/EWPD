<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	width: 5%;
	
}.shortDescriptionColumn {
	width: 18%;
}
.longDescriptionColumn {
	width: 67%;
}
.shortDescriptionColumn1 {
	width: 10%;
}
.longDescriptionColumn1 {
	
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
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,shortDescriptionColumn1,longDescriptionColumn1" cellspacing="1"
						width="100%" id="baseContractCodeTable"
						rendered="#{popupBackingBean.searchList != null}"
						value="#{popupBackingBean.searchList}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
								<h:selectBooleanCheckbox id="lineOfBusinessChkBox" onclick="isCheckedAll();">
								</h:selectBooleanCheckbox>

						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.generatedRuleId}"></h:outputText>
							<h:inputHidden id ="RuleDescHidden" value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden id ="ProductRuleIdHidden" value="#{eachRow.productRuleSysId}"></h:inputHidden>
							<h:inputHidden id ="RuleIdHidden" value="#{eachRow.ruleId}"></h:inputHidden>  
						</h:column>
						<h:column>
							
                              <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.description}"></h:outputText>
							
						</h:column>
						<h:column>
							
                              <f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.rulePVA}"></h:outputText>
							
						</h:column>
						<h:column>
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;">
							</h:commandButton>
							<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							
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

