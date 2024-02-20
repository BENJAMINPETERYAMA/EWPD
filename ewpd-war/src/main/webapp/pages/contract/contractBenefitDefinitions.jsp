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
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>contractBenefitDefinitions.jsp</TITLE>
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
					//txtBox.value = 0;
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
					//txtBox.value = 0;
				}
			}	
		}
	}
	return true;
}
</script>
</HEAD>
<f:view>
	<BODY>
<h:inputHidden id="Hidden" binding="#{contractSearchBenefitDefBackingBean.dummyVar}"></h:inputHidden>
<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="benefitDefinitionForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->				<jsp:include page="contractSearchTree.jsp"></jsp:include>		

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
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabActive"> <h:outputText value="Benefits"/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										
						<!-- 			<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														<h:commandLink > <h:outputText value="Adj Benefit Mandates"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
										</td> -->	

									
									</tr>
								</table>	
				<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
			<table width="80%" border="0" bordercolor="green" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" class="ContentArea" >
				<fieldset >
						<DIV id="noBenefitDefinitions" style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No Benefit Definitions available.</DIV>
						<DIV id="benefitDefinitionTable">
						<TABLE class="smallfont" id="resultsTable" width="100%"
							cellpadding="3" cellspacing="1" border="0" bordercolor="red">
							<TR bgcolor="#cccccc">
								<TD colspan="1" bgcolor="#CCCCCC"><SPAN id="stateCodeStar"><STRONG><h:outputText
									value="Associated Benefit Lines" /></STRONG></SPAN></TD>
							</TR>
						<TR align="left">
								<TD class="ContentArea" align="left" valign="top" width="100%"><TABLE
									width="100%" cellpadding="0" align="left" border="0"
									id="tabheader" class="smallfont">
									<TR>

										<TD align="left" valign="top"><DIV
											style="position:relative;top:0px;left:0px" align="left"><DIV
											id="resultHeaderDiv" align="left"
											style="position:relative;background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
											id="resultHeaderTable"
											binding="#{contractSearchBenefitDefBackingBean.headerPanel}"
											rowClasses="dataTableOddRow">
										</h:panelGrid></DIV>

										<DIV id="panelContent"
											style="position:relative;background-color:#FFFFFF;overflow:auto;height:150">
										<h:panelGrid id="panelTable"
											binding="#{contractSearchBenefitDefBackingBean.panel}"
											>
										</h:panelGrid></DIV>
									</DIV>
								</TD>
							</TR>

							<TR>
										<TD>&nbsp;</TD>
									</TR>

									<TR>
										<TD>&nbsp;</TD>
									</TR>

									<TR>
										<TD><SPAN style="margin-left:6px;margin-right:6px;"> <h:commandButton
											value="AddToSearch" styleClass="wpdButton" onclick="return validate();"
											action="#{contractSearchBenefitDefBackingBean.addToSearch}">
										</h:commandButton> &nbsp;
										 <h:commandButton
											value="Done" styleClass="wpdButton" 
											action="#{contractSearchBenefitDefBackingBean.done}">
										</h:commandButton> </TD>
									</TR>

							
						</TABLE>
					
				</TD>
			</TR>




</TABLE>
</DIV>
<DIV id="doneButton"  style="margin-left:6px;margin-right:6px;"> <SPAN style="margin-left:6px;margin-right:6px;"> <h:commandButton
											value="Done" styleClass="wpdButton" action="#{contractSearchBenefitDefBackingBean.done}">
											
										</h:commandButton> </SPAN>
</DIV>
</fieldset>
</td>
</tr>
</table>
</fieldset>




	
</TD>
</TR>
</table>
					

</h:form>
</td>
</tr>
<tr>
			<td>
				<%@ include file ="../navigation/bottom.jsp" %>
			</td>
</tr>
</table>

	</BODY>
</f:view>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="productBenefitDefinitionPrint" />
</form>
<script>

setColumnWidth('benefitDefinitionForm:resultHeaderTable','15%:15%:15%:15%:15%:15%:10%');
setColumnWidth('benefitDefinitionForm:panelTable','15%:15%:15%:15%:15%:15%:10%');




//hides the header panel when no data is present
var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
if(tableObject.rows.length > 0){
	var divBnftDefn = document.getElementById('noBenefitDefinitions');
	divBnftDefn.style.visibility = "hidden";
	divBnftDefn.style.height = "2px";
	var divDone = document.getElementById('doneButton');
	divDone.style.visibility = "hidden";
	divDone.style.height = "2px";
}else{
	var divObj = document.getElementById('benefitDefinitionTable');
	divObj.style.visibility = "hidden";
	divObj.style.height = "2px";
	document.getElementById('noBenefitDefinitions').style.visibility = "visible";
	document.getElementById('doneButton').style.visibility = "visible";
}


</script>
</HTML>
