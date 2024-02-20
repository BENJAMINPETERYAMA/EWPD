<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectsystemDomainIdColumn {
	width: 51%;
}
.selectsystemDomainIdColumn1 {
	style: text-indent: 2px;
}
</style>

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function noenter(){
  	 return !(window.event && window.event.keyCode == 13); 
	 }
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
</script>
<TITLE>Note Domains</TITLE>

</HEAD>
<f:view>
	<BODY><hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>

		<TR>
			<TD><h:form styleClass="form" id="notesDomainForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						
						<TABLE>
									<TBODY>
								<TR>
									<TD><TABLE><TR><TD><w:message></w:message></TD></TR></TABLE></TD>
								</TR>
								</TBODY>
								</TABLE>

						<!-- Table containing Tabs -->
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">								
								
							<TR>
								<TD width="200">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD width="100%" class="tabNormal" onclick="confirmNavigation();">
																<h:outputText style="cursor:hand;" id="generalInfoTable" value="General Information" /> 
										<h:inputHidden id="noteID" value="#{notesBackingBean.noteId}"></h:inputHidden>
										<h:inputHidden id="noteName" value="#{notesBackingBean.name}"></h:inputHidden>
										</TD>
										<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="25%">
		          							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
													<TD width="100%" class="tabActive"><h:commandLink action="#{notesBackingBean.loadNoteDomains}">
																<h:outputText id="dataDomainTable" value="Data Domain" /> </h:commandLink></TD> 
										
				                					<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="4" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="100%">
							<TBODY>
								<TR height="4%"><TD width="461"></TD></TR>
								<TR>
									<TD width="100%">
										<FIELDSET style="width:470">
													<LEGEND><FONT color="black">Limited To</FONT></LEGEND>
										<TABLE width="470">              
											<TR>
												<TD width="135"><h:outputText styleClass="mandatoryNormal"
													id="LobStar" value="Line of Business " /></TD>
												<TD width="295">

												<DIV id="lobDiv" class="selectDataDisplayDivForNotes"></DIV>
												</TD>


												<TD width="36"><h:inputHidden id="txtLob"
													value="#{notesBackingBean.lob}"></h:inputHidden><h:commandButton
													alt="Select" id="lobButton" image="../../images/select.gif"
													style="cursor: hand" onclick="lobSelected();return false;"
													tabindex="1"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="135"><h:outputText styleClass="mandatoryNormal"
													id="BusinessEntityStar" value="Business Entity " /></TD>
												<TD width="295">

												<DIV id="BusinessEntityDiv"
													class="selectDataDisplayDivForNotes"></DIV>


												</TD>
												<TD width="36"><h:commandButton alt="Select"
													id="BusinessEntityButton" image="../../images/select.gif"
													style="cursor: hand"
													onclick="busEntitySelected(); return false;" tabindex="2"></h:commandButton><h:inputHidden
													id="txtBusinessEntity"
													value="#{notesBackingBean.businessEntity}"></h:inputHidden></TD>
											</TR>
											<TR>

												<TD width="135"><h:outputText styleClass="mandatoryNormal"
													id="BusinessGroupStar" value="Business Group " /></TD>
												<TD width="295">

												<DIV id="BusinessGroupDiv"
													class="selectDataDisplayDivForNotes"></DIV>


												</TD>
												<TD width="36"><h:commandButton alt="Select"
													id="BusinessGroupButton" image="../../images/select.gif"
													style="cursor: hand"
													onclick="busGroupSelected(); return false;" tabindex="3"></h:commandButton><h:inputHidden
													id="txtBusinessGroup"
													value="#{notesBackingBean.businessGroup}"></h:inputHidden></TD>
											</TR>
<!-- --------------------------------------------------------------------------- -->
											<TR>

												<TD width="135"><h:outputText styleClass="mandatoryNormal"
													id="MarketBusinessUnitStar" value="Market Business Unit " /></TD>
												<TD width="295">

												<DIV id="MarketBusinessUnitDiv"
													class="selectDataDisplayDivForNotes"></DIV>


												</TD>
												<TD width="36"><h:commandButton alt="Select"
													id="MarketBusinessUnitButton" image="../../images/select.gif"
													style="cursor: hand"
													onclick="marketBusniessUnitSelected(); return false;" tabindex="3"></h:commandButton><h:inputHidden
													id="txtMarketBusinessUnit"
													value="#{notesBackingBean.marketBusinessUnit}"></h:inputHidden></TD>
											</TR>
