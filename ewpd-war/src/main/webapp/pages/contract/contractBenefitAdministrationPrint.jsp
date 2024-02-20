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
<STYLE type="text/css">  
.gridColumn40{
	width: 40%;				
}
.gridColumn20{
	width: 20%;		
}
.gridColumn25{
	width: 25%;		
}
.gridColumn15{
	width: 15%;		
}
.gridColumn30{
	width: 30%;		
}
.gridColumn10{
	width: 10%;		
}
 </STYLE>
<TITLE>Contract Benefit Administration View</TITLE>
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
			<td>&nbsp;</td>
		</tr>
		<TR>
			<TD>
			<FIELDSET
				style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">

			<h:outputText id="breadcrumb"
				value="#{contractBasicInfoBackingBean.breadCrumpText}">

			</h:outputText></FIELDSET>


			</TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
			<!--  WAS 7.0 Changes - Hidden id loadPrintPage value loaded using binding instead of value -->
				<h:inputHidden id="printPageLoad"
					binding="#{contractBenefitAdministrationBackingBean.loadPrintPage}"></h:inputHidden>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD colspan="2" valign="top" class="ContentArea">



						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						 <!--	Start of Table for actual Data	-->
						<BR><BR>
						<div id="resultInfo"><h:outputText
							value="No Benefit Administration Available."
							styleClass="dataTableColumnHeader" /></div>
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
									<div id="LabelHeaderDiv2"
										style="background-color:#cccccc;z-index:1;height:20px;width:100%">
									<B> <h:outputText value="Associated Questionnaire "></h:outputText> </B>
									</div>
									<div id="displayHeaderDiv"
										style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
										id="displayHeaderTable"
										binding="#{contractBenefitAdministrationBackingBean.headerPanelForPrint}"
										rowClasses="dataTableOddRow">
									</h:panelGrid></div>
									<DIV id="resultHeaderDiv">
									<div id="displayPanelContent12"
										style="position:relative;"><h:panelGrid
										id="panelTable"
										binding="#{contractBenefitAdministrationBackingBean.viewPanel}"
										rowClasses="dataTableOddRow">
									</h:panelGrid></div>
									<tr style="height:20px;">
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td valign="middle">										
										<div id="LabelTierHeaderDiv2"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
										<B>&nbsp;<h:outputText value="Associated Tiered Questionnaire "></h:outputText> </B>
										</div>
										<div id="displayTierHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;width:100%"><h:panelGrid
											id="displayTierHeaderTable" width="100%"
											binding="#{contractBenefitAdministrationBackingBean.tierHeaderPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></div>
										<div id="displayPanelContentTier"
											style="position:relative;width:100%">
										<h:panelGrid id="panelTierTable" width="100%"
											binding="#{contractBenefitAdministrationBackingBean.tierQuestionarePanel}"
											>
										</h:panelGrid></div>
										</td>


									</TR>

									</DIV>
									</td>
								</TR>
								<TR>
									<td><BR>
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
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->
				<h:inputHidden id="renderFlag"
					value="#{contractBenefitAdministrationBackingBean.renderFlagForPanel}" />
				<h:inputHidden id="editModeId"
								value="#{contractBenefitAdministrationBackingBean.editMode}"></h:inputHidden>
			</h:form></td>
		</tr>

	</table>
	</BODY>
</f:view>

<script language="JavaScript">
if(document.getElementById('benefitAdmnForm:renderFlag').value=='true'){
			var divObj = document.getElementById('resultInfo');
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		// document.getElementById('resultInfo').style.visibility = 'hidden';
				setColumnWidth('benefitAdmnForm:panelTable','40%:20%:25%:15%');
				setColumnWidth('benefitAdmnForm:displayHeaderTable','40%:20%:25%:15%');
	}
		else{
			document.getElementById('resultInfo').style.visibility = 'visible';
			
			document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
			
		}


	window.print();

	hideIfNoTierData();
	function hideIfNoTierData(){
		if(null!=document.getElementById("benefitAdmnForm:panelTierTable")){
			var noOfRows = document.getElementById('benefitAdmnForm:panelTierTable').rows.length ;
			if(noOfRows ==0){
			document.getElementById('LabelTierHeaderDiv2').style.visibility = 'hidden';
			document.getElementById('displayTierHeaderDiv').style.visibility = 'hidden';
			document.getElementById('displayPanelContentTier').style.visibility = 'hidden';
			}
			else{
			document.getElementById('LabelTierHeaderDiv2').style.visibility = 'visible';
			document.getElementById('displayTierHeaderDiv').style.visibility = 'visible';
			document.getElementById('displayPanelContentTier').style.visibility = 'visible';
			}
		}
	}
</script>

</HTML>
