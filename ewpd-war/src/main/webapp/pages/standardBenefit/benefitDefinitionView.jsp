
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

<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Benefit Definition View</TITLE>

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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>


<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>
<BASE target="_self" />
</head>



<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD><h:form styleClass="form" id="BenefitDefinitionViewForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="25%"><DIV class="treeDiv"><jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>
						</TD>
						<TD colspan="1" valign="top" class="ContentArea" width="100%">
						<TABLE>
							<TBODY>
								<TR>
									<TD><!-- Insert WPD Message Tag --></TD>
								</TR>
							</TBODY>
						</TABLE>




						<!-- Table containing Tabs -->



						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">


							<TR>
								<TD width="34%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD align="center" width="99%" class="tabNormal"><h:commandLink
											action="#{standardBenefitBackingBean.loadStandardBenefitView}">
											<h:outputText value=" General Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="33%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabActive" align="center" width="99%">
											<h:outputText
												value="#{standardBenefitBackingBean.benefitTypeTab}" />
										</TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<!-- 
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabNormal">
												<h:outputText
												value="Adj Benefit Mandates" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
 -->
								<TD id="manInfo" width="33%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD align="center" width="99%" class="tabNormal"><h:commandLink
											action="#{benefitMandateBackingBean.loadBenefitMandateView}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<td id="noteTab" width="33%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{standardBenefitNotesBackingBean.loadBenefitNotesView}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2%" align="right"><img
											src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%;height:430">

						<!--	Start of Table for actual Data	--> <br>
						<div id="emptymsg"><center><h:outputText
									value="No Benefit Definitions Attached."
									styleClass="dataTableColumnHeader" /></center></div>
						<DIV id="resultHeader">
						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">

							<tbody>
								<tr>
									<td>
									<DIV id="panel2Header" class="tabTitleBar">
									 <STRONG><h:outputText value="Associated Benefit Definitions"></h:outputText></STRONG></DIV>
									</td>
								</tr>

								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader" style="height:20">
												<td align="left"><h:outputText
													value="Description"></h:outputText></td>
												<td align="left"><h:outputText
													value="Effective Date"></h:outputText></td>
												<td align="left"><h:outputText
													value="Expiry Date"></h:outputText></td>
												<td align="left"><h:outputText
													value="Tier Definition"></h:outputText></td>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:242px; overflow: auto;"><!-- Search Result Data Table -->
									<h:dataTable
										rendered="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList != null}"
										rowClasses="dataTableEvenRow,dataTableOddRow"
										bgcolor="#cccccc" styleClass="outputText"
										headerClass="dataTableHeader" id="benefitDefinitionsTable"
										var="singleValue" cellpadding="3" width="100%" cellspacing="1"
										value="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList}">

										<h:column>
											<h:outputText id="desc" value="#{singleValue.description}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="effDate"
												value="#{singleValue.effectiveDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="expDate" value="#{singleValue.expiryDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="tier" value="#{singleValue.tierDefinitions}"> 												
											</h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</tbody>

						</TABLE>
					</DIV>
						</FIELDSET>
						<BR>
						<br>

						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<TABLE align="right" border="0" cellspacing="0" cellpadding="0"
							width="100%">

							<TR>
								<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>

								<TD align="left" width="127">
								<TABLE width="100%">
									<TR>
										<TD>State</TD>
										<td><h:outputText value=":" /></td>
										<TD><h:outputText id="state"
											value="#{benefitDefinitionBackingBean.state}"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD>Status</TD>
										<td><h:outputText value=":" /></td>
										<TD><h:outputText id="status"
											value="#{benefitDefinitionBackingBean.status}"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD>Version</TD>
										<td><h:outputText value=":" /></td>
										<TD><h:outputText id="version"
											value="#{benefitDefinitionBackingBean.version}"></h:outputText>
										</TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</TABLE>
						</FIELDSET>
						</TD>
					</TR>
				</TABLE>
				<!--	End of Page data	-->

				<!-- Space for hidden fields -->
				<h:inputHidden id="tabHidden"
					value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
				<!-- End of hidden fields  -->

			</h:form></TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
		</TR>

	</TABLE>
	</BODY>
</f:view>
<script language="javascript">
		var tableobject = document.getElementById('BenefitDefinitionViewForm:benefitDefinitionsTable');
		if(tableobject!=null){
			var msgDivObj = document.getElementById('emptymsg');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
		}else{
			var divObj = document.getElementById('resultHeader');
			divObj.style.visibility = "hidden";
			divObj.style.height = "2px";
		}
				
		if(tableobject!=null){
		var rowlength = document.getElementById('BenefitDefinitionViewForm:benefitDefinitionsTable').rows.length;
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		if(rowlength>1){
			document.getElementById('BenefitDefinitionViewForm:benefitDefinitionsTable').width = relTblWidth+"px";
			syncTables('resultHeaderTable','BenefitDefinitionViewForm:benefitDefinitionsTable');
			setColumnWidth('BenefitDefinitionViewForm:benefitDefinitionsTable','40%:20%:20%:20%');
			setColumnWidth('resultHeaderTable','40%:20%:20%:20%');
		}else{
			setColumnWidth('BenefitDefinitionViewForm:benefitDefinitionsTable','40%:20%:20%:20%');
			setColumnWidth('resultHeaderTable','40%:20%:20%:20%');
		}
		}
		
			// syncTables('resultHeaderTable','BenefitDefinitionViewForm:benefitDefinitionsTable');
		// setColumnWidth('BenefitDefinitionViewForm:benefitDefinitionsTable','50%:25%:25%');
hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("BenefitDefinitionViewForm:tabHidden").value ;
	if(tab=="Standard Definition"){
		manInfo.style.display='none';
		noteTab.style.display='';
	}
	else{
		manInfo.style.display='';
		noteTab.style.display='none';
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="standardBenefitDefinition" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>