<!-- --------------------------------------------------------------------------- -->
										</TABLE>
										</FIELDSET>
									</TD>

								</TR>
								<TR>
								<TD >
										<FIELDSET
											style="bordercolor: white; width: 471; border-width: 0px">
										<TABLE width="471">
											<TR>
												<TD width="325"><h:outputText id="productLabel"
													value="Product" styleClass="mandatoryNormal" /></TD>
												<TD width="22"><h:inputText id="productTxtArea"
													tabindex="4" value="#{notesBackingBean.productTxt}"
													styleClass="selectDataDisplayDivForNotes" onkeypress="return selectProd();"></h:inputText> </TD>
												<TD width="118"><h:commandButton id="productButton"
													alt="Select" image="../../images/autoComplete.gif"
													style="cursor: hand" onclick="prodSelected();return false;"
													tabindex="5" /><h:inputHidden id="productTxtHidden"
													value="#{notesBackingBean.product}"></h:inputHidden></TD>
											</TR>
											<TR>
												<TD width="325"><h:outputText id="componentLabel"
													value="Benefit Component" styleClass="mandatoryNormal" /></TD>
												<TD width="22"><h:inputText id="componentTxtArea" tabindex="6"
													value="#{notesBackingBean.benefitComponentTxt}"
													styleClass="selectDataDisplayDivForNotes" onkeypress=" return selectBenComp();"></h:inputText> </TD>
												<TD width="118"><h:commandButton id="componentButton" alt="Select"
													image="../../images/autoComplete.gif" style="cursor: hand"
													onclick="benCompSelected();return false;" tabindex="7" /><h:inputHidden
													id="componentTxtHidden"
													value="#{notesBackingBean.benefitComponent}"></h:inputHidden></TD>

											</TR>
											<TR>
												<TD width="325"><h:outputText id="benefitLabel" value="Benefit"
													styleClass="mandatoryNormal" /></TD>
												<TD width="22"><h:inputText id="benefitTxtArea"
													value="#{notesBackingBean.benefitTxt}" tabindex="8"
													styleClass="selectDataDisplayDivForNotes" onkeypress="return selectBenefit();"></h:inputText> </TD>
												<TD width="118"><h:commandButton id="benefitButton" alt="Select"
													image="../../images/autoComplete.gif" style="cursor: hand"
													onclick="benSelected();return false;" tabindex="9" /><h:inputHidden
													id="benefitTxtHidden" value="#{notesBackingBean.benefit}"></h:inputHidden></TD>
											</TR>
											<TR> 
												<TD width="325"><h:outputText id="questionLabel" value="Questions"
													styleClass="mandatoryNormal" /></TD>
												<TD width="22"><h:inputText id="questionTxtArea"
													value="#{notesBackingBean.questionTxt}" tabindex="8"
													styleClass="selectDataDisplayDivForNotes" onkeypress="return selectQuestions();"></h:inputText> </TD>
												<TD width="118"><h:commandButton id="questionButton" alt="Select"
													image="../../images/autoComplete.gif" style="cursor: hand"
													onclick="questionSelected();return false;" tabindex="9" /><h:inputHidden
													id="questionTxtHidden" value="#{notesBackingBean.question}"></h:inputHidden></TD>
											</TR>
										</TABLE>
										</FIELDSET>
										</TD></TR><TR>
									<TD >
										<FIELDSET style="width:470">
													<LEGEND><FONT color="black">Benefit Level</FONT></LEGEND>
										<TABLE width="470">

											<TR>
												<TD width="134"><h:outputText id="termLabel" value="Term"
													styleClass="mandatoryNormal" /></TD>
												<TD width="294">
												<DIV id="termDiv" class="selectDataDisplayDivForNotes"></DIV>
												<h:inputHidden id="termTxtHidden"
													value="#{notesBackingBean.term}"></h:inputHidden></TD>
												<TD width="38"><h:commandButton id="termButton" alt="Select"
													image="../../images/select.gif" style="cursor: hand"
													onclick="termSelected(); return false;" tabindex="10" /></TD>
											</TR>
											<TR height="4%">
												<TD colspan="3"></TD>
											</TR>
											<TR>
												<TD width="134"><h:outputText id="qualifierLabel"
													value="Qualifier" styleClass="mandatoryNormal" /></TD>
												<TD width="294">
												<DIV id="qualifierDiv" class="selectDataDisplayDivForNotes"></DIV>
												<h:inputHidden id="qualifierTxtHidden"
													value="#{notesBackingBean.qualifier}"></h:inputHidden></TD>
												<TD width="38"><h:commandButton id="qualifierButton"
													alt="Select" image="../../images/select.gif"
													style="cursor: hand"
													onclick="qualifierSelected(); return false;" tabindex="11" /></TD>
											</TR>
										</TABLE>
										</FIELDSET>
									</TD>
								</TR>
									
									<TR >
																											
										<TD>	
											<div id="DeleteDiv" style="background-color: #cccccc; height: 16px;" >											
											<SPAN style="width: 344px;"><STRONG>Domain Information</STRONG></SPAN> 
											<SPAN ><B><h:outputText value="Delete"></h:outputText></B></SPAN>	
											</div>	
										</TD>
																												
									</TR>
																
								<TR>
									<TD colspan="10">
									<DIV id="ProductHeaderDiv">	
									<!-- Product table -->

										<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
											<TR bgcolor="#ffffff">
												<TD colspan="6"><SPAN id="stateCodeStar" ><B><h:outputText value="Domained Product"></h:outputText></B></SPAN>
													
												</TD>	
											</TR>

												
											<TR>
												<TD>
												<DIV id="searchResultdataTableDivProduct" style="height:30px;width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectsystemDomainIdColumn,selectsystemDomainIdColumn1" cellspacing="1" width="100%" id="productTable" rendered="#{notesBackingBean.productList!= null}" value="#{notesBackingBean.productList}" var="eachRow" cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden id="domainId" value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:inputHidden id="type" value="#{eachRow.entityType}"></h:inputHidden>
														<h:inputHidden id="noteid" value="#{eachRow.noteId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
													<h:column>
														<h:selectBooleanCheckbox id="basicDelete"
															value="Delete" onclick = 'javascript:validateDelete(1)'
															rendered="#{eachRow.checkBoxVal}">
														</h:selectBooleanCheckbox>
														
												</h:column>
												</h:dataTable>
												</DIV>
												</TD>
											</TR>
										</TABLE>
									</DIV>
									</TD>
									</TR>
									
									<TR><TD width="461"></TD></TR>
							<!--  BenefitComponent Table -->
									
									<TR>
										<TD colspan="10">
										<DIV id="CompHeaderDiv">
										<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
											<TR bgcolor="#ffffff">
												<TD><span ><STRONG>Domained Benefit Component</STRONG></span>													
												</TD>
											</TR>

												
											<TR>
												<TD>
												
												<DIV id="searchResultdataTableDivComp" style="height:30px;width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectsystemDomainIdColumn,selectsystemDomainIdColumn1" cellspacing="1" width="100%" id="benfcompTable" rendered="#{notesBackingBean.componentList!= null}" value="#{notesBackingBean.componentList}" var="eachRow" cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden id="domainIdForComp" value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:inputHidden id="typeForComp" value="#{eachRow.entityType}"></h:inputHidden>
														<h:inputHidden id="noteidForComp" value="#{eachRow.noteId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
													<h:column>
														<h:selectBooleanCheckbox id="bnftcmpDelete"
															value="Delete" onclick = 'javascript:validateDelete(2)'
															rendered="#{eachRow.checkBoxVal}"></h:selectBooleanCheckbox>
														
												</h:column>
												</h:dataTable>
												</DIV>
												</TD>
											</TR>
										</TABLE>
										</DIV>
										</TD>										
										</TR>
										
									<TR><TD width="461"></TD></TR>
									<!--  Benefit Table -->
										
										<TR>
										<TD colspan="10">
										<DIV id="BenefitHeaderDiv">
										<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
											<TR bgcolor="#ffffff">
												<TD colspan="6"><SPAN id="stateCodeStar" ><STRONG>Domained Benefit</STRONG></SPAN>
												</TD>	
											</TR>

												
											<TR>
												<TD>
												<DIV id="searchResultdataTableDivBenefit" style="height:30px;width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectsystemDomainIdColumn,selectsystemDomainIdColumn1" cellspacing="1" width="100%" id="benfTable" rendered="#{notesBackingBean.benefitList!= null}" value="#{notesBackingBean.benefitList}" var="eachRow" cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden id="domainIdForBenefit" value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:inputHidden id="typeForBenefit" value="#{eachRow.entityType}"></h:inputHidden>
														<h:inputHidden id="noteidForBenefit" value="#{eachRow.noteId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
													<h:column>
														<h:selectBooleanCheckbox id="bnftDelete"
															value="Delete" onclick = 'javascript:validateDelete(3)'
															rendered="#{eachRow.checkBoxVal}"></h:selectBooleanCheckbox>
														
												</h:column>
												</h:dataTable>
												</DIV>
												</TD>
											</TR>
										</TABLE>
										</DIV>
										</TD>		
										</TR>


								<TR><TD width="461"></TD></TR>
									<!--  Question Table -->
										
										<TR>
										<TD colspan="10">
										<DIV id="QuestionHeaderDiv">
										<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
											<TR bgcolor="#ffffff">
												<TD colspan="6"><SPAN id="stateCodeStar" ><STRONG>Domained Question</STRONG></SPAN>
												</TD>
											</TR>

												
											<TR>
												<TD>
												<DIV id="searchResultdataTableDivQuestion" style="height:30px;width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectsystemDomainIdColumn,selectsystemDomainIdColumn1" cellspacing="1" width="100%" id="questTable" rendered="#{notesBackingBean.questionList!= null}" value="#{notesBackingBean.questionList}" var="eachRow" cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden id="domainIdForQuestion" value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:inputHidden id="typeForQuestion" value="#{eachRow.entityType}"></h:inputHidden>
														<h:inputHidden id="noteidForQuestion" value="#{eachRow.noteId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
													<h:column>
														<h:selectBooleanCheckbox id="questDelete"
															value="Delete" onclick = 'javascript:validateDelete(6)'
															rendered="#{eachRow.checkBoxVal}"></h:selectBooleanCheckbox>
														 
												</h:column>
												</h:dataTable>
												</DIV>
												</TD>
											</TR>
										</TABLE>
										</DIV>
										</TD>		
										</TR>

										
									<TR><TD width="461"></TD></TR>
										<!--  Term Table -->
										<TR>
										<TD colspan="10">
									<DIV id="TermHeaderDiv">									

										<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
											<TR bgcolor="#ffffff">
												<TD colspan="6"><SPAN id="stateCodeStar" ><STRONG>Domained Term</STRONG></SPAN>
												</TD>
											</TR>

												
											<TR>
												<TD>
												<DIV id="searchResultdataTableTermDiv" style="height:30px;width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectsystemDomainIdColumn,selectsystemDomainIdColumn1" cellspacing="1" width="100%" id="termTable" rendered="#{notesBackingBean.termList != null}" value="#{notesBackingBean.termList}" var="eachRow" cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden id="domainIdForTerm" value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:inputHidden id="typeForTerm" value="#{eachRow.entityType}"></h:inputHidden>
														<h:inputHidden id="noteidForTerm" value="#{eachRow.noteId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainId}"></h:outputText>
													</h:column>
													<h:column>
														<h:selectBooleanCheckbox id="termDelete"
															value="Delete" onclick = 'javascript:validateDelete(4)'
															rendered="#{eachRow.checkBoxVal}"></h:selectBooleanCheckbox>
														
												</h:column>
												</h:dataTable>
												<h:inputHidden id="termListSize" value="#{notesBackingBean.termListSize}"></h:inputHidden>
												</DIV>
												</TD>
											</TR>
										</TABLE>
									</DIV>
										</TD>
										</TR>
									<TR><TD width="461"></TD></TR>
						<!--  Qualifier Table -->
										<TR>
										<TD colspan="10">
										<DIV id="QualifierHeaderDiv">
										<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
											<TR bgcolor="#ffffff">
												<TD colspan="6"><SPAN id="stateCodeStar" ><STRONG>Domained Qualifier</STRONG></SPAN>
												</TD>
											</TR>

												
											<TR>
												<TD>
												<DIV id="searchResultdataTableQualifierDiv" style="height:30px;width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectsystemDomainIdColumn,selectsystemDomainIdColumn1" cellspacing="1" width="100%" id="qualifierTable" rendered="#{notesBackingBean.qualifierList != null}" value="#{notesBackingBean.qualifierList}" var="eachRow" cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden id="domainIdForQualifier" value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:inputHidden id="typeForQualifier" value="#{eachRow.entityType}"></h:inputHidden>
														<h:inputHidden id="noteidForQualifier" value="#{eachRow.noteId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainId}"></h:outputText>
														
													</h:column>
													<h:column>
														<h:selectBooleanCheckbox id="qualifierDelete"
															value="Delete" onclick = 'javascript:validateDelete(5)'
															rendered="#{eachRow.checkBoxVal}"></h:selectBooleanCheckbox>
														
												</h:column>
												</h:dataTable>
												<h:inputHidden id="qualifierListSize" value="#{notesBackingBean.qualifierListSize}"></h:inputHidden>
												</DIV>
												</TD>
											</TR>
										</TABLE>
										</DIV>
									</TD>
								</TR>

								<TR>	
									<TD height="36" width="461"><h:commandButton value="Save"
											styleClass="wpdButton" onclick="setVariableForAdd();" onmousedown="javascript:savePageActionForDataTable(this.id, 'notesDomainForm');"
											action="#{notesBackingBean.createDataDomains}" tabindex="12">
										</h:commandButton>&nbsp;&nbsp;
										<h:commandButton id="delete" value="Delete" tabindex="13" onclick="confirmDeletion();return false;"
											styleClass="wpdButton" > 
										</h:commandButton>
									</TD>

									
								</TR>


							</TBODY>
						</TABLE>
						<!--	End of Page data	--></FIELDSET>
						<BR>
							<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
								<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
									<TR>
										<TD width="15">
										<h:selectBooleanCheckbox id="checkIn" value="#{notesBackingBean.checkInFlag}"  tabindex="14"></h:selectBooleanCheckbox>
										</TD>
									<TD align="left"><h:outputText value="Check In"  /></TD>
									<TD align="left" width="127">
										<TABLE width="100%">
											<TR>
												<TD width="50%"><h:outputText id="outTxtState" value="State" /></TD>
												<TD>:&nbsp;<h:outputText id="state" value="#{notesBackingBean.state}" /></TD>
												<h:inputHidden id="stateHidden" value="#{notesBackingBean.state}" />
											</TR>
											<TR>
												<TD><h:outputText value="Status" /></TD> 
												<TD>:&nbsp;<h:outputText value="#{notesBackingBean.status}" /></TD>
													<h:inputHidden id="statusHidden" value="#{notesBackingBean.status}" />
											</TR>
											<TR>
												<TD><h:outputText value="Version" /></TD>
												<TD>:&nbsp;<h:outputText value="#{notesBackingBean.version}" /></TD>
													<h:inputHidden id="versionHidden" value="#{notesBackingBean.version}" />
											</TR>
										</TABLE>
									</TD>
									</TR>									
								</TABLE>
							</FIELDSET>
							<BR>
						&nbsp;&nbsp;<h:commandButton value="Done" styleClass="wpdButton" onclick="checkin();return false;"  tabindex="15"> </h:commandButton>
						</TD>
					</TR>
				</TABLE>

