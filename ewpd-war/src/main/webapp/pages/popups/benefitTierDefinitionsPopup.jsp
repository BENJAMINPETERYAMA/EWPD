<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%> 
<%@ taglib uri="/wpd.tld" prefix="w"%>

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<meta http-equiv="x-ua-compatible" content="IE=EmulateIE8" > 
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	vertical-align: middle;
}
</style>
<TITLE>Select Tier</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>
<BASE target="_self" />
</HEAD>


<f:view>
	<body onunload="closePage();">
	    <h:form styleClass="form" id="tierPopup">
	    <TABLE>
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
												<w:messageForPopup></w:messageForPopup>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>
	    
		<h:inputHidden value="#{benefitTierBackingBean.tierPopupHidden}" id="tierPopupHidden" />
		<h:inputHidden value="#{benefitTierBackingBean.crtListSize}" id="crListSize"/>
		<h:inputHidden value="#{benefitTierBackingBean.saveString}" id="saveString"/>
		<h:inputHidden value="#{benefitTierBackingBean.entitySysId}" id="entitySysId" />
		<h:inputHidden value="#{benefitTierBackingBean.benefitComponentSysId}" id="benefitComponentSysId"/>
		<h:inputHidden value="#{benefitTierBackingBean.benefitDefinitionSysId}" id="benefitDefinitionSysId"/>
		<h:inputHidden value="#{benefitTierBackingBean.benefitDefinitionLevelId}" id="benefitDefinitionLevelId"/>
		<h:inputHidden value="#{benefitTierBackingBean.entityType}" id="entityType"/>
		<h:inputHidden value="#{benefitTierBackingBean.selectedtierDefSysId}" id="tierDefSysId"/>
		<h:inputHidden value="#{benefitTierBackingBean.closePage}" id="closePage"/>
		<h:inputHidden value="#{benefitTierBackingBean.mandatoryFieldsValid}" id="mandatoryFieldsValid"/>
		<h:inputHidden value="#{benefitTierBackingBean.tierDefListSize}" id="tierDefListSize"/>
		<h:inputHidden value="#{benefitTierBackingBean.submitFlag}" id="submitFlag"/>
		<h:inputHidden value="#{benefitTierBackingBean.dataType}" id="dataType"/>
								
	
		<table width="100%" align="center">
			<tr bgcolor="#cccccc">
				<TD align="center" width="100%" height="10px">
					<strong><h:outputText value="Tier Definitions"> </h:outputText> </strong> 
				</TD>
			</tr>
		</table>
		<div id="tierDiv" style="height:170px;overflow:auto;border:2px solid #cccccc;" class="popupDataTableDiv">
		<table width="99%" align="center"  bordercolor="red" border:"1px"  > 
			<tr>
				<td id="tempTable">
					<h:dataTable width="100%" id="tierDataTable" style="display:none;" value="#{benefitTierBackingBean.tierDefsList}" var="singleValue" 
						bgcolor="#cccccc" cellpadding="0" cellspacing="1"
        				rendered="#{benefitTierBackingBean.tierDefsList != null}" border="0" rowClasses="dataTableEvenRow,dataTableOddRow"
        				columnClasses="selectOrOptionColumn">
						 	<h:column>
							
								<t:selectOneRadio id="selectRadio" forceId="true" forceIdIndex="false" required="true" onclick="getSelectedId(this);"
									value="#{benefitTierBackingBean.selectedExtClientId}">
							        <f:selectItem itemValue="#{singleValue.benefitTierDefSysId}" itemLabel="#{singleValue.benefitTierDefinitionName}" />
						      </t:selectOneRadio>
							</h:column>
							<h:column>
								<h:inputHidden value="#{singleValue.dataType}"/>
							</h:column>	
					</h:dataTable>
				</td>
			</tr>
			<tr>
				<td><f:verbatim>&nbsp;</f:verbatim></td>
			</tr>
		</table>			
		</div>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr bgcolor="#cccccc" id="titleRow" style="display:none;">
				<td><div id="criteriaTitle"></div>
				</td>
			</tr>
			<tr>
				<td><h:panelGrid binding="#{benefitTierBackingBean.panalGrid}" id="criteriaTable"/>
				</td>
			</tr>
			
		</table>
		<table width="100%" cellpadding="4" cellspacing="4">
			<tr>
				<td>
					<h:commandLink id="saveTiering" style="hidden" action="#{benefitTierBackingBean.addTierDefinition}"> </h:commandLink>
					<h:commandButton styleClass="wpdbutton" id="saveBtn" value=" Add " style="display:none;" onclick="saveData(this);return false;"/>
				</td>
			</tr>
		</table>
			<h:commandLink id="showTierCriteriaSection" style="hidden" action="#{benefitTierBackingBean.showTierCriteriaSection}"> </h:commandLink>
	    </h:form>
	</body>
</f:view>
<script language="javascript">
var preSelection ='';
var mandatoryFieldsValid = "true";
var selectedTierDef = '';
var psblValu = '';
var dataType = '';
var clearValues = true;

