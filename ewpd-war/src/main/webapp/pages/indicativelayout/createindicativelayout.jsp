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

<TITLE>Indicative Layout Mapping</TITLE>
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
</HEAD>
<f:view>
	<BODY>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<td><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Administration >> Indicative Layout >> Maintain ");
    %>
				 <jsp:include page="../navigation/top.jsp" /></TD>
			</TR>
			<TR>
				<TD>
					<h:form styleClass="form" id="indicativeLayoutForm">
						<h:inputHidden id="hidOperationPerformed" value='#{indicativeLayoutMappingBackingBean.operationPerformed}' />
						<h:inputHidden id = "hidOpenImportPopUp" value="#{indicativeLayoutMappingBackingBean.openImportPopUp}"></h:inputHidden>
						<h:inputHidden id = "hidOpenCopyToProdWindow" value="#{indicativeLayoutMappingBackingBean.openCopyToProdWindow}"></h:inputHidden>
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
							<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	--></DIV>
							</TD>
							<TD colspan="2" valign="top" class="ContentArea">
									 <TABLE>
										<TBODY>
											<TR>
											<TD><w:message
												value="#{indicativeLayoutMappingBackingBean.validationMessages}"></w:message>
											</TD>
											</TR>
										</TBODY>
									</TABLE> <!-- Table containing Tabs -->
									
									
									<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
										id="TabTable">
										<TR>
											<TD width="200">
												<TABLE width="200" border="0" cellspacing="0"
													cellpadding="0">
													<TR>
														<TD width="2" align="left"><IMG
															src="../../images/activeTabLeft.gif" width="3"
															height="21">
														</TD>
														<TD width="186" class="tabActive"><h:outputText
																value="Indicative Layout" />
														</TD>
														<TD width="2" align="right"><IMG
															src="../../images/activeTabRight.gif" width="2"
															height="21">
														</TD>
													</TR>
												</TABLE></TD>
													<TD width="100%"></TD>
										</TR>
									</TABLE> <!-- End of Tab table -->

									<FIELDSET
										style="margin-left: 6px; margin-right: -6px; padding-bottom: 1px; padding-top: 6px; width: 100% ; height:370px">

										<!--	Start of Table for actual Data	-->
										<TABLE border="0" cellspacing="0" cellpadding="3"
											class="outputText">
											<TBODY>

												<TR>
													<TD width="20%" colspan ="2"><h:outputText id="IndicativeLayout"
															value="Region">
														</h:outputText></TD>
													<TD width="20%" ><h:selectOneRadio required="true"
															id="applicationEnviroment" tabindex ="1"
															value="#{indicativeLayoutMappingBackingBean.applicationEnviroment}">
															<f:selectItem id="indicativeTest" itemLabel="Test"
																itemValue="Test" />
															<f:selectItem id="indicativeProd" itemLabel="Prod"
																itemValue="Prod" />
														</h:selectOneRadio></TD>
													<TD width="60%"><f:verbatim></f:verbatim></TD>
												</TR>

												<!-- EB03 Identifiers  -->

												<TR>
													<TD width="30%" colspan ="2"><h:outputText id="indicativeSegments"
															value="Indicative Segment ">
														</h:outputText></TD>
													<TD width="28%"><h:selectOneRadio required="true"
															id="indicativeSegment" tabindex ="2"
															value="#{indicativeLayoutMappingBackingBean.indicativeSegment}">
															<f:selectItem itemValue="1" id="segment1" itemLabel="1" />
															<f:selectItem itemValue="2" id="segment2" itemLabel="2" />
															<f:selectItem itemValue="3" id="segment3" itemLabel="3" />
															<f:selectItem itemValue="4" id="segment4" itemLabel="4" />

														</h:selectOneRadio></TD>
													<TD width="50%"><f:verbatim></f:verbatim></TD>
												</TR>
												<TR>
													<TD colspan="3" >&nbsp;</TD>
												</TR>
												<TR>
													<TD  colspan ="4">
														<h:commandButton tabindex ="3" value="Export" id="exportButton"  styleClass="wpdButton" 
															onclick="invokeServlet();return false;" />
																												
														<h:commandButton tabindex ="4" value="Import" id="importButton" style="margin-left: 40px" styleClass="wpdButton" 
															onclick="validateImportOrCopyToProd('IMPORT');return false;"/>
															
														<h:commandButton tabindex ="5" value="Copy To Prod" id="copyButton" style="margin-left: 40px" styleClass="wpdButton" 
															onclick="validateImportOrCopyToProd('COPY_TO_PROD');return false;"/>
													</TD>
													<TD width="250">&nbsp;</TD>
												</TR>
												
											</TBODY>
										</TABLE>
										<!--	End of Page data	-->
									</FIELDSET>
								</TD>
							</TR>
						</TABLE>
						<DIV id="hiddenDiv" style="visibility: hidden"></DIV>						
						<h:commandLink id="lnkValidateImportOrCopyToProd" style="display:none; visibility: hidden;"
							action="#{indicativeLayoutMappingBackingBean.validateImportOrCopyToProd}">
							<f:verbatim />
						</h:commandLink>						
					</h:form>
				</TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>

		<form name="submitIndicativeLayoutExportForm"
			action="indicativelayout.export" method="post" id="submitExportForm"
			target="targetIframe">
			<input type="hidden" id="indicativeSegmentHidden" name="indicativeSegmentHidden" /> 
			<input type="hidden" id="applicationEnviromentHidden" name="applicationEnviromentHidden" />
			<input type="hidden" id="reqType" name="reqType" />
		</form>
		<iframe width="100%" height="300"
			style="display: none; visibility: hidden" name="targetIframe"
			id="targetIframe" onreadystatechange="checkIframStatus();">
		</iframe>

		<jsp:include page="../template/freeze.html"></jsp:include>
	</BODY>
