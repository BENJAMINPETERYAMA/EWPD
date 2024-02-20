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
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 90%;
}
.longDescriptionColumn {
	width: 100%;
	colspan: 2;
}
</style>

</HEAD>
<f:view>
                <BODY>
                <h:form id="benefitTermSelectPopupForm">
				<h:inputHidden id="search"
					value="#{adminMethodMappingBackingBean.searchQuestionAnswer}"></h:inputHidden>
				<h:outputText value="$#~"></h:outputText>
				
				<h:dataTable headerClass="dataTableHeader" id="searchResultTable1test"
						var="question" cellpadding="2" width="100%"
						cellspacing="1" bgcolor="#cccccc"
						rendered="#{adminMethodMappingBackingBean.quesAnswerDataLookUpList!=null}"
						value="#{adminMethodMappingBackingBean.quesAnswerDataLookUpList}"
						border="0" rowClasses="dataTableEvenRow,dataTableEvenRow,dataTableOddRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn">
						
						<h:column>
							
							<h:inputHidden id="qstnId" value="#{question.questionId}"></h:inputHidden>						
							<h:selectBooleanCheckbox id="componentChkBox" onclick="return showAnswer(this);" 
								value ="#{question.questionChecked}">
							</h:selectBooleanCheckbox>
						</h:column>
						
						<h:column>
							
								<h:outputText id="queDesc" value="#{question.questionDesc}" 
										style="padding-left: 5px"></h:outputText>
							<h:dataTable headerClass="dataTableHeader" id="answerResultTable"
								var="answer" cellpadding="0" width="100%" style="display:none;"
								cellspacing="0" value="#{question.possibleAnswerIdList}" border="0" >
									<h:column>
										<h:inputHidden value="#{question.questionId}"></h:inputHidden>	
										
										<h:selectBooleanCheckbox id="ansChkBox1" onclick="return selectAnswer(this);" styleClass="mandatoryError"
										rendered="#{answer.possibleAnswerDesc!=null && answer.possibleAnswerDesc !=''}" value ="#{answer.answerChecked}"></h:selectBooleanCheckbox>
										<h:outputText id="psvlAnsDesc" value="#{answer.possibleAnswerDesc}"></h:outputText>
										<h:inputHidden id="psvlAnsId" value="#{answer.possibleAnswerId}"></h:inputHidden>					
										<h:inputHidden id="psvlAnsDesc00" value="#{answer.possibleAnswerDesc}"></h:inputHidden>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										
										<h:selectBooleanCheckbox id="ansChkBox2" onclick="return selectAnswer(this);" 
										rendered="#{answer.possibleAnswerDesc2!=null && answer.possibleAnswerDesc2 !=''}" value ="#{answer.answerChecked1}"></h:selectBooleanCheckbox>
										<h:outputText id="psvlAnsDesc2" value="#{answer.possibleAnswerDesc2}"></h:outputText>
										<h:inputHidden id="psvlAnsId2" value="#{answer.possibleAnswerId2}"></h:inputHidden>	
										<h:inputHidden id="psvlAnsDesc02" value="#{answer.possibleAnswerDesc2}"></h:inputHidden>				
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										
										<h:selectBooleanCheckbox id="ansChkBox3" onclick="return selectAnswer(this);" 
										rendered="#{answer.possibleAnswerDesc3!=null && answer.possibleAnswerDesc3 !=''}" value ="#{answer.answerChecked2}"></h:selectBooleanCheckbox>
										<h:outputText id="psvlAnsDesc3" value="#{answer.possibleAnswerDesc3}"></h:outputText>
										<h:inputHidden id="psvlAnsId3" value="#{answer.possibleAnswerId3}"></h:inputHidden>	
										<h:inputHidden id="psvlAnsDesc03" value="#{answer.possibleAnswerDesc3}"></h:inputHidden>				
										
										
									</h:column>
							</h:dataTable>
							
						</h:column>
						
					</h:dataTable>
<h:inputHidden id="recordsGreaterThanMaxSize" value="#{adminMethodMappingBackingBean.recordsGreaterThanMaxSize}"></h:inputHidden> 
<h:outputText value="$$~"></h:outputText>
<h:outputText value="*#~"></h:outputText>
	<h:dataTable id="errorDataTable"  
			   value="#{requestScope.messages}" var="item"  cellpadding="7" 
			   cellspacing="1" bgcolor="#cccccc" 
	    	   rendered="#{requestScope.messages != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="longDescriptionColumn">
			   <h:column >
					
					<w:messageForPopup></w:messageForPopup>
			   </h:column>
	</h:dataTable>
<h:outputText value="**~"></h:outputText>
</BODY>
</h:form>
</f:view>


