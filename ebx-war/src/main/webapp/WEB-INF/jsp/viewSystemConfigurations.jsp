<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>

<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<script>
	var blankRowIsActive = "false";		

	function displayEditConfiguration(button){
		var systemConfigurationID = getSystemConfigurationID(button);
		$('#lnkConfirm'+systemConfigurationID).show();
		$('#lnkDiscard'+systemConfigurationID).show();		
		$('#lnkEdit'+systemConfigurationID).hide();
		$('#lnkDelete'+systemConfigurationID).hide();	

		setValuesBeforeEdit(systemConfigurationID);
		
		$('#lblBackEndRegion'+systemConfigurationID).hide();
		$('#lblBackEndRegionDescription'+systemConfigurationID).hide();
		$('#lblSystem'+systemConfigurationID).hide();
		$('#lblFunctionality'+systemConfigurationID).hide();
		$('#lblEnvironment'+systemConfigurationID).hide();
		$('#lblSenderID'+systemConfigurationID).hide();
		$('#lblSourceEnvironment'+systemConfigurationID).hide();
		$('#lblDestinationEnvironment'+systemConfigurationID).hide();

		$('#txtBackEndRegion'+systemConfigurationID).show();
		$('#txtBackEndRegionDescription'+systemConfigurationID).show();
		$('#ddlSystem'+systemConfigurationID).show();
		$('#ddlFunctionality'+systemConfigurationID).show();
		$('#ddlEnvironment'+systemConfigurationID).show();
		$('#txtSenderID'+systemConfigurationID).show();
		$('#txtSourceEnvironment'+systemConfigurationID).show();
		$('#txtDestinationEnvironment'+systemConfigurationID).show();

	}
	
	
	function setValuesBeforeEdit(systemConfigurationID){
		var backEndRegionLabelValue = $('#lblBackEndRegion'+systemConfigurationID).text();
		var backEndRegionDescriptionLabelValue = $('#lblBackEndRegionDescription'+systemConfigurationID).text();
		var systemLabelValue = $('#lblSystem'+systemConfigurationID).text();		
		var functionalityLabelValue = $('#lblFunctionality'+systemConfigurationID).text();
		var environmentLabelValue = $('#lblEnvironment'+systemConfigurationID).text();
		var senderIDLabelValue = $('#lblSenderID'+systemConfigurationID).text();
		var sourceEnvironmentLabelValue = $('#lblSourceEnvironment'+systemConfigurationID).text();
		var destinationEnvironmentLabelValue = $('#lblDestinationEnvironment'+systemConfigurationID).text();
		
		$('#txtBackEndRegion'+systemConfigurationID).val($.trim(backEndRegionLabelValue));
		$('#txtBackEndRegionDescription'+systemConfigurationID).val($.trim(backEndRegionDescriptionLabelValue));
		$("#ddlSystem"+systemConfigurationID+" option[value=" + $.trim(systemLabelValue) +"]").attr("selected","selected") ;
		$("#ddlFunctionality"+systemConfigurationID+" option[value=" + $.trim(functionalityLabelValue) +"]").attr("selected","selected") ;
		$("#ddlEnvironment"+systemConfigurationID+" option[value=" + $.trim(environmentLabelValue) +"]").attr("selected","selected") ;		
		$('#txtSenderID'+systemConfigurationID).val($.trim(senderIDLabelValue));
		$('#txtSourceEnvironment'+systemConfigurationID).val($.trim(sourceEnvironmentLabelValue));
		$('#txtDestinationEnvironment'+systemConfigurationID).val($.trim(destinationEnvironmentLabelValue));
	}
	
	function editConfiguration(button){
	
		var errorFlag = false;
		var systemConfigurationID = getSystemConfigurationID(button);		
		var backEndRegion = $('#txtBackEndRegion'+systemConfigurationID).val();
		var backEndRegionDescription = $('#txtBackEndRegionDescription'+systemConfigurationID).val();
		var system = $('#ddlSystem'+systemConfigurationID).val();
		var functionality = $('#ddlFunctionality'+systemConfigurationID).val();
		var environment = $('#ddlEnvironment'+systemConfigurationID).val();
		var senderID = $('#txtSenderID'+systemConfigurationID).val();
		var sourceEnvironment = $('#txtSourceEnvironment'+systemConfigurationID).val();
		var destinationEnvironment = $('#txtDestinationEnvironment'+systemConfigurationID).val();
		
		/*
		
		 if (backEndRegion == null || backEndRegion == "") {
		 	addErrorToNotificationTray('Back End Region is mandatory');
		 	var errorFlag = true;
		 }	
		 */	 
		 if (senderID == null || senderID == "") {
		 	addErrorToNotificationTray('Sender ID/SrcLgcl is mandatory');
		 	var errorFlag = true;
		 }
		 /*
		 if (backEndRegion == null || backEndRegion == "") {
		 	addErrorToNotificationTray('Back End Region is mandatory');
		 	var errorFlag = true;
		 }*/		 
		 if(errorFlag){
		 	openTrayNotification();
		 }else{
		 		$.ajax({
					url: "${context}/systemconfiguration/editSystemConfiguration.ajax",
					dataType: "json",
					type: "POST",			
					data: 	"id="+systemConfigurationID+
							"&bckEndRegion="+backEndRegion+
							"&bckEndRegionDesc="+backEndRegionDescription+
							"&system="+system+
							"&functionality="+functionality+
							"&env="+environment+
							"&senderID="+senderID+
							"&srcEnv="+sourceEnvironment+
							"&destEnv="+destinationEnvironment,
					success: function(data) {
						cache: false,
						addMessages(data);
						if(data.updatestatus == 'updated'){
							$('#tblSystemConfiguration tbody').empty();
							refreshConfigurationData(data.configurationAsJsonArray);
						}
					}
				});	
		 	}	 
		}
	
		function refreshConfigurationData(jsonObjects){
			blankRowIsActive = "false";
			var table = document.getElementById("tblSystemConfiguration");
			var tbody = document.getElementById("systemConfigurationTableBody");
			var jsonObjectCount = countProperties(jsonObjects);
			var rowCount = 0;
			
			if(jsonObjectCount == 0){
				var tableRow = $('<tr class="alternate" id="tableRowNoData">');
				tableRow.appendTo(tbody);
				var tdNoDataFound = $('<td colspan="9" align="center">').appendTo(tableRow);
				$('<label ><b> No Data Found </label><b>').appendTo(tdNoDataFound);
			}else{
				for(var i=0; i<jsonObjectCount; i++){
					if (rowCount % 2 == 0) {
						trClass = "alternate";
					}else {
						trClass = "white-bg";
					}		
					
					var systemConfigurationID = jsonObjects[i].systemConfigurationID;				
					var system = jsonObjects[i].system;
					var functionality = jsonObjects[i].functionality;
					var environment= jsonObjects[i].environment;
					var backEndRegion = jsonObjects[i].backEndRegion;
					var backEndRegionDescription = jsonObjects[i].backEndRegionDescription;
					var senderID = jsonObjects[i].senderID;
					var sourceEnvironment = jsonObjects[i].sourceEnvironment;
					var destinationEnvironment = jsonObjects[i].destinationEnvironment;
					var systemConfigurationID = jsonObjects[i].systemConfigurationID;
									
					var tableRow = $('<tr style="WIDTH: 100%;" class="'+trClass+'">');
					
					var tdBackEndRegion = $('<td width="10%" align="left">').appendTo(tableRow);								
					$('<input type="hidden" value="'+systemConfigurationID+'" id="hidID'+systemConfigurationID+'"/>').appendTo(tdBackEndRegion);				
					$('<label id="lblBackEndRegion'+systemConfigurationID+'">'+backEndRegion+'</label>').appendTo(tdBackEndRegion);
					$('<input type="text" maxlength="25" id="txtBackEndRegion'+systemConfigurationID+'" style="display: none;WIDTH: 95%;" >').appendTo(tdBackEndRegion);
					var tdBackEndRegionDescription = $('<td width="25%" align="left">').appendTo(tableRow);
					$('<label id="lblBackEndRegionDescription'+systemConfigurationID+'">'+backEndRegionDescription+'</label>').appendTo(tdBackEndRegionDescription);
					$('<input type="text" maxlength="75" id="txtBackEndRegionDescription'+systemConfigurationID+'" style="display: none;WIDTH: 95%;" >').appendTo(tdBackEndRegionDescription);
					var tdSystem = $('<td width="10%" align="left">').appendTo(tableRow);
					$('<label id="lblSystem'+systemConfigurationID+'">'+system+'</label>').appendTo(tdSystem);
					$('<select id="ddlSystem'+systemConfigurationID+'" style="display: none;WIDTH: 100%;font-family:Arial,sans-serif;font-size:12px;"><option value="ACES">ACES</option><option value="FACETS">FACETS</option><option value="WGS/STAR">WGS/STAR</option><option value="OTHER">OTHER</option></select>').appendTo(tdSystem);				
					var tdFunctionality = $('<td width="13%" align="left">').appendTo(tableRow);
					$('<label id="lblFunctionality'+systemConfigurationID+'">'+functionality+'</label>').appendTo(tdFunctionality);
					$('<select id="ddlFunctionality'+systemConfigurationID+'" style="display: none;WIDTH: 100%;font-family:Arial,sans-serif;font-size:12px;"><option value="GENERATE271">GENERATE271</option><option value="STATIC REPORT">STATIC REPORT</option>').appendTo(tdFunctionality);				
					var tdEnvironment = $('<td width="12%" align="left">').appendTo(tableRow);				
					$('<label id="lblEnvironment'+systemConfigurationID+'">'+environment+'</label>').appendTo(tdEnvironment);
					$('<select id="ddlEnvironment'+systemConfigurationID+'" style="display: none;WIDTH: 100%;font-family:Arial,sans-serif;font-size:12px;"><option value="PRODUCTION">PRODUCTION</option><option value="TEST">TEST</option>').appendTo(tdEnvironment);	
					var tdSenderID = $('<td width="10%" align="left">').appendTo(tableRow);				
					$('<label id="lblSenderID'+systemConfigurationID+'">'+senderID+'</label>').appendTo(tdSenderID);
					$('<input type="text" maxlength="25" id="txtSenderID'+systemConfigurationID+'" style="display: none;WIDTH: 95%;">').appendTo(tdSenderID);
					var tdSourceEnvironment = $('<td width="7%" align="left">').appendTo(tableRow);				
					$('<label id="lblSourceEnvironment'+systemConfigurationID+'">'+sourceEnvironment+'</label>').appendTo(tdSourceEnvironment);
					$('<input type="text" maxlength="25" id="txtSourceEnvironment'+systemConfigurationID+'" style="display: none;WIDTH: 95%;">').appendTo(tdSourceEnvironment);
					var tdDestinationEnvironment = $('<td width="8%" align="left">').appendTo(tableRow);				
					$('<label id="lblDestinationEnvironment'+systemConfigurationID+'">'+destinationEnvironment+'</label>').appendTo(tdDestinationEnvironment);
					$('<input type="text" maxlength="25" id="txtDestinationEnvironment'+systemConfigurationID+'" style="display: none;WIDTH: 95%;">').appendTo(tdDestinationEnvironment);				
					var tdButtons = $('<td class="tblSystemConfigurationButton" width="4%;" align="left">').appendTo(tableRow);				
					$('<a href="#" id="lnkEdit'+systemConfigurationID+'"><img src="${imageDir}/edit_icon.gif"  title="Edit Configuration" onclick="displayEditConfiguration(this);"/></a>').appendTo(tdButtons);
					$('<a href="#" id="lnkConfirm'+systemConfigurationID+'" style="display: none;" ><img src="${imageDir}/confirm_icon.JPG"  title="Confirm and save" onclick="editConfiguration(this);"/></a>').appendTo(tdButtons);
					$('<a href="#" id="lnkDelete'+systemConfigurationID+'"><img src="${imageDir}/markAsNotApp.gif"  title="Delete Configuration" onclick="deleteSystemConfiguration(this);" /></a>').appendTo(tdButtons);
					$('<a href="#" id="lnkDiscard'+systemConfigurationID+'" style="display: none;"><img src="${imageDir}/discard_icon.bmp"  title="Discard Change" onclick="discardEdit(this);" /></a>').appendTo(tdButtons);									
					rowCount = parseInt(rowCount) + 1;
					tableRow.appendTo(tbody);
					//tbody.appendTo(table);
				}
			}
			document.getElementById("totalDataRows").value = rowCount;
		}
	
	
	function countProperties(obj) {
		var prop;
		var propCount = 0;
		for (prop in obj) {
		propCount++;
		}
		return propCount;
	}	

	function discardEdit(button){
		var systemConfigurationID = getSystemConfigurationID(button);
		$('#lnkConfirm'+systemConfigurationID).hide();
		$('#lnkDiscard'+systemConfigurationID).hide();
		$('#lnkEdit'+systemConfigurationID).show();
		$('#lnkDelete'+systemConfigurationID).show();		
		
		$('#lblBackEndRegion'+systemConfigurationID).show();
		$('#lblBackEndRegionDescription'+systemConfigurationID).show();
		$('#lblSystem'+systemConfigurationID).show();
		$('#lblFunctionality'+systemConfigurationID).show();
		$('#lblEnvironment'+systemConfigurationID).show();
		$('#lblSenderID'+systemConfigurationID).show();
		$('#lblSourceEnvironment'+systemConfigurationID).show();
		$('#lblDestinationEnvironment'+systemConfigurationID).show();	
		
		$('#txtBackEndRegion'+systemConfigurationID).hide();
		$('#txtBackEndRegionDescription'+systemConfigurationID).hide();
		$('#ddlSystem'+systemConfigurationID).hide();
		$('#ddlFunctionality'+systemConfigurationID).hide();
		$('#ddlEnvironment'+systemConfigurationID).hide();
		$('#txtSenderID'+systemConfigurationID).hide();
		$('#txtSourceEnvironment'+systemConfigurationID).hide();
		$('#txtDestinationEnvironment'+systemConfigurationID).hide();
		
	}
	
	function getSystemConfigurationID(button){	
		var systemConfigurationID = 0;
		var parent=button.parentNode;
		while(parent.nodeName.toLowerCase()!='tr'){
			parent=parent.parentNode;
		}	
		var firstCell = parent.cells[0];
		var inputs = firstCell.getElementsByTagName('input');
		for (i=0; i<inputs.length; i++){
		    if (inputs[i].type == 'hidden'){
		    	systemConfigurationID = inputs[i].value;
		    	break;
		    }
		}
		return systemConfigurationID;	
	}
	
	
	function addNewRowToCreateConfiguration(){
	
		if(blankRowIsActive == "false"){
			var rowCount = document.getElementById("totalDataRows").value;
			if(rowCount == 0){				
				$('#tableRowNoData').hide();
			}
			var table = document.getElementById("tblSystemConfiguration");
			var tbody = document.getElementById("systemConfigurationTableBody");
			var trClass = "";
			if (rowCount % 2 == 0) {
				trClass = "alternate";
			}else {
				trClass = "white-bg";
			}
			var row = document.createElement("TR");			
			row.className=trClass;
			row.setAttribute("id","tableRowAddSystemConfiguration");
			row.setAttribute("width","100%");
			
			
			var tdBackEndRegion = document.createElement("TD");
			tdBackEndRegion.style.width = "10%";
			//tdBackEndRegion.style.paddingRight = "5px";			
			//tdBackEndRegion.style.align = "left";
			tdBackEndRegion.setAttribute("align","left");			
						
			txtBackEndRegion = document.createElement("input");			
			txtBackEndRegion.setAttribute("type","text");
			txtBackEndRegion.setAttribute("value","");
			txtBackEndRegion.style.width = "95%";
			txtBackEndRegion.setAttribute("id","txtBackEndRegionToAdd");			
			txtBackEndRegion.setAttribute("maxLength", "25");
			
			tdBackEndRegion.appendChild(txtBackEndRegion);
			
			var tdBackEndRegionDesc = document.createElement("TD");
			tdBackEndRegionDesc.style.width = "25%";
			//tdBackEndRegionDesc.style.paddingRight = "5px";
			tdBackEndRegionDesc.setAttribute("align","left");			
			txtBackEndRegionDesc = document.createElement("input");					
			txtBackEndRegionDesc.setAttribute("type","text");
			txtBackEndRegionDesc.setAttribute("value","");
			txtBackEndRegionDesc.setAttribute("id","txtBackEndRegionDescToAdd");
			txtBackEndRegionDesc.style.width = "95%";				
			txtBackEndRegionDesc.setAttribute("maxLength", "75");
			tdBackEndRegionDesc.appendChild(txtBackEndRegionDesc);
			
			
			var tdSystem = document.createElement("TD");
			tdSystem.style.width = "10%";
			//tdSystem.style.paddingRight = "5px";	
			tdSystem.setAttribute("align","left");		
			ddlSystem=document.createElement("select");			
			ddlSystem.setAttribute("id","ddlSystemToAdd");	
			ddlSystem.style.width = "100%";		
			ddlSystem.style.fontFamily = "Arial";
			ddlSystem.style.fontSize = "12px";
			systemOptionAces = document.createElement("option");
			systemOptionAces.value="ACES";
			systemOptionAces.text="ACES";
			ddlSystem.options.add(systemOptionAces);
			var systemOptionFacets = document.createElement("option");
			systemOptionFacets.value="FACETS";
			systemOptionFacets.text="FACETS";
			ddlSystem.options.add(systemOptionFacets);
			var systemOptionWgsOrStar = document.createElement("option");
			systemOptionWgsOrStar.value="WGS/STAR";
			systemOptionWgsOrStar.text="WGS/STAR";
			ddlSystem.options.add(systemOptionWgsOrStar);			
			var systemOptionAny = document.createElement("option");
			systemOptionAny.value="OTHER";
			systemOptionAny.text="OTHER";
			ddlSystem.options.add(systemOptionAny);		
			tdSystem.appendChild(ddlSystem);
			var tdFunctionality = document.createElement("TD");
			tdFunctionality.style.width = "13%";
			//tdFunctionality.style.paddingRight = "5px";	
			tdFunctionality.setAttribute("align","left");		
			ddlFunctionality=document.createElement("select");
			ddlFunctionality.setAttribute("id","ddlFunctionalityToAdd");
			ddlFunctionality.style.width = "100%";			
			ddlFunctionality.style.fontFamily = "Arial,sans-serif";
			ddlFunctionality.style.fontSize  = "12px";
			
			
			functionalityOptionGenerate271 = document.createElement("option");
			functionalityOptionGenerate271.value="GENERATE271";
			functionalityOptionGenerate271.text="GENERATE271";
			ddlFunctionality.options.add(functionalityOptionGenerate271);
			var functionalityOptionStaticReport = document.createElement("option");
			functionalityOptionStaticReport.value="STATIC REPORT";
			functionalityOptionStaticReport.text="STATIC REPORT";
			ddlFunctionality.options.add(functionalityOptionStaticReport)
			tdFunctionality.appendChild(ddlFunctionality);				
			
			var tdEnvironment = document.createElement("TD");
			tdEnvironment.style.width = "12%";
			//tdEnvironment.style.paddingRight = "5px";
			tdEnvironment.setAttribute("align","left");
			ddlEnvironment=document.createElement("select");
			ddlEnvironment.setAttribute("id","ddlEnvironmentToAdd");
			ddlEnvironment.style.width = "100%";
			
			ddlEnvironment.style.fontFamily = "Arial,sans-serif";
			ddlEnvironment.style.fontSize = "12px";
			
			environmentOptionProduction = document.createElement("option");
			environmentOptionProduction.value="PRODUCTION";
			environmentOptionProduction.text="PRODUCTION";
			ddlEnvironment.options.add(environmentOptionProduction);
			var environmentOptionTest = document.createElement("option");
			environmentOptionTest.value="TEST";
			environmentOptionTest.text="TEST";
			ddlEnvironment.options.add(environmentOptionTest);	
			tdEnvironment.appendChild(ddlEnvironment);
			
			
			var tdSenderID = document.createElement("TD");
			tdSenderID.style.width = "10%";
			//tdSenderID.style.paddingRight = "5px";
			tdSenderID.setAttribute("align","left");
			txtSenderID = document.createElement("input");	
			txtSenderID.style.width = "95%";			
			txtSenderID.setAttribute("type","text");
			txtSenderID.setAttribute("value","");
			txtSenderID.setAttribute("id","txtSenderIDToAdd");
			txtSenderID.setAttribute("maxLength", "25");
			tdSenderID.appendChild(txtSenderID);
			
			
			var tdSrcEnv = document.createElement("TD");
			tdSrcEnv.style.width = "7%";
			//tdSrcEnv.style.paddingRight = "5px";
			tdSrcEnv.setAttribute("align","left");
			txtSrcEnv = document.createElement("input");
			txtSrcEnv.style.width = "95%";				
			txtSrcEnv.setAttribute("type","text");
			txtSrcEnv.setAttribute("value","");
			txtSrcEnv.setAttribute("id","txtSrcEnvToAdd");
			txtSrcEnv.setAttribute("maxLength", "25");
			tdSrcEnv.appendChild(txtSrcEnv);			
			
			var tdDestEnv = document.createElement("TD");
			tdDestEnv.style.width = "8%";
			//tdDestEnv.style.paddingRight = "5px";
			tdDestEnv.setAttribute("align","left");	
			txtDestEnv = document.createElement("input");
			txtDestEnv.style.width = "95%";			
			txtDestEnv.setAttribute("type","text");
			txtDestEnv.setAttribute("value","");
			txtDestEnv.setAttribute("id","txtDestEnvToAdd");
			txtDestEnv.setAttribute("maxLength", "25");
			tdDestEnv.appendChild(txtDestEnv);			
			
			var tdButtons = document.createElement("TD");
			tdButtons.style.width = "4%";
			//tdButtons.style.paddingRight = "5px";
			tdButtons.setAttribute("align","left");	
			tdButtons.setAttribute("class","tblSystemConfigurationButton");
			hrefConfirm = document.createElement("a");
			hrefConfirm.setAttribute("href","#");
			imgConfirm=document.createElement("img");			
			imgConfirm.setAttribute('src', '${imageDir}/confirm_icon.JPG');
			//imgConfirm.setAttribute('onclick', 'addSystemConfiguration();');
			
			
			imgConfirm.onclick = function() {
				addSystemConfiguration();
			}			
			
			imgConfirm.setAttribute('title', 'Confirm and save');
			hrefConfirm.appendChild(imgConfirm);
			tdButtons.appendChild(hrefConfirm);
			
			hrefDiscard = document.createElement("a");
			hrefDiscard.setAttribute("href","#");
			imgDiscard=document.createElement("img");			
			imgDiscard.setAttribute('src', '${imageDir}/discard_icon.bmp');
			imgDiscard.onclick = function() {
				discardAddSystemConfiguration();
			}
			//imgDiscard.setAttribute('onclick', 'discardAddSystemConfiguration();');
			imgDiscard.setAttribute('title', 'Discard Change');
			hrefDiscard.appendChild(imgDiscard);
			tdButtons.appendChild(hrefDiscard);
			
			
			row.appendChild(tdBackEndRegion);
			row.appendChild(tdBackEndRegionDesc);
			row.appendChild(tdSystem);
			row.appendChild(tdFunctionality);
			row.appendChild(tdEnvironment);
			row.appendChild(tdSenderID);
			row.appendChild(tdSrcEnv);
			row.appendChild(tdDestEnv);
			row.appendChild(tdButtons);
			tbody.appendChild(row);
			table.appendChild(tbody);
			//document.getElementById("totalDataRows").value = parseInt(rowCount)+1;
			blankRowIsActive = "true";			
		}
		
	}
	
	function discardAddSystemConfiguration(){
		$('#tableRowAddSystemConfiguration').remove();
		blankRowIsActive = "false";
		var rowCount = document.getElementById("totalDataRows").value;
		if(rowCount == 0){				
			$('#tableRowNoData').show();
		}		
	}
	function addSystemConfiguration(){
		var backEndRegion = $('#txtBackEndRegionToAdd').val();
		var backEndRegionDescription = $('#txtBackEndRegionDescToAdd').val();
		var system = $('#ddlSystemToAdd').val();
		var functionality = $('#ddlFunctionalityToAdd').val();
		var environment = $('#ddlEnvironmentToAdd').val();
		var senderID = $('#txtSenderIDToAdd').val();
		var sourceEnvironment = $('#txtSrcEnvToAdd').val();
		var destinationEnvironment = $('#txtDestEnvToAdd').val();
		
		
		 if (senderID == null || senderID == "") {
		 	addErrorToNotificationTray('Sender ID/SrcLgcl is mandatory');
		 	var errorFlag = true;
		 }
		 
		 if(errorFlag){
		 	openTrayNotification();
		 }else{
		 		$.ajax({
				url: "${context}/systemconfiguration/addSystemConfigurations.ajax",
				dataType: "json",
				type: "POST",			
				data: 	"&bckEndRegion="+backEndRegion+
						"&bckEndRegionDesc="+backEndRegionDescription+
						"&system="+system+
						"&functionality="+functionality+
						"&env="+environment+
						"&senderID="+senderID+
						"&srcEnv="+sourceEnvironment+
						"&destEnv="+destinationEnvironment,
				success: function(data) {
				cache: false,
				addMessages(data);
				if(data.updatestatus == 'inserted'){
					blankRowIsActive = "false";	
					$('#tblSystemConfiguration tbody').empty();
					refreshConfigurationData(data.configurationAsJsonArray);
				}
			}
		});
	}	
}
	
	function deleteSystemConfiguration(button){
		var systemConfigurationID = getSystemConfigurationID(button);
		var deleteMessage = "Configuration will be deleted. Do you wish to continue?"
 		$("#confirmationDivDeleteAction").html(deleteMessage);
		$("#confirmationDivDeleteAction").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:140,
			width:400,
			modal: true,
			buttons: {			
			No: function() {
				$(this).dialog('close');
				},		
			Yes: function() {
					deleteConfiguration(systemConfigurationID);
					$(this).dialog('close');
				}
			}
		});
		$("#confirmationDivDeleteAction").dialog('open');		
	}
	
	function deleteConfiguration(systemConfigurationID){
		 		$.ajax({
					url: "${context}/systemconfiguration/deleteSystemConfiguration.ajax",
					dataType: "json",
					type: "POST",			
					data: 	"id="+systemConfigurationID,
					success: function(data) {
						cache: false,
						addMessages(data);
						if(data.updatestatus == 'deleted'){
							//var totalDataRowsValue = document.getElementById("totalDataRows").value;
							//document.getElementById("totalDataRows").value = totalDataRowsValue-1;
							if(document.getElementById("totalDataRows").value == 0){								
								blankRowIsActive = "false";
							}
							$('#tblSystemConfiguration tbody').empty();
							refreshConfigurationData(data.configurationAsJsonArray);
						}
					}
			});
	}
	
	function addMessages(data){
		var infoMessages = data.info_messages;
		var errorMessages = data.error_messages;
		var warningMessages = data.warning_messages;
		if(typeof(infoMessages) != 'undefined' && infoMessages.length >0) {
			for(i=0; i< infoMessages.length; i++) {
				addInfoToNotificationTray(infoMessages[i]);
			}		
		}
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
		}
		//if(typeof(warningMessages) != 'undefined' && warningMessages.length >0) {
		//	for(i=0; i< warningMessages.length; i++) {
		//		addWarnToNotificationTray(warningMessages[i]);
		//	}
		//}
		openTrayNotification();
	}	