<h:inputHidden id="domainIdForDelete" value="#{notesBackingBean.domainId}" />
<h:inputHidden id="flagForDomains" value="#{notesBackingBean.flagForDomains}" />
<h:inputHidden id="flagForDomainPage" value="#{notesBackingBean.flagForDomainPage}" />
<h:inputHidden id="domainTypeForDelete" value="#{notesBackingBean.domainType}" />
<h:inputHidden id="noteIdForDelete" value="#{notesBackingBean.noteId}" />
<h:inputHidden id="flagForNavigation" value="#{notesBackingBean.flagForNavigation}" />
<h:inputHidden id="validationForTerm" value="#{notesBackingBean.validationForTerm}" />
<h:inputHidden id="targetHiddenFieldForDelete" value="#{notesBackingBean.targetHiddenFieldForDelete}" />
<h:inputHidden id="notesInActivelyUsedStatus" value="#{notesBackingBean.notesInActivelyUsedStatus}"/>
				<h:inputHidden id="hiddenValueChanged"
					value="#{notesBackingBean.valueChanged}"></h:inputHidden>
<h:inputHidden id="identifyTab" value="#{notesBackingBean.identifyTab}"/>
<h:commandLink id="deleteDataDomain" style="display:none; visibility: hidden;" action="#{notesBackingBean.deleteDataDomain}"> <f:verbatim /></h:commandLink>
<h:commandLink id="saveToCache" style="display:none; visibility: hidden;" action="#{notesBackingBean.saveToDatatable}"> <f:verbatim /></h:commandLink>
<h:commandLink id="genInfo" style="display:none; visibility: hidden;" action="#{notesBackingBean.retrieveNotes}"> <f:verbatim /></h:commandLink>
<h:commandLink id="checkinFlow" style="display:none; visibility: hidden;" action="#{notesBackingBean.checkIn}"> <f:verbatim /></h:commandLink>
			</h:form></TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>
