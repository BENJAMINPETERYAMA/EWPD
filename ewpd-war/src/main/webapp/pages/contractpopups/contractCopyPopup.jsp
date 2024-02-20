<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" %>
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<TITLE>contractCopyPopup.jsp</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<BASE target="_self" />
</HEAD>

	<BODY><f:view>
<jsp:include page="../navigation/popupHeader.jsp"></jsp:include>
<h:form id="copyContractForm">
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
			<TBODY>
				<TR>
					<td height="16" valign="middle" bgcolor="#7670B3"
						class="breadcrumb">Locate &gt;&gt; Contract &gt;&gt; Copy Contract
					</td>
					<td class="breadcrumb" width="55">&nbsp;</td>
				</TR>
			</TBODY>
		</TABLE>
		<table><tr>
				<td width="15">&nbsp;</td>
				<td width="29"></td>
			</tr>
		</table>
		<DIV id="popupDataTableDiv1">
		<TABLE width="96%" align="center" cellpadding="0" cellspacing="0">
			
			<TBODY>
				<TR>
					<TD>
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<TR>
								
								<TD width="50%" align="left"  height="20"> <STRONG>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Copy Contract </STRONG> </TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
				<TR>
					<TD>
						
								<TABLE>
									<TBODY>
										<TR>
											<TD>
												<w:message ></w:message>
											</TD>
										</TR>		
									</TBODY>
								</TABLE>		
							
							
		<table id="copyContractTable">
			<tr>
					<td>
				<input type="radio"  name="copyContract" value="copyContract" checked="checked" onclick="getValue('copyContract');">Copy Contract to a new contract.</td>
						</tr>
						<tr>
					<td id="copyContractWithHeading">
				<input type="radio"  name="copyContract" value="copyContractWithHeading" onclick="getValue('copyContractWithHeading');">Copy Contract with Headings to a new contract.</td>
						</tr>
						<tr>
					<td id="copyHeadingToExistingContract">
					<input type="radio"  name="copyContract" value="copyHeadingToExistingContract"   onclick="getValue('copyHeadingToExistingContract');">Copy Headings to an existing contract.</td>
						</tr>
					</table>
										</TD>
			</TR>
	</TBODY>
	</TABLE>
	</DIV>
	<BR>
	<DIV id="existingContractDiv">
	<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="50%">

										<tr></tr>
										
										<TR>
											<td ></td>
											<TD valign="top" width="28%" align="left">
												<h:outputText id="contractIdExisting" value="Contract ID *" styleClass="#{contractBasicInfoBackingBean.existingContractIdInvalid ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText>
											</TD>
											<TD width="26%"><DIV id="contractIdExistingDiv" class="selectDataDisplayDiv" ></DIV>
																<h:inputHidden id="contractIdExistingHidden"
													   				value="#{contractBasicInfoBackingBean.contractIdExistingDiv}"></h:inputHidden>
											</TD>
											<TD width="46%"><h:commandButton alt="Select" id="ContractIdButton" 
													image="../../images/select.gif" style="cursor: hand"
													onclick="contractIdPopup();
																				return false;"
													tabindex="2"></h:commandButton>
											</TD>
					
		
										</TR>
										
										<TR>
											<td ></td>
											<TD valign="top" width="28%" align="left">
												
												<h:outputText id="effectiveDate" value="Effective Date *" styleClass="#{contractBasicInfoBackingBean.existingContractDtInvalid ? 'mandatoryError': 'mandatoryNormal'}">
												</h:outputText>
										<h:inputHidden id="effectiveDateHidden" value="#{contractBasicInfoBackingBean.baseContractDtDiv}"></h:inputHidden>
											</TD>
											<TD width="26%"><DIV id="baseContractDtDiv" class="selectDataDisplayDiv"></DIV>
																
												
											</TD>
											<TD width="46%">
												<h:commandButton alt="Select" id="effectiveDateCodeButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="popupaction();	return false;"
													tabindex="2"></h:commandButton>
											</TD>
					
		
										</TR>

										
	</TABLE>
	</DIV>
