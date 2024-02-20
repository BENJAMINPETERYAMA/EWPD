<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	width: 50%;
}
.selectsystemDomainIdColumn1 {
	width: 51%;
}
</style>

<base target=_self>
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
</script>
<TITLE>Note Domains</TITLE>
<script language="JavaScript" src="../../js/wpd.js"></script>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="dataDomainForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="260" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td width="186" class="tabNormal"> <h:commandLink action="#{notesBackingBean.loadGeneralInfoForView}"><h:outputText value="General Information"/> </h:commandLink></td> 
													<h:inputHidden id="noteID" value="#{notesBackingBean.noteId}"></h:inputHidden>
													<h:inputHidden id="noteName" value="#{notesBackingBean.name}"></h:inputHidden>
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td class="tabActive"> 
														<h:outputText value="Data Domain"/>
													</td>
													<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
												</tr>
											</table>
										</td>
									
										<td width="100%"></td>
									</tr>
								</table>	
					
						<!-- End of Tab table -->
							<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="100%">
							<TBODY>				
								<TR>
									<TD colspan="10">
										<DIV id="ProductHeaderDiv">	
									<!-- Product table -->
										<TABLE width="100%"	cellpadding="0" cellspacing="0" border="0">
											<tr bgcolor="#cccccc">
												<td colspan="6"><span id="stateCodeStar"><strong>Domained Product</strong></span></td>
											</tr >

												<TR>
													<TD >
													<DIV id="resultHeaderDiv">
														<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
															id="resultHeaderTable" border="0" width="100%">
															<TBODY>
																<TR class="dataTableColumnHeader">
																	<td align="center" width="100%"></td>
																</TR>
															</TBODY>
														</TABLE>
														</DIV>
													</TD>
											  </TR>
											<tr>
												<td>
												<DIV id="ProductdataTableDiv" style="width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"     columnClasses ="selectsystemDomainIdColumn"
													cellspacing="1" width="100%" id="productTable" rendered="#{notesBackingBean.productList!= null}"
													value="#{notesBackingBean.productList}" var="eachRow"
													cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
												</h:dataTable>
												</DIV>
												</td>
											</tr>
										</TABLE>
										</DIV>
									</TD>
									</TR>
							<!--  BenefitComponent Table -->
									<TR>
										<TD colspan="10">
										<DIV id="CompHeaderDiv">
										<TABLE width="100%"	cellpadding="0" cellspacing="0" border="0">
											<tr bgcolor="#cccccc">
												<td colspan="6"><span id="stateCodeStar"><strong>Domained Benefit Component</strong></span></td>
											</tr >

												
											<tr>
												<td>
												<DIV id="BenefitdataTableDiv" style="width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses ="selectsystemDomainIdColumn1"
													cellspacing="1" width="100%" id="benfcompTable" rendered="#{notesBackingBean.componentList!= null}"
													value="#{notesBackingBean.componentList}" var="eachRow"
													cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
												</h:dataTable>
												</DIV>
												</td>
											</tr>
										</TABLE>
										</DIV>
										</TD>										
									</TR>									
									
									<!--  Benefit Table -->
										<TR>
										<TD colspan="10">
										<DIV id="BenefitHeaderDiv">
										<TABLE width="100%"	cellpadding="0" cellspacing="0" border="0">
											<tr bgcolor="#cccccc">
												<td colspan="6"><span id="stateCodeStar"><strong>Domained Benefit</strong></span></td>
											</tr >

												
											<tr>
												<td>
												<DIV id="DombendataTableDiv" style=width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;>
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"  columnClasses ="selectsystemDomainIdColumn1"
													cellspacing="1" width="100%" id="benfTable" rendered="#{notesBackingBean.benefitList!= null}"
													value="#{notesBackingBean.benefitList}" var="eachRow"
													cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
												</h:dataTable>
												</DIV>
												</td>
											</tr>
										</TABLE>
										</DIV>
										</TD>		
										</TR>

									<!--  Question Table -->
									<TR>
										<TD colspan="10">
										<DIV id="QuestionHeaderDiv">
										<TABLE width="100%"	cellpadding="0" cellspacing="0" border="0">
											<tr bgcolor="#cccccc">
												<td colspan="6"><span id="stateCodeStar"><strong>Domained Question</strong></span></td>
											</tr >

												
											<tr>
												<td>
												<DIV id="QuestiondataTableDiv" style="width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;">
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses ="selectsystemDomainIdColumn1"
													cellspacing="1" width="100%" id="questionTable" rendered="#{notesBackingBean.questionList!= null}"
													value="#{notesBackingBean.questionList}" var="eachRow"
													cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
												</h:dataTable>
												</DIV>
												</td>
											</tr>
										</TABLE>
										</DIV>
										</TD>										
									</TR>		 

										<!--  Term Table -->
										<TR>
										<TD colspan="10">
										<DIV id="TermHeaderDiv">			
										<TABLE  width="100%" cellpadding="0" cellspacing="0" border="0">
											<tr bgcolor="#cccccc">
												<td colspan="6"><span id="stateCodeStar"><strong>Domained Term</strong></span></td>
											</tr >

												
											<tr>
												<td>
												<DIV id="TermdataTableDiv" style=width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;>
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses ="selectsystemDomainIdColumn1"
													cellspacing="1" width="100%" id="termTable" rendered="#{notesBackingBean.termList!= null}"
													value="#{notesBackingBean.termList}" var="eachRow"
													cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
													</h:column>
												</h:dataTable>
												</DIV>
												</td>
											</tr>
										</TABLE>
										</DIV>
										</td>
										</tr>
						<!--  Qualifier Table -->
										<TR>
										<TD colspan="10">
										<DIV id="QualifierHeaderDiv">
										<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr bgcolor="#cccccc">
												<td colspan="6"><span id="stateCodeStar"><strong>Domained Qualifier</strong></span></td>
											</tr >

												
											<tr>
												<td>
												
												<DIV id="qualifierdataTableDiv" style=width:100%;position:relative;z-index:1;font-size:10px;overflow-y:scroll;overflow-x:hidden;>
												<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses ="selectsystemDomainIdColumn1"
													cellspacing="1" width="100%" id="qualifierTable" rendered="#{notesBackingBean.qualifierList!= null}"
													value="#{notesBackingBean.qualifierList}" var="eachRow"
													cellpadding="0" bgcolor="#cccccc">
													<h:column>
														
														<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
														<h:outputText value="#{eachRow.systemDomainId}"></h:outputText>
													</h:column>
												</h:dataTable>
												</DIV>
												</td>
											</tr>
										</TABLE>
										</DIV>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset></fieldset>
						<BR>
							<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
									<td width="15">&nbsp;</td>
								<td align="left">&nbsp;</td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{notesBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText id="status"
											value="#{notesBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>:&nbsp;<h:outputText id="version"
											value="#{notesBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
						</TABLE>
						</fieldset>
						</td></tr></table>
						
						</TD>
					</TR>
				</table>


			</h:form>
		
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="notesDataDomain" />
	</BODY>
</f:view>
<script>
	//Hide table if no value is present

	hideIfNoValue('ProductHeaderDiv','productTable');
	hideIfNoValue('BenefitHeaderDiv','benfTable');
	hideIfNoValue('CompHeaderDiv', 'benfcompTable');
	hideIfNoValue('TermHeaderDiv','termTable');
	hideIfNoValue('QualifierHeaderDiv', 'qualifierTable');
	hideIfNoValue('QuestionHeaderDiv', 'questionTable');

	function hideIfNoValue(divId, tableName){
		hiddenIdObj = document.getElementById('dataDomainForm:'+tableName);
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById(divId).innerText = null;
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}
adjustHieght('ProductdataTableDiv','productTable');
adjustHieght('BenefitdataTableDiv','benfcompTable');
adjustHieght('DombendataTableDiv','benfTable');


function adjustHieght(divId, tableName){
	var hgt = 15;
	hiddenIdObj = document.getElementById('dataDomainForm:'+tableName);
	if(hiddenIdObj != null){
		if(hiddenIdObj.rows.length > 12){
			document.getElementById(divId).style.height="170px";
		} else{
			hgt = hgt * hiddenIdObj.rows.length;
		document.getElementById(divId).style.height = hgt + "px";
		}
	}	
}
</script>
</HTML>
