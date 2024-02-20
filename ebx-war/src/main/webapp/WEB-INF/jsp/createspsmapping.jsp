<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Enterprise Blue Excohange</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/createeditpage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript">

	function sniffer() {
	   if(screen.height==1024) 
	   {
	      document.getElementById('createEditContainer').style.height = "510px";	   
	   } 
	   else if(screen.height==864)
	   {
	      document.getElementById("createEditContainer").style.height= "410px";
	   }
	}

// Method to submit the values in  'Create Button'
	function createMapping(){
		if(imposeMaxLength('changeCommentsId', '250', 'Change Comments')){
			trimTextValues();
			setDescription();	
			document.forms['submitCreateForm'].action="${context}/createoreditspsmapping/create.html"
			document.forms['submitCreateForm'].submit();
		}
	}
// Cancel action 
	function cancel(){
			var pageFrom = $("#pageFrom").val();
			//alert("pageFrom "+pageFrom);
			var action ;
		    if(pageFrom == "advanceSearchEbx"){
				action = "${context}/createoreditspsmapping/cancelToAdvanceSearchPage.html";
			}else{
				action = "${context}/createoreditspsmapping/cancel.html";
			}
			openConfirmationDialog(action);		
	}
// To check the maxlength
	function imposeMaxLength(elementId, MaxLen, element){
			var valueOfTextArea = $.trim($('#'+elementId).val());
			$('#'+elementId).val(valueOfTextArea);
			if(valueOfTextArea!=null && valueOfTextArea.length > MaxLen) {
				var message = "Text length cannot be greater than " +MaxLen+" for "+element;			
				addErrorToNotificationTray(message);
				openTrayNotification();	
				return false;
	  		}
			return true;
		}

	function markVariableAsNotApplicable(){
	if(imposeMaxLength('changeCommentsId', '250', 'Change Comments')){
			trimTextValues();
			setDescription();	
			document.forms['submitCreateForm'].action="${context}/createoreditspsmapping/notApplicable.html";
			document.forms['submitCreateForm'].submit();
		}
     }
     function printPage(){


  	    	
	var url = "${context}/createoreditspsmapping/printSPSMapping.html";

	
	  var spsId = $("#spsId").val();     
  	  var eb01Val = getTheValuesInTextBox("EB01Id","VALUE");
  	  var EB01Desc = getTheValuesInTextBox("EB01Id","DESC")  

  	  var eb02Val = getTheValuesInTextBox("EB02Id","VALUE"); 
  	   var EB02Desc = getTheValuesInTextBox("EB02Id","DESC"); 
  	  var eb06Val = getTheValuesInTextBox("EB06Id","VALUE");
 
  	  var EB06Desc = getTheValuesInTextBox("EB06Id","DESC");  
  	  var eb09Val = getTheValuesInTextBox("EB09Id","VALUE");
  	   var EB09Desc = getTheValuesInTextBox("EB09Id","DESC");   
  	  var accumulator = getTheValuesInTextBox("accumSpsId","VALUE");  
  	  var accumDesc = getTheValuesInTextBox("accumSpsId","DESC");
  	   var hsd01 = getTheValuesInTextBox("HSD01Id","VALUE");  
  	  var HSD01Desc = getTheValuesInTextBox("HSD01Id","DESC");   
  	  var hsd02 = getTheValuesInTextBox("HSD02Id","VALUE");
  	   var HSD02Desc = getTheValuesInTextBox("HSD02Id","DESC");  
  	  var hsd03 = getTheValuesInTextBox("HSD03Id","VALUE");   
  	  var HSD03Desc = getTheValuesInTextBox("HSD03Id","DESC");
  	   var hsd04 = getTheValuesInTextBox("HSD04Id","VALUE");  
  	  var HSD04Desc = getTheValuesInTextBox("HSD04Id","DESC");   
  	  var hsd05 = getTheValuesInTextBox("HSD05Id","VALUE");
  	   var HSD05Desc = getTheValuesInTextBox("HSD05Id","DESC");   
  	  var hsd06 = getTheValuesInTextBox("HSD06Id","VALUE"); 
  	  var HSD06Desc = getTheValuesInTextBox("HSD06Id","DESC");
  	   var hsd07 = getTheValuesInTextBox("HSD07Id","VALUE"); 
  	  var HSD07Desc = getTheValuesInTextBox("HSD07Id","DESC");  
  	  var hsd08 = getTheValuesInTextBox("HSD08Id","VALUE");
  	   var HSD08Desc = getTheValuesInTextBox("HSD08Id","DESC"); 
  	  var notFinalizedChkBox = $('#notFinalizedChkBox:checked').val();
  

	var param="?createPage=true&spsId="+spsId+"&eb01Val="+eb01Val+"&EB01Desc="+EB01Desc+"&eb02Val="+eb02Val+"&EB02Desc="+EB02Desc+"&eb06Val="+eb06Val+"&EB06Desc="+EB06Desc+"&eb09Val="+eb09Val+"&EB09Desc="+EB09Desc+"&accumulator="+accumulator+"&accumDesc="+accumDesc+"&hsd01="+hsd01+"&HSD01Desc="+HSD01Desc+"&hsd02="+hsd02+"&HSD02Desc="+HSD02Desc+"&hsd03="+hsd03+"&HSD03Desc="+HSD03Desc+"&hsd04="+hsd04+"&HSD04Desc="+HSD04Desc+"&hsd05="+hsd05+"&HSD05Desc="+HSD05Desc+"&hsd06="+hsd06+"&HSD06Desc="+HSD06Desc+"&hsd07="+hsd07+"&HSD07Desc="+HSD07Desc+"&hsd08="+hsd08+"&HSD08Desc="+HSD08Desc+"&notFinalizedChkBox="+notFinalizedChkBox;
	url=url+param;
	//newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  	window.open(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  }	
  function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
  }	

