<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">

<TITLE>Accumulator Popup</TITLE>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery-1.4.2.min.js"></script>
</HEAD>
<f:view>
	<BODY>
	<h:form id="accumulatorPopupForm">
		<h:inputHidden id="hiddenBenefitandQuestionForAccum" value="#{param['hiddenBenefitandQuestionForAccum']}"></h:inputHidden>
		<h:inputHidden id="fromSearchPage" value="#{param['fromSearchPage']}"></h:inputHidden>
		
		
		<DIV id="messageDispDiv">
		<TABLE width="100%"><TR><TD align="left"><span id="messag"><w:message/></span></TD></TR></TABLE>
			</div>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left"><h:commandButton id="selectButton"
					value="Select" styleClass="wpdbutton" onclick="getCheckedItems_accum('accumulatorPopupForm:accumulatorDataTable','accumulatorPopupForm:fromSearchPage');return false;"></h:commandButton>
				</td>
			</tr>
		</table>
		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<tr>
					<td align="left">
					<fieldset><legend>Filter By</legend>
						<input type="radio" id="searchItem1" name="searchItem" value="accumDescription" onclick="getSelectedSearchItem(this);" checked> Description
						<input type="radio" id="searchItem2" name="searchItem" value="providerArr" onclick="getSelectedSearchItem(this);" > PVA
						<input type="radio" id="searchItem3" name="searchItem" value="costShareType" onclick="getSelectedSearchItem(this);" > Cost Share Type
						<br/>
						<h:inputText id="searchText" value="#{lgContractRefDataBean.searchText}" style="visibility:visible;"
						onkeypress="return getSearchText(event,this);"></h:inputText>
						<div id="searchItemValueListDiv">
							<h:selectOneMenu id="searchItemValueList" value="#{lgContractRefDataBean.searchText}" style="visibility:hidden;"
							onkeypress="getSearchText(event,this);">
								<f:selectItems value="#{lgContractRefDataBean.searchItemValueList}" />
							</h:selectOneMenu>
						</div>
					</fieldset>
					</td>
				</tr>
			</TBODY>
		</table>
			<br/>
		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<tr>
					<td>
					
						<div id="accumulatorDataTableDiv" style="height:292px" >
							<h:dataTable headerClass="dataTableHeader" cellspacing="1" width="100%" id="accumulatorDataTable" rowClasses="dataTableEvenRow,dataTableOddRow" width="100%"
							value="#{lgContractRefDataBean.accumumlatorValues}" var="accumObj"
							cellpadding="0"cellspacing="1" bgcolor="#cccccc">							
							<h:column>
							<f:facet name="header">
           						<h:selectBooleanCheckbox onclick="checkAllid(this,'accumulatorPopupForm:accumulatorDataTable','accumulatorPopupForm:accumulatorCodeChkBox'); "></h:selectBooleanCheckbox>
        					</f:facet>
								<f:verbatim> <h:selectBooleanCheckbox id="accumulatorCodeChkBox"> </h:selectBooleanCheckbox></f:verbatim>
							</h:column>
							<h:column>
							<f:facet name="header">
           						<h:outputText  value="Name"/>
        					</f:facet>
								<h:outputText value="#{accumObj.svcCde}"></h:outputText>
								<h:inputHidden value="#{accumObj.svcCde}@@#{accumObj.moniesFlg}@@#{accumObj.daysFlg}@@#{accumObj.occursFlg}@@#{accumObj.inClaimsFlg}"></h:inputHidden>
							</h:column>
							<h:column>
							<f:facet name="header">
           						<h:outputText  value="Description"/>
        					</f:facet>
								<h:outputText value="#{accumObj.name}"></h:outputText>
							</h:column>
							<h:column>
							<f:facet name="header">
           						<h:outputText  value="PVA"/>
        					</f:facet>
								<h:outputText value="#{accumObj.pva}"></h:outputText>
							</h:column>
							<h:column>
							
        					<f:facet name="header">
           						<h:outputText  value="Cost Share Type"/>
        					</f:facet>
								<h:outputText value="#{accumObj.cstTyp}"></h:outputText>
							</h:column>
						</h:dataTable>
						</div>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>
	</BODY>