</script>


<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>
<div class="Level1">
<div class="systemConfiguration" >
<div class="titleBar" style="width: 950px;">
<div class="headerTitle" style="width: 950px;">Configuration Information</div>
</div>
<div id="confirmationDivDeleteAction"></div>
<div align="center" style="width: 950px;">
<input type="hidden"  id="totalDataRows">
<table border="0" cellpadding="0" cellspacing="0" id="tblSystemConfiguration">
	<thead>		
			<th class="tableHeader" width="10%" >
			Back End Region 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th> 
		
			<th class="tableHeader"  width="25%">
			Back End Region Description 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			
			<th class="tableHeader" width="10%" >
			System 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>	
			
			<th class="tableHeader" width="13%" >
			Functionality 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>	
			
			<th class="tableHeader" width="12%">
			Environment 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>	
			
			<th class="tableHeader" width="10%"  >
			Sender ID/ SrcLgcl 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>	
			
			<th class="tableHeader" width="7%" >
			srcNvmnt 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>	
			
			<th class="tableHeader" width="8%" >
			destNvmnt 
				<span id="systemIcon" class="" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			<th class="tableHeader" width="4%">
			&nbsp; 
				<span id="systemIcon" class="" style="display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
				</span>
			</th>
			
	</thead>
	

	<tbody id="systemConfigurationTableBody" >


		<c:if test="${fn:length(systemConfigurationList) eq 0}">
			<script>
				document.getElementById("totalDataRows").value = 0;
			</script>		
			<tr  id="tableRowNoData">
				<td colspan="9" align="center">
					No Data Found 
				</td>
			</tr>
		</c:if>		
		
		<c:if test="${fn:length(systemConfigurationList) gt 0}">			
			<c:set var="rowCount" value="0" />
			
			<c:forEach items="${systemConfigurationList}" var="systemConfigurationVO">
				<tr style="width: 100%;" class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}" >
					<td width="10%" align="left" >
						<input type="hidden" value="${systemConfigurationVO.systemConfigurationID}" 
							id="hidID${systemConfigurationVO.systemConfigurationID}" >
						<label id="lblBackEndRegion${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.backEndRegion}
						</label>	
						<input type="text" id="txtBackEndRegion${systemConfigurationVO.systemConfigurationID}" 
							style="display:none;width: 95%;" maxlength="25" >			
					</td>
					<td  width="25%" align="left">
						<label id="lblBackEndRegionDescription${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.backEndRegionDescription}
						</label>						
						<input type="text" id="txtBackEndRegionDescription${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width:95%;" maxlength="75" />						
					</td>
					<td  width="10%" align="left">
						<label id="lblSystem${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.system}
						</label>
						
						<select id="ddlSystem${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width: 100%; font-family: Arial, sans-serif;font-size:12px;" >							
							<option value="ACES">ACES</option>
							<option value="FACETS">FACETS</option>
							<option value="WGS/STAR">WGS/STAR</option>							
							<option value="OTHER">OTHER</option>
						</select>
									
					</td>
					<td  width="13%" align="left">
						<label id="lblFunctionality${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.functionality}
						</label>
						<select id="ddlFunctionality${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width: 100%;font-family: Arial, sans-serif;font-size:12px;">
							<option value="GENERATE271">GENERATE271</option>
							<option value="STATIC REPORT">STATIC REPORT</option>
						</select>				
					</td>
					<td  width="12%" align="left">
						<label id="lblEnvironment${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.environment}
						</label>
						
						<select id="ddlEnvironment${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width: 100%;font-family: Arial, sans-serif;font-size:12px;">
							<option value="PRODUCTION">PRODUCTION</option>							
							<option value="TEST">TEST</option>							
						</select>						
						
					</td>
					<td  width="10%" align="left">
						<label id="lblSenderID${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.senderID}
						</label>
						<input type="text" id="txtSenderID${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width:95%;" maxlength="25" >						
					</td>
					<td  width="7%" align="left">
						<label id="lblSourceEnvironment${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.sourceEnvironment}
						</label>
						<input type="text" id="txtSourceEnvironment${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width:95%;" maxlength="25">						
					</td>
					<td  width="8%" align="left">
						<label id="lblDestinationEnvironment${systemConfigurationVO.systemConfigurationID}" >
							${systemConfigurationVO.destinationEnvironment}
						</label>
						<input type="text" id="txtDestinationEnvironment${systemConfigurationVO.systemConfigurationID}" 
							style="display: none;width:95%;" maxlength="25">						
					</td>
					<td width="4%" align="left" class="tblSystemConfigurationButton" >
						
						<a href="#" id="lnkEdit${systemConfigurationVO.systemConfigurationID}">
							<img src="${imageDir}/edit_icon.gif"  title="Edit Configuration" onclick="displayEditConfiguration(this);"/>
						</a>
						
						<a href="#" id="lnkConfirm${systemConfigurationVO.systemConfigurationID}" style="display: none;" >
							<img src="${imageDir}/confirm_icon.JPG"  title="Confirm and save" onclick="editConfiguration(this);"/>
						</a>						
						<a href="#" id="lnkDelete${systemConfigurationVO.systemConfigurationID}" style="margin-left: -2px;">
							<img src="${imageDir}/markAsNotApp.gif"  title="Delete Configuration" onclick="deleteSystemConfiguration(this);"/>
						</a>
						
						<a href="#" id="lnkDiscard${systemConfigurationVO.systemConfigurationID}" style="display: none;margin-left: -4px;">
							<img src="${imageDir}/discard_icon.bmp"  title="Discard Change" onclick="discardEdit(this);"/>
						</a>
					</td>
				</tr>
				<c:set var="rowCount" value="${rowCount + 1}" />
				<script>
					document.getElementById("totalDataRows").value = ${rowCount};
				</script>
			</c:forEach>		
		</c:if>
		</tbody>
	</table>
	<table width="100%;">
		
		<tr>
			<td colspan="9" width="100%" align="right" style="padding-right:10px;padding-top:10px">
				<a href="#" onclick="addNewRowToCreateConfiguration(${rowCount})"><img border="0"
					src="${imageDir}/add.gif" title="Create Configuration" ></a>
			</td>

		</tr>
		
		<tr>
			<td colspan="9" width="100%" align="center" style="padding-bottom:0px">
				<a href="${pageContext.request.contextPath}/simulation/viewSimulationRequest.html"><img border="0" height="27" width="75"
					src="${imageDir}/close_btn.JPG" title="Close"></a>
			</td>
				
		</tr>		
	</table>
	
</div>
	
</div>

<%@include file="/WEB-INF/jspf/messageTray.jspf"%>

</body>
</html>