<FORM name="printForm">
	<INPUT id="currentPrintPage" type="hidden" name="currentPrintPage" value="notesDataDomainEdit">
	</FORM></hx:scriptCollector></BODY>
</f:view>
<script language="JavaScript">
	document.getElementById('notesDomainForm:identifyTab').value = "DATA DOMAIN";
	//copyHiddenToDiv('notesDomainForm:termTxtHidden','termDiv');	
	//copyHiddenToDiv_ewpd('notesDomainForm:qualifierTxtHidden','qualifierDiv',2,2);	
	copyHiddenToDiv_ewpd('notesDomainForm:txtLob','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('notesDomainForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('notesDomainForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
	copyHiddenToDiv_ewpd('notesDomainForm:txtMarketBusinessUnit','MarketBusinessUnitDiv',2,2); 

	//Hide table if no value is present
	hideIfNoValueDeleteDiv('DeleteDiv','productTable', 'benfTable', 'questTable', 'benfcompTable', 'termTable', 'qualifierTable');	
	hideIfNoValue('ProductHeaderDiv','productTable');
	hideIfNoValue('BenefitHeaderDiv','benfTable');
	hideIfNoValue('QuestionHeaderDiv','questTable');
	hideIfNoValue('CompHeaderDiv', 'benfcompTable');
	hideIfNoValue('TermHeaderDiv','termTable');
	hideIfNoValue('QualifierHeaderDiv', 'qualifierTable');

adjustHieght('searchResultdataTableDivProduct','productTable');
adjustHieght('searchResultdataTableDivBenefit','benfTable');
adjustHieght('searchResultdataTableDivQuestion','questTable');
adjustHieght('searchResultdataTableDivComp','benfcompTable');
adjustHieght('searchResultdataTableTermDiv','termTable');
adjustHieght('searchResultdataTableQualifierDiv','qualifierTable');

function checkin(){
		document.getElementById('notesDomainForm:flagForDomainPage').value = 'true';	
		submitLink('notesDomainForm:checkinFlow');	
}

	function hideIfNoValueDeleteDiv(divId, tableName1, tableName2, tableName3, tableName4, tableName5, tableName6){
		hiddenIdObj1 = document.getElementById('notesDomainForm:'+tableName1);
		hiddenIdObj2 = document.getElementById('notesDomainForm:'+tableName2);
		hiddenIdObj3 = document.getElementById('notesDomainForm:'+tableName3);
		hiddenIdObj4 = document.getElementById('notesDomainForm:'+tableName4);
		hiddenIdObj5 = document.getElementById('notesDomainForm:'+tableName5);
		hiddenIdObj6 = document.getElementById('notesDomainForm:'+tableName6);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj1 == null && hiddenIdObj2 == null && hiddenIdObj3 == null && hiddenIdObj4 == null 
			&& hiddenIdObj5 == null && hiddenIdObj6 == null){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}
		else if((hiddenIdObj1!=null && !hiddenIdObj1.rows.length > 0) || ((hiddenIdObj2!=null && !hiddenIdObj2.rows.length > 0)
				||(hiddenIdObj3!=null && !hiddenIdObj3.rows.length > 0) || (hiddenIdObj4!=null && !hiddenIdObj4.rows.length > 0) 
				||(hiddenIdObj5!=null && !hiddenIdObj5.rows.length > 0) || (hiddenIdObj6!=null && !hiddenIdObj1.rows.length > 0))){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}
		else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}
	
	function hideIfNoValue(divId, tableName){
		hiddenIdObj = document.getElementById('notesDomainForm:'+tableName);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}
		else if(hiddenIdObj!=null && !hiddenIdObj.rows.length > 0){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}
		else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}

