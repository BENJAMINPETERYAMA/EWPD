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

<TITLE>Edit Reserved Contract</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('contractForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpReq.setAttribute("breadCrumbText",
				"Administration >> Contract Id >> Edit");
	%> <jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">


						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{reservedContractBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="73%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">

								<TR>
									<td width="148">
									<TABLE width="148" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabActive">Basic Information</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>

							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:450">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="537"><h:outputText id="lineOfBusiness"
													value="Line Of Business"
													styleClass="#{reservedContractBackingBean.lobInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{reservedContractBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="220">
												<div id="lineOfBusinessDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
											<TR>
												<TD width="537"><h:outputText id="businessEntity"
													value="Business Entity"
													styleClass="#{reservedContractBackingBean.entityInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{reservedContractBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="220">
												<div id="businessEntityDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
											<TR>
												<TD width="537"><h:outputText id="businessGroup"
													value="Business Group"
													styleClass="#{reservedContractBackingBean.groupInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{reservedContractBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="220">
												<div id="businessGroupDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
											<%--<tr>
												<td width="440"><h:outputText id="marketSegmentLabel"
													value="Market Busines Unit " /></td>
												<TD width="220">
													<div id="MarketBusinessUnitDiv" class="selectDivReadOnly"></div>
												</TD>
												<td><h:inputHidden id="marketSegmentHidden"
													value="#{reservedContractBackingBean.marketSegment}"></h:inputHidden>
												</td>
												<TD width="100">&nbsp;</TD>
											</tr>--%>
											<TR>
												<TD width="537"><h:outputText id="marketSegment"
													value="Market Busines Unit"
													styleClass="#{reservedContractBackingBean.marketInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
												</TD>
												<h:inputHidden id="marketSegmentHidden" 
															   value="#{reservedContractBackingBean.marketSegment}"/>
												<TD width="220">
													<div id="MarketBusinessUnitDiv" class="selectDivReadOnly"></div>
												</TD>
												<TD width="100">&nbsp;</TD>
											</TR>
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>

									<TR>

										<TD width="210">&nbsp;&nbsp;<h:outputText id="contractId"
											value="Contract ID"
											styleClass="#{reservedContractBackingBean.contractIdForEdit ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="contractIdHidden"
											value="#{reservedContractBackingBean.contractIdForEdit}"></h:inputHidden>

										<TD width="185"><h:outputText id="contractId_txt"
											value="#{reservedContractBackingBean.contractIdForEdit}" />

										</TD>

									</TR>

									<TR>
										<TD width="210">&nbsp;&nbsp;<h:outputText id="comments"
											value="Comments" /></TD>
										<h:inputHidden id="commentsHidden"
											value="#{reservedContractBackingBean.commentsForEdit}"></h:inputHidden>
										<TD height="63" width="210"><h:outputText id="comments_txt"
											styleClass="formTxtAreaField_GeneralDesc" style="display:inline-block;"
											value="#{reservedContractBackingBean.commentsForEdit}" /></TD>
									</TR>


									<TR valign="top">

										<TD width="210">&nbsp;&nbsp;<h:outputText
											value="Effective Date"
											styleClass="#{reservedContractBackingBean.startDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD width="185"><h:outputText id="effectiveDate_txt"
											value="#{reservedContractBackingBean.startDate}" /> <h:inputHidden
											id="effectiveDateHidden"
											value="#{reservedContractBackingBean.startDate}">
										</h:inputHidden>
									</TR>
									<TR valign="top">

										<TD width="210">&nbsp;&nbsp;<h:outputText
											value="Expiry Date *"
											styleClass="#{reservedContractBackingBean.endDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<TD><h:inputText styleClass="formInputField" id="endDate_txt"
											maxlength="10" tabindex="5"
											value="#{reservedContractBackingBean.endDate}" /><BR
											class="brclass">
										<span class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD><A href="#"
											onclick="cal1.select('contractForm:endDate_txt','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1"
											title="cal1.select('contractForm:endDate_txt','anchor1','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="6" /> </A></TD>
										<h:inputHidden id="expiryDateHidden"
											value="#{reservedContractBackingBean.endDate}">
										</h:inputHidden>

									</TR>

									<TR>
										<TD width="210">&nbsp;&nbsp;<h:commandButton value="Update" 
											styleClass="wpdButton" tabindex="10" id="updateButton" onmousedown="dataToBeSaved();"
											onclick="copyToHidden('contractForm:endDate_txt');return false;">
										</h:commandButton></TD>
										<TD width="185">&nbsp; <h:inputHidden
											id="reservepoolStatusHidden"
											value="#{reservedContractBackingBean.reservepoolStatus}">
										</h:inputHidden></TD>
										<h:inputHidden id="duplicateData"
											value="#{reservedContractBackingBean.duplicateData}"></h:inputHidden>
										<h:commandLink id="updateLink"
											style="hidden" action="#{reservedContractBackingBean.updateReserveInfo}">
										</h:commandLink>
										<TD width="288"></TD>
									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>

							<BR>


							<BR>


							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					
					<%--<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>

function dataToBeSaved(){
	if(document.getElementById('contractForm:endDate_txt').value == document.getElementById('contractForm:duplicateData').value){
		alert('No modifications to be updated.');
	}
	
}
//document.getElementById('contractForm:marketSegmentHidden').value=document.getElementById('contractForm:marketSegment').value;

	// Space for page realated scripts
 copyHiddenToDiv_ewpd('contractForm:lineOfBusinessHidden','lineOfBusinessDiv',1,1); 
 copyHiddenToDiv_ewpd('contractForm:businessEntityHidden','businessEntityDiv',1,1); 
 copyHiddenToDiv_ewpd('contractForm:businessGroupHidden','businessGroupDiv',1,1); 
 copyHiddenToDiv_ewpd('contractForm:marketSegmentHidden','MarketBusinessUnitDiv',1,1); 

	function copyToHidden(field1){
		var idField = document.getElementById(field1).value;
 		document.getElementById('contractForm:expiryDateHidden').value = idField;
		document.getElementById('contractForm:updateLink').click();
	}

	document.getElementById('contractForm:duplicateData').value=document.getElementById('contractForm:endDate_txt').value;
 
</script>
</HTML>