</script>
</head>
<body onload="storeinputs();">
<form name="submitCreateForm" method="post">
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%> 
	
	<input type="hidden" id="pageFrom" name="pageFrom" value="${mapping.pageFrom}" /> 
	<input type="hidden" id="EB01Desc" name="EB01Desc" value="${mapping.eb01.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="EB02Desc" name="EB02Desc" value="${mapping.eb02.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="EB06Desc" name="EB06Desc" value="${mapping.eb06.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="EB09Desc" name="EB09Desc" value="${mapping.eb09.hippaCodeSelectedValues[0].description}" /> 
	
	<input type="hidden" id="accumDesc" name="accumDesc" value="${mapping.accum.hippaCodeSelectedValues[0].description}" /> 

	
	<input type="hidden" id="HSD01Desc" name="HSD01Desc" value="${mapping.hsd01.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD02Desc" name="HSD02Desc" value="${mapping.hsd02.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD03Desc" name="HSD03Desc" value="${mapping.hsd03.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD04Desc" name="HSD04Desc" value="${mapping.hsd04.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD05Desc" name="HSD05Desc" value="${mapping.hsd05.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD06Desc" name="HSD06Desc" value="${mapping.hsd06.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD07Desc" name="HSD07Desc" value="${mapping.hsd07.hippaCodeSelectedValues[0].description}" /> 
	<input type="hidden" id="HSD08Desc" name="HSD08Desc" value="${mapping.hsd08.hippaCodeSelectedValues[0].description}" />

	<input type="hidden" id="token" value="${TRANSACTION_TOKEN_KEY}" name="tokenKey" />	
	<input type="hidden" id="mappingItem" value="SPS" name="mappingItem" />	
	<c:set var="count" value="0" />
	<c:forEach items="${mapping.spsId.spsDetail}" var="spsDetailList" begin ="0">
		<input type="hidden" id="spsFormat${count}" name="spsFormat" value="${mapping.spsId.spsDetail[count].spsDataType}" />
		<c:set var="count" value="${count + 1}"/>
	</c:forEach>
<div class="innerContainer" style="height:96%;"><!-- innerContainer Starts-->
<div id="variableInfoDiv" class="overflowContainerVariable">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="Pd3">
	<THEAD>
		<tr class="createEditTable1">
			<td width="40" class="tableHeader">SPS ID</td>
			<td width="100" class="tableHeader">Description</td>
			<td width="80" class="tableHeader">Type of SPS</td>
			<td width="40" class="tableHeader">PVA</td>
			<td width="60" class="tableHeader">Data type</td>
			<td align="right"><a href="#" onclick='printPage();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a></td>
		</tr>
	</THEAD>
	<TBODY>
		<c:set var="rowCount" value="0" />
		<c:set var="count" value="1" />
		
		<tr>
		<c:set var="pva" value="" />
		<c:set var="type" value="" />
		<c:set var="dataType" value="" />
			<c:if test="${!empty mapping.spsId.spsDetail}">
				<c:set var="pva" value="${mapping.spsId.spsDetail[0].spsPVA}" />
				<c:set var="type" value="${mapping.spsId.spsDetail[0].spsType}" />
				<c:set var="dataType" value="${mapping.spsId.spsDetail[0].spsDataType}" />
			</c:if>
			<td width="30px">${mapping.spsId.spsId}
				<input type="hidden" id="spsId" name="spsId" value="${mapping.spsId.spsId}" />
			</td>			
			<td width="200px">${mapping.spsId.spsDesc}
				<input type="hidden" id="spsIdDesc" name="spsIdDesc" value="${mapping.spsId.spsDesc}" />
			</td>
			<td width="70x">${type}
				<input type="hidden" id="spsType0" name="spsType" value="${type}" />
			</td>
			<td width="70px">${pva}
				<input type="hidden" id="spsPVA0" name="spsPVA" value="${pva}" />
			</td>
			<td width="70px">${dataType}
				<input type="hidden" id="spsDataType0" name="spsDataType" value="${dataType}" />
			</td>
			<td></td>
		</tr>
		<c:set var="variableInfoDivScroll" value="false" />	
					<c:forEach items="${mapping.spsId.spsDetail}" var="spsDetailList" begin ="1">
						<c:set var="variableInfoDivScroll" value="true" />		
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
							<td></td>
							<td></td>
							<td>${spsDetailList.spsType}
								<input type="hidden" id="spsType${count}" name="spsType" value="${mapping.spsId.spsDetail[count].spsType}" />
							</td>
							<td>${spsDetailList.spsPVA}
								<input type="hidden" id="spsPVA${count}" name="spsPVA" value="${mapping.spsId.spsDetail[count].spsPVA}" />
							</td>
							<td>${spsDetailList.spsDataType}
								<input type="hidden" id="spsDataType${count}" name="spsDataType" value="${mapping.spsId.spsDetail[count].spsDataType}" />
							</td>		
							<td></td>					 
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					<c:set var="count" value="${count + 1}"/>
										
					</c:forEach>   
					<input type="hidden" id="totalCount" name="totalCount" value="${count}" />
		
					<c:if test="${variableInfoDivScroll == 'true'}">
						<script>
						$('#variableInfoDiv').height('77px');
						</script>
					</c:if>

	</TBODY>
</table>
</div>
<!-- variableInfoDiv closed -->

<div id="createEditContainer" style="margin-top:10px;"><!--createEditContainer Starts-->
<c:if test="${variableInfoDivScroll == 'true'}">
	<script>
						$('#createEditContainer').height('335px');							
					</script>
</c:if> <!--First Table-->

<table width="950" border="0" cellpadding="0" cellspacing="0"
	class="Pd3" id="ebCodesTable">
	<tr class="createEditTable1-Listdata">
		<td width="143" class="headText">EB01 <a href="#"
			onclick="displayInfo('EB01',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; <a
			href="#"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="EB01" onclick="popupDivHippaSegment('EB01')" /></a></td>
		<td width="95">&#160;</td>
		<td width="104">&#160;</td>
		<td width="143" class="headText" id="eb02td" nowrap>EB02 <a href="#"
			onclick="displayInfo('EB02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; <a
			href="#"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="EB02" onclick="popupDivHippaSegment('EB02')" /></a></td>
		<td width="84">&#160;</td>
		<td width="140">&#160;</td>
		<td class="headText">&#160;</td>
		<td width="121">&#160;</td>
	</tr>


	<tr class="createEditTable1-Listdata alternate">
		<td><input type="text" name="eb01Val" class="inputbox60"
			id="EB01Id" value="${mapping.eb01.hippaCodeSelectedValues[0].value}" />
		</td>
		<td><label id="EB01IdLabel" for="EB01Id" style="font-size:11px">
		<c:out value="${mapping.eb01.hippaCodeSelectedValues[0].description}" />
		</label></td>
		<td>&#160;</td>
		<td><input type="text" name="eb02Val" class="inputbox60"
			id="EB02Id" value="${mapping.eb02.hippaCodeSelectedValues[0].value}" />
		</td>
		<td><label id="EB02IdLabel" for="EB02Id" style="font-size:11px">
		<c:out value="${mapping.eb02.hippaCodeSelectedValues[0].description}" />
		</label></td>
		<td>&#160;</td>
		<td>&#160;</td>
		<td>&#160;</td>
	</tr>
	<tr class="createEditTable1-Listdata">
		<td class="headText" width="143">EB06 <a href="#"
			onclick="displayInfo('EB06',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a
			href="#"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="EB06" onclick="popupDivHippaSegment('EB06')" /></a></td>
		<td>&#160;</td>
		<td>&#160;</td>
		<td class="headText" width="143">EB09 <a href="#" onclick="displayInfo('EB09',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
			<a href="#"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="EB09" onclick="popupDivHippaSegment('EB09')" /></a></td>
		<td>&#160;</td>
		<td>&#160;</td>
		<td>&#160;</td>
		<td>&#160;</td>
	</tr>
	<tr class="createEditTable1-Listdata alternate">
		<td width="90px"><input type="text" name="eb06Val"
			class="inputbox60" id="EB06Id"
			value="${mapping.eb06.hippaCodeSelectedValues[0].value}" /></td>
		<td width="140px"><label id="EB06IdLabel" for="EB06Id"
			style="font-size:11px"> <c:out
			value="${mapping.eb06.hippaCodeSelectedValues[0].description}" /> </label></td>
		<td>&#160;</td>
		<td><input type="text" name="eb09Val" id="EB09Id"
			class="inputbox60"
			value="${mapping.eb09.hippaCodeSelectedValues[0].value}" /></td>
		<td><label id="EB09IdLabel" for="EB09Id" style="font-size:11px">
		<c:out value="${mapping.eb09.hippaCodeSelectedValues[0].description}" />
		</label></td>
		<td>&#160;</td>
		<td>&#160;</td>
		<td>&#160;</td>
	</tr>


</table>

<!--  --> <!--Second Table-->


<table width="950" border="0" cellpadding="0" cellspacing="0"
	class="Pd3 mT5 bT">

		<tr class="createEditTable1-Listdata">
			<td class="headText" colspan="2" nowrap>Accumulators SPS
			ID&#160; <a href="#" onclick="displayInfo('ACCUM',event,'../ajaxhippasegmenttooltip.ajax');">what
			is this?</a>&#160;<a href="#"> <img src="${imageDir}/icon-popup.gif"
				width="15" height="14" id="ACCUM" onclick="popupDivHippaSegment('ACCUMULATOR REFERENCE')" /> </a></td>
			<td>&#160;</td>

		</tr>

		<tr class="alternate">
			<td width="3%"><input type="text" name="accumulator"
				class="inputbox60" id="accumSpsId"
				value="${mapping.accum.hippaCodeSelectedValues[0].value}" /></td>
			<td><label id="accumSpsIdLabel" for="accumSpsId"
				style="font-size:11px"> <c:out
				value="${mapping.accum.hippaCodeSelectedValues[0].description}" />
			</label></td>
			<td>&#160;</td>
		</tr>

</table>

<!--Third Table-->

<table width="950" border="0" cellpadding="0" cellspacing="0"
	class="Pd3 mT5 bT">
	<tr class="createEditTable1-Listdata">
		<td class="headText" colspan="2">HSD01 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD01',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
			<a href="#" tabindex="-1"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="HSD01" onclick="popupDivHippaSegment('HSD01')" /></a></td>

		<td class="headText" colspan="2">HSD02 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;</td>

		<td class="headText" colspan="2">HSD03 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD03',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a
			href="#" tabindex="-1"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="HSD03" onclick="popupDivHippaSegment('HSD03')" /></a></td>
		<td class="headText" colspan="2">HSD04 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD04',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;</td>
	</tr>
	<tr class="createEditTable1-Listdata alternate">
		<td width="90">
			<input type="text" name="hsd01" class="inputbox60"
			id="HSD01Id" value="${mapping.hsd01.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">
			<label id="HSD01IdLabel" for="HSD01Id" style="font-size:11px">
			<c:out value="${mapping.hsd01.hippaCodeSelectedValues[0].description}" /> </label>
		</td>
		<td width="90">
			<input type="text" maxlength="5" name="hsd02"
			class="inputbox60" id="HSD02Id" value="${mapping.hsd02.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">&#160;</td>
		<td width="90"> 
			<input type="text" name="hsd03" class="inputbox60"
			id="HSD03Id" value="${mapping.hsd03.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">
			<label id="HSD03IdLabel" for="HSD03Id" style="font-size:11px"> 
				<c:out value="${mapping.hsd03.hippaCodeSelectedValues[0].description}" /> 
			</label>
		</td>
		<td>
			<input type="text" name="hsd04" class="inputbox60"
			id="HSD04Id" value="${mapping.hsd04.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">&#160;</td>
	</tr>
	<tr class="createEditTable1-Listdata">
		
		<td class="headText" colspan="2">HSD05 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD05',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a
			href="#" tabindex="-1"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="HSD05" onclick="popupDivHippaSegment('HSD05')" /></a></td>
		<td class="headText" colspan="2">HSD06 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD06',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;</td>
		<td class="headText" colspan="2">HSD07 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD07',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a
			href="#" tabindex="-1"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="HSD07" onclick="popupDivHippaSegment('HSD07')" /></a></td>
		<td class="headText" colspan="2">HSD08 <a href="#" tabindex="-1"
			onclick="displayInfo('HSD08',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a
			href="#" tabindex="-1"><img src="${imageDir}/icon-popup.gif" width="15"
			height="14" id="HSD08" onclick="popupDivHippaSegment('HSD08')" /></a></td>
	</tr>
	<tr class="createEditTable1-Listdata alternate">		
		<td width="90">
			<input type="text" maxlength="5" name="hsd05"
			class="inputbox60" id="HSD05Id" value="${mapping.hsd05.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">
			<label id="HSD05IdLabel" for="HSD05Id" style="font-size:11px"> 
				<c:out value="${mapping.hsd05.hippaCodeSelectedValues[0].description}" /> 
			</label>
		</td>
		<td width="90">
			<input type="text" name="hsd06" class="inputbox60"
			id="HSD06Id" value="${mapping.hsd06.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">&#160;</td>
		<td width="90"><input type="text" name="hsd07" class="inputbox60"
			id="HSD07Id" value="${mapping.hsd07.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">
			<label id="HSD07IdLabel" for="HSD07Id" style="font-size:11px">
				<c:out value="${mapping.hsd07.hippaCodeSelectedValues[0].description}" /> 
			</label>
		</td>
		<td width="90">
			<input type="text" maxlength="5" name="hsd08"
			class="inputbox60" id="HSD08Id" value="${mapping.hsd08.hippaCodeSelectedValues[0].value}" />
		</td>
		<td width="450px">
			<label id="HSD08IdLabel" for="HSD08Id" style="font-size:11px"> 
				<c:out value="${mapping.hsd08.hippaCodeSelectedValues[0].description}" /> 
			</label>
		</td>
	</tr>
	

	<tr class="createEditTable1-Listdata">

		<td class="headText" colspan="8">
			<input type="checkbox"
			<c:if test="${!mapping.finalized}">checked</c:if>
			name="notFinalizedChkBox" id="notFinalizedChkBox" value="checked" />
			&#160;Not Finalized&#160; 
			<a href="#" tabindex="-1" onclick="displayInfo('NOT FINALIZED',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;			
		</td>
	</tr>


</table>

<!--Fourth Table-->

<table width="950" border="0" cellpadding="0" cellspacing="0"
	class="Pd3 mT5 bT">
	<tr class="">
		<td width="900px" class="headText">Change Comments</td>
	</tr>
	<tr class="">
		<td><textarea name="userComments" id="changeCommentsId" rows="5"
			cols="110">${changeComments}</textarea></td>
	</tr>
</table>

</div>
<!--createEditContainer Ends--></div>
<!-- innerContainer Ends-->
</div>
<!-- container Ends-->
<div class="footer">
<div class="ajaxIdle" id="ajaxIdleIcon">
<div class="clear"></div>
</div>
<div class="ajaxActive" id="ajaxActiveIcon">
<div class="clear"></div>
</div>
<table border="0" align="center" cellpadding="0" cellspacing="0"
	class="footerTable">
	<tr>
		<td width="20" height="20"><img src="${imageDir}/footer_left.gif"
			width="20" height="20" /></td>
		<td width="0" height="20"><a href="#" onClick="createMapping();">Create</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif"
			width="9" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick='cancel();'>Cancel</a></td>
	    <c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">  	
			<td width="9" height="0"><img src="${imageDir}/seperator.gif"
				width="9" height="20" /></td>
			<td width="0" height="20"><a href="#"
				onclick='markVariableAsNotApplicable();'>Not Applicable</a></td>
	    </c:if>  	
		<td width="18" height="20"><img
			src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
	</tr>
</table>
</div>
</div>
<!-- wrapper Ends-->
<div id="hippaCodePopUpDiv"></div>

</form>
<script type="text/javascript">
	sniffer();
</script>
<div id="confirmationDiv"></div>
<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>
</html>