function adjustHieght(divId, tableName){
	var hgt = 17;
	hiddenIdObj = document.getElementById('notesDomainForm:'+tableName);
	if(hiddenIdObj != null){
		if(hiddenIdObj.rows.length > 12){
			document.getElementById(divId).style.height="195px";
		} else{
			hgt = hgt * hiddenIdObj.rows.length;
		document.getElementById(divId).style.height = hgt + "px";
		}
	}	
}

function setVariableForAdd(){

	//alert(document.getElementById('notesDomainForm:selectedFromPopup').value);
}


function lobSelected(){	
	var actionOne = '1';
	var lobNote = 'Line of Business';
	ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction=' + actionOne + '&parentCatalog='+lobNote, 'lobDiv', 'notesDomainForm:txtLob',2,2);
	
}
function busEntitySelected(){
	var actionOne = '1';		
	var beNote = 'Business Entity';
	ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction=' + actionOne + '&parentCatalog='+beNote,'BusinessEntityDiv','notesDomainForm:txtBusinessEntity',2,2);
}
function busGroupSelected(){	
	var actionOne = '1';
	var bgNote = 'business group';	
	ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction=' + actionOne + '&parentCatalog='+bgNote,'BusinessGroupDiv','notesDomainForm:txtBusinessGroup',2,2);
}
function marketBusniessUnitSelected(){	
	var actionOne = '1';
	var bgNote = 'Market Business Unit';	
	ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction=' + actionOne + '&parentCatalog='+bgNote,'MarketBusinessUnitDiv','notesDomainForm:txtMarketBusinessUnit',2,2);
}

