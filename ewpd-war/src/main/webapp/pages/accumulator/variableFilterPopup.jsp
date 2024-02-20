<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
	
<TITLE>Variable Popup</TITLE>
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
	<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery-1.4.2.min.js"></script>
</HEAD>
<f:view>
	<BODY>
		<h:form id="variableSelectForm">
		<DIV id="messageDispDiv">
			</div>
			<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left"><h:commandButton id="selectButton"
					value="Select" styleClass="wpdbutton" onclick="getCheckedItems_SAccum('variableSelectForm:varDataTable');return false;"></h:commandButton>
				</td>
			</tr>
		</table>
			<table width="96%" align="center" cellpadding="0" cellspacing="0" >
			<TBODY>
				<TR>
					<TD>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
							<td align="left">
								<fieldset><legend>Filter By</legend>
									<input type="radio" id="searchItem1" name="searchItem" value="variableId" onclick="getSelectedSearchItem(this);" checked> Variable Id
									<input type="radio" id="searchItem2" name="searchItem" value="variableCat" onclick="getSelectedSearchItem(this);" > Category
									<br/>
									<h:inputText id="searchText" value="#{lgContractRefDataBean.searchText}" style="visibility:visible;"
											onkeypress="return getSearchVar(event,this);"></h:inputText>
									<div id="searchItemValueListDiv">
									<h:selectOneMenu id="searchItemValueList" value="#{lgContractRefDataBean.searchText}" style="visibility:hidden;"
										onkeypress="getSearchCat(event,this);">
									<f:selectItems value="#{lgContractRefDataBean.categoryForCombo}" />
									</h:selectOneMenu>
									</div>
								</fieldset>
							</td>
							</tr>
						</table>
					</TD>
				</TR>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
							<tr>
								<td width="6%" align="left"> &nbsp;</TD>
								<TD width="95px;" align="center"> <strong> <h:outputText value="Id"> </h:outputText> </strong> </td>
								<TD width="" align="center"> <strong> <h:outputText value="Description"> </h:outputText> </strong> </td>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class="popupDataTableDiv">
						
					</DIV>
					</td>
				</tr>
			</TBODY>
			</table>
		</h:form>
	</BODY>
</f:view>

<script language="javascript">


	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj.value) {
		matchCheckboxItems('variableSelectForm:stateCodeDataTable',hiddenObj);

	}
	
	function getSearchCat(thisEvent,thisObj){
	if(thisEvent.keyCode=='13'){
			var elementId = thisObj.id;
			var searchText = thisObj.value;
			//alert(searchText);
			//To be changed to javascritp if needed.
			//Ajax call
			 var url = "../accumulator/ajaxVariableFilterPopupHelper.jsp"+getUrl();
			 var params = "variableCategory="+searchText+"&temp="+(Math.random()*100);
			 var divId1 = "popupDataTableDiv";
			 var divId2 = "messageDispDiv";
			 // alert(url);
			 //alert(params);
			 ajaxCall(url,params,divId1,divId2);
			 return false; 
		}
	}
	
		function getSearchVar(thisEvent,thisObj){
		if(thisEvent.keyCode=='13'){
			var elementId = thisObj.id;
			var searchText = thisObj.value;
			//alert(searchText);
			//To be changed to javascritp if needed.
			//Ajax call
			 var url = "../accumulator/ajaxVariableFilterPopupHelper.jsp"+getUrl();
			 var params = "variableId="+searchText+"&temp="+(Math.random()*100);
			 var divId1 = "popupDataTableDiv";
			 var divId2 = "messageDispDiv";
			 // alert(url);
			 //alert(params);
			 ajaxCall(url,params,divId1,divId2);
			 return false; 
		}
	}
	

	function getSelectedSearchItem(thisObj){
		var selectedSearchItem = thisObj.value;
		if(selectedSearchItem == "variableCat"){
			document.getElementById("variableSelectForm:searchItemValueList").style.visibility = "visible";
			document.getElementById("variableSelectForm:searchItemValueList").style.display = "inline";
			document.getElementById("variableSelectForm:searchText").style.visibility = "hidden";
			document.getElementById("variableSelectForm:searchText").style.display = "none";
			
		}else{
			document.getElementById("variableSelectForm:searchItemValueList").style.visibility = "hidden";
			document.getElementById("variableSelectForm:searchText").style.visibility = "visible";
			document.getElementById("variableSelectForm:searchItemValueList").style.display = "none";
			document.getElementById("variableSelectForm:searchText").style.display = "inline";
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
			if(divId1 == "popupDataTableDiv"){
				diVData1 = data.substring ((data.indexOf('**$'))+3,data.indexOf('@@$'));
			}
		
			document.getElementById(divId1).innerHTML = diVData1;
			document.getElementById(divId2).innerHTML = diVData2;
			tigra_tables('variableSelectForm:varDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');			
		    }
		});
	     			
	}

</script>
</HTML>