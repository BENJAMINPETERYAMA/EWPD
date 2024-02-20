
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<TITLE>Admin Method View</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>
</HEAD>

<f:view>
	<body onkeypress="return submitOnEnterKey('searchForm:basicSearch');">
	<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" height="100%">		
		<TR>
			<TD><h:form id="searchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD><w:message></w:message></TD>
								</TR>
							</TBODY>
						</TABLE>

						<DIV>
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" 
							id="TabTable"
							style="position:relative; top:25px; left:5px; z-index:120;">
							<TR>
								<TD width="200"></TD>
								<TD width="200"></TD>
							</TR>
						</TABLE>

						<!--Tab Ends-->
						<DIV id="accordionTest" style="margin:5px;">
						<DIV id="searchPanel" >
						<DIV id="searchPanelHeader" class="tabTitleBar"
							style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
						<DIV id="searchPanelContent" class="tabContentBox"
							style="position:relative;"><br />

						<TABLE width="500" cellpadding="2" border="0" class="outputText" >
							<TBODY>
								<TR>
									<TD width="27%"><h:outputText
										styleClass="#{adminMethodViewAllBackingBean.requiredType? 'mandatoryError': 'mandatoryNormal'}"
										id="BenefitTypeStar" value="Processing Method" /></TD>
									<TD width="29%">
										<TABLE cellspacing="0" cellpadding="0" border="0">
											<TR>
												<TD>
													<h:inputHidden id="benefitType_copyhidden"
														value="#{adminMethodViewAllBackingBean.processingMethodCriteria}"></h:inputHidden>
													<h:selectOneMenu id="CorpPlanCd_list1"
														styleClass="formInputField" tabindex="6" style="width:225px"
														value="#{adminMethodViewAllBackingBean.processingMethodCriteria}">
														<f:selectItems id="benefitTypeList"
															value="#{adminMethodMaintainBackingBean.processingMethodList}" />
													<%-- This code will direct the control to Standardbenefit BB from where it calls ReferencedataBB.
															 This modifiction was done for maintaining the value in benefittype dropdown to prevent the 
															 validation error. Please dont modify the code  --%>
													</h:selectOneMenu>
											   </TD>
											   <TD width="63%" align="right"></TD>
											</TR>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD width="24%">
										<h:outputText styleClass="#{adminMethodViewAllBackingBean.adminMethodNoReq? 'mandatoryError': 'mandatoryNormal'}"										
										id="TermStar" value="Admin Method Number" /></TD>
									<TD width="25%"><h:inputText id="amNoText"
											styleClass="formInputField" maxlength="5"																				
											value="#{adminMethodViewAllBackingBean.adminMethodNoCriteria}"
											tabindex="1" onkeypress="return isNum(event);"></h:inputText></TD>
									<td width="50%"><f:verbatim></f:verbatim></td>								
								</TR>
								<TR>
									<TD width="24%"><h:outputText										
										id="QualifierStar" value="Admin Method Description" /></TD>
									<TD width="25%"><h:inputText id="amNoText2"
											styleClass="formInputField"																				
											value="#{adminMethodViewAllBackingBean.adminMethodDescCriteria}"
											tabindex="1" onkeypress="return isSpecialChar(event);"></h:inputText></TD>
									<td width="50%"><f:verbatim></f:verbatim></td>
								</TR>
								<TR>
									<TD colspan="3">&nbsp;</TD>
								</TR>
								<TR>
									<TD align="left" valign="top" colspan="3"><h:commandButton
										styleClass="wpdbutton" id="basicSearch" value="Locate"
										style="cursor: hand"
										action="#{adminMethodViewAllBackingBean.searchAdminMethods}"
										tabindex="11"></h:commandButton></TD>
								</TR>

							</TBODY>
						</TABLE>


						</DIV>
						</DIV>
						<DIV id="panel2">
						<DIV id="panel2Header" class="tabTitleBar"
							style="position:relative; cursor:hand;">Locate Results</DIV>
						<DIV id="panel2Content" class="tabContentBox"
							style="position:relative;font-size:10px;width:100%;"><BR/>

							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD><!-- Search Result Data Table -->
										<DIV id="searchResultdataTableDiv" 
											style="height:370;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
											
											<h:dataTable
												headerClass="fixedDataTableHeader" id="searchResultTable"
												var="singleValue" cellpadding="3" cellspacing="1" width="100%"
												bgcolor="#cccccc"
												rendered="#{adminMethodViewAllBackingBean.searchAdminMethodList != null}"
												value="#{adminMethodViewAllBackingBean.searchAdminMethodList}"
												rowClasses="dataTableEvenRow,dataTableOddRow" >
												
												
												<h:column>
													<f:facet name="header">
														<f:verbatim>Admin Method Number</f:verbatim>
													</f:facet>
													<h:outputText id="adminMethodNo"
														value="#{singleValue.adminMethodNo}"></h:outputText>
												</h:column>
												<h:column>
	        	                                 	<f:facet name="header">
														<f:verbatim>Admin Method Description</f:verbatim>
													</f:facet>
													<h:outputText id="adminMethodDesc"
														value="#{singleValue.adminMethodDescription}"></h:outputText>
												</h:column>
												<h:column>
													<f:facet name="header">
														<f:verbatim>Term</f:verbatim>
													</f:facet>
													<h:outputText id="adminMethodTerm"
														value="#{singleValue.term}"></h:outputText>
												</h:column>
												<h:column>
													<f:facet name="header">
														<f:verbatim>Qualifier</f:verbatim>
													</f:facet>
													<h:outputText id="adminMethodQualifier"
														value="#{singleValue.qualifier}"></h:outputText>
												</h:column>
												<h:column>
													<f:facet name="header">
														<f:verbatim>PVA</f:verbatim>
													</f:facet>
													<h:outputText id="adminMethodPva"
														value="#{singleValue.pva}"></h:outputText>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<f:verbatim>Data Type</f:verbatim>
													</f:facet>
													<h:outputText id="adminMethodDataType"
														value="#{singleValue.dataType}"></h:outputText>
												</h:column>
												
												<h:column>	
													<f:facet name="header">
														<f:verbatim>Questions & Answers</f:verbatim>
													</f:facet>								
													<h:dataTable
														headerClass="dataTableHeader" id="searchResultTableAdmin"
														var="singleValueQuestionAnswer" cellpadding="3" cellspacing="1" width="100%"
														 border="0" 
														rendered="#{singleValue.questionAnswerList != null}"
														value="#{singleValue.questionAnswerList }"
														rowClasses="" >
														<h:column>
															<h:outputText id="adminMethodQuestionsDesc" 
																value="#{singleValueQuestionAnswer.questionDesc}">																						
															</h:outputText>	
															<f:verbatim>&nbsp;?&nbsp;&nbsp;</f:verbatim>																
															<h:outputText id="adminMethodQuestionsAnswer"
																value="#{singleValueQuestionAnswer.possibleAnswer}">																						
															</h:outputText>	
														</h:column>
													</h:dataTable>
												</h:column>
											</h:dataTable>
										</DIV>
									</TD>
								</TR>
							</TABLE>
						</DIV>
						</DIV>
					</DIV>
					</DIV>
						</TD>
					</TR>
				</TABLE>

			</h:form></TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>

	</body>