<br>
<DIV id="panelDataTable">
<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD align="center" width="3%">
								<h:selectBooleanCheckbox id="checkAllCheckBox" onclick="checkAll_ewpd(this,'copyContractForm:panelTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD align="center" width="47%">
								<strong><h:outputText value="Select Headings"></h:outputText></strong>
							</TD>
								<TD align="left" width="6%">
								<h:selectBooleanCheckbox id="disableAllCheckBox" onclick="disableCheckBoxes('copyContractForm:disableAllCheckBox','copyContractForm:checkAllCheckBox', 'copyContractForm:panelTable');">
								</h:selectBooleanCheckbox>
							</TD>
							<TD align="center" width="44%">
								<strong><h:outputText value="Select Headings(Includes Non-Coded Headings)"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
<td>
					<DIV id="panelContent"
											style="position:relative;background-color:#FFFFFF;overflow:auto;height:300">
										<h:panelGrid id="panelTable"
											binding="#{contractBasicInfoBackingBean.panel}">
										</h:panelGrid></DIV>
</td>	
			</tr>
			</TBODY>
		</table>
</DIV>
			<table width="96%" align="center" cellpadding="0" cellspacing="0" border="0">
			<tr>
			<td align="left" width="7%"><h:commandButton value="Copy"  styleClass="wpdButton"  tabindex = "12" onclick="copy();return false;"> </h:commandButton></td>
			<TD align="left" width="7%"><h:commandButton value="Cancel"  styleClass="wpdButton"  tabindex = "12" onclick="cancel();return false;"> </h:commandButton></td>
			 <td width="82%"></td>
			</tr>
			</table>
<h:commandLink id="copyLink"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.copyAction}">
				<f:verbatim />
			</h:commandLink>

<h:commandLink id="getHeadings"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.getSelectedHeadingsAction}">
				<f:verbatim />
			</h:commandLink>

<h:commandLink id="getExistingContracts"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.getExistingContractsAction}"> 
				<f:verbatim />
			</h:commandLink>

<h:inputHidden id="copy" value="#{contractBasicInfoBackingBean.forCopy}"></h:inputHidden>

<h:inputHidden id="flag" value="#{contractBasicInfoBackingBean.copy}"></h:inputHidden>



<h:inputHidden id="hiddenDateSegmentID" value="#{contractBasicInfoBackingBean.hiddenDateSegmentIdForCopy}"></h:inputHidden>
<h:inputHidden id="hiddenContractKey" value="#{contractBasicInfoBackingBean.hiddenContractKeyForCopy}"></h:inputHidden>
<h:inputHidden id="hiddenVersion" value="#{contractBasicInfoBackingBean.hiddenVersionForCopy}"></h:inputHidden>
<h:inputHidden id="hiddenContractId" value="#{contractBasicInfoBackingBean.hiddenContractIdForCopy}"></h:inputHidden>
<h:inputHidden id="hiddenStatus" value="#{contractBasicInfoBackingBean.hiddenStatusForCopy}"></h:inputHidden>
<h:inputHidden id="setExistingTrue" value="#{contractBasicInfoBackingBean.setExistingTrue}"></h:inputHidden>
<h:inputHidden id="productStatus" value="#{contractBasicInfoBackingBean.hiddenProductStatusForCopy}"></h:inputHidden>
<h:inputHidden id="noteStatus" value="#{contractBasicInfoBackingBean.hiddenNoteStatusForCopy}"></h:inputHidden>
<h:inputHidden id="copyStatus" value="#{contractBasicInfoBackingBean.hiddenLatestCopyStatus}"></h:inputHidden>
<h:inputHidden id="productSysId" value="#{contractBasicInfoBackingBean.hiddenProductIdForCopy}" ></h:inputHidden>
<h:inputHidden id="disableCheckBox"  value="#{contractBasicInfoBackingBean.disableCheckBox}"></h:inputHidden>