</f:view>

<script>
	var reportSubmitted = false;
	enablePage();

	// Check the status of the Iframe and enable screen by removing blocking div once the response is received.
	function checkIframStatus() {
		iframe = document.getElementById('targetIframe');
		if (reportSubmitted) {
			if (iframe
					&& iframe.document
					&& (iframe.document.readyState == 'interactive' || iframe.document.readyState == 'complete')) {
				reportSubmitted = false;
				enablePage();
			}
		}
	}

	function invokeServlet() {
		var options = document
				.getElementsByName('indicativeLayoutForm:indicativeSegment');
		var indicativeSegment = 1;
		if (options[0].checked) {
			indicativeSegment = options[0].value;
		} else if (options[1].checked) {
			indicativeSegment = options[1].value;
		} else if (options[2].checked) {
			indicativeSegment = options[2].value;
		} else {
			indicativeSegment = options[3].value;
		}		
		document.getElementById('indicativeSegmentHidden').value = indicativeSegment;
		var applicationEnvOption = document
				.getElementsByName('indicativeLayoutForm:applicationEnviroment');
		var applicationEnv = '';
		if (applicationEnvOption[0].checked) {		
			applicationEnv = applicationEnvOption[0].value;
		} else if (applicationEnvOption[1].checked) {		
			applicationEnv = applicationEnvOption[1].value;
		}
		// Setting the values to the form and invoking the servlet to export the xls file.
		document.getElementById('applicationEnviromentHidden').value = applicationEnv;
		disablePage();
		document.forms['submitIndicativeLayoutExportForm'].submit();
		reportSubmitted = true;
		enablePage();
	}
	
	function getSelectedSegment(){
	var options = document
				.getElementsByName('indicativeLayoutForm:indicativeSegment');
		var indicativeSegment = 1;
		if (options[0].checked) {
			indicativeSegment = options[0].value;
		} else if (options[1].checked) {
			indicativeSegment = options[1].value;
		} else if (options[2].checked) {
			indicativeSegment = options[2].value;
		} else {
			indicativeSegment = options[3].value;
		}		
		return indicativeSegment;
	}
	
	function getAplicationRegion(){
		var applicationEnvOption = document
				.getElementsByName('indicativeLayoutForm:applicationEnviroment');
		var applicationEnv = '';
		if (applicationEnvOption[0].checked) {	
			applicationEnv = applicationEnvOption[0].value;
		} else if (applicationEnvOption[1].checked) {	
			applicationEnv = applicationEnvOption[1].value;
		}
		return applicationEnv;
	}
	
	
	function validateImportOrCopyToProd(functionality){		
		document.getElementById('indicativeLayoutForm:hidOperationPerformed').value = functionality;
		var operationPerformed = document.getElementById('indicativeLayoutForm:hidOperationPerformed').value;
		document.getElementById('indicativeLayoutForm:lnkValidateImportOrCopyToProd').click();
	}
    
	 var bool = trim(document.getElementById('indicativeLayoutForm:hidOpenImportPopUp').value);
	 if(bool == 'true'){
		var indicativeSeg = getSelectedSegment();
		var region = getAplicationRegion();
		document.getElementById('indicativeLayoutForm:hidOpenImportPopUp').value = 'false';
	 	ewpdModalWindow_import( '../popups/importUploadFilePopUp.jsp'+getUrl()+'?&segmentId=' + indicativeSeg +'&region=' +region,'indicativeSegment',indicativeSeg, 465,512);
	 }
	 
	 var openCopyToProdWindow = trim(document.getElementById('indicativeLayoutForm:hidOpenCopyToProdWindow').value);
	 if(openCopyToProdWindow == 'true'){
		var indicativeSeg = getSelectedSegment();
		var region = getAplicationRegion();
		document.getElementById('indicativeLayoutForm:hidOpenCopyToProdWindow').value = 'false';
	 	ewpdModalWindow_import( '../popups/triggerCopyToProd.jsp'+getUrl()+'?&segmentId=' + indicativeSeg +'&region=' +region,'indicativeSegment',indicativeSeg, 465, 512);
	 }
  
</script>
</HTML>