</f:view>
<script language="JavaScript">
	
		var newWinForView;


		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'430',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});

if(document.getElementById('searchForm:searchResultTable') != null) {

		setColumnWidth('searchForm:searchResultTable','8%:20%:12%:14%:10%:9%:27%');	
		showResultsTab();

		}

function isNum(evt){
 	var k = document.all ? evt.keyCode : evt.which;
	if ((k == 17 ||(k > 47 && k < 58) || k == 8 || k == 0 || k==13)) {
		return true;
	} else {	
		evt.keyCode = 0;
		return false;
  	}
	
}
  	
function isSpecialChar(evt){
  	var k = document.all ? evt.keyCode : evt.which;
	if(((k >= 48 && k <= 57) || ( k>= 97 && k<= 122) || ( k>= 65 && k<= 90) || k == 47 || k == 8 || k == 0 || 
    	k == 38 || k ==32 || k == 45 || k == 95 || k == 127 )){
		
		evt.returnValue=true;

	}else {
		evt.returnValue = false;
	}	
 }			
function checkIsNum(){

	var adminMethodVal = document.getElementById('searchForm:amNoText').value;
		if (!isNaN(adminMethodVal)){
			return true;		
		}
		else {
			document.getElementById('searchForm:amNoText').value="";
			return false;
		}
}
</script>
<script language="JavaScript">
	fillSpace();
</script>
</HTML>