</h:form>
</f:view>
</BODY>


<script language="javascript">

//unchecks the check box when the page gets refreshed
if(document.getElementById('copyContractForm:disableAllCheckBox')!=null){

	if(document.getElementById('copyContractForm:disableAllCheckBox').checked){
	document.getElementById('copyContractForm:disableAllCheckBox').checked = false;
	}
}

//unchecks the check box when the page gets refreshed.
if (document.getElementById('copyContractForm:checkAllCheckBox')!=null){

	if(document.getElementById('copyContractForm:checkAllCheckBox').checked)
	document.getElementById('copyContractForm:checkAllCheckBox').checked = false;
}

if(document.getElementById('copyContractForm:copyStatus').value == 'true')
{

//var table = document.getElementById('benefitDefinitionForm:panelTable'); 
//	var rows = table.getElementsByTagName("tr");

document.getElementById('copyContractWithHeading').style.visibility = "hidden";
document.getElementById('copyHeadingToExistingContract').style.visibility = "hidden";

}




function popupaction()
{
	var baseContractid = document.getElementById('copyContractForm:contractIdExistingHidden').value;
	var url ='../contractpopups/SelectBaseContractStartDate.jsp'+getUrl()+'?sysId='+escape(baseContractid)+'&temp='+Math.random();
	var retValue = ewpdModalWindow_ewpd(url,'baseContractDtDiv','copyContractForm:effectiveDateHidden',2,1);
	return retValue;
}

function contractIdPopup(){
   var contractId = document.getElementById('copyContractForm:hiddenContractId').value;
   var url = '../contractpopups/contractIdPopup.jsp'+getUrl()+'?temp='+Math.random()+'&contractId='+escape(contractId);
   var retValue = ewpdModalWindow_ewpd(url,'contractIdExistingDiv','copyContractForm:contractIdExistingHidden',2,1,'baseContractDtDiv','copyContractForm:effectiveDateHidden');
   return retValue;

}
if(document.getElementById('copyContractForm:flag').value == 'true'){
	window.returnValue='copyHeadings';
	<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>

}


function copy(){
	var copyHeadings = "false";
	var disableCheckBox = "false";
	var x=document.getElementsByName('copyContract');
	for(var i=0;i<x.length;i++){
	if(x[i].checked){
		
			if(x[i].value == 'copyHeadingToExistingContract'){
				copyHeadings="true";
				if(document.getElementById('copyContractForm:disableAllCheckBox').checked == true)
						{
							document.getElementById('copyContractForm:disableCheckBox').value = "true";
	
						} 
				else
						document.getElementById('copyContractForm:disableCheckBox').value = "false";
				
			}
			else{
			   	if(x[i].value == 'copyContract'){
					
					window.returnValue='copy';
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>	
					return false;	
				}
				else if(x[i].value == 'copyContractWithHeading'){
	 		//		if(document.getElementById('copyContractForm:disableAllCheckBox').checked == true)
				//		{
					//		window.returnValue='copy';
					//		window.close();	
	
					//	}
					//	else
						submitLink('copyContractForm:getHeadings');
					}
			
			   }
		   }
		
	}
	if(copyHeadings == 'true')
     submitLink('copyContractForm:getExistingContracts');

	}





setColumnWidth('copyContractForm:panelTable','4%:96%');
document.getElementById('existingContractDiv').style.visibility = "hidden";
document.getElementById('panelDataTable').style.visibility = "hidden";