function checkForBusinessDomains(){
	if('' == document.getElementById('notesDomainForm:txtLob').value && 
				'' == document.getElementById('notesDomainForm:txtBusinessEntity').value && 
					'' == document.getElementById('notesDomainForm:txtBusinessGroup').value &&
						'' == document.getElementById('notesDomainForm:txtMarketBusinessUnit').value){
		return true;
	}
	if('' == document.getElementById('notesDomainForm:txtLob').value || 
				'' == document.getElementById('notesDomainForm:txtBusinessEntity').value || 
					'' == document.getElementById('notesDomainForm:txtBusinessGroup').value ||
						'' == document.getElementById('notesDomainForm:txtMarketBusinessUnit').value ){
					alert("Please select the remaining business domains if you have selected any one of them");
					return false;
	}
}

function saveToCacheFunction(retvalue){
	
	document.getElementById('notesDomainForm:productTxtArea').value = '';
	document.getElementById('notesDomainForm:componentTxtArea').value = '';
	document.getElementById('notesDomainForm:benefitTxtArea').value = '';
	document.getElementById('notesDomainForm:questionTxtArea').value = '';
	document.getElementById('termDiv').innerHTML = '';
	document.getElementById('qualifierDiv').innerHTML = '';

	submitLink('notesDomainForm:saveToCache');		
}

function prodSelected(){
	var retValue = checkForBusinessDomains();
	if(retValue == false){
		return false;
	}
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
var retvalue = ewpdModalWindow_ewpd('../popups/productPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'notesProductValue='+document.getElementById('notesDomainForm:productTxtArea').value + '&&' + 'lobValue='+escape(document.getElementById('notesDomainForm:txtLob').value) + '&&' +  'beValue=' +escape(document.getElementById('notesDomainForm:txtBusinessEntity').value) + '&&' +  'bgValue=' +escape(document.getElementById('notesDomainForm:txtBusinessGroup').value)+ '&&' +'bUValue=' +escape(document.getElementById('notesDomainForm:txtMarketBusinessUnit').value)+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value, 'notesDomainForm:productTxtArea','notesDomainForm:productTxtHidden',2,1);	
if(retvalue != false){
		copyToHidden(1,'notesDomainForm:flagForDomains');
		saveToCacheFunction(retvalue);
	}
<%
}
else {
%>
var retvalue = ewpdModalWindow_ewpd_noteDomain('../popups/productPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'notesProductValue='+document.getElementById('notesDomainForm:productTxtArea').value + '&&' + 'lobValue='+escape(document.getElementById('notesDomainForm:txtLob').value) + '&&' +  'beValue=' +escape(document.getElementById('notesDomainForm:txtBusinessEntity').value) + '&&' +  'bgValue=' +escape(document.getElementById('notesDomainForm:txtBusinessGroup').value)+ '&&' +'bUValue=' +escape(document.getElementById('notesDomainForm:txtMarketBusinessUnit').value)+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value, 'notesDomainForm:productTxtArea','notesDomainForm:productTxtHidden',2,1);	
if(retvalue != false){
		copyToHidden(1,'notesDomainForm:flagForDomains');
	}
<%
}
%>	
}

function copyToHidden(sourceField,hiddenField){
	var sourceObj = sourceField;
	var targetObj = document.getElementById(hiddenField);
	targetObj.value = sourceObj;
}

function benCompSelected(){		
	var retValue = checkForBusinessDomains();
	if(retValue == false){
		return false;
	}
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
var retvalue = ewpdModalWindow_ewpd('../popups/benefitComponentPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&'+'notesBenefitComponentValue='+document.getElementById('notesDomainForm:componentTxtArea').value + '&&' + 'lobValue='+escape(document.getElementById('notesDomainForm:txtLob').value) + '&&' +  'beValue=' +escape(document.getElementById('notesDomainForm:txtBusinessEntity').value) + '&&' +  'bgValue=' +escape(document.getElementById('notesDomainForm:txtBusinessGroup').value)+ '&&' +'bUValue=' +escape(document.getElementById('notesDomainForm:txtMarketBusinessUnit').value)+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value, 'notesDomainForm:componentTxtArea','notesDomainForm:componentTxtHidden',2,1);
	if(retvalue != false){
		copyToHidden(2,'notesDomainForm:flagForDomains');
		saveToCacheFunction(retvalue);
		}
<%
}
else {
%>
var retvalue = ewpdModalWindow_ewpd_noteDomain('../popups/benefitComponentPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&'+'notesBenefitComponentValue='+document.getElementById('notesDomainForm:componentTxtArea').value + '&&' + 'lobValue='+escape(document.getElementById('notesDomainForm:txtLob').value) + '&&' +  'beValue=' +escape(document.getElementById('notesDomainForm:txtBusinessEntity').value) + '&&' +  'bgValue=' +escape(document.getElementById('notesDomainForm:txtBusinessGroup').value)+ '&&' +'bUValue=' +escape(document.getElementById('notesDomainForm:txtMarketBusinessUnit').value)+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value, 'notesDomainForm:componentTxtArea','notesDomainForm:componentTxtHidden',2,1);
	if(retvalue != false){
		copyToHidden(2,'notesDomainForm:flagForDomains');
		}
<%
}
%>		
}

