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

<TITLE>Contract Product Administration Print</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</HEAD>

<f:view>
	<BODY>
	<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
		<h:inputHidden id="hidden1" binding="#{contractProductAdminOptionOverrideBackingBean.hiddenInit}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>	
			
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<tr><td>&nbsp; </td></tr>
						<TR>
							<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">
		
		                                    <h:outputText id="breadcrumb" 
		
		                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">
		
		                                    </h:outputText>
		
		                              </FIELDSET>
		
		
							</TD>
						</TR>
						<TR>
							<td valign="top" class="ContentArea">


							<!-- End of Tab table -->	
							<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <BR>

						<div id="resultInfo">
						<h:outputText value="No Questionnaire Available." 
											styleClass="dataTableColumnHeader"/>
						</div>
						<DIV id="benefitAdministrationDiv">
						<TABLE border="0" cellspacing="0" cellpadding="0" width="100%"
							class="outputText">
							<TBODY>
								<TR>
									<td></td>
								</TR>
								<TR>
									<td valign="middle">
									<DIV id="resultHeaderDiv">
									<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;height:15px;width:100%">
									<B>&nbsp;<h:outputText value="Associated Questionnaire"></h:outputText> </B>
									</div>
									<div id="displayHeaderDiv"
										style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
										id="displayHeaderTable"
										binding="#{contractProductAdminOptionOverrideBackingBean.headerPanelForPrint}"
										rowClasses="dataTableOddRow" cellpadding="1" cellspacing="1">
									</h:panelGrid></div>
								
									<div id="displayPanelContent12"
										style="position:relative;height:200px;"><h:panelGrid
										id="panelTable"
										binding="#{contractProductAdminOptionOverrideBackingBean.panelForprint}"
										rowClasses="dataTableOddRow" cellpadding="1" cellspacing="1">
									</h:panelGrid></div>
									</DIV>
									</td>
								</TR>
								<TR>
									<td><%-- div style="position:relative;  background-color:#ffffff;overflow:auto;">
											<div id="displayPanelContent12" style="position:relative;">
												<h:panelGrid id="panelTable"
													binding="#{productStructureBenefitAdministrationBackingBean.panel}"
													rowClasses="dataTableEvenRow,dataTableOddRow">
												</h:panelGrid> 
											</div>
										</div--%><BR>
									</td>
								</TR>
								<TR>
								</TR>
								<TR>
								</TR>
							</TBODY>
						</TABLE>
						</DIV>
						<!--	End of Page data	--></fieldset>
				</TABLE></td>
		</tr>
		<!-- Space for hidden fields -->
		<h:inputHidden id="renderFlag" value= "#{contractProductAdminOptionOverrideBackingBean.renderFlagForPanel}"/> 
		
		<!-- End of hidden fields -->

</h:form> 
	</table>
	</BODY>

</f:view>
<script language="javascript">
	var tableObject = document.getElementById('benefitAdmnForm:panelTable');

	if(tableObject != null){
		if(tableObject.rows.length > 0){
			var divObj = document.getElementById('resultInfo');
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';		
			setColumnWidth('benefitAdmnForm:panelTable','40%:20%:25%:15%');
			setColumnWidth('benefitAdmnForm:displayHeaderTable','40%:20%:25%:15%');
		}
		else{
			document.getElementById('resultInfo').style.visibility = 'visible';		
			document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		}	
	}
	else{
			document.getElementById('resultInfo').style.visibility = 'visible';		
			document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	}
	window.print();
</script>
</HTML>
