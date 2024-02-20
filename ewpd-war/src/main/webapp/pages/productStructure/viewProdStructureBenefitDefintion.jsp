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
<TITLE>Product Structure Benefit Definition</TITLE>
<BASE target="_self" />
</HEAD>

<f:view>
	<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitDefinitionForm">
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
											action="#{productStructureBenefitDefenitionBackingBean.viewBenefitDefenition}"
											id="linkToGeneralInfo">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive"><h:outputText
											value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							<td id = "notesTab" width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{productStructureBenefitDefenitionBackingBean.loadStandardBenefitNotes}"
												id="linkToNotes">
												<h:outputText
													value="Notes" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								<%-- <td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:outputText
											value="Adj Benefit Mandates" /></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>--%>
								<td id="mandTab" width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productStructureBenefitMandatesBackingBean.retrieveMandates}"
											id="linkToNonAdjMan">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<%--<h:outputText value="No Benefit Components Available." 
								rendered="#{productStructureBenefitDefenitionBackingBean.benefitDefinitonsList == null}" 
								styleClass="dataTableColumnHeader"/>
					DIV id="benefitDefinitionDiv"> --%>
						<table width="100%" border="0" bordercolor="green" cellspacing="0"
							cellpadding="0">
							<tr>
								<td valign="top" class="ContentArea">
								
								<div id="defnDiv"><h:outputText
									value="No Benefit Definitions Available."
									styleClass="dataTableColumnHeader" /></div>
								<div id="benftDefnDiv">
								<TABLE class="smallfont" id="resultsTable" cellpadding="3" cellspacing="1" border="0" bordercolor="red">
									<TR bgcolor="#cccccc">
										<TD colspan="1" bgcolor="#CCCCCC" width="704"><SPAN id="stateCodeStar"><STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></SPAN></TD>
									</TR>
									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
												<DIV style="position:relative;top:0px;left:0px" align="left">
												<DIV id="resultHeaderDiv" align="left"
													style="position:relative;background-color:#FFFFFF;z-index:1;overflow:scroll;hieght:35px;"><h:panelGrid
													id="resultHeaderTable"
													binding="#{productStructureBenefitDefenitionBackingBean.headerPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>

												<DIV id="panelContent"
													style="position:relative;background-color:#FFFFFF;height:400;overflow:auto; ">
												<h:panelGrid id="panelTable"
													binding="#{productStructureBenefitDefenitionBackingBean.panel}">
												</h:panelGrid></DIV>
												</DIV>
										</TD>
									</TR>
								</TABLE>
								</div>
								<!--	Start of Table for actual Data	-->

								<div id="selectDiv" style="height:150;width:400px; padding:5px;"></div>
								</td>
							</tr>
						</table>
						<%--/DIV> --%> <!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:inputHidden id="benTypeHidden"
					value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
				<h:inputHidden id="levelsToDeleteHidden"
					value="#{productStructureBenefitDefenitionBackingBean.levelsToDelete}" />
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />

					<!-- End of hidden fields  -->
				</h:commandLink>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="javascript">
		initialize();
		function initialize(){
			if(document.getElementById('benefitDefinitionForm:panelTable') != null) {

				var relTblWidth = document.getElementById('benefitDefinitionForm:resultHeaderTable').offsetWidth;
				document.getElementById('defnDiv').style.visibility = 'hidden';
			if(document.getElementById('benefitDefinitionForm:panelTable').rows.length < 13) {
				//document.getElementById('benefitDefinitionForm:panelTable').width = relTblWidth+"px";
				setColumnWidth('benefitDefinitionForm:resultHeaderTable','18%:15%:16%:6%:9%:8%:20%:6%');	
				setColumnWidth('benefitDefinitionForm:panelTable','18%:15%:16%:6%:9%:8%:20%:6%');
			syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
			}else{
			var relTblWidth = document.getElementById('benefitDefinitionForm:panelTable').offsetWidth;
			var headerWidth = relTblWidth - 12;
			document.getElementById('benefitDefinitionForm:resultHeaderTable').width = headerWidth+"px";
			setColumnWidth('benefitDefinitionForm:resultHeaderTable','18%:13%:18%:6%:9%:9%:21%:6%');	
			setColumnWidth('benefitDefinitionForm:panelTable','18%:13%:18%:6%:9%:9%:21%:6%');
			syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
			}

			}else {
				document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('panelContent').style.height = '1px';
				document.getElementById('panelContent').style.visibility = 'hidden';
			}
		}
			
			if(document.getElementById('benefitDefinitionForm:panelTable') != null){
				//setColumnWidth('benefitDefinitionForm:panelTable','18%:15%:16%:6%:9%:8%:20%:6%');
				//setColumnWidth('benefitDefinitionForm:resultHeaderTable','18%:15%:16%:6%:9%:8%:20%:6%');
				document.getElementById('defnDiv').style.visibility = 'hidden';
				var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
				/* if(tableObject.rows.length > 0){
				}else{
					var divObj = document.getElementById('benefitDefinitionTable');
					divObj.style.visibility = "hidden";
					divObj.style.height = "2px";
				} */	
			}else{
				document.getElementById('benftDefnDiv').style.visibility = 'hidden';
				document.getElementById('benftDefnDiv').style.height = '1px';
			}
	displayMandateTab();
	function displayMandateTab(){
	var benType = document.getElementById('benefitDefinitionForm:benTypeHidden').value;
	if(benType=="Mandate Definition"){
		mandTab.style.display='';
		notesTab.style.display='none';
	}
	else{
		mandTab.style.display='none';
	}
}
function getUrlAssigned(linesysid,benefitComponentId){
	ewpdModalWindow_ewpd('../productStructure/productStrucureBenefitLevelNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHCOMP&lookUpAction=4&secondaryEntityId='+linesysid+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);	
	return false;
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitDefinition" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
