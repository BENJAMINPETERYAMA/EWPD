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
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 20%;
	vertical-align: middle;
}
.longDescriptionColumn {
	width: 60%;
	vertical-align: middle;
}
.longDescriptionColumn2 {
	width: 100%;
	colspan: 2;
}
</style>
</HEAD>
<f:view>
	<BODY>
	<h:form id="notesLookUpForm">
<h:outputText value="$#~"></h:outputText>
					<h:dataTable id="notesDataTable"
										rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn,selectOrOptionColumn" cellspacing="1" width="100%" 
										value="#{contractProductAdminOptionOverrideBackingBean.questionNotes}"
										var="singleValue" rendered="#{contractProductAdminOptionOverrideBackingBean.questionNotes!=null}" cellpadding="0" bgcolor="#cccccc">
										<h:column>
										<f:verbatim>
										<wpd:singleRowSelect groupName="majorHeading" 
											id="stateCodeRadioButton"  rendered="true"></wpd:singleRowSelect>
										</f:verbatim>
										</h:column>
										<h:column>
												
												<h:inputHidden id = "hiddenNoteName" value="#{singleValue.noteName}"></h:inputHidden>
												<h:inputHidden id="statusHidden" value="#{singleValue.overrideStatus}"></h:inputHidden>	                            
												<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
												<h:inputHidden id="hiddenVersion" value="#{singleValue.version}"></h:inputHidden>
												<h:inputHidden id="isdelete" value="#{singleValue.isdelete}"></h:inputHidden>
												<h:inputHidden id="isInsert" value="#{singleValue.isInsert}"></h:inputHidden>
												<h:inputHidden id="isUpdate" value="#{singleValue.isUpdate}"></h:inputHidden>
												<f:verbatim>&nbsp;</f:verbatim>
												<h:outputText value="#{singleValue.noteId}"></h:outputText>
										</h:column>
										<h:column>
												
												<f:verbatim>&nbsp;</f:verbatim>
												<h:outputText value="#{singleValue.noteName}"></h:outputText>
										</h:column>
										<h:column>
												
												<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
												<h:commandButton alt="View" id="viewButton"
																	image="../../images/view.gif" onclick="viewAction();return false;">
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