function benSelected(){		
	var retValue = checkForBusinessDomains();
	if(retValue == false){
		return false;
	}
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
var retvalue = ewpdModalWindow_ewpd('../popups/benefitPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'notesBenefitValue='+document.getElementById('notesDomainForm:benefitTxtArea').value+ '&&' +  'lobValue='+escape(document.getElementById('notesDomainForm:txtLob').value) + '&&' + 'beValue=' + escape(document.getElementById('notesDomainForm:txtBusinessEntity').value) + '&&' +  'bgValue=' +escape(document.getElementById('notesDomainForm:txtBusinessGroup').value)+ '&&' +'bUValue=' +escape(document.getElementById('notesDomainForm:txtMarketBusinessUnit').value)+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value, 'notesDomainForm:benefitTxtArea','notesDomainForm:benefitTxtHidden',2,1); 
	if(retvalue != false){
		copyToHidden(3,'notesDomainForm:flagForDomains');
		saveToCacheFunction(retvalue);
	}
<%
}
else {
%>
var retvalue = ewpdModalWindow_ewpd_noteDomain('../popups/benefitPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'notesBenefitValue='+document.getElementById('notesDomainForm:benefitTxtArea').value+ '&&' +  'lobValue='+escape(document.getElementById('notesDomainForm:txtLob').value) + '&&' + 'beValue=' + escape(document.getElementById('notesDomainForm:txtBusinessEntity').value) + '&&' +  'bgValue=' +escape(document.getElementById('notesDomainForm:txtBusinessGroup').value)+ '&&' +'bUValue=' +escape(document.getElementById('notesDomainForm:txtMarketBusinessUnit').value)+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value, 'notesDomainForm:benefitTxtArea','notesDomainForm:benefitTxtHidden',2,1); 
	if(retvalue != false){
		copyToHidden(3,'notesDomainForm:flagForDomains');
	}
<%
}
%>	
}

function questionSelected(){	
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
var retvalue = ewpdModalWindow_ewpd('../popups/questionPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'notesQuestionValue='+document.getElementById('notesDomainForm:questionTxtArea').value+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value,'notesDomainForm:questionTxtArea','notesDomainForm:questionTxtHidden',2,1); 
	if(retvalue != false){
		copyToHidden(6,'notesDomainForm:flagForDomains');
		saveToCacheFunction(retvalue);
	}
<%
}
else {
%>
var retvalue = ewpdModalWindow_ewpd_noteDomain('../popups/questionPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'notesQuestionValue='+document.getElementById('notesDomainForm:questionTxtArea').value+ '&&' + 'noteId=' + document.getElementById('notesDomainForm:noteID').value,'notesDomainForm:questionTxtArea','notesDomainForm:questionTxtHidden',2,1); 
	if(retvalue != false){
		copyToHidden(6,'notesDomainForm:flagForDomains');
	}
<%
}
%>		
}

function termSelected(){
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
var retvalue = getSelectedDomainReferenceData('../standardbenefitpopups/benefitTermPopUp.jsp'+getUrl(),'notesDomainForm','txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','termDiv','notesDomainForm:termTxtHidden',2,1,'term');setRefDataUseFlag('notesDomainForm', 'termTxtHidden', 'termDiv');
		
	if(retvalue != false){
		copyToHidden(4,'notesDomainForm:flagForDomains');
		saveToCacheFunction(retvalue);
	}
<%
}
else {
%>
var retvalue = getSelectedDomainReferenceDataNoteDomain('../standardbenefitpopups/benefitTermPopUp.jsp'+getUrl(),'notesDomainForm','txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','termDiv','notesDomainForm:termTxtHidden',2,1,'term');setRefDataUseFlag('notesDomainForm', 'termTxtHidden', 'termDiv');
		
	if(retvalue != false){
		copyToHidden(4,'notesDomainForm:flagForDomains');
	}
<%
}
%>	
}

function qualifierSelected(){
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
var retvalue = getSelectedDomainReferenceData('../standardbenefitpopups/benefitQualifierPopUp.jsp','notesDomainForm','txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','qualifierDiv','notesDomainForm:qualifierTxtHidden',2,1,'qualifier');setRefDataUseFlag('notesDomainForm', 'qualifierTxtHidden', 'qualifierDiv',2,2);
	if(retvalue != false){
		copyToHidden(5,'notesDomainForm:flagForDomains');
		saveToCacheFunction(retvalue);
	}
<%
}
else {
%>
var retvalue = getSelectedDomainReferenceDataNoteDomain('../standardbenefitpopups/benefitQualifierPopUp.jsp','notesDomainForm','txtMarketBusinessUnit','txtLob','txtBusinessEntity','txtBusinessGroup','qualifierDiv','notesDomainForm:qualifierTxtHidden',2,1,'qualifier');setRefDataUseFlag('notesDomainForm', 'qualifierTxtHidden', 'qualifierDiv',2,2);
	if(retvalue != false){
		copyToHidden(5,'notesDomainForm:flagForDomains');
	}
<%
}
%>	
}

function confirmNavigation(){
		if(document.getElementById('notesDomainForm:flagForNavigation').value == 'true'){
			var message = "Are you sure you want to navigate away from this page? \nAll unsaved changes will be lost.  Click OK to continue, or Cancel to stay on the current page."	
			if(window.confirm(message)){
				submitLink('notesDomainForm:genInfo');	
			}else{
				return false;
			}
		}else{
			submitLink('notesDomainForm:genInfo');	
		}
	
}
function printSelection() 
{
	var param = new Object();
	param.parentWindow = window;
	param.navigationForPrint = document.getElementById('notesDomainForm:flagForNavigation').value;
	var url = "../popups/printSelectionPopup.jsp"+getUrl();
	newWinForView =window.showModalDialog(url,param,"dialogHeight:115px;dialogWidth:275px;resizable=false;status=no;");
}
function selectProd(){
	if(window.event.keyCode == 13){
		prodSelected();
		return false;
	}else{
		return true;
	}
}
function selectBenComp(){
	if(window.event.keyCode == 13){
		benCompSelected();
		return false;
	}else{
		return true;
	}
}
function selectBenefit(){
	if(window.event.keyCode == 13){
		benSelected();
		return false;
	}else{
		return true;
	}
}

