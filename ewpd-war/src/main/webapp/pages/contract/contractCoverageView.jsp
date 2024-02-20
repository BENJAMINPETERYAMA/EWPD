<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractCoverageView.java" --%><%-- /jsf:pagecode --%>
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
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<STYLE type="text/css">
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
	text-align:left;
}
.gridColumn22{
	width: 22%;
	text-align:left;
}
.gridColumn20{
	width: 20%;
	text-align:left;
}
.gridColumn19{
	width: 19%;
	text-align:left;
}
.gridColumn18{
	width: 18%;
	text-align:left;
}
.gridColumn17{
	width: 17%;
	text-align:left;
}
.gridColumn16{
	width: 16%;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	text-align:left;
}
.gridColumn14{
	width: 14%;
	text-align:left;
}
.gridColumn12{
	width: 12%;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	text-align:left;
}
.gridColumn8{
	width: 8%;
	text-align:left;
}
.gridColumn7{
	width: 7%;
	text-align:left;
}
 .gridColumn3{
	width: 3%;
	text-align:left;
}
 .gridColumn5{
	width: 5%;
	text-align:left;
}
</style>
<TITLE>contractCoverageView.jsp</TITLE>
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
<script>

//validates the benefit value fields on the click of save
function validate() {
	var objList = document.getElementsByTagName('input');
	var txtBox;
	for(var i=0; i<objList.length; i++) {
		txtBox = objList[i];
		
		//checks for benefit values with data type $
		if(txtBox.type == 'text' && txtBox.title == 'BenefitValue$') {
			if(isNaN(+txtBox.value)){
				alert("Benefit Value should be a Number.");
				document.getElementById(txtBox.id).focus();
				return false;
			}
			else{
				if(txtBox.value < 0){
					alert("Benefit Value with data type $ should be greater than or equal to 0.");
					document.getElementById(txtBox.id).focus();
					return false;
				}
				if(trim(txtBox.value) == '') {
					txtBox.value = 0;
				}
			}
		}

		//checks for benefit value with data type %
		if(txtBox.type == 'text' && txtBox.title == 'BenefitValue%') {
			if(isNaN(+txtBox.value)){
				alert("Benefit Value should be a Number.");
				document.getElementById(txtBox.id).focus();
				return false;
			}
			else{
				if(txtBox.value < 0 ||txtBox.value > 100 ){
					alert("Benefit Value with data type % should be between 0 and 100.");
					document.getElementById(txtBox.id).focus();
					return false;
				}
				if(trim(txtBox.value) == '') {
					txtBox.value = 0;
				}
			}	
		}
	}
	return true;
}

//changes the colour of the row when clicked
function changeColour(levelId,lineSize,rowNum){
	var rowCount=rowNum-1;
	var table = document.getElementById('contractCoverageForm:panelTable'); 
	var rows = table.getElementsByTagName("tr");
	var changeDisabled = '';

	//gets the background colour of the clicked row
	var checkBackGroundColor = rows[rowCount].style.backgroundColor;

	var changeColor = '';
	//changes colour of the row if clicked alternatively
	if( checkBackGroundColor == '' ){
		changeColor = "#cccccc";
		changeDisabled = true;
	}
	else if( checkBackGroundColor == "#cccccc" ){
		changeColor = "";
		changeDisabled = false;
	}
	
	//changes the colour of the row as well as disables and enables the benefit value field
	rows[rowCount].style.backgroundColor = changeColor;
	
	for( var i = 0; i < lineSize; i++ ){
			
			var count = i+1;
			
			rows[rowCount+count].style.backgroundColor = changeColor;
			var bnftValueId = "contractCoverageForm:lineBnftValue" + (count-1) + levelId;
			document.getElementById(bnftValueId).readOnly = changeDisabled;
		}
	
	
	
	/* for(var i=1;i==lineSize;i++){
		rows[rowCount+i].style.backgroundColor = changeColor;
		var bnftValueId = "benefitDefinitionForm:lineBnftValue" + (i-1) + levelId;
		document.getElementById(bnftValueId).readOnly = changeDisabled;
	}*/
  
	//gets the id to which the levels to be deleted is to be added
		
}

</script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
<h:inputHidden id="Hidden" value="#{contractCoverageBackingBean.dummyVar}"></h:inputHidden>
<h:inputHidden id="hiddenLineId" value="#{contractCoverageBackingBean.lineSysId}"></h:inputHidden>

