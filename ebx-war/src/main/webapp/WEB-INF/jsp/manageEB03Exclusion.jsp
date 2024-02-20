<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	
<script type="text/javascript">
	
var eb03list = new Array();
var eb03ExcludedList = new Array();
var eb03AutoSuggestionList = [];

$(document).ready(function(){
	$('#EB03Id').blur(function() 
	{
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) 
	   {
	   	 $("#EB03IdLabel0").text('');
	   }
	   $('#EB03Id').val($('#EB03Id').val().toUpperCase());
	});  
	$("#EB03Id").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel0").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxeb03autocompletelistforebx.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
						success: function(data) {
						cache: false,
						response(data.rows);
						eb03list = data.rows;
						eb03AutoSuggestionList = eb03list;
						}
					});
				},
		change: function(event, ui) 
		{
			var text = $(this).val();
			var tmpEb03List = [];
			for(var tmp in eb03AutoSuggestionList){
			
				tmpEb03List.push(eb03AutoSuggestionList[tmp].value);
			}
			var positionVal = jQuery.inArray(text, tmpEb03List);
			
			if(!ui.item)
			{
			    if(positionVal > -1)
				{
				    document.getElementById('EB03IdLabel0').innerHTML =  eb03AutoSuggestionList[positionVal].id;   
				}
				else
				{
					displayLabelForSelectedItem(text,eb03list,"EB03IdLabel0","Invalid Eb03 Value");
					document.getElementById('eb03Desc').value =document.getElementById('EB03IdLabel0').innerHTML;
				}
			}
		}
								
	})
  });
  				
function createEb03Exclusion()  
{
   document.getElementById('eb03Desc').value = document.getElementById('EB03IdLabel0').innerHTML; 
   
    var eb03Desc = document.getElementById('eb03Desc').value;  
    var eb03Val = document.getElementById('EB03Id').value;
    var tableId = document.getElementById('exclusiontbody');
	var tableRow = tableId.getElementsByTagName('tr');
	
	for (var t = 0; t < tableRow.length; t++)
	{
    	eb03ExcludedList.push(tableRow[t].firstElementChild.textContent);
	}
    if(eb03ExcludedList.indexOf(eb03Val) > -1)
    {
		alert("Eb03 Value is Already Excluded");
		return;
	}

    if(eb03Val=="")
    {
	    alert("Please enter Eb03 value");
	    return;
    }
    if(eb03Desc=="Invalid Eb03 Value")
	{
	  alert("Invalid Eb03 value");
	  return;
	}
		
    $.ajax({
		url: "${context}/managespidereb03exclusion/createEB03Exclusion.ajax",
		dataType: "html",
		type: "POST",
		data: "&eb03Label="+encodeURIComponent(eb03Desc)+"&eb03Val="+encodeURIComponent(eb03Val),
		success: function(data) {
		
		    alert("EB03 exclusion saved successfully");
			exclusionPage();
		}
	});
		

}

function exclusionPage()
{
      document.forms['categoryMappingForm'].action="${context}/managespidereb03exclusion/showManageEB03ExclusionPage.html";
	  document.forms['categoryMappingForm'].submit();
}

function deleteRowForRuleEb03(eb03,ebtr)
{
  if(confirm('Are you sure you want to delete ? '))
  {
	   $.ajax({
				url: "${context}/managespidereb03exclusion/deleteEb03ExclusionMapping.ajax",
				dataType: "html",
				type: "POST",
				data: "&eb03Value="+encodeURIComponent(eb03),
				success: function(data) 
				{
					$('table#locateTableResults tr#'+ebtr).remove();
					alert("Deleted Successfully !!");				
				}
			});
			
   }
   else
   {
     return false;
   }
		
}

function cancel()
{
	   document.getElementById('EB03IdLabel0').innerHTML = ''; 
	   document.getElementById('eb03Desc').value = '';  
	   document.getElementById('EB03Id').value = '';
		/* document.forms['categoryMappingForm'].action="../ebxspiderworkflow/cancel.html";
        document.forms["categoryMappingForm"].submit(); */
}

function back()
{
    document.forms['categoryMappingForm'].action="../ebxspiderworkflow/back.html";
    document.forms["categoryMappingForm"].submit();
}

function filterTable(){
  var value = $('#filter_age').val();
        $('#locateTableResults tr').not(":first").filter(function(){
          $(this).toggle( ($(this).text().toLowerCase().indexOf(value.toLowerCase()) > -1)  ||  ($(this).text().toUpperCase().indexOf(value.toUpperCase()) > -1))
      }).show();
 } 			
  				
$(document).ready(function(){  		
	   $('#filter_age').bind( "click", function(){
	      var value = $(this).val();
	        $('#locateTableResults tr').not(":first").filter(function(){
	          $(this).toggle( ($(this).text().toLowerCase().indexOf(value.toLowerCase()) > -1)  ||  ($(this).text().toUpperCase().indexOf(value.toUpperCase()) > -1))
	      }).show();
	  });		
  });
</script>
</head>

<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<form name="categoryMappingForm" method="POST">

<%@include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height: 98%" class="Pd3">
<TABLE>
	<TR style="width:960px;" class="Pd3">
	    <td align="center" colspan="6"><input type="text" id="deleteMsg" value="Deleted Successfully !!" style="display: none;" ></td>
	</TR>
</TABLE>

