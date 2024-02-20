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
<META http-equiv="Content-Style-Type" content="text/css">
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

.gridColumn4{
	width: 4%;
	text-align:left;
}
.gridColumn17{
	width: 17%;
	text-align:left;
}
.gridColumn12{
	width: 12%;
	text-align:left;
}
.gridColumn11{
	width: 11%;
	text-align:left;
}
.gridColumn18{
	width: 18%;
	text-align:left;
}
.gridColumn9{
	width: 9%;
	text-align:left;
}
.gridColumn8{
	width: 8%;
	text-align:left;
}
.gridColumn6{
	width: 6%;
	text-align:left;
}
.gridColumn14{
	width: 14%;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	text-align:left;
}
.gridColumn16{
	width: 16%;
	text-align:left;
}
.gridColumn75{
	width: 75%;
	text-align:left;
}
.gridColumnRight25{
	width: 25%;
	text-align:right;
}
.gridColumn25{
	width: 25%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn20{
	width: 20%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn3{
	width: 3%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn5{
	width: 5%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 </STYLE>
<base target=_self>
<TITLE>productBenefitDefinitionView.jsp</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:inputHidden id="Hidden"
		value="#{productBenefitDetailBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitDefinitionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">

						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200" id="tab1">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitGeneralInfoBackingBean.getProductBenefitGenaralInfo}">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="tab2">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Standard Definition" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="tabmandate">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Mandate Definition" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="tab3">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitMandateBackingBean.loadMandateInfo}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="tab4">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{productBenefitNoteBackingBean.loadNotes}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200"></td>
								<td width="200"></td>
							</tr>
						</table>
						<fieldset
							style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:100%">
						<table width="100%" border="0" bordercolor="green" cellspacing="0"
							cellpadding="0">
							<tr>
								<td valign="top" class="ContentArea"><!-- <fieldset> -->
								<DIV id="noBenefitDefinitions"
									style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No
								Benefit Definitions available.</DIV>
								<DIV id="benefitDefinitionTable">
								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0" bordercolor="red">

									

									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<TABLE width="70%" cellpadding="0" align="left" border="0"
											id="tabheader" class="smallfont">
											
											<TR bgcolor="#cccccc">
										<TD width="70%" bgcolor="#CCCCCC"><SPAN id="stateCodeStar"> <STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></SPAN>
										</TD>
									</TR>

										<TR>
												
												<TD align="left" valign="top">
												<DIV style="position:relative;top:0px;left:0px" align="left">
												<DIV id="resultHeaderDiv" align="left"
													style="position:relative;background-color:#FFFFFF;z-index:1;">
												<h:panelGrid id="resultHeaderTable"
													binding="#{productBenefitDetailBackingBean.headerPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>

												<DIV id="panelContent"
													style="position:relative;background-color:#FFFFFF;border:0px solid #CCCCCC;">
												<h:panelGrid id="panelTable"
													binding="#{productBenefitDetailBackingBean.panel}">
												</h:panelGrid></DIV>
												</TD>
											</TR>
											<TR>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD>&nbsp;</TD>
											</TR>

											<TR id="tierHeadTR" bgcolor="#cccccc">
												<TD colspan="1" bgcolor="#CCCCCC"><SPAN id="stateCodeStar">
												<STRONG><h:outputText
													value="Associated Tiered Benefit Levels" /></STRONG></SPAN></TD>
											</TR>
											<TR id="tierDataTR">
												<TD valign="top">
												<DIV id="resultHeaderDiv1"
													style="position:relative;background-color:#FFFFFF;"><h:panelGrid
													id="resultHeaderTable1"
													binding="#{productBenefitDetailBackingBean.tierHeaderPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>
												<DIV id="panelContent1"
													style="position:relative;background-color:#FFFFFF;solid #CCCCCC;">
												<h:panelGrid id="panelTable1"
													binding="#{productBenefitDetailBackingBean.tierPanel}">
												</h:panelGrid>
												</DIV>
												</TD>
											</TR>
											<TR>
												<TD>&nbsp;</TD>
											</TR>
										</TABLE>
										</TD>
									</TR>
								</TABLE>
								</DIV>
								<!-- </fieldset> --> <h:inputHidden id="hidden1" value="value1"></h:inputHidden>
								<div id="dummyDiv"></div>
								</td>
							</tr>
						</table>
						</fieldset>
						</TD>
					</TR>
				</table>
				<h:inputHidden id="levelsToDeleteHidden"
					value="#{productBenefitDetailBackingBean.levelsToDelete}" />
				<h:inputHidden id="hiddenProductType"
					value="#{productBenefitDetailBackingBean.productType}"></h:inputHidden>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>

	</BODY>
</f:view>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitDefinitionPrint" /></form>
<script>

var tableObjectTier = document.getElementById('benefitDefinitionForm:panelTable1');
if(tableObjectTier.rows.length == 0){ 
document.getElementById('tierHeadTR').style.display = 'none';
document.getElementById('tierDataTR').style.display = 'none';
}

var relTblWidth = document.getElementById('benefitDefinitionForm:resultHeaderTable').offsetWidth;
if(document.getElementById('benefitDefinitionForm:panelTable').offsetHeight <= 250)
{
	syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
	document.getElementById('benefitDefinitionForm:panelTable').width = relTblWidth+"px";
	document.getElementById('benefitDefinitionForm:resultHeaderTable').width = relTblWidth+"px";
	setColumnWidth('benefitDefinitionForm:resultHeaderTable','16%:12%:16%:6%:8%:8%:17%:5%');
	setColumnWidth('benefitDefinitionForm:panelTable','16%:12%:16%:6%:8%:8%:17%:5%');
	syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
}
else
{
	syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
	document.getElementById('benefitDefinitionForm:panelTable').width = (relTblWidth-17)+"px";
	document.getElementById('benefitDefinitionForm:resultHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('benefitDefinitionForm:panelTable','16%:12%:16%:6%:8%:8%:17%:5%');
	setColumnWidth('benefitDefinitionForm:resultHeaderTable','16%:12%:16%:6%:8%:8%:17%:5%');
	syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
}


//hides the header panel when no data is present
var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
if(tableObject.rows.length > 0){
	var divBnftDefn = document.getElementById('noBenefitDefinitions');
	divBnftDefn.style.visibility = "hidden";
	divBnftDefn.style.height = "2px";
}else{
	var divObj = document.getElementById('benefitDefinitionTable');
	divObj.style.visibility = "hidden";
	divObj.style.height = "2px";
	document.getElementById('noBenefitDefinitions').style.visibility = "visible";
}
var primaryId;
var linesysid;
var bnftLineId;
var primaryType = 'ATTACHPRODUCT';
var secType='ATTACHBNFTLINE'
var	primaryId;
function getUrlAssigned(linesysid,compId,primaryId){
	ewpdModalWindow_ewpd('../notes/benefitLineAssociatedNotesViewPopUp.jsp'+getUrl()+'?primaryId='+primaryId+'&primaryType='+primaryType+'&bnftLineId='+linesysid+'&secType='+secType+'&benefitComponentId='+compId+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);
	return false;
}

i = document.getElementById("benefitDefinitionForm:hiddenProductType").value;
if(i=='MANDATE')
{
tab4.style.display='none';
tab2.style.display='none';
tab3.style.display='';
tabmandate.style.display='';
}else{
tab4.style.display='';
tab2.style.display='';
tab3.style.display='none';
tabmandate.style.display='none';
}

function getUrlForTier(linesysid, j, i,tierSysId){
		var retValue = ewpdModalWindow_ewpd('../notes/benefitLineAssociatedNotesViewPopUp.jsp'+getUrl()+'?primaryId='+i+'&primaryType='+primaryType+'&bnftLineId='+linesysid+'&secType='+secType+'&benefitComponentId='+j+'&tierSysId='+tierSysId+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);
		var imageObj = document.getElementById('benefitDefinitionForm:notesButton'+j+'_'+i+tierSysId);
		var divObj = document.getElementById('dummyDiv');
		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	
		//document.getElementById('benefitDefinitionForm:hiddenNotesStatus' + j + '_' + i).value = divObj.innerHTML;
		divObj.innerHTML = '';
		return false;
	}
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
