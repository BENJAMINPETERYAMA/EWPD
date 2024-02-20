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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 20%;
}
.shortDescriptionColumn {
	width: 80%;
}
</style>
<TITLE>Benefit Component Hierarchies View</TITLE>
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
	<h:form styleClass="form" id="BenefitComponentHierarchiesViewForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>

			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
						<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>



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
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{benefitComponentBackingBean.loadGeneralInformationView}">
											<h:outputText value=" General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<h:inputHidden id="hiddenTabValue"
									value="#{benefitComponentBackingBean.componentTypeTab}"></h:inputHidden>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:commandLink>
											<h:outputText value="Benefit Hierarchies" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Active"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="bcNotesTab">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{BenefitComponentNotesBackingBean.loadBenefitComponentNotesForView}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <BR />
						<div id="emptymsg" ><center><h:outputText
									value="No benefits Available."
									styleClass="dataTableColumnHeader" /></center></div>
								<div id="resultHeaderDiv">
						<TABLE id="newTable" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>

							<div id="resultHeaderDiv">

								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0">
									<tr>
										<TD><b><h:outputText value="Associated Benefits"></h:outputText></b>
										</TD>
									</tr>
								</TABLE>
							<div id="resultHeaderDiv1">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable2" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="20%"><b><h:outputText
												value="Sequence"></h:outputText></b></td>
											<td align="left" width="80%"><b><h:outputText
												value="Benefit"></h:outputText></b></td>

										</TR>
									</TBODY>
								</TABLE>
								</div>
								</div>
								</td>
							</tr>

							<TR valign="top">
								<TD width="100%"><div id="dataTableDiv" style="height:240;overflow:auto"><h:dataTable
									headerClass="dataTableHeader" id="benefitHierarchiesTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									bgcolor="#cccccc"
									value="#{BenefitComponentHierarchiesBackingBean.benefitHierarchies}"
									rowClasses="dataTableEvenRow,dataTableOddRow"  columnClasses="selectOrOptionColumn,shortDescriptionColumn"
									border="0" width="100%">
									<h:column>
										<h:inputHidden id="benefitIdHidden" value="#{singleValue.sequenceNumber}"></h:inputHidden>
										<h:outputText id="benefitId"
											value="#{singleValue.sequenceNumber}"></h:outputText>
										</h:column>
									<h:column>
										<h:outputText id="benefitName"
											value="#{singleValue.benefitName}"></h:outputText>
										
									</h:column>
								</h:dataTable></div>

								</TD>
							</TR>

						</TABLE>
						</div>
						<!--	End of Page data	--></fieldset>

						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td align="right">
								<table>
									<tr>
										<td><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentBackingBean.state}" />
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentBackingBean.status}" />
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						



						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --> </td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
		</h:form>
	</BODY>
</f:view>

<script>
	// setColumnWidth('BenefitComponentHierarchiesViewForm:benefitHierarchiesTable','20%:80%');

	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('BenefitComponentHierarchiesViewForm:benefitHierarchiesTable');
		if(hiddenIdObj.rows.length == 0){

			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		}else{
			document.getElementById('resultHeaderTable').width = "100%";
				var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
				if(document.getElementById('BenefitComponentHierarchiesViewForm:benefitHierarchiesTable').rows.length <=12){
					document.getElementById('dataTableDiv').width = relTblWidth+"px";	
					document.getElementById('resultHeaderDiv1').width = relTblWidth+"px";
					setColumnWidth('BenefitComponentHierarchiesViewForm:benefitHierarchiesTable','20%:80%');
				}else{
					document.getElementById('resultHeaderTable2').width = relTblWidth-17+"px";
					setColumnWidth('BenefitComponentHierarchiesViewForm:benefitHierarchiesTable','20%:80%');
				}
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsg').style.visibility = 'hidden';
		}
	}	
	
// Enhancement	
	// To display the notes tab only if the componentType is standard
	hideNotesTab();
	function hideNotesTab(){
	var tab = document.getElementById('BenefitComponentHierarchiesViewForm:hiddenTabValue').value;	
	 // alert('tab:'+ tab);
	if(tab == "Standard Definition")
		bcNotesTab.style.display = '';
	else
		bcNotesTab.style.display = 'none';
	}
// End - Enhancement
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponentHierarchyPrint" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>

