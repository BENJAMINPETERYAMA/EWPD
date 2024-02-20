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

<TITLE>Product Structure Benefit Component View</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="viewProdStructureForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">

						
						<DIV class="treeDiv"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<TD class="tabNormal"><h:commandLink
											action="#{productStructureGeneralInfoBackingBean.loadGeneralInfo}"
											id="linkToGeneralInfo">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>


								<TD width="200">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TBODY>
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
												width="3" height="21"></TD>
											<TD class="tabActive"><h:outputText id="labelBC"
												value="Benefit Component"></h:outputText></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TBODY>
								</TABLE>
								</TD>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>

								<td colspan="2" valign="top" class="ContentArea">

								<fieldset>

								<div id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% ">Associated Benefit
								Components</div>
								<h:outputText value="No Benefit Components Available."
									rendered="#{productStructureBenefitComponentBackingBean.selectBenefitComponents == null}"
									styleClass="dataTableColumnHeader" />
								<table class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0">
									<%-- tr bgcolor="#cccccc">
	
						<td colspan="3" class= "tabTitleBar" bgcolor="#cccccc"><span id="stateCodeStar"><strong>
						<h:outputText value="Associated Benefit Components"/></strong></span></td>
					</tr>
					<h:outputText value="No Major Heading Information is Available." rendered="#{productStructureBenefitComponentBackingBean.benefitComponentList == null}" styleClass="dataTableColumnHeader"/--%>
									<tr>
										<td>
										<div id="resultHeaderDiv">
										<TABLE id="headerTable" width="100%" border="0"
											bgcolor="#cccccc" cellpadding="1" cellspacing="1">
											<tr class="dataTableOddRow">
												<TD></TD>
												<td><strong><h:outputText value="Benefit Component" /></strong></td>
											</tr>
										</TABLE>
										</div>
										</td>
									</tr>
									<tr>
										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv"
											style="height:261px; overflow: auto;"><h:dataTable
											cellspacing="1" id="bComponentDataTable"
											rendered="#{productStructureBenefitComponentBackingBean.selectBenefitComponents != null}"
											value="#{productStructureBenefitComponentBackingBean.selectBenefitComponents}"
											rowClasses="dataTableEvenRow,dataTableOddRow"
											var="singleValue" cellpadding="3" width="100%"
											bgcolor="#cccccc">
											<h:column>
													<h:outputText value="#{singleValue.sequenceNum}"></h:outputText>
													<h:inputHidden value="#{singleValue.sequenceNum}"></h:inputHidden>
												<%-- <h:inputText id="id" value="#{singleValue.sequenceNum}"
													maxlength="3" onkeypress="return isNumberKey(event);"
													styleClass="sequenceNumberReadOnly" readonly="true" />  --%>
											</h:column>
											<h:column>
												<h:outputText id="name" value="#{singleValue.name}"></h:outputText>
												<h:inputHidden id="benefitComponentName"
													value="#{singleValue.name}"></h:inputHidden>
											</h:column>
										</h:dataTable></div>

										</td>
									</tr>
									

								</table>



								</fieldset>
								<br />
								<FIELDSET
									style="margin-left:0px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
								<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
									<tr>
										<td align="right">
										<table>
											<tr>
												<td>State</td>
												<td>:<h:outputText id="state"
													value="#{productStructureBenefitComponentBackingBean.state}">
												</h:outputText><h:inputHidden id="stateHidden"
													value="#{productStructureBenefitComponentBackingBean.state}">
												</h:inputHidden> <SCRIPT>copyHiddenToInputText('viewProdStructureForm:stateHidden','viewProdStructureForm:state',1); </SCRIPT>
												&nbsp;</td>
											</tr>
											<tr>
												<td>Status</td>
												<td>:&nbsp;<h:outputText id="status"
													value="#{productStructureBenefitComponentBackingBean.status}"></h:outputText><h:inputHidden
													id="statusHidden"
													value="#{productStructureBenefitComponentBackingBean.status}">
												</h:inputHidden><SCRIPT>copyHiddenToInputText('viewProdStructureForm:statusHidden','viewProdStructureForm:status',1); </SCRIPT>
												</td>
											</tr>
											<tr>
												<td>Version</td>
												<td>:<h:outputText id="version"
													value="#{productStructureBenefitComponentBackingBean.version}">
												</h:outputText> <h:inputHidden id="versionHidden"
													value="#{productStructureBenefitComponentBackingBean.version}">
												</h:inputHidden> <SCRIPT>copyHiddenToInputText('viewProdStructureForm:versionHidden','viewProdStructureForm:version',1); </SCRIPT>
												&nbsp;</td>
											</tr>
										</table>
										</td>
									</tr>
								</TABLE>
								</FIELDSET>
								</td>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
initialize();
function initialize(){
	if(document.getElementById('viewProdStructureForm:bComponentDataTable') != null) {
	var relTblWidth = document.getElementById('viewProdStructureForm:bComponentDataTable').offsetWidth;
		if(document.getElementById('viewProdStructureForm:bComponentDataTable').offsetHeight <= 100) {
				document.getElementById('viewProdStructureForm:bComponentDataTable').width = relTblWidth+"px";
				setColumnWidth('headerTable','21%:51%');	
				setColumnWidth('viewProdStructureForm:bComponentDataTable','21%:51%');
			}else{
				var relTblWidth = document.getElementById('viewProdStructureForm:bComponentDataTable').offsetWidth;
				document.getElementById('headerTable').width = relTblWidth;
			setColumnWidth('headerTable','21%:51%');	
			setColumnWidth('viewProdStructureForm:bComponentDataTable','21%:51%');
			}		
	}else {
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
	}
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComp" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