function selectQuestions(){
	if(window.event.keyCode == 13){
		questionSelected();
		return false;
	}else{
		return true;
	}
}
document.getElementById('lobDiv').style.height= "17px";
document.getElementById('BusinessEntityDiv').style.height= "17px";
document.getElementById('BusinessGroupDiv').style.height= "17px";
document.getElementById('MarketBusinessUnitDiv').style.height= "17px";

// Enable or disable delete button
function validateDelete(value){
	// var declarations
	var tableObject = null;
	var isChecked = null;
	var checkBoxObject = null;
	var noOfTerms = null;

	// get the table object
	if(value == 1){
		tableObject = document.getElementById('notesDomainForm:productTable');
	}else if(value == 2){
		tableObject = document.getElementById('notesDomainForm:benfcompTable');
	}else if(value == 3){
		tableObject = document.getElementById('notesDomainForm:benfTable');
	}else if(value == 4){
		copyToHidden(true,'notesDomainForm:validationForTerm');
		tableObject = document.getElementById('notesDomainForm:termTable');
	}else if(value == 5){
		tableObject = document.getElementById('notesDomainForm:qualifierTable');
	}
	else if(value == 6){	
		tableObject = document.getElementById('notesDomainForm:questTable');
	}

	if(tableObject){
		isChecked = false;
		// iterate the rows
		for(var i = 0; i < tableObject.rows.length; i++){
			// check whether the checkbox is checked
			checkBoxObject = tableObject.rows[i].cells[1].children[0];
			if(checkBoxObject && checkBoxObject.checked){
				isChecked = true;
				break;
			}
		}
		document.getElementById('notesDomainForm:delete').disabled = !isChecked;	
	}
}

//function to confirm deletion
function confirmDeletion(){				
	var message = "Are you sure you want to delete?";
	var domains = getSelectedNoteDomains();
	if(domains == ''){
		alert("Please select atleast one domain to delete.");	
		return false;
	}
	else{
		if(window.confirm(message)){
			document.getElementById('notesDomainForm:targetHiddenFieldForDelete').value = domains;
			submitLink('notesDomainForm:deleteDataDomain');		
		}
		else{
			return false;
		}
	}
}
// function to get the selected notedomains for delete.
function getSelectedNoteDomains(){
	// variable declarations
	var productTableObject = null;
	var benfcompTableObject = null;
	var benfTableObject = null;
	var questTableObject = null;
	var termTableObject = null;
	var qualifierTableObject = null;
	var productDomains = null;
	var benfcompDomains = null;
	var questDomains = null;
	var benfDomains = null;
	var termDomains = null;
	var qualifierDomains = null;
	var wholeDomains = '';

	// get the table object
	
	productTableObject = document.getElementById('notesDomainForm:productTable');
	productDomains = getTableValues(productTableObject);
	questTableObject = document.getElementById('notesDomainForm:questTable');
	questDomains = getTableValues(questTableObject);
	benfcompTableObject = document.getElementById('notesDomainForm:benfcompTable');
	benfcompDomains = getTableValues(benfcompTableObject);
	benfTableObject = document.getElementById('notesDomainForm:benfTable');
	benfDomains = getTableValues(benfTableObject);
	qualifierTableObject = document.getElementById('notesDomainForm:qualifierTable');
	qualifierDomains = getTableValues(qualifierTableObject);
	termTableObject = document.getElementById('notesDomainForm:termTable');
	termDomains = getTableValues(termTableObject);
	
	//combining each domain
	if(productDomains != '' && productDomains){
		wholeDomains = productDomains;
	}

	if(benfcompDomains != '' && benfcompDomains){
		if(wholeDomains != ''){
			wholeDomains = wholeDomains + '~' + benfcompDomains;		
		}else{
			wholeDomains = benfcompDomains;
		}
	}

	if(benfDomains != '' && benfDomains){
		if(wholeDomains != ''){
			wholeDomains = wholeDomains + '~' + benfDomains;		
		}else{
			wholeDomains = benfDomains;
		}
	}

	if(questDomains != '' && questDomains){
		if(wholeDomains != ''){
			wholeDomains = wholeDomains + '~' + questDomains;		
		}else{
			wholeDomains = questDomains;
		}
	}

	if(qualifierDomains != '' && qualifierDomains){
		if(wholeDomains != ''){
			wholeDomains = wholeDomains + '~' + qualifierDomains;		
		}else{
			wholeDomains = qualifierDomains;
		}
	}
	if(termDomains != '' && termDomains){
		if(wholeDomains != ''){
			wholeDomains = wholeDomains + '~' + termDomains;		
		}else{
			wholeDomains = termDomains;
		}
	}
	return wholeDomains;
}
function getTableValues(tableObject){
	// variable declarations
	var checkBoxObject = null;
	var domains = null;
	var status = null;

	if(tableObject){
		domains = '';
		status = false;
		// iterate the rows
		for(var i = 0; i < tableObject.rows.length; i++){
			// check whether the checkbox is checked
			checkBoxObject = tableObject.rows[i].cells[1].children[0];
			if(checkBoxObject && checkBoxObject.checked){
				if(status){
					domains = domains + '~';
				}
				status = true;
				// get the defn id and append the ids
				domains = domains + tableObject.rows[i].cells[0].children[0].value + '~' + tableObject.rows[i].cells[0].children[1].value + '~' + tableObject.rows[i].cells[0].children[2].value;
			}
		}
	}
	// return the selected ids
	return domains;

}

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
