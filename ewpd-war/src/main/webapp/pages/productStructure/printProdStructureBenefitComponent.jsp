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

<TITLE>Associated Benefit Component</TITLE>
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
			<td><h:form styleClass="form" id="viewProdStructureForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<h:inputHidden id="getValue"
							value="#{productStructureBenefitComponentBackingBean.values}"></h:inputHidden>
						<TD colspan="2" valign="top" class="ContentArea"><!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">

		   <TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{productStructureBenefitComponentBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
                  <TR>
                        <TD>
                              &nbsp;
                        </TD>
                  </TR>
							
							<tr>
								<td colspan="2" valign="top" class="ContentArea">
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:71%">

								<div id="panel2Header" class="tabTitleBar" style="position:relative;width:100% "><strong>Associated
								Benefit Components</strong></div>
								<BR />
								<h:outputText value="No Benefit Components Available."
									rendered="#{productStructureBenefitComponentBackingBean.selectBenefitComponents == null}"
									/>
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
											cellpadding="1" cellspacing="1">
											<tr class="dataTableOddRow">
												<td><strong><h:outputText value="Sequence Number" /></strong></td>
												<td><strong><h:outputText value="Benefit Component" /></strong></td>
											</tr>
										</TABLE>
										</div>
										</td>
									</tr>
									<tr>
										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv" style="overflow: auto;"><h:dataTable
											cellspacing="1" id="bComponentDataTable"
											rendered="#{productStructureBenefitComponentBackingBean.selectBenefitComponents != null}"
											value="#{productStructureBenefitComponentBackingBean.selectBenefitComponents}"
											rowClasses="dataTableOddRow" var="singleValue"
											cellpadding="3" width="100%">
											<h:column>
												<h:outputText id="id" value="#{singleValue.sequenceNum}" />
											</h:column>
											<h:column>
												<h:outputText id="name" value="#{singleValue.name}"></h:outputText>
												<h:inputHidden id="benefitComponentName"
													value="#{singleValue.name}"></h:inputHidden>
											</h:column>
										</h:dataTable></div>

										</td>
									</tr>
									<tr>
										<TD></TD>
									</tr>
									<tr>
										<TD></TD>
									</tr>

								</table>



								</fieldset>
								<br />
								<FIELDSET
									style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:71%">
								<TABLE align="right" border="0" cellspacing="0" cellpadding="0"
									width="100%">
									<tr>
										<td align="right">
										<table>
											
											<TR>
												<TD>State</TD>
												<TD>:<h:outputText id="state"
													value="#{productStructureBenefitComponentBackingBean.state}"></h:outputText>
												</TD>
											</TR>
											<TR>
												<TD>Status</TD>
												<TD>:<h:outputText id="status"
													value="#{productStructureBenefitComponentBackingBean.status}"></h:outputText>
												</TD>
											</TR>
											<TR>
												<TD>Version</TD>
												<TD>:<h:outputText id="version"
													value="#{productStructureBenefitComponentBackingBean.version}"></h:outputText>
												</TD>
											</TR>
										</table>
										</td>
									</tr>
								</TABLE>
								</FIELDSET>
								</td>
							</tr>
						</table>
						<!--	End of Page data	--></TD>
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
	</table>
	</BODY>
</f:view>

<script>
initialize();
function initialize(){
	if(document.getElementById('viewProdStructureForm:bComponentDataTable') != null) {

		setColumnWidth('headerTable','21%:51%');		
		setColumnWidth('viewProdStructureForm:bComponentDataTable','21%:51%');
	}else {
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
	}
}
</script>
<script>window.print();</script>
</HTML>
