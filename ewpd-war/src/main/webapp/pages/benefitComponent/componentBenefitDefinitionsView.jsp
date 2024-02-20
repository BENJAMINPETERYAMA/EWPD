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
<script language="JavaScript" src="../../js/prototype.js"></script>
<TITLE>Benefit Definitions View</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<h:form styleClass="form" id="componentBenefitDefinitionView">
		<h:inputHidden id="hiddenViewMode"	value="#{ComponentBenefitDefinitionsBackingBean.dummyVar}"></h:inputHidden>
			<table width="100%" cellpadding="0" border="0" cellspacing="0">
				<TR>
					<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
				</TR>
				<tr>
					<TD>
					<TABLE width="90%" height="90%">
						<tr>
							<!-- Space for Tree  Data	-->
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><jsp:include
								page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>
							</DIV>
							</TD>

							<TD valign="top" class="ContentArea"><!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{benefitComponentCreateBackingBean.loadComponentBenefitforView}">
												<h:outputText value="General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<h:inputHidden id="hiddenTabValue"
										value="#{benefitComponentBackingBean.componentTypeTab}"></h:inputHidden>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="#{benefitComponentBackingBean.componentTypeTab}" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="200" id="sbMandateInfoTab">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{benefitMandateBackingBean.loadMandateInformationForView}">
												<h:outputText value="Mandate Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="200" id="sbOverrideNotesTab">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{BenefitComponentNotesBackingBean.loadStandardBenefitNotesView}">
												<h:outputText value="Notes" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="60"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:660;height:500">
							<DIV id="messageTextForNoBenefitLevelsDiv" align="center"><BR>
							<CENTER><BR>
							<STRONG>&nbsp;<h:outputText value="No Benefit Level available." /></STRONG>
							</CENTER>
							</DIV>
							<DIV id="associatedBenefitspanelHeader"
								style="overflow-y:scroll=no;width=100%">
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTable" border="0" width="100%">
								<TBODY>
									<TR bgcolor="#cccccc">
										<TD bgcolor="#CCCCCC" height="23px" colspan="3"><B><h:outputText
											value="Associated Benefit Lines" /></B></TD>
									</TR>
									<TR class="dataTableColumnHeader">
										<TD align="left"><h:outputText value="Description" /></TD>
										<TD align="left"><h:outputText value="Term" /></TD>
										<TD align="left"><h:outputText value="Frequency - Qualifier" /></TD>
										<TD align="left"><h:outputText value="PVA" /></TD>
										<TD align="left"><h:outputText value="Format" /></TD>
										<TD align="left"><h:outputText value="Benefit Value" /></TD>
										<TD align="left"><h:outputText value="Reference" /></TD>
										<TD width="0"></TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							<DIV id="associatedBenefitspanel"
								style="height:360px;overflow-y:auto;width=100%"><h:dataTable
								headerClass="tableHeader" id="panelTable" border="0"
								width="100%"
								value="#{ComponentBenefitDefinitionsBackingBean.benefitLevelsListForView}"
								var="eachRow" cellpadding="0" cellspacing="1" bgcolor="#cccccc"
								rowClasses="dataTableOddRow,dataTableEvenRow">
								<h:column>
									<h:inputHidden id="benefitLineIdHidden"
										value="#{eachRow.benefitLineId}" />
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.description}" />
								</h:column>

								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.term}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.qualifier}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.pva}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.format}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.benefitValue}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.reference}" />
								</h:column>
								<h:column>
									<h:outputText value=" " id="a11spaceSpan" rendered="false" />
									<h:commandButton alt="notes" id="scheduleToProductionButton"
										image="#{eachRow.notesExist?'../../images/notes_exist.gif':'../../images/page.gif'}"
										onclick="notesAction();return false;"
										rendered="#{eachRow.renderNotesAttachmentImage}" />
								</h:column>
							</h:dataTable> <!-- Space for hidden fields --> 
							<h:inputHidden	id="selectedBenefitLineId"  /> <h:inputHidden
								id="bencompHidden"
								value="#{ComponentBenefitDefinitionsBackingBean.benefitComponent}"></h:inputHidden>
							<h:inputHidden id="hidden1" value="value1"></h:inputHidden> <h:inputHidden
								id="displayHeaderPanel"
								value="#{ComponentBenefitDefinitionsBackingBean.headerDisplay}" />
							<h:commandLink id="hiddenLnk1"
								style="display:none; visibility: hidden;">
							</h:commandLink> <!-- End of hidden fields  --></DIV>
							</FIELDSET>
							<DIV id="dummyDiv"></DIV>

							</TD>
						</tr>
					</TABLE>
					</TD>

				</tr>


				<TR>
					<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
				</TR>
			</table>
		</h:form>


	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
