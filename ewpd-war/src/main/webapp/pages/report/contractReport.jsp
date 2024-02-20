<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>eWPD Contract Data Extract</TITLE>
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
	var systemComponents;
	var systemBenefits;
	var searchOpen = 'F';
</script>
<style type="text/css">

#hintbox{ /*CSS for pop up hint box */
	position:absolute;
	top: 0;
	background-color: lightyellow;
	width: 150px; /*Default width of hint.*/ 
	padding: 3px;
	border:1px solid black;
	font:normal 11px Verdana;
	line-height:18px;
	z-index:100;
	border-right: 3px solid black;
	border-bottom: 3px solid black;
	visibility: hidden;
}

.hintanchor{ /*CSS for link that shows hint onmouseover*/
	font-weight: bold;
	color: navy;
	margin: 3px 8px;
}

</style>

<script type="text/javascript">

	function loadingWindow(){
		disablePage();
		enablePage();
	}

</script>
</HEAD>
<f:view>
<BODY>
<div onkeypress="return submitOnEnterKey('contractReportForm:submitButton');" onclick="closeSearchDivOnBodyClick();">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>	
			<td>
				<%
				javax.servlet.http.HttpServletRequest httpReq = 
					(javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                		.getCurrentInstance().getExternalContext().getRequest();
        		httpReq.setAttribute("breadCrumbText","Contract Development >> Contract Extract ");
				%>
				<jsp:include page="../navigation/top.jsp"></jsp:include>
    		</td>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="contractReportForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
						
	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
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
													<td width="186" class="tabActive"> <h:outputText value=" Contract Extract "/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" height="200">
									<TBODY>
										<TR>
											<TD width="150" height = "10"></TD>
											<TD width="229"></TD>
										</TR>									
										<TR>
											<TD width="150">
												<h:outputText value="Contract ID*"/>
												<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(contractInputHint, this, event, '300px')">[?]</a> 
											</TD>
											<TD width="229" colspan=2><h:inputText tabindex="1" styleClass="formInputField" id="contractId" style="width:300px;" value="#{ContractReportBackingBean.contractId}"/> </TD>
											<TD ROWSPAN=5 VALIGN="top">
												<table >
													<tbody>
														<tr>
															<td width="20" ROWSPAN=6></td>
															<td>
																<h:selectBooleanCheckbox id="headerRule" tabindex="6" value="#{ContractReportBackingBean.headerRule}"></h:selectBooleanCheckbox>
																<h:outputLabel for="headerRule" value="Include Header Rules"></h:outputLabel>
																<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(headerRuleHint , this, event, '300px')">[?]</a>
															</td>
														</tr>
														<tr><td>
															<h:selectBooleanCheckbox id="benefitLine" tabindex="7" value="#{ContractReportBackingBean.benefitLine}"></h:selectBooleanCheckbox>
															<h:outputLabel for="benefitLine" value="Include Benefit Lines"></h:outputLabel>
															<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(costShareHint , this, event, '300px')">[?]</a>
														</td></tr>
														<tr><td>
															<h:selectBooleanCheckbox id="question" tabindex="8" value="#{ContractReportBackingBean.question}"></h:selectBooleanCheckbox>
															<h:outputLabel for="question" value="Include Questions"></h:outputLabel>
															<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(questionHint , this, event, '300px')">[?]</a>
														</td></tr>
														<tr><td>
															<h:selectBooleanCheckbox id="adminMethod" tabindex="9" value="#{ContractReportBackingBean.adminMethod}"></h:selectBooleanCheckbox>
															<h:outputLabel for="adminMethod" value="Include Admin Methods"></h:outputLabel>
															<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(adminMethodHint , this, event, '300px')">[?]</a>
														</td></tr>																												
														<tr><td>
															<h:selectBooleanCheckbox id="notes" tabindex="10" value="#{ContractReportBackingBean.notes}"></h:selectBooleanCheckbox>
															<h:outputLabel for="notes" value="Include Notes"></h:outputLabel>
															<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(notesHint , this, event, '300px')">[?]</a>
														</td></tr>
													</tbody>
												</table>
											</TD>											
										</TR>
										<TR>
											<TD width="150"><h:outputText value="Effective Date"/>
											<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(startDateFieldHint , this, event, '300px')">[?]</a> 
											</TD>
											<TD width="229" colspan=2><h:inputText tabindex="2" styleClass="formInputField" id="effDate" value="#{ContractReportBackingBean.startDate}" style="width:300px;"/> </TD>
										</TR>
										<TR>
											<TD width="150" valign="top">
												<h:outputText value="Benefit Component"/>
												<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(componentFieldHint , this, event, '300px')">[?]</a>
											</TD>
											<TD width="229" valign="top">
												<h:inputTextarea tabindex="3" styleClass="formTxtAreaField_GeneralDesc" id="component" value="#{ContractReportBackingBean.components}" style="width:300px;height:100px">
												</h:inputTextarea>
											</TD>
											<td valign="top">
												<input name="divSearchButton" type="image" src="../../images/select.gif"  onclick="searchWindow('Benefit Component Search','contractReportForm:systemComponentsHidden','contractReportForm:component');return false;"/>
											</td>
										</TR>
										<TR>
											<TD width="150" valign="top"><h:outputText value="Benefit"/>
											<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(benefitFieldHint  , this, event, '300px')">[?]</a> 
											</TD>
											<TD width="229" valign="top">
												<h:inputTextarea tabindex="4" styleClass="formTxtAreaField_GeneralDesc" id="benefit" value="#{ContractReportBackingBean.benefits}" style="width:300px;height:100px">
												</h:inputTextarea> 											 
											</TD>
											<td valign="top">
												<input name="divSearchButton" type="image" src="../../images/select.gif"  onclick="searchWindow('Benefit Search','contractReportForm:systemBenefitsHidden','contractReportForm:benefit');return false;"/>
											</td>											
										</TR>
										<tr><td colspan=3>
											<h:selectBooleanCheckbox id="singleSheet" tabindex="5" value="#{ContractReportBackingBean.singleSheet}"></h:selectBooleanCheckbox>
											<h:outputLabel for="singleSheet" value="Use multiple Worksheets"></h:outputLabel>
											<a href="#" class="hintanchor" onClick="return false;" onMouseover="showhint(singleSheetInputHint , this, event, '300px')">[?]</a>
										</td></tr>																														
										<TR>
											<TD width="150">
												<h:commandButton id="submitButton" value="Extract Data"  tabindex="11" styleClass="wpdButton" action="#{ContractReportBackingBean.validateInputs}" onclick="loadingWindow();"> </h:commandButton>
											</TD>
											<TD width="229" colspan=2>&nbsp;</TD>
										</TR>
										<TR>
											<TD width="150" height = "10"></TD>
											<TD width="229" colspan=2></TD>
										</TR>												
									</TBODY>
									</TABLE>
<!--	End of Page data	-->
								</fieldset>		
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="inputValidFlag" value="#{ContractReportBackingBean.inputValidFlag}"></h:inputHidden>
					<h:inputHidden id="systemBenefitsHidden" value="#{ContractReportBackingBean.systemBenefits}"></h:inputHidden>
					<h:inputHidden id="systemComponentsHidden" value="#{ContractReportBackingBean.systemComponents}"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
					<h:inputHidden id="maxContractCountHidden" value="#{ContractReportBackingBean.maxContractCount}"></h:inputHidden>
					<h:inputHidden id="maxComponentCountHidden" value="#{ContractReportBackingBean.maxComponentCount}"></h:inputHidden>
					<h:inputHidden id="maxBenefitCountHidden" value="#{ContractReportBackingBean.maxBenefitCount}"></h:inputHidden>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file ="../navigation/pageFooter.jsp" %>
			</td>
		</tr>
	</table>
</div>

	<form name="submitContractForm" action="contractReport.rpt" method="post" id="submitContractForm" target="targetIframe">
		<input type="hidden" id="contractIdHidden" name="contractIdHidden"/>
		<input type="hidden" id="startDateHidden" name="startDateHidden"/>
		<input type="hidden" id="componentHidden" name="componentHidden"/>
		<input type="hidden" id="benefitHidden" name="benefitHidden"/>
		
		<input type="hidden" id="headerRuleFlag" name="headerRuleFlag"/>
		<input type="hidden" id="benefitLineFlag" name="benefitLineFlag"/>
		<input type="hidden" id="quesitonFlag" name="quesitonFlag"/>
		<input type="hidden" id="adminMethodFlag" name="adminMethodFlag"/>
		<input type="hidden" id="notesFlag" name="notesFlag"/>
		<input type="hidden" id="singleSheetFlag" name="singleSheetFlag"/>
	</form>

	<div id="searchDiv" style="position:absolute;z-index:25;width:400px;height:300px;overflow:auto;background-color:lightyellow;border:1px solid #CCCCCC;color: #000000;display:none" >
		<table width="100%" id="divTitleTable">
			<tr>
				<td align="center" width="80%">
					
				</td>
				<td align="right" width="20%">
					<input type="image" src="../../images/divclose.jpg"  onclick="closeSearchDiv();return false;"/>
				</td>
			</tr>
		</table>

		<table width="100%" CELLSPACING=0 CELLPADDING=0 >
			<tr align="left">
				<td width="25px"></td>
				<td align="left"><input type="text" onkeydown="return disableEnter();" onkeyup="refresh();return false;" id="searchBox" style="width:320px"> </input></td>
				<td align="left"><input type="submit" value="add" onclick="addItemstoParent();return false;" class="wpdButton"></td>
			</tr>
		</table>

		<div style="width:395px;height:220px;overflow:auto;" >
			<TABLE ALIGN="left" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%" id="itemTable">
			</TABLE>
		</div>
	</div>	

	<iframe width="100%" height="300" style="display:none;visibility:hidden" name="targetIframe" id="targetIframe" onreadystatechange ="checkIframStatus();">
	</iframe>
	
	<jsp:include page="../template/freeze.html"></jsp:include>
	
</BODY>


</f:view>

<script>
	// variable to indiate whether servlet is invoked to get the Report.
	var reportSubmitted = false;
	enablePage();
	function closeSearchDivOnBodyClick() {
		var e = window.event;
		if(!e || e==undefined) {
			return;
		}
		var button_id = e.srcElement.name;
		if(	button_id != 'divSearchButton') {
			if(searchOpen == 'T') {
				closeSearchDiv();
			}
		}
	}
	
	// Check the status of the Iframe and enable screen by removing blocking div once
	// the response is received.
	function checkIframStatus() {
		iframe = document.getElementById('targetIframe');
		if(reportSubmitted) {
			if( iframe && iframe.document && (iframe.document.readyState == 'interactive' || iframe.document.readyState == 'complete')) {
				reportSubmitted = false;
				enablePage();	
			}
		}
	}

	/* In page help messages for fields */
	var contractInputHint = 'Contracts codes for which data needs to be extracted.  This is a mandatory input.  Multiple codes must be separated by a comma (,).  Extract must be restricted to specific Benefits if multiple Contract codes are specified.  See Benefit Component and Benefit tips for more details.  <br/> eg: GC81,GC82';
	
	var startDateFieldHint = 'The date segment for which data needs to be extracted.  This is an optional input.  If a date is not provided all the date segments in the Contract code will be extracted.  Format must be <b>mm/dd/yyyy</b>';
	
	var componentFieldHint = 'Benefit Component name(s).  Data will be extracted only for the Benefits within the specified component.  Enter only one name per line.  By default this is optional; however extract must be restricted to one or more Benefits Components if multiple Contract codes are specified.' ;
	
	var benefitFieldHint = 'Benefit name(s).  Data will be extracted only for the specified Benefits if specified.  Enter only one name per line.  By default this is optional; however extract must be restricted to one or more Benefits if multiple Contract codes are specified.';
	
	var headerRuleHint = 'Controls whether header rule ids are included in the extract.  By default they are included.';
	
	var costShareHint = 'Controls whether Benefit Lines are included in the extract.  By default they are included.';
	var questionHint = 'Controls whether Admin Options/Questions are included in the extract.  By default they are included.';
	var adminMethodHint = 'Controls whether Admin Methods are included in the extract.  By default they are included.';
	var notesHint = 'Controls whether Notes are included in the extract.  By default they are included.';
	var singleSheetInputHint = 'Control layout of Excel document.  By default all the data is extracted into one worksheet;  Check this option to have date segments extracted to individual worksheets.';

	// Setting focus to Contract Id field
	document.getElementById('contractReportForm:contractId').focus();
	
	/* Script to invoke the Servlet if server returns valid response */
	var validFlagVal = document.getElementById('contractReportForm:inputValidFlag').value;
	
	if(validFlagVal != '') {
		var submitConfirm = false;
		
		if( validFlagVal == 'Y') {
			submitConfirm = true;
		}

		if(submitConfirm == true) {
			invokeServlet();
		}
	}
	
	function invokeServlet() {
			// Setting the values to the form and invoking the servlet to get the report.
			document.getElementById('contractIdHidden').value = document.getElementById('contractReportForm:contractId').value;
			document.getElementById('startDateHidden').value = document.getElementById('contractReportForm:effDate').value;
			document.getElementById('componentHidden').value = document.getElementById('contractReportForm:component').value;
			document.getElementById('benefitHidden').value = document.getElementById('contractReportForm:benefit').value;
			
			document.getElementById('headerRuleFlag').value = document.getElementById('contractReportForm:headerRule').checked;
			document.getElementById('benefitLineFlag').value = document.getElementById('contractReportForm:benefitLine').checked;
			document.getElementById('quesitonFlag').value = document.getElementById('contractReportForm:question').checked;
			document.getElementById('adminMethodFlag').value = document.getElementById('contractReportForm:adminMethod').checked;
			document.getElementById('notesFlag').value = document.getElementById('contractReportForm:notes').checked;
			document.getElementById('singleSheetFlag').value = document.getElementById('contractReportForm:singleSheet').checked;
					
			disablePage();
			document.forms['submitContractForm'].submit();
			reportSubmitted = true;	
			enablePage();	
	}
	
	/* Script to initialize and handle the Search boxes for Benefit and Components */
	systemComponents = document.getElementById('contractReportForm:systemComponentsHidden').value.split('~');
	systemBenefits = document.getElementById('contractReportForm:systemBenefitsHidden').value.split('~');
	var inputTextBoxName;
	var preLoadedArrName;

	// Adds selected values from div to text area for Benefit/Component.
	function addItemstoParent() {
		tableObject = document.getElementById('itemTable');
		textBoxObj = document.getElementById(inputTextBoxName);
		
		var textBoxValues = textBoxObj.value.split('\n');
		
		for(var i=0; i<tableObject.rows.length; i++) {
			curRow = tableObject.rows[i];
			var selectedValue = curRow.cells[1].children[0].innerText;
			if(curRow.cells[0].children[0].checked) {
				if(textBoxObj.value == '')
					textBoxObj.value = selectedValue;
				else {
					var found = false;
					for (var j=0; j<textBoxValues.length; j++) {
						if(textBoxValues[j].indexOf(selectedValue) != -1)
							found = true; 
					}
					if(!found) {
						textBoxObj.value = textBoxObj.value + '\n' + selectedValue;
					}
				}
			}

		}
	}

	// Close the Search Div
	function closeSearchDiv() {
		document.getElementById('searchDiv').style.display = 'none';
		searchOpen = 'F';
	}

	
	// Open the Search Div
	function searchWindow(title, itemObjs, textBoxId){
		if(searchOpen == 'T' && textBoxId == inputTextBoxName)
			return;
			
		document.getElementById('divTitleTable').rows[0].cells[0].innerHTML = title;
		document.getElementById('searchDiv').style.left = '500px';
		document.getElementById('searchDiv').style.top = '180px';

		document.getElementById('searchDiv').style.display = 'block';
		document.getElementById('searchBox').focus();
		document.getElementById('searchBox').value = '';
		inputTextBoxName = textBoxId;
		preLoadedArrName = document.getElementById(itemObjs).value.split('~');
		refresh();
		searchOpen = 'T';
	}

	// Refresh the results based on the data typed in search field.
	function refresh() {
		tableObject = document.getElementById('itemTable');
		numrows = tableObject.rows.length;

		for(var i=0; i<numrows; i++) {
			tableObject.deleteRow(this);
		}

		typeVal = document.getElementById('searchBox').value.toUpperCase();
		
		var rowCount = 0;

		for(var i = 0; i < preLoadedArrName.length; i++) {
			if(rowCount > 100)
				break;
			if(preLoadedArrName[i].indexOf(typeVal) != -1) {
				insertRows('itemTable', preLoadedArrName[i]);
				rowCount ++;
			}
		}

		if(tableObject.rows.length == 1) {
			tableObject.rows[0].cells[0].children[0].checked = true;
		}

	}

	// Disable enter key to avoid page getting submitted.
	function disableEnter() {
		if(window.event.keyCode==13) {
			addItemstoParent();
			return false;
		}
	}

	// Funcation to insert values
	function insertRows(tableId, itemName){
			tableObject = document.getElementById(tableId);
			numrows = tableObject.rows.length;
			aRow =  tableObject.insertRow();
			aRow.align = 'Left';
			aCell2 = aRow.insertCell();
			aCell2.align='Left';
			aCell2.style.width='25px';
			aCell2.innerHTML = "<input type='checkbox' id='listItemCheckbox"+numrows+"'/>";
			//aCell2.innerHTML = "<input type='image' id='listItemCheckbox"+numrows+"' src='../../images/add.jpg'  onclick='closeSearchDiv();return false;'/>";
			aCell2 = aRow.insertCell();
			aCell2.align='Left';
			aCell2.innerHTML = "<label for='listItemCheckbox"+numrows+"'>"+itemName+"</label>";
	}

	/***********************************************
	* Show Hint script- © Dynamic Drive (www.dynamicdrive.com)
	* This notice MUST stay intact for legal use
	* Visit http://www.dynamicdrive.com/ for this script and 100s more.
	***********************************************/
			
	var horizontal_offset="9px" //horizontal offset of hint box from anchor link
	
	
	var vertical_offset="0" //horizontal offset of hint box from anchor link. No need to change.
	var ie=document.all
	var ns6=document.getElementById&&!document.all

	function getposOffset(what, offsettype){
		var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
		var parentEl=what.offsetParent;
		while (parentEl!=null){
			totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
			parentEl=parentEl.offsetParent;
		}
		return totaloffset;
	}

	function iecompattest(){
		return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
	}

	function clearbrowseredge(obj, whichedge){
		var edgeoffset=(whichedge=="rightedge")? parseInt(horizontal_offset)*-1 : parseInt(vertical_offset)*-1
		if (whichedge=="rightedge"){
			var windowedge=ie && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-30 : window.pageXOffset+window.innerWidth-40
			dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
			if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
				edgeoffset=dropmenuobj.contentmeasure+obj.offsetWidth+parseInt(horizontal_offset)
		}
		else{
			var windowedge=ie && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
			dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
			if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
				edgeoffset=dropmenuobj.contentmeasure-obj.offsetHeight
		}
		return edgeoffset
	}

	function showhint(menucontents, obj, e, tipwidth){
		if(document.getElementById("hintbox") == null) {
			createhintbox();
		}
		if ((ie||ns6) && document.getElementById("hintbox")){
			dropmenuobj=document.getElementById("hintbox")
			dropmenuobj.innerHTML=menucontents
			dropmenuobj.style.left=dropmenuobj.style.top=-500
			if (tipwidth!=""){
				dropmenuobj.widthobj=dropmenuobj.style
				dropmenuobj.widthobj.width=tipwidth
			}
			dropmenuobj.x=getposOffset(obj, "left")
			dropmenuobj.y=getposOffset(obj, "top")
			dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+obj.offsetWidth+"px"
			dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+"px"
			dropmenuobj.style.visibility="visible"
			obj.onmouseout=hidetip
		}
	}

	function hidetip(e){
		dropmenuobj.style.visibility="hidden"
		dropmenuobj.style.left="-500px"
	}

	function createhintbox(){
		var divblock=document.createElement("div")
		divblock.setAttribute("id", "hintbox")
		document.body.appendChild(divblock)
	}
	//createhintbox();
	
	if (window.addEventListener) {
		window.addEventListener("load", createhintbox, false);
	}
	else if (window.attachEvent) {
		window.attachEvent("onload", createhintbox);
	}
	else if (document.getElementById) {
		window.onload=createhintbox;
	}

</script>
</HTML>