function saveData(id){
id.disabled = true;
var inputString ='';
var cListSize = document.getElementById('tierPopup:crListSize').value;
	for(var i=0;i<cListSize;i++){
		if(document.getElementById(preSelection+'_'+i)!= null){
			if(trim(document.getElementById(preSelection+'_'+i).value) == '' || trim(document.getElementById(preSelection+'_'+i).value) == '-1') mandatoryFieldsValid = "false"; 
			inputString = inputString + document.getElementById(preSelection+'_'+i).value +'~';
			dataType = (document.getElementById(preSelection+'_'+i).title != null ? document.getElementById(preSelection+'_'+i).title :'');
		}
	}
document.getElementById('tierPopup:saveString').value = inputString;
document.getElementById('tierPopup:tierDefSysId').value = selectedTierDef;
document.getElementById('tierPopup:mandatoryFieldsValid').value = mandatoryFieldsValid;
document.getElementById('tierPopup:dataType').value = dataType;
document.getElementById('tierPopup:saveTiering').click();
}

function getSelectedId(obj){
var count = 0;
var parent = obj;
	while(parent.tagName.toLowerCase()!='tr'|| count<1){
		if(parent.tagName.toLowerCase()=='tr')count++; 
		parent=parent.parentNode;

	}
	//dataType = parent.childNodes[1].childNodes[0].value;
	if(document.getElementById('tierPopup:saveBtn').style.display == "none"){
		document.getElementById('tierPopup:saveBtn').style.display = "block";
		document.getElementById('titleRow').style.display = "block";
	}
	var crTitle = obj.parentNode.innerText;
	selectedTierDef = obj.value;
	document.getElementById('criteriaTitle').innerHTML = '<b>&nbsp;&nbsp;'+ crTitle +'</b>';
	if(preSelection != '' && preSelection != null){
		document.getElementById(preSelection).style.display = 'none';
	}
	document.getElementById('tierPopup:TD'+obj.value).style.display = 'block';
		preSelection = 'tierPopup:TD'+obj.value;
	refreshValues();
}
function refreshValues(){
var cListSize = document.getElementById('tierPopup:crListSize').value;
var saveStr = document.getElementById('tierPopup:saveString').value;
var j=0;
	for(var i=0;i<cListSize;i++){
		if(document.getElementById(preSelection+'_'+i)!= null){
				if(clearValues){
					 if(document.getElementById(preSelection+'_'+i).tagName !='SELECT'){
							document.getElementById(preSelection+'_'+i).value = '';
						}else document.getElementById(preSelection+'_'+i).value = -1;
				}else {
						if(saveStr.indexOf("~")!=-1){
							var inputVal = saveStr.split('~');
							document.getElementById(preSelection+'_'+i).value = (inputVal[j]!=null)?inputVal[j]:'';
							j++;
						}
					}
			document.getElementById(preSelection+'_'+i).parentNode.parentNode.getElementsByTagName('span')[0].className = "mandatoryNormal";
		}
	}
	if(document.getElementById('tierPopup:mandatoryFieldsValid').value == 'false') {
		for(var i=0;i<cListSize;i++){
			if(document.getElementById(preSelection+'_'+i)!= null && (document.getElementById(preSelection+'_'+i).value == '' ||document.getElementById(preSelection+'_'+i).value == '-1' )){
				document.getElementById(preSelection+'_'+i).parentNode.parentNode.getElementsByTagName('span')[0].className = "mandatoryError";
			}
		}
	}
	document.getElementById('tierPopup:mandatoryFieldsValid').value = 'true';
	clearValues = true;
}
function getSeleValue(){
window.returnValue = ''+preSelection;
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
	if(preSelection !='')
		return true;
	return false;
}
function closePage(){
	if(document.getElementById('tierPopup:closePage').value != null && document.getElementById('tierPopup:closePage').value != ''){
	if(document.getElementById('tierPopup:closePage').value == 'true'){
		window.returnValue = 'sucsess';
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
	}else if(document.getElementById('tierPopup:closePage').value == 'exception'){
		window.returnValue = 'exception';
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
 }
}
function stateMaintain(){
 	closePage();
    if(document.getElementById('tierPopup:tierDefSysId').value !='' && document.getElementById('tierPopup:tierDefSysId').value != null){
		var tblSize = document.getElementById('tierPopup:tierDefListSize').value;
		var selectedTier = document.getElementById('tierPopup:tierDefSysId').value;
		var tabObj = document.getElementById("tierPopup:tierDataTable").childNodes[0];
			for(var i=0;;i++){
				if(tabObj.getElementsByTagName('input')[i]==null) break;
				if(tabObj.getElementsByTagName('input')[i].value == selectedTier){
					clearValues = false;
					tabObj.getElementsByTagName('input')[i].click();
					break;
				}
			}
	}
	var submitFlag = document.getElementById('tierPopup:submitFlag').value;
	if(submitFlag == 'false'){
		document.getElementById('tierPopup:showTierCriteriaSection').click();
	}else if(document.getElementById('tierPopup:tierDataTable').style.display == 'none'){
		document.getElementById('tierPopup:tierDataTable').style.display = 'block';
	}
}
stateMaintain();
</script>
</HTML>