var panelTableObject = document.getElementById('componentBenefitDefinitionView:panelTable');
		if(panelTableObject.rows.length > 0){
			var panelDivObj = document.getElementById('messageTextForNoBenefitLevelsDiv');
			panelDivObj.style.visibility = "hidden";
			panelDivObj.style.height = "0px";
			if(document.getElementById('componentBenefitDefinitionView:panelTable').rows.length < 10) {
						setColumnWidth('resultHeaderTable','19%:13%:19%:6%:9%:9%:21%:4%');	
						setColumnWidth('componentBenefitDefinitionView:panelTable','19%:13%:19%:6%:9%:9%:21%:4%');
			}else{
						setColumnWidth('resultHeaderTable','19%:13%:19%:6%:9%:9%:21%:4%');
						setColumnWidth('componentBenefitDefinitionView:panelTable','19%:13%:19%:6%:9%:9%:21%:4%');
			}
		}else{
			var msgDivObj = document.getElementById('componentBenefitDefinitionView:panelTable');
			var object = document.getElementById('associatedBenefitspanelHeader');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
			object.style.visibility = "hidden";
		}

function getUrlAssigned(linesysid){
	ewpdModalWindow_ewpd('../contract/contractCoverageBenefitLevelNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHCOMP&lookUpAction=4&secondaryEntityId='+linesysid+'&temp='+Math.random(),'dummyDiv','componentBenefitDefinitionView:hidden1',2,1);	
	return false;
}


function notesAction(){	
	getFromDataTableToHidden('componentBenefitDefinitionView:panelTable','benefitLineIdHidden','componentBenefitDefinitionView:selectedBenefitLineId');						
	var benefitLineId =  document.getElementById('componentBenefitDefinitionView:selectedBenefitLineId').value;
	var benefitComponentId=document.getElementById('componentBenefitDefinitionView:bencompHidden').value;
	var primaryType = 'ATTACHCOMP';
	var secType='ATTACHBNFTLINE';
			
	var url = '../notes/benefitLineAssociatedNotesViewPopUp.jsp'+getUrl()+'?primaryId='+benefitComponentId+'&primaryType='+primaryType+'&bnftLineId='+benefitLineId+'&secType='+secType+'&benefitComponentId='+benefitComponentId;
	newWinForView=window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:400px;dialogWidth:500px;resizable=true;status=no;");		
}

hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("componentBenefitDefinitionView:hiddenTabValue").value;

	if(tab=="Standard Definition"){
		//viewButton.style.display = '' ;
		sbOverrideNotesTab.style.display = '';		
		sbMandateInfoTab.style.display = 'none';
	}
	else{		
		//viewButton.style.display = 'none' ;
		sbOverrideNotesTab.style.display = 'none';	
		sbMandateInfoTab.style.display = '';	
	}
}
	if(document.getElementById('componentBenefitDefinitionView:panelTable') != null){
	var tableWidth = document.getElementById('componentBenefitDefinitionView:panelTable').offsetWidth;
		if(tableWidth>0){
			document.getElementById('resultHeaderTable').width = tableWidth;
	
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="componentBenefitDefinitions" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
