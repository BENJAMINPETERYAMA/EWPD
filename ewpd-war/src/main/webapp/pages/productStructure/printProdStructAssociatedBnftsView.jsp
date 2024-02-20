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
	width: 50%;
	
}
</style>

<TITLE>Product Structure Associated Benefits</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="BenefitComponentHierarchiesForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<TD></TD>
					</tr>
					<TR>
						<TD>
						<FIELDSET
							style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99%">
						<h:outputText id="breadcrumb"
							value="#{productStructureGeneralInfoBackingBean.printBreadCrumbText}">
						</h:outputText></FIELDSET>
						</TD>
					</TR>
					<TR>

						<TD colspan="2" valign="top" class="ContentArea"><!-- Table containing Tabs -->
						
						<!-- End of Tab table -->
			<h:inputHidden id="getValue"
									value="#{productStructureGeneralInfoBackingBean.printProductStructureAssociatedBenefits}"></h:inputHidden>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% "><strong>
							Associated Standard Benefits</strong></div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDiv">
								<TABLE id="headerTable" width="100%" border="0" cellpadding="2"
										cellspacing="1">
										<tr class="dataTableOddRow">
											<td><strong><h:outputText value="Benefit Name" /></strong></td>
										</tr>
										<tr><td><br></td></tr>
									</TABLE>
								</div>
								<div id="InformationDiv"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDiv" style="width:100%;"><h:dataTable
									cellspacing="1" id="associatedBenefitsDataTable" columnClasses="selectOrOptionColumn"
									rendered="#{productStructureGeneralInfoBackingBean.benefitDetailsList != null}"
										value="#{productStructureGeneralInfoBackingBean.benefitDetailsList}"
									var="singleValue" cellpadding="2" width="100%">
										
										<h:column>
											<h:outputText id="Name" value="#{singleValue.standardBenefitDesc}"></h:outputText>
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							
						</table>
				</fieldset>

						</TD>
					</TR>

				</table>

			</h:form></td>
		</tr>
		<tr>

		</tr>
	</table>
	</BODY>
</f:view>

<script>

	
initialize();
	function initialize(){
		
		if(null != document.getElementById('BenefitComponentHierarchiesForm:associatedBenefitsDataTable')){
			if(document.getElementById('BenefitComponentHierarchiesForm:associatedBenefitsDataTable').rows.length == 0){
					document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
					document.getElementById('searchResultdataTableDiv').style.height = '1px';
					document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
					document.getElementById('InformationDiv').innerHTML = "No Benefits Available";
				}
		}
	}

window.print();

// Enhancement	
	// To display the notes tab only if the componentType is standard
	//hideNotesTab();
	//function hideNotesTab(){
	//var tab = document.getElementById('BenefitComponentHierarchiesForm:hiddenTabValue').value;	
	// alert('tab:'+ tab);
//	if(tab == "Standard Definition")
//		bcNotesTab.style.display = '';
//	else
//		bcNotesTab.style.display = 'none';
//	}


</script>

</HTML>