if(document.getElementById('copyContractForm:setExistingTrue').value == 'true'){
		var x=document.getElementsByName('copyContract');
			x[2].checked = true;
document.getElementById('existingContractDiv').style.visibility = "visible";
	if(checkTableSize()){
	document.getElementById('panelDataTable').style.visibility = "visible";
}
	else{
	document.getElementById('panelDataTable').style.visibility = "hidden";
}

 copyHiddenToDiv_ewpd('copyContractForm:contractIdExistingHidden','contractIdExistingDiv',2,1);  
 copyHiddenToDiv_ewpd('copyContractForm:effectiveDateHidden','baseContractDtDiv',2,1); 
	
}


function getValue(heading){

var x=document.getElementsByName('copyContract');
for(var i=0;i<x.length;i++){
if(x[i].checked){

if(x[i].value == 'copyHeadingToExistingContract'){
	document.getElementById('contractIdExistingDiv').style.visibility = "visible";	
	document.getElementById('baseContractDtDiv').style.visibility = "visible";
	document.getElementById('existingContractDiv').style.visibility = "visible";
	if(checkTableSize()){
	document.getElementById('panelDataTable').style.visibility = "visible";
	document.getElementById('copyContractForm:disableAllCheckBox').disabled = false;
	}
	else
	document.getElementById('panelDataTable').style.visibility = "hidden";
	
}
else{
	document.getElementById('contractIdExistingDiv').style.visibility = "hidden";	
	document.getElementById('baseContractDtDiv').style.visibility = "hidden";
	
	
   	if(x[i].value == 'copyContract')
			document.getElementById('panelDataTable').style.visibility = "hidden";
	else if(x[i].value == 'copyContractWithHeading'){
		if(checkTableSize()){
			document.getElementById('panelDataTable').style.visibility = "visible";
			document.getElementById('copyContractForm:disableAllCheckBox').disabled = true;
	}
		else
			document.getElementById('panelDataTable').style.visibility = "hidden";

	}
	document.getElementById('existingContractDiv').style.visibility = "hidden";

	}
}
}
}


function checkTableSize(){
var tableObject = document.getElementById('copyContractForm:panelTable');
if(tableObject.rows.length > 0){
	return true;
}else{
	var divObj = document.getElementById('panelDataTable');
	divObj.style.visibility = "hidden";
	//divObj.style.height = "2px";
	return false;
	
}
}



function disableCheckBoxes(copyAllCheckBox, selectAllCheckBox, tableId){
		var copyAllCheckBoxObj = document.getElementById(copyAllCheckBox);
		var tableObj = document.getElementById(tableId);
		if(copyAllCheckBoxObj.checked == true){
			document.getElementById(selectAllCheckBox).disabled = true;
			for(var i=0; i<tableObj.rows.length; i++) {
				tableObj.rows[i].cells[0].children[0].disabled = true;
			}
		}
		else if(copyAllCheckBoxObj.checked == false){
			document.getElementById(selectAllCheckBox).disabled = false;
			for(var i=0; i<tableObj.rows.length; i++) {
				tableObj.rows[i].cells[0].children[0].disabled = false;
			}
		}
	}


function checkStndBnfts(bnftCmpntNo,stdBnftSize){

 var bnftCmpnt=document.getElementById('copyContractForm:BnftCmpnt'+bnftCmpntNo);

for(var i=0;i<stdBnftSize;i++){
   var stdBnft = document.getElementById('copyContractForm:StdBnft'+bnftCmpntNo+'_'+i);
   stdBnft.checked=bnftCmpnt.checked;
}

}


function checkAll_ewpd(controller, table, columnNo, childPos) 
{
	
	var tableObject = document.getElementById(table);
	var chkname;
	var chkbox;
	if(columnNo == null || columnNo == undefined)
		columnNo = 1;
	if(childPos == null || childPos == undefined)
		childPos = 1;
		
	for(var i=0; i<tableObject.rows.length; i++) {
		chkbox = tableObject.rows[i].cells[columnNo-1].children[childPos-1];
		if (!(chkbox.disabled == true)) {
			chkbox.checked = controller.checked;
		}
	}
}
function cancel(){
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
}

</script>
</HTML>