<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="contractCoverageForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->					
<jsp:include page="contractTree.jsp"></jsp:include>
						 		</DIV>

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
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
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														 <h:commandLink action="#{contractBenefitGeneralInfoBackingBean.displayStandardBenefitGeneralInfo}"> <h:outputText value="General Information"/></h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
										</td>

										<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabActive"> <h:outputText value="#{contractCoverageBackingBean.benefitTypeTab}"/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										
							<td width="200" id="tab2">
										<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal">
											<h:commandLink action="#{contractBenefitNotesBackingBean.loadNotes}" id="noteId"> <h:outputText value="Notes" /> </h:commandLink> 
										</td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
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
											action="#{contractBenefitMndateInfoBacingBean.retrieveMandates}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200"></td>
								<td width="200%"></td>
								</tr>
								</table>	
				<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
			<table width="100%" border="0" " cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" class="ContentArea" >
		
						<DIV id="noBenefitDefinitions" style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No Benefit Definitions available.</DIV>
						<DIV id="benefitDefinitionTable">
						<TABLE class="smallfont" id="resultsTable" width="100%"
							cellpadding="3" cellspacing="1" border="0" >
							
							<TR align="left">
								<TD class="ContentArea" align="left" valign="top" width="100%">
									<TABLE	width="100%" cellpadding="0" align="left" border="0" 
										id="tabheader" class="smallfont">
									<tr bgcolor="#cccccc">
										<TD colspan="1" bgcolor="#CCCCCC"><SPAN	id="stateCodeStar">
											<STRONG><h:outputText value="Associated Benefit Lines" /></STRONG></SPAN></TD>
									</tr>
									<TR>
										<TD align="left" valign="top">
											<DIV style="position:relative;top:-2px;left:0px" align="left">
											<DIV id="resultHeaderDiv" align="left"
												style="position:relative;background-color:#FFFFFF;z-index:1;border:1;bordercolor:red;">
												<h:panelGrid
													id="resultHeaderTable"
													binding="#{contractCoverageBackingBean.headerPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid>
											</DIV>
											<DIV id="panelContent"
												style="position:relative;background-color:#FFFFFF;">
											<h:panelGrid id="panelTable"
												binding="#{contractCoverageBackingBean.panel}">
											</h:panelGrid>
											</DIV>											
									</DIV>
									</TD>
									</TR>
									<TR>
										<TD>&nbsp;</TD>
									</TR>
									<TR id="tierHeadTR" bgcolor="#cccccc">
										<TD colspan="1" bgcolor="#CCCCCC"><SPAN	id="stateCodeStar">
											<STRONG><h:outputText value="Associated Tiered Benefit Levels" /></STRONG></SPAN></TD>
									</TR>
									<TR id="tierDataTR"><TD>
											<DIV id="resultHeaderDiv1" 
												style="position:relative;background-color:#FFFFFF;">
												<h:panelGrid id="resultHeaderTable1" binding="#{contractCoverageBackingBean.tierHeaderPanel}"
													rowClasses="dataTableOddRow"></h:panelGrid>
											</DIV>
											<DIV id="panelContent1"
												style="position:relative;background-color:#FFFFFF;">
											<h:panelGrid id="panelTable1"
												binding="#{contractCoverageBackingBean.tierPanel}">
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

</td>
</tr>
</table>
</fieldset>




	
</TD>
</TR>
</table>
	<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

<div id="dummyDiv" >
</div>
				
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
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractCoveragePrint" />
</form>
<script>

//setColumnWidth('contractCoverageForm:resultHeaderTable','13%:14%:12%:7%:9%:15%:21%:9%');
//setColumnWidth('contractCoverageForm:panelTable','13%:14%:12%:7%:9%:15%:21%:9%');

setColumnWidth('contractCoverageForm:resultHeaderTable','20%:12%:17%:7%:9%:10%:18%:7%');
setColumnWidth('contractCoverageForm:panelTable','20%:12%:17%:7%:9%:10%:18%:7%');



var tableObjectTier = document.getElementById('contractCoverageForm:panelTable1');
	if(tableObjectTier.rows.length == 0){ 
		document.getElementById('tierHeadTR').style.display = 'none';
		document.getElementById('tierDataTR').style.display = 'none';
	}

//hides the header panel when no data is present
var tableObject = document.getElementById('contractCoverageForm:panelTable');
if(tableObject.rows.length > 0){ 
		if(document.getElementById('contractCoverageForm:panelTable').offsetHeight > 230) {	
			document.getElementById('contractCoverageForm:resultHeaderTable').width = "100%";
			document.getElementById('contractCoverageForm:panelTable').width = "100%";
		}
		var divBnftDefn = document.getElementById('noBenefitDefinitions');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('benefitDefinitionTable');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noBenefitDefinitions').style.visibility = "visible";
}

	i = document.getElementById("contractCoverageForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tab2.style.display='none';
	tab3.style.display='';
	}else{
	tab2.style.display='';
	tab3.style.display='none';
	}

function getUrlAssigned(benefitLineId,j,i,tierSysId){
	var url='../contract/benefitLineNotesOverridePopupView.jsp'+getUrl();
	ewpdModalWindow_ewpd(url+'?parentEntityType=ATTACHCONTRACT&lookUpAction=101&secondaryEntityId='+ benefitLineId+'&tierSysId='+tierSysId+'&temp=' + Math.random() ,'dummyDiv', 'contractCoverageForm:hidden1',2,1);

	var imageObj = document.getElementById('contractCoverageForm:noteImage'+j+'_'+i);

		var divObj = document.getElementById('dummyDiv');

		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	

		//document.getElementById('contractCoverageForm:hiddenNotesStatus' + j + '_' + i).value = divObj.innerHTML;

		divObj.innerHTML = '';
		//submitLink('contractCoverageForm:refresh');
		return false;
}

function getUrlForTier(benefitLineId,j,i,tierSysId){
	
	ewpdModalWindow_ewpd('../contract/benefitLineNotesOverridePopupView.jsp'+getUrl()+'?parentEntityType=ATTACHCONTRACT&lookUpAction=101&secondaryEntityId='+ benefitLineId+'&tierSysId='+tierSysId+'&temp=' + Math.random() ,'dummyDiv', 'contractCoverageForm:hidden1',2,1);

	var imageObj = document.getElementById('contractCoverageForm:noteImage'+j+'_'+i);

		var divObj = document.getElementById('dummyDiv');

		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	

		//document.getElementById('contractCoverageForm:hiddenNotesStatus' + j + '_' + i).value = divObj.innerHTML;

		divObj.innerHTML = '';
		//submitLink('contractCoverageForm:refresh');
		return false;
}
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
