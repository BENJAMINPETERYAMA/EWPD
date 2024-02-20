<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<base target=_self>
<TITLE>ProductNonAdjBenefitMandatesView</TITLE>
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
<h:inputHidden id="Hidden" value="#{nonAdjBenefitMandatesBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
				<h:form styleClass="form" id="nonAdjBenCompForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->	<jsp:include page="productTree.jsp"></jsp:include>						

						 		</DIV>

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<w:message></w:message>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td width="186" class="tabNormal"><h:commandLink action ="#{productBenefitGeneralInfoBackingBean.getProductBenefitGenaralInfo}"> <h:outputText value="General Information"/> </h:commandLink></td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td width="186" class="tabNormal"> <h:commandLink action ="#{productBenefitDetailBackingBean.getBenefitDefinitionsPage}"> <h:outputText value="Standard Definition"/> </h:commandLink> </td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<!--  <td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td width="186" class="tabNormal"> <h:outputText value="Adj Benefit Mandate"/> </td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif"  alt="Tab Right Normal" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td> -->
										<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/activeTabLeft.gif"  width="3" height="21" /></td>
													<td class="tabActive"> 
														<h:commandLink > <h:outputText value="Non Adj Benefit Mandate"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/activeTabRight.gif"  width="4" height="21" /></td>
												</tr>
											</table>
										</td>
										<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														<h:commandLink action="#{productBenefitNoteBackingBean.loadNotes}"><h:outputText value="Notes"/></h:commandLink>
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
										</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:3px;margin-right:3px;padding-bottom:1px;padding-top:3px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<DIV id="noNonAdj" style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000; height:292px;
						background-color:#FFFFFF;">No Non Adj Benefit Mandate Available.</DIV>
						<div id="dataTableDiv"
							style="height:292px; overflow:auto; width:100%;">
									<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left" width="25%"><strong><h:outputText value="Optional Identifier"></h:outputText></strong></td>
									<td align="left" width="25%"><strong><h:outputText value="Effective Date"></h:outputText></strong></td>
									<td align="left" width="25%"><strong><h:outputText value="Expiry Date"></h:outputText></strong></td>
									<td align="left" width="25%"><strong><h:outputText value="Mandate"></h:outputText></strong></td>
								</TR>
							</TBODY>
						</TABLE>
						
						</td>
					</tr>
					<tr>
						<TD><!-- Search Result Data Table -->
						<h:dataTable
							headerClass="dataTableHeader" id="nonAdjBenTable"
							var="singleValue" cellpadding="3" cellspacing="1"
							bgcolor="#cccccc" rendered="true"
							value="#{nonAdjBenefitMandatesBackingBean.locateResultsList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="100%">
							<h:column>
								<h:outputText id="productName"
									value="#{singleValue.optionalIdentifier}"></h:outputText>
								<h:inputHidden id="hidden_productStructureName"
									value="#{singleValue.optionalIdentifier}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productEffectiveDate"
									value="#{singleValue.effectiveDate}"><f:convertDateTime pattern="MM/dd/yyyy" />	</h:outputText>
								<h:inputHidden id="hidden_benefitComponents"
									value="#{singleValue.effectiveDate}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productExpiryDate"
									value="#{singleValue.expiryDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
								<h:inputHidden id="hidden_businessDomain"
									value="#{singleValue.expiryDate}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productVersion"
									value="#{singleValue.mandate}"></h:outputText>
								<h:inputHidden id="hidden_productStructureDescription"
									value="#{singleValue.mandate}"></h:inputHidden>
							</h:column>
						</h:dataTable>

						</TD>
					</tr>
					<TR>
						<TD><h:inputHidden id="selectedStructureId"
							value="#{productStructureSearchBackingBean.selectedStructureId}" /></TD>
					</TR>
				</table>
				</div>
<!--	End of Page data	-->
								</fieldset>		
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1" ></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file ="../navigation/bottom_view.jsp" %>
			</td>
		</tr>
	</table>
</BODY>
</f:view>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="productNonAdjBenefitManadatePrint" />
</form>
<script>
function dataTableRender(){
	if(document.getElementById('nonAdjBenCompForm:nonAdjBenTable')!= null){
	setColumnWidth('nonAdjBenCompForm:nonAdjBenTable','25%:25%:25%:25%');
	}	
}
dataTableRender();

function hideTable(){
	var tableObject = document.getElementById('nonAdjBenCompForm:nonAdjBenTable');
	if(tableObject.rows.length > 0){
		var divBnftDefn = document.getElementById('noNonAdj');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('dataTableDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noNonAdj').style.visibility = "visible";
	}
}
hideTable();
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