<div id="exclusionEditContainer" style="height: 120px">
<div id="exclusionSectionOne1" style="height: 100px">
<div id="exclusionTitleBarSectionOne1">
  <div class="headerTitleExclusion">Manage EB03 – Exclusion</div>
</div>
<div id="exclusionContentSectionOne1">
	<table style="margin-top: 3px; width: 960px;" class="mt10 mL10 Pd3 shadedText" id="exclusionTableWidth1" border="0" cellspacing="0" cellpadding="0">
		<TBODY>
			<tr class="createEditTable1-Listdata" style="padding-top: 0px">&nbsp;&nbsp;&nbsp;
				<td width="120px" class="headTextExclusions">EB03</td>
				<td width="152px"><input type="text" class="inputbox60" id="EB03Id" name="eb03Val" value="${EB03Id1}" onkeypress="enterKeyPress(event);" /></td>
	 			<td>
		 			<label id="EB03IdLabel0"  for="selectedEb03" style="font-size: 11px"></label>
					<input type="hidden" id="eb03Desc" name="eb03Label"	/>
				</td>                             
				<td class="headText" width="152px">&nbsp;</td>
			</tr>
			<tr>
				<td align="center" colspan="6">
					<a href="#">
					   <IMG id="Locate" src="${imageDir}/create_but.gif" width="70" height="18" alt="Create" onclick="createEb03Exclusion();">
				    </a>
					<a href="#">
					   <IMG id="Cancel" src="${imageDir}/cancel_but.gif" width="70" height="18" alt="Cancel" onclick="cancel();">
					</a>
				</td>
			</tr>
		</TBODY>
	</table>
</div>
</div>

<div id="exclusionSectionTwo1" style="display: none">
	<div class="exclusionTitleBarSectionTwo1">
		<table class="mappedTable1 pd3 shadedText" border="0" width="100%">
			<THEAD>
				<tr>
					<th id="systemId" width="10%" class="tableHeader">EB03</th>
				</tr>
			</THEAD>
		</table>
	</div>
</div>
<div id="exclusionSectionThree1"
	style="display: none; height: 220px">
	<div class="exclusionTitleBarSectionTwo">
		<div class="headerTitleSub">Manage</div>
	</div>
	<!-- <div class="exclusionSectionThreeSub1"> -->
	<div id="editpage">
		<jsp:include flush="true" page="headerRuleToEB03EditMapping.jsp"></jsp:include>
	</div>
  </div>
</div>

<div class="LocateResultTableDiv"
	style="margin-left: 14px; width: 960px;">
	<label style="margin-left: 73%;">EB03 Filter: </label>   
	<input style="float:right;margin-right: 34px; margin-bottom: 5px;" type="search" id="filter_age" name="filter eb03"  onkeyup="filterTable()"   placeholder="Eb03 Filter" />
	<br>
	<table border="0" cellpadding="0" cellspacing="0"
		class="locateTable locateT shadedText" id="locateTableResults"
		style="width: 930px;">
		<THEAD>
			<tr class="UnmappedVariables locateResultsTable">
				<th width="300px" class="tableHeader">EB03 </th>
				<th width="400px" class="tableHeader">EB03_Description </th>
				<th width="200px" class="tableHeader">&nbsp;&nbsp;&nbsp;Actions	</th>
			</tr>
		</THEAD>

		<TBODY  id="exclusiontbody">
			<c:set var="locateEB03ExclusionCount" value="0" />
			<c:if test="${! empty locateEb03Exclusions}">
				<c:forEach items="${locateEb03Exclusions}" var="locateEb03ExclusionList" varStatus="locateEb03Exclusion">
					<tr class="${locateEB03ExclusionCount mod 2 == 0 ? 'white-bg' : 'alternate'}" id="exclusionTr${locateEb03Exclusion.index}">                              
						<td width="80px">${locateEb03ExclusionList.eb03}<input type="hidden" id="eb03Excluded${locateEb03Exclusion.index}" value="${locateEb03ExclusionList.eb03}"/></td>
						<td width="80px">${locateEb03ExclusionList.eb03Description}</td>
						<td width="130px" id="status_${locateResultList.umRuleId}">
							<a href="#">
							   <img alt="Delete Row" title="Delete Row" id="deleteRow" src="${imageDir}/delete_button.gif" width="19" height="19"
								onclick='deleteRowForRuleEb03("${locateEb03ExclusionList.eb03}","exclusionTr${locateEb03Exclusion.index}");' />
							</a>
						</td>
					</tr>
					<c:set var="locateEB03ExclusionCount" value="${locateEB03ExclusionCount + 1}" />
				</c:forEach>
			</c:if>
			<c:if test="${empty locateEb03Exclusions}">
				<tr>
					<td colspan="5" align="center">No Results matching your search criteria</td>
				</tr>
			</c:if>
		</TBODY>
	</table>

</div>
</div>

<div id="initialLoad" class="footer">
	<div class="ajaxIdle" id="ajaxIdleIcon">
		<div class="clear"></div>
	</div>
	<div class="ajaxActive" id="ajaxActiveIcon">
		<div class="clear"></div>
	</div>

	<table border="0" align="center" cellpadding="0" cellspacing="0"
		class="footerTable">
		<tr>
			<td width="18" height="20"><img
				src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
			<td width="0" height="20"><a href="#" onclick='back()'>Back</a></td>
			<td width="18" height="20"><img
				src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
		</tr>
	</table>

 </div>

</form>

</body>
</html>