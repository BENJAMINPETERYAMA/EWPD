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
		<h:inputHidden id="hidden1" binding="#{contractProductAdminOptionOverrideBackingBean.hiddenView}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
						</DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="600" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>

								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive"><h:commandLink>
											<h:outputText value="Administration" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <BR>
						<div id="adminDiv"><h:outputText
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

									<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
									<B> <h:outputText value="Associated Questionnaire"></h:outputText>
									</B></div>
									<div id="displayHeaderDiv"
										style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
										id="displayHeaderTable"
										binding="#{contractProductAdminOptionOverrideBackingBean.headerPanel}"
										rowClasses="dataTableOddRow,dataTableEvenRow">
									</h:panelGrid></div>
									<div id="displayPanelContent12"
										style="position:relative;overflow:auto;height:200px;"><h:panelGrid
										id="panelTable"
										binding="#{contractProductAdminOptionOverrideBackingBean.panel}">
									</h:panelGrid></div>

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
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
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

<script language="JavaScript">

initialize();
		function initialize(){
			if(document.getElementById('benefitAdmnForm:panelTable') != null) {
				if(document.getElementById('benefitAdmnForm:panelTable').offsetHeight <= 200) {	
					setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
					setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');

				}else{

					//document.getElementById('resultHeaderTable').width = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
					document.getElementById('benefitAdmnForm:displayHeaderTable').width = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
					document.getElementById('LabelHeaderDiv').style.width = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
					setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
					setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');


				}
				document.getElementById('adminDiv').style.visibility = 'hidden';
			}else {
				document.getElementById('benefitAdministrationDiv').style.visibility = 'hidden';
			}
		}
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId){

var secondaryEntityType="ATTACHADMNQUEST";
var retValue = window.showModalDialog(popupName + "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType, 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
}
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractProductAdmin" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