</f:view>
<script type="text/javascript">
	jQuery.noConflict();  
	tigra_tables('accumulatorPopupForm:accumulatorDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
 	document.getElementById("accumulatorPopupForm:searchText").value="";
 	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
		matchCheckboxItems_accum('accumulatorPopupForm:accumulatorDataTable',hiddenObj);
	}
	
	function getSelectedSearchItem(thisObj){
	var selectedSearchItem = thisObj.value;
		if(selectedSearchItem == "providerArr" || selectedSearchItem == "costShareType"){
			document.getElementById("accumulatorPopupForm:searchItemValueList").style.visibility = "visible";
			document.getElementById("accumulatorPopupForm:searchItemValueList").style.display = "inline";
			document.getElementById("accumulatorPopupForm:searchText").style.visibility = "hidden";
			document.getElementById("accumulatorPopupForm:searchText").style.display = "none";
			
			//ajax call for fetching PVA/CST
			var url = "../accumulator/ajaxAccumulatorPopUpHelper.jsp"+getUrl();
			var params = "selectedSearchItem="+selectedSearchItem;
			var divId1 = "searchItemValueListDiv";
			var divId2 = "messageDispDiv";
			ajaxCall(url,params,divId1,divId2);
			
		}else{
			document.getElementById("accumulatorPopupForm:searchItemValueList").style.visibility = "hidden";
			document.getElementById("accumulatorPopupForm:searchText").style.visibility = "visible";
			document.getElementById("accumulatorPopupForm:searchItemValueList").style.display = "none";
			document.getElementById("accumulatorPopupForm:searchText").style.display = "inline";
		}
	}
	
	function getSearchText(thisEvent,thisObj){
	//WAS 6.0 Migration Changes - Code changes for Standard Accumulator Menu issue
	   //if(thisEvent.keyCode=='13'){ 
		if(thisEvent.keyCode=='13' ||thisEvent.keyCode=='0' ){
			var elementId = thisObj.id;
			var searchText = thisObj.value;
			
			var selectedSearchItem = jQuery('input[name=searchItem]:checked', '#accumulatorPopupForm').val();
			//Ajax call
			var hiddenBenefitandQuestionForAccum = document.getElementById("accumulatorPopupForm:hiddenBenefitandQuestionForAccum").value;
			var url = "../accumulator/ajaxAccumulatorPopUpHelper.jsp"+getUrl();
			
			if(hiddenBenefitandQuestionForAccum!= ""){
			var params = "selectedSearchItem="+selectedSearchItem+"&searchText="+searchText+"&hiddenBenefitandQuestionForAccum="+hiddenBenefitandQuestionForAccum;
			}
			var divId1 = "accumulatorDataTableDiv";
			var divId2 = "messageDispDiv";
			ajaxCall(url,params,divId1,divId2);
			return false;
		}
	}

	function ajaxCall(url,params,divId1,divId2){
		
		jQuery.ajax({
			url: url,
			data: params,
			success: function(data) {
			var  diVData1 = "";
			var  diVData2 = "";
			if(divId2 == "messageDispDiv"){
				diVData2 = data.substring ((data.indexOf('$##'))+3,data.indexOf('~$~'));
			}
			if(divId1 == "searchItemValueListDiv"){
				diVData1 = data.substring ((data.indexOf('$#~'))+3,data.indexOf('$$~'));
			}else if(divId1 = "accumulatorDataTableDiv"){
				diVData1 = data.substring ((data.indexOf('**$'))+3,data.indexOf('@@$'));
				document.getElementById(divId2).innerHTML = diVData2;
			}
			
			document.getElementById(divId1).innerHTML = diVData1;
			
			tigra_tables('accumulatorPopupForm:accumulatorDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');			
		    }
		});
	     			
	}

</script>
</HTML>
